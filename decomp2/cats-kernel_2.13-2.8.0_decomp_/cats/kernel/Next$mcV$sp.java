package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxedUnit;

public interface Next$mcV$sp extends Next, PartialNext$mcV$sp {
   // $FF: synthetic method
   static Option partialNext$(final Next$mcV$sp $this, final BoxedUnit a) {
      return $this.partialNext(a);
   }

   default Option partialNext(final BoxedUnit a) {
      return this.partialNext$mcV$sp(a);
   }

   // $FF: synthetic method
   static Option partialNext$mcV$sp$(final Next$mcV$sp $this, final BoxedUnit a) {
      return $this.partialNext$mcV$sp(a);
   }

   default Option partialNext$mcV$sp(final BoxedUnit a) {
      this.next$mcV$sp(a);
      return new Some(BoxedUnit.UNIT);
   }
}
