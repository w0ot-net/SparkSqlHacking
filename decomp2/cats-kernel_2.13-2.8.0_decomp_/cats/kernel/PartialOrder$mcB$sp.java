package cats.kernel;

import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichDouble;

public interface PartialOrder$mcB$sp extends PartialOrder, Eq$mcB$sp {
   // $FF: synthetic method
   static Option partialComparison$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.partialComparison(x, y);
   }

   default Option partialComparison(final byte x, final byte y) {
      return this.partialComparison$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static Option partialComparison$mcB$sp$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.partialComparison$mcB$sp(x, y);
   }

   default Option partialComparison$mcB$sp(final byte x, final byte y) {
      return Comparison$.MODULE$.fromDouble(this.partialCompare$mcB$sp(x, y));
   }

   // $FF: synthetic method
   static Option tryCompare$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.tryCompare(x, y);
   }

   default Option tryCompare(final byte x, final byte y) {
      return this.tryCompare$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static Option tryCompare$mcB$sp$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.tryCompare$mcB$sp(x, y);
   }

   default Option tryCompare$mcB$sp(final byte x, final byte y) {
      double c = BoxesRunTime.unboxToDouble((new RichDouble(.MODULE$.doubleWrapper(this.partialCompare$mcB$sp(x, y)))).sign());
      return (Option)(Double.isNaN(c) ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger((int)c)));
   }

   // $FF: synthetic method
   static Option pmin$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.pmin(x, y);
   }

   default Option pmin(final byte x, final byte y) {
      return this.pmin$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmin$mcB$sp$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.pmin$mcB$sp(x, y);
   }

   default Option pmin$mcB$sp(final byte x, final byte y) {
      double c = this.partialCompare$mcB$sp(x, y);
      return (Option)(c <= (double)0 ? new Some(BoxesRunTime.boxToByte(x)) : (c > (double)0 ? new Some(BoxesRunTime.boxToByte(y)) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static Option pmax$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.pmax(x, y);
   }

   default Option pmax(final byte x, final byte y) {
      return this.pmax$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmax$mcB$sp$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.pmax$mcB$sp(x, y);
   }

   default Option pmax$mcB$sp(final byte x, final byte y) {
      double c = this.partialCompare$mcB$sp(x, y);
      return (Option)(c >= (double)0 ? new Some(BoxesRunTime.boxToByte(x)) : (c < (double)0 ? new Some(BoxesRunTime.boxToByte(y)) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static boolean eqv$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final byte x, final byte y) {
      return this.eqv$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcB$sp$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.eqv$mcB$sp(x, y);
   }

   default boolean eqv$mcB$sp(final byte x, final byte y) {
      return this.partialCompare$mcB$sp(x, y) == (double)0;
   }

   // $FF: synthetic method
   static boolean lteqv$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final byte x, final byte y) {
      return this.lteqv$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcB$sp$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.lteqv$mcB$sp(x, y);
   }

   default boolean lteqv$mcB$sp(final byte x, final byte y) {
      return this.partialCompare$mcB$sp(x, y) <= (double)0;
   }

   // $FF: synthetic method
   static boolean lt$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.lt(x, y);
   }

   default boolean lt(final byte x, final byte y) {
      return this.lt$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcB$sp$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.lt$mcB$sp(x, y);
   }

   default boolean lt$mcB$sp(final byte x, final byte y) {
      return this.partialCompare$mcB$sp(x, y) < (double)0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final byte x, final byte y) {
      return this.gteqv$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcB$sp$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.gteqv$mcB$sp(x, y);
   }

   default boolean gteqv$mcB$sp(final byte x, final byte y) {
      return this.partialCompare$mcB$sp(x, y) >= (double)0;
   }

   // $FF: synthetic method
   static boolean gt$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.gt(x, y);
   }

   default boolean gt(final byte x, final byte y) {
      return this.gt$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcB$sp$(final PartialOrder$mcB$sp $this, final byte x, final byte y) {
      return $this.gt$mcB$sp(x, y);
   }

   default boolean gt$mcB$sp(final byte x, final byte y) {
      return this.partialCompare$mcB$sp(x, y) > (double)0;
   }
}
