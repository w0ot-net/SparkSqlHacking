package org.apache.commons.math3.ml.clustering;

import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.ml.clustering.evaluation.ClusterEvaluator;
import org.apache.commons.math3.ml.clustering.evaluation.SumOfClusterVariances;

public class MultiKMeansPlusPlusClusterer extends Clusterer {
   private final KMeansPlusPlusClusterer clusterer;
   private final int numTrials;
   private final ClusterEvaluator evaluator;

   public MultiKMeansPlusPlusClusterer(KMeansPlusPlusClusterer clusterer, int numTrials) {
      this(clusterer, numTrials, new SumOfClusterVariances(clusterer.getDistanceMeasure()));
   }

   public MultiKMeansPlusPlusClusterer(KMeansPlusPlusClusterer clusterer, int numTrials, ClusterEvaluator evaluator) {
      super(clusterer.getDistanceMeasure());
      this.clusterer = clusterer;
      this.numTrials = numTrials;
      this.evaluator = evaluator;
   }

   public KMeansPlusPlusClusterer getClusterer() {
      return this.clusterer;
   }

   public int getNumTrials() {
      return this.numTrials;
   }

   public ClusterEvaluator getClusterEvaluator() {
      return this.evaluator;
   }

   public List cluster(Collection points) throws MathIllegalArgumentException, ConvergenceException {
      List<CentroidCluster<T>> best = null;
      double bestVarianceSum = Double.POSITIVE_INFINITY;

      for(int i = 0; i < this.numTrials; ++i) {
         List<CentroidCluster<T>> clusters = this.clusterer.cluster(points);
         double varianceSum = this.evaluator.score(clusters);
         if (this.evaluator.isBetterScore(varianceSum, bestVarianceSum)) {
            best = clusters;
            bestVarianceSum = varianceSum;
         }
      }

      return best;
   }
}
