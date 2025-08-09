package org.apache.zookeeper.server.quorum;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import javax.management.JMException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.jmx.MBeanRegistry;
import org.apache.zookeeper.metrics.Gauge;
import org.apache.zookeeper.metrics.MetricsContext;
import org.apache.zookeeper.server.ContainerManager;
import org.apache.zookeeper.server.DataTreeBean;
import org.apache.zookeeper.server.FinalRequestProcessor;
import org.apache.zookeeper.server.PrepRequestProcessor;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.RequestProcessor;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.ZKDatabase;
import org.apache.zookeeper.server.persistence.FileTxnSnapLog;

public class LeaderZooKeeperServer extends QuorumZooKeeperServer {
   private ContainerManager containerManager;
   CommitProcessor commitProcessor;
   PrepRequestProcessor prepRequestProcessor;

   public LeaderZooKeeperServer(FileTxnSnapLog logFactory, QuorumPeer self, ZKDatabase zkDb) throws IOException {
      super(logFactory, self.tickTime, self.minSessionTimeout, self.maxSessionTimeout, self.clientPortListenBacklog, zkDb, self);
   }

   public Leader getLeader() {
      return this.self.leader;
   }

   protected void setupRequestProcessors() {
      RequestProcessor finalProcessor = new FinalRequestProcessor(this);
      RequestProcessor toBeAppliedProcessor = new Leader.ToBeAppliedRequestProcessor(finalProcessor, this.getLeader());
      this.commitProcessor = new CommitProcessor(toBeAppliedProcessor, Long.toString(this.getServerId()), false, this.getZooKeeperServerListener());
      this.commitProcessor.start();
      ProposalRequestProcessor proposalProcessor = new ProposalRequestProcessor(this, this.commitProcessor);
      proposalProcessor.initialize();
      this.prepRequestProcessor = new PrepRequestProcessor(this, proposalProcessor);
      this.prepRequestProcessor.start();
      this.firstProcessor = new LeaderRequestProcessor(this, this.prepRequestProcessor);
      this.setupContainerManager();
   }

   private synchronized void setupContainerManager() {
      this.containerManager = new ContainerManager(this.getZKDatabase(), this.prepRequestProcessor, Integer.getInteger("znode.container.checkIntervalMs", (int)TimeUnit.MINUTES.toMillis(1L)), Integer.getInteger("znode.container.maxPerMinute", 10000), Long.getLong("znode.container.maxNeverUsedIntervalMs", 0L));
   }

   public synchronized void startup() {
      super.startup();
      if (this.containerManager != null) {
         this.containerManager.start();
      }

   }

   protected void registerMetrics() {
      super.registerMetrics();
      MetricsContext rootContext = ServerMetrics.getMetrics().getMetricsProvider().getRootContext();
      rootContext.registerGauge("learners", this.gaugeWithLeader((leader) -> leader.getLearners().size()));
      rootContext.registerGauge("synced_followers", this.gaugeWithLeader((leader) -> leader.getForwardingFollowers().size()));
      rootContext.registerGauge("synced_non_voting_followers", this.gaugeWithLeader((leader) -> leader.getNonVotingFollowers().size()));
      QuorumPeer var10002 = this.self;
      Objects.requireNonNull(var10002);
      rootContext.registerGauge("synced_observers", var10002::getSynced_observers_metric);
      rootContext.registerGauge("pending_syncs", this.gaugeWithLeader((leader) -> leader.getNumPendingSyncs()));
      rootContext.registerGauge("leader_uptime", this.gaugeWithLeader((leader) -> leader.getUptime()));
      rootContext.registerGauge("last_proposal_size", this.gaugeWithLeader((leader) -> leader.getProposalStats().getLastBufferSize()));
      rootContext.registerGauge("max_proposal_size", this.gaugeWithLeader((leader) -> leader.getProposalStats().getMaxBufferSize()));
      rootContext.registerGauge("min_proposal_size", this.gaugeWithLeader((leader) -> leader.getProposalStats().getMinBufferSize()));
   }

   private Gauge gaugeWithLeader(Function supplier) {
      return () -> {
         Leader leader = this.getLeader();
         return leader == null ? null : (Number)supplier.apply(leader);
      };
   }

