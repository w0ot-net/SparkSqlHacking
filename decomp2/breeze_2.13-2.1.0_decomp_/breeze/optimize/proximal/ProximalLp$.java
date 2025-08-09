package breeze.optimize.proximal;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ProximalLp$ extends AbstractFunction1 implements Serializable {
   public static final ProximalLp$ MODULE$ = new ProximalLp$();

   public final String toString() {
      return "ProximalLp";
   }

   public ProximalLp apply(final DenseVector c) {
      return new ProximalLp(c);
   }

   public Option unapply(final ProximalLp x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.c()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProximalLp$.class);
   }

   private ProximalLp$() {
   }
}
