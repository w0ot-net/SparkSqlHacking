package org.apache.datasketches.quantilescommon;

import java.util.Objects;
import org.apache.datasketches.common.SketchesArgumentException;

public enum InequalitySearch {
   LT {
      int compare(double[] arr, int a, int b, double v) {
         return v <= arr[a] ? -1 : (arr[b] < v ? 1 : 0);
      }

      int compare(float[] arr, int a, int b, float v) {
         return v <= arr[a] ? -1 : (arr[b] < v ? 1 : 0);
      }

      int compare(long[] arr, int a, int b, long v) {
         return v <= arr[a] ? -1 : (arr[b] < v ? 1 : 0);
      }

      int compare(long[] arr, int a, int b, double v) {
         return v <= (double)arr[a] ? -1 : ((double)arr[b] < v ? 1 : 0);
      }

      int getIndex(double[] arr, int a, int b, double v) {
         return a;
      }

      int getIndex(float[] arr, int a, int b, float v) {
         return a;
      }

      int getIndex(long[] arr, int a, int b, long v) {
         return a;
      }

      int getIndex(long[] arr, int a, int b, double v) {
         return a;
      }

      int resolve(double[] arr, int lo, int hi, double v) {
         return lo == hi ? (v > arr[lo] ? lo : -1) : (v > arr[hi] ? hi : (v > arr[lo] ? lo : -1));
      }

      int resolve(float[] arr, int lo, int hi, float v) {
         return lo == hi ? (v > arr[lo] ? lo : -1) : (v > arr[hi] ? hi : (v > arr[lo] ? lo : -1));
      }

      int resolve(long[] arr, int lo, int hi, long v) {
         return lo == hi ? (v > arr[lo] ? lo : -1) : (v > arr[hi] ? hi : (v > arr[lo] ? lo : -1));
      }

      int resolve(long[] arr, int lo, int hi, double v) {
         return lo == hi ? (v > (double)arr[lo] ? lo : -1) : (v > (double)arr[hi] ? hi : (v > (double)arr[lo] ? lo : -1));
      }

      public String desc(double[] arr, int low, int high, double v, int idx) {
         if (idx == -1) {
            return "LT: " + v + " <= arr[" + low + "]=" + arr[low] + "; return -1";
         } else {
            return idx == high ? "LT: " + v + " > arr[" + high + "]=" + arr[high] + "; return arr[" + high + "]=" + arr[high] : "LT: " + v + ": arr[" + idx + "]=" + arr[idx] + " < " + v + " <= arr[" + (idx + 1) + "]=" + arr[idx + 1] + "; return arr[" + idx + "]=" + arr[idx];
         }
      }

      public String desc(float[] arr, int low, int high, float v, int idx) {
         if (idx == -1) {
            return "LT: " + v + " <= arr[" + low + "]=" + arr[low] + "; return -1";
         } else {
            return idx == high ? "LT: " + v + " > arr[" + high + "]=" + arr[high] + "; return arr[" + high + "]=" + arr[high] : "LT: " + v + ": arr[" + idx + "]=" + arr[idx] + " < " + v + " <= arr[" + (idx + 1) + "]=" + arr[idx + 1] + "; return arr[" + idx + "]=" + arr[idx];
         }
      }

      public String desc(long[] arr, int low, int high, long v, int idx) {
         if (idx == -1) {
            return "LT: " + v + " <= arr[" + low + "]=" + arr[low] + "; return -1";
         } else {
            return idx == high ? "LT: " + v + " > arr[" + high + "]=" + arr[high] + "; return arr[" + high + "]=" + arr[high] : "LT: " + v + ": arr[" + idx + "]=" + arr[idx] + " < " + v + " <= arr[" + (idx + 1) + "]=" + arr[idx + 1] + "; return arr[" + idx + "]=" + arr[idx];
         }
      }

      public String desc(long[] arr, int low, int high, double v, int idx) {
         if (idx == -1) {
            return "LT: " + v + " <= arr[" + low + "]=" + arr[low] + "; return -1";
         } else {
            return idx == high ? "LT: " + v + " > arr[" + high + "]=" + arr[high] + "; return arr[" + high + "]=" + arr[high] : "LT: " + v + ": arr[" + idx + "]=" + arr[idx] + " < " + v + " <= arr[" + (idx + 1) + "]=" + arr[idx + 1] + "; return arr[" + idx + "]=" + arr[idx];
         }
      }
   },
   LE {
      int compare(double[] arr, int a, int b, double v) {
         return v < arr[a] ? -1 : (arr[b] <= v ? 1 : 0);
      }

      int compare(float[] arr, int a, int b, float v) {
         return v < arr[a] ? -1 : (arr[b] <= v ? 1 : 0);
      }

      int compare(long[] arr, int a, int b, long v) {
         return v < arr[a] ? -1 : (arr[b] <= v ? 1 : 0);
      }

      int compare(long[] arr, int a, int b, double v) {
         return v < (double)arr[a] ? -1 : ((double)arr[b] <= v ? 1 : 0);
      }

      int getIndex(double[] arr, int a, int b, double v) {
         return a;
      }

      int getIndex(float[] arr, int a, int b, float v) {
         return a;
      }

      int getIndex(long[] arr, int a, int b, long v) {
         return a;
      }

      int getIndex(long[] arr, int a, int b, double v) {
         return a;
      }

      int resolve(double[] arr, int lo, int hi, double v) {
         return lo == hi ? (v >= arr[lo] ? lo : -1) : (v >= arr[hi] ? hi : (v >= arr[lo] ? lo : -1));
      }

      int resolve(float[] arr, int lo, int hi, float v) {
         return lo == hi ? (v >= arr[lo] ? lo : -1) : (v >= arr[hi] ? hi : (v >= arr[lo] ? lo : -1));
      }

      int resolve(long[] arr, int lo, int hi, long v) {
         return lo == hi ? (v >= arr[lo] ? lo : -1) : (v >= arr[hi] ? hi : (v >= arr[lo] ? lo : -1));
      }

      int resolve(long[] arr, int lo, int hi, double v) {
         return lo == hi ? (v >= (double)arr[lo] ? lo : -1) : (v >= (double)arr[hi] ? hi : (v >= (double)arr[lo] ? lo : -1));
      }

      public String desc(double[] arr, int low, int high, double v, int idx) {
         if (idx == -1) {
            return "LE: " + v + " < arr[" + low + "]=" + arr[low] + "; return -1";
         } else {
            return idx == high ? "LE: " + v + " >= arr[" + high + "]=" + arr[high] + "; return arr[" + high + "]=" + arr[high] : "LE: " + v + ": arr[" + idx + "]=" + arr[idx] + " <= " + v + " < arr[" + (idx + 1) + "]=" + arr[idx + 1] + "; return arr[" + idx + "]=" + arr[idx];
         }
      }

      public String desc(float[] arr, int low, int high, float v, int idx) {
         if (idx == -1) {
            return "LE: " + v + " < arr[" + low + "]=" + arr[low] + "; return -1";
         } else {
            return idx == high ? "LE: " + v + " >= arr[" + high + "]=" + arr[high] + "; return arr[" + high + "]=" + arr[high] : "LE: " + v + ": arr[" + idx + "]=" + arr[idx] + " <= " + v + " < arr[" + (idx + 1) + "]=" + arr[idx + 1] + "; return arr[" + idx + "]=" + arr[idx];
         }
      }

      public String desc(long[] arr, int low, int high, long v, int idx) {
         if (idx == -1) {
            return "LE: " + v + " < arr[" + low + "]=" + arr[low] + "; return -1";
         } else {
            return idx == high ? "LE: " + v + " >= arr[" + high + "]=" + arr[high] + "; return arr[" + high + "]=" + arr[high] : "LE: " + v + ": arr[" + idx + "]=" + arr[idx] + " <= " + v + " < arr[" + (idx + 1) + "]=" + arr[idx + 1] + "; return arr[" + idx + "]=" + arr[idx];
         }
      }

      public String desc(long[] arr, int low, int high, double v, int idx) {
         if (idx == -1) {
            return "LE: " + v + " < arr[" + low + "]=" + arr[low] + "; return -1";
         } else {
            return idx == high ? "LE: " + v + " >= arr[" + high + "]=" + arr[high] + "; return arr[" + high + "]=" + arr[high] : "LE: " + v + ": arr[" + idx + "]=" + arr[idx] + " <= " + v + " < arr[" + (idx + 1) + "]=" + arr[idx + 1] + "; return arr[" + idx + "]=" + arr[idx];
         }
      }
   },
   EQ {
      int compare(double[] arr, int a, int b, double v) {
         return v < arr[a] ? -1 : (arr[b] < v ? 1 : 0);
      }

      int compare(float[] arr, int a, int b, float v) {
         return v < arr[a] ? -1 : (arr[b] < v ? 1 : 0);
      }

      int compare(long[] arr, int a, int b, long v) {
         return v < arr[a] ? -1 : (arr[b] < v ? 1 : 0);
      }

      int compare(long[] arr, int a, int b, double v) {
         return v < (double)arr[a] ? -1 : ((double)arr[b] < v ? 1 : 0);
      }

      int getIndex(double[] arr, int a, int b, double v) {
         return v == arr[a] ? a : (v == arr[b] ? b : -1);
      }

      int getIndex(float[] arr, int a, int b, float v) {
         return v == arr[a] ? a : (v == arr[b] ? b : -1);
      }

      int getIndex(long[] arr, int a, int b, long v) {
         return v == arr[a] ? a : (v == arr[b] ? b : -1);
      }

      int getIndex(long[] arr, int a, int b, double v) {
         return v == (double)arr[a] ? a : (v == (double)arr[b] ? b : -1);
      }

      int resolve(double[] arr, int lo, int hi, double v) {
         return lo == hi ? (v == arr[lo] ? lo : -1) : (v == arr[lo] ? lo : (v == arr[hi] ? hi : -1));
      }

      int resolve(float[] arr, int lo, int hi, float v) {
         return lo == hi ? (v == arr[lo] ? lo : -1) : (v == arr[lo] ? lo : (v == arr[hi] ? hi : -1));
      }

      int resolve(long[] arr, int lo, int hi, long v) {
         return lo == hi ? (v == arr[lo] ? lo : -1) : (v == arr[lo] ? lo : (v == arr[hi] ? hi : -1));
      }

      int resolve(long[] arr, int lo, int hi, double v) {
         return lo == hi ? (v == (double)arr[lo] ? lo : -1) : (v == (double)arr[lo] ? lo : (v == (double)arr[hi] ? hi : -1));
      }

      public String desc(double[] arr, int low, int high, double v, int idx) {
         if (idx == -1) {
            if (v > arr[high]) {
               return "EQ: " + v + " > arr[" + high + "]; return -1";
            } else {
               return v < arr[low] ? "EQ: " + v + " < arr[" + low + "]; return -1" : "EQ: " + v + " Cannot be found within arr[" + low + "], arr[" + high + "]; return -1";
            }
         } else {
            return "EQ: " + v + " == arr[" + idx + "]; return arr[" + idx + "]=" + arr[idx];
         }
      }

      public String desc(float[] arr, int low, int high, float v, int idx) {
         if (idx == -1) {
            if (v > arr[high]) {
               return "EQ: " + v + " > arr[" + high + "]; return -1";
            } else {
               return v < arr[low] ? "EQ: " + v + " < arr[" + low + "]; return -1" : "EQ: " + v + " Cannot be found within arr[" + low + "], arr[" + high + "]; return -1";
            }
         } else {
            return "EQ: " + v + " == arr[" + idx + "]; return arr[" + idx + "]=" + arr[idx];
         }
      }

      public String desc(long[] arr, int low, int high, long v, int idx) {
         if (idx == -1) {
            if (v > arr[high]) {
               return "EQ: " + v + " > arr[" + high + "]; return -1";
            } else {
               return v < arr[low] ? "EQ: " + v + " < arr[" + low + "]; return -1" : "EQ: " + v + " Cannot be found within arr[" + low + "], arr[" + high + "]; return -1";
            }
         } else {
            return "EQ: " + v + " == arr[" + idx + "]; return arr[" + idx + "]=" + arr[idx];
         }
      }

      public String desc(long[] arr, int low, int high, double v, int idx) {
         if (idx == -1) {
            if (v > (double)arr[high]) {
               return "EQ: " + v + " > arr[" + high + "]; return -1";
            } else {
               return v < (double)arr[low] ? "EQ: " + v + " < arr[" + low + "]; return -1" : "EQ: " + v + " Cannot be found within arr[" + low + "], arr[" + high + "]; return -1";
            }
         } else {
            return "EQ: " + v + " == arr[" + idx + "]; return arr[" + idx + "]=" + arr[idx];
         }
      }
   },
   GE {
      int compare(double[] arr, int a, int b, double v) {
         return v <= arr[a] ? -1 : (arr[b] < v ? 1 : 0);
      }

      int compare(float[] arr, int a, int b, float v) {
         return v <= arr[a] ? -1 : (arr[b] < v ? 1 : 0);
      }

      int compare(long[] arr, int a, int b, long v) {
         return v <= arr[a] ? -1 : (arr[b] < v ? 1 : 0);
      }

      int compare(long[] arr, int a, int b, double v) {
         return v <= (double)arr[a] ? -1 : ((double)arr[b] < v ? 1 : 0);
      }

      int getIndex(double[] arr, int a, int b, double v) {
         return b;
      }

      int getIndex(float[] arr, int a, int b, float v) {
         return b;
      }

      int getIndex(long[] arr, int a, int b, long v) {
         return b;
      }

      int getIndex(long[] arr, int a, int b, double v) {
         return b;
      }

      int resolve(double[] arr, int lo, int hi, double v) {
         return lo == hi ? (v <= arr[lo] ? lo : -1) : (v <= arr[lo] ? lo : (v <= arr[hi] ? hi : -1));
      }

      int resolve(float[] arr, int lo, int hi, float v) {
         return lo == hi ? (v <= arr[lo] ? lo : -1) : (v <= arr[lo] ? lo : (v <= arr[hi] ? hi : -1));
      }

      int resolve(long[] arr, int lo, int hi, long v) {
         return lo == hi ? (v <= arr[lo] ? lo : -1) : (v <= arr[lo] ? lo : (v <= arr[hi] ? hi : -1));
      }

      int resolve(long[] arr, int lo, int hi, double v) {
         return lo == hi ? (v <= (double)arr[lo] ? lo : -1) : (v <= (double)arr[lo] ? lo : (v <= (double)arr[hi] ? hi : -1));
      }

      public String desc(double[] arr, int low, int high, double v, int idx) {
         if (idx == -1) {
            return "GE: " + v + " > arr[" + high + "]=" + arr[high] + "; return -1";
         } else {
            return idx == low ? "GE: " + v + " <= arr[" + low + "]=" + arr[low] + "; return arr[" + low + "]=" + arr[low] : "GE: " + v + ": arr[" + (idx - 1) + "]=" + arr[idx - 1] + " < " + v + " <= arr[" + idx + "]=" + arr[idx] + "; return arr[" + idx + "]=" + arr[idx];
         }
      }

      public String desc(float[] arr, int low, int high, float v, int idx) {
         if (idx == -1) {
            return "GE: " + v + " > arr[" + high + "]=" + arr[high] + "; return -1";
         } else {
            return idx == low ? "GE: " + v + " <= arr[" + low + "]=" + arr[low] + "; return arr[" + low + "]=" + arr[low] : "GE: " + v + ": arr[" + (idx - 1) + "]=" + arr[idx - 1] + " < " + v + " <= arr[" + idx + "]=" + arr[idx] + "; return arr[" + idx + "]=" + arr[idx];
         }
      }

      public String desc(long[] arr, int low, int high, long v, int idx) {
         if (idx == -1) {
            return "GE: " + v + " > arr[" + high + "]=" + arr[high] + "; return -1";
         } else {
            return idx == low ? "GE: " + v + " <= arr[" + low + "]=" + arr[low] + "; return arr[" + low + "]=" + arr[low] : "GE: " + v + ": arr[" + (idx - 1) + "]=" + arr[idx - 1] + " < " + v + " <= arr[" + idx + "]=" + arr[idx] + "; return arr[" + idx + "]=" + arr[idx];
         }
      }

      public String desc(long[] arr, int low, int high, double v, int idx) {
         if (idx == -1) {
            return "GE: " + v + " > arr[" + high + "]=" + arr[high] + "; return -1";
         } else {
            return idx == low ? "GE: " + v + " <= arr[" + low + "]=" + arr[low] + "; return arr[" + low + "]=" + arr[low] : "GE: " + v + ": arr[" + (idx - 1) + "]=" + arr[idx - 1] + " < " + v + " <= arr[" + idx + "]=" + arr[idx] + "; return arr[" + idx + "]=" + arr[idx];
         }
      }
   },
   GT {
      int compare(double[] arr, int a, int b, double v) {
         return v < arr[a] ? -1 : (arr[b] <= v ? 1 : 0);
      }

      int compare(float[] arr, int a, int b, float v) {
         return v < arr[a] ? -1 : (arr[b] <= v ? 1 : 0);
      }

      int compare(long[] arr, int a, int b, long v) {
         return v < arr[a] ? -1 : (arr[b] <= v ? 1 : 0);
      }

      int compare(long[] arr, int a, int b, double v) {
         return v < (double)arr[a] ? -1 : ((double)arr[b] <= v ? 1 : 0);
      }

      int getIndex(double[] arr, int a, int b, double v) {
         return b;
      }

      int getIndex(float[] arr, int a, int b, float v) {
         return b;
      }

      int getIndex(long[] arr, int a, int b, long v) {
         return b;
      }

      int getIndex(long[] arr, int a, int b, double v) {
         return b;
      }

      int resolve(double[] arr, int lo, int hi, double v) {
         return lo == hi ? (v < arr[lo] ? lo : -1) : (v < arr[lo] ? lo : (v < arr[hi] ? hi : -1));
      }

      int resolve(float[] arr, int lo, int hi, float v) {
         return lo == hi ? (v < arr[lo] ? lo : -1) : (v < arr[lo] ? lo : (v < arr[hi] ? hi : -1));
      }

      int resolve(long[] arr, int lo, int hi, long v) {
         return lo == hi ? (v < arr[lo] ? lo : -1) : (v < arr[lo] ? lo : (v < arr[hi] ? hi : -1));
      }

      int resolve(long[] arr, int lo, int hi, double v) {
         return lo == hi ? (v < (double)arr[lo] ? lo : -1) : (v < (double)arr[lo] ? lo : (v < (double)arr[hi] ? hi : -1));
      }

      public String desc(double[] arr, int low, int high, double v, int idx) {
         if (idx == -1) {
            return "GT: " + v + " >= arr[" + high + "]=" + arr[high] + "; return -1";
         } else {
            return idx == low ? "GT: " + v + " < arr[" + low + "]=" + arr[low] + "; return arr[" + low + "]=" + arr[low] : "GT: " + v + ": arr[" + (idx - 1) + "]=" + arr[idx - 1] + " <= " + v + " < arr[" + idx + "]=" + arr[idx] + "; return arr[" + idx + "]=" + arr[idx];
         }
      }

      public String desc(float[] arr, int low, int high, float v, int idx) {
         if (idx == -1) {
            return "GT: " + v + " >= arr[" + high + "]=" + arr[high] + "; return -1";
         } else {
            return idx == low ? "GT: " + v + " < arr[" + low + "]=" + arr[low] + "; return arr[" + low + "]=" + arr[low] : "GT: " + v + ": arr[" + (idx - 1) + "]=" + arr[idx - 1] + " <= " + v + " < arr[" + idx + "]=" + arr[idx] + "; return arr[" + idx + "]=" + arr[idx];
         }
      }

      public String desc(long[] arr, int low, int high, long v, int idx) {
         if (idx == -1) {
            return "GT: " + v + " >= arr[" + high + "]=" + arr[high] + "; return -1";
         } else {
            return idx == low ? "GT: " + v + " < arr[" + low + "]=" + arr[low] + "; return arr[" + low + "]=" + arr[low] : "GT: " + v + ": arr[" + (idx - 1) + "]=" + arr[idx - 1] + " <= " + v + " < arr[" + idx + "]=" + arr[idx] + "; return arr[" + idx + "]=" + arr[idx];
         }
      }

      public String desc(long[] arr, int low, int high, double v, int idx) {
         if (idx == -1) {
            return "GT: " + v + " >= arr[" + high + "]=" + arr[high] + "; return -1";
         } else {
            return idx == low ? "GT: " + v + " < arr[" + low + "]=" + arr[low] + "; return arr[" + low + "]=" + arr[low] : "GT: " + v + ": arr[" + (idx - 1) + "]=" + arr[idx - 1] + " <= " + v + " < arr[" + idx + "]=" + arr[idx] + "; return arr[" + idx + "]=" + arr[idx];
         }
      }
   };

