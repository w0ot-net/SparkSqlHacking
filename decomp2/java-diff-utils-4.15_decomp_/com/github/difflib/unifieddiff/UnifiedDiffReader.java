package com.github.difflib.unifieddiff;

import com.github.difflib.patch.ChangeDelta;
import com.github.difflib.patch.Chunk;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UnifiedDiffReader {
   static final Pattern UNIFIED_DIFF_CHUNK_REGEXP = Pattern.compile("^@@\\s+-(?:(\\d+)(?:,(\\d+))?)\\s+\\+(?:(\\d+)(?:,(\\d+))?)\\s+@@");
   static final Pattern TIMESTAMP_REGEXP = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}[T ]\\d{2}:\\d{2}:\\d{2}\\.\\d{3,})(?: [+-]\\d+)?");
   private final InternalUnifiedDiffReader READER;
   private final UnifiedDiff data = new UnifiedDiff();
   private final UnifiedDiffLine DIFF_COMMAND = new UnifiedDiffLine(true, "^diff\\s", this::processDiff);
   private final UnifiedDiffLine SIMILARITY_INDEX = new UnifiedDiffLine(true, "^similarity index (\\d+)%$", this::processSimilarityIndex);
   private final UnifiedDiffLine INDEX = new UnifiedDiffLine(true, "^index\\s[\\da-zA-Z]+\\.\\.[\\da-zA-Z]+(\\s(\\d+))?$", this::processIndex);
   private final UnifiedDiffLine FROM_FILE = new UnifiedDiffLine(true, "^---\\s", this::processFromFile);
   private final UnifiedDiffLine TO_FILE = new UnifiedDiffLine(true, "^\\+\\+\\+\\s", this::processToFile);
   private final UnifiedDiffLine RENAME_FROM = new UnifiedDiffLine(true, "^rename\\sfrom\\s(.+)$", this::processRenameFrom);
   private final UnifiedDiffLine RENAME_TO = new UnifiedDiffLine(true, "^rename\\sto\\s(.+)$", this::processRenameTo);
   private final UnifiedDiffLine COPY_FROM = new UnifiedDiffLine(true, "^copy\\sfrom\\s(.+)$", this::processCopyFrom);
   private final UnifiedDiffLine COPY_TO = new UnifiedDiffLine(true, "^copy\\sto\\s(.+)$", this::processCopyTo);
   private final UnifiedDiffLine NEW_FILE_MODE = new UnifiedDiffLine(true, "^new\\sfile\\smode\\s(\\d+)", this::processNewFileMode);
   private final UnifiedDiffLine DELETED_FILE_MODE = new UnifiedDiffLine(true, "^deleted\\sfile\\smode\\s(\\d+)", this::processDeletedFileMode);
   private final UnifiedDiffLine OLD_MODE = new UnifiedDiffLine(true, "^old\\smode\\s(\\d+)", this::processOldMode);
   private final UnifiedDiffLine NEW_MODE = new UnifiedDiffLine(true, "^new\\smode\\s(\\d+)", this::processNewMode);
   private final UnifiedDiffLine BINARY_ADDED = new UnifiedDiffLine(true, "^Binary\\sfiles\\s/dev/null\\sand\\sb/(.+)\\sdiffer", this::processBinaryAdded);
   private final UnifiedDiffLine BINARY_DELETED = new UnifiedDiffLine(true, "^Binary\\sfiles\\sa/(.+)\\sand\\s/dev/null\\sdiffer", this::processBinaryDeleted);
   private final UnifiedDiffLine BINARY_EDITED = new UnifiedDiffLine(true, "^Binary\\sfiles\\sa/(.+)\\sand\\sb/(.+)\\sdiffer", this::processBinaryEdited);
   private final UnifiedDiffLine CHUNK;
   private final UnifiedDiffLine LINE_NORMAL;
   private final UnifiedDiffLine LINE_DEL;
   private final UnifiedDiffLine LINE_ADD;
   private UnifiedDiffFile actualFile;
   private static final Logger LOG = Logger.getLogger(UnifiedDiffReader.class.getName());
   private List originalTxt;
   private List revisedTxt;
   private List addLineIdxList;
   private List delLineIdxList;
   private int old_ln;
   private int old_size;
   private int new_ln;
   private int new_size;
   private int delLineIdx;
   private int addLineIdx;

   UnifiedDiffReader(Reader reader) {
      this.CHUNK = new UnifiedDiffLine(false, UNIFIED_DIFF_CHUNK_REGEXP, this::processChunk);
      this.LINE_NORMAL = new UnifiedDiffLine("^\\s", this::processNormalLine);
      this.LINE_DEL = new UnifiedDiffLine("^-", this::processDelLine);
      this.LINE_ADD = new UnifiedDiffLine("^\\+", this::processAddLine);
      this.originalTxt = new ArrayList();
      this.revisedTxt = new ArrayList();
      this.addLineIdxList = new ArrayList();
      this.delLineIdxList = new ArrayList();
      this.delLineIdx = 0;
      this.addLineIdx = 0;
      this.READER = new InternalUnifiedDiffReader(reader);
   }

   private UnifiedDiff parse() throws IOException, UnifiedDiffParserException {
      String line = this.READER.readLine();

      while(line != null) {
         String headerTxt = "";
         LOG.log(Level.FINE, "header parsing");

         while(line != null) {
            LOG.log(Level.FINE, "parsing line {0}", line);
            if (this.validLine(line, this.DIFF_COMMAND, this.SIMILARITY_INDEX, this.INDEX, this.FROM_FILE, this.TO_FILE, this.RENAME_FROM, this.RENAME_TO, this.COPY_FROM, this.COPY_TO, this.NEW_FILE_MODE, this.DELETED_FILE_MODE, this.OLD_MODE, this.NEW_MODE, this.BINARY_ADDED, this.BINARY_DELETED, this.BINARY_EDITED, this.CHUNK)) {
               break;
            }

            headerTxt = headerTxt + line + "\n";
            line = this.READER.readLine();
         }

         if (!"".equals(headerTxt)) {
            this.data.setHeader(headerTxt);
         }

         if (line != null && !this.CHUNK.validLine(line)) {
            this.initFileIfNecessary();

            while(line != null && !this.CHUNK.validLine(line)) {
               if (!this.processLine(line, this.DIFF_COMMAND, this.SIMILARITY_INDEX, this.INDEX, this.FROM_FILE, this.TO_FILE, this.RENAME_FROM, this.RENAME_TO, this.COPY_FROM, this.COPY_TO, this.NEW_FILE_MODE, this.DELETED_FILE_MODE, this.OLD_MODE, this.NEW_MODE, this.BINARY_ADDED, this.BINARY_DELETED, this.BINARY_EDITED)) {
                  throw new UnifiedDiffParserException("expected file start line not found");
               }

               line = this.READER.readLine();
            }
         }

         if (line != null) {
            this.processLine(line, this.CHUNK);

            while((line = this.READER.readLine()) != null) {
               line = this.checkForNoNewLineAtTheEndOfTheFile(line);
               if (!this.processLine(line, this.LINE_NORMAL, this.LINE_ADD, this.LINE_DEL)) {
                  throw new UnifiedDiffParserException("expected data line not found");
               }

               if (this.originalTxt.size() == this.old_size && this.revisedTxt.size() == this.new_size || this.old_size == 0 && this.new_size == 0 && this.originalTxt.size() == this.old_ln && this.revisedTxt.size() == this.new_ln) {
                  this.finalizeChunk();
                  break;
               }
            }

            line = this.READER.readLine();
            line = this.checkForNoNewLineAtTheEndOfTheFile(line);
         }

         if (line == null || line.startsWith("--") && !line.startsWith("---")) {
            break;
         }
      }

      if (this.READER.ready()) {
         String tailTxt;
         for(tailTxt = ""; this.READER.ready(); tailTxt = tailTxt + this.READER.readLine()) {
            if (tailTxt.length() > 0) {
               tailTxt = tailTxt + "\n";
            }
         }

         this.data.setTailTxt(tailTxt);
      }

      return this.data;
   }

   private String checkForNoNewLineAtTheEndOfTheFile(String line) throws IOException {
      if ("\\ No newline at end of file".equals(line)) {
         this.actualFile.setNoNewLineAtTheEndOfTheFile(true);
         return this.READER.readLine();
      } else {
         return line;
      }
   }

   static String[] parseFileNames(String line) {
      String[] split = line.split(" ");
      return new String[]{split[2].replaceAll("^a/", ""), split[3].replaceAll("^b/", "")};
   }

   public static UnifiedDiff parseUnifiedDiff(InputStream stream) throws IOException, UnifiedDiffParserException {
      UnifiedDiffReader parser = new UnifiedDiffReader(new BufferedReader(new InputStreamReader(stream)));
      return parser.parse();
   }

   private boolean processLine(String line, UnifiedDiffLine... rules) throws UnifiedDiffParserException {
      if (line == null) {
         return false;
      } else {
         for(UnifiedDiffLine rule : rules) {
            if (rule.processLine(line)) {
               LOG.fine("  >>> processed rule " + rule.toString());
               return true;
            }
         }

         LOG.warning("  >>> no rule matched " + line);
         return false;
      }
   }

   private boolean validLine(String line, UnifiedDiffLine... rules) {
      if (line == null) {
         return false;
      } else {
         for(UnifiedDiffLine rule : rules) {
            if (rule.validLine(line)) {
               LOG.fine("  >>> accepted rule " + rule.toString());
               return true;
            }
         }

         return false;
      }
   }

   private void initFileIfNecessary() {
      if (this.originalTxt.isEmpty() && this.revisedTxt.isEmpty()) {
         this.actualFile = null;
         if (this.actualFile == null) {
            this.actualFile = new UnifiedDiffFile();
            this.data.addFile(this.actualFile);
         }

      } else {
         throw new IllegalStateException();
      }
   }

   private void processDiff(MatchResult match, String line) {
      LOG.log(Level.FINE, "start {0}", line);
      String[] fromTo = parseFileNames(this.READER.lastLine());
      this.actualFile.setFromFile(fromTo[0]);
      this.actualFile.setToFile(fromTo[1]);
      this.actualFile.setDiffCommand(line);
   }

   private void processSimilarityIndex(MatchResult match, String line) {
      this.actualFile.setSimilarityIndex(Integer.valueOf(match.group(1)));
   }

   private void finalizeChunk() {
      if (!this.originalTxt.isEmpty() || !this.revisedTxt.isEmpty()) {
         this.actualFile.getPatch().addDelta(new ChangeDelta(new Chunk(this.old_ln - 1, this.originalTxt, this.delLineIdxList), new Chunk(this.new_ln - 1, this.revisedTxt, this.addLineIdxList)));
         this.old_ln = 0;
         this.new_ln = 0;
         this.originalTxt.clear();
         this.revisedTxt.clear();
         this.addLineIdxList.clear();
         this.delLineIdxList.clear();
         this.delLineIdx = 0;
         this.addLineIdx = 0;
      }

   }

   private void processNormalLine(MatchResult match, String line) {
      String cline = line.substring(1);
      this.originalTxt.add(cline);
      this.revisedTxt.add(cline);
      ++this.delLineIdx;
      ++this.addLineIdx;
   }

   private void processAddLine(MatchResult match, String line) {
      String cline = line.substring(1);
      this.revisedTxt.add(cline);
      ++this.addLineIdx;
      this.addLineIdxList.add(this.new_ln - 1 + this.addLineIdx);
   }

   private void processDelLine(MatchResult match, String line) {
      String cline = line.substring(1);
      this.originalTxt.add(cline);
      ++this.delLineIdx;
      this.delLineIdxList.add(this.old_ln - 1 + this.delLineIdx);
   }

   private void processChunk(MatchResult match, String chunkStart) {
      this.old_ln = toInteger(match, 1, 1);
      this.old_size = toInteger(match, 2, 1);
      this.new_ln = toInteger(match, 3, 1);
      this.new_size = toInteger(match, 4, 1);
      if (this.old_ln == 0) {
         this.old_ln = 1;
      }

      if (this.new_ln == 0) {
         this.new_ln = 1;
      }

   }

   private static Integer toInteger(MatchResult match, int group, int defValue) throws NumberFormatException {
      return Integer.valueOf(Objects.toString(match.group(group), "" + defValue));
   }

   private void processIndex(MatchResult match, String line) {
      LOG.log(Level.FINE, "index {0}", line);
      this.actualFile.setIndex(line.substring(6));
   }

   private void processFromFile(MatchResult match, String line) {
      this.actualFile.setFromFile(this.extractFileName(line));
      this.actualFile.setFromTimestamp(this.extractTimestamp(line));
   }

   private void processToFile(MatchResult match, String line) {
      this.actualFile.setToFile(this.extractFileName(line));
      this.actualFile.setToTimestamp(this.extractTimestamp(line));
   }

   private void processRenameFrom(MatchResult match, String line) {
      this.actualFile.setRenameFrom(match.group(1));
   }

   private void processRenameTo(MatchResult match, String line) {
      this.actualFile.setRenameTo(match.group(1));
   }

   private void processCopyFrom(MatchResult match, String line) {
      this.actualFile.setCopyFrom(match.group(1));
   }

   private void processCopyTo(MatchResult match, String line) {
      this.actualFile.setCopyTo(match.group(1));
   }

   private void processNewFileMode(MatchResult match, String line) {
      this.actualFile.setNewFileMode(match.group(1));
   }

   private void processDeletedFileMode(MatchResult match, String line) {
      this.actualFile.setDeletedFileMode(match.group(1));
   }

   private void processOldMode(MatchResult match, String line) {
      this.actualFile.setOldMode(match.group(1));
   }

   private void processNewMode(MatchResult match, String line) {
      this.actualFile.setNewMode(match.group(1));
   }

   private void processBinaryAdded(MatchResult match, String line) {
      this.actualFile.setBinaryAdded(match.group(1));
   }

   private void processBinaryDeleted(MatchResult match, String line) {
      this.actualFile.setBinaryDeleted(match.group(1));
   }

   private void processBinaryEdited(MatchResult match, String line) {
      this.actualFile.setBinaryEdited(match.group(1));
   }

   private String extractFileName(String _line) {
      Matcher matcher = TIMESTAMP_REGEXP.matcher(_line);
      String line = _line;
      if (matcher.find()) {
         line = _line.substring(0, matcher.start());
      }

      line = line.split("\t")[0];
      return line.substring(4).replaceFirst("^(a|b|old|new)/", "").trim();
   }

   private String extractTimestamp(String line) {
      Matcher matcher = TIMESTAMP_REGEXP.matcher(line);
      return matcher.find() ? matcher.group() : null;
   }

   final class UnifiedDiffLine {
      private final Pattern pattern;
      private final BiConsumer command;
      private final boolean stopsHeaderParsing;

      public UnifiedDiffLine(String pattern, BiConsumer command) {
         this(false, (String)pattern, command);
      }

      public UnifiedDiffLine(boolean stopsHeaderParsing, String pattern, BiConsumer command) {
         this.pattern = Pattern.compile(pattern);
         this.command = command;
         this.stopsHeaderParsing = stopsHeaderParsing;
      }

      public UnifiedDiffLine(boolean stopsHeaderParsing, Pattern pattern, BiConsumer command) {
         this.pattern = pattern;
         this.command = command;
         this.stopsHeaderParsing = stopsHeaderParsing;
      }

      public boolean validLine(String line) {
         Matcher m = this.pattern.matcher(line);
         return m.find();
      }

      public boolean processLine(String line) throws UnifiedDiffParserException {
         Matcher m = this.pattern.matcher(line);
         if (m.find()) {
            this.command.accept(m.toMatchResult(), line);
            return true;
         } else {
            return false;
         }
      }

      public boolean isStopsHeaderParsing() {
         return this.stopsHeaderParsing;
      }

      public String toString() {
         return "UnifiedDiffLine{pattern=" + this.pattern + ", stopsHeaderParsing=" + this.stopsHeaderParsing + '}';
      }
   }
}
