package cats.kernel;

import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichDouble;

public interface PartialOrder$mcZ$sp extends PartialOrder, Eq$mcZ$sp {
   // $FF: synthetic method
   static Option partialComparison$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.partialComparison(x, y);
   }

   default Option partialComparison(final boolean x, final boolean y) {
      return this.partialComparison$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static Option partialComparison$mcZ$sp$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.partialComparison$mcZ$sp(x, y);
   }

   default Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
      return Comparison$.MODULE$.fromDouble(this.partialCompare$mcZ$sp(x, y));
   }

   // $FF: synthetic method
   static Option tryCompare$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.tryCompare(x, y);
   }

   default Option tryCompare(final boolean x, final boolean y) {
      return this.tryCompare$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static Option tryCompare$mcZ$sp$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.tryCompare$mcZ$sp(x, y);
   }

   default Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
      double c = BoxesRunTime.unboxToDouble((new RichDouble(.MODULE$.doubleWrapper(this.partialCompare$mcZ$sp(x, y)))).sign());
      return (Option)(Double.isNaN(c) ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger((int)c)));
   }

   // $FF: synthetic method
   static Option pmin$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.pmin(x, y);
   }

   default Option pmin(final boolean x, final boolean y) {
      return this.pmin$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmin$mcZ$sp$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.pmin$mcZ$sp(x, y);
   }

   default Option pmin$mcZ$sp(final boolean x, final boolean y) {
      double c = this.partialCompare$mcZ$sp(x, y);
      return (Option)(c <= (double)0 ? new Some(BoxesRunTime.boxToBoolean(x)) : (c > (double)0 ? new Some(BoxesRunTime.boxToBoolean(y)) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static Option pmax$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.pmax(x, y);
   }

   default Option pmax(final boolean x, final boolean y) {
      return this.pmax$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmax$mcZ$sp$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.pmax$mcZ$sp(x, y);
   }

   default Option pmax$mcZ$sp(final boolean x, final boolean y) {
      double c = this.partialCompare$mcZ$sp(x, y);
      return (Option)(c >= (double)0 ? new Some(BoxesRunTime.boxToBoolean(x)) : (c < (double)0 ? new Some(BoxesRunTime.boxToBoolean(y)) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static boolean eqv$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final boolean x, final boolean y) {
      return this.eqv$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcZ$sp$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.eqv$mcZ$sp(x, y);
   }

   default boolean eqv$mcZ$sp(final boolean x, final boolean y) {
      return this.partialCompare$mcZ$sp(x, y) == (double)0;
   }

   // $FF: synthetic method
   static boolean lteqv$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final boolean x, final boolean y) {
      return this.lteqv$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcZ$sp$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.lteqv$mcZ$sp(x, y);
   }

   default boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
      return this.partialCompare$mcZ$sp(x, y) <= (double)0;
   }

   // $FF: synthetic method
   static boolean lt$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.lt(x, y);
   }

   default boolean lt(final boolean x, final boolean y) {
      return this.lt$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcZ$sp$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.lt$mcZ$sp(x, y);
   }

   default boolean lt$mcZ$sp(final boolean x, final boolean y) {
      return this.partialCompare$mcZ$sp(x, y) < (double)0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final boolean x, final boolean y) {
      return this.gteqv$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcZ$sp$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.gteqv$mcZ$sp(x, y);
   }

   default boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
      return this.partialCompare$mcZ$sp(x, y) >= (double)0;
   }

   // $FF: synthetic method
   static boolean gt$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.gt(x, y);
   }

   default boolean gt(final boolean x, final boolean y) {
      return this.gt$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcZ$sp$(final PartialOrder$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.gt$mcZ$sp(x, y);
   }

   default boolean gt$mcZ$sp(final boolean x, final boolean y) {
      return this.partialCompare$mcZ$sp(x, y) > (double)0;
   }
}
