package spire.syntax;

import algebra.ring.EuclideanRing;
import scala.Tuple2;
import spire.math.ConvertableTo;

public final class LiteralLongEuclideanRingOps$ {
   public static final LiteralLongEuclideanRingOps$ MODULE$ = new LiteralLongEuclideanRingOps$();

   public final Object equot$extension(final long $this, final Object rhs, final EuclideanRing ev, final ConvertableTo c) {
      return ev.equot(c.fromLong($this), rhs);
   }

   public final Object emod$extension(final long $this, final Object rhs, final EuclideanRing ev, final ConvertableTo c) {
      return ev.emod(c.fromLong($this), rhs);
   }

   public final Tuple2 equotmod$extension(final long $this, final Object rhs, final EuclideanRing ev, final ConvertableTo c) {
      return ev.equotmod(c.fromLong($this), rhs);
   }

   public final int hashCode$extension(final long $this) {
      return Long.hashCode($this);
   }

   public final boolean equals$extension(final long $this, final Object x$1) {
      boolean var4;
      if (x$1 instanceof LiteralLongEuclideanRingOps) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var10000;
      if (var4) {
         long var6 = ((LiteralLongEuclideanRingOps)x$1).lhs();
         if ($this == var6) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralLongEuclideanRingOps$() {
   }
}
