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
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005y4Qa\u0005\u000b\u00011\u0001B\u0001\u0002\u000f\u0001\u0003\u0002\u0003\u0006IA\u000f\u0005\t\r\u0002\u0011\t\u0011)A\u0005u!Aq\t\u0001B\u0001B\u0003%\u0001\n\u0003\u0005L\u0001\t\u0005\t\u0015!\u0003D\u0011!a\u0005A!A!\u0002\u0013\u0019\u0005\u0002C'\u0001\u0005\u0003\u0005\u000b\u0011\u0002(\t\u000bU\u0003A\u0011\u0001,\t\u000fy\u0003!\u0019!C\u0005?\"11\r\u0001Q\u0001\n\u0001Dq\u0001\u001a\u0001C\u0002\u0013Es\f\u0003\u0004f\u0001\u0001\u0006I\u0001\u0019\u0005\tM\u0002A)\u0019!C\u0005O\"9A\u000e\u0001b\u0001\n\u0013i\u0007B\u00028\u0001A\u0003%1\tC\u0005p\u0001\u0001\u0007\t\u0019!C\u0005O\"I\u0001\u000f\u0001a\u0001\u0002\u0004%I!\u001d\u0005\no\u0002\u0001\r\u0011!Q!\n\u0001CQ!\u001f\u0001\u0005\u0002i\u00141\u0004T3bgR\u001c\u0016/^1sKN\u0014En\\2l\u0003\u001e<'/Z4bi>\u0014(BA\u000b\u0017\u0003)\twm\u001a:fO\u0006$xN\u001d\u0006\u0003/a\tQa\u001c9uS6T!!\u0007\u000e\u0002\u00055d'BA\u000e\u001d\u0003\u0015\u0019\b/\u0019:l\u0015\tib$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002?\u0005\u0019qN]4\u0014\t\u0001\tsE\r\t\u0003E\u0015j\u0011a\t\u0006\u0002I\u0005)1oY1mC&\u0011ae\t\u0002\u0007\u0003:L(+\u001a4\u0011\t!J3&M\u0007\u0002)%\u0011!\u0006\u0006\u0002\u001d\t&4g-\u001a:f]RL\u0017M\u00197f\u0019>\u001c8/Q4he\u0016<\u0017\r^8s!\tas&D\u0001.\u0015\tq\u0003$A\u0004gK\u0006$XO]3\n\u0005Aj#!D%ogR\fgnY3CY>\u001c7\u000e\u0005\u0002)\u0001A\u00111GN\u0007\u0002i)\u0011QGG\u0001\tS:$XM\u001d8bY&\u0011q\u0007\u000e\u0002\b\u0019><w-\u001b8h\u00031\u00117-\u00138wKJ\u001cXm\u0015;e\u0007\u0001\u00012a\u000f A\u001b\u0005a$BA\u001f\u001b\u0003%\u0011'o\\1eG\u0006\u001cH/\u0003\u0002@y\tI!I]8bI\u000e\f7\u000f\u001e\t\u0004E\u0005\u001b\u0015B\u0001\"$\u0005\u0015\t%O]1z!\t\u0011C)\u0003\u0002FG\t1Ai\\;cY\u0016\fABY2TG\u0006dW\rZ'fC:\fABZ5u\u0013:$XM]2faR\u0004\"AI%\n\u0005)\u001b#a\u0002\"p_2,\u0017M\\\u0001\tY\u0006\u0014W\r\\*uI\u0006IA.\u00192fY6+\u0017M\\\u0001\u000fE\u000e\u001cu.\u001a4gS\u000eLWM\u001c;t!\rYdh\u0014\t\u0003!Nk\u0011!\u0015\u0006\u0003%b\ta\u0001\\5oC2<\u0017B\u0001+R\u0005\u00191Vm\u0019;pe\u00061A(\u001b8jiz\"baV-[7rkFCA\u0019Y\u0011\u0015iu\u00011\u0001O\u0011\u0015At\u00011\u0001;\u0011\u00151u\u00011\u0001;\u0011\u00159u\u00011\u0001I\u0011\u0015Yu\u00011\u0001D\u0011\u0015au\u00011\u0001D\u0003-qW/\u001c$fCR,(/Z:\u0016\u0003\u0001\u0004\"AI1\n\u0005\t\u001c#aA%oi\u0006aa.^7GK\u0006$XO]3tA\u0005\u0019A-[7\u0002\t\u0011LW\u000eI\u0001\u000eK\u001a4Wm\u0019;jm\u0016\u001cu.\u001a4\u0016\u0003\u0001C#\u0001D5\u0011\u0005\tR\u0017BA6$\u0005%!(/\u00198tS\u0016tG/\u0001\u0004pM\u001a\u001cX\r^\u000b\u0002\u0007\u00069qN\u001a4tKR\u0004\u0013A\u00022vM\u001a,'/\u0001\u0006ck\u001a4WM]0%KF$\"A];\u0011\u0005\t\u001a\u0018B\u0001;$\u0005\u0011)f.\u001b;\t\u000fY\u0004\u0012\u0011!a\u0001\u0001\u0006\u0019\u0001\u0010J\u0019\u0002\u000f\t,hMZ3sA!\u0012\u0011#[\u0001\u0004C\u0012$GCA>}\u001b\u0005\u0001\u0001\"B?\u0013\u0001\u0004Y\u0013!\u00022m_\u000e\\\u0007"
)
public class LeastSquaresBlockAggregator implements DifferentiableLossAggregator, Logging {
   private transient double[] effectiveCoef;
   private final Broadcast bcInverseStd;
   private final boolean fitIntercept;
   private final double labelStd;
   private final Broadcast bcCoefficients;
   private final int numFeatures;
   private final int dim;
   private final double offset;
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

