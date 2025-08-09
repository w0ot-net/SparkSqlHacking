package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public interface Previous$mcZ$sp extends Previous, PartialPrevious$mcZ$sp {
   // $FF: synthetic method
   static Option partialPrevious$(final Previous$mcZ$sp $this, final boolean a) {
      return $this.partialPrevious(a);
   }

   default Option partialPrevious(final boolean a) {
      return this.partialPrevious$mcZ$sp(a);
   }

   // $FF: synthetic method
   static Option partialPrevious$mcZ$sp$(final Previous$mcZ$sp $this, final boolean a) {
      return $this.partialPrevious$mcZ$sp(a);
   }

   default Option partialPrevious$mcZ$sp(final boolean a) {
      return new Some(BoxesRunTime.boxToBoolean(this.previous$mcZ$sp(a)));
   }
}
