package org.apache.log4j;

import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;

public abstract class AppenderSkeleton implements Appender, OptionHandler {
   protected Layout layout;
   protected String name;
   protected Priority threshold;
   protected ErrorHandler errorHandler = new NoOpErrorHandler();
   protected Filter headFilter;
   protected Filter tailFilter;
   protected boolean closed = false;

   public AppenderSkeleton() {
   }

   protected AppenderSkeleton(final boolean isActive) {
   }

   public void activateOptions() {
   }

   public void addFilter(final Filter newFilter) {
      if (this.headFilter == null) {
         this.headFilter = this.tailFilter = newFilter;
      } else {
         this.tailFilter.setNext(newFilter);
         this.tailFilter = newFilter;
      }

   }

   protected abstract void append(LoggingEvent event);

   public void clearFilters() {
      this.headFilter = this.tailFilter = null;
   }

   public void finalize() {
   }

   public ErrorHandler getErrorHandler() {
      return this.errorHandler;
   }

   public Filter getFilter() {
      return this.headFilter;
   }

   public final Filter getFirstFilter() {
      return this.headFilter;
   }

   public Layout getLayout() {
      return this.layout;
   }

   public final String getName() {
      return this.name;
   }

   public Priority getThreshold() {
      return this.threshold;
   }

   public boolean isAsSevereAsThreshold(final Priority priority) {
      return this.threshold == null || priority.isGreaterOrEqual(this.threshold);
   }

   public synchronized void doAppend(final LoggingEvent event) {
      this.append(event);
   }

   public synchronized void setErrorHandler(final ErrorHandler eh) {
      if (eh != null) {
         this.errorHandler = eh;
      }

   }

   public void setLayout(final Layout layout) {
      this.layout = layout;
   }

   public void setName(final String name) {
      this.name = name;
   }

   public void setThreshold(final Priority threshold) {
      this.threshold = threshold;
   }

   public static class NoOpErrorHandler implements ErrorHandler {
      public void setLogger(final Logger logger) {
      }

      public void error(final String message, final Exception e, final int errorCode) {
      }

      public void error(final String message) {
      }

      public void error(final String message, final Exception e, final int errorCode, final LoggingEvent event) {
      }

      public void setAppender(final Appender appender) {
      }

      public void setBackupAppender(final Appender appender) {
      }
   }
}