   private double[] effectiveCoef$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            Vector var3 = (Vector)this.bcCoefficients.value();
            if (var3 instanceof DenseVector) {
               DenseVector var4 = (DenseVector)var3;
               Option var5 = .MODULE$.unapply(var4);
               if (!var5.isEmpty()) {
                  double[] values = (double[])var5.get();
                  double[] inverseStd = (double[])this.bcInverseStd.value();
                  this.effectiveCoef = (double[])scala.Array..MODULE$.tabulate(this.numFeatures(), (JFunction1.mcDI.sp)(i) -> inverseStd[i] != (double)0 ? values[i] : (double)0.0F, scala.reflect.ClassTag..MODULE$.Double());
                  this.bitmap$trans$0 = true;
                  return this.effectiveCoef;
               }
            }

            throw new IllegalArgumentException("coefficients only supports dense vector but got type " + this.bcCoefficients.value().getClass() + ".)");
         }
      } catch (Throwable var9) {
         throw var9;
      }

      return this.effectiveCoef;
   }

   private double[] effectiveCoef() {
      return !this.bitmap$trans$0 ? this.effectiveCoef$lzycompute() : this.effectiveCoef;
   }

   private double offset() {
      return this.offset;
   }

   private double[] buffer() {
      return this.buffer;
   }

   private void buffer_$eq(final double[] x$1) {
      this.buffer = x$1;
   }

   public LeastSquaresBlockAggregator add(final InstanceBlock block) {
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
            Arrays.fill(arr, 0, size, this.offset());
         } else {
            Arrays.fill(arr, 0, size, (double)0.0F);
         }

         org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().daxpy(size, (double)-1.0F / this.labelStd, block.labels(), 1, arr, 1);
         org.apache.spark.ml.linalg.BLAS..MODULE$.gemv((double)1.0F, block.matrix(), this.effectiveCoef(), (double)1.0F, arr);
         double localLossSum = (double)0.0F;
         double localWeightSum = (double)0.0F;

         for(int i = 0; i < size; ++i) {
            double weight = block.getWeight().apply$mcDI$sp(i);
            localWeightSum += weight;
            double diff = arr[i];
            localLossSum += weight * diff * diff / (double)2;
            double multiplier = weight * diff;
            arr[i] = multiplier;
         }

         this.lossSum_$eq(this.lossSum() + localLossSum);
         this.weightSum_$eq(this.weightSum() + localWeightSum);
         org.apache.spark.ml.linalg.BLAS..MODULE$.gemv((double)1.0F, block.matrix().transpose(), arr, (double)1.0F, this.gradientSumArray());
         return this;
      }
   }

   public LeastSquaresBlockAggregator(final Broadcast bcInverseStd, final Broadcast bcScaledMean, final boolean fitIntercept, final double labelStd, final double labelMean, final Broadcast bcCoefficients) {
      this.bcInverseStd = bcInverseStd;
      this.fitIntercept = fitIntercept;
      this.labelStd = labelStd;
      this.bcCoefficients = bcCoefficients;
      DifferentiableLossAggregator.$init$(this);
      Logging.$init$(this);
      scala.Predef..MODULE$.require(labelStd > (double)0.0F, () -> this.getClass().getName() + " requires the label standard deviation to be positive.");
      this.numFeatures = ((double[])bcInverseStd.value()).length;
      this.dim = this.numFeatures();
      this.offset = fitIntercept ? labelMean / labelStd - org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().ddot(this.numFeatures(), ((Vector)bcCoefficients.value()).toArray(), 1, (double[])bcScaledMean.value(), 1) : Double.NaN;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
