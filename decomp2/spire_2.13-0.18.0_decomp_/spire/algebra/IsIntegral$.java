package spire.algebra;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class IsIntegral$ implements Serializable {
   public static final IsIntegral$ MODULE$ = new IsIntegral$();

   public IsIntegral apply(final IsIntegral A) {
      return A;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IsIntegral$.class);
   }

   private IsIntegral$() {
   }
}
