package org.apache.zookeeper.server.quorum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.zookeeper.jmx.MBeanRegistry;
import org.apache.zookeeper.server.DataTreeBean;
import org.apache.zookeeper.server.FinalRequestProcessor;
import org.apache.zookeeper.server.PrepRequestProcessor;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.RequestProcessor;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.ZKDatabase;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.apache.zookeeper.server.ZooKeeperServerBean;
import org.apache.zookeeper.server.persistence.FileTxnSnapLog;

public class ReadOnlyZooKeeperServer extends ZooKeeperServer {
   protected final QuorumPeer self;
   private volatile boolean shutdown = false;

   ReadOnlyZooKeeperServer(FileTxnSnapLog logFactory, QuorumPeer self, ZKDatabase zkDb) {
      super(logFactory, self.tickTime, self.minSessionTimeout, self.maxSessionTimeout, self.clientPortListenBacklog, zkDb, self.getInitialConfig(), self.isReconfigEnabled());
      this.self = self;
   }

   protected void setupRequestProcessors() {
      RequestProcessor finalProcessor = new FinalRequestProcessor(this);
      RequestProcessor prepProcessor = new PrepRequestProcessor(this, finalProcessor);
      ((PrepRequestProcessor)prepProcessor).start();
      this.firstProcessor = new ReadOnlyRequestProcessor(this, prepProcessor);
      ((ReadOnlyRequestProcessor)this.firstProcessor).start();
   }

   public synchronized void startup() {
      if (this.shutdown) {
         LOG.warn("Not starting Read-only server as startup follows shutdown!");
      } else {
         this.registerJMX(new ReadOnlyBean(this), this.self.jmxLocalPeerBean);
         super.startup();
         this.self.setZooKeeperServer(this);
         this.self.adminServer.setZooKeeperServer(this);
         LOG.info("Read-only server started");
      }
   }

   public void createSessionTracker() {
      this.sessionTracker = new LearnerSessionTracker(this, this.getZKDatabase().getSessionWithTimeOuts(), this.tickTime, this.self.getMyId(), this.self.areLocalSessionsEnabled(), this.getZooKeeperServerListener());
   }

   protected void startSessionTracker() {
      ((LearnerSessionTracker)this.sessionTracker).start();
   }

   protected void setLocalSessionFlag(Request si) {
      switch (si.type) {
         case -11:
            if (((UpgradeableSessionTracker)this.sessionTracker).isLocalSession(si.sessionId)) {
               si.setLocalSession(true);
            } else {
               LOG.warn("Submitting global closeSession request for session 0x{} in ReadOnly mode", Long.toHexString(si.sessionId));
            }
            break;
         case -10:
            if (this.self.areLocalSessionsEnabled()) {
               si.setLocalSession(true);
            }
      }

   }

   protected void validateSession(ServerCnxn cnxn, long sessionId) throws IOException {
      if (((LearnerSessionTracker)this.sessionTracker).isGlobalSession(sessionId)) {
         String msg = "Refusing global session reconnection in RO mode " + cnxn.getRemoteSocketAddress();
         LOG.info(msg);
         throw new ServerCnxn.CloseRequestException(msg, ServerCnxn.DisconnectReason.RENEW_GLOBAL_SESSION_IN_RO_MODE);
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

   protected void unregisterJMX(ZooKeeperServer zks) {
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
      return "read-only";
   }

   public long getServerId() {
      return this.self.getMyId();
   }

   protected void shutdownComponents() {
      this.shutdown = true;
      this.unregisterJMX(this);
      this.self.setZooKeeperServer((ZooKeeperServer)null);
      this.self.closeAllConnections();
      this.self.adminServer.setZooKeeperServer((ZooKeeperServer)null);
      super.shutdownComponents();
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
   }

   protected void setState(ZooKeeperServer.State state) {
      this.state = state;
   }
}
