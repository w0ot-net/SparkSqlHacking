package cats.kernel;

import scala.runtime.BoxedUnit;

public interface Eq$mcV$sp extends Eq {
   // $FF: synthetic method
   static boolean neqv$(final Eq$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final BoxedUnit x, final BoxedUnit y) {
      return this.neqv$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcV$sp$(final Eq$mcV$sp $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.neqv$mcV$sp(x, y);
   }

   default boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return !this.eqv$mcV$sp(x, y);
   }
}
