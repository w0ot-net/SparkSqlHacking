package breeze.stats.distributions;

import java.io.Serializable;
import org.apache.commons.math3.random.MersenneTwister;
import scala.runtime.ModuleSerializationProxy;

public final class Rand$ extends RandBasis {
   public static final Rand$ MODULE$ = new Rand$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(Rand$.class);
   }

   private Rand$() {
      super(new ThreadLocalRandomGenerator(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final MersenneTwister apply() {
            return new MersenneTwister();
         }
      }));
   }
}
