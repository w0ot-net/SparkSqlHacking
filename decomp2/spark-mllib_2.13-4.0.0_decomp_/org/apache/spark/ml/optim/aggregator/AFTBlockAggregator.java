package org.apache.spark.ml.optim.aggregator;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.ml.feature.InstanceBlock;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.DenseVector.;
import scala.Option;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005=4Q\u0001E\t\u0001+uA\u0001b\f\u0001\u0003\u0002\u0003\u0006I!\r\u0005\t{\u0001\u0011\t\u0011)A\u0005}!A\u0011\t\u0001B\u0001B\u0003%!\tC\u0003J\u0001\u0011\u0005!\nC\u0004P\u0001\t\u0007I\u0011\u000b)\t\rQ\u0003\u0001\u0015!\u0003R\u0011\u001d)\u0006A1A\u0005\nACaA\u0016\u0001!\u0002\u0013\t\u0006\u0002C,\u0001\u0011\u000b\u0007I\u0011\u0002-\t\u000fu\u0003!\u0019!C\u0005=\"1q\f\u0001Q\u0001\niB\u0011\u0002\u0019\u0001A\u0002\u0003\u0007I\u0011\u0002-\t\u0013\u0005\u0004\u0001\u0019!a\u0001\n\u0013\u0011\u0007\"\u00035\u0001\u0001\u0004\u0005\t\u0015)\u00038\u0011\u0015Q\u0007\u0001\"\u0001l\u0005I\te\t\u0016\"m_\u000e\\\u0017iZ4sK\u001e\fGo\u001c:\u000b\u0005I\u0019\u0012AC1hOJ,w-\u0019;pe*\u0011A#F\u0001\u0006_B$\u0018.\u001c\u0006\u0003-]\t!!\u001c7\u000b\u0005aI\u0012!B:qCJ\\'B\u0001\u000e\u001c\u0003\u0019\t\u0007/Y2iK*\tA$A\u0002pe\u001e\u001c2\u0001\u0001\u0010%!\ty\"%D\u0001!\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0005\u0019\te.\u001f*fMB!QE\n\u0015/\u001b\u0005\t\u0012BA\u0014\u0012\u0005q!\u0015N\u001a4fe\u0016tG/[1cY\u0016dun]:BO\u001e\u0014XmZ1u_J\u0004\"!\u000b\u0017\u000e\u0003)R!aK\u000b\u0002\u000f\u0019,\u0017\r^;sK&\u0011QF\u000b\u0002\u000e\u0013:\u001cH/\u00198dK\ncwnY6\u0011\u0005\u0015\u0002\u0011\u0001\u00042d'\u000e\fG.\u001a3NK\u0006t7\u0001\u0001\t\u0004eU:T\"A\u001a\u000b\u0005Q:\u0012!\u00032s_\u0006$7-Y:u\u0013\t14GA\u0005Ce>\fGmY1tiB\u0019q\u0004\u000f\u001e\n\u0005e\u0002#!B!se\u0006L\bCA\u0010<\u0013\ta\u0004E\u0001\u0004E_V\u0014G.Z\u0001\rM&$\u0018J\u001c;fe\u000e,\u0007\u000f\u001e\t\u0003?}J!\u0001\u0011\u0011\u0003\u000f\t{w\u000e\\3b]\u0006q!mY\"pK\u001a4\u0017nY5f]R\u001c\bc\u0001\u001a6\u0007B\u0011AiR\u0007\u0002\u000b*\u0011a)F\u0001\u0007Y&t\u0017\r\\4\n\u0005!+%A\u0002,fGR|'/\u0001\u0004=S:LGO\u0010\u000b\u0004\u00176sEC\u0001\u0018M\u0011\u0015\tE\u00011\u0001C\u0011\u0015yC\u00011\u00012\u0011\u0015iD\u00011\u0001?\u0003\r!\u0017.\\\u000b\u0002#B\u0011qDU\u0005\u0003'\u0002\u00121!\u00138u\u0003\u0011!\u0017.\u001c\u0011\u0002\u00179,XNR3biV\u0014Xm]\u0001\r]Vlg)Z1ukJ,7\u000fI\u0001\u0012G>,gMZ5dS\u0016tGo]!se\u0006LX#A\u001c)\u0005%Q\u0006CA\u0010\\\u0013\ta\u0006EA\u0005ue\u0006t7/[3oi\u0006aQ.\u0019:hS:|eMZ:fiV\t!(A\u0007nCJ<\u0017N\\(gMN,G\u000fI\u0001\u0007EV4g-\u001a:\u0002\u0015\t,hMZ3s?\u0012*\u0017\u000f\u0006\u0002dMB\u0011q\u0004Z\u0005\u0003K\u0002\u0012A!\u00168ji\"9q-DA\u0001\u0002\u00049\u0014a\u0001=%c\u00059!-\u001e4gKJ\u0004\u0003F\u0001\b[\u0003\r\tG\r\u001a\u000b\u0003Y6l\u0011\u0001\u0001\u0005\u0006]>\u0001\r\u0001K\u0001\u0006E2|7m\u001b"
)
public class AFTBlockAggregator implements DifferentiableLossAggregator {
   private transient double[] coefficientsArray;
   private final Broadcast bcScaledMean;
   private final boolean fitIntercept;
   private final Broadcast bcCoefficients;
   private final int dim;
   private final int numFeatures;
   private final double marginOffset;
   private transient double[] buffer;
   private double weightSum;
   private double lossSum;
   private double[] gradientSumArray;
   private volatile boolean bitmap$0;
   private transient volatile boolean bitmap$trans$0;

