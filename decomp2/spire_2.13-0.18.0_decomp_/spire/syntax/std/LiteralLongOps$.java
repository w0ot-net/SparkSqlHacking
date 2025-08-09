package spire.syntax.std;

import scala.Tuple2;
import scala.math.BigInt;

public final class LiteralLongOps$ {
   public static final LiteralLongOps$ MODULE$ = new LiteralLongOps$();

   public final long $div$tilde$extension(final long $this, final long rhs) {
      return $this / rhs;
   }

   public final Tuple2 $div$percent$extension(final long $this, final long rhs) {
      return new Tuple2.mcJJ.sp($this / rhs, $this % rhs);
   }

   public final long pow$extension(final long $this, final long rhs) {
      return spire.math.package$.MODULE$.pow($this, rhs);
   }

   public final long $times$times$extension(final long $this, final long rhs) {
      return spire.math.package$.MODULE$.pow($this, rhs);
   }

   public final BigInt unary_$bang$extension(final long $this) {
      return spire.math.package$.MODULE$.fact($this);
   }

   public final BigInt choose$extension(final long $this, final long rhs) {
      return spire.math.package$.MODULE$.choose($this, rhs);
   }

   public final int hashCode$extension(final long $this) {
      return Long.hashCode($this);
   }

   public final boolean equals$extension(final long $this, final Object x$1) {
      boolean var4;
      if (x$1 instanceof LiteralLongOps) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var10000;
      if (var4) {
         long var6 = ((LiteralLongOps)x$1).lhs();
         if ($this == var6) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralLongOps$() {
   }
}
