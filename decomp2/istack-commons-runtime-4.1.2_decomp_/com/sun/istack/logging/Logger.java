package com.sun.istack.logging;

import com.sun.istack.NotNull;
import java.util.StringTokenizer;
import java.util.logging.Level;

public class Logger {
   private static final String WS_LOGGING_SUBSYSTEM_NAME_ROOT = "com.sun.metro";
   private static final String ROOT_WS_PACKAGE = "com.sun.xml.ws.";
   private static final Level METHOD_CALL_LEVEL_VALUE;
   private final String componentClassName;
   private final java.util.logging.Logger logger;

   protected Logger(String systemLoggerName, String componentName) {
      this.componentClassName = "[" + componentName + "] ";
      this.logger = java.util.logging.Logger.getLogger(systemLoggerName);
   }

   @NotNull
   public static Logger getLogger(@NotNull Class componentClass) {
      return new Logger(getSystemLoggerName(componentClass), componentClass.getName());
   }

   @NotNull
   public static Logger getLogger(@NotNull String customLoggerName, @NotNull Class componentClass) {
      return new Logger(customLoggerName, componentClass.getName());
   }

   static final String getSystemLoggerName(@NotNull Class componentClass) {
      StringBuilder sb = new StringBuilder(componentClass.getPackage().getName());
      int lastIndexOfWsPackage = sb.lastIndexOf("com.sun.xml.ws.");
      if (lastIndexOfWsPackage > -1) {
         sb.replace(0, lastIndexOfWsPackage + "com.sun.xml.ws.".length(), "");
         StringTokenizer st = new StringTokenizer(sb.toString(), ".");
         sb = (new StringBuilder("com.sun.metro")).append(".");
         if (st.hasMoreTokens()) {
            String token = st.nextToken();
            if ("api".equals(token)) {
               token = st.nextToken();
            }

            sb.append(token);
         }
      }

      return sb.toString();
   }

   public void log(Level level, String message) {
      if (this.logger.isLoggable(level)) {
         this.logger.logp(level, this.componentClassName, StackHelper.getCallerMethodName(), message);
      }
   }

   public void log(Level level, String message, Object param1) {
      if (this.logger.isLoggable(level)) {
         this.logger.logp(level, this.componentClassName, StackHelper.getCallerMethodName(), message, param1);
      }
   }

   public void log(Level level, String message, Object[] params) {
      if (this.logger.isLoggable(level)) {
         this.logger.logp(level, this.componentClassName, StackHelper.getCallerMethodName(), message, params);
      }
   }

   public void log(Level level, String message, Throwable thrown) {
      if (this.logger.isLoggable(level)) {
         this.logger.logp(level, this.componentClassName, StackHelper.getCallerMethodName(), message, thrown);
      }
   }

   public void finest(String message) {
      if (this.logger.isLoggable(Level.FINEST)) {
         this.logger.logp(Level.FINEST, this.componentClassName, StackHelper.getCallerMethodName(), message);
      }
   }

   public void finest(String message, Object[] params) {
      if (this.logger.isLoggable(Level.FINEST)) {
         this.logger.logp(Level.FINEST, this.componentClassName, StackHelper.getCallerMethodName(), message, params);
      }
   }

   public void finest(String message, Throwable thrown) {
      if (this.logger.isLoggable(Level.FINEST)) {
         this.logger.logp(Level.FINEST, this.componentClassName, StackHelper.getCallerMethodName(), message, thrown);
      }
   }

   public void finer(String message) {
      if (this.logger.isLoggable(Level.FINER)) {
         this.logger.logp(Level.FINER, this.componentClassName, StackHelper.getCallerMethodName(), message);
      }
   }

   public void finer(String message, Object[] params) {
      if (this.logger.isLoggable(Level.FINER)) {
         this.logger.logp(Level.FINER, this.componentClassName, StackHelper.getCallerMethodName(), message, params);
      }
   }

   public void finer(String message, Throwable thrown) {
      if (this.logger.isLoggable(Level.FINER)) {
         this.logger.logp(Level.FINER, this.componentClassName, StackHelper.getCallerMethodName(), message, thrown);
      }
   }

   public void fine(String message) {
      if (this.logger.isLoggable(Level.FINE)) {
         this.logger.logp(Level.FINE, this.componentClassName, StackHelper.getCallerMethodName(), message);
      }
   }

   public void fine(String message, Throwable thrown) {
      if (this.logger.isLoggable(Level.FINE)) {
         this.logger.logp(Level.FINE, this.componentClassName, StackHelper.getCallerMethodName(), message, thrown);
      }
   }

   public void info(String message) {
      if (this.logger.isLoggable(Level.INFO)) {
         this.logger.logp(Level.INFO, this.componentClassName, StackHelper.getCallerMethodName(), message);
      }
   }

   public void info(String message, Object[] params) {
      if (this.logger.isLoggable(Level.INFO)) {
         this.logger.logp(Level.INFO, this.componentClassName, StackHelper.getCallerMethodName(), message, params);
      }
   }

   public void info(String message, Throwable thrown) {
      if (this.logger.isLoggable(Level.INFO)) {
         this.logger.logp(Level.INFO, this.componentClassName, StackHelper.getCallerMethodName(), message, thrown);
      }
   }

   public void config(String message) {
      if (this.logger.isLoggable(Level.CONFIG)) {
         this.logger.logp(Level.CONFIG, this.componentClassName, StackHelper.getCallerMethodName(), message);
      }
   }

   public void config(String message, Object[] params) {
      if (this.logger.isLoggable(Level.CONFIG)) {
         this.logger.logp(Level.CONFIG, this.componentClassName, StackHelper.getCallerMethodName(), message, params);
      }
   }

