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

public final class ArrayOps$mcJ$sp extends ArrayOps {
   public final long[] arr$mcJ$sp;

   public long qsum(final AdditiveMonoid ev) {
      return this.qsum$mcJ$sp(ev);
   }

   public long qsum$mcJ$sp(final AdditiveMonoid ev) {
      long result = ev.zero$mcJ$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcJ$sp.length; ++index$macro$1) {
         result = ev.plus$mcJ$sp(result, this.arr$mcJ$sp[index$macro$1]);
      }

      return result;
   }

   public long qproduct(final MultiplicativeMonoid ev) {
      return this.qproduct$mcJ$sp(ev);
   }

   public long qproduct$mcJ$sp(final MultiplicativeMonoid ev) {
      long result = ev.one$mcJ$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcJ$sp.length; ++index$macro$1) {
         result = ev.times$mcJ$sp(result, this.arr$mcJ$sp[index$macro$1]);
      }

      return result;
   }

   public long qcombine(final Monoid ev) {
      return this.qcombine$mcJ$sp(ev);
   }

   public long qcombine$mcJ$sp(final Monoid ev) {
      long result = ev.empty$mcJ$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcJ$sp.length; ++index$macro$1) {
         result = ev.combine$mcJ$sp(result, this.arr$mcJ$sp[index$macro$1]);
      }

      return result;
   }

   public long qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return this.qnorm$mcJ$sp(p, ev, s, nr);
   }

   public long qnorm$mcJ$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      long result = ev.one$mcJ$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcJ$sp.length; ++index$macro$1) {
         result = ev.plus$mcJ$sp(result, ev.pow$mcJ$sp(s.abs$mcJ$sp(this.arr$mcJ$sp[index$macro$1]), p));
      }

      return nr.nroot$mcJ$sp(result, p);
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcJ$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcJ$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      Object result = ev.one();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcJ$sp.length; ++index$macro$1) {
         result = ev.plus(result, ev.pow(s.abs(f.apply(BoxesRunTime.boxToLong(this.arr$mcJ$sp[index$macro$1]))), p));
      }

      return nr.nroot(result, p);
   }

   public double qnormWith$mDc$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDcJ$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcJ$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      double result = ev.one$mcD$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcJ$sp.length; ++index$macro$1) {
         result = ev.plus$mcD$sp(result, ev.pow$mcD$sp(s.abs$mcD$sp(f.apply$mcDJ$sp(this.arr$mcJ$sp[index$macro$1])), p));
      }

      return nr.nroot$mcD$sp(result, p);
   }

   public long qmin(final Order ev) {
      return this.qmin$mcJ$sp(ev);
   }

   public long qmin$mcJ$sp(final Order ev) {
      if (this.arr$mcJ$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         long result = this.arr$mcJ$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcJ$sp.length; ++index$macro$1) {
            result = ev.min$mcJ$sp(result, this.arr$mcJ$sp[index$macro$1]);
         }

         return result;
      }
   }

   public long qmax(final Order ev) {
      return this.qmax$mcJ$sp(ev);
   }

   public long qmax$mcJ$sp(final Order ev) {
      if (this.arr$mcJ$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         long result = this.arr$mcJ$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcJ$sp.length; ++index$macro$1) {
            result = ev.max$mcJ$sp(result, this.arr$mcJ$sp[index$macro$1]);
         }

         return result;
      }
   }

   public long qmean(final Field ev) {
      return this.qmean$mcJ$sp(ev);
   }

   public long qmean$mcJ$sp(final Field ev) {
      if (this.arr$mcJ$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         long result = ev.zero$mcJ$sp();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcJ$sp.length; ++index$macro$1) {
            result = ev.plus$mcJ$sp(ev.div$mcJ$sp(ev.times$mcJ$sp(result, ev.fromInt$mcJ$sp(index$macro$1)), ev.fromInt$mcJ$sp(index$macro$1 + 1)), ev.div$mcJ$sp(this.arr$mcJ$sp[index$macro$1], ev.fromInt$mcJ$sp(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcJ$sp(f, ev);
   }

   public Object qmeanWith$mcJ$sp(final Function1 f, final Field ev) {
      if (this.arr$mcJ$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         Object result = ev.zero();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcJ$sp.length; ++index$macro$1) {
            result = ev.plus(ev.div(ev.times(result, ev.fromInt(index$macro$1)), ev.fromInt(index$macro$1 + 1)), ev.div(f.apply(BoxesRunTime.boxToLong(this.arr$mcJ$sp[index$macro$1])), ev.fromInt(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public double qmeanWith$mDc$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDcJ$sp(f, ev);
   }

   public double qmeanWith$mDcJ$sp(final Function1 f, final Field ev) {
      if (this.arr$mcJ$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         double result = ev.zero$mcD$sp();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcJ$sp.length; ++index$macro$1) {
            result = ev.plus$mcD$sp(ev.div$mcD$sp(ev.times$mcD$sp(result, ev.fromInt$mcD$sp(index$macro$1)), ev.fromInt$mcD$sp(index$macro$1 + 1)), ev.div$mcD$sp(f.apply$mcDJ$sp(this.arr$mcJ$sp[index$macro$1]), ev.fromInt$mcD$sp(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public int qsearch(final long a, final Order ev) {
      return this.qsearch$mcJ$sp(a, ev);
   }

   public int qsearch$mcJ$sp(final long a, final Order ev) {
      return Searching$.MODULE$.search$mJc$sp(this.arr$mcJ$sp, a, ev);
   }

   public void qsort(final Order ev, final ClassTag ct) {
      this.qsort$mcJ$sp(ev, ct);
   }

   public void qsort$mcJ$sp(final Order ev, final ClassTag ct) {
      Sorting$.MODULE$.sort$mJc$sp(this.arr$mcJ$sp, ev, ct);
   }

   public void qsortBy(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mcJ$sp(f, ev, ct);
   }

   public void qsortBy$mcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort$mJc$sp(this.arr$mcJ$sp, ord, ct);
   }

   public void qsortBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZcJ$sp(f, ev, ct);
   }

   public void qsortBy$mZcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJZc$sp(f, ev);
      Sorting$.MODULE$.sort$mJc$sp(this.arr$mcJ$sp, ord, ct);
   }

   public void qsortBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBcJ$sp(f, ev, ct);
   }

   public void qsortBy$mBcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJBc$sp(f, ev);
      Sorting$.MODULE$.sort$mJc$sp(this.arr$mcJ$sp, ord, ct);
   }

   public void qsortBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCcJ$sp(f, ev, ct);
   }

   public void qsortBy$mCcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJCc$sp(f, ev);
      Sorting$.MODULE$.sort$mJc$sp(this.arr$mcJ$sp, ord, ct);
   }

   public void qsortBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDcJ$sp(f, ev, ct);
   }

   public void qsortBy$mDcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJDc$sp(f, ev);
      Sorting$.MODULE$.sort$mJc$sp(this.arr$mcJ$sp, ord, ct);
   }

   public void qsortBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFcJ$sp(f, ev, ct);
   }

   public void qsortBy$mFcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJFc$sp(f, ev);
      Sorting$.MODULE$.sort$mJc$sp(this.arr$mcJ$sp, ord, ct);
   }

   public void qsortBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIcJ$sp(f, ev, ct);
   }

   public void qsortBy$mIcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJIc$sp(f, ev);
      Sorting$.MODULE$.sort$mJc$sp(this.arr$mcJ$sp, ord, ct);
   }

   public void qsortBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJcJ$sp(f, ev, ct);
   }

   public void qsortBy$mJcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJJc$sp(f, ev);
      Sorting$.MODULE$.sort$mJc$sp(this.arr$mcJ$sp, ord, ct);
   }

   public void qsortBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mScJ$sp(f, ev, ct);
   }

   public void qsortBy$mScJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJSc$sp(f, ev);
      Sorting$.MODULE$.sort$mJc$sp(this.arr$mcJ$sp, ord, ct);
   }

   public void qsortBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVcJ$sp(f, ev, ct);
   }

   public void qsortBy$mVcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJVc$sp(f, ev);
      Sorting$.MODULE$.sort$mJc$sp(this.arr$mcJ$sp, ord, ct);
   }

   public void qsortWith(final Function2 f, final ClassTag ct) {
      this.qsortWith$mcJ$sp(f, ct);
   }

   public void qsortWith$mcJ$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mJc$sp(f);
      Sorting$.MODULE$.sort$mJc$sp(this.arr$mcJ$sp, ord, ct);
   }

   public long[] qsorted(final Order ev, final ClassTag ct) {
      return this.qsorted$mcJ$sp(ev, ct);
   }

   public long[] qsorted$mcJ$sp(final Order ev, final ClassTag ct) {
      long[] arr2 = (long[])this.arr$mcJ$sp.clone();
      Sorting$.MODULE$.sort$mJc$sp(arr2, ev, ct);
      return arr2;
   }

   public long[] qsortedBy(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mcJ$sp(f, ev, ct);
   }

   public long[] qsortedBy$mcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      long[] arr2 = (long[])this.arr$mcJ$sp.clone();
      Sorting$.MODULE$.sort$mJc$sp(arr2, ord, ct);
      return arr2;
   }

   public long[] qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mZcJ$sp(f, ev, ct);
   }

   public long[] qsortedBy$mZcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJZc$sp(f, ev);
      long[] arr2 = (long[])this.arr$mcJ$sp.clone();
      Sorting$.MODULE$.sort$mJc$sp(arr2, ord, ct);
      return arr2;
   }

   public long[] qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mBcJ$sp(f, ev, ct);
   }

   public long[] qsortedBy$mBcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJBc$sp(f, ev);
      long[] arr2 = (long[])this.arr$mcJ$sp.clone();
      Sorting$.MODULE$.sort$mJc$sp(arr2, ord, ct);
      return arr2;
   }

   public long[] qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mCcJ$sp(f, ev, ct);
   }

   public long[] qsortedBy$mCcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJCc$sp(f, ev);
      long[] arr2 = (long[])this.arr$mcJ$sp.clone();
      Sorting$.MODULE$.sort$mJc$sp(arr2, ord, ct);
      return arr2;
   }

   public long[] qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mDcJ$sp(f, ev, ct);
   }

   public long[] qsortedBy$mDcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJDc$sp(f, ev);
      long[] arr2 = (long[])this.arr$mcJ$sp.clone();
      Sorting$.MODULE$.sort$mJc$sp(arr2, ord, ct);
      return arr2;
   }

   public long[] qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mFcJ$sp(f, ev, ct);
   }

   public long[] qsortedBy$mFcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJFc$sp(f, ev);
      long[] arr2 = (long[])this.arr$mcJ$sp.clone();
      Sorting$.MODULE$.sort$mJc$sp(arr2, ord, ct);
      return arr2;
   }

   public long[] qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mIcJ$sp(f, ev, ct);
   }

   public long[] qsortedBy$mIcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJIc$sp(f, ev);
      long[] arr2 = (long[])this.arr$mcJ$sp.clone();
      Sorting$.MODULE$.sort$mJc$sp(arr2, ord, ct);
      return arr2;
   }

   public long[] qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mJcJ$sp(f, ev, ct);
   }

   public long[] qsortedBy$mJcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJJc$sp(f, ev);
      long[] arr2 = (long[])this.arr$mcJ$sp.clone();
      Sorting$.MODULE$.sort$mJc$sp(arr2, ord, ct);
      return arr2;
   }

   public long[] qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mScJ$sp(f, ev, ct);
   }

   public long[] qsortedBy$mScJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJSc$sp(f, ev);
      long[] arr2 = (long[])this.arr$mcJ$sp.clone();
      Sorting$.MODULE$.sort$mJc$sp(arr2, ord, ct);
      return arr2;
   }

   public long[] qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mVcJ$sp(f, ev, ct);
   }

   public long[] qsortedBy$mVcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJVc$sp(f, ev);
      long[] arr2 = (long[])this.arr$mcJ$sp.clone();
      Sorting$.MODULE$.sort$mJc$sp(arr2, ord, ct);
      return arr2;
   }

   public long[] qsortedWith(final Function2 f, final ClassTag ct) {
      return this.qsortedWith$mcJ$sp(f, ct);
   }

   public long[] qsortedWith$mcJ$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mJc$sp(f);
      long[] arr2 = (long[])this.arr$mcJ$sp.clone();
      Sorting$.MODULE$.sort$mJc$sp(arr2, ord, ct);
      return arr2;
   }

   public void qselect(final int k, final Order ev, final ClassTag ct) {
      this.qselect$mcJ$sp(k, ev, ct);
   }

   public void qselect$mcJ$sp(final int k, final Order ev, final ClassTag ct) {
      Selection$.MODULE$.select$mJc$sp(this.arr$mcJ$sp, k, ev, ct);
   }

   public long[] qselected(final int k, final Order ev, final ClassTag ct) {
      return this.qselected$mcJ$sp(k, ev, ct);
   }

   public long[] qselected$mcJ$sp(final int k, final Order ev, final ClassTag ct) {
      long[] arr2 = (long[])this.arr$mcJ$sp.clone();
      Selection$.MODULE$.select$mJc$sp(arr2, k, ev, ct);
      return arr2;
   }

   public long[] qshuffled(final Generator gen) {
      return this.qshuffled$mcJ$sp(gen);
   }

   public long[] qshuffled$mcJ$sp(final Generator gen) {
      long[] arr2 = (long[])this.arr$mcJ$sp.clone();
      gen.shuffle$mJc$sp(arr2, gen);
      return arr2;
   }

   public long[] qsampled(final int n, final Generator gen, final ClassTag ct) {
      return this.qsampled$mcJ$sp(n, gen, ct);
   }

   public long[] qsampled$mcJ$sp(final int n, final Generator gen, final ClassTag ct) {
      return gen.sampleFromArray$mJc$sp(this.arr$mcJ$sp, n, ct, gen);
   }

   public ArrayOps$mcJ$sp(final long[] arr$mcJ$sp) {
      super(arr$mcJ$sp);
      this.arr$mcJ$sp = arr$mcJ$sp;
   }
}
