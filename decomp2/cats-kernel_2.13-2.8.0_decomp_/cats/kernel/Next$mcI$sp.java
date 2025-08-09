package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public interface Next$mcI$sp extends Next, PartialNext$mcI$sp {
   // $FF: synthetic method
   static Option partialNext$(final Next$mcI$sp $this, final int a) {
      return $this.partialNext(a);
   }

   default Option partialNext(final int a) {
      return this.partialNext$mcI$sp(a);
   }

   // $FF: synthetic method
   static Option partialNext$mcI$sp$(final Next$mcI$sp $this, final int a) {
      return $this.partialNext$mcI$sp(a);
   }

   default Option partialNext$mcI$sp(final int a) {
      return new Some(BoxesRunTime.boxToInteger(this.next$mcI$sp(a)));
   }
}
