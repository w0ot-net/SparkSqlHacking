package spire.syntax.std;

import algebra.ring.AdditiveMonoid;
import algebra.ring.Field;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.Signed;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;
import spire.algebra.NRoot;
import spire.math.QuickSort$;
import spire.math.Searching$;
import spire.math.Selection$;
import spire.math.Sorting$;
import spire.random.Generator;

public final class SeqOps$mcD$sp extends SeqOps {
   private final Iterable as;

   public double qsum(final AdditiveMonoid ev) {
      return this.qsum$mcD$sp(ev);
   }

   public double qsum$mcD$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToDouble(this.spire$syntax$std$SeqOps$$as.foldLeft(BoxesRunTime.boxToDouble(ev.zero$mcD$sp()), (JFunction2.mcDDD.sp)(x, y) -> ev.plus$mcD$sp(x, y)));
   }

   public double qproduct(final MultiplicativeMonoid ev) {
      return this.qproduct$mcD$sp(ev);
   }

   public double qproduct$mcD$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToDouble(this.spire$syntax$std$SeqOps$$as.foldLeft(BoxesRunTime.boxToDouble(ev.one$mcD$sp()), (JFunction2.mcDDD.sp)(x, y) -> ev.times$mcD$sp(x, y)));
   }

   public double qcombine(final Monoid ev) {
      return this.qcombine$mcD$sp(ev);
   }

   public double qcombine$mcD$sp(final Monoid ev) {
      return BoxesRunTime.unboxToDouble(this.spire$syntax$std$SeqOps$$as.foldLeft(BoxesRunTime.boxToDouble(ev.empty$mcD$sp()), (JFunction2.mcDDD.sp)(x, y) -> ev.combine$mcD$sp(x, y)));
   }

   public double qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return this.qnorm$mcD$sp(p, ev, s, nr);
   }

   public double qnorm$mcD$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return nr.nroot$mcD$sp(BoxesRunTime.unboxToDouble(this.spire$syntax$std$SeqOps$$as.foldLeft(BoxesRunTime.boxToDouble(ev.one$mcD$sp()), (JFunction2.mcDDD.sp)(x$19, x$20) -> ev.plus$mcD$sp(x$19, ev.pow$mcD$sp(s.abs$mcD$sp(x$20), p)))), p);
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcD$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcD$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return nr.nroot(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (t, a) -> $anonfun$qnormWith$4(ev, s, f, p, t, BoxesRunTime.unboxToDouble(a))), p);
   }

   public Seq pmin(final PartialOrder ev) {
      return this.pmin$mcD$sp(ev);
   }

   public Seq pmin$mcD$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, ev);
   }

   public Seq pmax(final PartialOrder ev) {
      return this.pmax$mcD$sp(ev);
   }

   public Seq pmax$mcD$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, spire.algebra.package$.MODULE$.PartialOrder().reverse$mDc$sp(ev));
   }

   public double qmin(final Order ev) {
      return this.qmin$mcD$sp(ev);
   }

   public double qmin$mcD$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return BoxesRunTime.unboxToDouble(this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (JFunction2.mcDDD.sp)(x, y) -> ev.min$mcD$sp(x, y)));
      }
   }

   public double qmax(final Order ev) {
      return this.qmax$mcD$sp(ev);
   }

   public double qmax$mcD$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return BoxesRunTime.unboxToDouble(this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (JFunction2.mcDDD.sp)(x, y) -> ev.max$mcD$sp(x, y)));
      }
   }

   public double qmean(final Field ev) {
      return this.qmean$mcD$sp(ev);
   }

   public double qmean$mcD$sp(final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         DoubleRef mean = DoubleRef.create(ev.zero$mcD$sp());
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((JFunction1.mcVD.sp)(a) -> {
            double t = ev.div$mcD$sp(ev.times$mcD$sp(mean.elem, ev.fromInt$mcD$sp(i.elem)), ev.fromInt$mcD$sp(j.elem));
            double z = ev.div$mcD$sp(a, ev.fromInt$mcD$sp(j.elem));
            mean.elem = ev.plus$mcD$sp(t, z);
            ++i.elem;
            ++j.elem;
         });
         return mean.elem;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcD$sp(f, ev);
   }

   public Object qmeanWith$mcD$sp(final Function1 f, final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         ObjectRef mean = ObjectRef.create(ev.zero());
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((JFunction1.mcVD.sp)(a) -> {
            Object t = ev.div(ev.times(mean.elem, ev.fromInt(i.elem)), ev.fromInt(j.elem));
            Object z = ev.div(f.apply(BoxesRunTime.boxToDouble(a)), ev.fromInt(j.elem));
            mean.elem = ev.plus(t, z);
            ++i.elem;
            ++j.elem;
         });
         return mean.elem;
      }
   }

   public Iterable fromArray(final double[] arr, final Factory cbf) {
      return this.fromArray$mcD$sp(arr, cbf);
   }

   public Iterable fromArray$mcD$sp(final double[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(arr.length);

      for(int index$macro$1 = 0; index$macro$1 < arr.length; ++index$macro$1) {
         b.$plus$eq(BoxesRunTime.boxToDouble(arr[index$macro$1]));
      }

      return (Iterable)b.result();
   }

   public Iterable fromSizeAndArray(final int size, final double[] arr, final Factory cbf) {
      return this.fromSizeAndArray$mcD$sp(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcD$sp(final int size, final double[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(size);

      for(int index$macro$1 = 0; index$macro$1 < size; ++index$macro$1) {
         b.$plus$eq(BoxesRunTime.boxToDouble(arr[index$macro$1]));
      }

      return (Iterable)b.result();
   }

   public Iterable qsorted(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted$mcD$sp(ev, ct, cbf);
   }

   public Iterable qsorted$mcD$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      double[] arr = (double[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mDc$sp(arr, ev, ct);
      return this.fromArray$mcD$sp(arr, cbf);
   }

   public Iterable qsortedBy(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mcD$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      double[] arr = (double[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mDc$sp(arr, ord, ct);
      return this.fromArray$mcD$sp(arr, cbf);
   }

   public Iterable qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZcD$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDZc$sp(f, ev);
      double[] arr = (double[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mDc$sp(arr, ord, ct);
      return this.fromArray$mcD$sp(arr, cbf);
   }

   public Iterable qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBcD$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDBc$sp(f, ev);
      double[] arr = (double[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mDc$sp(arr, ord, ct);
      return this.fromArray$mcD$sp(arr, cbf);
   }

   public Iterable qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCcD$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDCc$sp(f, ev);
      double[] arr = (double[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mDc$sp(arr, ord, ct);
      return this.fromArray$mcD$sp(arr, cbf);
   }

   public Iterable qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDcD$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDDc$sp(f, ev);
      double[] arr = (double[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mDc$sp(arr, ord, ct);
      return this.fromArray$mcD$sp(arr, cbf);
   }

   public Iterable qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFcD$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDFc$sp(f, ev);
      double[] arr = (double[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mDc$sp(arr, ord, ct);
      return this.fromArray$mcD$sp(arr, cbf);
   }

   public Iterable qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIcD$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDIc$sp(f, ev);
      double[] arr = (double[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mDc$sp(arr, ord, ct);
      return this.fromArray$mcD$sp(arr, cbf);
   }

   public Iterable qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJcD$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDJc$sp(f, ev);
      double[] arr = (double[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mDc$sp(arr, ord, ct);
      return this.fromArray$mcD$sp(arr, cbf);
   }

   public Iterable qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mScD$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDSc$sp(f, ev);
      double[] arr = (double[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mDc$sp(arr, ord, ct);
      return this.fromArray$mcD$sp(arr, cbf);
   }

   public Iterable qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVcD$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mDVc$sp(f, ev);
      double[] arr = (double[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mDc$sp(arr, ord, ct);
      return this.fromArray$mcD$sp(arr, cbf);
   }

   public Iterable qsortedWith(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith$mcD$sp(f, ct, cbf);
   }

   public Iterable qsortedWith$mcD$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mDc$sp(f);
      double[] arr = (double[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mDc$sp(arr, ord, ct);
      return this.fromArray$mcD$sp(arr, cbf);
   }

   public Iterable qselected(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected$mcD$sp(k, ev, ct, cbf);
   }

   public Iterable qselected$mcD$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      double[] arr = (double[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Selection$.MODULE$.select$mDc$sp(arr, k, ev, ct);
      return this.fromArray$mcD$sp(arr, cbf);
   }

   public Iterable qselectk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk$mcD$sp(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcD$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      double[] arr = (double[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         var10000 = this.fromArray$mcD$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mDc$sp(arr, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcD$sp(k, arr, cbf);
      }

      return var10000;
   }

   public Iterable qtopk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk$mcD$sp(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcD$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      double[] arr = (double[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         Sorting$.MODULE$.sort$mDc$sp(arr, ev, ct);
         var10000 = this.fromArray$mcD$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mDc$sp(arr, k, ev, ct);
         QuickSort$.MODULE$.qsort$mDc$sp(arr, 0, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcD$sp(k, arr, cbf);
      }

      return var10000;
   }

   public double qchoose(final Generator gen) {
      return this.qchoose$mcD$sp(gen);
   }

   public double qchoose$mcD$sp(final Generator gen) {
      return BoxesRunTime.unboxToDouble(gen.chooseFromIterable(this.spire$syntax$std$SeqOps$$as, gen));
   }

   // $FF: synthetic method
   public static final Object $anonfun$qnormWith$4(final Field ev$32, final Signed s$8, final Function1 f$7, final int p$8, final Object t, final double a) {
      return ev$32.plus(t, ev$32.pow(s$8.abs(f$7.apply(BoxesRunTime.boxToDouble(a))), p$8));
   }

   public SeqOps$mcD$sp(final Iterable as) {
      super(as);
      this.as = as;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
