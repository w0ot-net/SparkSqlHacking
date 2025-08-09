package cats.kernel;

public interface Order$mcD$sp extends Order, PartialOrder$mcD$sp {
   // $FF: synthetic method
   static Comparison comparison$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.comparison(x, y);
   }

   default Comparison comparison(final double x, final double y) {
      return this.comparison$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static Comparison comparison$mcD$sp$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.comparison$mcD$sp(x, y);
   }

   default Comparison comparison$mcD$sp(final double x, final double y) {
      return Comparison$.MODULE$.fromInt(this.compare$mcD$sp(x, y));
   }

   // $FF: synthetic method
   static double partialCompare$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.partialCompare(x, y);
   }

   default double partialCompare(final double x, final double y) {
      return this.partialCompare$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static double partialCompare$mcD$sp$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.partialCompare$mcD$sp(x, y);
   }

   default double partialCompare$mcD$sp(final double x, final double y) {
      return (double)this.compare$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static double min$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.min(x, y);
   }

   default double min(final double x, final double y) {
      return this.min$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static double min$mcD$sp$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.min$mcD$sp(x, y);
   }

   default double min$mcD$sp(final double x, final double y) {
      return this.lt$mcD$sp(x, y) ? x : y;
   }

   // $FF: synthetic method
   static double max$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.max(x, y);
   }

   default double max(final double x, final double y) {
      return this.max$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static double max$mcD$sp$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.max$mcD$sp(x, y);
   }

   default double max$mcD$sp(final double x, final double y) {
      return this.gt$mcD$sp(x, y) ? x : y;
   }

   // $FF: synthetic method
   static boolean eqv$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final double x, final double y) {
      return this.eqv$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcD$sp$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.eqv$mcD$sp(x, y);
   }

   default boolean eqv$mcD$sp(final double x, final double y) {
      return this.compare$mcD$sp(x, y) == 0;
   }

   // $FF: synthetic method
   static boolean neqv$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final double x, final double y) {
      return this.neqv$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcD$sp$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.neqv$mcD$sp(x, y);
   }

   default boolean neqv$mcD$sp(final double x, final double y) {
      return !this.eqv$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final double x, final double y) {
      return this.lteqv$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcD$sp$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.lteqv$mcD$sp(x, y);
   }

   default boolean lteqv$mcD$sp(final double x, final double y) {
      return this.compare$mcD$sp(x, y) <= 0;
   }

   // $FF: synthetic method
   static boolean lt$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.lt(x, y);
   }

   default boolean lt(final double x, final double y) {
      return this.lt$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcD$sp$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.lt$mcD$sp(x, y);
   }

   default boolean lt$mcD$sp(final double x, final double y) {
      return this.compare$mcD$sp(x, y) < 0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final double x, final double y) {
      return this.gteqv$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcD$sp$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.gteqv$mcD$sp(x, y);
   }

   default boolean gteqv$mcD$sp(final double x, final double y) {
      return this.compare$mcD$sp(x, y) >= 0;
   }

   // $FF: synthetic method
   static boolean gt$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.gt(x, y);
   }

   default boolean gt(final double x, final double y) {
      return this.gt$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcD$sp$(final Order$mcD$sp $this, final double x, final double y) {
      return $this.gt$mcD$sp(x, y);
   }

   default boolean gt$mcD$sp(final double x, final double y) {
      return this.compare$mcD$sp(x, y) > 0;
   }
}
