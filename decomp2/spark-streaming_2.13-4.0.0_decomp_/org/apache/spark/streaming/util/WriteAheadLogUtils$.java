package org.apache.spark.streaming.util;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.streaming.StreamingConf$;
import org.apache.spark.util.Utils.;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.runtime.BoxesRunTime;

public final class WriteAheadLogUtils$ implements Logging {
   public static final WriteAheadLogUtils$ MODULE$ = new WriteAheadLogUtils$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
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

   public boolean enableReceiverLog(final SparkConf conf) {
      return BoxesRunTime.unboxToBoolean(conf.get(StreamingConf$.MODULE$.RECEIVER_WAL_ENABLE_CONF_KEY()));
   }

   public int getRollingIntervalSecs(final SparkConf conf, final boolean isDriver) {
      return isDriver ? BoxesRunTime.unboxToInt(conf.get(StreamingConf$.MODULE$.DRIVER_WAL_ROLLING_INTERVAL_CONF_KEY())) : BoxesRunTime.unboxToInt(conf.get(StreamingConf$.MODULE$.RECEIVER_WAL_ROLLING_INTERVAL_CONF_KEY()));
   }

   public int getMaxFailures(final SparkConf conf, final boolean isDriver) {
      return isDriver ? BoxesRunTime.unboxToInt(conf.get(StreamingConf$.MODULE$.DRIVER_WAL_MAX_FAILURES_CONF_KEY())) : BoxesRunTime.unboxToInt(conf.get(StreamingConf$.MODULE$.RECEIVER_WAL_MAX_FAILURES_CONF_KEY()));
   }

   public boolean isBatchingEnabled(final SparkConf conf, final boolean isDriver) {
      return isDriver && BoxesRunTime.unboxToBoolean(conf.get(StreamingConf$.MODULE$.DRIVER_WAL_BATCHING_CONF_KEY()));
   }

   public long getBatchingTimeout(final SparkConf conf) {
      return BoxesRunTime.unboxToLong(conf.get(StreamingConf$.MODULE$.DRIVER_WAL_BATCHING_TIMEOUT_CONF_KEY()));
   }

   public boolean shouldCloseFileAfterWrite(final SparkConf conf, final boolean isDriver) {
      return isDriver ? BoxesRunTime.unboxToBoolean(conf.get(StreamingConf$.MODULE$.DRIVER_WAL_CLOSE_AFTER_WRITE_CONF_KEY())) : BoxesRunTime.unboxToBoolean(conf.get(StreamingConf$.MODULE$.RECEIVER_WAL_CLOSE_AFTER_WRITE_CONF_KEY()));
   }

   public WriteAheadLog createLogForDriver(final SparkConf sparkConf, final String fileWalLogDirectory, final Configuration fileWalHadoopConf) {
      return this.createLog(true, sparkConf, fileWalLogDirectory, fileWalHadoopConf);
   }

   public WriteAheadLog createLogForReceiver(final SparkConf sparkConf, final String fileWalLogDirectory, final Configuration fileWalHadoopConf) {
      return this.createLog(false, sparkConf, fileWalLogDirectory, fileWalHadoopConf);
   }

   private WriteAheadLog createLog(final boolean isDriver, final SparkConf sparkConf, final String fileWalLogDirectory, final Configuration fileWalHadoopConf) {
      Option classNameOption = isDriver ? (Option)sparkConf.get(StreamingConf$.MODULE$.DRIVER_WAL_CLASS_CONF_KEY()) : (Option)sparkConf.get(StreamingConf$.MODULE$.RECEIVER_WAL_CLASS_CONF_KEY());
      WriteAheadLog wal = (WriteAheadLog)classNameOption.map((className) -> {
         try {
            return MODULE$.instantiateClass(.MODULE$.classForName(className, .MODULE$.classForName$default$2(), .MODULE$.classForName$default$3()), sparkConf);
         } catch (Throwable var6) {
            if (var6 != null && scala.util.control.NonFatal..MODULE$.apply(var6)) {
               throw new SparkException("Could not create a write ahead log of class " + className, var6);
            } else {
               throw var6;
            }
         }
      }).getOrElse(() -> new FileBasedWriteAheadLog(sparkConf, fileWalLogDirectory, fileWalHadoopConf, MODULE$.getRollingIntervalSecs(sparkConf, isDriver), MODULE$.getMaxFailures(sparkConf, isDriver), MODULE$.shouldCloseFileAfterWrite(sparkConf, isDriver)));
      return (WriteAheadLog)(this.isBatchingEnabled(sparkConf, isDriver) ? new BatchedWriteAheadLog(wal, sparkConf) : wal);
   }

   private WriteAheadLog instantiateClass(final Class cls, final SparkConf conf) {
      WriteAheadLog var10000;
      try {
         var10000 = (WriteAheadLog)cls.getConstructor(SparkConf.class).newInstance(conf);
      } catch (NoSuchMethodException var4) {
         var10000 = (WriteAheadLog)cls.getConstructor().newInstance();
      }

      return var10000;
   }

   private WriteAheadLogUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
