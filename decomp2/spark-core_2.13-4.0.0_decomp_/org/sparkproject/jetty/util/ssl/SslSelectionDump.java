package org.sparkproject.jetty.util.ssl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.sparkproject.jetty.util.component.Dumpable;

class SslSelectionDump implements Dumpable {
   final String type;
   final CaptionedList enabled = new CaptionedList("Enabled");
   final CaptionedList disabled = new CaptionedList("Disabled");

   public SslSelectionDump(String type, String[] supportedByJVM, String[] enabledByJVM, String[] excludedByConfig, String[] includedByConfig) {
      this.type = type;
      List<String> jvmEnabled = Arrays.asList(enabledByJVM);
      List<Pattern> excludedPatterns = (List)Arrays.stream(excludedByConfig).map((entry) -> Pattern.compile(entry)).collect(Collectors.toList());
      List<Pattern> includedPatterns = (List)Arrays.stream(includedByConfig).map((entry) -> Pattern.compile(entry)).collect(Collectors.toList());
      Arrays.stream(supportedByJVM).sorted(Comparator.naturalOrder()).forEach((entry) -> {
         boolean isPresent = true;
         StringBuilder s = new StringBuilder();
         s.append(entry);

         for(Pattern pattern : excludedPatterns) {
            Matcher m = pattern.matcher(entry);
            if (m.matches()) {
               if (isPresent) {
                  s.append(" -");
                  isPresent = false;
               } else {
                  s.append(",");
               }

               s.append(" ConfigExcluded:'").append(pattern.pattern()).append('\'');
            }
         }

         boolean isIncluded = false;
         if (!includedPatterns.isEmpty()) {
            for(Pattern pattern : includedPatterns) {
               Matcher m = pattern.matcher(entry);
               if (m.matches()) {
                  isIncluded = true;
                  break;
               }
            }

            if (!isIncluded) {
               if (isPresent) {
                  s.append(" -");
                  isPresent = false;
               } else {
                  s.append(",");
               }

               s.append(" ConfigIncluded:NotSelected");
            }
         }

         if (!isIncluded && !jvmEnabled.contains(entry)) {
            if (isPresent) {
               s.append(" -");
               isPresent = false;
            }

            s.append(" JVM:disabled");
         }

         if (isPresent) {
            this.enabled.add(s.toString());
         } else {
            this.disabled.add(s.toString());
         }

      });
   }

   public String dump() {
      return Dumpable.dump(this);
   }

   public void dump(Appendable out, String indent) throws IOException {
      Dumpable.dumpObjects(out, indent, this, this.enabled, this.disabled);
   }

   public String toString() {
      return String.format("%s Selections", this.type);
   }

   static class CaptionedList extends ArrayList implements Dumpable {
      private final String caption;

      public CaptionedList(String caption) {
         this.caption = caption;
      }

      public String dump() {
         return Dumpable.dump(this);
      }

      public void dump(Appendable out, String indent) throws IOException {
         Object[] array = this.toArray();
         Dumpable.dumpObjects(out, indent, this.caption + " size=" + array.length, array);
      }
   }
}
