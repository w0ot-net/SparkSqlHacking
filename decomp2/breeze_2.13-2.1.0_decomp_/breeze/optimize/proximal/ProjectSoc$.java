package breeze.optimize.proximal;

import java.io.Serializable;
import scala.runtime.AbstractFunction0;
import scala.runtime.ModuleSerializationProxy;

public final class ProjectSoc$ extends AbstractFunction0 implements Serializable {
   public static final ProjectSoc$ MODULE$ = new ProjectSoc$();

   public final String toString() {
      return "ProjectSoc";
   }

   public ProjectSoc apply() {
      return new ProjectSoc();
   }

   public boolean unapply(final ProjectSoc x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProjectSoc$.class);
   }

   private ProjectSoc$() {
   }
}
