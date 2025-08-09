package spire.math;

public class Algebraic$Expr$Flags$ {
   public static final Algebraic$Expr$Flags$ MODULE$ = new Algebraic$Expr$Flags$();
   private static final int IntegerLeaf = 0;
   private static final int DoubleLeaf = 2;
   private static final int BigDecimalLeaf = 4;
   private static final int RationalLeaf = 8;
   private static final int IsRadical = 1;

   public final int RadicalFlag() {
      return 1;
   }

   public final int HasDoubleLeaf() {
      return 2;
   }

   public final int HasBigDecimalLeaf() {
      return 4;
   }

   public final int HasRationalLeaf() {
      return 8;
   }

   public final int IntegerLeaf() {
      return IntegerLeaf;
   }

   public final int DoubleLeaf() {
      return DoubleLeaf;
   }

   public final int BigDecimalLeaf() {
      return BigDecimalLeaf;
   }

   public final int RationalLeaf() {
      return RationalLeaf;
   }

   public final int IsRadical() {
      return IsRadical;
   }

   public final int $bar$extension(final int $this, final int that) {
      return $this | that;
   }

   public final boolean check$extension(final int $this, final int n) {
      return ($this & n) != 0;
   }

   public final boolean isRational$extension(final int $this) {
      return !this.isRadical$extension($this);
   }

   public final boolean isRadical$extension(final int $this) {
      return this.check$extension($this, 1);
   }

   public final boolean hasDoubleLeaf$extension(final int $this) {
      return this.check$extension($this, 2);
   }

   public final boolean hasBigDecimalLeaf$extension(final int $this) {
      return this.check$extension($this, 4);
   }

   public final boolean hasRationalLeaf$extension(final int $this) {
      return this.check$extension($this, 8);
   }

   public final int hashCode$extension(final int $this) {
      return Integer.hashCode($this);
   }

   public final boolean equals$extension(final int $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof Algebraic$Expr$Flags) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var10000;
      if (var3) {
         int var5 = ((Algebraic$Expr$Flags)x$1).bits();
         if ($this == var5) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }
}
