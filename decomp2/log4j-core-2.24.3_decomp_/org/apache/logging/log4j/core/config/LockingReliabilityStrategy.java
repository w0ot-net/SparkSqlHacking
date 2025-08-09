package org.apache.logging.log4j.core.config;

import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.Supplier;

public class LockingReliabilityStrategy implements ReliabilityStrategy, LocationAwareReliabilityStrategy {
   private final LoggerConfig loggerConfig;
   private final ReadWriteLock reconfigureLock = new ReentrantReadWriteLock();
   private volatile boolean isStopping;

   public LockingReliabilityStrategy(final LoggerConfig loggerConfig) {
      this.loggerConfig = (LoggerConfig)Objects.requireNonNull(loggerConfig, "loggerConfig was null");
   }

   public void log(final Supplier reconfigured, final String loggerName, final String fqcn, final Marker marker, final Level level, final Message data, final Throwable t) {
      LoggerConfig config = this.getActiveLoggerConfig(reconfigured);

      try {
         config.log(loggerName, fqcn, marker, level, data, t);
      } finally {
         config.getReliabilityStrategy().afterLogEvent();
      }

   }

   public void log(final Supplier reconfigured, final String loggerName, final String fqcn, final StackTraceElement location, final Marker marker, final Level level, final Message data, final Throwable t) {
      LoggerConfig config = this.getActiveLoggerConfig(reconfigured);

      try {
         config.log(loggerName, fqcn, location, marker, level, data, t);
      } finally {
         config.getReliabilityStrategy().afterLogEvent();
      }

   }

   public void log(final Supplier reconfigured, final LogEvent event) {
      LoggerConfig config = this.getActiveLoggerConfig(reconfigured);

      try {
         config.log(event);
      } finally {
         config.getReliabilityStrategy().afterLogEvent();
      }

   }

   public LoggerConfig getActiveLoggerConfig(final Supplier next) {
      LoggerConfig result = this.loggerConfig;
      if (!this.beforeLogEvent()) {
         result = (LoggerConfig)next.get();
         return result == this.loggerConfig ? result : result.getReliabilityStrategy().getActiveLoggerConfig(next);
      } else {
         return result;
      }
   }

   private boolean beforeLogEvent() {
      this.reconfigureLock.readLock().lock();
      if (this.isStopping) {
         this.reconfigureLock.readLock().unlock();
         return false;
      } else {
         return true;
      }
   }

   public void afterLogEvent() {
      this.reconfigureLock.readLock().unlock();
   }

   public void beforeStopAppenders() {
      this.reconfigureLock.writeLock().lock();

      try {
         this.isStopping = true;
      } finally {
         this.reconfigureLock.writeLock().unlock();
      }

   }

   public void beforeStopConfiguration(final Configuration configuration) {
   }
}
