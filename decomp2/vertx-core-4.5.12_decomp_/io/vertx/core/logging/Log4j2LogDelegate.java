package io.vertx.core.logging;

import io.vertx.core.spi.logging.LogDelegate;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.FormattedMessage;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.spi.ExtendedLogger;

public class Log4j2LogDelegate implements LogDelegate {
   final ExtendedLogger logger;
   static final String FQCN = Logger.class.getCanonicalName();

   Log4j2LogDelegate(String name) {
      this.logger = (ExtendedLogger)LogManager.getLogger(name);
   }

   public boolean isWarnEnabled() {
      return this.logger.isWarnEnabled();
   }

   public boolean isInfoEnabled() {
      return this.logger.isInfoEnabled();
   }

   public boolean isDebugEnabled() {
      return this.logger.isDebugEnabled();
   }

   public boolean isTraceEnabled() {
      return this.logger.isTraceEnabled();
   }

   public void fatal(Object message) {
      this.log(Level.FATAL, message);
   }

   public void fatal(Object message, Throwable t) {
      this.log(Level.FATAL, message, t);
   }

   public void error(Object message) {
      this.log(Level.ERROR, message);
   }

   public void error(Object message, Object... params) {
      this.log(Level.ERROR, message.toString(), params);
   }

   public void error(Object message, Throwable t) {
      this.log(Level.ERROR, message, t);
   }

   public void error(Object message, Throwable t, Object... params) {
      this.log(Level.ERROR, message.toString(), t, params);
   }

   public void warn(Object message) {
      this.log(Level.WARN, message);
   }

   public void warn(Object message, Object... params) {
      this.log(Level.WARN, message.toString(), params);
   }

   public void warn(Object message, Throwable t) {
      this.log(Level.WARN, message, t);
   }

   public void warn(Object message, Throwable t, Object... params) {
      this.log(Level.WARN, message.toString(), t, params);
   }

   public void info(Object message) {
      this.log(Level.INFO, message);
   }

   public void info(Object message, Object... params) {
      this.log(Level.INFO, message.toString(), params);
   }

   public void info(Object message, Throwable t) {
      this.log(Level.INFO, message, t);
   }

   public void info(Object message, Throwable t, Object... params) {
      this.log(Level.INFO, message.toString(), t, params);
   }

   public void debug(Object message) {
      this.log(Level.DEBUG, message);
   }

   public void debug(Object message, Object... params) {
      this.log(Level.DEBUG, message.toString(), params);
   }

   public void debug(Object message, Throwable t) {
      this.log(Level.DEBUG, message, t);
   }

   public void debug(Object message, Throwable t, Object... params) {
      this.log(Level.DEBUG, message.toString(), t, params);
   }

   public void trace(Object message) {
      this.log(Level.TRACE, message);
   }

   public void trace(Object message, Object... params) {
      this.log(Level.TRACE, message.toString(), params);
   }

   public void trace(Object message, Throwable t) {
      this.log(Level.TRACE, (Object)message.toString(), (Throwable)t);
   }

   public void trace(Object message, Throwable t, Object... params) {
      this.log(Level.TRACE, message.toString(), t, params);
   }

   private void log(Level level, Object message) {
      this.log(level, (Object)message, (Throwable)null);
   }

   private void log(Level level, Object message, Throwable t) {
      if (message instanceof Message) {
         this.logger.logIfEnabled(FQCN, level, (Marker)null, (Message)message, t);
      } else {
         this.logger.logIfEnabled(FQCN, level, (Marker)null, message, t);
      }

   }

   private void log(Level level, String message, Object... params) {
      this.logger.logIfEnabled(FQCN, level, (Marker)null, message, params);
   }

   private void log(Level level, String message, Throwable t, Object... params) {
      this.logger.logIfEnabled(FQCN, level, (Marker)null, new FormattedMessage(message, params), t);
   }

   public Object unwrap() {
      return this.logger;
   }
}
