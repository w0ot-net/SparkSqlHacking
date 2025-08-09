package org.apache.logging.log4j.core.impl;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.async.InternalAsyncUtil;
import org.apache.logging.log4j.core.time.Instant;
import org.apache.logging.log4j.core.time.MutableInstant;
import org.apache.logging.log4j.core.util.Clock;
import org.apache.logging.log4j.core.util.Constants;
import org.apache.logging.log4j.core.util.NanoClock;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.ParameterConsumer;
import org.apache.logging.log4j.message.ParameterVisitable;
import org.apache.logging.log4j.message.ReusableMessage;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.message.TimestampMessage;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.StackLocatorUtil;
import org.apache.logging.log4j.util.StringBuilders;
import org.apache.logging.log4j.util.StringMap;

public class MutableLogEvent implements LogEvent, ReusableMessage, ParameterVisitable {
   private static final Message EMPTY = new SimpleMessage("");
   private int threadPriority;
   private long threadId;
   private final MutableInstant instant;
   private long nanoTime;
   private short parameterCount;
   private boolean includeLocation;
   private boolean endOfBatch;
   private Level level;
   private String threadName;
   private String loggerName;
   private Message message;
   private String messageFormat;
   private StringBuilder messageText;
   private Object[] parameters;
   private Throwable thrown;
   private ThrowableProxy thrownProxy;
   private StringMap contextData;
   private Marker marker;
   private String loggerFqcn;
   StackTraceElement source;
   private ThreadContext.ContextStack contextStack;
   transient boolean reserved;

   public MutableLogEvent() {
      this((StringBuilder)null, (Object[])null);
   }

   public MutableLogEvent(final StringBuilder msgText, final Object[] replacementParameters) {
      this.instant = new MutableInstant();
      this.endOfBatch = false;
      this.contextData = ContextDataFactory.createContextData();
      this.reserved = false;
      this.messageText = msgText;
      this.parameters = replacementParameters;
   }

   public Log4jLogEvent toImmutable() {
      return this.createMemento();
   }

   public void initFrom(final LogEvent event) {
      this.loggerFqcn = event.getLoggerFqcn();
      this.marker = event.getMarker();
      this.level = event.getLevel();
      this.loggerName = event.getLoggerName();
      this.thrown = event.getThrown();
      this.thrownProxy = event.getThrownProxy();
      this.instant.initFrom(event.getInstant());
      this.contextData.putAll(event.getContextData());
      this.contextStack = event.getContextStack();
      this.source = event.isIncludeLocation() ? event.getSource() : null;
      this.threadId = event.getThreadId();
      this.threadName = event.getThreadName();
      this.threadPriority = event.getThreadPriority();
      this.endOfBatch = event.isEndOfBatch();
      this.includeLocation = event.isIncludeLocation();
      this.nanoTime = event.getNanoTime();
      this.setMessage(event.getMessage());
   }

   public void clear() {
      this.loggerFqcn = null;
      this.marker = null;
      this.level = null;
      this.loggerName = null;
      this.message = null;
      this.messageFormat = null;
      this.thrown = null;
      this.thrownProxy = null;
      this.source = null;
      if (this.contextData != null) {
         if (this.contextData.isFrozen()) {
            this.contextData = null;
         } else {
            this.contextData.clear();
         }
      }

      this.contextStack = null;
      StringBuilders.trimToMaxSize(this.messageText, Constants.MAX_REUSABLE_MESSAGE_SIZE);
      if (this.parameters != null) {
         Arrays.fill(this.parameters, (Object)null);
      }

   }

   public String getLoggerFqcn() {
      return this.loggerFqcn;
   }

   public void setLoggerFqcn(final String loggerFqcn) {
      this.loggerFqcn = loggerFqcn;
   }

   public Marker getMarker() {
      return this.marker;
   }

   public void setMarker(final Marker marker) {
      this.marker = marker;
   }

   public Level getLevel() {
      if (this.level == null) {
         this.level = Level.OFF;
      }

      return this.level;
   }

   public void setLevel(final Level level) {
      this.level = level;
   }

   public String getLoggerName() {
      return this.loggerName;
   }

   public void setLoggerName(final String loggerName) {
      this.loggerName = loggerName;
   }

   public Message getMessage() {
      if (this.message == null) {
         return (Message)(this.messageText == null ? EMPTY : this);
      } else {
         return this.message;
      }
   }

   public void setMessage(final Message msg) {
      if (msg instanceof ReusableMessage) {
         ReusableMessage reusable = (ReusableMessage)msg;
         reusable.formatTo(this.getMessageTextForWriting());
         this.messageFormat = msg.getFormat();
         this.parameters = reusable.swapParameters(this.parameters == null ? new Object[10] : this.parameters);
         this.parameterCount = reusable.getParameterCount();
      } else {
         this.message = InternalAsyncUtil.makeMessageImmutable(msg);
      }

   }

   private StringBuilder getMessageTextForWriting() {
      if (this.messageText == null) {
         this.messageText = new StringBuilder(Constants.INITIAL_REUSABLE_MESSAGE_SIZE);
      }

      this.messageText.setLength(0);
      return this.messageText;
   }

