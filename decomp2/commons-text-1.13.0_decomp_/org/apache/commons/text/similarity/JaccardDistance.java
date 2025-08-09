package org.apache.commons.text.similarity;

public class JaccardDistance implements EditDistance {
   public Double apply(CharSequence left, CharSequence right) {
      return this.apply(SimilarityInput.input(left), SimilarityInput.input(right));
   }

   public Double apply(SimilarityInput left, SimilarityInput right) {
      return (double)1.0F - JaccardSimilarity.INSTANCE.apply(left, right);
   }
}
