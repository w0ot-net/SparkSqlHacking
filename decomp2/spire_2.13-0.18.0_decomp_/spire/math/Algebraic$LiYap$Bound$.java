package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public class Algebraic$LiYap$Bound$ extends AbstractFunction5 implements Serializable {
   public static final Algebraic$LiYap$Bound$ MODULE$ = new Algebraic$LiYap$Bound$();

   public final String toString() {
      return "Bound";
   }

   public Algebraic$LiYap$Bound apply(final long lc, final long tc, final long measure, final long lb, final long ub) {
      return new Algebraic$LiYap$Bound(lc, tc, measure, lb, ub);
   }

   public Option unapply(final Algebraic$LiYap$Bound x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToLong(x$0.lc()), BoxesRunTime.boxToLong(x$0.tc()), BoxesRunTime.boxToLong(x$0.measure()), BoxesRunTime.boxToLong(x$0.lb()), BoxesRunTime.boxToLong(x$0.ub()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Algebraic$LiYap$Bound$.class);
   }
}
