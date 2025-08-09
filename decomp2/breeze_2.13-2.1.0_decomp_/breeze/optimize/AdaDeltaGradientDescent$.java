package breeze.optimize;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class AdaDeltaGradientDescent$ implements Serializable {
   public static final AdaDeltaGradientDescent$ MODULE$ = new AdaDeltaGradientDescent$();

   public double $lessinit$greater$default$3() {
      return 1.0E-5;
   }

   public double $lessinit$greater$default$4() {
      return 1.0E-4;
   }

   public int $lessinit$greater$default$5() {
      return 50;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AdaDeltaGradientDescent$.class);
   }

   private AdaDeltaGradientDescent$() {
   }
}
