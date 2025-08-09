package breeze.math;

public interface Semiring$mcD$sp extends Semiring {
   // $FF: synthetic method
   static boolean close$(final Semiring$mcD$sp $this, final double a, final double b, final double tolerance) {
      return $this.close(a, b, tolerance);
   }

   default boolean close(final double a, final double b, final double tolerance) {
      return this.close$mcD$sp(a, b, tolerance);
   }

   // $FF: synthetic method
   static boolean close$mcD$sp$(final Semiring$mcD$sp $this, final double a, final double b, final double tolerance) {
      return $this.close$mcD$sp(a, b, tolerance);
   }

   default boolean close$mcD$sp(final double a, final double b, final double tolerance) {
      return a == b;
   }
}
