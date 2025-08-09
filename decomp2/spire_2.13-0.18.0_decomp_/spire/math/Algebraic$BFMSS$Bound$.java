package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public class Algebraic$BFMSS$Bound$ extends AbstractFunction2 implements Serializable {
   public static final Algebraic$BFMSS$Bound$ MODULE$ = new Algebraic$BFMSS$Bound$();

   public final String toString() {
      return "Bound";
   }

   public Algebraic$BFMSS$Bound apply(final long l, final long u) {
      return new Algebraic$BFMSS$Bound(l, u);
   }

   public Option unapply(final Algebraic$BFMSS$Bound x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcJJ.sp(x$0.l(), x$0.u())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Algebraic$BFMSS$Bound$.class);
   }
}
