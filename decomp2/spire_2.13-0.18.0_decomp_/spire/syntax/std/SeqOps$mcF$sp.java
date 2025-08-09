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
import scala.runtime.FloatRef;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction1;
import spire.algebra.NRoot;
import spire.math.QuickSort$;
import spire.math.Searching$;
import spire.math.Selection$;
import spire.math.Sorting$;
import spire.random.Generator;

public final class SeqOps$mcF$sp extends SeqOps {
   private final Iterable as;

   public float qsum(final AdditiveMonoid ev) {
      return this.qsum$mcF$sp(ev);
   }

   public float qsum$mcF$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToFloat(this.spire$syntax$std$SeqOps$$as.foldLeft(BoxesRunTime.boxToFloat(ev.zero$mcF$sp()), (x, y) -> BoxesRunTime.boxToFloat($anonfun$qsum$5(ev, BoxesRunTime.unboxToFloat(x), BoxesRunTime.unboxToFloat(y)))));
   }

   public float qproduct(final MultiplicativeMonoid ev) {
      return this.qproduct$mcF$sp(ev);
   }

   public float qproduct$mcF$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToFloat(this.spire$syntax$std$SeqOps$$as.foldLeft(BoxesRunTime.boxToFloat(ev.one$mcF$sp()), (x, y) -> BoxesRunTime.boxToFloat($anonfun$qproduct$5(ev, BoxesRunTime.unboxToFloat(x), BoxesRunTime.unboxToFloat(y)))));
   }

   public float qcombine(final Monoid ev) {
      return this.qcombine$mcF$sp(ev);
   }

   public float qcombine$mcF$sp(final Monoid ev) {
      return BoxesRunTime.unboxToFloat(this.spire$syntax$std$SeqOps$$as.foldLeft(BoxesRunTime.boxToFloat(ev.empty$mcF$sp()), (x, y) -> BoxesRunTime.boxToFloat($anonfun$qcombine$5(ev, BoxesRunTime.unboxToFloat(x), BoxesRunTime.unboxToFloat(y)))));
   }

   public float qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return this.qnorm$mcF$sp(p, ev, s, nr);
   }

   public float qnorm$mcF$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return nr.nroot$mcF$sp(BoxesRunTime.unboxToFloat(this.spire$syntax$std$SeqOps$$as.foldLeft(BoxesRunTime.boxToFloat(ev.one$mcF$sp()), (x$19, x$20) -> BoxesRunTime.boxToFloat($anonfun$qnorm$5(ev, s, p, BoxesRunTime.unboxToFloat(x$19), BoxesRunTime.unboxToFloat(x$20))))), p);
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcF$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcF$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return nr.nroot(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (t, a) -> $anonfun$qnormWith$5(ev, s, f, p, t, BoxesRunTime.unboxToFloat(a))), p);
   }

   public Seq pmin(final PartialOrder ev) {
      return this.pmin$mcF$sp(ev);
   }

   public Seq pmin$mcF$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, ev);
   }

   public Seq pmax(final PartialOrder ev) {
      return this.pmax$mcF$sp(ev);
   }

   public Seq pmax$mcF$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, spire.algebra.package$.MODULE$.PartialOrder().reverse$mFc$sp(ev));
   }

   public float qmin(final Order ev) {
      return this.qmin$mcF$sp(ev);
   }

   public float qmin$mcF$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return BoxesRunTime.unboxToFloat(this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (x, y) -> BoxesRunTime.boxToFloat($anonfun$qmin$5(ev, BoxesRunTime.unboxToFloat(x), BoxesRunTime.unboxToFloat(y)))));
      }
   }

   public float qmax(final Order ev) {
      return this.qmax$mcF$sp(ev);
   }

   public float qmax$mcF$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return BoxesRunTime.unboxToFloat(this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (x, y) -> BoxesRunTime.boxToFloat($anonfun$qmax$5(ev, BoxesRunTime.unboxToFloat(x), BoxesRunTime.unboxToFloat(y)))));
      }
   }

   public float qmean(final Field ev) {
      return this.qmean$mcF$sp(ev);
   }

   public float qmean$mcF$sp(final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         FloatRef mean = FloatRef.create(ev.zero$mcF$sp());
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((JFunction1.mcVF.sp)(a) -> {
            float t = ev.div$mcF$sp(ev.times$mcF$sp(mean.elem, ev.fromInt$mcF$sp(i.elem)), ev.fromInt$mcF$sp(j.elem));
            float z = ev.div$mcF$sp(a, ev.fromInt$mcF$sp(j.elem));
            mean.elem = ev.plus$mcF$sp(t, z);
            ++i.elem;
            ++j.elem;
         });
         return mean.elem;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcF$sp(f, ev);
   }

   public Object qmeanWith$mcF$sp(final Function1 f, final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         ObjectRef mean = ObjectRef.create(ev.zero());
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((JFunction1.mcVF.sp)(a) -> {
            Object t = ev.div(ev.times(mean.elem, ev.fromInt(i.elem)), ev.fromInt(j.elem));
            Object z = ev.div(f.apply(BoxesRunTime.boxToFloat(a)), ev.fromInt(j.elem));
            mean.elem = ev.plus(t, z);
            ++i.elem;
            ++j.elem;
         });
         return mean.elem;
      }
   }

   public Iterable fromArray(final float[] arr, final Factory cbf) {
      return this.fromArray$mcF$sp(arr, cbf);
   }

   public Iterable fromArray$mcF$sp(final float[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(arr.length);

      for(int index$macro$1 = 0; index$macro$1 < arr.length; ++index$macro$1) {
         b.$plus$eq(BoxesRunTime.boxToFloat(arr[index$macro$1]));
      }

      return (Iterable)b.result();
   }

   public Iterable fromSizeAndArray(final int size, final float[] arr, final Factory cbf) {
      return this.fromSizeAndArray$mcF$sp(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcF$sp(final int size, final float[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(size);

      for(int index$macro$1 = 0; index$macro$1 < size; ++index$macro$1) {
         b.$plus$eq(BoxesRunTime.boxToFloat(arr[index$macro$1]));
      }

      return (Iterable)b.result();
   }

   public Iterable qsorted(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted$mcF$sp(ev, ct, cbf);
   }

   public Iterable qsorted$mcF$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      float[] arr = (float[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mFc$sp(arr, ev, ct);
      return this.fromArray$mcF$sp(arr, cbf);
   }

   public Iterable qsortedBy(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mcF$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      float[] arr = (float[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mFc$sp(arr, ord, ct);
      return this.fromArray$mcF$sp(arr, cbf);
   }

   public Iterable qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZcF$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFZc$sp(f, ev);
      float[] arr = (float[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mFc$sp(arr, ord, ct);
      return this.fromArray$mcF$sp(arr, cbf);
   }

   public Iterable qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBcF$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFBc$sp(f, ev);
      float[] arr = (float[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mFc$sp(arr, ord, ct);
      return this.fromArray$mcF$sp(arr, cbf);
   }

   public Iterable qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCcF$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFCc$sp(f, ev);
      float[] arr = (float[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mFc$sp(arr, ord, ct);
      return this.fromArray$mcF$sp(arr, cbf);
   }

   public Iterable qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDcF$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFDc$sp(f, ev);
      float[] arr = (float[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mFc$sp(arr, ord, ct);
      return this.fromArray$mcF$sp(arr, cbf);
   }

   public Iterable qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFcF$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFFc$sp(f, ev);
      float[] arr = (float[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mFc$sp(arr, ord, ct);
      return this.fromArray$mcF$sp(arr, cbf);
   }

   public Iterable qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIcF$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFIc$sp(f, ev);
      float[] arr = (float[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mFc$sp(arr, ord, ct);
      return this.fromArray$mcF$sp(arr, cbf);
   }

   public Iterable qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJcF$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFJc$sp(f, ev);
      float[] arr = (float[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mFc$sp(arr, ord, ct);
      return this.fromArray$mcF$sp(arr, cbf);
   }

   public Iterable qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mScF$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFSc$sp(f, ev);
      float[] arr = (float[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mFc$sp(arr, ord, ct);
      return this.fromArray$mcF$sp(arr, cbf);
   }

   public Iterable qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVcF$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mFVc$sp(f, ev);
      float[] arr = (float[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mFc$sp(arr, ord, ct);
      return this.fromArray$mcF$sp(arr, cbf);
   }

   public Iterable qsortedWith(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith$mcF$sp(f, ct, cbf);
   }

   public Iterable qsortedWith$mcF$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mFc$sp(f);
      float[] arr = (float[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mFc$sp(arr, ord, ct);
      return this.fromArray$mcF$sp(arr, cbf);
   }

   public Iterable qselected(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected$mcF$sp(k, ev, ct, cbf);
   }

   public Iterable qselected$mcF$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      float[] arr = (float[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Selection$.MODULE$.select$mFc$sp(arr, k, ev, ct);
      return this.fromArray$mcF$sp(arr, cbf);
   }

   public Iterable qselectk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk$mcF$sp(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcF$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      float[] arr = (float[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         var10000 = this.fromArray$mcF$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mFc$sp(arr, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcF$sp(k, arr, cbf);
      }

      return var10000;
   }

   public Iterable qtopk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk$mcF$sp(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcF$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      float[] arr = (float[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         Sorting$.MODULE$.sort$mFc$sp(arr, ev, ct);
         var10000 = this.fromArray$mcF$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mFc$sp(arr, k, ev, ct);
         QuickSort$.MODULE$.qsort$mFc$sp(arr, 0, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcF$sp(k, arr, cbf);
      }

      return var10000;
   }

   public float qchoose(final Generator gen) {
      return this.qchoose$mcF$sp(gen);
   }

   public float qchoose$mcF$sp(final Generator gen) {
      return BoxesRunTime.unboxToFloat(gen.chooseFromIterable(this.spire$syntax$std$SeqOps$$as, gen));
   }

   // $FF: synthetic method
   public static final float $anonfun$qsum$5(final AdditiveMonoid ev$37, final float x, final float y) {
      return ev$37.plus$mcF$sp(x, y);
   }

   // $FF: synthetic method
   public static final float $anonfun$qproduct$5(final MultiplicativeMonoid ev$38, final float x, final float y) {
      return ev$38.times$mcF$sp(x, y);
   }

   // $FF: synthetic method
   public static final float $anonfun$qcombine$5(final Monoid ev$39, final float x, final float y) {
      return ev$39.combine$mcF$sp(x, y);
   }

   // $FF: synthetic method
   public static final float $anonfun$qnorm$5(final Field ev$40, final Signed s$9, final int p$9, final float x$19, final float x$20) {
      return ev$40.plus$mcF$sp(x$19, ev$40.pow$mcF$sp(s$9.abs$mcF$sp(x$20), p$9));
   }

   // $FF: synthetic method
   public static final Object $anonfun$qnormWith$5(final Field ev$41, final Signed s$10, final Function1 f$9, final int p$10, final Object t, final float a) {
      return ev$41.plus(t, ev$41.pow(s$10.abs(f$9.apply(BoxesRunTime.boxToFloat(a))), p$10));
   }

   // $FF: synthetic method
   public static final float $anonfun$qmin$5(final Order ev$42, final float x, final float y) {
      return ev$42.min$mcF$sp(x, y);
   }

   // $FF: synthetic method
   public static final float $anonfun$qmax$5(final Order ev$43, final float x, final float y) {
      return ev$43.max$mcF$sp(x, y);
   }

   public SeqOps$mcF$sp(final Iterable as) {
      super(as);
      this.as = as;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
