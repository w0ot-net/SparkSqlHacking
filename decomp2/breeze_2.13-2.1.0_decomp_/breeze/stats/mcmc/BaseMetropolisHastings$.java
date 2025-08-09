package breeze.stats.mcmc;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class BaseMetropolisHastings$ implements Serializable {
   public static final BaseMetropolisHastings$ MODULE$ = new BaseMetropolisHastings$();

   public int $lessinit$greater$default$3() {
      return 0;
   }

   public int $lessinit$greater$default$4() {
      return 0;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BaseMetropolisHastings$.class);
   }

   private BaseMetropolisHastings$() {
   }
}
