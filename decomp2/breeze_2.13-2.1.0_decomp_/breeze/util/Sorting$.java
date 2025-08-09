package breeze.util;

import java.lang.invoke.SerializedLambda;
import scala.Predef.;
import scala.runtime.BoxedUnit;

public final class Sorting$ {
   public static final Sorting$ MODULE$ = new Sorting$();

   public void indirectSort(final int[] keys, final Object elems, final int off, final int length) {
      this.indirectSort_Int(keys, elems, off, length);
   }

   public void indirectSort(final long[] keys, final Object elems, final int off, final int length) {
      this.indirectSort_Long(keys, elems, off, length);
   }

   public void indirectSort(final float[] keys, final Object elems, final int off, final int length) {
      this.indirectSort_Float(keys, elems, off, length);
   }

   public void indirectSort(final double[] keys, final Object elems, final int off, final int length) {
      this.indirectSort_Double(keys, elems, off, length);
   }

   public void indirectSort_Int(final int[] keys, final Object elems, final int off, final int length) {
      .MODULE$.require(keys.length == scala.runtime.ScalaRunTime..MODULE$.array_length(elems), () -> "arrays must have the same length");
      this.sort2$1(off, length, keys, elems);
   }

   public void indirectSort_Long(final long[] keys, final Object elems, final int off, final int length) {
      .MODULE$.require(keys.length == scala.runtime.ScalaRunTime..MODULE$.array_length(elems), () -> "arrays must have the same length");
      this.sort2$2(off, length, keys, elems);
   }

   public void indirectSort_Float(final float[] keys, final Object elems, final int off, final int length) {
      .MODULE$.require(keys.length == scala.runtime.ScalaRunTime..MODULE$.array_length(elems), () -> "arrays must have the same length");
      this.sort2$3(off, length, keys, elems);
   }

   public void indirectSort_Double(final double[] keys, final Object elems, final int off, final int length) {
      .MODULE$.require(keys.length == scala.runtime.ScalaRunTime..MODULE$.array_length(elems), () -> "arrays must have the same length");
      this.sort2$4(off, length, keys, elems);
   }

   public void indirectSort$mDc$sp(final int[] keys, final double[] elems, final int off, final int length) {
      this.indirectSort_Int$mDc$sp(keys, elems, off, length);
   }

   public void indirectSort$mFc$sp(final int[] keys, final float[] elems, final int off, final int length) {
      this.indirectSort_Int$mFc$sp(keys, elems, off, length);
   }

   public void indirectSort$mIc$sp(final int[] keys, final int[] elems, final int off, final int length) {
      this.indirectSort_Int$mIc$sp(keys, elems, off, length);
   }

   public void indirectSort$mJc$sp(final int[] keys, final long[] elems, final int off, final int length) {
      this.indirectSort_Int$mJc$sp(keys, elems, off, length);
   }

   public void indirectSort$mDc$sp(final long[] keys, final double[] elems, final int off, final int length) {
      this.indirectSort_Long$mDc$sp(keys, elems, off, length);
   }

   public void indirectSort$mFc$sp(final long[] keys, final float[] elems, final int off, final int length) {
      this.indirectSort_Long$mFc$sp(keys, elems, off, length);
   }

   public void indirectSort$mIc$sp(final long[] keys, final int[] elems, final int off, final int length) {
      this.indirectSort_Long$mIc$sp(keys, elems, off, length);
   }

   public void indirectSort$mJc$sp(final long[] keys, final long[] elems, final int off, final int length) {
      this.indirectSort_Long$mJc$sp(keys, elems, off, length);
   }

   public void indirectSort$mDc$sp(final float[] keys, final double[] elems, final int off, final int length) {
      this.indirectSort_Float$mDc$sp(keys, elems, off, length);
   }

   public void indirectSort$mFc$sp(final float[] keys, final float[] elems, final int off, final int length) {
      this.indirectSort_Float$mFc$sp(keys, elems, off, length);
   }

   public void indirectSort$mIc$sp(final float[] keys, final int[] elems, final int off, final int length) {
      this.indirectSort_Float$mIc$sp(keys, elems, off, length);
   }

   public void indirectSort$mJc$sp(final float[] keys, final long[] elems, final int off, final int length) {
      this.indirectSort_Float$mJc$sp(keys, elems, off, length);
   }

   public void indirectSort$mDc$sp(final double[] keys, final double[] elems, final int off, final int length) {
      this.indirectSort_Double$mDc$sp(keys, elems, off, length);
   }

   public void indirectSort$mFc$sp(final double[] keys, final float[] elems, final int off, final int length) {
      this.indirectSort_Double$mFc$sp(keys, elems, off, length);
   }

   public void indirectSort$mIc$sp(final double[] keys, final int[] elems, final int off, final int length) {
      this.indirectSort_Double$mIc$sp(keys, elems, off, length);
   }

   public void indirectSort$mJc$sp(final double[] keys, final long[] elems, final int off, final int length) {
      this.indirectSort_Double$mJc$sp(keys, elems, off, length);
   }

   public void indirectSort_Int$mDc$sp(final int[] keys, final double[] elems, final int off, final int length) {
      .MODULE$.require(keys.length == elems.length, () -> "arrays must have the same length");
      this.sort2$5(off, length, keys, elems);
   }

   public void indirectSort_Int$mFc$sp(final int[] keys, final float[] elems, final int off, final int length) {
      .MODULE$.require(keys.length == elems.length, () -> "arrays must have the same length");
      this.sort2$6(off, length, keys, elems);
   }

   public void indirectSort_Int$mIc$sp(final int[] keys, final int[] elems, final int off, final int length) {
      .MODULE$.require(keys.length == elems.length, () -> "arrays must have the same length");
      this.sort2$7(off, length, keys, elems);
   }

   public void indirectSort_Int$mJc$sp(final int[] keys, final long[] elems, final int off, final int length) {
      .MODULE$.require(keys.length == elems.length, () -> "arrays must have the same length");
      this.sort2$8(off, length, keys, elems);
   }

   public void indirectSort_Long$mDc$sp(final long[] keys, final double[] elems, final int off, final int length) {
      .MODULE$.require(keys.length == elems.length, () -> "arrays must have the same length");
      this.sort2$9(off, length, keys, elems);
   }

   public void indirectSort_Long$mFc$sp(final long[] keys, final float[] elems, final int off, final int length) {
      .MODULE$.require(keys.length == elems.length, () -> "arrays must have the same length");
      this.sort2$10(off, length, keys, elems);
   }

   public void indirectSort_Long$mIc$sp(final long[] keys, final int[] elems, final int off, final int length) {
      .MODULE$.require(keys.length == elems.length, () -> "arrays must have the same length");
      this.sort2$11(off, length, keys, elems);
   }

