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

public final class ArrayOps$mcB$sp extends ArrayOps {
   public final byte[] arr$mcB$sp;

   public byte qsum(final AdditiveMonoid ev) {
      return this.qsum$mcB$sp(ev);
   }

   public byte qsum$mcB$sp(final AdditiveMonoid ev) {
      byte result = BoxesRunTime.unboxToByte(ev.zero());

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcB$sp.length; ++index$macro$1) {
         result = BoxesRunTime.unboxToByte(ev.plus(BoxesRunTime.boxToByte(result), BoxesRunTime.boxToByte(this.arr$mcB$sp[index$macro$1])));
      }

      return result;
   }

   public byte qproduct(final MultiplicativeMonoid ev) {
      return this.qproduct$mcB$sp(ev);
   }

   public byte qproduct$mcB$sp(final MultiplicativeMonoid ev) {
      byte result = BoxesRunTime.unboxToByte(ev.one());

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcB$sp.length; ++index$macro$1) {
         result = BoxesRunTime.unboxToByte(ev.times(BoxesRunTime.boxToByte(result), BoxesRunTime.boxToByte(this.arr$mcB$sp[index$macro$1])));
      }

      return result;
   }

   public byte qcombine(final Monoid ev) {
      return this.qcombine$mcB$sp(ev);
   }

   public byte qcombine$mcB$sp(final Monoid ev) {
      byte result = BoxesRunTime.unboxToByte(ev.empty());

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcB$sp.length; ++index$macro$1) {
         result = BoxesRunTime.unboxToByte(ev.combine(BoxesRunTime.boxToByte(result), BoxesRunTime.boxToByte(this.arr$mcB$sp[index$macro$1])));
      }

      return result;
   }

   public byte qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return this.qnorm$mcB$sp(p, ev, s, nr);
   }

   public byte qnorm$mcB$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      byte result = BoxesRunTime.unboxToByte(ev.one());

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcB$sp.length; ++index$macro$1) {
         result = BoxesRunTime.unboxToByte(ev.plus(BoxesRunTime.boxToByte(result), BoxesRunTime.boxToByte(BoxesRunTime.unboxToByte(ev.pow(BoxesRunTime.boxToByte(s.abs$mcB$sp(this.arr$mcB$sp[index$macro$1])), p)))));
      }

      return BoxesRunTime.unboxToByte(nr.nroot(BoxesRunTime.boxToByte(result), p));
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcB$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcB$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      Object result = ev.one();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcB$sp.length; ++index$macro$1) {
         result = ev.plus(result, ev.pow(s.abs(f.apply(BoxesRunTime.boxToByte(this.arr$mcB$sp[index$macro$1]))), p));
      }

      return nr.nroot(result, p);
   }

   public double qnormWith$mDc$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDcB$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcB$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      double result = ev.one$mcD$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcB$sp.length; ++index$macro$1) {
         result = ev.plus$mcD$sp(result, ev.pow$mcD$sp(s.abs$mcD$sp(BoxesRunTime.unboxToDouble(f.apply(BoxesRunTime.boxToByte(this.arr$mcB$sp[index$macro$1])))), p));
      }

      return nr.nroot$mcD$sp(result, p);
   }

   public byte qmin(final Order ev) {
      return this.qmin$mcB$sp(ev);
   }

   public byte qmin$mcB$sp(final Order ev) {
      if (this.arr$mcB$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         byte result = this.arr$mcB$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcB$sp.length; ++index$macro$1) {
            result = ev.min$mcB$sp(result, this.arr$mcB$sp[index$macro$1]);
         }

         return result;
      }
   }

   public byte qmax(final Order ev) {
      return this.qmax$mcB$sp(ev);
   }

   public byte qmax$mcB$sp(final Order ev) {
      if (this.arr$mcB$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         byte result = this.arr$mcB$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcB$sp.length; ++index$macro$1) {
            result = ev.max$mcB$sp(result, this.arr$mcB$sp[index$macro$1]);
         }

         return result;
      }
   }

   public byte qmean(final Field ev) {
      return this.qmean$mcB$sp(ev);
   }

   public byte qmean$mcB$sp(final Field ev) {
      if (this.arr$mcB$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         byte result = BoxesRunTime.unboxToByte(ev.zero());

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcB$sp.length; ++index$macro$1) {
            result = BoxesRunTime.unboxToByte(ev.plus(BoxesRunTime.boxToByte(BoxesRunTime.unboxToByte(ev.div(BoxesRunTime.boxToByte(BoxesRunTime.unboxToByte(ev.times(BoxesRunTime.boxToByte(result), ev.fromInt(index$macro$1)))), ev.fromInt(index$macro$1 + 1)))), BoxesRunTime.boxToByte(BoxesRunTime.unboxToByte(ev.div(BoxesRunTime.boxToByte(this.arr$mcB$sp[index$macro$1]), ev.fromInt(index$macro$1 + 1))))));
         }

         return result;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcB$sp(f, ev);
   }

   public Object qmeanWith$mcB$sp(final Function1 f, final Field ev) {
      if (this.arr$mcB$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         Object result = ev.zero();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcB$sp.length; ++index$macro$1) {
            result = ev.plus(ev.div(ev.times(result, ev.fromInt(index$macro$1)), ev.fromInt(index$macro$1 + 1)), ev.div(f.apply(BoxesRunTime.boxToByte(this.arr$mcB$sp[index$macro$1])), ev.fromInt(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public double qmeanWith$mDc$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDcB$sp(f, ev);
   }

   public double qmeanWith$mDcB$sp(final Function1 f, final Field ev) {
      if (this.arr$mcB$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         double result = ev.zero$mcD$sp();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcB$sp.length; ++index$macro$1) {
            result = ev.plus$mcD$sp(ev.div$mcD$sp(ev.times$mcD$sp(result, ev.fromInt$mcD$sp(index$macro$1)), ev.fromInt$mcD$sp(index$macro$1 + 1)), ev.div$mcD$sp(BoxesRunTime.unboxToDouble(f.apply(BoxesRunTime.boxToByte(this.arr$mcB$sp[index$macro$1]))), ev.fromInt$mcD$sp(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public int qsearch(final byte a, final Order ev) {
      return this.qsearch$mcB$sp(a, ev);
   }

   public int qsearch$mcB$sp(final byte a, final Order ev) {
      return Searching$.MODULE$.search$mBc$sp(this.arr$mcB$sp, a, ev);
   }

   public void qsort(final Order ev, final ClassTag ct) {
      this.qsort$mcB$sp(ev, ct);
   }

   public void qsort$mcB$sp(final Order ev, final ClassTag ct) {
      Sorting$.MODULE$.sort$mBc$sp(this.arr$mcB$sp, ev, ct);
   }

   public void qsortBy(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mcB$sp(f, ev, ct);
   }

   public void qsortBy$mcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort$mBc$sp(this.arr$mcB$sp, ord, ct);
   }

   public void qsortBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZcB$sp(f, ev, ct);
   }

   public void qsortBy$mZcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBZc$sp(f, ev);
      Sorting$.MODULE$.sort$mBc$sp(this.arr$mcB$sp, ord, ct);
   }

   public void qsortBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBcB$sp(f, ev, ct);
   }

   public void qsortBy$mBcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBBc$sp(f, ev);
      Sorting$.MODULE$.sort$mBc$sp(this.arr$mcB$sp, ord, ct);
   }

   public void qsortBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCcB$sp(f, ev, ct);
   }

   public void qsortBy$mCcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBCc$sp(f, ev);
      Sorting$.MODULE$.sort$mBc$sp(this.arr$mcB$sp, ord, ct);
   }

   public void qsortBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDcB$sp(f, ev, ct);
   }

   public void qsortBy$mDcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBDc$sp(f, ev);
      Sorting$.MODULE$.sort$mBc$sp(this.arr$mcB$sp, ord, ct);
   }

   public void qsortBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFcB$sp(f, ev, ct);
   }

   public void qsortBy$mFcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBFc$sp(f, ev);
      Sorting$.MODULE$.sort$mBc$sp(this.arr$mcB$sp, ord, ct);
   }

   public void qsortBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIcB$sp(f, ev, ct);
   }

   public void qsortBy$mIcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBIc$sp(f, ev);
      Sorting$.MODULE$.sort$mBc$sp(this.arr$mcB$sp, ord, ct);
   }

   public void qsortBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJcB$sp(f, ev, ct);
   }

   public void qsortBy$mJcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBJc$sp(f, ev);
      Sorting$.MODULE$.sort$mBc$sp(this.arr$mcB$sp, ord, ct);
   }

   public void qsortBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mScB$sp(f, ev, ct);
   }

   public void qsortBy$mScB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBSc$sp(f, ev);
      Sorting$.MODULE$.sort$mBc$sp(this.arr$mcB$sp, ord, ct);
   }

   public void qsortBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVcB$sp(f, ev, ct);
   }

   public void qsortBy$mVcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBVc$sp(f, ev);
      Sorting$.MODULE$.sort$mBc$sp(this.arr$mcB$sp, ord, ct);
   }

   public void qsortWith(final Function2 f, final ClassTag ct) {
      this.qsortWith$mcB$sp(f, ct);
   }

   public void qsortWith$mcB$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mBc$sp(f);
      Sorting$.MODULE$.sort$mBc$sp(this.arr$mcB$sp, ord, ct);
   }

   public byte[] qsorted(final Order ev, final ClassTag ct) {
      return this.qsorted$mcB$sp(ev, ct);
   }

   public byte[] qsorted$mcB$sp(final Order ev, final ClassTag ct) {
      byte[] arr2 = (byte[])this.arr$mcB$sp.clone();
      Sorting$.MODULE$.sort$mBc$sp(arr2, ev, ct);
      return arr2;
   }

   public byte[] qsortedBy(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mcB$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      byte[] arr2 = (byte[])this.arr$mcB$sp.clone();
      Sorting$.MODULE$.sort$mBc$sp(arr2, ord, ct);
      return arr2;
   }

   public byte[] qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mZcB$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mZcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBZc$sp(f, ev);
      byte[] arr2 = (byte[])this.arr$mcB$sp.clone();
      Sorting$.MODULE$.sort$mBc$sp(arr2, ord, ct);
      return arr2;
   }

   public byte[] qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mBcB$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mBcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBBc$sp(f, ev);
      byte[] arr2 = (byte[])this.arr$mcB$sp.clone();
      Sorting$.MODULE$.sort$mBc$sp(arr2, ord, ct);
      return arr2;
   }

   public byte[] qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mCcB$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mCcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBCc$sp(f, ev);
      byte[] arr2 = (byte[])this.arr$mcB$sp.clone();
      Sorting$.MODULE$.sort$mBc$sp(arr2, ord, ct);
      return arr2;
   }

   public byte[] qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mDcB$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mDcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBDc$sp(f, ev);
      byte[] arr2 = (byte[])this.arr$mcB$sp.clone();
      Sorting$.MODULE$.sort$mBc$sp(arr2, ord, ct);
      return arr2;
   }

   public byte[] qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mFcB$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mFcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBFc$sp(f, ev);
      byte[] arr2 = (byte[])this.arr$mcB$sp.clone();
      Sorting$.MODULE$.sort$mBc$sp(arr2, ord, ct);
      return arr2;
   }

   public byte[] qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mIcB$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mIcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBIc$sp(f, ev);
      byte[] arr2 = (byte[])this.arr$mcB$sp.clone();
      Sorting$.MODULE$.sort$mBc$sp(arr2, ord, ct);
      return arr2;
   }

   public byte[] qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mJcB$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mJcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBJc$sp(f, ev);
      byte[] arr2 = (byte[])this.arr$mcB$sp.clone();
      Sorting$.MODULE$.sort$mBc$sp(arr2, ord, ct);
      return arr2;
   }

   public byte[] qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mScB$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mScB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBSc$sp(f, ev);
      byte[] arr2 = (byte[])this.arr$mcB$sp.clone();
      Sorting$.MODULE$.sort$mBc$sp(arr2, ord, ct);
      return arr2;
   }

   public byte[] qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mVcB$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mVcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBVc$sp(f, ev);
      byte[] arr2 = (byte[])this.arr$mcB$sp.clone();
      Sorting$.MODULE$.sort$mBc$sp(arr2, ord, ct);
      return arr2;
   }

   public byte[] qsortedWith(final Function2 f, final ClassTag ct) {
      return this.qsortedWith$mcB$sp(f, ct);
   }

   public byte[] qsortedWith$mcB$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mBc$sp(f);
      byte[] arr2 = (byte[])this.arr$mcB$sp.clone();
      Sorting$.MODULE$.sort$mBc$sp(arr2, ord, ct);
      return arr2;
   }

   public void qselect(final int k, final Order ev, final ClassTag ct) {
      this.qselect$mcB$sp(k, ev, ct);
   }

   public void qselect$mcB$sp(final int k, final Order ev, final ClassTag ct) {
      Selection$.MODULE$.select$mBc$sp(this.arr$mcB$sp, k, ev, ct);
   }

   public byte[] qselected(final int k, final Order ev, final ClassTag ct) {
      return this.qselected$mcB$sp(k, ev, ct);
   }

   public byte[] qselected$mcB$sp(final int k, final Order ev, final ClassTag ct) {
      byte[] arr2 = (byte[])this.arr$mcB$sp.clone();
      Selection$.MODULE$.select$mBc$sp(arr2, k, ev, ct);
      return arr2;
   }

   public byte[] qshuffled(final Generator gen) {
      return this.qshuffled$mcB$sp(gen);
   }

   public byte[] qshuffled$mcB$sp(final Generator gen) {
      byte[] arr2 = (byte[])this.arr$mcB$sp.clone();
      gen.shuffle$mBc$sp(arr2, gen);
      return arr2;
   }

   public byte[] qsampled(final int n, final Generator gen, final ClassTag ct) {
      return this.qsampled$mcB$sp(n, gen, ct);
   }

   public byte[] qsampled$mcB$sp(final int n, final Generator gen, final ClassTag ct) {
      return gen.sampleFromArray$mBc$sp(this.arr$mcB$sp, n, ct, gen);
   }

   public ArrayOps$mcB$sp(final byte[] arr$mcB$sp) {
      super(arr$mcB$sp);
      this.arr$mcB$sp = arr$mcB$sp;
   }
}
