package javolution.util;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javolution.context.LogContext;

public class StandardLog extends LogContext {
   private Logger _logger;

   public StandardLog() {
      this(Logger.getLogger(""));
   }

   public StandardLog(Logger logger) {
      this._logger = logger;
   }

   public final Logger getLogger() {
      return this._logger;
   }

   public static boolean isLoggable(Level level) {
      LogContext log = LogContext.getCurrentLogContext();
      if (log instanceof StandardLog) {
         return ((StandardLog)log)._logger.isLoggable(level);
      } else if (level.intValue() >= Level.SEVERE.intValue()) {
         return LogContext.isErrorLogged();
      } else if (level.intValue() >= Level.WARNING.intValue()) {
         return LogContext.isWarningLogged();
      } else if (level.intValue() >= Level.INFO.intValue()) {
         return LogContext.isInfoLogged();
      } else {
         return level.intValue() >= Level.FINE.intValue() ? LogContext.isDebugLogged() : false;
      }
   }

   public static void log(LogRecord record) {
      LogContext log = LogContext.getCurrentLogContext();
      if (log instanceof StandardLog) {
         ((StandardLog)log)._logger.log(record);
      } else {
         Throwable error = record.getThrown();
         if (error != null) {
            LogContext.error(error, (CharSequence)record.getMessage());
         } else if (record.getLevel().intValue() >= Level.SEVERE.intValue()) {
            LogContext.error((CharSequence)record.getMessage());
         } else if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
            LogContext.warning((CharSequence)record.getMessage());
         } else if (record.getLevel().intValue() >= Level.INFO.intValue()) {
            LogContext.info((CharSequence)record.getMessage());
         } else if (record.getLevel().intValue() > Level.FINE.intValue()) {
            LogContext.debug((CharSequence)record.getMessage());
         }
      }

   }

   public static void severe(String msg) {
      LogContext log = LogContext.getCurrentLogContext();
      if (log instanceof StandardLog) {
         ((StandardLog)log)._logger.severe(msg);
      } else {
         LogContext.warning((CharSequence)msg);
      }

   }

   public static void config(String msg) {
      LogContext log = LogContext.getCurrentLogContext();
      if (log instanceof StandardLog) {
         ((StandardLog)log)._logger.config(msg);
      }

   }

   public static void fine(String msg) {
      LogContext log = LogContext.getCurrentLogContext();
      if (log instanceof StandardLog) {
         ((StandardLog)log)._logger.fine(msg);
      }

   }

   public static void finer(String msg) {
      LogContext log = LogContext.getCurrentLogContext();
      if (log instanceof StandardLog) {
         ((StandardLog)log)._logger.finer(msg);
      }

   }

   public static void finest(String msg) {
      LogContext log = LogContext.getCurrentLogContext();
      if (log instanceof StandardLog) {
         ((StandardLog)log)._logger.finest(msg);
      }

   }

   public static void throwing(String sourceClass, String sourceMethod, Throwable thrown) {
      LogContext log = LogContext.getCurrentLogContext();
      if (log instanceof StandardLog) {
         ((StandardLog)log)._logger.throwing(sourceClass, sourceMethod, thrown);
      } else {
         LogContext.error(thrown, (CharSequence)("Thrown by " + sourceClass + "." + sourceMethod));
      }

   }

   public static void entering(String sourceClass, String sourceMethod) {
      LogContext log = LogContext.getCurrentLogContext();
      if (log instanceof StandardLog) {
         ((StandardLog)log)._logger.entering(sourceClass, sourceMethod);
      } else {
         LogContext.debug((CharSequence)("Entering " + sourceClass + "." + sourceMethod));
      }

   }

   public static void exiting(String sourceClass, String sourceMethod) {
      LogContext log = LogContext.getCurrentLogContext();
      if (log instanceof StandardLog) {
         ((StandardLog)log)._logger.exiting(sourceClass, sourceMethod);
      } else {
         LogContext.debug((CharSequence)("Exiting " + sourceClass + "." + sourceMethod));
      }

   }

   protected boolean isLogged(String category) {
      if (category.equals("debug")) {
         return this._logger.isLoggable(Level.FINE);
      } else if (category.equals("info")) {
         return this._logger.isLoggable(Level.INFO);
      } else if (category.equals("warning")) {
         return this._logger.isLoggable(Level.WARNING);
      } else {
         return category.equals("error") ? this._logger.isLoggable(Level.SEVERE) : true;
      }
   }

   public void logDebug(CharSequence message) {
      this._logger.fine(message.toString());
   }

   public void logInfo(CharSequence message) {
      this._logger.info(message.toString());
   }

   public void logWarning(CharSequence message) {
      this._logger.warning(message.toString());
   }

   public void logError(Throwable error, CharSequence message) {
      String description = message != null ? message.toString() : "";
      description = error != null ? error.toString() + " " + description : description;
      this._logger.severe(description);
   }

   protected void logMessage(String category, CharSequence message) {
      this._logger.info("[" + category + "] " + message);
   }
}
