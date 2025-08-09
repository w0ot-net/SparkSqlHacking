package org.apache.spark.streaming.scheduler;

import org.apache.hadoop.fs.Path;

public final class ReceivedBlockTracker$ {
   public static final ReceivedBlockTracker$ MODULE$ = new ReceivedBlockTracker$();

   public String checkpointDirToLogDir(final String checkpointDir) {
      return (new Path(checkpointDir, "receivedBlockMetadata")).toString();
   }

   private ReceivedBlockTracker$() {
   }
}
