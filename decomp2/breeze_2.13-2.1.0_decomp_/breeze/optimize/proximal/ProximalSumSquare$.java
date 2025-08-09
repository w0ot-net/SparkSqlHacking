package breeze.optimize.proximal;

import java.io.Serializable;
import scala.runtime.AbstractFunction0;
import scala.runtime.ModuleSerializationProxy;

public final class ProximalSumSquare$ extends AbstractFunction0 implements Serializable {
   public static final ProximalSumSquare$ MODULE$ = new ProximalSumSquare$();

   public final String toString() {
      return "ProximalSumSquare";
   }

   public ProximalSumSquare apply() {
      return new ProximalSumSquare();
   }

   public boolean unapply(final ProximalSumSquare x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProximalSumSquare$.class);
   }

   private ProximalSumSquare$() {
   }
}
