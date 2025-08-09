package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public interface Next$mcS$sp extends Next, PartialNext$mcS$sp {
   // $FF: synthetic method
   static Option partialNext$(final Next$mcS$sp $this, final short a) {
      return $this.partialNext(a);
   }

   default Option partialNext(final short a) {
      return this.partialNext$mcS$sp(a);
   }

   // $FF: synthetic method
   static Option partialNext$mcS$sp$(final Next$mcS$sp $this, final short a) {
      return $this.partialNext$mcS$sp(a);
   }

   default Option partialNext$mcS$sp(final short a) {
      return new Some(BoxesRunTime.boxToShort(this.next$mcS$sp(a)));
   }
}
