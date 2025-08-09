package org.apache.zookeeper.server.quorum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.common.AtomicFileWritingIdiom;
import org.apache.zookeeper.common.ClientX509Util;
import org.apache.zookeeper.common.NetUtils;
import org.apache.zookeeper.common.PathUtils;
import org.apache.zookeeper.common.StringUtils;
import org.apache.zookeeper.metrics.impl.DefaultMetricsProvider;
import org.apache.zookeeper.server.quorum.flexible.QuorumHierarchical;
import org.apache.zookeeper.server.quorum.flexible.QuorumMaj;
import org.apache.zookeeper.server.quorum.flexible.QuorumOracleMaj;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;
import org.apache.zookeeper.server.util.VerifyingFileFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@Public
public class QuorumPeerConfig {
   private static final Logger LOG = LoggerFactory.getLogger(QuorumPeerConfig.class);
   private static final int UNSET_SERVERID = -1;
   public static final String nextDynamicConfigFileSuffix = ".dynamic.next";
   private static boolean standaloneEnabled = true;
   private static boolean reconfigEnabled = false;
   protected InetSocketAddress clientPortAddress;
   protected InetSocketAddress secureClientPortAddress;
   protected boolean sslQuorum = false;
   protected boolean shouldUsePortUnification = false;
   protected int observerMasterPort;
   protected boolean sslQuorumReloadCertFiles = false;
   protected File dataDir;
   protected File dataLogDir;
   protected String dynamicConfigFileStr = null;
   protected String configFileStr = null;
   protected int tickTime = 3000;
   protected int maxClientCnxns = 60;
   protected int minSessionTimeout = -1;
   protected int maxSessionTimeout = -1;
   protected String metricsProviderClassName = DefaultMetricsProvider.class.getName();
   protected Properties metricsProviderConfiguration = new Properties();
   protected boolean localSessionsEnabled = false;
   protected boolean localSessionsUpgradingEnabled = false;
   protected int clientPortListenBacklog = -1;
   protected int initLimit;
   protected int syncLimit;
   protected int connectToLearnerMasterLimit;
   protected int electionAlg = 3;
   protected int electionPort = 2182;
   protected boolean quorumListenOnAllIPs = false;
   protected long serverId = -1L;
   protected QuorumVerifier quorumVerifier = null;
   protected QuorumVerifier lastSeenQuorumVerifier = null;
   protected int snapRetainCount = 3;
   protected int purgeInterval = 0;
   protected boolean syncEnabled = true;
   protected String initialConfig;
   protected QuorumPeer.LearnerType peerType;
   protected boolean quorumServerRequireSasl;
   protected boolean quorumLearnerRequireSasl;
   protected boolean quorumEnableSasl;
   protected String quorumServicePrincipal;
   protected String quorumLearnerLoginContext;
   protected String quorumServerLoginContext;
   protected int quorumCnxnThreadsSize;
   private boolean multiAddressEnabled;
   private boolean multiAddressReachabilityCheckEnabled;
   private int multiAddressReachabilityCheckTimeoutMs;
   protected String oraclePath;
   private final int MIN_SNAP_RETAIN_COUNT;
   protected boolean jvmPauseMonitorToRun;
   protected long jvmPauseWarnThresholdMs;
   protected long jvmPauseInfoThresholdMs;
   protected long jvmPauseSleepTimeMs;

   public QuorumPeerConfig() {
      this.peerType = QuorumPeer.LearnerType.PARTICIPANT;
      this.quorumServerRequireSasl = false;
      this.quorumLearnerRequireSasl = false;
      this.quorumEnableSasl = false;
      this.quorumServicePrincipal = "zkquorum/localhost";
      this.quorumLearnerLoginContext = "QuorumLearner";
      this.quorumServerLoginContext = "QuorumServer";
      this.multiAddressEnabled = Boolean.parseBoolean(System.getProperty("zookeeper.multiAddress.enabled", "false"));
      this.multiAddressReachabilityCheckEnabled = Boolean.parseBoolean(System.getProperty("zookeeper.multiAddress.reachabilityCheckEnabled", "true"));
      this.multiAddressReachabilityCheckTimeoutMs = Integer.parseInt(System.getProperty("zookeeper.multiAddress.reachabilityCheckTimeoutMs", String.valueOf(MultipleAddresses.DEFAULT_TIMEOUT.toMillis())));
      this.MIN_SNAP_RETAIN_COUNT = 3;
      this.jvmPauseMonitorToRun = false;
      this.jvmPauseWarnThresholdMs = 10000L;
      this.jvmPauseInfoThresholdMs = 1000L;
      this.jvmPauseSleepTimeMs = 500L;
   }

