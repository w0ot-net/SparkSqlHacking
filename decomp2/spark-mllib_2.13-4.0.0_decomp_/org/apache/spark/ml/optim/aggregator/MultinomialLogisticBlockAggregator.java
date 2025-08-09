package org.apache.spark.ml.optim.aggregator;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.Map;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.feature.InstanceBlock;
import org.apache.spark.ml.linalg.DenseMatrix;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.linalg.SparseMatrix;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.DenseVector.;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}a!B\f\u0019\u0001q!\u0003\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b\u0011\u0002 \t\u0011)\u0003!\u0011!Q\u0001\nyB\u0001b\u0013\u0001\u0003\u0002\u0003\u0006I\u0001\u0014\u0005\t\u001f\u0002\u0011\t\u0011)A\u0005\u0019\"A\u0001\u000b\u0001B\u0001B\u0003%\u0011\u000bC\u0003Y\u0001\u0011\u0005\u0011\fC\u0004a\u0001\t\u0007I\u0011B1\t\r\u0015\u0004\u0001\u0015!\u0003c\u0011\u001d1\u0007A1A\u0005R\u0005Daa\u001a\u0001!\u0002\u0013\u0011\u0007b\u00025\u0001\u0005\u0004%I!\u0019\u0005\u0007S\u0002\u0001\u000b\u0011\u00022\t\u000f)\u0004!\u0019!C\u0005C\"11\u000e\u0001Q\u0001\n\tD\u0001\u0002\u001c\u0001\t\u0006\u0004%I!\u001c\u0005\te\u0002A)\u0019!C\u0005g\"A\u0001\u0010\u0001EC\u0002\u0013%\u0011\u0010\u0003\u0005\u007f\u0001!\u0015\r\u0011\"\u0003z\u0011)\t\t\u0001\u0001a\u0001\u0002\u0004%I!\u001c\u0005\f\u0003\u0007\u0001\u0001\u0019!a\u0001\n\u0013\t)\u0001\u0003\u0006\u0002\u0012\u0001\u0001\r\u0011!Q!\n\u0011Cq!!\u0006\u0001\t\u0003\t9B\u0001\u0012Nk2$\u0018N\\8nS\u0006dGj\\4jgRL7M\u00117pG.\fum\u001a:fO\u0006$xN\u001d\u0006\u00033i\t!\"Y4he\u0016<\u0017\r^8s\u0015\tYB$A\u0003paRLWN\u0003\u0002\u001e=\u0005\u0011Q\u000e\u001c\u0006\u0003?\u0001\nQa\u001d9be.T!!\t\u0012\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0013aA8sON!\u0001!J\u00167!\t1\u0013&D\u0001(\u0015\u0005A\u0013!B:dC2\f\u0017B\u0001\u0016(\u0005\u0019\te.\u001f*fMB!A&L\u00186\u001b\u0005A\u0012B\u0001\u0018\u0019\u0005q!\u0015N\u001a4fe\u0016tG/[1cY\u0016dun]:BO\u001e\u0014XmZ1u_J\u0004\"\u0001M\u001a\u000e\u0003ER!A\r\u000f\u0002\u000f\u0019,\u0017\r^;sK&\u0011A'\r\u0002\u000e\u0013:\u001cH/\u00198dK\ncwnY6\u0011\u00051\u0002\u0001CA\u001c;\u001b\u0005A$BA\u001d\u001f\u0003!Ig\u000e^3s]\u0006d\u0017BA\u001e9\u0005\u001daunZ4j]\u001e\fABY2J]Z,'o]3Ti\u0012\u001c\u0001\u0001E\u0002@\u0005\u0012k\u0011\u0001\u0011\u0006\u0003\u0003z\t\u0011B\u0019:pC\u0012\u001c\u0017m\u001d;\n\u0005\r\u0003%!\u0003\"s_\u0006$7-Y:u!\r1SiR\u0005\u0003\r\u001e\u0012Q!\u0011:sCf\u0004\"A\n%\n\u0005%;#A\u0002#pk\ndW-\u0001\u0007cGN\u001b\u0017\r\\3e\u001b\u0016\fg.\u0001\u0007gSRLe\u000e^3sG\u0016\u0004H\u000f\u0005\u0002'\u001b&\u0011aj\n\u0002\b\u0005>|G.Z1o\u0003-1\u0017\u000e^,ji\"lU-\u00198\u0002\u001d\t\u001c7i\\3gM&\u001c\u0017.\u001a8ugB\u0019qH\u0011*\u0011\u0005M3V\"\u0001+\u000b\u0005Uc\u0012A\u00027j]\u0006dw-\u0003\u0002X)\n1a+Z2u_J\fa\u0001P5oSRtD#\u0002.];z{FCA\u001b\\\u0011\u0015\u0001f\u00011\u0001R\u0011\u0015ad\u00011\u0001?\u0011\u0015Qe\u00011\u0001?\u0011\u0015Ye\u00011\u0001M\u0011\u0015ye\u00011\u0001M\u0003-qW/\u001c$fCR,(/Z:\u0016\u0003\t\u0004\"AJ2\n\u0005\u0011<#aA%oi\u0006aa.^7GK\u0006$XO]3tA\u0005\u0019A-[7\u0002\t\u0011LW\u000eI\u0001\u0019]Vlg)Z1ukJ,7\u000f\u00157vg&sG/\u001a:dKB$\u0018!\u00078v[\u001a+\u0017\r^;sKN\u0004F.^:J]R,'oY3qi\u0002\n!B\\;n\u00072\f7o]3t\u0003-qW/\\\"mCN\u001cXm\u001d\u0011\u0002#\r|WM\u001a4jG&,g\u000e^:BeJ\f\u00170F\u0001EQ\tyq\u000e\u0005\u0002'a&\u0011\u0011o\n\u0002\niJ\fgn]5f]R\fa\u0001\\5oK\u0006\u0014X#\u0001;\u0011\u0005M+\u0018B\u0001<U\u0005-!UM\\:f\u001b\u0006$(/\u001b=)\u0005Ay\u0017!C5oi\u0016\u00148-\u001a9u+\u0005Q\bCA*|\u0013\taHKA\u0006EK:\u001cXMV3di>\u0014\bFA\tp\u00031i\u0017M]4j]>3gm]3uQ\t\u0011r.\u0001\u0004ck\u001a4WM]\u0001\u000bEV4g-\u001a:`I\u0015\fH\u0003BA\u0004\u0003\u001b\u00012AJA\u0005\u0013\r\tYa\n\u0002\u0005+:LG\u000f\u0003\u0005\u0002\u0010Q\t\t\u00111\u0001E\u0003\rAH%M\u0001\bEV4g-\u001a:!Q\t)r.A\u0002bI\u0012$B!!\u0007\u0002\u001c5\t\u0001\u0001\u0003\u0004\u0002\u001eY\u0001\raL\u0001\u0006E2|7m\u001b"
)
public class MultinomialLogisticBlockAggregator implements DifferentiableLossAggregator, Logging {
   private transient double[] coefficientsArray;
   private transient DenseMatrix linear;
   private transient DenseVector intercept;
   private transient DenseVector marginOffset;
   private final Broadcast bcScaledMean;
   private final boolean fitIntercept;
   private final boolean fitWithMean;
   private final Broadcast bcCoefficients;
   private final int numFeatures;
   private final int dim;
   private final int numFeaturesPlusIntercept;
   private final int numClasses;
   private transient double[] buffer;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private double weightSum;
   private double lossSum;
   private double[] gradientSumArray;
   private volatile boolean bitmap$0;
   private transient volatile byte bitmap$trans$0;

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

