package org.apache.zookeeper.server.quorum;

public class SyncThrottleException extends Exception {
   private static final long serialVersionUID = 1L;

   public SyncThrottleException(int concurrentSyncNumber, int throttleThreshold, LearnerSyncThrottler.SyncType syncType) {
      super(getMessage(concurrentSyncNumber, throttleThreshold, syncType));
   }

   private static String getMessage(int concurrentSyncNumber, int throttleThreshold, LearnerSyncThrottler.SyncType syncType) {
      return String.format("new %s sync would make %d concurrently in progress; maximum is %d", syncType.toString().toLowerCase(), concurrentSyncNumber, throttleThreshold);
   }
}
