package org.apache.datasketches.quantilescommon;

public final class BinarySearch {
   public static int find(float[] arr, int low, int high, float v) {
      int lo = low;
      int hi = high;

      while(lo <= hi) {
         int mid = lo + (hi - lo) / 2;
         if (v < arr[mid]) {
            hi = mid - 1;
         } else {
            if (!(v > arr[mid])) {
               return mid;
            }

            lo = mid + 1;
         }
      }

      return -1;
   }

   public static int find(double[] arr, int low, int high, double v) {
      int lo = low;
      int hi = high;

      while(lo <= hi) {
         int mid = lo + (hi - lo) / 2;
         if (v < arr[mid]) {
            hi = mid - 1;
         } else {
            if (!(v > arr[mid])) {
               return mid;
            }

            lo = mid + 1;
         }
      }

      return -1;
   }

   public static int find(long[] arr, int low, int high, long v) {
      int lo = low;
      int hi = high;

      while(lo <= hi) {
         int mid = lo + (hi - lo) / 2;
         if (v < arr[mid]) {
            hi = mid - 1;
         } else {
            if (v <= arr[mid]) {
               return mid;
            }

            lo = mid + 1;
         }
      }

      return -1;
   }
}
