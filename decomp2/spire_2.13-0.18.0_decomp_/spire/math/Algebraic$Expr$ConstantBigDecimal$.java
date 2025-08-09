package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.math.BigDecimal;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public class Algebraic$Expr$ConstantBigDecimal$ extends AbstractFunction1 implements Serializable {
   public static final Algebraic$Expr$ConstantBigDecimal$ MODULE$ = new Algebraic$Expr$ConstantBigDecimal$();

   public final String toString() {
      return "ConstantBigDecimal";
   }

   public Algebraic$Expr$ConstantBigDecimal apply(final BigDecimal value) {
      return new Algebraic$Expr$ConstantBigDecimal(value);
   }

   public Option unapply(final Algebraic$Expr$ConstantBigDecimal x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.value()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Algebraic$Expr$ConstantBigDecimal$.class);
   }
}
