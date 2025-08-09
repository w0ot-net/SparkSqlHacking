package breeze.linalg;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class LapackException$ implements Serializable {
   public static final LapackException$ MODULE$ = new LapackException$();

   public String $lessinit$greater$default$1() {
      return "";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LapackException$.class);
   }

   private LapackException$() {
   }
}