   public void parse(String path) throws ConfigException {
      LOG.info("Reading configuration from: " + path);

      try {
         File configFile = (new VerifyingFileFactory.Builder(LOG)).warnForRelativePath().failForNonExistingPath().build().create(path);
         Properties cfg = new Properties();
         FileInputStream in = new FileInputStream(configFile);

         try {
            cfg.load(in);
            this.configFileStr = path;
         } catch (Throwable var12) {
            try {
               in.close();
            } catch (Throwable var9) {
               var12.addSuppressed(var9);
            }

            throw var12;
         }

         in.close();
         this.initialConfig = new String(Files.readAllBytes(configFile.toPath()));
         this.parseProperties(cfg);
      } catch (IOException e) {
         throw new ConfigException("Error processing " + path, e);
      } catch (IllegalArgumentException e) {
         throw new ConfigException("Error processing " + path, e);
      }

      if (this.dynamicConfigFileStr != null) {
         try {
            Properties dynamicCfg = new Properties();
            FileInputStream inConfig = new FileInputStream(this.dynamicConfigFileStr);

            try {
               dynamicCfg.load(inConfig);
               if (dynamicCfg.getProperty("version") != null) {
                  throw new ConfigException("dynamic file shouldn't have version inside");
               }

               String version = getVersionFromFilename(this.dynamicConfigFileStr);
               if (version != null) {
                  dynamicCfg.setProperty("version", version);
               }
            } catch (Throwable var16) {
               try {
                  inConfig.close();
               } catch (Throwable var8) {
                  var16.addSuppressed(var8);
               }

               throw var16;
            }

            inConfig.close();
            this.setupQuorumPeerConfig(dynamicCfg, false);
         } catch (IOException e) {
            throw new ConfigException("Error processing " + this.dynamicConfigFileStr, e);
         } catch (IllegalArgumentException e) {
            throw new ConfigException("Error processing " + this.dynamicConfigFileStr, e);
         }

         File nextDynamicConfigFile = new File(this.configFileStr + ".dynamic.next");
         if (nextDynamicConfigFile.exists()) {
            try {
               Properties dynamicConfigNextCfg = new Properties();
               FileInputStream inConfigNext = new FileInputStream(nextDynamicConfigFile);

               try {
                  dynamicConfigNextCfg.load(inConfigNext);
               } catch (Throwable var11) {
                  try {
                     inConfigNext.close();
                  } catch (Throwable var10) {
                     var11.addSuppressed(var10);
                  }

                  throw var11;
               }

               inConfigNext.close();
               boolean isHierarchical = false;

               for(Map.Entry entry : dynamicConfigNextCfg.entrySet()) {
                  String key = entry.getKey().toString().trim();
                  if (key.startsWith("group") || key.startsWith("weight")) {
                     isHierarchical = true;
                     break;
                  }
               }

               this.lastSeenQuorumVerifier = createQuorumVerifier(dynamicConfigNextCfg, isHierarchical);
            } catch (IOException var15) {
               LOG.warn("NextQuorumVerifier is initiated to null");
            }
         }
      }

   }

   public static String getVersionFromFilename(String filename) {
      int i = filename.lastIndexOf(46);
      if (i >= 0 && i < filename.length()) {
         String hexVersion = filename.substring(i + 1);

         try {
            long version = Long.parseLong(hexVersion, 16);
            return Long.toHexString(version);
         } catch (NumberFormatException var5) {
            return null;
         }
      } else {
         return null;
      }
   }

