package breeze.stats.distributions;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class MultivariateGaussian$ implements Serializable {
   public static final MultivariateGaussian$ MODULE$ = new MultivariateGaussian$();

   public final String toString() {
      return "MultivariateGaussian";
   }

   public MultivariateGaussian apply(final DenseVector mean, final DenseMatrix covariance, final RandBasis rand) {
      return new MultivariateGaussian(mean, covariance, rand);
   }

   public Option unapply(final MultivariateGaussian x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.mean(), x$0.covariance())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MultivariateGaussian$.class);
   }

   private MultivariateGaussian$() {
   }
}
