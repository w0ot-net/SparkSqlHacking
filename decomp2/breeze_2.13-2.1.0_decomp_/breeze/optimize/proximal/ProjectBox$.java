package breeze.optimize.proximal;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ProjectBox$ extends AbstractFunction2 implements Serializable {
   public static final ProjectBox$ MODULE$ = new ProjectBox$();

   public final String toString() {
      return "ProjectBox";
   }

   public ProjectBox apply(final DenseVector l, final DenseVector u) {
      return new ProjectBox(l, u);
   }

   public Option unapply(final ProjectBox x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.l(), x$0.u())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProjectBox$.class);
   }

   private ProjectBox$() {
   }
}
