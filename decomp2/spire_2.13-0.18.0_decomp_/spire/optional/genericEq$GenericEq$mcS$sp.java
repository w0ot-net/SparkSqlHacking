package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Eq.mcS.sp;

public class genericEq$GenericEq$mcS$sp extends genericEq.GenericEq implements Eq.mcS.sp {
   private static final long serialVersionUID = 0L;

   public boolean neqv(final short x, final short y) {
      return sp.neqv$(this, x, y);
   }

   public boolean neqv$mcS$sp(final short x, final short y) {
      return sp.neqv$mcS$sp$(this, x, y);
   }

   public boolean eqv(final short x, final short y) {
      return this.eqv$mcS$sp(x, y);
   }

   public boolean eqv$mcS$sp(final short x, final short y) {
      return x == y;
   }
}
