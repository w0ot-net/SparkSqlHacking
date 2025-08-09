package breeze.optimize.proximal;

import java.io.Serializable;
import scala.runtime.AbstractFunction0;
import scala.runtime.ModuleSerializationProxy;

public final class ProjectPos$ extends AbstractFunction0 implements Serializable {
   public static final ProjectPos$ MODULE$ = new ProjectPos$();

   public final String toString() {
      return "ProjectPos";
   }

   public ProjectPos apply() {
      return new ProjectPos();
   }

   public boolean unapply(final ProjectPos x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProjectPos$.class);
   }

   private ProjectPos$() {
   }
}
