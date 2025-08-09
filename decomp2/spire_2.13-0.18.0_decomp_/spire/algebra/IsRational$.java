package spire.algebra;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class IsRational$ implements Serializable {
   public static final IsRational$ MODULE$ = new IsRational$();

   public IsRational apply(final IsRational A) {
      return A;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IsRational$.class);
   }

   private IsRational$() {
   }
}