   public void indirectSort_Long$mJc$sp(final long[] keys, final long[] elems, final int off, final int length) {
      .MODULE$.require(keys.length == elems.length, () -> "arrays must have the same length");
      this.sort2$12(off, length, keys, elems);
   }

   public void indirectSort_Float$mDc$sp(final float[] keys, final double[] elems, final int off, final int length) {
      .MODULE$.require(keys.length == elems.length, () -> "arrays must have the same length");
      this.sort2$13(off, length, keys, elems);
   }

   public void indirectSort_Float$mFc$sp(final float[] keys, final float[] elems, final int off, final int length) {
      .MODULE$.require(keys.length == elems.length, () -> "arrays must have the same length");
      this.sort2$14(off, length, keys, elems);
   }

   public void indirectSort_Float$mIc$sp(final float[] keys, final int[] elems, final int off, final int length) {
      .MODULE$.require(keys.length == elems.length, () -> "arrays must have the same length");
      this.sort2$15(off, length, keys, elems);
   }

   public void indirectSort_Float$mJc$sp(final float[] keys, final long[] elems, final int off, final int length) {
      .MODULE$.require(keys.length == elems.length, () -> "arrays must have the same length");
      this.sort2$16(off, length, keys, elems);
   }

   public void indirectSort_Double$mDc$sp(final double[] keys, final double[] elems, final int off, final int length) {
      .MODULE$.require(keys.length == elems.length, () -> "arrays must have the same length");
      this.sort2$17(off, length, keys, elems);
   }

   public void indirectSort_Double$mFc$sp(final double[] keys, final float[] elems, final int off, final int length) {
      .MODULE$.require(keys.length == elems.length, () -> "arrays must have the same length");
      this.sort2$18(off, length, keys, elems);
   }

   public void indirectSort_Double$mIc$sp(final double[] keys, final int[] elems, final int off, final int length) {
      .MODULE$.require(keys.length == elems.length, () -> "arrays must have the same length");
      this.sort2$19(off, length, keys, elems);
   }

   public void indirectSort_Double$mJc$sp(final double[] keys, final long[] elems, final int off, final int length) {
      .MODULE$.require(keys.length == elems.length, () -> "arrays must have the same length");
      this.sort2$20(off, length, keys, elems);
   }

   private static final void swap$1(final int a, final int b, final int[] keys$1, final Object elems$1) {
      int t0 = keys$1[a];
      keys$1[a] = keys$1[b];
      keys$1[b] = t0;
      Object t1 = scala.runtime.ScalaRunTime..MODULE$.array_apply(elems$1, a);
      scala.runtime.ScalaRunTime..MODULE$.array_update(elems$1, a, scala.runtime.ScalaRunTime..MODULE$.array_apply(elems$1, b));
      scala.runtime.ScalaRunTime..MODULE$.array_update(elems$1, b, t1);
   }

