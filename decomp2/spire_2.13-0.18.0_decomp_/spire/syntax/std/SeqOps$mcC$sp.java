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
import scala.runtime.CharRef;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import spire.algebra.NRoot;
import spire.math.QuickSort$;
import spire.math.Searching$;
import spire.math.Selection$;
import spire.math.Sorting$;
import spire.random.Generator;

public final class SeqOps$mcC$sp extends SeqOps {
   private final Iterable as;

   public char qsum(final AdditiveMonoid ev) {
      return this.qsum$mcC$sp(ev);
   }

   public char qsum$mcC$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToChar(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.zero(), (x, y) -> BoxesRunTime.boxToCharacter($anonfun$qsum$3(ev, BoxesRunTime.unboxToChar(x), BoxesRunTime.unboxToChar(y)))));
   }

   public char qproduct(final MultiplicativeMonoid ev) {
      return this.qproduct$mcC$sp(ev);
   }

   public char qproduct$mcC$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToChar(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (x, y) -> BoxesRunTime.boxToCharacter($anonfun$qproduct$3(ev, BoxesRunTime.unboxToChar(x), BoxesRunTime.unboxToChar(y)))));
   }

   public char qcombine(final Monoid ev) {
      return this.qcombine$mcC$sp(ev);
   }

   public char qcombine$mcC$sp(final Monoid ev) {
      return BoxesRunTime.unboxToChar(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.empty(), (x, y) -> BoxesRunTime.boxToCharacter($anonfun$qcombine$3(ev, BoxesRunTime.unboxToChar(x), BoxesRunTime.unboxToChar(y)))));
   }

   public char qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return this.qnorm$mcC$sp(p, ev, s, nr);
   }

   public char qnorm$mcC$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToChar(nr.nroot(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (x$19, x$20) -> BoxesRunTime.boxToCharacter($anonfun$qnorm$3(ev, s, p, BoxesRunTime.unboxToChar(x$19), BoxesRunTime.unboxToChar(x$20)))), p));
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mcC$sp(p, f, ev, s, nr);
   }

   public Object qnormWith$mcC$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return nr.nroot(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (t, a) -> $anonfun$qnormWith$3(ev, s, f, p, t, BoxesRunTime.unboxToChar(a))), p);
   }

   public Seq pmin(final PartialOrder ev) {
      return this.pmin$mcC$sp(ev);
   }

   public Seq pmin$mcC$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, ev);
   }

   public Seq pmax(final PartialOrder ev) {
      return this.pmax$mcC$sp(ev);
   }

   public Seq pmax$mcC$sp(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, spire.algebra.package$.MODULE$.PartialOrder().reverse$mCc$sp(ev));
   }

   public char qmin(final Order ev) {
      return this.qmin$mcC$sp(ev);
   }

   public char qmin$mcC$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return BoxesRunTime.unboxToChar(this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (x, y) -> BoxesRunTime.boxToCharacter($anonfun$qmin$3(ev, BoxesRunTime.unboxToChar(x), BoxesRunTime.unboxToChar(y)))));
      }
   }

   public char qmax(final Order ev) {
      return this.qmax$mcC$sp(ev);
   }

   public char qmax$mcC$sp(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return BoxesRunTime.unboxToChar(this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (x, y) -> BoxesRunTime.boxToCharacter($anonfun$qmax$3(ev, BoxesRunTime.unboxToChar(x), BoxesRunTime.unboxToChar(y)))));
      }
   }

   public char qmean(final Field ev) {
      return this.qmean$mcC$sp(ev);
   }

   public char qmean$mcC$sp(final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         CharRef mean = CharRef.create(BoxesRunTime.unboxToChar(ev.zero()));
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((a) -> {
            $anonfun$qmean$3(ev, mean, i, j, BoxesRunTime.unboxToChar(a));
            return BoxedUnit.UNIT;
         });
         return mean.elem;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      return this.qmeanWith$mcC$sp(f, ev);
   }

   public Object qmeanWith$mcC$sp(final Function1 f, final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         ObjectRef mean = ObjectRef.create(ev.zero());
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((a) -> {
            $anonfun$qmeanWith$3(ev, mean, i, j, f, BoxesRunTime.unboxToChar(a));
            return BoxedUnit.UNIT;
         });
         return mean.elem;
      }
   }

   public Iterable fromArray(final char[] arr, final Factory cbf) {
      return this.fromArray$mcC$sp(arr, cbf);
   }

   public Iterable fromArray$mcC$sp(final char[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(arr.length);

      for(int index$macro$1 = 0; index$macro$1 < arr.length; ++index$macro$1) {
         b.$plus$eq(BoxesRunTime.boxToCharacter(arr[index$macro$1]));
      }

      return (Iterable)b.result();
   }

   public Iterable fromSizeAndArray(final int size, final char[] arr, final Factory cbf) {
      return this.fromSizeAndArray$mcC$sp(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcC$sp(final int size, final char[] arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(size);

      for(int index$macro$1 = 0; index$macro$1 < size; ++index$macro$1) {
         b.$plus$eq(BoxesRunTime.boxToCharacter(arr[index$macro$1]));
      }

      return (Iterable)b.result();
   }

   public Iterable qsorted(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted$mcC$sp(ev, ct, cbf);
   }

   public Iterable qsorted$mcC$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      char[] arr = (char[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mCc$sp(arr, ev, ct);
      return this.fromArray$mcC$sp(arr, cbf);
   }

   public Iterable qsortedBy(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mcC$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      char[] arr = (char[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mCc$sp(arr, ord, ct);
      return this.fromArray$mcC$sp(arr, cbf);
   }

   public Iterable qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZcC$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCZc$sp(f, ev);
      char[] arr = (char[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mCc$sp(arr, ord, ct);
      return this.fromArray$mcC$sp(arr, cbf);
   }

   public Iterable qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBcC$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCBc$sp(f, ev);
      char[] arr = (char[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mCc$sp(arr, ord, ct);
      return this.fromArray$mcC$sp(arr, cbf);
   }

   public Iterable qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCcC$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCCc$sp(f, ev);
      char[] arr = (char[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mCc$sp(arr, ord, ct);
      return this.fromArray$mcC$sp(arr, cbf);
   }

   public Iterable qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDcC$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCDc$sp(f, ev);
      char[] arr = (char[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mCc$sp(arr, ord, ct);
      return this.fromArray$mcC$sp(arr, cbf);
   }

   public Iterable qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFcC$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCFc$sp(f, ev);
      char[] arr = (char[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mCc$sp(arr, ord, ct);
      return this.fromArray$mcC$sp(arr, cbf);
   }

   public Iterable qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIcC$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCIc$sp(f, ev);
      char[] arr = (char[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mCc$sp(arr, ord, ct);
      return this.fromArray$mcC$sp(arr, cbf);
   }

   public Iterable qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJcC$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCJc$sp(f, ev);
      char[] arr = (char[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mCc$sp(arr, ord, ct);
      return this.fromArray$mcC$sp(arr, cbf);
   }

   public Iterable qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mScC$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCSc$sp(f, ev);
      char[] arr = (char[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mCc$sp(arr, ord, ct);
      return this.fromArray$mcC$sp(arr, cbf);
   }

   public Iterable qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVcC$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by$mCVc$sp(f, ev);
      char[] arr = (char[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mCc$sp(arr, ord, ct);
      return this.fromArray$mcC$sp(arr, cbf);
   }

   public Iterable qsortedWith(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith$mcC$sp(f, ct, cbf);
   }

   public Iterable qsortedWith$mcC$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().from$mCc$sp(f);
      char[] arr = (char[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort$mCc$sp(arr, ord, ct);
      return this.fromArray$mcC$sp(arr, cbf);
   }

   public Iterable qselected(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected$mcC$sp(k, ev, ct, cbf);
   }

   public Iterable qselected$mcC$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      char[] arr = (char[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Selection$.MODULE$.select$mCc$sp(arr, k, ev, ct);
      return this.fromArray$mcC$sp(arr, cbf);
   }

   public Iterable qselectk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk$mcC$sp(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcC$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      char[] arr = (char[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         var10000 = this.fromArray$mcC$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mCc$sp(arr, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcC$sp(k, arr, cbf);
      }

      return var10000;
   }

   public Iterable qtopk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk$mcC$sp(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcC$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      char[] arr = (char[])this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (arr.length <= k) {
         Sorting$.MODULE$.sort$mCc$sp(arr, ev, ct);
         var10000 = this.fromArray$mcC$sp(arr, cbf);
      } else {
         Selection$.MODULE$.select$mCc$sp(arr, k, ev, ct);
         QuickSort$.MODULE$.qsort$mCc$sp(arr, 0, k, ev, ct);
         var10000 = this.fromSizeAndArray$mcC$sp(k, arr, cbf);
      }

      return var10000;
   }

   public char qchoose(final Generator gen) {
      return this.qchoose$mcC$sp(gen);
   }

   public char qchoose$mcC$sp(final Generator gen) {
      return BoxesRunTime.unboxToChar(gen.chooseFromIterable(this.spire$syntax$std$SeqOps$$as, gen));
   }

   // $FF: synthetic method
   public static final char $anonfun$qsum$3(final AdditiveMonoid ev$19, final char x, final char y) {
      return BoxesRunTime.unboxToChar(ev$19.plus(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y)));
   }

   // $FF: synthetic method
   public static final char $anonfun$qproduct$3(final MultiplicativeMonoid ev$20, final char x, final char y) {
      return BoxesRunTime.unboxToChar(ev$20.times(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y)));
   }

   // $FF: synthetic method
   public static final char $anonfun$qcombine$3(final Monoid ev$21, final char x, final char y) {
      return BoxesRunTime.unboxToChar(ev$21.combine(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y)));
   }

   // $FF: synthetic method
   public static final char $anonfun$qnorm$3(final Field ev$22, final Signed s$5, final int p$5, final char x$19, final char x$20) {
      return BoxesRunTime.unboxToChar(ev$22.plus(BoxesRunTime.boxToCharacter(x$19), BoxesRunTime.boxToCharacter(BoxesRunTime.unboxToChar(ev$22.pow(BoxesRunTime.boxToCharacter(BoxesRunTime.unboxToChar(s$5.abs(BoxesRunTime.boxToCharacter(x$20)))), p$5)))));
   }

   // $FF: synthetic method
   public static final Object $anonfun$qnormWith$3(final Field ev$23, final Signed s$6, final Function1 f$5, final int p$6, final Object t, final char a) {
      return ev$23.plus(t, ev$23.pow(s$6.abs(f$5.apply(BoxesRunTime.boxToCharacter(a))), p$6));
   }

   // $FF: synthetic method
   public static final char $anonfun$qmin$3(final Order ev$24, final char x, final char y) {
      return ev$24.min$mcC$sp(x, y);
   }

   // $FF: synthetic method
   public static final char $anonfun$qmax$3(final Order ev$25, final char x, final char y) {
      return ev$25.max$mcC$sp(x, y);
   }

   // $FF: synthetic method
   public static final void $anonfun$qmean$3(final Field ev$26, final CharRef mean$5, final IntRef i$5, final IntRef j$5, final char a) {
      char t = BoxesRunTime.unboxToChar(ev$26.div(ev$26.times(BoxesRunTime.boxToCharacter(mean$5.elem), ev$26.fromInt(i$5.elem)), ev$26.fromInt(j$5.elem)));
      char z = BoxesRunTime.unboxToChar(ev$26.div(BoxesRunTime.boxToCharacter(a), ev$26.fromInt(j$5.elem)));
      mean$5.elem = BoxesRunTime.unboxToChar(ev$26.plus(BoxesRunTime.boxToCharacter(t), BoxesRunTime.boxToCharacter(z)));
      ++i$5.elem;
      ++j$5.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$qmeanWith$3(final Field ev$27, final ObjectRef mean$6, final IntRef i$6, final IntRef j$6, final Function1 f$6, final char a) {
      Object t = ev$27.div(ev$27.times(mean$6.elem, ev$27.fromInt(i$6.elem)), ev$27.fromInt(j$6.elem));
      Object z = ev$27.div(f$6.apply(BoxesRunTime.boxToCharacter(a)), ev$27.fromInt(j$6.elem));
      mean$6.elem = ev$27.plus(t, z);
      ++i$6.elem;
      ++j$6.elem;
   }

   public SeqOps$mcC$sp(final Iterable as) {
      super(as);
      this.as = as;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
