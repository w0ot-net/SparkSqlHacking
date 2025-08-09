package scala.math;

public interface Equiv$Double$StrictEquiv extends Equiv {
   // $FF: synthetic method
   static boolean equiv$(final Equiv$Double$StrictEquiv $this, final double x, final double y) {
      return $this.equiv(x, y);
   }

   default boolean equiv(final double x, final double y) {
      return Double.compare(x, y) == 0;
   }

   static void $init$(final Equiv$Double$StrictEquiv $this) {
   }
}
