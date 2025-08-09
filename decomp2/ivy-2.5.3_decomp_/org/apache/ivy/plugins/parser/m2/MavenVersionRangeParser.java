package org.apache.ivy.plugins.parser.m2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

class MavenVersionRangeParser {
   private static final DeweyDecimal javaVersion;

   static boolean currentJavaVersionInRange(String range) {
      if (range == null) {
         return false;
      } else if (javaVersion == null) {
         return false;
      } else {
         Range parsedRange = parse(range);
         return parsedRange != null && parsedRange.accepts(javaVersion);
      }
   }

   static boolean rangeAccepts(String range, String value) {
      if (value == null) {
         return false;
      } else {
         DeweyDecimal valToCompare;
         try {
            valToCompare = new DeweyDecimal(value);
         } catch (NumberFormatException var4) {
            return false;
         }

         Range parsedRange = parse(range);
         return parsedRange != null && parsedRange.accepts(valToCompare);
      }
   }

   private static Range parse(String rangeValue) {
      if (rangeValue != null && !rangeValue.trim().isEmpty()) {
         try {
            String[] versionParts = rangeValue.split(",");
            if (versionParts.length == 1) {
               String boundVal = versionParts[0].trim();
               String stripped = stripBoundChars(boundVal);
               if (stripped.isEmpty()) {
                  return null;
               } else {
                  DeweyDecimal bound = new DeweyDecimal(stripped);
                  return new BasicRange(bound, !boundVal.startsWith("("), bound, !boundVal.endsWith(")"));
               }
            } else if (versionParts.length == 2) {
               String lowerBoundVal = versionParts[0].trim();
               String strippedLowerBound = stripBoundChars(lowerBoundVal);
               DeweyDecimal lowerBound;
               if (strippedLowerBound.isEmpty()) {
                  lowerBound = null;
               } else {
                  lowerBound = new DeweyDecimal(strippedLowerBound);
               }

               String upperBoundVal = versionParts[1].trim();
               String strippedUpperBound = stripBoundChars(upperBoundVal);
               DeweyDecimal upperBound;
               if (strippedUpperBound.isEmpty()) {
                  upperBound = null;
               } else {
                  upperBound = new DeweyDecimal(strippedUpperBound);
               }

               return new BasicRange(lowerBound, !lowerBoundVal.startsWith("("), upperBound, !upperBoundVal.endsWith(")"));
            } else if (versionParts.length <= 2) {
               return null;
            } else {
               Collection<Range> ranges = new ArrayList();

               for(int i = 0; i < versionParts.length; i = i + 2 < versionParts.length ? i + 2 : i + 1) {
                  String partOne = versionParts[i];
                  String partTwo;
                  if (i + 1 < versionParts.length) {
                     partTwo = versionParts[i + 1];
                  } else {
                     partTwo = "";
                  }

                  Range rangePart = parse(partOne + "," + partTwo);
                  if (rangePart != null) {
                     ranges.add(rangePart);
                  }
               }

               return ranges != null && !ranges.isEmpty() ? new MultiSetRange(ranges) : null;
            }
         } catch (NumberFormatException var8) {
            return null;
         }
      } else {
         return null;
      }
   }

   private static String stripBoundChars(String value) {
      return value == null ? null : value.replace("(", "").replace(")", "").replace("[", "").replace("]", "");
   }

   static {
      DeweyDecimal v = null;

      try {
         v = new DeweyDecimal(System.getProperty("java.specification.version"));
      } catch (Exception var2) {
         v = null;
      }

      javaVersion = v;
   }

   private static final class BasicRange implements Range {
      private final DeweyDecimal lowerBound;
      private final DeweyDecimal upperBound;
      private final boolean lowerInclusive;
      private final boolean upperInclusive;

      private BasicRange(DeweyDecimal lowerBound, boolean lowerInclusive, DeweyDecimal upperBound, boolean upperInclusive) {
         this.lowerBound = lowerBound;
         this.lowerInclusive = lowerInclusive;
         this.upperBound = upperBound;
         this.upperInclusive = upperInclusive;
      }

