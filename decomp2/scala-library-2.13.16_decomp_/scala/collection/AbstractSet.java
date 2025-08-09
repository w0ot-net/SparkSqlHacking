package scala.collection;

import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00012QAA\u0002\u0002\u0002!AQ!\b\u0001\u0005\u0002y\u00111\"\u00112tiJ\f7\r^*fi*\u0011A!B\u0001\u000bG>dG.Z2uS>t'\"\u0001\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011\u0011\u0002E\n\u0004\u0001)Q\u0002cA\u0006\r\u001d5\t1!\u0003\u0002\u000e\u0007\t\u0001\u0012IY:ue\u0006\u001cG/\u0013;fe\u0006\u0014G.\u001a\t\u0003\u001fAa\u0001\u0001B\u0003\u0012\u0001\t\u0007!CA\u0001B#\t\u0019r\u0003\u0005\u0002\u0015+5\tQ!\u0003\u0002\u0017\u000b\t9aj\u001c;iS:<\u0007C\u0001\u000b\u0019\u0013\tIRAA\u0002B]f\u00042aC\u000e\u000f\u0013\ta2AA\u0002TKR\fa\u0001P5oSRtD#A\u0010\u0011\u0007-\u0001a\u0002"
)
public abstract class AbstractSet extends AbstractIterable implements Set {
   public boolean canEqual(final Object that) {
      return Set.canEqual$(this, that);
   }

   public boolean equals(final Object that) {
      return Set.equals$(this, that);
   }

   public int hashCode() {
      return Set.hashCode$(this);
   }

   public IterableFactory iterableFactory() {
      return Set.iterableFactory$(this);
   }

   public String stringPrefix() {
      return Set.stringPrefix$(this);
   }

   public String toString() {
      return Set.toString$(this);
   }

   public final boolean apply(final Object elem) {
      return SetOps.apply$(this, elem);
   }

   public boolean subsetOf(final Set that) {
      return SetOps.subsetOf$(this, that);
   }

   public Iterator subsets(final int len) {
      return SetOps.subsets$(this, len);
   }

   public Iterator subsets() {
      return SetOps.subsets$(this);
   }

   public SetOps intersect(final Set that) {
      return SetOps.intersect$(this, that);
   }

   public final SetOps $amp(final Set that) {
      return SetOps.$amp$(this, that);
   }

   public final SetOps $amp$tilde(final Set that) {
      return SetOps.$amp$tilde$(this, that);
   }

   /** @deprecated */
   public SetOps $minus$minus(final IterableOnce that) {
      return SetOps.$minus$minus$(this, that);
   }

   /** @deprecated */
   public SetOps $minus(final Object elem) {
      return SetOps.$minus$(this, elem);
   }

   /** @deprecated */
   public SetOps $minus(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      return SetOps.$minus$(this, elem1, elem2, elems);
   }

   public SetOps concat(final IterableOnce that) {
      return SetOps.concat$(this, that);
   }

   /** @deprecated */
   public SetOps $plus(final Object elem) {
      return SetOps.$plus$(this, elem);
   }

   /** @deprecated */
   public SetOps $plus(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      return SetOps.$plus$(this, elem1, elem2, elems);
   }

   public final SetOps $plus$plus(final IterableOnce that) {
      return SetOps.$plus$plus$(this, that);
   }

   public final SetOps union(final Set that) {
      return SetOps.union$(this, that);
   }

   public final SetOps $bar(final Set that) {
      return SetOps.$bar$(this, that);
   }

   public boolean apply$mcZD$sp(final double v1) {
      return Function1.apply$mcZD$sp$(this, v1);
   }

   public double apply$mcDD$sp(final double v1) {
      return Function1.apply$mcDD$sp$(this, v1);
   }

   public float apply$mcFD$sp(final double v1) {
      return Function1.apply$mcFD$sp$(this, v1);
   }

   public int apply$mcID$sp(final double v1) {
      return Function1.apply$mcID$sp$(this, v1);
   }

   public long apply$mcJD$sp(final double v1) {
      return Function1.apply$mcJD$sp$(this, v1);
   }

   public void apply$mcVD$sp(final double v1) {
      Function1.apply$mcVD$sp$(this, v1);
   }

   public boolean apply$mcZF$sp(final float v1) {
      return Function1.apply$mcZF$sp$(this, v1);
   }

   public double apply$mcDF$sp(final float v1) {
      return Function1.apply$mcDF$sp$(this, v1);
   }

   public float apply$mcFF$sp(final float v1) {
      return Function1.apply$mcFF$sp$(this, v1);
   }

   public int apply$mcIF$sp(final float v1) {
      return Function1.apply$mcIF$sp$(this, v1);
   }

   public long apply$mcJF$sp(final float v1) {
      return Function1.apply$mcJF$sp$(this, v1);
   }

   public void apply$mcVF$sp(final float v1) {
      Function1.apply$mcVF$sp$(this, v1);
   }

   public boolean apply$mcZI$sp(final int v1) {
      return Function1.apply$mcZI$sp$(this, v1);
   }

   public double apply$mcDI$sp(final int v1) {
      return Function1.apply$mcDI$sp$(this, v1);
   }

   public float apply$mcFI$sp(final int v1) {
      return Function1.apply$mcFI$sp$(this, v1);
   }

   public int apply$mcII$sp(final int v1) {
      return Function1.apply$mcII$sp$(this, v1);
   }

   public long apply$mcJI$sp(final int v1) {
      return Function1.apply$mcJI$sp$(this, v1);
   }

   public void apply$mcVI$sp(final int v1) {
      Function1.apply$mcVI$sp$(this, v1);
   }

   public boolean apply$mcZJ$sp(final long v1) {
      return Function1.apply$mcZJ$sp$(this, v1);
   }

   public double apply$mcDJ$sp(final long v1) {
      return Function1.apply$mcDJ$sp$(this, v1);
   }

   public float apply$mcFJ$sp(final long v1) {
      return Function1.apply$mcFJ$sp$(this, v1);
   }

   public int apply$mcIJ$sp(final long v1) {
      return Function1.apply$mcIJ$sp$(this, v1);
   }

   public long apply$mcJJ$sp(final long v1) {
      return Function1.apply$mcJJ$sp$(this, v1);
   }

   public void apply$mcVJ$sp(final long v1) {
      Function1.apply$mcVJ$sp$(this, v1);
   }

   public Function1 compose(final Function1 g) {
      return Function1.compose$(this, g);
   }

   public Function1 andThen(final Function1 g) {
      return Function1.andThen$(this, g);
   }
}
