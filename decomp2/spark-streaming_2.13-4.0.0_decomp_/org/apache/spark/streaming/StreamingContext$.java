package org.apache.spark.streaming;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.ShutdownHookManager.;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.collection.immutable.Seq;

/** @deprecated */
public final class StreamingContext$ implements Logging {
   public static final StreamingContext$ MODULE$ = new StreamingContext$();
   private static final Object org$apache$spark$streaming$StreamingContext$$ACTIVATION_LOCK;
   private static final int org$apache$spark$streaming$StreamingContext$$SHUTDOWN_HOOK_PRIORITY;
   private static final AtomicReference activeContext;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      org$apache$spark$streaming$StreamingContext$$ACTIVATION_LOCK = new Object();
      org$apache$spark$streaming$StreamingContext$$SHUTDOWN_HOOK_PRIORITY = .MODULE$.SPARK_CONTEXT_SHUTDOWN_PRIORITY() + 1;
      activeContext = new AtomicReference((Object)null);
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

   public String $lessinit$greater$default$4() {
      return null;
   }

   public Seq $lessinit$greater$default$5() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   public scala.collection.Map $lessinit$greater$default$6() {
      return (scala.collection.Map)scala.collection.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }

   public Object org$apache$spark$streaming$StreamingContext$$ACTIVATION_LOCK() {
      return org$apache$spark$streaming$StreamingContext$$ACTIVATION_LOCK;
   }

   public int org$apache$spark$streaming$StreamingContext$$SHUTDOWN_HOOK_PRIORITY() {
      return org$apache$spark$streaming$StreamingContext$$SHUTDOWN_HOOK_PRIORITY;
   }

   private AtomicReference activeContext() {
      return activeContext;
   }

   public void org$apache$spark$streaming$StreamingContext$$assertNoOtherContextIsActive() {
      synchronized(this.org$apache$spark$streaming$StreamingContext$$ACTIVATION_LOCK()){}

      try {
         if (this.activeContext().get() != null) {
            throw new IllegalStateException("Only one StreamingContext may be started in this JVM. Currently running StreamingContext was started at" + ((StreamingContext)this.activeContext().get()).getStartSite().longForm());
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public void org$apache$spark$streaming$StreamingContext$$setActiveContext(final StreamingContext ssc) {
      synchronized(this.org$apache$spark$streaming$StreamingContext$$ACTIVATION_LOCK()){}

      try {
         this.activeContext().set(ssc);
      } catch (Throwable var4) {
         throw var4;
      }

   }

   public Option getActive() {
      synchronized(this.org$apache$spark$streaming$StreamingContext$$ACTIVATION_LOCK()){}

      Option var2;
      try {
         var2 = scala.Option..MODULE$.apply(this.activeContext().get());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   public StreamingContext getActiveOrCreate(final Function0 creatingFunc) {
      synchronized(this.org$apache$spark$streaming$StreamingContext$$ACTIVATION_LOCK()){}

      StreamingContext var3;
      try {
         var3 = (StreamingContext)this.getActive().getOrElse(creatingFunc);
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   public StreamingContext getActiveOrCreate(final String checkpointPath, final Function0 creatingFunc, final Configuration hadoopConf, final boolean createOnError) {
      synchronized(this.org$apache$spark$streaming$StreamingContext$$ACTIVATION_LOCK()){}

      StreamingContext var6;
      try {
         var6 = (StreamingContext)this.getActive().getOrElse(() -> MODULE$.getOrCreate(checkpointPath, creatingFunc, hadoopConf, createOnError));
      } catch (Throwable var8) {
         throw var8;
      }

      return var6;
   }

   public Configuration getActiveOrCreate$default$3() {
      return org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().conf();
   }

   public boolean getActiveOrCreate$default$4() {
      return false;
   }

   public StreamingContext getOrCreate(final String checkpointPath, final Function0 creatingFunc, final Configuration hadoopConf, final boolean createOnError) {
      Option checkpointOption = CheckpointReader$.MODULE$.read(checkpointPath, new SparkConf(), hadoopConf, createOnError);
      return (StreamingContext)checkpointOption.map((x$7) -> new StreamingContext((SparkContext)null, x$7, (Duration)null)).getOrElse(creatingFunc);
   }

   public Configuration getOrCreate$default$3() {
      return org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().conf();
   }

   public boolean getOrCreate$default$4() {
      return false;
   }

   public Option jarOfClass(final Class cls) {
      return org.apache.spark.SparkContext..MODULE$.jarOfClass(cls);
   }

   public SparkContext createNewSparkContext(final SparkConf conf) {
      return new SparkContext(conf);
   }

   public SparkContext createNewSparkContext(final String master, final String appName, final String sparkHome, final Seq jars, final scala.collection.Map environment) {
      SparkConf conf = org.apache.spark.SparkContext..MODULE$.updatedConf(new SparkConf(), master, appName, sparkHome, jars, environment);
      return new SparkContext(conf);
   }

   public String rddToFileName(final String prefix, final String suffix, final Time time) {
      String result = Long.toString(time.milliseconds());
      if (prefix != null && prefix.length() > 0) {
         result = prefix + "-" + result;
      }

      if (suffix != null && suffix.length() > 0) {
         result = result + "." + suffix;
      }

      return result;
   }

   private StreamingContext$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
