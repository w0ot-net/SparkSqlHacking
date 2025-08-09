package org.apache.zookeeper.server;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.management.JMException;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.audit.ZKAuditProvider;
import org.apache.zookeeper.jmx.ManagedUtil;
import org.apache.zookeeper.metrics.MetricsProvider;
import org.apache.zookeeper.metrics.MetricsProviderLifeCycleException;
import org.apache.zookeeper.metrics.impl.MetricsProviderBootstrap;
import org.apache.zookeeper.server.admin.AdminServer;
import org.apache.zookeeper.server.admin.AdminServerFactory;
import org.apache.zookeeper.server.auth.ProviderRegistry;
import org.apache.zookeeper.server.persistence.FileTxnSnapLog;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.apache.zookeeper.server.util.JvmPauseMonitor;
import org.apache.zookeeper.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Public
public class ZooKeeperServerMain {
   private static final Logger LOG = LoggerFactory.getLogger(ZooKeeperServerMain.class);
   private static final String USAGE = "Usage: ZooKeeperServerMain configfile | port datadir [ticktime] [maxcnxns]";
   private ServerCnxnFactory cnxnFactory;
   private ServerCnxnFactory secureCnxnFactory;
   private ContainerManager containerManager;
   private MetricsProvider metricsProvider;
   private AdminServer adminServer;