   public void parseProperties(Properties zkProp) throws IOException, ConfigException {
      Integer clientPort = null;
      Integer secureClientPort = null;
      int observerMasterPort = 0;
      String clientPortAddress = null;
      String secureClientPortAddress = null;
      VerifyingFileFactory vff = (new VerifyingFileFactory.Builder(LOG)).warnForRelativePath().build();

      for(Map.Entry entry : zkProp.entrySet()) {
         String key = entry.getKey().toString().trim();
         String value = entry.getValue().toString().trim();
         if (key.equals("dataDir")) {
            this.dataDir = vff.create(value);
         } else if (key.equals("dataLogDir")) {
            this.dataLogDir = vff.create(value);
         } else if (key.equals("clientPort")) {
            clientPort = Integer.parseInt(value);
         } else if (key.equals("localSessionsEnabled")) {
            this.localSessionsEnabled = this.parseBoolean(key, value);
         } else if (key.equals("localSessionsUpgradingEnabled")) {
            this.localSessionsUpgradingEnabled = this.parseBoolean(key, value);
         } else if (key.equals("clientPortAddress")) {
            clientPortAddress = value.trim();
         } else if (key.equals("secureClientPort")) {
            secureClientPort = Integer.parseInt(value);
         } else if (key.equals("secureClientPortAddress")) {
            secureClientPortAddress = value.trim();
         } else if (key.equals("observerMasterPort")) {
            observerMasterPort = Integer.parseInt(value);
         } else if (key.equals("clientPortListenBacklog")) {
            this.clientPortListenBacklog = Integer.parseInt(value);
         } else if (key.equals("tickTime")) {
            this.tickTime = Integer.parseInt(value);
         } else if (key.equals("maxClientCnxns")) {
            this.maxClientCnxns = Integer.parseInt(value);
         } else if (key.equals("minSessionTimeout")) {
            this.minSessionTimeout = Integer.parseInt(value);
         } else if (key.equals("maxSessionTimeout")) {
            this.maxSessionTimeout = Integer.parseInt(value);
         } else if (key.equals("initLimit")) {
            this.initLimit = Integer.parseInt(value);
         } else if (key.equals("syncLimit")) {
            this.syncLimit = Integer.parseInt(value);
         } else if (key.equals("connectToLearnerMasterLimit")) {
            this.connectToLearnerMasterLimit = Integer.parseInt(value);
         } else if (key.equals("electionAlg")) {
            this.electionAlg = Integer.parseInt(value);
            if (this.electionAlg != 3) {
               throw new ConfigException("Invalid electionAlg value. Only 3 is supported.");
            }
         } else if (key.equals("quorumListenOnAllIPs")) {
            this.quorumListenOnAllIPs = this.parseBoolean(key, value);
         } else if (key.equals("peerType")) {
            if (value.toLowerCase().equals("observer")) {
               this.peerType = QuorumPeer.LearnerType.OBSERVER;
            } else {
               if (!value.toLowerCase().equals("participant")) {
                  throw new ConfigException("Unrecognised peertype: " + value);
               }

               this.peerType = QuorumPeer.LearnerType.PARTICIPANT;
            }
         } else if (key.equals("syncEnabled")) {
            this.syncEnabled = this.parseBoolean(key, value);
         } else if (key.equals("dynamicConfigFile")) {
            this.dynamicConfigFileStr = value;
         } else if (key.equals("autopurge.snapRetainCount")) {
            this.snapRetainCount = Integer.parseInt(value);
         } else if (key.equals("autopurge.purgeInterval")) {
            this.purgeInterval = Integer.parseInt(value);
         } else if (key.equals("standaloneEnabled")) {
            setStandaloneEnabled(this.parseBoolean(key, value));
         } else if (key.equals("reconfigEnabled")) {
            setReconfigEnabled(this.parseBoolean(key, value));
         } else if (key.equals("sslQuorum")) {
            this.sslQuorum = this.parseBoolean(key, value);
         } else if (key.equals("portUnification")) {
            this.shouldUsePortUnification = this.parseBoolean(key, value);
         } else if (key.equals("sslQuorumReloadCertFiles")) {
            this.sslQuorumReloadCertFiles = this.parseBoolean(key, value);
         } else {
            if ((key.startsWith("server.") || key.startsWith("group") || key.startsWith("weight")) && zkProp.containsKey("dynamicConfigFile")) {
               throw new ConfigException("parameter: " + key + " must be in a separate dynamic config file");
            }

            if (key.equals("quorum.auth.enableSasl")) {
               this.quorumEnableSasl = this.parseBoolean(key, value);
            } else if (key.equals("quorum.auth.serverRequireSasl")) {
               this.quorumServerRequireSasl = this.parseBoolean(key, value);
            } else if (key.equals("quorum.auth.learnerRequireSasl")) {
               this.quorumLearnerRequireSasl = this.parseBoolean(key, value);
            } else if (key.equals("quorum.auth.learner.saslLoginContext")) {
               this.quorumLearnerLoginContext = value;
            } else if (key.equals("quorum.auth.server.saslLoginContext")) {
               this.quorumServerLoginContext = value;
            } else if (key.equals("quorum.auth.kerberos.servicePrincipal")) {
               this.quorumServicePrincipal = value;
            } else if (key.equals("quorum.cnxn.threads.size")) {
               this.quorumCnxnThreadsSize = Integer.parseInt(value);
            } else if (key.equals("jvm.pause.info-threshold.ms")) {
               this.jvmPauseInfoThresholdMs = Long.parseLong(value);
            } else if (key.equals("jvm.pause.warn-threshold.ms")) {
               this.jvmPauseWarnThresholdMs = Long.parseLong(value);
            } else if (key.equals("jvm.pause.sleep.time.ms")) {
               this.jvmPauseSleepTimeMs = Long.parseLong(value);
            } else if (key.equals("jvm.pause.monitor")) {
               this.jvmPauseMonitorToRun = this.parseBoolean(key, value);
            } else if (key.equals("metricsProvider.className")) {
               this.metricsProviderClassName = value;
            } else if (key.startsWith("metricsProvider.")) {
               String keyForMetricsProvider = key.substring(16);
               this.metricsProviderConfiguration.put(keyForMetricsProvider, value);
            } else if (key.equals("multiAddress.enabled")) {
               this.multiAddressEnabled = this.parseBoolean(key, value);
            } else if (key.equals("multiAddress.reachabilityCheckTimeoutMs")) {
               this.multiAddressReachabilityCheckTimeoutMs = Integer.parseInt(value);
            } else if (key.equals("multiAddress.reachabilityCheckEnabled")) {
               this.multiAddressReachabilityCheckEnabled = this.parseBoolean(key, value);
            } else if (key.equals("oraclePath")) {
               this.oraclePath = value;
            } else {
               System.setProperty("zookeeper." + key, value);
            }
         }
      }

      if (!this.quorumEnableSasl && this.quorumServerRequireSasl) {
         throw new IllegalArgumentException("quorum.auth.enableSasl is disabled, so cannot enable quorum.auth.serverRequireSasl");
      } else if (!this.quorumEnableSasl && this.quorumLearnerRequireSasl) {
         throw new IllegalArgumentException("quorum.auth.enableSasl is disabled, so cannot enable quorum.auth.learnerRequireSasl");
      } else if (!this.quorumLearnerRequireSasl && this.quorumServerRequireSasl) {
         throw new IllegalArgumentException("quorum.auth.learnerRequireSasl is disabled, so cannot enable quorum.auth.serverRequireSasl");
      } else {
         if (this.snapRetainCount < 3) {
            LOG.warn("Invalid autopurge.snapRetainCount: " + this.snapRetainCount + ". Defaulting to " + 3);
            this.snapRetainCount = 3;
         }

         if (this.dataDir == null) {
            throw new IllegalArgumentException("dataDir is not set");
         } else {
            if (this.dataLogDir == null) {
               this.dataLogDir = this.dataDir;
            }

            if (clientPort == null) {
               LOG.info("clientPort is not set");
               if (clientPortAddress != null) {
                  throw new IllegalArgumentException("clientPortAddress is set but clientPort is not set");
               }
            } else if (clientPortAddress != null) {
               this.clientPortAddress = new InetSocketAddress(InetAddress.getByName(clientPortAddress), clientPort);
               LOG.info("clientPortAddress is {}", NetUtils.formatInetAddr(this.clientPortAddress));
            } else {
               this.clientPortAddress = new InetSocketAddress(clientPort);
               LOG.info("clientPortAddress is {}", NetUtils.formatInetAddr(this.clientPortAddress));
            }

            if (secureClientPort == null) {
               LOG.info("secureClientPort is not set");
               if (secureClientPortAddress != null) {
                  throw new IllegalArgumentException("secureClientPortAddress is set but secureClientPort is not set");
               }
            } else if (secureClientPortAddress != null) {
               this.secureClientPortAddress = new InetSocketAddress(InetAddress.getByName(secureClientPortAddress), secureClientPort);
               LOG.info("secureClientPortAddress is {}", NetUtils.formatInetAddr(this.secureClientPortAddress));
            } else {
               this.secureClientPortAddress = new InetSocketAddress(secureClientPort);
               LOG.info("secureClientPortAddress is {}", NetUtils.formatInetAddr(this.secureClientPortAddress));
            }

            if (this.secureClientPortAddress != null) {
               configureSSLAuth();
            }

            if (observerMasterPort <= 0) {
               LOG.info("observerMasterPort is not set");
            } else {
               this.observerMasterPort = observerMasterPort;
               LOG.info("observerMasterPort is {}", observerMasterPort);
            }

            if (this.tickTime == 0) {
               throw new IllegalArgumentException("tickTime is not set");
            } else {
               this.minSessionTimeout = this.minSessionTimeout == -1 ? this.tickTime * 2 : this.minSessionTimeout;
               this.maxSessionTimeout = this.maxSessionTimeout == -1 ? this.tickTime * 20 : this.maxSessionTimeout;
               if (this.minSessionTimeout > this.maxSessionTimeout) {
                  throw new IllegalArgumentException("minSessionTimeout must not be larger than maxSessionTimeout");
               } else {
                  LOG.info("metricsProvider.className is {}", this.metricsProviderClassName);

                  try {
                     Class.forName(this.metricsProviderClassName, false, Thread.currentThread().getContextClassLoader());
                  } catch (ClassNotFoundException error) {
                     throw new IllegalArgumentException("metrics provider class was not found", error);
                  }

                  if (this.dynamicConfigFileStr == null) {
                     this.setupQuorumPeerConfig(zkProp, true);
                     if (this.isDistributed() && isReconfigEnabled()) {
                        this.backupOldConfig();
                     }
                  }

               }
            }
         }
      }
   }

