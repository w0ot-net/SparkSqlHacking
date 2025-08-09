package jline.console.completer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import jline.console.ConsoleReader;
import jline.console.CursorBuffer;
import jline.internal.Ansi;

public class CandidateListCompletionHandler implements CompletionHandler {
   private boolean printSpaceAfterFullCompletion = true;
   private boolean stripAnsi;

   public boolean getPrintSpaceAfterFullCompletion() {
      return this.printSpaceAfterFullCompletion;
   }

   public void setPrintSpaceAfterFullCompletion(boolean printSpaceAfterFullCompletion) {
      this.printSpaceAfterFullCompletion = printSpaceAfterFullCompletion;
   }

   public boolean isStripAnsi() {
      return this.stripAnsi;
   }

   public void setStripAnsi(boolean stripAnsi) {
      this.stripAnsi = stripAnsi;
   }

   public boolean complete(ConsoleReader reader, List candidates, int pos) throws IOException {
      CursorBuffer buf = reader.getCursorBuffer();
      if (candidates.size() == 1) {
         String value = Ansi.stripAnsi(((CharSequence)candidates.get(0)).toString());
         if (buf.cursor == buf.buffer.length() && this.printSpaceAfterFullCompletion && !value.endsWith(" ")) {
            value = value + " ";
         }

         if (value.equals(buf.toString())) {
            return false;
         } else {
            setBuffer(reader, value, pos);
            return true;
         }
      } else {
         if (candidates.size() > 1) {
            String value = this.getUnambiguousCompletions(candidates);
            setBuffer(reader, value, pos);
         }

         printCandidates(reader, candidates);
         reader.drawLine();
         return true;
      }
   }

   public static void setBuffer(ConsoleReader reader, CharSequence value, int offset) throws IOException {
      while(reader.getCursorBuffer().cursor > offset && reader.backspace()) {
      }

      reader.putString(value);
      reader.setCursorPosition(offset + value.length());
   }

   public static void printCandidates(ConsoleReader reader, Collection candidates) throws IOException {
      Set<CharSequence> distinct = new HashSet(candidates);
      if (distinct.size() > reader.getAutoprintThreshold()) {
         reader.println();
         reader.print(CandidateListCompletionHandler.Messages.DISPLAY_CANDIDATES.format(distinct.size()));
         reader.flush();
         String noOpt = CandidateListCompletionHandler.Messages.DISPLAY_CANDIDATES_NO.format();
         String yesOpt = CandidateListCompletionHandler.Messages.DISPLAY_CANDIDATES_YES.format();
         char[] allowed = new char[]{yesOpt.charAt(0), noOpt.charAt(0)};

         int c;
         while((c = reader.readCharacter(allowed)) != -1) {
            String tmp = new String(new char[]{(char)c});
            if (noOpt.startsWith(tmp)) {
               reader.println();
               return;
            }

            if (yesOpt.startsWith(tmp)) {
               break;
            }

            reader.beep();
         }
      }

      if (distinct.size() != candidates.size()) {
         Collection<CharSequence> copy = new ArrayList();

         for(CharSequence next : candidates) {
            if (!copy.contains(next)) {
               copy.add(next);
            }
         }

         candidates = copy;
      }

      reader.println();
      reader.printColumns(candidates);
   }

   private String getUnambiguousCompletions(List candidates) {
      if (candidates != null && !candidates.isEmpty()) {
         if (candidates.size() == 1) {
            return ((CharSequence)candidates.get(0)).toString();
         } else {
            String first = null;
            String[] strings = new String[candidates.size() - 1];

            for(int i = 0; i < candidates.size(); ++i) {
               String str = ((CharSequence)candidates.get(i)).toString();
               if (this.stripAnsi) {
                  str = Ansi.stripAnsi(str);
               }

               if (first == null) {
                  first = str;
               } else {
                  strings[i - 1] = str;
               }
            }

            StringBuilder candidate = new StringBuilder();

            for(int i = 0; i < first.length() && startsWith(first.substring(0, i + 1), strings); ++i) {
               candidate.append(first.charAt(i));
            }

            return candidate.toString();
         }
      } else {
         return null;
      }
   }

   private static boolean startsWith(String starts, String[] candidates) {
      for(String candidate : candidates) {
         if (!candidate.toLowerCase().startsWith(starts.toLowerCase())) {
            return false;
         }
      }

      return true;
   }

   private static enum Messages {
      DISPLAY_CANDIDATES,
      DISPLAY_CANDIDATES_YES,
      DISPLAY_CANDIDATES_NO;

      private static final ResourceBundle bundle = ResourceBundle.getBundle(CandidateListCompletionHandler.class.getName(), Locale.getDefault());

      public String format(Object... args) {
         return bundle == null ? "" : String.format(bundle.getString(this.name()), args);
      }
   }
}
