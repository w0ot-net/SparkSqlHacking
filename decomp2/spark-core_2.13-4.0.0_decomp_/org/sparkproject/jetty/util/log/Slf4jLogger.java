package org.sparkproject.jetty.util.log;

import org.slf4j.LoggerFactory;

/** @deprecated */
@Deprecated
class Slf4jLogger implements Logger {
   private final org.slf4j.Logger logger;

   Slf4jLogger(org.slf4j.Logger logger) {
      this.logger = logger;
   }

   public void debug(String format, Object... args) {
      this.logger.debug(format, args);
   }

   public void debug(String msg, long value) {
      this.logger.debug(msg, value);
   }

   public void debug(Throwable cause) {
      this.logger.debug(cause.getMessage(), cause);
   }

   public void debug(String msg, Throwable thrown) {
      this.logger.debug(msg, thrown);
   }

   public Logger getLogger(String name) {
      String var10002 = this.getName();
      return new Slf4jLogger(LoggerFactory.getLogger(var10002 + name));
   }

   public void ignore(Throwable cause) {
      this.logger.trace("IGNORED", cause);
   }

   public void info(String format, Object... args) {
      this.logger.info(format, args);
   }

   public void info(Throwable cause) {
      this.logger.info(cause.getMessage(), cause);
   }

   public void info(String msg, Throwable thrown) {
      this.logger.info(msg, thrown);
   }

   public boolean isDebugEnabled() {
      return this.logger.isDebugEnabled();
   }

   /** @deprecated */
   @Deprecated
   public void setDebugEnabled(boolean enabled) {
   }

   public void warn(Throwable cause) {
      this.logger.warn(cause.getMessage(), cause);
   }

   public void warn(String msg, Throwable cause) {
      this.logger.warn(msg, cause);
   }

   public String getName() {
      return this.logger.getName();
   }

   public void warn(String format, Object... args) {
      this.logger.warn(format, args);
   }
}
