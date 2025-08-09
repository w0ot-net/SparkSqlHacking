package spire.syntax;

import algebra.ring.Ring;
import spire.math.ConvertableTo;

public final class LiteralLongAdditiveSemigroupOps$ {
   public static final LiteralLongAdditiveSemigroupOps$ MODULE$ = new LiteralLongAdditiveSemigroupOps$();

   public final Object $plus$extension(final long $this, final Object rhs, final Ring ev, final ConvertableTo c) {
      return ev.plus(c.fromLong($this), rhs);
   }

   public final int hashCode$extension(final long $this) {
      return Long.hashCode($this);
   }

   public final boolean equals$extension(final long $this, final Object x$1) {
      boolean var4;
      if (x$1 instanceof LiteralLongAdditiveSemigroupOps) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var10000;
      if (var4) {
         long var6 = ((LiteralLongAdditiveSemigroupOps)x$1).lhs();
         if ($this == var6) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralLongAdditiveSemigroupOps$() {
   }
}
