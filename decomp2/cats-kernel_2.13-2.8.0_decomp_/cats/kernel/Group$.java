package cats.kernel;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Group$ extends GroupFunctions implements Serializable {
   public static final Group$ MODULE$ = new Group$();

   public final Group apply(final Group ev) {
      return ev;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Group$.class);
   }

   private Group$() {
   }
}
