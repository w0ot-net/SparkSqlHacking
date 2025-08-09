package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxedUnit;

public interface Previous$mcV$sp extends Previous, PartialPrevious$mcV$sp {
   // $FF: synthetic method
   static Option partialPrevious$(final Previous$mcV$sp $this, final BoxedUnit a) {
      return $this.partialPrevious(a);
   }

   default Option partialPrevious(final BoxedUnit a) {
      return this.partialPrevious$mcV$sp(a);
   }

   // $FF: synthetic method
   static Option partialPrevious$mcV$sp$(final Previous$mcV$sp $this, final BoxedUnit a) {
      return $this.partialPrevious$mcV$sp(a);
   }

   default Option partialPrevious$mcV$sp(final BoxedUnit a) {
      this.previous$mcV$sp(a);
      return new Some(BoxedUnit.UNIT);
   }
}