   public String getFormattedMessage() {
      return this.messageText.toString();
   }

   public String getFormat() {
      return this.messageFormat;
   }

   public Object[] getParameters() {
      return this.parameters == null ? null : Arrays.copyOf(this.parameters, this.parameterCount);
   }

   public void forEachParameter(final ParameterConsumer action, final Object state) {
      if (this.parameters != null) {
         for(short i = 0; i < this.parameterCount; ++i) {
            action.accept(this.parameters[i], i, state);
         }
      }

   }

   public Throwable getThrowable() {
      return this.getThrown();
   }

   public void formatTo(final StringBuilder buffer) {
      buffer.append(this.messageText);
   }

   public Object[] swapParameters(final Object[] emptyReplacement) {
      Object[] result = this.parameters;
      this.parameters = emptyReplacement;
      return result;
   }

   public short getParameterCount() {
      return this.parameterCount;
   }

   public Message memento() {
      if (this.message == null) {
         this.message = new MementoMessage(String.valueOf(this.messageText), this.messageFormat, this.getParameters());
      }

      return this.message;
   }

   public Throwable getThrown() {
      return this.thrown;
   }

   public void setThrown(final Throwable thrown) {
      this.thrown = thrown;
   }

   void initTime(final Clock clock, final NanoClock nanoClock) {
      if (this.message instanceof TimestampMessage) {
         this.instant.initFromEpochMilli(((TimestampMessage)this.message).getTimestamp(), 0);
      } else {
         this.instant.initFrom(clock);
      }

      this.nanoTime = nanoClock.nanoTime();
   }

   public long getTimeMillis() {
      return this.instant.getEpochMillisecond();
   }

   public void setTimeMillis(final long timeMillis) {
      this.instant.initFromEpochMilli(timeMillis, 0);
   }

   public Instant getInstant() {
      return this.instant;
   }

   public ThrowableProxy getThrownProxy() {
      if (this.thrownProxy == null && this.thrown != null) {
         this.thrownProxy = new ThrowableProxy(this.thrown);
      }

      return this.thrownProxy;
   }

   public void setSource(StackTraceElement source) {
      this.source = source;
   }

   public StackTraceElement getSource() {
      if (this.source != null) {
         return this.source;
      } else if (this.loggerFqcn != null && this.includeLocation) {
         this.source = StackLocatorUtil.calcLocation(this.loggerFqcn);
         return this.source;
      } else {
         return null;
      }
   }

   public ReadOnlyStringMap getContextData() {
      return this.contextData;
   }

   public Map getContextMap() {
      return this.contextData.toMap();
   }

   public void setContextData(final StringMap mutableContextData) {
      this.contextData = mutableContextData;
   }

   public ThreadContext.ContextStack getContextStack() {
      return this.contextStack;
   }

   public void setContextStack(final ThreadContext.ContextStack contextStack) {
      this.contextStack = contextStack;
   }

   public long getThreadId() {
      return this.threadId;
   }

   public void setThreadId(final long threadId) {
      this.threadId = threadId;
   }

   public String getThreadName() {
      return this.threadName;
   }

   public void setThreadName(final String threadName) {
      this.threadName = threadName;
   }

   public int getThreadPriority() {
      return this.threadPriority;
   }

   public void setThreadPriority(final int threadPriority) {
      this.threadPriority = threadPriority;
   }

   public boolean isIncludeLocation() {
      return this.includeLocation;
   }

   public void setIncludeLocation(final boolean includeLocation) {
      this.includeLocation = includeLocation;
   }

   public boolean isEndOfBatch() {
      return this.endOfBatch;
   }

   public void setEndOfBatch(final boolean endOfBatch) {
      this.endOfBatch = endOfBatch;
   }

   public long getNanoTime() {
      return this.nanoTime;
   }

   public void setNanoTime(final long nanoTime) {
      this.nanoTime = nanoTime;
   }

   protected Object writeReplace() {
      return new Log4jLogEvent.LogEventProxy(this, this.includeLocation);
   }

   private void readObject(final ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Proxy required");
   }

   public Log4jLogEvent createMemento() {
      return Log4jLogEvent.deserialize(Log4jLogEvent.serialize((LogEvent)this, this.includeLocation));
   }

   public void initializeBuilder(final Log4jLogEvent.Builder builder) {
      builder.setContextData(this.contextData).setContextStack(this.contextStack).setEndOfBatch(this.endOfBatch).setIncludeLocation(this.includeLocation).setLevel(this.getLevel()).setLoggerFqcn(this.loggerFqcn).setLoggerName(this.loggerName).setMarker(this.marker).setMessage(this.memento()).setNanoTime(this.nanoTime).setSource(this.source).setThreadId(this.threadId).setThreadName(this.threadName).setThreadPriority(this.threadPriority).setThrown(this.getThrown()).setThrownProxy(this.thrownProxy).setInstant(this.instant);
   }
}
