package org.datanucleus.util;

public class NullLogger extends NucleusLogger {
   public NullLogger(String logName) {
   }

   public void debug(Object msg) {
   }

   public void debug(Object msg, Throwable thr) {
   }

   public void error(Object msg) {
   }

   public void error(Object msg, Throwable thr) {
   }

   public void fatal(Object msg) {
   }

   public void fatal(Object msg, Throwable thr) {
   }

   public void info(Object msg) {
   }

   public void info(Object msg, Throwable thr) {
   }

   public boolean isDebugEnabled() {
      return false;
   }

   public boolean isInfoEnabled() {
      return false;
   }

   public void warn(Object msg) {
   }

   public void warn(Object msg, Throwable thr) {
   }
}
