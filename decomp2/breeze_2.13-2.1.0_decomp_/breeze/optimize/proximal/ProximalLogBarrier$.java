package breeze.optimize.proximal;

import java.io.Serializable;
import scala.runtime.AbstractFunction0;
import scala.runtime.ModuleSerializationProxy;

public final class ProximalLogBarrier$ extends AbstractFunction0 implements Serializable {
   public static final ProximalLogBarrier$ MODULE$ = new ProximalLogBarrier$();

   public final String toString() {
      return "ProximalLogBarrier";
   }

   public ProximalLogBarrier apply() {
      return new ProximalLogBarrier();
   }

   public boolean unapply(final ProximalLogBarrier x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProximalLogBarrier$.class);
   }

   private ProximalLogBarrier$() {
   }
}
