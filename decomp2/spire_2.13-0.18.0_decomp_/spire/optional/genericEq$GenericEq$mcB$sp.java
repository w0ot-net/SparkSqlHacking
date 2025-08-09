package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Eq.mcB.sp;

public class genericEq$GenericEq$mcB$sp extends genericEq.GenericEq implements Eq.mcB.sp {
   private static final long serialVersionUID = 0L;

   public boolean neqv(final byte x, final byte y) {
      return sp.neqv$(this, x, y);
   }

   public boolean neqv$mcB$sp(final byte x, final byte y) {
      return sp.neqv$mcB$sp$(this, x, y);
   }

   public boolean eqv(final byte x, final byte y) {
      return this.eqv$mcB$sp(x, y);
   }

   public boolean eqv$mcB$sp(final byte x, final byte y) {
      return x == y;
   }
}
