package org.apache.spark.shuffle.sort.io;

import org.apache.spark.SparkConf;
import org.apache.spark.shuffle.api.ShuffleDataIO;
import org.apache.spark.shuffle.api.ShuffleDriverComponents;
import org.apache.spark.shuffle.api.ShuffleExecutorComponents;

public class LocalDiskShuffleDataIO implements ShuffleDataIO {
   private final SparkConf sparkConf;

   public LocalDiskShuffleDataIO(SparkConf sparkConf) {
      this.sparkConf = sparkConf;
   }

   public ShuffleExecutorComponents executor() {
      return new LocalDiskShuffleExecutorComponents(this.sparkConf);
   }

   public ShuffleDriverComponents driver() {
      return new LocalDiskShuffleDriverComponents();
   }
}
