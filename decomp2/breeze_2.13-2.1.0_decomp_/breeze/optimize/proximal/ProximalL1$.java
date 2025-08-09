package breeze.optimize.proximal;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ProximalL1$ extends AbstractFunction1 implements Serializable {
   public static final ProximalL1$ MODULE$ = new ProximalL1$();

   public double $lessinit$greater$default$1() {
      return (double)1.0F;
   }

   public final String toString() {
      return "ProximalL1";
   }

   public ProximalL1 apply(final double lambda) {
      return new ProximalL1(lambda);
   }

   public double apply$default$1() {
      return (double)1.0F;
   }

   public Option unapply(final ProximalL1 x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToDouble(x$0.lambda())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProximalL1$.class);
   }

   private ProximalL1$() {
   }
}
