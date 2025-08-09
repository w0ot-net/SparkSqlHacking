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
   bytes = "\u0006\u0005m4QAE\n\u0001/}A\u0001b\u000e\u0001\u0003\u0002\u0003\u0006I!\u000f\u0005\t\u000b\u0002\u0011\t\u0011)A\u0005s!Aa\t\u0001B\u0001B\u0003%q\t\u0003\u0005K\u0001\t\u0005\t\u0015!\u0003H\u0011!Y\u0005A!A!\u0002\u0013a\u0005\"B*\u0001\t\u0003!\u0006bB.\u0001\u0005\u0004%I\u0001\u0018\u0005\u0007A\u0002\u0001\u000b\u0011B/\t\u000f\u0005\u0004!\u0019!C)9\"1!\r\u0001Q\u0001\nuC\u0001b\u0019\u0001\t\u0006\u0004%I\u0001\u001a\u0005\bS\u0002\u0011\r\u0011\"\u0003k\u0011\u0019Y\u0007\u0001)A\u0005\u0005\"IA\u000e\u0001a\u0001\u0002\u0004%I\u0001\u001a\u0005\n[\u0002\u0001\r\u00111A\u0005\n9D\u0011\u0002\u001e\u0001A\u0002\u0003\u0005\u000b\u0015B \t\u000bY\u0004A\u0011A<\u0003;\tKg.\u0019:z\u0019><\u0017n\u001d;jG\ncwnY6BO\u001e\u0014XmZ1u_JT!\u0001F\u000b\u0002\u0015\u0005<wM]3hCR|'O\u0003\u0002\u0017/\u0005)q\u000e\u001d;j[*\u0011\u0001$G\u0001\u0003[2T!AG\u000e\u0002\u000bM\u0004\u0018M]6\u000b\u0005qi\u0012AB1qC\u000eDWMC\u0001\u001f\u0003\ry'oZ\n\u0005\u0001\u00012\u0013\u0007\u0005\u0002\"I5\t!EC\u0001$\u0003\u0015\u00198-\u00197b\u0013\t)#E\u0001\u0004B]f\u0014VM\u001a\t\u0005O!R\u0003'D\u0001\u0014\u0013\tI3C\u0001\u000fES\u001a4WM]3oi&\f'\r\\3M_N\u001c\u0018iZ4sK\u001e\fGo\u001c:\u0011\u0005-rS\"\u0001\u0017\u000b\u00055:\u0012a\u00024fCR,(/Z\u0005\u0003_1\u0012Q\"\u00138ti\u0006t7-\u001a\"m_\u000e\\\u0007CA\u0014\u0001!\t\u0011T'D\u00014\u0015\t!\u0014$\u0001\u0005j]R,'O\\1m\u0013\t14GA\u0004M_\u001e<\u0017N\\4\u0002\u0019\t\u001c\u0017J\u001c<feN,7\u000b\u001e3\u0004\u0001A\u0019!(P \u000e\u0003mR!\u0001P\r\u0002\u0013\t\u0014x.\u00193dCN$\u0018B\u0001 <\u0005%\u0011%o\\1eG\u0006\u001cH\u000fE\u0002\"\u0001\nK!!\u0011\u0012\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u0005\u001a\u0015B\u0001##\u0005\u0019!u.\u001e2mK\u0006a!mY*dC2,G-T3b]\u0006aa-\u001b;J]R,'oY3qiB\u0011\u0011\u0005S\u0005\u0003\u0013\n\u0012qAQ8pY\u0016\fg.A\u0006gSR<\u0016\u000e\u001e5NK\u0006t\u0017A\u00042d\u0007>,gMZ5dS\u0016tGo\u001d\t\u0004uuj\u0005C\u0001(R\u001b\u0005y%B\u0001)\u0018\u0003\u0019a\u0017N\\1mO&\u0011!k\u0014\u0002\u0007-\u0016\u001cGo\u001c:\u0002\rqJg.\u001b;?)\u0015)v\u000bW-[)\t\u0001d\u000bC\u0003L\r\u0001\u0007A\nC\u00038\r\u0001\u0007\u0011\bC\u0003F\r\u0001\u0007\u0011\bC\u0003G\r\u0001\u0007q\tC\u0003K\r\u0001\u0007q)A\u0006ok64U-\u0019;ve\u0016\u001cX#A/\u0011\u0005\u0005r\u0016BA0#\u0005\rIe\u000e^\u0001\r]Vlg)Z1ukJ,7\u000fI\u0001\u0004I&l\u0017\u0001\u00023j[\u0002\n\u0011cY8fM\u001aL7-[3oiN\f%O]1z+\u0005y\u0004FA\u0006g!\t\ts-\u0003\u0002iE\tIAO]1og&,g\u000e^\u0001\r[\u0006\u0014x-\u001b8PM\u001a\u001cX\r^\u000b\u0002\u0005\u0006iQ.\u0019:hS:|eMZ:fi\u0002\naAY;gM\u0016\u0014\u0018A\u00032vM\u001a,'o\u0018\u0013fcR\u0011qN\u001d\t\u0003CAL!!\u001d\u0012\u0003\tUs\u0017\u000e\u001e\u0005\bg>\t\t\u00111\u0001@\u0003\rAH%M\u0001\bEV4g-\u001a:!Q\t\u0001b-A\u0002bI\u0012$\"\u0001_=\u000e\u0003\u0001AQA_\tA\u0002)\nQA\u00197pG.\u0004"
)
public class BinaryLogisticBlockAggregator implements DifferentiableLossAggregator, Logging {
   private transient double[] coefficientsArray;
   private final Broadcast bcScaledMean;
   private final boolean fitIntercept;
   private final boolean fitWithMean;
   private final Broadcast bcCoefficients;
   private final int numFeatures;
   private final int dim;
   private final double marginOffset;
   private transient double[] buffer;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private double weightSum;
   private double lossSum;
   private double[] gradientSumArray;
   private volatile boolean bitmap$0;
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

