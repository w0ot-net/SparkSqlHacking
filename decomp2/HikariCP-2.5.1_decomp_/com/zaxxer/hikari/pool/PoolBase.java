package com.zaxxer.hikari.pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.metrics.MetricsTracker;
import com.zaxxer.hikari.util.ClockSource;
import com.zaxxer.hikari.util.DriverDataSource;
import com.zaxxer.hikari.util.PropertyElf;
import com.zaxxer.hikari.util.UtilityElf;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class PoolBase {
   private final Logger LOGGER = LoggerFactory.getLogger(PoolBase.class);
   protected final HikariConfig config;
   protected final String poolName;
   protected long connectionTimeout;
   protected long validationTimeout;
   private static final String[] RESET_STATES = new String[]{"readOnly", "autoCommit", "isolation", "catalog", "netTimeout"};
   private static final int UNINITIALIZED = -1;
   private static final int TRUE = 1;
   private static final int FALSE = 0;
   private int networkTimeout;
   private int isNetworkTimeoutSupported;
   private int isQueryTimeoutSupported;
   private int defaultTransactionIsolation;
   private int transactionIsolation;
   private Executor netTimeoutExecutor;
   private DataSource dataSource;
   private final String catalog;
   private final boolean isReadOnly;
   private final boolean isAutoCommit;
   private final boolean isUseJdbc4Validation;
   private final boolean isIsolateInternalQueries;
   private final AtomicReference lastConnectionFailure;
   private volatile boolean isValidChecked;

   PoolBase(HikariConfig config) {
      this.config = config;
      this.networkTimeout = -1;
      this.catalog = config.getCatalog();
      this.isReadOnly = config.isReadOnly();
      this.isAutoCommit = config.isAutoCommit();
      this.transactionIsolation = UtilityElf.getTransactionIsolation(config.getTransactionIsolation());
      this.isQueryTimeoutSupported = -1;
      this.isNetworkTimeoutSupported = -1;
      this.isUseJdbc4Validation = config.getConnectionTestQuery() == null;
      this.isIsolateInternalQueries = config.isIsolateInternalQueries();
      this.poolName = config.getPoolName();
      this.connectionTimeout = config.getConnectionTimeout();
      this.validationTimeout = config.getValidationTimeout();
      this.lastConnectionFailure = new AtomicReference();
      this.initializeDataSource();
   }

   public String toString() {
      return this.poolName;
   }

   abstract void recycle(PoolEntry var1);

   void quietlyCloseConnection(Connection connection, String closureReason) {
      if (connection != null) {
         try {
            this.LOGGER.debug("{} - Closing connection {}: {}", new Object[]{this.poolName, connection, closureReason});

            try {
               this.setNetworkTimeout(connection, TimeUnit.SECONDS.toMillis(15L));
            } finally {
               connection.close();
            }
         } catch (Throwable e) {
            this.LOGGER.debug("{} - Closing connection {} failed", new Object[]{this.poolName, connection, e});
         }
      }

   }

   boolean isConnectionAlive(Connection connection) {
      try {
         try {
            if (this.isUseJdbc4Validation) {
               boolean var25 = connection.isValid((int)TimeUnit.MILLISECONDS.toSeconds(Math.max(1000L, this.validationTimeout)));
               return var25;
            }

            this.setNetworkTimeout(connection, this.validationTimeout);
            Statement statement = connection.createStatement();
            Throwable var3 = null;

            try {
               if (this.isNetworkTimeoutSupported != 1) {
                  this.setQueryTimeout(statement, (int)TimeUnit.MILLISECONDS.toSeconds(Math.max(1000L, this.validationTimeout)));
               }

               statement.execute(this.config.getConnectionTestQuery());
            } catch (Throwable var21) {
               var3 = var21;
               throw var21;
            } finally {
               if (statement != null) {
                  if (var3 != null) {
                     try {
                        statement.close();
                     } catch (Throwable var20) {
                        var3.addSuppressed(var20);
                     }
                  } else {
                     statement.close();
                  }
               }

            }
         } finally {
            if (this.isIsolateInternalQueries && !this.isAutoCommit) {
               connection.rollback();
            }

         }

         this.setNetworkTimeout(connection, (long)this.networkTimeout);
         return true;
      } catch (SQLException e) {
         this.lastConnectionFailure.set(e);
         this.LOGGER.warn("{} - Failed to validate connection {} ({})", new Object[]{this.poolName, connection, e.getMessage()});
         return false;
      }
   }

   Throwable getLastConnectionFailure() {
      return (Throwable)this.lastConnectionFailure.get();
   }

   public DataSource getUnwrappedDataSource() {
      return this.dataSource;
   }

   PoolEntry newPoolEntry() throws Exception {
      return new PoolEntry(this.newConnection(), this, this.isReadOnly, this.isAutoCommit);
   }

   void resetConnectionState(Connection connection, ProxyConnection proxyConnection, int dirtyBits) throws SQLException {
      int resetBits = 0;
      if ((dirtyBits & 1) != 0 && proxyConnection.getReadOnlyState() != this.isReadOnly) {
         connection.setReadOnly(this.isReadOnly);
         resetBits |= 1;
      }

      if ((dirtyBits & 2) != 0 && proxyConnection.getAutoCommitState() != this.isAutoCommit) {
         connection.setAutoCommit(this.isAutoCommit);
         resetBits |= 2;
      }

      if ((dirtyBits & 4) != 0 && proxyConnection.getTransactionIsolationState() != this.transactionIsolation) {
         connection.setTransactionIsolation(this.transactionIsolation);
         resetBits |= 4;
      }

      if ((dirtyBits & 8) != 0 && this.catalog != null && !this.catalog.equals(proxyConnection.getCatalogState())) {
         connection.setCatalog(this.catalog);
         resetBits |= 8;
      }

      if ((dirtyBits & 16) != 0 && proxyConnection.getNetworkTimeoutState() != this.networkTimeout) {
         this.setNetworkTimeout(connection, (long)this.networkTimeout);
         resetBits |= 16;
      }

      if (resetBits != 0 && this.LOGGER.isDebugEnabled()) {
         this.LOGGER.debug("{} - Reset ({}) on connection {}", new Object[]{this.poolName, this.stringFromResetBits(resetBits), connection});
      }

   }

   void shutdownNetworkTimeoutExecutor() {
      if (this.netTimeoutExecutor instanceof ThreadPoolExecutor) {
         ((ThreadPoolExecutor)this.netTimeoutExecutor).shutdownNow();
      }

   }

   void registerMBeans(HikariPool hikariPool) {
      if (this.config.isRegisterMbeans()) {
         try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName beanConfigName = new ObjectName("com.zaxxer.hikari:type=PoolConfig (" + this.poolName + ")");
            ObjectName beanPoolName = new ObjectName("com.zaxxer.hikari:type=Pool (" + this.poolName + ")");
            if (!mBeanServer.isRegistered(beanConfigName)) {
               mBeanServer.registerMBean(this.config, beanConfigName);
               mBeanServer.registerMBean(hikariPool, beanPoolName);
            } else {
               this.LOGGER.error("{} - JMX name ({}) is already registered.", this.poolName, this.poolName);
            }
         } catch (Exception e) {
            this.LOGGER.warn("{} - Failed to register management beans.", this.poolName, e);
         }

      }
   }

   void unregisterMBeans() {
      if (this.config.isRegisterMbeans()) {
         try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName beanConfigName = new ObjectName("com.zaxxer.hikari:type=PoolConfig (" + this.poolName + ")");
            ObjectName beanPoolName = new ObjectName("com.zaxxer.hikari:type=Pool (" + this.poolName + ")");
            if (mBeanServer.isRegistered(beanConfigName)) {
               mBeanServer.unregisterMBean(beanConfigName);
               mBeanServer.unregisterMBean(beanPoolName);
            }
         } catch (Exception e) {
            this.LOGGER.warn("{} - Failed to unregister management beans.", this.poolName, e);
         }

      }
   }

   private void initializeDataSource() {
      String jdbcUrl = this.config.getJdbcUrl();
      String username = this.config.getUsername();
      String password = this.config.getPassword();
      String dsClassName = this.config.getDataSourceClassName();
      String driverClassName = this.config.getDriverClassName();
      Properties dataSourceProperties = this.config.getDataSourceProperties();
      DataSource dataSource = this.config.getDataSource();
      if (dsClassName != null && dataSource == null) {
         dataSource = (DataSource)UtilityElf.createInstance(dsClassName, DataSource.class);
         PropertyElf.setTargetFromProperties(dataSource, dataSourceProperties);
      } else if (jdbcUrl != null && dataSource == null) {
         dataSource = new DriverDataSource(jdbcUrl, driverClassName, dataSourceProperties, username, password);
      }

      if (dataSource != null) {
         this.setLoginTimeout(dataSource);
         this.createNetworkTimeoutExecutor(dataSource, dsClassName, jdbcUrl);
      }

      this.dataSource = dataSource;
   }

   Connection newConnection() throws Exception {
      Connection connection = null;

      try {
         String username = this.config.getUsername();
         String password = this.config.getPassword();
         connection = username == null ? this.dataSource.getConnection() : this.dataSource.getConnection(username, password);
         if (connection == null) {
            throw new SQLTransientConnectionException("DataSource returned null unexpectedly");
         } else {
            this.setupConnection(connection);
            this.lastConnectionFailure.set((Object)null);
            return connection;
         }
      } catch (Exception e) {
         this.lastConnectionFailure.set(e);
         this.quietlyCloseConnection(connection, "(Failed to create/set connection)");
         throw e;
      }
   }

   private void setupConnection(Connection connection) throws SQLException {
      if (this.networkTimeout == -1) {
         this.networkTimeout = this.getAndSetNetworkTimeout(connection, this.validationTimeout);
      } else {
         this.setNetworkTimeout(connection, this.validationTimeout);
      }

      connection.setReadOnly(this.isReadOnly);
      connection.setAutoCommit(this.isAutoCommit);
      this.checkDriverSupport(connection);
      if (this.transactionIsolation != this.defaultTransactionIsolation) {
         connection.setTransactionIsolation(this.transactionIsolation);
      }

      if (this.catalog != null) {
         connection.setCatalog(this.catalog);
      }

      this.executeSql(connection, this.config.getConnectionInitSql(), true);
      this.setNetworkTimeout(connection, (long)this.networkTimeout);
   }

   private void checkDriverSupport(Connection connection) throws SQLException {
      if (!this.isValidChecked) {
         try {
            if (this.isUseJdbc4Validation) {
               connection.isValid(1);
            } else {
               this.executeSql(connection, this.config.getConnectionTestQuery(), false);
            }
         } catch (Throwable e) {
            this.LOGGER.error("{} - Failed to execute" + (this.isUseJdbc4Validation ? " isValid() for connection, configure" : "") + " connection test query. ({})", this.poolName, e.getMessage());
            throw e;
         }

         try {
            this.defaultTransactionIsolation = connection.getTransactionIsolation();
            if (this.transactionIsolation == -1) {
               this.transactionIsolation = this.defaultTransactionIsolation;
            }
         } catch (SQLException e) {
            this.LOGGER.warn("{} - Default transaction isolation level detection failed. ({})", this.poolName, e.getMessage());
         } finally {
            this.isValidChecked = true;
         }
      }

   }

   private void setQueryTimeout(Statement statement, int timeoutSec) {
      if (this.isQueryTimeoutSupported != 0) {
         try {
            statement.setQueryTimeout(timeoutSec);
            this.isQueryTimeoutSupported = 1;
         } catch (Throwable e) {
            if (this.isQueryTimeoutSupported == -1) {
               this.isQueryTimeoutSupported = 0;
               this.LOGGER.info("{} - Failed to set query timeout for statement. ({})", this.poolName, e.getMessage());
            }
         }
      }

   }

   private int getAndSetNetworkTimeout(Connection connection, long timeoutMs) {
      if (this.isNetworkTimeoutSupported != 0) {
         try {
            int originalTimeout = connection.getNetworkTimeout();
            connection.setNetworkTimeout(this.netTimeoutExecutor, (int)timeoutMs);
            this.isNetworkTimeoutSupported = 1;
            return originalTimeout;
         } catch (Throwable e) {
            if (this.isNetworkTimeoutSupported == -1) {
               this.isNetworkTimeoutSupported = 0;
               this.LOGGER.info("{} - Driver does not support get/set network timeout for connections. ({})", this.poolName, e.getMessage());
               if (this.validationTimeout < TimeUnit.SECONDS.toMillis(1L)) {
                  this.LOGGER.warn("{} - A validationTimeout of less than 1 second cannot be honored on drivers without setNetworkTimeout() support.", this.poolName);
               } else if (this.validationTimeout % TimeUnit.SECONDS.toMillis(1L) != 0L) {
                  this.LOGGER.warn("{} - A validationTimeout with fractional second granularity cannot be honored on drivers without setNetworkTimeout() support.", this.poolName);
               }
            }
         }
      }

      return 0;
   }

   private void setNetworkTimeout(Connection connection, long timeoutMs) throws SQLException {
      if (this.isNetworkTimeoutSupported == 1) {
         connection.setNetworkTimeout(this.netTimeoutExecutor, (int)timeoutMs);
      }

   }

   private void executeSql(Connection connection, String sql, boolean isCommit) throws SQLException {
      if (sql != null) {
         Statement statement = connection.createStatement();
         Throwable var5 = null;

         try {
            statement.execute(sql);
         } catch (Throwable var14) {
            var5 = var14;
            throw var14;
         } finally {
            if (statement != null) {
               if (var5 != null) {
                  try {
                     statement.close();
                  } catch (Throwable var13) {
                     var5.addSuppressed(var13);
                  }
               } else {
                  statement.close();
               }
            }

         }

         if (this.isIsolateInternalQueries && !this.isAutoCommit) {
            if (isCommit) {
               connection.commit();
            } else {
               connection.rollback();
            }
         }
      }

   }

   private void createNetworkTimeoutExecutor(DataSource dataSource, String dsClassName, String jdbcUrl) {
      if ((dsClassName == null || !dsClassName.contains("Mysql")) && (jdbcUrl == null || !jdbcUrl.contains("mysql")) && (dataSource == null || !dataSource.getClass().getName().contains("Mysql"))) {
         ThreadFactory threadFactory = this.config.getThreadFactory();
         ThreadFactory var6 = threadFactory != null ? threadFactory : new UtilityElf.DefaultThreadFactory(this.poolName + " network timeout executor", true);
         ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newCachedThreadPool((ThreadFactory)var6);
         executor.setKeepAliveTime(15L, TimeUnit.SECONDS);
         executor.allowCoreThreadTimeOut(true);
         this.netTimeoutExecutor = executor;
      } else {
         this.netTimeoutExecutor = new SynchronousExecutor();
      }

   }

   private void setLoginTimeout(DataSource dataSource) {
      if (this.connectionTimeout != 2147483647L) {
         try {
            dataSource.setLoginTimeout(Math.max(1, (int)TimeUnit.MILLISECONDS.toSeconds(500L + this.connectionTimeout)));
         } catch (Throwable e) {
            this.LOGGER.info("{} - Failed to set login timeout for data source. ({})", this.poolName, e.getMessage());
         }
      }

   }

   private String stringFromResetBits(int bits) {
      StringBuilder sb = new StringBuilder();

      for(int ndx = 0; ndx < RESET_STATES.length; ++ndx) {
         if ((bits & 1 << ndx) != 0) {
            sb.append(RESET_STATES[ndx]).append(", ");
         }
      }

      sb.setLength(sb.length() - 2);
      return sb.toString();
   }

   private static class SynchronousExecutor implements Executor {
      private SynchronousExecutor() {
      }

      public void execute(Runnable command) {
         try {
            command.run();
         } catch (Throwable t) {
            LoggerFactory.getLogger(PoolBase.class).debug("Failed to execute: {}", command, t);
         }

      }
   }

   static class MetricsTrackerDelegate implements AutoCloseable {
      final MetricsTracker tracker;

      protected MetricsTrackerDelegate() {
         this.tracker = null;
      }

      MetricsTrackerDelegate(MetricsTracker tracker) {
         this.tracker = tracker;
      }

      public void close() {
         this.tracker.close();
      }

      void recordConnectionUsage(PoolEntry poolEntry) {
         this.tracker.recordConnectionUsageMillis(poolEntry.getMillisSinceBorrowed());
      }

      void recordBorrowStats(PoolEntry poolEntry, long startTime) {
         long now = ClockSource.INSTANCE.currentTime();
         poolEntry.lastBorrowed = now;
         this.tracker.recordConnectionAcquiredNanos(ClockSource.INSTANCE.elapsedNanos(startTime, now));
      }

      void recordConnectionTimeout() {
         this.tracker.recordConnectionTimeout();
      }
   }

   static final class NopMetricsTrackerDelegate extends MetricsTrackerDelegate {
      void recordConnectionUsage(PoolEntry poolEntry) {
      }

      public void close() {
      }

      void recordBorrowStats(PoolEntry poolEntry, long startTime) {
      }

      void recordConnectionTimeout() {
      }
   }
}
