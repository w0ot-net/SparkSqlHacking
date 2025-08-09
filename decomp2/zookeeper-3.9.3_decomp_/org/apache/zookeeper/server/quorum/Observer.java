package org.apache.zookeeper.server.quorum;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.jute.Record;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.server.ObserverBean;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.TxnLogEntry;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;
import org.apache.zookeeper.server.util.SerializeUtils;
import org.apache.zookeeper.txn.SetDataTxn;
import org.apache.zookeeper.txn.TxnDigest;
import org.apache.zookeeper.txn.TxnHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Observer extends Learner {
   private static final Logger LOG = LoggerFactory.getLogger(Observer.class);
   public static final String OBSERVER_RECONNECT_DELAY_MS = "zookeeper.observer.reconnectDelayMs";
   public static final String OBSERVER_ELECTION_DELAY_MS = "zookeeper.observer.election.DelayMs";
   private static final long reconnectDelayMs = Long.getLong("zookeeper.observer.reconnectDelayMs", 0L);
   private static volatile long observerElectionDelayMs;
   private static final AtomicReference nextLearnerMaster;
   private QuorumPeer.QuorumServer currentLearnerMaster = null;

   Observer(QuorumPeer self, ObserverZooKeeperServer observerZooKeeperServer) {
      this.self = self;
      this.zk = observerZooKeeperServer;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Observer ").append(this.sock);
      sb.append(" pendingRevalidationCount:").append(this.pendingRevalidations.size());
      return sb.toString();
   }

   void observeLeader() throws Exception {
      this.zk.registerJMX(new ObserverBean(this, this.zk), this.self.jmxLocalPeerBean);
      long connectTime = 0L;
      boolean completedSync = false;
      boolean var17 = false;

      try {
         var17 = true;
         this.self.setZabState(QuorumPeer.ZabState.DISCOVERY);
         QuorumPeer.QuorumServer master = this.findLearnerMaster();

         try {
            this.connectToLeader(master.addr, master.hostname);
            connectTime = System.currentTimeMillis();
            long newLeaderZxid = this.registerWithLeader(16);
            if (this.self.isReconfigStateChange()) {
               throw new Exception("learned about role change");
            }

            long startTime = Time.currentElapsedTime();
            this.self.setLeaderAddressAndId(master.addr, master.getId());
            this.self.setZabState(QuorumPeer.ZabState.SYNCHRONIZATION);
            this.syncWithLeader(newLeaderZxid);
            this.self.setZabState(QuorumPeer.ZabState.BROADCAST);
            completedSync = true;
            long syncTime = Time.currentElapsedTime() - startTime;
            ServerMetrics.getMetrics().OBSERVER_SYNC_TIME.add(syncTime);
            QuorumPacket qp = new QuorumPacket();

            while(true) {
               if (!this.isRunning()) {
                  var17 = false;
                  break;
               }

               if (nextLearnerMaster.get() != null) {
                  var17 = false;
                  break;
               }

               this.readPacket(qp);
               this.processPacket(qp);
            }
         } catch (Exception e) {
            LOG.warn("Exception when observing the leader", e);
            this.closeSocket();
            this.pendingRevalidations.clear();
            var17 = false;
         }
      } finally {
         if (var17) {
            this.currentLearnerMaster = null;
            this.zk.unregisterJMX(this);
            if (connectTime != 0L) {
               long connectionDuration = System.currentTimeMillis() - connectTime;
               LOG.info("Disconnected from leader (with address: {}). Was connected for {}ms. Sync state: {}", new Object[]{this.leaderAddr, connectionDuration, completedSync});
               this.messageTracker.dumpToLog(this.leaderAddr.toString());
            }

         }
      }

      this.currentLearnerMaster = null;
      this.zk.unregisterJMX(this);
      if (connectTime != 0L) {
         long connectionDuration = System.currentTimeMillis() - connectTime;
         LOG.info("Disconnected from leader (with address: {}). Was connected for {}ms. Sync state: {}", new Object[]{this.leaderAddr, connectionDuration, completedSync});
         this.messageTracker.dumpToLog(this.leaderAddr.toString());
      }

   }

   private QuorumPeer.QuorumServer findLearnerMaster() {
      QuorumPeer.QuorumServer prescribedLearnerMaster = (QuorumPeer.QuorumServer)nextLearnerMaster.getAndSet((Object)null);
      if (prescribedLearnerMaster != null && this.self.validateLearnerMaster(Long.toString(prescribedLearnerMaster.id)) == null) {
         LOG.warn("requested next learner master {} is no longer valid", prescribedLearnerMaster);
         prescribedLearnerMaster = null;
      }

      QuorumPeer.QuorumServer master = prescribedLearnerMaster == null ? this.self.findLearnerMaster(this.findLeader()) : prescribedLearnerMaster;
      this.currentLearnerMaster = master;
      if (master == null) {
         LOG.warn("No learner master found");
      } else {
         LOG.info("Observing new leader sid={} addr={}", master.id, master.addr);
      }

      return master;
   }

   protected void processPacket(QuorumPacket qp) throws Exception {
      switch (qp.getType()) {
         case 2:
            LOG.warn("Ignoring proposal");
            break;
         case 3:
         case 9:
         case 10:
         case 11:
         case 13:
         case 14:
         case 15:
         case 16:
         case 17:
         case 18:
         default:
            LOG.warn("Unknown packet type: {}", LearnerHandler.packetToString(qp));
            break;
         case 4:
            LOG.warn("Ignoring commit");
            break;
         case 5:
            this.ping(qp);
            break;
         case 6:
            this.revalidate(qp);
            break;
         case 7:
            ((ObserverZooKeeperServer)this.zk).sync();
            break;
         case 8:
            ServerMetrics.getMetrics().LEARNER_COMMIT_RECEIVED_COUNT.add(1L);
            TxnLogEntry logEntry = SerializeUtils.deserializeTxn(qp.getData());
            TxnHeader hdr = logEntry.getHeader();
            Record txn = logEntry.getTxn();
            TxnDigest digest = logEntry.getDigest();
            Request request = new Request(hdr.getClientId(), hdr.getCxid(), hdr.getType(), hdr, txn, 0L);
            request.logLatency(ServerMetrics.getMetrics().COMMIT_PROPAGATION_LATENCY);
            request.setTxnDigest(digest);
            ObserverZooKeeperServer obs = (ObserverZooKeeperServer)this.zk;
            obs.commitRequest(request);
            break;
         case 12:
            LOG.error("Received an UPTODATE message after Observer started");
            break;
         case 19:
            ByteBuffer buffer = ByteBuffer.wrap(qp.getData());
            long suggestedLeaderId = buffer.getLong();
            byte[] remainingdata = new byte[buffer.remaining()];
            buffer.get(remainingdata);
            TxnLogEntry logEntry = SerializeUtils.deserializeTxn(remainingdata);
            TxnHeader hdr = logEntry.getHeader();
            Record txn = logEntry.getTxn();
            TxnDigest digest = logEntry.getDigest();
            QuorumVerifier qv = this.self.configFromString(new String(((SetDataTxn)txn).getData(), StandardCharsets.UTF_8));
            Request request = new Request(hdr.getClientId(), hdr.getCxid(), hdr.getType(), hdr, txn, 0L);
            request.setTxnDigest(digest);
            ObserverZooKeeperServer obs = (ObserverZooKeeperServer)this.zk;
            boolean majorChange = this.self.processReconfig(qv, suggestedLeaderId, qp.getZxid(), true);
            obs.commitRequest(request);
            if (majorChange) {
               throw new Exception("changes proposed in reconfig");
            }
      }

   }

   public void shutdown() {
      LOG.info("shutdown Observer");
      super.shutdown();
   }

   static void waitForReconnectDelay() {
      waitForReconnectDelayHelper(reconnectDelayMs);
   }

   static void waitForObserverElectionDelay() {
      waitForReconnectDelayHelper(observerElectionDelayMs);
   }

   private static void waitForReconnectDelayHelper(long delayValueMs) {
      if (delayValueMs > 0L) {
         long randomDelay = ThreadLocalRandom.current().nextLong(delayValueMs);
         LOG.info("Waiting for {} ms before reconnecting with the leader", randomDelay);

         try {
            Thread.sleep(randomDelay);
         } catch (InterruptedException e) {
            LOG.warn("Interrupted while waiting", e);
         }
      }

   }

   public long getLearnerMasterId() {
      QuorumPeer.QuorumServer current = this.currentLearnerMaster;
      return current == null ? -1L : current.id;
   }

   public boolean setLearnerMaster(String learnerMaster) {
      QuorumPeer.QuorumServer server = this.self.validateLearnerMaster(learnerMaster);
      if (server == null) {
         return false;
      } else if (server.equals(this.currentLearnerMaster)) {
         LOG.info("Already connected to requested learner master sid={} addr={}", server.id, server.addr);
         return true;
      } else {
         LOG.info("Requesting disconnect and reconnect to new learner master sid={} addr={}", server.id, server.addr);
         nextLearnerMaster.set(server);
         return true;
      }
   }

   public QuorumPeer.QuorumServer getCurrentLearnerMaster() {
      return this.currentLearnerMaster;
   }

   public static long getObserverElectionDelayMs() {
      return observerElectionDelayMs;
   }

   public static void setObserverElectionDelayMs(long electionDelayMs) {
      observerElectionDelayMs = electionDelayMs;
      LOG.info("{} = {}", "zookeeper.observer.election.DelayMs", observerElectionDelayMs);
   }

   static {
      LOG.info("{} = {}", "zookeeper.observer.reconnectDelayMs", reconnectDelayMs);
      observerElectionDelayMs = Long.getLong("zookeeper.observer.election.DelayMs", 200L);
      LOG.info("{} = {}", "zookeeper.observer.election.DelayMs", observerElectionDelayMs);
      nextLearnerMaster = new AtomicReference();
   }
}
