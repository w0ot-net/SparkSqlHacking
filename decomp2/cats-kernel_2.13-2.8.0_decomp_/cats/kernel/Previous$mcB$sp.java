package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public interface Previous$mcB$sp extends Previous, PartialPrevious$mcB$sp {
   // $FF: synthetic method
   static Option partialPrevious$(final Previous$mcB$sp $this, final byte a) {
      return $this.partialPrevious(a);
   }

   default Option partialPrevious(final byte a) {
      return this.partialPrevious$mcB$sp(a);
   }

   // $FF: synthetic method
   static Option partialPrevious$mcB$sp$(final Previous$mcB$sp $this, final byte a) {
      return $this.partialPrevious$mcB$sp(a);
   }

   default Option partialPrevious$mcB$sp(final byte a) {
      return new Some(BoxesRunTime.boxToByte(this.previous$mcB$sp(a)));
   }
}
