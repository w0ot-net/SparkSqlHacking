package breeze.optimize.proximal;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ProjectL1$ extends AbstractFunction1 implements Serializable {
   public static final ProjectL1$ MODULE$ = new ProjectL1$();

   public final String toString() {
      return "ProjectL1";
   }

   public ProjectL1 apply(final double s) {
      return new ProjectL1(s);
   }

   public Option unapply(final ProjectL1 x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToDouble(x$0.s())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProjectL1$.class);
   }

   private ProjectL1$() {
   }
}
