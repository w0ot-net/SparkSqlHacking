package algebra.ring;

import cats.kernel.CommutativeMonoid;
import cats.kernel.Eq;
import java.io.Serializable;
import scala.Option;
import scala.collection.IterableOnce;
import scala.runtime.ModuleSerializationProxy;

public final class AdditiveCommutativeMonoid$ implements AdditiveMonoidFunctions, Serializable {
   public static final AdditiveCommutativeMonoid$ MODULE$ = new AdditiveCommutativeMonoid$();

   static {
      AdditiveSemigroupFunctions.$init$(MODULE$);
      AdditiveMonoidFunctions.$init$(MODULE$);
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

   public final AdditiveCommutativeMonoid apply(final AdditiveCommutativeMonoid ev) {
      return ev;
   }

   public final CommutativeMonoid additive(final AdditiveCommutativeMonoid ev) {
      return ev.additive();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AdditiveCommutativeMonoid$.class);
   }

   private AdditiveCommutativeMonoid$() {
   }
}
