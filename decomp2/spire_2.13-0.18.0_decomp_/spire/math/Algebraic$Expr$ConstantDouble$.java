package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public class Algebraic$Expr$ConstantDouble$ extends AbstractFunction1 implements Serializable {
   public static final Algebraic$Expr$ConstantDouble$ MODULE$ = new Algebraic$Expr$ConstantDouble$();

   public final String toString() {
      return "ConstantDouble";
   }

   public Algebraic$Expr$ConstantDouble apply(final double value) {
      return new Algebraic$Expr$ConstantDouble(value);
   }

   public Option unapply(final Algebraic$Expr$ConstantDouble x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToDouble(x$0.value())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Algebraic$Expr$ConstantDouble$.class);
   }
}
