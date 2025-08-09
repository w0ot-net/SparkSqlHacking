package org.apache.spark.shuffle;

import org.apache.spark.MapOutputTracker;
import org.apache.spark.SparkEnv$;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.storage.BlockManager;

public final class BlockStoreShuffleReader$ {
   public static final BlockStoreShuffleReader$ MODULE$ = new BlockStoreShuffleReader$();

   public SerializerManager $lessinit$greater$default$5() {
      return SparkEnv$.MODULE$.get().serializerManager();
   }

   public BlockManager $lessinit$greater$default$6() {
      return SparkEnv$.MODULE$.get().blockManager();
   }

   public MapOutputTracker $lessinit$greater$default$7() {
      return SparkEnv$.MODULE$.get().mapOutputTracker();
   }

   public boolean $lessinit$greater$default$8() {
      return false;
   }

   private BlockStoreShuffleReader$() {
   }
}
