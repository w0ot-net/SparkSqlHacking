package org.apache.logging.log4j.core.appender;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.ErrorHandler;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.status.StatusLogger;

public class DefaultErrorHandler implements ErrorHandler {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static final int MAX_EXCEPTION_COUNT = 3;
   private static final long EXCEPTION_INTERVAL_NANOS;
   private int exceptionCount = 0;
   private long lastExceptionInstantNanos;
   private final Appender appender;

   public DefaultErrorHandler(final Appender appender) {
      this.lastExceptionInstantNanos = System.nanoTime() - EXCEPTION_INTERVAL_NANOS - 1L;
      this.appender = (Appender)Objects.requireNonNull(appender, "appender");
   }

   public void error(final String msg) {
      boolean allowed = this.acquirePermit();
      if (allowed) {
         LOGGER.error(msg);
      }

   }

   public void error(final String msg, final Throwable error) {
      boolean allowed = this.acquirePermit();
      if (allowed) {
         LOGGER.error(msg, error);
      }

      if (!this.appender.ignoreExceptions() && error != null && !(error instanceof AppenderLoggingException)) {
         throw new AppenderLoggingException(msg, error);
      }
   }

   public void error(final String msg, final LogEvent event, final Throwable error) {
      boolean allowed = this.acquirePermit();
      if (allowed) {
         LOGGER.error(msg, error);
      }

      if (!this.appender.ignoreExceptions() && error != null && !(error instanceof AppenderLoggingException)) {
         throw new AppenderLoggingException(msg, error);
      }
   }

   private boolean acquirePermit() {
      long currentInstantNanos = System.nanoTime();
      synchronized(this) {
         if (currentInstantNanos - this.lastExceptionInstantNanos > EXCEPTION_INTERVAL_NANOS) {
            this.lastExceptionInstantNanos = currentInstantNanos;
            return true;
         } else if (this.exceptionCount < 3) {
            ++this.exceptionCount;
            this.lastExceptionInstantNanos = currentInstantNanos;
            return true;
         } else {
            return false;
         }
      }
   }

   public Appender getAppender() {
      return this.appender;
   }

   static {
      EXCEPTION_INTERVAL_NANOS = TimeUnit.MINUTES.toNanos(5L);
   }
}
