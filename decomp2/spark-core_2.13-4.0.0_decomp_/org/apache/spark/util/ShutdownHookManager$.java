package org.apache.spark.util;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.ArrayOps.;
import scala.collection.mutable.HashSet;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class ShutdownHookManager$ implements Logging {
   public static final ShutdownHookManager$ MODULE$ = new ShutdownHookManager$();
   private static SparkShutdownHookManager shutdownHooks;
   private static final int DEFAULT_SHUTDOWN_PRIORITY;
   private static final int SPARK_CONTEXT_SHUTDOWN_PRIORITY;
   private static final int TEMP_DIR_SHUTDOWN_PRIORITY;
   private static final HashSet shutdownDeletePaths;
   private static transient Logger org$apache$spark$internal$Logging$$log_;
   private static volatile boolean bitmap$0;

   static {
      Logging.$init$(MODULE$);
      DEFAULT_SHUTDOWN_PRIORITY = 100;
      SPARK_CONTEXT_SHUTDOWN_PRIORITY = 50;
      TEMP_DIR_SHUTDOWN_PRIORITY = 25;
      shutdownDeletePaths = new HashSet();
      MODULE$.logDebug((Function0)(() -> "Adding shutdown hook"));
      MODULE$.addShutdownHook(MODULE$.TEMP_DIR_SHUTDOWN_PRIORITY(), (JFunction0.mcV.sp)() -> {
         MODULE$.logInfo((Function0)(() -> "Shutdown hook called"));
         .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(MODULE$.shutdownDeletePaths().toArray(scala.reflect.ClassTag..MODULE$.apply(String.class))), (dirPath) -> {
            $anonfun$new$4(dirPath);
            return BoxedUnit.UNIT;
         });
      });
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public int DEFAULT_SHUTDOWN_PRIORITY() {
      return DEFAULT_SHUTDOWN_PRIORITY;
   }

   public int SPARK_CONTEXT_SHUTDOWN_PRIORITY() {
      return SPARK_CONTEXT_SHUTDOWN_PRIORITY;
   }

   public int TEMP_DIR_SHUTDOWN_PRIORITY() {
      return TEMP_DIR_SHUTDOWN_PRIORITY;
   }

   private SparkShutdownHookManager shutdownHooks$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            SparkShutdownHookManager manager = new SparkShutdownHookManager();
            manager.install();
            shutdownHooks = manager;
            bitmap$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return shutdownHooks;
   }

   private SparkShutdownHookManager shutdownHooks() {
      return !bitmap$0 ? this.shutdownHooks$lzycompute() : shutdownHooks;
   }

   private HashSet shutdownDeletePaths() {
      return shutdownDeletePaths;
   }

   public void registerShutdownDeleteDir(final File file) {
      String absolutePath = file.getAbsolutePath();
      synchronized(this.shutdownDeletePaths()){}

      try {
         HashSet var10000 = (HashSet)this.shutdownDeletePaths().$plus$eq(absolutePath);
      } catch (Throwable var5) {
         throw var5;
      }

   }

   public void removeShutdownDeleteDir(final File file) {
      String absolutePath = file.getAbsolutePath();
      synchronized(this.shutdownDeletePaths()){}

      try {
         this.shutdownDeletePaths().remove(absolutePath);
      } catch (Throwable var5) {
         throw var5;
      }

   }

   public boolean hasShutdownDeleteDir(final File file) {
      String absolutePath = file.getAbsolutePath();
      synchronized(this.shutdownDeletePaths()){}

      boolean var4;
      try {
         var4 = this.shutdownDeletePaths().contains(absolutePath);
      } catch (Throwable var6) {
         throw var6;
      }

      return var4;
   }

   public boolean hasRootAsShutdownDeleteDir(final File file) {
      String absolutePath = file.getAbsolutePath();
      synchronized(this.shutdownDeletePaths()){}

      boolean var5;
      try {
         var5 = this.shutdownDeletePaths().exists((path) -> BoxesRunTime.boxToBoolean($anonfun$hasRootAsShutdownDeleteDir$1(absolutePath, path)));
      } catch (Throwable var7) {
         throw var7;
      }

      if (var5) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"path = ", ", already present as root for deletion."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, file)})))));
      }

      return var5;
   }

   public boolean inShutdown() {
      try {
         Thread hook = new Thread() {
            public void run() {
            }
         };
         Runtime.getRuntime().addShutdownHook(hook);
         Runtime.getRuntime().removeShutdownHook(hook);
      } catch (IllegalStateException var3) {
         return true;
      }

      return false;
   }

   public Object addShutdownHook(final Function0 hook) {
      return this.addShutdownHook(this.DEFAULT_SHUTDOWN_PRIORITY(), hook);
   }

   public Object addShutdownHook(final int priority, final Function0 hook) {
      return this.shutdownHooks().add(priority, hook);
   }

   public boolean removeShutdownHook(final Object ref) {
      return this.shutdownHooks().remove(ref);
   }

   // $FF: synthetic method
   public static final void $anonfun$new$4(final String dirPath) {
      try {
         MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleting directory ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, dirPath)})))));
         Utils$.MODULE$.deleteRecursively(new File(dirPath));
      } catch (Exception var2) {
         MODULE$.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception while deleting Spark temp dir: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, dirPath)})))), var2);
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasRootAsShutdownDeleteDir$1(final String absolutePath$1, final String path) {
      return !absolutePath$1.equals(path) && absolutePath$1.startsWith(path);
   }

   private ShutdownHookManager$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
