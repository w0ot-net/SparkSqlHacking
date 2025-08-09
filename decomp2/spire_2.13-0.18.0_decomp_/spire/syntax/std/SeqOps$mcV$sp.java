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
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import spire.algebra.NRoot;
import spire.math.QuickSort$;
import spire.math.Searching$;
import spire.math.Selection$;
import spire.math.Sorting$;
import spire.random.Generator;

public final class SeqOps$mcV$sp extends SeqOps {
   private final Iterable as;

   public void qsum(final AdditiveMonoid ev) {
      this.qsum$mcV$sp(ev);
   }

   public void qsum$mcV$sp(final AdditiveMonoid ev) {
      this.spire$syntax$std$SeqOps$$as.foldLeft(ev.zero(), (x, y) -> {
         $anonfun$qsum$9(ev, x, y);
         return BoxedUnit.UNIT;
      });
   }

   public void qproduct(final MultiplicativeMonoid ev) {
      this.qproduct$mcV$sp(ev);
   }

   public void qproduct$mcV$sp(final MultiplicativeMonoid ev) {
      this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (x, y) -> {
         $anonfun$qproduct$9(ev, x, y);
         return BoxedUnit.UNIT;
      });
   }

   public void qcombine(final Monoid ev) {
      this.qcombine$mcV$sp(ev);
   }

   public void qcombine$mcV$sp(final Monoid ev) {
      this.spire$syntax$std$SeqOps$$as.foldLeft(ev.empty(), (x, y) -> {
         $anonfun$qcombine$9(ev, x, y);
         return BoxedUnit.UNIT;
      });
   }

   public void qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      this.qnorm$mcV$sp(p, ev, s, nr);
   }

   public void qnorm$mcV$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      BoxedUnit var10000 = (BoxedUnit)nr.nroot(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (x$19, x$20) -> {
         $anonfun$qnorm$9(ev, s, p, x$19, x$20);
         return BoxedUnit.UNIT;
      }), p);
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcV$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcV$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return nr.nroot(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (t, a) -> ev.plus(t, ev.pow(s.abs(f.apply(a)), p))), p);
   }

   public Seq pmin(final PartialOrder ev) {
      return this.pmin$mcV$sp(ev);
   }

   public Seq pmin$mcV$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, ev);
   }

   public Seq pmax(final PartialOrder ev) {
      return this.pmax$mcV$sp(ev);
   }

   public Seq pmax$mcV$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, spire.algebra.package$.MODULE$.PartialOrder().reverse$mVc$sp(ev));
   }

   public void qmin(final Order ev) {
      this.qmin$mcV$sp(ev);
   }

   public void qmin$mcV$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (x, y) -> {
            $anonfun$qmin$9(ev, x, y);
            return BoxedUnit.UNIT;
         });
      }
   }

   public void qmax(final Order ev) {
      this.qmax$mcV$sp(ev);
   }

   public void qmax$mcV$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (x, y) -> {
            $anonfun$qmax$9(ev, x, y);
            return BoxedUnit.UNIT;
         });
      }
   }

   public void qmean(final Field ev) {
      this.qmean$mcV$sp(ev);
   }

   public void qmean$mcV$sp(final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         ObjectRef mean = ObjectRef.create((BoxedUnit)ev.zero());
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((a) -> {
            $anonfun$qmean$9(ev, mean, i, j, a);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = (BoxedUnit)mean.elem;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcV$sp(f, ev);
   }

   public Object qmeanWith$mcV$sp(final Function1 f, final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         ObjectRef mean = ObjectRef.create(ev.zero());
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((a) -> {
            $anonfun$qmeanWith$9(ev, mean, i, j, f, a);
            return BoxedUnit.UNIT;
         });
         return mean.elem;
      }
   }

   public Iterable fromArray(final BoxedUnit[] arr, final Factory cbf) {
      return this.fromArray$mcV$sp(arr, cbf);
   }

   public Iterable fromArray$mcV$sp(final BoxedUnit[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(arr.length);

      for(int index$macro$1 = 0; index$macro$1 < arr.length; ++index$macro$1) {
         b.$plus$eq(arr[index$macro$1]);
      }

      return (Iterable)b.result();
   }

   public Iterable fromSizeAndArray(final int size, final BoxedUnit[] arr, final Factory cbf) {
      return this.fromSizeAndArray$mcV$sp(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcV$sp(final int size, final BoxedUnit[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(size);

      for(int index$macro$1 = 0; index$macro$1 < size; ++index$macro$1) {
         b.$plus$eq(arr[index$macro$1]);
      }

      return (Iterable)b.result();
   }

   public Iterable qsorted(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted$mcV$sp(ev, ct, cbf);
   }

   public Iterable qsorted$mcV$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      BoxedUnit[] arr = (BoxedUnit[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mVc$sp(arr, ev, ct);
      return this.fromArray$mcV$sp(arr, cbf);
   }

   public Iterable qsortedBy(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mcV$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      BoxedUnit[] arr = (BoxedUnit[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mVc$sp(arr, ord, ct);
      return this.fromArray$mcV$sp(arr, cbf);
   }

   public Iterable qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZcV$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVZc$sp(f, ev);
      BoxedUnit[] arr = (BoxedUnit[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mVc$sp(arr, ord, ct);
      return this.fromArray$mcV$sp(arr, cbf);
   }

   public Iterable qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBcV$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVBc$sp(f, ev);
      BoxedUnit[] arr = (BoxedUnit[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mVc$sp(arr, ord, ct);
      return this.fromArray$mcV$sp(arr, cbf);
   }

   public Iterable qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCcV$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVCc$sp(f, ev);
      BoxedUnit[] arr = (BoxedUnit[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mVc$sp(arr, ord, ct);
      return this.fromArray$mcV$sp(arr, cbf);
   }

   public Iterable qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDcV$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVDc$sp(f, ev);
      BoxedUnit[] arr = (BoxedUnit[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mVc$sp(arr, ord, ct);
      return this.fromArray$mcV$sp(arr, cbf);
   }

   public Iterable qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFcV$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVFc$sp(f, ev);
      BoxedUnit[] arr = (BoxedUnit[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mVc$sp(arr, ord, ct);
      return this.fromArray$mcV$sp(arr, cbf);
   }

   public Iterable qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIcV$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVIc$sp(f, ev);
      BoxedUnit[] arr = (BoxedUnit[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mVc$sp(arr, ord, ct);
      return this.fromArray$mcV$sp(arr, cbf);
   }

   public Iterable qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJcV$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVJc$sp(f, ev);
      BoxedUnit[] arr = (BoxedUnit[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mVc$sp(arr, ord, ct);
      return this.fromArray$mcV$sp(arr, cbf);
   }

   public Iterable qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mScV$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVSc$sp(f, ev);
      BoxedUnit[] arr = (BoxedUnit[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mVc$sp(arr, ord, ct);
      return this.fromArray$mcV$sp(arr, cbf);
   }

   public Iterable qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVcV$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mVVc$sp(f, ev);
      BoxedUnit[] arr = (BoxedUnit[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mVc$sp(arr, ord, ct);
      return this.fromArray$mcV$sp(arr, cbf);
   }

   public Iterable qsortedWith(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith$mcV$sp(f, ct, cbf);
   }

   public Iterable qsortedWith$mcV$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mVc$sp(f);
      BoxedUnit[] arr = (BoxedUnit[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mVc$sp(arr, ord, ct);
      return this.fromArray$mcV$sp(arr, cbf);
   }

   public Iterable qselected(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected$mcV$sp(k, ev, ct, cbf);
   }

   public Iterable qselected$mcV$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      BoxedUnit[] arr = (BoxedUnit[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Selection$.MODULE$.select$mVc$sp(arr, k, ev, ct);
      return this.fromArray$mcV$sp(arr, cbf);
   }

   public Iterable qselectk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk$mcV$sp(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcV$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      BoxedUnit[] arr = (BoxedUnit[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         var10000 = this.fromArray$mcV$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mVc$sp(arr, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcV$sp(k, arr, cbf);
      }

      return var10000;
   }

   public Iterable qtopk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk$mcV$sp(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcV$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      BoxedUnit[] arr = (BoxedUnit[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         Sorting$.MODULE$.sort$mVc$sp(arr, ev, ct);
         var10000 = this.fromArray$mcV$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mVc$sp(arr, k, ev, ct);
         QuickSort$.MODULE$.qsort$mVc$sp(arr, 0, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcV$sp(k, arr, cbf);
      }

      return var10000;
   }

   public void qchoose(final Generator gen) {
      this.qchoose$mcV$sp(gen);
   }

   public void qchoose$mcV$sp(final Generator gen) {
      gen.chooseFromIterable(this.spire$syntax$std$SeqOps$$as, gen);
   }

   // $FF: synthetic method
   public static final void $anonfun$qsum$9(final AdditiveMonoid ev$73, final BoxedUnit x, final BoxedUnit y) {
      ev$73.plus(x, y);
   }

   // $FF: synthetic method
   public static final void $anonfun$qproduct$9(final MultiplicativeMonoid ev$74, final BoxedUnit x, final BoxedUnit y) {
      ev$74.times(x, y);
   }

   // $FF: synthetic method
   public static final void $anonfun$qcombine$9(final Monoid ev$75, final BoxedUnit x, final BoxedUnit y) {
      ev$75.combine(x, y);
   }

   // $FF: synthetic method
   public static final void $anonfun$qnorm$9(final Field ev$76, final Signed s$17, final int p$17, final BoxedUnit x$19, final BoxedUnit x$20) {
      BoxedUnit var10000 = (BoxedUnit)ev$76.plus(x$19, (BoxedUnit)ev$76.pow((BoxedUnit)s$17.abs(x$20), p$17));
   }

   // $FF: synthetic method
   public static final void $anonfun$qmin$9(final Order ev$78, final BoxedUnit x, final BoxedUnit y) {
      ev$78.min$mcV$sp(x, y);
   }

   // $FF: synthetic method
   public static final void $anonfun$qmax$9(final Order ev$79, final BoxedUnit x, final BoxedUnit y) {
      ev$79.max$mcV$sp(x, y);
   }

   // $FF: synthetic method
   public static final void $anonfun$qmean$9(final Field ev$80, final ObjectRef mean$17, final IntRef i$17, final IntRef j$17, final BoxedUnit a) {
      BoxedUnit t = (BoxedUnit)ev$80.div(ev$80.times((BoxedUnit)mean$17.elem, ev$80.fromInt(i$17.elem)), ev$80.fromInt(j$17.elem));
      BoxedUnit z = (BoxedUnit)ev$80.div(a, ev$80.fromInt(j$17.elem));
      mean$17.elem = (BoxedUnit)ev$80.plus(t, z);
      ++i$17.elem;
      ++j$17.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$qmeanWith$9(final Field ev$81, final ObjectRef mean$18, final IntRef i$18, final IntRef j$18, final Function1 f$18, final BoxedUnit a) {
      Object t = ev$81.div(ev$81.times(mean$18.elem, ev$81.fromInt(i$18.elem)), ev$81.fromInt(j$18.elem));
      Object z = ev$81.div(f$18.apply(a), ev$81.fromInt(j$18.elem));
      mean$18.elem = ev$81.plus(t, z);
      ++i$18.elem;
      ++j$18.elem;
   }

   public SeqOps$mcV$sp(final Iterable as) {
      super(as);
      this.as = as;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
