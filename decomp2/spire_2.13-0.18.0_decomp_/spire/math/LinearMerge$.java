package spire.math;

import cats.kernel.Order;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;

public final class LinearMerge$ implements Merge {
   public static final LinearMerge$ MODULE$ = new LinearMerge$();

   public Object merge(final Object a, final Object b, final Order evidence$5, final ClassTag evidence$6) {
      Order o = (Order).MODULE$.implicitly(evidence$5);
      Object r = scala.Array..MODULE$.ofDim(scala.runtime.ScalaRunTime..MODULE$.array_length(a) + scala.runtime.ScalaRunTime..MODULE$.array_length(b), evidence$6);
      int ri = 0;
      int ai = 0;
      int bi = 0;

      while(ai < scala.runtime.ScalaRunTime..MODULE$.array_length(a) && bi < scala.runtime.ScalaRunTime..MODULE$.array_length(b)) {
         int c = o.compare(scala.runtime.ScalaRunTime..MODULE$.array_apply(a, ai), scala.runtime.ScalaRunTime..MODULE$.array_apply(b, bi));
         if (c < 0) {
            scala.runtime.ScalaRunTime..MODULE$.array_update(r, ri, scala.runtime.ScalaRunTime..MODULE$.array_apply(a, ai));
            ++ri;
            ++ai;
         } else if (c > 0) {
            scala.runtime.ScalaRunTime..MODULE$.array_update(r, ri, scala.runtime.ScalaRunTime..MODULE$.array_apply(b, bi));
            ++ri;
            ++bi;
         } else {
            scala.runtime.ScalaRunTime..MODULE$.array_update(r, ri, scala.runtime.ScalaRunTime..MODULE$.array_apply(a, ai));
            ++ri;
            scala.runtime.ScalaRunTime..MODULE$.array_update(r, ri, scala.runtime.ScalaRunTime..MODULE$.array_apply(b, bi));
            ++ri;
            ++ai;
            ++bi;
         }
      }

      while(ai < scala.runtime.ScalaRunTime..MODULE$.array_length(a)) {
         scala.runtime.ScalaRunTime..MODULE$.array_update(r, ri, scala.runtime.ScalaRunTime..MODULE$.array_apply(a, ai));
         ++ri;
         ++ai;
      }

      while(bi < scala.runtime.ScalaRunTime..MODULE$.array_length(b)) {
         scala.runtime.ScalaRunTime..MODULE$.array_update(r, ri, scala.runtime.ScalaRunTime..MODULE$.array_apply(b, bi));
         ++ri;
         ++bi;
      }

      return r;
   }

   public boolean[] merge$mZc$sp(final boolean[] a, final boolean[] b, final Order evidence$5, final ClassTag evidence$6) {
      Order o = (Order).MODULE$.implicitly(evidence$5);
      boolean[] r = (boolean[])scala.Array..MODULE$.ofDim(a.length + b.length, evidence$6);
      int ri = 0;
      int ai = 0;
      int bi = 0;

      while(ai < a.length && bi < b.length) {
         int c = o.compare$mcZ$sp(a[ai], b[bi]);
         if (c < 0) {
            r[ri] = a[ai];
            ++ri;
            ++ai;
         } else if (c > 0) {
            r[ri] = b[bi];
            ++ri;
            ++bi;
         } else {
            r[ri] = a[ai];
            ++ri;
            r[ri] = b[bi];
            ++ri;
            ++ai;
            ++bi;
         }
      }

      while(ai < a.length) {
         r[ri] = a[ai];
         ++ri;
         ++ai;
      }

      while(bi < b.length) {
         r[ri] = b[bi];
         ++ri;
         ++bi;
      }

      return r;
   }

