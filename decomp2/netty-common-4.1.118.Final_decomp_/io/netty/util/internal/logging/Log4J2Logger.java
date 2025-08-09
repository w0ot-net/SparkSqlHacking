package io.netty.util.internal.logging;

import java.security.AccessController;
import java.security.PrivilegedAction;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.apache.logging.log4j.spi.ExtendedLoggerWrapper;

class Log4J2Logger extends ExtendedLoggerWrapper implements InternalLogger {
   private static final long serialVersionUID = 5485418394879791397L;
   private static final boolean VARARGS_ONLY = (Boolean)AccessController.doPrivileged(new PrivilegedAction() {
      public Boolean run() {
         try {
            Logger.class.getMethod("debug", String.class, Object.class);
            return false;
         } catch (NoSuchMethodException var2) {
            return true;
         } catch (SecurityException var3) {
            return false;
         }
      }
   });

   Log4J2Logger(Logger logger) {
      super((ExtendedLogger)logger, logger.getName(), logger.getMessageFactory());
      if (VARARGS_ONLY) {
         throw new UnsupportedOperationException("Log4J2 version mismatch");
      }
   }

   public String name() {
      return this.getName();
   }

   public void trace(Throwable t) {
      this.log((Level)Level.TRACE, "Unexpected exception:", (Throwable)t);
   }

   public void debug(Throwable t) {
      this.log((Level)Level.DEBUG, "Unexpected exception:", (Throwable)t);
   }

   public void info(Throwable t) {
      this.log((Level)Level.INFO, "Unexpected exception:", (Throwable)t);
   }

   public void warn(Throwable t) {
      this.log((Level)Level.WARN, "Unexpected exception:", (Throwable)t);
   }

   public void error(Throwable t) {
      this.log((Level)Level.ERROR, "Unexpected exception:", (Throwable)t);
   }

   public boolean isEnabled(InternalLogLevel level) {
      return this.isEnabled(toLevel(level));
   }

   public void log(InternalLogLevel level, String msg) {
      this.log((Level)toLevel(level), (String)msg);
   }

   public void log(InternalLogLevel level, String format, Object arg) {
      this.log((Level)toLevel(level), format, (Object)arg);
   }

   public void log(InternalLogLevel level, String format, Object argA, Object argB) {
      this.log(toLevel(level), format, argA, argB);
   }

   public void log(InternalLogLevel level, String format, Object... arguments) {
      this.log((Level)toLevel(level), format, (Object[])arguments);
   }

   public void log(InternalLogLevel level, String msg, Throwable t) {
      this.log((Level)toLevel(level), msg, (Throwable)t);
   }

   public void log(InternalLogLevel level, Throwable t) {
      this.log((Level)toLevel(level), "Unexpected exception:", (Throwable)t);
   }

   private static Level toLevel(InternalLogLevel level) {
      switch (level) {
         case INFO:
            return Level.INFO;
         case DEBUG:
            return Level.DEBUG;
         case WARN:
            return Level.WARN;
         case ERROR:
            return Level.ERROR;
         case TRACE:
            return Level.TRACE;
         default:
            throw new Error();
      }
   }
}