   private int numFeaturesPlusIntercept() {
      return this.numFeaturesPlusIntercept;
   }

   private int numClasses() {
      return this.numClasses;
   }

   private double[] coefficientsArray$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            Vector var3 = (Vector)this.bcCoefficients.value();
            if (var3 instanceof DenseVector) {
               DenseVector var4 = (DenseVector)var3;
               Option var5 = .MODULE$.unapply(var4);
               if (!var5.isEmpty()) {
                  double[] values = (double[])var5.get();
                  this.coefficientsArray = values;
                  this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
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
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.coefficientsArray$lzycompute() : this.coefficientsArray;
   }

   private DenseMatrix linear$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            this.linear = this.fitIntercept ? new DenseMatrix(this.numClasses(), this.numFeatures(), (double[])scala.collection.ArrayOps..MODULE$.take$extension(scala.Predef..MODULE$.doubleArrayOps(this.coefficientsArray()), this.numClasses() * this.numFeatures())) : new DenseMatrix(this.numClasses(), this.numFeatures(), this.coefficientsArray());
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.linear;
   }

   private DenseMatrix linear() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.linear$lzycompute() : this.linear;
   }

   private DenseVector intercept$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 4) == 0) {
            this.intercept = this.fitIntercept ? new DenseVector((double[])scala.collection.ArrayOps..MODULE$.takeRight$extension(scala.Predef..MODULE$.doubleArrayOps(this.coefficientsArray()), this.numClasses())) : null;
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.intercept;
   }

   private DenseVector intercept() {
      return (byte)(this.bitmap$trans$0 & 4) == 0 ? this.intercept$lzycompute() : this.intercept;
   }

   private DenseVector marginOffset$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 8) == 0) {
            DenseVector var10001;
            if (this.fitWithMean) {
               DenseVector offset = new DenseVector((double[])scala.collection.ArrayOps..MODULE$.takeRight$extension(scala.Predef..MODULE$.doubleArrayOps(this.coefficientsArray()), this.numClasses()));
               org.apache.spark.ml.linalg.BLAS..MODULE$.gemv((double)-1.0F, this.linear(), org.apache.spark.ml.linalg.Vectors..MODULE$.dense((double[])this.bcScaledMean.value()), (double)1.0F, offset);
               var10001 = offset;
            } else {
               var10001 = null;
            }

            this.marginOffset = var10001;
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 8);
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.marginOffset;
   }

   private DenseVector marginOffset() {
      return (byte)(this.bitmap$trans$0 & 8) == 0 ? this.marginOffset$lzycompute() : this.marginOffset;
   }

   private double[] buffer() {
      return this.buffer;
   }

   private void buffer_$eq(final double[] x$1) {
      this.buffer = x$1;
   }

   public MultinomialLogisticBlockAggregator add(final InstanceBlock block) {
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
         if (this.buffer() == null || this.buffer().length < size * this.numClasses()) {
            this.buffer_$eq((double[])scala.Array..MODULE$.ofDim(size * this.numClasses(), scala.reflect.ClassTag..MODULE$.Double()));
         }

         double[] arr = this.buffer();
         if (this.fitIntercept) {
            DenseVector offset = this.fitWithMean ? this.marginOffset() : this.intercept();

            for(int j = 0; j < this.numClasses(); ++j) {
               if (offset.apply(j) != (double)0) {
                  Arrays.fill(arr, j * size, (j + 1) * size, offset.apply(j));
               }
            }

            org.apache.spark.ml.linalg.BLAS..MODULE$.gemm((double)1.0F, block.matrix(), this.linear().transpose(), (double)1.0F, arr);
         } else {
            org.apache.spark.ml.linalg.BLAS..MODULE$.gemm((double)1.0F, block.matrix(), this.linear().transpose(), (double)0.0F, arr);
         }

         double localLossSum = (double)0.0F;
         double localWeightSum = (double)0.0F;

         for(int i = 0; i < size; ++i) {
            double weight = block.getWeight().apply$mcDI$sp(i);
            localWeightSum += weight;
            if (weight > (double)0) {
               int labelIndex = i + (int)block.getLabel(i) * size;
               org.apache.spark.ml.impl.Utils..MODULE$.softmax(arr, this.numClasses(), i, size, arr);
               localLossSum -= weight * scala.math.package..MODULE$.log(arr[labelIndex]);
               if (weight != (double)1) {
                  org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().dscal(this.numClasses(), weight, arr, i, size);
               }

               arr[labelIndex] -= weight;
            } else {
               org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().dscal(this.numClasses(), (double)0.0F, arr, i, size);
            }
         }

         this.lossSum_$eq(this.lossSum() + localLossSum);
         this.weightSum_$eq(this.weightSum() + localWeightSum);
         Matrix var15 = block.matrix();
         if (var15 instanceof DenseMatrix) {
            DenseMatrix var16 = (DenseMatrix)var15;
            org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().dgemm("T", "T", this.numClasses(), this.numFeatures(), size, (double)1.0F, arr, size, var16.values(), this.numFeatures(), (double)1.0F, this.gradientSumArray(), this.numClasses());
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            if (!(var15 instanceof SparseMatrix)) {
               throw new MatchError(var15);
            }

            SparseMatrix var17 = (SparseMatrix)var15;
            SparseMatrix A = var17.transpose();
            int kA = A.numCols();
            double[] Avals = A.values();
            int[] ArowIndices = A.rowIndices();
            int[] AcolPtrs = A.colPtrs();
            double[] Bvals = arr;
            int nB = this.numClasses();
            int kB = size;

            for(int colCounterForB = 0; colCounterForB < nB; ++colCounterForB) {
               int colCounterForA = 0;

               for(int Bstart = colCounterForB * kB; colCounterForA < kA; ++colCounterForA) {
                  int i = AcolPtrs[colCounterForA];
                  int indEnd = AcolPtrs[colCounterForA + 1];

                  for(double Bval = Bvals[Bstart + colCounterForA]; i < indEnd; ++i) {
                     int var33 = colCounterForB + nB * ArowIndices[i];
                     this.gradientSumArray()[var33] += Avals[i] * Bval;
                  }
               }
            }

            BoxedUnit var39 = BoxedUnit.UNIT;
         }

         if (this.fitIntercept) {
            double[] multiplierSum = (double[])scala.Array..MODULE$.ofDim(this.numClasses(), scala.reflect.ClassTag..MODULE$.Double());

            for(int j = 0; j < this.numClasses(); ++j) {
               int i = j * size;

               for(int end = i + size; i < end; ++i) {
                  multiplierSum[j] += arr[i];
               }
            }

            if (this.fitWithMean) {
               org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().dger(this.numClasses(), this.numFeatures(), (double)-1.0F, multiplierSum, 1, (double[])this.bcScaledMean.value(), 1, this.gradientSumArray(), this.numClasses());
            }

            org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().daxpy(this.numClasses(), (double)1.0F, multiplierSum, 0, 1, this.gradientSumArray(), this.numClasses() * this.numFeatures(), 1);
         }

         return this;
      }
   }

   public MultinomialLogisticBlockAggregator(final Broadcast bcInverseStd, final Broadcast bcScaledMean, final boolean fitIntercept, final boolean fitWithMean, final Broadcast bcCoefficients) {
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
      this.numFeaturesPlusIntercept = fitIntercept ? this.numFeatures() + 1 : this.numFeatures();
      this.numClasses = this.dim() / this.numFeaturesPlusIntercept();
      scala.Predef..MODULE$.require(this.dim() == this.numClasses() * this.numFeaturesPlusIntercept());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
