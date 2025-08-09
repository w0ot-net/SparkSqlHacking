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
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import scala.runtime.ShortRef;
import spire.algebra.NRoot;
import spire.math.QuickSort$;
import spire.math.Searching$;
import spire.math.Selection$;
import spire.math.Sorting$;
import spire.random.Generator;

public final class SeqOps$mcS$sp extends SeqOps {
   private final Iterable as;

   public short qsum(final AdditiveMonoid ev) {
      return this.qsum$mcS$sp(ev);
   }

   public short qsum$mcS$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToShort(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.zero(), (x, y) -> BoxesRunTime.boxToShort($anonfun$qsum$8(ev, BoxesRunTime.unboxToShort(x), BoxesRunTime.unboxToShort(y)))));
   }

   public short qproduct(final MultiplicativeMonoid ev) {
      return this.qproduct$mcS$sp(ev);
   }

   public short qproduct$mcS$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToShort(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (x, y) -> BoxesRunTime.boxToShort($anonfun$qproduct$8(ev, BoxesRunTime.unboxToShort(x), BoxesRunTime.unboxToShort(y)))));
   }

   public short qcombine(final Monoid ev) {
      return this.qcombine$mcS$sp(ev);
   }

   public short qcombine$mcS$sp(final Monoid ev) {
      return BoxesRunTime.unboxToShort(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.empty(), (x, y) -> BoxesRunTime.boxToShort($anonfun$qcombine$8(ev, BoxesRunTime.unboxToShort(x), BoxesRunTime.unboxToShort(y)))));
   }

   public short qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return this.qnorm$mcS$sp(p, ev, s, nr);
   }

   public short qnorm$mcS$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToShort(nr.nroot(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (x$19, x$20) -> BoxesRunTime.boxToShort($anonfun$qnorm$8(ev, s, p, BoxesRunTime.unboxToShort(x$19), BoxesRunTime.unboxToShort(x$20)))), p));
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcS$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcS$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return nr.nroot(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (t, a) -> $anonfun$qnormWith$8(ev, s, f, p, t, BoxesRunTime.unboxToShort(a))), p);
   }

   public Seq pmin(final PartialOrder ev) {
      return this.pmin$mcS$sp(ev);
   }

   public Seq pmin$mcS$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, ev);
   }

   public Seq pmax(final PartialOrder ev) {
      return this.pmax$mcS$sp(ev);
   }

   public Seq pmax$mcS$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, spire.algebra.package$.MODULE$.PartialOrder().reverse$mSc$sp(ev));
   }

   public short qmin(final Order ev) {
      return this.qmin$mcS$sp(ev);
   }

   public short qmin$mcS$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return BoxesRunTime.unboxToShort(this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (x, y) -> BoxesRunTime.boxToShort($anonfun$qmin$8(ev, BoxesRunTime.unboxToShort(x), BoxesRunTime.unboxToShort(y)))));
      }
   }

   public short qmax(final Order ev) {
      return this.qmax$mcS$sp(ev);
   }

   public short qmax$mcS$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return BoxesRunTime.unboxToShort(this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (x, y) -> BoxesRunTime.boxToShort($anonfun$qmax$8(ev, BoxesRunTime.unboxToShort(x), BoxesRunTime.unboxToShort(y)))));
      }
   }

   public short qmean(final Field ev) {
      return this.qmean$mcS$sp(ev);
   }

   public short qmean$mcS$sp(final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         ShortRef mean = ShortRef.create(BoxesRunTime.unboxToShort(ev.zero()));
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((a) -> {
            $anonfun$qmean$8(ev, mean, i, j, BoxesRunTime.unboxToShort(a));
            return BoxedUnit.UNIT;
         });
         return mean.elem;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcS$sp(f, ev);
   }

   public Object qmeanWith$mcS$sp(final Function1 f, final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         ObjectRef mean = ObjectRef.create(ev.zero());
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((a) -> {
            $anonfun$qmeanWith$8(ev, mean, i, j, f, BoxesRunTime.unboxToShort(a));
            return BoxedUnit.UNIT;
         });
         return mean.elem;
      }
   }

   public Iterable fromArray(final short[] arr, final Factory cbf) {
      return this.fromArray$mcS$sp(arr, cbf);
   }

   public Iterable fromArray$mcS$sp(final short[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(arr.length);

      for(int index$macro$1 = 0; index$macro$1 < arr.length; ++index$macro$1) {
         b.$plus$eq(BoxesRunTime.boxToShort(arr[index$macro$1]));
      }

      return (Iterable)b.result();
   }

   public Iterable fromSizeAndArray(final int size, final short[] arr, final Factory cbf) {
      return this.fromSizeAndArray$mcS$sp(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcS$sp(final int size, final short[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(size);

      for(int index$macro$1 = 0; index$macro$1 < size; ++index$macro$1) {
         b.$plus$eq(BoxesRunTime.boxToShort(arr[index$macro$1]));
      }

      return (Iterable)b.result();
   }

   public Iterable qsorted(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted$mcS$sp(ev, ct, cbf);
   }

   public Iterable qsorted$mcS$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      short[] arr = (short[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mSc$sp(arr, ev, ct);
      return this.fromArray$mcS$sp(arr, cbf);
   }

   public Iterable qsortedBy(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mcS$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      short[] arr = (short[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mSc$sp(arr, ord, ct);
      return this.fromArray$mcS$sp(arr, cbf);
   }

   public Iterable qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZcS$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSZc$sp(f, ev);
      short[] arr = (short[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mSc$sp(arr, ord, ct);
      return this.fromArray$mcS$sp(arr, cbf);
   }

   public Iterable qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBcS$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSBc$sp(f, ev);
      short[] arr = (short[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mSc$sp(arr, ord, ct);
      return this.fromArray$mcS$sp(arr, cbf);
   }

   public Iterable qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCcS$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSCc$sp(f, ev);
      short[] arr = (short[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mSc$sp(arr, ord, ct);
      return this.fromArray$mcS$sp(arr, cbf);
   }

   public Iterable qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDcS$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSDc$sp(f, ev);
      short[] arr = (short[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mSc$sp(arr, ord, ct);
      return this.fromArray$mcS$sp(arr, cbf);
   }

   public Iterable qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFcS$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSFc$sp(f, ev);
      short[] arr = (short[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mSc$sp(arr, ord, ct);
      return this.fromArray$mcS$sp(arr, cbf);
   }

   public Iterable qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIcS$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSIc$sp(f, ev);
      short[] arr = (short[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mSc$sp(arr, ord, ct);
      return this.fromArray$mcS$sp(arr, cbf);
   }

   public Iterable qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJcS$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSJc$sp(f, ev);
      short[] arr = (short[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mSc$sp(arr, ord, ct);
      return this.fromArray$mcS$sp(arr, cbf);
   }

   public Iterable qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mScS$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSSc$sp(f, ev);
      short[] arr = (short[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mSc$sp(arr, ord, ct);
      return this.fromArray$mcS$sp(arr, cbf);
   }

   public Iterable qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVcS$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mSVc$sp(f, ev);
      short[] arr = (short[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mSc$sp(arr, ord, ct);
      return this.fromArray$mcS$sp(arr, cbf);
   }

   public Iterable qsortedWith(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith$mcS$sp(f, ct, cbf);
   }

   public Iterable qsortedWith$mcS$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mSc$sp(f);
      short[] arr = (short[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mSc$sp(arr, ord, ct);
      return this.fromArray$mcS$sp(arr, cbf);
   }

   public Iterable qselected(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected$mcS$sp(k, ev, ct, cbf);
   }

   public Iterable qselected$mcS$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      short[] arr = (short[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Selection$.MODULE$.select$mSc$sp(arr, k, ev, ct);
      return this.fromArray$mcS$sp(arr, cbf);
   }

   public Iterable qselectk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk$mcS$sp(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcS$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      short[] arr = (short[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         var10000 = this.fromArray$mcS$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mSc$sp(arr, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcS$sp(k, arr, cbf);
      }

      return var10000;
   }

   public Iterable qtopk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk$mcS$sp(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcS$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      short[] arr = (short[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         Sorting$.MODULE$.sort$mSc$sp(arr, ev, ct);
         var10000 = this.fromArray$mcS$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mSc$sp(arr, k, ev, ct);
         QuickSort$.MODULE$.qsort$mSc$sp(arr, 0, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcS$sp(k, arr, cbf);
      }

      return var10000;
   }

   public short qchoose(final Generator gen) {
      return this.qchoose$mcS$sp(gen);
   }

   public short qchoose$mcS$sp(final Generator gen) {
      return BoxesRunTime.unboxToShort(gen.chooseFromIterable(this.spire$syntax$std$SeqOps$$as, gen));
   }

   // $FF: synthetic method
   public static final short $anonfun$qsum$8(final AdditiveMonoid ev$64, final short x, final short y) {
      return BoxesRunTime.unboxToShort(ev$64.plus(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y)));
   }

   // $FF: synthetic method
   public static final short $anonfun$qproduct$8(final MultiplicativeMonoid ev$65, final short x, final short y) {
      return BoxesRunTime.unboxToShort(ev$65.times(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y)));
   }

   // $FF: synthetic method
   public static final short $anonfun$qcombine$8(final Monoid ev$66, final short x, final short y) {
      return BoxesRunTime.unboxToShort(ev$66.combine(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y)));
   }

   // $FF: synthetic method
   public static final short $anonfun$qnorm$8(final Field ev$67, final Signed s$15, final int p$15, final short x$19, final short x$20) {
      return BoxesRunTime.unboxToShort(ev$67.plus(BoxesRunTime.boxToShort(x$19), BoxesRunTime.boxToShort(BoxesRunTime.unboxToShort(ev$67.pow(BoxesRunTime.boxToShort(s$15.abs$mcS$sp(x$20)), p$15)))));
   }

   // $FF: synthetic method
   public static final Object $anonfun$qnormWith$8(final Field ev$68, final Signed s$16, final Function1 f$15, final int p$16, final Object t, final short a) {
      return ev$68.plus(t, ev$68.pow(s$16.abs(f$15.apply(BoxesRunTime.boxToShort(a))), p$16));
   }

   // $FF: synthetic method
   public static final short $anonfun$qmin$8(final Order ev$69, final short x, final short y) {
      return ev$69.min$mcS$sp(x, y);
   }

   // $FF: synthetic method
   public static final short $anonfun$qmax$8(final Order ev$70, final short x, final short y) {
      return ev$70.max$mcS$sp(x, y);
   }

   // $FF: synthetic method
   public static final void $anonfun$qmean$8(final Field ev$71, final ShortRef mean$15, final IntRef i$15, final IntRef j$15, final short a) {
      short t = BoxesRunTime.unboxToShort(ev$71.div(ev$71.times(BoxesRunTime.boxToShort(mean$15.elem), ev$71.fromInt(i$15.elem)), ev$71.fromInt(j$15.elem)));
      short z = BoxesRunTime.unboxToShort(ev$71.div(BoxesRunTime.boxToShort(a), ev$71.fromInt(j$15.elem)));
      mean$15.elem = BoxesRunTime.unboxToShort(ev$71.plus(BoxesRunTime.boxToShort(t), BoxesRunTime.boxToShort(z)));
      ++i$15.elem;
      ++j$15.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$qmeanWith$8(final Field ev$72, final ObjectRef mean$16, final IntRef i$16, final IntRef j$16, final Function1 f$16, final short a) {
      Object t = ev$72.div(ev$72.times(mean$16.elem, ev$72.fromInt(i$16.elem)), ev$72.fromInt(j$16.elem));
      Object z = ev$72.div(f$16.apply(BoxesRunTime.boxToShort(a)), ev$72.fromInt(j$16.elem));
      mean$16.elem = ev$72.plus(t, z);
      ++i$16.elem;
      ++j$16.elem;
   }

   public SeqOps$mcS$sp(final Iterable as) {
      super(as);
      this.as = as;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
