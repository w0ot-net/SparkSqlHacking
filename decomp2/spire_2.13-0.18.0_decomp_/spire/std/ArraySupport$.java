package spire.std;

import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Rig;
import algebra.ring.Ring;
import cats.kernel.Eq;
import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

public final class ArraySupport$ {
   public static final ArraySupport$ MODULE$ = new ArraySupport$();

   public boolean eqv(final Object x, final Object y, final Eq evidence$1) {
      int i = 0;
      if (.MODULE$.array_length(x) != .MODULE$.array_length(y)) {
         return false;
      } else {
         while(i < .MODULE$.array_length(x) && i < .MODULE$.array_length(y) && evidence$1.eqv(.MODULE$.array_apply(x, i), .MODULE$.array_apply(y, i))) {
            ++i;
         }

         return i == .MODULE$.array_length(x);
      }
   }

   public boolean vectorEqv(final Object x, final Object y, final Eq ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < .MODULE$.array_length(x) && i < .MODULE$.array_length(y) && ev.eqv(.MODULE$.array_apply(x, i), .MODULE$.array_apply(y, i)); ++i) {
      }

      while(i < .MODULE$.array_length(x) && ev.eqv(.MODULE$.array_apply(x, i), sc.zero())) {
         ++i;
      }

      while(i < .MODULE$.array_length(y) && ev.eqv(.MODULE$.array_apply(y, i), sc.zero())) {
         ++i;
      }

