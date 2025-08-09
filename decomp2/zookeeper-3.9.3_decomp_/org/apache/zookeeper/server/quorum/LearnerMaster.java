package org.apache.zookeeper.server.quorum;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.ZKDatabase;
import org.apache.zookeeper.server.quorum.auth.QuorumAuthServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LearnerMaster {
   private static final Logger LOG = LoggerFactory.getLogger(LearnerMaster.class);
   private static final String MAX_CONCURRENT_SNAPSYNCS = "zookeeper.leader.maxConcurrentSnapSyncs";
   private static final int DEFAULT_CONCURRENT_SNAPSYNCS = Integer.getInteger("zookeeper.leader.maxConcurrentSnapSyncs", 10);
   private static final String MAX_CONCURRENT_DIFF_SYNCS = "zookeeper.leader.maxConcurrentDiffSyncs";
   private static final int DEFAULT_CONCURRENT_DIFF_SYNCS;
   private volatile int maxConcurrentSnapSyncs;
   private volatile int maxConcurrentDiffSyncs;
   private final LearnerSyncThrottler learnerSnapSyncThrottler;
   private final LearnerSyncThrottler learnerDiffSyncThrottler;

   public LearnerMaster() {
      this.maxConcurrentSnapSyncs = DEFAULT_CONCURRENT_SNAPSYNCS;
      this.maxConcurrentDiffSyncs = DEFAULT_CONCURRENT_DIFF_SYNCS;
      this.learnerSnapSyncThrottler = new LearnerSyncThrottler(this.maxConcurrentSnapSyncs, LearnerSyncThrottler.SyncType.SNAP);
      this.learnerDiffSyncThrottler = new LearnerSyncThrottler(this.maxConcurrentDiffSyncs, LearnerSyncThrottler.SyncType.DIFF);
   }

   public int getMaxConcurrentSnapSyncs() {
      return this.maxConcurrentSnapSyncs;
   }

   public void setMaxConcurrentSnapSyncs(int maxConcurrentSnapSyncs) {
      LOG.info("Set maxConcurrentSnapSyncs to {}", maxConcurrentSnapSyncs);
      this.maxConcurrentSnapSyncs = maxConcurrentSnapSyncs;
      this.learnerSnapSyncThrottler.setMaxConcurrentSyncs(maxConcurrentSnapSyncs);
   }

   public int getMaxConcurrentDiffSyncs() {
      return this.maxConcurrentDiffSyncs;
   }

   public void setMaxConcurrentDiffSyncs(int maxConcurrentDiffSyncs) {
      LOG.info("Set maxConcurrentDiffSyncs to {}", maxConcurrentDiffSyncs);
      this.maxConcurrentDiffSyncs = maxConcurrentDiffSyncs;
      this.learnerDiffSyncThrottler.setMaxConcurrentSyncs(maxConcurrentDiffSyncs);
   }

   public LearnerSyncThrottler getLearnerSnapSyncThrottler() {
      return this.learnerSnapSyncThrottler;
   }

   public LearnerSyncThrottler getLearnerDiffSyncThrottler() {
      return this.learnerDiffSyncThrottler;
   }

   abstract void addLearnerHandler(LearnerHandler var1);

   abstract void removeLearnerHandler(LearnerHandler var1);

   abstract void waitForEpochAck(long var1, StateSummary var3) throws IOException, InterruptedException;

   abstract void waitForStartup() throws InterruptedException;

   abstract long getEpochToPropose(long var1, long var3) throws InterruptedException, IOException;

   abstract ZKDatabase getZKDatabase();

   abstract void waitForNewLeaderAck(long var1, long var3) throws InterruptedException;

   abstract long getLastProposed();

   abstract int getCurrentTick();

   abstract int syncTimeout();

   abstract int getTickOfNextAckDeadline();

   abstract int getTickOfInitialAckDeadline();

   abstract long getAndDecrementFollowerCounter();

   abstract void processAck(long var1, long var3, SocketAddress var5);

   abstract void touch(long var1, int var3);

   abstract void revalidateSession(QuorumPacket var1, LearnerHandler var2) throws IOException;

   abstract void submitLearnerRequest(Request var1);

   abstract long startForwarding(LearnerHandler var1, long var2);

   abstract long getQuorumVerifierVersion();

   abstract String getPeerInfo(long var1);

   abstract byte[] getQuorumVerifierBytes();

   abstract QuorumAuthServer getQuorumAuthServer();

   abstract void registerLearnerHandlerBean(LearnerHandler var1, Socket var2);

   abstract void unregisterLearnerHandlerBean(LearnerHandler var1);

   static {
      LOG.info("{} = {}", "zookeeper.leader.maxConcurrentSnapSyncs", DEFAULT_CONCURRENT_SNAPSYNCS);
      DEFAULT_CONCURRENT_DIFF_SYNCS = Integer.getInteger("zookeeper.leader.maxConcurrentDiffSyncs", 100);
      LOG.info("{} = {}", "zookeeper.leader.maxConcurrentDiffSyncs", DEFAULT_CONCURRENT_DIFF_SYNCS);
   }
}
