package org.apache.spark.ml.ann;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import java.lang.invoke.SerializedLambda;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005M3Q\u0001C\u0005\u0001\u0013MAQ!\t\u0001\u0005\u0002\rBq!\n\u0001C\u0002\u0013\u0005a\u0005\u0003\u00043\u0001\u0001\u0006Ia\n\u0005\u0006g\u0001!\t\u0005\u000e\u0005\u0006\u007f\u0001!\t\u0005\u0011\u0005\u0006\u000f\u0002!\t\u0005\u0013\u0005\u0006\u001b\u0002!\tE\u0014\u0002&'>4G/\\1y\u0019\u0006LXM]'pI\u0016dw+\u001b;i\u0007J|7o]#oiJ|\u0007/\u001f'pgNT!AC\u0006\u0002\u0007\u0005tgN\u0003\u0002\r\u001b\u0005\u0011Q\u000e\u001c\u0006\u0003\u001d=\tQa\u001d9be.T!\u0001E\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0012aA8sON!\u0001\u0001\u0006\u000e\u001f!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fMB\u00111\u0004H\u0007\u0002\u0013%\u0011Q$\u0003\u0002\u000b\u0019\u0006LXM]'pI\u0016d\u0007CA\u000e \u0013\t\u0001\u0013B\u0001\u0007M_N\u001ch)\u001e8di&|g.\u0001\u0004=S:LGOP\u0002\u0001)\u0005!\u0003CA\u000e\u0001\u0003\u001d9X-[4iiN,\u0012a\n\t\u0004Q5zS\"A\u0015\u000b\u0005)Z\u0013A\u00027j]\u0006dwMC\u0001-\u0003\u0019\u0011'/Z3{K&\u0011a&\u000b\u0002\f\t\u0016t7/\u001a,fGR|'\u000f\u0005\u0002\u0016a%\u0011\u0011G\u0006\u0002\u0007\t>,(\r\\3\u0002\u0011],\u0017n\u001a5ug\u0002\nA!\u001a<bYR\u0019Q\u0007O\u001f\u0011\u0005U1\u0014BA\u001c\u0017\u0005\u0011)f.\u001b;\t\u000be\"\u0001\u0019\u0001\u001e\u0002\t\u0011\fG/\u0019\t\u0004Qmz\u0013B\u0001\u001f*\u0005-!UM\\:f\u001b\u0006$(/\u001b=\t\u000by\"\u0001\u0019\u0001\u001e\u0002\r=,H\u000f];u\u0003A\u0019w.\u001c9vi\u0016\u0004&/\u001a<EK2$\u0018\r\u0006\u00036\u0003\u000e+\u0005\"\u0002\"\u0006\u0001\u0004Q\u0014!\u00038fqR$U\r\u001c;b\u0011\u0015!U\u00011\u0001;\u0003\u0015Ig\u000e];u\u0011\u00151U\u00011\u0001;\u0003\u0015!W\r\u001c;b\u0003\u00119'/\u00193\u0015\tUJ%j\u0013\u0005\u0006\r\u001a\u0001\rA\u000f\u0005\u0006\t\u001a\u0001\rA\u000f\u0005\u0006\u0019\u001a\u0001\raJ\u0001\bGVlwI]1e\u0003\u0011awn]:\u0015\t=z\u0005K\u0015\u0005\u0006}\u001d\u0001\rA\u000f\u0005\u0006#\u001e\u0001\rAO\u0001\u0007i\u0006\u0014x-\u001a;\t\u000b\u0019;\u0001\u0019\u0001\u001e"
)
public class SoftmaxLayerModelWithCrossEntropyLoss implements LayerModel, LossFunction {
   private final DenseVector weights;

   public DenseVector weights() {
      return this.weights;
   }

   public void eval(final DenseMatrix data, final DenseMatrix output) {
      for(int j = 0; j < data.cols(); ++j) {
         int i = 0;

         double max;
         for(max = -Double.MAX_VALUE; i < data.rows(); ++i) {
            if (data.apply$mcD$sp(i, j) > max) {
               max = data.apply$mcD$sp(i, j);
            }
         }

         double sum = (double)0.0F;

         for(int var13 = 0; var13 < data.rows(); ++var13) {
            double res = .MODULE$.exp(data.apply$mcD$sp(var13, j) - max);
            output.update$mcD$sp(var13, j, res);
            sum += res;
         }

         for(int var14 = 0; var14 < data.rows(); ++var14) {
            output.update$mcD$sp(var14, j, output.apply$mcD$sp(var14, j) / sum);
         }
      }

   }

   public void computePrevDelta(final DenseMatrix nextDelta, final DenseMatrix input, final DenseMatrix delta) {
   }

   public void grad(final DenseMatrix delta, final DenseMatrix input, final DenseVector cumGrad) {
   }

   public double loss(final DenseMatrix output, final DenseMatrix target, final DenseMatrix delta) {
      ApplyInPlace$.MODULE$.apply(output, target, delta, (JFunction2.mcDDD.sp)(o, t) -> o - t);
      return -BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply(target.$times$colon$times(breeze.numerics.package.log..MODULE$.apply(output, breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseMatrix..MODULE$.scalarOf(), breeze.numerics.package.log.logDoubleImpl..MODULE$, breeze.linalg.operators.HasOps..MODULE$.canMapValues_DM$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double()))), breeze.linalg.operators.HasOps..MODULE$.op_DM_DM_Double_OpMulScalar()), breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.canTraverseValues()))) / (double)output.cols();
   }

   public SoftmaxLayerModelWithCrossEntropyLoss() {
      this.weights = new DenseVector.mcD.sp(0, scala.reflect.ClassTag..MODULE$.Double());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
