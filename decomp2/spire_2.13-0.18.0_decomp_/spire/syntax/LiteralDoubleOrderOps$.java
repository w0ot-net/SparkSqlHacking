package spire.syntax;

import cats.kernel.Order;
import spire.math.ConvertableTo;

public final class LiteralDoubleOrderOps$ {
   public static final LiteralDoubleOrderOps$ MODULE$ = new LiteralDoubleOrderOps$();

   public final boolean $less$extension(final double $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.lt(c.fromDouble($this), rhs);
   }

   public final boolean $less$eq$extension(final double $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.lteqv(c.fromDouble($this), rhs);
   }

   public final boolean $greater$extension(final double $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.gt(c.fromDouble($this), rhs);
   }

   public final boolean $greater$eq$extension(final double $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.gteqv(c.fromDouble($this), rhs);
   }

   public final int cmp$extension(final double $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.compare(c.fromDouble($this), rhs);
   }

   public final Object min$extension(final double $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.min(c.fromDouble($this), rhs);
   }

   public final Object max$extension(final double $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return ev.max(c.fromDouble($this), rhs);
   }

   public final int hashCode$extension(final double $this) {
      return Double.hashCode($this);
   }

   public final boolean equals$extension(final double $this, final Object x$1) {
      boolean var4;
      if (x$1 instanceof LiteralDoubleOrderOps) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var10000;
      if (var4) {
         double var6 = ((LiteralDoubleOrderOps)x$1).lhs();
         if ($this == var6) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralDoubleOrderOps$() {
   }
}
