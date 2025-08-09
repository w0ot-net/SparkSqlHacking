package breeze.stats.distributions;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Pareto$ implements Serializable {
   public static final Pareto$ MODULE$ = new Pareto$();

   public final String toString() {
      return "Pareto";
   }

   public Pareto apply(final double scale, final double shape, final RandBasis rand) {
      return new Pareto(scale, shape, rand);
   }

   public Option unapply(final Pareto x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.scale(), x$0.shape())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Pareto$.class);
   }

   private Pareto$() {
   }
}
