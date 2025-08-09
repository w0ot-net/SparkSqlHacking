package org.apache.zookeeper.server.embedded;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.security.sasl.SaslException;
import org.apache.zookeeper.server.DatadirCleanupManager;
import org.apache.zookeeper.server.ExitCode;
import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeer;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.apache.zookeeper.server.quorum.QuorumPeerMain;
import org.apache.zookeeper.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ZooKeeperServerEmbeddedImpl implements ZooKeeperServerEmbedded {
   private static final Logger LOG = LoggerFactory.getLogger(ZooKeeperServerEmbeddedImpl.class);
   private final QuorumPeerConfig config;
   private QuorumPeerMain maincluster;
   private ZooKeeperServerMain mainsingle;
   private Thread thread;
   private DatadirCleanupManager purgeMgr;
   private final ExitHandler exitHandler;
   private volatile boolean stopping;
   private int boundClientPort;
   private int boundSecureClientPort;

   ZooKeeperServerEmbeddedImpl(Properties p, Path baseDir, ExitHandler exitHandler) throws Exception {
      if (!p.containsKey("dataDir")) {
         p.put("dataDir", baseDir.resolve("data").toAbsolutePath().toString());
      }

      Path configFile = Files.createTempFile(baseDir, "zookeeper.configuration", ".properties");
      OutputStream oo = Files.newOutputStream(configFile);

      try {
         p.store(oo, "Automatically generated at every-boot");
      } catch (Throwable var9) {
         if (oo != null) {
            try {
               oo.close();
            } catch (Throwable var8) {
               var9.addSuppressed(var8);
            }
         }

         throw var9;
      }

      if (oo != null) {
         oo.close();
      }

      this.exitHandler = exitHandler;
      LOG.info("Current configuration is at {}", configFile.toAbsolutePath());
      this.config = new QuorumPeerConfig();
      this.config.parse(configFile.toAbsolutePath().toString());
      LOG.info("ServerID:" + this.config.getServerId());
      LOG.info("DataDir:" + this.config.getDataDir());
      LOG.info("Servers:" + this.config.getServers());
      LOG.info("ElectionPort:" + this.config.getElectionPort());
      LOG.info("SyncLimit:" + this.config.getSyncLimit());
      LOG.info("PeerType:" + this.config.getPeerType());
      LOG.info("Distributed:" + this.config.isDistributed());
      LOG.info("SyncEnabled:" + this.config.getSyncEnabled());
      LOG.info("MetricsProviderClassName:" + this.config.getMetricsProviderClassName());

      for(Map.Entry server : this.config.getServers().entrySet()) {
         LOG.info("Server: " + server.getKey() + " -> addr " + ((QuorumPeer.QuorumServer)server.getValue()).addr + " elect " + ((QuorumPeer.QuorumServer)server.getValue()).electionAddr + " id=" + ((QuorumPeer.QuorumServer)server.getValue()).id + " type " + ((QuorumPeer.QuorumServer)server.getValue()).type);
      }

   }

   public void start() throws Exception {
      this.start(2147483647L);
   }

   public void start(long startupTimeout) throws Exception {
      switch (this.exitHandler) {
         case EXIT:
            ServiceUtils.setSystemExitProcedure(ServiceUtils.SYSTEM_EXIT);
            break;
         case LOG_ONLY:
            ServiceUtils.setSystemExitProcedure(ServiceUtils.LOG_ONLY);
            break;
         default:
            ServiceUtils.setSystemExitProcedure(ServiceUtils.SYSTEM_EXIT);
      }

      final CompletableFuture<String> started = new CompletableFuture();
      if (this.config.getServers().size() <= 1 && !this.config.isDistributed()) {
         LOG.info("Running ZK Server in single STANDALONE MODE");
         this.mainsingle = new ZooKeeperServerMain() {
            public void serverStarted() {
               ZooKeeperServerEmbeddedImpl.LOG.info("ZK Server started");
               ZooKeeperServerEmbeddedImpl.this.boundClientPort = this.getClientPort();
               ZooKeeperServerEmbeddedImpl.this.boundSecureClientPort = this.getSecureClientPort();
               started.complete((Object)null);
            }
         };
         this.purgeMgr = new DatadirCleanupManager(this.config.getDataDir(), this.config.getDataLogDir(), this.config.getSnapRetainCount(), this.config.getPurgeInterval());
         this.purgeMgr.start();
         this.thread = new Thread("zkservermainrunner") {
            public void run() {
               try {
                  ServerConfig cc = new ServerConfig();
                  cc.readFrom(ZooKeeperServerEmbeddedImpl.this.config);
                  ZooKeeperServerEmbeddedImpl.LOG.info("ZK server starting");
                  ZooKeeperServerEmbeddedImpl.this.mainsingle.runFromConfig(cc);
                  ZooKeeperServerEmbeddedImpl.LOG.info("ZK server died. Requesting stop on JVM");
                  if (!ZooKeeperServerEmbeddedImpl.this.stopping) {
                     ServiceUtils.requestSystemExit(ExitCode.EXECUTION_FINISHED.getValue());
                  }
               } catch (Throwable t) {
                  ZooKeeperServerEmbeddedImpl.LOG.error("error during server lifecycle", t);
                  ZooKeeperServerEmbeddedImpl.this.mainsingle.close();
                  if (!ZooKeeperServerEmbeddedImpl.this.stopping) {
                     ServiceUtils.requestSystemExit(ExitCode.INVALID_INVOCATION.getValue());
                  }
               }

            }
         };
         this.thread.start();
      } else {
         LOG.info("Running ZK Server in single Quorum MODE");
         this.maincluster = new QuorumPeerMain() {
            protected QuorumPeer getQuorumPeer() throws SaslException {
               return new QuorumPeer() {
                  public void start() {
                     super.start();
                     ZooKeeperServerEmbeddedImpl.this.boundClientPort = this.getClientPort();
                     ZooKeeperServerEmbeddedImpl.this.boundSecureClientPort = this.getSecureClientPort();
                     ZooKeeperServerEmbeddedImpl.LOG.info("ZK Server {} started", this);
                     started.complete((Object)null);
                  }
               };
            }
         };
         this.purgeMgr = new DatadirCleanupManager(this.config.getDataDir(), this.config.getDataLogDir(), this.config.getSnapRetainCount(), this.config.getPurgeInterval());
         this.purgeMgr.start();
         this.thread = new Thread("zkservermainrunner") {
            public void run() {
               try {
                  ZooKeeperServerEmbeddedImpl.this.maincluster.runFromConfig(ZooKeeperServerEmbeddedImpl.this.config);
                  ZooKeeperServerEmbeddedImpl.this.maincluster.close();
                  ZooKeeperServerEmbeddedImpl.LOG.info("ZK server died. Requsting stop on JVM");
                  if (!ZooKeeperServerEmbeddedImpl.this.stopping) {
                     ServiceUtils.requestSystemExit(ExitCode.EXECUTION_FINISHED.getValue());
                  }
               } catch (Throwable t) {
                  ZooKeeperServerEmbeddedImpl.LOG.error("error during server lifecycle", t);
                  ZooKeeperServerEmbeddedImpl.this.maincluster.close();
                  if (!ZooKeeperServerEmbeddedImpl.this.stopping) {
                     ServiceUtils.requestSystemExit(ExitCode.INVALID_INVOCATION.getValue());
                  }
               }

            }
         };
         this.thread.start();
      }

      try {
         started.get(startupTimeout, TimeUnit.MILLISECONDS);
      } catch (TimeoutException err) {
         LOG.info("Startup timed out, trying to close");
         this.close();
         throw err;
      }
   }

   public String getConnectionString() {
      return this.prettifyConnectionString(this.config.getClientPortAddress(), this.boundClientPort);
   }

   public String getSecureConnectionString() {
      return this.prettifyConnectionString(this.config.getSecureClientPortAddress(), this.boundSecureClientPort);
   }

   private String prettifyConnectionString(InetSocketAddress confAddress, int boundPort) {
      if (confAddress != null) {
         return confAddress.getHostString().replace("0.0.0.0", "localhost").replace("0:0:0:0:0:0:0:0", "localhost") + ":" + boundPort;
      } else {
         throw new IllegalStateException("No client address is configured");
      }
   }

   public void close() {
      LOG.info("Stopping ZK Server");
      this.stopping = true;
      if (this.mainsingle != null) {
         this.mainsingle.close();
      }

      if (this.maincluster != null) {
         this.maincluster.close();
      }

   }
}
