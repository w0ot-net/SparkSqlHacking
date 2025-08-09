package cats.kernel;

public interface Order$mcZ$sp extends Order, PartialOrder$mcZ$sp {
   // $FF: synthetic method
   static Comparison comparison$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.comparison(x, y);
   }

   default Comparison comparison(final boolean x, final boolean y) {
      return this.comparison$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static Comparison comparison$mcZ$sp$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.comparison$mcZ$sp(x, y);
   }

   default Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
      return Comparison$.MODULE$.fromInt(this.compare$mcZ$sp(x, y));
   }

   // $FF: synthetic method
   static double partialCompare$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.partialCompare(x, y);
   }

   default double partialCompare(final boolean x, final boolean y) {
      return this.partialCompare$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static double partialCompare$mcZ$sp$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.partialCompare$mcZ$sp(x, y);
   }

   default double partialCompare$mcZ$sp(final boolean x, final boolean y) {
      return (double)this.compare$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean min$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.min(x, y);
   }

   default boolean min(final boolean x, final boolean y) {
      return this.min$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean min$mcZ$sp$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.min$mcZ$sp(x, y);
   }

   default boolean min$mcZ$sp(final boolean x, final boolean y) {
      return this.lt$mcZ$sp(x, y) ? x : y;
   }

   // $FF: synthetic method
   static boolean max$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.max(x, y);
   }

   default boolean max(final boolean x, final boolean y) {
      return this.max$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean max$mcZ$sp$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.max$mcZ$sp(x, y);
   }

   default boolean max$mcZ$sp(final boolean x, final boolean y) {
      return this.gt$mcZ$sp(x, y) ? x : y;
   }

   // $FF: synthetic method
   static boolean eqv$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final boolean x, final boolean y) {
      return this.eqv$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcZ$sp$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.eqv$mcZ$sp(x, y);
   }

   default boolean eqv$mcZ$sp(final boolean x, final boolean y) {
      return this.compare$mcZ$sp(x, y) == 0;
   }

   // $FF: synthetic method
   static boolean neqv$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final boolean x, final boolean y) {
      return this.neqv$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcZ$sp$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.neqv$mcZ$sp(x, y);
   }

   default boolean neqv$mcZ$sp(final boolean x, final boolean y) {
      return !this.eqv$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final boolean x, final boolean y) {
      return this.lteqv$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcZ$sp$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.lteqv$mcZ$sp(x, y);
   }

   default boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
      return this.compare$mcZ$sp(x, y) <= 0;
   }

   // $FF: synthetic method
   static boolean lt$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.lt(x, y);
   }

   default boolean lt(final boolean x, final boolean y) {
      return this.lt$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcZ$sp$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.lt$mcZ$sp(x, y);
   }

   default boolean lt$mcZ$sp(final boolean x, final boolean y) {
      return this.compare$mcZ$sp(x, y) < 0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final boolean x, final boolean y) {
      return this.gteqv$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcZ$sp$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.gteqv$mcZ$sp(x, y);
   }

   default boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
      return this.compare$mcZ$sp(x, y) >= 0;
   }

   // $FF: synthetic method
   static boolean gt$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.gt(x, y);
   }

   default boolean gt(final boolean x, final boolean y) {
      return this.gt$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcZ$sp$(final Order$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.gt$mcZ$sp(x, y);
   }

   default boolean gt$mcZ$sp(final boolean x, final boolean y) {
      return this.compare$mcZ$sp(x, y) > 0;
   }
}
