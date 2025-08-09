package org.apache.commons.text.similarity;

import java.util.Arrays;

public class LevenshteinDistance implements EditDistance {
   private static final LevenshteinDistance INSTANCE = new LevenshteinDistance();
   private final Integer threshold;

   public static LevenshteinDistance getDefaultInstance() {
      return INSTANCE;
   }

   private static int limitedCompare(SimilarityInput left, SimilarityInput right, int threshold) {
      if (left != null && right != null) {
         if (threshold < 0) {
            throw new IllegalArgumentException("Threshold must not be negative");
         } else {
            int n = left.length();
            int m = right.length();
            if (n == 0) {
               return m <= threshold ? m : -1;
            } else if (m == 0) {
               return n <= threshold ? n : -1;
            } else {
               if (n > m) {
                  SimilarityInput<E> tmp = left;
                  left = right;
                  right = tmp;
                  n = m;
                  m = tmp.length();
               }

               if (m - n > threshold) {
                  return -1;
               } else {
                  int[] p = new int[n + 1];
                  int[] d = new int[n + 1];
                  int boundary = Math.min(n, threshold) + 1;

                  for(int i = 0; i < boundary; p[i] = i++) {
                  }

                  Arrays.fill(p, boundary, p.length, Integer.MAX_VALUE);
                  Arrays.fill(d, Integer.MAX_VALUE);

                  for(int j = 1; j <= m; ++j) {
                     E rightJ = (E)right.at(j - 1);
                     d[0] = j;
                     int min = Math.max(1, j - threshold);
                     int max = j > Integer.MAX_VALUE - threshold ? n : Math.min(n, j + threshold);
                     if (min > 1) {
                        d[min - 1] = Integer.MAX_VALUE;
                     }

                     int lowerBound = Integer.MAX_VALUE;

                     for(int i = min; i <= max; ++i) {
                        if (left.at(i - 1).equals(rightJ)) {
                           d[i] = p[i - 1];
                        } else {
                           d[i] = 1 + Math.min(Math.min(d[i - 1], p[i]), p[i - 1]);
                        }

                        lowerBound = Math.min(lowerBound, d[i]);
                     }

                     if (lowerBound > threshold) {
                        return -1;
                     }

                     int[] tempD = p;
                     p = d;
                     d = tempD;
                  }

                  if (p[n] <= threshold) {
                     return p[n];
                  } else {
                     return -1;
                  }
               }
            }
         }
      } else {
         throw new IllegalArgumentException("CharSequences must not be null");
      }
   }

   private static int unlimitedCompare(SimilarityInput left, SimilarityInput right) {
      if (left != null && right != null) {
         int n = left.length();
         int m = right.length();
         if (n == 0) {
            return m;
         } else if (m == 0) {
            return n;
         } else {
            if (n > m) {
               SimilarityInput<E> tmp = left;
               left = right;
               right = tmp;
               n = m;
               m = tmp.length();
            }

            int[] p = new int[n + 1];

            for(int i = 0; i <= n; p[i] = i++) {
            }

            for(int j = 1; j <= m; ++j) {
               int upperLeft = p[0];
               E rightJ = (E)right.at(j - 1);
               p[0] = j;

               for(int var12 = 1; var12 <= n; ++var12) {
                  int upper = p[var12];
                  int cost = left.at(var12 - 1).equals(rightJ) ? 0 : 1;
                  p[var12] = Math.min(Math.min(p[var12 - 1] + 1, p[var12] + 1), upperLeft + cost);
                  upperLeft = upper;
               }
            }

            return p[n];
         }
      } else {
         throw new IllegalArgumentException("CharSequences must not be null");
      }
   }

   /** @deprecated */
   @Deprecated
   public LevenshteinDistance() {
      this((Integer)null);
   }

   public LevenshteinDistance(Integer threshold) {
      if (threshold != null && threshold < 0) {
         throw new IllegalArgumentException("Threshold must not be negative");
      } else {
         this.threshold = threshold;
      }
   }

   public Integer apply(CharSequence left, CharSequence right) {
      return this.apply(SimilarityInput.input(left), SimilarityInput.input(right));
   }

   public Integer apply(SimilarityInput left, SimilarityInput right) {
      return this.threshold != null ? limitedCompare(left, right, this.threshold) : unlimitedCompare(left, right);
   }

   public Integer getThreshold() {
      return this.threshold;
   }
}
