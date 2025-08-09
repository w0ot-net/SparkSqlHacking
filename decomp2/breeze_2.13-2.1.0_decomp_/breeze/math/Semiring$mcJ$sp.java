package breeze.math;

public interface Semiring$mcJ$sp extends Semiring {
   // $FF: synthetic method
   static boolean close$(final Semiring$mcJ$sp $this, final long a, final long b, final double tolerance) {
      return $this.close(a, b, tolerance);
   }

   default boolean close(final long a, final long b, final double tolerance) {
      return this.close$mcJ$sp(a, b, tolerance);
   }

   // $FF: synthetic method
   static boolean close$mcJ$sp$(final Semiring$mcJ$sp $this, final long a, final long b, final double tolerance) {
      return $this.close$mcJ$sp(a, b, tolerance);
   }

   default boolean close$mcJ$sp(final long a, final long b, final double tolerance) {
      return a == b;
   }
}