      public boolean accepts(DeweyDecimal value) {
         boolean var10000;
         label43: {
            if (value != null) {
               label37: {
                  if (this.lowerBound != null) {
                     if (this.lowerInclusive) {
                        if (!value.isGreaterThanOrEqual(this.lowerBound)) {
                           break label37;
                        }
                     } else if (!value.isGreaterThan(this.lowerBound)) {
                        break label37;
                     }
                  }

                  if (this.upperBound == null) {
                     break label43;
                  }

                  if (this.upperInclusive) {
                     if (value.isLessThanOrEqual(this.upperBound)) {
                        break label43;
                     }
                  } else if (value.isLessThan(this.upperBound)) {
                     break label43;
                  }
               }
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   private static final class MultiSetRange implements Range {
      private final Collection ranges;

      private MultiSetRange(Collection ranges) {
         this.ranges = (Collection)(ranges == null ? Collections.emptySet() : ranges);
      }

      public boolean accepts(DeweyDecimal value) {
         if (this.ranges.isEmpty()) {
            return false;
         } else {
            for(Range range : this.ranges) {
               if (range != null && range.accepts(value)) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   private static final class DeweyDecimal {
      private final int[] components;

      public DeweyDecimal(int[] components) {
         this.components = new int[components.length];
         System.arraycopy(components, 0, this.components, 0, components.length);
      }

      public DeweyDecimal(String string) throws NumberFormatException {
         StringTokenizer tokenizer = new StringTokenizer(string, ".", true);
         int size = tokenizer.countTokens();
         this.components = new int[(size + 1) / 2];

         for(int i = 0; i < this.components.length; ++i) {
            String component = tokenizer.nextToken();
            if (component.length() == 0) {
               throw new NumberFormatException("Empty component in string");
            }

            this.components[i] = Integer.parseInt(component);
            if (tokenizer.hasMoreTokens()) {
               tokenizer.nextToken();
               if (!tokenizer.hasMoreTokens()) {
                  throw new NumberFormatException("DeweyDecimal ended in a '.'");
               }
            }
         }

      }

      public int getSize() {
         return this.components.length;
      }

      public int get(int index) {
         return this.components[index];
      }

      public boolean isEqual(DeweyDecimal other) {
         int max = Math.max(other.components.length, this.components.length);

         for(int i = 0; i < max; ++i) {
            int component1 = i < this.components.length ? this.components[i] : 0;
            int component2 = i < other.components.length ? other.components[i] : 0;
            if (component2 != component1) {
               return false;
            }
         }

         return true;
      }

      public boolean isLessThan(DeweyDecimal other) {
         return !this.isGreaterThanOrEqual(other);
      }

      public boolean isLessThanOrEqual(DeweyDecimal other) {
         return !this.isGreaterThan(other);
      }

      public boolean isGreaterThan(DeweyDecimal other) {
         int max = Math.max(other.components.length, this.components.length);

         for(int i = 0; i < max; ++i) {
            int component1 = i < this.components.length ? this.components[i] : 0;
            int component2 = i < other.components.length ? other.components[i] : 0;
            if (component2 > component1) {
               return false;
            }

            if (component2 < component1) {
               return true;
            }
         }

         return false;
      }

      public boolean isGreaterThanOrEqual(DeweyDecimal other) {
         int max = Math.max(other.components.length, this.components.length);

         for(int i = 0; i < max; ++i) {
            int component1 = i < this.components.length ? this.components[i] : 0;
            int component2 = i < other.components.length ? other.components[i] : 0;
            if (component2 > component1) {
               return false;
            }

            if (component2 < component1) {
               return true;
            }
         }

         return true;
      }

      public String toString() {
         StringBuilder sb = new StringBuilder();

         for(int component : this.components) {
            if (sb.length() > 0) {
               sb.append('.');
            }

            sb.append(component);
         }

         return sb.toString();
      }

      public int compareTo(DeweyDecimal other) {
         int max = Math.max(other.components.length, this.components.length);

         for(int i = 0; i < max; ++i) {
            int component1 = i < this.components.length ? this.components[i] : 0;
            int component2 = i < other.components.length ? other.components[i] : 0;
            if (component1 != component2) {
               return component1 - component2;
            }
         }

         return 0;
      }

      public int hashCode() {
         return this.toString().hashCode();
      }

      public boolean equals(Object o) {
         return o instanceof DeweyDecimal && this.isEqual((DeweyDecimal)o);
      }
   }

   private interface Range {
      boolean accepts(DeweyDecimal var1);
   }
}
