package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public class Algebraic$Expr$ConstantRational$ extends AbstractFunction1 implements Serializable {
   public static final Algebraic$Expr$ConstantRational$ MODULE$ = new Algebraic$Expr$ConstantRational$();

   public final String toString() {
      return "ConstantRational";
   }

   public Algebraic$Expr$ConstantRational apply(final Rational value) {
      return new Algebraic$Expr$ConstantRational(value);
   }

   public Option unapply(final Algebraic$Expr$ConstantRational x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.value()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Algebraic$Expr$ConstantRational$.class);
   }
}
