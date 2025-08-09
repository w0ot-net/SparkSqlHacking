package org.apache.datasketches.cpc;

final class RuntimeAsserts {
   static void rtAssert(boolean b) {
      if (!b) {
         error("False, expected True.");
      }

   }

   static void rtAssertFalse(boolean b) {
      if (b) {
         error("True, expected False.");
      }

   }

   static void rtAssertEquals(long a, long b) {
      if (a != b) {
         error(a + " != " + b);
      }

   }

   static void rtAssertEquals(double a, double b, double eps) {
      if (Math.abs(a - b) > eps) {
         error("abs(" + a + " - " + b + ") > " + eps);
      }

   }

   static void rtAssertEquals(boolean a, boolean b) {
      if (a != b) {
         error(a + " != " + b);
      }

   }

   static void rtAssertEquals(byte[] a, byte[] b) {
      if (a != null || b != null) {
         if (a != null && b != null) {
            int alen = a.length;
            if (alen != b.length) {
               error("Array lengths not equal: " + a.length + ", " + b.length);
            }

            for(int i = 0; i < alen; ++i) {
               if (a[i] != b[i]) {
                  error(a[i] + " != " + b[i] + " at index " + i);
               }
            }
         } else {
            error("Array " + (a == null ? "a" : "b") + " is null");
         }

      }
   }

   static void rtAssertEquals(short[] a, short[] b) {
      if (a != null || b != null) {
         if (a != null && b != null) {
            int alen = a.length;
            if (alen != b.length) {
               error("Array lengths not equal: " + a.length + ", " + b.length);
            }

            for(int i = 0; i < alen; ++i) {
               if (a[i] != b[i]) {
                  error(a[i] + " != " + b[i] + " at index " + i);
               }
            }
         } else {
            error("Array " + (a == null ? "a" : "b") + " is null");
         }

      }
   }

   static void rtAssertEquals(int[] a, int[] b) {
      if (a != null || b != null) {
         if (a != null && b != null) {
            int alen = a.length;
            if (alen != b.length) {
               error("Array lengths not equal: " + a.length + ", " + b.length);
            }

            for(int i = 0; i < alen; ++i) {
               if (a[i] != b[i]) {
                  error(a[i] + " != " + b[i] + " at index " + i);
               }
            }
         } else {
            error("Array " + (a == null ? "a" : "b") + " is null");
         }

      }
   }

   static void rtAssertEquals(long[] a, long[] b) {
      if (a != null || b != null) {
         if (a != null && b != null) {
            int alen = a.length;
            if (alen != b.length) {
               error("Array lengths not equal: " + a.length + ", " + b.length);
            }

            for(int i = 0; i < alen; ++i) {
               if (a[i] != b[i]) {
                  error(a[i] + " != " + b[i] + " at index " + i);
               }
            }
         } else {
            error("Array " + (a == null ? "a" : "b") + " is null");
         }

      }
   }

   static void rtAssertEquals(float[] a, float[] b, float eps) {
      if (a != null || b != null) {
         if (a != null && b != null) {
            int alen = a.length;
            if (alen != b.length) {
               error("Array lengths not equal: " + a.length + ", " + b.length);
            }

            for(int i = 0; i < alen; ++i) {
               if (Math.abs(a[i] - b[i]) > eps) {
                  error("abs(" + a[i] + " - " + b[i] + ") > " + eps);
               }
            }
         } else {
            error("Array " + (a == null ? "a" : "b") + " is null");
         }

      }
   }

   static void rtAssertEquals(double[] a, double[] b, double eps) {
      if (a != null || b != null) {
         if (a != null && b != null) {
            int alen = a.length;
            if (alen != b.length) {
               error("Array lengths not equal: " + alen + ", " + b.length);
            }

            for(int i = 0; i < alen; ++i) {
               if (Math.abs(a[i] - b[i]) > eps) {
                  error("abs(" + a[i] + " - " + b[i] + ") > " + eps);
               }
            }
         } else {
            error("Array " + (a == null ? "a" : "b") + " is null");
         }

      }
   }

   private static void error(String message) {
      throw new AssertionError(message);
   }
}
