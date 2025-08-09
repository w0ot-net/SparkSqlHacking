package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public interface Previous$mcC$sp extends Previous, PartialPrevious$mcC$sp {
   // $FF: synthetic method
   static Option partialPrevious$(final Previous$mcC$sp $this, final char a) {
      return $this.partialPrevious(a);
   }

   default Option partialPrevious(final char a) {
      return this.partialPrevious$mcC$sp(a);
   }

   // $FF: synthetic method
   static Option partialPrevious$mcC$sp$(final Previous$mcC$sp $this, final char a) {
      return $this.partialPrevious$mcC$sp(a);
   }

   default Option partialPrevious$mcC$sp(final char a) {
      return new Some(BoxesRunTime.boxToCharacter(this.previous$mcC$sp(a)));
   }
}
