package org.apache.spark.mllib.clustering;

import org.apache.spark.ml.impl.Utils.;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u4QAD\b\u0001'eAQA\b\u0001\u0005\u0002\u0001BQA\t\u0001\u0005B\rBQ\u0001\f\u0001\u0005B5BQ\u0001\f\u0001\u0005B\u0005CQ\u0001\u0012\u0001\u0005B\u0015CQa\u000b\u0001\u0005B=CQ\u0001\u0016\u0001\u0005BUCQA\u0018\u0001\u0005B};aAY\b\t\u0002M\u0019gA\u0002\b\u0010\u0011\u0003\u0019B\rC\u0003\u001f\u0015\u0011\u0005\u0001\u000f\u0003\u0004r\u0015\u0011\u0005qB\u001d\u0005\bk*\t\t\u0011\"\u0003w\u0005a)Uo\u00197jI\u0016\fg\u000eR5ti\u0006t7-Z'fCN,(/\u001a\u0006\u0003!E\t!b\u00197vgR,'/\u001b8h\u0015\t\u00112#A\u0003nY2L'M\u0003\u0002\u0015+\u0005)1\u000f]1sW*\u0011acF\u0001\u0007CB\f7\r[3\u000b\u0003a\t1a\u001c:h'\t\u0001!\u0004\u0005\u0002\u001c95\tq\"\u0003\u0002\u001e\u001f\tyA)[:uC:\u001cW-T3bgV\u0014X-\u0001\u0004=S:LGOP\u0002\u0001)\u0005\t\u0003CA\u000e\u0001\u0003E\u0019w.\u001c9vi\u0016\u001cF/\u0019;jgRL7m\u001d\u000b\u0003I)\u0002\"!\n\u0015\u000e\u0003\u0019R\u0011aJ\u0001\u0006g\u000e\fG.Y\u0005\u0003S\u0019\u0012a\u0001R8vE2,\u0007\"B\u0016\u0003\u0001\u0004!\u0013\u0001\u00033jgR\fgnY3\u0002\u0017\u0019Lg\u000eZ\"m_N,7\u000f\u001e\u000b\u0005]Qbt\b\u0005\u0003&_E\"\u0013B\u0001\u0019'\u0005\u0019!V\u000f\u001d7feA\u0011QEM\u0005\u0003g\u0019\u00121!\u00138u\u0011\u0015)4\u00011\u00017\u0003\u001d\u0019WM\u001c;feN\u00042!J\u001c:\u0013\tAdEA\u0003BeJ\f\u0017\u0010\u0005\u0002\u001cu%\u00111h\u0004\u0002\u000f-\u0016\u001cGo\u001c:XSRDgj\u001c:n\u0011\u0015i4\u00011\u0001?\u0003)\u0019H/\u0019;jgRL7m\u001d\t\u0004K]\"\u0003\"\u0002!\u0004\u0001\u0004I\u0014!\u00029pS:$Hc\u0001\u0018C\u0007\")Q\u0007\u0002a\u0001m!)\u0001\t\u0002a\u0001s\u0005\t\u0012n]\"f]R,'oQ8om\u0016\u0014x-\u001a3\u0015\t\u0019K5*\u0014\t\u0003K\u001dK!\u0001\u0013\u0014\u0003\u000f\t{w\u000e\\3b]\")!*\u0002a\u0001s\u0005Iq\u000e\u001c3DK:$XM\u001d\u0005\u0006\u0019\u0016\u0001\r!O\u0001\n]\u0016<8)\u001a8uKJDQAT\u0003A\u0002\u0011\nq!\u001a9tS2|g\u000eF\u0002%!JCQ!\u0015\u0004A\u0002e\n!A^\u0019\t\u000bM3\u0001\u0019A\u001d\u0002\u0005Y\u0014\u0014aC2mkN$XM]\"pgR$R\u0001\n,Y5rCQaV\u0004A\u0002e\n\u0001bY3oiJ|\u0017\u000e\u001a\u0005\u00063\u001e\u0001\r!O\u0001\na>Lg\u000e^:Tk6DQaW\u0004A\u0002\u0011\n\u0011b^3jO\"$8+^7\t\u000bu;\u0001\u0019\u0001\u0013\u0002#A|\u0017N\u001c;t'F,\u0018M]3e\u001d>\u0014X.\u0001\u0003d_N$Hc\u0001\u0013aC\")\u0001\t\u0003a\u0001s!)q\u000b\u0003a\u0001s\u0005AR)^2mS\u0012,\u0017M\u001c#jgR\fgnY3NK\u0006\u001cXO]3\u0011\u0005mQ1c\u0001\u0006fQB\u0011QEZ\u0005\u0003O\u001a\u0012a!\u00118z%\u00164\u0007CA5o\u001b\u0005Q'BA6m\u0003\tIwNC\u0001n\u0003\u0011Q\u0017M^1\n\u0005=T'\u0001D*fe&\fG.\u001b>bE2,G#A2\u0002'\u0019\f7\u000f^*rk\u0006\u0014X\r\u001a#jgR\fgnY3\u0015\u0007\u0011\u001aH\u000fC\u0003R\u0019\u0001\u0007\u0011\bC\u0003T\u0019\u0001\u0007\u0011(\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001x!\tA80D\u0001z\u0015\tQH.\u0001\u0003mC:<\u0017B\u0001?z\u0005\u0019y%M[3di\u0002"
)
public class EuclideanDistanceMeasure extends DistanceMeasure {
   public double computeStatistics(final double distance) {
      return (double)0.25F * distance * distance;
   }

