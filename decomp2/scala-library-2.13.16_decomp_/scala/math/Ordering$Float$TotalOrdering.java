package scala.math;

public interface Ordering$Float$TotalOrdering extends Ordering {
   // $FF: synthetic method
   static int compare$(final Ordering$Float$TotalOrdering $this, final float x, final float y) {
      return $this.compare(x, y);
   }

   default int compare(final float x, final float y) {
      return Float.compare(x, y);
   }

   static void $init$(final Ordering$Float$TotalOrdering $this) {
   }
}
