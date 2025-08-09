package cats.kernel;

public interface Order$mcB$sp extends Order, PartialOrder$mcB$sp {
   // $FF: synthetic method
   static Comparison comparison$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.comparison(x, y);
   }

   default Comparison comparison(final byte x, final byte y) {
      return this.comparison$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static Comparison comparison$mcB$sp$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.comparison$mcB$sp(x, y);
   }

   default Comparison comparison$mcB$sp(final byte x, final byte y) {
      return Comparison$.MODULE$.fromInt(this.compare$mcB$sp(x, y));
   }

   // $FF: synthetic method
   static double partialCompare$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.partialCompare(x, y);
   }

   default double partialCompare(final byte x, final byte y) {
      return this.partialCompare$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static double partialCompare$mcB$sp$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.partialCompare$mcB$sp(x, y);
   }

   default double partialCompare$mcB$sp(final byte x, final byte y) {
      return (double)this.compare$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static byte min$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.min(x, y);
   }

   default byte min(final byte x, final byte y) {
      return this.min$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static byte min$mcB$sp$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.min$mcB$sp(x, y);
   }

   default byte min$mcB$sp(final byte x, final byte y) {
      return this.lt$mcB$sp(x, y) ? x : y;
   }

   // $FF: synthetic method
   static byte max$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.max(x, y);
   }

   default byte max(final byte x, final byte y) {
      return this.max$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static byte max$mcB$sp$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.max$mcB$sp(x, y);
   }

   default byte max$mcB$sp(final byte x, final byte y) {
      return this.gt$mcB$sp(x, y) ? x : y;
   }

   // $FF: synthetic method
   static boolean eqv$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final byte x, final byte y) {
      return this.eqv$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcB$sp$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.eqv$mcB$sp(x, y);
   }

   default boolean eqv$mcB$sp(final byte x, final byte y) {
      return this.compare$mcB$sp(x, y) == 0;
   }

   // $FF: synthetic method
   static boolean neqv$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final byte x, final byte y) {
      return this.neqv$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcB$sp$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.neqv$mcB$sp(x, y);
   }

   default boolean neqv$mcB$sp(final byte x, final byte y) {
      return !this.eqv$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final byte x, final byte y) {
      return this.lteqv$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcB$sp$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.lteqv$mcB$sp(x, y);
   }

   default boolean lteqv$mcB$sp(final byte x, final byte y) {
      return this.compare$mcB$sp(x, y) <= 0;
   }

   // $FF: synthetic method
   static boolean lt$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.lt(x, y);
   }

   default boolean lt(final byte x, final byte y) {
      return this.lt$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcB$sp$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.lt$mcB$sp(x, y);
   }

   default boolean lt$mcB$sp(final byte x, final byte y) {
      return this.compare$mcB$sp(x, y) < 0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final byte x, final byte y) {
      return this.gteqv$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcB$sp$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.gteqv$mcB$sp(x, y);
   }

   default boolean gteqv$mcB$sp(final byte x, final byte y) {
      return this.compare$mcB$sp(x, y) >= 0;
   }

   // $FF: synthetic method
   static boolean gt$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.gt(x, y);
   }

   default boolean gt(final byte x, final byte y) {
      return this.gt$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcB$sp$(final Order$mcB$sp $this, final byte x, final byte y) {
      return $this.gt$mcB$sp(x, y);
   }

   default boolean gt$mcB$sp(final byte x, final byte y) {
      return this.compare$mcB$sp(x, y) > 0;
   }
}
