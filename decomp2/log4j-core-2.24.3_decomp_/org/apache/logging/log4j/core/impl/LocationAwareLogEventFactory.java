package org.apache.logging.log4j.core.impl;

import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.message.Message;

public interface LocationAwareLogEventFactory {
   LogEvent createEvent(String loggerName, Marker marker, String fqcn, StackTraceElement location, Level level, Message data, List properties, Throwable t);
}
