package org.apache.spark.ml.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.feature.InstanceBlock;
import org.apache.spark.ml.linalg.DenseMatrix;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Vector;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ub\u0001\u0002\u000f\u001e\t!B\u0001b\u000f\u0001\u0003\u0006\u0004%\t\u0001\u0010\u0005\t\u0007\u0002\u0011\t\u0011)A\u0005{!AA\t\u0001BC\u0002\u0013\u0005Q\t\u0003\u0005J\u0001\t\u0005\t\u0015!\u0003G\u0011!Q\u0005A!b\u0001\n\u0003)\u0005\u0002C&\u0001\u0005\u0003\u0005\u000b\u0011\u0002$\t\u00111\u0003!Q1A\u0005\u00025C\u0001B\u0016\u0001\u0003\u0002\u0003\u0006IA\u0014\u0005\u0006/\u0002!\t\u0001\u0017\u0005\u0006?\u0002!\t\u0001\u0019\u0005\bI\u0002\u0001\r\u0011\"\u0001a\u0011\u001d)\u0007\u00011A\u0005\u0002\u0019Da\u0001\u001c\u0001!B\u0013\t\u0007bB7\u0001\u0001\u0004%\tA\u001c\u0005\be\u0002\u0001\r\u0011\"\u0001t\u0011\u0019)\b\u0001)Q\u0005_\"9a\u000f\u0001b\u0001\n\u00039\bBB>\u0001A\u0003%\u0001\u0010C\u0004}\u0001\t\u0007I\u0011\u0001\u001f\t\ru\u0004\u0001\u0015!\u0003>\u0011!q\b\u0001#b\u0001\n\u0013y\bBCA\b\u0001\u0001\u0007\t\u0019!C\u0005\u007f\"Y\u0011\u0011\u0003\u0001A\u0002\u0003\u0007I\u0011BA\n\u0011-\t9\u0002\u0001a\u0001\u0002\u0003\u0006K!!\u0001\t\u000f\u0005m\u0001\u0001\"\u0001\u0002\u001e!9\u0011\u0011\u0007\u0001\u0005\n\u0005M\u0002bBA\u001c\u0001\u0011%\u0011\u0011\b\u0002\u0011\u00176+\u0017M\\:BO\u001e\u0014XmZ1u_JT!AH\u0010\u0002\u0015\rdWo\u001d;fe&twM\u0003\u0002!C\u0005\u0011Q\u000e\u001c\u0006\u0003E\r\nQa\u001d9be.T!\u0001J\u0013\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0013aA8sO\u000e\u00011c\u0001\u0001*_A\u0011!&L\u0007\u0002W)\tA&A\u0003tG\u0006d\u0017-\u0003\u0002/W\t1\u0011I\\=SK\u001a\u0004\"\u0001\r\u001d\u000f\u0005E2dB\u0001\u001a6\u001b\u0005\u0019$B\u0001\u001b(\u0003\u0019a$o\\8u}%\tA&\u0003\u00028W\u00059\u0001/Y2lC\u001e,\u0017BA\u001d;\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t94&\u0001\u0007dK:$XM]'biJL\u00070F\u0001>!\tq\u0014)D\u0001@\u0015\t\u0001u$\u0001\u0004mS:\fGnZ\u0005\u0003\u0005~\u00121\u0002R3og\u0016l\u0015\r\u001e:jq\u0006i1-\u001a8uKJl\u0015\r\u001e:jq\u0002\n\u0011a[\u000b\u0002\rB\u0011!fR\u0005\u0003\u0011.\u00121!\u00138u\u0003\tY\u0007%A\u0006ok64U-\u0019;ve\u0016\u001c\u0018\u0001\u00048v[\u001a+\u0017\r^;sKN\u0004\u0013a\u00043jgR\fgnY3NK\u0006\u001cXO]3\u0016\u00039\u0003\"aT*\u000f\u0005A\u000b\u0006C\u0001\u001a,\u0013\t\u00116&\u0001\u0004Qe\u0016$WMZ\u0005\u0003)V\u0013aa\u0015;sS:<'B\u0001*,\u0003A!\u0017n\u001d;b]\u000e,W*Z1tkJ,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u00063ncVL\u0018\t\u00035\u0002i\u0011!\b\u0005\u0006w%\u0001\r!\u0010\u0005\u0006\t&\u0001\rA\u0012\u0005\u0006\u0015&\u0001\rA\u0012\u0005\u0006\u0019&\u0001\rAT\u0001\no\u0016Lw\r\u001b;Tk6,\u0012!\u0019\t\u0003U\tL!aY\u0016\u0003\r\u0011{WO\u00197f\u0003\u001d\u0019wn\u001d;Tk6\f1bY8tiN+Xn\u0018\u0013fcR\u0011qM\u001b\t\u0003U!L!![\u0016\u0003\tUs\u0017\u000e\u001e\u0005\bW2\t\t\u00111\u0001b\u0003\rAH%M\u0001\tG>\u001cHoU;nA\u0005)1m\\;oiV\tq\u000e\u0005\u0002+a&\u0011\u0011o\u000b\u0002\u0005\u0019>tw-A\u0005d_VtGo\u0018\u0013fcR\u0011q\r\u001e\u0005\bW>\t\t\u00111\u0001p\u0003\u0019\u0019w.\u001e8uA\u0005aq/Z5hQR\u001cV/\u001c,fGV\t\u0001\u0010\u0005\u0002?s&\u0011!p\u0010\u0002\f\t\u0016t7/\u001a,fGR|'/A\u0007xK&<\u0007\u000e^*v[Z+7\rI\u0001\u0007gVlW*\u0019;\u0002\u000fM,X.T1uA\u0005\u00112-\u001a8uKJ\u001c\u0016/^1sK\u0012tuN]7t+\t\t\t\u0001\u0005\u0003+\u0003\u0007\t\u0017bAA\u0003W\t)\u0011I\u001d:bs\"\u001aQ#!\u0003\u0011\u0007)\nY!C\u0002\u0002\u000e-\u0012\u0011\u0002\u001e:b]NLWM\u001c;\u0002\r\t,hMZ3s\u0003)\u0011WO\u001a4fe~#S-\u001d\u000b\u0004O\u0006U\u0001\u0002C6\u0018\u0003\u0003\u0005\r!!\u0001\u0002\u000f\t,hMZ3sA!\u001a\u0001$!\u0003\u0002\u0007\u0005$G\r\u0006\u0003\u0002 \u0005\u0005R\"\u0001\u0001\t\u000f\u0005\r\u0012\u00041\u0001\u0002&\u0005)!\r\\8dWB!\u0011qEA\u0017\u001b\t\tICC\u0002\u0002,}\tqAZ3biV\u0014X-\u0003\u0003\u00020\u0005%\"!D%ogR\fgnY3CY>\u001c7.\u0001\ffk\u000ed\u0017\u000eZ3b]V\u0003H-\u0019;f\u0013:\u0004F.Y2f)\r9\u0017Q\u0007\u0005\b\u0003GQ\u0002\u0019AA\u0013\u0003M\u0019wn]5oKV\u0003H-\u0019;f\u0013:\u0004F.Y2f)\r9\u00171\b\u0005\b\u0003GY\u0002\u0019AA\u0013\u0001"
)
public class KMeansAggregator implements Serializable {
   private transient double[] centerSquaredNorms;
   private final DenseMatrix centerMatrix;
   private final int k;
   private final int numFeatures;
   private final String distanceMeasure;
   private double costSum;
   private long count;
   private final DenseVector weightSumVec;
   private final DenseMatrix sumMat;
   private transient double[] buffer;
   private transient volatile boolean bitmap$trans$0;

