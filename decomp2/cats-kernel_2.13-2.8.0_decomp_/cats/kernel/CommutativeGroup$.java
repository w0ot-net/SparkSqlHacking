package cats.kernel;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class CommutativeGroup$ extends GroupFunctions implements Serializable {
   public static final CommutativeGroup$ MODULE$ = new CommutativeGroup$();

   public final CommutativeGroup apply(final CommutativeGroup ev) {
      return ev;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CommutativeGroup$.class);
   }

   private CommutativeGroup$() {
   }
}
