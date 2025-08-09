package org.apache.spark.ml.r;

import org.apache.spark.ml.recommendation.ALS;
import org.apache.spark.ml.recommendation.ALSModel;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;

public final class ALSWrapper$ implements MLReadable {
   public static final ALSWrapper$ MODULE$ = new ALSWrapper$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public ALSWrapper fit(final Dataset data, final String ratingCol, final String userCol, final String itemCol, final int rank, final double regParam, final int maxIter, final boolean implicitPrefs, final double alpha, final boolean nonnegative, final int numUserBlocks, final int numItemBlocks, final int checkpointInterval, final int seed) {
      ALS als = (new ALS()).setRatingCol(ratingCol).setUserCol(userCol).setItemCol(itemCol).setRank(rank).setRegParam(regParam).setMaxIter(maxIter).setImplicitPrefs(implicitPrefs).setAlpha(alpha).setNonnegative(nonnegative).setNumBlocks(numUserBlocks).setNumItemBlocks(numItemBlocks).setCheckpointInterval(checkpointInterval).setSeed((long)seed);
      ALSModel alsModel = als.fit(data);
      return new ALSWrapper(alsModel, ratingCol);
   }

   public MLReader read() {
      return new ALSWrapper.ALSWrapperReader();
   }

   public ALSWrapper load(final String path) {
      return (ALSWrapper)MLReadable.load$(this, path);
   }

   private ALSWrapper$() {
   }
}
