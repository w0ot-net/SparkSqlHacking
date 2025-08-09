package breeze.optimize.proximal;

import java.io.Serializable;
import scala.runtime.AbstractFunction0;
import scala.runtime.ModuleSerializationProxy;

public final class ProximalHuber$ extends AbstractFunction0 implements Serializable {
   public static final ProximalHuber$ MODULE$ = new ProximalHuber$();

   public final String toString() {
      return "ProximalHuber";
   }

   public ProximalHuber apply() {
      return new ProximalHuber();
   }

   public boolean unapply(final ProximalHuber x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProximalHuber$.class);
   }

   private ProximalHuber$() {
   }
}
