package org.apache.datasketches.tdigest;

public final class BinarySearch {
   static int lowerBound(double[] values, int first, int last, double value) {
      int count = last - first;

      while(count > 0) {
         int step = count / 2;
         int current = first + step;
         if (values[current] < value) {
            ++current;
            first = current;
            count -= step + 1;
         } else {
            count = step;
         }
      }

      return first;
   }

   static int upperBound(double[] values, int first, int last, double value) {
      int count = last - first;

      while(count > 0) {
         int step = count / 2;
         int current = first + step;
         if (!(value < values[current])) {
            ++current;
            first = current;
            count -= step + 1;
         } else {
            count = step;
         }
      }

      return first;
   }
}
