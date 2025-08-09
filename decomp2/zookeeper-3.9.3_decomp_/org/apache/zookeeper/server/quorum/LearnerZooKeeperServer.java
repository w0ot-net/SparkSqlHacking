package org.apache.zookeeper.server.quorum;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import org.apache.zookeeper.jmx.MBeanRegistry;
import org.apache.zookeeper.server.DataTreeBean;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.SyncRequestProcessor;
import org.apache.zookeeper.server.ZKDatabase;
import org.apache.zookeeper.server.ZooKeeperServerBean;
import org.apache.zookeeper.server.persistence.FileTxnSnapLog;

public abstract class LearnerZooKeeperServer extends QuorumZooKeeperServer {
   protected CommitProcessor commitProcessor;
   protected SyncRequestProcessor syncProcessor;

   public LearnerZooKeeperServer(FileTxnSnapLog logFactory, int tickTime, int minSessionTimeout, int maxSessionTimeout, int listenBacklog, ZKDatabase zkDb, QuorumPeer self) throws IOException {
      super(logFactory, tickTime, minSessionTimeout, maxSessionTimeout, listenBacklog, zkDb, self);
   }

   public abstract Learner getLearner();

   protected Map getTouchSnapshot() {
      if (this.sessionTracker != null) {
         return ((LearnerSessionTracker)this.sessionTracker).snapshot();
      } else {
         Map<Long, Integer> map = Collections.emptyMap();
         return map;
      }
   }

   public long getServerId() {
      return this.self.getMyId();
   }

   public void createSessionTracker() {
      this.sessionTracker = new LearnerSessionTracker(this, this.getZKDatabase().getSessionWithTimeOuts(), this.tickTime, this.self.getMyId(), this.self.areLocalSessionsEnabled(), this.getZooKeeperServerListener());
   }

   protected void revalidateSession(ServerCnxn cnxn, long sessionId, int sessionTimeout) throws IOException {
      if (this.upgradeableSessionTracker.isLocalSession(sessionId)) {
         super.revalidateSession(cnxn, sessionId, sessionTimeout);
      } else {
         this.getLearner().validateSession(cnxn, sessionId, sessionTimeout);
      }

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

   public void registerJMX(ZooKeeperServerBean serverBean, LocalPeerBean localPeerBean) {
      if (this.self.jmxLeaderElectionBean != null) {
         try {
            MBeanRegistry.getInstance().unregister(this.self.jmxLeaderElectionBean);
         } catch (Exception e) {
            LOG.warn("Failed to register with JMX", e);
         }

         this.self.jmxLeaderElectionBean = null;
      }

      try {
         this.jmxServerBean = serverBean;
         MBeanRegistry.getInstance().register(serverBean, localPeerBean);
      } catch (Exception e) {
         LOG.warn("Failed to register with JMX", e);
         this.jmxServerBean = null;
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

   protected void unregisterJMX(Learner peer) {
      try {
         if (this.jmxServerBean != null) {
            MBeanRegistry.getInstance().unregister(this.jmxServerBean);
         }
      } catch (Exception e) {
         LOG.warn("Failed to unregister with JMX", e);
      }

      this.jmxServerBean = null;
   }

   protected void shutdownComponents() {
      try {
         if (this.syncProcessor != null) {
            this.syncProcessor.shutdown();
         }
      } catch (Exception e) {
         LOG.warn("Ignoring unexpected exception in syncprocessor shutdown", e);
      }

      try {
         super.shutdownComponents();
      } catch (Exception e) {
         LOG.warn("Ignoring unexpected exception during shutdown", e);
      }

   }
}
