package org.apache.zookeeper.server.quorum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LearnerSyncThrottler {
   private static final Logger LOG = LoggerFactory.getLogger(LearnerSyncThrottler.class);
   private final Object countSyncObject = new Object();
   private int syncInProgress;
   private volatile int maxConcurrentSyncs;
   private final SyncType syncType;

   public LearnerSyncThrottler(int maxConcurrentSyncs, SyncType syncType) throws IllegalArgumentException {
      if (maxConcurrentSyncs <= 0) {
         String errorMsg = "maxConcurrentSyncs must be positive, was " + maxConcurrentSyncs;
         throw new IllegalArgumentException(errorMsg);
      } else {
         this.maxConcurrentSyncs = maxConcurrentSyncs;
         this.syncType = syncType;
         synchronized(this.countSyncObject) {
            this.syncInProgress = 0;
         }
      }
   }

   protected void beginSync(boolean essential) throws SyncThrottleException, InterruptedException {
      synchronized(this.countSyncObject) {
         if (!essential && this.syncInProgress >= this.maxConcurrentSyncs) {
            throw new SyncThrottleException(this.syncInProgress + 1, this.maxConcurrentSyncs, this.syncType);
         } else {
            ++this.syncInProgress;
         }
      }
   }

   public void endSync() {
      int newCount;
      synchronized(this.countSyncObject) {
         --this.syncInProgress;
         newCount = this.syncInProgress;
         this.countSyncObject.notify();
      }

      if (newCount < 0) {
         String errorMsg = "endSync() called incorrectly; current sync count is " + newCount;
         LOG.error(errorMsg);
      }

   }

   public void setMaxConcurrentSyncs(int maxConcurrentSyncs) {
      this.maxConcurrentSyncs = maxConcurrentSyncs;
   }

   public int getSyncInProgress() {
      return this.syncInProgress;
   }

   public static enum SyncType {
      DIFF,
      SNAP;
   }
}
