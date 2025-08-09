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
import scala.runtime.ByteRef;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import spire.algebra.NRoot;
import spire.math.QuickSort$;
import spire.math.Searching$;
import spire.math.Selection$;
import spire.math.Sorting$;
import spire.random.Generator;

public final class SeqOps$mcB$sp extends SeqOps {
   private final Iterable as;

   public byte qsum(final AdditiveMonoid ev) {
      return this.qsum$mcB$sp(ev);
   }

   public byte qsum$mcB$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToByte(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.zero(), (x, y) -> BoxesRunTime.boxToByte($anonfun$qsum$2(ev, BoxesRunTime.unboxToByte(x), BoxesRunTime.unboxToByte(y)))));
   }

   public byte qproduct(final MultiplicativeMonoid ev) {
      return this.qproduct$mcB$sp(ev);
   }

   public byte qproduct$mcB$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToByte(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (x, y) -> BoxesRunTime.boxToByte($anonfun$qproduct$2(ev, BoxesRunTime.unboxToByte(x), BoxesRunTime.unboxToByte(y)))));
   }

   public byte qcombine(final Monoid ev) {
      return this.qcombine$mcB$sp(ev);
   }

   public byte qcombine$mcB$sp(final Monoid ev) {
      return BoxesRunTime.unboxToByte(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.empty(), (x, y) -> BoxesRunTime.boxToByte($anonfun$qcombine$2(ev, BoxesRunTime.unboxToByte(x), BoxesRunTime.unboxToByte(y)))));
   }

   public byte qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return this.qnorm$mcB$sp(p, ev, s, nr);
   }

   public byte qnorm$mcB$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToByte(nr.nroot(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (x$19, x$20) -> BoxesRunTime.boxToByte($anonfun$qnorm$2(ev, s, p, BoxesRunTime.unboxToByte(x$19), BoxesRunTime.unboxToByte(x$20)))), p));
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcB$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcB$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return nr.nroot(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (t, a) -> $anonfun$qnormWith$2(ev, s, f, p, t, BoxesRunTime.unboxToByte(a))), p);
   }

   public Seq pmin(final PartialOrder ev) {
      return this.pmin$mcB$sp(ev);
   }

   public Seq pmin$mcB$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, ev);
   }

   public Seq pmax(final PartialOrder ev) {
      return this.pmax$mcB$sp(ev);
   }

   public Seq pmax$mcB$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, spire.algebra.package$.MODULE$.PartialOrder().reverse$mBc$sp(ev));
   }

   public byte qmin(final Order ev) {
      return this.qmin$mcB$sp(ev);
   }

   public byte qmin$mcB$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return BoxesRunTime.unboxToByte(this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (x, y) -> BoxesRunTime.boxToByte($anonfun$qmin$2(ev, BoxesRunTime.unboxToByte(x), BoxesRunTime.unboxToByte(y)))));
      }
   }

   public byte qmax(final Order ev) {
      return this.qmax$mcB$sp(ev);
   }

   public byte qmax$mcB$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return BoxesRunTime.unboxToByte(this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (x, y) -> BoxesRunTime.boxToByte($anonfun$qmax$2(ev, BoxesRunTime.unboxToByte(x), BoxesRunTime.unboxToByte(y)))));
      }
   }

   public byte qmean(final Field ev) {
      return this.qmean$mcB$sp(ev);
   }

   public byte qmean$mcB$sp(final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         ByteRef mean = ByteRef.create(BoxesRunTime.unboxToByte(ev.zero()));
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((a) -> {
            $anonfun$qmean$2(ev, mean, i, j, BoxesRunTime.unboxToByte(a));
            return BoxedUnit.UNIT;
         });
         return mean.elem;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcB$sp(f, ev);
   }

   public Object qmeanWith$mcB$sp(final Function1 f, final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         ObjectRef mean = ObjectRef.create(ev.zero());
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((a) -> {
            $anonfun$qmeanWith$2(ev, mean, i, j, f, BoxesRunTime.unboxToByte(a));
            return BoxedUnit.UNIT;
         });
         return mean.elem;
      }
   }

   public Iterable fromArray(final byte[] arr, final Factory cbf) {
      return this.fromArray$mcB$sp(arr, cbf);
   }

   public Iterable fromArray$mcB$sp(final byte[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(arr.length);

      for(int index$macro$1 = 0; index$macro$1 < arr.length; ++index$macro$1) {
         b.$plus$eq(BoxesRunTime.boxToByte(arr[index$macro$1]));
      }

      return (Iterable)b.result();
   }

   public Iterable fromSizeAndArray(final int size, final byte[] arr, final Factory cbf) {
      return this.fromSizeAndArray$mcB$sp(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcB$sp(final int size, final byte[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(size);

      for(int index$macro$1 = 0; index$macro$1 < size; ++index$macro$1) {
         b.$plus$eq(BoxesRunTime.boxToByte(arr[index$macro$1]));
      }

      return (Iterable)b.result();
   }

   public Iterable qsorted(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted$mcB$sp(ev, ct, cbf);
   }

   public Iterable qsorted$mcB$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      byte[] arr = (byte[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mBc$sp(arr, ev, ct);
      return this.fromArray$mcB$sp(arr, cbf);
   }

   public Iterable qsortedBy(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mcB$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      byte[] arr = (byte[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mBc$sp(arr, ord, ct);
      return this.fromArray$mcB$sp(arr, cbf);
   }

   public Iterable qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZcB$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBZc$sp(f, ev);
      byte[] arr = (byte[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mBc$sp(arr, ord, ct);
      return this.fromArray$mcB$sp(arr, cbf);
   }

   public Iterable qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBcB$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBBc$sp(f, ev);
      byte[] arr = (byte[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mBc$sp(arr, ord, ct);
      return this.fromArray$mcB$sp(arr, cbf);
   }

   public Iterable qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCcB$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBCc$sp(f, ev);
      byte[] arr = (byte[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mBc$sp(arr, ord, ct);
      return this.fromArray$mcB$sp(arr, cbf);
   }

   public Iterable qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDcB$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBDc$sp(f, ev);
      byte[] arr = (byte[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mBc$sp(arr, ord, ct);
      return this.fromArray$mcB$sp(arr, cbf);
   }

   public Iterable qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFcB$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBFc$sp(f, ev);
      byte[] arr = (byte[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mBc$sp(arr, ord, ct);
      return this.fromArray$mcB$sp(arr, cbf);
   }

   public Iterable qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIcB$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBIc$sp(f, ev);
      byte[] arr = (byte[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mBc$sp(arr, ord, ct);
      return this.fromArray$mcB$sp(arr, cbf);
   }

   public Iterable qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJcB$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBJc$sp(f, ev);
      byte[] arr = (byte[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mBc$sp(arr, ord, ct);
      return this.fromArray$mcB$sp(arr, cbf);
   }

   public Iterable qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mScB$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBSc$sp(f, ev);
      byte[] arr = (byte[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mBc$sp(arr, ord, ct);
      return this.fromArray$mcB$sp(arr, cbf);
   }

   public Iterable qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVcB$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mBVc$sp(f, ev);
      byte[] arr = (byte[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mBc$sp(arr, ord, ct);
      return this.fromArray$mcB$sp(arr, cbf);
   }

   public Iterable qsortedWith(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith$mcB$sp(f, ct, cbf);
   }

   public Iterable qsortedWith$mcB$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mBc$sp(f);
      byte[] arr = (byte[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mBc$sp(arr, ord, ct);
      return this.fromArray$mcB$sp(arr, cbf);
   }

   public Iterable qselected(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected$mcB$sp(k, ev, ct, cbf);
   }

   public Iterable qselected$mcB$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      byte[] arr = (byte[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Selection$.MODULE$.select$mBc$sp(arr, k, ev, ct);
      return this.fromArray$mcB$sp(arr, cbf);
   }

   public Iterable qselectk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk$mcB$sp(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcB$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      byte[] arr = (byte[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         var10000 = this.fromArray$mcB$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mBc$sp(arr, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcB$sp(k, arr, cbf);
      }

      return var10000;
   }

   public Iterable qtopk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk$mcB$sp(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcB$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      byte[] arr = (byte[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         Sorting$.MODULE$.sort$mBc$sp(arr, ev, ct);
         var10000 = this.fromArray$mcB$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mBc$sp(arr, k, ev, ct);
         QuickSort$.MODULE$.qsort$mBc$sp(arr, 0, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcB$sp(k, arr, cbf);
      }

      return var10000;
   }

   public byte qchoose(final Generator gen) {
      return this.qchoose$mcB$sp(gen);
   }

   public byte qchoose$mcB$sp(final Generator gen) {
      return BoxesRunTime.unboxToByte(gen.chooseFromIterable(this.spire$syntax$std$SeqOps$$as, gen));
   }

   // $FF: synthetic method
   public static final byte $anonfun$qsum$2(final AdditiveMonoid ev$10, final byte x, final byte y) {
      return BoxesRunTime.unboxToByte(ev$10.plus(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y)));
   }

   // $FF: synthetic method
   public static final byte $anonfun$qproduct$2(final MultiplicativeMonoid ev$11, final byte x, final byte y) {
      return BoxesRunTime.unboxToByte(ev$11.times(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y)));
   }

   // $FF: synthetic method
   public static final byte $anonfun$qcombine$2(final Monoid ev$12, final byte x, final byte y) {
      return BoxesRunTime.unboxToByte(ev$12.combine(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y)));
   }

   // $FF: synthetic method
   public static final byte $anonfun$qnorm$2(final Field ev$13, final Signed s$3, final int p$3, final byte x$19, final byte x$20) {
      return BoxesRunTime.unboxToByte(ev$13.plus(BoxesRunTime.boxToByte(x$19), BoxesRunTime.boxToByte(BoxesRunTime.unboxToByte(ev$13.pow(BoxesRunTime.boxToByte(s$3.abs$mcB$sp(x$20)), p$3)))));
   }

   // $FF: synthetic method
   public static final Object $anonfun$qnormWith$2(final Field ev$14, final Signed s$4, final Function1 f$3, final int p$4, final Object t, final byte a) {
      return ev$14.plus(t, ev$14.pow(s$4.abs(f$3.apply(BoxesRunTime.boxToByte(a))), p$4));
   }

   // $FF: synthetic method
   public static final byte $anonfun$qmin$2(final Order ev$15, final byte x, final byte y) {
      return ev$15.min$mcB$sp(x, y);
   }

   // $FF: synthetic method
   public static final byte $anonfun$qmax$2(final Order ev$16, final byte x, final byte y) {
      return ev$16.max$mcB$sp(x, y);
   }

   // $FF: synthetic method
   public static final void $anonfun$qmean$2(final Field ev$17, final ByteRef mean$3, final IntRef i$3, final IntRef j$3, final byte a) {
      byte t = BoxesRunTime.unboxToByte(ev$17.div(ev$17.times(BoxesRunTime.boxToByte(mean$3.elem), ev$17.fromInt(i$3.elem)), ev$17.fromInt(j$3.elem)));
      byte z = BoxesRunTime.unboxToByte(ev$17.div(BoxesRunTime.boxToByte(a), ev$17.fromInt(j$3.elem)));
      mean$3.elem = BoxesRunTime.unboxToByte(ev$17.plus(BoxesRunTime.boxToByte(t), BoxesRunTime.boxToByte(z)));
      ++i$3.elem;
      ++j$3.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$qmeanWith$2(final Field ev$18, final ObjectRef mean$4, final IntRef i$4, final IntRef j$4, final Function1 f$4, final byte a) {
      Object t = ev$18.div(ev$18.times(mean$4.elem, ev$18.fromInt(i$4.elem)), ev$18.fromInt(j$4.elem));
      Object z = ev$18.div(f$4.apply(BoxesRunTime.boxToByte(a)), ev$18.fromInt(j$4.elem));
      mean$4.elem = ev$18.plus(t, z);
      ++i$4.elem;
      ++j$4.elem;
   }

   public SeqOps$mcB$sp(final Iterable as) {
      super(as);
      this.as = as;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
