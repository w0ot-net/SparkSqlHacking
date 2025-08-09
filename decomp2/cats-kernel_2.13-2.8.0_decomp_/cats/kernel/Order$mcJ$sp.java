package cats.kernel;

public interface Order$mcJ$sp extends Order, PartialOrder$mcJ$sp {
   // $FF: synthetic method
   static Comparison comparison$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.comparison(x, y);
   }

   default Comparison comparison(final long x, final long y) {
      return this.comparison$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static Comparison comparison$mcJ$sp$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.comparison$mcJ$sp(x, y);
   }

   default Comparison comparison$mcJ$sp(final long x, final long y) {
      return Comparison$.MODULE$.fromInt(this.compare$mcJ$sp(x, y));
   }

   // $FF: synthetic method
   static double partialCompare$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.partialCompare(x, y);
   }

   default double partialCompare(final long x, final long y) {
      return this.partialCompare$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static double partialCompare$mcJ$sp$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.partialCompare$mcJ$sp(x, y);
   }

   default double partialCompare$mcJ$sp(final long x, final long y) {
      return (double)this.compare$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static long min$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.min(x, y);
   }

   default long min(final long x, final long y) {
      return this.min$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static long min$mcJ$sp$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.min$mcJ$sp(x, y);
   }

   default long min$mcJ$sp(final long x, final long y) {
      return this.lt$mcJ$sp(x, y) ? x : y;
   }

   // $FF: synthetic method
   static long max$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.max(x, y);
   }

   default long max(final long x, final long y) {
      return this.max$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static long max$mcJ$sp$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.max$mcJ$sp(x, y);
   }

   default long max$mcJ$sp(final long x, final long y) {
      return this.gt$mcJ$sp(x, y) ? x : y;
   }

   // $FF: synthetic method
   static boolean eqv$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final long x, final long y) {
      return this.eqv$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcJ$sp$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.eqv$mcJ$sp(x, y);
   }

   default boolean eqv$mcJ$sp(final long x, final long y) {
      return this.compare$mcJ$sp(x, y) == 0;
   }

   // $FF: synthetic method
   static boolean neqv$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final long x, final long y) {
      return this.neqv$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcJ$sp$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.neqv$mcJ$sp(x, y);
   }

   default boolean neqv$mcJ$sp(final long x, final long y) {
      return !this.eqv$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final long x, final long y) {
      return this.lteqv$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcJ$sp$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.lteqv$mcJ$sp(x, y);
   }

   default boolean lteqv$mcJ$sp(final long x, final long y) {
      return this.compare$mcJ$sp(x, y) <= 0;
   }

   // $FF: synthetic method
   static boolean lt$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.lt(x, y);
   }

   default boolean lt(final long x, final long y) {
      return this.lt$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcJ$sp$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.lt$mcJ$sp(x, y);
   }

   default boolean lt$mcJ$sp(final long x, final long y) {
      return this.compare$mcJ$sp(x, y) < 0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final long x, final long y) {
      return this.gteqv$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcJ$sp$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.gteqv$mcJ$sp(x, y);
   }

   default boolean gteqv$mcJ$sp(final long x, final long y) {
      return this.compare$mcJ$sp(x, y) >= 0;
   }

   // $FF: synthetic method
   static boolean gt$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.gt(x, y);
   }

   default boolean gt(final long x, final long y) {
      return this.gt$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcJ$sp$(final Order$mcJ$sp $this, final long x, final long y) {
      return $this.gt$mcJ$sp(x, y);
   }

   default boolean gt$mcJ$sp(final long x, final long y) {
      return this.compare$mcJ$sp(x, y) > 0;
   }
}
