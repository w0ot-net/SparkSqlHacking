package algebra.ring;

import cats.kernel.Eq;
import java.io.Serializable;
import scala.Option;
import scala.collection.IterableOnce;
import scala.runtime.ModuleSerializationProxy;

public final class CommutativeSemifield$ implements AdditiveMonoidFunctions, MultiplicativeGroupFunctions, Serializable {
   public static final CommutativeSemifield$ MODULE$ = new CommutativeSemifield$();

   static {
      AdditiveSemigroupFunctions.$init$(MODULE$);
      AdditiveMonoidFunctions.$init$(MODULE$);
      MultiplicativeSemigroupFunctions.$init$(MODULE$);
      MultiplicativeMonoidFunctions.$init$(MODULE$);
      MultiplicativeGroupFunctions.$init$(MODULE$);
   }

   public Object reciprocal(final Object x, final MultiplicativeGroup ev) {
      return MultiplicativeGroupFunctions.reciprocal$(this, x, ev);
   }

   public double reciprocal$mDc$sp(final double x, final MultiplicativeGroup ev) {
      return MultiplicativeGroupFunctions.reciprocal$mDc$sp$(this, x, ev);
   }

   public float reciprocal$mFc$sp(final float x, final MultiplicativeGroup ev) {
      return MultiplicativeGroupFunctions.reciprocal$mFc$sp$(this, x, ev);
   }

   public int reciprocal$mIc$sp(final int x, final MultiplicativeGroup ev) {
      return MultiplicativeGroupFunctions.reciprocal$mIc$sp$(this, x, ev);
   }

   public long reciprocal$mJc$sp(final long x, final MultiplicativeGroup ev) {
      return MultiplicativeGroupFunctions.reciprocal$mJc$sp$(this, x, ev);
   }

   public Object div(final Object x, final Object y, final MultiplicativeGroup ev) {
      return MultiplicativeGroupFunctions.div$(this, x, y, ev);
   }

   public double div$mDc$sp(final double x, final double y, final MultiplicativeGroup ev) {
      return MultiplicativeGroupFunctions.div$mDc$sp$(this, x, y, ev);
   }

   public float div$mFc$sp(final float x, final float y, final MultiplicativeGroup ev) {
      return MultiplicativeGroupFunctions.div$mFc$sp$(this, x, y, ev);
   }

   public int div$mIc$sp(final int x, final int y, final MultiplicativeGroup ev) {
      return MultiplicativeGroupFunctions.div$mIc$sp$(this, x, y, ev);
   }

   public long div$mJc$sp(final long x, final long y, final MultiplicativeGroup ev) {
      return MultiplicativeGroupFunctions.div$mJc$sp$(this, x, y, ev);
   }

   public Object one(final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.one$(this, ev);
   }

   public double one$mDc$sp(final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.one$mDc$sp$(this, ev);
   }

   public float one$mFc$sp(final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.one$mFc$sp$(this, ev);
   }

   public int one$mIc$sp(final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.one$mIc$sp$(this, ev);
   }

   public long one$mJc$sp(final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.one$mJc$sp$(this, ev);
   }

   public boolean isOne(final Object a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return MultiplicativeMonoidFunctions.isOne$(this, a, ev0, ev1);
   }

   public boolean isOne$mDc$sp(final double a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return MultiplicativeMonoidFunctions.isOne$mDc$sp$(this, a, ev0, ev1);
   }

   public boolean isOne$mFc$sp(final float a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return MultiplicativeMonoidFunctions.isOne$mFc$sp$(this, a, ev0, ev1);
   }

   public boolean isOne$mIc$sp(final int a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return MultiplicativeMonoidFunctions.isOne$mIc$sp$(this, a, ev0, ev1);
   }

   public boolean isOne$mJc$sp(final long a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return MultiplicativeMonoidFunctions.isOne$mJc$sp$(this, a, ev0, ev1);
   }

   public Object product(final IterableOnce as, final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.product$(this, as, ev);
   }

   public double product$mDc$sp(final IterableOnce as, final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.product$mDc$sp$(this, as, ev);
   }

   public float product$mFc$sp(final IterableOnce as, final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.product$mFc$sp$(this, as, ev);
   }

   public int product$mIc$sp(final IterableOnce as, final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.product$mIc$sp$(this, as, ev);
   }

   public long product$mJc$sp(final IterableOnce as, final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.product$mJc$sp$(this, as, ev);
   }

