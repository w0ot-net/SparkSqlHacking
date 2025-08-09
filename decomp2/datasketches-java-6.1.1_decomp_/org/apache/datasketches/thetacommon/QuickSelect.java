package org.apache.datasketches.thetacommon;

public final class QuickSelect {
   private QuickSelect() {
   }

   public static long select(long[] arr, int lo, int hi, int pivot) {
      while(hi > lo) {
         int j = partition(arr, lo, hi);
         if (j == pivot) {
            return arr[pivot];
         }

         if (j > pivot) {
            hi = j - 1;
         } else {
            lo = j + 1;
         }
      }

      return arr[pivot];
   }

   public static long selectIncludingZeros(long[] arr, int pivot) {
      int arrSize = arr.length;
      int adj = pivot - 1;
      return select((long[])arr, 0, arrSize - 1, adj);
   }

   public static long selectExcludingZeros(long[] arr, int nonZeros, int pivot) {
      if (pivot > nonZeros) {
         return 0L;
      } else {
         int arrSize = arr.length;
         int zeros = arrSize - nonZeros;
         int adjK = pivot + zeros - 1;
         return select((long[])arr, 0, arrSize - 1, adjK);
      }
   }

   private static int partition(long[] arr, int lo, int hi) {
      int i = lo;
      int j = hi + 1;
      long v = arr[lo];

      while(true) {
         ++i;
         if (arr[i] >= v || i == hi) {
            do {
               --j;
            } while(v < arr[j] && j != lo);

            if (i >= j) {
               long x = arr[lo];
               arr[lo] = arr[j];
               arr[j] = x;
               return j;
            }

            long x = arr[i];
            arr[i] = arr[j];
            arr[j] = x;
         }
      }
   }

   public static double select(double[] arr, int lo, int hi, int pivot) {
      while(hi > lo) {
         int j = partition(arr, lo, hi);
         if (j == pivot) {
            return arr[pivot];
         }

         if (j > pivot) {
            hi = j - 1;
         } else {
            lo = j + 1;
         }
      }

      return arr[pivot];
   }

   public static double selectIncludingZeros(double[] arr, int pivot) {
      int arrSize = arr.length;
      int adj = pivot - 1;
      return select((double[])arr, 0, arrSize - 1, adj);
   }

   public static double selectExcludingZeros(double[] arr, int nonZeros, int pivot) {
      if (pivot > nonZeros) {
         return (double)0.0F;
      } else {
         int arrSize = arr.length;
         int zeros = arrSize - nonZeros;
         int adjK = pivot + zeros - 1;
         return select((double[])arr, 0, arrSize - 1, adjK);
      }
   }

   private static int partition(double[] arr, int lo, int hi) {
      int i = lo;
      int j = hi + 1;
      double v = arr[lo];

      while(true) {
         ++i;
         if (!(arr[i] < v) || i == hi) {
            do {
               --j;
            } while(v < arr[j] && j != lo);

            if (i >= j) {
               double x = arr[lo];
               arr[lo] = arr[j];
               arr[j] = x;
               return j;
            }

            double x = arr[i];
            arr[i] = arr[j];
            arr[j] = x;
         }
      }
   }
}
