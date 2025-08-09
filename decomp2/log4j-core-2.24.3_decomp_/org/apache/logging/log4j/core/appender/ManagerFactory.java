package org.apache.logging.log4j.core.appender;

public interface ManagerFactory {
   Object createManager(String name, Object data);
}
