package org.apache.logging.log4j.core.appender.mom.kafka;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;
import org.apache.logging.log4j.core.AbstractLifeCycle;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.layout.SerializedLayout;
import org.apache.logging.log4j.core.util.Integers;

@Plugin(
   name = "Kafka",
   category = "Core",
   elementType = "appender",
   printObject = true
)
public final class KafkaAppender extends AbstractAppender {
   private static final String[] KAFKA_CLIENT_PACKAGES = new String[]{"org.apache.kafka.common", "org.apache.kafka.clients"};
   private final Integer retryCount;
   private final KafkaManager manager;

   /** @deprecated */
   @Deprecated
   public static KafkaAppender createAppender(final Layout layout, final Filter filter, final String name, final boolean ignoreExceptions, final String topic, final Property[] properties, final Configuration configuration, final String key) {
      if (layout == null) {
         AbstractLifeCycle.LOGGER.error("No layout provided for KafkaAppender");
         return null;
      } else {
         KafkaManager kafkaManager = KafkaManager.getManager(configuration.getLoggerContext(), name, topic, true, properties, key);
         return new KafkaAppender(name, layout, filter, ignoreExceptions, kafkaManager, (Property[])null, 0);
      }
   }

   private static boolean isRecursive(final LogEvent event) {
      return Stream.of(KAFKA_CLIENT_PACKAGES).anyMatch((prefix) -> event.getLoggerName().startsWith(prefix));
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return (Builder)(new Builder()).asBuilder();
   }

   private KafkaAppender(final String name, final Layout layout, final Filter filter, final boolean ignoreExceptions, final KafkaManager manager, final Property[] properties, final int retryCount) {
      super(name, filter, layout, ignoreExceptions, properties);
      this.manager = (KafkaManager)Objects.requireNonNull(manager, "manager");
      this.retryCount = retryCount;
   }

   public void append(final LogEvent event) {
      if (event.getLoggerName() != null && isRecursive(event)) {
         LOGGER.warn("Recursive logging from [{}] for appender [{}].", event.getLoggerName(), this.getName());
      } else {
         try {
            this.tryAppend(event);
         } catch (Exception e) {
            if (this.retryCount != null) {
               int currentRetryAttempt = 0;

               while(currentRetryAttempt < this.retryCount) {
                  ++currentRetryAttempt;

                  try {
                     this.tryAppend(event);
                     break;
                  } catch (Exception var5) {
                  }
               }
            }

            this.error("Unable to write to Kafka in appender [" + this.getName() + "]", event, e);
         }
      }

   }

   public void start() {
      super.start();
      this.manager.startup();
   }

   public boolean stop(final long timeout, final TimeUnit timeUnit) {
      this.setStopping();
      boolean stopped = super.stop(timeout, timeUnit, false);
      stopped &= this.manager.stop(timeout, timeUnit);
      this.setStopped();
      return stopped;
   }

   public String toString() {
      return "KafkaAppender{name=" + this.getName() + ", state=" + this.getState() + ", topic=" + this.manager.getTopic() + '}';
   }

   private void tryAppend(final LogEvent event) throws ExecutionException, InterruptedException, TimeoutException {
      Layout<? extends Serializable> layout = this.getLayout();
      byte[] data;
      if (layout instanceof SerializedLayout) {
         byte[] header = layout.getHeader();
         byte[] body = layout.toByteArray(event);
         data = new byte[header.length + body.length];
         System.arraycopy(header, 0, data, 0, header.length);
         System.arraycopy(body, 0, data, header.length, body.length);
      } else {
         data = layout.toByteArray(event);
      }

      this.manager.send(data, event.getTimeMillis());
   }

   public static class Builder extends AbstractAppender.Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginAttribute("retryCount")
      private int retryCount;
      @PluginAttribute("topic")
      private String topic;
      @PluginAttribute("key")
      private String key;
      @PluginAttribute(
         value = "syncSend",
         defaultBoolean = true
      )
      private boolean syncSend;
      @PluginAttribute(
         value = "sendEventTimestamp",
         defaultBoolean = false
      )
      private boolean sendEventTimestamp;

      public KafkaAppender build() {
         Layout<? extends Serializable> layout = this.getLayout();
         if (layout == null) {
            KafkaAppender.LOGGER.error("No layout provided for KafkaAppender");
            return null;
         } else {
            KafkaManager kafkaManager = KafkaManager.getManager(this.getConfiguration().getLoggerContext(), this.getName(), this.topic, this.syncSend, this.sendEventTimestamp, this.getPropertyArray(), this.key);
            return new KafkaAppender(this.getName(), layout, this.getFilter(), this.isIgnoreExceptions(), kafkaManager, this.getPropertyArray(), this.getRetryCount());
         }
      }

      public Integer getRetryCount() {
         Integer intRetryCount = null;

         try {
            intRetryCount = this.retryCount;
         } catch (NumberFormatException var3) {
         }

         return intRetryCount;
      }

      public String getTopic() {
         return this.topic;
      }

      public boolean isSendEventTimestamp() {
         return this.sendEventTimestamp;
      }

      public boolean isSyncSend() {
         return this.syncSend;
      }

      public Builder setKey(final String key) {
         this.key = key;
         return (Builder)this.asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public Builder setRetryCount(final String retryCount) {
         this.retryCount = Integers.parseInt(retryCount, 0);
         return (Builder)this.asBuilder();
      }

      public Builder setRetryCount(final int retryCount) {
         this.retryCount = retryCount;
         return (Builder)this.asBuilder();
      }

      public Builder setSendEventTimestamp(final boolean sendEventTimestamp) {
         this.sendEventTimestamp = sendEventTimestamp;
         return (Builder)this.asBuilder();
      }

      public Builder setSyncSend(final boolean syncSend) {
         this.syncSend = syncSend;
         return (Builder)this.asBuilder();
      }

      public Builder setTopic(final String topic) {
         this.topic = topic;
         return (Builder)this.asBuilder();
      }
   }
}
