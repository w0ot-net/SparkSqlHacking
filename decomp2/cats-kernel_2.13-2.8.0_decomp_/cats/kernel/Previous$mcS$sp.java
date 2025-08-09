package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public interface Previous$mcS$sp extends Previous, PartialPrevious$mcS$sp {
   // $FF: synthetic method
   static Option partialPrevious$(final Previous$mcS$sp $this, final short a) {
      return $this.partialPrevious(a);
   }

   default Option partialPrevious(final short a) {
      return this.partialPrevious$mcS$sp(a);
   }

   // $FF: synthetic method
   static Option partialPrevious$mcS$sp$(final Previous$mcS$sp $this, final short a) {
      return $this.partialPrevious$mcS$sp(a);
   }

   default Option partialPrevious$mcS$sp(final short a) {
      return new Some(BoxesRunTime.boxToShort(this.previous$mcS$sp(a)));
   }
}
