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

public final class ArrayOps$mcS$sp extends ArrayOps {
   public final short[] arr$mcS$sp;

   public short qsum(final AdditiveMonoid ev) {
      return this.qsum$mcS$sp(ev);
   }

   public short qsum$mcS$sp(final AdditiveMonoid ev) {
      short result = BoxesRunTime.unboxToShort(ev.zero());

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcS$sp.length; ++index$macro$1) {
         result = BoxesRunTime.unboxToShort(ev.plus(BoxesRunTime.boxToShort(result), BoxesRunTime.boxToShort(this.arr$mcS$sp[index$macro$1])));
      }

      return result;
   }

   public short qproduct(final MultiplicativeMonoid ev) {
      return this.qproduct$mcS$sp(ev);
   }

   public short qproduct$mcS$sp(final MultiplicativeMonoid ev) {
      short result = BoxesRunTime.unboxToShort(ev.one());

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcS$sp.length; ++index$macro$1) {
         result = BoxesRunTime.unboxToShort(ev.times(BoxesRunTime.boxToShort(result), BoxesRunTime.boxToShort(this.arr$mcS$sp[index$macro$1])));
      }

      return result;
   }

   public short qcombine(final Monoid ev) {
      return this.qcombine$mcS$sp(ev);
   }

   public short qcombine$mcS$sp(final Monoid ev) {
      short result = BoxesRunTime.unboxToShort(ev.empty());

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcS$sp.length; ++index$macro$1) {
         result = BoxesRunTime.unboxToShort(ev.combine(BoxesRunTime.boxToShort(result), BoxesRunTime.boxToShort(this.arr$mcS$sp[index$macro$1])));
      }

      return result;
   }

   public short qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return this.qnorm$mcS$sp(p, ev, s, nr);
   }

   public short qnorm$mcS$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      short result = BoxesRunTime.unboxToShort(ev.one());

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcS$sp.length; ++index$macro$1) {
         result = BoxesRunTime.unboxToShort(ev.plus(BoxesRunTime.boxToShort(result), BoxesRunTime.boxToShort(BoxesRunTime.unboxToShort(ev.pow(BoxesRunTime.boxToShort(s.abs$mcS$sp(this.arr$mcS$sp[index$macro$1])), p)))));
      }

      return BoxesRunTime.unboxToShort(nr.nroot(BoxesRunTime.boxToShort(result), p));
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcS$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcS$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      Object result = ev.one();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcS$sp.length; ++index$macro$1) {
         result = ev.plus(result, ev.pow(s.abs(f.apply(BoxesRunTime.boxToShort(this.arr$mcS$sp[index$macro$1]))), p));
      }

      return nr.nroot(result, p);
   }

   public double qnormWith$mDc$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDcS$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcS$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      double result = ev.one$mcD$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcS$sp.length; ++index$macro$1) {
         result = ev.plus$mcD$sp(result, ev.pow$mcD$sp(s.abs$mcD$sp(BoxesRunTime.unboxToDouble(f.apply(BoxesRunTime.boxToShort(this.arr$mcS$sp[index$macro$1])))), p));
      }

      return nr.nroot$mcD$sp(result, p);
   }

   public short qmin(final Order ev) {
      return this.qmin$mcS$sp(ev);
   }

   public short qmin$mcS$sp(final Order ev) {
      if (this.arr$mcS$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         short result = this.arr$mcS$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcS$sp.length; ++index$macro$1) {
            result = ev.min$mcS$sp(result, this.arr$mcS$sp[index$macro$1]);
         }

         return result;
      }
   }

   public short qmax(final Order ev) {
      return this.qmax$mcS$sp(ev);
   }

   public short qmax$mcS$sp(final Order ev) {
      if (this.arr$mcS$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         short result = this.arr$mcS$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcS$sp.length; ++index$macro$1) {
            result = ev.max$mcS$sp(result, this.arr$mcS$sp[index$macro$1]);
         }

         return result;
      }
   }

   public short qmean(final Field ev) {
      return this.qmean$mcS$sp(ev);
   }

   public short qmean$mcS$sp(final Field ev) {
      if (this.arr$mcS$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         short result = BoxesRunTime.unboxToShort(ev.zero());

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcS$sp.length; ++index$macro$1) {
            result = BoxesRunTime.unboxToShort(ev.plus(BoxesRunTime.boxToShort(BoxesRunTime.unboxToShort(ev.div(BoxesRunTime.boxToShort(BoxesRunTime.unboxToShort(ev.times(BoxesRunTime.boxToShort(result), ev.fromInt(index$macro$1)))), ev.fromInt(index$macro$1 + 1)))), BoxesRunTime.boxToShort(BoxesRunTime.unboxToShort(ev.div(BoxesRunTime.boxToShort(this.arr$mcS$sp[index$macro$1]), ev.fromInt(index$macro$1 + 1))))));
         }

         return result;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcS$sp(f, ev);
   }

   public Object qmeanWith$mcS$sp(final Function1 f, final Field ev) {
      if (this.arr$mcS$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         Object result = ev.zero();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcS$sp.length; ++index$macro$1) {
            result = ev.plus(ev.div(ev.times(result, ev.fromInt(index$macro$1)), ev.fromInt(index$macro$1 + 1)), ev.div(f.apply(BoxesRunTime.boxToShort(this.arr$mcS$sp[index$macro$1])), ev.fromInt(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public double qmeanWith$mDc$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDcS$sp(f, ev);
   }

   public double qmeanWith$mDcS$sp(final Function1 f, final Field ev) {
      if (this.arr$mcS$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         double result = ev.zero$mcD$sp();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcS$sp.length; ++index$macro$1) {
            result = ev.plus$mcD$sp(ev.div$mcD$sp(ev.times$mcD$sp(result, ev.fromInt$mcD$sp(index$macro$1)), ev.fromInt$mcD$sp(index$macro$1 + 1)), ev.div$mcD$sp(BoxesRunTime.unboxToDouble(f.apply(BoxesRunTime.boxToShort(this.arr$mcS$sp[index$macro$1]))), ev.fromInt$mcD$sp(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public int qsearch(final short a, final Order ev) {
      return this.qsearch$mcS$sp(a, ev);
   }

   public int qsearch$mcS$sp(final short a, final Order ev) {
      return Searching$.MODULE$.search$mSc$sp(this.arr$mcS$sp, a, ev);
   }

   public void qsort(final Order ev, final ClassTag ct) {
      this.qsort$mcS$sp(ev, ct);
   }

   public void qsort$mcS$sp(final Order ev, final ClassTag ct) {
      Sorting$.MODULE$.sort$mSc$sp(this.arr$mcS$sp, ev, ct);
   }

   public void qsortBy(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mcS$sp(f, ev, ct);
   }

   public void qsortBy$mcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort$mSc$sp(this.arr$mcS$sp, ord, ct);
   }

   public void qsortBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZcS$sp(f, ev, ct);
   }

   public void qsortBy$mZcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSZc$sp(f, ev);
      Sorting$.MODULE$.sort$mSc$sp(this.arr$mcS$sp, ord, ct);
   }

   public void qsortBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBcS$sp(f, ev, ct);
   }

   public void qsortBy$mBcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSBc$sp(f, ev);
      Sorting$.MODULE$.sort$mSc$sp(this.arr$mcS$sp, ord, ct);
   }

   public void qsortBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCcS$sp(f, ev, ct);
   }

   public void qsortBy$mCcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSCc$sp(f, ev);
      Sorting$.MODULE$.sort$mSc$sp(this.arr$mcS$sp, ord, ct);
   }

   public void qsortBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDcS$sp(f, ev, ct);
   }

   public void qsortBy$mDcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSDc$sp(f, ev);
      Sorting$.MODULE$.sort$mSc$sp(this.arr$mcS$sp, ord, ct);
   }

   public void qsortBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFcS$sp(f, ev, ct);
   }

   public void qsortBy$mFcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSFc$sp(f, ev);
      Sorting$.MODULE$.sort$mSc$sp(this.arr$mcS$sp, ord, ct);
   }

   public void qsortBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIcS$sp(f, ev, ct);
   }

   public void qsortBy$mIcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSIc$sp(f, ev);
      Sorting$.MODULE$.sort$mSc$sp(this.arr$mcS$sp, ord, ct);
   }

   public void qsortBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJcS$sp(f, ev, ct);
   }

   public void qsortBy$mJcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSJc$sp(f, ev);
      Sorting$.MODULE$.sort$mSc$sp(this.arr$mcS$sp, ord, ct);
   }

   public void qsortBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mScS$sp(f, ev, ct);
   }

   public void qsortBy$mScS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSSc$sp(f, ev);
      Sorting$.MODULE$.sort$mSc$sp(this.arr$mcS$sp, ord, ct);
   }

   public void qsortBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVcS$sp(f, ev, ct);
   }

   public void qsortBy$mVcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSVc$sp(f, ev);
      Sorting$.MODULE$.sort$mSc$sp(this.arr$mcS$sp, ord, ct);
   }

   public void qsortWith(final Function2 f, final ClassTag ct) {
      this.qsortWith$mcS$sp(f, ct);
   }

   public void qsortWith$mcS$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mSc$sp(f);
      Sorting$.MODULE$.sort$mSc$sp(this.arr$mcS$sp, ord, ct);
   }

   public short[] qsorted(final Order ev, final ClassTag ct) {
      return this.qsorted$mcS$sp(ev, ct);
   }

   public short[] qsorted$mcS$sp(final Order ev, final ClassTag ct) {
      short[] arr2 = (short[])this.arr$mcS$sp.clone();
      Sorting$.MODULE$.sort$mSc$sp(arr2, ev, ct);
      return arr2;
   }

   public short[] qsortedBy(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mcS$sp(f, ev, ct);
   }

   public short[] qsortedBy$mcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      short[] arr2 = (short[])this.arr$mcS$sp.clone();
      Sorting$.MODULE$.sort$mSc$sp(arr2, ord, ct);
      return arr2;
   }

   public short[] qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mZcS$sp(f, ev, ct);
   }

   public short[] qsortedBy$mZcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSZc$sp(f, ev);
      short[] arr2 = (short[])this.arr$mcS$sp.clone();
      Sorting$.MODULE$.sort$mSc$sp(arr2, ord, ct);
      return arr2;
   }

   public short[] qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mBcS$sp(f, ev, ct);
   }

   public short[] qsortedBy$mBcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSBc$sp(f, ev);
      short[] arr2 = (short[])this.arr$mcS$sp.clone();
      Sorting$.MODULE$.sort$mSc$sp(arr2, ord, ct);
      return arr2;
   }

   public short[] qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mCcS$sp(f, ev, ct);
   }

   public short[] qsortedBy$mCcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSCc$sp(f, ev);
      short[] arr2 = (short[])this.arr$mcS$sp.clone();
      Sorting$.MODULE$.sort$mSc$sp(arr2, ord, ct);
      return arr2;
   }

   public short[] qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mDcS$sp(f, ev, ct);
   }

   public short[] qsortedBy$mDcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSDc$sp(f, ev);
      short[] arr2 = (short[])this.arr$mcS$sp.clone();
      Sorting$.MODULE$.sort$mSc$sp(arr2, ord, ct);
      return arr2;
   }

   public short[] qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mFcS$sp(f, ev, ct);
   }

   public short[] qsortedBy$mFcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSFc$sp(f, ev);
      short[] arr2 = (short[])this.arr$mcS$sp.clone();
      Sorting$.MODULE$.sort$mSc$sp(arr2, ord, ct);
      return arr2;
   }

   public short[] qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mIcS$sp(f, ev, ct);
   }

   public short[] qsortedBy$mIcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSIc$sp(f, ev);
      short[] arr2 = (short[])this.arr$mcS$sp.clone();
      Sorting$.MODULE$.sort$mSc$sp(arr2, ord, ct);
      return arr2;
   }

   public short[] qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mJcS$sp(f, ev, ct);
   }

   public short[] qsortedBy$mJcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSJc$sp(f, ev);
      short[] arr2 = (short[])this.arr$mcS$sp.clone();
      Sorting$.MODULE$.sort$mSc$sp(arr2, ord, ct);
      return arr2;
   }

   public short[] qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mScS$sp(f, ev, ct);
   }

   public short[] qsortedBy$mScS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSSc$sp(f, ev);
      short[] arr2 = (short[])this.arr$mcS$sp.clone();
      Sorting$.MODULE$.sort$mSc$sp(arr2, ord, ct);
      return arr2;
   }

   public short[] qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mVcS$sp(f, ev, ct);
   }

   public short[] qsortedBy$mVcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSVc$sp(f, ev);
      short[] arr2 = (short[])this.arr$mcS$sp.clone();
      Sorting$.MODULE$.sort$mSc$sp(arr2, ord, ct);
      return arr2;
   }

   public short[] qsortedWith(final Function2 f, final ClassTag ct) {
      return this.qsortedWith$mcS$sp(f, ct);
   }

   public short[] qsortedWith$mcS$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mSc$sp(f);
      short[] arr2 = (short[])this.arr$mcS$sp.clone();
      Sorting$.MODULE$.sort$mSc$sp(arr2, ord, ct);
      return arr2;
   }

   public void qselect(final int k, final Order ev, final ClassTag ct) {
      this.qselect$mcS$sp(k, ev, ct);
   }

   public void qselect$mcS$sp(final int k, final Order ev, final ClassTag ct) {
      Selection$.MODULE$.select$mSc$sp(this.arr$mcS$sp, k, ev, ct);
   }

   public short[] qselected(final int k, final Order ev, final ClassTag ct) {
      return this.qselected$mcS$sp(k, ev, ct);
   }

   public short[] qselected$mcS$sp(final int k, final Order ev, final ClassTag ct) {
      short[] arr2 = (short[])this.arr$mcS$sp.clone();
      Selection$.MODULE$.select$mSc$sp(arr2, k, ev, ct);
      return arr2;
   }

   public short[] qshuffled(final Generator gen) {
      return this.qshuffled$mcS$sp(gen);
   }

   public short[] qshuffled$mcS$sp(final Generator gen) {
      short[] arr2 = (short[])this.arr$mcS$sp.clone();
      gen.shuffle$mSc$sp(arr2, gen);
      return arr2;
   }

   public short[] qsampled(final int n, final Generator gen, final ClassTag ct) {
      return this.qsampled$mcS$sp(n, gen, ct);
   }

   public short[] qsampled$mcS$sp(final int n, final Generator gen, final ClassTag ct) {
      return gen.sampleFromArray$mSc$sp(this.arr$mcS$sp, n, ct, gen);
   }

   public ArrayOps$mcS$sp(final short[] arr$mcS$sp) {
      super(arr$mcS$sp);
      this.arr$mcS$sp = arr$mcS$sp;
   }
}
