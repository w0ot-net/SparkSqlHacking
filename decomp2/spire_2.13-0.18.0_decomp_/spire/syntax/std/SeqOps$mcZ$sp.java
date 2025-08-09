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
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import spire.algebra.NRoot;
import spire.math.QuickSort$;
import spire.math.Searching$;
import spire.math.Selection$;
import spire.math.Sorting$;
import spire.random.Generator;

public final class SeqOps$mcZ$sp extends SeqOps {
   private final Iterable as;

   public boolean qsum(final AdditiveMonoid ev) {
      return this.qsum$mcZ$sp(ev);
   }

   public boolean qsum$mcZ$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToBoolean(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.zero(), (x, y) -> BoxesRunTime.boxToBoolean($anonfun$qsum$10(ev, BoxesRunTime.unboxToBoolean(x), BoxesRunTime.unboxToBoolean(y)))));
   }

   public boolean qproduct(final MultiplicativeMonoid ev) {
      return this.qproduct$mcZ$sp(ev);
   }

   public boolean qproduct$mcZ$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToBoolean(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (x, y) -> BoxesRunTime.boxToBoolean($anonfun$qproduct$10(ev, BoxesRunTime.unboxToBoolean(x), BoxesRunTime.unboxToBoolean(y)))));
   }

   public boolean qcombine(final Monoid ev) {
      return this.qcombine$mcZ$sp(ev);
   }

   public boolean qcombine$mcZ$sp(final Monoid ev) {
      return BoxesRunTime.unboxToBoolean(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.empty(), (x, y) -> BoxesRunTime.boxToBoolean($anonfun$qcombine$10(ev, BoxesRunTime.unboxToBoolean(x), BoxesRunTime.unboxToBoolean(y)))));
   }

   public boolean qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return this.qnorm$mcZ$sp(p, ev, s, nr);
   }

   public boolean qnorm$mcZ$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToBoolean(nr.nroot(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (x$19, x$20) -> BoxesRunTime.boxToBoolean($anonfun$qnorm$10(ev, s, p, BoxesRunTime.unboxToBoolean(x$19), BoxesRunTime.unboxToBoolean(x$20)))), p));
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcZ$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcZ$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return nr.nroot(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (t, a) -> $anonfun$qnormWith$10(ev, s, f, p, t, BoxesRunTime.unboxToBoolean(a))), p);
   }

   public Seq pmin(final PartialOrder ev) {
      return this.pmin$mcZ$sp(ev);
   }

   public Seq pmin$mcZ$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, ev);
   }

   public Seq pmax(final PartialOrder ev) {
      return this.pmax$mcZ$sp(ev);
   }

   public Seq pmax$mcZ$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, spire.algebra.package$.MODULE$.PartialOrder().reverse$mZc$sp(ev));
   }

   public boolean qmin(final Order ev) {
      return this.qmin$mcZ$sp(ev);
   }

   public boolean qmin$mcZ$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return BoxesRunTime.unboxToBoolean(this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (x, y) -> BoxesRunTime.boxToBoolean($anonfun$qmin$10(ev, BoxesRunTime.unboxToBoolean(x), BoxesRunTime.unboxToBoolean(y)))));
      }
   }

   public boolean qmax(final Order ev) {
      return this.qmax$mcZ$sp(ev);
   }

   public boolean qmax$mcZ$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return BoxesRunTime.unboxToBoolean(this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (x, y) -> BoxesRunTime.boxToBoolean($anonfun$qmax$10(ev, BoxesRunTime.unboxToBoolean(x), BoxesRunTime.unboxToBoolean(y)))));
      }
   }

   public boolean qmean(final Field ev) {
      return this.qmean$mcZ$sp(ev);
   }

   public boolean qmean$mcZ$sp(final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         BooleanRef mean = BooleanRef.create(BoxesRunTime.unboxToBoolean(ev.zero()));
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((a) -> {
            $anonfun$qmean$10(ev, mean, i, j, BoxesRunTime.unboxToBoolean(a));
            return BoxedUnit.UNIT;
         });
         return mean.elem;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcZ$sp(f, ev);
   }

   public Object qmeanWith$mcZ$sp(final Function1 f, final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         ObjectRef mean = ObjectRef.create(ev.zero());
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((a) -> {
            $anonfun$qmeanWith$10(ev, mean, i, j, f, BoxesRunTime.unboxToBoolean(a));
            return BoxedUnit.UNIT;
         });
         return mean.elem;
      }
   }

   public Iterable fromArray(final boolean[] arr, final Factory cbf) {
      return this.fromArray$mcZ$sp(arr, cbf);
   }

   public Iterable fromArray$mcZ$sp(final boolean[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(arr.length);

      for(int index$macro$1 = 0; index$macro$1 < arr.length; ++index$macro$1) {
         b.$plus$eq(BoxesRunTime.boxToBoolean(arr[index$macro$1]));
      }

      return (Iterable)b.result();
   }

   public Iterable fromSizeAndArray(final int size, final boolean[] arr, final Factory cbf) {
      return this.fromSizeAndArray$mcZ$sp(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcZ$sp(final int size, final boolean[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(size);

      for(int index$macro$1 = 0; index$macro$1 < size; ++index$macro$1) {
         b.$plus$eq(BoxesRunTime.boxToBoolean(arr[index$macro$1]));
      }

      return (Iterable)b.result();
   }

   public Iterable qsorted(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted$mcZ$sp(ev, ct, cbf);
   }

   public Iterable qsorted$mcZ$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      boolean[] arr = (boolean[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mZc$sp(arr, ev, ct);
      return this.fromArray$mcZ$sp(arr, cbf);
   }

   public Iterable qsortedBy(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mcZ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      boolean[] arr = (boolean[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mZc$sp(arr, ord, ct);
      return this.fromArray$mcZ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZcZ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZZc$sp(f, ev);
      boolean[] arr = (boolean[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mZc$sp(arr, ord, ct);
      return this.fromArray$mcZ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBcZ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZBc$sp(f, ev);
      boolean[] arr = (boolean[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mZc$sp(arr, ord, ct);
      return this.fromArray$mcZ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCcZ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZCc$sp(f, ev);
      boolean[] arr = (boolean[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mZc$sp(arr, ord, ct);
      return this.fromArray$mcZ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDcZ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZDc$sp(f, ev);
      boolean[] arr = (boolean[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mZc$sp(arr, ord, ct);
      return this.fromArray$mcZ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFcZ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZFc$sp(f, ev);
      boolean[] arr = (boolean[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mZc$sp(arr, ord, ct);
      return this.fromArray$mcZ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIcZ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZIc$sp(f, ev);
      boolean[] arr = (boolean[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mZc$sp(arr, ord, ct);
      return this.fromArray$mcZ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJcZ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZJc$sp(f, ev);
      boolean[] arr = (boolean[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mZc$sp(arr, ord, ct);
      return this.fromArray$mcZ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mScZ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZSc$sp(f, ev);
      boolean[] arr = (boolean[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mZc$sp(arr, ord, ct);
      return this.fromArray$mcZ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVcZ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mZVc$sp(f, ev);
      boolean[] arr = (boolean[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mZc$sp(arr, ord, ct);
      return this.fromArray$mcZ$sp(arr, cbf);
   }

   public Iterable qsortedWith(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith$mcZ$sp(f, ct, cbf);
   }

   public Iterable qsortedWith$mcZ$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mZc$sp(f);
      boolean[] arr = (boolean[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mZc$sp(arr, ord, ct);
      return this.fromArray$mcZ$sp(arr, cbf);
   }

   public Iterable qselected(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected$mcZ$sp(k, ev, ct, cbf);
   }

   public Iterable qselected$mcZ$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      boolean[] arr = (boolean[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Selection$.MODULE$.select$mZc$sp(arr, k, ev, ct);
      return this.fromArray$mcZ$sp(arr, cbf);
   }

   public Iterable qselectk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk$mcZ$sp(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcZ$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      boolean[] arr = (boolean[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         var10000 = this.fromArray$mcZ$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mZc$sp(arr, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcZ$sp(k, arr, cbf);
      }

      return var10000;
   }

   public Iterable qtopk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk$mcZ$sp(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcZ$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      boolean[] arr = (boolean[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         Sorting$.MODULE$.sort$mZc$sp(arr, ev, ct);
         var10000 = this.fromArray$mcZ$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mZc$sp(arr, k, ev, ct);
         QuickSort$.MODULE$.qsort$mZc$sp(arr, 0, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcZ$sp(k, arr, cbf);
      }

      return var10000;
   }

   public boolean qchoose(final Generator gen) {
      return this.qchoose$mcZ$sp(gen);
   }

   public boolean qchoose$mcZ$sp(final Generator gen) {
      return BoxesRunTime.unboxToBoolean(gen.chooseFromIterable(this.spire$syntax$std$SeqOps$$as, gen));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$qsum$10(final AdditiveMonoid ev$82, final boolean x, final boolean y) {
      return BoxesRunTime.unboxToBoolean(ev$82.plus(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$qproduct$10(final MultiplicativeMonoid ev$83, final boolean x, final boolean y) {
      return BoxesRunTime.unboxToBoolean(ev$83.times(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$qcombine$10(final Monoid ev$84, final boolean x, final boolean y) {
      return BoxesRunTime.unboxToBoolean(ev$84.combine(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$qnorm$10(final Field ev$85, final Signed s$19, final int p$19, final boolean x$19, final boolean x$20) {
      return BoxesRunTime.unboxToBoolean(ev$85.plus(BoxesRunTime.boxToBoolean(x$19), BoxesRunTime.boxToBoolean(BoxesRunTime.unboxToBoolean(ev$85.pow(BoxesRunTime.boxToBoolean(BoxesRunTime.unboxToBoolean(s$19.abs(BoxesRunTime.boxToBoolean(x$20)))), p$19)))));
   }

   // $FF: synthetic method
   public static final Object $anonfun$qnormWith$10(final Field ev$86, final Signed s$20, final Function1 f$19, final int p$20, final Object t, final boolean a) {
      return ev$86.plus(t, ev$86.pow(s$20.abs(f$19.apply(BoxesRunTime.boxToBoolean(a))), p$20));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$qmin$10(final Order ev$87, final boolean x, final boolean y) {
      return ev$87.min$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$qmax$10(final Order ev$88, final boolean x, final boolean y) {
      return ev$88.max$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   public static final void $anonfun$qmean$10(final Field ev$89, final BooleanRef mean$19, final IntRef i$19, final IntRef j$19, final boolean a) {
      boolean t = BoxesRunTime.unboxToBoolean(ev$89.div(ev$89.times(BoxesRunTime.boxToBoolean(mean$19.elem), ev$89.fromInt(i$19.elem)), ev$89.fromInt(j$19.elem)));
      boolean z = BoxesRunTime.unboxToBoolean(ev$89.div(BoxesRunTime.boxToBoolean(a), ev$89.fromInt(j$19.elem)));
      mean$19.elem = BoxesRunTime.unboxToBoolean(ev$89.plus(BoxesRunTime.boxToBoolean(t), BoxesRunTime.boxToBoolean(z)));
      ++i$19.elem;
      ++j$19.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$qmeanWith$10(final Field ev$90, final ObjectRef mean$20, final IntRef i$20, final IntRef j$20, final Function1 f$20, final boolean a) {
      Object t = ev$90.div(ev$90.times(mean$20.elem, ev$90.fromInt(i$20.elem)), ev$90.fromInt(j$20.elem));
      Object z = ev$90.div(f$20.apply(BoxesRunTime.boxToBoolean(a)), ev$90.fromInt(j$20.elem));
      mean$20.elem = ev$90.plus(t, z);
      ++i$20.elem;
      ++j$20.elem;
   }

   public SeqOps$mcZ$sp(final Iterable as) {
      super(as);
      this.as = as;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
