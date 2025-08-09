package algebra.ring;

import cats.kernel.CommutativeMonoid;
import cats.kernel.Eq;
import java.io.Serializable;
import scala.Option;
import scala.collection.IterableOnce;
import scala.runtime.ModuleSerializationProxy;

public final class MultiplicativeCommutativeMonoid$ implements MultiplicativeMonoidFunctions, Serializable {
   public static final MultiplicativeCommutativeMonoid$ MODULE$ = new MultiplicativeCommutativeMonoid$();

   static {
      MultiplicativeSemigroupFunctions.$init$(MODULE$);
      MultiplicativeMonoidFunctions.$init$(MODULE$);
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

   public final MultiplicativeCommutativeMonoid apply(final MultiplicativeCommutativeMonoid ev) {
      return ev;
   }

   public final CommutativeMonoid multiplicative(final MultiplicativeCommutativeMonoid ev) {
      return ev.multiplicative();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MultiplicativeCommutativeMonoid$.class);
   }

   private MultiplicativeCommutativeMonoid$() {
   }
}