   public Tuple2 findClosest(final VectorWithNorm[] centers, final double[] statistics, final VectorWithNorm point) {
      double bestDistance = EuclideanDistanceMeasure$.MODULE$.fastSquaredDistance(centers[0], point);
      if (bestDistance < statistics[0]) {
         return new Tuple2.mcID.sp(0, bestDistance);
      } else {
         int k = centers.length;
         int bestIndex = 0;

         for(int i = 1; i < k; ++i) {
            VectorWithNorm center = centers[i];
            double normDiff = center.norm() - point.norm();
            double lowerBound = normDiff * normDiff;
            if (lowerBound < bestDistance) {
               int index1 = .MODULE$.indexUpperTriangular(k, i, bestIndex);
               if (statistics[index1] < bestDistance) {
                  double d = EuclideanDistanceMeasure$.MODULE$.fastSquaredDistance(center, point);
                  int index2 = .MODULE$.indexUpperTriangular(k, i, i);
                  if (d < statistics[index2]) {
                     return new Tuple2.mcID.sp(i, d);
                  }

                  if (d < bestDistance) {
                     bestDistance = d;
                     bestIndex = i;
                  }
               }
            }
         }

         return new Tuple2.mcID.sp(bestIndex, bestDistance);
      }
   }

   public Tuple2 findClosest(final VectorWithNorm[] centers, final VectorWithNorm point) {
      double bestDistance = Double.POSITIVE_INFINITY;
      int bestIndex = 0;

      for(int i = 0; i < centers.length; ++i) {
         VectorWithNorm center = centers[i];
         double lowerBoundOfSqDist = center.norm() - point.norm();
         lowerBoundOfSqDist *= lowerBoundOfSqDist;
         if (lowerBoundOfSqDist < bestDistance) {
            double distance = EuclideanDistanceMeasure$.MODULE$.fastSquaredDistance(center, point);
            if (distance < bestDistance) {
               bestDistance = distance;
               bestIndex = i;
            }
         }
      }

      return new Tuple2.mcID.sp(bestIndex, bestDistance);
   }

   public boolean isCenterConverged(final VectorWithNorm oldCenter, final VectorWithNorm newCenter, final double epsilon) {
      return EuclideanDistanceMeasure$.MODULE$.fastSquaredDistance(newCenter, oldCenter) <= epsilon * epsilon;
   }

   public double distance(final VectorWithNorm v1, final VectorWithNorm v2) {
      return Math.sqrt(EuclideanDistanceMeasure$.MODULE$.fastSquaredDistance(v1, v2));
   }

   public double clusterCost(final VectorWithNorm centroid, final VectorWithNorm pointsSum, final double weightSum, final double pointsSquaredNorm) {
      return scala.math.package..MODULE$.max(pointsSquaredNorm - weightSum * centroid.norm() * centroid.norm(), (double)0.0F);
   }

   public double cost(final VectorWithNorm point, final VectorWithNorm centroid) {
      return EuclideanDistanceMeasure$.MODULE$.fastSquaredDistance(point, centroid);
   }
}
