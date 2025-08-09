package org.apache.logging.log4j.core.async;

import aQute.bnd.annotation.baseline.BaselineIgnore;
import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.dsl.Disruptor;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.ContextDataInjector;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.ReliabilityStrategy;
import org.apache.logging.log4j.core.impl.ContextDataFactory;
import org.apache.logging.log4j.core.impl.ContextDataInjectorFactory;
import org.apache.logging.log4j.core.util.Clock;
import org.apache.logging.log4j.core.util.ClockFactory;
import org.apache.logging.log4j.core.util.NanoClock;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.ReusableMessage;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.StackLocatorUtil;
import org.apache.logging.log4j.util.StringMap;

@BaselineIgnore("2.24.3")
public class AsyncLogger extends Logger implements EventTranslatorVararg {
   private static final StatusLogger LOGGER = StatusLogger.getLogger();
   private static Clock CLOCK = ClockFactory.getClock();
   private static final ContextDataInjector CONTEXT_DATA_INJECTOR = ContextDataInjectorFactory.createInjector();
   private static final ThreadNameCachingStrategy THREAD_NAME_CACHING_STRATEGY = ThreadNameCachingStrategy.create();
   private final ThreadLocal threadLocalTranslator = new ThreadLocal();
   private final AsyncLoggerDisruptor loggerDisruptor;
   private volatile boolean includeLocation;
   private volatile NanoClock nanoClock;
   private final TranslatorType threadLocalTranslatorType = new TranslatorType() {
      void log(final String fqcn, final StackTraceElement location, final Level level, final Marker marker, final Message message, final Throwable thrown) {
         AsyncLogger.this.logWithThreadLocalTranslator(fqcn, location, level, marker, message, thrown);
      }

      void log(final String fqcn, final Level level, final Marker marker, final Message message, final Throwable thrown) {
         AsyncLogger.this.logWithThreadLocalTranslator(fqcn, level, marker, message, thrown);
      }
   };
   private final TranslatorType varargTranslatorType = new TranslatorType() {
      void log(final String fqcn, final StackTraceElement location, final Level level, final Marker marker, final Message message, final Throwable thrown) {
         AsyncLogger.this.logWithVarargTranslator(fqcn, location, level, marker, message, thrown);
      }

      void log(final String fqcn, final Level level, final Marker marker, final Message message, final Throwable thrown) {
         AsyncLogger.this.logWithVarargTranslator(fqcn, level, marker, message, thrown);
      }
   };

   AsyncLogger(LoggerContext context, String name, MessageFactory messageFactory, AsyncLoggerDisruptor loggerDisruptor) {
      super(context, name, messageFactory);
      this.loggerDisruptor = loggerDisruptor;
      this.includeLocation = this.privateConfig.loggerConfig.isIncludeLocation();
      this.nanoClock = context.getConfiguration().getNanoClock();
   }

   protected void updateConfiguration(final Configuration newConfig) {
      this.nanoClock = newConfig.getNanoClock();
      this.includeLocation = newConfig.getLoggerConfig(this.name).isIncludeLocation();
      super.updateConfiguration(newConfig);
   }

   NanoClock getNanoClock() {
      return this.nanoClock;
   }

   private RingBufferLogEventTranslator getCachedTranslator() {
      RingBufferLogEventTranslator result = (RingBufferLogEventTranslator)this.threadLocalTranslator.get();
      if (result == null) {
         result = new RingBufferLogEventTranslator();
         this.threadLocalTranslator.set(result);
      }

      return result;
   }

   public void logMessage(final String fqcn, final Level level, final Marker marker, final Message message, final Throwable thrown) {
      this.getTranslatorType().log(fqcn, level, marker, message, thrown);
   }

   public void log(final Level level, final Marker marker, final String fqcn, final StackTraceElement location, final Message message, final Throwable throwable) {
      this.getTranslatorType().log(fqcn, location, level, marker, message, throwable);
   }

   private TranslatorType getTranslatorType() {
      return this.loggerDisruptor.isUseThreadLocals() ? this.threadLocalTranslatorType : this.varargTranslatorType;
   }

   private boolean isReused(final Message message) {
      return message instanceof ReusableMessage;
   }

