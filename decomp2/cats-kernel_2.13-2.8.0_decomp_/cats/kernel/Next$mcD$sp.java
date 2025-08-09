package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public interface Next$mcD$sp extends Next, PartialNext$mcD$sp {
   // $FF: synthetic method
   static Option partialNext$(final Next$mcD$sp $this, final double a) {
      return $this.partialNext(a);
   }

   default Option partialNext(final double a) {
      return this.partialNext$mcD$sp(a);
   }

   // $FF: synthetic method
   static Option partialNext$mcD$sp$(final Next$mcD$sp $this, final double a) {
      return $this.partialNext$mcD$sp(a);
   }

   default Option partialNext$mcD$sp(final double a) {
      return new Some(BoxesRunTime.boxToDouble(this.next$mcD$sp(a)));
   }
}
