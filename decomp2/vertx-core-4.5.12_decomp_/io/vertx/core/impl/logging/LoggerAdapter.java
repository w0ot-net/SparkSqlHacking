package io.vertx.core.impl.logging;

import io.vertx.core.spi.logging.LogDelegate;

public final class LoggerAdapter implements Logger {
   private final LogDelegate adapted;

   LoggerAdapter(LogDelegate adapted) {
      this.adapted = adapted;
   }

   public boolean isTraceEnabled() {
      return this.adapted.isTraceEnabled();
   }

   public void trace(Object message) {
      this.adapted.trace(message);
   }

   public void trace(Object message, Throwable t) {
      this.adapted.trace(message, t);
   }

   public boolean isDebugEnabled() {
      return this.adapted.isDebugEnabled();
   }

   public void debug(Object message) {
      this.adapted.debug(message);
   }

   public void debug(Object message, Throwable t) {
      this.adapted.debug(message, t);
   }

   public boolean isInfoEnabled() {
      return this.adapted.isInfoEnabled();
   }

   public void info(Object message) {
      this.adapted.info(message);
   }

   public void info(Object message, Throwable t) {
      this.adapted.info(message, t);
   }

   public boolean isWarnEnabled() {
      return this.adapted.isWarnEnabled();
   }

   public void warn(Object message) {
      this.adapted.warn(message);
   }

   public void warn(Object message, Throwable t) {
      this.adapted.warn(message, t);
   }

   public void error(Object message) {
      this.adapted.error(message);
   }

   public void error(Object message, Throwable t) {
      this.adapted.error(message, t);
   }
}
