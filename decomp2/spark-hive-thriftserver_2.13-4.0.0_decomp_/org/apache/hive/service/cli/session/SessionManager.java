package org.apache.hive.service.cli.session;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hive.service.CompositeService;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.cli.SessionHandle;
import org.apache.hive.service.cli.operation.OperationManager;
import org.apache.hive.service.rpc.thrift.TProtocolVersion;
import org.apache.hive.service.server.HiveServer2;
import org.apache.hive.service.server.ThreadFactoryWithGarbageCleanup;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.THREAD_POOL_SIZE.;

public class SessionManager extends CompositeService {
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(SessionManager.class);
   public static final String HIVERCFILE = ".hiverc";
   private HiveConf hiveConf;
   private final Map handleToSession = new ConcurrentHashMap();
   private final OperationManager operationManager = new OperationManager();
   private ThreadPoolExecutor backgroundOperationPool;
   private boolean isOperationLogEnabled;
   private File operationLogRootDir;
   private long checkInterval;
   private long sessionTimeout;
   private boolean checkOperation;
   private volatile boolean shutdown;
   private final HiveServer2 hiveServer2;
   private final Object timeoutCheckerLock = new Object();
   private static ThreadLocal threadLocalIpAddress = new ThreadLocal() {
      protected synchronized String initialValue() {
         return null;
      }
   };
   private static ThreadLocal threadLocalUserName = new ThreadLocal() {
      protected synchronized String initialValue() {
         return null;
      }
   };
   private static ThreadLocal threadLocalProxyUserName = new ThreadLocal() {
      protected synchronized String initialValue() {
         return null;
      }
   };

   public SessionManager(HiveServer2 hiveServer2) {
      super(SessionManager.class.getSimpleName());
      this.hiveServer2 = hiveServer2;
   }

   public synchronized void init(HiveConf hiveConf) {
      this.hiveConf = hiveConf;
      if (hiveConf.getBoolVar(ConfVars.HIVE_SERVER2_LOGGING_OPERATION_ENABLED)) {
         this.initOperationLogRootDir();
      }

      this.createBackgroundOperationPool();
      this.addService(this.operationManager);
      super.init(hiveConf);
   }

