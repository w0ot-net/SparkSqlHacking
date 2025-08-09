package cats.kernel;

public interface Order$mcI$sp extends Order, PartialOrder$mcI$sp {
   // $FF: synthetic method
   static Comparison comparison$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.comparison(x, y);
   }

   default Comparison comparison(final int x, final int y) {
      return this.comparison$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static Comparison comparison$mcI$sp$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.comparison$mcI$sp(x, y);
   }

   default Comparison comparison$mcI$sp(final int x, final int y) {
      return Comparison$.MODULE$.fromInt(this.compare$mcI$sp(x, y));
   }

   // $FF: synthetic method
   static double partialCompare$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.partialCompare(x, y);
   }

   default double partialCompare(final int x, final int y) {
      return this.partialCompare$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static double partialCompare$mcI$sp$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.partialCompare$mcI$sp(x, y);
   }

   default double partialCompare$mcI$sp(final int x, final int y) {
      return (double)this.compare$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static int min$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.min(x, y);
   }

   default int min(final int x, final int y) {
      return this.min$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static int min$mcI$sp$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.min$mcI$sp(x, y);
   }

   default int min$mcI$sp(final int x, final int y) {
      return this.lt$mcI$sp(x, y) ? x : y;
   }

   // $FF: synthetic method
   static int max$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.max(x, y);
   }

   default int max(final int x, final int y) {
      return this.max$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static int max$mcI$sp$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.max$mcI$sp(x, y);
   }

   default int max$mcI$sp(final int x, final int y) {
      return this.gt$mcI$sp(x, y) ? x : y;
   }

   // $FF: synthetic method
   static boolean eqv$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final int x, final int y) {
      return this.eqv$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcI$sp$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.eqv$mcI$sp(x, y);
   }

   default boolean eqv$mcI$sp(final int x, final int y) {
      return this.compare$mcI$sp(x, y) == 0;
   }

   // $FF: synthetic method
   static boolean neqv$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final int x, final int y) {
      return this.neqv$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcI$sp$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.neqv$mcI$sp(x, y);
   }

   default boolean neqv$mcI$sp(final int x, final int y) {
      return !this.eqv$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final int x, final int y) {
      return this.lteqv$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcI$sp$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.lteqv$mcI$sp(x, y);
   }

   default boolean lteqv$mcI$sp(final int x, final int y) {
      return this.compare$mcI$sp(x, y) <= 0;
   }

   // $FF: synthetic method
   static boolean lt$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.lt(x, y);
   }

   default boolean lt(final int x, final int y) {
      return this.lt$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcI$sp$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.lt$mcI$sp(x, y);
   }

   default boolean lt$mcI$sp(final int x, final int y) {
      return this.compare$mcI$sp(x, y) < 0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final int x, final int y) {
      return this.gteqv$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcI$sp$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.gteqv$mcI$sp(x, y);
   }

   default boolean gteqv$mcI$sp(final int x, final int y) {
      return this.compare$mcI$sp(x, y) >= 0;
   }

   // $FF: synthetic method
   static boolean gt$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.gt(x, y);
   }

   default boolean gt(final int x, final int y) {
      return this.gt$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcI$sp$(final Order$mcI$sp $this, final int x, final int y) {
      return $this.gt$mcI$sp(x, y);
   }

   default boolean gt$mcI$sp(final int x, final int y) {
      return this.compare$mcI$sp(x, y) > 0;
   }
}
