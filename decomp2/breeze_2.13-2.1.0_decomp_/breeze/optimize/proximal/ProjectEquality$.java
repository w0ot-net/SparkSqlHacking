package breeze.optimize.proximal;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ProjectEquality$ extends AbstractFunction2 implements Serializable {
   public static final ProjectEquality$ MODULE$ = new ProjectEquality$();

   public final String toString() {
      return "ProjectEquality";
   }

   public ProjectEquality apply(final DenseMatrix Aeq, final DenseVector beq) {
      return new ProjectEquality(Aeq, beq);
   }

   public Option unapply(final ProjectEquality x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.Aeq(), x$0.beq())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProjectEquality$.class);
   }

   private ProjectEquality$() {
   }
}