   private static final void vecswap$1(final int _a, final int _b, final int n, final int[] keys$1, final Object elems$1) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$1(a, b, keys$1, elems$1);
         ++i;
         ++a;
      }

   }

   private static final int med3$1(final int a, final int b, final int c, final int[] keys$1) {
      return keys$1[a] < keys$1[b] ? (keys$1[b] < keys$1[c] ? b : (keys$1[a] < keys$1[c] ? c : a)) : (keys$1[b] > keys$1[c] ? b : (keys$1[a] > keys$1[c] ? c : a));
   }

   private final void sort2$1(final int off, final int length, final int[] keys$1, final Object elems$1) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$1[j - 1] > keys$1[j]; --j) {
                  swap$1(j, j - 1, keys$1, elems$1);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$1(off, off + s, off + 2 * s, keys$1);
                  m = med3$1(m - s, m, m + s, keys$1);
                  n = med3$1(n - 2 * s, n - s, n, keys$1);
               }

               m = med3$1(l, m, n, keys$1);
            }

            int v = keys$1[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$1[b] <= v; ++b) {
                  if (keys$1[b] == v) {
                     swap$1(a, b, keys$1, elems$1);
                     ++a;
                  }
               }

               for(; c >= b && keys$1[c] >= v; --c) {
                  if (keys$1[c] == v) {
                     swap$1(c, d, keys$1, elems$1);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$1(b, c, keys$1, elems$1);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$1(off, b - s, s, keys$1, elems$1);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$1(b, n - s, s, keys$1, elems$1);
            s = b - a;
            if (s > 1) {
               this.sort2$1(off, s, keys$1, elems$1);
            }

            s = d - c;
            if (s > 1) {
               int var24 = n - s;
               length = s;
               off = var24;
               continue;
            }

            BoxedUnit var23 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$2(final int a, final int b, final long[] keys$2, final Object elems$2) {
      long t0 = keys$2[a];
      keys$2[a] = keys$2[b];
      keys$2[b] = t0;
      Object t1 = scala.runtime.ScalaRunTime..MODULE$.array_apply(elems$2, a);
      scala.runtime.ScalaRunTime..MODULE$.array_update(elems$2, a, scala.runtime.ScalaRunTime..MODULE$.array_apply(elems$2, b));
      scala.runtime.ScalaRunTime..MODULE$.array_update(elems$2, b, t1);
   }

   private static final void vecswap$2(final int _a, final int _b, final int n, final long[] keys$2, final Object elems$2) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$2(a, b, keys$2, elems$2);
         ++i;
         ++a;
      }

   }

   private static final int med3$2(final int a, final int b, final int c, final long[] keys$2) {
      return keys$2[a] < keys$2[b] ? (keys$2[b] < keys$2[c] ? b : (keys$2[a] < keys$2[c] ? c : a)) : (keys$2[b] > keys$2[c] ? b : (keys$2[a] > keys$2[c] ? c : a));
   }

   private final void sort2$2(final int off, final int length, final long[] keys$2, final Object elems$2) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$2[j - 1] > keys$2[j]; --j) {
                  swap$2(j, j - 1, keys$2, elems$2);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$2(off, off + s, off + 2 * s, keys$2);
                  m = med3$2(m - s, m, m + s, keys$2);
                  n = med3$2(n - 2 * s, n - s, n, keys$2);
               }

               m = med3$2(l, m, n, keys$2);
            }

            long v = keys$2[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$2[b] <= v; ++b) {
                  if (keys$2[b] == v) {
                     swap$2(a, b, keys$2, elems$2);
                     ++a;
                  }
               }

               for(; c >= b && keys$2[c] >= v; --c) {
                  if (keys$2[c] == v) {
                     swap$2(c, d, keys$2, elems$2);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$2(b, c, keys$2, elems$2);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$2(off, b - s, s, keys$2, elems$2);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$2(b, n - s, s, keys$2, elems$2);
            s = b - a;
            if (s > 1) {
               this.sort2$2(off, s, keys$2, elems$2);
            }

            s = d - c;
            if (s > 1) {
               int var25 = n - s;
               length = s;
               off = var25;
               continue;
            }

            BoxedUnit var24 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$3(final int a, final int b, final float[] keys$3, final Object elems$3) {
      float t0 = keys$3[a];
      keys$3[a] = keys$3[b];
      keys$3[b] = t0;
      Object t1 = scala.runtime.ScalaRunTime..MODULE$.array_apply(elems$3, a);
      scala.runtime.ScalaRunTime..MODULE$.array_update(elems$3, a, scala.runtime.ScalaRunTime..MODULE$.array_apply(elems$3, b));
      scala.runtime.ScalaRunTime..MODULE$.array_update(elems$3, b, t1);
   }

   private static final void vecswap$3(final int _a, final int _b, final int n, final float[] keys$3, final Object elems$3) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$3(a, b, keys$3, elems$3);
         ++i;
         ++a;
      }

   }

   private static final int med3$3(final int a, final int b, final int c, final float[] keys$3) {
      return keys$3[a] < keys$3[b] ? (keys$3[b] < keys$3[c] ? b : (keys$3[a] < keys$3[c] ? c : a)) : (keys$3[b] > keys$3[c] ? b : (keys$3[a] > keys$3[c] ? c : a));
   }

   private final void sort2$3(final int off, final int length, final float[] keys$3, final Object elems$3) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$3[j - 1] > keys$3[j]; --j) {
                  swap$3(j, j - 1, keys$3, elems$3);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$3(off, off + s, off + 2 * s, keys$3);
                  m = med3$3(m - s, m, m + s, keys$3);
                  n = med3$3(n - 2 * s, n - s, n, keys$3);
               }

               m = med3$3(l, m, n, keys$3);
            }

            float v = keys$3[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$3[b] <= v; ++b) {
                  if (keys$3[b] == v) {
                     swap$3(a, b, keys$3, elems$3);
                     ++a;
                  }
               }

               for(; c >= b && keys$3[c] >= v; --c) {
                  if (keys$3[c] == v) {
                     swap$3(c, d, keys$3, elems$3);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$3(b, c, keys$3, elems$3);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$3(off, b - s, s, keys$3, elems$3);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$3(b, n - s, s, keys$3, elems$3);
            s = b - a;
            if (s > 1) {
               this.sort2$3(off, s, keys$3, elems$3);
            }

            s = d - c;
            if (s > 1) {
               int var24 = n - s;
               length = s;
               off = var24;
               continue;
            }

            BoxedUnit var23 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$4(final int a, final int b, final double[] keys$4, final Object elems$4) {
      double t0 = keys$4[a];
      keys$4[a] = keys$4[b];
      keys$4[b] = t0;
      Object t1 = scala.runtime.ScalaRunTime..MODULE$.array_apply(elems$4, a);
      scala.runtime.ScalaRunTime..MODULE$.array_update(elems$4, a, scala.runtime.ScalaRunTime..MODULE$.array_apply(elems$4, b));
      scala.runtime.ScalaRunTime..MODULE$.array_update(elems$4, b, t1);
   }

   private static final void vecswap$4(final int _a, final int _b, final int n, final double[] keys$4, final Object elems$4) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$4(a, b, keys$4, elems$4);
         ++i;
         ++a;
      }

   }

   private static final int med3$4(final int a, final int b, final int c, final double[] keys$4) {
      return keys$4[a] < keys$4[b] ? (keys$4[b] < keys$4[c] ? b : (keys$4[a] < keys$4[c] ? c : a)) : (keys$4[b] > keys$4[c] ? b : (keys$4[a] > keys$4[c] ? c : a));
   }

   private final void sort2$4(final int off, final int length, final double[] keys$4, final Object elems$4) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$4[j - 1] > keys$4[j]; --j) {
                  swap$4(j, j - 1, keys$4, elems$4);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$4(off, off + s, off + 2 * s, keys$4);
                  m = med3$4(m - s, m, m + s, keys$4);
                  n = med3$4(n - 2 * s, n - s, n, keys$4);
               }

               m = med3$4(l, m, n, keys$4);
            }

            double v = keys$4[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$4[b] <= v; ++b) {
                  if (keys$4[b] == v) {
                     swap$4(a, b, keys$4, elems$4);
                     ++a;
                  }
               }

               for(; c >= b && keys$4[c] >= v; --c) {
                  if (keys$4[c] == v) {
                     swap$4(c, d, keys$4, elems$4);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$4(b, c, keys$4, elems$4);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$4(off, b - s, s, keys$4, elems$4);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$4(b, n - s, s, keys$4, elems$4);
            s = b - a;
            if (s > 1) {
               this.sort2$4(off, s, keys$4, elems$4);
            }

            s = d - c;
            if (s > 1) {
               int var25 = n - s;
               length = s;
               off = var25;
               continue;
            }

            BoxedUnit var24 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$5(final int a, final int b, final int[] keys$5, final double[] elems$5) {
      int t0 = keys$5[a];
      keys$5[a] = keys$5[b];
      keys$5[b] = t0;
      double t1 = elems$5[a];
      elems$5[a] = elems$5[b];
      elems$5[b] = t1;
   }

   private static final void vecswap$5(final int _a, final int _b, final int n, final int[] keys$5, final double[] elems$5) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$5(a, b, keys$5, elems$5);
         ++i;
         ++a;
      }

   }

   private static final int med3$5(final int a, final int b, final int c, final int[] keys$5) {
      return keys$5[a] < keys$5[b] ? (keys$5[b] < keys$5[c] ? b : (keys$5[a] < keys$5[c] ? c : a)) : (keys$5[b] > keys$5[c] ? b : (keys$5[a] > keys$5[c] ? c : a));
   }

   private final void sort2$5(final int off, final int length, final int[] keys$5, final double[] elems$5) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$5[j - 1] > keys$5[j]; --j) {
                  swap$5(j, j - 1, keys$5, elems$5);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$5(off, off + s, off + 2 * s, keys$5);
                  m = med3$5(m - s, m, m + s, keys$5);
                  n = med3$5(n - 2 * s, n - s, n, keys$5);
               }

               m = med3$5(l, m, n, keys$5);
            }

            int v = keys$5[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$5[b] <= v; ++b) {
                  if (keys$5[b] == v) {
                     swap$5(a, b, keys$5, elems$5);
                     ++a;
                  }
               }

               for(; c >= b && keys$5[c] >= v; --c) {
                  if (keys$5[c] == v) {
                     swap$5(c, d, keys$5, elems$5);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$5(b, c, keys$5, elems$5);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$5(off, b - s, s, keys$5, elems$5);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$5(b, n - s, s, keys$5, elems$5);
            s = b - a;
            if (s > 1) {
               this.sort2$5(off, s, keys$5, elems$5);
            }

            s = d - c;
            if (s > 1) {
               int var24 = n - s;
               length = s;
               off = var24;
               continue;
            }

            BoxedUnit var23 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$6(final int a, final int b, final int[] keys$6, final float[] elems$6) {
      int t0 = keys$6[a];
      keys$6[a] = keys$6[b];
      keys$6[b] = t0;
      float t1 = elems$6[a];
      elems$6[a] = elems$6[b];
      elems$6[b] = t1;
   }

   private static final void vecswap$6(final int _a, final int _b, final int n, final int[] keys$6, final float[] elems$6) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$6(a, b, keys$6, elems$6);
         ++i;
         ++a;
      }

   }

   private static final int med3$6(final int a, final int b, final int c, final int[] keys$6) {
      return keys$6[a] < keys$6[b] ? (keys$6[b] < keys$6[c] ? b : (keys$6[a] < keys$6[c] ? c : a)) : (keys$6[b] > keys$6[c] ? b : (keys$6[a] > keys$6[c] ? c : a));
   }

   private final void sort2$6(final int off, final int length, final int[] keys$6, final float[] elems$6) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$6[j - 1] > keys$6[j]; --j) {
                  swap$6(j, j - 1, keys$6, elems$6);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$6(off, off + s, off + 2 * s, keys$6);
                  m = med3$6(m - s, m, m + s, keys$6);
                  n = med3$6(n - 2 * s, n - s, n, keys$6);
               }

               m = med3$6(l, m, n, keys$6);
            }

            int v = keys$6[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$6[b] <= v; ++b) {
                  if (keys$6[b] == v) {
                     swap$6(a, b, keys$6, elems$6);
                     ++a;
                  }
               }

               for(; c >= b && keys$6[c] >= v; --c) {
                  if (keys$6[c] == v) {
                     swap$6(c, d, keys$6, elems$6);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$6(b, c, keys$6, elems$6);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$6(off, b - s, s, keys$6, elems$6);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$6(b, n - s, s, keys$6, elems$6);
            s = b - a;
            if (s > 1) {
               this.sort2$6(off, s, keys$6, elems$6);
            }

            s = d - c;
            if (s > 1) {
               int var24 = n - s;
               length = s;
               off = var24;
               continue;
            }

            BoxedUnit var23 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$7(final int a, final int b, final int[] keys$7, final int[] elems$7) {
      int t0 = keys$7[a];
      keys$7[a] = keys$7[b];
      keys$7[b] = t0;
      int t1 = elems$7[a];
      elems$7[a] = elems$7[b];
      elems$7[b] = t1;
   }

   private static final void vecswap$7(final int _a, final int _b, final int n, final int[] keys$7, final int[] elems$7) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$7(a, b, keys$7, elems$7);
         ++i;
         ++a;
      }

   }

   private static final int med3$7(final int a, final int b, final int c, final int[] keys$7) {
      return keys$7[a] < keys$7[b] ? (keys$7[b] < keys$7[c] ? b : (keys$7[a] < keys$7[c] ? c : a)) : (keys$7[b] > keys$7[c] ? b : (keys$7[a] > keys$7[c] ? c : a));
   }

   private final void sort2$7(final int off, final int length, final int[] keys$7, final int[] elems$7) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$7[j - 1] > keys$7[j]; --j) {
                  swap$7(j, j - 1, keys$7, elems$7);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$7(off, off + s, off + 2 * s, keys$7);
                  m = med3$7(m - s, m, m + s, keys$7);
                  n = med3$7(n - 2 * s, n - s, n, keys$7);
               }

               m = med3$7(l, m, n, keys$7);
            }

            int v = keys$7[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$7[b] <= v; ++b) {
                  if (keys$7[b] == v) {
                     swap$7(a, b, keys$7, elems$7);
                     ++a;
                  }
               }

               for(; c >= b && keys$7[c] >= v; --c) {
                  if (keys$7[c] == v) {
                     swap$7(c, d, keys$7, elems$7);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$7(b, c, keys$7, elems$7);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$7(off, b - s, s, keys$7, elems$7);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$7(b, n - s, s, keys$7, elems$7);
            s = b - a;
            if (s > 1) {
               this.sort2$7(off, s, keys$7, elems$7);
            }

            s = d - c;
            if (s > 1) {
               int var24 = n - s;
               length = s;
               off = var24;
               continue;
            }

            BoxedUnit var23 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$8(final int a, final int b, final int[] keys$8, final long[] elems$8) {
      int t0 = keys$8[a];
      keys$8[a] = keys$8[b];
      keys$8[b] = t0;
      long t1 = elems$8[a];
      elems$8[a] = elems$8[b];
      elems$8[b] = t1;
   }

   private static final void vecswap$8(final int _a, final int _b, final int n, final int[] keys$8, final long[] elems$8) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$8(a, b, keys$8, elems$8);
         ++i;
         ++a;
      }

   }

   private static final int med3$8(final int a, final int b, final int c, final int[] keys$8) {
      return keys$8[a] < keys$8[b] ? (keys$8[b] < keys$8[c] ? b : (keys$8[a] < keys$8[c] ? c : a)) : (keys$8[b] > keys$8[c] ? b : (keys$8[a] > keys$8[c] ? c : a));
   }

   private final void sort2$8(final int off, final int length, final int[] keys$8, final long[] elems$8) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$8[j - 1] > keys$8[j]; --j) {
                  swap$8(j, j - 1, keys$8, elems$8);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$8(off, off + s, off + 2 * s, keys$8);
                  m = med3$8(m - s, m, m + s, keys$8);
                  n = med3$8(n - 2 * s, n - s, n, keys$8);
               }

               m = med3$8(l, m, n, keys$8);
            }

            int v = keys$8[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$8[b] <= v; ++b) {
                  if (keys$8[b] == v) {
                     swap$8(a, b, keys$8, elems$8);
                     ++a;
                  }
               }

               for(; c >= b && keys$8[c] >= v; --c) {
                  if (keys$8[c] == v) {
                     swap$8(c, d, keys$8, elems$8);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$8(b, c, keys$8, elems$8);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$8(off, b - s, s, keys$8, elems$8);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$8(b, n - s, s, keys$8, elems$8);
            s = b - a;
            if (s > 1) {
               this.sort2$8(off, s, keys$8, elems$8);
            }

            s = d - c;
            if (s > 1) {
               int var24 = n - s;
               length = s;
               off = var24;
               continue;
            }

            BoxedUnit var23 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$9(final int a, final int b, final long[] keys$9, final double[] elems$9) {
      long t0 = keys$9[a];
      keys$9[a] = keys$9[b];
      keys$9[b] = t0;
      double t1 = elems$9[a];
      elems$9[a] = elems$9[b];
      elems$9[b] = t1;
   }

   private static final void vecswap$9(final int _a, final int _b, final int n, final long[] keys$9, final double[] elems$9) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$9(a, b, keys$9, elems$9);
         ++i;
         ++a;
      }

   }

   private static final int med3$9(final int a, final int b, final int c, final long[] keys$9) {
      return keys$9[a] < keys$9[b] ? (keys$9[b] < keys$9[c] ? b : (keys$9[a] < keys$9[c] ? c : a)) : (keys$9[b] > keys$9[c] ? b : (keys$9[a] > keys$9[c] ? c : a));
   }

   private final void sort2$9(final int off, final int length, final long[] keys$9, final double[] elems$9) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$9[j - 1] > keys$9[j]; --j) {
                  swap$9(j, j - 1, keys$9, elems$9);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$9(off, off + s, off + 2 * s, keys$9);
                  m = med3$9(m - s, m, m + s, keys$9);
                  n = med3$9(n - 2 * s, n - s, n, keys$9);
               }

               m = med3$9(l, m, n, keys$9);
            }

            long v = keys$9[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$9[b] <= v; ++b) {
                  if (keys$9[b] == v) {
                     swap$9(a, b, keys$9, elems$9);
                     ++a;
                  }
               }

               for(; c >= b && keys$9[c] >= v; --c) {
                  if (keys$9[c] == v) {
                     swap$9(c, d, keys$9, elems$9);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$9(b, c, keys$9, elems$9);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$9(off, b - s, s, keys$9, elems$9);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$9(b, n - s, s, keys$9, elems$9);
            s = b - a;
            if (s > 1) {
               this.sort2$9(off, s, keys$9, elems$9);
            }

            s = d - c;
            if (s > 1) {
               int var25 = n - s;
               length = s;
               off = var25;
               continue;
            }

            BoxedUnit var24 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$10(final int a, final int b, final long[] keys$10, final float[] elems$10) {
      long t0 = keys$10[a];
      keys$10[a] = keys$10[b];
      keys$10[b] = t0;
      float t1 = elems$10[a];
      elems$10[a] = elems$10[b];
      elems$10[b] = t1;
   }

   private static final void vecswap$10(final int _a, final int _b, final int n, final long[] keys$10, final float[] elems$10) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$10(a, b, keys$10, elems$10);
         ++i;
         ++a;
      }

   }

   private static final int med3$10(final int a, final int b, final int c, final long[] keys$10) {
      return keys$10[a] < keys$10[b] ? (keys$10[b] < keys$10[c] ? b : (keys$10[a] < keys$10[c] ? c : a)) : (keys$10[b] > keys$10[c] ? b : (keys$10[a] > keys$10[c] ? c : a));
   }

   private final void sort2$10(final int off, final int length, final long[] keys$10, final float[] elems$10) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$10[j - 1] > keys$10[j]; --j) {
                  swap$10(j, j - 1, keys$10, elems$10);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$10(off, off + s, off + 2 * s, keys$10);
                  m = med3$10(m - s, m, m + s, keys$10);
                  n = med3$10(n - 2 * s, n - s, n, keys$10);
               }

               m = med3$10(l, m, n, keys$10);
            }

            long v = keys$10[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$10[b] <= v; ++b) {
                  if (keys$10[b] == v) {
                     swap$10(a, b, keys$10, elems$10);
                     ++a;
                  }
               }

               for(; c >= b && keys$10[c] >= v; --c) {
                  if (keys$10[c] == v) {
                     swap$10(c, d, keys$10, elems$10);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$10(b, c, keys$10, elems$10);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$10(off, b - s, s, keys$10, elems$10);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$10(b, n - s, s, keys$10, elems$10);
            s = b - a;
            if (s > 1) {
               this.sort2$10(off, s, keys$10, elems$10);
            }

            s = d - c;
            if (s > 1) {
               int var25 = n - s;
               length = s;
               off = var25;
               continue;
            }

            BoxedUnit var24 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$11(final int a, final int b, final long[] keys$11, final int[] elems$11) {
      long t0 = keys$11[a];
      keys$11[a] = keys$11[b];
      keys$11[b] = t0;
      int t1 = elems$11[a];
      elems$11[a] = elems$11[b];
      elems$11[b] = t1;
   }

   private static final void vecswap$11(final int _a, final int _b, final int n, final long[] keys$11, final int[] elems$11) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$11(a, b, keys$11, elems$11);
         ++i;
         ++a;
      }

   }

   private static final int med3$11(final int a, final int b, final int c, final long[] keys$11) {
      return keys$11[a] < keys$11[b] ? (keys$11[b] < keys$11[c] ? b : (keys$11[a] < keys$11[c] ? c : a)) : (keys$11[b] > keys$11[c] ? b : (keys$11[a] > keys$11[c] ? c : a));
   }

   private final void sort2$11(final int off, final int length, final long[] keys$11, final int[] elems$11) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$11[j - 1] > keys$11[j]; --j) {
                  swap$11(j, j - 1, keys$11, elems$11);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$11(off, off + s, off + 2 * s, keys$11);
                  m = med3$11(m - s, m, m + s, keys$11);
                  n = med3$11(n - 2 * s, n - s, n, keys$11);
               }

               m = med3$11(l, m, n, keys$11);
            }

            long v = keys$11[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$11[b] <= v; ++b) {
                  if (keys$11[b] == v) {
                     swap$11(a, b, keys$11, elems$11);
                     ++a;
                  }
               }

               for(; c >= b && keys$11[c] >= v; --c) {
                  if (keys$11[c] == v) {
                     swap$11(c, d, keys$11, elems$11);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$11(b, c, keys$11, elems$11);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$11(off, b - s, s, keys$11, elems$11);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$11(b, n - s, s, keys$11, elems$11);
            s = b - a;
            if (s > 1) {
               this.sort2$11(off, s, keys$11, elems$11);
            }

            s = d - c;
            if (s > 1) {
               int var25 = n - s;
               length = s;
               off = var25;
               continue;
            }

            BoxedUnit var24 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$12(final int a, final int b, final long[] keys$12, final long[] elems$12) {
      long t0 = keys$12[a];
      keys$12[a] = keys$12[b];
      keys$12[b] = t0;
      long t1 = elems$12[a];
      elems$12[a] = elems$12[b];
      elems$12[b] = t1;
   }

   private static final void vecswap$12(final int _a, final int _b, final int n, final long[] keys$12, final long[] elems$12) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$12(a, b, keys$12, elems$12);
         ++i;
         ++a;
      }

   }

   private static final int med3$12(final int a, final int b, final int c, final long[] keys$12) {
      return keys$12[a] < keys$12[b] ? (keys$12[b] < keys$12[c] ? b : (keys$12[a] < keys$12[c] ? c : a)) : (keys$12[b] > keys$12[c] ? b : (keys$12[a] > keys$12[c] ? c : a));
   }

   private final void sort2$12(final int off, final int length, final long[] keys$12, final long[] elems$12) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$12[j - 1] > keys$12[j]; --j) {
                  swap$12(j, j - 1, keys$12, elems$12);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$12(off, off + s, off + 2 * s, keys$12);
                  m = med3$12(m - s, m, m + s, keys$12);
                  n = med3$12(n - 2 * s, n - s, n, keys$12);
               }

               m = med3$12(l, m, n, keys$12);
            }

            long v = keys$12[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$12[b] <= v; ++b) {
                  if (keys$12[b] == v) {
                     swap$12(a, b, keys$12, elems$12);
                     ++a;
                  }
               }

               for(; c >= b && keys$12[c] >= v; --c) {
                  if (keys$12[c] == v) {
                     swap$12(c, d, keys$12, elems$12);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$12(b, c, keys$12, elems$12);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$12(off, b - s, s, keys$12, elems$12);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$12(b, n - s, s, keys$12, elems$12);
            s = b - a;
            if (s > 1) {
               this.sort2$12(off, s, keys$12, elems$12);
            }

            s = d - c;
            if (s > 1) {
               int var25 = n - s;
               length = s;
               off = var25;
               continue;
            }

            BoxedUnit var24 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$13(final int a, final int b, final float[] keys$13, final double[] elems$13) {
      float t0 = keys$13[a];
      keys$13[a] = keys$13[b];
      keys$13[b] = t0;
      double t1 = elems$13[a];
      elems$13[a] = elems$13[b];
      elems$13[b] = t1;
   }

   private static final void vecswap$13(final int _a, final int _b, final int n, final float[] keys$13, final double[] elems$13) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$13(a, b, keys$13, elems$13);
         ++i;
         ++a;
      }

   }

   private static final int med3$13(final int a, final int b, final int c, final float[] keys$13) {
      return keys$13[a] < keys$13[b] ? (keys$13[b] < keys$13[c] ? b : (keys$13[a] < keys$13[c] ? c : a)) : (keys$13[b] > keys$13[c] ? b : (keys$13[a] > keys$13[c] ? c : a));
   }

   private final void sort2$13(final int off, final int length, final float[] keys$13, final double[] elems$13) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$13[j - 1] > keys$13[j]; --j) {
                  swap$13(j, j - 1, keys$13, elems$13);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$13(off, off + s, off + 2 * s, keys$13);
                  m = med3$13(m - s, m, m + s, keys$13);
                  n = med3$13(n - 2 * s, n - s, n, keys$13);
               }

               m = med3$13(l, m, n, keys$13);
            }

            float v = keys$13[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$13[b] <= v; ++b) {
                  if (keys$13[b] == v) {
                     swap$13(a, b, keys$13, elems$13);
                     ++a;
                  }
               }

               for(; c >= b && keys$13[c] >= v; --c) {
                  if (keys$13[c] == v) {
                     swap$13(c, d, keys$13, elems$13);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$13(b, c, keys$13, elems$13);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$13(off, b - s, s, keys$13, elems$13);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$13(b, n - s, s, keys$13, elems$13);
            s = b - a;
            if (s > 1) {
               this.sort2$13(off, s, keys$13, elems$13);
            }

            s = d - c;
            if (s > 1) {
               int var24 = n - s;
               length = s;
               off = var24;
               continue;
            }

            BoxedUnit var23 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$14(final int a, final int b, final float[] keys$14, final float[] elems$14) {
      float t0 = keys$14[a];
      keys$14[a] = keys$14[b];
      keys$14[b] = t0;
      float t1 = elems$14[a];
      elems$14[a] = elems$14[b];
      elems$14[b] = t1;
   }

   private static final void vecswap$14(final int _a, final int _b, final int n, final float[] keys$14, final float[] elems$14) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$14(a, b, keys$14, elems$14);
         ++i;
         ++a;
      }

   }

   private static final int med3$14(final int a, final int b, final int c, final float[] keys$14) {
      return keys$14[a] < keys$14[b] ? (keys$14[b] < keys$14[c] ? b : (keys$14[a] < keys$14[c] ? c : a)) : (keys$14[b] > keys$14[c] ? b : (keys$14[a] > keys$14[c] ? c : a));
   }

   private final void sort2$14(final int off, final int length, final float[] keys$14, final float[] elems$14) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$14[j - 1] > keys$14[j]; --j) {
                  swap$14(j, j - 1, keys$14, elems$14);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$14(off, off + s, off + 2 * s, keys$14);
                  m = med3$14(m - s, m, m + s, keys$14);
                  n = med3$14(n - 2 * s, n - s, n, keys$14);
               }

               m = med3$14(l, m, n, keys$14);
            }

            float v = keys$14[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$14[b] <= v; ++b) {
                  if (keys$14[b] == v) {
                     swap$14(a, b, keys$14, elems$14);
                     ++a;
                  }
               }

               for(; c >= b && keys$14[c] >= v; --c) {
                  if (keys$14[c] == v) {
                     swap$14(c, d, keys$14, elems$14);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$14(b, c, keys$14, elems$14);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$14(off, b - s, s, keys$14, elems$14);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$14(b, n - s, s, keys$14, elems$14);
            s = b - a;
            if (s > 1) {
               this.sort2$14(off, s, keys$14, elems$14);
            }

            s = d - c;
            if (s > 1) {
               int var24 = n - s;
               length = s;
               off = var24;
               continue;
            }

            BoxedUnit var23 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$15(final int a, final int b, final float[] keys$15, final int[] elems$15) {
      float t0 = keys$15[a];
      keys$15[a] = keys$15[b];
      keys$15[b] = t0;
      int t1 = elems$15[a];
      elems$15[a] = elems$15[b];
      elems$15[b] = t1;
   }

   private static final void vecswap$15(final int _a, final int _b, final int n, final float[] keys$15, final int[] elems$15) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$15(a, b, keys$15, elems$15);
         ++i;
         ++a;
      }

   }

   private static final int med3$15(final int a, final int b, final int c, final float[] keys$15) {
      return keys$15[a] < keys$15[b] ? (keys$15[b] < keys$15[c] ? b : (keys$15[a] < keys$15[c] ? c : a)) : (keys$15[b] > keys$15[c] ? b : (keys$15[a] > keys$15[c] ? c : a));
   }

   private final void sort2$15(final int off, final int length, final float[] keys$15, final int[] elems$15) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$15[j - 1] > keys$15[j]; --j) {
                  swap$15(j, j - 1, keys$15, elems$15);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$15(off, off + s, off + 2 * s, keys$15);
                  m = med3$15(m - s, m, m + s, keys$15);
                  n = med3$15(n - 2 * s, n - s, n, keys$15);
               }

               m = med3$15(l, m, n, keys$15);
            }

            float v = keys$15[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$15[b] <= v; ++b) {
                  if (keys$15[b] == v) {
                     swap$15(a, b, keys$15, elems$15);
                     ++a;
                  }
               }

               for(; c >= b && keys$15[c] >= v; --c) {
                  if (keys$15[c] == v) {
                     swap$15(c, d, keys$15, elems$15);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$15(b, c, keys$15, elems$15);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$15(off, b - s, s, keys$15, elems$15);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$15(b, n - s, s, keys$15, elems$15);
            s = b - a;
            if (s > 1) {
               this.sort2$15(off, s, keys$15, elems$15);
            }

            s = d - c;
            if (s > 1) {
               int var24 = n - s;
               length = s;
               off = var24;
               continue;
            }

            BoxedUnit var23 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$16(final int a, final int b, final float[] keys$16, final long[] elems$16) {
      float t0 = keys$16[a];
      keys$16[a] = keys$16[b];
      keys$16[b] = t0;
      long t1 = elems$16[a];
      elems$16[a] = elems$16[b];
      elems$16[b] = t1;
   }

   private static final void vecswap$16(final int _a, final int _b, final int n, final float[] keys$16, final long[] elems$16) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$16(a, b, keys$16, elems$16);
         ++i;
         ++a;
      }

   }

   private static final int med3$16(final int a, final int b, final int c, final float[] keys$16) {
      return keys$16[a] < keys$16[b] ? (keys$16[b] < keys$16[c] ? b : (keys$16[a] < keys$16[c] ? c : a)) : (keys$16[b] > keys$16[c] ? b : (keys$16[a] > keys$16[c] ? c : a));
   }

   private final void sort2$16(final int off, final int length, final float[] keys$16, final long[] elems$16) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$16[j - 1] > keys$16[j]; --j) {
                  swap$16(j, j - 1, keys$16, elems$16);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$16(off, off + s, off + 2 * s, keys$16);
                  m = med3$16(m - s, m, m + s, keys$16);
                  n = med3$16(n - 2 * s, n - s, n, keys$16);
               }

               m = med3$16(l, m, n, keys$16);
            }

            float v = keys$16[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$16[b] <= v; ++b) {
                  if (keys$16[b] == v) {
                     swap$16(a, b, keys$16, elems$16);
                     ++a;
                  }
               }

               for(; c >= b && keys$16[c] >= v; --c) {
                  if (keys$16[c] == v) {
                     swap$16(c, d, keys$16, elems$16);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$16(b, c, keys$16, elems$16);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$16(off, b - s, s, keys$16, elems$16);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$16(b, n - s, s, keys$16, elems$16);
            s = b - a;
            if (s > 1) {
               this.sort2$16(off, s, keys$16, elems$16);
            }

            s = d - c;
            if (s > 1) {
               int var24 = n - s;
               length = s;
               off = var24;
               continue;
            }

            BoxedUnit var23 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$17(final int a, final int b, final double[] keys$17, final double[] elems$17) {
      double t0 = keys$17[a];
      keys$17[a] = keys$17[b];
      keys$17[b] = t0;
      double t1 = elems$17[a];
      elems$17[a] = elems$17[b];
      elems$17[b] = t1;
   }

   private static final void vecswap$17(final int _a, final int _b, final int n, final double[] keys$17, final double[] elems$17) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$17(a, b, keys$17, elems$17);
         ++i;
         ++a;
      }

   }

   private static final int med3$17(final int a, final int b, final int c, final double[] keys$17) {
      return keys$17[a] < keys$17[b] ? (keys$17[b] < keys$17[c] ? b : (keys$17[a] < keys$17[c] ? c : a)) : (keys$17[b] > keys$17[c] ? b : (keys$17[a] > keys$17[c] ? c : a));
   }

   private final void sort2$17(final int off, final int length, final double[] keys$17, final double[] elems$17) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$17[j - 1] > keys$17[j]; --j) {
                  swap$17(j, j - 1, keys$17, elems$17);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$17(off, off + s, off + 2 * s, keys$17);
                  m = med3$17(m - s, m, m + s, keys$17);
                  n = med3$17(n - 2 * s, n - s, n, keys$17);
               }

               m = med3$17(l, m, n, keys$17);
            }

            double v = keys$17[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$17[b] <= v; ++b) {
                  if (keys$17[b] == v) {
                     swap$17(a, b, keys$17, elems$17);
                     ++a;
                  }
               }

               for(; c >= b && keys$17[c] >= v; --c) {
                  if (keys$17[c] == v) {
                     swap$17(c, d, keys$17, elems$17);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$17(b, c, keys$17, elems$17);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$17(off, b - s, s, keys$17, elems$17);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$17(b, n - s, s, keys$17, elems$17);
            s = b - a;
            if (s > 1) {
               this.sort2$17(off, s, keys$17, elems$17);
            }

            s = d - c;
            if (s > 1) {
               int var25 = n - s;
               length = s;
               off = var25;
               continue;
            }

            BoxedUnit var24 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$18(final int a, final int b, final double[] keys$18, final float[] elems$18) {
      double t0 = keys$18[a];
      keys$18[a] = keys$18[b];
      keys$18[b] = t0;
      float t1 = elems$18[a];
      elems$18[a] = elems$18[b];
      elems$18[b] = t1;
   }

   private static final void vecswap$18(final int _a, final int _b, final int n, final double[] keys$18, final float[] elems$18) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$18(a, b, keys$18, elems$18);
         ++i;
         ++a;
      }

   }

   private static final int med3$18(final int a, final int b, final int c, final double[] keys$18) {
      return keys$18[a] < keys$18[b] ? (keys$18[b] < keys$18[c] ? b : (keys$18[a] < keys$18[c] ? c : a)) : (keys$18[b] > keys$18[c] ? b : (keys$18[a] > keys$18[c] ? c : a));
   }

   private final void sort2$18(final int off, final int length, final double[] keys$18, final float[] elems$18) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$18[j - 1] > keys$18[j]; --j) {
                  swap$18(j, j - 1, keys$18, elems$18);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$18(off, off + s, off + 2 * s, keys$18);
                  m = med3$18(m - s, m, m + s, keys$18);
                  n = med3$18(n - 2 * s, n - s, n, keys$18);
               }

               m = med3$18(l, m, n, keys$18);
            }

            double v = keys$18[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$18[b] <= v; ++b) {
                  if (keys$18[b] == v) {
                     swap$18(a, b, keys$18, elems$18);
                     ++a;
                  }
               }

               for(; c >= b && keys$18[c] >= v; --c) {
                  if (keys$18[c] == v) {
                     swap$18(c, d, keys$18, elems$18);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$18(b, c, keys$18, elems$18);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$18(off, b - s, s, keys$18, elems$18);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$18(b, n - s, s, keys$18, elems$18);
            s = b - a;
            if (s > 1) {
               this.sort2$18(off, s, keys$18, elems$18);
            }

            s = d - c;
            if (s > 1) {
               int var25 = n - s;
               length = s;
               off = var25;
               continue;
            }

            BoxedUnit var24 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$19(final int a, final int b, final double[] keys$19, final int[] elems$19) {
      double t0 = keys$19[a];
      keys$19[a] = keys$19[b];
      keys$19[b] = t0;
      int t1 = elems$19[a];
      elems$19[a] = elems$19[b];
      elems$19[b] = t1;
   }

   private static final void vecswap$19(final int _a, final int _b, final int n, final double[] keys$19, final int[] elems$19) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$19(a, b, keys$19, elems$19);
         ++i;
         ++a;
      }

   }

   private static final int med3$19(final int a, final int b, final int c, final double[] keys$19) {
      return keys$19[a] < keys$19[b] ? (keys$19[b] < keys$19[c] ? b : (keys$19[a] < keys$19[c] ? c : a)) : (keys$19[b] > keys$19[c] ? b : (keys$19[a] > keys$19[c] ? c : a));
   }

   private final void sort2$19(final int off, final int length, final double[] keys$19, final int[] elems$19) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$19[j - 1] > keys$19[j]; --j) {
                  swap$19(j, j - 1, keys$19, elems$19);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$19(off, off + s, off + 2 * s, keys$19);
                  m = med3$19(m - s, m, m + s, keys$19);
                  n = med3$19(n - 2 * s, n - s, n, keys$19);
               }

               m = med3$19(l, m, n, keys$19);
            }

            double v = keys$19[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$19[b] <= v; ++b) {
                  if (keys$19[b] == v) {
                     swap$19(a, b, keys$19, elems$19);
                     ++a;
                  }
               }

               for(; c >= b && keys$19[c] >= v; --c) {
                  if (keys$19[c] == v) {
                     swap$19(c, d, keys$19, elems$19);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$19(b, c, keys$19, elems$19);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$19(off, b - s, s, keys$19, elems$19);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$19(b, n - s, s, keys$19, elems$19);
            s = b - a;
            if (s > 1) {
               this.sort2$19(off, s, keys$19, elems$19);
            }

            s = d - c;
            if (s > 1) {
               int var25 = n - s;
               length = s;
               off = var25;
               continue;
            }

            BoxedUnit var24 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private static final void swap$20(final int a, final int b, final double[] keys$20, final long[] elems$20) {
      double t0 = keys$20[a];
      keys$20[a] = keys$20[b];
      keys$20[b] = t0;
      long t1 = elems$20[a];
      elems$20[a] = elems$20[b];
      elems$20[b] = t1;
   }

   private static final void vecswap$20(final int _a, final int _b, final int n, final double[] keys$20, final long[] elems$20) {
      int a = _a;
      int b = _b;

      for(int i = 0; i < n; ++b) {
         swap$20(a, b, keys$20, elems$20);
         ++i;
         ++a;
      }

   }

   private static final int med3$20(final int a, final int b, final int c, final double[] keys$20) {
      return keys$20[a] < keys$20[b] ? (keys$20[b] < keys$20[c] ? b : (keys$20[a] < keys$20[c] ? c : a)) : (keys$20[b] > keys$20[c] ? b : (keys$20[a] > keys$20[c] ? c : a));
   }

   private final void sort2$20(final int off, final int length, final double[] keys$20, final long[] elems$20) {
      while(true) {
         if (length < 7) {
            for(int i = off; i < length + off; ++i) {
               for(int j = i; j > off && keys$20[j - 1] > keys$20[j]; --j) {
                  swap$20(j, j - 1, keys$20, elems$20);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            int m = off + (length >> 1);
            if (length > 7) {
               int l = off;
               int n = off + length - 1;
               if (length > 40) {
                  int s = length / 8;
                  l = med3$20(off, off + s, off + 2 * s, keys$20);
                  m = med3$20(m - s, m, m + s, keys$20);
                  n = med3$20(n - 2 * s, n - s, n, keys$20);
               }

               m = med3$20(l, m, n, keys$20);
            }

            double v = keys$20[m];
            int a = off;
            int b = off;
            int c = off + length - 1;
            int d = c;
            boolean done = false;

            while(!done) {
               for(; b <= c && keys$20[b] <= v; ++b) {
                  if (keys$20[b] == v) {
                     swap$20(a, b, keys$20, elems$20);
                     ++a;
                  }
               }

               for(; c >= b && keys$20[c] >= v; --c) {
                  if (keys$20[c] == v) {
                     swap$20(c, d, keys$20, elems$20);
                     --d;
                  }
               }

               if (b > c) {
                  done = true;
               } else {
                  swap$20(b, c, keys$20, elems$20);
                  --c;
                  ++b;
               }
            }

            int n = off + length;
            int s = scala.math.package..MODULE$.min(a - off, b - a);
            vecswap$20(off, b - s, s, keys$20, elems$20);
            s = scala.math.package..MODULE$.min(d - c, n - d - 1);
            vecswap$20(b, n - s, s, keys$20, elems$20);
            s = b - a;
            if (s > 1) {
               this.sort2$20(off, s, keys$20, elems$20);
            }

            s = d - c;
            if (s > 1) {
               int var25 = n - s;
               length = s;
               off = var25;
               continue;
            }

            BoxedUnit var24 = BoxedUnit.UNIT;
         }

         return;
      }
   }

   private Sorting$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