   private InequalitySearch() {
   }

   abstract int compare(double[] var1, int var2, int var3, double var4);

   abstract int compare(float[] var1, int var2, int var3, float var4);

   abstract int compare(long[] var1, int var2, int var3, long var4);

   abstract int compare(long[] var1, int var2, int var3, double var4);

   abstract int getIndex(double[] var1, int var2, int var3, double var4);

   abstract int getIndex(float[] var1, int var2, int var3, float var4);

   abstract int getIndex(long[] var1, int var2, int var3, long var4);

   abstract int getIndex(long[] var1, int var2, int var3, double var4);

   abstract int resolve(double[] var1, int var2, int var3, double var4);

   abstract int resolve(float[] var1, int var2, int var3, float var4);

   abstract int resolve(long[] var1, int var2, int var3, long var4);

   abstract int resolve(long[] var1, int var2, int var3, double var4);

   public abstract String desc(double[] var1, int var2, int var3, double var4, int var6);

   public abstract String desc(float[] var1, int var2, int var3, float var4, int var5);

   public abstract String desc(long[] var1, int var2, int var3, long var4, int var6);

   public abstract String desc(long[] var1, int var2, int var3, double var4, int var6);

   public static int find(double[] arr, int low, int high, double v, InequalitySearch crit) {
      Objects.requireNonNull(arr, "Input arr must not be null");
      Objects.requireNonNull(crit, "Input crit must not be null");
      if (arr.length == 0) {
         throw new SketchesArgumentException("Input array must not be empty.");
      } else if (Double.isNaN(v)) {
         throw new SketchesArgumentException("Input v must not be NaN.");
      } else {
         int lo = low;
         int hi = high;

         while(lo <= hi) {
            if (hi - lo <= 1) {
               return crit.resolve(arr, lo, hi, v);
            }

            int mid = lo + (hi - lo) / 2;
            int ret = crit.compare(arr, mid, mid + 1, v);
            if (ret == -1) {
               hi = mid;
            } else {
               if (ret != 1) {
                  return crit.getIndex(arr, mid, mid + 1, v);
               }

               lo = mid + 1;
            }
         }

         return -1;
      }
   }

