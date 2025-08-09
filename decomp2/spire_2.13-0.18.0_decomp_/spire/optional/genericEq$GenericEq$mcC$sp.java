package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Eq.mcC.sp;

public class genericEq$GenericEq$mcC$sp extends genericEq.GenericEq implements Eq.mcC.sp {
   private static final long serialVersionUID = 0L;

   public boolean neqv(final char x, final char y) {
      return sp.neqv$(this, x, y);
   }

   public boolean neqv$mcC$sp(final char x, final char y) {
      return sp.neqv$mcC$sp$(this, x, y);
   }

   public boolean eqv(final char x, final char y) {
      return this.eqv$mcC$sp(x, y);
   }

   public boolean eqv$mcC$sp(final char x, final char y) {
      return x == y;
   }
}
