package spire.math;

import scala.collection.immutable.List;
import scala.package.;

public abstract class Algebraic$Expr$UnaryExpr extends Algebraic.Expr {
   public abstract Algebraic.Expr sub();

   public List children() {
      Algebraic.Expr var1 = this.sub();
      return .MODULE$.Nil().$colon$colon(var1);
   }
}
