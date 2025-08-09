package org.roaringbitmap;

public class ArraysShim {
   public static boolean equals(char[] x, int xmin, int xmax, char[] y, int ymin, int ymax) {
      int xlen = xmax - xmin;
      int ylen = ymax - ymin;
      if (xlen != ylen) {
         return false;
      } else {
         int i = xmin;

         for(int j = ymin; i < xmax && j < ymax; ++j) {
            if (x[i] != y[j]) {
               return false;
            }

            ++i;
         }

         return true;
      }
   }

   public static int mismatch(byte[] a, int aFromIndex, int aToIndex, byte[] b, int bFromIndex, int bToIndex) {
      int aLength = aToIndex - aFromIndex;
      int bLength = bToIndex - bFromIndex;
      int length = Math.min(aLength, bLength);

      for(int i = 0; i < length; ++i) {
         if (a[aFromIndex + i] != b[bFromIndex + i]) {
            return i;
         }
      }

      if (aLength != bLength) {
         return length;
      } else {
         return -1;
      }
   }
}
