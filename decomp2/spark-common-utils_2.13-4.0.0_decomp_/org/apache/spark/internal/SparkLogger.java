package org.apache.spark.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.apache.logging.log4j.CloseableThreadContext;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.ParameterizedMessageFactory;
import org.slf4j.Logger;

public class SparkLogger {
   private static final MessageFactory MESSAGE_FACTORY;
   private final Logger slf4jLogger;

   SparkLogger(Logger slf4jLogger) {
      this.slf4jLogger = slf4jLogger;
   }

   public boolean isErrorEnabled() {
      return this.slf4jLogger.isErrorEnabled();
   }

   public void error(String msg) {
      this.slf4jLogger.error(msg);
   }

   public void error(String msg, Throwable throwable) {
      this.slf4jLogger.error(msg, throwable);
   }

   public void error(String msg, MDC... mdcs) {
      if (mdcs != null && mdcs.length != 0) {
         if (this.slf4jLogger.isErrorEnabled()) {
            this.withLogContext(msg, mdcs, (Throwable)null, (mt) -> this.slf4jLogger.error(mt.message));
         }
      } else {
         this.slf4jLogger.error(msg);
      }

   }

   public void error(String msg, Throwable throwable, MDC... mdcs) {
      if (mdcs != null && mdcs.length != 0) {
         if (this.slf4jLogger.isErrorEnabled()) {
            this.withLogContext(msg, mdcs, throwable, (mt) -> this.slf4jLogger.error(mt.message, mt.throwable));
         }
      } else {
         this.slf4jLogger.error(msg, throwable);
      }

   }

   public boolean isWarnEnabled() {
      return this.slf4jLogger.isWarnEnabled();
   }

   public void warn(String msg) {
      this.slf4jLogger.warn(msg);
   }

   public void warn(String msg, Throwable throwable) {
      this.slf4jLogger.warn(msg, throwable);
   }

   public void warn(String msg, MDC... mdcs) {
      if (mdcs != null && mdcs.length != 0) {
         if (this.slf4jLogger.isWarnEnabled()) {
            this.withLogContext(msg, mdcs, (Throwable)null, (mt) -> this.slf4jLogger.warn(mt.message));
         }
      } else {
         this.slf4jLogger.warn(msg);
      }

   }

   public void warn(String msg, Throwable throwable, MDC... mdcs) {
      if (mdcs != null && mdcs.length != 0) {
         if (this.slf4jLogger.isWarnEnabled()) {
            this.withLogContext(msg, mdcs, throwable, (mt) -> this.slf4jLogger.warn(mt.message, mt.throwable));
         }
      } else {
         this.slf4jLogger.warn(msg, throwable);
      }

   }

   public boolean isInfoEnabled() {
      return this.slf4jLogger.isInfoEnabled();
   }

   public void info(String msg) {
      this.slf4jLogger.info(msg);
   }

   public void info(String msg, Throwable throwable) {
      this.slf4jLogger.info(msg, throwable);
   }

   public void info(String msg, MDC... mdcs) {
      if (mdcs != null && mdcs.length != 0) {
         if (this.slf4jLogger.isInfoEnabled()) {
            this.withLogContext(msg, mdcs, (Throwable)null, (mt) -> this.slf4jLogger.info(mt.message));
         }
      } else {
         this.slf4jLogger.info(msg);
      }

   }

   public void info(String msg, Throwable throwable, MDC... mdcs) {
      if (mdcs != null && mdcs.length != 0) {
         if (this.slf4jLogger.isInfoEnabled()) {
            this.withLogContext(msg, mdcs, throwable, (mt) -> this.slf4jLogger.info(mt.message, mt.throwable));
         }
      } else {
         this.slf4jLogger.info(msg, throwable);
      }

   }

   public boolean isDebugEnabled() {
      return this.slf4jLogger.isDebugEnabled();
   }

   public void debug(String msg) {
      this.slf4jLogger.debug(msg);
   }

   public void debug(String format, Object arg) {
      this.slf4jLogger.debug(format, arg);
   }

   public void debug(String format, Object arg1, Object arg2) {
      this.slf4jLogger.debug(format, arg1, arg2);
   }

   public void debug(String format, Object... arguments) {
      this.slf4jLogger.debug(format, arguments);
   }

   public void debug(String msg, Throwable throwable) {
      this.slf4jLogger.debug(msg, throwable);
   }

   public boolean isTraceEnabled() {
      return this.slf4jLogger.isTraceEnabled();
   }

   public void trace(String msg) {
      this.slf4jLogger.trace(msg);
   }

   public void trace(String format, Object arg) {
      this.slf4jLogger.trace(format, arg);
   }

   public void trace(String format, Object arg1, Object arg2) {
      this.slf4jLogger.trace(format, arg1, arg2);
   }

   public void trace(String format, Object... arguments) {
      this.slf4jLogger.trace(format, arguments);
   }

   public void trace(String msg, Throwable throwable) {
      this.slf4jLogger.trace(msg, throwable);
   }

   private void withLogContext(String pattern, MDC[] mdcs, Throwable throwable, Consumer func) {
      Map<String, String> context = new HashMap();
      Object[] args = new Object[mdcs.length];

      for(int index = 0; index < mdcs.length; ++index) {
         MDC mdc = mdcs[index];
         String value = mdc.value() != null ? mdc.value().toString() : null;
         if (Logging$.MODULE$.isStructuredLoggingEnabled()) {
            context.put(mdc.key().name(), value);
         }

         args[index] = value;
      }

      MessageThrowable messageThrowable = SparkLogger.MessageThrowable.of(MESSAGE_FACTORY.newMessage(pattern, args).getFormattedMessage(), throwable);
      CloseableThreadContext.Instance ignored = CloseableThreadContext.putAll(context);

      try {
         func.accept(messageThrowable);
      } catch (Throwable var12) {
         if (ignored != null) {
            try {
               ignored.close();
            } catch (Throwable var11) {
               var12.addSuppressed(var11);
            }
         }

         throw var12;
      }

      if (ignored != null) {
         ignored.close();
      }

   }

   public Logger getSlf4jLogger() {
      return this.slf4jLogger;
   }

   static {
      MESSAGE_FACTORY = ParameterizedMessageFactory.INSTANCE;
   }

   private static record MessageThrowable(String message, Throwable throwable) {
      static MessageThrowable of(String message, Throwable throwable) {
         return new MessageThrowable(message, throwable);
      }
   }
}
