package org.apache.zookeeper.server.quorum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.MultiOperationRecord;
import org.apache.zookeeper.Op;
import org.apache.zookeeper.metrics.MetricsContext;
import org.apache.zookeeper.proto.CreateRequest;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.RequestRecord;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.ZKDatabase;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.apache.zookeeper.server.persistence.FileTxnSnapLog;
import org.apache.zookeeper.txn.CreateSessionTxn;

public abstract class QuorumZooKeeperServer extends ZooKeeperServer {
   public final QuorumPeer self;
   protected UpgradeableSessionTracker upgradeableSessionTracker;

   protected QuorumZooKeeperServer(FileTxnSnapLog logFactory, int tickTime, int minSessionTimeout, int maxSessionTimeout, int listenBacklog, ZKDatabase zkDb, QuorumPeer self) {
      super(logFactory, tickTime, minSessionTimeout, maxSessionTimeout, listenBacklog, zkDb, self.getInitialConfig(), self.isReconfigEnabled());
      this.self = self;
   }

   protected void startSessionTracker() {
      this.upgradeableSessionTracker = (UpgradeableSessionTracker)this.sessionTracker;
      this.upgradeableSessionTracker.start();
   }

   public Request checkUpgradeSession(Request request) throws IOException, KeeperException {
      if (request.isThrottled()) {
         return null;
      } else if ((request.type == 1 || request.type == 15 || request.type == 14) && this.upgradeableSessionTracker.isLocalSession(request.sessionId)) {
         if (14 == request.type) {
            MultiOperationRecord multiTransactionRecord = (MultiOperationRecord)request.readRequestRecord(MultiOperationRecord::new);
            boolean containsEphemeralCreate = false;

            for(Op op : multiTransactionRecord) {
               if (op.getType() == 1 || op.getType() == 15) {
                  CreateRequest createRequest = (CreateRequest)op.toRequestRecord();
                  CreateMode createMode = CreateMode.fromFlag(createRequest.getFlags());
                  if (createMode.isEphemeral()) {
                     containsEphemeralCreate = true;
                     break;
                  }
               }
            }

            if (!containsEphemeralCreate) {
               return null;
            }
         } else {
            CreateRequest createRequest = (CreateRequest)request.readRequestRecord(CreateRequest::new);
            CreateMode createMode = CreateMode.fromFlag(createRequest.getFlags());
            if (!createMode.isEphemeral()) {
               return null;
            }
         }

         if (!this.self.isLocalSessionsUpgradingEnabled()) {
            throw new KeeperException.EphemeralOnLocalSessionException();
         } else {
            return this.makeUpgradeRequest(request.sessionId);
         }
      } else {
         return null;
      }
   }

   private Request makeUpgradeRequest(long sessionId) {
      synchronized(this.upgradeableSessionTracker) {
         if (this.upgradeableSessionTracker.isLocalSession(sessionId)) {
            int timeout = this.upgradeableSessionTracker.upgradeSession(sessionId);
            CreateSessionTxn txn = new CreateSessionTxn(timeout);
            return new Request((ServerCnxn)null, sessionId, 0, -10, RequestRecord.fromRecord(txn), (List)null);
         } else {
            return null;
         }
      }
   }

   public void upgrade(long sessionId) {
      Request request = this.makeUpgradeRequest(sessionId);
      if (request != null) {
         LOG.info("Upgrading session 0x{}", Long.toHexString(sessionId));
         this.submitRequest(request);
      }

   }

   protected void setLocalSessionFlag(Request si) {
      switch (si.type) {
         case -11:
            String reqType = "global";
            if (this.upgradeableSessionTracker.isLocalSession(si.sessionId)) {
               si.setLocalSession(true);
               reqType = "local";
            }

            LOG.info("Submitting {} closeSession request for session 0x{}", reqType, Long.toHexString(si.sessionId));
            break;
         case -10:
            if (this.self.areLocalSessionsEnabled()) {
               si.setLocalSession(true);
            }
      }

   }

   public void dumpConf(PrintWriter pwriter) {
      super.dumpConf(pwriter);
      pwriter.print("initLimit=");
      pwriter.println(this.self.getInitLimit());
      pwriter.print("syncLimit=");
      pwriter.println(this.self.getSyncLimit());
      pwriter.print("electionAlg=");
      pwriter.println(this.self.getElectionType());
      pwriter.print("electionPort=");
      pwriter.println((String)this.self.getElectionAddress().getAllPorts().stream().map(Objects::toString).collect(Collectors.joining("|")));
      pwriter.print("quorumPort=");
      pwriter.println((String)this.self.getQuorumAddress().getAllPorts().stream().map(Objects::toString).collect(Collectors.joining("|")));
      pwriter.print("peerType=");
      pwriter.println(this.self.getLearnerType().ordinal());
      pwriter.println("membership: ");
      pwriter.print(this.self.getQuorumVerifier().toString());
   }

   protected void setState(ZooKeeperServer.State state) {
      this.state = state;
   }

   protected void registerMetrics() {
      super.registerMetrics();
      MetricsContext rootContext = ServerMetrics.getMetrics().getMetricsProvider().getRootContext();
      rootContext.registerGauge("quorum_size", () -> this.self.getQuorumSize());
   }

   protected void unregisterMetrics() {
      super.unregisterMetrics();
      MetricsContext rootContext = ServerMetrics.getMetrics().getMetricsProvider().getRootContext();
      rootContext.unregisterGauge("quorum_size");
   }

   public void dumpMonitorValues(BiConsumer response) {
      super.dumpMonitorValues(response);
      response.accept("peer_state", this.self.getDetailedPeerState());
   }
}
