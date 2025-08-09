package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public class Algebraic$Expr$Neg$ extends AbstractFunction1 implements Serializable {
   public static final Algebraic$Expr$Neg$ MODULE$ = new Algebraic$Expr$Neg$();

   public final String toString() {
      return "Neg";
   }

   public Algebraic$Expr$Neg apply(final Algebraic.Expr sub) {
      return new Algebraic$Expr$Neg(sub);
   }

   public Option unapply(final Algebraic$Expr$Neg x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.sub()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Algebraic$Expr$Neg$.class);
   }
}
