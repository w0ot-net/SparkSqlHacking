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

public final class ArrayOps$mcF$sp extends ArrayOps {
   public final float[] arr$mcF$sp;

   public float qsum(final AdditiveMonoid ev) {
      return this.qsum$mcF$sp(ev);
   }

   public float qsum$mcF$sp(final AdditiveMonoid ev) {
      float result = ev.zero$mcF$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcF$sp.length; ++index$macro$1) {
         result = ev.plus$mcF$sp(result, this.arr$mcF$sp[index$macro$1]);
      }

      return result;
   }

   public float qproduct(final MultiplicativeMonoid ev) {
      return this.qproduct$mcF$sp(ev);
   }

   public float qproduct$mcF$sp(final MultiplicativeMonoid ev) {
      float result = ev.one$mcF$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcF$sp.length; ++index$macro$1) {
         result = ev.times$mcF$sp(result, this.arr$mcF$sp[index$macro$1]);
      }

      return result;
   }

   public float qcombine(final Monoid ev) {
      return this.qcombine$mcF$sp(ev);
   }

   public float qcombine$mcF$sp(final Monoid ev) {
      float result = ev.empty$mcF$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcF$sp.length; ++index$macro$1) {
         result = ev.combine$mcF$sp(result, this.arr$mcF$sp[index$macro$1]);
      }

      return result;
   }

   public float qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return this.qnorm$mcF$sp(p, ev, s, nr);
   }

   public float qnorm$mcF$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      float result = ev.one$mcF$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcF$sp.length; ++index$macro$1) {
         result = ev.plus$mcF$sp(result, ev.pow$mcF$sp(s.abs$mcF$sp(this.arr$mcF$sp[index$macro$1]), p));
      }

      return nr.nroot$mcF$sp(result, p);
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcF$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcF$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      Object result = ev.one();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcF$sp.length; ++index$macro$1) {
         result = ev.plus(result, ev.pow(s.abs(f.apply(BoxesRunTime.boxToFloat(this.arr$mcF$sp[index$macro$1]))), p));
      }

      return nr.nroot(result, p);
   }

   public double qnormWith$mDc$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDcF$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcF$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      double result = ev.one$mcD$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcF$sp.length; ++index$macro$1) {
         result = ev.plus$mcD$sp(result, ev.pow$mcD$sp(s.abs$mcD$sp(f.apply$mcDF$sp(this.arr$mcF$sp[index$macro$1])), p));
      }

      return nr.nroot$mcD$sp(result, p);
   }

   public float qmin(final Order ev) {
      return this.qmin$mcF$sp(ev);
   }

   public float qmin$mcF$sp(final Order ev) {
      if (this.arr$mcF$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         float result = this.arr$mcF$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcF$sp.length; ++index$macro$1) {
            result = ev.min$mcF$sp(result, this.arr$mcF$sp[index$macro$1]);
         }

         return result;
      }
   }

   public float qmax(final Order ev) {
      return this.qmax$mcF$sp(ev);
   }

   public float qmax$mcF$sp(final Order ev) {
      if (this.arr$mcF$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         float result = this.arr$mcF$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcF$sp.length; ++index$macro$1) {
            result = ev.max$mcF$sp(result, this.arr$mcF$sp[index$macro$1]);
         }

         return result;
      }
   }

   public float qmean(final Field ev) {
      return this.qmean$mcF$sp(ev);
   }

   public float qmean$mcF$sp(final Field ev) {
      if (this.arr$mcF$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         float result = ev.zero$mcF$sp();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcF$sp.length; ++index$macro$1) {
            result = ev.plus$mcF$sp(ev.div$mcF$sp(ev.times$mcF$sp(result, ev.fromInt$mcF$sp(index$macro$1)), ev.fromInt$mcF$sp(index$macro$1 + 1)), ev.div$mcF$sp(this.arr$mcF$sp[index$macro$1], ev.fromInt$mcF$sp(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcF$sp(f, ev);
   }

   public Object qmeanWith$mcF$sp(final Function1 f, final Field ev) {
      if (this.arr$mcF$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         Object result = ev.zero();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcF$sp.length; ++index$macro$1) {
            result = ev.plus(ev.div(ev.times(result, ev.fromInt(index$macro$1)), ev.fromInt(index$macro$1 + 1)), ev.div(f.apply(BoxesRunTime.boxToFloat(this.arr$mcF$sp[index$macro$1])), ev.fromInt(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public double qmeanWith$mDc$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDcF$sp(f, ev);
   }

   public double qmeanWith$mDcF$sp(final Function1 f, final Field ev) {
      if (this.arr$mcF$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         double result = ev.zero$mcD$sp();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcF$sp.length; ++index$macro$1) {
            result = ev.plus$mcD$sp(ev.div$mcD$sp(ev.times$mcD$sp(result, ev.fromInt$mcD$sp(index$macro$1)), ev.fromInt$mcD$sp(index$macro$1 + 1)), ev.div$mcD$sp(f.apply$mcDF$sp(this.arr$mcF$sp[index$macro$1]), ev.fromInt$mcD$sp(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public int qsearch(final float a, final Order ev) {
      return this.qsearch$mcF$sp(a, ev);
   }

   public int qsearch$mcF$sp(final float a, final Order ev) {
      return Searching$.MODULE$.search$mFc$sp(this.arr$mcF$sp, a, ev);
   }

   public void qsort(final Order ev, final ClassTag ct) {
      this.qsort$mcF$sp(ev, ct);
   }

   public void qsort$mcF$sp(final Order ev, final ClassTag ct) {
      Sorting$.MODULE$.sort$mFc$sp(this.arr$mcF$sp, ev, ct);
   }

   public void qsortBy(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mcF$sp(f, ev, ct);
   }

   public void qsortBy$mcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort$mFc$sp(this.arr$mcF$sp, ord, ct);
   }

   public void qsortBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZcF$sp(f, ev, ct);
   }

   public void qsortBy$mZcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFZc$sp(f, ev);
      Sorting$.MODULE$.sort$mFc$sp(this.arr$mcF$sp, ord, ct);
   }

   public void qsortBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBcF$sp(f, ev, ct);
   }

   public void qsortBy$mBcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFBc$sp(f, ev);
      Sorting$.MODULE$.sort$mFc$sp(this.arr$mcF$sp, ord, ct);
   }

   public void qsortBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCcF$sp(f, ev, ct);
   }

   public void qsortBy$mCcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFCc$sp(f, ev);
      Sorting$.MODULE$.sort$mFc$sp(this.arr$mcF$sp, ord, ct);
   }

   public void qsortBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDcF$sp(f, ev, ct);
   }

   public void qsortBy$mDcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFDc$sp(f, ev);
      Sorting$.MODULE$.sort$mFc$sp(this.arr$mcF$sp, ord, ct);
   }

   public void qsortBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFcF$sp(f, ev, ct);
   }

   public void qsortBy$mFcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFFc$sp(f, ev);
      Sorting$.MODULE$.sort$mFc$sp(this.arr$mcF$sp, ord, ct);
   }

   public void qsortBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIcF$sp(f, ev, ct);
   }

   public void qsortBy$mIcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFIc$sp(f, ev);
      Sorting$.MODULE$.sort$mFc$sp(this.arr$mcF$sp, ord, ct);
   }

   public void qsortBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJcF$sp(f, ev, ct);
   }

   public void qsortBy$mJcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFJc$sp(f, ev);
      Sorting$.MODULE$.sort$mFc$sp(this.arr$mcF$sp, ord, ct);
   }

   public void qsortBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mScF$sp(f, ev, ct);
   }

   public void qsortBy$mScF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFSc$sp(f, ev);
      Sorting$.MODULE$.sort$mFc$sp(this.arr$mcF$sp, ord, ct);
   }

   public void qsortBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVcF$sp(f, ev, ct);
   }

   public void qsortBy$mVcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFVc$sp(f, ev);
      Sorting$.MODULE$.sort$mFc$sp(this.arr$mcF$sp, ord, ct);
   }

   public void qsortWith(final Function2 f, final ClassTag ct) {
      this.qsortWith$mcF$sp(f, ct);
   }

   public void qsortWith$mcF$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mFc$sp(f);
      Sorting$.MODULE$.sort$mFc$sp(this.arr$mcF$sp, ord, ct);
   }

   public float[] qsorted(final Order ev, final ClassTag ct) {
      return this.qsorted$mcF$sp(ev, ct);
   }

   public float[] qsorted$mcF$sp(final Order ev, final ClassTag ct) {
      float[] arr2 = (float[])this.arr$mcF$sp.clone();
      Sorting$.MODULE$.sort$mFc$sp(arr2, ev, ct);
      return arr2;
   }

   public float[] qsortedBy(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mcF$sp(f, ev, ct);
   }

   public float[] qsortedBy$mcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      float[] arr2 = (float[])this.arr$mcF$sp.clone();
      Sorting$.MODULE$.sort$mFc$sp(arr2, ord, ct);
      return arr2;
   }

   public float[] qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mZcF$sp(f, ev, ct);
   }

   public float[] qsortedBy$mZcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFZc$sp(f, ev);
      float[] arr2 = (float[])this.arr$mcF$sp.clone();
      Sorting$.MODULE$.sort$mFc$sp(arr2, ord, ct);
      return arr2;
   }

   public float[] qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mBcF$sp(f, ev, ct);
   }

   public float[] qsortedBy$mBcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFBc$sp(f, ev);
      float[] arr2 = (float[])this.arr$mcF$sp.clone();
      Sorting$.MODULE$.sort$mFc$sp(arr2, ord, ct);
      return arr2;
   }

   public float[] qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mCcF$sp(f, ev, ct);
   }

   public float[] qsortedBy$mCcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFCc$sp(f, ev);
      float[] arr2 = (float[])this.arr$mcF$sp.clone();
      Sorting$.MODULE$.sort$mFc$sp(arr2, ord, ct);
      return arr2;
   }

   public float[] qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mDcF$sp(f, ev, ct);
   }

   public float[] qsortedBy$mDcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFDc$sp(f, ev);
      float[] arr2 = (float[])this.arr$mcF$sp.clone();
      Sorting$.MODULE$.sort$mFc$sp(arr2, ord, ct);
      return arr2;
   }

   public float[] qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mFcF$sp(f, ev, ct);
   }

   public float[] qsortedBy$mFcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFFc$sp(f, ev);
      float[] arr2 = (float[])this.arr$mcF$sp.clone();
      Sorting$.MODULE$.sort$mFc$sp(arr2, ord, ct);
      return arr2;
   }

   public float[] qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mIcF$sp(f, ev, ct);
   }

   public float[] qsortedBy$mIcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFIc$sp(f, ev);
      float[] arr2 = (float[])this.arr$mcF$sp.clone();
      Sorting$.MODULE$.sort$mFc$sp(arr2, ord, ct);
      return arr2;
   }

   public float[] qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mJcF$sp(f, ev, ct);
   }

   public float[] qsortedBy$mJcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFJc$sp(f, ev);
      float[] arr2 = (float[])this.arr$mcF$sp.clone();
      Sorting$.MODULE$.sort$mFc$sp(arr2, ord, ct);
      return arr2;
   }

   public float[] qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mScF$sp(f, ev, ct);
   }

   public float[] qsortedBy$mScF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFSc$sp(f, ev);
      float[] arr2 = (float[])this.arr$mcF$sp.clone();
      Sorting$.MODULE$.sort$mFc$sp(arr2, ord, ct);
      return arr2;
   }

   public float[] qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mVcF$sp(f, ev, ct);
   }

   public float[] qsortedBy$mVcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFVc$sp(f, ev);
      float[] arr2 = (float[])this.arr$mcF$sp.clone();
      Sorting$.MODULE$.sort$mFc$sp(arr2, ord, ct);
      return arr2;
   }

   public float[] qsortedWith(final Function2 f, final ClassTag ct) {
      return this.qsortedWith$mcF$sp(f, ct);
   }

   public float[] qsortedWith$mcF$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mFc$sp(f);
      float[] arr2 = (float[])this.arr$mcF$sp.clone();
      Sorting$.MODULE$.sort$mFc$sp(arr2, ord, ct);
      return arr2;
   }

   public void qselect(final int k, final Order ev, final ClassTag ct) {
      this.qselect$mcF$sp(k, ev, ct);
   }

   public void qselect$mcF$sp(final int k, final Order ev, final ClassTag ct) {
      Selection$.MODULE$.select$mFc$sp(this.arr$mcF$sp, k, ev, ct);
   }

   public float[] qselected(final int k, final Order ev, final ClassTag ct) {
      return this.qselected$mcF$sp(k, ev, ct);
   }

   public float[] qselected$mcF$sp(final int k, final Order ev, final ClassTag ct) {
      float[] arr2 = (float[])this.arr$mcF$sp.clone();
      Selection$.MODULE$.select$mFc$sp(arr2, k, ev, ct);
      return arr2;
   }

   public float[] qshuffled(final Generator gen) {
      return this.qshuffled$mcF$sp(gen);
   }

   public float[] qshuffled$mcF$sp(final Generator gen) {
      float[] arr2 = (float[])this.arr$mcF$sp.clone();
      gen.shuffle$mFc$sp(arr2, gen);
      return arr2;
   }

   public float[] qsampled(final int n, final Generator gen, final ClassTag ct) {
      return this.qsampled$mcF$sp(n, gen, ct);
   }

   public float[] qsampled$mcF$sp(final int n, final Generator gen, final ClassTag ct) {
      return gen.sampleFromArray$mFc$sp(this.arr$mcF$sp, n, ct, gen);
   }

   public ArrayOps$mcF$sp(final float[] arr$mcF$sp) {
      super(arr$mcF$sp);
      this.arr$mcF$sp = arr$mcF$sp;
   }
}
