package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public interface Next$mcB$sp extends Next, PartialNext$mcB$sp {
   // $FF: synthetic method
   static Option partialNext$(final Next$mcB$sp $this, final byte a) {
      return $this.partialNext(a);
   }

   default Option partialNext(final byte a) {
      return this.partialNext$mcB$sp(a);
   }

   // $FF: synthetic method
   static Option partialNext$mcB$sp$(final Next$mcB$sp $this, final byte a) {
      return $this.partialNext$mcB$sp(a);
   }

   default Option partialNext$mcB$sp(final byte a) {
      return new Some(BoxesRunTime.boxToByte(this.next$mcB$sp(a)));
   }
}