   public static void main(String[] args) {
      ZooKeeperServerMain main = new ZooKeeperServerMain();

      try {
         main.initializeAndRun(args);
      } catch (IllegalArgumentException e) {
         LOG.error("Invalid arguments, exiting abnormally", e);
         LOG.info("Usage: ZooKeeperServerMain configfile | port datadir [ticktime] [maxcnxns]");
         System.err.println("Usage: ZooKeeperServerMain configfile | port datadir [ticktime] [maxcnxns]");
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
      try {
         ManagedUtil.registerLog4jMBeans();
      } catch (JMException e) {
         LOG.warn("Unable to register log4j JMX control", e);
      }

      ServerConfig config = new ServerConfig();
      if (args.length == 1) {
         config.parse(args[0]);
      } else {
         config.parse(args);
      }

      this.runFromConfig(config);
   }

   public void runFromConfig(ServerConfig config) throws IOException, AdminServer.AdminServerException {
      LOG.info("Starting server");
      FileTxnSnapLog txnLog = null;

      try {
         try {
            this.metricsProvider = MetricsProviderBootstrap.startMetricsProvider(config.getMetricsProviderClassName(), config.getMetricsProviderConfiguration());
         } catch (MetricsProviderLifeCycleException error) {
            throw new IOException("Cannot boot MetricsProvider " + config.getMetricsProviderClassName(), error);
         }

         ServerMetrics.metricsProviderInitialized(this.metricsProvider);
         ProviderRegistry.initialize();
         txnLog = new FileTxnSnapLog(config.dataLogDir, config.dataDir);
         JvmPauseMonitor jvmPauseMonitor = null;
         if (config.jvmPauseMonitorToRun) {
            jvmPauseMonitor = new JvmPauseMonitor(config);
         }

         ZooKeeperServer zkServer = new ZooKeeperServer(jvmPauseMonitor, txnLog, config.tickTime, config.minSessionTimeout, config.maxSessionTimeout, config.listenBacklog, (ZKDatabase)null, config.initialConfig);
         txnLog.setServerStats(zkServer.serverStats());
         CountDownLatch shutdownLatch = new CountDownLatch(1);
         zkServer.registerServerShutdownHandler(new ZooKeeperServerShutdownHandler(shutdownLatch));
         this.adminServer = AdminServerFactory.createAdminServer();
         this.adminServer.setZooKeeperServer(zkServer);
         this.adminServer.start();
         boolean needStartZKServer = true;
         if (config.getClientPortAddress() != null) {
            this.cnxnFactory = ServerCnxnFactory.createFactory();
            this.cnxnFactory.configure(config.getClientPortAddress(), config.getMaxClientCnxns(), config.getClientPortListenBacklog(), false);
            this.cnxnFactory.startup(zkServer);
            needStartZKServer = false;
         }

         if (config.getSecureClientPortAddress() != null) {
            this.secureCnxnFactory = ServerCnxnFactory.createFactory();
            this.secureCnxnFactory.configure(config.getSecureClientPortAddress(), config.getMaxClientCnxns(), config.getClientPortListenBacklog(), true);
            this.secureCnxnFactory.startup(zkServer, needStartZKServer);
         }

         this.containerManager = new ContainerManager(zkServer.getZKDatabase(), zkServer.firstProcessor, Integer.getInteger("znode.container.checkIntervalMs", (int)TimeUnit.MINUTES.toMillis(1L)), Integer.getInteger("znode.container.maxPerMinute", 10000), Long.getLong("znode.container.maxNeverUsedIntervalMs", 0L));
         this.containerManager.start();
         ZKAuditProvider.addZKStartStopAuditLog();
         this.serverStarted();
         shutdownLatch.await();
         this.shutdown();
         if (this.cnxnFactory != null) {
            this.cnxnFactory.join();
         }

         if (this.secureCnxnFactory != null) {
            this.secureCnxnFactory.join();
         }

         zkServer.shutdown(true);
      } catch (InterruptedException e) {
         LOG.warn("Server interrupted", e);
      } finally {
         if (txnLog != null) {
            txnLog.close();
         }

         if (this.metricsProvider != null) {
            try {
               this.metricsProvider.stop();
            } catch (Throwable error) {
               LOG.warn("Error while stopping metrics", error);
            }
         }

      }

   }

   protected void shutdown() {
      if (this.containerManager != null) {
         this.containerManager.stop();
      }

      if (this.cnxnFactory != null) {
         this.cnxnFactory.shutdown();
      }

      if (this.secureCnxnFactory != null) {
         this.secureCnxnFactory.shutdown();
      }

      try {
         if (this.adminServer != null) {
            this.adminServer.shutdown();
         }
      } catch (AdminServer.AdminServerException e) {
         LOG.warn("Problem stopping AdminServer", e);
      }

   }

   ServerCnxnFactory getCnxnFactory() {
      return this.cnxnFactory;
   }

   ServerCnxnFactory getSecureCnxnFactory() {
      return this.secureCnxnFactory;
   }

   public int getClientPort() {
      return this.cnxnFactory != null ? this.cnxnFactory.getLocalPort() : 0;
   }

   public int getSecureClientPort() {
      return this.secureCnxnFactory != null ? this.secureCnxnFactory.getLocalPort() : 0;
   }

   public void close() {
      ServerCnxnFactory primaryCnxnFactory = this.cnxnFactory;
      ServerCnxnFactory secondaryCnxnFactory = this.secureCnxnFactory;

      try {
         if (primaryCnxnFactory == null) {
            primaryCnxnFactory = secondaryCnxnFactory;
         }

         if (primaryCnxnFactory != null && primaryCnxnFactory.getZooKeeperServer() != null) {
            ZooKeeperServerShutdownHandler zkShutdownHandler = primaryCnxnFactory.getZooKeeperServer().getZkShutdownHandler();
            zkShutdownHandler.handle(ZooKeeperServer.State.SHUTDOWN);

            try {
               primaryCnxnFactory.join();
            } catch (InterruptedException var8) {
               Thread.currentThread().interrupt();
            }

            return;
         }

         LOG.info("Connection factory did not start");
      } finally {
         if (primaryCnxnFactory != null) {
            primaryCnxnFactory.shutdown();
         }

         if (secondaryCnxnFactory != null) {
            secondaryCnxnFactory.shutdown();
         }

      }

   }

   protected void serverStarted() {
   }
}