   public void config(String message, Throwable thrown) {
      if (this.logger.isLoggable(Level.CONFIG)) {
         this.logger.logp(Level.CONFIG, this.componentClassName, StackHelper.getCallerMethodName(), message, thrown);
      }
   }

   public void warning(String message) {
      if (this.logger.isLoggable(Level.WARNING)) {
         this.logger.logp(Level.WARNING, this.componentClassName, StackHelper.getCallerMethodName(), message);
      }
   }

   public void warning(String message, Object[] params) {
      if (this.logger.isLoggable(Level.WARNING)) {
         this.logger.logp(Level.WARNING, this.componentClassName, StackHelper.getCallerMethodName(), message, params);
      }
   }

   public void warning(String message, Throwable thrown) {
      if (this.logger.isLoggable(Level.WARNING)) {
         this.logger.logp(Level.WARNING, this.componentClassName, StackHelper.getCallerMethodName(), message, thrown);
      }
   }

   public void severe(String message) {
      if (this.logger.isLoggable(Level.SEVERE)) {
         this.logger.logp(Level.SEVERE, this.componentClassName, StackHelper.getCallerMethodName(), message);
      }
   }

   public void severe(String message, Object[] params) {
      if (this.logger.isLoggable(Level.SEVERE)) {
         this.logger.logp(Level.SEVERE, this.componentClassName, StackHelper.getCallerMethodName(), message, params);
      }
   }

   public void severe(String message, Throwable thrown) {
      if (this.logger.isLoggable(Level.SEVERE)) {
         this.logger.logp(Level.SEVERE, this.componentClassName, StackHelper.getCallerMethodName(), message, thrown);
      }
   }

   public boolean isMethodCallLoggable() {
      return this.logger.isLoggable(METHOD_CALL_LEVEL_VALUE);
   }

   public boolean isLoggable(Level level) {
      return this.logger.isLoggable(level);
   }

   public void setLevel(Level level) {
      this.logger.setLevel(level);
   }

   public void entering() {
      if (this.logger.isLoggable(METHOD_CALL_LEVEL_VALUE)) {
         this.logger.entering(this.componentClassName, StackHelper.getCallerMethodName());
      }
   }

   public void entering(Object... parameters) {
      if (this.logger.isLoggable(METHOD_CALL_LEVEL_VALUE)) {
         this.logger.entering(this.componentClassName, StackHelper.getCallerMethodName(), parameters);
      }
   }

   public void exiting() {
      if (this.logger.isLoggable(METHOD_CALL_LEVEL_VALUE)) {
         this.logger.exiting(this.componentClassName, StackHelper.getCallerMethodName());
      }
   }

   public void exiting(Object result) {
      if (this.logger.isLoggable(METHOD_CALL_LEVEL_VALUE)) {
         this.logger.exiting(this.componentClassName, StackHelper.getCallerMethodName(), result);
      }
   }

   public Throwable logSevereException(Throwable exception, Throwable cause) {
      if (this.logger.isLoggable(Level.SEVERE)) {
         if (cause == null) {
            this.logger.logp(Level.SEVERE, this.componentClassName, StackHelper.getCallerMethodName(), exception.getMessage());
         } else {
            exception.initCause(cause);
            this.logger.logp(Level.SEVERE, this.componentClassName, StackHelper.getCallerMethodName(), exception.getMessage(), cause);
         }
      }

      return exception;
   }

   public Throwable logSevereException(Throwable exception, boolean logCause) {
      if (this.logger.isLoggable(Level.SEVERE)) {
         if (logCause && exception.getCause() != null) {
            this.logger.logp(Level.SEVERE, this.componentClassName, StackHelper.getCallerMethodName(), exception.getMessage(), exception.getCause());
         } else {
            this.logger.logp(Level.SEVERE, this.componentClassName, StackHelper.getCallerMethodName(), exception.getMessage());
         }
      }

      return exception;
   }

   public Throwable logSevereException(Throwable exception) {
      if (this.logger.isLoggable(Level.SEVERE)) {
         if (exception.getCause() == null) {
            this.logger.logp(Level.SEVERE, this.componentClassName, StackHelper.getCallerMethodName(), exception.getMessage());
         } else {
            this.logger.logp(Level.SEVERE, this.componentClassName, StackHelper.getCallerMethodName(), exception.getMessage(), exception.getCause());
         }
      }

      return exception;
   }

   public Throwable logException(Throwable exception, Throwable cause, Level level) {
      if (this.logger.isLoggable(level)) {
         if (cause == null) {
            this.logger.logp(level, this.componentClassName, StackHelper.getCallerMethodName(), exception.getMessage());
         } else {
            exception.initCause(cause);
            this.logger.logp(level, this.componentClassName, StackHelper.getCallerMethodName(), exception.getMessage(), cause);
         }
      }

      return exception;
   }

   public Throwable logException(Throwable exception, boolean logCause, Level level) {
      if (this.logger.isLoggable(level)) {
         if (logCause && exception.getCause() != null) {
            this.logger.logp(level, this.componentClassName, StackHelper.getCallerMethodName(), exception.getMessage(), exception.getCause());
         } else {
            this.logger.logp(level, this.componentClassName, StackHelper.getCallerMethodName(), exception.getMessage());
         }
      }

      return exception;
   }

   public Throwable logException(Throwable exception, Level level) {
      if (this.logger.isLoggable(level)) {
         if (exception.getCause() == null) {
            this.logger.logp(level, this.componentClassName, StackHelper.getCallerMethodName(), exception.getMessage());
         } else {
            this.logger.logp(level, this.componentClassName, StackHelper.getCallerMethodName(), exception.getMessage(), exception.getCause());
         }
      }

      return exception;
   }

   static {
      METHOD_CALL_LEVEL_VALUE = Level.FINEST;
   }
}
