package org.apache.spark.network.shuffle;

import com.codahale.metrics.MetricSet;
import java.io.IOException;
import java.util.Collections;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.client.StreamCallbackWithID;
import org.apache.spark.network.shuffle.protocol.ExecutorShuffleInfo;
import org.apache.spark.network.shuffle.protocol.FinalizeShuffleMerge;
import org.apache.spark.network.shuffle.protocol.MergeStatuses;
import org.apache.spark.network.shuffle.protocol.PushBlockStream;
import org.apache.spark.network.shuffle.protocol.RemoveShuffleMerge;

@Evolving
public interface MergedShuffleFileManager {
   StreamCallbackWithID receiveBlockDataAsStream(PushBlockStream var1);

   MergeStatuses finalizeShuffleMerge(FinalizeShuffleMerge var1) throws IOException;

   void registerExecutor(String var1, ExecutorShuffleInfo var2);

   void applicationRemoved(String var1, boolean var2);

   ManagedBuffer getMergedBlockData(String var1, int var2, int var3, int var4, int var5);

   MergedBlockMeta getMergedBlockMeta(String var1, int var2, int var3, int var4);

   String[] getMergedBlockDirs(String var1);

   void removeShuffleMerge(RemoveShuffleMerge var1);

   default void close() {
   }

   default MetricSet getMetrics() {
      return Collections::emptyMap;
   }
}
