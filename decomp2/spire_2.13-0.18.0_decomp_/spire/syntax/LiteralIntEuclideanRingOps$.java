package spire.syntax;

import algebra.ring.EuclideanRing;
import scala.Tuple2;

public final class LiteralIntEuclideanRingOps$ {
   public static final LiteralIntEuclideanRingOps$ MODULE$ = new LiteralIntEuclideanRingOps$();

   public final Object equot$extension(final int $this, final Object rhs, final EuclideanRing ev) {
      return ev.equot(ev.fromInt($this), rhs);
   }

   public final Object emod$extension(final int $this, final Object rhs, final EuclideanRing ev) {
      return ev.emod(ev.fromInt($this), rhs);
   }

   public final Tuple2 equotmod$extension(final int $this, final Object rhs, final EuclideanRing ev) {
      return ev.equotmod(ev.fromInt($this), rhs);
   }

   public final int hashCode$extension(final int $this) {
      return Integer.hashCode($this);
   }

   public final boolean equals$extension(final int $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof LiteralIntEuclideanRingOps) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var10000;
      if (var3) {
         int var5 = ((LiteralIntEuclideanRingOps)x$1).lhs();
         if ($this == var5) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralIntEuclideanRingOps$() {
   }
}
