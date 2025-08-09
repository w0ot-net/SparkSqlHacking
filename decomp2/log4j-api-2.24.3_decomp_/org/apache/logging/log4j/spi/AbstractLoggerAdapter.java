package org.apache.logging.log4j.spi;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.util.LoaderUtil;

public abstract class AbstractLoggerAdapter implements LoggerAdapter, LoggerContextShutdownAware {
   protected final Map registry = new ConcurrentHashMap();
   private final ReadWriteLock lock = new ReentrantReadWriteLock(true);

   public Object getLogger(final String name) {
      LoggerContext context = this.getContext();
      ConcurrentMap<String, L> loggers = this.getLoggersInContext(context);
      L logger = (L)loggers.get(name);
      if (logger != null) {
         return logger;
      } else {
         loggers.putIfAbsent(name, this.newLogger(name, context));
         return loggers.get(name);
      }
   }

   public void contextShutdown(final LoggerContext loggerContext) {
      this.registry.remove(loggerContext);
   }

   public ConcurrentMap getLoggersInContext(final LoggerContext context) {
      this.lock.readLock().lock();

      ConcurrentMap<String, L> loggers;
      try {
         loggers = (ConcurrentMap)this.registry.get(context);
      } finally {
         this.lock.readLock().unlock();
      }

      if (loggers != null) {
         return loggers;
      } else {
         this.lock.writeLock().lock();

         Object var3;
         try {
            ConcurrentMap loggers = (ConcurrentMap)this.registry.get(context);
            if (loggers == null) {
               loggers = new ConcurrentHashMap();
               this.registry.put(context, loggers);
               if (context instanceof LoggerContextShutdownEnabled) {
                  ((LoggerContextShutdownEnabled)context).addShutdownListener(this);
               }
            }

            var3 = loggers;
         } finally {
            this.lock.writeLock().unlock();
         }

         return (ConcurrentMap)var3;
      }
   }

   public Set getLoggerContexts() {
      return new HashSet(this.registry.keySet());
   }

   protected abstract Object newLogger(final String name, final LoggerContext context);

   protected abstract LoggerContext getContext();

   protected LoggerContext getContext(final Class callerClass) {
      ClassLoader cl = null;
      if (callerClass != null) {
         cl = callerClass.getClassLoader();
      }

      if (cl == null) {
         cl = LoaderUtil.getThreadContextClassLoader();
      }

      return LogManager.getContext(cl, false);
   }

   public void close() {
      this.lock.writeLock().lock();

      try {
         this.registry.clear();
      } finally {
         this.lock.writeLock().unlock();
      }

   }
}
