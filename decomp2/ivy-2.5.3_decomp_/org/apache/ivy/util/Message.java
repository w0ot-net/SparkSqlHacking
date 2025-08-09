package org.apache.ivy.util;

import java.util.List;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.IvyContext;

public final class Message {
   public static final int MSG_ERR = 0;
   public static final int MSG_WARN = 1;
   public static final int MSG_INFO = 2;
   public static final int MSG_VERBOSE = 3;
   public static final int MSG_DEBUG = 4;
   private static boolean showedInfo = false;
   private static MessageLogger defaultLogger = new DefaultMessageLogger(2);

   public static MessageLogger getDefaultLogger() {
      return defaultLogger;
   }

   public static void setDefaultLogger(MessageLogger logger) {
      Checks.checkNotNull(logger, "logger");
      defaultLogger = logger;
   }

   private static MessageLogger getLogger() {
      return IvyContext.getContext().getMessageLogger();
   }

   public static void showInfo() {
      if (!showedInfo) {
         info(":: Apache Ivy " + Ivy.getIvyVersion() + " - " + Ivy.getIvyDate() + " :: " + Ivy.getIvyHomeURL() + " ::");
         showedInfo = true;
      }

   }

   public static void debug(String msg) {
      getLogger().debug(msg);
   }

   public static void verbose(String msg) {
      getLogger().verbose(msg);
   }

   public static void info(String msg) {
      getLogger().info(msg);
   }

   public static void rawinfo(String msg) {
      getLogger().rawinfo(msg);
   }

   public static void deprecated(String msg) {
      getLogger().deprecated(msg);
   }

   public static void warn(String msg) {
      getLogger().warn(msg);
   }

   public static void error(String msg) {
      getLogger().error(msg);
   }

   public static void log(int logLevel, String msg) {
      switch (logLevel) {
         case 0:
            error(msg);
            break;
         case 1:
            warn(msg);
            break;
         case 2:
            info(msg);
            break;
         case 3:
            verbose(msg);
            break;
         case 4:
            debug(msg);
            break;
         default:
            throw new IllegalArgumentException("Unknown log level " + logLevel);
      }

   }

   public static List getProblems() {
      return getLogger().getProblems();
   }

   public static void sumupProblems() {
      getLogger().sumupProblems();
   }

   public static void progress() {
      getLogger().progress();
   }

   public static void endProgress() {
      getLogger().endProgress();
   }

   public static void endProgress(String msg) {
      getLogger().endProgress(msg);
   }

   public static boolean isShowProgress() {
      return getLogger().isShowProgress();
   }

   public static void setShowProgress(boolean progress) {
      getLogger().setShowProgress(progress);
   }

   private Message() {
   }

   public static void debug(String message, Throwable t) {
      if (t == null) {
         debug(message);
      } else {
         debug(message + " (" + t.getClass().getName() + ": " + t.getMessage() + ")");
         debug(t);
      }

   }

   public static void verbose(String message, Throwable t) {
      if (t == null) {
         verbose(message);
      } else {
         verbose(message + " (" + t.getClass().getName() + ": " + t.getMessage() + ")");
         debug(t);
      }

   }

   public static void info(String message, Throwable t) {
      if (t == null) {
         info(message);
      } else {
         info(message + " (" + t.getClass().getName() + ": " + t.getMessage() + ")");
         debug(t);
      }

   }

   public static void warn(String message, Throwable t) {
      if (t == null) {
         warn(message);
      } else {
         warn(message + " (" + t.getClass().getName() + ": " + t.getMessage() + ")");
         debug(t);
      }

   }

   public static void error(String message, Throwable t) {
      if (t == null) {
         error(message);
      } else {
         error(message + " (" + t.getClass().getName() + ": " + t.getMessage() + ")");
         debug(t);
      }

   }

   public static void debug(Throwable t) {
      debug(StringUtils.getStackTrace(t));
   }
}
