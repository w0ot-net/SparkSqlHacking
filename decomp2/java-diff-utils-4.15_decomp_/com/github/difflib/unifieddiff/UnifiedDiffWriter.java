package com.github.difflib.unifieddiff;

import com.github.difflib.patch.AbstractDelta;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UnifiedDiffWriter {
   private static final Logger LOG = Logger.getLogger(UnifiedDiffWriter.class.getName());

   public static void write(UnifiedDiff diff, Function originalLinesProvider, Writer writer, int contextSize) throws IOException {
      Objects.requireNonNull(originalLinesProvider, "original lines provider needs to be specified");
      write(diff, originalLinesProvider, (Consumer)((line) -> {
         try {
            writer.append(line).append("\n");
         } catch (IOException ex) {
            LOG.log(Level.SEVERE, (String)null, ex);
         }

      }), contextSize);
   }

   public static void write(UnifiedDiff diff, Function originalLinesProvider, Consumer writer, int contextSize) throws IOException {
      if (diff.getHeader() != null) {
         writer.accept(diff.getHeader());
      }

      for(UnifiedDiffFile file : diff.getFiles()) {
         List<AbstractDelta<String>> patchDeltas = new ArrayList(file.getPatch().getDeltas());
         if (!patchDeltas.isEmpty()) {
            writeOrNothing(writer, file.getDiffCommand());
            if (file.getIndex() != null) {
               writer.accept("index " + file.getIndex());
            }

            writer.accept("--- " + (file.getFromFile() == null ? "/dev/null" : file.getFromFile()));
            if (file.getToFile() != null) {
               writer.accept("+++ " + file.getToFile());
            }

            List<String> originalLines = (List)originalLinesProvider.apply(file.getFromFile());
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
                     processDeltas(writer, originalLines, deltas, contextSize, false);
                     deltas.clear();
                     deltas.add(nextDelta);
                  }

                  delta = nextDelta;
               }
            }

            processDeltas(writer, originalLines, deltas, contextSize, patchDeltas.size() == 1 && file.getFromFile() == null);
         }
      }

      if (diff.getTail() != null) {
         writer.accept("--");
         writer.accept(diff.getTail());
      }

   }

   private static void processDeltas(Consumer writer, List origLines, List deltas, int contextSize, boolean newFile) {
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

      for(int line = contextStart; line < curDelta.getSource().getPosition() && line < origLines.size(); ++line) {
         buffer.add(" " + (String)origLines.get(line));
         ++origTotal;
         ++revTotal;
      }

      getDeltaText((txt) -> buffer.add(txt), curDelta);
      origTotal += curDelta.getSource().getLines().size();
      revTotal += curDelta.getTarget().getLines().size();

      for(int deltaIndex = 1; deltaIndex < deltas.size(); ++deltaIndex) {
         AbstractDelta<String> nextDelta = (AbstractDelta)deltas.get(deltaIndex);
         int intermediateStart = curDelta.getSource().getPosition() + curDelta.getSource().getLines().size();

         for(int var18 = intermediateStart; var18 < nextDelta.getSource().getPosition() && var18 < origLines.size(); ++var18) {
            buffer.add(" " + (String)origLines.get(var18));
            ++origTotal;
            ++revTotal;
         }

         getDeltaText((txt) -> buffer.add(txt), nextDelta);
         origTotal += nextDelta.getSource().getLines().size();
         revTotal += nextDelta.getTarget().getLines().size();
         curDelta = nextDelta;
      }

      contextStart = curDelta.getSource().getPosition() + curDelta.getSource().getLines().size();

      for(int var19 = contextStart; var19 < contextStart + contextSize && var19 < origLines.size(); ++var19) {
         buffer.add(" " + (String)origLines.get(var19));
         ++origTotal;
         ++revTotal;
      }

      writer.accept("@@ -" + origStart + "," + origTotal + " +" + revStart + "," + revTotal + " @@");
      buffer.forEach((txt) -> writer.accept(txt));
   }

   private static void getDeltaText(Consumer writer, AbstractDelta delta) {
      for(String line : delta.getSource().getLines()) {
         writer.accept("-" + line);
      }

      for(String line : delta.getTarget().getLines()) {
         writer.accept("+" + line);
      }

   }

   private static void writeOrNothing(Consumer writer, String str) throws IOException {
      if (str != null) {
         writer.accept(str);
      }

   }
}
