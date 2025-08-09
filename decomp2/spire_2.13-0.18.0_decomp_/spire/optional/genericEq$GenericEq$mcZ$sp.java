package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Eq.mcZ.sp;

public class genericEq$GenericEq$mcZ$sp extends genericEq.GenericEq implements Eq.mcZ.sp {
   private static final long serialVersionUID = 0L;

   public boolean neqv(final boolean x, final boolean y) {
      return sp.neqv$(this, x, y);
   }

   public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
      return sp.neqv$mcZ$sp$(this, x, y);
   }

   public boolean eqv(final boolean x, final boolean y) {
      return this.eqv$mcZ$sp(x, y);
   }

   public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
      return x == y;
   }
}
