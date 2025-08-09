package spire.syntax.std;

import algebra.ring.AdditiveMonoid;
import algebra.ring.Field;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.Signed;
import cats.kernel.Monoid;
import cats.kernel.Order;
import scala.Function1;
import scala.Function2;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import spire.algebra.NRoot;
import spire.math.Searching$;
import spire.math.Selection$;
import spire.math.Sorting$;
import spire.random.Generator;

public final class ArrayOps$mcD$sp extends ArrayOps {
   public final double[] arr$mcD$sp;

   public double qsum(final AdditiveMonoid ev) {
      return this.qsum$mcD$sp(ev);
   }

   public double qsum$mcD$sp(final AdditiveMonoid ev) {
      double result = ev.zero$mcD$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcD$sp.length; ++index$macro$1) {
         result = ev.plus$mcD$sp(result, this.arr$mcD$sp[index$macro$1]);
      }

      return result;
   }

   public double qproduct(final MultiplicativeMonoid ev) {
      return this.qproduct$mcD$sp(ev);
   }

   public double qproduct$mcD$sp(final MultiplicativeMonoid ev) {
      double result = ev.one$mcD$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcD$sp.length; ++index$macro$1) {
         result = ev.times$mcD$sp(result, this.arr$mcD$sp[index$macro$1]);
      }

      return result;
   }

   public double qcombine(final Monoid ev) {
      return this.qcombine$mcD$sp(ev);
   }

   public double qcombine$mcD$sp(final Monoid ev) {
      double result = ev.empty$mcD$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcD$sp.length; ++index$macro$1) {
         result = ev.combine$mcD$sp(result, this.arr$mcD$sp[index$macro$1]);
      }

      return result;
   }

   public double qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return this.qnorm$mcD$sp(p, ev, s, nr);
   }

   public double qnorm$mcD$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      double result = ev.one$mcD$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcD$sp.length; ++index$macro$1) {
         result = ev.plus$mcD$sp(result, ev.pow$mcD$sp(s.abs$mcD$sp(this.arr$mcD$sp[index$macro$1]), p));
      }

      return nr.nroot$mcD$sp(result, p);
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcD$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcD$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      Object result = ev.one();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcD$sp.length; ++index$macro$1) {
         result = ev.plus(result, ev.pow(s.abs(f.apply(BoxesRunTime.boxToDouble(this.arr$mcD$sp[index$macro$1]))), p));
      }

      return nr.nroot(result, p);
   }

   public double qnormWith$mDc$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDcD$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcD$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      double result = ev.one$mcD$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcD$sp.length; ++index$macro$1) {
         result = ev.plus$mcD$sp(result, ev.pow$mcD$sp(s.abs$mcD$sp(f.apply$mcDD$sp(this.arr$mcD$sp[index$macro$1])), p));
      }

      return nr.nroot$mcD$sp(result, p);
   }

   public double qmin(final Order ev) {
      return this.qmin$mcD$sp(ev);
   }

   public double qmin$mcD$sp(final Order ev) {
      if (this.arr$mcD$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         double result = this.arr$mcD$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcD$sp.length; ++index$macro$1) {
            result = ev.min$mcD$sp(result, this.arr$mcD$sp[index$macro$1]);
         }

         return result;
      }
   }

   public double qmax(final Order ev) {
      return this.qmax$mcD$sp(ev);
   }

   public double qmax$mcD$sp(final Order ev) {
      if (this.arr$mcD$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         double result = this.arr$mcD$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcD$sp.length; ++index$macro$1) {
            result = ev.max$mcD$sp(result, this.arr$mcD$sp[index$macro$1]);
         }

         return result;
      }
   }

   public double qmean(final Field ev) {
      return this.qmean$mcD$sp(ev);
   }

   public double qmean$mcD$sp(final Field ev) {
      if (this.arr$mcD$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         double result = ev.zero$mcD$sp();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcD$sp.length; ++index$macro$1) {
            result = ev.plus$mcD$sp(ev.div$mcD$sp(ev.times$mcD$sp(result, ev.fromInt$mcD$sp(index$macro$1)), ev.fromInt$mcD$sp(index$macro$1 + 1)), ev.div$mcD$sp(this.arr$mcD$sp[index$macro$1], ev.fromInt$mcD$sp(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcD$sp(f, ev);
   }

   public Object qmeanWith$mcD$sp(final Function1 f, final Field ev) {
      if (this.arr$mcD$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         Object result = ev.zero();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcD$sp.length; ++index$macro$1) {
            result = ev.plus(ev.div(ev.times(result, ev.fromInt(index$macro$1)), ev.fromInt(index$macro$1 + 1)), ev.div(f.apply(BoxesRunTime.boxToDouble(this.arr$mcD$sp[index$macro$1])), ev.fromInt(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public double qmeanWith$mDc$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDcD$sp(f, ev);
   }

   public double qmeanWith$mDcD$sp(final Function1 f, final Field ev) {
      if (this.arr$mcD$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         double result = ev.zero$mcD$sp();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcD$sp.length; ++index$macro$1) {
            result = ev.plus$mcD$sp(ev.div$mcD$sp(ev.times$mcD$sp(result, ev.fromInt$mcD$sp(index$macro$1)), ev.fromInt$mcD$sp(index$macro$1 + 1)), ev.div$mcD$sp(f.apply$mcDD$sp(this.arr$mcD$sp[index$macro$1]), ev.fromInt$mcD$sp(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public int qsearch(final double a, final Order ev) {
      return this.qsearch$mcD$sp(a, ev);
   }

   public int qsearch$mcD$sp(final double a, final Order ev) {
      return Searching$.MODULE$.search$mDc$sp(this.arr$mcD$sp, a, ev);
   }

   public void qsort(final Order ev, final ClassTag ct) {
      this.qsort$mcD$sp(ev, ct);
   }

   public void qsort$mcD$sp(final Order ev, final ClassTag ct) {
      Sorting$.MODULE$.sort$mDc$sp(this.arr$mcD$sp, ev, ct);
   }

   public void qsortBy(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mcD$sp(f, ev, ct);
   }

   public void qsortBy$mcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort$mDc$sp(this.arr$mcD$sp, ord, ct);
   }

   public void qsortBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZcD$sp(f, ev, ct);
   }

   public void qsortBy$mZcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDZc$sp(f, ev);
      Sorting$.MODULE$.sort$mDc$sp(this.arr$mcD$sp, ord, ct);
   }

   public void qsortBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBcD$sp(f, ev, ct);
   }

   public void qsortBy$mBcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDBc$sp(f, ev);
      Sorting$.MODULE$.sort$mDc$sp(this.arr$mcD$sp, ord, ct);
   }

   public void qsortBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCcD$sp(f, ev, ct);
   }

   public void qsortBy$mCcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDCc$sp(f, ev);
      Sorting$.MODULE$.sort$mDc$sp(this.arr$mcD$sp, ord, ct);
   }

   public void qsortBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDcD$sp(f, ev, ct);
   }

   public void qsortBy$mDcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDDc$sp(f, ev);
      Sorting$.MODULE$.sort$mDc$sp(this.arr$mcD$sp, ord, ct);
   }

   public void qsortBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFcD$sp(f, ev, ct);
   }

   public void qsortBy$mFcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDFc$sp(f, ev);
      Sorting$.MODULE$.sort$mDc$sp(this.arr$mcD$sp, ord, ct);
   }

   public void qsortBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIcD$sp(f, ev, ct);
   }

   public void qsortBy$mIcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDIc$sp(f, ev);
      Sorting$.MODULE$.sort$mDc$sp(this.arr$mcD$sp, ord, ct);
   }

   public void qsortBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJcD$sp(f, ev, ct);
   }

   public void qsortBy$mJcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDJc$sp(f, ev);
      Sorting$.MODULE$.sort$mDc$sp(this.arr$mcD$sp, ord, ct);
   }

   public void qsortBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mScD$sp(f, ev, ct);
   }

   public void qsortBy$mScD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDSc$sp(f, ev);
      Sorting$.MODULE$.sort$mDc$sp(this.arr$mcD$sp, ord, ct);
   }

   public void qsortBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVcD$sp(f, ev, ct);
   }

   public void qsortBy$mVcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDVc$sp(f, ev);
      Sorting$.MODULE$.sort$mDc$sp(this.arr$mcD$sp, ord, ct);
   }

   public void qsortWith(final Function2 f, final ClassTag ct) {
      this.qsortWith$mcD$sp(f, ct);
   }

   public void qsortWith$mcD$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mDc$sp(f);
      Sorting$.MODULE$.sort$mDc$sp(this.arr$mcD$sp, ord, ct);
   }

   public double[] qsorted(final Order ev, final ClassTag ct) {
      return this.qsorted$mcD$sp(ev, ct);
   }

   public double[] qsorted$mcD$sp(final Order ev, final ClassTag ct) {
      double[] arr2 = (double[])this.arr$mcD$sp.clone();
      Sorting$.MODULE$.sort$mDc$sp(arr2, ev, ct);
      return arr2;
   }

   public double[] qsortedBy(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mcD$sp(f, ev, ct);
   }

   public double[] qsortedBy$mcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      double[] arr2 = (double[])this.arr$mcD$sp.clone();
      Sorting$.MODULE$.sort$mDc$sp(arr2, ord, ct);
      return arr2;
   }

   public double[] qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mZcD$sp(f, ev, ct);
   }

   public double[] qsortedBy$mZcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDZc$sp(f, ev);
      double[] arr2 = (double[])this.arr$mcD$sp.clone();
      Sorting$.MODULE$.sort$mDc$sp(arr2, ord, ct);
      return arr2;
   }

   public double[] qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mBcD$sp(f, ev, ct);
   }

   public double[] qsortedBy$mBcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDBc$sp(f, ev);
      double[] arr2 = (double[])this.arr$mcD$sp.clone();
      Sorting$.MODULE$.sort$mDc$sp(arr2, ord, ct);
      return arr2;
   }

   public double[] qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mCcD$sp(f, ev, ct);
   }

   public double[] qsortedBy$mCcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDCc$sp(f, ev);
      double[] arr2 = (double[])this.arr$mcD$sp.clone();
      Sorting$.MODULE$.sort$mDc$sp(arr2, ord, ct);
      return arr2;
   }

   public double[] qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mDcD$sp(f, ev, ct);
   }

   public double[] qsortedBy$mDcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDDc$sp(f, ev);
      double[] arr2 = (double[])this.arr$mcD$sp.clone();
      Sorting$.MODULE$.sort$mDc$sp(arr2, ord, ct);
      return arr2;
   }

   public double[] qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mFcD$sp(f, ev, ct);
   }

   public double[] qsortedBy$mFcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDFc$sp(f, ev);
      double[] arr2 = (double[])this.arr$mcD$sp.clone();
      Sorting$.MODULE$.sort$mDc$sp(arr2, ord, ct);
      return arr2;
   }

   public double[] qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mIcD$sp(f, ev, ct);
   }

   public double[] qsortedBy$mIcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDIc$sp(f, ev);
      double[] arr2 = (double[])this.arr$mcD$sp.clone();
      Sorting$.MODULE$.sort$mDc$sp(arr2, ord, ct);
      return arr2;
   }

   public double[] qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mJcD$sp(f, ev, ct);
   }

   public double[] qsortedBy$mJcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDJc$sp(f, ev);
      double[] arr2 = (double[])this.arr$mcD$sp.clone();
      Sorting$.MODULE$.sort$mDc$sp(arr2, ord, ct);
      return arr2;
   }

   public double[] qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mScD$sp(f, ev, ct);
   }

   public double[] qsortedBy$mScD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDSc$sp(f, ev);
      double[] arr2 = (double[])this.arr$mcD$sp.clone();
      Sorting$.MODULE$.sort$mDc$sp(arr2, ord, ct);
      return arr2;
   }

   public double[] qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mVcD$sp(f, ev, ct);
   }

   public double[] qsortedBy$mVcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDVc$sp(f, ev);
      double[] arr2 = (double[])this.arr$mcD$sp.clone();
      Sorting$.MODULE$.sort$mDc$sp(arr2, ord, ct);
      return arr2;
   }

   public double[] qsortedWith(final Function2 f, final ClassTag ct) {
      return this.qsortedWith$mcD$sp(f, ct);
   }

   public double[] qsortedWith$mcD$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mDc$sp(f);
      double[] arr2 = (double[])this.arr$mcD$sp.clone();
      Sorting$.MODULE$.sort$mDc$sp(arr2, ord, ct);
      return arr2;
   }

   public void qselect(final int k, final Order ev, final ClassTag ct) {
      this.qselect$mcD$sp(k, ev, ct);
   }

   public void qselect$mcD$sp(final int k, final Order ev, final ClassTag ct) {
      Selection$.MODULE$.select$mDc$sp(this.arr$mcD$sp, k, ev, ct);
   }

   public double[] qselected(final int k, final Order ev, final ClassTag ct) {
      return this.qselected$mcD$sp(k, ev, ct);
   }

   public double[] qselected$mcD$sp(final int k, final Order ev, final ClassTag ct) {
      double[] arr2 = (double[])this.arr$mcD$sp.clone();
      Selection$.MODULE$.select$mDc$sp(arr2, k, ev, ct);
      return arr2;
   }

   public double[] qshuffled(final Generator gen) {
      return this.qshuffled$mcD$sp(gen);
   }

   public double[] qshuffled$mcD$sp(final Generator gen) {
      double[] arr2 = (double[])this.arr$mcD$sp.clone();
      gen.shuffle$mDc$sp(arr2, gen);
      return arr2;
   }

   public double[] qsampled(final int n, final Generator gen, final ClassTag ct) {
      return this.qsampled$mcD$sp(n, gen, ct);
   }

   public double[] qsampled$mcD$sp(final int n, final Generator gen, final ClassTag ct) {
      return gen.sampleFromArray$mDc$sp(this.arr$mcD$sp, n, ct, gen);
   }

   public ArrayOps$mcD$sp(final double[] arr$mcD$sp) {
      super(arr$mcD$sp);
      this.arr$mcD$sp = arr$mcD$sp;
   }
}
