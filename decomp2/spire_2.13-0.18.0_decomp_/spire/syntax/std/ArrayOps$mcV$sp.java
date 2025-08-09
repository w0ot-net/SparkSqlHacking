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
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import spire.algebra.NRoot;
import spire.math.Searching$;
import spire.math.Selection$;
import spire.math.Sorting$;
import spire.random.Generator;

public final class ArrayOps$mcV$sp extends ArrayOps {
   public final BoxedUnit[] arr$mcV$sp;

   public void qsum(final AdditiveMonoid ev) {
      this.qsum$mcV$sp(ev);
   }

   public void qsum$mcV$sp(final AdditiveMonoid ev) {
      BoxedUnit result = (BoxedUnit)ev.zero();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcV$sp.length; ++index$macro$1) {
         result = (BoxedUnit)ev.plus(result, this.arr$mcV$sp[index$macro$1]);
      }

   }

   public void qproduct(final MultiplicativeMonoid ev) {
      this.qproduct$mcV$sp(ev);
   }

   public void qproduct$mcV$sp(final MultiplicativeMonoid ev) {
      BoxedUnit result = (BoxedUnit)ev.one();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcV$sp.length; ++index$macro$1) {
         result = (BoxedUnit)ev.times(result, this.arr$mcV$sp[index$macro$1]);
      }

   }

   public void qcombine(final Monoid ev) {
      this.qcombine$mcV$sp(ev);
   }

   public void qcombine$mcV$sp(final Monoid ev) {
      BoxedUnit result = (BoxedUnit)ev.empty();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcV$sp.length; ++index$macro$1) {
         result = (BoxedUnit)ev.combine(result, this.arr$mcV$sp[index$macro$1]);
      }

   }

   public void qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      this.qnorm$mcV$sp(p, ev, s, nr);
   }

   public void qnorm$mcV$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      BoxedUnit result = (BoxedUnit)ev.one();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcV$sp.length; ++index$macro$1) {
         result = (BoxedUnit)ev.plus(result, (BoxedUnit)ev.pow((BoxedUnit)s.abs(this.arr$mcV$sp[index$macro$1]), p));
      }

      BoxedUnit var10000 = (BoxedUnit)nr.nroot(result, p);
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcV$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcV$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      Object result = ev.one();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcV$sp.length; ++index$macro$1) {
         result = ev.plus(result, ev.pow(s.abs(f.apply(this.arr$mcV$sp[index$macro$1])), p));
      }

      return nr.nroot(result, p);
   }

   public double qnormWith$mDc$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDcV$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcV$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      double result = ev.one$mcD$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcV$sp.length; ++index$macro$1) {
         result = ev.plus$mcD$sp(result, ev.pow$mcD$sp(s.abs$mcD$sp(BoxesRunTime.unboxToDouble(f.apply(this.arr$mcV$sp[index$macro$1]))), p));
      }

      return nr.nroot$mcD$sp(result, p);
   }

   public void qmin(final Order ev) {
      this.qmin$mcV$sp(ev);
   }

   public void qmin$mcV$sp(final Order ev) {
      if (this.arr$mcV$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         BoxedUnit result = this.arr$mcV$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcV$sp.length; ++index$macro$1) {
            ev.min$mcV$sp(result, this.arr$mcV$sp[index$macro$1]);
            result = BoxedUnit.UNIT;
         }

      }
   }

   public void qmax(final Order ev) {
      this.qmax$mcV$sp(ev);
   }

   public void qmax$mcV$sp(final Order ev) {
      if (this.arr$mcV$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         BoxedUnit result = this.arr$mcV$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcV$sp.length; ++index$macro$1) {
            ev.max$mcV$sp(result, this.arr$mcV$sp[index$macro$1]);
            result = BoxedUnit.UNIT;
         }

      }
   }

   public void qmean(final Field ev) {
      this.qmean$mcV$sp(ev);
   }

   public void qmean$mcV$sp(final Field ev) {
      if (this.arr$mcV$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         BoxedUnit result = (BoxedUnit)ev.zero();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcV$sp.length; ++index$macro$1) {
            result = (BoxedUnit)ev.plus((BoxedUnit)ev.div((BoxedUnit)ev.times(result, ev.fromInt(index$macro$1)), ev.fromInt(index$macro$1 + 1)), (BoxedUnit)ev.div(this.arr$mcV$sp[index$macro$1], ev.fromInt(index$macro$1 + 1)));
         }

      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcV$sp(f, ev);
   }

   public Object qmeanWith$mcV$sp(final Function1 f, final Field ev) {
      if (this.arr$mcV$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         Object result = ev.zero();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcV$sp.length; ++index$macro$1) {
            result = ev.plus(ev.div(ev.times(result, ev.fromInt(index$macro$1)), ev.fromInt(index$macro$1 + 1)), ev.div(f.apply(this.arr$mcV$sp[index$macro$1]), ev.fromInt(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public double qmeanWith$mDc$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDcV$sp(f, ev);
   }

   public double qmeanWith$mDcV$sp(final Function1 f, final Field ev) {
      if (this.arr$mcV$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         double result = ev.zero$mcD$sp();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcV$sp.length; ++index$macro$1) {
            result = ev.plus$mcD$sp(ev.div$mcD$sp(ev.times$mcD$sp(result, ev.fromInt$mcD$sp(index$macro$1)), ev.fromInt$mcD$sp(index$macro$1 + 1)), ev.div$mcD$sp(BoxesRunTime.unboxToDouble(f.apply(this.arr$mcV$sp[index$macro$1])), ev.fromInt$mcD$sp(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public int qsearch(final BoxedUnit a, final Order ev) {
      return this.qsearch$mcV$sp(a, ev);
   }

   public int qsearch$mcV$sp(final BoxedUnit a, final Order ev) {
      return Searching$.MODULE$.search$mVc$sp(this.arr$mcV$sp, a, ev);
   }

   public void qsort(final Order ev, final ClassTag ct) {
      this.qsort$mcV$sp(ev, ct);
   }

   public void qsort$mcV$sp(final Order ev, final ClassTag ct) {
      Sorting$.MODULE$.sort$mVc$sp(this.arr$mcV$sp, ev, ct);
   }

   public void qsortBy(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mcV$sp(f, ev, ct);
   }

   public void qsortBy$mcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort$mVc$sp(this.arr$mcV$sp, ord, ct);
   }

   public void qsortBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZcV$sp(f, ev, ct);
   }

   public void qsortBy$mZcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVZc$sp(f, ev);
      Sorting$.MODULE$.sort$mVc$sp(this.arr$mcV$sp, ord, ct);
   }

   public void qsortBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBcV$sp(f, ev, ct);
   }

   public void qsortBy$mBcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVBc$sp(f, ev);
      Sorting$.MODULE$.sort$mVc$sp(this.arr$mcV$sp, ord, ct);
   }

   public void qsortBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCcV$sp(f, ev, ct);
   }

   public void qsortBy$mCcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVCc$sp(f, ev);
      Sorting$.MODULE$.sort$mVc$sp(this.arr$mcV$sp, ord, ct);
   }

   public void qsortBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDcV$sp(f, ev, ct);
   }

   public void qsortBy$mDcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVDc$sp(f, ev);
      Sorting$.MODULE$.sort$mVc$sp(this.arr$mcV$sp, ord, ct);
   }

   public void qsortBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFcV$sp(f, ev, ct);
   }

   public void qsortBy$mFcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVFc$sp(f, ev);
      Sorting$.MODULE$.sort$mVc$sp(this.arr$mcV$sp, ord, ct);
   }

   public void qsortBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIcV$sp(f, ev, ct);
   }

   public void qsortBy$mIcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVIc$sp(f, ev);
      Sorting$.MODULE$.sort$mVc$sp(this.arr$mcV$sp, ord, ct);
   }

   public void qsortBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJcV$sp(f, ev, ct);
   }

   public void qsortBy$mJcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVJc$sp(f, ev);
      Sorting$.MODULE$.sort$mVc$sp(this.arr$mcV$sp, ord, ct);
   }

   public void qsortBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mScV$sp(f, ev, ct);
   }

   public void qsortBy$mScV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVSc$sp(f, ev);
      Sorting$.MODULE$.sort$mVc$sp(this.arr$mcV$sp, ord, ct);
   }

   public void qsortBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVcV$sp(f, ev, ct);
   }

   public void qsortBy$mVcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVVc$sp(f, ev);
      Sorting$.MODULE$.sort$mVc$sp(this.arr$mcV$sp, ord, ct);
   }

   public void qsortWith(final Function2 f, final ClassTag ct) {
      this.qsortWith$mcV$sp(f, ct);
   }

   public void qsortWith$mcV$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mVc$sp(f);
      Sorting$.MODULE$.sort$mVc$sp(this.arr$mcV$sp, ord, ct);
   }

   public BoxedUnit[] qsorted(final Order ev, final ClassTag ct) {
      return this.qsorted$mcV$sp(ev, ct);
   }

   public BoxedUnit[] qsorted$mcV$sp(final Order ev, final ClassTag ct) {
      BoxedUnit[] arr2 = (BoxedUnit[])this.arr$mcV$sp.clone();
      Sorting$.MODULE$.sort$mVc$sp(arr2, ev, ct);
      return arr2;
   }

   public BoxedUnit[] qsortedBy(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mcV$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      BoxedUnit[] arr2 = (BoxedUnit[])this.arr$mcV$sp.clone();
      Sorting$.MODULE$.sort$mVc$sp(arr2, ord, ct);
      return arr2;
   }

   public BoxedUnit[] qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mZcV$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mZcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVZc$sp(f, ev);
      BoxedUnit[] arr2 = (BoxedUnit[])this.arr$mcV$sp.clone();
      Sorting$.MODULE$.sort$mVc$sp(arr2, ord, ct);
      return arr2;
   }

   public BoxedUnit[] qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mBcV$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mBcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVBc$sp(f, ev);
      BoxedUnit[] arr2 = (BoxedUnit[])this.arr$mcV$sp.clone();
      Sorting$.MODULE$.sort$mVc$sp(arr2, ord, ct);
      return arr2;
   }

   public BoxedUnit[] qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mCcV$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mCcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVCc$sp(f, ev);
      BoxedUnit[] arr2 = (BoxedUnit[])this.arr$mcV$sp.clone();
      Sorting$.MODULE$.sort$mVc$sp(arr2, ord, ct);
      return arr2;
   }

   public BoxedUnit[] qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mDcV$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mDcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVDc$sp(f, ev);
      BoxedUnit[] arr2 = (BoxedUnit[])this.arr$mcV$sp.clone();
      Sorting$.MODULE$.sort$mVc$sp(arr2, ord, ct);
      return arr2;
   }

   public BoxedUnit[] qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mFcV$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mFcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVFc$sp(f, ev);
      BoxedUnit[] arr2 = (BoxedUnit[])this.arr$mcV$sp.clone();
      Sorting$.MODULE$.sort$mVc$sp(arr2, ord, ct);
      return arr2;
   }

   public BoxedUnit[] qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mIcV$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mIcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVIc$sp(f, ev);
      BoxedUnit[] arr2 = (BoxedUnit[])this.arr$mcV$sp.clone();
      Sorting$.MODULE$.sort$mVc$sp(arr2, ord, ct);
      return arr2;
   }

   public BoxedUnit[] qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mJcV$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mJcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVJc$sp(f, ev);
      BoxedUnit[] arr2 = (BoxedUnit[])this.arr$mcV$sp.clone();
      Sorting$.MODULE$.sort$mVc$sp(arr2, ord, ct);
      return arr2;
   }

   public BoxedUnit[] qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mScV$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mScV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVSc$sp(f, ev);
      BoxedUnit[] arr2 = (BoxedUnit[])this.arr$mcV$sp.clone();
      Sorting$.MODULE$.sort$mVc$sp(arr2, ord, ct);
      return arr2;
   }

   public BoxedUnit[] qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mVcV$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mVcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVVc$sp(f, ev);
      BoxedUnit[] arr2 = (BoxedUnit[])this.arr$mcV$sp.clone();
      Sorting$.MODULE$.sort$mVc$sp(arr2, ord, ct);
      return arr2;
   }

   public BoxedUnit[] qsortedWith(final Function2 f, final ClassTag ct) {
      return this.qsortedWith$mcV$sp(f, ct);
   }

   public BoxedUnit[] qsortedWith$mcV$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mVc$sp(f);
      BoxedUnit[] arr2 = (BoxedUnit[])this.arr$mcV$sp.clone();
      Sorting$.MODULE$.sort$mVc$sp(arr2, ord, ct);
      return arr2;
   }

   public void qselect(final int k, final Order ev, final ClassTag ct) {
      this.qselect$mcV$sp(k, ev, ct);
   }

   public void qselect$mcV$sp(final int k, final Order ev, final ClassTag ct) {
      Selection$.MODULE$.select$mVc$sp(this.arr$mcV$sp, k, ev, ct);
   }

   public BoxedUnit[] qselected(final int k, final Order ev, final ClassTag ct) {
      return this.qselected$mcV$sp(k, ev, ct);
   }

   public BoxedUnit[] qselected$mcV$sp(final int k, final Order ev, final ClassTag ct) {
      BoxedUnit[] arr2 = (BoxedUnit[])this.arr$mcV$sp.clone();
      Selection$.MODULE$.select$mVc$sp(arr2, k, ev, ct);
      return arr2;
   }

   public BoxedUnit[] qshuffled(final Generator gen) {
      return this.qshuffled$mcV$sp(gen);
   }

   public BoxedUnit[] qshuffled$mcV$sp(final Generator gen) {
      BoxedUnit[] arr2 = (BoxedUnit[])this.arr$mcV$sp.clone();
      gen.shuffle$mVc$sp(arr2, gen);
      return arr2;
   }

   public BoxedUnit[] qsampled(final int n, final Generator gen, final ClassTag ct) {
      return this.qsampled$mcV$sp(n, gen, ct);
   }

   public BoxedUnit[] qsampled$mcV$sp(final int n, final Generator gen, final ClassTag ct) {
      return gen.sampleFromArray$mVc$sp(this.arr$mcV$sp, n, ct, gen);
   }

   public ArrayOps$mcV$sp(final BoxedUnit[] arr$mcV$sp) {
      super(arr$mcV$sp);
      this.arr$mcV$sp = arr$mcV$sp;
   }
}
