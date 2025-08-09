package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public interface Previous$mcJ$sp extends Previous, PartialPrevious$mcJ$sp {
   // $FF: synthetic method
   static Option partialPrevious$(final Previous$mcJ$sp $this, final long a) {
      return $this.partialPrevious(a);
   }

   default Option partialPrevious(final long a) {
      return this.partialPrevious$mcJ$sp(a);
   }

   // $FF: synthetic method
   static Option partialPrevious$mcJ$sp$(final Previous$mcJ$sp $this, final long a) {
      return $this.partialPrevious$mcJ$sp(a);
   }

   default Option partialPrevious$mcJ$sp(final long a) {
      return new Some(BoxesRunTime.boxToLong(this.previous$mcJ$sp(a)));
   }
}
