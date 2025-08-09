package org.apache.logging.log4j.core.appender;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.AbstractLifeCycle;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationException;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.status.StatusLogger;

public abstract class AbstractManager implements AutoCloseable {
   protected static final Logger LOGGER = StatusLogger.getLogger();
   private static final Map MAP = new HashMap();
   private static final Lock LOCK = new ReentrantLock();
   protected int count;
   private final String name;
   private final LoggerContext loggerContext;

   protected AbstractManager(final LoggerContext loggerContext, final String name) {
      this.loggerContext = loggerContext;
      this.name = name;
      LOGGER.debug("Starting {} {}", this.getClass().getSimpleName(), name);
   }

   public void close() {
      this.stop(0L, AbstractLifeCycle.DEFAULT_STOP_TIMEUNIT);
   }

   public boolean stop(final long timeout, final TimeUnit timeUnit) {
      boolean stopped = true;
      LOCK.lock();

      try {
         --this.count;
         if (this.count <= 0) {
            MAP.remove(this.name);
            LOGGER.debug("Shutting down {} {}", this.getClass().getSimpleName(), this.getName());
            stopped = this.releaseSub(timeout, timeUnit);
            LOGGER.debug("Shut down {} {}, all resources released: {}", this.getClass().getSimpleName(), this.getName(), stopped);
         }
      } finally {
         LOCK.unlock();
      }

      return stopped;
   }

   public static AbstractManager getManager(final String name, final ManagerFactory factory, final Object data) {
      LOCK.lock();

      AbstractManager var4;
      try {
         M manager = (M)((AbstractManager)MAP.get(name));
         if (manager == null) {
            manager = (M)((AbstractManager)((ManagerFactory)Objects.requireNonNull(factory, "factory")).createManager(name, data));
            if (manager == null) {
               throw new IllegalStateException("ManagerFactory [" + factory + "] unable to create manager for [" + name + "] with data [" + data + "]");
            }

            MAP.put(name, manager);
         } else {
            manager.updateData(data);
         }

         ++manager.count;
         var4 = manager;
      } finally {
         LOCK.unlock();
      }

      return var4;
   }

   public void updateData(final Object data) {
   }

   public static boolean hasManager(final String name) {
      LOCK.lock();

      boolean var1;
      try {
         var1 = MAP.containsKey(name);
      } finally {
         LOCK.unlock();
      }

      return var1;
   }

   protected static AbstractManager narrow(final Class narrowClass, final AbstractManager manager) {
      if (narrowClass.isAssignableFrom(manager.getClass())) {
         return manager;
      } else {
         throw new ConfigurationException("Configuration has multiple incompatible Appenders pointing to the same resource '" + manager.getName() + "'");
      }
   }

   protected static StatusLogger logger() {
      return StatusLogger.getLogger();
   }

   static int getManagerCount() {
      return MAP.size();
   }

   protected boolean releaseSub(final long timeout, final TimeUnit timeUnit) {
      return true;
   }

   protected int getCount() {
      return this.count;
   }

   public LoggerContext getLoggerContext() {
      return this.loggerContext;
   }

   /** @deprecated */
   @Deprecated
   public void release() {
      this.close();
   }

   public String getName() {
      return this.name;
   }

   public Map getContentFormat() {
      return new HashMap();
   }

   protected StrSubstitutor getStrSubstitutor() {
      if (this.loggerContext == null) {
         return null;
      } else {
         Configuration configuration = this.loggerContext.getConfiguration();
         return configuration == null ? null : configuration.getStrSubstitutor();
      }
   }

   protected void log(final Level level, final String message, final Throwable throwable) {
      Message m = LOGGER.getMessageFactory().newMessage("{} {} {}: {}", new Object[]{this.getClass().getSimpleName(), this.getName(), message, throwable});
      LOGGER.log(level, m, throwable);
   }

   protected void logDebug(final String message, final Throwable throwable) {
      this.log(Level.DEBUG, message, throwable);
   }

   protected void logError(final String message, final Throwable throwable) {
      this.log(Level.ERROR, message, throwable);
   }

   protected void logWarn(final String message, final Throwable throwable) {
      this.log(Level.WARN, message, throwable);
   }

   protected abstract static class AbstractFactoryData {
      private final Configuration configuration;

      protected AbstractFactoryData(final Configuration configuration) {
         this.configuration = configuration;
      }

      public Configuration getConfiguration() {
         return this.configuration;
      }
   }
}
