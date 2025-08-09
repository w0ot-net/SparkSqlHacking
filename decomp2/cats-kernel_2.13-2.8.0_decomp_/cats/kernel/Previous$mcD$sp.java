package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public interface Previous$mcD$sp extends Previous, PartialPrevious$mcD$sp {
   // $FF: synthetic method
   static Option partialPrevious$(final Previous$mcD$sp $this, final double a) {
      return $this.partialPrevious(a);
   }

   default Option partialPrevious(final double a) {
      return this.partialPrevious$mcD$sp(a);
   }

   // $FF: synthetic method
   static Option partialPrevious$mcD$sp$(final Previous$mcD$sp $this, final double a) {
      return $this.partialPrevious$mcD$sp(a);
   }

   default Option partialPrevious$mcD$sp(final double a) {
      return new Some(BoxesRunTime.boxToDouble(this.previous$mcD$sp(a)));
   }
}
