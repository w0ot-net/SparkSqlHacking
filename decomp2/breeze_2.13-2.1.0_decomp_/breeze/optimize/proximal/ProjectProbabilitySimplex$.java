package breeze.optimize.proximal;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ProjectProbabilitySimplex$ extends AbstractFunction1 implements Serializable {
   public static final ProjectProbabilitySimplex$ MODULE$ = new ProjectProbabilitySimplex$();

   public final String toString() {
      return "ProjectProbabilitySimplex";
   }

   public ProjectProbabilitySimplex apply(final double s) {
      return new ProjectProbabilitySimplex(s);
   }

   public Option unapply(final ProjectProbabilitySimplex x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToDouble(x$0.s())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProjectProbabilitySimplex$.class);
   }

   private ProjectProbabilitySimplex$() {
   }
}