   public static void configureSSLAuth() throws ConfigException {
      ClientX509Util clientX509Util = new ClientX509Util();

      try {
         String sslAuthProp = "zookeeper.authProvider." + System.getProperty(clientX509Util.getSslAuthProviderProperty(), "x509");
         if (System.getProperty(sslAuthProp) == null) {
            if (!"zookeeper.authProvider.x509".equals(sslAuthProp)) {
               throw new ConfigException("No auth provider configured for the SSL authentication scheme '" + System.getProperty(clientX509Util.getSslAuthProviderProperty()) + "'.");
            }

            System.setProperty("zookeeper.authProvider.x509", "org.apache.zookeeper.server.auth.X509AuthenticationProvider");
         }
      } catch (Throwable var4) {
         try {
            clientX509Util.close();
         } catch (Throwable var3) {
            var4.addSuppressed(var3);
         }

         throw var4;
      }

      clientX509Util.close();
   }

   private void backupOldConfig() throws IOException {
      new AtomicFileWritingIdiom(new File(this.configFileStr + ".bak"), new AtomicFileWritingIdiom.OutputStreamStatement() {
         public void write(OutputStream output) throws IOException {
            InputStream input = new FileInputStream(new File(QuorumPeerConfig.this.configFileStr));

            try {
               byte[] buf = new byte[1024];

               int bytesRead;
               while((bytesRead = input.read(buf)) > 0) {
                  output.write(buf, 0, bytesRead);
               }
            } catch (Throwable var6) {
               try {
                  input.close();
               } catch (Throwable var5) {
                  var6.addSuppressed(var5);
               }

               throw var6;
            }

            input.close();
         }
      });
   }

