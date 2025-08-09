package org.apache.spark.ml.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.ml.linalg.DenseMatrix;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.stat.distribution.MultivariateGaussian;
import scala.MatchError;
import scala.Tuple2;
import scala.Array.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mb\u0001B\r\u001b\t\u0015B\u0001\u0002\u000f\u0001\u0003\u0002\u0003\u0006I!\u000f\u0005\ty\u0001\u0011\t\u0011)A\u0005{!A\u0011\n\u0001B\u0001B\u0003%!\nC\u0003V\u0001\u0011\u0005a\u000bC\u0004]\u0001\t\u0007I\u0011B/\t\ry\u0003\u0001\u0015!\u0003:\u0011\u001dy\u0006\u00011A\u0005\n\u0001Dq\u0001\u001a\u0001A\u0002\u0013%Q\r\u0003\u0004l\u0001\u0001\u0006K!\u0019\u0005\bY\u0002\u0001\r\u0011\"\u0003n\u0011\u001dq\u0007\u00011A\u0005\n=Da!\u001d\u0001!B\u00131\u0005b\u0002:\u0001\u0005\u0004%I!\u0018\u0005\u0007g\u0002\u0001\u000b\u0011B\u001d\t\u0011Q\u0004\u0001R1A\u0005\nUD\u0001B\u001e\u0001\t\u0006\u0004%Ia\u001e\u0005\t{\u0002A)\u0019!C\u0005o\"Iq\u0010\u0001EC\u0002\u0013%\u0011\u0011\u0001\u0005\u0007\u0003/\u0001A\u0011\u00011\t\r\u0005e\u0001\u0001\"\u0001n\u0011\u0019\tY\u0002\u0001C\u0001k\"1\u0011Q\u0004\u0001\u0005\u0002]Da!a\b\u0001\t\u00039\bbBA\u0011\u0001\u0011\u0005\u00111\u0005\u0002\u0016\u000bb\u0004Xm\u0019;bi&|g.Q4he\u0016<\u0017\r^8s\u0015\tYB$\u0001\u0006dYV\u001cH/\u001a:j]\u001eT!!\b\u0010\u0002\u00055d'BA\u0010!\u0003\u0015\u0019\b/\u0019:l\u0015\t\t#%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002G\u0005\u0019qN]4\u0004\u0001M\u0019\u0001A\n\u0017\u0011\u0005\u001dRS\"\u0001\u0015\u000b\u0003%\nQa]2bY\u0006L!a\u000b\u0015\u0003\r\u0005s\u0017PU3g!\tiSG\u0004\u0002/g9\u0011qFM\u0007\u0002a)\u0011\u0011\u0007J\u0001\u0007yI|w\u000e\u001e \n\u0003%J!\u0001\u000e\u0015\u0002\u000fA\f7m[1hK&\u0011ag\u000e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003i!\n1B\\;n\r\u0016\fG/\u001e:fgB\u0011qEO\u0005\u0003w!\u00121!\u00138u\u0003%\u00117mV3jO\"$8\u000fE\u0002?\u0003\u000ek\u0011a\u0010\u0006\u0003\u0001z\t\u0011B\u0019:pC\u0012\u001c\u0017m\u001d;\n\u0005\t{$!\u0003\"s_\u0006$7-Y:u!\r9CIR\u0005\u0003\u000b\"\u0012Q!\u0011:sCf\u0004\"aJ$\n\u0005!C#A\u0002#pk\ndW-A\u0006cG\u001e\u000bWo]:jC:\u001c\bc\u0001 B\u0017B\u0019q\u0005\u0012'\u0011\t\u001djujT\u0005\u0003\u001d\"\u0012a\u0001V;qY\u0016\u0014\u0004C\u0001)T\u001b\u0005\t&B\u0001*\u001d\u0003\u0019a\u0017N\\1mO&\u0011A+\u0015\u0002\f\t\u0016t7/\u001a,fGR|'/\u0001\u0004=S:LGO\u0010\u000b\u0005/fS6\f\u0005\u0002Y\u00015\t!\u0004C\u00039\t\u0001\u0007\u0011\bC\u0003=\t\u0001\u0007Q\bC\u0003J\t\u0001\u0007!*A\u0001l+\u0005I\u0014AA6!\u0003!!x\u000e^1m\u0007:$X#A1\u0011\u0005\u001d\u0012\u0017BA2)\u0005\u0011auN\\4\u0002\u0019Q|G/\u00197D]R|F%Z9\u0015\u0005\u0019L\u0007CA\u0014h\u0013\tA\u0007F\u0001\u0003V]&$\bb\u00026\t\u0003\u0003\u0005\r!Y\u0001\u0004q\u0012\n\u0014!\u0003;pi\u0006d7I\u001c;!\u0003AqWm\u001e'pO2K7.\u001a7jQ>|G-F\u0001G\u0003QqWm\u001e'pO2K7.\u001a7jQ>|Gm\u0018\u0013fcR\u0011a\r\u001d\u0005\bU.\t\t\u00111\u0001G\u0003EqWm\u001e'pO2K7.\u001a7jQ>|G\rI\u0001\bG>48+\u001b>f\u0003!\u0019wN^*ju\u0016\u0004\u0013A\u00038fo^+\u0017n\u001a5ugV\t1)\u0001\u0005oK^lU-\u00198t+\u0005A\bcA\u0014E\u001f\"\u0012\u0001C\u001f\t\u0003OmL!\u0001 \u0015\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018a\u00028fo\u000e{go\u001d\u0015\u0003#i\f\u0011bZ1vgNL\u0017M\\:\u0016\u0005\u0005\r\u0001\u0003B\u0014E\u0003\u000b\u0001B!a\u0002\u0002\u00125\u0011\u0011\u0011\u0002\u0006\u0005\u0003\u0017\ti!\u0001\u0007eSN$(/\u001b2vi&|gNC\u0002\u0002\u0010q\tAa\u001d;bi&!\u00111CA\u0005\u0005QiU\u000f\u001c;jm\u0006\u0014\u0018.\u0019;f\u000f\u0006,8o]5b]\"\u0012!C_\u0001\u0006G>,h\u000e^\u0001\u000eY><G*[6fY&Dwn\u001c3\u0002\u000f],\u0017n\u001a5ug\u0006)Q.Z1og\u0006!1m\u001c<t\u0003\r\tG\r\u001a\u000b\u0005\u0003K\t9#D\u0001\u0001\u0011\u001d\tI\u0003\u0007a\u0001\u0003W\t\u0001\"\u001b8ti\u0006t7-\u001a\t\u0006O5\u000biC\u0012\t\u0004!\u0006=\u0012bAA\u0019#\n1a+Z2u_J\u0004"
)
public class ExpectationAggregator implements Serializable {
   private double[] newWeights;
   private transient DenseVector[] newMeans;
   private transient DenseVector[] newCovs;
   private transient MultivariateGaussian[] gaussians;
   private final int numFeatures;
   private final Broadcast bcWeights;
   private final Broadcast bcGaussians;
   private final int k;
   private long totalCnt;
   private double newLogLikelihood;
   private final int covSize;
   private volatile boolean bitmap$0;
   private transient volatile byte bitmap$trans$0;

