package cats.kernel;

public interface Order$mcC$sp extends Order, PartialOrder$mcC$sp {
   // $FF: synthetic method
   static Comparison comparison$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.comparison(x, y);
   }

   default Comparison comparison(final char x, final char y) {
      return this.comparison$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static Comparison comparison$mcC$sp$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.comparison$mcC$sp(x, y);
   }

   default Comparison comparison$mcC$sp(final char x, final char y) {
      return Comparison$.MODULE$.fromInt(this.compare$mcC$sp(x, y));
   }

   // $FF: synthetic method
   static double partialCompare$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.partialCompare(x, y);
   }

   default double partialCompare(final char x, final char y) {
      return this.partialCompare$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static double partialCompare$mcC$sp$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.partialCompare$mcC$sp(x, y);
   }

   default double partialCompare$mcC$sp(final char x, final char y) {
      return (double)this.compare$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static char min$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.min(x, y);
   }

   default char min(final char x, final char y) {
      return this.min$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static char min$mcC$sp$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.min$mcC$sp(x, y);
   }

   default char min$mcC$sp(final char x, final char y) {
      return this.lt$mcC$sp(x, y) ? x : y;
   }

   // $FF: synthetic method
   static char max$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.max(x, y);
   }

   default char max(final char x, final char y) {
      return this.max$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static char max$mcC$sp$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.max$mcC$sp(x, y);
   }

   default char max$mcC$sp(final char x, final char y) {
      return this.gt$mcC$sp(x, y) ? x : y;
   }

   // $FF: synthetic method
   static boolean eqv$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final char x, final char y) {
      return this.eqv$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcC$sp$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.eqv$mcC$sp(x, y);
   }

   default boolean eqv$mcC$sp(final char x, final char y) {
      return this.compare$mcC$sp(x, y) == 0;
   }

   // $FF: synthetic method
   static boolean neqv$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final char x, final char y) {
      return this.neqv$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcC$sp$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.neqv$mcC$sp(x, y);
   }

   default boolean neqv$mcC$sp(final char x, final char y) {
      return !this.eqv$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final char x, final char y) {
      return this.lteqv$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcC$sp$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.lteqv$mcC$sp(x, y);
   }

   default boolean lteqv$mcC$sp(final char x, final char y) {
      return this.compare$mcC$sp(x, y) <= 0;
   }

   // $FF: synthetic method
   static boolean lt$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.lt(x, y);
   }

   default boolean lt(final char x, final char y) {
      return this.lt$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcC$sp$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.lt$mcC$sp(x, y);
   }

   default boolean lt$mcC$sp(final char x, final char y) {
      return this.compare$mcC$sp(x, y) < 0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final char x, final char y) {
      return this.gteqv$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcC$sp$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.gteqv$mcC$sp(x, y);
   }

   default boolean gteqv$mcC$sp(final char x, final char y) {
      return this.compare$mcC$sp(x, y) >= 0;
   }

   // $FF: synthetic method
   static boolean gt$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.gt(x, y);
   }

   default boolean gt(final char x, final char y) {
      return this.gt$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcC$sp$(final Order$mcC$sp $this, final char x, final char y) {
      return $this.gt$mcC$sp(x, y);
   }

   default boolean gt$mcC$sp(final char x, final char y) {
      return this.compare$mcC$sp(x, y) > 0;
   }
}
