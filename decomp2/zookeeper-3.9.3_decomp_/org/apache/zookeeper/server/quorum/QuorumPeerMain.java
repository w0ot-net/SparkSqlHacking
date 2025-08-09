package org.apache.zookeeper.server.quorum;

import java.io.IOException;
import javax.management.JMException;
import javax.security.sasl.SaslException;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.audit.ZKAuditProvider;
import org.apache.zookeeper.jmx.ManagedUtil;
import org.apache.zookeeper.metrics.MetricsProvider;
import org.apache.zookeeper.metrics.MetricsProviderLifeCycleException;
import org.apache.zookeeper.metrics.impl.MetricsProviderBootstrap;
import org.apache.zookeeper.server.DatadirCleanupManager;
import org.apache.zookeeper.server.ExitCode;
import org.apache.zookeeper.server.ServerCnxnFactory;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.ZKDatabase;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.admin.AdminServer;
import org.apache.zookeeper.server.auth.ProviderRegistry;
import org.apache.zookeeper.server.persistence.FileTxnSnapLog;
import org.apache.zookeeper.server.util.JvmPauseMonitor;
import org.apache.zookeeper.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Public
public class QuorumPeerMain {
   private static final Logger LOG = LoggerFactory.getLogger(QuorumPeerMain.class);
   private static final String USAGE = "Usage: QuorumPeerMain configfile";
   protected QuorumPeer quorumPeer;

   public static void main(String[] args) {
      QuorumPeerMain main = new QuorumPeerMain();

      try {
         main.initializeAndRun(args);
      } catch (IllegalArgumentException e) {
         LOG.error("Invalid arguments, exiting abnormally", e);
         LOG.info("Usage: QuorumPeerMain configfile");
         System.err.println("Usage: QuorumPeerMain configfile");
         ZKAuditProvider.addServerStartFailureAuditLog();
         ServiceUtils.requestSystemExit(ExitCode.INVALID_INVOCATION.getValue());
      } catch (QuorumPeerConfig.ConfigException e) {
         LOG.error("Invalid config, exiting abnormally", e);
         System.err.println("Invalid config, exiting abnormally");
         ZKAuditProvider.addServerStartFailureAuditLog();
         ServiceUtils.requestSystemExit(ExitCode.INVALID_INVOCATION.getValue());
      } catch (FileTxnSnapLog.DatadirException e) {
         LOG.error("Unable to access datadir, exiting abnormally", e);
         System.err.println("Unable to access datadir, exiting abnormally");
         ZKAuditProvider.addServerStartFailureAuditLog();
         ServiceUtils.requestSystemExit(ExitCode.UNABLE_TO_ACCESS_DATADIR.getValue());
      } catch (AdminServer.AdminServerException e) {
         LOG.error("Unable to start AdminServer, exiting abnormally", e);
         System.err.println("Unable to start AdminServer, exiting abnormally");
         ZKAuditProvider.addServerStartFailureAuditLog();
         ServiceUtils.requestSystemExit(ExitCode.ERROR_STARTING_ADMIN_SERVER.getValue());
      } catch (Exception e) {
         LOG.error("Unexpected exception, exiting abnormally", e);
         ZKAuditProvider.addServerStartFailureAuditLog();
         ServiceUtils.requestSystemExit(ExitCode.UNEXPECTED_ERROR.getValue());
      }

      LOG.info("Exiting normally");
      ServiceUtils.requestSystemExit(ExitCode.EXECUTION_FINISHED.getValue());
   }

   protected void initializeAndRun(String[] args) throws QuorumPeerConfig.ConfigException, IOException, AdminServer.AdminServerException {
      QuorumPeerConfig config = new QuorumPeerConfig();
      if (args.length == 1) {
         config.parse(args[0]);
      }

      DatadirCleanupManager purgeMgr = new DatadirCleanupManager(config.getDataDir(), config.getDataLogDir(), config.getSnapRetainCount(), config.getPurgeInterval());
      purgeMgr.start();
      if (args.length == 1 && config.isDistributed()) {
         this.runFromConfig(config);
      } else {
         LOG.warn("Either no config or no quorum defined in config, running in standalone mode");
         ZooKeeperServerMain.main(args);
      }

   }

