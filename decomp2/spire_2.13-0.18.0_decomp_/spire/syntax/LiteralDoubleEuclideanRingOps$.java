package spire.syntax;

import algebra.ring.Field;
import scala.Tuple2;

public final class LiteralDoubleEuclideanRingOps$ {
   public static final LiteralDoubleEuclideanRingOps$ MODULE$ = new LiteralDoubleEuclideanRingOps$();

   public final Object equot$extension(final double $this, final Object rhs, final Field ev) {
      return ev.equot(ev.fromDouble($this), rhs);
   }

   public final Object emod$extension(final double $this, final Object rhs, final Field ev) {
      return ev.emod(ev.fromDouble($this), rhs);
   }

   public final Tuple2 equotmod$extension(final double $this, final Object rhs, final Field ev) {
      return ev.equotmod(ev.fromDouble($this), rhs);
   }

   public final int hashCode$extension(final double $this) {
      return Double.hashCode($this);
   }

   public final boolean equals$extension(final double $this, final Object x$1) {
      boolean var4;
      if (x$1 instanceof LiteralDoubleEuclideanRingOps) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var10000;
      if (var4) {
         double var6 = ((LiteralDoubleEuclideanRingOps)x$1).lhs();
         if ($this == var6) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralDoubleEuclideanRingOps$() {
   }
}
