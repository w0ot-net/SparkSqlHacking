package breeze.optimize;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class OWLQN$ implements Serializable {
   public static final OWLQN$ MODULE$ = new OWLQN$();

   public double $lessinit$greater$default$4() {
      return 1.0E-8;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OWLQN$.class);
   }

   private OWLQN$() {
   }
}
