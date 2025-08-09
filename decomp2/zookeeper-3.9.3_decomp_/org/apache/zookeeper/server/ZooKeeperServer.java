package org.apache.zookeeper.server;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import javax.security.sasl.SaslException;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.InputArchive;
import org.apache.jute.Record;
import org.apache.zookeeper.Environment;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Quotas;
import org.apache.zookeeper.StatsTrack;
import org.apache.zookeeper.Version;
import org.apache.zookeeper.ZookeeperBanner;
import org.apache.zookeeper.common.PathUtils;
import org.apache.zookeeper.common.StringUtils;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.data.StatPersisted;
import org.apache.zookeeper.jmx.MBeanRegistry;
import org.apache.zookeeper.jmx.ZKMBeanInfo;
import org.apache.zookeeper.metrics.MetricsContext;
import org.apache.zookeeper.proto.AuthPacket;
import org.apache.zookeeper.proto.ConnectRequest;
import org.apache.zookeeper.proto.ConnectResponse;
import org.apache.zookeeper.proto.CreateRequest;
import org.apache.zookeeper.proto.DeleteRequest;
import org.apache.zookeeper.proto.GetSASLRequest;
import org.apache.zookeeper.proto.ReplyHeader;
import org.apache.zookeeper.proto.RequestHeader;
import org.apache.zookeeper.proto.SetACLRequest;
import org.apache.zookeeper.proto.SetDataRequest;
import org.apache.zookeeper.proto.SetSASLResponse;
import org.apache.zookeeper.server.auth.ProviderRegistry;
import org.apache.zookeeper.server.auth.ServerAuthenticationProvider;
import org.apache.zookeeper.server.persistence.FileTxnSnapLog;
import org.apache.zookeeper.server.quorum.BufferStats;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.apache.zookeeper.server.quorum.ReadOnlyZooKeeperServer;
import org.apache.zookeeper.server.util.JvmPauseMonitor;
import org.apache.zookeeper.server.util.OSMXBean;
import org.apache.zookeeper.server.util.QuotaMetricsUtils;
import org.apache.zookeeper.server.util.RequestPathMetricsCollector;
import org.apache.zookeeper.txn.CreateSessionTxn;
import org.apache.zookeeper.txn.TxnDigest;
import org.apache.zookeeper.txn.TxnHeader;
import org.apache.zookeeper.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZooKeeperServer implements SessionTracker.SessionExpirer, ServerStats.Provider {
   protected static final Logger LOG = LoggerFactory.getLogger(ZooKeeperServer.class);
   private static final RateLogger RATE_LOGGER;
   public static final String GLOBAL_OUTSTANDING_LIMIT = "zookeeper.globalOutstandingLimit";
   public static final String ENABLE_EAGER_ACL_CHECK = "zookeeper.enableEagerACLCheck";
   public static final String SKIP_ACL = "zookeeper.skipACL";
   public static final String ENFORCE_QUOTA = "zookeeper.enforceQuota";
   static boolean enableEagerACLCheck;
   static final boolean skipACL;
   public static final boolean enforceQuota;
   public static final String SASL_SUPER_USER = "zookeeper.superUser";
   public static final String ALLOW_SASL_FAILED_CLIENTS = "zookeeper.allowSaslFailedClients";
   public static final String ZOOKEEPER_DIGEST_ENABLED = "zookeeper.digest.enabled";
   private static boolean digestEnabled;
   public static final String ZOOKEEPER_SERIALIZE_LAST_PROCESSED_ZXID_ENABLED = "zookeeper.serializeLastProcessedZxid.enabled";
   private static boolean serializeLastProcessedZxidEnabled;
   public static final String CLOSE_SESSION_TXN_ENABLED = "zookeeper.closeSessionTxn.enabled";
   private static boolean closeSessionTxnEnabled = true;
   private volatile CountDownLatch restoreLatch;
   protected ZooKeeperServerBean jmxServerBean;
   protected DataTreeBean jmxDataTreeBean;
   public static final int DEFAULT_TICK_TIME = 3000;
   protected int tickTime;
   public static final int DEFAULT_THROTTLED_OP_WAIT_TIME = 0;
   protected static volatile int throttledOpWaitTime;
   protected int minSessionTimeout;
   protected int maxSessionTimeout;
   protected int listenBacklog;
   protected SessionTracker sessionTracker;
   private FileTxnSnapLog txnLogFactory;
   private ZKDatabase zkDb;
   private ResponseCache readResponseCache;
   private ResponseCache getChildrenResponseCache;
   private final AtomicLong hzxid;
   public static final Exception ok;
   protected RequestProcessor firstProcessor;
   protected JvmPauseMonitor jvmPauseMonitor;
   protected volatile State state;
   private boolean isResponseCachingEnabled;
   protected String initialConfig;
   protected boolean reconfigEnabled;
   private final RequestPathMetricsCollector requestPathMetricsCollector;
   private static final int DEFAULT_SNAP_COUNT = 100000;
   private static final int DEFAULT_GLOBAL_OUTSTANDING_LIMIT = 1000;
   private boolean localSessionEnabled;
   private static final long superSecret = 3007405056L;
   private final AtomicInteger requestsInProcess;
   final Deque outstandingChanges;
   final Map outstandingChangesForPath;
   protected ServerCnxnFactory serverCnxnFactory;
   protected ServerCnxnFactory secureServerCnxnFactory;
   private final ServerStats serverStats;
   private final ZooKeeperServerListener listener;
   private ZooKeeperServerShutdownHandler zkShutdownHandler;
   private volatile int createSessionTrackerServerId;
   private static final String FLUSH_DELAY = "zookeeper.flushDelay";
   private static volatile long flushDelay;
   private static final String MAX_WRITE_QUEUE_POLL_SIZE = "zookeeper.maxWriteQueuePollTime";
   private static volatile long maxWriteQueuePollTime;
   private static final String MAX_BATCH_SIZE = "zookeeper.maxBatchSize";
   private static volatile int maxBatchSize;
   public static final String INT_BUFFER_STARTING_SIZE_BYTES = "zookeeper.intBufferStartingSizeBytes";
   public static final int DEFAULT_STARTING_BUFFER_SIZE = 1024;
   public static final int intBufferStartingSizeBytes;
   public static final String GET_DATA_RESPONSE_CACHE_SIZE = "zookeeper.maxResponseCacheSize";
   public static final String GET_CHILDREN_RESPONSE_CACHE_SIZE = "zookeeper.maxGetChildrenResponseCacheSize";
   private final BlueThrottle connThrottle;
   @SuppressFBWarnings(
      value = {"IS2_INCONSISTENT_SYNC"},
      justification = "Internally the throttler has a BlockingQueue so once the throttler is created and started, it is thread-safe"
   )
   private RequestThrottler requestThrottler;
   public static final String SNAP_COUNT = "zookeeper.snapCount";
   private volatile int largeRequestMaxBytes;
   private volatile int largeRequestThreshold;
   private final AtomicInteger currentLargeRequestBytes;
   private final AuthenticationHelper authHelper;

   public static boolean isEnableEagerACLCheck() {
      return enableEagerACLCheck;
   }

   public static void setEnableEagerACLCheck(boolean enabled) {
      enableEagerACLCheck = enabled;
      LOG.info("Update {} to {}", "zookeeper.enableEagerACLCheck", enabled);
   }

   public static boolean isCloseSessionTxnEnabled() {
      return closeSessionTxnEnabled;
   }

   public static void setCloseSessionTxnEnabled(boolean enabled) {
      closeSessionTxnEnabled = enabled;
      LOG.info("Update {} to {}", "zookeeper.closeSessionTxn.enabled", closeSessionTxnEnabled);
   }

   void removeCnxn(ServerCnxn cnxn) {
      this.zkDb.removeCnxn(cnxn);
   }

   public ZooKeeperServer() {
      this.tickTime = 3000;
      this.minSessionTimeout = -1;
      this.maxSessionTimeout = -1;
      this.listenBacklog = -1;
      this.txnLogFactory = null;
      this.hzxid = new AtomicLong(0L);
      this.state = ZooKeeperServer.State.INITIAL;
      this.isResponseCachingEnabled = true;
      this.localSessionEnabled = false;
      this.requestsInProcess = new AtomicInteger(0);
      this.outstandingChanges = new ArrayDeque();
      this.outstandingChangesForPath = new HashMap();
      this.createSessionTrackerServerId = 1;
      this.connThrottle = new BlueThrottle();
      this.largeRequestMaxBytes = 104857600;
      this.largeRequestThreshold = -1;
      this.currentLargeRequestBytes = new AtomicInteger(0);
      this.authHelper = new AuthenticationHelper();
      this.listener = new ZooKeeperServerListenerImpl(this);
      this.serverStats = new ServerStats(this);
      this.requestPathMetricsCollector = new RequestPathMetricsCollector();
   }

   public ZooKeeperServer(FileTxnSnapLog txnLogFactory, int tickTime, int minSessionTimeout, int maxSessionTimeout, int clientPortListenBacklog, ZKDatabase zkDb, String initialConfig) {
      this(txnLogFactory, tickTime, minSessionTimeout, maxSessionTimeout, clientPortListenBacklog, zkDb, initialConfig, QuorumPeerConfig.isReconfigEnabled());
   }

   public ZooKeeperServer(FileTxnSnapLog txnLogFactory, int tickTime, int minSessionTimeout, int maxSessionTimeout, int clientPortListenBacklog, ZKDatabase zkDb, String initialConfig, boolean reconfigEnabled) {
      this.tickTime = 3000;
      this.minSessionTimeout = -1;
      this.maxSessionTimeout = -1;
      this.listenBacklog = -1;
      this.txnLogFactory = null;
      this.hzxid = new AtomicLong(0L);
      this.state = ZooKeeperServer.State.INITIAL;
      this.isResponseCachingEnabled = true;
      this.localSessionEnabled = false;
      this.requestsInProcess = new AtomicInteger(0);
      this.outstandingChanges = new ArrayDeque();
      this.outstandingChangesForPath = new HashMap();
      this.createSessionTrackerServerId = 1;
      this.connThrottle = new BlueThrottle();
      this.largeRequestMaxBytes = 104857600;
      this.largeRequestThreshold = -1;
      this.currentLargeRequestBytes = new AtomicInteger(0);
      this.authHelper = new AuthenticationHelper();
      this.serverStats = new ServerStats(this);
      this.txnLogFactory = txnLogFactory;
      this.txnLogFactory.setServerStats(this.serverStats);
      this.zkDb = zkDb;
      this.tickTime = tickTime;
      this.setMinSessionTimeout(minSessionTimeout);
      this.setMaxSessionTimeout(maxSessionTimeout);
      this.listenBacklog = clientPortListenBacklog;
      this.reconfigEnabled = reconfigEnabled;
      this.listener = new ZooKeeperServerListenerImpl(this);
      this.readResponseCache = new ResponseCache(Integer.getInteger("zookeeper.maxResponseCacheSize", 400), "getData");
      this.getChildrenResponseCache = new ResponseCache(Integer.getInteger("zookeeper.maxGetChildrenResponseCacheSize", 400), "getChildren");
      this.initialConfig = initialConfig;
      this.requestPathMetricsCollector = new RequestPathMetricsCollector();
      this.initLargeRequestThrottlingSettings();
      LOG.info("Created server with tickTime {} ms minSessionTimeout {} ms maxSessionTimeout {} ms clientPortListenBacklog {} dataLogdir {} snapdir {}", new Object[]{tickTime, this.getMinSessionTimeout(), this.getMaxSessionTimeout(), this.getClientPortListenBacklog(), txnLogFactory.getDataLogDir(), txnLogFactory.getSnapDir()});
   }

   public String getInitialConfig() {
      return this.initialConfig;
   }

   public ZooKeeperServer(JvmPauseMonitor jvmPauseMonitor, FileTxnSnapLog txnLogFactory, int tickTime, int minSessionTimeout, int maxSessionTimeout, int clientPortListenBacklog, ZKDatabase zkDb, String initialConfig) {
      this(txnLogFactory, tickTime, minSessionTimeout, maxSessionTimeout, clientPortListenBacklog, zkDb, initialConfig, QuorumPeerConfig.isReconfigEnabled());
      this.jvmPauseMonitor = jvmPauseMonitor;
      if (jvmPauseMonitor != null) {
         LOG.info("Added JvmPauseMonitor to server");
      }

   }

   public ZooKeeperServer(FileTxnSnapLog txnLogFactory, int tickTime, String initialConfig) {
      this(txnLogFactory, tickTime, -1, -1, -1, new ZKDatabase(txnLogFactory), initialConfig, QuorumPeerConfig.isReconfigEnabled());
   }

   public ServerStats serverStats() {
      return this.serverStats;
   }

   public RequestPathMetricsCollector getRequestPathMetricsCollector() {
      return this.requestPathMetricsCollector;
   }

   public BlueThrottle connThrottle() {
      return this.connThrottle;
   }

   public void dumpConf(PrintWriter pwriter) {
      pwriter.print("clientPort=");
      pwriter.println(this.getClientPort());
      pwriter.print("secureClientPort=");
      pwriter.println(this.getSecureClientPort());
      pwriter.print("dataDir=");
      pwriter.println(this.zkDb.snapLog.getSnapDir().getAbsolutePath());
      pwriter.print("dataDirSize=");
      pwriter.println(this.getDataDirSize());
      pwriter.print("dataLogDir=");
      pwriter.println(this.zkDb.snapLog.getDataLogDir().getAbsolutePath());
      pwriter.print("dataLogSize=");
      pwriter.println(this.getLogDirSize());
      pwriter.print("tickTime=");
      pwriter.println(this.getTickTime());
      pwriter.print("maxClientCnxns=");
      pwriter.println(this.getMaxClientCnxnsPerHost());
      pwriter.print("minSessionTimeout=");
      pwriter.println(this.getMinSessionTimeout());
      pwriter.print("maxSessionTimeout=");
      pwriter.println(this.getMaxSessionTimeout());
      pwriter.print("clientPortListenBacklog=");
      pwriter.println(this.getClientPortListenBacklog());
      pwriter.print("serverId=");
      pwriter.println(this.getServerId());
   }

   public ZooKeeperServerConf getConf() {
      return new ZooKeeperServerConf(this.getClientPort(), this.zkDb.snapLog.getSnapDir().getAbsolutePath(), this.zkDb.snapLog.getDataLogDir().getAbsolutePath(), this.getTickTime(), this.getMaxClientCnxnsPerHost(), this.getMinSessionTimeout(), this.getMaxSessionTimeout(), this.getServerId(), this.getClientPortListenBacklog());
   }

   public ZooKeeperServer(File snapDir, File logDir, int tickTime) throws IOException {
      this(new FileTxnSnapLog(snapDir, logDir), tickTime, "");
   }

   public ZooKeeperServer(FileTxnSnapLog txnLogFactory) throws IOException {
      this(txnLogFactory, 3000, -1, -1, -1, new ZKDatabase(txnLogFactory), "", QuorumPeerConfig.isReconfigEnabled());
   }

   public ZKDatabase getZKDatabase() {
      return this.zkDb;
   }

   public void setZKDatabase(ZKDatabase zkDb) {
      this.zkDb = zkDb;
   }

   public void loadData() throws IOException, InterruptedException {
      if (this.zkDb.isInitialized()) {
         this.setZxid(this.zkDb.getDataTreeLastProcessedZxid());
      } else {
         this.setZxid(this.zkDb.loadDataBase());
      }

      this.zkDb.getSessions().stream().filter((session) -> this.zkDb.getSessionWithTimeOuts().get(session) == null).forEach((session) -> this.killSession(session, this.zkDb.getDataTreeLastProcessedZxid()));
      this.takeSnapshot();
   }

   public File takeSnapshot() throws IOException {
      return this.takeSnapshot(false);
   }

   public File takeSnapshot(boolean syncSnap) throws IOException {
      return this.takeSnapshot(syncSnap, true, false);
   }

   public synchronized File takeSnapshot(boolean syncSnap, boolean isSevere, boolean fastForwardFromEdits) throws IOException {
      long start = Time.currentElapsedTime();
      File snapFile = null;

      try {
         if (fastForwardFromEdits) {
            this.zkDb.fastForwardDataBase();
         }

         snapFile = this.txnLogFactory.save(this.zkDb.getDataTree(), this.zkDb.getSessionWithTimeOuts(), syncSnap);
      } catch (IOException e) {
         if (!isSevere) {
            throw e;
         }

         LOG.error("Severe unrecoverable error, exiting", e);
         ServiceUtils.requestSystemExit(ExitCode.TXNLOG_ERROR_TAKING_SNAPSHOT.getValue());
      }

      long elapsed = Time.currentElapsedTime() - start;
      LOG.info("Snapshot taken in {} ms", elapsed);
      ServerMetrics.getMetrics().SNAPSHOT_TIME.add(elapsed);
      return snapFile;
   }

   public synchronized long restoreFromSnapshot(InputStream inputStream) throws IOException {
      if (inputStream == null) {
         throw new IllegalArgumentException("InputStream can not be null when restoring from snapshot");
      } else {
         long start = Time.currentElapsedTime();
         LOG.info("Before restore database. lastProcessedZxid={}, nodeCount={}，sessionCount={}", new Object[]{this.getZKDatabase().getDataTreeLastProcessedZxid(), this.getZKDatabase().dataTree.getNodeCount(), this.getZKDatabase().getSessionCount()});
         ZKDatabase newZKDatabase = new ZKDatabase(this.txnLogFactory);
         CheckedInputStream cis = new CheckedInputStream(new BufferedInputStream(inputStream), new Adler32());
         InputArchive ia = BinaryInputArchive.getArchive(cis);
         newZKDatabase.deserializeSnapshot(ia, cis);
         LOG.info("Restored to a new database. lastProcessedZxid={}, nodeCount={}, sessionCount={}", new Object[]{newZKDatabase.getDataTreeLastProcessedZxid(), newZKDatabase.dataTree.getNodeCount(), newZKDatabase.getSessionCount()});
         this.restoreLatch = new CountDownLatch(1);

         try {
            this.setZKDatabase(newZKDatabase);
            this.createSessionTracker();
         } finally {
            this.restoreLatch.countDown();
            this.restoreLatch = null;
         }

         LOG.info("After restore database. lastProcessedZxid={}, nodeCount={}, sessionCount={}", new Object[]{this.getZKDatabase().getDataTreeLastProcessedZxid(), this.getZKDatabase().dataTree.getNodeCount(), this.getZKDatabase().getSessionCount()});
         long elapsed = Time.currentElapsedTime() - start;
         LOG.info("Restore taken in {} ms", elapsed);
         ServerMetrics.getMetrics().RESTORE_TIME.add(elapsed);
         return this.getLastProcessedZxid();
      }
   }

   public boolean shouldForceWriteInitialSnapshotAfterLeaderElection() {
      return this.txnLogFactory.shouldForceWriteInitialSnapshotAfterLeaderElection();
   }

   public long getDataDirSize() {
      if (this.zkDb == null) {
         return 0L;
      } else {
         File path = this.zkDb.snapLog.getSnapDir();
         return this.getDirSize(path);
      }
   }

   public long getLogDirSize() {
      if (this.zkDb == null) {
         return 0L;
      } else {
         File path = this.zkDb.snapLog.getDataLogDir();
         return this.getDirSize(path);
      }
   }

   private long getDirSize(File file) {
      long size = 0L;
      if (file.isDirectory()) {
         File[] files = file.listFiles();
         if (files != null) {
            for(File f : files) {
               size += this.getDirSize(f);
            }
         }
      } else {
         size = file.length();
      }

      return size;
   }

   public long getZxid() {
      return this.hzxid.get();
   }

   public SessionTracker getSessionTracker() {
      return this.sessionTracker;
   }

   long getNextZxid() {
      return this.hzxid.incrementAndGet();
   }

   public void setZxid(long zxid) {
      this.hzxid.set(zxid);
   }

   private void close(long sessionId) {
      Request si = new Request((ServerCnxn)null, sessionId, 0, -11, (RequestRecord)null, (List)null);
      this.submitRequest(si);
   }

   public void closeSession(long sessionId) {
      LOG.info("Closing session 0x{}", Long.toHexString(sessionId));
      this.close(sessionId);
   }

   protected void killSession(long sessionId, long zxid) {
      this.zkDb.killSession(sessionId, zxid);
      if (LOG.isTraceEnabled()) {
         ZooTrace.logTraceMessage(LOG, 32L, "ZooKeeperServer --- killSession: 0x" + Long.toHexString(sessionId));
      }

      if (this.sessionTracker != null) {
         this.sessionTracker.removeSession(sessionId);
      }

   }

   public void expire(SessionTracker.Session session) {
      long sessionId = session.getSessionId();
      LOG.info("Expiring session 0x{}, timeout of {}ms exceeded", Long.toHexString(sessionId), session.getTimeout());
      this.close(sessionId);
   }

   public void expire(long sessionId) {
      LOG.info("forcibly expiring session 0x{}", Long.toHexString(sessionId));
      this.close(sessionId);
   }

   void touch(ServerCnxn cnxn) throws MissingSessionException {
      if (cnxn != null) {
         long id = cnxn.getSessionId();
         int to = cnxn.getSessionTimeout();
         if (!this.sessionTracker.touchSession(id, to)) {
            throw new MissingSessionException("No session with sessionid 0x" + Long.toHexString(id) + " exists, probably expired and removed");
         }
      }
   }

   protected void registerJMX() {
      try {
         this.jmxServerBean = new ZooKeeperServerBean(this);
         MBeanRegistry.getInstance().register(this.jmxServerBean, (ZKMBeanInfo)null);

         try {
            this.jmxDataTreeBean = new DataTreeBean(this.zkDb.getDataTree());
            MBeanRegistry.getInstance().register(this.jmxDataTreeBean, this.jmxServerBean);
         } catch (Exception e) {
            LOG.warn("Failed to register with JMX", e);
            this.jmxDataTreeBean = null;
         }
      } catch (Exception e) {
         LOG.warn("Failed to register with JMX", e);
         this.jmxServerBean = null;
      }

   }

   public void startdata() throws IOException, InterruptedException {
      if (this.zkDb == null) {
         this.zkDb = new ZKDatabase(this.txnLogFactory);
      }

      if (!this.zkDb.isInitialized()) {
         this.loadData();
      }

   }

   public synchronized void startup() {
      if (this.sessionTracker == null) {
         this.createSessionTracker();
      }

      this.startSessionTracker();
      this.setupRequestProcessors();
      this.startRequestThrottler();
      this.registerJMX();
      this.startJvmPauseMonitor();
      this.registerMetrics();
      this.setState(ZooKeeperServer.State.RUNNING);
      this.requestPathMetricsCollector.start();
      this.localSessionEnabled = this.sessionTracker.isLocalSessionsEnabled();
      this.notifyAll();
   }

   protected void startJvmPauseMonitor() {
      if (this.jvmPauseMonitor != null) {
         this.jvmPauseMonitor.serviceStart();
      }

   }

   protected void startRequestThrottler() {
      this.requestThrottler = this.createRequestThrottler();
      this.requestThrottler.start();
   }

   protected RequestThrottler createRequestThrottler() {
      return new RequestThrottler(this);
   }

   protected void setupRequestProcessors() {
      RequestProcessor finalProcessor = new FinalRequestProcessor(this);
      RequestProcessor syncProcessor = new SyncRequestProcessor(this, finalProcessor);
      ((SyncRequestProcessor)syncProcessor).start();
      this.firstProcessor = new PrepRequestProcessor(this, syncProcessor);
      ((PrepRequestProcessor)this.firstProcessor).start();
   }

   public ZooKeeperServerListener getZooKeeperServerListener() {
      return this.listener;
   }

   public void setCreateSessionTrackerServerId(int newId) {
      this.createSessionTrackerServerId = newId;
   }

   protected void createSessionTracker() {
      this.sessionTracker = new SessionTrackerImpl(this, this.zkDb.getSessionWithTimeOuts(), this.tickTime, (long)this.createSessionTrackerServerId, this.getZooKeeperServerListener());
   }

   protected void startSessionTracker() {
      ((SessionTrackerImpl)this.sessionTracker).start();
   }

   protected void setState(State state) {
      this.state = state;
      if (this.zkShutdownHandler != null) {
         this.zkShutdownHandler.handle(state);
      } else {
         LOG.debug("ZKShutdownHandler is not registered, so ZooKeeper server won't take any action on ERROR or SHUTDOWN server state changes");
      }

   }

   private boolean canShutdown() {
      return this.state == ZooKeeperServer.State.RUNNING || this.state == ZooKeeperServer.State.ERROR;
   }

   public boolean isRunning() {
      return this.state == ZooKeeperServer.State.RUNNING;
   }

   public final void shutdown() {
      this.shutdown(false);
   }

   public final synchronized void shutdown(boolean fullyShutDown) {
      if (this.canShutdown()) {
         LOG.info("Shutting down");
         this.shutdownComponents();
         if (this.zkDb != null && !fullyShutDown) {
            try {
               this.zkDb.fastForwardDataBase();
            } catch (IOException e) {
               LOG.error("Error updating DB", e);
               fullyShutDown = true;
            }
         }

         this.setState(ZooKeeperServer.State.SHUTDOWN);
      } else {
         LOG.debug("ZooKeeper server is not running, so not proceeding to shutdown!");
      }

      if (this.zkDb != null && fullyShutDown) {
         this.zkDb.clear();
      }

   }

   protected void shutdownComponents() {
      this.unregisterMetrics();
      if (this.requestThrottler != null) {
         this.requestThrottler.shutdown();
      }

      if (this.sessionTracker != null) {
         this.sessionTracker.shutdown();
      }

      if (this.firstProcessor != null) {
         this.firstProcessor.shutdown();
      }

      if (this.jvmPauseMonitor != null) {
         this.jvmPauseMonitor.serviceStop();
      }

      this.requestPathMetricsCollector.shutdown();
      this.unregisterJMX();
   }

   protected void unregisterJMX() {
      try {
         if (this.jmxDataTreeBean != null) {
            MBeanRegistry.getInstance().unregister(this.jmxDataTreeBean);
         }
      } catch (Exception e) {
         LOG.warn("Failed to unregister with JMX", e);
      }

      try {
         if (this.jmxServerBean != null) {
            MBeanRegistry.getInstance().unregister(this.jmxServerBean);
         }
      } catch (Exception e) {
         LOG.warn("Failed to unregister with JMX", e);
      }

      this.jmxServerBean = null;
      this.jmxDataTreeBean = null;
   }

   public void incInProcess() {
      this.requestsInProcess.incrementAndGet();
   }

   public void decInProcess() {
      this.requestsInProcess.decrementAndGet();
      if (this.requestThrottler != null) {
         this.requestThrottler.throttleWake();
      }

   }

   public int getInProcess() {
      return this.requestsInProcess.get();
   }

   public int getInflight() {
      return this.requestThrottleInflight();
   }

   private int requestThrottleInflight() {
      return this.requestThrottler != null ? this.requestThrottler.getInflight() : 0;
   }

   byte[] generatePasswd(long id) {
      Random r = new Random(id ^ 3007405056L);
      byte[] p = new byte[16];
      r.nextBytes(p);
      return p;
   }

   protected boolean checkPasswd(long sessionId, byte[] passwd) {
      return sessionId != 0L && Arrays.equals(passwd, this.generatePasswd(sessionId));
   }

   long createSession(ServerCnxn cnxn, byte[] passwd, int timeout) {
      if (passwd == null) {
         passwd = new byte[0];
      }

      long sessionId = this.sessionTracker.createSession(timeout);
      Random r = new Random(sessionId ^ 3007405056L);
      r.nextBytes(passwd);
      CreateSessionTxn txn = new CreateSessionTxn(timeout);
      cnxn.setSessionId(sessionId);
      Request si = new Request(cnxn, sessionId, 0, -10, RequestRecord.fromRecord(txn), (List)null);
      this.submitRequest(si);
      return sessionId;
   }

   public void setOwner(long id, Object owner) throws KeeperException.SessionExpiredException {
      this.sessionTracker.setOwner(id, owner);
   }

   protected void revalidateSession(ServerCnxn cnxn, long sessionId, int sessionTimeout) throws IOException {
      boolean rc = this.sessionTracker.touchSession(sessionId, sessionTimeout);
      if (LOG.isTraceEnabled()) {
         ZooTrace.logTraceMessage(LOG, 32L, "Session 0x" + Long.toHexString(sessionId) + " is valid: " + rc);
      }

      this.finishSessionInit(cnxn, rc);
   }

   public void reopenSession(ServerCnxn cnxn, long sessionId, byte[] passwd, int sessionTimeout) throws IOException {
      if (this.checkPasswd(sessionId, passwd)) {
         this.revalidateSession(cnxn, sessionId, sessionTimeout);
      } else {
         LOG.warn("Incorrect password from {} for session 0x{}", cnxn.getRemoteSocketAddress(), Long.toHexString(sessionId));
         this.finishSessionInit(cnxn, false);
      }

   }

   public void finishSessionInit(ServerCnxn cnxn, boolean valid) {
      try {
         if (valid) {
            if (this.serverCnxnFactory != null && this.serverCnxnFactory.cnxns.contains(cnxn)) {
               this.serverCnxnFactory.registerConnection(cnxn);
            } else if (this.secureServerCnxnFactory != null && this.secureServerCnxnFactory.cnxns.contains(cnxn)) {
               this.secureServerCnxnFactory.registerConnection(cnxn);
            }
         }
      } catch (Exception e) {
         LOG.warn("Failed to register with JMX", e);
      }

      try {
         ConnectResponse rsp = new ConnectResponse(0, valid ? cnxn.getSessionTimeout() : 0, valid ? cnxn.getSessionId() : 0L, valid ? this.generatePasswd(cnxn.getSessionId()) : new byte[16], this instanceof ReadOnlyZooKeeperServer);
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         BinaryOutputArchive bos = BinaryOutputArchive.getArchive(baos);
         bos.writeInt(-1, "len");
         cnxn.protocolManager.serializeConnectResponse(rsp, bos);
         baos.close();
         ByteBuffer bb = ByteBuffer.wrap(baos.toByteArray());
         bb.putInt(bb.remaining() - 4).rewind();
         cnxn.sendBuffer(bb);
         if (valid) {
            LOG.debug("Established session 0x{} with negotiated timeout {} for client {}", new Object[]{Long.toHexString(cnxn.getSessionId()), cnxn.getSessionTimeout(), cnxn.getRemoteSocketAddress()});
            cnxn.enableRecv();
         } else {
            LOG.info("Invalid session 0x{} for client {}, probably expired", Long.toHexString(cnxn.getSessionId()), cnxn.getRemoteSocketAddress());
            cnxn.sendBuffer(ServerCnxnFactory.closeConn);
         }
      } catch (Exception e) {
         LOG.warn("Exception while establishing session, closing", e);
         cnxn.close(ServerCnxn.DisconnectReason.IO_EXCEPTION_IN_SESSION_INIT);
      }

   }

   public void closeSession(ServerCnxn cnxn, RequestHeader requestHeader) {
      this.closeSession(cnxn.getSessionId());
   }

   public long getServerId() {
      return 0L;
   }

   protected void setLocalSessionFlag(Request si) {
   }

   public void submitRequest(Request si) {
      if (this.restoreLatch != null) {
         try {
            LOG.info("Blocking request submission while restore is in progress");
            this.restoreLatch.await();
         } catch (InterruptedException e) {
            LOG.warn("Unexpected interruption", e);
         }
      }

      this.enqueueRequest(si);
   }

   public void enqueueRequest(Request si) {
      if (this.requestThrottler == null) {
         synchronized(this) {
            try {
               while(this.state == ZooKeeperServer.State.INITIAL) {
                  this.wait(1000L);
               }
            } catch (InterruptedException e) {
               LOG.warn("Unexpected interruption", e);
            }

            if (this.requestThrottler == null) {
               throw new RuntimeException("Not started");
            }
         }
      }

      this.requestThrottler.submitRequest(si);
   }

   public void submitRequestNow(Request si) {
      if (this.firstProcessor == null) {
         synchronized(this) {
            try {
               while(this.state == ZooKeeperServer.State.INITIAL) {
                  this.wait(1000L);
               }
            } catch (InterruptedException e) {
               LOG.warn("Unexpected interruption", e);
            }

            if (this.firstProcessor == null || this.state != ZooKeeperServer.State.RUNNING) {
               throw new RuntimeException("Not started");
            }
         }
      }

      try {
         this.touch(si.cnxn);
         boolean validpacket = Request.isValid(si.type);
         if (validpacket) {
            this.setLocalSessionFlag(si);
            this.firstProcessor.processRequest(si);
            if (si.cnxn != null) {
               this.incInProcess();
            }
         } else {
            LOG.warn("Received packet at server of unknown type {}", si.type);
            this.requestFinished(si);
            (new UnimplementedRequestProcessor()).processRequest(si);
         }
      } catch (MissingSessionException e) {
         LOG.debug("Dropping request.", e);
         this.requestFinished(si);
      } catch (RequestProcessor.RequestProcessorException e) {
         LOG.error("Unable to process request", e);
         this.requestFinished(si);
      }

   }

   public static int getSnapCount() {
      int snapCount = Integer.getInteger("zookeeper.snapCount", 100000);
      if (snapCount < 2) {
         LOG.warn("SnapCount should be 2 or more. Now, snapCount is reset to 2");
         snapCount = 2;
      }

      return snapCount;
   }

   public int getGlobalOutstandingLimit() {
      return Integer.getInteger("zookeeper.globalOutstandingLimit", 1000);
   }

   public static long getSnapSizeInBytes() {
      long size = Long.getLong("zookeeper.snapSizeLimitInKb", 4194304L);
      if (size <= 0L) {
         LOG.info("zookeeper.snapSizeLimitInKb set to a non-positive value {}; disabling feature", size);
      }

      return size * 1024L;
   }

   public void setServerCnxnFactory(ServerCnxnFactory factory) {
      this.serverCnxnFactory = factory;
   }

   public ServerCnxnFactory getServerCnxnFactory() {
      return this.serverCnxnFactory;
   }

   public ServerCnxnFactory getSecureServerCnxnFactory() {
      return this.secureServerCnxnFactory;
   }

   public void setSecureServerCnxnFactory(ServerCnxnFactory factory) {
      this.secureServerCnxnFactory = factory;
   }

   public long getLastProcessedZxid() {
      return this.zkDb.getDataTreeLastProcessedZxid();
   }

   public long getOutstandingRequests() {
      return (long)this.getInProcess();
   }

   public int getNumAliveConnections() {
      int numAliveConnections = 0;
      if (this.serverCnxnFactory != null) {
         numAliveConnections += this.serverCnxnFactory.getNumAliveConnections();
      }

      if (this.secureServerCnxnFactory != null) {
         numAliveConnections += this.secureServerCnxnFactory.getNumAliveConnections();
      }

      return numAliveConnections;
   }

   public void truncateLog(long zxid) throws IOException {
      this.zkDb.truncateLog(zxid);
   }

   public int getTickTime() {
      return this.tickTime;
   }

   public void setTickTime(int tickTime) {
      LOG.info("tickTime set to {} ms", tickTime);
      this.tickTime = tickTime;
   }

   public static int getThrottledOpWaitTime() {
      return throttledOpWaitTime;
   }

   public static void setThrottledOpWaitTime(int time) {
      LOG.info("throttledOpWaitTime set to {} ms", time);
      throttledOpWaitTime = time;
   }

   public int getMinSessionTimeout() {
      return this.minSessionTimeout;
   }

   public void setMinSessionTimeout(int min) {
      this.minSessionTimeout = min == -1 ? this.tickTime * 2 : min;
      LOG.info("minSessionTimeout set to {} ms", this.minSessionTimeout);
   }

   public int getMaxSessionTimeout() {
      return this.maxSessionTimeout;
   }

   public void setMaxSessionTimeout(int max) {
      this.maxSessionTimeout = max == -1 ? this.tickTime * 20 : max;
      LOG.info("maxSessionTimeout set to {} ms", this.maxSessionTimeout);
   }

   public int getClientPortListenBacklog() {
      return this.listenBacklog;
   }

   public void setClientPortListenBacklog(int backlog) {
      this.listenBacklog = backlog;
      LOG.info("clientPortListenBacklog set to {}", backlog);
   }

   public int getClientPort() {
      return this.serverCnxnFactory != null ? this.serverCnxnFactory.getLocalPort() : -1;
   }

   public int getSecureClientPort() {
      return this.secureServerCnxnFactory != null ? this.secureServerCnxnFactory.getLocalPort() : -1;
   }

   public int getMaxClientCnxnsPerHost() {
      if (this.serverCnxnFactory != null) {
         return this.serverCnxnFactory.getMaxClientCnxnsPerHost();
      } else {
         return this.secureServerCnxnFactory != null ? this.secureServerCnxnFactory.getMaxClientCnxnsPerHost() : -1;
      }
   }

   public void setTxnLogFactory(FileTxnSnapLog txnLog) {
      this.txnLogFactory = txnLog;
   }

   public FileTxnSnapLog getTxnLogFactory() {
      return this.txnLogFactory;
   }

   public long getTxnLogElapsedSyncTime() {
      return this.txnLogFactory.getTxnLogElapsedSyncTime();
   }

   public String getState() {
      return "standalone";
   }

   public void dumpEphemerals(PrintWriter pwriter) {
      this.zkDb.dumpEphemerals(pwriter);
   }

   public Map getEphemerals() {
      return this.zkDb.getEphemerals();
   }

   public double getConnectionDropChance() {
      return this.connThrottle.getDropChance();
   }

   @SuppressFBWarnings(
      value = {"IS2_INCONSISTENT_SYNC"},
      justification = "the value won't change after startup"
   )
   public void processConnectRequest(ServerCnxn cnxn, ConnectRequest request) throws IOException, ClientCnxnLimitException {
      LOG.debug("Session establishment request from client {} client's lastZxid is 0x{}", cnxn.getRemoteSocketAddress(), Long.toHexString(request.getLastZxidSeen()));
      long sessionId = request.getSessionId();
      int tokensNeeded = 1;
      if (this.connThrottle.isConnectionWeightEnabled()) {
         if (sessionId == 0L) {
            if (this.localSessionEnabled) {
               tokensNeeded = this.connThrottle.getRequiredTokensForLocal();
            } else {
               tokensNeeded = this.connThrottle.getRequiredTokensForGlobal();
            }
         } else {
            tokensNeeded = this.connThrottle.getRequiredTokensForRenew();
         }
      }

      if (!this.connThrottle.checkLimit(tokensNeeded)) {
         throw new ClientCnxnLimitException();
      } else {
         ServerMetrics.getMetrics().CONNECTION_TOKEN_DEFICIT.add((long)this.connThrottle.getDeficit());
         ServerMetrics.getMetrics().CONNECTION_REQUEST_COUNT.add(1L);
         if (!cnxn.protocolManager.isReadonlyAvailable()) {
            LOG.warn("Connection request from old client {}; will be dropped if server is in r-o mode", cnxn.getRemoteSocketAddress());
         }

         if (!request.getReadOnly() && this instanceof ReadOnlyZooKeeperServer) {
            String msg = "Refusing session request for not-read-only client " + cnxn.getRemoteSocketAddress();
            LOG.info(msg);
            throw new ServerCnxn.CloseRequestException(msg, ServerCnxn.DisconnectReason.NOT_READ_ONLY_CLIENT);
         } else if (request.getLastZxidSeen() > this.zkDb.dataTree.lastProcessedZxid) {
            String msg = "Refusing session(0x" + Long.toHexString(sessionId) + ") request for client " + cnxn.getRemoteSocketAddress() + " as it has seen zxid 0x" + Long.toHexString(request.getLastZxidSeen()) + " our last zxid is 0x" + Long.toHexString(this.getZKDatabase().getDataTreeLastProcessedZxid()) + " client must try another server";
            LOG.info(msg);
            throw new ServerCnxn.CloseRequestException(msg, ServerCnxn.DisconnectReason.CLIENT_ZXID_AHEAD);
         } else {
            int sessionTimeout = request.getTimeOut();
            byte[] passwd = request.getPasswd();
            int minSessionTimeout = this.getMinSessionTimeout();
            if (sessionTimeout < minSessionTimeout) {
               sessionTimeout = minSessionTimeout;
            }

            int maxSessionTimeout = this.getMaxSessionTimeout();
            if (sessionTimeout > maxSessionTimeout) {
               sessionTimeout = maxSessionTimeout;
            }

            cnxn.setSessionTimeout(sessionTimeout);
            cnxn.disableRecv();
            if (sessionId == 0L) {
               long id = this.createSession(cnxn, passwd, sessionTimeout);
               LOG.debug("Client attempting to establish new session: session = 0x{}, zxid = 0x{}, timeout = {}, address = {}", new Object[]{Long.toHexString(id), Long.toHexString(request.getLastZxidSeen()), request.getTimeOut(), cnxn.getRemoteSocketAddress()});
            } else {
               this.validateSession(cnxn, sessionId);
               LOG.debug("Client attempting to renew session: session = 0x{}, zxid = 0x{}, timeout = {}, address = {}", new Object[]{Long.toHexString(sessionId), Long.toHexString(request.getLastZxidSeen()), request.getTimeOut(), cnxn.getRemoteSocketAddress()});
               if (this.serverCnxnFactory != null) {
                  this.serverCnxnFactory.closeSession(sessionId, ServerCnxn.DisconnectReason.CLIENT_RECONNECT);
               }

               if (this.secureServerCnxnFactory != null) {
                  this.secureServerCnxnFactory.closeSession(sessionId, ServerCnxn.DisconnectReason.CLIENT_RECONNECT);
               }

               cnxn.setSessionId(sessionId);
               this.reopenSession(cnxn, sessionId, passwd, sessionTimeout);
               ServerMetrics.getMetrics().CONNECTION_REVALIDATE_COUNT.add(1L);
            }

         }
      }
   }

   protected void validateSession(ServerCnxn cnxn, long sessionId) throws IOException {
   }

   public boolean shouldThrottle(long outStandingCount) {
      int globalOutstandingLimit = this.getGlobalOutstandingLimit();
      if (globalOutstandingLimit >= this.getInflight() && globalOutstandingLimit >= this.getInProcess()) {
         return false;
      } else {
         return outStandingCount > 0L;
      }
   }

   long getFlushDelay() {
      return flushDelay;
   }

   static void setFlushDelay(long delay) {
      LOG.info("{} = {} ms", "zookeeper.flushDelay", delay);
      flushDelay = delay;
   }

   long getMaxWriteQueuePollTime() {
      return maxWriteQueuePollTime;
   }

   static void setMaxWriteQueuePollTime(long maxTime) {
      LOG.info("{} = {} ms", "zookeeper.maxWriteQueuePollTime", maxTime);
      maxWriteQueuePollTime = maxTime;
   }

   int getMaxBatchSize() {
      return maxBatchSize;
   }

   static void setMaxBatchSize(int size) {
      LOG.info("{}={}", "zookeeper.maxBatchSize", size);
      maxBatchSize = size;
   }

   private void initLargeRequestThrottlingSettings() {
      this.setLargeRequestMaxBytes(Integer.getInteger("zookeeper.largeRequestMaxBytes", this.largeRequestMaxBytes));
      this.setLargeRequestThreshold(Integer.getInteger("zookeeper.largeRequestThreshold", -1));
   }

   public int getLargeRequestMaxBytes() {
      return this.largeRequestMaxBytes;
   }

   public void setLargeRequestMaxBytes(int bytes) {
      if (bytes <= 0) {
         LOG.warn("Invalid max bytes for all large requests {}. It should be a positive number.", bytes);
         LOG.warn("Will not change the setting. The max bytes stay at {}", this.largeRequestMaxBytes);
      } else {
         this.largeRequestMaxBytes = bytes;
         LOG.info("The max bytes for all large requests are set to {}", this.largeRequestMaxBytes);
      }

   }

   public int getLargeRequestThreshold() {
      return this.largeRequestThreshold;
   }

   public void setLargeRequestThreshold(int threshold) {
      if (threshold != 0 && threshold >= -1) {
         this.largeRequestThreshold = threshold;
         LOG.info("The large request threshold is set to {}", this.largeRequestThreshold);
      } else {
         LOG.warn("Invalid large request threshold {}. It should be -1 or positive. Setting to -1 ", threshold);
         this.largeRequestThreshold = -1;
      }

   }

   public int getLargeRequestBytes() {
      return this.currentLargeRequestBytes.get();
   }

   private boolean isLargeRequest(int length) {
      if (this.largeRequestThreshold == -1) {
         return false;
      } else {
         return length > this.largeRequestThreshold;
      }
   }

   public boolean checkRequestSizeWhenReceivingMessage(int length) throws IOException {
      if (!this.isLargeRequest(length)) {
         return true;
      } else if (this.currentLargeRequestBytes.get() + length <= this.largeRequestMaxBytes) {
         return true;
      } else {
         ServerMetrics.getMetrics().LARGE_REQUESTS_REJECTED.add(1L);
         throw new IOException("Rejecting large request");
      }
   }

   private boolean checkRequestSizeWhenMessageReceived(int length) throws IOException {
      if (!this.isLargeRequest(length)) {
         return true;
      } else {
         int bytes = this.currentLargeRequestBytes.addAndGet(length);
         if (bytes > this.largeRequestMaxBytes) {
            this.currentLargeRequestBytes.addAndGet(-length);
            ServerMetrics.getMetrics().LARGE_REQUESTS_REJECTED.add(1L);
            throw new IOException("Rejecting large request");
         } else {
            return true;
         }
      }
   }

   public void requestFinished(Request request) {
      int largeRequestLength = request.getLargeRequestSize();
      if (largeRequestLength != -1) {
         this.currentLargeRequestBytes.addAndGet(-largeRequestLength);
      }

   }

   public void processPacket(ServerCnxn cnxn, RequestHeader h, RequestRecord request) throws IOException {
      cnxn.incrOutstandingAndCheckThrottle(h);
      if (h.getType() == 100) {
         LOG.info("got auth packet {}", cnxn.getRemoteSocketAddress());
         AuthPacket authPacket = (AuthPacket)request.readRecord(AuthPacket::new);
         String scheme = authPacket.getScheme();
         ServerAuthenticationProvider ap = ProviderRegistry.getServerProvider(scheme);
         KeeperException.Code authReturn = KeeperException.Code.AUTHFAILED;
         if (ap != null) {
            try {
               authReturn = ap.handleAuthentication(new ServerAuthenticationProvider.ServerObjs(this, cnxn), authPacket.getAuth());
            } catch (RuntimeException e) {
               LOG.warn("Caught runtime exception from AuthenticationProvider: {}", scheme, e);
               authReturn = KeeperException.Code.AUTHFAILED;
            }
         }

         if (authReturn == KeeperException.Code.OK) {
            LOG.info("Session 0x{}: auth success for scheme {} and address {}", new Object[]{Long.toHexString(cnxn.getSessionId()), scheme, cnxn.getRemoteSocketAddress()});
            ReplyHeader rh = new ReplyHeader(h.getXid(), 0L, KeeperException.Code.OK.intValue());
            cnxn.sendResponse(rh, (Record)null, (String)null);
         } else {
            if (ap == null) {
               LOG.warn("No authentication provider for scheme: {} has {}", scheme, ProviderRegistry.listProviders());
            } else {
               LOG.warn("Authentication failed for scheme: {}", scheme);
            }

            ReplyHeader rh = new ReplyHeader(h.getXid(), 0L, KeeperException.Code.AUTHFAILED.intValue());
            cnxn.sendResponse(rh, (Record)null, (String)null);
            cnxn.sendBuffer(ServerCnxnFactory.closeConn);
            cnxn.disableRecv();
         }

      } else {
         if (h.getType() == 102) {
            this.processSasl(request, cnxn, h);
         } else {
            if (!this.authHelper.enforceAuthentication(cnxn, h.getXid())) {
               return;
            }

            Request si = new Request(cnxn, cnxn.getSessionId(), h.getXid(), h.getType(), request, cnxn.getAuthInfo());
            int length = request.limit();
            if (this.isLargeRequest(length)) {
               this.checkRequestSizeWhenMessageReceived(length);
               si.setLargeRequestSize(length);
            }

            si.setOwner(ServerCnxn.me);
            this.submitRequest(si);
         }

      }
   }

   private static boolean isSaslSuperUser(String id) {
      if (id != null && !id.isEmpty()) {
         Properties properties = System.getProperties();
         int prefixLen = "zookeeper.superUser".length();

         for(String k : properties.stringPropertyNames()) {
            if (k.startsWith("zookeeper.superUser") && (k.length() == prefixLen || k.charAt(prefixLen) == '.')) {
               String value = properties.getProperty(k);
               if (value != null && value.equals(id)) {
                  return true;
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private static boolean shouldAllowSaslFailedClientsConnect() {
      return Boolean.getBoolean("zookeeper.allowSaslFailedClients");
   }

   private void processSasl(RequestRecord request, ServerCnxn cnxn, RequestHeader requestHeader) throws IOException {
      LOG.debug("Responding to client SASL token.");
      GetSASLRequest clientTokenRecord = (GetSASLRequest)request.readRecord(GetSASLRequest::new);
      byte[] clientToken = clientTokenRecord.getToken();
      LOG.debug("Size of client SASL token: {}", clientToken.length);
      byte[] responseToken = null;

      try {
         ZooKeeperSaslServer saslServer = cnxn.zooKeeperSaslServer;

         try {
            responseToken = saslServer.evaluateResponse(clientToken);
            if (saslServer.isComplete()) {
               String authorizationID = saslServer.getAuthorizationID();
               LOG.info("Session 0x{}: adding SASL authorization for authorizationID: {}", Long.toHexString(cnxn.getSessionId()), authorizationID);
               cnxn.addAuthInfo(new Id("sasl", authorizationID));
               if (isSaslSuperUser(authorizationID)) {
                  cnxn.addAuthInfo(new Id("super", ""));
                  LOG.info("Session 0x{}: Authenticated Id '{}' as super user", Long.toHexString(cnxn.getSessionId()), authorizationID);
               }
            }
         } catch (SaslException e) {
            LOG.warn("Client {} failed to SASL authenticate: {}", cnxn.getRemoteSocketAddress(), e);
            if (!shouldAllowSaslFailedClientsConnect() || this.authHelper.isSaslAuthRequired()) {
               int error;
               if (this.authHelper.isSaslAuthRequired()) {
                  LOG.warn("Closing client connection due to server requires client SASL authentication,but client SASL authentication has failed, or client is not configured with SASL authentication.");
                  error = KeeperException.Code.SESSIONCLOSEDREQUIRESASLAUTH.intValue();
               } else {
                  LOG.warn("Closing client connection due to SASL authentication failure.");
                  error = KeeperException.Code.AUTHFAILED.intValue();
               }

               ReplyHeader replyHeader = new ReplyHeader(requestHeader.getXid(), 0L, error);
               cnxn.sendResponse(replyHeader, new SetSASLResponse((byte[])null), "response");
               cnxn.sendCloseSession();
               cnxn.disableRecv();
               return;
            }

            LOG.warn("Maintaining client connection despite SASL authentication failure.");
         }
      } catch (NullPointerException var12) {
         LOG.error("cnxn.saslServer is null: cnxn object did not initialize its saslServer properly.");
      }

      if (responseToken != null) {
         LOG.debug("Size of server SASL response: {}", responseToken.length);
      }

      ReplyHeader replyHeader = new ReplyHeader(requestHeader.getXid(), 0L, KeeperException.Code.OK.intValue());
      Record record = new SetSASLResponse(responseToken);
      cnxn.sendResponse(replyHeader, record, "response");
   }

   public DataTree.ProcessTxnResult processTxn(TxnHeader hdr, Record txn) {
      this.processTxnForSessionEvents((Request)null, hdr, txn);
      return this.processTxnInDB(hdr, txn, (TxnDigest)null);
   }

   public DataTree.ProcessTxnResult processTxn(Request request) {
      TxnHeader hdr = request.getHdr();
      this.processTxnForSessionEvents(request, hdr, request.getTxn());
      boolean writeRequest = hdr != null;
      boolean quorumRequest = request.isQuorum();
      if (!writeRequest && !quorumRequest) {
         return new DataTree.ProcessTxnResult();
      } else {
         synchronized(this.outstandingChanges) {
            DataTree.ProcessTxnResult rc = this.processTxnInDB(hdr, request.getTxn(), request.getTxnDigest());
            if (writeRequest) {
               long zxid = hdr.getZxid();

               while(!this.outstandingChanges.isEmpty() && ((ChangeRecord)this.outstandingChanges.peek()).zxid <= zxid) {
                  ChangeRecord cr = (ChangeRecord)this.outstandingChanges.remove();
                  ServerMetrics.getMetrics().OUTSTANDING_CHANGES_REMOVED.add(1L);
                  if (cr.zxid < zxid) {
                     LOG.warn("Zxid outstanding 0x{} is less than current 0x{}", Long.toHexString(cr.zxid), Long.toHexString(zxid));
                  }

                  if (this.outstandingChangesForPath.get(cr.path) == cr) {
                     this.outstandingChangesForPath.remove(cr.path);
                  }
               }
            }

            if (quorumRequest) {
               this.getZKDatabase().addCommittedProposal(request);
            }

            return rc;
         }
      }
   }

   private void processTxnForSessionEvents(Request request, TxnHeader hdr, Record txn) {
      int opCode = request == null ? hdr.getType() : request.type;
      long sessionId = request == null ? hdr.getClientId() : request.sessionId;
      if (opCode == -10) {
         if (hdr != null && txn instanceof CreateSessionTxn) {
            CreateSessionTxn cst = (CreateSessionTxn)txn;
            this.sessionTracker.commitSession(sessionId, cst.getTimeOut());
         } else if (request == null || !request.isLocalSession()) {
            LOG.warn("*****>>>>> Got {} {}", txn.getClass(), txn.toString());
         }
      } else if (opCode == -11) {
         this.sessionTracker.removeSession(sessionId);
      }

   }

   private DataTree.ProcessTxnResult processTxnInDB(TxnHeader hdr, Record txn, TxnDigest digest) {
      return hdr == null ? new DataTree.ProcessTxnResult() : this.getZKDatabase().processTxn(hdr, txn, digest);
   }

   public Map getSessionExpiryMap() {
      return this.sessionTracker.getSessionExpiryMap();
   }

   void registerServerShutdownHandler(ZooKeeperServerShutdownHandler zkShutdownHandler) {
      this.zkShutdownHandler = zkShutdownHandler;
   }

   public boolean isResponseCachingEnabled() {
      return this.isResponseCachingEnabled;
   }

   public void setResponseCachingEnabled(boolean isEnabled) {
      this.isResponseCachingEnabled = isEnabled;
   }

   public ResponseCache getReadResponseCache() {
      return this.isResponseCachingEnabled ? this.readResponseCache : null;
   }

   public ResponseCache getGetChildrenResponseCache() {
      return this.isResponseCachingEnabled ? this.getChildrenResponseCache : null;
   }

   protected void registerMetrics() {
      MetricsContext rootContext = ServerMetrics.getMetrics().getMetricsProvider().getRootContext();
      ZKDatabase zkdb = this.getZKDatabase();
      ServerStats stats = this.serverStats();
      Objects.requireNonNull(stats);
      rootContext.registerGauge("avg_latency", stats::getAvgLatency);
      Objects.requireNonNull(stats);
      rootContext.registerGauge("max_latency", stats::getMaxLatency);
      Objects.requireNonNull(stats);
      rootContext.registerGauge("min_latency", stats::getMinLatency);
      Objects.requireNonNull(stats);
      rootContext.registerGauge("packets_received", stats::getPacketsReceived);
      Objects.requireNonNull(stats);
      rootContext.registerGauge("packets_sent", stats::getPacketsSent);
      Objects.requireNonNull(stats);
      rootContext.registerGauge("num_alive_connections", stats::getNumAliveClientConnections);
      Objects.requireNonNull(stats);
      rootContext.registerGauge("outstanding_requests", stats::getOutstandingRequests);
      Objects.requireNonNull(stats);
      rootContext.registerGauge("uptime", stats::getUptime);
      Objects.requireNonNull(zkdb);
      rootContext.registerGauge("znode_count", zkdb::getNodeCount);
      DataTree var10002 = zkdb.getDataTree();
      Objects.requireNonNull(var10002);
      rootContext.registerGauge("watch_count", var10002::getWatchCount);
      var10002 = zkdb.getDataTree();
      Objects.requireNonNull(var10002);
      rootContext.registerGauge("ephemerals_count", var10002::getEphemeralsCount);
      var10002 = zkdb.getDataTree();
      Objects.requireNonNull(var10002);
      rootContext.registerGauge("approximate_data_size", var10002::cachedApproximateDataSize);
      Objects.requireNonNull(zkdb);
      rootContext.registerGauge("global_sessions", zkdb::getSessionCount);
      SessionTracker var7 = this.getSessionTracker();
      Objects.requireNonNull(var7);
      rootContext.registerGauge("local_sessions", var7::getLocalSessionCount);
      OSMXBean osMbean = new OSMXBean();
      Objects.requireNonNull(osMbean);
      rootContext.registerGauge("open_file_descriptor_count", osMbean::getOpenFileDescriptorCount);
      Objects.requireNonNull(osMbean);
      rootContext.registerGauge("max_file_descriptor_count", osMbean::getMaxFileDescriptorCount);
      rootContext.registerGauge("connection_drop_probability", this::getConnectionDropChance);
      BufferStats var8 = stats.getClientResponseStats();
      Objects.requireNonNull(var8);
      rootContext.registerGauge("last_client_response_size", var8::getLastBufferSize);
      var8 = stats.getClientResponseStats();
      Objects.requireNonNull(var8);
      rootContext.registerGauge("max_client_response_size", var8::getMaxBufferSize);
      var8 = stats.getClientResponseStats();
      Objects.requireNonNull(var8);
      rootContext.registerGauge("min_client_response_size", var8::getMinBufferSize);
      rootContext.registerGauge("outstanding_tls_handshake", this::getOutstandingHandshakeNum);
      Objects.requireNonNull(stats);
      rootContext.registerGauge("auth_failed_count", stats::getAuthFailedCount);
      Objects.requireNonNull(stats);
      rootContext.registerGauge("non_mtls_remote_conn_count", stats::getNonMTLSRemoteConnCount);
      Objects.requireNonNull(stats);
      rootContext.registerGauge("non_mtls_local_conn_count", stats::getNonMTLSLocalConnCount);
      rootContext.registerGaugeSet("quota_count_limit_per_namespace", () -> QuotaMetricsUtils.getQuotaCountLimit(this.zkDb.getDataTree()));
      rootContext.registerGaugeSet("quota_bytes_limit_per_namespace", () -> QuotaMetricsUtils.getQuotaBytesLimit(this.zkDb.getDataTree()));
      rootContext.registerGaugeSet("quota_count_usage_per_namespace", () -> QuotaMetricsUtils.getQuotaCountUsage(this.zkDb.getDataTree()));
      rootContext.registerGaugeSet("quota_bytes_usage_per_namespace", () -> QuotaMetricsUtils.getQuotaBytesUsage(this.zkDb.getDataTree()));
   }

   protected void unregisterMetrics() {
      MetricsContext rootContext = ServerMetrics.getMetrics().getMetricsProvider().getRootContext();
      rootContext.unregisterGauge("avg_latency");
      rootContext.unregisterGauge("max_latency");
      rootContext.unregisterGauge("min_latency");
      rootContext.unregisterGauge("packets_received");
      rootContext.unregisterGauge("packets_sent");
      rootContext.unregisterGauge("num_alive_connections");
      rootContext.unregisterGauge("outstanding_requests");
      rootContext.unregisterGauge("uptime");
      rootContext.unregisterGauge("znode_count");
      rootContext.unregisterGauge("watch_count");
      rootContext.unregisterGauge("ephemerals_count");
      rootContext.unregisterGauge("approximate_data_size");
      rootContext.unregisterGauge("global_sessions");
      rootContext.unregisterGauge("local_sessions");
      rootContext.unregisterGauge("open_file_descriptor_count");
      rootContext.unregisterGauge("max_file_descriptor_count");
      rootContext.unregisterGauge("connection_drop_probability");
      rootContext.unregisterGauge("last_client_response_size");
      rootContext.unregisterGauge("max_client_response_size");
      rootContext.unregisterGauge("min_client_response_size");
      rootContext.unregisterGauge("auth_failed_count");
      rootContext.unregisterGauge("non_mtls_remote_conn_count");
      rootContext.unregisterGauge("non_mtls_local_conn_count");
      rootContext.unregisterGaugeSet("quota_count_limit_per_namespace");
      rootContext.unregisterGaugeSet("quota_bytes_limit_per_namespace");
      rootContext.unregisterGaugeSet("quota_count_usage_per_namespace");
      rootContext.unregisterGaugeSet("quota_bytes_usage_per_namespace");
   }

   public void dumpMonitorValues(BiConsumer response) {
      ServerStats stats = this.serverStats();
      response.accept("version", Version.getFullVersion());
      response.accept("server_state", stats.getServerState());
   }

   public void checkACL(ServerCnxn cnxn, List acl, int perm, List ids, String path, List setAcls) throws KeeperException.NoAuthException {
      if (!skipACL) {
         LOG.debug("Permission requested: {} ", perm);
         LOG.debug("ACLs for node: {}", acl);
         LOG.debug("Client credentials: {}", ids);
         if (acl != null && acl.size() != 0) {
            for(Id authId : ids) {
               if (authId.getScheme().equals("super")) {
                  return;
               }
            }

            for(ACL a : acl) {
               Id id = a.getId();
               if ((a.getPerms() & perm) != 0) {
                  if (id.getScheme().equals("world") && id.getId().equals("anyone")) {
                     return;
                  }

                  ServerAuthenticationProvider ap = ProviderRegistry.getServerProvider(id.getScheme());
                  if (ap != null) {
                     for(Id authId : ids) {
                        if (authId.getScheme().equals(id.getScheme()) && ap.matches(new ServerAuthenticationProvider.ServerObjs(this, cnxn), new ServerAuthenticationProvider.MatchValues(path, authId.getId(), id.getId(), perm, setAcls))) {
                           return;
                        }
                     }
                  }
               }
            }

            throw new KeeperException.NoAuthException();
         }
      }
   }

   public void checkQuota(String path, byte[] lastData, byte[] data, int type) throws KeeperException.QuotaExceededException {
      if (enforceQuota) {
         long dataBytes = data == null ? 0L : (long)data.length;
         ZKDatabase zkDatabase = this.getZKDatabase();
         String lastPrefix = zkDatabase.getDataTree().getMaxPrefixWithQuota(path);
         if (!StringUtils.isEmpty(lastPrefix)) {
            String namespace = PathUtils.getTopNamespace(path);
            switch (type) {
               case 1:
                  this.checkQuota(lastPrefix, dataBytes, 1L, namespace);
                  break;
               case 5:
                  this.checkQuota(lastPrefix, dataBytes - (long)(lastData == null ? 0 : lastData.length), 0L, namespace);
                  break;
               default:
                  throw new IllegalArgumentException("Unsupported OpCode for checkQuota: " + type);
            }

         }
      }
   }

   private void checkQuota(String lastPrefix, long bytesDiff, long countDiff, String namespace) throws KeeperException.QuotaExceededException {
      LOG.debug("checkQuota: lastPrefix={}, bytesDiff={}, countDiff={}", new Object[]{lastPrefix, bytesDiff, countDiff});
      String limitNode = Quotas.limitPath(lastPrefix);
      DataNode node = this.getZKDatabase().getNode(limitNode);
      if (node == null) {
         LOG.error("Missing limit node for quota {}", limitNode);
      } else {
         StatsTrack limitStats;
         synchronized(node) {
            limitStats = new StatsTrack(node.data);
         }

         boolean checkCountQuota = countDiff != 0L && (limitStats.getCount() > -1L || limitStats.getCountHardLimit() > -1L);
         boolean checkByteQuota = bytesDiff != 0L && (limitStats.getBytes() > -1L || limitStats.getByteHardLimit() > -1L);
         if (checkCountQuota || checkByteQuota) {
            String statNode = Quotas.statPath(lastPrefix);
            node = this.getZKDatabase().getNode(statNode);
            if (node == null) {
               LOG.error("Missing node for stat {}", statNode);
            } else {
               StatsTrack currentStats;
               synchronized(node) {
                  currentStats = new StatsTrack(node.data);
               }

               if (checkCountQuota) {
                  long newCount = currentStats.getCount() + countDiff;
                  boolean isCountHardLimit = limitStats.getCountHardLimit() > -1L;
                  long countLimit = isCountHardLimit ? limitStats.getCountHardLimit() : limitStats.getCount();
                  if (newCount > countLimit) {
                     String msg = "Quota exceeded: " + lastPrefix + " [current count=" + newCount + ", " + (isCountHardLimit ? "hard" : "soft") + "CountLimit=" + countLimit + "]";
                     RATE_LOGGER.rateLimitLog(msg);
                     if (isCountHardLimit) {
                        updateQuotaExceededMetrics(namespace);
                        throw new KeeperException.QuotaExceededException(lastPrefix);
                     }
                  }
               }

               if (checkByteQuota) {
                  long newBytes = currentStats.getBytes() + bytesDiff;
                  boolean isByteHardLimit = limitStats.getByteHardLimit() > -1L;
                  long byteLimit = isByteHardLimit ? limitStats.getByteHardLimit() : limitStats.getBytes();
                  if (newBytes > byteLimit) {
                     String msg = "Quota exceeded: " + lastPrefix + " [current bytes=" + newBytes + ", " + (isByteHardLimit ? "hard" : "soft") + "ByteLimit=" + byteLimit + "]";
                     RATE_LOGGER.rateLimitLog(msg);
                     if (isByteHardLimit) {
                        updateQuotaExceededMetrics(namespace);
                        throw new KeeperException.QuotaExceededException(lastPrefix);
                     }
                  }
               }

            }
         }
      }
   }

   public static boolean isDigestEnabled() {
      return digestEnabled;
   }

   public static void setDigestEnabled(boolean digestEnabled) {
      LOG.info("{} = {}", "zookeeper.digest.enabled", digestEnabled);
      ZooKeeperServer.digestEnabled = digestEnabled;
   }

   public static boolean isSerializeLastProcessedZxidEnabled() {
      return serializeLastProcessedZxidEnabled;
   }

   public static void setSerializeLastProcessedZxidEnabled(boolean serializeLastZxidEnabled) {
      serializeLastProcessedZxidEnabled = serializeLastZxidEnabled;
      LOG.info("{} = {}", "zookeeper.serializeLastProcessedZxid.enabled", serializeLastZxidEnabled);
   }

   private String parentPath(String path) throws KeeperException.BadArgumentsException {
      int lastSlash = path.lastIndexOf(47);
      if (lastSlash != -1 && path.indexOf(0) == -1 && !this.getZKDatabase().isSpecialPath(path)) {
         return lastSlash == 0 ? "/" : path.substring(0, lastSlash);
      } else {
         throw new KeeperException.BadArgumentsException(path);
      }
   }

   private String effectiveACLPath(Request request) throws KeeperException.BadArgumentsException, KeeperException.InvalidACLException {
      boolean mustCheckACL = false;
      String path = null;
      List<ACL> acl = null;
      switch (request.type) {
         case 1:
         case 15:
            CreateRequest req = (CreateRequest)request.readRequestRecordNoException(CreateRequest::new);
            if (req != null) {
               mustCheckACL = true;
               acl = req.getAcl();
               path = this.parentPath(req.getPath());
            }
            break;
         case 2:
            DeleteRequest req = (DeleteRequest)request.readRequestRecordNoException(DeleteRequest::new);
            if (req != null) {
               path = this.parentPath(req.getPath());
            }
         case 3:
         case 4:
         case 6:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         default:
            break;
         case 5:
            SetDataRequest req = (SetDataRequest)request.readRequestRecordNoException(SetDataRequest::new);
            if (req != null) {
               path = req.getPath();
            }
            break;
         case 7:
            SetACLRequest req = (SetACLRequest)request.readRequestRecordNoException(SetACLRequest::new);
            if (req != null) {
               mustCheckACL = true;
               acl = req.getAcl();
               path = req.getPath();
            }
      }

      if (mustCheckACL) {
         PrepRequestProcessor.fixupACL(path, request.authInfo, acl);
      }

      return path;
   }

   private int effectiveACLPerms(Request request) {
      switch (request.type) {
         case 1:
         case 15:
            return 4;
         case 2:
            return 8;
         case 3:
         case 4:
         case 6:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         default:
            return 31;
         case 5:
            return 2;
         case 7:
            return 16;
      }
   }

   public boolean authWriteRequest(Request request) {
      if (!enableEagerACLCheck) {
         return true;
      } else {
         int err = KeeperException.Code.OK.intValue();

         try {
            String pathToCheck = this.effectiveACLPath(request);
            if (pathToCheck != null) {
               this.checkACL(request.cnxn, this.zkDb.getACL(pathToCheck, (Stat)null), this.effectiveACLPerms(request), request.authInfo, pathToCheck, (List)null);
            }
         } catch (KeeperException.NoAuthException e) {
            LOG.debug("Request failed ACL check", e);
            err = e.code().intValue();
         } catch (KeeperException.InvalidACLException e) {
            LOG.debug("Request has an invalid ACL check", e);
            err = e.code().intValue();
         } catch (KeeperException.NoNodeException e) {
            LOG.debug("ACL check against non-existent node: {}", e.getMessage());
         } catch (KeeperException.BadArgumentsException e) {
            LOG.debug("ACL check against illegal node path: {}", e.getMessage());
         } catch (Throwable t) {
            LOG.error("Uncaught exception in authWriteRequest with: ", t);
            throw t;
         } finally {
            if (err != KeeperException.Code.OK.intValue()) {
               this.decInProcess();
               ReplyHeader rh = new ReplyHeader(request.cxid, 0L, err);

               try {
                  request.cnxn.sendResponse(rh, (Record)null, (String)null);
               } catch (IOException e) {
                  LOG.error("IOException : {}", e);
               }
            }

         }

         return err == KeeperException.Code.OK.intValue();
      }
   }

   public int getOutstandingHandshakeNum() {
      return this.serverCnxnFactory instanceof NettyServerCnxnFactory ? ((NettyServerCnxnFactory)this.serverCnxnFactory).getOutstandingHandshakeNum() : 0;
   }

   public boolean isReconfigEnabled() {
      return this.reconfigEnabled;
   }

   public ZooKeeperServerShutdownHandler getZkShutdownHandler() {
      return this.zkShutdownHandler;
   }

   static void updateQuotaExceededMetrics(String namespace) {
      if (namespace != null) {
         ServerMetrics.getMetrics().QUOTA_EXCEEDED_ERROR_PER_NAMESPACE.add(namespace, 1L);
      }
   }

   static {
      RATE_LOGGER = new RateLogger(LOG);
      ZookeeperBanner.printBanner(LOG);
      Environment.logEnv("Server environment:", LOG);
      enableEagerACLCheck = Boolean.getBoolean("zookeeper.enableEagerACLCheck");
      LOG.info("{} = {}", "zookeeper.enableEagerACLCheck", enableEagerACLCheck);
      skipACL = System.getProperty("zookeeper.skipACL", "no").equals("yes");
      if (skipACL) {
         LOG.info("{}==\"yes\", ACL checks will be skipped", "zookeeper.skipACL");
      }

      enforceQuota = Boolean.parseBoolean(System.getProperty("zookeeper.enforceQuota", "false"));
      if (enforceQuota) {
         LOG.info("{} = {}, Quota Enforce enables", "zookeeper.enforceQuota", enforceQuota);
      }

      digestEnabled = Boolean.parseBoolean(System.getProperty("zookeeper.digest.enabled", "true"));
      LOG.info("{} = {}", "zookeeper.digest.enabled", digestEnabled);
      closeSessionTxnEnabled = Boolean.parseBoolean(System.getProperty("zookeeper.closeSessionTxn.enabled", "true"));
      LOG.info("{} = {}", "zookeeper.closeSessionTxn.enabled", closeSessionTxnEnabled);
      setSerializeLastProcessedZxidEnabled(Boolean.parseBoolean(System.getProperty("zookeeper.serializeLastProcessedZxid.enabled", "true")));
      throttledOpWaitTime = Integer.getInteger("zookeeper.throttled_op_wait_time", 0);
      ok = new Exception("No prob");
      long configuredFlushDelay = Long.getLong("zookeeper.flushDelay", 0L);
      setFlushDelay(configuredFlushDelay);
      setMaxWriteQueuePollTime(Long.getLong("zookeeper.maxWriteQueuePollTime", configuredFlushDelay / 3L));
      setMaxBatchSize(Integer.getInteger("zookeeper.maxBatchSize", 1000));
      intBufferStartingSizeBytes = Integer.getInteger("zookeeper.intBufferStartingSizeBytes", 1024);
      if (intBufferStartingSizeBytes < 32) {
         String msg = "Buffer starting size (" + intBufferStartingSizeBytes + ") must be greater than or equal to 32. Configure with \"-Dzookeeper.intBufferStartingSizeBytes=<size>\" ";
         LOG.error(msg);
         throw new IllegalArgumentException(msg);
      } else {
         LOG.info("{} = {}", "zookeeper.intBufferStartingSizeBytes", intBufferStartingSizeBytes);
      }
   }

   protected static enum State {
      INITIAL,
      RUNNING,
      SHUTDOWN,
      ERROR;
   }

   public static class MissingSessionException extends IOException {
      private static final long serialVersionUID = 7467414635467261007L;

      public MissingSessionException(String msg) {
         super(msg);
      }
   }

   static class PrecalculatedDigest {
      final long nodeDigest;
      final long treeDigest;

      PrecalculatedDigest(long nodeDigest, long treeDigest) {
         this.nodeDigest = nodeDigest;
         this.treeDigest = treeDigest;
      }
   }

   static class ChangeRecord {
      PrecalculatedDigest precalculatedDigest;
      byte[] data;
      long zxid;
      String path;
      StatPersisted stat;
      int childCount;
      List acl;

      ChangeRecord(long zxid, String path, StatPersisted stat, int childCount, List acl) {
         this.zxid = zxid;
         this.path = path;
         this.stat = stat;
         this.childCount = childCount;
         this.acl = acl;
      }

      ChangeRecord duplicate(long zxid) {
         StatPersisted stat = new StatPersisted();
         if (this.stat != null) {
            DataTree.copyStatPersisted(this.stat, stat);
         }

         ChangeRecord changeRecord = new ChangeRecord(zxid, this.path, stat, this.childCount, this.acl == null ? new ArrayList() : new ArrayList(this.acl));
         changeRecord.precalculatedDigest = this.precalculatedDigest;
         changeRecord.data = this.data;
         return changeRecord;
      }
   }
}
