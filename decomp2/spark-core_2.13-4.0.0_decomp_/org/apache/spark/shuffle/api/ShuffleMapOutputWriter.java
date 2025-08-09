package org.apache.spark.shuffle.api;

import java.io.IOException;
import org.apache.spark.annotation.Private;
import org.apache.spark.shuffle.api.metadata.MapOutputCommitMessage;

@Private
public interface ShuffleMapOutputWriter {
   ShufflePartitionWriter getPartitionWriter(int var1) throws IOException;

   MapOutputCommitMessage commitAllPartitions(long[] var1) throws IOException;

   void abort(Throwable var1) throws IOException;
}
