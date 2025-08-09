package breeze.optimize.linear;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class InfeasibleProblem$ extends AbstractFunction1 implements Serializable {
   public static final InfeasibleProblem$ MODULE$ = new InfeasibleProblem$();

   public final String toString() {
      return "InfeasibleProblem";
   }

   public InfeasibleProblem apply(final LinearProgram.Problem prob) {
      return new InfeasibleProblem(prob);
   }

   public Option unapply(final InfeasibleProblem x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.prob()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(InfeasibleProblem$.class);
   }

   private InfeasibleProblem$() {
   }
}
