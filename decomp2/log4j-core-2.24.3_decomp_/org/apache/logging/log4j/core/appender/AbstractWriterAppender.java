package org.apache.logging.log4j.core.appender;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.StringLayout;
import org.apache.logging.log4j.core.config.Property;

public abstract class AbstractWriterAppender extends AbstractAppender {
   protected final boolean immediateFlush;
   private final WriterManager manager;
   private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock readLock;

   protected AbstractWriterAppender(final String name, final StringLayout layout, final Filter filter, final boolean ignoreExceptions, final boolean immediateFlush, final Property[] properties, final WriterManager manager) {
      super(name, filter, layout, ignoreExceptions, properties);
      this.readLock = this.readWriteLock.readLock();
      this.manager = manager;
      this.immediateFlush = immediateFlush;
   }

   /** @deprecated */
   @Deprecated
   protected AbstractWriterAppender(final String name, final StringLayout layout, final Filter filter, final boolean ignoreExceptions, final boolean immediateFlush, final WriterManager manager) {
      super(name, filter, layout, ignoreExceptions, Property.EMPTY_ARRAY);
      this.readLock = this.readWriteLock.readLock();
      this.manager = manager;
      this.immediateFlush = immediateFlush;
   }

   public void append(final LogEvent event) {
      this.readLock.lock();

      try {
         String str = (String)this.getStringLayout().toSerializable(event);
         if (str.length() > 0) {
            this.manager.write(str);
            if (this.immediateFlush || event.isEndOfBatch()) {
               this.manager.flush();
            }
         }
      } catch (AppenderLoggingException ex) {
         this.error("Unable to write " + this.manager.getName() + " for appender " + this.getName(), event, ex);
         throw ex;
      } finally {
         this.readLock.unlock();
      }

   }

   public WriterManager getManager() {
      return this.manager;
   }

   public StringLayout getStringLayout() {
      return (StringLayout)this.getLayout();
   }

   public void start() {
      if (this.getLayout() == null) {
         LOGGER.error("No layout set for the appender named [{}].", this.getName());
      }

      if (this.manager == null) {
         LOGGER.error("No OutputStreamManager set for the appender named [{}].", this.getName());
      }

      super.start();
   }

   public boolean stop(final long timeout, final TimeUnit timeUnit) {
      this.setStopping();
      boolean stopped = super.stop(timeout, timeUnit, false);
      stopped &= this.manager.stop(timeout, timeUnit);
      this.setStopped();
      return stopped;
   }
}
