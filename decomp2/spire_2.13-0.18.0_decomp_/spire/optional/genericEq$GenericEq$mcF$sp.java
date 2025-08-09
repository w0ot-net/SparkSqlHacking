package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Eq.mcF.sp;

public class genericEq$GenericEq$mcF$sp extends genericEq.GenericEq implements Eq.mcF.sp {
   private static final long serialVersionUID = 0L;

   public boolean neqv(final float x, final float y) {
      return sp.neqv$(this, x, y);
   }

   public boolean neqv$mcF$sp(final float x, final float y) {
      return sp.neqv$mcF$sp$(this, x, y);
   }

   public boolean eqv(final float x, final float y) {
      return this.eqv$mcF$sp(x, y);
   }

   public boolean eqv$mcF$sp(final float x, final float y) {
      return x == y;
   }
}
