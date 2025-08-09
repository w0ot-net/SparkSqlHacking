package org.apache.spark.ml.param.shared;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.param.DoubleArrayParam;
import org.apache.spark.ml.param.Params;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005=2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007I\u0011A\u0012\t\u000b\u001d\u0002A\u0011\u0001\u0015\u0003\u001b!\u000b7\u000f\u00165sKNDw\u000e\u001c3t\u0015\t1q!\u0001\u0004tQ\u0006\u0014X\r\u001a\u0006\u0003\u0011%\tQ\u0001]1sC6T!AC\u0006\u0002\u00055d'B\u0001\u0007\u000e\u0003\u0015\u0019\b/\u0019:l\u0015\tqq\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002!\u0005\u0019qN]4\u0004\u0001M\u0019\u0001aE\r\u0011\u0005Q9R\"A\u000b\u000b\u0003Y\tQa]2bY\u0006L!\u0001G\u000b\u0003\r\u0005s\u0017PU3g!\tQ2$D\u0001\b\u0013\tarA\u0001\u0004QCJ\fWn]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003}\u0001\"\u0001\u0006\u0011\n\u0005\u0005*\"\u0001B+oSR\f!\u0002\u001e5sKNDw\u000e\u001c3t+\u0005!\u0003C\u0001\u000e&\u0013\t1sA\u0001\tE_V\u0014G.Z!se\u0006L\b+\u0019:b[\u0006iq-\u001a;UQJ,7\u000f[8mIN,\u0012!\u000b\t\u0004))b\u0013BA\u0016\u0016\u0005\u0015\t%O]1z!\t!R&\u0003\u0002/+\t1Ai\\;cY\u0016\u0004"
)
public interface HasThresholds extends Params {
   void org$apache$spark$ml$param$shared$HasThresholds$_setter_$thresholds_$eq(final DoubleArrayParam x$1);

   DoubleArrayParam thresholds();

   // $FF: synthetic method
   static double[] getThresholds$(final HasThresholds $this) {
      return $this.getThresholds();
   }

   default double[] getThresholds() {
      return (double[])this.$(this.thresholds());
   }

   // $FF: synthetic method
   static boolean $anonfun$thresholds$1(final double[] t) {
      return .MODULE$.forall$extension(scala.Predef..MODULE$.doubleArrayOps(t), (JFunction1.mcZD.sp)(x$1) -> x$1 >= (double)0) && .MODULE$.count$extension(scala.Predef..MODULE$.doubleArrayOps(t), (JFunction1.mcZD.sp)(x$2) -> x$2 == (double)0) <= 1;
   }

   static void $init$(final HasThresholds $this) {
      $this.org$apache$spark$ml$param$shared$HasThresholds$_setter_$thresholds_$eq(new DoubleArrayParam($this, "thresholds", "Thresholds in multi-class classification to adjust the probability of predicting each class. Array must have length equal to the number of classes, with values > 0 excepting that at most one value may be 0. The class with largest value p/t is predicted, where p is the original probability of that class and t is the class's threshold", (t) -> BoxesRunTime.boxToBoolean($anonfun$thresholds$1(t))));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
