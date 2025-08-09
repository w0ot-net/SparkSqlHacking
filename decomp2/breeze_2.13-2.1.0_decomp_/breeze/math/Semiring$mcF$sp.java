package breeze.math;

public interface Semiring$mcF$sp extends Semiring {
   // $FF: synthetic method
   static boolean close$(final Semiring$mcF$sp $this, final float a, final float b, final double tolerance) {
      return $this.close(a, b, tolerance);
   }

   default boolean close(final float a, final float b, final double tolerance) {
      return this.close$mcF$sp(a, b, tolerance);
   }

   // $FF: synthetic method
   static boolean close$mcF$sp$(final Semiring$mcF$sp $this, final float a, final float b, final double tolerance) {
      return $this.close$mcF$sp(a, b, tolerance);
   }

   default boolean close$mcF$sp(final float a, final float b, final double tolerance) {
      return a == b;
   }
}
