package org.apache.spark.shuffle.sort;

import org.apache.spark.ShuffleDependency;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.config.package$;
import scala.runtime.BoxesRunTime;

public final class SortShuffleWriter$ {
   public static final SortShuffleWriter$ MODULE$ = new SortShuffleWriter$();

   public boolean shouldBypassMergeSort(final SparkConf conf, final ShuffleDependency dep) {
      if (dep.mapSideCombine()) {
         return false;
      } else {
         int bypassMergeThreshold = BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.SHUFFLE_SORT_BYPASS_MERGE_THRESHOLD()));
         return dep.partitioner().numPartitions() <= bypassMergeThreshold;
      }
   }

   private SortShuffleWriter$() {
   }
}
