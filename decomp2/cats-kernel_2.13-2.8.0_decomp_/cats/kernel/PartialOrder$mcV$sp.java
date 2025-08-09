package cats.kernel;

import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichDouble;

public interface PartialOrder$mcV$sp extends PartialOrder, Eq$mcV$sp {
   // $FF: synthetic method
   static Option partialComparison$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.partialComparison(x, y);
   }

   default Option partialComparison(final BoxedUnit x, final BoxedUnit y) {
      return this.partialComparison$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static Option partialComparison$mcV$sp$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.partialComparison$mcV$sp(x, y);
   }

   default Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Comparison$.MODULE$.fromDouble(this.partialCompare$mcV$sp(x, y));
   }

   // $FF: synthetic method
   static Option tryCompare$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.tryCompare(x, y);
   }

   default Option tryCompare(final BoxedUnit x, final BoxedUnit y) {
      return this.tryCompare$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static Option tryCompare$mcV$sp$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.tryCompare$mcV$sp(x, y);
   }

   default Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      double c = BoxesRunTime.unboxToDouble((new RichDouble(.MODULE$.doubleWrapper(this.partialCompare$mcV$sp(x, y)))).sign());
      return (Option)(Double.isNaN(c) ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger((int)c)));
   }

   // $FF: synthetic method
   static Option pmin$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.pmin(x, y);
   }

   default Option pmin(final BoxedUnit x, final BoxedUnit y) {
      return this.pmin$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmin$mcV$sp$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.pmin$mcV$sp(x, y);
   }

   default Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      double c = this.partialCompare$mcV$sp(x, y);
      return (Option)(c <= (double)0 ? new Some(x) : (c > (double)0 ? new Some(y) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static Option pmax$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.pmax(x, y);
   }

   default Option pmax(final BoxedUnit x, final BoxedUnit y) {
      return this.pmax$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmax$mcV$sp$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.pmax$mcV$sp(x, y);
   }

   default Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      double c = this.partialCompare$mcV$sp(x, y);
      return (Option)(c >= (double)0 ? new Some(x) : (c < (double)0 ? new Some(y) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static boolean eqv$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final BoxedUnit x, final BoxedUnit y) {
      return this.eqv$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcV$sp$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.eqv$mcV$sp(x, y);
   }

   default boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.partialCompare$mcV$sp(x, y) == (double)0;
   }

   // $FF: synthetic method
   static boolean lteqv$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final BoxedUnit x, final BoxedUnit y) {
      return this.lteqv$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcV$sp$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.lteqv$mcV$sp(x, y);
   }

   default boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.partialCompare$mcV$sp(x, y) <= (double)0;
   }

   // $FF: synthetic method
   static boolean lt$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.lt(x, y);
   }

   default boolean lt(final BoxedUnit x, final BoxedUnit y) {
      return this.lt$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcV$sp$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.lt$mcV$sp(x, y);
   }

   default boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.partialCompare$mcV$sp(x, y) < (double)0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final BoxedUnit x, final BoxedUnit y) {
      return this.gteqv$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcV$sp$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.gteqv$mcV$sp(x, y);
   }

   default boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.partialCompare$mcV$sp(x, y) >= (double)0;
   }

   // $FF: synthetic method
   static boolean gt$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.gt(x, y);
   }

   default boolean gt(final BoxedUnit x, final BoxedUnit y) {
      return this.gt$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcV$sp$(final PartialOrder$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.gt$mcV$sp(x, y);
   }

   default boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.partialCompare$mcV$sp(x, y) > (double)0;
   }
}
