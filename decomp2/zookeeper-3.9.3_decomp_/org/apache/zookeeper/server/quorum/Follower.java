package org.apache.zookeeper.server.quorum;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Objects;
import org.apache.jute.Record;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.TxnLogEntry;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;
import org.apache.zookeeper.server.util.SerializeUtils;
import org.apache.zookeeper.server.util.ZxidUtils;
import org.apache.zookeeper.txn.SetDataTxn;
import org.apache.zookeeper.txn.TxnDigest;
import org.apache.zookeeper.txn.TxnHeader;

public class Follower extends Learner {
   private long lastQueued;
   final FollowerZooKeeperServer fzk;
   ObserverMaster om;

   Follower(QuorumPeer self, FollowerZooKeeperServer zk) {
      this.self = (QuorumPeer)Objects.requireNonNull(self);
      this.fzk = (FollowerZooKeeperServer)Objects.requireNonNull(zk);
      this.zk = zk;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Follower ").append(this.sock);
      sb.append(" lastQueuedZxid:").append(this.lastQueued);
      sb.append(" pendingRevalidationCount:").append(this.pendingRevalidations.size());
      return sb.toString();
   }

   void followLeader() throws InterruptedException {
      this.self.end_fle = Time.currentElapsedTime();
      long electionTimeTaken = this.self.end_fle - this.self.start_fle;
      this.self.setElectionTimeTaken(electionTimeTaken);
      ServerMetrics.getMetrics().ELECTION_TIME.add(electionTimeTaken);
      LOG.info("FOLLOWING - LEADER ELECTION TOOK - {} {}", electionTimeTaken, "MS");
      this.self.start_fle = 0L;
      this.self.end_fle = 0L;
      this.fzk.registerJMX(new FollowerBean(this, this.zk), this.self.jmxLocalPeerBean);
      long connectionTime = 0L;
      boolean completedSync = false;
      boolean var21 = false;

      try {
         var21 = true;
         this.self.setZabState(QuorumPeer.ZabState.DISCOVERY);
         QuorumPeer.QuorumServer leaderServer = this.findLeader();

         try {
            this.connectToLeader(leaderServer.addr, leaderServer.hostname);
            connectionTime = System.currentTimeMillis();
            long newEpochZxid = this.registerWithLeader(11);
            if (this.self.isReconfigStateChange()) {
               throw new Exception("learned about role change");
            }

            long newEpoch = ZxidUtils.getEpochFromZxid(newEpochZxid);
            if (newEpoch < this.self.getAcceptedEpoch()) {
               LOG.error("Proposed leader epoch " + ZxidUtils.zxidToString(newEpochZxid) + " is less than our accepted epoch " + ZxidUtils.zxidToString(this.self.getAcceptedEpoch()));
               throw new IOException("Error: Epoch of leader is lower");
            }

            long startTime = Time.currentElapsedTime();
            this.self.setLeaderAddressAndId(leaderServer.addr, leaderServer.getId());
            this.self.setZabState(QuorumPeer.ZabState.SYNCHRONIZATION);
            this.syncWithLeader(newEpochZxid);
            this.self.setZabState(QuorumPeer.ZabState.BROADCAST);
            completedSync = true;
            long syncTime = Time.currentElapsedTime() - startTime;
            ServerMetrics.getMetrics().FOLLOWER_SYNC_TIME.add(syncTime);
            if (this.self.getObserverMasterPort() > 0) {
               LOG.info("Starting ObserverMaster");
               this.om = new ObserverMaster(this.self, this.fzk, this.self.getObserverMasterPort());
               this.om.start();
            } else {
               this.om = null;
            }

            QuorumPacket qp = new QuorumPacket();

            while(this.isRunning()) {
               this.readPacket(qp);
               this.processPacket(qp);
            }

            var21 = false;
         } catch (Exception e) {
            LOG.warn("Exception when following the leader", e);
            this.closeSocket();
            this.pendingRevalidations.clear();
            var21 = false;
         }
      } finally {
         if (var21) {
            if (this.om != null) {
               this.om.stop();
            }

            this.zk.unregisterJMX(this);
            if (connectionTime != 0L) {
               long connectionDuration = System.currentTimeMillis() - connectionTime;
               LOG.info("Disconnected from leader (with address: {}). Was connected for {}ms. Sync state: {}", new Object[]{this.leaderAddr, connectionDuration, completedSync});
               this.messageTracker.dumpToLog(this.leaderAddr.toString());
            }

         }
      }

      if (this.om != null) {
         this.om.stop();
      }

      this.zk.unregisterJMX(this);
      if (connectionTime != 0L) {
         long connectionDuration = System.currentTimeMillis() - connectionTime;
         LOG.info("Disconnected from leader (with address: {}). Was connected for {}ms. Sync state: {}", new Object[]{this.leaderAddr, connectionDuration, completedSync});
         this.messageTracker.dumpToLog(this.leaderAddr.toString());
      }

   }

