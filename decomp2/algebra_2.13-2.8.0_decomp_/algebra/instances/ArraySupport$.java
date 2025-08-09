package algebra.instances;

import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.runtime.BoxedUnit;
import scala.runtime.ScalaRunTime.;

public final class ArraySupport$ {
   public static final ArraySupport$ MODULE$ = new ArraySupport$();

   public int algebra$instances$ArraySupport$$signum(final int x) {
      return x < 0 ? -1 : (x > 0 ? 1 : 0);
   }

   public boolean eqv(final Object x, final Object y, final Eq ev) {
      int i = 0;
      if (.MODULE$.array_length(x) != .MODULE$.array_length(y)) {
         return false;
      } else {
         while(i < .MODULE$.array_length(x) && i < .MODULE$.array_length(y) && ev.eqv(.MODULE$.array_apply(x, i), .MODULE$.array_apply(y, i))) {
            ++i;
         }

         return i == .MODULE$.array_length(x);
      }
   }

   public int compare(final Object x, final Object y, final Order ev) {
      for(int i = 0; i < .MODULE$.array_length(x) && i < .MODULE$.array_length(y); ++i) {
         int cmp = ev.compare(.MODULE$.array_apply(x, i), .MODULE$.array_apply(y, i));
         if (cmp != 0) {
            return cmp;
         }
      }

      return this.algebra$instances$ArraySupport$$signum(.MODULE$.array_length(x) - .MODULE$.array_length(y));
   }

   public double partialCompare(final Object x, final Object y, final PartialOrder ev) {
      for(int i = 0; i < .MODULE$.array_length(x) && i < .MODULE$.array_length(y); ++i) {
         double cmp = ev.partialCompare(.MODULE$.array_apply(x, i), .MODULE$.array_apply(y, i));
         if (cmp != (double)0.0F) {
            return cmp;
         }
      }

      return (double)this.algebra$instances$ArraySupport$$signum(.MODULE$.array_length(x) - .MODULE$.array_length(y));
   }

   public boolean eqv$mZc$sp(final boolean[] x, final boolean[] y, final Eq ev) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && ev.eqv$mcZ$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean eqv$mBc$sp(final byte[] x, final byte[] y, final Eq ev) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && ev.eqv$mcB$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean eqv$mCc$sp(final char[] x, final char[] y, final Eq ev) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && ev.eqv$mcC$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean eqv$mDc$sp(final double[] x, final double[] y, final Eq ev) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && ev.eqv$mcD$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean eqv$mFc$sp(final float[] x, final float[] y, final Eq ev) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && ev.eqv$mcF$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean eqv$mIc$sp(final int[] x, final int[] y, final Eq ev) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && ev.eqv$mcI$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean eqv$mJc$sp(final long[] x, final long[] y, final Eq ev) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && ev.eqv$mcJ$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean eqv$mSc$sp(final short[] x, final short[] y, final Eq ev) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && ev.eqv$mcS$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public boolean eqv$mVc$sp(final BoxedUnit[] x, final BoxedUnit[] y, final Eq ev) {
      int i = 0;
      if (x.length != y.length) {
         return false;
      } else {
         while(i < x.length && i < y.length && ev.eqv$mcV$sp(x[i], y[i])) {
            ++i;
         }

         return i == x.length;
      }
   }

   public int compare$mZc$sp(final boolean[] x, final boolean[] y, final Order ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcZ$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public int compare$mBc$sp(final byte[] x, final byte[] y, final Order ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcB$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public int compare$mCc$sp(final char[] x, final char[] y, final Order ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcC$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public int compare$mDc$sp(final double[] x, final double[] y, final Order ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcD$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public int compare$mFc$sp(final float[] x, final float[] y, final Order ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcF$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public int compare$mIc$sp(final int[] x, final int[] y, final Order ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcI$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public int compare$mJc$sp(final long[] x, final long[] y, final Order ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcJ$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public int compare$mSc$sp(final short[] x, final short[] y, final Order ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcS$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public int compare$mVc$sp(final BoxedUnit[] x, final BoxedUnit[] y, final Order ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         int cmp = ev.compare$mcV$sp(x[i], y[i]);
         if (cmp != 0) {
            return cmp;
         }
      }

      return this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public double partialCompare$mZc$sp(final boolean[] x, final boolean[] y, final PartialOrder ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         double cmp = ev.partialCompare$mcZ$sp(x[i], y[i]);
         if (cmp != (double)0.0F) {
            return cmp;
         }
      }

      return (double)this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public double partialCompare$mBc$sp(final byte[] x, final byte[] y, final PartialOrder ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         double cmp = ev.partialCompare$mcB$sp(x[i], y[i]);
         if (cmp != (double)0.0F) {
            return cmp;
         }
      }

      return (double)this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public double partialCompare$mCc$sp(final char[] x, final char[] y, final PartialOrder ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         double cmp = ev.partialCompare$mcC$sp(x[i], y[i]);
         if (cmp != (double)0.0F) {
            return cmp;
         }
      }

      return (double)this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public double partialCompare$mDc$sp(final double[] x, final double[] y, final PartialOrder ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         double cmp = ev.partialCompare$mcD$sp(x[i], y[i]);
         if (cmp != (double)0.0F) {
            return cmp;
         }
      }

      return (double)this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public double partialCompare$mFc$sp(final float[] x, final float[] y, final PartialOrder ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         double cmp = ev.partialCompare$mcF$sp(x[i], y[i]);
         if (cmp != (double)0.0F) {
            return cmp;
         }
      }

      return (double)this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public double partialCompare$mIc$sp(final int[] x, final int[] y, final PartialOrder ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         double cmp = ev.partialCompare$mcI$sp(x[i], y[i]);
         if (cmp != (double)0.0F) {
            return cmp;
         }
      }

      return (double)this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public double partialCompare$mJc$sp(final long[] x, final long[] y, final PartialOrder ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         double cmp = ev.partialCompare$mcJ$sp(x[i], y[i]);
         if (cmp != (double)0.0F) {
            return cmp;
         }
      }

      return (double)this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public double partialCompare$mSc$sp(final short[] x, final short[] y, final PartialOrder ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         double cmp = ev.partialCompare$mcS$sp(x[i], y[i]);
         if (cmp != (double)0.0F) {
            return cmp;
         }
      }

      return (double)this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   public double partialCompare$mVc$sp(final BoxedUnit[] x, final BoxedUnit[] y, final PartialOrder ev) {
      for(int i = 0; i < x.length && i < y.length; ++i) {
         double cmp = ev.partialCompare$mcV$sp(x[i], y[i]);
         if (cmp != (double)0.0F) {
            return cmp;
         }
      }

      return (double)this.algebra$instances$ArraySupport$$signum(x.length - y.length);
   }

   private ArraySupport$() {
   }
}
