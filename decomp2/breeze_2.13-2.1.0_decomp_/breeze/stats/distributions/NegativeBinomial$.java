package breeze.stats.distributions;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class NegativeBinomial$ implements Serializable {
   public static final NegativeBinomial$ MODULE$ = new NegativeBinomial$();

   public final String toString() {
      return "NegativeBinomial";
   }

   public NegativeBinomial apply(final double r, final double p, final RandBasis rand) {
      return new NegativeBinomial(r, p, rand);
   }

   public Option unapply(final NegativeBinomial x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.r(), x$0.p())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NegativeBinomial$.class);
   }

   private NegativeBinomial$() {
   }
}