   private double marginOffset() {
      return this.marginOffset;
   }

   private double[] buffer() {
      return this.buffer;
   }

   private void buffer_$eq(final double[] x$1) {
      this.buffer = x$1;
   }

   public BinaryLogisticBlockAggregator add(final InstanceBlock block) {
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
            double offset = this.fitWithMean ? this.marginOffset() : BoxesRunTime.unboxToDouble(scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.doubleArrayOps(this.coefficientsArray())));
            Arrays.fill(arr, 0, size, offset);
            org.apache.spark.ml.linalg.BLAS..MODULE$.gemv((double)1.0F, block.matrix(), this.coefficientsArray(), (double)1.0F, arr);
         } else {
            org.apache.spark.ml.linalg.BLAS..MODULE$.gemv((double)1.0F, block.matrix(), this.coefficientsArray(), (double)0.0F, arr);
         }

         double localLossSum = (double)0.0F;
         double localWeightSum = (double)0.0F;
         double multiplierSum = (double)0.0F;

         for(int i = 0; i < size; ++i) {
            double weight = block.getWeight().apply$mcDI$sp(i);
            localWeightSum += weight;
            if (weight > (double)0) {
               double label = block.getLabel(i);
               double margin = arr[i];
               if (label > (double)0) {
                  localLossSum += weight * org.apache.spark.ml.impl.Utils..MODULE$.log1pExp(-margin);
               } else {
                  localLossSum += weight * (org.apache.spark.ml.impl.Utils..MODULE$.log1pExp(-margin) + margin);
               }

               double multiplier = weight * ((double)1.0F / ((double)1.0F + scala.math.package..MODULE$.exp(-margin)) - label);
               arr[i] = multiplier;
               multiplierSum += multiplier;
            } else {
               arr[i] = (double)0.0F;
            }
         }

         this.lossSum_$eq(this.lossSum() + localLossSum);
         this.weightSum_$eq(this.weightSum() + localWeightSum);
         if (scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.doubleArrayOps(arr), (JFunction1.mcZD.sp)(x$4) -> x$4 == (double)0)) {
            return this;
         } else {
            org.apache.spark.ml.linalg.BLAS..MODULE$.gemv((double)1.0F, block.matrix().transpose(), arr, (double)1.0F, this.gradientSumArray());
            if (this.fitWithMean) {
               org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().daxpy(this.numFeatures(), -multiplierSum, (double[])this.bcScaledMean.value(), 1, this.gradientSumArray(), 1);
            }

            if (this.fitIntercept) {
               this.gradientSumArray()[this.numFeatures()] += multiplierSum;
            }

            return this;
         }
      }
   }

   public BinaryLogisticBlockAggregator(final Broadcast bcInverseStd, final Broadcast bcScaledMean, final boolean fitIntercept, final boolean fitWithMean, final Broadcast bcCoefficients) {
      this.bcScaledMean = bcScaledMean;
      this.fitIntercept = fitIntercept;
      this.fitWithMean = fitWithMean;
      this.bcCoefficients = bcCoefficients;
      DifferentiableLossAggregator.$init$(this);
      Logging.$init$(this);
      if (fitWithMean) {
         scala.Predef..MODULE$.require(fitIntercept, () -> "for training without intercept, should not center the vectors");
         scala.Predef..MODULE$.require(bcScaledMean != null && ((double[])bcScaledMean.value()).length == ((double[])bcInverseStd.value()).length, () -> "scaled means is required when center the vectors");
      }

      this.numFeatures = ((double[])bcInverseStd.value()).length;
      this.dim = ((Vector)bcCoefficients.value()).size();
      this.marginOffset = fitWithMean ? BoxesRunTime.unboxToDouble(scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.doubleArrayOps(this.coefficientsArray()))) - org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().ddot(this.numFeatures(), this.coefficientsArray(), 1, (double[])bcScaledMean.value(), 1) : Double.NaN;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
