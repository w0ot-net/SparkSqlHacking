package spire.syntax;

import algebra.ring.Field;
import spire.math.ConvertableTo;

public final class LiteralLongMultiplicativeGroupOps$ {
   public static final LiteralLongMultiplicativeGroupOps$ MODULE$ = new LiteralLongMultiplicativeGroupOps$();

   public final Object $div$extension(final long $this, final Object rhs, final Field ev, final ConvertableTo c) {
      return ev.div(c.fromLong($this), rhs);
   }

   public final int hashCode$extension(final long $this) {
      return Long.hashCode($this);
   }

   public final boolean equals$extension(final long $this, final Object x$1) {
      boolean var4;
      if (x$1 instanceof LiteralLongMultiplicativeGroupOps) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var10000;
      if (var4) {
         long var6 = ((LiteralLongMultiplicativeGroupOps)x$1).lhs();
         if ($this == var6) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralLongMultiplicativeGroupOps$() {
   }
}