   public static void writeDynamicConfig(String dynamicConfigFilename, final QuorumVerifier qv, final boolean needKeepVersion) throws IOException {
      new AtomicFileWritingIdiom(new File(dynamicConfigFilename), new AtomicFileWritingIdiom.WriterStatement() {
         public void write(Writer out) throws IOException {
            Properties cfg = new Properties();
            cfg.load(new StringReader(qv.toString()));
            List<String> servers = new ArrayList();

            for(Map.Entry entry : cfg.entrySet()) {
               String key = entry.getKey().toString().trim();
               if (needKeepVersion || !key.startsWith("version")) {
                  String value = entry.getValue().toString().trim();
                  servers.add(key.concat("=").concat(value));
               }
            }

            Collections.sort(servers);
            out.write(StringUtils.joinStrings(servers, "\n"));
         }
      });
   }

   public static void editStaticConfig(String configFileStr, String dynamicFileStr, final boolean eraseClientPortAddress) throws IOException {
      if (configFileStr != null) {
         File configFile = (new VerifyingFileFactory.Builder(LOG)).warnForRelativePath().failForNonExistingPath().build().create(configFileStr);
         final File dynamicFile = (new VerifyingFileFactory.Builder(LOG)).warnForRelativePath().failForNonExistingPath().build().create(dynamicFileStr);
         final Properties cfg = new Properties();
         FileInputStream in = new FileInputStream(configFile);

         try {
            cfg.load(in);
         } catch (Throwable var10) {
            try {
               in.close();
            } catch (Throwable var9) {
               var10.addSuppressed(var9);
            }

            throw var10;
         }

         in.close();
         new AtomicFileWritingIdiom(new File(configFileStr), new AtomicFileWritingIdiom.WriterStatement() {
            public void write(Writer out) throws IOException {
               for(Map.Entry entry : cfg.entrySet()) {
                  String key = entry.getKey().toString().trim();
                  if (!key.startsWith("server.") && !key.startsWith("group") && !key.startsWith("weight") && !key.startsWith("dynamicConfigFile") && !key.startsWith("peerType") && (!eraseClientPortAddress || !key.startsWith("clientPort") && !key.startsWith("clientPortAddress"))) {
                     String value = entry.getValue().toString().trim();
                     out.write(key.concat("=").concat(value).concat("\n"));
                  }
               }

               String dynamicConfigFilePath = PathUtils.normalizeFileSystemPath(dynamicFile.getCanonicalPath());
               out.write("dynamicConfigFile=".concat(dynamicConfigFilePath).concat("\n"));
            }
         });
      }
   }