   public void runFromConfig(QuorumPeerConfig config) throws IOException, AdminServer.AdminServerException {
      try {
         ManagedUtil.registerLog4jMBeans();
      } catch (JMException e) {
         LOG.warn("Unable to register log4j JMX control", e);
      }

      LOG.info("Starting quorum peer, myid=" + config.getServerId());

      MetricsProvider metricsProvider;
      try {
         metricsProvider = MetricsProviderBootstrap.startMetricsProvider(config.getMetricsProviderClassName(), config.getMetricsProviderConfiguration());
      } catch (MetricsProviderLifeCycleException error) {
         throw new IOException("Cannot boot MetricsProvider " + config.getMetricsProviderClassName(), error);
      }

      try {
         ServerMetrics.metricsProviderInitialized(metricsProvider);
         ProviderRegistry.initialize();
         ServerCnxnFactory cnxnFactory = null;
         ServerCnxnFactory secureCnxnFactory = null;
         if (config.getClientPortAddress() != null) {
            cnxnFactory = ServerCnxnFactory.createFactory();
            cnxnFactory.configure(config.getClientPortAddress(), config.getMaxClientCnxns(), config.getClientPortListenBacklog(), false);
         }

         if (config.getSecureClientPortAddress() != null) {
            secureCnxnFactory = ServerCnxnFactory.createFactory();
            secureCnxnFactory.configure(config.getSecureClientPortAddress(), config.getMaxClientCnxns(), config.getClientPortListenBacklog(), true);
         }

         this.quorumPeer = this.getQuorumPeer();
         this.quorumPeer.setTxnFactory(new FileTxnSnapLog(config.getDataLogDir(), config.getDataDir()));
         this.quorumPeer.enableLocalSessions(config.areLocalSessionsEnabled());
         this.quorumPeer.enableLocalSessionsUpgrading(config.isLocalSessionsUpgradingEnabled());
         this.quorumPeer.setElectionType(config.getElectionAlg());
         this.quorumPeer.setMyid(config.getServerId());
         this.quorumPeer.setTickTime(config.getTickTime());
         this.quorumPeer.setMinSessionTimeout(config.getMinSessionTimeout());
         this.quorumPeer.setMaxSessionTimeout(config.getMaxSessionTimeout());
         this.quorumPeer.setInitLimit(config.getInitLimit());
         this.quorumPeer.setSyncLimit(config.getSyncLimit());
         this.quorumPeer.setConnectToLearnerMasterLimit(config.getConnectToLearnerMasterLimit());
         this.quorumPeer.setObserverMasterPort(config.getObserverMasterPort());
         this.quorumPeer.setConfigFileName(config.getConfigFilename());
         this.quorumPeer.setClientPortListenBacklog(config.getClientPortListenBacklog());
         this.quorumPeer.setZKDatabase(new ZKDatabase(this.quorumPeer.getTxnFactory()));
         this.quorumPeer.setQuorumVerifier(config.getQuorumVerifier(), false);
         if (config.getLastSeenQuorumVerifier() != null) {
            this.quorumPeer.setLastSeenQuorumVerifier(config.getLastSeenQuorumVerifier(), false);
         }

         this.quorumPeer.initConfigInZKDatabase();
         this.quorumPeer.setCnxnFactory(cnxnFactory);
         this.quorumPeer.setSecureCnxnFactory(secureCnxnFactory);
         this.quorumPeer.setSslQuorum(config.isSslQuorum());
         this.quorumPeer.setUsePortUnification(config.shouldUsePortUnification());
         this.quorumPeer.setLearnerType(config.getPeerType());
         this.quorumPeer.setSyncEnabled(config.getSyncEnabled());
         this.quorumPeer.setQuorumListenOnAllIPs(config.getQuorumListenOnAllIPs());
         if (config.sslQuorumReloadCertFiles) {
            this.quorumPeer.getX509Util().enableCertFileReloading();
         }

         this.quorumPeer.setMultiAddressEnabled(config.isMultiAddressEnabled());
         this.quorumPeer.setMultiAddressReachabilityCheckEnabled(config.isMultiAddressReachabilityCheckEnabled());
         this.quorumPeer.setMultiAddressReachabilityCheckTimeoutMs(config.getMultiAddressReachabilityCheckTimeoutMs());
         this.quorumPeer.setQuorumSaslEnabled(config.quorumEnableSasl);
         if (this.quorumPeer.isQuorumSaslAuthEnabled()) {
            this.quorumPeer.setQuorumServerSaslRequired(config.quorumServerRequireSasl);
            this.quorumPeer.setQuorumLearnerSaslRequired(config.quorumLearnerRequireSasl);
            this.quorumPeer.setQuorumServicePrincipal(config.quorumServicePrincipal);
            this.quorumPeer.setQuorumServerLoginContext(config.quorumServerLoginContext);
            this.quorumPeer.setQuorumLearnerLoginContext(config.quorumLearnerLoginContext);
         }

         this.quorumPeer.setQuorumCnxnThreadsSize(config.quorumCnxnThreadsSize);
         this.quorumPeer.initialize();
         if (config.jvmPauseMonitorToRun) {
            this.quorumPeer.setJvmPauseMonitor(new JvmPauseMonitor(config));
         }

         this.quorumPeer.start();
         ZKAuditProvider.addZKStartStopAuditLog();
         this.quorumPeer.join();
      } catch (InterruptedException e) {
         LOG.warn("Quorum Peer interrupted", e);
      } finally {
         try {
            metricsProvider.stop();
         } catch (Throwable error) {
            LOG.warn("Error while stopping metrics", error);
         }

      }

   }

   protected QuorumPeer getQuorumPeer() throws SaslException {
      return new QuorumPeer();
   }

   public void close() {
      if (this.quorumPeer != null) {
         try {
            this.quorumPeer.shutdown();
         } finally {
            this.quorumPeer = null;
         }
      }

   }

   public String toString() {
      QuorumPeer peer = this.quorumPeer;
      return peer == null ? "" : peer.toString();
   }
}
