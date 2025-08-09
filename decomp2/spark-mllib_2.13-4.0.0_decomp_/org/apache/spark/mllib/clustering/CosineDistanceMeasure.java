package org.apache.spark.mllib.clustering;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import scala.MatchError;
import scala.Tuple2;
import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114Q!\u0003\u0006\u0001\u001dQAQ!\u0007\u0001\u0005\u0002mAQ!\b\u0001\u0005ByAQa\n\u0001\u0005\u0002!BQA\n\u0001\u0005BqBQ!\u0011\u0001\u0005B\tCQa\u0014\u0001\u0005BACQ\u0001\u0016\u0001\u0005BUCQ\u0001\u0018\u0001\u0005Bu\u0013QcQ8tS:,G)[:uC:\u001cW-T3bgV\u0014XM\u0003\u0002\f\u0019\u0005Q1\r\\;ti\u0016\u0014\u0018N\\4\u000b\u00055q\u0011!B7mY&\u0014'BA\b\u0011\u0003\u0015\u0019\b/\u0019:l\u0015\t\t\"#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002'\u0005\u0019qN]4\u0014\u0005\u0001)\u0002C\u0001\f\u0018\u001b\u0005Q\u0011B\u0001\r\u000b\u0005=!\u0015n\u001d;b]\u000e,W*Z1tkJ,\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003q\u0001\"A\u0006\u0001\u0002#\r|W\u000e];uKN#\u0018\r^5ti&\u001c7\u000f\u0006\u0002 KA\u0011\u0001eI\u0007\u0002C)\t!%A\u0003tG\u0006d\u0017-\u0003\u0002%C\t1Ai\\;cY\u0016DQA\n\u0002A\u0002}\t\u0001\u0002Z5ti\u0006t7-Z\u0001\fM&tGm\u00117pg\u0016\u001cH\u000f\u0006\u0003*_]R\u0004\u0003\u0002\u0011+Y}I!aK\u0011\u0003\rQ+\b\u000f\\33!\t\u0001S&\u0003\u0002/C\t\u0019\u0011J\u001c;\t\u000bA\u001a\u0001\u0019A\u0019\u0002\u000f\r,g\u000e^3sgB\u0019\u0001E\r\u001b\n\u0005M\n#!B!se\u0006L\bC\u0001\f6\u0013\t1$B\u0001\bWK\u000e$xN],ji\"tuN]7\t\u000ba\u001a\u0001\u0019A\u001d\u0002\u0015M$\u0018\r^5ti&\u001c7\u000fE\u0002!e}AQaO\u0002A\u0002Q\nQ\u0001]8j]R$2aH\u001f@\u0011\u0015qD\u00011\u00015\u0003\t1\u0018\u0007C\u0003A\t\u0001\u0007A'\u0001\u0002we\u0005\u0001R\u000f\u001d3bi\u0016\u001cE.^:uKJ\u001cV/\u001c\u000b\u0004\u0007\u001a;\u0005C\u0001\u0011E\u0013\t)\u0015E\u0001\u0003V]&$\b\"B\u001e\u0006\u0001\u0004!\u0004\"\u0002%\u0006\u0001\u0004I\u0015aA:v[B\u0011!*T\u0007\u0002\u0017*\u0011A\nD\u0001\u0007Y&t\u0017\r\\4\n\u00059[%A\u0002,fGR|'/\u0001\u0005dK:$(o\\5e)\r!\u0014K\u0015\u0005\u0006\u0011\u001a\u0001\r!\u0013\u0005\u0006'\u001a\u0001\raH\u0001\no\u0016Lw\r\u001b;Tk6\f1b\u00197vgR,'oQ8tiR)qDV,Z5\")qj\u0002a\u0001i!)\u0001l\u0002a\u0001i\u0005I\u0001o\\5oiN\u001cV/\u001c\u0005\u0006'\u001e\u0001\ra\b\u0005\u00067\u001e\u0001\raH\u0001\u0012a>Lg\u000e^:TcV\f'/\u001a3O_Jl\u0017AE:z[6,GO]5d\u0007\u0016tGO]8jIN$BAX0bGB!\u0001E\u000b\u001b5\u0011\u0015\u0001\u0007\u00021\u0001 \u0003\u0015aWM^3m\u0011\u0015\u0011\u0007\u00021\u0001J\u0003\u0015qw.[:f\u0011\u0015y\u0005\u00021\u0001J\u0001"
)
public class CosineDistanceMeasure extends DistanceMeasure {
   public double computeStatistics(final double distance) {
      return (double)1 - .MODULE$.sqrt((double)1 - distance / (double)2);
   }

