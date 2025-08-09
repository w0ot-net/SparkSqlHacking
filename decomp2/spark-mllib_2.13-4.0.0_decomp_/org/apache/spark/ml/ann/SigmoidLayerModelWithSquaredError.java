package org.apache.spark.ml.ann;

import breeze.linalg.DenseMatrix;
import breeze.linalg.sum.;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005A2Qa\u0001\u0003\u0001\t9AQA\u0006\u0001\u0005\u0002aAQA\u0007\u0001\u0005Bm\u0011\u0011eU5h[>LG\rT1zKJlu\u000eZ3m/&$\bnU9vCJ,G-\u0012:s_JT!!\u0002\u0004\u0002\u0007\u0005tgN\u0003\u0002\b\u0011\u0005\u0011Q\u000e\u001c\u0006\u0003\u0013)\tQa\u001d9be.T!a\u0003\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0011aA8sON\u0019\u0001aD\n\u0011\u0005A\tR\"\u0001\u0003\n\u0005I!!\u0001\u0006$v]\u000e$\u0018n\u001c8bY2\u000b\u00170\u001a:N_\u0012,G\u000e\u0005\u0002\u0011)%\u0011Q\u0003\u0002\u0002\r\u0019>\u001c8OR;oGRLwN\\\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t\u0011\u0004\u0005\u0002\u0011\u0001\u0005!An\\:t)\u0011a\"\u0005\f\u0018\u0011\u0005u\u0001S\"\u0001\u0010\u000b\u0003}\tQa]2bY\u0006L!!\t\u0010\u0003\r\u0011{WO\u00197f\u0011\u0015\u0019#\u00011\u0001%\u0003\u0019yW\u000f\u001e9viB\u0019QE\u000b\u000f\u000e\u0003\u0019R!a\n\u0015\u0002\r1Lg.\u00197h\u0015\u0005I\u0013A\u00022sK\u0016TX-\u0003\u0002,M\tYA)\u001a8tK6\u000bGO]5y\u0011\u0015i#\u00011\u0001%\u0003\u0019!\u0018M]4fi\")qF\u0001a\u0001I\u0005)A-\u001a7uC\u0002"
)
public class SigmoidLayerModelWithSquaredError extends FunctionalLayerModel implements LossFunction {
   public double loss(final DenseMatrix output, final DenseMatrix target, final DenseMatrix delta) {
      ApplyInPlace$.MODULE$.apply(output, target, delta, (JFunction2.mcDDD.sp)(o, t) -> o - t);
      double error = BoxesRunTime.unboxToDouble(.MODULE$.apply(delta.$times$colon$times(delta, breeze.linalg.operators.HasOps..MODULE$.op_DM_DM_Double_OpMulScalar()), .MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.canTraverseValues()))) / (double)2 / (double)output.cols();
      ApplyInPlace$.MODULE$.apply(delta, output, delta, (JFunction2.mcDDD.sp)(x, o) -> x * (o - o * o));
      return error;
   }

   public SigmoidLayerModelWithSquaredError() {
      super(new FunctionalLayer(new SigmoidFunction()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
