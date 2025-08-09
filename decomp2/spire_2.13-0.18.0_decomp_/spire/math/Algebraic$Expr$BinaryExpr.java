package spire.math;

import scala.collection.immutable.List;
import scala.package.;

public abstract class Algebraic$Expr$BinaryExpr extends Algebraic.Expr {
   private final int flagBits;

   public abstract Algebraic.Expr lhs();

   public abstract Algebraic.Expr rhs();

   public int flagBits() {
      return this.flagBits;
   }

   public List children() {
      Algebraic.Expr var1 = this.lhs();
      Algebraic.Expr var2 = this.rhs();
      return .MODULE$.Nil().$colon$colon(var2).$colon$colon(var1);
   }

   public Algebraic$Expr$BinaryExpr() {
      this.flagBits = Algebraic$Expr$Flags$.MODULE$.$bar$extension(this.lhs().flags(), this.rhs().flags());
   }
}