   private void logWithThreadLocalTranslator(final String fqcn, final Level level, final Marker marker, final Message message, final Throwable thrown) {
      RingBufferLogEventTranslator translator = this.getCachedTranslator();
      this.initTranslator(translator, fqcn, level, marker, message, thrown);
      this.initTranslatorThreadValues(translator);
      this.publish(translator);
   }

   private void logWithThreadLocalTranslator(final String fqcn, final StackTraceElement location, final Level level, final Marker marker, final Message message, final Throwable thrown) {
      RingBufferLogEventTranslator translator = this.getCachedTranslator();
      this.initTranslator(translator, fqcn, location, level, marker, message, thrown);
      this.initTranslatorThreadValues(translator);
      this.publish(translator);
   }

   private void publish(final RingBufferLogEventTranslator translator) {
      if (!this.loggerDisruptor.tryPublish(translator)) {
         this.handleRingBufferFull(translator);
      }

   }

   private void handleRingBufferFull(final RingBufferLogEventTranslator translator) {
      if (AbstractLogger.getRecursionDepth() > 1) {
         AsyncQueueFullMessageUtil.logWarningToStatusLogger();
         this.logMessageInCurrentThread(translator.fqcn, translator.level, translator.marker, translator.message, translator.thrown);
         translator.clear();
      } else {
         EventRoute eventRoute = this.loggerDisruptor.getEventRoute(translator.level);
         switch (eventRoute) {
            case ENQUEUE:
               this.loggerDisruptor.enqueueLogMessageWhenQueueFull(translator);
               break;
            case SYNCHRONOUS:
               this.logMessageInCurrentThread(translator.fqcn, translator.level, translator.marker, translator.message, translator.thrown);
               translator.clear();
               break;
            case DISCARD:
               translator.clear();
               break;
            default:
               throw new IllegalStateException("Unknown EventRoute " + eventRoute);
         }

      }
   }

   private void initTranslator(final RingBufferLogEventTranslator translator, final String fqcn, final StackTraceElement location, final Level level, final Marker marker, final Message message, final Throwable thrown) {
      translator.setBasicValues(this, this.name, marker, fqcn, level, message, thrown, ThreadContext.getImmutableStack(), location, CLOCK, this.nanoClock);
   }

   private void initTranslator(final RingBufferLogEventTranslator translator, final String fqcn, final Level level, final Marker marker, final Message message, final Throwable thrown) {
      translator.setBasicValues(this, this.name, marker, fqcn, level, message, thrown, ThreadContext.getImmutableStack(), this.calcLocationIfRequested(fqcn), CLOCK, this.nanoClock);
   }

   private void initTranslatorThreadValues(final RingBufferLogEventTranslator translator) {
      if (THREAD_NAME_CACHING_STRATEGY == ThreadNameCachingStrategy.UNCACHED) {
         translator.updateThreadValues();
      }

   }

   private StackTraceElement calcLocationIfRequested(final String fqcn) {
      return this.includeLocation ? StackLocatorUtil.calcLocation(fqcn) : null;
   }

   private void logWithVarargTranslator(final String fqcn, final Level level, final Marker marker, final Message message, final Throwable thrown) {
      Disruptor<RingBufferLogEvent> disruptor = this.loggerDisruptor.getDisruptor();
      if (disruptor == null) {
         LOGGER.error("Ignoring log event after Log4j has been shut down.");
      } else {
         if (!this.isReused(message)) {
            InternalAsyncUtil.makeMessageImmutable(message);
         }

         StackTraceElement location;
         if (!disruptor.getRingBuffer().tryPublishEvent(this, new Object[]{this, location = this.calcLocationIfRequested(fqcn), fqcn, level, marker, message, thrown})) {
            this.handleRingBufferFull(location, fqcn, level, marker, message, thrown);
         }

      }
   }

   private void logWithVarargTranslator(final String fqcn, final StackTraceElement location, final Level level, final Marker marker, final Message message, final Throwable thrown) {
      Disruptor<RingBufferLogEvent> disruptor = this.loggerDisruptor.getDisruptor();
      if (disruptor == null) {
         LOGGER.error("Ignoring log event after Log4j has been shut down.");
      } else {
         if (!this.isReused(message)) {
            InternalAsyncUtil.makeMessageImmutable(message);
         }

         if (!disruptor.getRingBuffer().tryPublishEvent(this, new Object[]{this, location, fqcn, level, marker, message, thrown})) {
            this.handleRingBufferFull(location, fqcn, level, marker, message, thrown);
         }

      }
   }

