package org.apache.commons.text.similarity;

import java.util.Arrays;
import java.util.Objects;

public class JaroWinklerSimilarity implements SimilarityScore {
   static final JaroWinklerSimilarity INSTANCE = new JaroWinklerSimilarity();

   protected static int[] matches(CharSequence first, CharSequence second) {
      return matches(SimilarityInput.input(first), SimilarityInput.input(second));
   }

   protected static int[] matches(SimilarityInput first, SimilarityInput second) {
      SimilarityInput<E> max;
      SimilarityInput<E> min;
      if (first.length() > second.length()) {
         max = first;
         min = second;
      } else {
         max = second;
         min = first;
      }

      int range = Math.max(max.length() / 2 - 1, 0);
      int[] matchIndexes = new int[min.length()];
      Arrays.fill(matchIndexes, -1);
      boolean[] matchFlags = new boolean[max.length()];
      int matches = 0;

      for(int mi = 0; mi < min.length(); ++mi) {
         E c1 = (E)min.at(mi);
         int xi = Math.max(mi - range, 0);

         for(int xn = Math.min(mi + range + 1, max.length()); xi < xn; ++xi) {
            if (!matchFlags[xi] && c1.equals(max.at(xi))) {
               matchIndexes[mi] = xi;
               matchFlags[xi] = true;
               ++matches;
               break;
            }
         }
      }

      Object[] ms1 = new Object[matches];
      Object[] ms2 = new Object[matches];
      int i = 0;

      for(int si = 0; i < min.length(); ++i) {
         if (matchIndexes[i] != -1) {
            ms1[si] = min.at(i);
            ++si;
         }
      }

      i = 0;

      for(int si = 0; i < max.length(); ++i) {
         if (matchFlags[i]) {
            ms2[si] = max.at(i);
            ++si;
         }
      }

      i = 0;

      for(int mi = 0; mi < ms1.length; ++mi) {
         if (!ms1[mi].equals(ms2[mi])) {
            ++i;
         }
      }

      int prefix = 0;

      for(int mi = 0; mi < Math.min(4, min.length()) && first.at(mi).equals(second.at(mi)); ++mi) {
         ++prefix;
      }

      return new int[]{matches, i, prefix};
   }

   public Double apply(CharSequence left, CharSequence right) {
      return this.apply(SimilarityInput.input(left), SimilarityInput.input(right));
   }

   public Double apply(SimilarityInput left, SimilarityInput right) {
      double defaultScalingFactor = 0.1;
      if (left != null && right != null) {
         if (Objects.equals(left, right)) {
            return (double)1.0F;
         } else {
            int[] mtp = matches(left, right);
            double m = (double)mtp[0];
            if (m == (double)0.0F) {
               return (double)0.0F;
            } else {
               double j = (m / (double)left.length() + m / (double)right.length() + (m - (double)mtp[1] / (double)2.0F) / m) / (double)3.0F;
               return j < 0.7 ? j : j + 0.1 * (double)mtp[2] * ((double)1.0F - j);
            }
         }
      } else {
         throw new IllegalArgumentException("CharSequences must not be null");
      }
   }
}