   public static void deleteFile(String filename) {
      if (filename != null) {
         File f = new File(filename);
         if (f.exists()) {
            try {
               f.delete();
            } catch (Exception var3) {
               LOG.warn("deleting {} failed", filename);
            }
         }

      }
   }

   private static QuorumVerifier createQuorumVerifier(Properties dynamicConfigProp, boolean isHierarchical, String oraclePath) throws ConfigException {
      return (QuorumVerifier)(oraclePath == null ? createQuorumVerifier(dynamicConfigProp, isHierarchical) : new QuorumOracleMaj(dynamicConfigProp, oraclePath));
   }

   private static QuorumVerifier createQuorumVerifier(Properties dynamicConfigProp, boolean isHierarchical) throws ConfigException {
      return (QuorumVerifier)(isHierarchical ? new QuorumHierarchical(dynamicConfigProp) : new QuorumMaj(dynamicConfigProp));
   }

   void setupQuorumPeerConfig(Properties prop, boolean configBackwardCompatibilityMode) throws IOException, ConfigException {
      this.quorumVerifier = parseDynamicConfig(prop, this.electionAlg, true, configBackwardCompatibilityMode, this.oraclePath);
      this.setupMyId();
      this.setupClientPort();
      this.setupPeerType();
      this.checkValidity();
   }