   public void translateTo(final RingBufferLogEvent event, final long sequence, final Object... args) {
      AsyncLogger asyncLogger = (AsyncLogger)args[0];
      StackTraceElement location = (StackTraceElement)args[1];
      String fqcn = (String)args[2];
      Level level = (Level)args[3];
      Marker marker = (Marker)args[4];
      Message message = (Message)args[5];
      Throwable thrown = (Throwable)args[6];
      ThreadContext.ContextStack contextStack = ThreadContext.getImmutableStack();
      Thread currentThread = Thread.currentThread();
      String threadName = THREAD_NAME_CACHING_STRATEGY.getThreadName();
      event.setValues(asyncLogger, asyncLogger.getName(), marker, fqcn, level, message, thrown, CONTEXT_DATA_INJECTOR.injectContextData((List)null, (StringMap)event.getContextData()), contextStack, currentThread.getId(), threadName, currentThread.getPriority(), location, CLOCK, this.nanoClock);
   }

   void logMessageInCurrentThread(final String fqcn, final Level level, final Marker marker, final Message message, final Throwable thrown) {
      ReliabilityStrategy strategy = this.privateConfig.loggerConfig.getReliabilityStrategy();
      strategy.log(this, this.getName(), fqcn, marker, level, message, thrown);
   }

   private void handleRingBufferFull(final StackTraceElement location, final String fqcn, final Level level, final Marker marker, final Message msg, final Throwable thrown) {
      if (AbstractLogger.getRecursionDepth() > 1) {
         AsyncQueueFullMessageUtil.logWarningToStatusLogger();
         this.logMessageInCurrentThread(fqcn, level, marker, msg, thrown);
      } else {
         EventRoute eventRoute = this.loggerDisruptor.getEventRoute(level);
         switch (eventRoute) {
            case ENQUEUE:
               this.loggerDisruptor.enqueueLogMessageWhenQueueFull(this, this, location, fqcn, level, marker, msg, thrown);
               break;
            case SYNCHRONOUS:
               this.logMessageInCurrentThread(fqcn, level, marker, msg, thrown);
            case DISCARD:
               break;
            default:
               throw new IllegalStateException("Unknown EventRoute " + eventRoute);
         }

      }
   }

   public void actualAsyncLog(final RingBufferLogEvent event) {
      LoggerConfig privateConfigLoggerConfig = this.privateConfig.loggerConfig;
      List<Property> properties = privateConfigLoggerConfig.getPropertyList();
      if (properties != null) {
         this.onPropertiesPresent(event, properties);
      }

      privateConfigLoggerConfig.getReliabilityStrategy().log(this, event);
   }

   private void onPropertiesPresent(final RingBufferLogEvent event, final List properties) {
      StringMap contextData = getContextData(event);
      int i = 0;

      for(int size = properties.size(); i < size; ++i) {
         Property prop = (Property)properties.get(i);
         if (contextData.getValue(prop.getName()) == null) {
            String value = prop.evaluate(this.privateConfig.config.getStrSubstitutor());
            contextData.putValue(prop.getName(), value);
         }
      }

      event.setContextData(contextData);
   }

   private static StringMap getContextData(final RingBufferLogEvent event) {
      StringMap contextData = (StringMap)event.getContextData();
      if (contextData.isFrozen()) {
         StringMap temp = ContextDataFactory.createContextData();
         temp.putAll(contextData);
         return temp;
      } else {
         return contextData;
      }
   }

   AsyncLoggerDisruptor getAsyncLoggerDisruptor() {
      return this.loggerDisruptor;
   }

   abstract static class TranslatorType {
      abstract void log(final String fqcn, final StackTraceElement location, final Level level, final Marker marker, final Message message, final Throwable thrown);

      abstract void log(final String fqcn, final Level level, final Marker marker, final Message message, final Throwable thrown);
   }
}
