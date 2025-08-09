package io.vertx.core.logging;

import io.vertx.core.spi.logging.LogDelegate;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

public class SLF4JLogDelegate implements LogDelegate {
   private static final Object[] EMPTY_PARAMETERS = new Object[0];
   private static final String FQCN = Logger.class.getCanonicalName();
   private final org.slf4j.Logger logger;

   SLF4JLogDelegate(String name) {
      this.logger = org.slf4j.LoggerFactory.getLogger(name);
   }

   public SLF4JLogDelegate(Object logger) {
      this.logger = (org.slf4j.Logger)logger;
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
      this.log(40, message);
   }

   public void fatal(Object message, Throwable t) {
      this.log(40, message, t);
   }

   public void error(Object message) {
      this.log(40, message);
   }

   public void error(Object message, Object... params) {
      this.log(40, message, (Throwable)null, params);
   }

   public void error(Object message, Throwable t) {
      this.log(40, message, t);
   }

   public void error(Object message, Throwable t, Object... params) {
      this.log(40, message, t, params);
   }

   public void warn(Object message) {
      this.log(30, message);
   }

   public void warn(Object message, Object... params) {
      this.log(30, message, (Throwable)null, params);
   }

   public void warn(Object message, Throwable t) {
      this.log(30, message, t);
   }

   public void warn(Object message, Throwable t, Object... params) {
      this.log(30, message, t, params);
   }

   public void info(Object message) {
      this.log(20, message);
   }

   public void info(Object message, Object... params) {
      this.log(20, message, (Throwable)null, params);
   }

   public void info(Object message, Throwable t) {
      this.log(20, message, t);
   }

   public void info(Object message, Throwable t, Object... params) {
      this.log(20, message, t, params);
   }

   public void debug(Object message) {
      this.log(10, message);
   }

   public void debug(Object message, Object... params) {
      this.log(10, message, (Throwable)null, params);
   }

   public void debug(Object message, Throwable t) {
      this.log(10, message, t);
   }

   public void debug(Object message, Throwable t, Object... params) {
      this.log(10, message, t, params);
   }

   public void trace(Object message) {
      this.log(0, message);
   }

   public void trace(Object message, Object... params) {
      this.log(0, message, (Throwable)null, params);
   }

   public void trace(Object message, Throwable t) {
      this.log(0, message, t);
   }

   public void trace(Object message, Throwable t, Object... params) {
      this.log(0, message, t, params);
   }

   private void log(int level, Object message) {
      this.log(level, message, (Throwable)null);
   }

   private void log(int level, Object message, Throwable t) {
      this.log(level, message, t, (Object[])null);
   }

   private void log(int level, Object message, Throwable t, Object... params) {
      String msg = message == null ? "NULL" : message.toString();
      Object[] parameters;
      if (params == null) {
         if (t == null) {
            parameters = EMPTY_PARAMETERS;
         } else {
            parameters = new Object[]{t};
         }
      } else if (t == null) {
         parameters = params;
      } else {
         parameters = new Object[params.length + 1];
         System.arraycopy(params, 0, parameters, 0, params.length);
         parameters[params.length] = t;
      }

      if (this.logger instanceof LocationAwareLogger) {
         if (level == 0 && this.logger.isTraceEnabled() || level == 10 && this.logger.isDebugEnabled() || level == 20 && this.logger.isInfoEnabled() || level == 30 && this.logger.isWarnEnabled() || level == 40 && this.logger.isErrorEnabled()) {
            LocationAwareLogger l = (LocationAwareLogger)this.logger;
            FormattingTuple ft = MessageFormatter.arrayFormat(msg, parameters);
            l.log((Marker)null, FQCN, level, ft.getMessage(), (Object[])null, ft.getThrowable());
         }
      } else {
         switch (level) {
            case 0:
               this.logger.trace(msg, parameters);
               break;
            case 10:
               this.logger.debug(msg, parameters);
               break;
            case 20:
               this.logger.info(msg, parameters);
               break;
            case 30:
               this.logger.warn(msg, parameters);
               break;
            case 40:
               this.logger.error(msg, parameters);
               break;
            default:
               throw new IllegalArgumentException("Unknown log level " + level);
         }
      }

   }

   public Object unwrap() {
      return this.logger;
   }
}
