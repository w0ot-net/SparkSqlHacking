package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Eq.mcI.sp;

public class genericEq$GenericEq$mcI$sp extends genericEq.GenericEq implements Eq.mcI.sp {
   private static final long serialVersionUID = 0L;

   public boolean neqv(final int x, final int y) {
      return sp.neqv$(this, x, y);
   }

   public boolean neqv$mcI$sp(final int x, final int y) {
      return sp.neqv$mcI$sp$(this, x, y);
   }

   public boolean eqv(final int x, final int y) {
      return this.eqv$mcI$sp(x, y);
   }

   public boolean eqv$mcI$sp(final int x, final int y) {
      return x == y;
   }
}
