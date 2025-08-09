package breeze.math;

public interface Semiring$mcI$sp extends Semiring {
   // $FF: synthetic method
   static boolean close$(final Semiring$mcI$sp $this, final int a, final int b, final double tolerance) {
      return $this.close(a, b, tolerance);
   }

   default boolean close(final int a, final int b, final double tolerance) {
      return this.close$mcI$sp(a, b, tolerance);
   }

   // $FF: synthetic method
   static boolean close$mcI$sp$(final Semiring$mcI$sp $this, final int a, final int b, final double tolerance) {
      return $this.close$mcI$sp(a, b, tolerance);
   }

   default boolean close$mcI$sp(final int a, final int b, final double tolerance) {
      return a == b;
   }
}
