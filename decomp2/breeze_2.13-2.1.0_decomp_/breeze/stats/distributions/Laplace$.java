package breeze.stats.distributions;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Laplace$ implements Serializable {
   public static final Laplace$ MODULE$ = new Laplace$();

   public final String toString() {
      return "Laplace";
   }

   public Laplace apply(final double location, final double scale, final RandBasis rand) {
      return new Laplace(location, scale, rand);
   }

   public Option unapply(final Laplace x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.location(), x$0.scale())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Laplace$.class);
   }

   private Laplace$() {
   }
}
