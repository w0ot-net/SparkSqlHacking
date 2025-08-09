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
import scala.runtime.LongRef;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;
import spire.algebra.NRoot;
import spire.math.QuickSort$;
import spire.math.Searching$;
import spire.math.Selection$;
import spire.math.Sorting$;
import spire.random.Generator;

public final class SeqOps$mcJ$sp extends SeqOps {
   private final Iterable as;

   public long qsum(final AdditiveMonoid ev) {
      return this.qsum$mcJ$sp(ev);
   }

   public long qsum$mcJ$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToLong(this.spire$syntax$std$SeqOps$$as.foldLeft(BoxesRunTime.boxToLong(ev.zero$mcJ$sp()), (JFunction2.mcJJJ.sp)(x, y) -> ev.plus$mcJ$sp(x, y)));
   }

   public long qproduct(final MultiplicativeMonoid ev) {
      return this.qproduct$mcJ$sp(ev);
   }

   public long qproduct$mcJ$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToLong(this.spire$syntax$std$SeqOps$$as.foldLeft(BoxesRunTime.boxToLong(ev.one$mcJ$sp()), (JFunction2.mcJJJ.sp)(x, y) -> ev.times$mcJ$sp(x, y)));
   }

   public long qcombine(final Monoid ev) {
      return this.qcombine$mcJ$sp(ev);
   }

   public long qcombine$mcJ$sp(final Monoid ev) {
      return BoxesRunTime.unboxToLong(this.spire$syntax$std$SeqOps$$as.foldLeft(BoxesRunTime.boxToLong(ev.empty$mcJ$sp()), (JFunction2.mcJJJ.sp)(x, y) -> ev.combine$mcJ$sp(x, y)));
   }

   public long qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return this.qnorm$mcJ$sp(p, ev, s, nr);
   }

   public long qnorm$mcJ$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return nr.nroot$mcJ$sp(BoxesRunTime.unboxToLong(this.spire$syntax$std$SeqOps$$as.foldLeft(BoxesRunTime.boxToLong(ev.one$mcJ$sp()), (JFunction2.mcJJJ.sp)(x$19, x$20) -> ev.plus$mcJ$sp(x$19, ev.pow$mcJ$sp(s.abs$mcJ$sp(x$20), p)))), p);
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcJ$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcJ$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return nr.nroot(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (t, a) -> $anonfun$qnormWith$7(ev, s, f, p, t, BoxesRunTime.unboxToLong(a))), p);
   }

   public Seq pmin(final PartialOrder ev) {
      return this.pmin$mcJ$sp(ev);
   }

   public Seq pmin$mcJ$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, ev);
   }

   public Seq pmax(final PartialOrder ev) {
      return this.pmax$mcJ$sp(ev);
   }

   public Seq pmax$mcJ$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, spire.algebra.package$.MODULE$.PartialOrder().reverse$mJc$sp(ev));
   }

   public long qmin(final Order ev) {
      return this.qmin$mcJ$sp(ev);
   }

   public long qmin$mcJ$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return BoxesRunTime.unboxToLong(this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (JFunction2.mcJJJ.sp)(x, y) -> ev.min$mcJ$sp(x, y)));
      }
   }

   public long qmax(final Order ev) {
      return this.qmax$mcJ$sp(ev);
   }

   public long qmax$mcJ$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return BoxesRunTime.unboxToLong(this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (JFunction2.mcJJJ.sp)(x, y) -> ev.max$mcJ$sp(x, y)));
      }
   }

   public long qmean(final Field ev) {
      return this.qmean$mcJ$sp(ev);
   }

   public long qmean$mcJ$sp(final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         LongRef mean = LongRef.create(ev.zero$mcJ$sp());
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((JFunction1.mcVJ.sp)(a) -> {
            long t = ev.div$mcJ$sp(ev.times$mcJ$sp(mean.elem, ev.fromInt$mcJ$sp(i.elem)), ev.fromInt$mcJ$sp(j.elem));
            long z = ev.div$mcJ$sp(a, ev.fromInt$mcJ$sp(j.elem));
            mean.elem = ev.plus$mcJ$sp(t, z);
            ++i.elem;
            ++j.elem;
         });
         return mean.elem;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcJ$sp(f, ev);
   }

   public Object qmeanWith$mcJ$sp(final Function1 f, final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         ObjectRef mean = ObjectRef.create(ev.zero());
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((JFunction1.mcVJ.sp)(a) -> {
            Object t = ev.div(ev.times(mean.elem, ev.fromInt(i.elem)), ev.fromInt(j.elem));
            Object z = ev.div(f.apply(BoxesRunTime.boxToLong(a)), ev.fromInt(j.elem));
            mean.elem = ev.plus(t, z);
            ++i.elem;
            ++j.elem;
         });
         return mean.elem;
      }
   }

   public Iterable fromArray(final long[] arr, final Factory cbf) {
      return this.fromArray$mcJ$sp(arr, cbf);
   }

   public Iterable fromArray$mcJ$sp(final long[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(arr.length);

      for(int index$macro$1 = 0; index$macro$1 < arr.length; ++index$macro$1) {
         b.$plus$eq(BoxesRunTime.boxToLong(arr[index$macro$1]));
      }

      return (Iterable)b.result();
   }

   public Iterable fromSizeAndArray(final int size, final long[] arr, final Factory cbf) {
      return this.fromSizeAndArray$mcJ$sp(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcJ$sp(final int size, final long[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(size);

      for(int index$macro$1 = 0; index$macro$1 < size; ++index$macro$1) {
         b.$plus$eq(BoxesRunTime.boxToLong(arr[index$macro$1]));
      }

      return (Iterable)b.result();
   }

   public Iterable qsorted(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted$mcJ$sp(ev, ct, cbf);
   }

   public Iterable qsorted$mcJ$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      long[] arr = (long[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mJc$sp(arr, ev, ct);
      return this.fromArray$mcJ$sp(arr, cbf);
   }

   public Iterable qsortedBy(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mcJ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      long[] arr = (long[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mJc$sp(arr, ord, ct);
      return this.fromArray$mcJ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZcJ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJZc$sp(f, ev);
      long[] arr = (long[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mJc$sp(arr, ord, ct);
      return this.fromArray$mcJ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBcJ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJBc$sp(f, ev);
      long[] arr = (long[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mJc$sp(arr, ord, ct);
      return this.fromArray$mcJ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCcJ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJCc$sp(f, ev);
      long[] arr = (long[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mJc$sp(arr, ord, ct);
      return this.fromArray$mcJ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDcJ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJDc$sp(f, ev);
      long[] arr = (long[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mJc$sp(arr, ord, ct);
      return this.fromArray$mcJ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFcJ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJFc$sp(f, ev);
      long[] arr = (long[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mJc$sp(arr, ord, ct);
      return this.fromArray$mcJ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIcJ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJIc$sp(f, ev);
      long[] arr = (long[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mJc$sp(arr, ord, ct);
      return this.fromArray$mcJ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJcJ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJJc$sp(f, ev);
      long[] arr = (long[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mJc$sp(arr, ord, ct);
      return this.fromArray$mcJ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mScJ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJSc$sp(f, ev);
      long[] arr = (long[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mJc$sp(arr, ord, ct);
      return this.fromArray$mcJ$sp(arr, cbf);
   }

   public Iterable qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVcJ$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mJVc$sp(f, ev);
      long[] arr = (long[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mJc$sp(arr, ord, ct);
      return this.fromArray$mcJ$sp(arr, cbf);
   }

   public Iterable qsortedWith(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith$mcJ$sp(f, ct, cbf);
   }

   public Iterable qsortedWith$mcJ$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mJc$sp(f);
      long[] arr = (long[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mJc$sp(arr, ord, ct);
      return this.fromArray$mcJ$sp(arr, cbf);
   }

   public Iterable qselected(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected$mcJ$sp(k, ev, ct, cbf);
   }

   public Iterable qselected$mcJ$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      long[] arr = (long[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Selection$.MODULE$.select$mJc$sp(arr, k, ev, ct);
      return this.fromArray$mcJ$sp(arr, cbf);
   }

   public Iterable qselectk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk$mcJ$sp(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcJ$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      long[] arr = (long[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         var10000 = this.fromArray$mcJ$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mJc$sp(arr, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcJ$sp(k, arr, cbf);
      }

      return var10000;
   }

   public Iterable qtopk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk$mcJ$sp(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcJ$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      long[] arr = (long[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         Sorting$.MODULE$.sort$mJc$sp(arr, ev, ct);
         var10000 = this.fromArray$mcJ$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mJc$sp(arr, k, ev, ct);
         QuickSort$.MODULE$.qsort$mJc$sp(arr, 0, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcJ$sp(k, arr, cbf);
      }

      return var10000;
   }

   public long qchoose(final Generator gen) {
      return this.qchoose$mcJ$sp(gen);
   }

   public long qchoose$mcJ$sp(final Generator gen) {
      return BoxesRunTime.unboxToLong(gen.chooseFromIterable(this.spire$syntax$std$SeqOps$$as, gen));
   }

   // $FF: synthetic method
   public static final Object $anonfun$qnormWith$7(final Field ev$59, final Signed s$14, final Function1 f$13, final int p$14, final Object t, final long a) {
      return ev$59.plus(t, ev$59.pow(s$14.abs(f$13.apply(BoxesRunTime.boxToLong(a))), p$14));
   }

   public SeqOps$mcJ$sp(final Iterable as) {
      super(as);
      this.as = as;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
