package org.apache.datasketches.tdigest;

import java.util.concurrent.ThreadLocalRandom;

public final class Sort {
   public static void stableSort(double[] keys, long[] values, int n) {
      stableLimitedQuickSort(keys, values, 0, n, 64);
      stableLimitedInsertionSort(keys, values, 0, n, 64);
   }

   private static void stableLimitedQuickSort(double[] keys, long[] values, int start, int end, int limit) {
      while(end - start > limit) {
         int pivotIndex = start + ThreadLocalRandom.current().nextInt(end - start);
         double pivotValue = keys[pivotIndex];
         swap(keys, start, pivotIndex);
         swap(values, start, pivotIndex);
         int low = start + 1;
         int high = end;
         int i = low;

         while(i < high) {
            double vi = keys[i];
            if (vi == pivotValue && i == pivotIndex) {
               if (low != i) {
                  swap(keys, low, i);
                  swap(values, low, i);
               } else {
                  ++i;
               }

               ++low;
            } else if (!(vi > pivotValue) && (vi != pivotValue || i <= pivotIndex)) {
               ++i;
            } else {
               --high;
               swap(keys, i, high);
               swap(values, i, high);
            }
         }

         int from = start;
         int to = high - 1;

         for(int var14 = 0; from < low && to >= low; ++var14) {
            swap(keys, from, to);
            swap(values, from++, to--);
         }

         if (from == low) {
            low = to + 1;
         } else {
            low = from;
         }

         if (low - start < end - high) {
            stableLimitedQuickSort(keys, values, start, low, limit);
            start = high;
         } else {
            stableLimitedQuickSort(keys, values, high, end, limit);
            end = low;
         }
      }

   }

   private static void stableLimitedInsertionSort(double[] keys, long[] values, int start, int n, int limit) {
      for(int i = start + 1; i < n; ++i) {
         double k = keys[i];
         long v = values[i];
         int m = Math.max(i - limit, start);

         for(int j = i; j >= m; --j) {
            if (j == 0 || keys[j - 1] <= k) {
               if (j < i) {
                  System.arraycopy(keys, j, keys, j + 1, i - j);
                  System.arraycopy(values, j, values, j + 1, i - j);
                  keys[j] = k;
                  values[j] = v;
               }
               break;
            }
         }
      }

   }

   private static void swap(double[] values, int i, int j) {
      double tmpValue = values[i];
      values[i] = values[j];
      values[j] = tmpValue;
   }

   private static void swap(long[] values, int i, int j) {
      long tmpValue = values[i];
      values[i] = values[j];
      values[j] = tmpValue;
   }

   public static void reverse(double[] values, int n) {
      for(int i = 0; i < n / 2; ++i) {
         swap(values, i, n - i - 1);
      }

   }

   public static void reverse(long[] values, int n) {
      for(int i = 0; i < n / 2; ++i) {
         swap(values, i, n - i - 1);
      }

   }
}
