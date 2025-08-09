package org.apache.logging.log4j.core.appender;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.util.Constants;

public abstract class AbstractOutputStreamAppender extends AbstractAppender {
   private final boolean immediateFlush;
   private final OutputStreamManager manager;

   /** @deprecated */
   @Deprecated
   protected AbstractOutputStreamAppender(final String name, final Layout layout, final Filter filter, final boolean ignoreExceptions, final boolean immediateFlush, final OutputStreamManager manager) {
      super(name, filter, layout, ignoreExceptions, Property.EMPTY_ARRAY);
      this.manager = manager;
      this.immediateFlush = immediateFlush;
   }

   protected AbstractOutputStreamAppender(final String name, final Layout layout, final Filter filter, final boolean ignoreExceptions, final boolean immediateFlush, final Property[] properties, final OutputStreamManager manager) {
      super(name, filter, layout, ignoreExceptions, properties);
      this.manager = manager;
      this.immediateFlush = immediateFlush;
   }

   public boolean getImmediateFlush() {
      return this.immediateFlush;
   }

   public OutputStreamManager getManager() {
      return this.manager;
   }

   public void start() {
      if (this.getLayout() == null) {
         LOGGER.error("No layout set for the appender named [" + this.getName() + "].");
      }

      if (this.manager == null) {
         LOGGER.error("No OutputStreamManager set for the appender named [" + this.getName() + "].");
      }

      super.start();
   }

   public boolean stop(final long timeout, final TimeUnit timeUnit) {
      return this.stop(timeout, timeUnit, true);
   }

   protected boolean stop(final long timeout, final TimeUnit timeUnit, final boolean changeLifeCycleState) {
      boolean stopped = super.stop(timeout, timeUnit, changeLifeCycleState);
      stopped &= this.manager.stop(timeout, timeUnit);
      if (changeLifeCycleState) {
         this.setStopped();
      }

      LOGGER.debug("Appender {} stopped with status {}", this.getName(), stopped);
      return stopped;
   }

   public void append(final LogEvent event) {
      try {
         this.tryAppend(event);
      } catch (AppenderLoggingException ex) {
         this.error("Unable to write to stream " + this.manager.getName() + " for appender " + this.getName(), event, ex);
         throw ex;
      }
   }

   private void tryAppend(final LogEvent event) {
      if (Constants.ENABLE_DIRECT_ENCODERS) {
         this.directEncodeEvent(event);
      } else {
         this.writeByteArrayToManager(event);
      }

   }

   protected void directEncodeEvent(final LogEvent event) {
      this.getLayout().encode(event, this.manager);
      if (this.immediateFlush || event.isEndOfBatch()) {
         this.manager.flush();
      }

   }

   protected void writeByteArrayToManager(final LogEvent event) {
      byte[] bytes = this.getLayout().toByteArray(event);
      if (bytes != null && bytes.length > 0) {
         this.manager.write(bytes, this.immediateFlush || event.isEndOfBatch());
      }

   }

   public abstract static class Builder extends AbstractAppender.Builder {
      @PluginBuilderAttribute
      private boolean bufferedIo = true;
      @PluginBuilderAttribute
      private int bufferSize;
      @PluginBuilderAttribute
      private boolean immediateFlush;

      public Builder() {
         this.bufferSize = Constants.ENCODER_BYTE_BUFFER_SIZE;
         this.immediateFlush = true;
      }

      public int getBufferSize() {
         return this.bufferSize;
      }

      public boolean isBufferedIo() {
         return this.bufferedIo;
      }

      public boolean isImmediateFlush() {
         return this.immediateFlush;
      }

      public Builder setImmediateFlush(final boolean immediateFlush) {
         this.immediateFlush = immediateFlush;
         return (Builder)this.asBuilder();
      }

      public Builder setBufferedIo(final boolean bufferedIo) {
         this.bufferedIo = bufferedIo;
         return (Builder)this.asBuilder();
      }

      public Builder setBufferSize(final int bufferSize) {
         this.bufferSize = bufferSize;
         return (Builder)this.asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public Builder withImmediateFlush(final boolean immediateFlush) {
         this.immediateFlush = immediateFlush;
         return (Builder)this.asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public Builder withBufferedIo(final boolean bufferedIo) {
         this.bufferedIo = bufferedIo;
         return (Builder)this.asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public Builder withBufferSize(final int bufferSize) {
         this.bufferSize = bufferSize;
         return (Builder)this.asBuilder();
      }
   }
}
