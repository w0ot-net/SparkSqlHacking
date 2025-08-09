package org.apache.commons.text.similarity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public class IntersectionSimilarity implements SimilarityScore {
   private final Function converter;

   private static int getIntersection(Set setA, Set setB) {
      int intersection = 0;

      for(Object element : setA) {
         if (setB.contains(element)) {
            ++intersection;
         }
      }

      return intersection;
   }

   public IntersectionSimilarity(Function converter) {
      if (converter == null) {
         throw new IllegalArgumentException("Converter must not be null");
      } else {
         this.converter = converter;
      }
   }

   public IntersectionResult apply(CharSequence left, CharSequence right) {
      if (left != null && right != null) {
         Collection<T> objectsA = (Collection)this.converter.apply(left);
         Collection<T> objectsB = (Collection)this.converter.apply(right);
         int sizeA = objectsA.size();
         int sizeB = objectsB.size();
         if (Math.min(sizeA, sizeB) == 0) {
            return new IntersectionResult(sizeA, sizeB, 0);
         } else {
            int intersection;
            if (objectsA instanceof Set && objectsB instanceof Set) {
               intersection = sizeA < sizeB ? getIntersection((Set)objectsA, (Set)objectsB) : getIntersection((Set)objectsB, (Set)objectsA);
            } else {
               IntersectionSimilarity<T>.TinyBag bagA = this.toBag(objectsA);
               IntersectionSimilarity<T>.TinyBag bagB = this.toBag(objectsB);
               intersection = bagA.uniqueElementSize() < bagB.uniqueElementSize() ? this.getIntersection(bagA, bagB) : this.getIntersection(bagB, bagA);
            }

            return new IntersectionResult(sizeA, sizeB, intersection);
         }
      } else {
         throw new IllegalArgumentException("Input cannot be null");
      }
   }

   private int getIntersection(TinyBag bagA, TinyBag bagB) {
      int intersection = 0;

      for(Map.Entry entry : bagA.entrySet()) {
         T element = (T)entry.getKey();
         int count = ((BagCount)entry.getValue()).count;
         intersection += Math.min(count, bagB.getCount(element));
      }

      return intersection;
   }

   private TinyBag toBag(Collection objects) {
      IntersectionSimilarity<T>.TinyBag bag = new TinyBag(objects.size());
      Objects.requireNonNull(bag);
      objects.forEach((x$0) -> bag.add(x$0));
      return bag;
   }

   private static final class BagCount {
      private static final BagCount ZERO = new BagCount();
      private int count;

      private BagCount() {
         this.count = 0;
      }
   }

   private final class TinyBag {
      private final Map map;

      private TinyBag(int initialCapacity) {
         this.map = new HashMap(initialCapacity);
      }

      private void add(Object object) {
         ((BagCount)this.map.computeIfAbsent(object, (k) -> new BagCount())).count++;
      }

      private Set entrySet() {
         return this.map.entrySet();
      }

      private int getCount(Object object) {
         return ((BagCount)this.map.getOrDefault(object, IntersectionSimilarity.BagCount.ZERO)).count;
      }

      private int uniqueElementSize() {
         return this.map.size();
      }
   }
}
