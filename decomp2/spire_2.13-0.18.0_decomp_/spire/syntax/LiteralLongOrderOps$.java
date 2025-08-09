package spire.syntax;

import cats.kernel.Order;
import spire.math.ConvertableTo;

public final class LiteralLongOrderOps$ {
   public static final LiteralLongOrderOps$ MODULE$ = new LiteralLongOrderOps$();

   public final boolean $less$extension(final long $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.lt(c.fromLong($this), rhs);
   }

   public final boolean $less$eq$extension(final long $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.lteqv(c.fromLong($this), rhs);
   }

   public final boolean $greater$extension(final long $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.gt(c.fromLong($this), rhs);
   }

   public final boolean $greater$eq$extension(final long $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.gteqv(c.fromLong($this), rhs);
   }

   public final int cmp$extension(final long $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.compare(c.fromLong($this), rhs);
   }

   public final Object min$extension(final long $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.min(c.fromLong($this), rhs);
   }

   public final Object max$extension(final long $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.max(c.fromLong($this), rhs);
   }

   public final int hashCode$extension(final long $this) {
      return Long.hashCode($this);
   }

   public final boolean equals$extension(final long $this, final Object x$1) {
      boolean var4;
      if (x$1 instanceof LiteralLongOrderOps) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var10000;
      if (var4) {
         long var6 = ((LiteralLongOrderOps)x$1).lhs();
         if ($this == var6) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralLongOrderOps$() {
   }
}
