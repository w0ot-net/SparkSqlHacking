package org.apache.logging.log4j.core.impl;

import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.ContextDataInjector;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.async.ThreadNameCachingStrategy;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.util.Clock;
import org.apache.logging.log4j.core.util.ClockFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.StringMap;

public class ReusableLogEventFactory implements LogEventFactory, LocationAwareLogEventFactory {
   private static final ThreadNameCachingStrategy THREAD_NAME_CACHING_STRATEGY = ThreadNameCachingStrategy.create();
   private static final Clock CLOCK = ClockFactory.getClock();
   private static final ThreadLocal mutableLogEventThreadLocal = new ThreadLocal();
   private final ContextDataInjector injector = ContextDataInjectorFactory.createInjector();

   public LogEvent createEvent(final String loggerName, final Marker marker, final String fqcn, final Level level, final Message message, final List properties, final Throwable t) {
      return this.createEvent(loggerName, marker, fqcn, (StackTraceElement)null, level, message, properties, t);
   }

   public LogEvent createEvent(final String loggerName, final Marker marker, final String fqcn, final StackTraceElement location, final Level level, final Message message, final List properties, final Throwable t) {
      MutableLogEvent result = getOrCreateMutableLogEvent();
      result.reserved = true;
      result.setLoggerName(loggerName);
      result.setMarker(marker);
      result.setLoggerFqcn(fqcn);
      result.setLevel(level == null ? Level.OFF : level);
      result.setMessage(message);
      result.initTime(CLOCK, Log4jLogEvent.getNanoClock());
      result.setThrown(t);
      result.setSource(location);
      result.setContextData(this.injector.injectContextData(properties, (StringMap)result.getContextData()));
      result.setContextStack((ThreadContext.ContextStack)(ThreadContext.getDepth() == 0 ? ThreadContext.EMPTY_STACK : ThreadContext.cloneStack()));
      if (THREAD_NAME_CACHING_STRATEGY == ThreadNameCachingStrategy.UNCACHED) {
         result.setThreadName(Thread.currentThread().getName());
         result.setThreadPriority(Thread.currentThread().getPriority());
      }

      return result;
   }

   private static MutableLogEvent getOrCreateMutableLogEvent() {
      MutableLogEvent result = (MutableLogEvent)mutableLogEventThreadLocal.get();
      return result != null && !result.reserved ? result : createInstance(result);
   }

   private static MutableLogEvent createInstance(final MutableLogEvent existing) {
      MutableLogEvent result = new MutableLogEvent();
      result.setThreadId(Thread.currentThread().getId());
      result.setThreadName(Thread.currentThread().getName());
      result.setThreadPriority(Thread.currentThread().getPriority());
      if (existing == null) {
         mutableLogEventThreadLocal.set(result);
      }

      return result;
   }

   public static void release(final LogEvent logEvent) {
      if (logEvent instanceof MutableLogEvent) {
         MutableLogEvent mutableLogEvent = (MutableLogEvent)logEvent;
         mutableLogEvent.clear();
         mutableLogEvent.reserved = false;
      }

   }
}
