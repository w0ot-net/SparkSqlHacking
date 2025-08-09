package breeze.math;

public interface Semiring$mcS$sp extends Semiring {
   // $FF: synthetic method
   static boolean close$(final Semiring$mcS$sp $this, final short a, final short b, final double tolerance) {
      return $this.close(a, b, tolerance);
   }

   default boolean close(final short a, final short b, final double tolerance) {
      return this.close$mcS$sp(a, b, tolerance);
   }

   // $FF: synthetic method
   static boolean close$mcS$sp$(final Semiring$mcS$sp $this, final short a, final short b, final double tolerance) {
      return $this.close$mcS$sp(a, b, tolerance);
   }

   default boolean close$mcS$sp(final short a, final short b, final double tolerance) {
      return a == b;
   }
}
