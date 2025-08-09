package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public class Algebraic$Expr$ConstantRoot$ extends AbstractFunction4 implements Serializable {
   public static final Algebraic$Expr$ConstantRoot$ MODULE$ = new Algebraic$Expr$ConstantRoot$();

   public final String toString() {
      return "ConstantRoot";
   }

   public Algebraic$Expr$ConstantRoot apply(final Polynomial poly, final int i, final Rational lb, final Rational ub) {
      return new Algebraic$Expr$ConstantRoot(poly, i, lb, ub);
   }

   public Option unapply(final Algebraic$Expr$ConstantRoot x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.poly(), BoxesRunTime.boxToInteger(x$0.i()), x$0.lb(), x$0.ub())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Algebraic$Expr$ConstantRoot$.class);
   }
}