   private void createBackgroundOperationPool() {
      int poolSize = this.hiveConf.getIntVar(ConfVars.HIVE_SERVER2_ASYNC_EXEC_THREADS);
      LOG.info("HiveServer2: Background operation thread pool size: {}", new MDC[]{MDC.of(.MODULE$, poolSize)});
      int poolQueueSize = this.hiveConf.getIntVar(ConfVars.HIVE_SERVER2_ASYNC_EXEC_WAIT_QUEUE_SIZE);
      LOG.info("HiveServer2: Background operation thread wait queue size: {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.THREAD_POOL_WAIT_QUEUE_SIZE..MODULE$, poolQueueSize)});
      long keepAliveTime = HiveConf.getTimeVar(this.hiveConf, ConfVars.HIVE_SERVER2_ASYNC_EXEC_KEEPALIVE_TIME, TimeUnit.SECONDS);
      LOG.info("HiveServer2: Background operation thread keepalive time: {} ms", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.THREAD_POOL_KEEPALIVE_TIME..MODULE$, keepAliveTime * 1000L)});
      String threadPoolName = "HiveServer2-Background-Pool";
      this.backgroundOperationPool = new ThreadPoolExecutor(poolSize, poolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue(poolQueueSize), new ThreadFactoryWithGarbageCleanup(threadPoolName));
      this.backgroundOperationPool.allowCoreThreadTimeOut(true);
      this.checkInterval = HiveConf.getTimeVar(this.hiveConf, ConfVars.HIVE_SERVER2_SESSION_CHECK_INTERVAL, TimeUnit.MILLISECONDS);
      this.sessionTimeout = HiveConf.getTimeVar(this.hiveConf, ConfVars.HIVE_SERVER2_IDLE_SESSION_TIMEOUT, TimeUnit.MILLISECONDS);
      this.checkOperation = HiveConf.getBoolVar(this.hiveConf, ConfVars.HIVE_SERVER2_IDLE_SESSION_CHECK_OPERATION);
   }

   private void initOperationLogRootDir() {
      this.operationLogRootDir = new File(this.hiveConf.getVar(ConfVars.HIVE_SERVER2_LOGGING_OPERATION_LOG_LOCATION));
      this.isOperationLogEnabled = true;
      if (this.operationLogRootDir.exists() && !this.operationLogRootDir.isDirectory()) {
         LOG.warn("The operation log root directory exists, but it is not a directory: {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.operationLogRootDir.getAbsolutePath())});
         this.isOperationLogEnabled = false;
      }

      if (!this.operationLogRootDir.exists() && !this.operationLogRootDir.mkdirs()) {
         LOG.warn("Unable to create operation log root directory: {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.operationLogRootDir.getAbsolutePath())});
         this.isOperationLogEnabled = false;
      }

      if (this.isOperationLogEnabled) {
         LOG.info("Operation log root directory is created: {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.operationLogRootDir.getAbsolutePath())});

         try {
            FileUtils.forceDeleteOnExit(this.operationLogRootDir);
         } catch (IOException e) {
            LOG.warn("Failed to schedule cleanup HS2 operation logging root dir: {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.operationLogRootDir.getAbsolutePath())});
         }
      }

   }

   public synchronized void start() {
      super.start();
      if (this.checkInterval > 0L) {
         this.startTimeoutChecker();
      }

   }

   private void startTimeoutChecker() {
      final long interval = Math.max(this.checkInterval, 3000L);
      Runnable timeoutChecker = new Runnable() {
         public void run() {
            this.sleepFor(interval);

            for(; !SessionManager.this.shutdown; this.sleepFor(interval)) {
               long current = System.currentTimeMillis();

               for(HiveSession session : new ArrayList(SessionManager.this.handleToSession.values())) {
                  if (SessionManager.this.shutdown) {
                     break;
                  }

                  if (SessionManager.this.sessionTimeout > 0L && session.getLastAccessTime() + SessionManager.this.sessionTimeout <= current && (!SessionManager.this.checkOperation || session.getNoOperationTime() > SessionManager.this.sessionTimeout)) {
                     SessionHandle handle = session.getSessionHandle();
                     SessionManager.LOG.warn("Session {} is Timed-out (last access : {}) and will be closed", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.SESSION_HANDLE..MODULE$, handle), MDC.of(org.apache.spark.internal.LogKeys.LAST_ACCESS_TIME..MODULE$, new Date(session.getLastAccessTime()))});

                     try {
                        SessionManager.this.closeSession(handle);
                     } catch (HiveSQLException e) {
                        SessionManager.LOG.warn("Exception is thrown closing session {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.SESSION_HANDLE..MODULE$, handle)});
                     }
                  } else {
                     session.closeExpiredOperations();
                  }
               }
            }

         }

         private void sleepFor(long intervalx) {
            synchronized(SessionManager.this.timeoutCheckerLock) {
               try {
                  SessionManager.this.timeoutCheckerLock.wait(interval);
               } catch (InterruptedException var6) {
               }

            }
         }
      };
      this.backgroundOperationPool.execute(timeoutChecker);
   }

   private void shutdownTimeoutChecker() {
      this.shutdown = true;
      synchronized(this.timeoutCheckerLock) {
         this.timeoutCheckerLock.notify();
      }
   }

   public synchronized void stop() {
      super.stop();
      this.shutdownTimeoutChecker();
      if (this.backgroundOperationPool != null) {
         this.backgroundOperationPool.shutdown();
         long timeout = this.hiveConf.getTimeVar(ConfVars.HIVE_SERVER2_ASYNC_EXEC_SHUTDOWN_TIMEOUT, TimeUnit.SECONDS);

         try {
            this.backgroundOperationPool.awaitTermination(timeout, TimeUnit.SECONDS);
         } catch (InterruptedException e) {
            LOG.warn("HIVE_SERVER2_ASYNC_EXEC_SHUTDOWN_TIMEOUT = {} ms has been exceeded. RUNNING background operations will be shut down", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.TIMEOUT..MODULE$, timeout * 1000L)});
         }

         this.backgroundOperationPool = null;
      }

      this.cleanupLoggingRootDir();
   }

   private void cleanupLoggingRootDir() {
      if (this.isOperationLogEnabled) {
         try {
            FileUtils.forceDelete(this.operationLogRootDir);
         } catch (Exception e) {
            LOG.warn("Failed to cleanup root dir of HS2 logging: {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.operationLogRootDir.getAbsolutePath())});
         }
      }

   }

   public SessionHandle openSession(TProtocolVersion protocol, String username, String password, String ipAddress, Map sessionConf) throws HiveSQLException {
      return this.openSession(protocol, username, password, ipAddress, sessionConf, false, (String)null);
   }

   public SessionHandle openSession(TProtocolVersion protocol, String username, String password, String ipAddress, Map sessionConf, boolean withImpersonation, String delegationToken) throws HiveSQLException {
      HiveSession session;
      if (withImpersonation) {
         HiveSessionImplwithUGI sessionWithUGI = new HiveSessionImplwithUGI(protocol, username, password, this.hiveConf, ipAddress, delegationToken);
         session = HiveSessionProxy.getProxy(sessionWithUGI, sessionWithUGI.getSessionUgi());
         sessionWithUGI.setProxySession(session);
      } else {
         session = new HiveSessionImpl(protocol, username, password, this.hiveConf, ipAddress);
      }

      session.setSessionManager(this);
      session.setOperationManager(this.operationManager);

      try {
         session.open(sessionConf);
      } catch (Exception e) {
         try {
            session.close();
         } catch (Throwable t) {
            LOG.warn("Error closing session", t);
         }

         HiveSession var13 = null;
         throw new HiveSQLException("Failed to open new session: " + String.valueOf(e), e);
      }

      if (this.isOperationLogEnabled) {
         session.setOperationLogSessionDir(this.operationLogRootDir);
      }

      this.handleToSession.put(session.getSessionHandle(), session);
      return session.getSessionHandle();
   }

   public void closeSession(SessionHandle sessionHandle) throws HiveSQLException {
      HiveSession session = (HiveSession)this.handleToSession.remove(sessionHandle);
      if (session == null) {
         throw new HiveSQLException("Session does not exist!");
      } else {
         session.close();
      }
   }

   public HiveSession getSession(SessionHandle sessionHandle) throws HiveSQLException {
      HiveSession session = (HiveSession)this.handleToSession.get(sessionHandle);
      if (session == null) {
         throw new HiveSQLException("Invalid SessionHandle: " + String.valueOf(sessionHandle));
      } else {
         return session;
      }
   }

   public OperationManager getOperationManager() {
      return this.operationManager;
   }

   public static void setIpAddress(String ipAddress) {
      threadLocalIpAddress.set(ipAddress);
   }

   public static void clearIpAddress() {
      threadLocalIpAddress.remove();
   }

   public static String getIpAddress() {
      return (String)threadLocalIpAddress.get();
   }

   public static void setUserName(String userName) {
      threadLocalUserName.set(userName);
   }

   public static void clearUserName() {
      threadLocalUserName.remove();
   }

   public static String getUserName() {
      return (String)threadLocalUserName.get();
   }

   public static void setProxyUserName(String userName) {
      LOG.debug("setting proxy user name based on query param to: " + userName);
      threadLocalProxyUserName.set(userName);
   }

   public static String getProxyUserName() {
      return (String)threadLocalProxyUserName.get();
   }

   public static void clearProxyUserName() {
      threadLocalProxyUserName.remove();
   }

   public Future submitBackgroundOperation(Runnable r) {
      return this.backgroundOperationPool.submit(r);
   }

   public int getOpenSessionCount() {
      return this.handleToSession.size();
   }
}
