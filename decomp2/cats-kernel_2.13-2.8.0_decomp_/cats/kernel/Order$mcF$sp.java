package cats.kernel;

public interface Order$mcF$sp extends Order, PartialOrder$mcF$sp {
   // $FF: synthetic method
   static Comparison comparison$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.comparison(x, y);
   }

   default Comparison comparison(final float x, final float y) {
      return this.comparison$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static Comparison comparison$mcF$sp$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.comparison$mcF$sp(x, y);
   }

   default Comparison comparison$mcF$sp(final float x, final float y) {
      return Comparison$.MODULE$.fromInt(this.compare$mcF$sp(x, y));
   }

   // $FF: synthetic method
   static double partialCompare$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.partialCompare(x, y);
   }

   default double partialCompare(final float x, final float y) {
      return this.partialCompare$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static double partialCompare$mcF$sp$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.partialCompare$mcF$sp(x, y);
   }

   default double partialCompare$mcF$sp(final float x, final float y) {
      return (double)this.compare$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static float min$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.min(x, y);
   }

   default float min(final float x, final float y) {
      return this.min$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static float min$mcF$sp$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.min$mcF$sp(x, y);
   }

   default float min$mcF$sp(final float x, final float y) {
      return this.lt$mcF$sp(x, y) ? x : y;
   }

   // $FF: synthetic method
   static float max$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.max(x, y);
   }

   default float max(final float x, final float y) {
      return this.max$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static float max$mcF$sp$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.max$mcF$sp(x, y);
   }

   default float max$mcF$sp(final float x, final float y) {
      return this.gt$mcF$sp(x, y) ? x : y;
   }

   // $FF: synthetic method
   static boolean eqv$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final float x, final float y) {
      return this.eqv$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcF$sp$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.eqv$mcF$sp(x, y);
   }

   default boolean eqv$mcF$sp(final float x, final float y) {
      return this.compare$mcF$sp(x, y) == 0;
   }

   // $FF: synthetic method
   static boolean neqv$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final float x, final float y) {
      return this.neqv$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcF$sp$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.neqv$mcF$sp(x, y);
   }

   default boolean neqv$mcF$sp(final float x, final float y) {
      return !this.eqv$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final float x, final float y) {
      return this.lteqv$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcF$sp$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.lteqv$mcF$sp(x, y);
   }

   default boolean lteqv$mcF$sp(final float x, final float y) {
      return this.compare$mcF$sp(x, y) <= 0;
   }

   // $FF: synthetic method
   static boolean lt$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.lt(x, y);
   }

   default boolean lt(final float x, final float y) {
      return this.lt$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcF$sp$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.lt$mcF$sp(x, y);
   }

   default boolean lt$mcF$sp(final float x, final float y) {
      return this.compare$mcF$sp(x, y) < 0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final float x, final float y) {
      return this.gteqv$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcF$sp$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.gteqv$mcF$sp(x, y);
   }

   default boolean gteqv$mcF$sp(final float x, final float y) {
      return this.compare$mcF$sp(x, y) >= 0;
   }

   // $FF: synthetic method
   static boolean gt$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.gt(x, y);
   }

   default boolean gt(final float x, final float y) {
      return this.gt$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcF$sp$(final Order$mcF$sp $this, final float x, final float y) {
      return $this.gt$mcF$sp(x, y);
   }

   default boolean gt$mcF$sp(final float x, final float y) {
      return this.compare$mcF$sp(x, y) > 0;
   }
}
