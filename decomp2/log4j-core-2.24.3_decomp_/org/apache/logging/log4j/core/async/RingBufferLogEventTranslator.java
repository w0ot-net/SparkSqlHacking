package org.apache.logging.log4j.core.async;

import com.lmax.disruptor.EventTranslator;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.ContextDataInjector;
import org.apache.logging.log4j.core.impl.ContextDataInjectorFactory;
import org.apache.logging.log4j.core.util.Clock;
import org.apache.logging.log4j.core.util.NanoClock;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.StringMap;

public class RingBufferLogEventTranslator implements EventTranslator {
   private static final ContextDataInjector INJECTOR = ContextDataInjectorFactory.createInjector();
   private AsyncLogger asyncLogger;
   String loggerName;
   protected Marker marker;
   protected String fqcn;
   protected Level level;
   protected Message message;
   protected Throwable thrown;
   private ThreadContext.ContextStack contextStack;
   private long threadId = Thread.currentThread().getId();
   private String threadName = Thread.currentThread().getName();
   private int threadPriority = Thread.currentThread().getPriority();
   private StackTraceElement location;
   private Clock clock;
   private NanoClock nanoClock;

   public void translateTo(final RingBufferLogEvent event, final long sequence) {
      try {
         ReadOnlyStringMap contextData = event.getContextData();
         event.setValues(this.asyncLogger, this.loggerName, this.marker, this.fqcn, this.level, this.message, this.thrown, INJECTOR.injectContextData((List)null, contextData instanceof StringMap ? (StringMap)contextData : null), this.contextStack, this.threadId, this.threadName, this.threadPriority, this.location, this.clock, this.nanoClock);
      } finally {
         this.clear();
      }

   }

   void clear() {
      this.setBasicValues((AsyncLogger)null, (String)null, (Marker)null, (String)null, (Level)null, (Message)null, (Throwable)null, (ThreadContext.ContextStack)null, (StackTraceElement)null, (Clock)null, (NanoClock)null);
   }

   public void setBasicValues(final AsyncLogger anAsyncLogger, final String aLoggerName, final Marker aMarker, final String theFqcn, final Level aLevel, final Message msg, final Throwable aThrowable, final ThreadContext.ContextStack aContextStack, final StackTraceElement aLocation, final Clock aClock, final NanoClock aNanoClock) {
      this.asyncLogger = anAsyncLogger;
      this.loggerName = aLoggerName;
      this.marker = aMarker;
      this.fqcn = theFqcn;
      this.level = aLevel;
      this.message = msg;
      this.thrown = aThrowable;
      this.contextStack = aContextStack;
      this.location = aLocation;
      this.clock = aClock;
      this.nanoClock = aNanoClock;
   }

   public void updateThreadValues() {
      Thread currentThread = Thread.currentThread();
      this.threadId = currentThread.getId();
      this.threadName = currentThread.getName();
      this.threadPriority = currentThread.getPriority();
   }
}
