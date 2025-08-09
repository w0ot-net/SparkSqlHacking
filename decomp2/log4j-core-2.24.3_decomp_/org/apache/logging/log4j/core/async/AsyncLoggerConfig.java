package org.apache.logging.log4j.core.async;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.jmx.RingBufferAdmin;
import org.apache.logging.log4j.core.util.Booleans;
import org.apache.logging.log4j.spi.AbstractLogger;

@Plugin(
   name = "AsyncLogger",
   category = "Core",
   printObject = true
)
public class AsyncLoggerConfig extends LoggerConfig {
   private static final ThreadLocal ASYNC_LOGGER_ENTERED = new ThreadLocal() {
      protected Boolean initialValue() {
         return Boolean.FALSE;
      }
   };
   private final AsyncLoggerConfigDelegate delegate;

   @PluginBuilderFactory
   public static Builder newAsyncBuilder() {
      return (Builder)(new Builder()).asBuilder();
   }

   protected AsyncLoggerConfig(final String name, final List appenders, final Filter filter, final Level level, final boolean additive, final Property[] properties, final Configuration config, final boolean includeLocation) {
      super(name, appenders, filter, level, additive, properties, config, includeLocation);
      this.delegate = config.getAsyncLoggerConfigDelegate();
      this.delegate.setLogEventFactory(this.getLogEventFactory());
   }

   AsyncLoggerConfigDelegate getAsyncLoggerConfigDelegate() {
      return this.delegate;
   }

   protected void log(final LogEvent event, final LoggerConfig.LoggerConfigPredicate predicate) {
      if (predicate == LoggerConfig.LoggerConfigPredicate.ALL && ASYNC_LOGGER_ENTERED.get() == Boolean.FALSE && this.hasAppenders()) {
         ASYNC_LOGGER_ENTERED.set(Boolean.TRUE);

         try {
            if (!this.isFiltered(event)) {
               this.processLogEvent(event, LoggerConfig.LoggerConfigPredicate.SYNCHRONOUS_ONLY);
               this.logToAsyncDelegate(event);
            }
         } finally {
            ASYNC_LOGGER_ENTERED.set(Boolean.FALSE);
         }
      } else {
         super.log(event, predicate);
      }

   }

   protected void callAppenders(final LogEvent event) {
      super.callAppenders(event);
   }

   private void logToAsyncDelegate(final LogEvent event) {
      this.populateLazilyInitializedFields(event);
      if (!this.delegate.tryEnqueue(event, this)) {
         this.handleQueueFull(event);
      }

   }

   private void handleQueueFull(final LogEvent event) {
      if (AbstractLogger.getRecursionDepth() > 1) {
         AsyncQueueFullMessageUtil.logWarningToStatusLogger();
         this.logToAsyncLoggerConfigsOnCurrentThread(event);
      } else {
         EventRoute eventRoute = this.delegate.getEventRoute(event.getLevel());
         eventRoute.logMessage(this, event);
      }

   }

   private void populateLazilyInitializedFields(final LogEvent event) {
      event.getSource();
      event.getThreadName();
   }

   void logInBackgroundThread(final LogEvent event) {
      this.delegate.enqueueEvent(event, this);
   }

   void logToAsyncLoggerConfigsOnCurrentThread(final LogEvent event) {
      this.processLogEvent(event, LoggerConfig.LoggerConfigPredicate.ASYNCHRONOUS_ONLY);
   }

   private String displayName() {
      return "".equals(this.getName()) ? "root" : this.getName();
   }

   public void start() {
      LOGGER.trace("AsyncLoggerConfig[{}] starting...", this.displayName());
      super.start();
   }

   public boolean stop(final long timeout, final TimeUnit timeUnit) {
      this.setStopping();
      super.stop(timeout, timeUnit, false);
      LOGGER.trace("AsyncLoggerConfig[{}] stopping...", this.displayName());
      this.setStopped();
      return true;
   }

   public RingBufferAdmin createRingBufferAdmin(final String contextName) {
      return this.delegate.createRingBufferAdmin(contextName, this.getName());
   }