   public static QuorumVerifier parseDynamicConfig(Properties dynamicConfigProp, int eAlg, boolean warnings, boolean configBackwardCompatibilityMode, String oraclePath) throws IOException, ConfigException {
      boolean isHierarchical = false;

      for(Map.Entry entry : dynamicConfigProp.entrySet()) {
         String key = entry.getKey().toString().trim();
         if (!key.startsWith("group") && !key.startsWith("weight")) {
            if (!configBackwardCompatibilityMode && !key.startsWith("server.") && !key.equals("version")) {
               LOG.info(dynamicConfigProp.toString());
               throw new ConfigException("Unrecognised parameter: " + key);
            }
         } else {
            isHierarchical = true;
         }
      }

      QuorumVerifier qv = createQuorumVerifier(dynamicConfigProp, isHierarchical, oraclePath);
      int numParticipators = qv.getVotingMembers().size();
      int numObservers = qv.getObservingMembers().size();
      if (numParticipators == 0) {
         if (!standaloneEnabled) {
            throw new IllegalArgumentException("standaloneEnabled = false then number of participants should be >0");
         }

         if (numObservers > 0) {
            throw new IllegalArgumentException("Observers w/o participants is an invalid configuration");
         }
      } else if (numParticipators == 1 && standaloneEnabled) {
         LOG.error("Invalid configuration, only one server specified (ignoring)");
         if (numObservers > 0) {
            throw new IllegalArgumentException("Observers w/o quorum is an invalid configuration");
         }
      } else {
         if (warnings) {
            if (numParticipators <= 2) {
               LOG.warn("No server failure will be tolerated. You need at least 3 servers.");
            } else if (numParticipators % 2 == 0) {
               LOG.warn("Non-optimal configuration, consider an odd number of servers.");
            }
         }

         for(QuorumPeer.QuorumServer s : qv.getVotingMembers().values()) {
            if (s.electionAddr == null) {
               throw new IllegalArgumentException("Missing election port for server: " + s.id);
            }
         }
      }

      return qv;
   }

   private void setupMyId() throws IOException {
      File myIdFile = new File(this.dataDir, "myid");
      if (myIdFile.isFile()) {
         BufferedReader br = new BufferedReader(new FileReader(myIdFile));

         String myIdString;
         try {
            myIdString = br.readLine();
         } finally {
            br.close();
         }

         try {
            this.serverId = Long.parseLong(myIdString);
            MDC.put("myid", myIdString);
         } catch (NumberFormatException var7) {
            throw new IllegalArgumentException("serverid " + myIdString + " is not a number");
         }
      }
   }

   private void setupClientPort() throws ConfigException {
      if (this.serverId != -1L) {
         QuorumPeer.QuorumServer qs = (QuorumPeer.QuorumServer)this.quorumVerifier.getAllMembers().get(this.serverId);
         if (this.clientPortAddress == null || qs == null || qs.clientAddr == null || (this.clientPortAddress.getAddress().isAnyLocalAddress() || this.clientPortAddress.equals(qs.clientAddr)) && (!this.clientPortAddress.getAddress().isAnyLocalAddress() || this.clientPortAddress.getPort() == qs.clientAddr.getPort())) {
            if (qs != null && qs.clientAddr != null) {
               this.clientPortAddress = qs.clientAddr;
            }

            if (qs != null && qs.clientAddr == null) {
               qs.clientAddr = this.clientPortAddress;
               qs.isClientAddrFromStatic = true;
            }

         } else {
            throw new ConfigException("client address for this server (id = " + this.serverId + ") in static config file is " + this.clientPortAddress + " is different from client address found in dynamic file: " + qs.clientAddr);
         }
      }
   }

   private void setupPeerType() {
      QuorumPeer.LearnerType roleByServersList = this.quorumVerifier.getObservingMembers().containsKey(this.serverId) ? QuorumPeer.LearnerType.OBSERVER : QuorumPeer.LearnerType.PARTICIPANT;
      if (roleByServersList != this.peerType) {
         LOG.warn("Peer type from servers list ({}) doesn't match peerType ({}). Defaulting to servers list.", roleByServersList, this.peerType);
         this.peerType = roleByServersList;
      }

   }

