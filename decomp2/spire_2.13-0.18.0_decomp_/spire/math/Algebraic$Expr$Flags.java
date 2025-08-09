package spire.math;

public final class Algebraic$Expr$Flags {
   private final int bits;

   public int bits() {
      return this.bits;
   }

   public int $bar(final int that) {
      return Algebraic$Expr$Flags$.MODULE$.$bar$extension(this.bits(), that);
   }

   private boolean check(final int n) {
      return Algebraic$Expr$Flags$.MODULE$.check$extension(this.bits(), n);
   }

   public boolean isRational() {
      return Algebraic$Expr$Flags$.MODULE$.isRational$extension(this.bits());
   }

   public boolean isRadical() {
      return Algebraic$Expr$Flags$.MODULE$.isRadical$extension(this.bits());
   }

   public boolean hasDoubleLeaf() {
      return Algebraic$Expr$Flags$.MODULE$.hasDoubleLeaf$extension(this.bits());
   }

   public boolean hasBigDecimalLeaf() {
      return Algebraic$Expr$Flags$.MODULE$.hasBigDecimalLeaf$extension(this.bits());
   }

   public boolean hasRationalLeaf() {
      return Algebraic$Expr$Flags$.MODULE$.hasRationalLeaf$extension(this.bits());
   }

   public int hashCode() {
      return Algebraic$Expr$Flags$.MODULE$.hashCode$extension(this.bits());
   }

   public boolean equals(final Object x$1) {
      return Algebraic$Expr$Flags$.MODULE$.equals$extension(this.bits(), x$1);
   }

   public Algebraic$Expr$Flags(final int bits) {
      this.bits = bits;
   }
}
