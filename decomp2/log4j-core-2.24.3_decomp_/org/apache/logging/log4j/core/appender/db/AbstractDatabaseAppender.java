package org.apache.logging.log4j.core.appender.db;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.logging.log4j.LoggingException;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.config.Property;

public abstract class AbstractDatabaseAppender extends AbstractAppender {
   public static final int DEFAULT_RECONNECT_INTERVAL_MILLIS = 5000;
   private final ReadWriteLock lock = new ReentrantReadWriteLock();
   private final Lock readLock;
   private final Lock writeLock;
   private AbstractDatabaseManager manager;

   /** @deprecated */
   @Deprecated
   protected AbstractDatabaseAppender(final String name, final Filter filter, final boolean ignoreExceptions, final AbstractDatabaseManager manager) {
      super(name, filter, (Layout)null, ignoreExceptions, Property.EMPTY_ARRAY);
      this.readLock = this.lock.readLock();
      this.writeLock = this.lock.writeLock();
      this.manager = manager;
   }

   protected AbstractDatabaseAppender(final String name, final Filter filter, final Layout layout, final boolean ignoreExceptions, final Property[] properties, final AbstractDatabaseManager manager) {
      super(name, filter, layout, ignoreExceptions, properties);
      this.readLock = this.lock.readLock();
      this.writeLock = this.lock.writeLock();
      this.manager = manager;
   }

   /** @deprecated */
   @Deprecated
   protected AbstractDatabaseAppender(final String name, final Filter filter, final Layout layout, final boolean ignoreExceptions, final AbstractDatabaseManager manager) {
      super(name, filter, layout, ignoreExceptions, Property.EMPTY_ARRAY);
      this.readLock = this.lock.readLock();
      this.writeLock = this.lock.writeLock();
      this.manager = manager;
   }

   public final void append(final LogEvent event) {
      this.readLock.lock();

      try {
         this.getManager().write(event, this.toSerializable(event));
      } catch (LoggingException e) {
         LOGGER.error("Unable to write to database [{}] for appender [{}].", this.getManager().getName(), this.getName(), e);
         throw e;
      } catch (Exception e) {
         LOGGER.error("Unable to write to database [{}] for appender [{}].", this.getManager().getName(), this.getName(), e);
         throw new AppenderLoggingException("Unable to write to database in appender: " + e.getMessage(), e);
      } finally {
         this.readLock.unlock();
      }

   }

   public final Layout getLayout() {
      return null;
   }

   public final AbstractDatabaseManager getManager() {
      return this.manager;
   }

   protected final void replaceManager(final AbstractDatabaseManager manager) {
      this.writeLock.lock();

      try {
         T old = (T)this.getManager();
         if (!manager.isRunning()) {
            manager.startup();
         }

         this.manager = manager;
         old.close();
      } finally {
         this.writeLock.unlock();
      }

   }

   public final void start() {
      if (this.getManager() == null) {
         LOGGER.error("No AbstractDatabaseManager set for the appender named [{}].", this.getName());
      }

      super.start();
      if (this.getManager() != null) {
         this.getManager().startup();
      }

   }

   public boolean stop(final long timeout, final TimeUnit timeUnit) {
      this.setStopping();
      boolean stopped = super.stop(timeout, timeUnit, false);
      if (this.getManager() != null) {
         stopped &= this.getManager().stop(timeout, timeUnit);
      }

      this.setStopped();
      return stopped;
   }

   public static class Builder extends AbstractAppender.Builder {
   }
}
