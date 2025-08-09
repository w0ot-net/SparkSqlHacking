package io.vertx.core.spi.logging;

public interface LogDelegate {
   boolean isWarnEnabled();

   boolean isInfoEnabled();

   boolean isDebugEnabled();

   boolean isTraceEnabled();

   void fatal(Object var1);

   void fatal(Object var1, Throwable var2);

   void error(Object var1);

   void error(Object var1, Object... var2);

   void error(Object var1, Throwable var2);

   void error(Object var1, Throwable var2, Object... var3);

   void warn(Object var1);

   void warn(Object var1, Object... var2);

   void warn(Object var1, Throwable var2);

   void warn(Object var1, Throwable var2, Object... var3);

   void info(Object var1);

   void info(Object var1, Object... var2);

   void info(Object var1, Throwable var2);

   void info(Object var1, Throwable var2, Object... var3);

   void debug(Object var1);

   void debug(Object var1, Object... var2);

   void debug(Object var1, Throwable var2);

   void debug(Object var1, Throwable var2, Object... var3);

   void trace(Object var1);

   void trace(Object var1, Object... var2);

   void trace(Object var1, Throwable var2);

   void trace(Object var1, Throwable var2, Object... var3);

   default Object unwrap() {
      return null;
   }
}
