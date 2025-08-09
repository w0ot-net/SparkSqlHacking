package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Eq.mcD.sp;

public class genericEq$GenericEq$mcD$sp extends genericEq.GenericEq implements Eq.mcD.sp {
   private static final long serialVersionUID = 0L;

   public boolean neqv(final double x, final double y) {
      return sp.neqv$(this, x, y);
   }

   public boolean neqv$mcD$sp(final double x, final double y) {
      return sp.neqv$mcD$sp$(this, x, y);
   }

   public boolean eqv(final double x, final double y) {
      return this.eqv$mcD$sp(x, y);
   }

   public boolean eqv$mcD$sp(final double x, final double y) {
      return x == y;
   }
}
