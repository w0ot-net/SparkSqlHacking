package org.codehaus.commons.compiler.util;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.commons.nullanalysis.Nullable;

public class StringPattern {
   public static final int INCLUDE = 0;
   public static final int EXCLUDE = 1;
   private final int mode;
   @Nullable
   private final String pattern;
   public static final StringPattern[] PATTERNS_ALL = new StringPattern[]{new StringPattern("*")};
   public static final StringPattern[] PATTERNS_NONE = new StringPattern[0];

   public StringPattern(int mode, String pattern) {
      this.mode = mode;
      this.pattern = pattern;
   }

   public StringPattern(@Nullable String pattern) {
      this.mode = 0;
      this.pattern = pattern;
   }

   public int getMode() {
      return this.mode;
   }

   public boolean matches(String text) {
      return wildmatch(this.pattern, text);
   }

   public static StringPattern[] parseCombinedPattern(String combinedPattern) {
      List<StringPattern> al = new ArrayList();

      int l;
      for(int k = 0; k < combinedPattern.length(); k = l) {
         char c = combinedPattern.charAt(k);
         int patternMode;
         if (c == '+') {
            patternMode = 0;
            ++k;
         } else if (c == '-') {
            patternMode = 1;
            ++k;
         } else {
            patternMode = 0;
         }

         for(l = k; l < combinedPattern.length(); ++l) {
            c = combinedPattern.charAt(l);
            if (c == '+' || c == '-') {
               break;
            }
         }

         al.add(new StringPattern(patternMode, combinedPattern.substring(k, l)));
      }

      return (StringPattern[])al.toArray(new StringPattern[al.size()]);
   }

   public static boolean matches(StringPattern[] patterns, String text) {
      if (patterns == null) {
         return false;
      } else {
         for(int i = patterns.length - 1; i >= 0; --i) {
            if (patterns[i].matches(text)) {
               return patterns[i].getMode() == 0;
            }
         }

         return false;
      }
   }

   public String toString() {
      return (this.mode == 0 ? '+' : (this.mode == 1 ? '-' : '?')) + this.pattern;
   }

   private static boolean wildmatch(@Nullable String pattern, String text) {
      if (pattern == null) {
         return true;
      } else {
         int i;
         for(i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            switch (c) {
               case '*':
                  if (pattern.length() == i + 1) {
                     return true;
                  }

                  for(String var4 = pattern.substring(i + 1); i <= text.length(); ++i) {
                     if (wildmatch(var4, text.substring(i))) {
                        return true;
                     }
                  }

                  return false;
               case '?':
                  if (i == text.length()) {
                     return false;
                  }
                  break;
               default:
                  if (i == text.length()) {
                     return false;
                  }

                  if (text.charAt(i) != c) {
                     return false;
                  }
            }
         }

         return text.length() == i;
      }
   }
}