   public DifferentiableLossAggregator merge(final DifferentiableLossAggregator other) {
      return DifferentiableLossAggregator.merge$(this, other);
   }

   public Vector gradient() {
      return DifferentiableLossAggregator.gradient$(this);
   }

   public double weight() {
      return DifferentiableLossAggregator.weight$(this);
   }

   public double loss() {
      return DifferentiableLossAggregator.loss$(this);
   }

   public double weightSum() {
      return this.weightSum;
   }

   public void weightSum_$eq(final double x$1) {
      this.weightSum = x$1;
   }

   public double lossSum() {
      return this.lossSum;
   }

   public void lossSum_$eq(final double x$1) {
      this.lossSum = x$1;
   }

   private double[] gradientSumArray$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.gradientSumArray = DifferentiableLossAggregator.gradientSumArray$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.gradientSumArray;
   }

   public double[] gradientSumArray() {
      return !this.bitmap$0 ? this.gradientSumArray$lzycompute() : this.gradientSumArray;
   }

   public int dim() {
      return this.dim;
   }

   private int numFeatures() {
      return this.numFeatures;
   }

   private double[] coefficientsArray$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            Vector var3 = (Vector)this.bcCoefficients.value();
            if (var3 instanceof DenseVector) {
               DenseVector var4 = (DenseVector)var3;
               Option var5 = .MODULE$.unapply(var4);
               if (!var5.isEmpty()) {
                  double[] values = (double[])var5.get();
                  this.coefficientsArray = values;
                  this.bitmap$trans$0 = true;
                  return this.coefficientsArray;
               }
            }

            throw new IllegalArgumentException("coefficients only supports dense vector but got type " + this.bcCoefficients.value().getClass() + ".");
         }
      } catch (Throwable var8) {
         throw var8;
      }

      return this.coefficientsArray;
   }

   private double[] coefficientsArray() {
      return !this.bitmap$trans$0 ? this.coefficientsArray$lzycompute() : this.coefficientsArray;
   }

   private double marginOffset() {
      return this.marginOffset;
   }

   private double[] buffer() {
      return this.buffer;
   }

   private void buffer_$eq(final double[] x$1) {
      this.buffer = x$1;
   }

   public AFTBlockAggregator add(final InstanceBlock block) {
      scala.Predef..MODULE$.require(block.matrix().isTransposed());
      scala.Predef..MODULE$.require(this.numFeatures() == block.numFeatures(), () -> {
         int var10000 = this.numFeatures();
         return "Dimensions mismatch when adding new instance. Expecting " + var10000 + " but got " + block.numFeatures() + ".";
      });
      scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.doubleArrayOps(block.labels()), (JFunction1.mcZD.sp)(x$2) -> x$2 > (double)0.0F), () -> "The lifetime or label should be greater than 0.");
      int size = block.size();
      double sigma = scala.math.package..MODULE$.exp(this.coefficientsArray()[this.dim() - 1]);
      if (this.buffer() == null || this.buffer().length < size) {
         this.buffer_$eq((double[])scala.Array..MODULE$.ofDim(size, scala.reflect.ClassTag..MODULE$.Double()));
      }

      double[] arr = this.buffer();
      if (this.fitIntercept) {
         Arrays.fill(arr, 0, size, this.marginOffset());
         org.apache.spark.ml.linalg.BLAS..MODULE$.gemv((double)1.0F, block.matrix(), this.coefficientsArray(), (double)1.0F, arr);
      } else {
         org.apache.spark.ml.linalg.BLAS..MODULE$.gemv((double)1.0F, block.matrix(), this.coefficientsArray(), (double)0.0F, arr);
      }

      double localLossSum = (double)0.0F;
      int i = 0;
      double sigmaGradSum = (double)0.0F;

      double multiplierSum;
      for(multiplierSum = (double)0.0F; i < size; ++i) {
         double ti = block.getLabel(i);
         double delta = block.getWeight().apply$mcDI$sp(i);
         double margin = arr[i];
         double epsilon = (scala.math.package..MODULE$.log(ti) - margin) / sigma;
         double expEpsilon = scala.math.package..MODULE$.exp(epsilon);
         localLossSum += delta * scala.math.package..MODULE$.log(sigma) - delta * epsilon + expEpsilon;
         double multiplier = (delta - expEpsilon) / sigma;
         arr[i] = multiplier;
         multiplierSum += multiplier;
         sigmaGradSum += delta + multiplier * sigma * epsilon;
      }

      this.lossSum_$eq(this.lossSum() + localLossSum);
      this.weightSum_$eq(this.weightSum() + (double)size);
      org.apache.spark.ml.linalg.BLAS..MODULE$.gemv((double)1.0F, block.matrix().transpose(), arr, (double)1.0F, this.gradientSumArray());
      if (this.fitIntercept) {
         org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().daxpy(this.numFeatures(), -multiplierSum, (double[])this.bcScaledMean.value(), 1, this.gradientSumArray(), 1);
         int var25 = this.dim() - 2;
         this.gradientSumArray()[var25] += multiplierSum;
      }

      int var26 = this.dim() - 1;
      this.gradientSumArray()[var26] += sigmaGradSum;
      return this;
   }

   public AFTBlockAggregator(final Broadcast bcScaledMean, final boolean fitIntercept, final Broadcast bcCoefficients) {
      this.bcScaledMean = bcScaledMean;
      this.fitIntercept = fitIntercept;
      this.bcCoefficients = bcCoefficients;
      DifferentiableLossAggregator.$init$(this);
      this.dim = ((Vector)bcCoefficients.value()).size();
      this.numFeatures = this.dim() - 2;
      this.marginOffset = fitIntercept ? this.coefficientsArray()[this.dim() - 2] - org.apache.spark.ml.linalg.BLAS..MODULE$.getBLAS(this.numFeatures()).ddot(this.numFeatures(), this.coefficientsArray(), 1, (double[])bcScaledMean.value(), 1) : Double.NaN;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
