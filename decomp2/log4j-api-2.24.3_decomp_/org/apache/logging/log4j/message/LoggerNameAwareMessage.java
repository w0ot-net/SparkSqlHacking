package org.apache.logging.log4j.message;

public interface LoggerNameAwareMessage {
   void setLoggerName(String name);

   String getLoggerName();
}