   private int k() {
      return this.k;
   }

   private long totalCnt() {
      return this.totalCnt;
   }

   private void totalCnt_$eq(final long x$1) {
      this.totalCnt = x$1;
   }

   private double newLogLikelihood() {
      return this.newLogLikelihood;
   }

   private void newLogLikelihood_$eq(final double x$1) {
      this.newLogLikelihood = x$1;
   }

   private int covSize() {
      return this.covSize;
   }

   private double[] newWeights$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.newWeights = (double[]).MODULE$.ofDim(this.k(), scala.reflect.ClassTag..MODULE$.Double());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.newWeights;
   }

   private double[] newWeights() {
      return !this.bitmap$0 ? this.newWeights$lzycompute() : this.newWeights;
   }

   private DenseVector[] newMeans$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            this.newMeans = (DenseVector[]).MODULE$.fill(this.k(), () -> org.apache.spark.ml.linalg.Vectors..MODULE$.zeros(this.numFeatures).toDense(), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.newMeans;
   }

   private DenseVector[] newMeans() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.newMeans$lzycompute() : this.newMeans;
   }

   private DenseVector[] newCovs$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            this.newCovs = (DenseVector[]).MODULE$.fill(this.k(), () -> org.apache.spark.ml.linalg.Vectors..MODULE$.zeros(this.covSize()).toDense(), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.newCovs;
   }

   private DenseVector[] newCovs() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.newCovs$lzycompute() : this.newCovs;
   }

   private MultivariateGaussian[] gaussians$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 4) == 0) {
            this.gaussians = (MultivariateGaussian[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.bcGaussians.value()), (x0$1) -> {
               if (x0$1 != null) {
                  DenseVector mean = (DenseVector)x0$1._1();
                  DenseVector covVec = (DenseVector)x0$1._2();
                  DenseMatrix cov = GaussianMixture$.MODULE$.unpackUpperTriangularMatrix(this.numFeatures, covVec.values());
                  return new MultivariateGaussian(mean, cov);
               } else {
                  throw new MatchError(x0$1);
               }
            }, scala.reflect.ClassTag..MODULE$.apply(MultivariateGaussian.class));
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.gaussians;
   }

   private MultivariateGaussian[] gaussians() {
      return (byte)(this.bitmap$trans$0 & 4) == 0 ? this.gaussians$lzycompute() : this.gaussians;
   }

   public long count() {
      return this.totalCnt();
   }

   public double logLikelihood() {
      return this.newLogLikelihood();
   }

   public double[] weights() {
      return this.newWeights();
   }

   public DenseVector[] means() {
      return this.newMeans();
   }

   public DenseVector[] covs() {
      return this.newCovs();
   }

   public ExpectationAggregator add(final Tuple2 instance) {
      if (instance != null) {
         Vector vector = (Vector)instance._1();
         double weight = instance._2$mcD$sp();
         if (vector != null && true) {
            Tuple2 var3 = new Tuple2(vector, BoxesRunTime.boxToDouble(weight));
            Vector vector = (Vector)var3._1();
            double weight = var3._2$mcD$sp();
            double[] localWeights = (double[])this.bcWeights.value();
            MultivariateGaussian[] localGaussians = this.gaussians();
            double[] prob = new double[this.k()];
            double probSum = (double)0.0F;

            for(int i = 0; i < this.k(); ++i) {
               double p = org.apache.spark.ml.impl.Utils..MODULE$.EPSILON() + localWeights[i] * localGaussians[i].pdf(vector);
               prob[i] = p;
               probSum += p;
            }

            this.newLogLikelihood_$eq(this.newLogLikelihood() + scala.math.package..MODULE$.log(probSum) * weight);
            double[] localNewWeights = this.newWeights();
            DenseVector[] localNewMeans = this.newMeans();
            DenseVector[] localNewCovs = this.newCovs();

            for(int var28 = 0; var28 < this.k(); ++var28) {
               double w = prob[var28] / probSum * weight;
               localNewWeights[var28] += w;
               org.apache.spark.ml.linalg.BLAS..MODULE$.axpy(w, vector, localNewMeans[var28]);
               org.apache.spark.ml.linalg.BLAS..MODULE$.spr(w, vector, localNewCovs[var28]);
            }

            this.totalCnt_$eq(this.totalCnt() + 1L);
            return this;
         }
      }

      throw new MatchError(instance);
   }

   public ExpectationAggregator(final int numFeatures, final Broadcast bcWeights, final Broadcast bcGaussians) {
      this.numFeatures = numFeatures;
      this.bcWeights = bcWeights;
      this.bcGaussians = bcGaussians;
      this.k = ((double[])bcWeights.value()).length;
      this.totalCnt = 0L;
      this.newLogLikelihood = (double)0.0F;
      this.covSize = numFeatures * (numFeatures + 1) / 2;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
