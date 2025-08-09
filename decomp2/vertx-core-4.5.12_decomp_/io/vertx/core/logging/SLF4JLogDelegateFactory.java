package io.vertx.core.logging;

import io.vertx.core.spi.logging.LogDelegate;
import io.vertx.core.spi.logging.LogDelegateFactory;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.slf4j.ILoggerFactory;
import org.slf4j.helpers.NOPLoggerFactory;

public class SLF4JLogDelegateFactory implements LogDelegateFactory {
   public boolean isAvailable() {
      ILoggerFactory fact = org.slf4j.LoggerFactory.getILoggerFactory();
      return !(fact instanceof NOPLoggerFactory);
   }

   public LogDelegate createDelegate(String clazz) {
      return new SLF4JLogDelegate(clazz);
   }

   static {
      PrintStream err = System.err;

      try {
         System.setErr(new PrintStream(new ByteArrayOutputStream()));
         org.slf4j.LoggerFactory.getILoggerFactory();
      } finally {
         System.setErr(err);
      }

   }
}
