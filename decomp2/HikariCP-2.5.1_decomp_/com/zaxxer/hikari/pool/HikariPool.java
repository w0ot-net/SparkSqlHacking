package com.zaxxer.hikari.pool;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariPoolMXBean;
import com.zaxxer.hikari.metrics.MetricsTrackerFactory;
import com.zaxxer.hikari.metrics.PoolStats;
import com.zaxxer.hikari.metrics.dropwizard.CodahaleHealthChecker;
import com.zaxxer.hikari.metrics.dropwizard.CodahaleMetricsTrackerFactory;
import com.zaxxer.hikari.util.ClockSource;
import com.zaxxer.hikari.util.ConcurrentBag;
import com.zaxxer.hikari.util.SuspendResumeLock;
import com.zaxxer.hikari.util.UtilityElf;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HikariPool extends PoolBase implements HikariPoolMXBean, ConcurrentBag.IBagStateListener {
   private final Logger LOGGER = LoggerFactory.getLogger(HikariPool.class);
   private static final ClockSource clockSource;
   private static final int POOL_NORMAL = 0;
   private static final int POOL_SUSPENDED = 1;
   private static final int POOL_SHUTDOWN = 2;
   private volatile int poolState;
   private final long ALIVE_BYPASS_WINDOW_MS;
   private final long HOUSEKEEPING_PERIOD_MS;
   private final PoolEntryCreator POOL_ENTRY_CREATOR;
   private final AtomicInteger totalConnections;
   private final ThreadPoolExecutor addConnectionExecutor;
   private final ThreadPoolExecutor closeConnectionExecutor;
   private final ScheduledThreadPoolExecutor houseKeepingExecutorService;
   private final ConcurrentBag connectionBag;
   private final ProxyLeakTask leakTask;
   private final SuspendResumeLock suspendResumeLock;
   private PoolBase.MetricsTrackerDelegate metricsTracker;

   public HikariPool(HikariConfig config) {
      super(config);
      this.ALIVE_BYPASS_WINDOW_MS = Long.getLong("com.zaxxer.hikari.aliveBypassWindowMs", TimeUnit.MILLISECONDS.toMillis(500L));
      this.HOUSEKEEPING_PERIOD_MS = Long.getLong("com.zaxxer.hikari.housekeeping.periodMs", TimeUnit.SECONDS.toMillis(30L));
      this.POOL_ENTRY_CREATOR = new PoolEntryCreator();
      this.connectionBag = new ConcurrentBag(this);
      this.totalConnections = new AtomicInteger();
      this.suspendResumeLock = config.isAllowPoolSuspension() ? new SuspendResumeLock() : SuspendResumeLock.FAUX_LOCK;
      this.checkFailFast();
      if (config.getMetricsTrackerFactory() != null) {
         this.setMetricsTrackerFactory(config.getMetricsTrackerFactory());
      } else {
         this.setMetricRegistry(config.getMetricRegistry());
      }

      this.setHealthCheckRegistry(config.getHealthCheckRegistry());
      this.registerMBeans(this);
      ThreadFactory threadFactory = config.getThreadFactory();
      this.addConnectionExecutor = UtilityElf.createThreadPoolExecutor(config.getMaximumPoolSize(), this.poolName + " connection adder", threadFactory, new ThreadPoolExecutor.DiscardPolicy());
      this.closeConnectionExecutor = UtilityElf.createThreadPoolExecutor(config.getMaximumPoolSize(), this.poolName + " connection closer", threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
      if (config.getScheduledExecutorService() == null) {
         ThreadFactory var3 = threadFactory != null ? threadFactory : new UtilityElf.DefaultThreadFactory(this.poolName + " housekeeper", true);
         this.houseKeepingExecutorService = new ScheduledThreadPoolExecutor(1, (ThreadFactory)var3, new ThreadPoolExecutor.DiscardPolicy());
         this.houseKeepingExecutorService.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
         this.houseKeepingExecutorService.setRemoveOnCancelPolicy(true);
      } else {
         this.houseKeepingExecutorService = config.getScheduledExecutorService();
      }

      this.leakTask = new ProxyLeakTask(config.getLeakDetectionThreshold(), this.houseKeepingExecutorService);
      this.houseKeepingExecutorService.scheduleWithFixedDelay(new HouseKeeper(), 100L, this.HOUSEKEEPING_PERIOD_MS, TimeUnit.MILLISECONDS);
   }

   public final Connection getConnection() throws SQLException {
      return this.getConnection(this.connectionTimeout);
   }

   public final Connection getConnection(long hardTimeout) throws SQLException {
      this.suspendResumeLock.acquire();
      long startTime = clockSource.currentTime();

      try {
         long timeout = hardTimeout;

         do {
            PoolEntry poolEntry = (PoolEntry)this.connectionBag.borrow(timeout, TimeUnit.MILLISECONDS);
            if (poolEntry == null) {
               throw this.createTimeoutException(startTime);
            }

            long now = clockSource.currentTime();
            if (!poolEntry.isMarkedEvicted() && (clockSource.elapsedMillis(poolEntry.lastAccessed, now) <= this.ALIVE_BYPASS_WINDOW_MS || this.isConnectionAlive(poolEntry.connection))) {
               this.metricsTracker.recordBorrowStats(poolEntry, startTime);
               Connection var10 = poolEntry.createProxyConnection(this.leakTask.schedule(poolEntry), now);
               return var10;
            }

            this.closeConnection(poolEntry, "(connection is evicted or dead)");
            timeout = hardTimeout - clockSource.elapsedMillis(startTime);
         } while(timeout > 0L);

         throw this.createTimeoutException(startTime);
      } catch (InterruptedException e) {
         throw new SQLException(this.poolName + " - Interrupted during connection acquisition", e);
      } finally {
         this.suspendResumeLock.release();
      }
   }

   public final synchronized void shutdown() throws InterruptedException {
      try {
         this.poolState = 2;
         if (this.addConnectionExecutor != null) {
            this.LOGGER.info("{} - Close initiated...", this.poolName);
            this.logPoolState("Before closing ");
            this.softEvictConnections();
            this.addConnectionExecutor.shutdown();
            this.addConnectionExecutor.awaitTermination(5L, TimeUnit.SECONDS);
            if (this.config.getScheduledExecutorService() == null) {
               this.houseKeepingExecutorService.shutdown();
               this.houseKeepingExecutorService.awaitTermination(5L, TimeUnit.SECONDS);
            }

            this.connectionBag.close();
            ExecutorService assassinExecutor = UtilityElf.createThreadPoolExecutor(this.config.getMaximumPoolSize(), this.poolName + " connection assassinator", this.config.getThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

            try {
               long start = clockSource.currentTime();

               do {
                  this.abortActiveConnections(assassinExecutor);
                  this.softEvictConnections();
               } while(this.getTotalConnections() > 0 && clockSource.elapsedMillis(start) < TimeUnit.SECONDS.toMillis(5L));
            } finally {
               assassinExecutor.shutdown();
               assassinExecutor.awaitTermination(5L, TimeUnit.SECONDS);
            }

            this.shutdownNetworkTimeoutExecutor();
            this.closeConnectionExecutor.shutdown();
            this.closeConnectionExecutor.awaitTermination(5L, TimeUnit.SECONDS);
            return;
         }
      } finally {
         this.logPoolState("After closing ");
         this.unregisterMBeans();
         this.metricsTracker.close();
         this.LOGGER.info("{} - Closed.", this.poolName);
      }

   }

   public final void evictConnection(Connection connection) {
      ProxyConnection proxyConnection = (ProxyConnection)connection;
      proxyConnection.cancelLeakTask();

      try {
         this.softEvictConnection(proxyConnection.getPoolEntry(), "(connection evicted by user)", !connection.isClosed());
      } catch (SQLException var4) {
      }

   }

   public void setMetricRegistry(Object metricRegistry) {
      if (metricRegistry != null) {
         this.setMetricsTrackerFactory(new CodahaleMetricsTrackerFactory((MetricRegistry)metricRegistry));
      } else {
         this.setMetricsTrackerFactory((MetricsTrackerFactory)null);
      }

   }

   public void setMetricsTrackerFactory(MetricsTrackerFactory metricsTrackerFactory) {
      if (metricsTrackerFactory != null) {
         this.metricsTracker = new PoolBase.MetricsTrackerDelegate(metricsTrackerFactory.create(this.config.getPoolName(), this.getPoolStats()));
      } else {
         this.metricsTracker = new PoolBase.NopMetricsTrackerDelegate();
      }

   }

   public void setHealthCheckRegistry(Object healthCheckRegistry) {
      if (healthCheckRegistry != null) {
         CodahaleHealthChecker.registerHealthChecks(this, this.config, (HealthCheckRegistry)healthCheckRegistry);
      }

   }

   public Future addBagItem() {
      return this.addConnectionExecutor.submit(this.POOL_ENTRY_CREATOR);
   }

   public final int getActiveConnections() {
      return this.connectionBag.getCount(1);
   }

   public final int getIdleConnections() {
      return this.connectionBag.getCount(0);
   }

   public final int getTotalConnections() {
      return this.connectionBag.size() - this.connectionBag.getCount(-1);
   }

   public final int getThreadsAwaitingConnection() {
      return this.connectionBag.getPendingQueue();
   }

   public void softEvictConnections() {
      for(PoolEntry poolEntry : this.connectionBag.values()) {
         this.softEvictConnection(poolEntry, "(connection evicted)", false);
      }

   }

   public final synchronized void suspendPool() {
      if (this.suspendResumeLock == SuspendResumeLock.FAUX_LOCK) {
         throw new IllegalStateException(this.poolName + " - is not suspendable");
      } else {
         if (this.poolState != 1) {
            this.suspendResumeLock.suspend();
            this.poolState = 1;
         }

      }
   }

   public final synchronized void resumePool() {
      if (this.poolState == 1) {
         this.poolState = 0;
         this.fillPool();
         this.suspendResumeLock.resume();
      }

   }

   final void logPoolState(String... prefix) {
      if (this.LOGGER.isDebugEnabled()) {
         this.LOGGER.debug("{} - {}stats (total={}, active={}, idle={}, waiting={})", new Object[]{this.poolName, prefix.length > 0 ? prefix[0] : "", this.getTotalConnections(), this.getActiveConnections(), this.getIdleConnections(), this.getThreadsAwaitingConnection()});
      }

   }

   final void recycle(PoolEntry poolEntry) {
      this.metricsTracker.recordConnectionUsage(poolEntry);
      this.connectionBag.requite(poolEntry);
   }

   final void closeConnection(PoolEntry poolEntry, final String closureReason) {
      if (this.connectionBag.remove(poolEntry)) {
         int tc = this.totalConnections.decrementAndGet();
         if (tc < 0) {
            this.LOGGER.warn("{} - Unexpected value of totalConnections={}", new Object[]{this.poolName, tc, new Exception()});
         }

         final Connection connection = poolEntry.close();
         this.closeConnectionExecutor.execute(new Runnable() {
            public void run() {
               HikariPool.this.quietlyCloseConnection(connection, closureReason);
            }
         });
      }

   }

   private PoolEntry createPoolEntry() {
      try {
         final PoolEntry poolEntry = this.newPoolEntry();
         long maxLifetime = this.config.getMaxLifetime();
         if (maxLifetime > 0L) {
            long variance = maxLifetime > 10000L ? ThreadLocalRandom.current().nextLong(maxLifetime / 40L) : 0L;
            long lifetime = maxLifetime - variance;
            poolEntry.setFutureEol(this.houseKeepingExecutorService.schedule(new Runnable() {
               public void run() {
                  HikariPool.this.softEvictConnection(poolEntry, "(connection has passed maxLifetime)", false);
               }
            }, lifetime, TimeUnit.MILLISECONDS));
         }

         this.LOGGER.debug("{} - Added connection {}", this.poolName, poolEntry.connection);
         return poolEntry;
      } catch (Exception e) {
         if (this.poolState == 0) {
            this.LOGGER.debug("{} - Cannot acquire connection from data source", this.poolName, e);
         }

         return null;
      }
   }

   private void fillPool() {
      int connectionsToAdd = Math.min(this.config.getMaximumPoolSize() - this.totalConnections.get(), this.config.getMinimumIdle() - this.getIdleConnections()) - this.addConnectionExecutor.getQueue().size();

      for(int i = 0; i < connectionsToAdd; ++i) {
         this.addBagItem();
      }

      if (connectionsToAdd > 0 && this.LOGGER.isDebugEnabled()) {
         this.addConnectionExecutor.execute(new Runnable() {
            public void run() {
               HikariPool.this.logPoolState("After adding ");
            }
         });
      }

   }

   private void abortActiveConnections(ExecutorService assassinExecutor) {
      for(PoolEntry poolEntry : this.connectionBag.values(1)) {
         Connection connection = poolEntry.close();

         try {
            connection.abort(assassinExecutor);
         } catch (Throwable var9) {
            this.quietlyCloseConnection(connection, "(connection aborted during shutdown)");
         } finally {
            if (this.connectionBag.remove(poolEntry)) {
               this.totalConnections.decrementAndGet();
            }

         }
      }

   }

   private void checkFailFast() {
      if (this.config.isInitializationFailFast()) {
         try {
            Connection connection = this.newConnection();
            Throwable var2 = null;

            try {
               if (!connection.getAutoCommit()) {
                  connection.commit();
               }
            } catch (Throwable var12) {
               var2 = var12;
               throw var12;
            } finally {
               if (connection != null) {
                  if (var2 != null) {
                     try {
                        connection.close();
                     } catch (Throwable var11) {
                        var2.addSuppressed(var11);
                     }
                  } else {
                     connection.close();
                  }
               }

            }
         } catch (Throwable e) {
            throw new PoolInitializationException(e);
         }
      }

   }

   private void softEvictConnection(PoolEntry poolEntry, String reason, boolean owner) {
      poolEntry.markEvicted();
      if (owner || this.connectionBag.reserve(poolEntry)) {
         this.closeConnection(poolEntry, reason);
      }

   }

   private PoolStats getPoolStats() {
      return new PoolStats(TimeUnit.SECONDS.toMillis(1L)) {
         protected void update() {
            this.pendingThreads = HikariPool.this.getThreadsAwaitingConnection();
            this.idleConnections = HikariPool.this.getIdleConnections();
            this.totalConnections = HikariPool.this.getTotalConnections();
            this.activeConnections = HikariPool.this.getActiveConnections();
         }
      };
   }

   private SQLException createTimeoutException(long startTime) {
      this.logPoolState("Timeout failure ");
      this.metricsTracker.recordConnectionTimeout();
      String sqlState = null;
      Throwable originalException = this.getLastConnectionFailure();
      if (originalException instanceof SQLException) {
         sqlState = ((SQLException)originalException).getSQLState();
      }

      SQLException connectionException = new SQLTransientConnectionException(this.poolName + " - Connection is not available, request timed out after " + clockSource.elapsedMillis(startTime) + "ms.", sqlState, originalException);
      if (originalException instanceof SQLException) {
         connectionException.setNextException((SQLException)originalException);
      }

      return connectionException;
   }

   static {
      clockSource = ClockSource.INSTANCE;
   }

   private class PoolEntryCreator implements Callable {
      private PoolEntryCreator() {
      }

      public Boolean call() throws Exception {
         for(long sleepBackoff = 250L; HikariPool.this.poolState == 0 && HikariPool.this.totalConnections.get() < HikariPool.this.config.getMaximumPoolSize(); sleepBackoff = Math.min(TimeUnit.SECONDS.toMillis(10L), Math.min(HikariPool.this.connectionTimeout, (long)((double)sleepBackoff * (double)1.5F)))) {
            PoolEntry poolEntry = HikariPool.this.createPoolEntry();
            if (poolEntry != null) {
               HikariPool.this.totalConnections.incrementAndGet();
               HikariPool.this.connectionBag.add(poolEntry);
               return Boolean.TRUE;
            }

            UtilityElf.quietlySleep(sleepBackoff);
         }

         return Boolean.FALSE;
      }
   }

   private class HouseKeeper implements Runnable {
      private volatile long previous;

      private HouseKeeper() {
         this.previous = HikariPool.clockSource.plusMillis(HikariPool.clockSource.currentTime(), -HikariPool.this.HOUSEKEEPING_PERIOD_MS);
      }

      public void run() {
         try {
            HikariPool.this.connectionTimeout = HikariPool.this.config.getConnectionTimeout();
            HikariPool.this.validationTimeout = HikariPool.this.config.getValidationTimeout();
            HikariPool.this.leakTask.updateLeakDetectionThreshold(HikariPool.this.config.getLeakDetectionThreshold());
            long idleTimeout = HikariPool.this.config.getIdleTimeout();
            long now = HikariPool.clockSource.currentTime();
            if (HikariPool.clockSource.plusMillis(now, 128L) < HikariPool.clockSource.plusMillis(this.previous, HikariPool.this.HOUSEKEEPING_PERIOD_MS)) {
               HikariPool.this.LOGGER.warn("{} - Retrograde clock change detected (housekeeper delta={}), soft-evicting connections from pool.", HikariPool.this.poolName, HikariPool.clockSource.elapsedDisplayString(this.previous, now));
               this.previous = now;
               HikariPool.this.softEvictConnections();
               HikariPool.this.fillPool();
               return;
            }

            if (now > HikariPool.clockSource.plusMillis(this.previous, 3L * HikariPool.this.HOUSEKEEPING_PERIOD_MS / 2L)) {
               HikariPool.this.LOGGER.warn("{} - Thread starvation or clock leap detected (housekeeper delta={}).", HikariPool.this.poolName, HikariPool.clockSource.elapsedDisplayString(this.previous, now));
            }

            this.previous = now;
            String afterPrefix = "Pool ";
            if (idleTimeout > 0L) {
               List<PoolEntry> idleList = HikariPool.this.connectionBag.values(0);
               int removable = idleList.size() - HikariPool.this.config.getMinimumIdle();
               if (removable > 0) {
                  HikariPool.this.logPoolState("Before cleanup ");
                  afterPrefix = "After cleanup  ";
                  idleList.sort(PoolEntry.LASTACCESS_COMPARABLE);

                  for(PoolEntry poolEntry : idleList) {
                     if (HikariPool.clockSource.elapsedMillis(poolEntry.lastAccessed, now) > idleTimeout && HikariPool.this.connectionBag.reserve(poolEntry)) {
                        HikariPool.this.closeConnection(poolEntry, "(connection has passed idleTimeout)");
                        --removable;
                        if (removable == 0) {
                           break;
                        }
                     }
                  }
               }
            }

            HikariPool.this.logPoolState(afterPrefix);
            HikariPool.this.fillPool();
         } catch (Exception e) {
            HikariPool.this.LOGGER.error("Unexpected exception in housekeeping task", e);
         }

      }
   }

   public static class PoolInitializationException extends RuntimeException {
      private static final long serialVersionUID = 929872118275916520L;

      public PoolInitializationException(Throwable t) {
         super("Failed to initialize pool: " + t.getMessage(), t);
      }
   }
}
