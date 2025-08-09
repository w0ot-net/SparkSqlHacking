package io.vertx.core.logging;

import io.vertx.core.spi.logging.LogDelegate;
import io.vertx.core.spi.logging.LogDelegateFactory;
import org.apache.logging.log4j.LogManager;

public class Log4j2LogDelegateFactory implements LogDelegateFactory {
   public boolean isAvailable() {
      return LogManager.getLogger(Log4j2LogDelegateFactory.class) != null;
   }

   public LogDelegate createDelegate(String name) {
      return new Log4j2LogDelegate(name);
   }
}
