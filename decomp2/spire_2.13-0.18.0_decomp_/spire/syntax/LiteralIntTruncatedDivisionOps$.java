package spire.syntax;

import algebra.ring.TruncatedDivision;
import scala.Tuple2;
import spire.math.ConvertableTo;

public final class LiteralIntTruncatedDivisionOps$ {
   public static final LiteralIntTruncatedDivisionOps$ MODULE$ = new LiteralIntTruncatedDivisionOps$();

   public final Object tquot$extension(final int $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.tquot(c.fromInt($this), rhs);
   }

   public final Object tmod$extension(final int $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.tmod(c.fromInt($this), rhs);
   }

   public final Tuple2 tquotmod$extension(final int $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.tquotmod(c.fromInt($this), rhs);
   }

   public final Object fquot$extension(final int $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.fquot(c.fromInt($this), rhs);
   }

   public final Object fmod$extension(final int $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.fmod(c.fromInt($this), rhs);
   }

   public final Tuple2 fquotmod$extension(final int $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.fquotmod(c.fromInt($this), rhs);
   }

   public final int hashCode$extension(final int $this) {
      return Integer.hashCode($this);
   }

   public final boolean equals$extension(final int $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof LiteralIntTruncatedDivisionOps) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var10000;
      if (var3) {
         int var5 = ((LiteralIntTruncatedDivisionOps)x$1).lhs();
         if ($this == var5) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralIntTruncatedDivisionOps$() {
   }
}
