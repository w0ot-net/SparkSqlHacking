package org.apache.spark.ml.optim.aggregator;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import scala.Array.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000594\u0011\"\u0004\b\u0011\u0002\u0007\u0005!CG'\t\u000b=\u0002A\u0011\u0001\u0019\t\u000fQ\u0002\u0001\u0019!C\tk!9\u0011\b\u0001a\u0001\n#Q\u0004bB\u001f\u0001\u0001\u0004%\t\"\u000e\u0005\b}\u0001\u0001\r\u0011\"\u0005@\u0011\u001d\t\u0005A1A\u0007\u0012\tC\u0001B\u0012\u0001\t\u0006\u0004%\tb\u0012\u0005\u0006\u0017\u00021\t\u0001\u0014\u0005\u0006A\u0002!\t!\u0019\u0005\u0006I\u0002!\t!\u001a\u0005\u0006Y\u0002!\t!\u000e\u0005\u0006[\u0002!\t!\u000e\u0002\u001d\t&4g-\u001a:f]RL\u0017M\u00197f\u0019>\u001c8/Q4he\u0016<\u0017\r^8s\u0015\ty\u0001#\u0001\u0006bO\u001e\u0014XmZ1u_JT!!\u0005\n\u0002\u000b=\u0004H/[7\u000b\u0005M!\u0012AA7m\u0015\t)b#A\u0003ta\u0006\u00148N\u0003\u0002\u00181\u00051\u0011\r]1dQ\u0016T\u0011!G\u0001\u0004_J<WcA\u000eY\u001fN\u0019\u0001\u0001\b\u0012\u0011\u0005u\u0001S\"\u0001\u0010\u000b\u0003}\tQa]2bY\u0006L!!\t\u0010\u0003\r\u0005s\u0017PU3g!\t\u0019CF\u0004\u0002%U9\u0011Q%K\u0007\u0002M)\u0011q\u0005K\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tq$\u0003\u0002,=\u00059\u0001/Y2lC\u001e,\u0017BA\u0017/\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tYc$\u0001\u0004%S:LG\u000f\n\u000b\u0002cA\u0011QDM\u0005\u0003gy\u0011A!\u00168ji\u0006Iq/Z5hQR\u001cV/\\\u000b\u0002mA\u0011QdN\u0005\u0003qy\u0011a\u0001R8vE2,\u0017!D<fS\u001eDGoU;n?\u0012*\u0017\u000f\u0006\u00022w!9AhAA\u0001\u0002\u00041\u0014a\u0001=%c\u00059An\\:t'Vl\u0017a\u00037pgN\u001cV/\\0%KF$\"!\r!\t\u000fq*\u0011\u0011!a\u0001m\u0005\u0019A-[7\u0016\u0003\r\u0003\"!\b#\n\u0005\u0015s\"aA%oi\u0006\u0001rM]1eS\u0016tGoU;n\u0003J\u0014\u0018-_\u000b\u0002\u0011B\u0019Q$\u0013\u001c\n\u0005)s\"!B!se\u0006L\u0018aA1eIR\u0011QJ\u0018\t\u0003\u001d>c\u0001\u0001B\u0003Q\u0001\t\u0007\u0011KA\u0002BO\u001e\f\"AU+\u0011\u0005u\u0019\u0016B\u0001+\u001f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004BA\u0016\u0001X\u001b6\ta\u0002\u0005\u0002O1\u0012)\u0011\f\u0001b\u00015\n)A)\u0019;v[F\u0011!k\u0017\t\u0003;qK!!\u0018\u0010\u0003\u0007\u0005s\u0017\u0010C\u0003`\u0011\u0001\u0007q+\u0001\u0005j]N$\u0018M\\2f\u0003\u0015iWM]4f)\ti%\rC\u0003d\u0013\u0001\u0007Q*A\u0003pi\",'/\u0001\u0005he\u0006$\u0017.\u001a8u+\u00051\u0007CA4k\u001b\u0005A'BA5\u0013\u0003\u0019a\u0017N\\1mO&\u00111\u000e\u001b\u0002\u0007-\u0016\u001cGo\u001c:\u0002\r],\u0017n\u001a5u\u0003\u0011awn]:"
)
public interface DifferentiableLossAggregator extends Serializable {
   double weightSum();

   void weightSum_$eq(final double x$1);

   double lossSum();

   void lossSum_$eq(final double x$1);

   int dim();

   // $FF: synthetic method
   static double[] gradientSumArray$(final DifferentiableLossAggregator $this) {
      return $this.gradientSumArray();
   }

   default double[] gradientSumArray() {
      return (double[]).MODULE$.ofDim(this.dim(), scala.reflect.ClassTag..MODULE$.Double());
   }

   DifferentiableLossAggregator add(final Object instance);

   // $FF: synthetic method
   static DifferentiableLossAggregator merge$(final DifferentiableLossAggregator $this, final DifferentiableLossAggregator other) {
      return $this.merge(other);
   }

   default DifferentiableLossAggregator merge(final DifferentiableLossAggregator other) {
      scala.Predef..MODULE$.require(this.dim() == other.dim(), () -> {
         String var10000 = this.getClass().getSimpleName();
         return "Dimensions mismatch when merging with another " + var10000 + ". Expecting " + this.dim() + " but got " + other.dim() + ".";
      });
      if (other.weightSum() != (double)0) {
         this.weightSum_$eq(this.weightSum() + other.weightSum());
         this.lossSum_$eq(this.lossSum() + other.lossSum());
         org.apache.spark.ml.linalg.BLAS..MODULE$.getBLAS(this.dim()).daxpy(this.dim(), (double)1.0F, other.gradientSumArray(), 1, this.gradientSumArray(), 1);
      }

      return this;
   }

   // $FF: synthetic method
   static Vector gradient$(final DifferentiableLossAggregator $this) {
      return $this.gradient();
   }

   default Vector gradient() {
      scala.Predef..MODULE$.require(this.weightSum() > (double)0.0F, () -> "The effective number of instances should be greater than 0.0, but was " + this.weightSum() + ".");
      Vector result = org.apache.spark.ml.linalg.Vectors..MODULE$.dense((double[])this.gradientSumArray().clone());
      org.apache.spark.ml.linalg.BLAS..MODULE$.scal((double)1.0F / this.weightSum(), result);
      return result;
   }

   // $FF: synthetic method
   static double weight$(final DifferentiableLossAggregator $this) {
      return $this.weight();
   }

   default double weight() {
      return this.weightSum();
   }

   // $FF: synthetic method
   static double loss$(final DifferentiableLossAggregator $this) {
      return $this.loss();
   }

   default double loss() {
      scala.Predef..MODULE$.require(this.weightSum() > (double)0.0F, () -> "The effective number of instances should be greater than 0.0, but was " + this.weightSum() + ".");
      return this.lossSum() / this.weightSum();
   }

   static void $init$(final DifferentiableLossAggregator $this) {
      $this.weightSum_$eq((double)0.0F);
      $this.lossSum_$eq((double)0.0F);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
