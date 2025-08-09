package org.apache.spark.network.util;

import io.netty.util.NettyRuntime;
import java.io.File;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.primitives.Ints;

public class TransportConf {
   private final String SPARK_NETWORK_IO_MODE_KEY;
   private final String SPARK_NETWORK_IO_PREFERDIRECTBUFS_KEY;
   private final String SPARK_NETWORK_IO_CONNECTIONTIMEOUT_KEY;
   private final String SPARK_NETWORK_IO_CONNECTIONCREATIONTIMEOUT_KEY;
   private final String SPARK_NETWORK_IO_BACKLOG_KEY;
   private final String SPARK_NETWORK_IO_NUMCONNECTIONSPERPEER_KEY;
   private final String SPARK_NETWORK_IO_SERVERTHREADS_KEY;
   private final String SPARK_NETWORK_IO_CLIENTTHREADS_KEY;
   private final String SPARK_NETWORK_IO_RECEIVEBUFFER_KEY;
   private final String SPARK_NETWORK_IO_SENDBUFFER_KEY;
   private final String SPARK_NETWORK_SASL_TIMEOUT_KEY;
   private final String SPARK_NETWORK_IO_MAXRETRIES_KEY;
   private final String SPARK_NETWORK_IO_RETRYWAIT_KEY;
   private final String SPARK_NETWORK_IO_LAZYFD_KEY;
   private final String SPARK_NETWORK_VERBOSE_METRICS;
   private final String SPARK_NETWORK_IO_ENABLETCPKEEPALIVE_KEY;
   private final ConfigProvider conf;
   private final String module;

   public TransportConf(String module, ConfigProvider conf) {
      this.module = module;
      this.conf = conf;
      this.SPARK_NETWORK_IO_MODE_KEY = this.getConfKey("io.mode");
      this.SPARK_NETWORK_IO_PREFERDIRECTBUFS_KEY = this.getConfKey("io.preferDirectBufs");
      this.SPARK_NETWORK_IO_CONNECTIONTIMEOUT_KEY = this.getConfKey("io.connectionTimeout");
      this.SPARK_NETWORK_IO_CONNECTIONCREATIONTIMEOUT_KEY = this.getConfKey("io.connectionCreationTimeout");
      this.SPARK_NETWORK_IO_BACKLOG_KEY = this.getConfKey("io.backLog");
      this.SPARK_NETWORK_IO_NUMCONNECTIONSPERPEER_KEY = this.getConfKey("io.numConnectionsPerPeer");
      this.SPARK_NETWORK_IO_SERVERTHREADS_KEY = this.getConfKey("io.serverThreads");
      this.SPARK_NETWORK_IO_CLIENTTHREADS_KEY = this.getConfKey("io.clientThreads");
      this.SPARK_NETWORK_IO_RECEIVEBUFFER_KEY = this.getConfKey("io.receiveBuffer");
      this.SPARK_NETWORK_IO_SENDBUFFER_KEY = this.getConfKey("io.sendBuffer");
      this.SPARK_NETWORK_SASL_TIMEOUT_KEY = this.getConfKey("sasl.timeout");
      this.SPARK_NETWORK_IO_MAXRETRIES_KEY = this.getConfKey("io.maxRetries");
      this.SPARK_NETWORK_IO_RETRYWAIT_KEY = this.getConfKey("io.retryWait");
      this.SPARK_NETWORK_IO_LAZYFD_KEY = this.getConfKey("io.lazyFD");
      this.SPARK_NETWORK_VERBOSE_METRICS = this.getConfKey("io.enableVerboseMetrics");
      this.SPARK_NETWORK_IO_ENABLETCPKEEPALIVE_KEY = this.getConfKey("io.enableTcpKeepAlive");
   }

   public int getInt(String name, int defaultValue) {
      return this.conf.getInt(name, defaultValue);
   }

   public String get(String name, String defaultValue) {
      return this.conf.get(name, defaultValue);
   }

