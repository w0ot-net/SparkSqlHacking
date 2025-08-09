package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public interface Next$mcZ$sp extends Next, PartialNext$mcZ$sp {
   // $FF: synthetic method
   static Option partialNext$(final Next$mcZ$sp $this, final boolean a) {
      return $this.partialNext(a);
   }

   default Option partialNext(final boolean a) {
      return this.partialNext$mcZ$sp(a);
   }

   // $FF: synthetic method
   static Option partialNext$mcZ$sp$(final Next$mcZ$sp $this, final boolean a) {
      return $this.partialNext$mcZ$sp(a);
   }

   default Option partialNext$mcZ$sp(final boolean a) {
      return new Some(BoxesRunTime.boxToBoolean(this.next$mcZ$sp(a)));
   }
}
