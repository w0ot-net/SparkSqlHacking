package algebra.ring;

import cats.kernel.CommutativeSemigroup;
import java.io.Serializable;
import scala.Option;
import scala.collection.IterableOnce;
import scala.runtime.ModuleSerializationProxy;

public final class MultiplicativeCommutativeSemigroup$ implements MultiplicativeSemigroupFunctions, Serializable {
   public static final MultiplicativeCommutativeSemigroup$ MODULE$ = new MultiplicativeCommutativeSemigroup$();

   static {
      MultiplicativeSemigroupFunctions.$init$(MODULE$);
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

   public final MultiplicativeCommutativeSemigroup apply(final MultiplicativeCommutativeSemigroup ev) {
      return ev;
   }

   public final CommutativeSemigroup multiplicative(final MultiplicativeCommutativeSemigroup ev) {
      return ev.multiplicative();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MultiplicativeCommutativeSemigroup$.class);
   }

   private MultiplicativeCommutativeSemigroup$() {
   }
}
