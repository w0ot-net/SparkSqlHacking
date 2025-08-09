package org.apache.commons.text.similarity;

import org.apache.commons.lang3.Validate;

public class SimilarityScoreFrom {
   private final SimilarityScore similarityScore;
   private final CharSequence left;

   public SimilarityScoreFrom(SimilarityScore similarityScore, CharSequence left) {
      Validate.isTrue(similarityScore != null, "The edit distance may not be null.", new Object[0]);
      this.similarityScore = similarityScore;
      this.left = left;
   }

   public Object apply(CharSequence right) {
      return this.similarityScore.apply(this.left, right);
   }

   public CharSequence getLeft() {
      return this.left;
   }

   public SimilarityScore getSimilarityScore() {
      return this.similarityScore;
   }
}
