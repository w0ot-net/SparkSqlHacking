package scala.math;

public interface Equiv$Float$IeeeEquiv extends Equiv {
   // $FF: synthetic method
   static boolean equiv$(final Equiv$Float$IeeeEquiv $this, final float x, final float y) {
      return $this.equiv(x, y);
   }

   default boolean equiv(final float x, final float y) {
      return x == y;
   }

   static void $init$(final Equiv$Float$IeeeEquiv $this) {
   }
}
