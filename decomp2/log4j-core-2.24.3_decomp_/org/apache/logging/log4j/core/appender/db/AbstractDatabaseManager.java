package org.apache.logging.log4j.core.appender.db;

import java.io.Flushable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractManager;
import org.apache.logging.log4j.core.appender.ManagerFactory;
import org.apache.logging.log4j.core.config.Configuration;

public abstract class AbstractDatabaseManager extends AbstractManager implements Flushable {
   private final ArrayList buffer;
   private final int bufferSize;
   private final Layout layout;
   private boolean running;

   protected static AbstractDatabaseManager getManager(final String name, final AbstractFactoryData data, final ManagerFactory factory) {
      return (AbstractDatabaseManager)AbstractManager.getManager(name, factory, data);
   }

   /** @deprecated */
   @Deprecated
   protected AbstractDatabaseManager(final String name, final int bufferSize) {
      this(name, bufferSize, (Layout)null);
   }

   /** @deprecated */
   @Deprecated
   protected AbstractDatabaseManager(final String name, final int bufferSize, final Layout layout) {
      this(name, bufferSize, layout, (Configuration)null);
   }

   protected AbstractDatabaseManager(final String name, final int bufferSize, final Layout layout, final Configuration configuration) {
      super(configuration != null ? configuration.getLoggerContext() : null, name);
      this.bufferSize = bufferSize;
      this.buffer = new ArrayList(bufferSize + 1);
      this.layout = layout;
   }

   protected void buffer(final LogEvent event) {
      this.buffer.add(event.toImmutable());
      if (this.buffer.size() >= this.bufferSize || event.isEndOfBatch()) {
         this.flush();
      }

   }

   protected abstract boolean commitAndClose();

   protected abstract void connectAndStart();

   public final synchronized void flush() {
      if (this.isRunning() && this.isBuffered()) {
         this.connectAndStart();

         try {
            for(LogEvent event : this.buffer) {
               this.writeInternal(event, this.layout != null ? this.layout.toSerializable(event) : null);
            }
         } finally {
            this.commitAndClose();
            this.buffer.clear();
         }
      }

   }

   protected boolean isBuffered() {
      return this.bufferSize > 0;
   }

   public final boolean isRunning() {
      return this.running;
   }

   public final boolean releaseSub(final long timeout, final TimeUnit timeUnit) {
      return this.shutdown();
   }

   public final synchronized boolean shutdown() {
      boolean closed = true;
      this.flush();
      if (this.isRunning()) {
         try {
            closed &= this.shutdownInternal();
         } catch (Exception e) {
            this.logWarn("Caught exception while performing database shutdown operations", e);
            closed = false;
         } finally {
            this.running = false;
         }
      }

      return closed;
   }

   protected abstract boolean shutdownInternal() throws Exception;

   public final synchronized void startup() {
      if (!this.isRunning()) {
         try {
            this.startupInternal();
            this.running = true;
         } catch (Exception e) {
            this.logError("Could not perform database startup operations", e);
         }
      }

   }

   protected abstract void startupInternal() throws Exception;

   public final String toString() {
      return this.getName();
   }

   /** @deprecated */
   @Deprecated
   public final synchronized void write(final LogEvent event) {
      this.write(event, (Serializable)null);
   }

   public final synchronized void write(final LogEvent event, final Serializable serializable) {
      if (this.isBuffered()) {
         this.buffer(event);
      } else {
         this.writeThrough(event, serializable);
      }

   }

   /** @deprecated */
   @Deprecated
   protected void writeInternal(final LogEvent event) {
      this.writeInternal(event, (Serializable)null);
   }

   protected abstract void writeInternal(LogEvent event, Serializable serializable);

   protected void writeThrough(final LogEvent event, final Serializable serializable) {
      this.connectAndStart();

      try {
         this.writeInternal(event, serializable);
      } finally {
         this.commitAndClose();
      }

   }

   protected abstract static class AbstractFactoryData extends AbstractManager.AbstractFactoryData {
      private final int bufferSize;
      private final Layout layout;

      /** @deprecated */
      protected AbstractFactoryData(final int bufferSize, final Layout layout) {
         this((Configuration)null, bufferSize, layout);
      }

      protected AbstractFactoryData(final Configuration configuration, final int bufferSize, final Layout layout) {
         super(configuration);
         this.bufferSize = bufferSize;
         this.layout = layout;
      }

      public int getBufferSize() {
         return this.bufferSize;
      }

      public Layout getLayout() {
         return this.layout;
      }
   }
}
