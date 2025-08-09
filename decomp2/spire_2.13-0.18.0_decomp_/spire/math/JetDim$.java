package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class JetDim$ extends AbstractFunction1 implements Serializable {
   public static final JetDim$ MODULE$ = new JetDim$();

   public final String toString() {
      return "JetDim";
   }

   public JetDim apply(final int dimension) {
      return new JetDim(dimension);
   }

   public Option unapply(final JetDim x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.dimension())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JetDim$.class);
   }

   private JetDim$() {
   }
}