   private String getConfKey(String suffix) {
      return "spark." + this.module + "." + suffix;
   }

   public String getModuleName() {
      return this.module;
   }

   public String ioMode() {
      return this.conf.get(this.SPARK_NETWORK_IO_MODE_KEY, "NIO").toUpperCase(Locale.ROOT);
   }

   public boolean preferDirectBufs() {
      return this.conf.getBoolean(this.SPARK_NETWORK_IO_PREFERDIRECTBUFS_KEY, true);
   }

   public int connectionTimeoutMs() {
      long defaultNetworkTimeoutS = JavaUtils.timeStringAsSec(this.conf.get("spark.network.timeout", "120s"));
      long defaultTimeoutMs = JavaUtils.timeStringAsSec(this.conf.get(this.SPARK_NETWORK_IO_CONNECTIONTIMEOUT_KEY, defaultNetworkTimeoutS + "s")) * 1000L;
      return defaultTimeoutMs < 0L ? 0 : (int)defaultTimeoutMs;
   }

   public int connectionCreationTimeoutMs() {
      long connectionTimeoutS = TimeUnit.MILLISECONDS.toSeconds((long)this.connectionTimeoutMs());
      long defaultTimeoutMs = JavaUtils.timeStringAsSec(this.conf.get(this.SPARK_NETWORK_IO_CONNECTIONCREATIONTIMEOUT_KEY, connectionTimeoutS + "s")) * 1000L;
      return defaultTimeoutMs < 0L ? 0 : (int)defaultTimeoutMs;
   }

   public int numConnectionsPerPeer() {
      return this.conf.getInt(this.SPARK_NETWORK_IO_NUMCONNECTIONSPERPEER_KEY, 1);
   }

   public int backLog() {
      return this.conf.getInt(this.SPARK_NETWORK_IO_BACKLOG_KEY, -1);
   }

   public int serverThreads() {
      return this.conf.getInt(this.SPARK_NETWORK_IO_SERVERTHREADS_KEY, 0);
   }

   public int clientThreads() {
      return this.conf.getInt(this.SPARK_NETWORK_IO_CLIENTTHREADS_KEY, 0);
   }

   public int receiveBuf() {
      return this.conf.getInt(this.SPARK_NETWORK_IO_RECEIVEBUFFER_KEY, -1);
   }

   public int sendBuf() {
      return this.conf.getInt(this.SPARK_NETWORK_IO_SENDBUFFER_KEY, -1);
   }

   public int authRTTimeoutMs() {
      return (int)JavaUtils.timeStringAsSec(this.conf.get("spark.network.auth.rpcTimeout", this.conf.get(this.SPARK_NETWORK_SASL_TIMEOUT_KEY, "30s"))) * 1000;
   }

   public int maxIORetries() {
      return this.conf.getInt(this.SPARK_NETWORK_IO_MAXRETRIES_KEY, 3);
   }

   public int ioRetryWaitTimeMs() {
      return (int)JavaUtils.timeStringAsSec(this.conf.get(this.SPARK_NETWORK_IO_RETRYWAIT_KEY, "5s")) * 1000;
   }

   public int memoryMapBytes() {
      return Ints.checkedCast(JavaUtils.byteStringAsBytes(this.conf.get("spark.storage.memoryMapThreshold", "2m")));
   }

   public boolean lazyFileDescriptor() {
      return this.conf.getBoolean(this.SPARK_NETWORK_IO_LAZYFD_KEY, true);
   }

   public boolean verboseMetrics() {
      return this.conf.getBoolean(this.SPARK_NETWORK_VERBOSE_METRICS, false);
   }

   public boolean enableTcpKeepAlive() {
      return this.conf.getBoolean(this.SPARK_NETWORK_IO_ENABLETCPKEEPALIVE_KEY, false);
   }

   public int portMaxRetries() {
      return this.conf.getInt("spark.port.maxRetries", 16);
   }

