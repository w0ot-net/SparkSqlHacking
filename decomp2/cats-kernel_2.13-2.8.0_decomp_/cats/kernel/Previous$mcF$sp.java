package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public interface Previous$mcF$sp extends Previous, PartialPrevious$mcF$sp {
   // $FF: synthetic method
   static Option partialPrevious$(final Previous$mcF$sp $this, final float a) {
      return $this.partialPrevious(a);
   }

   default Option partialPrevious(final float a) {
      return this.partialPrevious$mcF$sp(a);
   }

   // $FF: synthetic method
   static Option partialPrevious$mcF$sp$(final Previous$mcF$sp $this, final float a) {
      return $this.partialPrevious$mcF$sp(a);
   }

   default Option partialPrevious$mcF$sp(final float a) {
      return new Some(BoxesRunTime.boxToFloat(this.previous$mcF$sp(a)));
   }
}
