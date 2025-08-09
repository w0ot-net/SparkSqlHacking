package cats.kernel;

import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichDouble;

public interface PartialOrder$mcF$sp extends PartialOrder, Eq$mcF$sp {
   // $FF: synthetic method
   static Option partialComparison$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.partialComparison(x, y);
   }

   default Option partialComparison(final float x, final float y) {
      return this.partialComparison$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static Option partialComparison$mcF$sp$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.partialComparison$mcF$sp(x, y);
   }

   default Option partialComparison$mcF$sp(final float x, final float y) {
      return Comparison$.MODULE$.fromDouble(this.partialCompare$mcF$sp(x, y));
   }

   // $FF: synthetic method
   static Option tryCompare$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.tryCompare(x, y);
   }

   default Option tryCompare(final float x, final float y) {
      return this.tryCompare$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static Option tryCompare$mcF$sp$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.tryCompare$mcF$sp(x, y);
   }

   default Option tryCompare$mcF$sp(final float x, final float y) {
      double c = BoxesRunTime.unboxToDouble((new RichDouble(.MODULE$.doubleWrapper(this.partialCompare$mcF$sp(x, y)))).sign());
      return (Option)(Double.isNaN(c) ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger((int)c)));
   }

   // $FF: synthetic method
   static Option pmin$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.pmin(x, y);
   }

   default Option pmin(final float x, final float y) {
      return this.pmin$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmin$mcF$sp$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.pmin$mcF$sp(x, y);
   }

   default Option pmin$mcF$sp(final float x, final float y) {
      double c = this.partialCompare$mcF$sp(x, y);
      return (Option)(c <= (double)0 ? new Some(BoxesRunTime.boxToFloat(x)) : (c > (double)0 ? new Some(BoxesRunTime.boxToFloat(y)) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static Option pmax$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.pmax(x, y);
   }

   default Option pmax(final float x, final float y) {
      return this.pmax$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static Option pmax$mcF$sp$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.pmax$mcF$sp(x, y);
   }

   default Option pmax$mcF$sp(final float x, final float y) {
      double c = this.partialCompare$mcF$sp(x, y);
      return (Option)(c >= (double)0 ? new Some(BoxesRunTime.boxToFloat(x)) : (c < (double)0 ? new Some(BoxesRunTime.boxToFloat(y)) : scala.None..MODULE$));
   }

   // $FF: synthetic method
   static boolean eqv$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final float x, final float y) {
      return this.eqv$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcF$sp$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.eqv$mcF$sp(x, y);
   }

   default boolean eqv$mcF$sp(final float x, final float y) {
      return this.partialCompare$mcF$sp(x, y) == (double)0;
   }

   // $FF: synthetic method
   static boolean lteqv$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final float x, final float y) {
      return this.lteqv$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcF$sp$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.lteqv$mcF$sp(x, y);
   }

   default boolean lteqv$mcF$sp(final float x, final float y) {
      return this.partialCompare$mcF$sp(x, y) <= (double)0;
   }

   // $FF: synthetic method
   static boolean lt$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.lt(x, y);
   }

   default boolean lt(final float x, final float y) {
      return this.lt$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcF$sp$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.lt$mcF$sp(x, y);
   }

   default boolean lt$mcF$sp(final float x, final float y) {
      return this.partialCompare$mcF$sp(x, y) < (double)0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final float x, final float y) {
      return this.gteqv$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcF$sp$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.gteqv$mcF$sp(x, y);
   }

   default boolean gteqv$mcF$sp(final float x, final float y) {
      return this.partialCompare$mcF$sp(x, y) >= (double)0;
   }

   // $FF: synthetic method
   static boolean gt$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.gt(x, y);
   }

   default boolean gt(final float x, final float y) {
      return this.gt$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcF$sp$(final PartialOrder$mcF$sp $this, final float x, final float y) {
      return $this.gt$mcF$sp(x, y);
   }

   default boolean gt$mcF$sp(final float x, final float y) {
      return this.partialCompare$mcF$sp(x, y) > (double)0;
   }
}
