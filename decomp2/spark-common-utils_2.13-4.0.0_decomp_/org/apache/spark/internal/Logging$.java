package org.apache.spark.internal;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.spark.util.SparkClassUtils$;
import org.slf4j.LoggerFactory;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class Logging$ {
   public static final Logging$ MODULE$ = new Logging$();
   private static volatile boolean org$apache$spark$internal$Logging$$initialized = false;
   private static volatile Level org$apache$spark$internal$Logging$$defaultRootLevel = null;
   private static volatile boolean org$apache$spark$internal$Logging$$defaultSparkLog4jConfig = false;
   private static volatile boolean structuredLoggingEnabled = false;
   private static volatile Level sparkShellThresholdLevel = null;
   private static volatile boolean setLogLevelPrinted = false;
   private static final Object initLock = new Object();

   static {
      try {
         Class bridgeClass = SparkClassUtils$.MODULE$.classForName("org.slf4j.bridge.SLF4JBridgeHandler", SparkClassUtils$.MODULE$.classForName$default$2(), SparkClassUtils$.MODULE$.classForName$default$3());
         bridgeClass.getMethod("removeHandlersForRootLogger").invoke((Object)null);
         boolean installed = BoxesRunTime.unboxToBoolean(bridgeClass.getMethod("isInstalled").invoke((Object)null));
         if (!installed) {
            bridgeClass.getMethod("install").invoke((Object)null);
         } else {
            BoxedUnit var4 = BoxedUnit.UNIT;
         }
      } catch (ClassNotFoundException var3) {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

   }

   public boolean org$apache$spark$internal$Logging$$initialized() {
      return org$apache$spark$internal$Logging$$initialized;
   }

   public void org$apache$spark$internal$Logging$$initialized_$eq(final boolean x$1) {
      org$apache$spark$internal$Logging$$initialized = x$1;
   }

   public Level org$apache$spark$internal$Logging$$defaultRootLevel() {
      return org$apache$spark$internal$Logging$$defaultRootLevel;
   }

   public void org$apache$spark$internal$Logging$$defaultRootLevel_$eq(final Level x$1) {
      org$apache$spark$internal$Logging$$defaultRootLevel = x$1;
   }

   public boolean org$apache$spark$internal$Logging$$defaultSparkLog4jConfig() {
      return org$apache$spark$internal$Logging$$defaultSparkLog4jConfig;
   }

   public void org$apache$spark$internal$Logging$$defaultSparkLog4jConfig_$eq(final boolean x$1) {
      org$apache$spark$internal$Logging$$defaultSparkLog4jConfig = x$1;
   }

   private boolean structuredLoggingEnabled() {
      return structuredLoggingEnabled;
   }

   private void structuredLoggingEnabled_$eq(final boolean x$1) {
      structuredLoggingEnabled = x$1;
   }

   public Level sparkShellThresholdLevel() {
      return sparkShellThresholdLevel;
   }

   public void sparkShellThresholdLevel_$eq(final Level x$1) {
      sparkShellThresholdLevel = x$1;
   }

   public boolean setLogLevelPrinted() {
      return setLogLevelPrinted;
   }

   public void setLogLevelPrinted_$eq(final boolean x$1) {
      setLogLevelPrinted = x$1;
   }

   public Object initLock() {
      return initLock;
   }

   public void uninitialize() {
      synchronized(this.initLock()){}

      try {
         if (this.org$apache$spark$internal$Logging$$isLog4j2()) {
            if (this.org$apache$spark$internal$Logging$$defaultSparkLog4jConfig()) {
               LoggerContext context = (LoggerContext)LogManager.getContext(false);
               context.reconfigure();
            } else {
               Logger rootLogger = (Logger)LogManager.getRootLogger();
               rootLogger.setLevel(this.org$apache$spark$internal$Logging$$defaultRootLevel());
               this.sparkShellThresholdLevel_$eq((Level)null);
            }
         }

         this.org$apache$spark$internal$Logging$$initialized_$eq(false);
      } catch (Throwable var5) {
         throw var5;
      }

   }

   public boolean org$apache$spark$internal$Logging$$isLog4j2() {
      return "org.apache.logging.slf4j.Log4jLoggerFactory".equals(LoggerFactory.getILoggerFactory().getClass().getName());
   }

   public boolean org$apache$spark$internal$Logging$$loggerWithCustomConfig(final Logger logger) {
      LoggerConfig rootConfig = ((Logger)LogManager.getRootLogger()).get();
      boolean var4;
      if (logger.get() == rootConfig) {
         label29: {
            Level var10000 = logger.getLevel();
            Level var3 = rootConfig.getLevel();
            if (var10000 == null) {
               if (var3 != null) {
                  break label29;
               }
            } else if (!var10000.equals(var3)) {
               break label29;
            }

            var4 = false;
            return var4;
         }
      }

      var4 = true;
      return var4;
   }

   public boolean islog4j2DefaultConfigured() {
      Logger rootLogger = (Logger)LogManager.getRootLogger();
      boolean var3;
      if (!rootLogger.getAppenders().isEmpty()) {
         label39: {
            if (rootLogger.getAppenders().size() == 1) {
               label32: {
                  Level var10000 = rootLogger.getLevel();
                  Level var2 = Level.ERROR;
                  if (var10000 == null) {
                     if (var2 != null) {
                        break label32;
                     }
                  } else if (!var10000.equals(var2)) {
                     break label32;
                  }

                  if (((LoggerContext)LogManager.getContext()).getConfiguration() instanceof DefaultConfiguration) {
                     break label39;
                  }
               }
            }

            var3 = false;
            return var3;
         }
      }

      var3 = true;
      return var3;
   }

   public void enableStructuredLogging() {
      this.structuredLoggingEnabled_$eq(true);
   }

   public void disableStructuredLogging() {
      this.structuredLoggingEnabled_$eq(false);
   }

   public boolean isStructuredLoggingEnabled() {
      return this.structuredLoggingEnabled();
   }

   private Logging$() {
   }
}