   public DenseMatrix centerMatrix() {
      return this.centerMatrix;
   }

   public int k() {
      return this.k;
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public String distanceMeasure() {
      return this.distanceMeasure;
   }

   public double weightSum() {
      return BoxesRunTime.unboxToDouble(.MODULE$.wrapDoubleArray(this.weightSumVec().values()).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
   }

   public double costSum() {
      return this.costSum;
   }

   public void costSum_$eq(final double x$1) {
      this.costSum = x$1;
   }

   public long count() {
      return this.count;
   }

   public void count_$eq(final long x$1) {
      this.count = x$1;
   }

   public DenseVector weightSumVec() {
      return this.weightSumVec;
   }

   public DenseMatrix sumMat() {
      return this.sumMat;
   }

   private double[] centerSquaredNorms$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            double[] var9;
            label82: {
               label91: {
                  String var3 = this.distanceMeasure();
                  String var10001 = KMeans$.MODULE$.EUCLIDEAN();
                  if (var10001 == null) {
                     if (var3 == null) {
                        break label91;
                     }
                  } else if (var10001.equals(var3)) {
                     break label91;
                  }

                  var10001 = KMeans$.MODULE$.COSINE();
                  if (var10001 == null) {
                     if (var3 != null) {
                        throw new MatchError(var3);
                     }
                  } else if (!var10001.equals(var3)) {
                     throw new MatchError(var3);
                  }

                  var9 = null;
                  break label82;
               }

               var9 = (double[])this.centerMatrix().rowIter().map((center) -> BoxesRunTime.boxToDouble($anonfun$centerSquaredNorms$1(center))).toArray(scala.reflect.ClassTag..MODULE$.Double());
            }

            this.centerSquaredNorms = var9;
            this.bitmap$trans$0 = true;
            return this.centerSquaredNorms;
         }
      } catch (Throwable var7) {
         throw var7;
      }

