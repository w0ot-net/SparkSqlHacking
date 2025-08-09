package breeze.optimize.proximal;

import java.io.Serializable;
import scala.runtime.AbstractFunction0;
import scala.runtime.ModuleSerializationProxy;

public final class ProjectIdentity$ extends AbstractFunction0 implements Serializable {
   public static final ProjectIdentity$ MODULE$ = new ProjectIdentity$();

   public final String toString() {
      return "ProjectIdentity";
   }

   public ProjectIdentity apply() {
      return new ProjectIdentity();
   }

   public boolean unapply(final ProjectIdentity x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProjectIdentity$.class);
   }

   private ProjectIdentity$() {
   }
}
