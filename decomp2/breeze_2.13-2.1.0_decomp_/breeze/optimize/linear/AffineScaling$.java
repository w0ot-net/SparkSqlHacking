package breeze.optimize.linear;

import scala.runtime.ModuleSerializationProxy;

public final class AffineScaling$ extends AffineScaling {
   public static final AffineScaling$ MODULE$ = new AffineScaling$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(AffineScaling$.class);
   }

   private AffineScaling$() {
   }
}
