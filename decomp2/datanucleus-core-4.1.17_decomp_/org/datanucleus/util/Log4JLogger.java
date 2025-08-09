package org.datanucleus.util;

import org.apache.log4j.Logger;

public class Log4JLogger extends NucleusLogger {
   private Logger logger = null;

   public Log4JLogger(String logName) {
      this.logger = Logger.getLogger(logName);
   }

   public void debug(Object msg) {
      this.logger.debug(msg);
   }

   public void debug(Object msg, Throwable thr) {
      this.logger.debug(msg, thr);
   }

   public void info(Object msg) {
      this.logger.info(msg);
   }

   public void info(Object msg, Throwable thr) {
      this.logger.info(msg, thr);
   }

   public void warn(Object msg) {
      this.logger.warn(msg);
   }

   public void warn(Object msg, Throwable thr) {
      this.logger.warn(msg, thr);
   }

   public void error(Object msg) {
      this.logger.error(msg);
   }

   public void error(Object msg, Throwable thr) {
      this.logger.error(msg, thr);
   }

   public void fatal(Object msg) {
      this.logger.fatal(msg);
   }

   public void fatal(Object msg, Throwable thr) {
      this.logger.fatal(msg, thr);
   }

   public boolean isDebugEnabled() {
      return this.logger.isDebugEnabled();
   }

   public boolean isInfoEnabled() {
      return this.logger.isInfoEnabled();
   }
}
