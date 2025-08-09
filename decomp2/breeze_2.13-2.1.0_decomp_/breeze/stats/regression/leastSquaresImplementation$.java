package breeze.stats.regression;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$mcD$sp;
import dev.ludovic.netlib.lapack.LAPACK;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import org.netlib.util.intW;
import scala.Predef.;
import scala.runtime.DoubleRef;
import scala.runtime.java8.JFunction1;

public final class leastSquaresImplementation$ {
   public static final leastSquaresImplementation$ MODULE$ = new leastSquaresImplementation$();

   public LeastSquaresRegressionResult doLeastSquares(final DenseMatrix data, final DenseVector outputs, final double[] workArray) {
      .MODULE$.require(data.rows() == outputs.size());
      .MODULE$.require(data.rows() > data.cols() + 1);
      .MODULE$.require(workArray.length >= 2 * data.rows() * data.cols());
      intW info = new intW(0);
      LAPACK.getInstance().dgels("N", data.rows(), data.cols(), 1, data.data$mcD$sp(), data.rows(), outputs.data$mcD$sp(), data.rows(), workArray, workArray.length, info);
      if (info.val < 0) {
         throw new ArithmeticException("Least squares did not converge.");
      } else {
         DenseVector coefficients = new DenseVector$mcD$sp(Arrays.copyOf(outputs.data$mcD$sp(), data.cols()));
         DoubleRef r2 = DoubleRef.create((double)0.0F);
         scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), data.rows() - data.cols()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> r2.elem += scala.math.package..MODULE$.pow(outputs.data$mcD$sp()[data.cols() + i], (double)2.0F));
         return new LeastSquaresRegressionResult(coefficients, r2.elem);
      }
   }

   private leastSquaresImplementation$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
