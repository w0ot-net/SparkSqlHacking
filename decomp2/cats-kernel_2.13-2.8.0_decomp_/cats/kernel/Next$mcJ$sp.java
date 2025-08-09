package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public interface Next$mcJ$sp extends Next, PartialNext$mcJ$sp {
   // $FF: synthetic method
   static Option partialNext$(final Next$mcJ$sp $this, final long a) {
      return $this.partialNext(a);
   }

   default Option partialNext(final long a) {
      return this.partialNext$mcJ$sp(a);
   }

   // $FF: synthetic method
   static Option partialNext$mcJ$sp$(final Next$mcJ$sp $this, final long a) {
      return $this.partialNext$mcJ$sp(a);
   }

   default Option partialNext$mcJ$sp(final long a) {
      return new Some(BoxesRunTime.boxToLong(this.next$mcJ$sp(a)));
   }
}