   protected void processPacket(QuorumPacket qp) throws Exception {
      switch (qp.getType()) {
         case 2:
            ServerMetrics.getMetrics().LEARNER_PROPOSAL_RECEIVED_COUNT.add(1L);
            TxnLogEntry logEntry = SerializeUtils.deserializeTxn(qp.getData());
            TxnHeader hdr = logEntry.getHeader();
            Record txn = logEntry.getTxn();
            TxnDigest digest = logEntry.getDigest();
            if (hdr.getZxid() != this.lastQueued + 1L) {
               LOG.warn("Got zxid 0x{} expected 0x{}", Long.toHexString(hdr.getZxid()), Long.toHexString(this.lastQueued + 1L));
            }

            this.lastQueued = hdr.getZxid();
            if (hdr.getType() == 16) {
               SetDataTxn setDataTxn = (SetDataTxn)txn;
               QuorumVerifier qv = this.self.configFromString(new String(setDataTxn.getData(), StandardCharsets.UTF_8));
               this.self.setLastSeenQuorumVerifier(qv, true);
            }

            this.fzk.logRequest(hdr, txn, digest);
            if (hdr != null) {
               long now = Time.currentWallTime();
               long latency = now - hdr.getTime();
               if (latency >= 0L) {
                  ServerMetrics.getMetrics().PROPOSAL_LATENCY.add(latency);
               }
            }

            if (this.om != null) {
               long startTime = Time.currentElapsedTime();
               this.om.proposalReceived(qp);
               ServerMetrics.getMetrics().OM_PROPOSAL_PROCESS_TIME.add(Time.currentElapsedTime() - startTime);
            }
            break;
         case 3:
         case 8:
         case 10:
         case 11:
         default:
            LOG.warn("Unknown packet type: {}", LearnerHandler.packetToString(qp));
            break;
         case 4:
            ServerMetrics.getMetrics().LEARNER_COMMIT_RECEIVED_COUNT.add(1L);
            this.fzk.commit(qp.getZxid());
            if (this.om != null) {
               long startTime = Time.currentElapsedTime();
               this.om.proposalCommitted(qp.getZxid());
               ServerMetrics.getMetrics().OM_COMMIT_PROCESS_TIME.add(Time.currentElapsedTime() - startTime);
            }
            break;
         case 5:
            this.ping(qp);
            break;
         case 6:
            if (this.om == null || !this.om.revalidateLearnerSession(qp)) {
               this.revalidate(qp);
            }
            break;
         case 7:
            this.fzk.sync();
            break;
         case 9:
            Request request = (Request)this.fzk.pendingTxns.element();
            SetDataTxn setDataTxn = (SetDataTxn)request.getTxn();
            QuorumVerifier qv = this.self.configFromString(new String(setDataTxn.getData(), StandardCharsets.UTF_8));
            ByteBuffer buffer = ByteBuffer.wrap(qp.getData());
            long suggestedLeaderId = buffer.getLong();
            long zxid = qp.getZxid();
            boolean majorChange = this.self.processReconfig(qv, suggestedLeaderId, zxid, true);
            this.fzk.commit(zxid);
            if (this.om != null) {
               this.om.informAndActivate(zxid, suggestedLeaderId);
            }

            if (majorChange) {
               throw new Exception("changes proposed in reconfig");
            }
            break;
         case 12:
            LOG.error("Received an UPTODATE message after Follower started");
      }

   }

   public long getZxid() {
      synchronized(this.fzk) {
         return this.fzk.getZxid();
      }
   }

   protected long getLastQueued() {
      return this.lastQueued;
   }

   public Integer getSyncedObserverSize() {
      return this.om == null ? null : this.om.getNumActiveObservers();
   }

   public Iterable getSyncedObserversInfo() {
      return (Iterable)(this.om != null && this.om.getNumActiveObservers() > 0 ? this.om.getActiveObservers() : Collections.emptySet());
   }

   public void resetObserverConnectionStats() {
      if (this.om != null && this.om.getNumActiveObservers() > 0) {
         this.om.resetObserverConnectionStats();
      }

   }

   public void shutdown() {
      LOG.info("shutdown Follower");
      super.shutdown();
   }
}
