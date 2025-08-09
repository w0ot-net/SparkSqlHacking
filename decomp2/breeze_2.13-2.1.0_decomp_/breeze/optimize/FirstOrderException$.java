package breeze.optimize;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class FirstOrderException$ implements Serializable {
   public static final FirstOrderException$ MODULE$ = new FirstOrderException$();

   public String $lessinit$greater$default$1() {
      return "";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FirstOrderException$.class);
   }

   private FirstOrderException$() {
   }
}
