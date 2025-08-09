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

public final class ArrayOps$mcZ$sp extends ArrayOps {
   public final boolean[] arr$mcZ$sp;

   public boolean qsum(final AdditiveMonoid ev) {
      return this.qsum$mcZ$sp(ev);
   }

   public boolean qsum$mcZ$sp(final AdditiveMonoid ev) {
      boolean result = BoxesRunTime.unboxToBoolean(ev.zero());

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcZ$sp.length; ++index$macro$1) {
         result = BoxesRunTime.unboxToBoolean(ev.plus(BoxesRunTime.boxToBoolean(result), BoxesRunTime.boxToBoolean(this.arr$mcZ$sp[index$macro$1])));
      }

      return result;
   }

   public boolean qproduct(final MultiplicativeMonoid ev) {
      return this.qproduct$mcZ$sp(ev);
   }

   public boolean qproduct$mcZ$sp(final MultiplicativeMonoid ev) {
      boolean result = BoxesRunTime.unboxToBoolean(ev.one());

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcZ$sp.length; ++index$macro$1) {
         result = BoxesRunTime.unboxToBoolean(ev.times(BoxesRunTime.boxToBoolean(result), BoxesRunTime.boxToBoolean(this.arr$mcZ$sp[index$macro$1])));
      }

      return result;
   }

   public boolean qcombine(final Monoid ev) {
      return this.qcombine$mcZ$sp(ev);
   }

   public boolean qcombine$mcZ$sp(final Monoid ev) {
      boolean result = BoxesRunTime.unboxToBoolean(ev.empty());

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcZ$sp.length; ++index$macro$1) {
         result = BoxesRunTime.unboxToBoolean(ev.combine(BoxesRunTime.boxToBoolean(result), BoxesRunTime.boxToBoolean(this.arr$mcZ$sp[index$macro$1])));
      }

      return result;
   }

   public boolean qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return this.qnorm$mcZ$sp(p, ev, s, nr);
   }

   public boolean qnorm$mcZ$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      boolean result = BoxesRunTime.unboxToBoolean(ev.one());

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcZ$sp.length; ++index$macro$1) {
         result = BoxesRunTime.unboxToBoolean(ev.plus(BoxesRunTime.boxToBoolean(result), BoxesRunTime.boxToBoolean(BoxesRunTime.unboxToBoolean(ev.pow(BoxesRunTime.boxToBoolean(BoxesRunTime.unboxToBoolean(s.abs(BoxesRunTime.boxToBoolean(this.arr$mcZ$sp[index$macro$1])))), p)))));
      }

      return BoxesRunTime.unboxToBoolean(nr.nroot(BoxesRunTime.boxToBoolean(result), p));
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcZ$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcZ$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      Object result = ev.one();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcZ$sp.length; ++index$macro$1) {
         result = ev.plus(result, ev.pow(s.abs(f.apply(BoxesRunTime.boxToBoolean(this.arr$mcZ$sp[index$macro$1]))), p));
      }

      return nr.nroot(result, p);
   }

   public double qnormWith$mDc$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDcZ$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcZ$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      double result = ev.one$mcD$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcZ$sp.length; ++index$macro$1) {
         result = ev.plus$mcD$sp(result, ev.pow$mcD$sp(s.abs$mcD$sp(BoxesRunTime.unboxToDouble(f.apply(BoxesRunTime.boxToBoolean(this.arr$mcZ$sp[index$macro$1])))), p));
      }

      return nr.nroot$mcD$sp(result, p);
   }

   public boolean qmin(final Order ev) {
      return this.qmin$mcZ$sp(ev);
   }

   public boolean qmin$mcZ$sp(final Order ev) {
      if (this.arr$mcZ$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         boolean result = this.arr$mcZ$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcZ$sp.length; ++index$macro$1) {
            result = ev.min$mcZ$sp(result, this.arr$mcZ$sp[index$macro$1]);
         }

         return result;
      }
   }

   public boolean qmax(final Order ev) {
      return this.qmax$mcZ$sp(ev);
   }

   public boolean qmax$mcZ$sp(final Order ev) {
      if (this.arr$mcZ$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         boolean result = this.arr$mcZ$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcZ$sp.length; ++index$macro$1) {
            result = ev.max$mcZ$sp(result, this.arr$mcZ$sp[index$macro$1]);
         }

         return result;
      }
   }

   public boolean qmean(final Field ev) {
      return this.qmean$mcZ$sp(ev);
   }

   public boolean qmean$mcZ$sp(final Field ev) {
      if (this.arr$mcZ$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         boolean result = BoxesRunTime.unboxToBoolean(ev.zero());

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcZ$sp.length; ++index$macro$1) {
            result = BoxesRunTime.unboxToBoolean(ev.plus(BoxesRunTime.boxToBoolean(BoxesRunTime.unboxToBoolean(ev.div(BoxesRunTime.boxToBoolean(BoxesRunTime.unboxToBoolean(ev.times(BoxesRunTime.boxToBoolean(result), ev.fromInt(index$macro$1)))), ev.fromInt(index$macro$1 + 1)))), BoxesRunTime.boxToBoolean(BoxesRunTime.unboxToBoolean(ev.div(BoxesRunTime.boxToBoolean(this.arr$mcZ$sp[index$macro$1]), ev.fromInt(index$macro$1 + 1))))));
         }

         return result;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcZ$sp(f, ev);
   }

   public Object qmeanWith$mcZ$sp(final Function1 f, final Field ev) {
      if (this.arr$mcZ$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         Object result = ev.zero();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcZ$sp.length; ++index$macro$1) {
            result = ev.plus(ev.div(ev.times(result, ev.fromInt(index$macro$1)), ev.fromInt(index$macro$1 + 1)), ev.div(f.apply(BoxesRunTime.boxToBoolean(this.arr$mcZ$sp[index$macro$1])), ev.fromInt(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public double qmeanWith$mDc$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDcZ$sp(f, ev);
   }

   public double qmeanWith$mDcZ$sp(final Function1 f, final Field ev) {
      if (this.arr$mcZ$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         double result = ev.zero$mcD$sp();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcZ$sp.length; ++index$macro$1) {
            result = ev.plus$mcD$sp(ev.div$mcD$sp(ev.times$mcD$sp(result, ev.fromInt$mcD$sp(index$macro$1)), ev.fromInt$mcD$sp(index$macro$1 + 1)), ev.div$mcD$sp(BoxesRunTime.unboxToDouble(f.apply(BoxesRunTime.boxToBoolean(this.arr$mcZ$sp[index$macro$1]))), ev.fromInt$mcD$sp(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public int qsearch(final boolean a, final Order ev) {
      return this.qsearch$mcZ$sp(a, ev);
   }

   public int qsearch$mcZ$sp(final boolean a, final Order ev) {
      return Searching$.MODULE$.search$mZc$sp(this.arr$mcZ$sp, a, ev);
   }

   public void qsort(final Order ev, final ClassTag ct) {
      this.qsort$mcZ$sp(ev, ct);
   }

   public void qsort$mcZ$sp(final Order ev, final ClassTag ct) {
      Sorting$.MODULE$.sort$mZc$sp(this.arr$mcZ$sp, ev, ct);
   }

   public void qsortBy(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mcZ$sp(f, ev, ct);
   }

   public void qsortBy$mcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort$mZc$sp(this.arr$mcZ$sp, ord, ct);
   }

   public void qsortBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZcZ$sp(f, ev, ct);
   }

   public void qsortBy$mZcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZZc$sp(f, ev);
      Sorting$.MODULE$.sort$mZc$sp(this.arr$mcZ$sp, ord, ct);
   }

   public void qsortBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBcZ$sp(f, ev, ct);
   }

   public void qsortBy$mBcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZBc$sp(f, ev);
      Sorting$.MODULE$.sort$mZc$sp(this.arr$mcZ$sp, ord, ct);
   }

   public void qsortBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCcZ$sp(f, ev, ct);
   }

   public void qsortBy$mCcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZCc$sp(f, ev);
      Sorting$.MODULE$.sort$mZc$sp(this.arr$mcZ$sp, ord, ct);
   }

   public void qsortBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDcZ$sp(f, ev, ct);
   }

   public void qsortBy$mDcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZDc$sp(f, ev);
      Sorting$.MODULE$.sort$mZc$sp(this.arr$mcZ$sp, ord, ct);
   }

   public void qsortBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFcZ$sp(f, ev, ct);
   }

   public void qsortBy$mFcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZFc$sp(f, ev);
      Sorting$.MODULE$.sort$mZc$sp(this.arr$mcZ$sp, ord, ct);
   }

   public void qsortBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIcZ$sp(f, ev, ct);
   }

   public void qsortBy$mIcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZIc$sp(f, ev);
      Sorting$.MODULE$.sort$mZc$sp(this.arr$mcZ$sp, ord, ct);
   }

   public void qsortBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJcZ$sp(f, ev, ct);
   }

   public void qsortBy$mJcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZJc$sp(f, ev);
      Sorting$.MODULE$.sort$mZc$sp(this.arr$mcZ$sp, ord, ct);
   }

   public void qsortBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mScZ$sp(f, ev, ct);
   }

   public void qsortBy$mScZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZSc$sp(f, ev);
      Sorting$.MODULE$.sort$mZc$sp(this.arr$mcZ$sp, ord, ct);
   }

   public void qsortBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVcZ$sp(f, ev, ct);
   }

   public void qsortBy$mVcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZVc$sp(f, ev);
      Sorting$.MODULE$.sort$mZc$sp(this.arr$mcZ$sp, ord, ct);
   }

   public void qsortWith(final Function2 f, final ClassTag ct) {
      this.qsortWith$mcZ$sp(f, ct);
   }

   public void qsortWith$mcZ$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mZc$sp(f);
      Sorting$.MODULE$.sort$mZc$sp(this.arr$mcZ$sp, ord, ct);
   }

   public boolean[] qsorted(final Order ev, final ClassTag ct) {
      return this.qsorted$mcZ$sp(ev, ct);
   }

   public boolean[] qsorted$mcZ$sp(final Order ev, final ClassTag ct) {
      boolean[] arr2 = (boolean[])this.arr$mcZ$sp.clone();
      Sorting$.MODULE$.sort$mZc$sp(arr2, ev, ct);
      return arr2;
   }

   public boolean[] qsortedBy(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mcZ$sp(f, ev, ct);
   }

   public boolean[] qsortedBy$mcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      boolean[] arr2 = (boolean[])this.arr$mcZ$sp.clone();
      Sorting$.MODULE$.sort$mZc$sp(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mZcZ$sp(f, ev, ct);
   }

   public boolean[] qsortedBy$mZcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZZc$sp(f, ev);
      boolean[] arr2 = (boolean[])this.arr$mcZ$sp.clone();
      Sorting$.MODULE$.sort$mZc$sp(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mBcZ$sp(f, ev, ct);
   }

   public boolean[] qsortedBy$mBcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZBc$sp(f, ev);
      boolean[] arr2 = (boolean[])this.arr$mcZ$sp.clone();
      Sorting$.MODULE$.sort$mZc$sp(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mCcZ$sp(f, ev, ct);
   }

   public boolean[] qsortedBy$mCcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZCc$sp(f, ev);
      boolean[] arr2 = (boolean[])this.arr$mcZ$sp.clone();
      Sorting$.MODULE$.sort$mZc$sp(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mDcZ$sp(f, ev, ct);
   }

   public boolean[] qsortedBy$mDcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZDc$sp(f, ev);
      boolean[] arr2 = (boolean[])this.arr$mcZ$sp.clone();
      Sorting$.MODULE$.sort$mZc$sp(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mFcZ$sp(f, ev, ct);
   }

   public boolean[] qsortedBy$mFcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZFc$sp(f, ev);
      boolean[] arr2 = (boolean[])this.arr$mcZ$sp.clone();
      Sorting$.MODULE$.sort$mZc$sp(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mIcZ$sp(f, ev, ct);
   }

   public boolean[] qsortedBy$mIcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZIc$sp(f, ev);
      boolean[] arr2 = (boolean[])this.arr$mcZ$sp.clone();
      Sorting$.MODULE$.sort$mZc$sp(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mJcZ$sp(f, ev, ct);
   }

   public boolean[] qsortedBy$mJcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZJc$sp(f, ev);
      boolean[] arr2 = (boolean[])this.arr$mcZ$sp.clone();
      Sorting$.MODULE$.sort$mZc$sp(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mScZ$sp(f, ev, ct);
   }

   public boolean[] qsortedBy$mScZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZSc$sp(f, ev);
      boolean[] arr2 = (boolean[])this.arr$mcZ$sp.clone();
      Sorting$.MODULE$.sort$mZc$sp(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mVcZ$sp(f, ev, ct);
   }

   public boolean[] qsortedBy$mVcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZVc$sp(f, ev);
      boolean[] arr2 = (boolean[])this.arr$mcZ$sp.clone();
      Sorting$.MODULE$.sort$mZc$sp(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedWith(final Function2 f, final ClassTag ct) {
      return this.qsortedWith$mcZ$sp(f, ct);
   }

   public boolean[] qsortedWith$mcZ$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mZc$sp(f);
      boolean[] arr2 = (boolean[])this.arr$mcZ$sp.clone();
      Sorting$.MODULE$.sort$mZc$sp(arr2, ord, ct);
      return arr2;
   }

   public void qselect(final int k, final Order ev, final ClassTag ct) {
      this.qselect$mcZ$sp(k, ev, ct);
   }

   public void qselect$mcZ$sp(final int k, final Order ev, final ClassTag ct) {
      Selection$.MODULE$.select$mZc$sp(this.arr$mcZ$sp, k, ev, ct);
   }

   public boolean[] qselected(final int k, final Order ev, final ClassTag ct) {
      return this.qselected$mcZ$sp(k, ev, ct);
   }

   public boolean[] qselected$mcZ$sp(final int k, final Order ev, final ClassTag ct) {
      boolean[] arr2 = (boolean[])this.arr$mcZ$sp.clone();
      Selection$.MODULE$.select$mZc$sp(arr2, k, ev, ct);
      return arr2;
   }

   public boolean[] qshuffled(final Generator gen) {
      return this.qshuffled$mcZ$sp(gen);
   }

   public boolean[] qshuffled$mcZ$sp(final Generator gen) {
      boolean[] arr2 = (boolean[])this.arr$mcZ$sp.clone();
      gen.shuffle$mZc$sp(arr2, gen);
      return arr2;
   }

   public boolean[] qsampled(final int n, final Generator gen, final ClassTag ct) {
      return this.qsampled$mcZ$sp(n, gen, ct);
   }

   public boolean[] qsampled$mcZ$sp(final int n, final Generator gen, final ClassTag ct) {
      return gen.sampleFromArray$mZc$sp(this.arr$mcZ$sp, n, ct, gen);
   }

   public ArrayOps$mcZ$sp(final boolean[] arr$mcZ$sp) {
      super(arr$mcZ$sp);
      this.arr$mcZ$sp = arr$mcZ$sp;
   }
}
