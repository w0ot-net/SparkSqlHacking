package org.apache.zookeeper.server.quorum;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.management.JMException;
import org.apache.jute.Record;
import org.apache.zookeeper.jmx.MBeanRegistry;
import org.apache.zookeeper.metrics.MetricsContext;
import org.apache.zookeeper.server.ExitCode;
import org.apache.zookeeper.server.FinalRequestProcessor;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.RequestProcessor;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.SyncRequestProcessor;
import org.apache.zookeeper.server.ZKDatabase;
import org.apache.zookeeper.server.persistence.FileTxnSnapLog;
import org.apache.zookeeper.txn.TxnDigest;
import org.apache.zookeeper.txn.TxnHeader;
import org.apache.zookeeper.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FollowerZooKeeperServer extends LearnerZooKeeperServer {
   private static final Logger LOG = LoggerFactory.getLogger(FollowerZooKeeperServer.class);
   ConcurrentLinkedQueue pendingSyncs = new ConcurrentLinkedQueue();
   LinkedBlockingQueue pendingTxns = new LinkedBlockingQueue();

   FollowerZooKeeperServer(FileTxnSnapLog logFactory, QuorumPeer self, ZKDatabase zkDb) throws IOException {
      super(logFactory, self.tickTime, self.minSessionTimeout, self.maxSessionTimeout, self.clientPortListenBacklog, zkDb, self);
   }

   public Follower getFollower() {
      return this.self.follower;
   }

   protected void setupRequestProcessors() {
      RequestProcessor finalProcessor = new FinalRequestProcessor(this);
      this.commitProcessor = new CommitProcessor(finalProcessor, Long.toString(this.getServerId()), true, this.getZooKeeperServerListener());
      this.commitProcessor.start();
      this.firstProcessor = new FollowerRequestProcessor(this, this.commitProcessor);
      ((FollowerRequestProcessor)this.firstProcessor).start();
      this.syncProcessor = new SyncRequestProcessor(this, new SendAckRequestProcessor(this.getFollower()));
      this.syncProcessor.start();
   }

   public void logRequest(TxnHeader hdr, Record txn, TxnDigest digest) {
      Request request = this.buildRequestToProcess(hdr, txn, digest);
      this.syncProcessor.processRequest(request);
   }

   public void appendRequest(TxnHeader hdr, Record txn, TxnDigest digest) throws IOException {
      Request request = new Request(hdr.getClientId(), hdr.getCxid(), hdr.getType(), hdr, txn, hdr.getZxid());
      request.setTxnDigest(digest);
      this.getZKDatabase().append(request);
   }

   public void commit(long zxid) {
      if (this.pendingTxns.size() == 0) {
         LOG.warn("Committing " + Long.toHexString(zxid) + " without seeing txn");
      } else {
         long firstElementZxid = ((Request)this.pendingTxns.element()).zxid;
         if (firstElementZxid != zxid) {
            LOG.error("Committing zxid 0x" + Long.toHexString(zxid) + " but next pending txn 0x" + Long.toHexString(firstElementZxid));
            ServiceUtils.requestSystemExit(ExitCode.UNMATCHED_TXN_COMMIT.getValue());
         }

         Request request = (Request)this.pendingTxns.remove();
         request.logLatency(ServerMetrics.getMetrics().COMMIT_PROPAGATION_LATENCY);
         this.commitProcessor.commit(request);
      }
   }

   public synchronized void sync() {
      if (this.pendingSyncs.size() == 0) {
         LOG.warn("Not expecting a sync.");
      } else {
         Request r = (Request)this.pendingSyncs.remove();
         if (r instanceof LearnerSyncRequest) {
            LearnerSyncRequest lsr = (LearnerSyncRequest)r;
            lsr.fh.queuePacket(new QuorumPacket(7, 0L, (byte[])null, (List)null));
         }

         this.commitProcessor.commit(r);
      }
   }

   public int getGlobalOutstandingLimit() {
      int divisor = this.self.getQuorumSize() > 2 ? this.self.getQuorumSize() - 1 : 1;
      int globalOutstandingLimit = super.getGlobalOutstandingLimit() / divisor;
      return globalOutstandingLimit;
   }

   public String getState() {
      return "follower";
   }

   public Learner getLearner() {
      return this.getFollower();
   }

   void processObserverRequest(Request request) {
      ((FollowerRequestProcessor)this.firstProcessor).processRequest(request, false);
   }

   boolean registerJMX(LearnerHandlerBean handlerBean) {
      try {
         MBeanRegistry.getInstance().register(handlerBean, this.jmxServerBean);
         return true;
      } catch (JMException e) {
         LOG.warn("Could not register connection", e);
         return false;
      }
   }

   protected void registerMetrics() {
      super.registerMetrics();
      MetricsContext rootContext = ServerMetrics.getMetrics().getMetricsProvider().getRootContext();
      QuorumPeer var10002 = this.self;
      Objects.requireNonNull(var10002);
      rootContext.registerGauge("synced_observers", var10002::getSynced_observers_metric);
   }

   protected void unregisterMetrics() {
      super.unregisterMetrics();
      MetricsContext rootContext = ServerMetrics.getMetrics().getMetricsProvider().getRootContext();
      rootContext.unregisterGauge("synced_observers");
   }

   private Request buildRequestToProcess(TxnHeader hdr, Record txn, TxnDigest digest) {
      Request request = new Request(hdr.getClientId(), hdr.getCxid(), hdr.getType(), hdr, txn, hdr.getZxid());
      request.setTxnDigest(digest);
      if ((request.zxid & 4294967295L) != 0L) {
         this.pendingTxns.add(request);
      }

      return request;
   }
}
