package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Eq.mcV.sp;
import scala.runtime.BoxedUnit;

public class genericEq$GenericEq$mcV$sp extends genericEq.GenericEq implements Eq.mcV.sp {
   private static final long serialVersionUID = 0L;

   public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
      return sp.neqv$(this, x, y);
   }

   public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return sp.neqv$mcV$sp$(this, x, y);
   }

   public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
      return this.eqv$mcV$sp(x, y);
   }

   public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      boolean var10000;
      label23: {
         if (x == null) {
            if (y == null) {
               break label23;
            }
         } else if (x.equals(y)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }
}
