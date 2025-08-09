package breeze.stats.distributions;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Wald$ implements Serializable {
   public static final Wald$ MODULE$ = new Wald$();

   public final String toString() {
      return "Wald";
   }

   public Wald apply(final double mean, final double shape, final RandBasis rand) {
      return new Wald(mean, shape, rand);
   }

   public Option unapply(final Wald x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.mean(), x$0.shape())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Wald$.class);
   }

   private Wald$() {
   }
}
