package breeze.optimize.proximal;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ProjectHyperPlane$ extends AbstractFunction2 implements Serializable {
   public static final ProjectHyperPlane$ MODULE$ = new ProjectHyperPlane$();

   public final String toString() {
      return "ProjectHyperPlane";
   }

   public ProjectHyperPlane apply(final DenseVector a, final double b) {
      return new ProjectHyperPlane(a, b);
   }

   public Option unapply(final ProjectHyperPlane x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.a(), BoxesRunTime.boxToDouble(x$0.b()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProjectHyperPlane$.class);
   }

   private ProjectHyperPlane$() {
   }
}
