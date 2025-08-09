package com.zaxxer.hikari;

import com.zaxxer.hikari.metrics.MetricsTrackerFactory;
import com.zaxxer.hikari.pool.HikariPool;
import java.io.Closeable;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HikariDataSource extends HikariConfig implements DataSource, Closeable {
   private static final Logger LOGGER = LoggerFactory.getLogger(HikariDataSource.class);
   private final AtomicBoolean isShutdown = new AtomicBoolean();
   private final HikariPool fastPathPool;
   private volatile HikariPool pool;

   public HikariDataSource() {
      this.fastPathPool = null;
   }

   public HikariDataSource(HikariConfig configuration) {
      configuration.validate();
      configuration.copyState(this);
      LOGGER.info("{} - Started.", configuration.getPoolName());
      this.pool = this.fastPathPool = new HikariPool(this);
   }

   public Connection getConnection() throws SQLException {
      if (this.isClosed()) {
         throw new SQLException("HikariDataSource " + this + " has been closed.");
      } else if (this.fastPathPool != null) {
         return this.fastPathPool.getConnection();
      } else {
         HikariPool result = this.pool;
         if (result == null) {
            synchronized(this) {
               result = this.pool;
               if (result == null) {
                  this.validate();
                  LOGGER.info("{} - Started.", this.getPoolName());
                  this.pool = result = new HikariPool(this);
               }
            }
         }

         return result.getConnection();
      }
   }

   public Connection getConnection(String username, String password) throws SQLException {
      throw new SQLFeatureNotSupportedException();
   }

   public PrintWriter getLogWriter() throws SQLException {
      HikariPool p = this.pool;
      return p != null ? p.getUnwrappedDataSource().getLogWriter() : null;
   }

   public void setLogWriter(PrintWriter out) throws SQLException {
      HikariPool p = this.pool;
      if (p != null) {
         p.getUnwrappedDataSource().setLogWriter(out);
      }

   }

   public void setLoginTimeout(int seconds) throws SQLException {
      HikariPool p = this.pool;
      if (p != null) {
         p.getUnwrappedDataSource().setLoginTimeout(seconds);
      }

   }

   public int getLoginTimeout() throws SQLException {
      HikariPool p = this.pool;
      return p != null ? p.getUnwrappedDataSource().getLoginTimeout() : 0;
   }

   public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
      throw new SQLFeatureNotSupportedException();
   }

   public Object unwrap(Class iface) throws SQLException {
      if (iface.isInstance(this)) {
         return this;
      } else {
         HikariPool p = this.pool;
         if (p != null) {
            DataSource unwrappedDataSource = p.getUnwrappedDataSource();
            if (iface.isInstance(unwrappedDataSource)) {
               return unwrappedDataSource;
            }

            if (unwrappedDataSource != null) {
               return unwrappedDataSource.unwrap(iface);
            }
         }

         throw new SQLException("Wrapped DataSource is not an instance of " + iface);
      }
   }

   public boolean isWrapperFor(Class iface) throws SQLException {
      if (iface.isInstance(this)) {
         return true;
      } else {
         HikariPool p = this.pool;
         if (p != null) {
            DataSource unwrappedDataSource = p.getUnwrappedDataSource();
            if (iface.isInstance(unwrappedDataSource)) {
               return true;
            }

            if (unwrappedDataSource != null) {
               return unwrappedDataSource.isWrapperFor(iface);
            }
         }

         return false;
      }
   }

   public void setMetricRegistry(Object metricRegistry) {
      boolean isAlreadySet = this.getMetricRegistry() != null;
      super.setMetricRegistry(metricRegistry);
      HikariPool p = this.pool;
      if (p != null) {
         if (isAlreadySet) {
            throw new IllegalStateException("MetricRegistry can only be set one time");
         }

         p.setMetricRegistry(super.getMetricRegistry());
      }

   }

   public void setMetricsTrackerFactory(MetricsTrackerFactory metricsTrackerFactory) {
      boolean isAlreadySet = this.getMetricsTrackerFactory() != null;
      super.setMetricsTrackerFactory(metricsTrackerFactory);
      HikariPool p = this.pool;
      if (p != null) {
         if (isAlreadySet) {
            throw new IllegalStateException("MetricsTrackerFactory can only be set one time");
         }

         p.setMetricsTrackerFactory(super.getMetricsTrackerFactory());
      }

   }

   public void setHealthCheckRegistry(Object healthCheckRegistry) {
      boolean isAlreadySet = this.getHealthCheckRegistry() != null;
      super.setHealthCheckRegistry(healthCheckRegistry);
      HikariPool p = this.pool;
      if (p != null) {
         if (isAlreadySet) {
            throw new IllegalStateException("HealthCheckRegistry can only be set one time");
         }

         p.setHealthCheckRegistry(super.getHealthCheckRegistry());
      }

   }

   public void evictConnection(Connection connection) {
      HikariPool p;
      if (!this.isClosed() && (p = this.pool) != null && connection.getClass().getName().startsWith("com.zaxxer.hikari")) {
         p.evictConnection(connection);
      }

   }

   public void suspendPool() {
      HikariPool p;
      if (!this.isClosed() && (p = this.pool) != null) {
         p.suspendPool();
      }

   }

   public void resumePool() {
      HikariPool p;
      if (!this.isClosed() && (p = this.pool) != null) {
         p.resumePool();
      }

   }

   public void close() {
      if (!this.isShutdown.getAndSet(true)) {
         HikariPool p = this.pool;
         if (p != null) {
            try {
               p.shutdown();
            } catch (InterruptedException e) {
               LOGGER.warn("Interrupted during closing", e);
               Thread.currentThread().interrupt();
            }
         }

      }
   }

   public boolean isClosed() {
      return this.isShutdown.get();
   }

   /** @deprecated */
   @Deprecated
   public void shutdown() {
      LOGGER.warn("The shutdown() method has been deprecated, please use the close() method instead");
      this.close();
   }

   public String toString() {
      return "HikariDataSource (" + this.pool + ")";
   }
}
