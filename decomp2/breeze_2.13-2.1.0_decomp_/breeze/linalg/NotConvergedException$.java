package breeze.linalg;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class NotConvergedException$ implements Serializable {
   public static final NotConvergedException$ MODULE$ = new NotConvergedException$();

   public String $lessinit$greater$default$2() {
      return "";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NotConvergedException$.class);
   }

   private NotConvergedException$() {
   }
}
