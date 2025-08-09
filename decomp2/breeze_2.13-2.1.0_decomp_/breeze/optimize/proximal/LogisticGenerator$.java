package breeze.optimize.proximal;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.optimize.DiffFunction;
import breeze.stats.distributions.Rand;
import breeze.stats.distributions.Rand$;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ClassTag.;
import scala.runtime.java8.JFunction1;

public final class LogisticGenerator$ {
   public static final LogisticGenerator$ MODULE$ = new LogisticGenerator$();

   public DiffFunction apply(final int ndim) {
      Rand rand = Rand$.MODULE$.gaussian((double)0.0F, (double)1.0F);
      DenseMatrix data = (DenseMatrix)DenseMatrix$.MODULE$.rand(ndim, ndim, rand, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseVector labels = (DenseVector)DenseVector$.MODULE$.rand(ndim, rand, .MODULE$.Double()).map$mcD$sp((JFunction1.mcDD.sp)(x) -> x > (double)0.5F ? (double)1.0F : (double)0.0F, DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double()));
      return new LogisticGenerator.Cost(data, labels);
   }

   private LogisticGenerator$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
