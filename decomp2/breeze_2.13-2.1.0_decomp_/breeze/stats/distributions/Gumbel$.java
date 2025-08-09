package breeze.stats.distributions;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Gumbel$ implements Serializable {
   public static final Gumbel$ MODULE$ = new Gumbel$();

   public final String toString() {
      return "Gumbel";
   }

   public Gumbel apply(final double location, final double scale, final RandBasis rand) {
      return new Gumbel(location, scale, rand);
   }

   public Option unapply(final Gumbel x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.location(), x$0.scale())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Gumbel$.class);
   }

   private Gumbel$() {
   }
}
