package org.apache.logging.log4j.core.config;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.Supplier;

public class AwaitCompletionReliabilityStrategy implements ReliabilityStrategy, LocationAwareReliabilityStrategy {
   private static final int MAX_RETRIES = 3;
   private final AtomicInteger counter = new AtomicInteger();
   private final AtomicBoolean shutdown = new AtomicBoolean();
   private final Lock shutdownLock = new ReentrantLock();
   private final Condition noLogEvents;
   private final LoggerConfig loggerConfig;

   public AwaitCompletionReliabilityStrategy(final LoggerConfig loggerConfig) {
      this.noLogEvents = this.shutdownLock.newCondition();
      this.loggerConfig = (LoggerConfig)Objects.requireNonNull(loggerConfig, "loggerConfig is null");
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
      return this.counter.incrementAndGet() > 0;
   }

   public void afterLogEvent() {
      if (this.counter.decrementAndGet() == 0 && this.shutdown.get()) {
         this.signalCompletionIfShutdown();
      }

   }

   private void signalCompletionIfShutdown() {
      Lock lock = this.shutdownLock;
      lock.lock();

      try {
         this.noLogEvents.signalAll();
      } finally {
         lock.unlock();
      }

   }

   public void beforeStopAppenders() {
      this.waitForCompletion();
   }

   private void waitForCompletion() {
      this.shutdownLock.lock();

      try {
         if (this.shutdown.compareAndSet(false, true)) {
            int retries = 0;

            while(!this.counter.compareAndSet(0, Integer.MIN_VALUE)) {
               if (this.counter.get() < 0) {
                  return;
               }

               try {
                  this.noLogEvents.await((long)(retries + 1), TimeUnit.SECONDS);
               } catch (InterruptedException var6) {
                  ++retries;
                  if (retries > 3) {
                     return;
                  }
               }
            }

         }
      } finally {
         this.shutdownLock.unlock();
      }
   }

   public void beforeStopConfiguration(final Configuration configuration) {
   }
}
