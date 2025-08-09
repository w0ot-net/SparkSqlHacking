package io.netty.util.internal.logging;

import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;

final class LocationAwareSlf4JLogger extends AbstractInternalLogger {
   static final String FQCN = LocationAwareSlf4JLogger.class.getName();
   private static final long serialVersionUID = -8292030083201538180L;
   private final transient LocationAwareLogger logger;

   LocationAwareSlf4JLogger(LocationAwareLogger logger) {
      super(logger.getName());
      this.logger = logger;
   }

   private void log(int level, String message) {
      this.logger.log((Marker)null, FQCN, level, message, (Object[])null, (Throwable)null);
   }

   private void log(int level, String message, Throwable cause) {
      this.logger.log((Marker)null, FQCN, level, message, (Object[])null, cause);
   }

   private void log(int level, org.slf4j.helpers.FormattingTuple tuple) {
      this.logger.log((Marker)null, FQCN, level, tuple.getMessage(), (Object[])null, tuple.getThrowable());
   }

   public boolean isTraceEnabled() {
      return this.logger.isTraceEnabled();
   }

   public void trace(String msg) {
      if (this.isTraceEnabled()) {
         this.log(0, (String)msg);
      }

   }

   public void trace(String format, Object arg) {
      if (this.isTraceEnabled()) {
         this.log(0, (org.slf4j.helpers.FormattingTuple)org.slf4j.helpers.MessageFormatter.format(format, arg));
      }

   }

   public void trace(String format, Object argA, Object argB) {
      if (this.isTraceEnabled()) {
         this.log(0, (org.slf4j.helpers.FormattingTuple)org.slf4j.helpers.MessageFormatter.format(format, argA, argB));
      }

   }

   public void trace(String format, Object... argArray) {
      if (this.isTraceEnabled()) {
         this.log(0, (org.slf4j.helpers.FormattingTuple)org.slf4j.helpers.MessageFormatter.arrayFormat(format, argArray));
      }

   }

   public void trace(String msg, Throwable t) {
      if (this.isTraceEnabled()) {
         this.log(0, msg, t);
      }

   }

   public boolean isDebugEnabled() {
      return this.logger.isDebugEnabled();
   }

   public void debug(String msg) {
      if (this.isDebugEnabled()) {
         this.log(10, (String)msg);
      }

   }

   public void debug(String format, Object arg) {
      if (this.isDebugEnabled()) {
         this.log(10, (org.slf4j.helpers.FormattingTuple)org.slf4j.helpers.MessageFormatter.format(format, arg));
      }

   }

   public void debug(String format, Object argA, Object argB) {
      if (this.isDebugEnabled()) {
         this.log(10, (org.slf4j.helpers.FormattingTuple)org.slf4j.helpers.MessageFormatter.format(format, argA, argB));
      }

   }

   public void debug(String format, Object... argArray) {
      if (this.isDebugEnabled()) {
         this.log(10, (org.slf4j.helpers.FormattingTuple)org.slf4j.helpers.MessageFormatter.arrayFormat(format, argArray));
      }

   }

   public void debug(String msg, Throwable t) {
      if (this.isDebugEnabled()) {
         this.log(10, msg, t);
      }

   }

   public boolean isInfoEnabled() {
      return this.logger.isInfoEnabled();
   }

   public void info(String msg) {
      if (this.isInfoEnabled()) {
         this.log(20, (String)msg);
      }

   }

   public void info(String format, Object arg) {
      if (this.isInfoEnabled()) {
         this.log(20, (org.slf4j.helpers.FormattingTuple)org.slf4j.helpers.MessageFormatter.format(format, arg));
      }

   }

   public void info(String format, Object argA, Object argB) {
      if (this.isInfoEnabled()) {
         this.log(20, (org.slf4j.helpers.FormattingTuple)org.slf4j.helpers.MessageFormatter.format(format, argA, argB));
      }

   }

   public void info(String format, Object... argArray) {
      if (this.isInfoEnabled()) {
         this.log(20, (org.slf4j.helpers.FormattingTuple)org.slf4j.helpers.MessageFormatter.arrayFormat(format, argArray));
      }

   }

   public void info(String msg, Throwable t) {
      if (this.isInfoEnabled()) {
         this.log(20, msg, t);
      }

   }

   public boolean isWarnEnabled() {
      return this.logger.isWarnEnabled();
   }

   public void warn(String msg) {
      if (this.isWarnEnabled()) {
         this.log(30, (String)msg);
      }

   }

   public void warn(String format, Object arg) {
      if (this.isWarnEnabled()) {
         this.log(30, (org.slf4j.helpers.FormattingTuple)org.slf4j.helpers.MessageFormatter.format(format, arg));
      }

   }

   public void warn(String format, Object... argArray) {
      if (this.isWarnEnabled()) {
         this.log(30, (org.slf4j.helpers.FormattingTuple)org.slf4j.helpers.MessageFormatter.arrayFormat(format, argArray));
      }

   }

   public void warn(String format, Object argA, Object argB) {
      if (this.isWarnEnabled()) {
         this.log(30, (org.slf4j.helpers.FormattingTuple)org.slf4j.helpers.MessageFormatter.format(format, argA, argB));
      }

   }

   public void warn(String msg, Throwable t) {
      if (this.isWarnEnabled()) {
         this.log(30, msg, t);
      }

   }

   public boolean isErrorEnabled() {
      return this.logger.isErrorEnabled();
   }

   public void error(String msg) {
      if (this.isErrorEnabled()) {
         this.log(40, (String)msg);
      }

   }

   public void error(String format, Object arg) {
      if (this.isErrorEnabled()) {
         this.log(40, (org.slf4j.helpers.FormattingTuple)org.slf4j.helpers.MessageFormatter.format(format, arg));
      }

   }

   public void error(String format, Object argA, Object argB) {
      if (this.isErrorEnabled()) {
         this.log(40, (org.slf4j.helpers.FormattingTuple)org.slf4j.helpers.MessageFormatter.format(format, argA, argB));
      }

   }

   public void error(String format, Object... argArray) {
      if (this.isErrorEnabled()) {
         this.log(40, (org.slf4j.helpers.FormattingTuple)org.slf4j.helpers.MessageFormatter.arrayFormat(format, argArray));
      }

   }

   public void error(String msg, Throwable t) {
      if (this.isErrorEnabled()) {
         this.log(40, msg, t);
      }

   }
}
