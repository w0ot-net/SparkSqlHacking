package org.apache.commons.math3.ml.clustering.evaluation;

import java.util.List;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

public class SumOfClusterVariances extends ClusterEvaluator {
   public SumOfClusterVariances(DistanceMeasure measure) {
      super(measure);
   }

   public double score(List clusters) {
      double varianceSum = (double)0.0F;

      for(Cluster cluster : clusters) {
         if (!cluster.getPoints().isEmpty()) {
            Clusterable center = this.centroidOf(cluster);
            Variance stat = new Variance();

            for(Clusterable point : cluster.getPoints()) {
               stat.increment(this.distance(point, center));
            }

            varianceSum += stat.getResult();
         }
      }

      return varianceSum;
   }
}
