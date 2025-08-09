package cats.kernel;

import scala.runtime.BoxedUnit;

public interface Order$mcV$sp extends Order, PartialOrder$mcV$sp {
   // $FF: synthetic method
   static Comparison comparison$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.comparison(x, y);
   }

   default Comparison comparison(final BoxedUnit x, final BoxedUnit y) {
      return this.comparison$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static Comparison comparison$mcV$sp$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.comparison$mcV$sp(x, y);
   }

   default Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Comparison$.MODULE$.fromInt(this.compare$mcV$sp(x, y));
   }

   // $FF: synthetic method
   static double partialCompare$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.partialCompare(x, y);
   }

   default double partialCompare(final BoxedUnit x, final BoxedUnit y) {
      return this.partialCompare$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static double partialCompare$mcV$sp$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.partialCompare$mcV$sp(x, y);
   }

   default double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return (double)this.compare$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static void min$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      $this.min(x, y);
   }

   default void min(final BoxedUnit x, final BoxedUnit y) {
      this.min$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static void min$mcV$sp$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      $this.min$mcV$sp(x, y);
   }

   default void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      if (this.lt$mcV$sp(x, y)) {
      }

   }

   // $FF: synthetic method
   static void max$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      $this.max(x, y);
   }

   default void max(final BoxedUnit x, final BoxedUnit y) {
      this.max$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static void max$mcV$sp$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      $this.max$mcV$sp(x, y);
   }

   default void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      if (this.gt$mcV$sp(x, y)) {
      }

   }

   // $FF: synthetic method
   static boolean eqv$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final BoxedUnit x, final BoxedUnit y) {
      return this.eqv$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcV$sp$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.eqv$mcV$sp(x, y);
   }

   default boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.compare$mcV$sp(x, y) == 0;
   }

   // $FF: synthetic method
   static boolean neqv$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final BoxedUnit x, final BoxedUnit y) {
      return this.neqv$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcV$sp$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.neqv$mcV$sp(x, y);
   }

   default boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return !this.eqv$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final BoxedUnit x, final BoxedUnit y) {
      return this.lteqv$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcV$sp$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.lteqv$mcV$sp(x, y);
   }

   default boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.compare$mcV$sp(x, y) <= 0;
   }

   // $FF: synthetic method
   static boolean lt$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.lt(x, y);
   }

   default boolean lt(final BoxedUnit x, final BoxedUnit y) {
      return this.lt$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcV$sp$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.lt$mcV$sp(x, y);
   }

   default boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.compare$mcV$sp(x, y) < 0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final BoxedUnit x, final BoxedUnit y) {
      return this.gteqv$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcV$sp$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.gteqv$mcV$sp(x, y);
   }

   default boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.compare$mcV$sp(x, y) >= 0;
   }

   // $FF: synthetic method
   static boolean gt$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.gt(x, y);
   }

   default boolean gt(final BoxedUnit x, final BoxedUnit y) {
      return this.gt$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcV$sp$(final Order$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.gt$mcV$sp(x, y);
   }

   default boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.compare$mcV$sp(x, y) > 0;
   }
}
