package breeze.stats.distributions;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ZipfDistribution$ extends AbstractFunction2 implements Serializable {
   public static final ZipfDistribution$ MODULE$ = new ZipfDistribution$();

   public final String toString() {
      return "ZipfDistribution";
   }

   public ZipfDistribution apply(final int numberOfElements, final double exponent) {
      return new ZipfDistribution(numberOfElements, exponent);
   }

   public Option unapply(final ZipfDistribution x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcID.sp(x$0.numberOfElements(), x$0.exponent())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ZipfDistribution$.class);
   }

   private ZipfDistribution$() {
   }
}
