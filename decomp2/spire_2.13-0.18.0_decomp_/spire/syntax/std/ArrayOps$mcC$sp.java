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

public final class ArrayOps$mcC$sp extends ArrayOps {
   public final char[] arr$mcC$sp;

   public char qsum(final AdditiveMonoid ev) {
      return this.qsum$mcC$sp(ev);
   }

   public char qsum$mcC$sp(final AdditiveMonoid ev) {
      char result = BoxesRunTime.unboxToChar(ev.zero());

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcC$sp.length; ++index$macro$1) {
         result = BoxesRunTime.unboxToChar(ev.plus(BoxesRunTime.boxToCharacter(result), BoxesRunTime.boxToCharacter(this.arr$mcC$sp[index$macro$1])));
      }

      return result;
   }

   public char qproduct(final MultiplicativeMonoid ev) {
      return this.qproduct$mcC$sp(ev);
   }

   public char qproduct$mcC$sp(final MultiplicativeMonoid ev) {
      char result = BoxesRunTime.unboxToChar(ev.one());

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcC$sp.length; ++index$macro$1) {
         result = BoxesRunTime.unboxToChar(ev.times(BoxesRunTime.boxToCharacter(result), BoxesRunTime.boxToCharacter(this.arr$mcC$sp[index$macro$1])));
      }

      return result;
   }

   public char qcombine(final Monoid ev) {
      return this.qcombine$mcC$sp(ev);
   }

   public char qcombine$mcC$sp(final Monoid ev) {
      char result = BoxesRunTime.unboxToChar(ev.empty());

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcC$sp.length; ++index$macro$1) {
         result = BoxesRunTime.unboxToChar(ev.combine(BoxesRunTime.boxToCharacter(result), BoxesRunTime.boxToCharacter(this.arr$mcC$sp[index$macro$1])));
      }

      return result;
   }

   public char qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return this.qnorm$mcC$sp(p, ev, s, nr);
   }

   public char qnorm$mcC$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      char result = BoxesRunTime.unboxToChar(ev.one());

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcC$sp.length; ++index$macro$1) {
         result = BoxesRunTime.unboxToChar(ev.plus(BoxesRunTime.boxToCharacter(result), BoxesRunTime.boxToCharacter(BoxesRunTime.unboxToChar(ev.pow(BoxesRunTime.boxToCharacter(BoxesRunTime.unboxToChar(s.abs(BoxesRunTime.boxToCharacter(this.arr$mcC$sp[index$macro$1])))), p)))));
      }

      return BoxesRunTime.unboxToChar(nr.nroot(BoxesRunTime.boxToCharacter(result), p));
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcC$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcC$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      Object result = ev.one();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcC$sp.length; ++index$macro$1) {
         result = ev.plus(result, ev.pow(s.abs(f.apply(BoxesRunTime.boxToCharacter(this.arr$mcC$sp[index$macro$1]))), p));
      }

      return nr.nroot(result, p);
   }

   public double qnormWith$mDc$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDcC$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcC$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      double result = ev.one$mcD$sp();

      for(int index$macro$1 = 0; index$macro$1 < this.arr$mcC$sp.length; ++index$macro$1) {
         result = ev.plus$mcD$sp(result, ev.pow$mcD$sp(s.abs$mcD$sp(BoxesRunTime.unboxToDouble(f.apply(BoxesRunTime.boxToCharacter(this.arr$mcC$sp[index$macro$1])))), p));
      }

      return nr.nroot$mcD$sp(result, p);
   }

   public char qmin(final Order ev) {
      return this.qmin$mcC$sp(ev);
   }

   public char qmin$mcC$sp(final Order ev) {
      if (this.arr$mcC$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         char result = this.arr$mcC$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcC$sp.length; ++index$macro$1) {
            result = ev.min$mcC$sp(result, this.arr$mcC$sp[index$macro$1]);
         }

         return result;
      }
   }

   public char qmax(final Order ev) {
      return this.qmax$mcC$sp(ev);
   }

   public char qmax$mcC$sp(final Order ev) {
      if (this.arr$mcC$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         char result = this.arr$mcC$sp[0];

         for(int index$macro$1 = 1; index$macro$1 < this.arr$mcC$sp.length; ++index$macro$1) {
            result = ev.max$mcC$sp(result, this.arr$mcC$sp[index$macro$1]);
         }

         return result;
      }
   }

   public char qmean(final Field ev) {
      return this.qmean$mcC$sp(ev);
   }

   public char qmean$mcC$sp(final Field ev) {
      if (this.arr$mcC$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         char result = BoxesRunTime.unboxToChar(ev.zero());

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcC$sp.length; ++index$macro$1) {
            result = BoxesRunTime.unboxToChar(ev.plus(BoxesRunTime.boxToCharacter(BoxesRunTime.unboxToChar(ev.div(BoxesRunTime.boxToCharacter(BoxesRunTime.unboxToChar(ev.times(BoxesRunTime.boxToCharacter(result), ev.fromInt(index$macro$1)))), ev.fromInt(index$macro$1 + 1)))), BoxesRunTime.boxToCharacter(BoxesRunTime.unboxToChar(ev.div(BoxesRunTime.boxToCharacter(this.arr$mcC$sp[index$macro$1]), ev.fromInt(index$macro$1 + 1))))));
         }

         return result;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcC$sp(f, ev);
   }

   public Object qmeanWith$mcC$sp(final Function1 f, final Field ev) {
      if (this.arr$mcC$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         Object result = ev.zero();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcC$sp.length; ++index$macro$1) {
            result = ev.plus(ev.div(ev.times(result, ev.fromInt(index$macro$1)), ev.fromInt(index$macro$1 + 1)), ev.div(f.apply(BoxesRunTime.boxToCharacter(this.arr$mcC$sp[index$macro$1])), ev.fromInt(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public double qmeanWith$mDc$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDcC$sp(f, ev);
   }

   public double qmeanWith$mDcC$sp(final Function1 f, final Field ev) {
      if (this.arr$mcC$sp.length == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         double result = ev.zero$mcD$sp();

         for(int index$macro$1 = 0; index$macro$1 < this.arr$mcC$sp.length; ++index$macro$1) {
            result = ev.plus$mcD$sp(ev.div$mcD$sp(ev.times$mcD$sp(result, ev.fromInt$mcD$sp(index$macro$1)), ev.fromInt$mcD$sp(index$macro$1 + 1)), ev.div$mcD$sp(BoxesRunTime.unboxToDouble(f.apply(BoxesRunTime.boxToCharacter(this.arr$mcC$sp[index$macro$1]))), ev.fromInt$mcD$sp(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public int qsearch(final char a, final Order ev) {
      return this.qsearch$mcC$sp(a, ev);
   }

   public int qsearch$mcC$sp(final char a, final Order ev) {
      return Searching$.MODULE$.search$mCc$sp(this.arr$mcC$sp, a, ev);
   }

   public void qsort(final Order ev, final ClassTag ct) {
      this.qsort$mcC$sp(ev, ct);
   }

   public void qsort$mcC$sp(final Order ev, final ClassTag ct) {
      Sorting$.MODULE$.sort$mCc$sp(this.arr$mcC$sp, ev, ct);
   }

   public void qsortBy(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mcC$sp(f, ev, ct);
   }

   public void qsortBy$mcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort$mCc$sp(this.arr$mcC$sp, ord, ct);
   }

   public void qsortBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZcC$sp(f, ev, ct);
   }

   public void qsortBy$mZcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCZc$sp(f, ev);
      Sorting$.MODULE$.sort$mCc$sp(this.arr$mcC$sp, ord, ct);
   }

   public void qsortBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBcC$sp(f, ev, ct);
   }

   public void qsortBy$mBcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCBc$sp(f, ev);
      Sorting$.MODULE$.sort$mCc$sp(this.arr$mcC$sp, ord, ct);
   }

   public void qsortBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCcC$sp(f, ev, ct);
   }

   public void qsortBy$mCcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCCc$sp(f, ev);
      Sorting$.MODULE$.sort$mCc$sp(this.arr$mcC$sp, ord, ct);
   }

   public void qsortBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDcC$sp(f, ev, ct);
   }

   public void qsortBy$mDcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCDc$sp(f, ev);
      Sorting$.MODULE$.sort$mCc$sp(this.arr$mcC$sp, ord, ct);
   }

   public void qsortBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFcC$sp(f, ev, ct);
   }

   public void qsortBy$mFcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCFc$sp(f, ev);
      Sorting$.MODULE$.sort$mCc$sp(this.arr$mcC$sp, ord, ct);
   }

   public void qsortBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIcC$sp(f, ev, ct);
   }

   public void qsortBy$mIcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCIc$sp(f, ev);
      Sorting$.MODULE$.sort$mCc$sp(this.arr$mcC$sp, ord, ct);
   }

   public void qsortBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJcC$sp(f, ev, ct);
   }

   public void qsortBy$mJcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCJc$sp(f, ev);
      Sorting$.MODULE$.sort$mCc$sp(this.arr$mcC$sp, ord, ct);
   }

   public void qsortBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mScC$sp(f, ev, ct);
   }

   public void qsortBy$mScC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCSc$sp(f, ev);
      Sorting$.MODULE$.sort$mCc$sp(this.arr$mcC$sp, ord, ct);
   }

   public void qsortBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVcC$sp(f, ev, ct);
   }

   public void qsortBy$mVcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCVc$sp(f, ev);
      Sorting$.MODULE$.sort$mCc$sp(this.arr$mcC$sp, ord, ct);
   }

   public void qsortWith(final Function2 f, final ClassTag ct) {
      this.qsortWith$mcC$sp(f, ct);
   }

   public void qsortWith$mcC$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mCc$sp(f);
      Sorting$.MODULE$.sort$mCc$sp(this.arr$mcC$sp, ord, ct);
   }

   public char[] qsorted(final Order ev, final ClassTag ct) {
      return this.qsorted$mcC$sp(ev, ct);
   }

   public char[] qsorted$mcC$sp(final Order ev, final ClassTag ct) {
      char[] arr2 = (char[])this.arr$mcC$sp.clone();
      Sorting$.MODULE$.sort$mCc$sp(arr2, ev, ct);
      return arr2;
   }

   public char[] qsortedBy(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mcC$sp(f, ev, ct);
   }

   public char[] qsortedBy$mcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      char[] arr2 = (char[])this.arr$mcC$sp.clone();
      Sorting$.MODULE$.sort$mCc$sp(arr2, ord, ct);
      return arr2;
   }

   public char[] qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mZcC$sp(f, ev, ct);
   }

   public char[] qsortedBy$mZcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCZc$sp(f, ev);
      char[] arr2 = (char[])this.arr$mcC$sp.clone();
      Sorting$.MODULE$.sort$mCc$sp(arr2, ord, ct);
      return arr2;
   }

   public char[] qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mBcC$sp(f, ev, ct);
   }

   public char[] qsortedBy$mBcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCBc$sp(f, ev);
      char[] arr2 = (char[])this.arr$mcC$sp.clone();
      Sorting$.MODULE$.sort$mCc$sp(arr2, ord, ct);
      return arr2;
   }

   public char[] qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mCcC$sp(f, ev, ct);
   }

   public char[] qsortedBy$mCcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCCc$sp(f, ev);
      char[] arr2 = (char[])this.arr$mcC$sp.clone();
      Sorting$.MODULE$.sort$mCc$sp(arr2, ord, ct);
      return arr2;
   }

   public char[] qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mDcC$sp(f, ev, ct);
   }

   public char[] qsortedBy$mDcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCDc$sp(f, ev);
      char[] arr2 = (char[])this.arr$mcC$sp.clone();
      Sorting$.MODULE$.sort$mCc$sp(arr2, ord, ct);
      return arr2;
   }

   public char[] qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mFcC$sp(f, ev, ct);
   }

   public char[] qsortedBy$mFcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCFc$sp(f, ev);
      char[] arr2 = (char[])this.arr$mcC$sp.clone();
      Sorting$.MODULE$.sort$mCc$sp(arr2, ord, ct);
      return arr2;
   }

   public char[] qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mIcC$sp(f, ev, ct);
   }

   public char[] qsortedBy$mIcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCIc$sp(f, ev);
      char[] arr2 = (char[])this.arr$mcC$sp.clone();
      Sorting$.MODULE$.sort$mCc$sp(arr2, ord, ct);
      return arr2;
   }

   public char[] qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mJcC$sp(f, ev, ct);
   }

   public char[] qsortedBy$mJcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCJc$sp(f, ev);
      char[] arr2 = (char[])this.arr$mcC$sp.clone();
      Sorting$.MODULE$.sort$mCc$sp(arr2, ord, ct);
      return arr2;
   }

   public char[] qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mScC$sp(f, ev, ct);
   }

   public char[] qsortedBy$mScC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCSc$sp(f, ev);
      char[] arr2 = (char[])this.arr$mcC$sp.clone();
      Sorting$.MODULE$.sort$mCc$sp(arr2, ord, ct);
      return arr2;
   }

   public char[] qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return this.qsortedBy$mVcC$sp(f, ev, ct);
   }

   public char[] qsortedBy$mVcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCVc$sp(f, ev);
      char[] arr2 = (char[])this.arr$mcC$sp.clone();
      Sorting$.MODULE$.sort$mCc$sp(arr2, ord, ct);
      return arr2;
   }

   public char[] qsortedWith(final Function2 f, final ClassTag ct) {
      return this.qsortedWith$mcC$sp(f, ct);
   }

   public char[] qsortedWith$mcC$sp(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mCc$sp(f);
      char[] arr2 = (char[])this.arr$mcC$sp.clone();
      Sorting$.MODULE$.sort$mCc$sp(arr2, ord, ct);
      return arr2;
   }

   public void qselect(final int k, final Order ev, final ClassTag ct) {
      this.qselect$mcC$sp(k, ev, ct);
   }

   public void qselect$mcC$sp(final int k, final Order ev, final ClassTag ct) {
      Selection$.MODULE$.select$mCc$sp(this.arr$mcC$sp, k, ev, ct);
   }

   public char[] qselected(final int k, final Order ev, final ClassTag ct) {
      return this.qselected$mcC$sp(k, ev, ct);
   }

   public char[] qselected$mcC$sp(final int k, final Order ev, final ClassTag ct) {
      char[] arr2 = (char[])this.arr$mcC$sp.clone();
      Selection$.MODULE$.select$mCc$sp(arr2, k, ev, ct);
      return arr2;
   }

   public char[] qshuffled(final Generator gen) {
      return this.qshuffled$mcC$sp(gen);
   }

   public char[] qshuffled$mcC$sp(final Generator gen) {
      char[] arr2 = (char[])this.arr$mcC$sp.clone();
      gen.shuffle$mCc$sp(arr2, gen);
      return arr2;
   }

   public char[] qsampled(final int n, final Generator gen, final ClassTag ct) {
      return this.qsampled$mcC$sp(n, gen, ct);
   }

   public char[] qsampled$mcC$sp(final int n, final Generator gen, final ClassTag ct) {
      return gen.sampleFromArray$mCc$sp(this.arr$mcC$sp, n, ct, gen);
   }

   public ArrayOps$mcC$sp(final char[] arr$mcC$sp) {
      super(arr$mcC$sp);
      this.arr$mcC$sp = arr$mcC$sp;
   }
}
