package spire.syntax;

import algebra.ring.TruncatedDivision;
import scala.Tuple2;
import spire.math.ConvertableTo;

public final class LiteralDoubleTruncatedDivisionOps$ {
   public static final LiteralDoubleTruncatedDivisionOps$ MODULE$ = new LiteralDoubleTruncatedDivisionOps$();

   public final Object tquot$extension(final double $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.tquot(c.fromDouble($this), rhs);
   }

   public final Object tmod$extension(final double $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.tmod(c.fromDouble($this), rhs);
   }

   public final Tuple2 tquotmod$extension(final double $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.tquotmod(c.fromDouble($this), rhs);
   }

   public final Object fquot$extension(final double $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.fquot(c.fromDouble($this), rhs);
   }

   public final Object fmod$extension(final double $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.fmod(c.fromDouble($this), rhs);
   }

   public final Tuple2 fquotmod$extension(final double $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.fquotmod(c.fromDouble($this), rhs);
   }

   public final int hashCode$extension(final double $this) {
      return Double.hashCode($this);
   }

   public final boolean equals$extension(final double $this, final Object x$1) {
      boolean var4;
      if (x$1 instanceof LiteralDoubleTruncatedDivisionOps) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var10000;
      if (var4) {
         double var6 = ((LiteralDoubleTruncatedDivisionOps)x$1).lhs();
         if ($this == var6) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralDoubleTruncatedDivisionOps$() {
   }
}
