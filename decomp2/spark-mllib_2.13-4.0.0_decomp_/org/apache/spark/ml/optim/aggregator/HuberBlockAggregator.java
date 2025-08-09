package org.apache.spark.ml.optim.aggregator;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.Map;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.feature.InstanceBlock;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.DenseVector.;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005e4Q!\u0005\n\u0001-yA\u0001B\u000e\u0001\u0003\u0002\u0003\u0006I\u0001\u000f\u0005\t\t\u0002\u0011\t\u0011)A\u0005q!AQ\t\u0001B\u0001B\u0003%a\t\u0003\u0005J\u0001\t\u0005\t\u0015!\u0003B\u0011!Q\u0005A!A!\u0002\u0013Y\u0005\"\u0002*\u0001\t\u0003\u0019\u0006b\u0002.\u0001\u0005\u0004%Ia\u0017\u0005\u0007?\u0002\u0001\u000b\u0011\u0002/\t\u000f\u0001\u0004!\u0019!C)7\"1\u0011\r\u0001Q\u0001\nqC\u0001B\u0019\u0001\t\u0006\u0004%Ia\u0019\u0005\tQ\u0002A)\u0019!C\u0005S\"I!\u000e\u0001a\u0001\u0002\u0004%Ia\u0019\u0005\nW\u0002\u0001\r\u00111A\u0005\n1D\u0011B\u001d\u0001A\u0002\u0003\u0005\u000b\u0015\u0002 \t\u000bQ\u0004A\u0011A;\u0003)!+(-\u001a:CY>\u001c7.Q4he\u0016<\u0017\r^8s\u0015\t\u0019B#\u0001\u0006bO\u001e\u0014XmZ1u_JT!!\u0006\f\u0002\u000b=\u0004H/[7\u000b\u0005]A\u0012AA7m\u0015\tI\"$A\u0003ta\u0006\u00148N\u0003\u0002\u001c9\u00051\u0011\r]1dQ\u0016T\u0011!H\u0001\u0004_J<7\u0003\u0002\u0001 KA\u0002\"\u0001I\u0012\u000e\u0003\u0005R\u0011AI\u0001\u0006g\u000e\fG.Y\u0005\u0003I\u0005\u0012a!\u00118z%\u00164\u0007\u0003\u0002\u0014(S=j\u0011AE\u0005\u0003QI\u0011A\u0004R5gM\u0016\u0014XM\u001c;jC\ndW\rT8tg\u0006;wM]3hCR|'\u000f\u0005\u0002+[5\t1F\u0003\u0002--\u00059a-Z1ukJ,\u0017B\u0001\u0018,\u00055Ien\u001d;b]\u000e,'\t\\8dWB\u0011a\u0005\u0001\t\u0003cQj\u0011A\r\u0006\u0003ga\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003kI\u0012q\u0001T8hO&tw-\u0001\u0007cG&sg/\u001a:tKN#Hm\u0001\u0001\u0011\u0007ebd(D\u0001;\u0015\tY\u0004$A\u0005ce>\fGmY1ti&\u0011QH\u000f\u0002\n\u0005J|\u0017\rZ2bgR\u00042\u0001I B\u0013\t\u0001\u0015EA\u0003BeJ\f\u0017\u0010\u0005\u0002!\u0005&\u00111)\t\u0002\u0007\t>,(\r\\3\u0002\u0019\t\u001c7kY1mK\u0012lU-\u00198\u0002\u0019\u0019LG/\u00138uKJ\u001cW\r\u001d;\u0011\u0005\u0001:\u0015B\u0001%\"\u0005\u001d\u0011un\u001c7fC:\fq!\u001a9tS2|g.\u0001\bcG\u000e{WM\u001a4jG&,g\u000e^:\u0011\u0007ebD\n\u0005\u0002N!6\taJ\u0003\u0002P-\u00051A.\u001b8bY\u001eL!!\u0015(\u0003\rY+7\r^8s\u0003\u0019a\u0014N\\5u}Q)AKV,Y3R\u0011q&\u0016\u0005\u0006\u0015\u001a\u0001\ra\u0013\u0005\u0006m\u0019\u0001\r\u0001\u000f\u0005\u0006\t\u001a\u0001\r\u0001\u000f\u0005\u0006\u000b\u001a\u0001\rA\u0012\u0005\u0006\u0013\u001a\u0001\r!Q\u0001\f]Vlg)Z1ukJ,7/F\u0001]!\t\u0001S,\u0003\u0002_C\t\u0019\u0011J\u001c;\u0002\u00199,XNR3biV\u0014Xm\u001d\u0011\u0002\u0007\u0011LW.\u0001\u0003eS6\u0004\u0013!E2pK\u001a4\u0017nY5f]R\u001c\u0018I\u001d:bsV\ta\b\u000b\u0002\fKB\u0011\u0001EZ\u0005\u0003O\u0006\u0012\u0011\u0002\u001e:b]NLWM\u001c;\u0002\u00195\f'oZ5o\u001f\u001a47/\u001a;\u0016\u0003\u0005\u000baAY;gM\u0016\u0014\u0018A\u00032vM\u001a,'o\u0018\u0013fcR\u0011Q\u000e\u001d\t\u0003A9L!a\\\u0011\u0003\tUs\u0017\u000e\u001e\u0005\bc:\t\t\u00111\u0001?\u0003\rAH%M\u0001\bEV4g-\u001a:!Q\tyQ-A\u0002bI\u0012$\"A^<\u000e\u0003\u0001AQ\u0001\u001f\tA\u0002%\nQA\u00197pG.\u0004"
)
public class HuberBlockAggregator implements DifferentiableLossAggregator, Logging {
   private transient double[] coefficientsArray;
   private double marginOffset;
   private final Broadcast bcScaledMean;
   private final boolean fitIntercept;
   private final double epsilon;
   private final Broadcast bcCoefficients;
   private final int numFeatures;
   private final int dim;
   private transient double[] buffer;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private double weightSum;
   private double lossSum;
   private double[] gradientSumArray;
   private volatile byte bitmap$0;
   private transient volatile boolean bitmap$trans$0;

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

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

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
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
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.gradientSumArray = DifferentiableLossAggregator.gradientSumArray$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.gradientSumArray;
   }

   public double[] gradientSumArray() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.gradientSumArray$lzycompute() : this.gradientSumArray;
   }

   private int numFeatures() {
      return this.numFeatures;
   }

   public int dim() {
      return this.dim;
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

            throw new IllegalArgumentException("coefficients only supports dense vector but got type " + this.bcCoefficients.value().getClass() + ".)");
         }
      } catch (Throwable var8) {
         throw var8;
      }

      return this.coefficientsArray;
   }

   private double[] coefficientsArray() {
      return !this.bitmap$trans$0 ? this.coefficientsArray$lzycompute() : this.coefficientsArray;
   }

   private double marginOffset$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.marginOffset = this.fitIntercept ? this.coefficientsArray()[this.dim() - 2] - org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().ddot(this.numFeatures(), this.coefficientsArray(), 1, (double[])this.bcScaledMean.value(), 1) : Double.NaN;
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.marginOffset;
   }

   private double marginOffset() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.marginOffset$lzycompute() : this.marginOffset;
   }

   private double[] buffer() {
      return this.buffer;
   }

   private void buffer_$eq(final double[] x$1) {
      this.buffer = x$1;
   }

   public HuberBlockAggregator add(final InstanceBlock block) {
      scala.Predef..MODULE$.require(block.matrix().isTransposed());
      scala.Predef..MODULE$.require(this.numFeatures() == block.numFeatures(), () -> {
         int var10000 = this.numFeatures();
         return "Dimensions mismatch when adding new instance. Expecting " + var10000 + " but got " + block.numFeatures() + ".";
      });
      scala.Predef..MODULE$.require(block.weightIter().forall((JFunction1.mcZD.sp)(x$2) -> x$2 >= (double)0), () -> {
         Iterator var10000 = block.weightIter();
         return "instance weights " + var10000.mkString("[", ",", "]") + " has to be >= 0.0";
      });
      if (block.weightIter().forall((JFunction1.mcZD.sp)(x$3) -> x$3 == (double)0)) {
         return this;
      } else {
         int size = block.size();
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

         double sigma = BoxesRunTime.unboxToDouble(scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.doubleArrayOps(this.coefficientsArray())));
         double sigmaGradSum = (double)0.0F;
         double localLossSum = (double)0.0F;
         double localWeightSum = (double)0.0F;
         double multiplierSum = (double)0.0F;

         for(int i = 0; i < size; ++i) {
            double weight = block.getWeight().apply$mcDI$sp(i);
            localWeightSum += weight;
            if (weight > (double)0) {
               double label = block.getLabel(i);
               double margin = arr[i];
               double linearLoss = label - margin;
               if (scala.math.package..MODULE$.abs(linearLoss) <= sigma * this.epsilon) {
                  localLossSum += (double)0.5F * weight * (sigma + scala.math.package..MODULE$.pow(linearLoss, (double)2.0F) / sigma);
                  double linearLossDivSigma = linearLoss / sigma;
                  double multiplier = (double)-1.0F * weight * linearLossDivSigma;
                  arr[i] = multiplier;
                  multiplierSum += multiplier;
                  sigmaGradSum += (double)0.5F * weight * ((double)1.0F - scala.math.package..MODULE$.pow(linearLossDivSigma, (double)2.0F));
               } else {
                  localLossSum += (double)0.5F * weight * (sigma + (double)2.0F * this.epsilon * scala.math.package..MODULE$.abs(linearLoss) - sigma * this.epsilon * this.epsilon);
                  double sign = linearLoss >= (double)0 ? (double)-1.0F : (double)1.0F;
                  double multiplier = weight * sign * this.epsilon;
                  arr[i] = multiplier;
                  multiplierSum += multiplier;
                  sigmaGradSum += (double)0.5F * weight * ((double)1.0F - this.epsilon * this.epsilon);
               }
            } else {
               arr[i] = (double)0.0F;
            }
         }

         this.lossSum_$eq(this.lossSum() + localLossSum);
         this.weightSum_$eq(this.weightSum() + localWeightSum);
         org.apache.spark.ml.linalg.BLAS..MODULE$.gemv((double)1.0F, block.matrix().transpose(), arr, (double)1.0F, this.gradientSumArray());
         if (this.fitIntercept) {
            org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().daxpy(this.numFeatures(), -multiplierSum, (double[])this.bcScaledMean.value(), 1, this.gradientSumArray(), 1);
            int var31 = this.dim() - 2;
            this.gradientSumArray()[var31] += multiplierSum;
         }

         int var32 = this.dim() - 1;
         this.gradientSumArray()[var32] += sigmaGradSum;
         return this;
      }
   }

   public HuberBlockAggregator(final Broadcast bcInverseStd, final Broadcast bcScaledMean, final boolean fitIntercept, final double epsilon, final Broadcast bcCoefficients) {
      this.bcScaledMean = bcScaledMean;
      this.fitIntercept = fitIntercept;
      this.epsilon = epsilon;
      this.bcCoefficients = bcCoefficients;
      DifferentiableLossAggregator.$init$(this);
      Logging.$init$(this);
      if (fitIntercept) {
         scala.Predef..MODULE$.require(bcScaledMean != null && ((double[])bcScaledMean.value()).length == ((double[])bcInverseStd.value()).length, () -> "scaled means is required when center the vectors");
      }

      this.numFeatures = ((double[])bcInverseStd.value()).length;
      this.dim = ((Vector)bcCoefficients.value()).size();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