   public boolean encryptionEnabled() {
      return this.conf.getBoolean("spark.network.crypto.enabled", false);
   }

   public int authEngineVersion() {
      return this.conf.getInt("spark.network.crypto.authEngineVersion", 1);
   }

   public String cipherTransformation() {
      return this.conf.get("spark.network.crypto.cipher", "AES/CTR/NoPadding");
   }

   public boolean saslFallback() {
      return this.conf.getBoolean("spark.network.crypto.saslFallback", true);
   }

   public boolean saslEncryption() {
      return this.conf.getBoolean("spark.authenticate.enableSaslEncryption", false);
   }

   public int maxSaslEncryptedBlockSize() {
      return Ints.checkedCast(JavaUtils.byteStringAsBytes(this.conf.get("spark.network.sasl.maxEncryptedBlockSize", "64k")));
   }

   public boolean saslServerAlwaysEncrypt() {
      return this.conf.getBoolean("spark.network.sasl.serverAlwaysEncrypt", false);
   }

   public int sslShuffleChunkSize() {
      return Ints.checkedCast(JavaUtils.byteStringAsBytes(this.conf.get("spark.network.ssl.maxEncryptedBlockSize", "64k")));
   }

   public boolean sslRpcEnabled() {
      return this.conf.getBoolean("spark.ssl.rpc.enabled", false);
   }

   public String sslRpcProtocol() {
      return this.conf.get("spark.ssl.rpc.protocol", (String)null);
   }

   public String[] sslRpcRequestedCiphers() {
      String ciphers = this.conf.get("spark.ssl.rpc.enabledAlgorithms", (String)null);
      return ciphers != null ? ciphers.split(",") : null;
   }

   public File sslRpcKeyStore() {
      String keyStore = this.conf.get("spark.ssl.rpc.keyStore", (String)null);
      return keyStore != null ? new File(keyStore) : null;
   }

   public String sslRpcKeyStorePassword() {
      return this.conf.get("spark.ssl.rpc.keyStorePassword", (String)null);
   }

   public String sslRpcKeyPassword() {
      return this.conf.get("spark.ssl.rpc.keyPassword", (String)null);
   }

   public File sslRpcPrivateKey() {
      String privateKey = this.conf.get("spark.ssl.rpc.privateKey", (String)null);
      return privateKey != null ? new File(privateKey) : null;
   }

   public String sslRpcPrivateKeyPassword() {
      return this.conf.get("spark.ssl.rpc.privateKeyPassword", (String)null);
   }

   public File sslRpcCertChain() {
      String certChain = this.conf.get("spark.ssl.rpc.certChain", (String)null);
      return certChain != null ? new File(certChain) : null;
   }

   public File sslRpcTrustStore() {
      String trustStore = this.conf.get("spark.ssl.rpc.trustStore", (String)null);
      return trustStore != null ? new File(trustStore) : null;
   }

   public String sslRpcTrustStorePassword() {
      return this.conf.get("spark.ssl.rpc.trustStorePassword", (String)null);
   }

   public boolean sslRpcTrustStoreReloadingEnabled() {
      return this.conf.getBoolean("spark.ssl.rpc.trustStoreReloadingEnabled", false);
   }

   public int sslRpctrustStoreReloadIntervalMs() {
      return this.conf.getInt("spark.ssl.rpc.trustStoreReloadIntervalMs", 10000);
   }

   public boolean sslRpcOpenSslEnabled() {
      return this.conf.getBoolean("spark.ssl.rpc.openSslEnabled", false);
   }

   public boolean sslRpcEnabledAndKeysAreValid() {
      if (!this.sslRpcEnabled()) {
         return false;
      } else if (this.sslRpcOpenSslEnabled()) {
         File privateKey = this.sslRpcPrivateKey();
         if (privateKey != null && privateKey.exists()) {
            File certChain = this.sslRpcCertChain();
            return certChain != null && certChain.exists();
         } else {
            return false;
         }
      } else {
         File keyStore = this.sslRpcKeyStore();
         return keyStore != null && keyStore.exists();
      }
   }

