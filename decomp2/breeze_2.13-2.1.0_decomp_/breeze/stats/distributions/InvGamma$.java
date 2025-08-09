package breeze.stats.distributions;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class InvGamma$ implements Serializable {
   public static final InvGamma$ MODULE$ = new InvGamma$();

   public final String toString() {
      return "InvGamma";
   }

   public InvGamma apply(final double shape, final double scale, final RandBasis basis) {
      return new InvGamma(shape, scale, basis);
   }

   public Option unapply(final InvGamma x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.shape(), x$0.scale())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(InvGamma$.class);
   }

   private InvGamma$() {
   }
}
