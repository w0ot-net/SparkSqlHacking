package scala.math;

public interface Ordering$Float$IeeeOrdering extends Ordering {
   // $FF: synthetic method
   static int compare$(final Ordering$Float$IeeeOrdering $this, final float x, final float y) {
      return $this.compare(x, y);
   }

   default int compare(final float x, final float y) {
      return Float.compare(x, y);
   }

   // $FF: synthetic method
   static boolean lteq$(final Ordering$Float$IeeeOrdering $this, final float x, final float y) {
      return $this.lteq(x, y);
   }

   default boolean lteq(final float x, final float y) {
      return x <= y;
   }

   // $FF: synthetic method
   static boolean gteq$(final Ordering$Float$IeeeOrdering $this, final float x, final float y) {
      return $this.gteq(x, y);
   }

   default boolean gteq(final float x, final float y) {
      return x >= y;
   }

   // $FF: synthetic method
   static boolean lt$(final Ordering$Float$IeeeOrdering $this, final float x, final float y) {
      return $this.lt(x, y);
   }

   default boolean lt(final float x, final float y) {
      return x < y;
   }

   // $FF: synthetic method
   static boolean gt$(final Ordering$Float$IeeeOrdering $this, final float x, final float y) {
      return $this.gt(x, y);
   }

   default boolean gt(final float x, final float y) {
      return x > y;
   }

   // $FF: synthetic method
   static boolean equiv$(final Ordering$Float$IeeeOrdering $this, final float x, final float y) {
      return $this.equiv(x, y);
   }

   default boolean equiv(final float x, final float y) {
      return x == y;
   }

   // $FF: synthetic method
   static float max$(final Ordering$Float$IeeeOrdering $this, final float x, final float y) {
      return $this.max(x, y);
   }

   default float max(final float x, final float y) {
      package$ var10000 = package$.MODULE$;
      return Math.max(x, y);
   }

   // $FF: synthetic method
   static float min$(final Ordering$Float$IeeeOrdering $this, final float x, final float y) {
      return $this.min(x, y);
   }

   default float min(final float x, final float y) {
      package$ var10000 = package$.MODULE$;
      return Math.min(x, y);
   }

   static void $init$(final Ordering$Float$IeeeOrdering $this) {
   }
}
