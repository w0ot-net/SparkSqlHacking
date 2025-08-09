package org.slf4j.event;

import java.util.Queue;
import org.slf4j.Marker;
import org.slf4j.helpers.LegacyAbstractLogger;
import org.slf4j.helpers.SubstituteLogger;

public class EventRecordingLogger extends LegacyAbstractLogger {
   private static final long serialVersionUID = -176083308134819629L;
   String name;
   SubstituteLogger logger;
   Queue eventQueue;
   static final boolean RECORD_ALL_EVENTS = true;

   public EventRecordingLogger(SubstituteLogger logger, Queue eventQueue) {
      this.logger = logger;
      this.name = logger.getName();
      this.eventQueue = eventQueue;
   }

   public String getName() {
      return this.name;
   }

   public boolean isTraceEnabled() {
      return true;
   }

   public boolean isDebugEnabled() {
      return true;
   }

   public boolean isInfoEnabled() {
      return true;
   }

   public boolean isWarnEnabled() {
      return true;
   }

   public boolean isErrorEnabled() {
      return true;
   }

   protected void handleNormalizedLoggingCall(Level level, Marker marker, String msg, Object[] args, Throwable throwable) {
      SubstituteLoggingEvent loggingEvent = new SubstituteLoggingEvent();
      loggingEvent.setTimeStamp(System.currentTimeMillis());
      loggingEvent.setLevel(level);
      loggingEvent.setLogger(this.logger);
      loggingEvent.setLoggerName(this.name);
      if (marker != null) {
         loggingEvent.addMarker(marker);
      }

      loggingEvent.setMessage(msg);
      loggingEvent.setThreadName(Thread.currentThread().getName());
      loggingEvent.setArgumentArray(args);
      loggingEvent.setThrowable(throwable);
      this.eventQueue.add(loggingEvent);
   }

   protected String getFullyQualifiedCallerName() {
      return null;
   }
}