      return i >= .MODULE$.array_length(x) && i >= .MODULE$.array_length(y);
   }

   public int compare(final Object x, final Object y, final Order evidence$2) {
      for(int i = 0; i < .MODULE$.array_length(x) && i < .MODULE$.array_length(y); ++i) {
         int cmp = evidence$2.compare(.MODULE$.array_apply(x, i), .MODULE$.array_apply(y, i));
         if (cmp != 0) {
            return cmp;
         }
      }

      return .MODULE$.array_length(x) - .MODULE$.array_length(y);
   }

   public int vectorCompare(final Object x, final Object y, final Order ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < .MODULE$.array_length(x) && i < .MODULE$.array_length(y); ++i) {
         int cmp = ev.compare(.MODULE$.array_apply(x, i), .MODULE$.array_apply(y, i));
         if (cmp != 0) {
            return cmp;
         }
      }

      while(i < .MODULE$.array_length(x)) {
         if (ev.neqv(.MODULE$.array_apply(x, i), sc.zero())) {
            return 1;
         }

         ++i;
      }

      while(i < .MODULE$.array_length(y)) {
         if (ev.neqv(.MODULE$.array_apply(y, i), sc.zero())) {
            return -1;
         }

         ++i;
      }

      return 0;
   }

   public Object concat(final Object x, final Object y, final ClassTag evidence$3) {
      Object z = evidence$3.newArray(.MODULE$.array_length(x) + .MODULE$.array_length(y));
      System.arraycopy(x, 0, z, 0, .MODULE$.array_length(x));
      System.arraycopy(y, 0, z, .MODULE$.array_length(x), .MODULE$.array_length(y));
      return z;
   }

   public Object negate(final Object x, final ClassTag evidence$4, final Ring evidence$5) {
      Object y = evidence$4.newArray(.MODULE$.array_length(x));

      for(int i = 0; i < .MODULE$.array_length(x); ++i) {
         .MODULE$.array_update(y, i, evidence$5.negate(.MODULE$.array_apply(x, i)));
      }

      return y;
   }

   public Object plus(final Object x, final Object y, final ClassTag evidence$6, final AdditiveMonoid evidence$7) {
      Object z = evidence$6.newArray(spire.math.package$.MODULE$.max(.MODULE$.array_length(x), .MODULE$.array_length(y)));

      int i;
      for(i = 0; i < .MODULE$.array_length(x) && i < .MODULE$.array_length(y); ++i) {
         .MODULE$.array_update(z, i, evidence$7.plus(.MODULE$.array_apply(x, i), .MODULE$.array_apply(y, i)));
      }

      while(i < .MODULE$.array_length(x)) {
         .MODULE$.array_update(z, i, .MODULE$.array_apply(x, i));
         ++i;
      }

      while(i < .MODULE$.array_length(y)) {
         .MODULE$.array_update(z, i, .MODULE$.array_apply(y, i));
         ++i;
      }

      return z;
   }

   public Object minus(final Object x, final Object y, final ClassTag evidence$8, final AdditiveGroup evidence$9) {
      Object z = evidence$8.newArray(spire.math.package$.MODULE$.max(.MODULE$.array_length(x), .MODULE$.array_length(y)));

      int i;
      for(i = 0; i < .MODULE$.array_length(x) && i < .MODULE$.array_length(y); ++i) {
         .MODULE$.array_update(z, i, evidence$9.minus(.MODULE$.array_apply(x, i), .MODULE$.array_apply(y, i)));
      }

      while(i < .MODULE$.array_length(x)) {
         .MODULE$.array_update(z, i, .MODULE$.array_apply(x, i));
         ++i;
      }

      while(i < .MODULE$.array_length(y)) {
         .MODULE$.array_update(z, i, evidence$9.negate(.MODULE$.array_apply(y, i)));
         ++i;
      }

      return z;
   }

   public Object timesl(final Object r, final Object x, final ClassTag evidence$10, final MultiplicativeSemigroup evidence$11) {
      Object y = evidence$10.newArray(.MODULE$.array_length(x));

      for(int i = 0; i < .MODULE$.array_length(y); ++i) {
         .MODULE$.array_update(y, i, evidence$11.times(r, .MODULE$.array_apply(x, i)));
      }

      return y;
   }

   public Object dot(final Object x, final Object y, final Rig sc) {
      Object z = sc.zero();

      for(int i = 0; i < .MODULE$.array_length(x) && i < .MODULE$.array_length(y); ++i) {
         z = sc.plus(z, sc.times(.MODULE$.array_apply(x, i), .MODULE$.array_apply(y, i)));
      }

      return z;
   }

   public Object axis(final int dimensions, final int i, final ClassTag ct, final Rig sc) {
      Object v = ct.newArray(dimensions);

      for(int j = 0; j < .MODULE$.array_length(v); ++j) {
         .MODULE$.array_update(v, j, sc.zero());
      }

      if (i < dimensions) {
         .MODULE$.array_update(v, i, sc.one());
      }

      return v;
   }

   public boolean eqv$mZc$sp(final boolean[] x, final boolean[] y, final Eq evidence$1) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && evidence$1.eqv$mcZ$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean eqv$mBc$sp(final byte[] x, final byte[] y, final Eq evidence$1) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && evidence$1.eqv$mcB$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean eqv$mCc$sp(final char[] x, final char[] y, final Eq evidence$1) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && evidence$1.eqv$mcC$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean eqv$mDc$sp(final double[] x, final double[] y, final Eq evidence$1) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && evidence$1.eqv$mcD$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean eqv$mFc$sp(final float[] x, final float[] y, final Eq evidence$1) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && evidence$1.eqv$mcF$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean eqv$mIc$sp(final int[] x, final int[] y, final Eq evidence$1) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && evidence$1.eqv$mcI$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean eqv$mJc$sp(final long[] x, final long[] y, final Eq evidence$1) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && evidence$1.eqv$mcJ$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean eqv$mSc$sp(final short[] x, final short[] y, final Eq evidence$1) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && evidence$1.eqv$mcS$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean eqv$mVc$sp(final BoxedUnit[] x, final BoxedUnit[] y, final Eq evidence$1) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && evidence$1.eqv$mcV$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean vectorEqv$mZc$sp(final boolean[] x, final boolean[] y, final Eq ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length && ev.eqv$mcZ$sp(x[i], y[i]); ++i) {
      }

      while(i < x.length && ev.eqv$mcZ$sp(x[i], BoxesRunTime.unboxToBoolean(sc.zero()))) {
         ++i;
      }

      while(i < y.length && ev.eqv$mcZ$sp(y[i], BoxesRunTime.unboxToBoolean(sc.zero()))) {
         ++i;
      }

      return i >= x.length && i >= y.length;
   }

   public boolean vectorEqv$mBc$sp(final byte[] x, final byte[] y, final Eq ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length && ev.eqv$mcB$sp(x[i], y[i]); ++i) {
      }

      while(i < x.length && ev.eqv$mcB$sp(x[i], BoxesRunTime.unboxToByte(sc.zero()))) {
         ++i;
      }

      while(i < y.length && ev.eqv$mcB$sp(y[i], BoxesRunTime.unboxToByte(sc.zero()))) {
         ++i;
      }

      return i >= x.length && i >= y.length;
   }

   public boolean vectorEqv$mCc$sp(final char[] x, final char[] y, final Eq ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length && ev.eqv$mcC$sp(x[i], y[i]); ++i) {
      }

      while(i < x.length && ev.eqv$mcC$sp(x[i], BoxesRunTime.unboxToChar(sc.zero()))) {
         ++i;
      }

      while(i < y.length && ev.eqv$mcC$sp(y[i], BoxesRunTime.unboxToChar(sc.zero()))) {
         ++i;
      }

      return i >= x.length && i >= y.length;
   }

   public boolean vectorEqv$mDc$sp(final double[] x, final double[] y, final Eq ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length && ev.eqv$mcD$sp(x[i], y[i]); ++i) {
      }

      while(i < x.length && ev.eqv$mcD$sp(x[i], sc.zero$mcD$sp())) {
         ++i;
      }

      while(i < y.length && ev.eqv$mcD$sp(y[i], sc.zero$mcD$sp())) {
         ++i;
      }

      return i >= x.length && i >= y.length;
   }

   public boolean vectorEqv$mFc$sp(final float[] x, final float[] y, final Eq ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length && ev.eqv$mcF$sp(x[i], y[i]); ++i) {
      }

      while(i < x.length && ev.eqv$mcF$sp(x[i], sc.zero$mcF$sp())) {
         ++i;
      }

      while(i < y.length && ev.eqv$mcF$sp(y[i], sc.zero$mcF$sp())) {
         ++i;
      }

      return i >= x.length && i >= y.length;
   }

   public boolean vectorEqv$mIc$sp(final int[] x, final int[] y, final Eq ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length && ev.eqv$mcI$sp(x[i], y[i]); ++i) {
      }

      while(i < x.length && ev.eqv$mcI$sp(x[i], sc.zero$mcI$sp())) {
         ++i;
      }

      while(i < y.length && ev.eqv$mcI$sp(y[i], sc.zero$mcI$sp())) {
         ++i;
      }

      return i >= x.length && i >= y.length;
   }

   public boolean vectorEqv$mJc$sp(final long[] x, final long[] y, final Eq ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length && ev.eqv$mcJ$sp(x[i], y[i]); ++i) {
      }

      while(i < x.length && ev.eqv$mcJ$sp(x[i], sc.zero$mcJ$sp())) {
         ++i;
      }

      while(i < y.length && ev.eqv$mcJ$sp(y[i], sc.zero$mcJ$sp())) {
         ++i;
      }

      return i >= x.length && i >= y.length;
   }

   public boolean vectorEqv$mSc$sp(final short[] x, final short[] y, final Eq ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length && ev.eqv$mcS$sp(x[i], y[i]); ++i) {
      }

      while(i < x.length && ev.eqv$mcS$sp(x[i], BoxesRunTime.unboxToShort(sc.zero()))) {
         ++i;
      }

      while(i < y.length && ev.eqv$mcS$sp(y[i], BoxesRunTime.unboxToShort(sc.zero()))) {
         ++i;
      }

      return i >= x.length && i >= y.length;
   }

   public boolean vectorEqv$mVc$sp(final BoxedUnit[] x, final BoxedUnit[] y, final Eq ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length && ev.eqv$mcV$sp(x[i], y[i]); ++i) {
      }

      while(i < x.length && ev.eqv$mcV$sp(x[i], (BoxedUnit)sc.zero())) {
         ++i;
      }

      while(i < y.length && ev.eqv$mcV$sp(y[i], (BoxedUnit)sc.zero())) {
         ++i;
      }

      return i >= x.length && i >= y.length;
   }

   public int compare$mZc$sp(final boolean[] x, final boolean[] y, final Order evidence$2) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = evidence$2.compare$mcZ$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return x.length - y.length;
   }

   public int compare$mBc$sp(final byte[] x, final byte[] y, final Order evidence$2) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = evidence$2.compare$mcB$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return x.length - y.length;
   }

   public int compare$mCc$sp(final char[] x, final char[] y, final Order evidence$2) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = evidence$2.compare$mcC$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return x.length - y.length;
   }

   public int compare$mDc$sp(final double[] x, final double[] y, final Order evidence$2) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = evidence$2.compare$mcD$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return x.length - y.length;
   }

   public int compare$mFc$sp(final float[] x, final float[] y, final Order evidence$2) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = evidence$2.compare$mcF$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return x.length - y.length;
   }

   public int compare$mIc$sp(final int[] x, final int[] y, final Order evidence$2) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = evidence$2.compare$mcI$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return x.length - y.length;
   }

   public int compare$mJc$sp(final long[] x, final long[] y, final Order evidence$2) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = evidence$2.compare$mcJ$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return x.length - y.length;
   }

   public int compare$mSc$sp(final short[] x, final short[] y, final Order evidence$2) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = evidence$2.compare$mcS$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return x.length - y.length;
   }

   public int compare$mVc$sp(final BoxedUnit[] x, final BoxedUnit[] y, final Order evidence$2) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = evidence$2.compare$mcV$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return x.length - y.length;
   }

   public int vectorCompare$mZc$sp(final boolean[] x, final boolean[] y, final Order ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcZ$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      while(i < x.length) {
         if (ev.neqv$mcZ$sp(x[i], BoxesRunTime.unboxToBoolean(sc.zero()))) {
            return 1;
         }

         ++i;
      }

      while(i < y.length) {
         if (ev.neqv$mcZ$sp(y[i], BoxesRunTime.unboxToBoolean(sc.zero()))) {
            return -1;
         }

         ++i;
      }

      return 0;
   }

   public int vectorCompare$mBc$sp(final byte[] x, final byte[] y, final Order ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcB$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      while(i < x.length) {
         if (ev.neqv$mcB$sp(x[i], BoxesRunTime.unboxToByte(sc.zero()))) {
            return 1;
         }

         ++i;
      }

      while(i < y.length) {
         if (ev.neqv$mcB$sp(y[i], BoxesRunTime.unboxToByte(sc.zero()))) {
            return -1;
         }

         ++i;
      }

      return 0;
   }

   public int vectorCompare$mCc$sp(final char[] x, final char[] y, final Order ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcC$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      while(i < x.length) {
         if (ev.neqv$mcC$sp(x[i], BoxesRunTime.unboxToChar(sc.zero()))) {
            return 1;
         }

         ++i;
      }

      while(i < y.length) {
         if (ev.neqv$mcC$sp(y[i], BoxesRunTime.unboxToChar(sc.zero()))) {
            return -1;
         }

         ++i;
      }

      return 0;
   }

   public int vectorCompare$mDc$sp(final double[] x, final double[] y, final Order ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcD$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      while(i < x.length) {
         if (ev.neqv$mcD$sp(x[i], sc.zero$mcD$sp())) {
            return 1;
         }

         ++i;
      }

      while(i < y.length) {
         if (ev.neqv$mcD$sp(y[i], sc.zero$mcD$sp())) {
            return -1;
         }

         ++i;
      }

      return 0;
   }

   public int vectorCompare$mFc$sp(final float[] x, final float[] y, final Order ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcF$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      while(i < x.length) {
         if (ev.neqv$mcF$sp(x[i], sc.zero$mcF$sp())) {
            return 1;
         }

         ++i;
      }

      while(i < y.length) {
         if (ev.neqv$mcF$sp(y[i], sc.zero$mcF$sp())) {
            return -1;
         }

         ++i;
      }

      return 0;
   }

   public int vectorCompare$mIc$sp(final int[] x, final int[] y, final Order ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcI$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      while(i < x.length) {
         if (ev.neqv$mcI$sp(x[i], sc.zero$mcI$sp())) {
            return 1;
         }

         ++i;
      }

      while(i < y.length) {
         if (ev.neqv$mcI$sp(y[i], sc.zero$mcI$sp())) {
            return -1;
         }

         ++i;
      }

      return 0;
   }

   public int vectorCompare$mJc$sp(final long[] x, final long[] y, final Order ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcJ$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      while(i < x.length) {
         if (ev.neqv$mcJ$sp(x[i], sc.zero$mcJ$sp())) {
            return 1;
         }

         ++i;
      }

      while(i < y.length) {
         if (ev.neqv$mcJ$sp(y[i], sc.zero$mcJ$sp())) {
            return -1;
         }

         ++i;
      }

      return 0;
   }

   public int vectorCompare$mSc$sp(final short[] x, final short[] y, final Order ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcS$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      while(i < x.length) {
         if (ev.neqv$mcS$sp(x[i], BoxesRunTime.unboxToShort(sc.zero()))) {
            return 1;
         }

         ++i;
      }

      while(i < y.length) {
         if (ev.neqv$mcS$sp(y[i], BoxesRunTime.unboxToShort(sc.zero()))) {
            return -1;
         }

         ++i;
      }

      return 0;
   }

   public int vectorCompare$mVc$sp(final BoxedUnit[] x, final BoxedUnit[] y, final Order ev, final AdditiveMonoid sc) {
      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcV$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      while(i < x.length) {
         if (ev.neqv$mcV$sp(x[i], (BoxedUnit)sc.zero())) {
            return 1;
         }

         ++i;
      }

      while(i < y.length) {
         if (ev.neqv$mcV$sp(y[i], (BoxedUnit)sc.zero())) {
            return -1;
         }

         ++i;
      }

      return 0;
   }

   public boolean[] concat$mZc$sp(final boolean[] x, final boolean[] y, final ClassTag evidence$3) {
      boolean[] z = (boolean[])evidence$3.newArray(x.length + y.length);
      System.arraycopy(x, 0, z, 0, x.length);
      System.arraycopy(y, 0, z, x.length, y.length);
      return z;
   }

   public byte[] concat$mBc$sp(final byte[] x, final byte[] y, final ClassTag evidence$3) {
      byte[] z = (byte[])evidence$3.newArray(x.length + y.length);
      System.arraycopy(x, 0, z, 0, x.length);
      System.arraycopy(y, 0, z, x.length, y.length);
      return z;
   }

   public char[] concat$mCc$sp(final char[] x, final char[] y, final ClassTag evidence$3) {
      char[] z = (char[])evidence$3.newArray(x.length + y.length);
      System.arraycopy(x, 0, z, 0, x.length);
      System.arraycopy(y, 0, z, x.length, y.length);
      return z;
   }

   public double[] concat$mDc$sp(final double[] x, final double[] y, final ClassTag evidence$3) {
      double[] z = (double[])evidence$3.newArray(x.length + y.length);
      System.arraycopy(x, 0, z, 0, x.length);
      System.arraycopy(y, 0, z, x.length, y.length);
      return z;
   }

   public float[] concat$mFc$sp(final float[] x, final float[] y, final ClassTag evidence$3) {
      float[] z = (float[])evidence$3.newArray(x.length + y.length);
      System.arraycopy(x, 0, z, 0, x.length);
      System.arraycopy(y, 0, z, x.length, y.length);
      return z;
   }

   public int[] concat$mIc$sp(final int[] x, final int[] y, final ClassTag evidence$3) {
      int[] z = (int[])evidence$3.newArray(x.length + y.length);
      System.arraycopy(x, 0, z, 0, x.length);
      System.arraycopy(y, 0, z, x.length, y.length);
      return z;
   }

   public long[] concat$mJc$sp(final long[] x, final long[] y, final ClassTag evidence$3) {
      long[] z = (long[])evidence$3.newArray(x.length + y.length);
      System.arraycopy(x, 0, z, 0, x.length);
      System.arraycopy(y, 0, z, x.length, y.length);
      return z;
   }

   public short[] concat$mSc$sp(final short[] x, final short[] y, final ClassTag evidence$3) {
      short[] z = (short[])evidence$3.newArray(x.length + y.length);
      System.arraycopy(x, 0, z, 0, x.length);
      System.arraycopy(y, 0, z, x.length, y.length);
      return z;
   }

   public BoxedUnit[] concat$mVc$sp(final BoxedUnit[] x, final BoxedUnit[] y, final ClassTag evidence$3) {
      BoxedUnit[] z = (BoxedUnit[])evidence$3.newArray(x.length + y.length);
      System.arraycopy(x, 0, z, 0, x.length);
      System.arraycopy(y, 0, z, x.length, y.length);
      return z;
   }

   public double[] negate$mDc$sp(final double[] x, final ClassTag evidence$4, final Ring evidence$5) {
      double[] y = (double[])evidence$4.newArray(x.length);

      for(int i = 0; i < x.length; ++i) {
         y[i] = evidence$5.negate$mcD$sp(x[i]);
      }

      return y;
   }

   public float[] negate$mFc$sp(final float[] x, final ClassTag evidence$4, final Ring evidence$5) {
      float[] y = (float[])evidence$4.newArray(x.length);

      for(int i = 0; i < x.length; ++i) {
         y[i] = evidence$5.negate$mcF$sp(x[i]);
      }

      return y;
   }

   public int[] negate$mIc$sp(final int[] x, final ClassTag evidence$4, final Ring evidence$5) {
      int[] y = (int[])evidence$4.newArray(x.length);

      for(int i = 0; i < x.length; ++i) {
         y[i] = evidence$5.negate$mcI$sp(x[i]);
      }

      return y;
   }

   public long[] negate$mJc$sp(final long[] x, final ClassTag evidence$4, final Ring evidence$5) {
      long[] y = (long[])evidence$4.newArray(x.length);

      for(int i = 0; i < x.length; ++i) {
         y[i] = evidence$5.negate$mcJ$sp(x[i]);
      }

      return y;
   }

   public double[] plus$mDc$sp(final double[] x, final double[] y, final ClassTag evidence$6, final AdditiveMonoid evidence$7) {
      double[] z = (double[])evidence$6.newArray(spire.math.package$.MODULE$.max(x.length, y.length));

      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         z[i] = evidence$7.plus$mcD$sp(x[i], y[i]);
      }

      while(i < x.length) {
         z[i] = x[i];
         ++i;
      }

      while(i < y.length) {
         z[i] = y[i];
         ++i;
      }

      return z;
   }

   public float[] plus$mFc$sp(final float[] x, final float[] y, final ClassTag evidence$6, final AdditiveMonoid evidence$7) {
      float[] z = (float[])evidence$6.newArray(spire.math.package$.MODULE$.max(x.length, y.length));

      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         z[i] = evidence$7.plus$mcF$sp(x[i], y[i]);
      }

      while(i < x.length) {
         z[i] = x[i];
         ++i;
      }

      while(i < y.length) {
         z[i] = y[i];
         ++i;
      }

      return z;
   }

   public int[] plus$mIc$sp(final int[] x, final int[] y, final ClassTag evidence$6, final AdditiveMonoid evidence$7) {
      int[] z = (int[])evidence$6.newArray(spire.math.package$.MODULE$.max(x.length, y.length));

      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         z[i] = evidence$7.plus$mcI$sp(x[i], y[i]);
      }

      while(i < x.length) {
         z[i] = x[i];
         ++i;
      }

      while(i < y.length) {
         z[i] = y[i];
         ++i;
      }

      return z;
   }

   public long[] plus$mJc$sp(final long[] x, final long[] y, final ClassTag evidence$6, final AdditiveMonoid evidence$7) {
      long[] z = (long[])evidence$6.newArray(spire.math.package$.MODULE$.max(x.length, y.length));

      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         z[i] = evidence$7.plus$mcJ$sp(x[i], y[i]);
      }

      while(i < x.length) {
         z[i] = x[i];
         ++i;
      }

      while(i < y.length) {
         z[i] = y[i];
         ++i;
      }

      return z;
   }

   public double[] minus$mDc$sp(final double[] x, final double[] y, final ClassTag evidence$8, final AdditiveGroup evidence$9) {
      double[] z = (double[])evidence$8.newArray(spire.math.package$.MODULE$.max(x.length, y.length));

      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         z[i] = evidence$9.minus$mcD$sp(x[i], y[i]);
      }

      while(i < x.length) {
         z[i] = x[i];
         ++i;
      }

      while(i < y.length) {
         z[i] = evidence$9.negate$mcD$sp(y[i]);
         ++i;
      }

      return z;
   }

   public float[] minus$mFc$sp(final float[] x, final float[] y, final ClassTag evidence$8, final AdditiveGroup evidence$9) {
      float[] z = (float[])evidence$8.newArray(spire.math.package$.MODULE$.max(x.length, y.length));

      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         z[i] = evidence$9.minus$mcF$sp(x[i], y[i]);
      }

      while(i < x.length) {
         z[i] = x[i];
         ++i;
      }

      while(i < y.length) {
         z[i] = evidence$9.negate$mcF$sp(y[i]);
         ++i;
      }

      return z;
   }

   public int[] minus$mIc$sp(final int[] x, final int[] y, final ClassTag evidence$8, final AdditiveGroup evidence$9) {
      int[] z = (int[])evidence$8.newArray(spire.math.package$.MODULE$.max(x.length, y.length));

      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         z[i] = evidence$9.minus$mcI$sp(x[i], y[i]);
      }

      while(i < x.length) {
         z[i] = x[i];
         ++i;
      }

      while(i < y.length) {
         z[i] = evidence$9.negate$mcI$sp(y[i]);
         ++i;
      }

      return z;
   }

   public long[] minus$mJc$sp(final long[] x, final long[] y, final ClassTag evidence$8, final AdditiveGroup evidence$9) {
      long[] z = (long[])evidence$8.newArray(spire.math.package$.MODULE$.max(x.length, y.length));

      int i;
      for(i = 0; i < x.length && i < y.length; ++i) {
         z[i] = evidence$9.minus$mcJ$sp(x[i], y[i]);
      }

      while(i < x.length) {
         z[i] = x[i];
         ++i;
      }

      while(i < y.length) {
         z[i] = evidence$9.negate$mcJ$sp(y[i]);
         ++i;
      }

      return z;
   }

   public double[] timesl$mDc$sp(final double r, final double[] x, final ClassTag evidence$10, final MultiplicativeSemigroup evidence$11) {
      double[] y = (double[])evidence$10.newArray(x.length);

      for(int i = 0; i < y.length; ++i) {
         y[i] = evidence$11.times$mcD$sp(r, x[i]);
      }

      return y;
   }

   public float[] timesl$mFc$sp(final float r, final float[] x, final ClassTag evidence$10, final MultiplicativeSemigroup evidence$11) {
      float[] y = (float[])evidence$10.newArray(x.length);

      for(int i = 0; i < y.length; ++i) {
         y[i] = evidence$11.times$mcF$sp(r, x[i]);
      }

      return y;
   }

   public int[] timesl$mIc$sp(final int r, final int[] x, final ClassTag evidence$10, final MultiplicativeSemigroup evidence$11) {
      int[] y = (int[])evidence$10.newArray(x.length);

      for(int i = 0; i < y.length; ++i) {
         y[i] = evidence$11.times$mcI$sp(r, x[i]);
      }

      return y;
   }

   public long[] timesl$mJc$sp(final long r, final long[] x, final ClassTag evidence$10, final MultiplicativeSemigroup evidence$11) {
      long[] y = (long[])evidence$10.newArray(x.length);

      for(int i = 0; i < y.length; ++i) {
         y[i] = evidence$11.times$mcJ$sp(r, x[i]);
      }

      return y;
   }

   public double dot$mDc$sp(final double[] x, final double[] y, final Rig sc) {
      double z = sc.zero$mcD$sp();

      for(int i = 0; i < x.length && i < y.length; ++i) {
         z = sc.plus$mcD$sp(z, sc.times$mcD$sp(x[i], y[i]));
      }

      return z;
   }

   public float dot$mFc$sp(final float[] x, final float[] y, final Rig sc) {
      float z = sc.zero$mcF$sp();

      for(int i = 0; i < x.length && i < y.length; ++i) {
         z = sc.plus$mcF$sp(z, sc.times$mcF$sp(x[i], y[i]));
      }

      return z;
   }

   public int dot$mIc$sp(final int[] x, final int[] y, final Rig sc) {
      int z = sc.zero$mcI$sp();

      for(int i = 0; i < x.length && i < y.length; ++i) {
         z = sc.plus$mcI$sp(z, sc.times$mcI$sp(x[i], y[i]));
      }

      return z;
   }

   public long dot$mJc$sp(final long[] x, final long[] y, final Rig sc) {
      long z = sc.zero$mcJ$sp();

      for(int i = 0; i < x.length && i < y.length; ++i) {
         z = sc.plus$mcJ$sp(z, sc.times$mcJ$sp(x[i], y[i]));
      }

      return z;
   }

   public double[] axis$mDc$sp(final int dimensions, final int i, final ClassTag ct, final Rig sc) {
      double[] v = (double[])ct.newArray(dimensions);

      for(int j = 0; j < v.length; ++j) {
         v[j] = sc.zero$mcD$sp();
      }

      if (i < dimensions) {
         v[i] = sc.one$mcD$sp();
      }

      return v;
   }

   public float[] axis$mFc$sp(final int dimensions, final int i, final ClassTag ct, final Rig sc) {
      float[] v = (float[])ct.newArray(dimensions);

      for(int j = 0; j < v.length; ++j) {
         v[j] = sc.zero$mcF$sp();
      }

      if (i < dimensions) {
         v[i] = sc.one$mcF$sp();
      }

      return v;
   }

   private ArraySupport$() {
   }
}
