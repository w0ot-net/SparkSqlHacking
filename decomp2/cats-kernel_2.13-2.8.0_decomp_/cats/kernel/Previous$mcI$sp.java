package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public interface Previous$mcI$sp extends Previous, PartialPrevious$mcI$sp {
   // $FF: synthetic method
   static Option partialPrevious$(final Previous$mcI$sp $this, final int a) {
      return $this.partialPrevious(a);
   }

   default Option partialPrevious(final int a) {
      return this.partialPrevious$mcI$sp(a);
   }

   // $FF: synthetic method
   static Option partialPrevious$mcI$sp$(final Previous$mcI$sp $this, final int a) {
      return $this.partialPrevious$mcI$sp(a);
   }

   default Option partialPrevious$mcI$sp(final int a) {
      return new Some(BoxesRunTime.boxToInteger(this.previous$mcI$sp(a)));
   }
}
