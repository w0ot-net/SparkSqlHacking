package org.apache.spark.shuffle.sort.io;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv;
import org.apache.spark.shuffle.IndexShuffleBlockResolver;
import org.apache.spark.shuffle.api.ShuffleExecutorComponents;
import org.apache.spark.shuffle.api.ShuffleMapOutputWriter;
import org.apache.spark.storage.BlockManager;
import org.sparkproject.guava.annotations.VisibleForTesting;

public class LocalDiskShuffleExecutorComponents implements ShuffleExecutorComponents {
   private final SparkConf sparkConf;
   private BlockManager blockManager;
   private IndexShuffleBlockResolver blockResolver;

   public LocalDiskShuffleExecutorComponents(SparkConf sparkConf) {
      this.sparkConf = sparkConf;
   }

   @VisibleForTesting
   public LocalDiskShuffleExecutorComponents(SparkConf sparkConf, BlockManager blockManager, IndexShuffleBlockResolver blockResolver) {
      this.sparkConf = sparkConf;
      this.blockManager = blockManager;
      this.blockResolver = blockResolver;
   }

   public void initializeExecutor(String appId, String execId, Map extraConfigs) {
      this.blockManager = SparkEnv.get().blockManager();
      if (this.blockManager == null) {
         throw new IllegalStateException("No blockManager available from the SparkEnv.");
      } else {
         this.blockResolver = new IndexShuffleBlockResolver(this.sparkConf, this.blockManager, Collections.emptyMap());
      }
   }

   public ShuffleMapOutputWriter createMapOutputWriter(int shuffleId, long mapTaskId, int numPartitions) {
      if (this.blockResolver == null) {
         throw new IllegalStateException("Executor components must be initialized before getting writers.");
      } else {
         return new LocalDiskShuffleMapOutputWriter(shuffleId, mapTaskId, numPartitions, this.blockResolver, this.sparkConf);
      }
   }

   public Optional createSingleFileMapOutputWriter(int shuffleId, long mapId) {
      if (this.blockResolver == null) {
         throw new IllegalStateException("Executor components must be initialized before getting writers.");
      } else {
         return Optional.of(new LocalDiskSingleSpillMapOutputWriter(shuffleId, mapId, this.blockResolver));
      }
   }
}
