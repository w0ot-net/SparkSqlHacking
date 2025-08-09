package com.github.difflib;

import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.ChangeDelta;
import com.github.difflib.patch.Chunk;
import com.github.difflib.patch.Patch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class UnifiedDiffUtils {
   private static final Pattern UNIFIED_DIFF_CHUNK_REGEXP = Pattern.compile("^@@\\s+-(?:(\\d+)(?:,(\\d+))?)\\s+\\+(?:(\\d+)(?:,(\\d+))?)\\s+@@$");
   private static final String NULL_FILE_INDICATOR = "/dev/null";

   public static Patch parseUnifiedDiff(List diff) {
      boolean inPrelude = true;
      List<String[]> rawChunk = new ArrayList();
      Patch<String> patch = new Patch();
      int old_ln = 0;
      int new_ln = 0;

      for(String line : diff) {
         if (inPrelude) {
            if (line.startsWith("+++")) {
               inPrelude = false;
            }
         } else {
            Matcher m = UNIFIED_DIFF_CHUNK_REGEXP.matcher(line);
            if (m.find()) {
               processLinesInPrevChunk(rawChunk, patch, old_ln, new_ln);
               old_ln = m.group(1) == null ? 1 : Integer.parseInt(m.group(1));
               new_ln = m.group(3) == null ? 1 : Integer.parseInt(m.group(3));
               if (old_ln == 0) {
                  old_ln = 1;
               }

               if (new_ln == 0) {
                  new_ln = 1;
               }
            } else if (line.length() > 0) {
               String tag = line.substring(0, 1);
               String rest = line.substring(1);
               if (" ".equals(tag) || "+".equals(tag) || "-".equals(tag)) {
                  rawChunk.add(new String[]{tag, rest});
               }
            } else {
               rawChunk.add(new String[]{" ", ""});
            }
         }
      }

      processLinesInPrevChunk(rawChunk, patch, old_ln, new_ln);
      return patch;
   }

   private static void processLinesInPrevChunk(List rawChunk, Patch patch, int old_ln, int new_ln) {
      if (!rawChunk.isEmpty()) {
         List<String> oldChunkLines = new ArrayList();
         List<String> newChunkLines = new ArrayList();
         List<Integer> removePosition = new ArrayList();
         List<Integer> addPosition = new ArrayList();
         int removeNum = 0;
         int addNum = 0;

         for(String[] raw_line : rawChunk) {
            String tag = raw_line[0];
            String rest = raw_line[1];
            if (" ".equals(tag) || "-".equals(tag)) {
               ++removeNum;
               oldChunkLines.add(rest);
               if ("-".equals(tag)) {
                  removePosition.add(old_ln - 1 + removeNum);
               }
            }

            if (" ".equals(tag) || "+".equals(tag)) {
               ++addNum;
               newChunkLines.add(rest);
               if ("+".equals(tag)) {
                  addPosition.add(new_ln - 1 + addNum);
               }
            }
         }

         patch.addDelta(new ChangeDelta(new Chunk(old_ln - 1, oldChunkLines, removePosition), new Chunk(new_ln - 1, newChunkLines, addPosition)));
         rawChunk.clear();
      }

   }

   public static List generateUnifiedDiff(String originalFileName, String revisedFileName, List originalLines, Patch patch, int contextSize) {
      if (patch.getDeltas().isEmpty()) {
         return new ArrayList();
      } else {
         List<String> ret = new ArrayList();
         ret.add("--- " + (String)Optional.ofNullable(originalFileName).orElse("/dev/null"));
         ret.add("+++ " + (String)Optional.ofNullable(revisedFileName).orElse("/dev/null"));
         List<AbstractDelta<String>> patchDeltas = new ArrayList(patch.getDeltas());
         List<AbstractDelta<String>> deltas = new ArrayList();
         AbstractDelta<String> delta = (AbstractDelta)patchDeltas.get(0);
         deltas.add(delta);
         if (patchDeltas.size() > 1) {
            for(int i = 1; i < patchDeltas.size(); ++i) {
               int position = delta.getSource().getPosition();
               AbstractDelta<String> nextDelta = (AbstractDelta)patchDeltas.get(i);
               if (position + delta.getSource().size() + contextSize >= nextDelta.getSource().getPosition() - contextSize) {
                  deltas.add(nextDelta);
               } else {
                  List<String> curBlock = processDeltas(originalLines, deltas, contextSize, false);
                  ret.addAll(curBlock);
                  deltas.clear();
                  deltas.add(nextDelta);
               }

               delta = nextDelta;
            }
         }

         List<String> curBlock = processDeltas(originalLines, deltas, contextSize, patchDeltas.size() == 1 && originalFileName == null);
         ret.addAll(curBlock);
         return ret;
      }
   }

   private static List processDeltas(List origLines, List deltas, int contextSize, boolean newFile) {
      List<String> buffer = new ArrayList();
      int origTotal = 0;
      int revTotal = 0;
      AbstractDelta<String> curDelta = (AbstractDelta)deltas.get(0);
      int origStart;
      if (newFile) {
         origStart = 0;
      } else {
         origStart = curDelta.getSource().getPosition() + 1 - contextSize;
         if (origStart < 1) {
            origStart = 1;
         }
      }

      int revStart = curDelta.getTarget().getPosition() + 1 - contextSize;
      if (revStart < 1) {
         revStart = 1;
      }

      int contextStart = curDelta.getSource().getPosition() - contextSize;
      if (contextStart < 0) {
         contextStart = 0;
      }

      for(int line = contextStart; line < curDelta.getSource().getPosition(); ++line) {
         buffer.add(" " + (String)origLines.get(line));
         ++origTotal;
         ++revTotal;
      }

      buffer.addAll(getDeltaText(curDelta));
      origTotal += curDelta.getSource().getLines().size();
      revTotal += curDelta.getTarget().getLines().size();

      for(int deltaIndex = 1; deltaIndex < deltas.size(); ++deltaIndex) {
         AbstractDelta<String> nextDelta = (AbstractDelta)deltas.get(deltaIndex);
         int intermediateStart = curDelta.getSource().getPosition() + curDelta.getSource().getLines().size();

         for(int var17 = intermediateStart; var17 < nextDelta.getSource().getPosition(); ++var17) {
            buffer.add(" " + (String)origLines.get(var17));
            ++origTotal;
            ++revTotal;
         }

         buffer.addAll(getDeltaText(nextDelta));
         origTotal += nextDelta.getSource().getLines().size();
         revTotal += nextDelta.getTarget().getLines().size();
         curDelta = nextDelta;
      }

      contextStart = curDelta.getSource().getPosition() + curDelta.getSource().getLines().size();

      for(int var18 = contextStart; var18 < contextStart + contextSize && var18 < origLines.size(); ++var18) {
         buffer.add(" " + (String)origLines.get(var18));
         ++origTotal;
         ++revTotal;
      }

      StringBuilder header = new StringBuilder();
      header.append("@@ -");
      header.append(origStart);
      header.append(",");
      header.append(origTotal);
      header.append(" +");
      header.append(revStart);
      header.append(",");
      header.append(revTotal);
      header.append(" @@");
      buffer.add(0, header.toString());
      return buffer;
   }

   private static List getDeltaText(AbstractDelta delta) {
      List<String> buffer = new ArrayList();

      for(String line : delta.getSource().getLines()) {
         buffer.add("-" + line);
      }

      for(String line : delta.getTarget().getLines()) {
         buffer.add("+" + line);
      }

      return buffer;
   }

   private UnifiedDiffUtils() {
   }

   public static List generateOriginalAndDiff(List original, List revised) {
      return generateOriginalAndDiff(original, revised, (String)null, (String)null);
   }

   public static List generateOriginalAndDiff(List original, List revised, String originalFileName, String revisedFileName) {
      String originalFileNameTemp = originalFileName;
      String revisedFileNameTemp = originalFileName;
      if (originalFileName == null) {
         originalFileNameTemp = "original";
      }

      if (originalFileName == null) {
         revisedFileNameTemp = "revised";
      }

      Patch<String> patch = DiffUtils.diff(original, revised);
      List<String> unifiedDiff = generateUnifiedDiff(originalFileNameTemp, revisedFileNameTemp, original, patch, 0);
      if (unifiedDiff.isEmpty()) {
         unifiedDiff.add("--- " + originalFileNameTemp);
         unifiedDiff.add("+++ " + revisedFileNameTemp);
         unifiedDiff.add("@@ -0,0 +0,0 @@");
      } else if (unifiedDiff.size() >= 3 && !((String)unifiedDiff.get(2)).contains("@@ -1,")) {
         unifiedDiff.set(1, unifiedDiff.get(1));
         unifiedDiff.add(2, "@@ -0,0 +0,0 @@");
      }

      List<String> originalWithPrefix = (List)original.stream().map((v) -> " " + v).collect(Collectors.toList());
      return insertOrig(originalWithPrefix, unifiedDiff);
   }

   private static List insertOrig(List original, List unifiedDiff) {
      List<String> result = new ArrayList();
      List<List<String>> diffList = new ArrayList();
      List<String> diff = new ArrayList();

      for(int i = 0; i < unifiedDiff.size(); ++i) {
         String u = (String)unifiedDiff.get(i);
         if (u.startsWith("@@") && !"@@ -0,0 +0,0 @@".equals(u) && !u.contains("@@ -1,")) {
            List<String> twoList = new ArrayList();
            twoList.addAll(diff);
            diffList.add(twoList);
            diff.clear();
            diff.add(u);
         } else {
            if (i == unifiedDiff.size() - 1) {
               diff.add(u);
               List<String> twoList = new ArrayList();
               twoList.addAll(diff);
               diffList.add(twoList);
               diff.clear();
               break;
            }

            diff.add(u);
         }
      }

      insertOrig(diffList, result, original);
      return result;
   }

   private static void insertOrig(List diffList, List result, List original) {
      for(int i = 0; i < diffList.size(); ++i) {
         List<String> diff = (List)diffList.get(i);
         List<String> nexDiff = i == diffList.size() - 1 ? null : (List)diffList.get(i + 1);
         String simb = i == 0 ? (String)diff.get(2) : (String)diff.get(0);
         String nexSimb = nexDiff == null ? null : (String)nexDiff.get(0);
         insert(result, diff);
         Map<String, Integer> map = getRowMap(simb);
         if (null != nexSimb) {
            Map<String, Integer> nexMap = getRowMap(nexSimb);
            int start = 0;
            if ((Integer)map.get("orgRow") != 0) {
               start = (Integer)map.get("orgRow") + (Integer)map.get("orgDel") - 1;
            }

            int end = (Integer)nexMap.get("revRow") - 2;
            insert(result, getOrigList(original, start, end));
         }

         int start = (Integer)map.get("orgRow") + (Integer)map.get("orgDel") - 1;
         start = start == -1 ? 0 : start;
         if (simb.contains("@@ -1,") && null == nexSimb && (Integer)map.get("orgDel") != original.size()) {
            insert(result, getOrigList(original, start, original.size() - 1));
         } else if (null == nexSimb && (Integer)map.get("orgRow") + (Integer)map.get("orgDel") - 1 < original.size()) {
            insert(result, getOrigList(original, start, original.size() - 1));
         }
      }

   }

   private static void insert(List result, List noChangeContent) {
      for(String ins : noChangeContent) {
         result.add(ins);
      }

   }

   private static Map getRowMap(String str) {
      Map<String, Integer> map = new HashMap();
      if (str.startsWith("@@")) {
         String[] sp = str.split(" ");
         String org = sp[1];
         String[] orgSp = org.split(",");
         map.put("orgRow", Integer.valueOf(orgSp[0].substring(1)));
         map.put("orgDel", Integer.valueOf(orgSp[1]));
         String[] revSp = org.split(",");
         map.put("revRow", Integer.valueOf(revSp[0].substring(1)));
         map.put("revAdd", Integer.valueOf(revSp[1]));
      }

      return map;
   }

   private static List getOrigList(List originalWithPrefix, int start, int end) {
      List<String> list = new ArrayList();
      if (originalWithPrefix.size() >= 1 && start <= end && end < originalWithPrefix.size()) {
         for(int startTemp = start; startTemp <= end; ++startTemp) {
            list.add(originalWithPrefix.get(startTemp));
         }
      }

      return list;
   }
}