      return this.centerSquaredNorms;
   }

   private double[] centerSquaredNorms() {
      return !this.bitmap$trans$0 ? this.centerSquaredNorms$lzycompute() : this.centerSquaredNorms;
   }

   private double[] buffer() {
      return this.buffer;
   }

   private void buffer_$eq(final double[] x$1) {
      this.buffer = x$1;
   }

   public KMeansAggregator add(final InstanceBlock block) {
      int size = block.size();
      .MODULE$.require(block.matrix().isTransposed());
      .MODULE$.require(this.numFeatures() == block.numFeatures(), () -> {
         int var10000 = this.numFeatures();
         return "Dimensions mismatch when adding new instance. Expecting " + var10000 + " but got " + block.numFeatures() + ".";
      });
      .MODULE$.require(block.weightIter().forall((JFunction1.mcZD.sp)(x$11) -> x$11 >= (double)0), () -> {
         Iterator var10000 = block.weightIter();
         return "instance weights " + var10000.mkString("[", ",", "]") + " has to be >= 0.0";
      });
      if (block.weightIter().forall((JFunction1.mcZD.sp)(x$12) -> x$12 == (double)0)) {
         return this;
      } else {
         if (this.buffer() == null || this.buffer().length < size * this.k()) {
            this.buffer_$eq((double[])scala.Array..MODULE$.ofDim(size * this.k(), scala.reflect.ClassTag..MODULE$.Double()));
         }

         label41: {
            label51: {
               String var4 = this.distanceMeasure();
               String var10000 = KMeans$.MODULE$.EUCLIDEAN();
               if (var10000 == null) {
                  if (var4 == null) {
                     break label51;
                  }
               } else if (var10000.equals(var4)) {
                  break label51;
               }

               var10000 = KMeans$.MODULE$.COSINE();
               if (var10000 == null) {
                  if (var4 != null) {
                     throw new MatchError(var4);
                  }
               } else if (!var10000.equals(var4)) {
                  throw new MatchError(var4);
               }

               this.cosineUpdateInPlace(block);
               BoxedUnit var8 = BoxedUnit.UNIT;
               break label41;
            }

            this.euclideanUpdateInPlace(block);
            BoxedUnit var9 = BoxedUnit.UNIT;
         }

         this.count_$eq(this.count() + (long)size);
         return this;
      }
   }

   private void euclideanUpdateInPlace(final InstanceBlock block) {
      double[] localBuffer = this.buffer();
      org.apache.spark.ml.linalg.BLAS..MODULE$.gemm((double)-2.0F, block.matrix(), this.centerMatrix().transpose(), (double)0.0F, localBuffer);
      int size = block.size();
      double[] localCenterSquaredNorms = this.centerSquaredNorms();
      double[] localWeightSumArr = this.weightSumVec().values();
      double[] localSumArr = this.sumMat().values();
      int i = 0;

      for(int j = 0; i < size; ++i) {
         double weight = block.getWeight().apply$mcDI$sp(i);
         if (weight > (double)0) {
            double instanceSquaredNorm = block.getLabel(i);
            IntRef bestIndex = IntRef.create(0);
            double bestSquaredDistance = Double.POSITIVE_INFINITY;

            for(int var19 = 0; var19 < this.k(); ++var19) {
               double squaredDistance = localBuffer[i + var19 * size] + instanceSquaredNorm + localCenterSquaredNorms[var19];
               if (squaredDistance < bestSquaredDistance) {
                  bestIndex.elem = var19;
                  bestSquaredDistance = squaredDistance;
               }
            }

            this.costSum_$eq(this.costSum() + weight * bestSquaredDistance);
            int var18 = bestIndex.elem;
            localWeightSumArr[var18] += weight;
            ((IterableOnceOps)block.getNonZeroIter().apply(BoxesRunTime.boxToInteger(i))).foreach((x0$1) -> {
               $anonfun$euclideanUpdateInPlace$1(this, bestIndex, localSumArr, weight, x0$1);
               return BoxedUnit.UNIT;
            });
         }
      }

   }

   private void cosineUpdateInPlace(final InstanceBlock block) {
      double[] localBuffer = this.buffer();
      org.apache.spark.ml.linalg.BLAS..MODULE$.gemm((double)-1.0F, block.matrix(), this.centerMatrix().transpose(), (double)0.0F, localBuffer);
      int size = block.size();
      double[] localWeightSumArr = this.weightSumVec().values();
      double[] localSumArr = this.sumMat().values();
      int i = 0;

      for(int j = 0; i < size; ++i) {
         double weight = block.getWeight().apply$mcDI$sp(i);
         if (weight > (double)0) {
            IntRef bestIndex = IntRef.create(0);
            double bestDistance = Double.POSITIVE_INFINITY;

            for(int var16 = 0; var16 < this.k(); ++var16) {
               double cosineDistance = (double)1 + localBuffer[i + var16 * size];
               if (cosineDistance < bestDistance) {
                  bestIndex.elem = var16;
                  bestDistance = cosineDistance;
               }
            }

            this.costSum_$eq(this.costSum() + weight * bestDistance);
            int var15 = bestIndex.elem;
            localWeightSumArr[var15] += weight;
            ((IterableOnceOps)block.getNonZeroIter().apply(BoxesRunTime.boxToInteger(i))).foreach((x0$1) -> {
               $anonfun$cosineUpdateInPlace$1(this, bestIndex, localSumArr, weight, x0$1);
               return BoxedUnit.UNIT;
            });
         }
      }

   }

   // $FF: synthetic method
   public static final double $anonfun$centerSquaredNorms$1(final Vector center) {
      return center.dot(center);
   }

   // $FF: synthetic method
   public static final void $anonfun$euclideanUpdateInPlace$1(final KMeansAggregator $this, final IntRef bestIndex$1, final double[] localSumArr$1, final double weight$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int j = x0$1._1$mcI$sp();
         double v = x0$1._2$mcD$sp();
         int var11 = bestIndex$1.elem + j * $this.k();
         localSumArr$1[var11] += v * weight$1;
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$cosineUpdateInPlace$1(final KMeansAggregator $this, final IntRef bestIndex$2, final double[] localSumArr$2, final double weight$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int j = x0$1._1$mcI$sp();
         double v = x0$1._2$mcD$sp();
         int var11 = bestIndex$2.elem + j * $this.k();
         localSumArr$2[var11] += v * weight$2;
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public KMeansAggregator(final DenseMatrix centerMatrix, final int k, final int numFeatures, final String distanceMeasure) {
      this.centerMatrix = centerMatrix;
      this.k = k;
      this.numFeatures = numFeatures;
      this.distanceMeasure = distanceMeasure;
      this.costSum = (double)0.0F;
      this.count = 0L;
      this.weightSumVec = new DenseVector((double[])scala.Array..MODULE$.ofDim(k, scala.reflect.ClassTag..MODULE$.Double()));
      this.sumMat = new DenseMatrix(k, numFeatures, (double[])scala.Array..MODULE$.ofDim(k * numFeatures, scala.reflect.ClassTag..MODULE$.Double()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
