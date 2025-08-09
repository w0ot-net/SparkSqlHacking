package org.apache.zookeeper.server;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Properties;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.metrics.impl.DefaultMetricsProvider;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

@Public
public class ServerConfig {
   protected InetSocketAddress clientPortAddress;
   protected InetSocketAddress secureClientPortAddress;
   protected File dataDir;
   protected File dataLogDir;
   protected int tickTime = 3000;
   protected int maxClientCnxns;
   protected int minSessionTimeout = -1;
   protected int maxSessionTimeout = -1;
   protected String metricsProviderClassName = DefaultMetricsProvider.class.getName();
   protected Properties metricsProviderConfiguration = new Properties();
   protected int listenBacklog = -1;
   protected String initialConfig;
   protected boolean jvmPauseMonitorToRun = false;
   protected long jvmPauseWarnThresholdMs;
   protected long jvmPauseInfoThresholdMs;
   protected long jvmPauseSleepTimeMs;

   public void parse(String[] args) {
      if (args.length >= 2 && args.length <= 4) {
         this.clientPortAddress = new InetSocketAddress(Integer.parseInt(args[0]));
         this.dataDir = new File(args[1]);
         this.dataLogDir = this.dataDir;
         if (args.length >= 3) {
            this.tickTime = Integer.parseInt(args[2]);
         }

         if (args.length == 4) {
            this.maxClientCnxns = Integer.parseInt(args[3]);
         }

      } else {
         throw new IllegalArgumentException("Invalid number of arguments:" + Arrays.toString(args));
      }
   }

   public void parse(String path) throws QuorumPeerConfig.ConfigException {
      QuorumPeerConfig config = new QuorumPeerConfig();
      config.parse(path);
      this.readFrom(config);
   }

   public void readFrom(QuorumPeerConfig config) {
      this.clientPortAddress = config.getClientPortAddress();
      this.secureClientPortAddress = config.getSecureClientPortAddress();
      this.dataDir = config.getDataDir();
      this.dataLogDir = config.getDataLogDir();
      this.tickTime = config.getTickTime();
      this.maxClientCnxns = config.getMaxClientCnxns();
      this.minSessionTimeout = config.getMinSessionTimeout();
      this.maxSessionTimeout = config.getMaxSessionTimeout();
      this.jvmPauseMonitorToRun = config.isJvmPauseMonitorToRun();
      this.jvmPauseInfoThresholdMs = config.getJvmPauseInfoThresholdMs();
      this.jvmPauseWarnThresholdMs = config.getJvmPauseWarnThresholdMs();
      this.jvmPauseSleepTimeMs = config.getJvmPauseSleepTimeMs();
      this.metricsProviderClassName = config.getMetricsProviderClassName();
      this.metricsProviderConfiguration = config.getMetricsProviderConfiguration();
      this.listenBacklog = config.getClientPortListenBacklog();
      this.initialConfig = config.getInitialConfig();
   }

   public InetSocketAddress getClientPortAddress() {
      return this.clientPortAddress;
   }

   public InetSocketAddress getSecureClientPortAddress() {
      return this.secureClientPortAddress;
   }

   public File getDataDir() {
      return this.dataDir;
   }

   public File getDataLogDir() {
      return this.dataLogDir;
   }

   public int getTickTime() {
      return this.tickTime;
   }

   public int getMaxClientCnxns() {
      return this.maxClientCnxns;
   }

   public int getMinSessionTimeout() {
      return this.minSessionTimeout;
   }

   public int getMaxSessionTimeout() {
      return this.maxSessionTimeout;
   }

   public long getJvmPauseInfoThresholdMs() {
      return this.jvmPauseInfoThresholdMs;
   }

   public long getJvmPauseWarnThresholdMs() {
      return this.jvmPauseWarnThresholdMs;
   }

   public long getJvmPauseSleepTimeMs() {
      return this.jvmPauseSleepTimeMs;
   }

   public boolean isJvmPauseMonitorToRun() {
      return this.jvmPauseMonitorToRun;
   }

   public String getMetricsProviderClassName() {
      return this.metricsProviderClassName;
   }

   public Properties getMetricsProviderConfiguration() {
      return this.metricsProviderConfiguration;
   }

   public int getClientPortListenBacklog() {
      return this.listenBacklog;
   }
}
