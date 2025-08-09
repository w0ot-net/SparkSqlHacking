package org.apache.logging.log4j.core.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.core.util.Integers;
import org.apache.logging.log4j.util.PerformanceSensitive;

@PerformanceSensitive({"allocation"})
public abstract class NameAbbreviator {
   private static final NameAbbreviator DEFAULT = new NOPAbbreviator();

   public static NameAbbreviator getAbbreviator(final String pattern) {
      if (pattern.length() <= 0) {
         return DEFAULT;
      } else {
         String trimmed = pattern.trim();
         if (trimmed.isEmpty()) {
            return DEFAULT;
         } else {
            NameAbbreviator dwa = DynamicWordAbbreviator.create(trimmed);
            if (dwa != null) {
               return dwa;
            } else {
               boolean isNegativeNumber;
               String number;
               if (trimmed.length() > 1 && trimmed.charAt(0) == '-') {
                  isNegativeNumber = true;
                  number = trimmed.substring(1);
               } else {
                  isNegativeNumber = false;
                  number = trimmed;
               }

               int i;
               for(i = 0; i < number.length() && number.charAt(i) >= '0' && number.charAt(i) <= '9'; ++i) {
               }

               if (i == number.length()) {
                  return new MaxElementAbbreviator(Integers.parseInt(number), isNegativeNumber ? NameAbbreviator.MaxElementAbbreviator.Strategy.DROP : NameAbbreviator.MaxElementAbbreviator.Strategy.RETAIN);
               } else {
                  List<PatternAbbreviatorFragment> fragments = new ArrayList(5);

                  for(int pos = 0; pos < trimmed.length() && pos >= 0; ++pos) {
                     int ellipsisPos = pos;
                     int charCount;
                     if (trimmed.charAt(pos) == '*') {
                        charCount = Integer.MAX_VALUE;
                        ellipsisPos = pos + 1;
                     } else if (trimmed.charAt(pos) >= '0' && trimmed.charAt(pos) <= '9') {
                        charCount = trimmed.charAt(pos) - 48;
                        ellipsisPos = pos + 1;
                     } else {
                        charCount = 0;
                     }

                     char ellipsis = 0;
                     if (ellipsisPos < trimmed.length()) {
                        ellipsis = trimmed.charAt(ellipsisPos);
                        if (ellipsis == '.') {
                           ellipsis = 0;
                        }
                     }

                     fragments.add(new PatternAbbreviatorFragment(charCount, ellipsis));
                     pos = trimmed.indexOf(46, pos);
                     if (pos == -1) {
                        break;
                     }
                  }

                  return new PatternAbbreviator(fragments);
               }
            }
         }
      }
   }

   public static NameAbbreviator getDefaultAbbreviator() {
      return DEFAULT;
   }

   public abstract void abbreviate(final String original, final StringBuilder destination);

   private static class NOPAbbreviator extends NameAbbreviator {
      public NOPAbbreviator() {
      }

      public void abbreviate(final String original, final StringBuilder destination) {
         destination.append(original);
      }
   }

   private static class MaxElementAbbreviator extends NameAbbreviator {
      private final int count;
      private final Strategy strategy;

      public MaxElementAbbreviator(final int count, final Strategy strategy) {
         this.count = Math.max(count, strategy.minCount);
         this.strategy = strategy;
      }

      public void abbreviate(final String original, final StringBuilder destination) {
         this.strategy.abbreviate(this.count, original, destination);
      }

      private static enum Strategy {
         DROP(0) {
            void abbreviate(final int count, final String original, final StringBuilder destination) {
               int start = 0;

               for(int i = 0; i < count; ++i) {
                  int nextStart = original.indexOf(46, start);
                  if (nextStart == -1) {
                     destination.append(original);
                     return;
                  }

                  start = nextStart + 1;
               }

               destination.append(original, start, original.length());
            }
         },
         RETAIN(1) {
            void abbreviate(final int count, final String original, final StringBuilder destination) {
               int end = original.length() - 1;

               for(int i = count; i > 0; --i) {
                  end = original.lastIndexOf(46, end - 1);
                  if (end == -1) {
                     destination.append(original);
                     return;
                  }
               }

               destination.append(original, end + 1, original.length());
            }
         };

         final int minCount;

         private Strategy(final int minCount) {
            this.minCount = minCount;
         }

         abstract void abbreviate(final int count, final String original, final StringBuilder destination);

         // $FF: synthetic method
         private static Strategy[] $values() {
            return new Strategy[]{DROP, RETAIN};
         }
      }
   }

   private static final class PatternAbbreviatorFragment {
      static final PatternAbbreviatorFragment[] EMPTY_ARRAY = new PatternAbbreviatorFragment[0];
      private final int charCount;
      private final char ellipsis;

      PatternAbbreviatorFragment(final int charCount, final char ellipsis) {
         this.charCount = charCount;
         this.ellipsis = ellipsis;
      }

      int abbreviate(final String input, final int inputIndex, final StringBuilder buf) {
         int nextDot = input.indexOf(46, inputIndex);
         if (nextDot < 0) {
            buf.append(input, inputIndex, input.length());
            return nextDot;
         } else {
            if (nextDot - inputIndex > this.charCount) {
               buf.append(input, inputIndex, inputIndex + this.charCount);
               if (this.ellipsis != 0) {
                  buf.append(this.ellipsis);
               }

               buf.append('.');
            } else {
               buf.append(input, inputIndex, nextDot + 1);
            }

            return nextDot + 1;
         }
      }

      public String toString() {
         return String.format("%s[charCount=%s, ellipsis=%s]", this.getClass().getSimpleName(), this.charCount, Integer.toHexString(this.ellipsis));
      }
   }

   private static final class PatternAbbreviator extends NameAbbreviator {
      private final PatternAbbreviatorFragment[] fragments;

      PatternAbbreviator(final List fragments) {
         if (fragments.isEmpty()) {
            throw new IllegalArgumentException("fragments must have at least one element");
         } else {
            this.fragments = (PatternAbbreviatorFragment[])fragments.toArray(NameAbbreviator.PatternAbbreviatorFragment.EMPTY_ARRAY);
         }
      }

      public void abbreviate(final String original, final StringBuilder destination) {
         int originalIndex = 0;
         int iteration = 0;

         for(int originalLength = original.length(); originalIndex >= 0 && originalIndex < originalLength; originalIndex = this.fragment(iteration++).abbreviate(original, originalIndex, destination)) {
         }

      }

      PatternAbbreviatorFragment fragment(final int index) {
         return this.fragments[Math.min(index, this.fragments.length - 1)];
      }

      public String toString() {
         return String.format("%s[fragments=%s]", this.getClass().getSimpleName(), Arrays.toString(this.fragments));
      }
   }
}
