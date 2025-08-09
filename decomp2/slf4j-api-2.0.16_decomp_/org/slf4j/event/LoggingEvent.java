package org.slf4j.event;

import java.util.List;

public interface LoggingEvent {
   Level getLevel();

   String getLoggerName();

   String getMessage();

   List getArguments();

   Object[] getArgumentArray();

   List getMarkers();

   List getKeyValuePairs();

   Throwable getThrowable();

   long getTimeStamp();

   String getThreadName();

   default String getCallerBoundary() {
      return null;
   }
}