   public Tuple2 findClosest(final VectorWithNorm[] centers, final double[] statistics, final VectorWithNorm point) {
      double bestDistance = this.distance(centers[0], point);
      if (bestDistance < statistics[0]) {
         return new Tuple2.mcID.sp(0, bestDistance);
      } else {
         int k = centers.length;
         int bestIndex = 0;

         for(int i = 1; i < k; ++i) {
            int index1 = org.apache.spark.ml.impl.Utils..MODULE$.indexUpperTriangular(k, i, bestIndex);
            if (statistics[index1] < bestDistance) {
               VectorWithNorm center = centers[i];
               double d = this.distance(center, point);
               int index2 = org.apache.spark.ml.impl.Utils..MODULE$.indexUpperTriangular(k, i, i);
               if (d < statistics[index2]) {
                  return new Tuple2.mcID.sp(i, d);
               }

               if (d < bestDistance) {
                  bestDistance = d;
                  bestIndex = i;
               }
            }
         }

         return new Tuple2.mcID.sp(bestIndex, bestDistance);
      }
   }

   public double distance(final VectorWithNorm v1, final VectorWithNorm v2) {
      scala.Predef..MODULE$.assert(v1.norm() > (double)0 && v2.norm() > (double)0, () -> "Cosine distance is not defined for zero-length vectors.");
      return (double)1 - BLAS$.MODULE$.dot(v1.vector(), v2.vector()) / v1.norm() / v2.norm();
   }

   public void updateClusterSum(final VectorWithNorm point, final Vector sum) {
      scala.Predef..MODULE$.assert(point.norm() > (double)0, () -> "Cosine distance is not defined for zero-length vectors.");
      BLAS$.MODULE$.axpy(point.weight() / point.norm(), point.vector(), sum);
   }

   public VectorWithNorm centroid(final Vector sum, final double weightSum) {
      BLAS$.MODULE$.scal((double)1.0F / weightSum, sum);
      double norm = Vectors$.MODULE$.norm(sum, (double)2.0F);
      BLAS$.MODULE$.scal((double)1.0F / norm, sum);
      return new VectorWithNorm(sum, (double)1.0F, VectorWithNorm$.MODULE$.$lessinit$greater$default$3());
   }

   public double clusterCost(final VectorWithNorm centroid, final VectorWithNorm pointsSum, final double weightSum, final double pointsSquaredNorm) {
      Vector costVector = pointsSum.vector().copy();
      return .MODULE$.max(weightSum - BLAS$.MODULE$.dot(centroid.vector(), costVector) / centroid.norm(), (double)0.0F);
   }

   public Tuple2 symmetricCentroids(final double level, final Vector noise, final Vector centroid) {
      Tuple2 var7 = super.symmetricCentroids(level, noise, centroid);
      if (var7 != null) {
         VectorWithNorm left = (VectorWithNorm)var7._1();
         VectorWithNorm right = (VectorWithNorm)var7._2();
         Tuple2 var6 = new Tuple2(left, right);
         VectorWithNorm left = (VectorWithNorm)var6._1();
         VectorWithNorm right = (VectorWithNorm)var6._2();
         Vector leftVector = left.vector();
         Vector rightVector = right.vector();
         BLAS$.MODULE$.scal((double)1.0F / left.norm(), leftVector);
         BLAS$.MODULE$.scal((double)1.0F / right.norm(), rightVector);
         return new Tuple2(new VectorWithNorm(leftVector, (double)1.0F, VectorWithNorm$.MODULE$.$lessinit$greater$default$3()), new VectorWithNorm(rightVector, (double)1.0F, VectorWithNorm$.MODULE$.$lessinit$greater$default$3()));
      } else {
         throw new MatchError(var7);
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
