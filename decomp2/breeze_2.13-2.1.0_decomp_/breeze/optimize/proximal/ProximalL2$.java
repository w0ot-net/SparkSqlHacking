package breeze.optimize.proximal;

import java.io.Serializable;
import scala.runtime.AbstractFunction0;
import scala.runtime.ModuleSerializationProxy;

public final class ProximalL2$ extends AbstractFunction0 implements Serializable {
   public static final ProximalL2$ MODULE$ = new ProximalL2$();

   public final String toString() {
      return "ProximalL2";
   }

   public ProximalL2 apply() {
      return new ProximalL2();
   }

   public boolean unapply(final ProximalL2 x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProximalL2$.class);
   }

   private ProximalL2$() {
   }
}
