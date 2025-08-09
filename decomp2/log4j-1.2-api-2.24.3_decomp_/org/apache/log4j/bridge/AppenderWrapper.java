package org.apache.log4j.bridge;

import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilterable;
import org.apache.logging.log4j.status.StatusLogger;

public class AppenderWrapper implements Appender {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private final org.apache.logging.log4j.core.Appender appender;

   public static Appender adapt(final org.apache.logging.log4j.core.Appender appender) {
      if (appender instanceof Appender) {
         return (Appender)appender;
      } else {
         if (appender instanceof AppenderAdapter.Adapter) {
            AppenderAdapter.Adapter adapter = (AppenderAdapter.Adapter)appender;
            if (!adapter.hasFilter()) {
               return adapter.getAppender();
            }
         }

         return appender != null ? new AppenderWrapper(appender) : null;
      }
   }

   public AppenderWrapper(final org.apache.logging.log4j.core.Appender appender) {
      this.appender = appender;
   }

   public org.apache.logging.log4j.core.Appender getAppender() {
      return this.appender;
   }

   public void addFilter(final Filter newFilter) {
      if (this.appender instanceof AbstractFilterable) {
         ((AbstractFilterable)this.appender).addFilter(FilterAdapter.adapt(newFilter));
      } else {
         LOGGER.warn("Unable to add filter to appender {}, it does not support filters", this.appender.getName());
      }

   }

   public Filter getFilter() {
      return null;
   }

   public void clearFilters() {
   }

   public void close() {
   }

   public void doAppend(final LoggingEvent event) {
      if (event instanceof LogEventAdapter) {
         this.appender.append(((LogEventAdapter)event).getEvent());
      }

   }

   public String getName() {
      return this.appender.getName();
   }

   public void setErrorHandler(final ErrorHandler errorHandler) {
      this.appender.setHandler(new ErrorHandlerAdapter(errorHandler));
   }

   public ErrorHandler getErrorHandler() {
      return ((ErrorHandlerAdapter)this.appender.getHandler()).getHandler();
   }

   public void setLayout(final Layout layout) {
   }

   public Layout getLayout() {
      return new LayoutWrapper(this.appender.getLayout());
   }

   public void setName(final String name) {
   }

   public boolean requiresLayout() {
      return false;
   }
}