   public void checkValidity() throws IOException, ConfigException {
      if (this.isDistributed()) {
         if (this.initLimit == 0) {
            throw new IllegalArgumentException("initLimit is not set");
         }

         if (this.syncLimit == 0) {
            throw new IllegalArgumentException("syncLimit is not set");
         }

         if (this.serverId == -1L) {
            throw new IllegalArgumentException("myid file is missing");
         }
      }

   }

   public InetSocketAddress getClientPortAddress() {
      return this.clientPortAddress;
   }

   public InetSocketAddress getSecureClientPortAddress() {
      return this.secureClientPortAddress;
   }

   public int getObserverMasterPort() {
      return this.observerMasterPort;
   }

   public File getDataDir() {
      return this.dataDir;
   }

   public File getDataLogDir() {
      return this.dataLogDir;
   }

   public String getInitialConfig() {
      return this.initialConfig;
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

   public String getMetricsProviderClassName() {
      return this.metricsProviderClassName;
   }

   public Properties getMetricsProviderConfiguration() {
      return this.metricsProviderConfiguration;
   }

   public boolean areLocalSessionsEnabled() {
      return this.localSessionsEnabled;
   }

   public boolean isLocalSessionsUpgradingEnabled() {
      return this.localSessionsUpgradingEnabled;
   }

   public boolean isSslQuorum() {
      return this.sslQuorum;
   }

   public boolean shouldUsePortUnification() {
      return this.shouldUsePortUnification;
   }

   public int getClientPortListenBacklog() {
      return this.clientPortListenBacklog;
   }

   public int getInitLimit() {
      return this.initLimit;
   }

   public int getSyncLimit() {
      return this.syncLimit;
   }

   public int getConnectToLearnerMasterLimit() {
      return this.connectToLearnerMasterLimit;
   }

   public int getElectionAlg() {
      return this.electionAlg;
   }

   public int getElectionPort() {
      return this.electionPort;
   }

   public int getSnapRetainCount() {
      return this.snapRetainCount;
   }

   public int getPurgeInterval() {
      return this.purgeInterval;
   }

   public boolean getSyncEnabled() {
      return this.syncEnabled;
   }

   public QuorumVerifier getQuorumVerifier() {
      return this.quorumVerifier;
   }

   public QuorumVerifier getLastSeenQuorumVerifier() {
      return this.lastSeenQuorumVerifier;
   }

   public Map getServers() {
      return Collections.unmodifiableMap(this.quorumVerifier.getAllMembers());
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

   public long getServerId() {
      return this.serverId;
   }

   public boolean isDistributed() {
      return this.quorumVerifier != null && (!standaloneEnabled || this.quorumVerifier.getVotingMembers().size() > 1);
   }

   public QuorumPeer.LearnerType getPeerType() {
      return this.peerType;
   }

   public String getConfigFilename() {
      return this.configFileStr;
   }

   public Boolean getQuorumListenOnAllIPs() {
      return this.quorumListenOnAllIPs;
   }

   public boolean isMultiAddressEnabled() {
      return this.multiAddressEnabled;
   }

   public boolean isMultiAddressReachabilityCheckEnabled() {
      return this.multiAddressReachabilityCheckEnabled;
   }

   public int getMultiAddressReachabilityCheckTimeoutMs() {
      return this.multiAddressReachabilityCheckTimeoutMs;
   }

   public static boolean isStandaloneEnabled() {
      return standaloneEnabled;
   }

   public static void setStandaloneEnabled(boolean enabled) {
      standaloneEnabled = enabled;
   }

   public static boolean isReconfigEnabled() {
      return reconfigEnabled;
   }

   public static void setReconfigEnabled(boolean enabled) {
      reconfigEnabled = enabled;
   }

   private boolean parseBoolean(String key, String value) throws ConfigException {
      if (value.equalsIgnoreCase("true")) {
         return true;
      } else if (value.equalsIgnoreCase("false")) {
         return false;
      } else {
         throw new ConfigException("Invalid option " + value + " for " + key + ". Choose 'true' or 'false.'");
      }
   }

   public static class ConfigException extends Exception {
      public ConfigException(String msg) {
         super(msg);
      }

      public ConfigException(String msg, Exception e) {
         super(msg, e);
      }
   }
}
