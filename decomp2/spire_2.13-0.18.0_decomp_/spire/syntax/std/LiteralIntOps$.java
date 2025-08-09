package spire.syntax.std;

import scala.Tuple2;
import scala.math.BigInt;

public final class LiteralIntOps$ {
   public static final LiteralIntOps$ MODULE$ = new LiteralIntOps$();

   public final int $div$tilde$extension(final int $this, final int rhs) {
      return $this / rhs;
   }

   public final Tuple2 $div$percent$extension(final int $this, final int rhs) {
      return new Tuple2.mcII.sp($this / rhs, $this % rhs);
   }

   public final int pow$extension(final int $this, final int rhs) {
      return (int)Math.pow((double)$this, (double)rhs);
   }

   public final int $times$times$extension(final int $this, final int rhs) {
      return (int)Math.pow((double)$this, (double)rhs);
   }

   public final BigInt unary_$bang$extension(final int $this) {
      return spire.math.package$.MODULE$.fact((long)$this);
   }

   public final BigInt choose$extension(final int $this, final int rhs) {
      return spire.math.package$.MODULE$.choose((long)$this, (long)rhs);
   }

   public final int hashCode$extension(final int $this) {
      return Integer.hashCode($this);
   }

   public final boolean equals$extension(final int $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof LiteralIntOps) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var10000;
      if (var3) {
         int var5 = ((LiteralIntOps)x$1).lhs();
         if ($this == var5) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralIntOps$() {
   }
}
