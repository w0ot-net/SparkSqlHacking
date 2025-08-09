package spire.syntax;

import algebra.ring.Field;

public final class LiteralDoubleMultiplicativeGroupOps$ {
   public static final LiteralDoubleMultiplicativeGroupOps$ MODULE$ = new LiteralDoubleMultiplicativeGroupOps$();

   public final Object $div$extension(final double $this, final Object rhs, final Field ev) {
      return ev.div(ev.fromDouble($this), rhs);
   }

   public final int hashCode$extension(final double $this) {
      return Double.hashCode($this);
   }

   public final boolean equals$extension(final double $this, final Object x$1) {
      boolean var4;
      if (x$1 instanceof LiteralDoubleMultiplicativeGroupOps) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var10000;
      if (var4) {
         double var6 = ((LiteralDoubleMultiplicativeGroupOps)x$1).lhs();
         if ($this == var6) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralDoubleMultiplicativeGroupOps$() {
   }
}
