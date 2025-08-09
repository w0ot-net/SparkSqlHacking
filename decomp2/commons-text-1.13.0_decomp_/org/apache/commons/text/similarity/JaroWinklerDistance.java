package org.apache.commons.text.similarity;

public class JaroWinklerDistance implements EditDistance {
   /** @deprecated */
   @Deprecated
   public static final int INDEX_NOT_FOUND = -1;

   /** @deprecated */
   @Deprecated
   protected static int[] matches(CharSequence first, CharSequence second) {
      return JaroWinklerSimilarity.matches(first, second);
   }

   public Double apply(CharSequence left, CharSequence right) {
      return this.apply(SimilarityInput.input(left), SimilarityInput.input(right));
   }

   public Double apply(SimilarityInput left, SimilarityInput right) {
      if (left != null && right != null) {
         return (double)1.0F - JaroWinklerSimilarity.INSTANCE.apply(left, right);
      } else {
         throw new IllegalArgumentException("CharSequences must not be null");
      }
   }
}
