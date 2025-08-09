package breeze.stats.distributions;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class HypergeometricDistribution$ implements Serializable {
   public static final HypergeometricDistribution$ MODULE$ = new HypergeometricDistribution$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(HypergeometricDistribution$.class);
   }

   private HypergeometricDistribution$() {
   }
}
