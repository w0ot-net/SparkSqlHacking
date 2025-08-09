package org.apache.spark.ml.regression;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.BLAS.;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.SparseVector;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.VectorImplicits$;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.optimization.Gradient;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u00154a\u0001D\u0007\u0002\u0002=9\u0002\u0002\u0003\u0011\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0012\t\u0011!\u0002!\u0011!Q\u0001\n%B\u0001\u0002\f\u0001\u0003\u0002\u0003\u0006I!\u000b\u0005\t[\u0001\u0011\t\u0011)A\u0005E!)a\u0006\u0001C\u0001_!)a\u0007\u0001C!o!)\u0011\n\u0001D\u0001\u0015\")Q\n\u0001D\t\u001d\")\u0011\u000b\u0001D\t%\")Q\u000b\u0001C\u0001-\")q\f\u0001C\u0005A\n\t#)Y:f\r\u0006\u001cGo\u001c:ju\u0006$\u0018n\u001c8NC\u000eD\u0017N\\3t\u000fJ\fG-[3oi*\u0011abD\u0001\u000be\u0016<'/Z:tS>t'B\u0001\t\u0012\u0003\tiGN\u0003\u0002\u0013'\u0005)1\u000f]1sW*\u0011A#F\u0001\u0007CB\f7\r[3\u000b\u0003Y\t1a\u001c:h'\t\u0001\u0001\u0004\u0005\u0002\u001a=5\t!D\u0003\u0002\u001c9\u0005aq\u000e\u001d;j[&T\u0018\r^5p]*\u0011Q$E\u0001\u0006[2d\u0017NY\u0005\u0003?i\u0011\u0001b\u0012:bI&,g\u000e^\u0001\u000bM\u0006\u001cGo\u001c:TSj,7\u0001\u0001\t\u0003G\u0019j\u0011\u0001\n\u0006\u0002K\u0005)1oY1mC&\u0011q\u0005\n\u0002\u0004\u0013:$\u0018\u0001\u00044ji&sG/\u001a:dKB$\bCA\u0012+\u0013\tYCEA\u0004C_>dW-\u00198\u0002\u0013\u0019LG\u000fT5oK\u0006\u0014\u0018a\u00038v[\u001a+\u0017\r^;sKN\fa\u0001P5oSRtD#\u0002\u00193gQ*\u0004CA\u0019\u0001\u001b\u0005i\u0001\"\u0002\u0011\u0006\u0001\u0004\u0011\u0003\"\u0002\u0015\u0006\u0001\u0004I\u0003\"\u0002\u0017\u0006\u0001\u0004I\u0003\"B\u0017\u0006\u0001\u0004\u0011\u0013aB2p[B,H/\u001a\u000b\u0006qm\u001aUi\u0012\t\u0003GeJ!A\u000f\u0013\u0003\r\u0011{WO\u00197f\u0011\u0015ad\u00011\u0001>\u0003\u0011!\u0017\r^1\u0011\u0005y\nU\"A \u000b\u0005\u0001c\u0012A\u00027j]\u0006dw-\u0003\u0002C\u007f\t1a+Z2u_JDQ\u0001\u0012\u0004A\u0002a\nQ\u0001\\1cK2DQA\u0012\u0004A\u0002u\nqa^3jO\"$8\u000fC\u0003I\r\u0001\u0007Q(A\u0006dk6<%/\u00193jK:$\u0018!D4fiB\u0013X\rZ5di&|g\u000e\u0006\u00029\u0017\")Aj\u0002a\u0001q\u0005i!/Y<Qe\u0016$\u0017n\u0019;j_:\fQbZ3u\u001bVdG/\u001b9mS\u0016\u0014Hc\u0001\u001dP!\")A\n\u0003a\u0001q!)A\t\u0003a\u0001q\u00059q-\u001a;M_N\u001cHc\u0001\u001dT)\")A*\u0003a\u0001q!)A)\u0003a\u0001q\u0005\u0001r-\u001a;SC^\u0004&/\u001a3jGRLwN\u001c\u000b\u0004/vs\u0006\u0003B\u0012YqiK!!\u0017\u0013\u0003\rQ+\b\u000f\\33!\r\u00193\fO\u0005\u00039\u0012\u0012Q!\u0011:sCfDQ\u0001\u0010\u0006A\u0002uBQA\u0012\u0006A\u0002u\nabZ3u%\u0006<xI]1eS\u0016tG\u000f\u0006\u0003>C\n\u001c\u0007\"\u0002\u001f\f\u0001\u0004i\u0004\"\u0002$\f\u0001\u0004i\u0004\"\u00023\f\u0001\u0004Q\u0016!B:v[ZC\u0006"
)
public abstract class BaseFactorizationMachinesGradient extends Gradient {
   private final int factorSize;
   private final boolean fitIntercept;
   private final boolean fitLinear;
   private final int numFeatures;

