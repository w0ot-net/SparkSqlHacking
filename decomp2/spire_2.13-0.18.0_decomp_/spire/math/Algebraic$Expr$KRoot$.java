package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public class Algebraic$Expr$KRoot$ extends AbstractFunction2 implements Serializable {
   public static final Algebraic$Expr$KRoot$ MODULE$ = new Algebraic$Expr$KRoot$();

   public final String toString() {
      return "KRoot";
   }

   public Algebraic$Expr$KRoot apply(final Algebraic.Expr sub, final int k) {
      return new Algebraic$Expr$KRoot(sub, k);
   }

   public Option unapply(final Algebraic$Expr$KRoot x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.sub(), BoxesRunTime.boxToInteger(x$0.k()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Algebraic$Expr$KRoot$.class);
   }
}
