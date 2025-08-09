package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Eq.mcJ.sp;

public class genericEq$GenericEq$mcJ$sp extends genericEq.GenericEq implements Eq.mcJ.sp {
   private static final long serialVersionUID = 0L;

   public boolean neqv(final long x, final long y) {
      return sp.neqv$(this, x, y);
   }

   public boolean neqv$mcJ$sp(final long x, final long y) {
      return sp.neqv$mcJ$sp$(this, x, y);
   }

   public boolean eqv(final long x, final long y) {
      return this.eqv$mcJ$sp(x, y);
   }

   public boolean eqv$mcJ$sp(final long x, final long y) {
      return x == y;
   }
}
