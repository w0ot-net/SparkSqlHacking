package cats.kernel;

import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichDouble;

public interface PartialOrder$mcD$sp extends PartialOrder, Eq$mcD$sp {
   // $FF: synthetic method
   static Option partialComparison$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.partialComparison(x, y);
   }

   default Option partialComparison(final double x, final double y) {
      return this.partialComparison$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static Option partialComparison$mcD$sp$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.partialComparison$mcD$sp(x, y);
   }

   default Option partialComparison$mcD$sp(final double x, final double y) {
      return Comparison$.MODULE$.fromDouble(this.partialCompare$mcD$sp(x, y));
   }

   // $FF: synthetic method
   static Option tryCompare$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.tryCompare(x, y);
   }

   default Option tryCompare(final double x, final double y) {
      return this.tryCompare$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static Option tryCompare$mcD$sp$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.tryCompare$mcD$sp(x, y);
   }

   default Option tryCompare$mcD$sp(final double x, final double y) {
      double c = BoxesRunTime.unboxToDouble((new RichDouble(.MODULE$.doubleWrapper(this.partialCompare$mcD$sp(x, y)))).sign());
      return (Option)(Double.isNaN(c) ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger((int)c)));
   }

   // $FF: synthetic method
   static Option pmin$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.pmin(x, y);
   }

   default Option pmin(final double x, final double y) {
      return this.pmin$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmin$mcD$sp$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.pmin$mcD$sp(x, y);
   }

   default Option pmin$mcD$sp(final double x, final double y) {
      double c = this.partialCompare$mcD$sp(x, y);
      return (Option)(c <= (double)0 ? new Some(BoxesRunTime.boxToDouble(x)) : (c > (double)0 ? new Some(BoxesRunTime.boxToDouble(y)) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static Option pmax$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.pmax(x, y);
   }

   default Option pmax(final double x, final double y) {
      return this.pmax$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmax$mcD$sp$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.pmax$mcD$sp(x, y);
   }

   default Option pmax$mcD$sp(final double x, final double y) {
      double c = this.partialCompare$mcD$sp(x, y);
      return (Option)(c >= (double)0 ? new Some(BoxesRunTime.boxToDouble(x)) : (c < (double)0 ? new Some(BoxesRunTime.boxToDouble(y)) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static boolean eqv$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final double x, final double y) {
      return this.eqv$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcD$sp$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.eqv$mcD$sp(x, y);
   }

   default boolean eqv$mcD$sp(final double x, final double y) {
      return this.partialCompare$mcD$sp(x, y) == (double)0;
   }

   // $FF: synthetic method
   static boolean lteqv$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final double x, final double y) {
      return this.lteqv$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcD$sp$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.lteqv$mcD$sp(x, y);
   }

   default boolean lteqv$mcD$sp(final double x, final double y) {
      return this.partialCompare$mcD$sp(x, y) <= (double)0;
   }

   // $FF: synthetic method
   static boolean lt$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.lt(x, y);
   }

   default boolean lt(final double x, final double y) {
      return this.lt$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcD$sp$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.lt$mcD$sp(x, y);
   }

   default boolean lt$mcD$sp(final double x, final double y) {
      return this.partialCompare$mcD$sp(x, y) < (double)0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final double x, final double y) {
      return this.gteqv$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcD$sp$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.gteqv$mcD$sp(x, y);
   }

   default boolean gteqv$mcD$sp(final double x, final double y) {
      return this.partialCompare$mcD$sp(x, y) >= (double)0;
   }

   // $FF: synthetic method
   static boolean gt$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.gt(x, y);
   }

   default boolean gt(final double x, final double y) {
      return this.gt$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcD$sp$(final PartialOrder$mcD$sp $this, final double x, final double y) {
      return $this.gt$mcD$sp(x, y);
   }

   default boolean gt$mcD$sp(final double x, final double y) {
      return this.partialCompare$mcD$sp(x, y) > (double)0;
   }
}
