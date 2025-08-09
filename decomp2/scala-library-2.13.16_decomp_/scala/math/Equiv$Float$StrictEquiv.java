package scala.math;

public interface Equiv$Float$StrictEquiv extends Equiv {
   // $FF: synthetic method
   static boolean equiv$(final Equiv$Float$StrictEquiv $this, final float x, final float y) {
      return $this.equiv(x, y);
   }

   default boolean equiv(final float x, final float y) {
      return Float.compare(x, y) == 0;
   }

   static void $init$(final Equiv$Float$StrictEquiv $this) {
   }
}
