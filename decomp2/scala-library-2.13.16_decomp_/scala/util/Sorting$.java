package scala.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Some;
import scala.collection.Seq;
import scala.math.Ordering;
import scala.math.Ordering$;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null$;
import scala.runtime.ScalaRunTime$;

public final class Sorting$ {
   public static final Sorting$ MODULE$ = new Sorting$();

   public void quickSort(final double[] a) {
      Arrays.sort(a);
   }

   public void quickSort(final int[] a) {
      Arrays.sort(a);
   }

   public void quickSort(final float[] a) {
      Arrays.sort(a);
   }

   private final int qsortThreshold() {
      return 16;
   }

   public void quickSort(final Object a, final Ordering evidence$1) {
      this.inner$1(a, 0, Array.getLength(a), evidence$1);
   }

   private final int mergeThreshold() {
      return 32;
   }

   public void scala$util$Sorting$$insertionSort(final Object a, final int i0, final int iN, final Ordering ord) {
      int n = iN - i0;
      if (n >= 2) {
         if (ord.compare(ScalaRunTime$.MODULE$.array_apply(a, i0), ScalaRunTime$.MODULE$.array_apply(a, i0 + 1)) > 0) {
            Object temp = ScalaRunTime$.MODULE$.array_apply(a, i0);
            ScalaRunTime$.MODULE$.array_update(a, i0, ScalaRunTime$.MODULE$.array_apply(a, i0 + 1));
            ScalaRunTime$.MODULE$.array_update(a, i0 + 1, temp);
         }

         for(int m = 2; m < n; ++m) {
            Object next = ScalaRunTime$.MODULE$.array_apply(a, i0 + m);
            if (ord.compare(next, ScalaRunTime$.MODULE$.array_apply(a, i0 + m - 1)) < 0) {
               int iA = i0;
               int iB = i0 + m - 1;

               while(iB - iA > 1) {
                  int ix = iA + iB >>> 1;
                  if (ord.compare(next, ScalaRunTime$.MODULE$.array_apply(a, ix)) < 0) {
                     iB = ix;
                  } else {
                     iA = ix;
                  }
               }

               int ix = iA + (ord.compare(next, ScalaRunTime$.MODULE$.array_apply(a, iA)) < 0 ? 0 : 1);

               for(int i = i0 + m; i > ix; --i) {
                  ScalaRunTime$.MODULE$.array_update(a, i, ScalaRunTime$.MODULE$.array_apply(a, i - 1));
               }

               ScalaRunTime$.MODULE$.array_update(a, ix, next);
            }
         }

      }
   }

   public void scala$util$Sorting$$mergeSort(final Object a, final int i0, final int iN, final Ordering ord, final Object scratch, final ClassTag evidence$2) {
      if (iN - i0 < 32) {
         this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
      } else {
         int iK = i0 + iN >>> 1;
         Object sc = scratch == null ? evidence$2.newArray(iK - i0) : scratch;
         this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
      }
   }

   public Null$ scala$util$Sorting$$mergeSort$default$5() {
      return null;
   }

   public void scala$util$Sorting$$mergeSorted(final Object a, final int i0, final int iK, final int iN, final Ordering ord, final Object scratch) {
      if (ord.compare(ScalaRunTime$.MODULE$.array_apply(a, iK - 1), ScalaRunTime$.MODULE$.array_apply(a, iK)) > 0) {
         int i = i0;
         int jN = iK - i0;

         for(int j = 0; i < iK; ++j) {
            ScalaRunTime$.MODULE$.array_update(scratch, j, ScalaRunTime$.MODULE$.array_apply(a, i));
            ++i;
         }

         int k = i0;

         int var11;
         for(var11 = 0; i < iN && var11 < jN; ++k) {
            if (ord.compare(ScalaRunTime$.MODULE$.array_apply(a, i), ScalaRunTime$.MODULE$.array_apply(scratch, var11)) < 0) {
               ScalaRunTime$.MODULE$.array_update(a, k, ScalaRunTime$.MODULE$.array_apply(a, i));
               ++i;
            } else {
               ScalaRunTime$.MODULE$.array_update(a, k, ScalaRunTime$.MODULE$.array_apply(scratch, var11));
               ++var11;
            }
         }

         while(var11 < jN) {
            ScalaRunTime$.MODULE$.array_update(a, k, ScalaRunTime$.MODULE$.array_apply(scratch, var11));
            ++var11;
            ++k;
         }

      }
   }

   public void scala$util$Sorting$$booleanSort(final boolean[] a, final int from, final int until) {
      int i = from;

      int n;
      for(n = 0; i < until; ++i) {
         if (!a[i]) {
            ++n;
         }
      }

      for(i = 0; i < n; ++i) {
         a[from + i] = false;
      }

      while(from + i < until) {
         a[from + i] = true;
         ++i;
      }

   }

