package org.datanucleus.util;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class JDK14Logger extends NucleusLogger {
   private final Logger logger;

   public JDK14Logger(String logName) {
      this.logger = Logger.getLogger(logName);
   }

   public void debug(Object msg) {
      this.log(Level.FINE, msg, (Throwable)null);
   }

   public void debug(Object msg, Throwable thr) {
      this.log(Level.FINE, msg, thr);
   }

   public void info(Object msg) {
      this.log(Level.INFO, msg, (Throwable)null);
   }

   public void info(Object msg, Throwable thr) {
      this.log(Level.INFO, msg, thr);
   }

   public void warn(Object msg) {
      this.log(Level.WARNING, msg, (Throwable)null);
   }

   public void warn(Object msg, Throwable thr) {
      this.log(Level.WARNING, msg, thr);
   }

   public void error(Object msg) {
      this.log(Level.SEVERE, msg, (Throwable)null);
   }

   public void error(Object msg, Throwable thr) {
      this.log(Level.SEVERE, msg, thr);
   }

   public void fatal(Object msg) {
      this.log(Level.SEVERE, msg, (Throwable)null);
   }

   public void fatal(Object msg, Throwable thr) {
      this.log(Level.SEVERE, msg, thr);
   }

   public boolean isDebugEnabled() {
      return this.logger.isLoggable(Level.FINE);
   }

   public boolean isInfoEnabled() {
      return this.logger.isLoggable(Level.INFO);
   }

   private void log(Level level, Object msg, Throwable thrown) {
      if (msg == null) {
         level = Level.SEVERE;
         msg = "Missing [msg] parameter";
      }

      if (this.logger.isLoggable(level)) {
         LogRecord result = new LogRecord(level, String.valueOf(msg));
         if (thrown != null) {
            result.setThrown(thrown);
         }

         StackTraceElement[] stacktrace = (new Throwable()).getStackTrace();

         for(int i = 0; i < stacktrace.length; ++i) {
            StackTraceElement element = stacktrace[i];
            if (!element.getClassName().equals(JDK14Logger.class.getName())) {
               result.setSourceClassName(element.getClassName());
               result.setSourceMethodName(element.getMethodName());
               break;
            }
         }

         this.logger.log(result);
      }

   }
}
