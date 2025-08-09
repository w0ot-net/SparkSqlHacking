package org.apache.logging.log4j.core.async;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AsyncAppender;
import org.apache.logging.log4j.message.Message;

public enum EventRoute {
   ENQUEUE {
      public void logMessage(final AsyncLogger asyncLogger, final String fqcn, final Level level, final Marker marker, final Message message, final Throwable thrown) {
      }

      public void logMessage(final AsyncLoggerConfig asyncLoggerConfig, final LogEvent event) {
         asyncLoggerConfig.logInBackgroundThread(event);
      }

      public void logMessage(final AsyncAppender asyncAppender, final LogEvent logEvent) {
         asyncAppender.logMessageInBackgroundThread(logEvent);
      }
   },
   SYNCHRONOUS {
      public void logMessage(final AsyncLogger asyncLogger, final String fqcn, final Level level, final Marker marker, final Message message, final Throwable thrown) {
      }

      public void logMessage(final AsyncLoggerConfig asyncLoggerConfig, final LogEvent event) {
         asyncLoggerConfig.logToAsyncLoggerConfigsOnCurrentThread(event);
      }

      public void logMessage(final AsyncAppender asyncAppender, final LogEvent logEvent) {
         asyncAppender.logMessageInCurrentThread(logEvent);
      }
   },
   DISCARD {
      public void logMessage(final AsyncLogger asyncLogger, final String fqcn, final Level level, final Marker marker, final Message message, final Throwable thrown) {
      }

      public void logMessage(final AsyncLoggerConfig asyncLoggerConfig, final LogEvent event) {
      }

      public void logMessage(final AsyncAppender asyncAppender, final LogEvent coreEvent) {
      }
   };

   private EventRoute() {
   }

   public abstract void logMessage(final AsyncLogger asyncLogger, final String fqcn, final Level level, final Marker marker, final Message message, final Throwable thrown);

   public abstract void logMessage(final AsyncLoggerConfig asyncLoggerConfig, final LogEvent event);

   public abstract void logMessage(final AsyncAppender asyncAppender, final LogEvent coreEvent);

   // $FF: synthetic method
   private static EventRoute[] $values() {
      return new EventRoute[]{ENQUEUE, SYNCHRONOUS, DISCARD};
   }
}
