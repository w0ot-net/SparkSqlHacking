package org.apache.commons.text.similarity;

import java.util.HashSet;
import java.util.Set;

public class JaccardSimilarity implements SimilarityScore {
   static final JaccardSimilarity INSTANCE = new JaccardSimilarity();

   public Double apply(CharSequence left, CharSequence right) {
      return this.apply(SimilarityInput.input(left), SimilarityInput.input(right));
   }

   public Double apply(SimilarityInput left, SimilarityInput right) {
      if (left != null && right != null) {
         int leftLength = left.length();
         int rightLength = right.length();
         if (leftLength == 0 && rightLength == 0) {
            return (double)1.0F;
         } else if (leftLength != 0 && rightLength != 0) {
            Set<E> leftSet = new HashSet();

            for(int i = 0; i < leftLength; ++i) {
               leftSet.add(left.at(i));
            }

            Set<E> rightSet = new HashSet();

            for(int i = 0; i < rightLength; ++i) {
               rightSet.add(right.at(i));
            }

            Set<E> unionSet = new HashSet(leftSet);
            unionSet.addAll(rightSet);
            int intersectionSize = leftSet.size() + rightSet.size() - unionSet.size();
            return (double)1.0F * (double)intersectionSize / (double)unionSet.size();
         } else {
            return (double)0.0F;
         }
      } else {
         throw new IllegalArgumentException("Input cannot be null");
      }
   }
}