   private void sort(final Object a, final int from, final int until, final Ordering ord) {
      if (a instanceof Object[]) {
         if (Array.getLength(a) > 1 && ord == null) {
            throw new NullPointerException("Ordering");
         } else {
            Arrays.sort(a, from, until, ord);
         }
      } else if (a instanceof int[]) {
         int[] var5 = (int[])a;
         if (ord == Ordering.Int$.MODULE$) {
            Arrays.sort(var5, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mIc$sp(var5, from, until, ord, (int[])null, ClassTag$.MODULE$.Int());
         }
      } else if (a instanceof double[]) {
         double[] var6 = (double[])a;
         this.scala$util$Sorting$$mergeSort$mDc$sp(var6, from, until, ord, (double[])null, ClassTag$.MODULE$.Double());
      } else if (a instanceof long[]) {
         long[] var7 = (long[])a;
         if (ord == Ordering.Long$.MODULE$) {
            Arrays.sort(var7, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mJc$sp(var7, from, until, ord, (long[])null, ClassTag$.MODULE$.Long());
         }
      } else if (a instanceof float[]) {
         float[] var8 = (float[])a;
         this.scala$util$Sorting$$mergeSort$mFc$sp(var8, from, until, ord, (float[])null, ClassTag$.MODULE$.Float());
      } else if (a instanceof char[]) {
         char[] var9 = (char[])a;
         if (ord == Ordering.Char$.MODULE$) {
            Arrays.sort(var9, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mCc$sp(var9, from, until, ord, (char[])null, ClassTag$.MODULE$.Char());
         }
      } else if (a instanceof byte[]) {
         byte[] var10 = (byte[])a;
         if (ord == Ordering.Byte$.MODULE$) {
            Arrays.sort(var10, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mBc$sp(var10, from, until, ord, (byte[])null, ClassTag$.MODULE$.Byte());
         }
      } else if (a instanceof short[]) {
         short[] var11 = (short[])a;
         if (ord == Ordering.Short$.MODULE$) {
            Arrays.sort(var11, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mSc$sp(var11, from, until, ord, (short[])null, ClassTag$.MODULE$.Short());
         }
      } else if (a instanceof boolean[]) {
         boolean[] var12 = (boolean[])a;
         if (ord == Ordering.Boolean$.MODULE$) {
            this.scala$util$Sorting$$booleanSort(var12, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mZc$sp(var12, from, until, ord, (boolean[])null, ClassTag$.MODULE$.Boolean());
         }
      } else if (a == null) {
         throw new NullPointerException();
      } else {
         throw new MatchError(a);
      }
   }

   public void stableSort(final Object a, final Ordering evidence$3) {
      this.stableSort(a, 0, Array.getLength(a), evidence$3);
   }

   public void stableSort(final Object a, final int from, final int until, final Ordering evidence$4) {
      Ordering$ var10000 = Ordering$.MODULE$;
      if (a instanceof Object[]) {
         if (Array.getLength(a) > 1 && evidence$4 == null) {
            throw new NullPointerException("Ordering");
         } else {
            Arrays.sort(a, from, until, evidence$4);
         }
      } else if (a instanceof int[]) {
         int[] var5 = (int[])a;
         if (evidence$4 == Ordering.Int$.MODULE$) {
            Arrays.sort(var5, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mIc$sp(var5, from, until, evidence$4, (int[])null, ClassTag$.MODULE$.Int());
         }
      } else if (a instanceof double[]) {
         double[] var6 = (double[])a;
         this.scala$util$Sorting$$mergeSort$mDc$sp(var6, from, until, evidence$4, (double[])null, ClassTag$.MODULE$.Double());
      } else if (a instanceof long[]) {
         long[] var7 = (long[])a;
         if (evidence$4 == Ordering.Long$.MODULE$) {
            Arrays.sort(var7, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mJc$sp(var7, from, until, evidence$4, (long[])null, ClassTag$.MODULE$.Long());
         }
      } else if (a instanceof float[]) {
         float[] var8 = (float[])a;
         this.scala$util$Sorting$$mergeSort$mFc$sp(var8, from, until, evidence$4, (float[])null, ClassTag$.MODULE$.Float());
      } else if (a instanceof char[]) {
         char[] var9 = (char[])a;
         if (evidence$4 == Ordering.Char$.MODULE$) {
            Arrays.sort(var9, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mCc$sp(var9, from, until, evidence$4, (char[])null, ClassTag$.MODULE$.Char());
         }
      } else if (a instanceof byte[]) {
         byte[] var10 = (byte[])a;
         if (evidence$4 == Ordering.Byte$.MODULE$) {
            Arrays.sort(var10, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mBc$sp(var10, from, until, evidence$4, (byte[])null, ClassTag$.MODULE$.Byte());
         }
      } else if (a instanceof short[]) {
         short[] var11 = (short[])a;
         if (evidence$4 == Ordering.Short$.MODULE$) {
            Arrays.sort(var11, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mSc$sp(var11, from, until, evidence$4, (short[])null, ClassTag$.MODULE$.Short());
         }
      } else if (a instanceof boolean[]) {
         boolean[] var12 = (boolean[])a;
         if (evidence$4 == Ordering.Boolean$.MODULE$) {
            this.scala$util$Sorting$$booleanSort(var12, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mZc$sp(var12, from, until, evidence$4, (boolean[])null, ClassTag$.MODULE$.Boolean());
         }
      } else if (a == null) {
         throw new NullPointerException();
      } else {
         throw new MatchError(a);
      }
   }

   public void stableSort(final Object a, final Function2 f) {
      int stableSort_until = Array.getLength(a);
      int stableSort_from = 0;
      Ordering$ var10000 = Ordering$.MODULE$;
      Ordering stableSort_sort_ord = new Ordering(f) {
         private final Function2 cmp$2;

         public Some tryCompare(final Object x, final Object y) {
            return Ordering.tryCompare$(this, x, y);
         }

         public boolean equiv(final Object x, final Object y) {
            return Ordering.equiv$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Ordering.max$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Ordering.min$(this, x, y);
         }

         public Ordering reverse() {
            return Ordering.reverse$(this);
         }

         public boolean isReverseOf(final Ordering other) {
            return Ordering.isReverseOf$(this, other);
         }

         public Ordering on(final Function1 f) {
            return Ordering.on$(this, f);
         }

         public Ordering orElse(final Ordering other) {
            return Ordering.orElse$(this, other);
         }

         public Ordering orElseBy(final Function1 f, final Ordering ord) {
            return Ordering.orElseBy$(this, f, ord);
         }

         public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
            return Ordering.mkOrderingOps$(this, lhs);
         }

         public int compare(final Object x, final Object y) {
            if (BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y))) {
               return -1;
            } else {
               return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x)) ? 1 : 0;
            }
         }

         public boolean lt(final Object x, final Object y) {
            return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
         }

         public boolean gt(final Object x, final Object y) {
            return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x));
         }

         public boolean gteq(final Object x, final Object y) {
            return !BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
         }

         public boolean lteq(final Object x, final Object y) {
            return !BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x));
         }

         public {
            this.cmp$2 = cmp$2;
         }
      };
      if (a instanceof Object[]) {
         Array.getLength(a);
         Arrays.sort(a, stableSort_from, stableSort_until, stableSort_sort_ord);
      } else if (a instanceof int[]) {
         int[] var6 = (int[])a;
         if (stableSort_sort_ord == Ordering.Int$.MODULE$) {
            Arrays.sort(var6, stableSort_from, stableSort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mIc$sp(var6, stableSort_from, stableSort_until, stableSort_sort_ord, (int[])null, ClassTag$.MODULE$.Int());
         }
      } else if (a instanceof double[]) {
         double[] var7 = (double[])a;
         this.scala$util$Sorting$$mergeSort$mDc$sp(var7, stableSort_from, stableSort_until, stableSort_sort_ord, (double[])null, ClassTag$.MODULE$.Double());
      } else if (a instanceof long[]) {
         long[] var8 = (long[])a;
         if (stableSort_sort_ord == Ordering.Long$.MODULE$) {
            Arrays.sort(var8, stableSort_from, stableSort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mJc$sp(var8, stableSort_from, stableSort_until, stableSort_sort_ord, (long[])null, ClassTag$.MODULE$.Long());
         }
      } else if (a instanceof float[]) {
         float[] var9 = (float[])a;
         this.scala$util$Sorting$$mergeSort$mFc$sp(var9, stableSort_from, stableSort_until, stableSort_sort_ord, (float[])null, ClassTag$.MODULE$.Float());
      } else if (a instanceof char[]) {
         char[] var10 = (char[])a;
         if (stableSort_sort_ord == Ordering.Char$.MODULE$) {
            Arrays.sort(var10, stableSort_from, stableSort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mCc$sp(var10, stableSort_from, stableSort_until, stableSort_sort_ord, (char[])null, ClassTag$.MODULE$.Char());
         }
      } else if (a instanceof byte[]) {
         byte[] var11 = (byte[])a;
         if (stableSort_sort_ord == Ordering.Byte$.MODULE$) {
            Arrays.sort(var11, stableSort_from, stableSort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mBc$sp(var11, stableSort_from, stableSort_until, stableSort_sort_ord, (byte[])null, ClassTag$.MODULE$.Byte());
         }
      } else if (a instanceof short[]) {
         short[] var12 = (short[])a;
         if (stableSort_sort_ord == Ordering.Short$.MODULE$) {
            Arrays.sort(var12, stableSort_from, stableSort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mSc$sp(var12, stableSort_from, stableSort_until, stableSort_sort_ord, (short[])null, ClassTag$.MODULE$.Short());
         }
      } else if (a instanceof boolean[]) {
         boolean[] var13 = (boolean[])a;
         if (stableSort_sort_ord == Ordering.Boolean$.MODULE$) {
            this.scala$util$Sorting$$booleanSort(var13, stableSort_from, stableSort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mZc$sp(var13, stableSort_from, stableSort_until, stableSort_sort_ord, (boolean[])null, ClassTag$.MODULE$.Boolean());
         }
      } else {
         throw new MatchError(a);
      }
   }

   public void stableSort(final Object a, final Function2 f, final int from, final int until) {
      Ordering$ var10000 = Ordering$.MODULE$;
      Ordering sort_ord = new Ordering(f) {
         private final Function2 cmp$2;

         public Some tryCompare(final Object x, final Object y) {
            return Ordering.tryCompare$(this, x, y);
         }

         public boolean equiv(final Object x, final Object y) {
            return Ordering.equiv$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Ordering.max$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Ordering.min$(this, x, y);
         }

         public Ordering reverse() {
            return Ordering.reverse$(this);
         }

         public boolean isReverseOf(final Ordering other) {
            return Ordering.isReverseOf$(this, other);
         }

         public Ordering on(final Function1 f) {
            return Ordering.on$(this, f);
         }

         public Ordering orElse(final Ordering other) {
            return Ordering.orElse$(this, other);
         }

         public Ordering orElseBy(final Function1 f, final Ordering ord) {
            return Ordering.orElseBy$(this, f, ord);
         }

         public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
            return Ordering.mkOrderingOps$(this, lhs);
         }

         public int compare(final Object x, final Object y) {
            if (BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y))) {
               return -1;
            } else {
               return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x)) ? 1 : 0;
            }
         }

         public boolean lt(final Object x, final Object y) {
            return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
         }

         public boolean gt(final Object x, final Object y) {
            return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x));
         }

         public boolean gteq(final Object x, final Object y) {
            return !BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
         }

         public boolean lteq(final Object x, final Object y) {
            return !BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x));
         }

         public {
            this.cmp$2 = cmp$2;
         }
      };
      if (a instanceof Object[]) {
         Array.getLength(a);
         Arrays.sort(a, from, until, sort_ord);
      } else if (a instanceof int[]) {
         int[] var6 = (int[])a;
         if (sort_ord == Ordering.Int$.MODULE$) {
            Arrays.sort(var6, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mIc$sp(var6, from, until, sort_ord, (int[])null, ClassTag$.MODULE$.Int());
         }
      } else if (a instanceof double[]) {
         double[] var7 = (double[])a;
         this.scala$util$Sorting$$mergeSort$mDc$sp(var7, from, until, sort_ord, (double[])null, ClassTag$.MODULE$.Double());
      } else if (a instanceof long[]) {
         long[] var8 = (long[])a;
         if (sort_ord == Ordering.Long$.MODULE$) {
            Arrays.sort(var8, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mJc$sp(var8, from, until, sort_ord, (long[])null, ClassTag$.MODULE$.Long());
         }
      } else if (a instanceof float[]) {
         float[] var9 = (float[])a;
         this.scala$util$Sorting$$mergeSort$mFc$sp(var9, from, until, sort_ord, (float[])null, ClassTag$.MODULE$.Float());
      } else if (a instanceof char[]) {
         char[] var10 = (char[])a;
         if (sort_ord == Ordering.Char$.MODULE$) {
            Arrays.sort(var10, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mCc$sp(var10, from, until, sort_ord, (char[])null, ClassTag$.MODULE$.Char());
         }
      } else if (a instanceof byte[]) {
         byte[] var11 = (byte[])a;
         if (sort_ord == Ordering.Byte$.MODULE$) {
            Arrays.sort(var11, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mBc$sp(var11, from, until, sort_ord, (byte[])null, ClassTag$.MODULE$.Byte());
         }
      } else if (a instanceof short[]) {
         short[] var12 = (short[])a;
         if (sort_ord == Ordering.Short$.MODULE$) {
            Arrays.sort(var12, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mSc$sp(var12, from, until, sort_ord, (short[])null, ClassTag$.MODULE$.Short());
         }
      } else if (a instanceof boolean[]) {
         boolean[] var13 = (boolean[])a;
         if (sort_ord == Ordering.Boolean$.MODULE$) {
            this.scala$util$Sorting$$booleanSort(var13, from, until);
         } else {
            this.scala$util$Sorting$$mergeSort$mZc$sp(var13, from, until, sort_ord, (boolean[])null, ClassTag$.MODULE$.Boolean());
         }
      } else if (a == null) {
         throw new NullPointerException();
      } else {
         throw new MatchError(a);
      }
   }

   public Object stableSort(final Seq a, final ClassTag evidence$5, final Ordering evidence$6) {
      Object ret = a.toArray(evidence$5);
      int var10001 = Array.getLength(ret);
      Ordering$ var10002 = Ordering$.MODULE$;
      int sort_until = var10001;
      int sort_from = 0;
      if (ret instanceof Object[]) {
         if (Array.getLength(ret) > 1 && evidence$6 == null) {
            throw new NullPointerException("Ordering");
         }

         Arrays.sort(ret, sort_from, sort_until, evidence$6);
      } else if (ret instanceof int[]) {
         int[] var7 = (int[])ret;
         if (evidence$6 == Ordering.Int$.MODULE$) {
            Arrays.sort(var7, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mIc$sp(var7, sort_from, sort_until, evidence$6, (int[])null, ClassTag$.MODULE$.Int());
         }
      } else if (ret instanceof double[]) {
         double[] var8 = (double[])ret;
         this.scala$util$Sorting$$mergeSort$mDc$sp(var8, sort_from, sort_until, evidence$6, (double[])null, ClassTag$.MODULE$.Double());
      } else if (ret instanceof long[]) {
         long[] var9 = (long[])ret;
         if (evidence$6 == Ordering.Long$.MODULE$) {
            Arrays.sort(var9, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mJc$sp(var9, sort_from, sort_until, evidence$6, (long[])null, ClassTag$.MODULE$.Long());
         }
      } else if (ret instanceof float[]) {
         float[] var10 = (float[])ret;
         this.scala$util$Sorting$$mergeSort$mFc$sp(var10, sort_from, sort_until, evidence$6, (float[])null, ClassTag$.MODULE$.Float());
      } else if (ret instanceof char[]) {
         char[] var11 = (char[])ret;
         if (evidence$6 == Ordering.Char$.MODULE$) {
            Arrays.sort(var11, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mCc$sp(var11, sort_from, sort_until, evidence$6, (char[])null, ClassTag$.MODULE$.Char());
         }
      } else if (ret instanceof byte[]) {
         byte[] var12 = (byte[])ret;
         if (evidence$6 == Ordering.Byte$.MODULE$) {
            Arrays.sort(var12, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mBc$sp(var12, sort_from, sort_until, evidence$6, (byte[])null, ClassTag$.MODULE$.Byte());
         }
      } else if (ret instanceof short[]) {
         short[] var13 = (short[])ret;
         if (evidence$6 == Ordering.Short$.MODULE$) {
            Arrays.sort(var13, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mSc$sp(var13, sort_from, sort_until, evidence$6, (short[])null, ClassTag$.MODULE$.Short());
         }
      } else {
         if (!(ret instanceof boolean[])) {
            throw new MatchError(ret);
         }

         boolean[] var14 = (boolean[])ret;
         if (evidence$6 == Ordering.Boolean$.MODULE$) {
            this.scala$util$Sorting$$booleanSort(var14, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mZc$sp(var14, sort_from, sort_until, evidence$6, (boolean[])null, ClassTag$.MODULE$.Boolean());
         }
      }

      return ret;
   }

   public Object stableSort(final Seq a, final Function2 f, final ClassTag evidence$7) {
      Object ret = a.toArray(evidence$7);
      int var10001 = Array.getLength(ret);
      Ordering$ var10002 = Ordering$.MODULE$;
      Ordering sort_ord = new Ordering(f) {
         private final Function2 cmp$2;

         public Some tryCompare(final Object x, final Object y) {
            return Ordering.tryCompare$(this, x, y);
         }

         public boolean equiv(final Object x, final Object y) {
            return Ordering.equiv$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Ordering.max$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Ordering.min$(this, x, y);
         }

         public Ordering reverse() {
            return Ordering.reverse$(this);
         }

         public boolean isReverseOf(final Ordering other) {
            return Ordering.isReverseOf$(this, other);
         }

         public Ordering on(final Function1 f) {
            return Ordering.on$(this, f);
         }

         public Ordering orElse(final Ordering other) {
            return Ordering.orElse$(this, other);
         }

         public Ordering orElseBy(final Function1 f, final Ordering ord) {
            return Ordering.orElseBy$(this, f, ord);
         }

         public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
            return Ordering.mkOrderingOps$(this, lhs);
         }

         public int compare(final Object x, final Object y) {
            if (BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y))) {
               return -1;
            } else {
               return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x)) ? 1 : 0;
            }
         }

         public boolean lt(final Object x, final Object y) {
            return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
         }

         public boolean gt(final Object x, final Object y) {
            return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x));
         }

         public boolean gteq(final Object x, final Object y) {
            return !BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
         }

         public boolean lteq(final Object x, final Object y) {
            return !BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x));
         }

         public {
            this.cmp$2 = cmp$2;
         }
      };
      int sort_until = var10001;
      int sort_from = 0;
      if (ret instanceof Object[]) {
         Array.getLength(ret);
         Arrays.sort(ret, sort_from, sort_until, sort_ord);
      } else if (ret instanceof int[]) {
         int[] var8 = (int[])ret;
         if (sort_ord == Ordering.Int$.MODULE$) {
            Arrays.sort(var8, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mIc$sp(var8, sort_from, sort_until, sort_ord, (int[])null, ClassTag$.MODULE$.Int());
         }
      } else if (ret instanceof double[]) {
         double[] var9 = (double[])ret;
         this.scala$util$Sorting$$mergeSort$mDc$sp(var9, sort_from, sort_until, sort_ord, (double[])null, ClassTag$.MODULE$.Double());
      } else if (ret instanceof long[]) {
         long[] var10 = (long[])ret;
         if (sort_ord == Ordering.Long$.MODULE$) {
            Arrays.sort(var10, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mJc$sp(var10, sort_from, sort_until, sort_ord, (long[])null, ClassTag$.MODULE$.Long());
         }
      } else if (ret instanceof float[]) {
         float[] var11 = (float[])ret;
         this.scala$util$Sorting$$mergeSort$mFc$sp(var11, sort_from, sort_until, sort_ord, (float[])null, ClassTag$.MODULE$.Float());
      } else if (ret instanceof char[]) {
         char[] var12 = (char[])ret;
         if (sort_ord == Ordering.Char$.MODULE$) {
            Arrays.sort(var12, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mCc$sp(var12, sort_from, sort_until, sort_ord, (char[])null, ClassTag$.MODULE$.Char());
         }
      } else if (ret instanceof byte[]) {
         byte[] var13 = (byte[])ret;
         if (sort_ord == Ordering.Byte$.MODULE$) {
            Arrays.sort(var13, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mBc$sp(var13, sort_from, sort_until, sort_ord, (byte[])null, ClassTag$.MODULE$.Byte());
         }
      } else if (ret instanceof short[]) {
         short[] var14 = (short[])ret;
         if (sort_ord == Ordering.Short$.MODULE$) {
            Arrays.sort(var14, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mSc$sp(var14, sort_from, sort_until, sort_ord, (short[])null, ClassTag$.MODULE$.Short());
         }
      } else {
         if (!(ret instanceof boolean[])) {
            throw new MatchError(ret);
         }

         boolean[] var15 = (boolean[])ret;
         if (sort_ord == Ordering.Boolean$.MODULE$) {
            this.scala$util$Sorting$$booleanSort(var15, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mZc$sp(var15, sort_from, sort_until, sort_ord, (boolean[])null, ClassTag$.MODULE$.Boolean());
         }
      }

      return ret;
   }

   public Object stableSort(final Seq a, final Function1 f, final ClassTag evidence$8, final Ordering evidence$9) {
      Object ret = a.toArray(evidence$8);
      int var10001 = Array.getLength(ret);
      Ordering$ var10002 = Ordering$.MODULE$;
      Ordering sort_ord = evidence$9.on(f);
      int sort_until = var10001;
      int sort_from = 0;
      if (ret instanceof Object[]) {
         if (Array.getLength(ret) > 1 && sort_ord == null) {
            throw new NullPointerException("Ordering");
         }

         Arrays.sort(ret, sort_from, sort_until, sort_ord);
      } else if (ret instanceof int[]) {
         int[] var9 = (int[])ret;
         if (sort_ord == Ordering.Int$.MODULE$) {
            Arrays.sort(var9, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mIc$sp(var9, sort_from, sort_until, sort_ord, (int[])null, ClassTag$.MODULE$.Int());
         }
      } else if (ret instanceof double[]) {
         double[] var10 = (double[])ret;
         this.scala$util$Sorting$$mergeSort$mDc$sp(var10, sort_from, sort_until, sort_ord, (double[])null, ClassTag$.MODULE$.Double());
      } else if (ret instanceof long[]) {
         long[] var11 = (long[])ret;
         if (sort_ord == Ordering.Long$.MODULE$) {
            Arrays.sort(var11, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mJc$sp(var11, sort_from, sort_until, sort_ord, (long[])null, ClassTag$.MODULE$.Long());
         }
      } else if (ret instanceof float[]) {
         float[] var12 = (float[])ret;
         this.scala$util$Sorting$$mergeSort$mFc$sp(var12, sort_from, sort_until, sort_ord, (float[])null, ClassTag$.MODULE$.Float());
      } else if (ret instanceof char[]) {
         char[] var13 = (char[])ret;
         if (sort_ord == Ordering.Char$.MODULE$) {
            Arrays.sort(var13, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mCc$sp(var13, sort_from, sort_until, sort_ord, (char[])null, ClassTag$.MODULE$.Char());
         }
      } else if (ret instanceof byte[]) {
         byte[] var14 = (byte[])ret;
         if (sort_ord == Ordering.Byte$.MODULE$) {
            Arrays.sort(var14, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mBc$sp(var14, sort_from, sort_until, sort_ord, (byte[])null, ClassTag$.MODULE$.Byte());
         }
      } else if (ret instanceof short[]) {
         short[] var15 = (short[])ret;
         if (sort_ord == Ordering.Short$.MODULE$) {
            Arrays.sort(var15, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mSc$sp(var15, sort_from, sort_until, sort_ord, (short[])null, ClassTag$.MODULE$.Short());
         }
      } else {
         if (!(ret instanceof boolean[])) {
            throw new MatchError(ret);
         }

         boolean[] var16 = (boolean[])ret;
         if (sort_ord == Ordering.Boolean$.MODULE$) {
            this.scala$util$Sorting$$booleanSort(var16, sort_from, sort_until);
         } else {
            this.scala$util$Sorting$$mergeSort$mZc$sp(var16, sort_from, sort_until, sort_ord, (boolean[])null, ClassTag$.MODULE$.Boolean());
         }
      }

      return ret;
   }

   private void insertionSort$mZc$sp(final boolean[] a, final int i0, final int iN, final Ordering ord) {
      int n = iN - i0;
      if (n >= 2) {
         if (ord.compare(a[i0], a[i0 + 1]) > 0) {
            boolean temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
         }

         for(int m = 2; m < n; ++m) {
            boolean next = a[i0 + m];
            if (ord.compare(next, a[i0 + m - 1]) < 0) {
               int iA = i0;
               int iB = i0 + m - 1;

               while(iB - iA > 1) {
                  int ix = iA + iB >>> 1;
                  if (ord.compare(next, a[ix]) < 0) {
                     iB = ix;
                  } else {
                     iA = ix;
                  }
               }

               int ix = iA + (ord.compare(next, a[iA]) < 0 ? 0 : 1);

               for(int i = i0 + m; i > ix; --i) {
                  a[i] = a[i - 1];
               }

               a[ix] = next;
            }
         }

      }
   }

   private void insertionSort$mBc$sp(final byte[] a, final int i0, final int iN, final Ordering ord) {
      int n = iN - i0;
      if (n >= 2) {
         if (ord.compare(a[i0], a[i0 + 1]) > 0) {
            byte temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
         }

         for(int m = 2; m < n; ++m) {
            byte next = a[i0 + m];
            if (ord.compare(next, a[i0 + m - 1]) < 0) {
               int iA = i0;
               int iB = i0 + m - 1;

               while(iB - iA > 1) {
                  int ix = iA + iB >>> 1;
                  if (ord.compare(next, a[ix]) < 0) {
                     iB = ix;
                  } else {
                     iA = ix;
                  }
               }

               int ix = iA + (ord.compare(next, a[iA]) < 0 ? 0 : 1);

               for(int i = i0 + m; i > ix; --i) {
                  a[i] = a[i - 1];
               }

               a[ix] = next;
            }
         }

      }
   }

   private void insertionSort$mCc$sp(final char[] a, final int i0, final int iN, final Ordering ord) {
      int n = iN - i0;
      if (n >= 2) {
         if (ord.compare(a[i0], a[i0 + 1]) > 0) {
            char temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
         }

         for(int m = 2; m < n; ++m) {
            char next = a[i0 + m];
            if (ord.compare(next, a[i0 + m - 1]) < 0) {
               int iA = i0;
               int iB = i0 + m - 1;

               while(iB - iA > 1) {
                  int ix = iA + iB >>> 1;
                  if (ord.compare(next, a[ix]) < 0) {
                     iB = ix;
                  } else {
                     iA = ix;
                  }
               }

               int ix = iA + (ord.compare(next, a[iA]) < 0 ? 0 : 1);

               for(int i = i0 + m; i > ix; --i) {
                  a[i] = a[i - 1];
               }

               a[ix] = next;
            }
         }

      }
   }

   private void insertionSort$mDc$sp(final double[] a, final int i0, final int iN, final Ordering ord) {
      int n = iN - i0;
      if (n >= 2) {
         if (ord.compare(a[i0], a[i0 + 1]) > 0) {
            double temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
         }

         for(int m = 2; m < n; ++m) {
            double next = a[i0 + m];
            if (ord.compare(next, a[i0 + m - 1]) < 0) {
               int iA = i0;
               int iB = i0 + m - 1;

               while(iB - iA > 1) {
                  int ix = iA + iB >>> 1;
                  if (ord.compare(next, a[ix]) < 0) {
                     iB = ix;
                  } else {
                     iA = ix;
                  }
               }

               int ix = iA + (ord.compare(next, a[iA]) < 0 ? 0 : 1);

               for(int i = i0 + m; i > ix; --i) {
                  a[i] = a[i - 1];
               }

               a[ix] = next;
            }
         }

      }
   }

   private void insertionSort$mFc$sp(final float[] a, final int i0, final int iN, final Ordering ord) {
      int n = iN - i0;
      if (n >= 2) {
         if (ord.compare(a[i0], a[i0 + 1]) > 0) {
            float temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
         }

         for(int m = 2; m < n; ++m) {
            float next = a[i0 + m];
            if (ord.compare(next, a[i0 + m - 1]) < 0) {
               int iA = i0;
               int iB = i0 + m - 1;

               while(iB - iA > 1) {
                  int ix = iA + iB >>> 1;
                  if (ord.compare(next, a[ix]) < 0) {
                     iB = ix;
                  } else {
                     iA = ix;
                  }
               }

               int ix = iA + (ord.compare(next, a[iA]) < 0 ? 0 : 1);

               for(int i = i0 + m; i > ix; --i) {
                  a[i] = a[i - 1];
               }

               a[ix] = next;
            }
         }

      }
   }

   private void insertionSort$mIc$sp(final int[] a, final int i0, final int iN, final Ordering ord) {
      int n = iN - i0;
      if (n >= 2) {
         if (ord.compare(a[i0], a[i0 + 1]) > 0) {
            int temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
         }

         for(int m = 2; m < n; ++m) {
            int next = a[i0 + m];
            if (ord.compare(next, a[i0 + m - 1]) < 0) {
               int iA = i0;
               int iB = i0 + m - 1;

               while(iB - iA > 1) {
                  int ix = iA + iB >>> 1;
                  if (ord.compare(next, a[ix]) < 0) {
                     iB = ix;
                  } else {
                     iA = ix;
                  }
               }

               int ix = iA + (ord.compare(next, a[iA]) < 0 ? 0 : 1);

               for(int i = i0 + m; i > ix; --i) {
                  a[i] = a[i - 1];
               }

               a[ix] = next;
            }
         }

      }
   }

   private void insertionSort$mJc$sp(final long[] a, final int i0, final int iN, final Ordering ord) {
      int n = iN - i0;
      if (n >= 2) {
         if (ord.compare(a[i0], a[i0 + 1]) > 0) {
            long temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
         }

         for(int m = 2; m < n; ++m) {
            long next = a[i0 + m];
            if (ord.compare(next, a[i0 + m - 1]) < 0) {
               int iA = i0;
               int iB = i0 + m - 1;

               while(iB - iA > 1) {
                  int ix = iA + iB >>> 1;
                  if (ord.compare(next, a[ix]) < 0) {
                     iB = ix;
                  } else {
                     iA = ix;
                  }
               }

               int ix = iA + (ord.compare(next, a[iA]) < 0 ? 0 : 1);

               for(int i = i0 + m; i > ix; --i) {
                  a[i] = a[i - 1];
               }

               a[ix] = next;
            }
         }

      }
   }

   private void insertionSort$mSc$sp(final short[] a, final int i0, final int iN, final Ordering ord) {
      int n = iN - i0;
      if (n >= 2) {
         if (ord.compare(a[i0], a[i0 + 1]) > 0) {
            short temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
         }

         for(int m = 2; m < n; ++m) {
            short next = a[i0 + m];
            if (ord.compare(next, a[i0 + m - 1]) < 0) {
               int iA = i0;
               int iB = i0 + m - 1;

               while(iB - iA > 1) {
                  int ix = iA + iB >>> 1;
                  if (ord.compare(next, a[ix]) < 0) {
                     iB = ix;
                  } else {
                     iA = ix;
                  }
               }

               int ix = iA + (ord.compare(next, a[iA]) < 0 ? 0 : 1);

               for(int i = i0 + m; i > ix; --i) {
                  a[i] = a[i - 1];
               }

               a[ix] = next;
            }
         }

      }
   }

   private void insertionSort$mVc$sp(final BoxedUnit[] a, final int i0, final int iN, final Ordering ord) {
      int n = iN - i0;
      if (n >= 2) {
         if (ord.compare(a[i0], a[i0 + 1]) > 0) {
            BoxedUnit temp = a[i0];
            a[i0] = a[i0 + 1];
            a[i0 + 1] = temp;
         }

         for(int m = 2; m < n; ++m) {
            BoxedUnit next = a[i0 + m];
            if (ord.compare(next, a[i0 + m - 1]) < 0) {
               int iA = i0;
               int iB = i0 + m - 1;

               while(iB - iA > 1) {
                  int ix = iA + iB >>> 1;
                  if (ord.compare(next, a[ix]) < 0) {
                     iB = ix;
                  } else {
                     iA = ix;
                  }
               }

               int ix = iA + (ord.compare(next, a[iA]) < 0 ? 0 : 1);

               for(int i = i0 + m; i > ix; --i) {
                  a[i] = a[i - 1];
               }

               a[ix] = next;
            }
         }

      }
   }

   public void scala$util$Sorting$$mergeSort$mZc$sp(final boolean[] a, final int i0, final int iN, final Ordering ord, final boolean[] scratch, final ClassTag evidence$2) {
      if (iN - i0 < 32) {
         this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
      } else {
         int iK = i0 + iN >>> 1;
         boolean[] sc = scratch == null ? (boolean[])evidence$2.newArray(iK - i0) : scratch;
         this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
      }
   }

   public void scala$util$Sorting$$mergeSort$mBc$sp(final byte[] a, final int i0, final int iN, final Ordering ord, final byte[] scratch, final ClassTag evidence$2) {
      if (iN - i0 < 32) {
         this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
      } else {
         int iK = i0 + iN >>> 1;
         byte[] sc = scratch == null ? (byte[])evidence$2.newArray(iK - i0) : scratch;
         this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
      }
   }

   public void scala$util$Sorting$$mergeSort$mCc$sp(final char[] a, final int i0, final int iN, final Ordering ord, final char[] scratch, final ClassTag evidence$2) {
      if (iN - i0 < 32) {
         this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
      } else {
         int iK = i0 + iN >>> 1;
         char[] sc = scratch == null ? (char[])evidence$2.newArray(iK - i0) : scratch;
         this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
      }
   }

   public void scala$util$Sorting$$mergeSort$mDc$sp(final double[] a, final int i0, final int iN, final Ordering ord, final double[] scratch, final ClassTag evidence$2) {
      if (iN - i0 < 32) {
         this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
      } else {
         int iK = i0 + iN >>> 1;
         double[] sc = scratch == null ? (double[])evidence$2.newArray(iK - i0) : scratch;
         this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
      }
   }

   public void scala$util$Sorting$$mergeSort$mFc$sp(final float[] a, final int i0, final int iN, final Ordering ord, final float[] scratch, final ClassTag evidence$2) {
      if (iN - i0 < 32) {
         this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
      } else {
         int iK = i0 + iN >>> 1;
         float[] sc = scratch == null ? (float[])evidence$2.newArray(iK - i0) : scratch;
         this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
      }
   }

   public void scala$util$Sorting$$mergeSort$mIc$sp(final int[] a, final int i0, final int iN, final Ordering ord, final int[] scratch, final ClassTag evidence$2) {
      if (iN - i0 < 32) {
         this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
      } else {
         int iK = i0 + iN >>> 1;
         int[] sc = scratch == null ? (int[])evidence$2.newArray(iK - i0) : scratch;
         this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
      }
   }

   public void scala$util$Sorting$$mergeSort$mJc$sp(final long[] a, final int i0, final int iN, final Ordering ord, final long[] scratch, final ClassTag evidence$2) {
      if (iN - i0 < 32) {
         this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
      } else {
         int iK = i0 + iN >>> 1;
         long[] sc = scratch == null ? (long[])evidence$2.newArray(iK - i0) : scratch;
         this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
      }
   }

   public void scala$util$Sorting$$mergeSort$mSc$sp(final short[] a, final int i0, final int iN, final Ordering ord, final short[] scratch, final ClassTag evidence$2) {
      if (iN - i0 < 32) {
         this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
      } else {
         int iK = i0 + iN >>> 1;
         short[] sc = scratch == null ? (short[])evidence$2.newArray(iK - i0) : scratch;
         this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
      }
   }

   private void mergeSort$mVc$sp(final BoxedUnit[] a, final int i0, final int iN, final Ordering ord, final BoxedUnit[] scratch, final ClassTag evidence$2) {
      if (iN - i0 < 32) {
         this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
      } else {
         int iK = i0 + iN >>> 1;
         BoxedUnit[] sc = scratch == null ? (BoxedUnit[])evidence$2.newArray(iK - i0) : scratch;
         this.scala$util$Sorting$$mergeSort(a, i0, iK, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSort(a, iK, iN, ord, sc, evidence$2);
         this.scala$util$Sorting$$mergeSorted(a, i0, iK, iN, ord, sc);
      }
   }

   private void mergeSorted$mZc$sp(final boolean[] a, final int i0, final int iK, final int iN, final Ordering ord, final boolean[] scratch) {
      if (ord.compare(a[iK - 1], a[iK]) > 0) {
         int i = i0;
         int jN = iK - i0;

         for(int j = 0; i < iK; ++j) {
            scratch[j] = a[i];
            ++i;
         }

         int k = i0;

         int var11;
         for(var11 = 0; i < iN && var11 < jN; ++k) {
            if (ord.compare(a[i], scratch[var11]) < 0) {
               a[k] = a[i];
               ++i;
            } else {
               a[k] = scratch[var11];
               ++var11;
            }
         }

         while(var11 < jN) {
            a[k] = scratch[var11];
            ++var11;
            ++k;
         }

      }
   }

   private void mergeSorted$mBc$sp(final byte[] a, final int i0, final int iK, final int iN, final Ordering ord, final byte[] scratch) {
      if (ord.compare(a[iK - 1], a[iK]) > 0) {
         int i = i0;
         int jN = iK - i0;

         for(int j = 0; i < iK; ++j) {
            scratch[j] = a[i];
            ++i;
         }

         int k = i0;

         int var11;
         for(var11 = 0; i < iN && var11 < jN; ++k) {
            if (ord.compare(a[i], scratch[var11]) < 0) {
               a[k] = a[i];
               ++i;
            } else {
               a[k] = scratch[var11];
               ++var11;
            }
         }

         while(var11 < jN) {
            a[k] = scratch[var11];
            ++var11;
            ++k;
         }

      }
   }

   private void mergeSorted$mCc$sp(final char[] a, final int i0, final int iK, final int iN, final Ordering ord, final char[] scratch) {
      if (ord.compare(a[iK - 1], a[iK]) > 0) {
         int i = i0;
         int jN = iK - i0;

         for(int j = 0; i < iK; ++j) {
            scratch[j] = a[i];
            ++i;
         }

         int k = i0;

         int var11;
         for(var11 = 0; i < iN && var11 < jN; ++k) {
            if (ord.compare(a[i], scratch[var11]) < 0) {
               a[k] = a[i];
               ++i;
            } else {
               a[k] = scratch[var11];
               ++var11;
            }
         }

         while(var11 < jN) {
            a[k] = scratch[var11];
            ++var11;
            ++k;
         }

      }
   }

   private void mergeSorted$mDc$sp(final double[] a, final int i0, final int iK, final int iN, final Ordering ord, final double[] scratch) {
      if (ord.compare(a[iK - 1], a[iK]) > 0) {
         int i = i0;
         int jN = iK - i0;

         for(int j = 0; i < iK; ++j) {
            scratch[j] = a[i];
            ++i;
         }

         int k = i0;

         int var11;
         for(var11 = 0; i < iN && var11 < jN; ++k) {
            if (ord.compare(a[i], scratch[var11]) < 0) {
               a[k] = a[i];
               ++i;
            } else {
               a[k] = scratch[var11];
               ++var11;
            }
         }

         while(var11 < jN) {
            a[k] = scratch[var11];
            ++var11;
            ++k;
         }

      }
   }

   private void mergeSorted$mFc$sp(final float[] a, final int i0, final int iK, final int iN, final Ordering ord, final float[] scratch) {
      if (ord.compare(a[iK - 1], a[iK]) > 0) {
         int i = i0;
         int jN = iK - i0;

         for(int j = 0; i < iK; ++j) {
            scratch[j] = a[i];
            ++i;
         }

         int k = i0;

         int var11;
         for(var11 = 0; i < iN && var11 < jN; ++k) {
            if (ord.compare(a[i], scratch[var11]) < 0) {
               a[k] = a[i];
               ++i;
            } else {
               a[k] = scratch[var11];
               ++var11;
            }
         }

         while(var11 < jN) {
            a[k] = scratch[var11];
            ++var11;
            ++k;
         }

      }
   }

   private void mergeSorted$mIc$sp(final int[] a, final int i0, final int iK, final int iN, final Ordering ord, final int[] scratch) {
      if (ord.compare(a[iK - 1], a[iK]) > 0) {
         int i = i0;
         int jN = iK - i0;

         for(int j = 0; i < iK; ++j) {
            scratch[j] = a[i];
            ++i;
         }

         int k = i0;

         int var11;
         for(var11 = 0; i < iN && var11 < jN; ++k) {
            if (ord.compare(a[i], scratch[var11]) < 0) {
               a[k] = a[i];
               ++i;
            } else {
               a[k] = scratch[var11];
               ++var11;
            }
         }

         while(var11 < jN) {
            a[k] = scratch[var11];
            ++var11;
            ++k;
         }

      }
   }

   private void mergeSorted$mJc$sp(final long[] a, final int i0, final int iK, final int iN, final Ordering ord, final long[] scratch) {
      if (ord.compare(a[iK - 1], a[iK]) > 0) {
         int i = i0;
         int jN = iK - i0;

         for(int j = 0; i < iK; ++j) {
            scratch[j] = a[i];
            ++i;
         }

         int k = i0;

         int var11;
         for(var11 = 0; i < iN && var11 < jN; ++k) {
            if (ord.compare(a[i], scratch[var11]) < 0) {
               a[k] = a[i];
               ++i;
            } else {
               a[k] = scratch[var11];
               ++var11;
            }
         }

         while(var11 < jN) {
            a[k] = scratch[var11];
            ++var11;
            ++k;
         }

      }
   }

   private void mergeSorted$mSc$sp(final short[] a, final int i0, final int iK, final int iN, final Ordering ord, final short[] scratch) {
      if (ord.compare(a[iK - 1], a[iK]) > 0) {
         int i = i0;
         int jN = iK - i0;

         for(int j = 0; i < iK; ++j) {
            scratch[j] = a[i];
            ++i;
         }

         int k = i0;

         int var11;
         for(var11 = 0; i < iN && var11 < jN; ++k) {
            if (ord.compare(a[i], scratch[var11]) < 0) {
               a[k] = a[i];
               ++i;
            } else {
               a[k] = scratch[var11];
               ++var11;
            }
         }

         while(var11 < jN) {
            a[k] = scratch[var11];
            ++var11;
            ++k;
         }

      }
   }

   private void mergeSorted$mVc$sp(final BoxedUnit[] a, final int i0, final int iK, final int iN, final Ordering ord, final BoxedUnit[] scratch) {
      if (ord.compare(a[iK - 1], a[iK]) > 0) {
         int i = i0;
         int jN = iK - i0;

         for(int j = 0; i < iK; ++j) {
            scratch[j] = a[i];
            ++i;
         }

         int k = i0;

         int var11;
         for(var11 = 0; i < iN && var11 < jN; ++k) {
            if (ord.compare(a[i], scratch[var11]) < 0) {
               a[k] = a[i];
               ++i;
            } else {
               a[k] = scratch[var11];
               ++var11;
            }
         }

         while(var11 < jN) {
            a[k] = scratch[var11];
            ++var11;
            ++k;
         }

      }
   }

   private final void inner$1(final Object a, final int i0, final int iN, final Ordering ord) {
      while(iN - i0 >= 16) {
         int iK = i0 + iN >>> 1;
         int pL = ord.compare(ScalaRunTime$.MODULE$.array_apply(a, i0), ScalaRunTime$.MODULE$.array_apply(a, iN - 1)) <= 0 ? (ord.compare(ScalaRunTime$.MODULE$.array_apply(a, i0), ScalaRunTime$.MODULE$.array_apply(a, iK)) < 0 ? (ord.compare(ScalaRunTime$.MODULE$.array_apply(a, iN - 1), ScalaRunTime$.MODULE$.array_apply(a, iK)) < 0 ? iN - 1 : iK) : i0) : (ord.compare(ScalaRunTime$.MODULE$.array_apply(a, i0), ScalaRunTime$.MODULE$.array_apply(a, iK)) < 0 ? i0 : (ord.compare(ScalaRunTime$.MODULE$.array_apply(a, iN - 1), ScalaRunTime$.MODULE$.array_apply(a, iK)) <= 0 ? iK : iN - 1));
         Object pivot = ScalaRunTime$.MODULE$.array_apply(a, pL);
         if (pL != iK) {
            ScalaRunTime$.MODULE$.array_update(a, pL, ScalaRunTime$.MODULE$.array_apply(a, iK));
            ScalaRunTime$.MODULE$.array_update(a, iK, pivot);
            pL = iK;
         }

         int pR = pL + 1;
         int iA = i0;
         int iB = iN;

         while(pL - iA > 0) {
            Object current = ScalaRunTime$.MODULE$.array_apply(a, iA);
            int var12 = ord.compare(current, pivot);
            switch (var12) {
               case 0:
                  ScalaRunTime$.MODULE$.array_update(a, iA, ScalaRunTime$.MODULE$.array_apply(a, pL - 1));
                  ScalaRunTime$.MODULE$.array_update(a, pL - 1, current);
                  --pL;
                  break;
               default:
                  if (var12 < 0) {
                     ++iA;
                  } else if (iB > pR) {
                     ScalaRunTime$.MODULE$.array_update(a, iA, ScalaRunTime$.MODULE$.array_apply(a, iB - 1));
                     ScalaRunTime$.MODULE$.array_update(a, iB - 1, current);
                     --iB;
                  } else {
                     ScalaRunTime$.MODULE$.array_update(a, iA, ScalaRunTime$.MODULE$.array_apply(a, pL - 1));
                     ScalaRunTime$.MODULE$.array_update(a, pL - 1, ScalaRunTime$.MODULE$.array_apply(a, pR - 1));
                     ScalaRunTime$.MODULE$.array_update(a, pR - 1, current);
                     --pL;
                     --pR;
                     --iB;
                  }
            }
         }

         while(iB - pR > 0) {
            Object current = ScalaRunTime$.MODULE$.array_apply(a, iB - 1);
            int var14 = ord.compare(current, pivot);
            switch (var14) {
               case 0:
                  ScalaRunTime$.MODULE$.array_update(a, iB - 1, ScalaRunTime$.MODULE$.array_apply(a, pR));
                  ScalaRunTime$.MODULE$.array_update(a, pR, current);
                  ++pR;
                  break;
               default:
                  if (var14 > 0) {
                     --iB;
                  } else {
                     ScalaRunTime$.MODULE$.array_update(a, iB - 1, ScalaRunTime$.MODULE$.array_apply(a, pR));
                     ScalaRunTime$.MODULE$.array_update(a, pR, ScalaRunTime$.MODULE$.array_apply(a, pL));
                     ScalaRunTime$.MODULE$.array_update(a, pL, current);
                     ++iA;
                     ++pL;
                     ++pR;
                  }
            }
         }

         if (iA - i0 < iN - iB) {
            this.inner$1(a, i0, iA, ord);
            ord = ord;
            iN = iN;
            i0 = iB;
            a = a;
         } else {
            this.inner$1(a, iB, iN, ord);
            ord = ord;
            iN = iA;
            i0 = i0;
            a = a;
         }
      }

      this.scala$util$Sorting$$insertionSort(a, i0, iN, ord);
   }

   private Sorting$() {
   }
}