   public boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.isMultiplicativeCommutative$(this, ev);
   }

   public Object times(final Object x, final Object y, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.times$(this, x, y, ev);
   }

   public double times$mDc$sp(final double x, final double y, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.times$mDc$sp$(this, x, y, ev);
   }

   public float times$mFc$sp(final float x, final float y, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.times$mFc$sp$(this, x, y, ev);
   }

   public int times$mIc$sp(final int x, final int y, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.times$mIc$sp$(this, x, y, ev);
   }

   public long times$mJc$sp(final long x, final long y, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.times$mJc$sp$(this, x, y, ev);
   }

   public Object pow(final Object a, final int n, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.pow$(this, a, n, ev);
   }

   public double pow$mDc$sp(final double a, final int n, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.pow$mDc$sp$(this, a, n, ev);
   }

   public float pow$mFc$sp(final float a, final int n, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.pow$mFc$sp$(this, a, n, ev);
   }

   public int pow$mIc$sp(final int a, final int n, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.pow$mIc$sp$(this, a, n, ev);
   }

   public long pow$mJc$sp(final long a, final int n, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.pow$mJc$sp$(this, a, n, ev);
   }

   public Option tryProduct(final IterableOnce as, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.tryProduct$(this, as, ev);
   }

   public Object zero(final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.zero$(this, ev);
   }

   public double zero$mDc$sp(final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.zero$mDc$sp$(this, ev);
   }

   public float zero$mFc$sp(final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.zero$mFc$sp$(this, ev);
   }

   public int zero$mIc$sp(final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.zero$mIc$sp$(this, ev);
   }

   public long zero$mJc$sp(final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.zero$mJc$sp$(this, ev);
   }

   public boolean isZero(final Object a, final AdditiveMonoid ev0, final Eq ev1) {
      return AdditiveMonoidFunctions.isZero$(this, a, ev0, ev1);
   }

   public boolean isZero$mDc$sp(final double a, final AdditiveMonoid ev0, final Eq ev1) {
      return AdditiveMonoidFunctions.isZero$mDc$sp$(this, a, ev0, ev1);
   }

   public boolean isZero$mFc$sp(final float a, final AdditiveMonoid ev0, final Eq ev1) {
      return AdditiveMonoidFunctions.isZero$mFc$sp$(this, a, ev0, ev1);
   }

   public boolean isZero$mIc$sp(final int a, final AdditiveMonoid ev0, final Eq ev1) {
      return AdditiveMonoidFunctions.isZero$mIc$sp$(this, a, ev0, ev1);
   }

   public boolean isZero$mJc$sp(final long a, final AdditiveMonoid ev0, final Eq ev1) {
      return AdditiveMonoidFunctions.isZero$mJc$sp$(this, a, ev0, ev1);
   }

   public Object sum(final IterableOnce as, final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.sum$(this, as, ev);
   }

   public double sum$mDc$sp(final IterableOnce as, final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.sum$mDc$sp$(this, as, ev);
   }

   public float sum$mFc$sp(final IterableOnce as, final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.sum$mFc$sp$(this, as, ev);
   }

   public int sum$mIc$sp(final IterableOnce as, final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.sum$mIc$sp$(this, as, ev);
   }

   public long sum$mJc$sp(final IterableOnce as, final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.sum$mJc$sp$(this, as, ev);
   }

   public boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.isAdditiveCommutative$(this, ev);
   }

   public Object plus(final Object x, final Object y, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.plus$(this, x, y, ev);
   }

   public double plus$mDc$sp(final double x, final double y, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.plus$mDc$sp$(this, x, y, ev);
   }

   public float plus$mFc$sp(final float x, final float y, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.plus$mFc$sp$(this, x, y, ev);
   }

   public int plus$mIc$sp(final int x, final int y, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.plus$mIc$sp$(this, x, y, ev);
   }

   public long plus$mJc$sp(final long x, final long y, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.plus$mJc$sp$(this, x, y, ev);
   }

   public Object sumN(final Object a, final int n, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.sumN$(this, a, n, ev);
   }

   public double sumN$mDc$sp(final double a, final int n, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.sumN$mDc$sp$(this, a, n, ev);
   }

   public float sumN$mFc$sp(final float a, final int n, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.sumN$mFc$sp$(this, a, n, ev);
   }

   public int sumN$mIc$sp(final int a, final int n, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.sumN$mIc$sp$(this, a, n, ev);
   }

   public long sumN$mJc$sp(final long a, final int n, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.sumN$mJc$sp$(this, a, n, ev);
   }

   public Option trySum(final IterableOnce as, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.trySum$(this, as, ev);
   }

   public final CommutativeSemifield apply(final CommutativeSemifield r) {
      return r;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CommutativeSemifield$.class);
   }

   private CommutativeSemifield$() {
   }
}
