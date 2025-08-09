package cats.kernel;

import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichDouble;

public interface PartialOrder$mcJ$sp extends PartialOrder, Eq$mcJ$sp {
   // $FF: synthetic method
   static Option partialComparison$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.partialComparison(x, y);
   }

   default Option partialComparison(final long x, final long y) {
      return this.partialComparison$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static Option partialComparison$mcJ$sp$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.partialComparison$mcJ$sp(x, y);
   }

   default Option partialComparison$mcJ$sp(final long x, final long y) {
      return Comparison$.MODULE$.fromDouble(this.partialCompare$mcJ$sp(x, y));
   }

   // $FF: synthetic method
   static Option tryCompare$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.tryCompare(x, y);
   }

   default Option tryCompare(final long x, final long y) {
      return this.tryCompare$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static Option tryCompare$mcJ$sp$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.tryCompare$mcJ$sp(x, y);
   }

   default Option tryCompare$mcJ$sp(final long x, final long y) {
      double c = BoxesRunTime.unboxToDouble((new RichDouble(.MODULE$.doubleWrapper(this.partialCompare$mcJ$sp(x, y)))).sign());
      return (Option)(Double.isNaN(c) ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger((int)c)));
   }

   // $FF: synthetic method
   static Option pmin$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.pmin(x, y);
   }

   default Option pmin(final long x, final long y) {
      return this.pmin$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmin$mcJ$sp$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.pmin$mcJ$sp(x, y);
   }

   default Option pmin$mcJ$sp(final long x, final long y) {
      double c = this.partialCompare$mcJ$sp(x, y);
      return (Option)(c <= (double)0 ? new Some(BoxesRunTime.boxToLong(x)) : (c > (double)0 ? new Some(BoxesRunTime.boxToLong(y)) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static Option pmax$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.pmax(x, y);
   }

   default Option pmax(final long x, final long y) {
      return this.pmax$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmax$mcJ$sp$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.pmax$mcJ$sp(x, y);
   }

   default Option pmax$mcJ$sp(final long x, final long y) {
      double c = this.partialCompare$mcJ$sp(x, y);
      return (Option)(c >= (double)0 ? new Some(BoxesRunTime.boxToLong(x)) : (c < (double)0 ? new Some(BoxesRunTime.boxToLong(y)) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static boolean eqv$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final long x, final long y) {
      return this.eqv$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcJ$sp$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.eqv$mcJ$sp(x, y);
   }

   default boolean eqv$mcJ$sp(final long x, final long y) {
      return this.partialCompare$mcJ$sp(x, y) == (double)0;
   }

   // $FF: synthetic method
   static boolean lteqv$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final long x, final long y) {
      return this.lteqv$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcJ$sp$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.lteqv$mcJ$sp(x, y);
   }

   default boolean lteqv$mcJ$sp(final long x, final long y) {
      return this.partialCompare$mcJ$sp(x, y) <= (double)0;
   }

   // $FF: synthetic method
   static boolean lt$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.lt(x, y);
   }

   default boolean lt(final long x, final long y) {
      return this.lt$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcJ$sp$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.lt$mcJ$sp(x, y);
   }

   default boolean lt$mcJ$sp(final long x, final long y) {
      return this.partialCompare$mcJ$sp(x, y) < (double)0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final long x, final long y) {
      return this.gteqv$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcJ$sp$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.gteqv$mcJ$sp(x, y);
   }

   default boolean gteqv$mcJ$sp(final long x, final long y) {
      return this.partialCompare$mcJ$sp(x, y) >= (double)0;
   }

   // $FF: synthetic method
   static boolean gt$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.gt(x, y);
   }

   default boolean gt(final long x, final long y) {
      return this.gt$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcJ$sp$(final PartialOrder$mcJ$sp $this, final long x, final long y) {
      return $this.gt$mcJ$sp(x, y);
   }

   default boolean gt$mcJ$sp(final long x, final long y) {
      return this.partialCompare$mcJ$sp(x, y) > (double)0;
   }
}
