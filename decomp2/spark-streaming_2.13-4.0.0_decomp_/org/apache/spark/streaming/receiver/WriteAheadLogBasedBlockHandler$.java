package org.apache.spark.streaming.receiver;

import org.apache.hadoop.fs.Path;
import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;

public final class WriteAheadLogBasedBlockHandler$ {
   public static final WriteAheadLogBasedBlockHandler$ MODULE$ = new WriteAheadLogBasedBlockHandler$();

   public Clock $lessinit$greater$default$8() {
      return new SystemClock();
   }

   public String checkpointDirToLogDir(final String checkpointDir, final int streamId) {
      return (new Path(checkpointDir, new Path("receivedData", Integer.toString(streamId)))).toString();
   }

   private WriteAheadLogBasedBlockHandler$() {
   }
}
