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

public final class SeqOps$mcI$sp extends SeqOps {
   private final Iterable as;

   public int qsum(final AdditiveMonoid ev) {
      return this.qsum$mcI$sp(ev);
   }

   public int qsum$mcI$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToInt(this.spire$syntax$std$SeqOps$$as.foldLeft(BoxesRunTime.boxToInteger(ev.zero$mcI$sp()), (JFunction2.mcIII.sp)(x, y) -> ev.plus$mcI$sp(x, y)));
   }

   public int qproduct(final MultiplicativeMonoid ev) {
      return this.qproduct$mcI$sp(ev);
   }

   public int qproduct$mcI$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToInt(this.spire$syntax$std$SeqOps$$as.foldLeft(BoxesRunTime.boxToInteger(ev.one$mcI$sp()), (JFunction2.mcIII.sp)(x, y) -> ev.times$mcI$sp(x, y)));
   }

   public int qcombine(final Monoid ev) {
      return this.qcombine$mcI$sp(ev);
   }

   public int qcombine$mcI$sp(final Monoid ev) {
      return BoxesRunTime.unboxToInt(this.spire$syntax$std$SeqOps$$as.foldLeft(BoxesRunTime.boxToInteger(ev.empty$mcI$sp()), (JFunction2.mcIII.sp)(x, y) -> ev.combine$mcI$sp(x, y)));
   }

   public int qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return this.qnorm$mcI$sp(p, ev, s, nr);
   }

   public int qnorm$mcI$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return nr.nroot$mcI$sp(BoxesRunTime.unboxToInt(this.spire$syntax$std$SeqOps$$as.foldLeft(BoxesRunTime.boxToInteger(ev.one$mcI$sp()), (JFunction2.mcIII.sp)(x$19, x$20) -> ev.plus$mcI$sp(x$19, ev.pow$mcI$sp(s.abs$mcI$sp(x$20), p)))), p);
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcI$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcI$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return nr.nroot(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (t, a) -> $anonfun$qnormWith$6(ev, s, f, p, t, BoxesRunTime.unboxToInt(a))), p);
   }

   public Seq pmin(final PartialOrder ev) {
      return this.pmin$mcI$sp(ev);
   }

   public Seq pmin$mcI$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, ev);
   }

   public Seq pmax(final PartialOrder ev) {
      return this.pmax$mcI$sp(ev);
   }

   public Seq pmax$mcI$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, spire.algebra.package$.MODULE$.PartialOrder().reverse$mIc$sp(ev));
   }

   public int qmin(final Order ev) {
      return this.qmin$mcI$sp(ev);
   }

   public int qmin$mcI$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return BoxesRunTime.unboxToInt(this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (JFunction2.mcIII.sp)(x, y) -> ev.min$mcI$sp(x, y)));
      }
   }

   public int qmax(final Order ev) {
      return this.qmax$mcI$sp(ev);
   }

   public int qmax$mcI$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return BoxesRunTime.unboxToInt(this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (JFunction2.mcIII.sp)(x, y) -> ev.max$mcI$sp(x, y)));
      }
   }

   public int qmean(final Field ev) {
      return this.qmean$mcI$sp(ev);
   }

   public int qmean$mcI$sp(final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         IntRef mean = IntRef.create(ev.zero$mcI$sp());
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((JFunction1.mcVI.sp)(a) -> {
            int t = ev.div$mcI$sp(ev.times$mcI$sp(mean.elem, ev.fromInt$mcI$sp(i.elem)), ev.fromInt$mcI$sp(j.elem));
            int z = ev.div$mcI$sp(a, ev.fromInt$mcI$sp(j.elem));
            mean.elem = ev.plus$mcI$sp(t, z);
            ++i.elem;
            ++j.elem;
         });
         return mean.elem;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcI$sp(f, ev);
   }

   public Object qmeanWith$mcI$sp(final Function1 f, final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         ObjectRef mean = ObjectRef.create(ev.zero());
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((JFunction1.mcVI.sp)(a) -> {
            Object t = ev.div(ev.times(mean.elem, ev.fromInt(i.elem)), ev.fromInt(j.elem));
            Object z = ev.div(f.apply(BoxesRunTime.boxToInteger(a)), ev.fromInt(j.elem));
            mean.elem = ev.plus(t, z);
            ++i.elem;
            ++j.elem;
         });
         return mean.elem;
      }
   }

   public Iterable fromArray(final int[] arr, final Factory cbf) {
      return this.fromArray$mcI$sp(arr, cbf);
   }

   public Iterable fromArray$mcI$sp(final int[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(arr.length);

      for(int index$macro$1 = 0; index$macro$1 < arr.length; ++index$macro$1) {
         b.$plus$eq(BoxesRunTime.boxToInteger(arr[index$macro$1]));
      }

      return (Iterable)b.result();
   }

   public Iterable fromSizeAndArray(final int size, final int[] arr, final Factory cbf) {
      return this.fromSizeAndArray$mcI$sp(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcI$sp(final int size, final int[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(size);

      for(int index$macro$1 = 0; index$macro$1 < size; ++index$macro$1) {
         b.$plus$eq(BoxesRunTime.boxToInteger(arr[index$macro$1]));
      }

      return (Iterable)b.result();
   }

   public Iterable qsorted(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted$mcI$sp(ev, ct, cbf);
   }

   public Iterable qsorted$mcI$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      int[] arr = (int[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mIc$sp(arr, ev, ct);
      return this.fromArray$mcI$sp(arr, cbf);
   }

   public Iterable qsortedBy(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mcI$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      int[] arr = (int[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mIc$sp(arr, ord, ct);
      return this.fromArray$mcI$sp(arr, cbf);
   }

   public Iterable qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZcI$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIZc$sp(f, ev);
      int[] arr = (int[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mIc$sp(arr, ord, ct);
      return this.fromArray$mcI$sp(arr, cbf);
   }

   public Iterable qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBcI$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIBc$sp(f, ev);
      int[] arr = (int[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mIc$sp(arr, ord, ct);
      return this.fromArray$mcI$sp(arr, cbf);
   }

   public Iterable qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCcI$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mICc$sp(f, ev);
      int[] arr = (int[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mIc$sp(arr, ord, ct);
      return this.fromArray$mcI$sp(arr, cbf);
   }

   public Iterable qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDcI$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIDc$sp(f, ev);
      int[] arr = (int[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mIc$sp(arr, ord, ct);
      return this.fromArray$mcI$sp(arr, cbf);
   }

   public Iterable qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFcI$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIFc$sp(f, ev);
      int[] arr = (int[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mIc$sp(arr, ord, ct);
      return this.fromArray$mcI$sp(arr, cbf);
   }

   public Iterable qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIcI$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIIc$sp(f, ev);
      int[] arr = (int[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mIc$sp(arr, ord, ct);
      return this.fromArray$mcI$sp(arr, cbf);
   }

   public Iterable qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJcI$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIJc$sp(f, ev);
      int[] arr = (int[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mIc$sp(arr, ord, ct);
      return this.fromArray$mcI$sp(arr, cbf);
   }

   public Iterable qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mScI$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mISc$sp(f, ev);
      int[] arr = (int[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mIc$sp(arr, ord, ct);
      return this.fromArray$mcI$sp(arr, cbf);
   }

   public Iterable qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVcI$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mIVc$sp(f, ev);
      int[] arr = (int[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mIc$sp(arr, ord, ct);
      return this.fromArray$mcI$sp(arr, cbf);
   }

   public Iterable qsortedWith(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith$mcI$sp(f, ct, cbf);
   }

   public Iterable qsortedWith$mcI$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mIc$sp(f);
      int[] arr = (int[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mIc$sp(arr, ord, ct);
      return this.fromArray$mcI$sp(arr, cbf);
   }

   public Iterable qselected(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected$mcI$sp(k, ev, ct, cbf);
   }

   public Iterable qselected$mcI$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      int[] arr = (int[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Selection$.MODULE$.select$mIc$sp(arr, k, ev, ct);
      return this.fromArray$mcI$sp(arr, cbf);
   }

   public Iterable qselectk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk$mcI$sp(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcI$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      int[] arr = (int[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         var10000 = this.fromArray$mcI$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mIc$sp(arr, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcI$sp(k, arr, cbf);
      }

      return var10000;
   }

   public Iterable qtopk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk$mcI$sp(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcI$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      int[] arr = (int[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         Sorting$.MODULE$.sort$mIc$sp(arr, ev, ct);
         var10000 = this.fromArray$mcI$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mIc$sp(arr, k, ev, ct);
         QuickSort$.MODULE$.qsort$mIc$sp(arr, 0, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcI$sp(k, arr, cbf);
      }

      return var10000;
   }

   public int qchoose(final Generator gen) {
      return this.qchoose$mcI$sp(gen);
   }

   public int qchoose$mcI$sp(final Generator gen) {
      return BoxesRunTime.unboxToInt(gen.chooseFromIterable(this.spire$syntax$std$SeqOps$$as, gen));
   }

   // $FF: synthetic method
   public static final Object $anonfun$qnormWith$6(final Field ev$50, final Signed s$12, final Function1 f$11, final int p$12, final Object t, final int a) {
      return ev$50.plus(t, ev$50.pow(s$12.abs(f$11.apply(BoxesRunTime.boxToInteger(a))), p$12));
   }

   public SeqOps$mcI$sp(final Iterable as) {
      super(as);
      this.as = as;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
