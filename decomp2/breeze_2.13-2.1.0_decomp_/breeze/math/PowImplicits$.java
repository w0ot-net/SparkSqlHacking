package breeze.math;

public final class PowImplicits$ {
   public static final PowImplicits$ MODULE$ = new PowImplicits$();

   public PowImplicits.DoublePow DoublePow(final double x) {
      return new PowImplicits.DoublePow(x);
   }

   public PowImplicits.FloatPow FloatPow(final float x) {
      return new PowImplicits.FloatPow(x);
   }

   public PowImplicits.IntPow IntPow(final int x) {
      return new PowImplicits.IntPow(x);
   }

   public PowImplicits.LongPow LongPow(final long x) {
      return new PowImplicits.LongPow(x);
   }

   private PowImplicits$() {
   }
}
