package spire.syntax;

import algebra.ring.Ring;
import spire.math.ConvertableTo;

public final class LiteralLongMultiplicativeSemigroupOps$ {
   public static final LiteralLongMultiplicativeSemigroupOps$ MODULE$ = new LiteralLongMultiplicativeSemigroupOps$();

   public final Object $times$extension(final long $this, final Object rhs, final Ring ev, final ConvertableTo c) {
      return ev.times(c.fromLong($this), rhs);
   }

   public final int hashCode$extension(final long $this) {
      return Long.hashCode($this);
   }

   public final boolean equals$extension(final long $this, final Object x$1) {
      boolean var4;
      if (x$1 instanceof LiteralLongMultiplicativeSemigroupOps) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var10000;
      if (var4) {
         long var6 = ((LiteralLongMultiplicativeSemigroupOps)x$1).lhs();
         if ($this == var6) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralLongMultiplicativeSemigroupOps$() {
   }
}
