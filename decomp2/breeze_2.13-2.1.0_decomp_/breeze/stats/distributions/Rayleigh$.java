package breeze.stats.distributions;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Rayleigh$ implements Serializable {
   public static final Rayleigh$ MODULE$ = new Rayleigh$();

   public final String toString() {
      return "Rayleigh";
   }

   public Rayleigh apply(final double scale, final RandBasis rand) {
      return new Rayleigh(scale, rand);
   }

   public Option unapply(final Rayleigh x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToDouble(x$0.scale())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Rayleigh$.class);
   }

   private Rayleigh$() {
   }
}
