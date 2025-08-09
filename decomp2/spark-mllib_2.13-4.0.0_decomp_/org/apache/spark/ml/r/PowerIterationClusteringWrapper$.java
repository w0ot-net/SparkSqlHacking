package org.apache.spark.ml.r;

import org.apache.spark.ml.clustering.PowerIterationClustering;
import scala.runtime.BoxedUnit;

public final class PowerIterationClusteringWrapper$ {
   public static final PowerIterationClusteringWrapper$ MODULE$ = new PowerIterationClusteringWrapper$();

   public PowerIterationClustering getPowerIterationClustering(final int k, final String initMode, final int maxIter, final String srcCol, final String dstCol, final String weightCol) {
      PowerIterationClustering pic = (new PowerIterationClustering()).setK(k).setInitMode(initMode).setMaxIter(maxIter).setSrcCol(srcCol).setDstCol(dstCol);
      if (weightCol != null) {
         pic.setWeightCol(weightCol);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return pic;
   }

   private PowerIterationClusteringWrapper$() {
   }
}
