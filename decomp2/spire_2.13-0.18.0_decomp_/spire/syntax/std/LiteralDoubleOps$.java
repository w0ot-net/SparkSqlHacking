package spire.syntax.std;

public final class LiteralDoubleOps$ {
   public static final LiteralDoubleOps$ MODULE$ = new LiteralDoubleOps$();

   public final double pow$extension(final double $this, final double rhs) {
      return spire.math.package$.MODULE$.pow($this, rhs);
   }

   public final double $times$times$extension(final double $this, final double rhs) {
      return spire.math.package$.MODULE$.pow($this, rhs);
   }

   public final int hashCode$extension(final double $this) {
      return Double.hashCode($this);
   }

   public final boolean equals$extension(final double $this, final Object x$1) {
      boolean var4;
      if (x$1 instanceof LiteralDoubleOps) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var10000;
      if (var4) {
         double var6 = ((LiteralDoubleOps)x$1).lhs();
         if ($this == var6) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private LiteralDoubleOps$() {
   }
}
