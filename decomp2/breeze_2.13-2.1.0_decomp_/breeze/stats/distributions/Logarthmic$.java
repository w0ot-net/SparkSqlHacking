package breeze.stats.distributions;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Logarthmic$ implements Serializable {
   public static final Logarthmic$ MODULE$ = new Logarthmic$();

   public final String toString() {
      return "Logarthmic";
   }

   public Logarthmic apply(final double p, final RandBasis rand) {
      return new Logarthmic(p, rand);
   }

   public Option unapply(final Logarthmic x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToDouble(x$0.p())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Logarthmic$.class);
   }

   private Logarthmic$() {
   }
}