   public double compute(final Vector data, final double label, final Vector weights, final Vector cumGradient) {
      Tuple2 var8 = this.getRawPrediction(data, weights);
      if (var8 != null) {
         double rawPrediction = var8._1$mcD$sp();
         double[] sumVX = (double[])var8._2();
         Tuple2 var7 = new Tuple2(BoxesRunTime.boxToDouble(rawPrediction), sumVX);
         double rawPrediction = var7._1$mcD$sp();
         double[] sumVX = (double[])var7._2();
         Vector rawGradient = this.getRawGradient(data, weights, sumVX);
         double multiplier = this.getMultiplier(rawPrediction, label);
         .MODULE$.axpy(multiplier, VectorImplicits$.MODULE$.mllibVectorToMLVector(rawGradient), VectorImplicits$.MODULE$.mllibVectorToMLVector(cumGradient));
         double loss = this.getLoss(rawPrediction, label);
         return loss;
      } else {
         throw new MatchError(var8);
      }
   }

   public abstract double getPrediction(final double rawPrediction);

   public abstract double getMultiplier(final double rawPrediction, final double label);

   public abstract double getLoss(final double rawPrediction, final double label);

   public Tuple2 getRawPrediction(final Vector data, final Vector weights) {
      double[] sumVX = new double[this.factorSize];
      DoubleRef rawPrediction = DoubleRef.create((double)0.0F);
      int vWeightsSize = this.numFeatures * this.factorSize;
      if (this.fitIntercept) {
         rawPrediction.elem += weights.apply(weights.size() - 1);
      }

      if (this.fitLinear) {
         data.foreachNonZero((JFunction2.mcVID.sp)(x0$1, x1$1) -> {
            Tuple2.mcID.sp var7 = new Tuple2.mcID.sp(x0$1, x1$1);
            if (var7 != null) {
               int index = ((Tuple2)var7)._1$mcI$sp();
               double value = ((Tuple2)var7)._2$mcD$sp();
               rawPrediction.elem += weights.apply(vWeightsSize + index) * value;
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(var7);
            }
         });
      }

      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.factorSize).foreach$mVc$sp((JFunction1.mcVI.sp)(f) -> {
         DoubleRef sumSquare = DoubleRef.create((double)0.0F);
         DoubleRef sum = DoubleRef.create((double)0.0F);
         data.foreachNonZero((JFunction2.mcVID.sp)(x0$2, x1$2) -> {
            Tuple2.mcID.sp var9 = new Tuple2.mcID.sp(x0$2, x1$2);
            if (var9 != null) {
               int index = ((Tuple2)var9)._1$mcI$sp();
               double value = ((Tuple2)var9)._2$mcD$sp();
               double vx = weights.apply(index * this.factorSize + f) * value;
               sumSquare.elem += vx * vx;
               sum.elem += vx;
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(var9);
            }
         });
         sumVX[f] = sum.elem;
         rawPrediction.elem += (double)0.5F * (sum.elem * sum.elem - sumSquare.elem);
      });
      return new Tuple2(BoxesRunTime.boxToDouble(rawPrediction.elem), sumVX);
   }

   private Vector getRawGradient(final Vector data, final Vector weights, final double[] sumVX) {
      if (data instanceof SparseVector var6) {
         int gardSize = var6.indices().length * this.factorSize + (this.fitLinear ? var6.indices().length : 0) + (this.fitIntercept ? 1 : 0);
         int[] gradIndex = (int[])scala.Array..MODULE$.ofDim(gardSize, scala.reflect.ClassTag..MODULE$.Int());
         double[] gradValue = (double[])scala.Array..MODULE$.ofDim(gardSize, scala.reflect.ClassTag..MODULE$.Double());
         IntRef gradI = IntRef.create(0);
         int vWeightsSize = this.numFeatures * this.factorSize;
         var6.foreachNonZero((JFunction2.mcVID.sp)(x0$1, x1$1) -> {
            Tuple2.mcID.sp var10 = new Tuple2.mcID.sp(x0$1, x1$1);
            if (var10 != null) {
               int index = ((Tuple2)var10)._1$mcI$sp();
               double value = ((Tuple2)var10)._2$mcD$sp();
               scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.factorSize).foreach$mVc$sp((JFunction1.mcVI.sp)(f) -> {
                  gradIndex[gradI.elem] = index * this.factorSize + f;
                  gradValue[gradI.elem] = value * sumVX[f] - weights.apply(index * this.factorSize + f) * value * value;
                  ++gradI.elem;
               });
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(var10);
            }
         });
         if (this.fitLinear) {
            var6.foreachNonZero((JFunction2.mcVID.sp)(x0$2, x1$2) -> {
               Tuple2.mcID.sp var8 = new Tuple2.mcID.sp(x0$2, x1$2);
               if (var8 != null) {
                  int index = ((Tuple2)var8)._1$mcI$sp();
                  double value = ((Tuple2)var8)._2$mcD$sp();
                  gradIndex[gradI.elem] = vWeightsSize + index;
                  gradValue[gradI.elem] = value;
                  ++gradI.elem;
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  throw new MatchError(var8);
               }
            });
         }

         if (this.fitIntercept) {
            gradIndex[gradI.elem] = weights.size() - 1;
            gradValue[gradI.elem] = (double)1.0F;
         }

         return Vectors$.MODULE$.sparse(weights.size(), gradIndex, gradValue);
      } else if (data instanceof DenseVector var12) {
         double[] gradient = (double[])scala.Array..MODULE$.ofDim(weights.size(), scala.reflect.ClassTag..MODULE$.Double());
         int vWeightsSize = this.numFeatures * this.factorSize;
         if (this.fitIntercept) {
            int var15 = weights.size() - 1;
            int var10002 = gradient[var15]++;
         }

         if (this.fitLinear) {
            var12.foreachNonZero((JFunction2.mcVID.sp)(x0$3, x1$3) -> {
               Tuple2.mcID.sp var6 = new Tuple2.mcID.sp(x0$3, x1$3);
               if (var6 != null) {
                  int index = ((Tuple2)var6)._1$mcI$sp();
                  double value = ((Tuple2)var6)._2$mcD$sp();
                  int var10 = vWeightsSize + index;
                  gradient[var10] += value;
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  throw new MatchError(var6);
               }
            });
         }

         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.factorSize).foreach$mVc$sp((JFunction1.mcVI.sp)(f) -> var12.foreachNonZero((JFunction2.mcVID.sp)(x0$4, x1$4) -> {
               Tuple2.mcID.sp var9 = new Tuple2.mcID.sp(x0$4, x1$4);
               if (var9 != null) {
                  int index = ((Tuple2)var9)._1$mcI$sp();
                  double value = ((Tuple2)var9)._2$mcD$sp();
                  int var13 = index * this.factorSize + f;
                  gradient[var13] += value * sumVX[f] - weights.apply(index * this.factorSize + f) * value * value;
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  throw new MatchError(var9);
               }
            }));
         return Vectors$.MODULE$.dense(gradient);
      } else {
         throw new MatchError(data);
      }
   }

   public BaseFactorizationMachinesGradient(final int factorSize, final boolean fitIntercept, final boolean fitLinear, final int numFeatures) {
      this.factorSize = factorSize;
      this.fitIntercept = fitIntercept;
      this.fitLinear = fitLinear;
      this.numFeatures = numFeatures;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
