package cats.kernel;

import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichDouble;

public interface PartialOrder$mcI$sp extends PartialOrder, Eq$mcI$sp {
   // $FF: synthetic method
   static Option partialComparison$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.partialComparison(x, y);
   }

   default Option partialComparison(final int x, final int y) {
      return this.partialComparison$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static Option partialComparison$mcI$sp$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.partialComparison$mcI$sp(x, y);
   }

   default Option partialComparison$mcI$sp(final int x, final int y) {
      return Comparison$.MODULE$.fromDouble(this.partialCompare$mcI$sp(x, y));
   }

   // $FF: synthetic method
   static Option tryCompare$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.tryCompare(x, y);
   }

   default Option tryCompare(final int x, final int y) {
      return this.tryCompare$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static Option tryCompare$mcI$sp$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.tryCompare$mcI$sp(x, y);
   }

   default Option tryCompare$mcI$sp(final int x, final int y) {
      double c = BoxesRunTime.unboxToDouble((new RichDouble(.MODULE$.doubleWrapper(this.partialCompare$mcI$sp(x, y)))).sign());
      return (Option)(Double.isNaN(c) ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger((int)c)));
   }

   // $FF: synthetic method
   static Option pmin$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.pmin(x, y);
   }

   default Option pmin(final int x, final int y) {
      return this.pmin$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmin$mcI$sp$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.pmin$mcI$sp(x, y);
   }

   default Option pmin$mcI$sp(final int x, final int y) {
      double c = this.partialCompare$mcI$sp(x, y);
      return (Option)(c <= (double)0 ? new Some(BoxesRunTime.boxToInteger(x)) : (c > (double)0 ? new Some(BoxesRunTime.boxToInteger(y)) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static Option pmax$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.pmax(x, y);
   }

   default Option pmax(final int x, final int y) {
      return this.pmax$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmax$mcI$sp$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.pmax$mcI$sp(x, y);
   }

   default Option pmax$mcI$sp(final int x, final int y) {
      double c = this.partialCompare$mcI$sp(x, y);
      return (Option)(c >= (double)0 ? new Some(BoxesRunTime.boxToInteger(x)) : (c < (double)0 ? new Some(BoxesRunTime.boxToInteger(y)) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static boolean eqv$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final int x, final int y) {
      return this.eqv$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcI$sp$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.eqv$mcI$sp(x, y);
   }

   default boolean eqv$mcI$sp(final int x, final int y) {
      return this.partialCompare$mcI$sp(x, y) == (double)0;
   }

   // $FF: synthetic method
   static boolean lteqv$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final int x, final int y) {
      return this.lteqv$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcI$sp$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.lteqv$mcI$sp(x, y);
   }

   default boolean lteqv$mcI$sp(final int x, final int y) {
      return this.partialCompare$mcI$sp(x, y) <= (double)0;
   }

   // $FF: synthetic method
   static boolean lt$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.lt(x, y);
   }

   default boolean lt(final int x, final int y) {
      return this.lt$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcI$sp$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.lt$mcI$sp(x, y);
   }

   default boolean lt$mcI$sp(final int x, final int y) {
      return this.partialCompare$mcI$sp(x, y) < (double)0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final int x, final int y) {
      return this.gteqv$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcI$sp$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.gteqv$mcI$sp(x, y);
   }

   default boolean gteqv$mcI$sp(final int x, final int y) {
      return this.partialCompare$mcI$sp(x, y) >= (double)0;
   }

   // $FF: synthetic method
   static boolean gt$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.gt(x, y);
   }

   default boolean gt(final int x, final int y) {
      return this.gt$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcI$sp$(final PartialOrder$mcI$sp $this, final int x, final int y) {
      return $this.gt$mcI$sp(x, y);
   }

   default boolean gt$mcI$sp(final int x, final int y) {
      return this.partialCompare$mcI$sp(x, y) > (double)0;
   }
}
