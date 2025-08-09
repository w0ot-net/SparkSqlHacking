package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public class Algebraic$Expr$ConstantLong$ extends AbstractFunction1 implements Serializable {
   public static final Algebraic$Expr$ConstantLong$ MODULE$ = new Algebraic$Expr$ConstantLong$();

   public final String toString() {
      return "ConstantLong";
   }

   public Algebraic$Expr$ConstantLong apply(final long value) {
      return new Algebraic$Expr$ConstantLong(value);
   }

   public Option unapply(final Algebraic$Expr$ConstantLong x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToLong(x$0.value())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Algebraic$Expr$ConstantLong$.class);
   }
}