   public static int find(float[] arr, int low, int high, float v, InequalitySearch crit) {
      Objects.requireNonNull(arr, "Input arr must not be null");
      Objects.requireNonNull(crit, "Input crit must not be null");
      if (arr.length == 0) {
         throw new SketchesArgumentException("Input array must not be empty.");
      } else if (Float.isNaN(v)) {
         throw new SketchesArgumentException("Input v must not be NaN.");
      } else {
         int lo = low;
         int hi = high;

         while(lo <= hi) {
            if (hi - lo <= 1) {
               return crit.resolve(arr, lo, hi, v);
            }

            int mid = lo + (hi - lo) / 2;
            int ret = crit.compare(arr, mid, mid + 1, v);
            if (ret == -1) {
               hi = mid;
            } else {
               if (ret != 1) {
                  return crit.getIndex(arr, mid, mid + 1, v);
               }

               lo = mid + 1;
            }
         }

         return -1;
      }
   }

   public static int find(long[] arr, int low, int high, long v, InequalitySearch crit) {
      Objects.requireNonNull(arr, "Input arr must not be null");
      Objects.requireNonNull(crit, "Input crit must not be null");
      if (arr.length == 0) {
         throw new SketchesArgumentException("Input array must not be empty.");
      } else {
         int lo = low;
         int hi = high;

         while(lo <= hi) {
            if (hi - lo <= 1) {
               return crit.resolve(arr, lo, hi, v);
            }

            int mid = lo + (hi - lo) / 2;
            int ret = crit.compare(arr, mid, mid + 1, v);
            if (ret == -1) {
               hi = mid;
            } else {
               if (ret != 1) {
                  return crit.getIndex(arr, mid, mid + 1, v);
               }

               lo = mid + 1;
            }
         }

         return -1;
      }
   }

   public static int find(long[] arr, int low, int high, double v, InequalitySearch crit) {
      Objects.requireNonNull(arr, "Input arr must not be null");
      Objects.requireNonNull(crit, "Input crit must not be null");
      if (arr.length == 0) {
         throw new SketchesArgumentException("Input array must not be empty.");
      } else {
         int lo = low;
         int hi = high;

         while(lo <= hi) {
            if (hi - lo <= 1) {
               return crit.resolve(arr, lo, hi, v);
            }

            int mid = lo + (hi - lo) / 2;
            int ret = crit.compare(arr, mid, mid + 1, v);
            if (ret == -1) {
               hi = mid;
            } else {
               if (ret != 1) {
                  return crit.getIndex(arr, mid, mid + 1, v);
               }

               lo = mid + 1;
            }
         }

         return -1;
      }
   }
}