   public byte[] merge$mBc$sp(final byte[] a, final byte[] b, final Order evidence$5, final ClassTag evidence$6) {
      Order o = (Order).MODULE$.implicitly(evidence$5);
      byte[] r = (byte[])scala.Array..MODULE$.ofDim(a.length + b.length, evidence$6);
      int ri = 0;
      int ai = 0;
      int bi = 0;

      while(ai < a.length && bi < b.length) {
         int c = o.compare$mcB$sp(a[ai], b[bi]);
         if (c < 0) {
            r[ri] = a[ai];
            ++ri;
            ++ai;
         } else if (c > 0) {
            r[ri] = b[bi];
            ++ri;
            ++bi;
         } else {
            r[ri] = a[ai];
            ++ri;
            r[ri] = b[bi];
            ++ri;
            ++ai;
            ++bi;
         }
      }

      while(ai < a.length) {
         r[ri] = a[ai];
         ++ri;
         ++ai;
      }

      while(bi < b.length) {
         r[ri] = b[bi];
         ++ri;
         ++bi;
      }

      return r;
   }

   public char[] merge$mCc$sp(final char[] a, final char[] b, final Order evidence$5, final ClassTag evidence$6) {
      Order o = (Order).MODULE$.implicitly(evidence$5);
      char[] r = (char[])scala.Array..MODULE$.ofDim(a.length + b.length, evidence$6);
      int ri = 0;
      int ai = 0;
      int bi = 0;

      while(ai < a.length && bi < b.length) {
         int c = o.compare$mcC$sp(a[ai], b[bi]);
         if (c < 0) {
            r[ri] = a[ai];
            ++ri;
            ++ai;
         } else if (c > 0) {
            r[ri] = b[bi];
            ++ri;
            ++bi;
         } else {
            r[ri] = a[ai];
            ++ri;
            r[ri] = b[bi];
            ++ri;
            ++ai;
            ++bi;
         }
      }

      while(ai < a.length) {
         r[ri] = a[ai];
         ++ri;
         ++ai;
      }

      while(bi < b.length) {
         r[ri] = b[bi];
         ++ri;
         ++bi;
      }

      return r;
   }

   public double[] merge$mDc$sp(final double[] a, final double[] b, final Order evidence$5, final ClassTag evidence$6) {
      Order o = (Order).MODULE$.implicitly(evidence$5);
      double[] r = (double[])scala.Array..MODULE$.ofDim(a.length + b.length, evidence$6);
      int ri = 0;
      int ai = 0;
      int bi = 0;

      while(ai < a.length && bi < b.length) {
         int c = o.compare$mcD$sp(a[ai], b[bi]);
         if (c < 0) {
            r[ri] = a[ai];
            ++ri;
            ++ai;
         } else if (c > 0) {
            r[ri] = b[bi];
            ++ri;
            ++bi;
         } else {
            r[ri] = a[ai];
            ++ri;
            r[ri] = b[bi];
            ++ri;
            ++ai;
            ++bi;
         }
      }

      while(ai < a.length) {
         r[ri] = a[ai];
         ++ri;
         ++ai;
      }

      while(bi < b.length) {
         r[ri] = b[bi];
         ++ri;
         ++bi;
      }

      return r;
   }

   public float[] merge$mFc$sp(final float[] a, final float[] b, final Order evidence$5, final ClassTag evidence$6) {
      Order o = (Order).MODULE$.implicitly(evidence$5);
      float[] r = (float[])scala.Array..MODULE$.ofDim(a.length + b.length, evidence$6);
      int ri = 0;
      int ai = 0;
      int bi = 0;

      while(ai < a.length && bi < b.length) {
         int c = o.compare$mcF$sp(a[ai], b[bi]);
         if (c < 0) {
            r[ri] = a[ai];
            ++ri;
            ++ai;
         } else if (c > 0) {
            r[ri] = b[bi];
            ++ri;
            ++bi;
         } else {
            r[ri] = a[ai];
            ++ri;
            r[ri] = b[bi];
            ++ri;
            ++ai;
            ++bi;
         }
      }

      while(ai < a.length) {
         r[ri] = a[ai];
         ++ri;
         ++ai;
      }

      while(bi < b.length) {
         r[ri] = b[bi];
         ++ri;
         ++bi;
      }

      return r;
   }

