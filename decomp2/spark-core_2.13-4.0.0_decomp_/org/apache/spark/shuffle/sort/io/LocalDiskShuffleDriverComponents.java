package org.apache.spark.shuffle.sort.io;

import java.util.Collections;
import java.util.Map;
import org.apache.spark.SparkEnv;
import org.apache.spark.shuffle.api.ShuffleDriverComponents;
import org.apache.spark.storage.BlockManagerMaster;

public class LocalDiskShuffleDriverComponents implements ShuffleDriverComponents {
   private BlockManagerMaster blockManagerMaster;

   public Map initializeApplication() {
      this.blockManagerMaster = SparkEnv.get().blockManager().master();
      return Collections.emptyMap();
   }

   public void cleanupApplication() {
   }

   public void removeShuffle(int shuffleId, boolean blocking) {
      if (this.blockManagerMaster == null) {
         throw new IllegalStateException("Driver components must be initialized before using");
      } else {
         this.blockManagerMaster.removeShuffle(shuffleId, blocking);
      }
   }
}
