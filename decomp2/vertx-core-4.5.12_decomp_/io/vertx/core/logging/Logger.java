package io.vertx.core.logging;

import io.vertx.core.spi.logging.LogDelegate;

/** @deprecated */
@Deprecated
public class Logger {
   final LogDelegate delegate;

   /** @deprecated */
   @Deprecated
   public Logger(LogDelegate delegate) {
      this.delegate = delegate;
   }

   /** @deprecated */
   @Deprecated
   public boolean isWarnEnabled() {
      return this.delegate.isWarnEnabled();
   }

   /** @deprecated */
   @Deprecated
   public boolean isInfoEnabled() {
      return this.delegate.isInfoEnabled();
   }

   /** @deprecated */
   @Deprecated
   public boolean isDebugEnabled() {
      return this.delegate.isDebugEnabled();
   }

   /** @deprecated */
   @Deprecated
   public boolean isTraceEnabled() {
      return this.delegate.isTraceEnabled();
   }

   /** @deprecated */
   @Deprecated
   public void fatal(Object message) {
      this.delegate.fatal(message);
   }

   /** @deprecated */
   @Deprecated
   public void fatal(Object message, Throwable t) {
      this.delegate.fatal(message, t);
   }

   /** @deprecated */
   @Deprecated
   public void error(Object message) {
      this.delegate.error(message);
   }

   /** @deprecated */
   @Deprecated
   public void error(Object message, Throwable t) {
      this.delegate.error(message, t);
   }

   /** @deprecated */
   @Deprecated
   public void error(Object message, Object... objects) {
      this.delegate.error(message, objects);
   }

   /** @deprecated */
   @Deprecated
   public void error(Object message, Throwable t, Object... objects) {
      this.delegate.error(message, t, objects);
   }

   /** @deprecated */
   @Deprecated
   public void warn(Object message) {
      this.delegate.warn(message);
   }

   /** @deprecated */
   @Deprecated
   public void warn(Object message, Throwable t) {
      this.delegate.warn(message, t);
   }

   /** @deprecated */
   @Deprecated
   public void warn(Object message, Object... objects) {
      this.delegate.warn(message, objects);
   }

   /** @deprecated */
   @Deprecated
   public void warn(Object message, Throwable t, Object... objects) {
      this.delegate.warn(message, t, objects);
   }

   /** @deprecated */
   @Deprecated
   public void info(Object message) {
      this.delegate.info(message);
   }

   /** @deprecated */
   @Deprecated
   public void info(Object message, Throwable t) {
      this.delegate.info(message, t);
   }

   /** @deprecated */
   @Deprecated
   public void info(Object message, Object... objects) {
      this.delegate.info(message, objects);
   }

   /** @deprecated */
   @Deprecated
   public void info(Object message, Throwable t, Object... objects) {
      this.delegate.info(message, t, objects);
   }

   /** @deprecated */
   @Deprecated
   public void debug(Object message) {
      this.delegate.debug(message);
   }

   /** @deprecated */
   @Deprecated
   public void debug(Object message, Throwable t) {
      this.delegate.debug(message, t);
   }

   /** @deprecated */
   @Deprecated
   public void debug(Object message, Object... objects) {
      this.delegate.debug(message, objects);
   }

   /** @deprecated */
   @Deprecated
   public void debug(Object message, Throwable t, Object... objects) {
      this.delegate.debug(message, t, objects);
   }

   /** @deprecated */
   @Deprecated
   public void trace(Object message) {
      this.delegate.trace(message);
   }

   /** @deprecated */
   @Deprecated
   public void trace(Object message, Throwable t) {
      this.delegate.trace(message, t);
   }

   /** @deprecated */
   @Deprecated
   public void trace(Object message, Object... objects) {
      this.delegate.trace(message, objects);
   }

   /** @deprecated */
   @Deprecated
   public void trace(Object message, Throwable t, Object... objects) {
      this.delegate.trace(message, t, objects);
   }

   /** @deprecated */
   @Deprecated
   public LogDelegate getDelegate() {
      return this.delegate;
   }
}
