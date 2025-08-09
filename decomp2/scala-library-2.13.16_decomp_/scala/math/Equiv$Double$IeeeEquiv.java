package scala.math;

public interface Equiv$Double$IeeeEquiv extends Equiv {
   // $FF: synthetic method
   static boolean equiv$(final Equiv$Double$IeeeEquiv $this, final double x, final double y) {
      return $this.equiv(x, y);
   }

   default boolean equiv(final double x, final double y) {
      return x == y;
   }

   static void $init$(final Equiv$Double$IeeeEquiv $this) {
   }
}
