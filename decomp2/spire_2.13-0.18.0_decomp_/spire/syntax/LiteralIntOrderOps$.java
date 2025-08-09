package spire.syntax;

import cats.kernel.Order;
import spire.math.ConvertableTo;

public final class LiteralIntOrderOps$ {
   public static final LiteralIntOrderOps$ MODULE$ = new LiteralIntOrderOps$();

   public final boolean $less$extension(final int $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.lt(c.fromInt($this), rhs);
   }

   public final boolean $less$eq$extension(final int $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.lteqv(c.fromInt($this), rhs);
   }

   public final boolean $greater$extension(final int $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.gt(c.fromInt($this), rhs);
   }

   public final boolean $greater$eq$extension(final int $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.gteqv(c.fromInt($this), rhs);
   }

   public final int cmp$extension(final int $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.compare(c.fromInt($this), rhs);
   }

   public final Object min$extension(final int $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.min(c.fromInt($this), rhs);
   }

   public final Object max$extension(final int $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.max(c.fromInt($this), rhs);
   }

   public final int hashCode$extension(final int $this) {
      return Integer.hashCode($this);
   }

   public final boolean equals$extension(final int $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof LiteralIntOrderOps) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var10000;
      if (var3) {
         int var5 = ((LiteralIntOrderOps)x$1).lhs();
         if ($this == var5) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralIntOrderOps$() {
   }
}
