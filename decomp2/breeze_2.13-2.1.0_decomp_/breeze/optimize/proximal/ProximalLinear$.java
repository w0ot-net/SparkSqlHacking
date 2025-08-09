package breeze.optimize.proximal;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ProximalLinear$ extends AbstractFunction1 implements Serializable {
   public static final ProximalLinear$ MODULE$ = new ProximalLinear$();

   public final String toString() {
      return "ProximalLinear";
   }

   public ProximalLinear apply(final DenseVector c) {
      return new ProximalLinear(c);
   }

   public Option unapply(final ProximalLinear x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.c()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProximalLinear$.class);
   }

   private ProximalLinear$() {
   }
}
