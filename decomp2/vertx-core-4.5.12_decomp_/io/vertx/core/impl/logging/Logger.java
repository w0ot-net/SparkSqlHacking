package io.vertx.core.impl.logging;

public interface Logger {
   boolean isTraceEnabled();

   void trace(Object var1);

   void trace(Object var1, Throwable var2);

   boolean isDebugEnabled();

   void debug(Object var1);

   void debug(Object var1, Throwable var2);

   boolean isInfoEnabled();

   void info(Object var1);

   void info(Object var1, Throwable var2);

   boolean isWarnEnabled();

   void warn(Object var1);

   void warn(Object var1, Throwable var2);

   void error(Object var1);

   void error(Object var1, Throwable var2);
}
