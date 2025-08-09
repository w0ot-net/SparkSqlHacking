package cats.kernel;

import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichDouble;

public interface PartialOrder$mcC$sp extends PartialOrder, Eq$mcC$sp {
   // $FF: synthetic method
   static Option partialComparison$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.partialComparison(x, y);
   }

   default Option partialComparison(final char x, final char y) {
      return this.partialComparison$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static Option partialComparison$mcC$sp$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.partialComparison$mcC$sp(x, y);
   }

   default Option partialComparison$mcC$sp(final char x, final char y) {
      return Comparison$.MODULE$.fromDouble(this.partialCompare$mcC$sp(x, y));
   }

   // $FF: synthetic method
   static Option tryCompare$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.tryCompare(x, y);
   }

   default Option tryCompare(final char x, final char y) {
      return this.tryCompare$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static Option tryCompare$mcC$sp$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.tryCompare$mcC$sp(x, y);
   }

   default Option tryCompare$mcC$sp(final char x, final char y) {
      double c = BoxesRunTime.unboxToDouble((new RichDouble(.MODULE$.doubleWrapper(this.partialCompare$mcC$sp(x, y)))).sign());
      return (Option)(Double.isNaN(c) ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger((int)c)));
   }

   // $FF: synthetic method
   static Option pmin$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.pmin(x, y);
   }

   default Option pmin(final char x, final char y) {
      return this.pmin$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmin$mcC$sp$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.pmin$mcC$sp(x, y);
   }

   default Option pmin$mcC$sp(final char x, final char y) {
      double c = this.partialCompare$mcC$sp(x, y);
      return (Option)(c <= (double)0 ? new Some(BoxesRunTime.boxToCharacter(x)) : (c > (double)0 ? new Some(BoxesRunTime.boxToCharacter(y)) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static Option pmax$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.pmax(x, y);
   }

   default Option pmax(final char x, final char y) {
      return this.pmax$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmax$mcC$sp$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.pmax$mcC$sp(x, y);
   }

   default Option pmax$mcC$sp(final char x, final char y) {
      double c = this.partialCompare$mcC$sp(x, y);
      return (Option)(c >= (double)0 ? new Some(BoxesRunTime.boxToCharacter(x)) : (c < (double)0 ? new Some(BoxesRunTime.boxToCharacter(y)) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static boolean eqv$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final char x, final char y) {
      return this.eqv$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcC$sp$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.eqv$mcC$sp(x, y);
   }

   default boolean eqv$mcC$sp(final char x, final char y) {
      return this.partialCompare$mcC$sp(x, y) == (double)0;
   }

   // $FF: synthetic method
   static boolean lteqv$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final char x, final char y) {
      return this.lteqv$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcC$sp$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.lteqv$mcC$sp(x, y);
   }

   default boolean lteqv$mcC$sp(final char x, final char y) {
      return this.partialCompare$mcC$sp(x, y) <= (double)0;
   }

   // $FF: synthetic method
   static boolean lt$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.lt(x, y);
   }

   default boolean lt(final char x, final char y) {
      return this.lt$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcC$sp$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.lt$mcC$sp(x, y);
   }

   default boolean lt$mcC$sp(final char x, final char y) {
      return this.partialCompare$mcC$sp(x, y) < (double)0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final char x, final char y) {
      return this.gteqv$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcC$sp$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.gteqv$mcC$sp(x, y);
   }

   default boolean gteqv$mcC$sp(final char x, final char y) {
      return this.partialCompare$mcC$sp(x, y) >= (double)0;
   }

   // $FF: synthetic method
   static boolean gt$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.gt(x, y);
   }

   default boolean gt(final char x, final char y) {
      return this.gt$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcC$sp$(final PartialOrder$mcC$sp $this, final char x, final char y) {
      return $this.gt$mcC$sp(x, y);
   }

   default boolean gt$mcC$sp(final char x, final char y) {
      return this.partialCompare$mcC$sp(x, y) > (double)0;
   }
}
