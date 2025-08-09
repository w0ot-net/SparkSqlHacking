package cats.kernel;

public interface Order$mcS$sp extends Order, PartialOrder$mcS$sp {
   // $FF: synthetic method
   static Comparison comparison$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.comparison(x, y);
   }

   default Comparison comparison(final short x, final short y) {
      return this.comparison$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static Comparison comparison$mcS$sp$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.comparison$mcS$sp(x, y);
   }

   default Comparison comparison$mcS$sp(final short x, final short y) {
      return Comparison$.MODULE$.fromInt(this.compare$mcS$sp(x, y));
   }

   // $FF: synthetic method
   static double partialCompare$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.partialCompare(x, y);
   }

   default double partialCompare(final short x, final short y) {
      return this.partialCompare$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static double partialCompare$mcS$sp$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.partialCompare$mcS$sp(x, y);
   }

   default double partialCompare$mcS$sp(final short x, final short y) {
      return (double)this.compare$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static short min$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.min(x, y);
   }

   default short min(final short x, final short y) {
      return this.min$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static short min$mcS$sp$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.min$mcS$sp(x, y);
   }

   default short min$mcS$sp(final short x, final short y) {
      return this.lt$mcS$sp(x, y) ? x : y;
   }

   // $FF: synthetic method
   static short max$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.max(x, y);
   }

   default short max(final short x, final short y) {
      return this.max$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static short max$mcS$sp$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.max$mcS$sp(x, y);
   }

   default short max$mcS$sp(final short x, final short y) {
      return this.gt$mcS$sp(x, y) ? x : y;
   }

   // $FF: synthetic method
   static boolean eqv$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final short x, final short y) {
      return this.eqv$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcS$sp$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.eqv$mcS$sp(x, y);
   }

   default boolean eqv$mcS$sp(final short x, final short y) {
      return this.compare$mcS$sp(x, y) == 0;
   }

   // $FF: synthetic method
   static boolean neqv$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final short x, final short y) {
      return this.neqv$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcS$sp$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.neqv$mcS$sp(x, y);
   }

   default boolean neqv$mcS$sp(final short x, final short y) {
      return !this.eqv$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final short x, final short y) {
      return this.lteqv$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcS$sp$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.lteqv$mcS$sp(x, y);
   }

   default boolean lteqv$mcS$sp(final short x, final short y) {
      return this.compare$mcS$sp(x, y) <= 0;
   }

   // $FF: synthetic method
   static boolean lt$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.lt(x, y);
   }

   default boolean lt(final short x, final short y) {
      return this.lt$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcS$sp$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.lt$mcS$sp(x, y);
   }

   default boolean lt$mcS$sp(final short x, final short y) {
      return this.compare$mcS$sp(x, y) < 0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final short x, final short y) {
      return this.gteqv$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcS$sp$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.gteqv$mcS$sp(x, y);
   }

   default boolean gteqv$mcS$sp(final short x, final short y) {
      return this.compare$mcS$sp(x, y) >= 0;
   }

   // $FF: synthetic method
   static boolean gt$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.gt(x, y);
   }

   default boolean gt(final short x, final short y) {
      return this.gt$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcS$sp$(final Order$mcS$sp $this, final short x, final short y) {
      return $this.gt$mcS$sp(x, y);
   }

   default boolean gt$mcS$sp(final short x, final short y) {
      return this.compare$mcS$sp(x, y) > 0;
   }
}