   protected void unregisterMetrics() {
      super.unregisterMetrics();
      MetricsContext rootContext = ServerMetrics.getMetrics().getMetricsProvider().getRootContext();
      rootContext.unregisterGauge("learners");
      rootContext.unregisterGauge("synced_followers");
      rootContext.unregisterGauge("synced_non_voting_followers");
      rootContext.unregisterGauge("synced_observers");
      rootContext.unregisterGauge("pending_syncs");
      rootContext.unregisterGauge("leader_uptime");
      rootContext.unregisterGauge("last_proposal_size");
      rootContext.unregisterGauge("max_proposal_size");
      rootContext.unregisterGauge("min_proposal_size");
   }

   protected synchronized void shutdownComponents() {
      if (this.containerManager != null) {
         this.containerManager.stop();
      }

      super.shutdownComponents();
   }

   public int getGlobalOutstandingLimit() {
      int divisor = this.self.getQuorumSize() > 2 ? this.self.getQuorumSize() - 1 : 1;
      int globalOutstandingLimit = super.getGlobalOutstandingLimit() / divisor;
      return globalOutstandingLimit;
   }

   public void createSessionTracker() {
      this.sessionTracker = new LeaderSessionTracker(this, this.getZKDatabase().getSessionWithTimeOuts(), this.tickTime, this.self.getMyId(), this.self.areLocalSessionsEnabled(), this.getZooKeeperServerListener());
   }

   public boolean touch(long sess, int to) {
      return this.sessionTracker.touchSession(sess, to);
   }

   public boolean checkIfValidGlobalSession(long sess, int to) {
      return this.self.areLocalSessionsEnabled() && !this.upgradeableSessionTracker.isGlobalSession(sess) ? false : this.sessionTracker.touchSession(sess, to);
   }

   public void submitLearnerRequest(Request request) {
      this.prepRequestProcessor.processRequest(request);
   }

   protected void registerJMX() {
      try {
         this.jmxDataTreeBean = new DataTreeBean(this.getZKDatabase().getDataTree());
         MBeanRegistry.getInstance().register(this.jmxDataTreeBean, this.jmxServerBean);
      } catch (Exception e) {
         LOG.warn("Failed to register with JMX", e);
         this.jmxDataTreeBean = null;
      }

   }

   public void registerJMX(LeaderBean leaderBean, LocalPeerBean localPeerBean) {
      if (this.self.jmxLeaderElectionBean != null) {
         try {
            MBeanRegistry.getInstance().unregister(this.self.jmxLeaderElectionBean);
         } catch (Exception e) {
            LOG.warn("Failed to register with JMX", e);
         }

         this.self.jmxLeaderElectionBean = null;
      }

      try {
         this.jmxServerBean = leaderBean;
         MBeanRegistry.getInstance().register(leaderBean, localPeerBean);
      } catch (Exception e) {
         LOG.warn("Failed to register with JMX", e);
         this.jmxServerBean = null;
      }

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

   protected void unregisterJMX() {
      try {
         if (this.jmxDataTreeBean != null) {
            MBeanRegistry.getInstance().unregister(this.jmxDataTreeBean);
         }
      } catch (Exception e) {
         LOG.warn("Failed to unregister with JMX", e);
      }

      this.jmxDataTreeBean = null;
   }

   protected void unregisterJMX(Leader leader) {
      try {
         if (this.jmxServerBean != null) {
            MBeanRegistry.getInstance().unregister(this.jmxServerBean);
         }
      } catch (Exception e) {
         LOG.warn("Failed to unregister with JMX", e);
      }

      this.jmxServerBean = null;
   }

   public String getState() {
      return "leader";
   }

   public long getServerId() {
      return this.self.getMyId();
   }

   protected void revalidateSession(ServerCnxn cnxn, long sessionId, int sessionTimeout) throws IOException {
      super.revalidateSession(cnxn, sessionId, sessionTimeout);

      try {
         this.setOwner(sessionId, ServerCnxn.me);
      } catch (KeeperException.SessionExpiredException var6) {
      }

   }
}
