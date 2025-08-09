package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public class Algebraic$Expr$Sub$ extends AbstractFunction2 implements Serializable {
   public static final Algebraic$Expr$Sub$ MODULE$ = new Algebraic$Expr$Sub$();

   public final String toString() {
      return "Sub";
   }

   public Algebraic$Expr$Sub apply(final Algebraic.Expr lhs, final Algebraic.Expr rhs) {
      return new Algebraic$Expr$Sub(lhs, rhs);
   }

   public Option unapply(final Algebraic$Expr$Sub x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.lhs(), x$0.rhs())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Algebraic$Expr$Sub$.class);
   }
}