   public boolean sharedByteBufAllocators() {
      return this.conf.getBoolean("spark.network.sharedByteBufAllocators.enabled", true);
   }

   public boolean preferDirectBufsForSharedByteBufAllocators() {
      return this.conf.getBoolean("spark.network.io.preferDirectBufs", true);
   }

   public Properties cryptoConf() {
      return CryptoUtils.toCryptoConf("spark.network.crypto.config.", this.conf.getAll());
   }

   public long maxChunksBeingTransferred() {
      return this.conf.getLong("spark.shuffle.maxChunksBeingTransferred", Long.MAX_VALUE);
   }

   public int chunkFetchHandlerThreads() {
      if (!this.getModuleName().equalsIgnoreCase("shuffle")) {
         return 0;
      } else {
         int chunkFetchHandlerThreadsPercent = Integer.parseInt(this.conf.get("spark.shuffle.server.chunkFetchHandlerThreadsPercent"));
         int threads = this.serverThreads() > 0 ? this.serverThreads() : 2 * NettyRuntime.availableProcessors();
         return (int)Math.ceil((double)threads * ((double)chunkFetchHandlerThreadsPercent / (double)100.0F));
      }
   }

   public boolean separateChunkFetchRequest() {
      return this.conf.getInt("spark.shuffle.server.chunkFetchHandlerThreadsPercent", 0) > 0;
   }

   public int finalizeShuffleMergeHandlerThreads() {
      if (!this.getModuleName().equalsIgnoreCase("shuffle")) {
         return 0;
      } else {
         Preconditions.checkArgument(this.separateFinalizeShuffleMerge(), "Please set spark.shuffle.server.finalizeShuffleMergeThreadsPercent to a positive value");
         int finalizeShuffleMergeThreadsPercent = Integer.parseInt(this.conf.get("spark.shuffle.server.finalizeShuffleMergeThreadsPercent"));
         int threads = this.serverThreads() > 0 ? this.serverThreads() : 2 * NettyRuntime.availableProcessors();
         return (int)Math.ceil((double)threads * ((double)finalizeShuffleMergeThreadsPercent / (double)100.0F));
      }
   }

   public boolean separateFinalizeShuffleMerge() {
      return this.conf.getInt("spark.shuffle.server.finalizeShuffleMergeThreadsPercent", 0) > 0;
   }

   public boolean useOldFetchProtocol() {
      return this.conf.getBoolean("spark.shuffle.useOldFetchProtocol", false);
   }

   public boolean enableSaslRetries() {
      return this.conf.getBoolean("spark.shuffle.sasl.enableRetries", false);
   }

   public String mergedShuffleFileManagerImpl() {
      return this.conf.get("spark.shuffle.push.server.mergedShuffleFileManagerImpl", "org.apache.spark.network.shuffle.NoOpMergedShuffleFileManager");
   }

   public int minChunkSizeInMergedShuffleFile() {
      return Ints.checkedCast(JavaUtils.byteStringAsBytes(this.conf.get("spark.shuffle.push.server.minChunkSizeInMergedShuffleFile", "2m")));
   }

   public long mergedIndexCacheSize() {
      return JavaUtils.byteStringAsBytes(this.conf.get("spark.shuffle.push.server.mergedIndexCacheSize", "100m"));
   }

   public int ioExceptionsThresholdDuringMerge() {
      return this.conf.getInt("spark.shuffle.push.server.ioExceptionsThresholdDuringMerge", 4);
   }

   public long mergedShuffleCleanerShutdownTimeout() {
      return JavaUtils.timeStringAsSec(this.conf.get("spark.shuffle.push.server.mergedShuffleCleaner.shutdown.timeout", "60s"));
   }
}
