package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public interface Next$mcF$sp extends Next, PartialNext$mcF$sp {
   // $FF: synthetic method
   static Option partialNext$(final Next$mcF$sp $this, final float a) {
      return $this.partialNext(a);
   }

   default Option partialNext(final float a) {
      return this.partialNext$mcF$sp(a);
   }

   // $FF: synthetic method
   static Option partialNext$mcF$sp$(final Next$mcF$sp $this, final float a) {
      return $this.partialNext$mcF$sp(a);
   }

   default Option partialNext$mcF$sp(final float a) {
      return new Some(BoxesRunTime.boxToFloat(this.next$mcF$sp(a)));
   }
}
