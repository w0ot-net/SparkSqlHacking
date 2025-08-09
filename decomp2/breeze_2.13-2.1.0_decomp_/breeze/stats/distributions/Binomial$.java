package breeze.stats.distributions;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Binomial$ implements Serializable {
   public static final Binomial$ MODULE$ = new Binomial$();

   public final String toString() {
      return "Binomial";
   }

   public Binomial apply(final int n, final double p, final RandBasis rand) {
      return new Binomial(n, p, rand);
   }

   public Option unapply(final Binomial x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcID.sp(x$0.n(), x$0.p())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Binomial$.class);
   }

   private Binomial$() {
   }
}