   public int[] merge$mIc$sp(final int[] a, final int[] b, final Order evidence$5, final ClassTag evidence$6) {
      Order o = (Order).MODULE$.implicitly(evidence$5);
      int[] r = (int[])scala.Array..MODULE$.ofDim(a.length + b.length, evidence$6);
      int ri = 0;
      int ai = 0;
      int bi = 0;

      while(ai < a.length && bi < b.length) {
         int c = o.compare$mcI$sp(a[ai], b[bi]);
         if (c < 0) {
            r[ri] = a[ai];
            ++ri;
            ++ai;
         } else if (c > 0) {
            r[ri] = b[bi];
            ++ri;
            ++bi;
         } else {
            r[ri] = a[ai];
            ++ri;
            r[ri] = b[bi];
            ++ri;
            ++ai;
            ++bi;
         }
      }

      while(ai < a.length) {
         r[ri] = a[ai];
         ++ri;
         ++ai;
      }

      while(bi < b.length) {
         r[ri] = b[bi];
         ++ri;
         ++bi;
      }

      return r;
   }

   public long[] merge$mJc$sp(final long[] a, final long[] b, final Order evidence$5, final ClassTag evidence$6) {
      Order o = (Order).MODULE$.implicitly(evidence$5);
      long[] r = (long[])scala.Array..MODULE$.ofDim(a.length + b.length, evidence$6);
      int ri = 0;
      int ai = 0;
      int bi = 0;

      while(ai < a.length && bi < b.length) {
         int c = o.compare$mcJ$sp(a[ai], b[bi]);
         if (c < 0) {
            r[ri] = a[ai];
            ++ri;
            ++ai;
         } else if (c > 0) {
            r[ri] = b[bi];
            ++ri;
            ++bi;
         } else {
            r[ri] = a[ai];
            ++ri;
            r[ri] = b[bi];
            ++ri;
            ++ai;
            ++bi;
         }
      }

      while(ai < a.length) {
         r[ri] = a[ai];
         ++ri;
         ++ai;
      }

      while(bi < b.length) {
         r[ri] = b[bi];
         ++ri;
         ++bi;
      }

      return r;
   }

   public short[] merge$mSc$sp(final short[] a, final short[] b, final Order evidence$5, final ClassTag evidence$6) {
      Order o = (Order).MODULE$.implicitly(evidence$5);
      short[] r = (short[])scala.Array..MODULE$.ofDim(a.length + b.length, evidence$6);
      int ri = 0;
      int ai = 0;
      int bi = 0;

      while(ai < a.length && bi < b.length) {
         int c = o.compare$mcS$sp(a[ai], b[bi]);
         if (c < 0) {
            r[ri] = a[ai];
            ++ri;
            ++ai;
         } else if (c > 0) {
            r[ri] = b[bi];
            ++ri;
            ++bi;
         } else {
            r[ri] = a[ai];
            ++ri;
            r[ri] = b[bi];
            ++ri;
            ++ai;
            ++bi;
         }
      }

      while(ai < a.length) {
         r[ri] = a[ai];
         ++ri;
         ++ai;
      }

      while(bi < b.length) {
         r[ri] = b[bi];
         ++ri;
         ++bi;
      }

      return r;
   }

   public BoxedUnit[] merge$mVc$sp(final BoxedUnit[] a, final BoxedUnit[] b, final Order evidence$5, final ClassTag evidence$6) {
      Order o = (Order).MODULE$.implicitly(evidence$5);
      BoxedUnit[] r = (BoxedUnit[])scala.Array..MODULE$.ofDim(a.length + b.length, evidence$6);
      int ri = 0;
      int ai = 0;
      int bi = 0;

      while(ai < a.length && bi < b.length) {
         int c = o.compare$mcV$sp(a[ai], b[bi]);
         if (c < 0) {
            r[ri] = a[ai];
            ++ri;
            ++ai;
         } else if (c > 0) {
            r[ri] = b[bi];
            ++ri;
            ++bi;
         } else {
            r[ri] = a[ai];
            ++ri;
            r[ri] = b[bi];
            ++ri;
            ++ai;
            ++bi;
         }
      }

      while(ai < a.length) {
         r[ri] = a[ai];
         ++ri;
         ++ai;
      }

      while(bi < b.length) {
         r[ri] = b[bi];
         ++ri;
         ++bi;
      }

      return r;
   }

   private LinearMerge$() {
   }
}
