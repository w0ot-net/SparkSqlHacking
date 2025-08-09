package cats.kernel;

import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichDouble;

public interface PartialOrder$mcS$sp extends PartialOrder, Eq$mcS$sp {
   // $FF: synthetic method
   static Option partialComparison$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.partialComparison(x, y);
   }

   default Option partialComparison(final short x, final short y) {
      return this.partialComparison$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static Option partialComparison$mcS$sp$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.partialComparison$mcS$sp(x, y);
   }

   default Option partialComparison$mcS$sp(final short x, final short y) {
      return Comparison$.MODULE$.fromDouble(this.partialCompare$mcS$sp(x, y));
   }

   // $FF: synthetic method
   static Option tryCompare$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.tryCompare(x, y);
   }

   default Option tryCompare(final short x, final short y) {
      return this.tryCompare$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static Option tryCompare$mcS$sp$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.tryCompare$mcS$sp(x, y);
   }

   default Option tryCompare$mcS$sp(final short x, final short y) {
      double c = BoxesRunTime.unboxToDouble((new RichDouble(.MODULE$.doubleWrapper(this.partialCompare$mcS$sp(x, y)))).sign());
      return (Option)(Double.isNaN(c) ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger((int)c)));
   }

   // $FF: synthetic method
   static Option pmin$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.pmin(x, y);
   }

   default Option pmin(final short x, final short y) {
      return this.pmin$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmin$mcS$sp$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.pmin$mcS$sp(x, y);
   }

   default Option pmin$mcS$sp(final short x, final short y) {
      double c = this.partialCompare$mcS$sp(x, y);
      return (Option)(c <= (double)0 ? new Some(BoxesRunTime.boxToShort(x)) : (c > (double)0 ? new Some(BoxesRunTime.boxToShort(y)) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static Option pmax$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.pmax(x, y);
   }

   default Option pmax(final short x, final short y) {
      return this.pmax$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmax$mcS$sp$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.pmax$mcS$sp(x, y);
   }

   default Option pmax$mcS$sp(final short x, final short y) {
      double c = this.partialCompare$mcS$sp(x, y);
      return (Option)(c >= (double)0 ? new Some(BoxesRunTime.boxToShort(x)) : (c < (double)0 ? new Some(BoxesRunTime.boxToShort(y)) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static boolean eqv$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final short x, final short y) {
      return this.eqv$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcS$sp$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.eqv$mcS$sp(x, y);
   }

   default boolean eqv$mcS$sp(final short x, final short y) {
      return this.partialCompare$mcS$sp(x, y) == (double)0;
   }

   // $FF: synthetic method
   static boolean lteqv$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final short x, final short y) {
      return this.lteqv$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcS$sp$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.lteqv$mcS$sp(x, y);
   }

   default boolean lteqv$mcS$sp(final short x, final short y) {
      return this.partialCompare$mcS$sp(x, y) <= (double)0;
   }

   // $FF: synthetic method
   static boolean lt$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.lt(x, y);
   }

   default boolean lt(final short x, final short y) {
      return this.lt$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcS$sp$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.lt$mcS$sp(x, y);
   }

   default boolean lt$mcS$sp(final short x, final short y) {
      return this.partialCompare$mcS$sp(x, y) < (double)0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final short x, final short y) {
      return this.gteqv$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcS$sp$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.gteqv$mcS$sp(x, y);
   }

   default boolean gteqv$mcS$sp(final short x, final short y) {
      return this.partialCompare$mcS$sp(x, y) >= (double)0;
   }

   // $FF: synthetic method
   static boolean gt$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.gt(x, y);
   }

   default boolean gt(final short x, final short y) {
      return this.gt$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcS$sp$(final PartialOrder$mcS$sp $this, final short x, final short y) {
      return $this.gt$mcS$sp(x, y);
   }

   default boolean gt$mcS$sp(final short x, final short y) {
      return this.partialCompare$mcS$sp(x, y) > (double)0;
   }
}
