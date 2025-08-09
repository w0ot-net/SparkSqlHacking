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
   bytes = "\u0006\u0005a4Q!\u0005\n\u0001-yA\u0001B\u000e\u0001\u0003\u0002\u0003\u0006I\u0001\u000f\u0005\t\t\u0002\u0011\t\u0011)A\u0005q!AQ\t\u0001B\u0001B\u0003%a\t\u0003\u0005J\u0001\t\u0005\t\u0015!\u0003K\u0011\u0015\t\u0006\u0001\"\u0001S\u0011\u001dA\u0006A1A\u0005\neCa!\u0018\u0001!\u0002\u0013Q\u0006b\u00020\u0001\u0005\u0004%\t&\u0017\u0005\u0007?\u0002\u0001\u000b\u0011\u0002.\t\u0011\u0001\u0004\u0001R1A\u0005\n\u0005DqA\u001a\u0001C\u0002\u0013%q\r\u0003\u0004i\u0001\u0001\u0006I!\u0011\u0005\nS\u0002\u0001\r\u00111A\u0005\n\u0005D\u0011B\u001b\u0001A\u0002\u0003\u0007I\u0011B6\t\u0013E\u0004\u0001\u0019!A!B\u0013q\u0004\"B:\u0001\t\u0003!(\u0001\u0006%j]\u001e,'\t\\8dW\u0006;wM]3hCR|'O\u0003\u0002\u0014)\u0005Q\u0011mZ4sK\u001e\fGo\u001c:\u000b\u0005U1\u0012!B8qi&l'BA\f\u0019\u0003\tiGN\u0003\u0002\u001a5\u0005)1\u000f]1sW*\u00111\u0004H\u0001\u0007CB\f7\r[3\u000b\u0003u\t1a\u001c:h'\u0011\u0001q$\n\u0019\u0011\u0005\u0001\u001aS\"A\u0011\u000b\u0003\t\nQa]2bY\u0006L!\u0001J\u0011\u0003\r\u0005s\u0017PU3g!\u00111s%K\u0018\u000e\u0003II!\u0001\u000b\n\u00039\u0011KgMZ3sK:$\u0018.\u00192mK2{7o]!hOJ,w-\u0019;peB\u0011!&L\u0007\u0002W)\u0011AFF\u0001\bM\u0016\fG/\u001e:f\u0013\tq3FA\u0007J]N$\u0018M\\2f\u00052|7m\u001b\t\u0003M\u0001\u0001\"!\r\u001b\u000e\u0003IR!a\r\r\u0002\u0011%tG/\u001a:oC2L!!\u000e\u001a\u0003\u000f1{wmZ5oO\u0006a!mY%om\u0016\u00148/Z*uI\u000e\u0001\u0001cA\u001d=}5\t!H\u0003\u0002<1\u0005I!M]8bI\u000e\f7\u000f^\u0005\u0003{i\u0012\u0011B\u0011:pC\u0012\u001c\u0017m\u001d;\u0011\u0007\u0001z\u0014)\u0003\u0002AC\t)\u0011I\u001d:bsB\u0011\u0001EQ\u0005\u0003\u0007\u0006\u0012a\u0001R8vE2,\u0017\u0001\u00042d'\u000e\fG.\u001a3NK\u0006t\u0017\u0001\u00044ji&sG/\u001a:dKB$\bC\u0001\u0011H\u0013\tA\u0015EA\u0004C_>dW-\u00198\u0002\u001d\t\u001c7i\\3gM&\u001c\u0017.\u001a8ugB\u0019\u0011\bP&\u0011\u00051{U\"A'\u000b\u000593\u0012A\u00027j]\u0006dw-\u0003\u0002Q\u001b\n1a+Z2u_J\fa\u0001P5oSRtD\u0003B*V-^#\"a\f+\t\u000b%+\u0001\u0019\u0001&\t\u000bY*\u0001\u0019\u0001\u001d\t\u000b\u0011+\u0001\u0019\u0001\u001d\t\u000b\u0015+\u0001\u0019\u0001$\u0002\u00179,XNR3biV\u0014Xm]\u000b\u00025B\u0011\u0001eW\u0005\u00039\u0006\u00121!\u00138u\u00031qW/\u001c$fCR,(/Z:!\u0003\r!\u0017.\\\u0001\u0005I&l\u0007%A\td_\u00164g-[2jK:$8/\u0011:sCf,\u0012A\u0010\u0015\u0003\u0015\r\u0004\"\u0001\t3\n\u0005\u0015\f#!\u0003;sC:\u001c\u0018.\u001a8u\u00031i\u0017M]4j]>3gm]3u+\u0005\t\u0015!D7be\u001eLgn\u00144gg\u0016$\b%\u0001\u0004ck\u001a4WM]\u0001\u000bEV4g-\u001a:`I\u0015\fHC\u00017p!\t\u0001S.\u0003\u0002oC\t!QK\\5u\u0011\u001d\u0001h\"!AA\u0002y\n1\u0001\u001f\u00132\u0003\u001d\u0011WO\u001a4fe\u0002B#aD2\u0002\u0007\u0005$G\r\u0006\u0002vm6\t\u0001\u0001C\u0003x!\u0001\u0007\u0011&A\u0003cY>\u001c7\u000e"
)
public class HingeBlockAggregator implements DifferentiableLossAggregator, Logging {
   private transient double[] coefficientsArray;
   private final Broadcast bcScaledMean;
   private final boolean fitIntercept;
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

   public HingeBlockAggregator add(final InstanceBlock block) {
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

         double localLossSum = (double)0.0F;
         double localWeightSum = (double)0.0F;
         double multiplierSum = (double)0.0F;

         for(int i = 0; i < size; ++i) {
            double weight = block.getWeight().apply$mcDI$sp(i);
            localWeightSum += weight;
            if (weight > (double)0) {
               double label = block.getLabel(i);
               double labelScaled = label + label - (double)1.0F;
               double loss = ((double)1.0F - labelScaled * arr[i]) * weight;
               if (loss > (double)0) {
                  localLossSum += loss;
                  double multiplier = -labelScaled * weight;
                  arr[i] = multiplier;
                  multiplierSum += multiplier;
               } else {
                  arr[i] = (double)0.0F;
               }
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
            if (this.fitIntercept) {
               org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().daxpy(this.numFeatures(), -multiplierSum, (double[])this.bcScaledMean.value(), 1, this.gradientSumArray(), 1);
               this.gradientSumArray()[this.numFeatures()] += multiplierSum;
            }

            return this;
         }
      }
   }

   public HingeBlockAggregator(final Broadcast bcInverseStd, final Broadcast bcScaledMean, final boolean fitIntercept, final Broadcast bcCoefficients) {
      this.bcScaledMean = bcScaledMean;
      this.fitIntercept = fitIntercept;
      this.bcCoefficients = bcCoefficients;
      DifferentiableLossAggregator.$init$(this);
      Logging.$init$(this);
      if (fitIntercept) {
         scala.Predef..MODULE$.require(bcScaledMean != null && ((double[])bcScaledMean.value()).length == ((double[])bcInverseStd.value()).length, () -> "scaled means is required when center the vectors");
      }

      this.numFeatures = ((double[])bcInverseStd.value()).length;
      this.dim = ((Vector)bcCoefficients.value()).size();
      this.marginOffset = fitIntercept ? BoxesRunTime.unboxToDouble(scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.doubleArrayOps(this.coefficientsArray()))) - org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().ddot(this.numFeatures(), this.coefficientsArray(), 1, (double[])bcScaledMean.value(), 1) : Double.NaN;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
