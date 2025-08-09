package org.apache.spark.network.shuffle;

import java.io.File;
import java.io.IOException;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.client.StreamCallbackWithID;
import org.apache.spark.network.shuffle.protocol.ExecutorShuffleInfo;
import org.apache.spark.network.shuffle.protocol.FinalizeShuffleMerge;
import org.apache.spark.network.shuffle.protocol.MergeStatuses;
import org.apache.spark.network.shuffle.protocol.PushBlockStream;
import org.apache.spark.network.shuffle.protocol.RemoveShuffleMerge;
import org.apache.spark.network.util.TransportConf;

public class NoOpMergedShuffleFileManager implements MergedShuffleFileManager {
   public NoOpMergedShuffleFileManager(TransportConf transportConf, File recoveryFile) {
   }

   public StreamCallbackWithID receiveBlockDataAsStream(PushBlockStream msg) {
      throw new UnsupportedOperationException("Cannot handle shuffle block merge");
   }

   public MergeStatuses finalizeShuffleMerge(FinalizeShuffleMerge msg) throws IOException {
      throw new UnsupportedOperationException("Cannot handle shuffle block merge");
   }

   public void registerExecutor(String appId, ExecutorShuffleInfo executorInfo) {
   }

   public void applicationRemoved(String appId, boolean cleanupLocalDirs) {
   }

   public ManagedBuffer getMergedBlockData(String appId, int shuffleId, int shuffleMergeId, int reduceId, int chunkId) {
      throw new UnsupportedOperationException("Cannot handle shuffle block merge");
   }

   public MergedBlockMeta getMergedBlockMeta(String appId, int shuffleId, int shuffleMergeId, int reduceId) {
      throw new UnsupportedOperationException("Cannot handle shuffle block merge");
   }

   public String[] getMergedBlockDirs(String appId) {
      throw new UnsupportedOperationException("Cannot handle shuffle block merge");
   }

   public void removeShuffleMerge(RemoveShuffleMerge removeShuffleMerge) {
      throw new UnsupportedOperationException("Cannot handle merged shuffle remove");
   }
}