   /** @deprecated */
   @Deprecated
   public static LoggerConfig createLogger(final String additivity, final String levelName, final String loggerName, final String includeLocation, final AppenderRef[] refs, final Property[] properties, final Configuration config, final Filter filter) {
      if (loggerName == null) {
         LOGGER.error("Loggers cannot be configured without a name");
         return null;
      } else {
         List<AppenderRef> appenderRefs = Arrays.asList(refs);

         Level level;
         try {
            level = Level.toLevel(levelName, Level.ERROR);
         } catch (Exception var12) {
            LOGGER.error("Invalid Log level specified: {}. Defaulting to Error", levelName);
            level = Level.ERROR;
         }

         String name = loggerName.equals("root") ? "" : loggerName;
         boolean additive = Booleans.parseBoolean(additivity, true);
         return new AsyncLoggerConfig(name, appenderRefs, filter, level, additive, properties, config, includeLocation(includeLocation));
      }
   }

   /** @deprecated */
   @Deprecated
   public static LoggerConfig createLogger(@PluginAttribute(value = "additivity",defaultBoolean = true) final boolean additivity, @PluginAttribute("level") final Level level, @Required(message = "Loggers cannot be configured without a name") @PluginAttribute("name") final String loggerName, @PluginAttribute("includeLocation") final String includeLocation, @PluginElement("AppenderRef") final AppenderRef[] refs, @PluginElement("Properties") final Property[] properties, @PluginConfiguration final Configuration config, @PluginElement("Filter") final Filter filter) {
      String name = loggerName.equals("root") ? "" : loggerName;
      return new AsyncLoggerConfig(name, Arrays.asList(refs), filter, level, additivity, properties, config, includeLocation(includeLocation));
   }

   protected static boolean includeLocation(final String includeLocationConfigValue) {
      return Boolean.parseBoolean(includeLocationConfigValue);
   }

   public static class Builder extends LoggerConfig.Builder {
      public LoggerConfig build() {
         String name = this.getLoggerName().equals("root") ? "" : this.getLoggerName();
         LoggerConfig.LevelAndRefs container = AsyncLoggerConfig.getLevelAndRefs(this.getLevel(), this.getRefs(), this.getLevelAndRefs(), this.getConfig());
         return new AsyncLoggerConfig(name, container.refs, this.getFilter(), container.level, this.isAdditivity(), this.getProperties(), this.getConfig(), AsyncLoggerConfig.includeLocation(this.getIncludeLocation()));
      }
   }

   @Plugin(
      name = "AsyncRoot",
      category = "Core",
      printObject = true
   )
   public static class RootLogger extends LoggerConfig {
      @PluginBuilderFactory
      public static Builder newAsyncRootBuilder() {
         return (Builder)(new Builder()).asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public static LoggerConfig createLogger(final String additivity, final String levelName, final String includeLocation, final AppenderRef[] refs, final Property[] properties, final Configuration config, final Filter filter) {
         List<AppenderRef> appenderRefs = Arrays.asList(refs);
         Level level = null;

         try {
            level = Level.toLevel(levelName, Level.ERROR);
         } catch (Exception var10) {
            LOGGER.error("Invalid Log level specified: {}. Defaulting to Error", levelName);
            level = Level.ERROR;
         }

         boolean additive = Booleans.parseBoolean(additivity, true);
         return new AsyncLoggerConfig("", appenderRefs, filter, level, additive, properties, config, AsyncLoggerConfig.includeLocation(includeLocation));
      }

      /** @deprecated */
      @Deprecated
      public static LoggerConfig createLogger(@PluginAttribute("additivity") final String additivity, @PluginAttribute("level") final Level level, @PluginAttribute("includeLocation") final String includeLocation, @PluginElement("AppenderRef") final AppenderRef[] refs, @PluginElement("Properties") final Property[] properties, @PluginConfiguration final Configuration config, @PluginElement("Filter") final Filter filter) {
         List<AppenderRef> appenderRefs = Arrays.asList(refs);
         Level actualLevel = level == null ? Level.ERROR : level;
         boolean additive = Booleans.parseBoolean(additivity, true);
         return new AsyncLoggerConfig("", appenderRefs, filter, actualLevel, additive, properties, config, AsyncLoggerConfig.includeLocation(includeLocation));
      }

      public static class Builder extends LoggerConfig.Builder {
         public LoggerConfig build() {
            LoggerConfig.LevelAndRefs container = AsyncLoggerConfig.RootLogger.getLevelAndRefs(this.getLevel(), this.getRefs(), this.getLevelAndRefs(), this.getConfig());
            return new AsyncLoggerConfig("", container.refs, this.getFilter(), container.level, this.isAdditivity(), this.getProperties(), this.getConfig(), AsyncLoggerConfig.includeLocation(this.getIncludeLocation()));
         }
      }
   }
}
