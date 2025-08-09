package org.apache.commons.text.similarity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CosineSimilarity {
   static final CosineSimilarity INSTANCE = new CosineSimilarity();

   public Double cosineSimilarity(Map leftVector, Map rightVector) {
      if (leftVector != null && rightVector != null) {
         Set<CharSequence> intersection = this.getIntersection(leftVector, rightVector);
         double dotProduct = this.dot(leftVector, rightVector, intersection);
         double d1 = (double)0.0F;

         for(Integer value : leftVector.values()) {
            d1 += Math.pow((double)value, (double)2.0F);
         }

         double d2 = (double)0.0F;

         for(Integer value : rightVector.values()) {
            d2 += Math.pow((double)value, (double)2.0F);
         }

         double cosineSimilarity;
         if (!(d1 <= (double)0.0F) && !(d2 <= (double)0.0F)) {
            cosineSimilarity = dotProduct / (Math.sqrt(d1) * Math.sqrt(d2));
         } else {
            cosineSimilarity = (double)0.0F;
         }

         return cosineSimilarity;
      } else {
         throw new IllegalArgumentException("Vectors must not be null");
      }
   }

   private double dot(Map leftVector, Map rightVector, Set intersection) {
      long dotProduct = 0L;

      for(CharSequence key : intersection) {
         dotProduct += (long)(Integer)leftVector.get(key) * (long)(Integer)rightVector.get(key);
      }

      return (double)dotProduct;
   }

   private Set getIntersection(Map leftVector, Map rightVector) {
      Set<CharSequence> intersection = new HashSet(leftVector.keySet());
      intersection.retainAll(rightVector.keySet());
      return intersection;
   }
}
