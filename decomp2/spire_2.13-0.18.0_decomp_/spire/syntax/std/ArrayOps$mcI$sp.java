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

public final class ArrayOps$mcI$sp extends ArrayOps {
   public final int[] arr$mcI$sp;

   public int qsum(final AdditiveMonoid ev) {
      return this.qsum$mcI$sp(ev);
   }

   public int qsum$mcI$sp(final AdditiveMonoid ev) {
      int result = ev.zero$mcI$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcI$sp.length; ++index$macro$1) {
         result = ev.plus$mcI$sp(result, this.arr$mcI$sp[index$macro$1]);
      }

      return result;
   }

   public int qproduct(final MultiplicativeMonoid ev) {
      return this.qproduct$mcI$sp(ev);
   }

   public int qproduct$mcI$sp(final MultiplicativeMonoid ev) {
      int result = ev.one$mcI$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcI$sp.length; ++index$macro$1) {
         result = ev.times$mcI$sp(result, this.arr$mcI$sp[index$macro$1]);
      }

      return result;
   }

   public int qcombine(final Monoid ev) {
      return this.qcombine$mcI$sp(ev);
   }

   public int qcombine$mcI$sp(final Monoid ev) {
      int result = ev.empty$mcI$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcI$sp.length; ++index$macro$1) {
         result = ev.combine$mcI$sp(result, this.arr$mcI$sp[index$macro$1]);
      }

      return result;
   }

   public int qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return this.qnorm$mcI$sp(p, ev, s, nr);
   }

   public int qnorm$mcI$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      int result = ev.one$mcI$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcI$sp.length; ++index$macro$1) {
         result = ev.plus$mcI$sp(result, ev.pow$mcI$sp(s.abs$mcI$sp(this.arr$mcI$sp[index$macro$1]), p));
      }

      return nr.nroot$mcI$sp(result, p);
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcI$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcI$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      Object result = ev.one();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcI$sp.length; ++index$macro$1) {
         result = ev.plus(result, ev.pow(s.abs(f.apply(BoxesRunTime.boxToInteger(this.arr$mcI$sp[index$macro$1]))), p));
      }

      return nr.nroot(result, p);
   }

   public double qnormWith$mDc$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDcI$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcI$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      double result = ev.one$mcD$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcI$sp.length; ++index$macro$1) {
         result = ev.plus$mcD$sp(result, ev.pow$mcD$sp(s.abs$mcD$sp(f.apply$mcDI$sp(this.arr$mcI$sp[index$macro$1])), p));
      }

      return nr.nroot$mcD$sp(result, p);
   }

   public int qmin(final Order ev) {
      return this.qmin$mcI$sp(ev);
   }

   public int qmin$mcI$sp(final Order ev) {
      if (this.arr$mcI$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         int result = this.arr$mcI$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcI$sp.length; ++index$macro$1) {
            result = ev.min$mcI$sp(result, this.arr$mcI$sp[index$macro$1]);
         }

         return result;
      }
   }

   public int qmax(final Order ev) {
      return this.qmax$mcI$sp(ev);
   }

   public int qmax$mcI$sp(final Order ev) {
      if (this.arr$mcI$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         int result = this.arr$mcI$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcI$sp.length; ++index$macro$1) {
            result = ev.max$mcI$sp(result, this.arr$mcI$sp[index$macro$1]);
         }

         return result;
      }
   }

   public int qmean(final Field ev) {
      return this.qmean$mcI$sp(ev);
   }

   public int qmean$mcI$sp(final Field ev) {
      if (this.arr$mcI$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         int result = ev.zero$mcI$sp();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcI$sp.length; ++index$macro$1) {
            result = ev.plus$mcI$sp(ev.div$mcI$sp(ev.times$mcI$sp(result, ev.fromInt$mcI$sp(index$macro$1)), ev.fromInt$mcI$sp(index$macro$1 + 1)), ev.div$mcI$sp(this.arr$mcI$sp[index$macro$1], ev.fromInt$mcI$sp(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcI$sp(f, ev);
   }

   public Object qmeanWith$mcI$sp(final Function1 f, final Field ev) {
      if (this.arr$mcI$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         Object result = ev.zero();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcI$sp.length; ++index$macro$1) {
            result = ev.plus(ev.div(ev.times(result, ev.fromInt(index$macro$1)), ev.fromInt(index$macro$1 + 1)), ev.div(f.apply(BoxesRunTime.boxToInteger(this.arr$mcI$sp[index$macro$1])), ev.fromInt(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public double qmeanWith$mDc$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDcI$sp(f, ev);
   }

   public double qmeanWith$mDcI$sp(final Function1 f, final Field ev) {
      if (this.arr$mcI$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         double result = ev.zero$mcD$sp();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcI$sp.length; ++index$macro$1) {
            result = ev.plus$mcD$sp(ev.div$mcD$sp(ev.times$mcD$sp(result, ev.fromInt$mcD$sp(index$macro$1)), ev.fromInt$mcD$sp(index$macro$1 + 1)), ev.div$mcD$sp(f.apply$mcDI$sp(this.arr$mcI$sp[index$macro$1]), ev.fromInt$mcD$sp(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public int qsearch(final int a, final Order ev) {
      return this.qsearch$mcI$sp(a, ev);
   }

   public int qsearch$mcI$sp(final int a, final Order ev) {
      return Searching$.MODULE$.search$mIc$sp(this.arr$mcI$sp, a, ev);
   }

   public void qsort(final Order ev, final ClassTag ct) {
      this.qsort$mcI$sp(ev, ct);
   }

   public void qsort$mcI$sp(final Order ev, final ClassTag ct) {
      Sorting$.MODULE$.sort$mIc$sp(this.arr$mcI$sp, ev, ct);
   }

   public void qsortBy(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mcI$sp(f, ev, ct);
   }

   public void qsortBy$mcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort$mIc$sp(this.arr$mcI$sp, ord, ct);
   }

   public void qsortBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZcI$sp(f, ev, ct);
   }

   public void qsortBy$mZcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIZc$sp(f, ev);
      Sorting$.MODULE$.sort$mIc$sp(this.arr$mcI$sp, ord, ct);
   }

   public void qsortBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBcI$sp(f, ev, ct);
   }

   public void qsortBy$mBcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIBc$sp(f, ev);
      Sorting$.MODULE$.sort$mIc$sp(this.arr$mcI$sp, ord, ct);
   }

   public void qsortBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCcI$sp(f, ev, ct);
   }

   public void qsortBy$mCcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mICc$sp(f, ev);
      Sorting$.MODULE$.sort$mIc$sp(this.arr$mcI$sp, ord, ct);
   }

   public void qsortBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDcI$sp(f, ev, ct);
   }

   public void qsortBy$mDcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIDc$sp(f, ev);
      Sorting$.MODULE$.sort$mIc$sp(this.arr$mcI$sp, ord, ct);
   }

   public void qsortBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFcI$sp(f, ev, ct);
   }

   public void qsortBy$mFcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIFc$sp(f, ev);
      Sorting$.MODULE$.sort$mIc$sp(this.arr$mcI$sp, ord, ct);
   }

   public void qsortBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIcI$sp(f, ev, ct);
   }

   public void qsortBy$mIcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIIc$sp(f, ev);
      Sorting$.MODULE$.sort$mIc$sp(this.arr$mcI$sp, ord, ct);
   }

   public void qsortBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJcI$sp(f, ev, ct);
   }

   public void qsortBy$mJcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIJc$sp(f, ev);
      Sorting$.MODULE$.sort$mIc$sp(this.arr$mcI$sp, ord, ct);
   }

   public void qsortBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mScI$sp(f, ev, ct);
   }

   public void qsortBy$mScI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mISc$sp(f, ev);
      Sorting$.MODULE$.sort$mIc$sp(this.arr$mcI$sp, ord, ct);
   }

   public void qsortBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVcI$sp(f, ev, ct);
   }

   public void qsortBy$mVcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIVc$sp(f, ev);
      Sorting$.MODULE$.sort$mIc$sp(this.arr$mcI$sp, ord, ct);
   }

   public void qsortWith(final Function2 f, final ClassTag ct) {
      this.qsortWith$mcI$sp(f, ct);
   }

   public void qsortWith$mcI$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mIc$sp(f);
      Sorting$.MODULE$.sort$mIc$sp(this.arr$mcI$sp, ord, ct);
   }

   public int[] qsorted(final Order ev, final ClassTag ct) {
      return this.qsorted$mcI$sp(ev, ct);
   }

   public int[] qsorted$mcI$sp(final Order ev, final ClassTag ct) {
      int[] arr2 = (int[])this.arr$mcI$sp.clone();
      Sorting$.MODULE$.sort$mIc$sp(arr2, ev, ct);
      return arr2;
   }

   public int[] qsortedBy(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mcI$sp(f, ev, ct);
   }

   public int[] qsortedBy$mcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      int[] arr2 = (int[])this.arr$mcI$sp.clone();
      Sorting$.MODULE$.sort$mIc$sp(arr2, ord, ct);
      return arr2;
   }

   public int[] qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mZcI$sp(f, ev, ct);
   }

   public int[] qsortedBy$mZcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIZc$sp(f, ev);
      int[] arr2 = (int[])this.arr$mcI$sp.clone();
      Sorting$.MODULE$.sort$mIc$sp(arr2, ord, ct);
      return arr2;
   }

   public int[] qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mBcI$sp(f, ev, ct);
   }

   public int[] qsortedBy$mBcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIBc$sp(f, ev);
      int[] arr2 = (int[])this.arr$mcI$sp.clone();
      Sorting$.MODULE$.sort$mIc$sp(arr2, ord, ct);
      return arr2;
   }

   public int[] qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mCcI$sp(f, ev, ct);
   }

   public int[] qsortedBy$mCcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mICc$sp(f, ev);
      int[] arr2 = (int[])this.arr$mcI$sp.clone();
      Sorting$.MODULE$.sort$mIc$sp(arr2, ord, ct);
      return arr2;
   }

   public int[] qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mDcI$sp(f, ev, ct);
   }

   public int[] qsortedBy$mDcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIDc$sp(f, ev);
      int[] arr2 = (int[])this.arr$mcI$sp.clone();
      Sorting$.MODULE$.sort$mIc$sp(arr2, ord, ct);
      return arr2;
   }

   public int[] qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mFcI$sp(f, ev, ct);
   }

   public int[] qsortedBy$mFcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIFc$sp(f, ev);
      int[] arr2 = (int[])this.arr$mcI$sp.clone();
      Sorting$.MODULE$.sort$mIc$sp(arr2, ord, ct);
      return arr2;
   }

   public int[] qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mIcI$sp(f, ev, ct);
   }

   public int[] qsortedBy$mIcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIIc$sp(f, ev);
      int[] arr2 = (int[])this.arr$mcI$sp.clone();
      Sorting$.MODULE$.sort$mIc$sp(arr2, ord, ct);
      return arr2;
   }

   public int[] qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mJcI$sp(f, ev, ct);
   }

   public int[] qsortedBy$mJcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIJc$sp(f, ev);
      int[] arr2 = (int[])this.arr$mcI$sp.clone();
      Sorting$.MODULE$.sort$mIc$sp(arr2, ord, ct);
      return arr2;
   }

   public int[] qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mScI$sp(f, ev, ct);
   }

   public int[] qsortedBy$mScI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mISc$sp(f, ev);
      int[] arr2 = (int[])this.arr$mcI$sp.clone();
      Sorting$.MODULE$.sort$mIc$sp(arr2, ord, ct);
      return arr2;
   }

   public int[] qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mVcI$sp(f, ev, ct);
   }

   public int[] qsortedBy$mVcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIVc$sp(f, ev);
      int[] arr2 = (int[])this.arr$mcI$sp.clone();
      Sorting$.MODULE$.sort$mIc$sp(arr2, ord, ct);
      return arr2;
   }

   public int[] qsortedWith(final Function2 f, final ClassTag ct) {
      return this.qsortedWith$mcI$sp(f, ct);
   }

   public int[] qsortedWith$mcI$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mIc$sp(f);
      int[] arr2 = (int[])this.arr$mcI$sp.clone();
      Sorting$.MODULE$.sort$mIc$sp(arr2, ord, ct);
      return arr2;
   }

   public void qselect(final int k, final Order ev, final ClassTag ct) {
      this.qselect$mcI$sp(k, ev, ct);
   }

   public void qselect$mcI$sp(final int k, final Order ev, final ClassTag ct) {
      Selection$.MODULE$.select$mIc$sp(this.arr$mcI$sp, k, ev, ct);
   }

   public int[] qselected(final int k, final Order ev, final ClassTag ct) {
      return this.qselected$mcI$sp(k, ev, ct);
   }

   public int[] qselected$mcI$sp(final int k, final Order ev, final ClassTag ct) {
      int[] arr2 = (int[])this.arr$mcI$sp.clone();
      Selection$.MODULE$.select$mIc$sp(arr2, k, ev, ct);
      return arr2;
   }

   public int[] qshuffled(final Generator gen) {
      return this.qshuffled$mcI$sp(gen);
   }

   public int[] qshuffled$mcI$sp(final Generator gen) {
      int[] arr2 = (int[])this.arr$mcI$sp.clone();
      gen.shuffle$mIc$sp(arr2, gen);
      return arr2;
   }

   public int[] qsampled(final int n, final Generator gen, final ClassTag ct) {
      return this.qsampled$mcI$sp(n, gen, ct);
   }

   public int[] qsampled$mcI$sp(final int n, final Generator gen, final ClassTag ct) {
      return gen.sampleFromArray$mIc$sp(this.arr$mcI$sp, n, ct, gen);
   }

   public ArrayOps$mcI$sp(final int[] arr$mcI$sp) {
      super(arr$mcI$sp);
      this.arr$mcI$sp = arr$mcI$sp;
   }
}
