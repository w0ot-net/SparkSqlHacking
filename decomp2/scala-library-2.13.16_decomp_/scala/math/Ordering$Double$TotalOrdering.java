package scala.math;

public interface Ordering$Double$TotalOrdering extends Ordering {
   // $FF: synthetic method
   static int compare$(final Ordering$Double$TotalOrdering $this, final double x, final double y) {
      return $this.compare(x, y);
   }

   default int compare(final double x, final double y) {
      return Double.compare(x, y);
   }

   static void $init$(final Ordering$Double$TotalOrdering $this) {
   }
}
