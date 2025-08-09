package spire.syntax;

import algebra.ring.TruncatedDivision;
import scala.Tuple2;
import spire.math.ConvertableTo;

public final class LiteralLongTruncatedDivisionOps$ {
   public static final LiteralLongTruncatedDivisionOps$ MODULE$ = new LiteralLongTruncatedDivisionOps$();

   public final Object tquot$extension(final long $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.tquot(c.fromLong($this), rhs);
   }

   public final Object tmod$extension(final long $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.tmod(c.fromLong($this), rhs);
   }

   public final Tuple2 tquotmod$extension(final long $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.tquotmod(c.fromLong($this), rhs);
   }

   public final Object fquot$extension(final long $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.fquot(c.fromLong($this), rhs);
   }

   public final Object fmod$extension(final long $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.fmod(c.fromLong($this), rhs);
   }

   public final Tuple2 fquotmod$extension(final long $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return ev.fquotmod(c.fromLong($this), rhs);
   }

   public final int hashCode$extension(final long $this) {
      return Long.hashCode($this);
   }

   public final boolean equals$extension(final long $this, final Object x$1) {
      boolean var4;
      if (x$1 instanceof LiteralLongTruncatedDivisionOps) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var10000;
      if (var4) {
         long var6 = ((LiteralLongTruncatedDivisionOps)x$1).lhs();
         if ($this == var6) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralLongTruncatedDivisionOps$() {
   }
}
