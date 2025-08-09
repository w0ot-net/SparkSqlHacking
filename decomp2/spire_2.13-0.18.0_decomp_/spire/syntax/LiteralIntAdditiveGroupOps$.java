package spire.syntax;

import algebra.ring.Ring;

public final class LiteralIntAdditiveGroupOps$ {
   public static final LiteralIntAdditiveGroupOps$ MODULE$ = new LiteralIntAdditiveGroupOps$();

   public final Object $minus$extension(final int $this, final Object rhs, final Ring ev) {
      return ev.minus(ev.fromInt($this), rhs);
   }

   public final int hashCode$extension(final int $this) {
      return Integer.hashCode($this);
   }

   public final boolean equals$extension(final int $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof LiteralIntAdditiveGroupOps) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var10000;
      if (var3) {
         int var5 = ((LiteralIntAdditiveGroupOps)x$1).lhs();
         if ($this == var5) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralIntAdditiveGroupOps$() {
   }
}
