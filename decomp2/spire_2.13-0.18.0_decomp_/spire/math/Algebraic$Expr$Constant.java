package spire.math;

import scala.collection.immutable.List;
import scala.package.;

public abstract class Algebraic$Expr$Constant extends Algebraic.Expr {
   public abstract Object value();

   public List children() {
      return .MODULE$.Nil();
   }
}
