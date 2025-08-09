package cats.kernel;

import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public interface Next$mcC$sp extends Next, PartialNext$mcC$sp {
   // $FF: synthetic method
   static Option partialNext$(final Next$mcC$sp $this, final char a) {
      return $this.partialNext(a);
   }

   default Option partialNext(final char a) {
      return this.partialNext$mcC$sp(a);
   }

   // $FF: synthetic method
   static Option partialNext$mcC$sp$(final Next$mcC$sp $this, final char a) {
      return $this.partialNext$mcC$sp(a);
   }

   default Option partialNext$mcC$sp(final char a) {
      return new Some(BoxesRunTime.boxToCharacter(this.next$mcC$sp(a)));
   }
}
