package scala.math;

public interface Ordering$Double$IeeeOrdering extends Ordering {
   // $FF: synthetic method
   static int compare$(final Ordering$Double$IeeeOrdering $this, final double x, final double y) {
      return $this.compare(x, y);
   }

   default int compare(final double x, final double y) {
      return Double.compare(x, y);
   }

   // $FF: synthetic method
   static boolean lteq$(final Ordering$Double$IeeeOrdering $this, final double x, final double y) {
      return $this.lteq(x, y);
   }

   default boolean lteq(final double x, final double y) {
      return x <= y;
   }

   // $FF: synthetic method
   static boolean gteq$(final Ordering$Double$IeeeOrdering $this, final double x, final double y) {
      return $this.gteq(x, y);
   }

   default boolean gteq(final double x, final double y) {
      return x >= y;
   }

   // $FF: synthetic method
   static boolean lt$(final Ordering$Double$IeeeOrdering $this, final double x, final double y) {
      return $this.lt(x, y);
   }

   default boolean lt(final double x, final double y) {
      return x < y;
   }

   // $FF: synthetic method
   static boolean gt$(final Ordering$Double$IeeeOrdering $this, final double x, final double y) {
      return $this.gt(x, y);
   }

   default boolean gt(final double x, final double y) {
      return x > y;
   }

   // $FF: synthetic method
   static boolean equiv$(final Ordering$Double$IeeeOrdering $this, final double x, final double y) {
      return $this.equiv(x, y);
   }

   default boolean equiv(final double x, final double y) {
      return x == y;
   }

   // $FF: synthetic method
   static double max$(final Ordering$Double$IeeeOrdering $this, final double x, final double y) {
      return $this.max(x, y);
   }

   default double max(final double x, final double y) {
      package$ var10000 = package$.MODULE$;
      return Math.max(x, y);
   }

   // $FF: synthetic method
   static double min$(final Ordering$Double$IeeeOrdering $this, final double x, final double y) {
      return $this.min(x, y);
   }

   default double min(final double x, final double y) {
      package$ var10000 = package$.MODULE$;
      return Math.min(x, y);
   }

   static void $init$(final Ordering$Double$IeeeOrdering $this) {
   }
}
