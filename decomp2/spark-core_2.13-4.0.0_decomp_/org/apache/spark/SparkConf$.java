package org.apache.spark;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.History$;
import org.apache.spark.internal.config.Kryo$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.runtime.BoxedUnit;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.NonLocalReturnControl;

public final class SparkConf$ implements Logging, Serializable {
   public static final SparkConf$ MODULE$ = new SparkConf$();
   private static final Map deprecatedConfigs;
   private static final Map org$apache$spark$SparkConf$$configsWithAlternatives;
   private static final Map allAlternatives;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      Seq configs = (Seq).MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new SparkConf.DeprecatedConfig[]{new SparkConf.DeprecatedConfig("spark.cache.class", "0.8", "The spark.cache.class property is no longer being used! Specify storage levels using the RDD.persist() method instead."), new SparkConf.DeprecatedConfig("spark.yarn.user.classpath.first", "1.3", "Please use spark.{driver,executor}.userClassPathFirst instead."), new SparkConf.DeprecatedConfig("spark.kryoserializer.buffer.mb", "1.4", "Please use spark.kryoserializer.buffer instead. The default value for spark.kryoserializer.buffer.mb was previously specified as '0.064'. Fractional values are no longer accepted. To specify the equivalent now, one may use '64k'."), new SparkConf.DeprecatedConfig("spark.shuffle.spill", "1.6", "Not used anymore."), new SparkConf.DeprecatedConfig("spark.rpc", "2.0", "Not used anymore."), new SparkConf.DeprecatedConfig("spark.scheduler.executorTaskBlacklistTime", "2.1.0", "Please use the new excludedOnFailure options, spark.excludeOnFailure.*"), new SparkConf.DeprecatedConfig("spark.yarn.am.port", "2.0.0", "Not used anymore"), new SparkConf.DeprecatedConfig("spark.executor.port", "2.0.0", "Not used anymore"), new SparkConf.DeprecatedConfig("spark.rpc.numRetries", "2.2.0", "Not used anymore"), new SparkConf.DeprecatedConfig("spark.rpc.retry.wait", "2.2.0", "Not used anymore"), new SparkConf.DeprecatedConfig("spark.shuffle.service.index.cache.entries", "2.3.0", "Not used anymore. Please use spark.shuffle.service.index.cache.size"), new SparkConf.DeprecatedConfig("spark.yarn.credentials.file.retention.count", "2.4.0", "Not used anymore."), new SparkConf.DeprecatedConfig("spark.yarn.credentials.file.retention.days", "2.4.0", "Not used anymore."), new SparkConf.DeprecatedConfig("spark.yarn.services", "3.0.0", "Feature no longer available."), new SparkConf.DeprecatedConfig("spark.executor.plugins", "3.0.0", "Feature replaced with new plugin API. See Monitoring documentation."), new SparkConf.DeprecatedConfig("spark.blacklist.enabled", "3.1.0", "Please use spark.excludeOnFailure.enabled"), new SparkConf.DeprecatedConfig("spark.blacklist.task.maxTaskAttemptsPerExecutor", "3.1.0", "Please use spark.excludeOnFailure.task.maxTaskAttemptsPerExecutor"), new SparkConf.DeprecatedConfig("spark.blacklist.task.maxTaskAttemptsPerNode", "3.1.0", "Please use spark.excludeOnFailure.task.maxTaskAttemptsPerNode"), new SparkConf.DeprecatedConfig("spark.blacklist.application.maxFailedTasksPerExecutor", "3.1.0", "Please use spark.excludeOnFailure.application.maxFailedTasksPerExecutor"), new SparkConf.DeprecatedConfig("spark.blacklist.stage.maxFailedTasksPerExecutor", "3.1.0", "Please use spark.excludeOnFailure.stage.maxFailedTasksPerExecutor"), new SparkConf.DeprecatedConfig("spark.blacklist.application.maxFailedExecutorsPerNode", "3.1.0", "Please use spark.excludeOnFailure.application.maxFailedExecutorsPerNode"), new SparkConf.DeprecatedConfig("spark.blacklist.stage.maxFailedExecutorsPerNode", "3.1.0", "Please use spark.excludeOnFailure.stage.maxFailedExecutorsPerNode"), new SparkConf.DeprecatedConfig("spark.blacklist.timeout", "3.1.0", "Please use spark.excludeOnFailure.timeout"), new SparkConf.DeprecatedConfig("spark.blacklist.application.fetchFailure.enabled", "3.1.0", "Please use spark.excludeOnFailure.application.fetchFailure.enabled"), new SparkConf.DeprecatedConfig("spark.scheduler.blacklist.unschedulableTaskSetTimeout", "3.1.0", "Please use spark.scheduler.excludeOnFailure.unschedulableTaskSetTimeout"), new SparkConf.DeprecatedConfig("spark.blacklist.killBlacklistedExecutors", "3.1.0", "Please use spark.excludeOnFailure.killExcludedExecutors"), new SparkConf.DeprecatedConfig("spark.yarn.blacklist.executor.launch.blacklisting.enabled", "3.1.0", "Please use spark.yarn.executor.launch.excludeOnFailure.enabled"), new SparkConf.DeprecatedConfig("spark.network.remoteReadNioBufferConversion", "3.5.2", "Please open a JIRA ticket to report it if you need to use this configuration."), new SparkConf.DeprecatedConfig("spark.shuffle.unsafe.file.output.buffer", "4.0.0", "Please use spark.shuffle.localDisk.file.output.buffer")}));
      deprecatedConfigs = (Map)scala.Predef..MODULE$.Map().apply((Seq)configs.map((cfg) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(cfg.key()), cfg)));
      org$apache$spark$SparkConf$$configsWithAlternatives = (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_USER_CLASS_PATH_FIRST().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.files.userClassPathFirst", "1.3", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(History$.MODULE$.UPDATE_INTERVAL_S().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.history.fs.update.interval.seconds", "1.4", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.history.fs.updateInterval", "1.3", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.history.updateInterval", "1.3", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(History$.MODULE$.CLEANER_INTERVAL_S().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.history.fs.cleaner.interval.seconds", "1.4", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(History$.MODULE$.MAX_LOG_AGE_S().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.history.fs.cleaner.maxAge.seconds", "1.4", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("spark.yarn.am.waitTime"), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.yarn.applicationMaster.waitTries", "1.3", (s) -> {
         long var10000 = scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(s));
         return var10000 * 10L + "s";
      }), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.REDUCER_MAX_SIZE_IN_FLIGHT().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.reducer.maxMbInFlight", "1.4", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Kryo$.MODULE$.KRYO_SERIALIZER_BUFFER_SIZE().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.kryoserializer.buffer.mb", "1.4", (s) -> {
         double var10000 = scala.collection.StringOps..MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString(s));
         return (int)(var10000 * (double)1000) + "k";
      }), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Kryo$.MODULE$.KRYO_SERIALIZER_MAX_BUFFER_SIZE().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.kryoserializer.buffer.max.mb", "1.4", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_FILE_BUFFER_SIZE().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.shuffle.file.buffer.kb", "1.4", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_LOGS_ROLLING_MAX_SIZE().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.executor.logs.rolling.size.maxBytes", "1.4", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.IO_COMPRESSION_SNAPPY_BLOCKSIZE().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.io.compression.snappy.block.size", "1.4", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.IO_COMPRESSION_LZ4_BLOCKSIZE().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.io.compression.lz4.block.size", "1.4", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("spark.streaming.fileStream.minRememberDuration"), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.streaming.minRememberDuration", "1.5", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("spark.yarn.max.executor.failures"), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.yarn.max.worker.failures", "1.5", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.MEMORY_OFFHEAP_ENABLED().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.unsafe.offHeap", "1.6", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("spark.yarn.jars"), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.yarn.jar", "2.0", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.MAX_REMOTE_BLOCK_SIZE_FETCH_TO_MEM().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.reducer.maxReqSizeShuffleToMem", "2.3", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.maxRemoteBlockSizeFetchToMem", "3.0", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.LISTENER_BUS_EVENT_QUEUE_CAPACITY().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.scheduler.listenerbus.eventqueue.size", "2.3", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.DRIVER_MEMORY_OVERHEAD().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.yarn.driver.memoryOverhead", "2.3", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_MEMORY_OVERHEAD().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.yarn.executor.memoryOverhead", "2.3", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.KEYTAB().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.yarn.keytab", "3.0", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.PRINCIPAL().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.yarn.principal", "3.0", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.KERBEROS_RELOGIN_PERIOD().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.yarn.kerberos.relogin.period", "3.0", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.KERBEROS_FILESYSTEMS_TO_ACCESS().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.yarn.access.namenodes", "2.2", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.yarn.access.hadoopFileSystems", "3.0", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("spark.kafka.consumer.cache.capacity"), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.sql.kafkaConsumerCache.capacity", "3.0", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.MAX_EXECUTOR_FAILURES().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.yarn.max.executor.failures", "3.5", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_ATTEMPT_FAILURE_VALIDITY_INTERVAL_MS().key()), new scala.collection.immutable..colon.colon(new SparkConf.AlternateConfig("spark.yarn.executor.failuresValidityInterval", "3.5", SparkConf.AlternateConfig$.MODULE$.apply$default$3()), scala.collection.immutable.Nil..MODULE$))})));
      allAlternatives = ((IterableOnceOps)MODULE$.org$apache$spark$SparkConf$$configsWithAlternatives().keys().flatMap((key) -> (Seq)((IterableOps)MODULE$.org$apache$spark$SparkConf$$configsWithAlternatives().apply(key)).map((cfg) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(cfg.key()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), cfg))))).toMap(scala..less.colon.less..MODULE$.refl());
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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   private Map deprecatedConfigs() {
      return deprecatedConfigs;
   }

   public Map org$apache$spark$SparkConf$$configsWithAlternatives() {
      return org$apache$spark$SparkConf$$configsWithAlternatives;
   }

   private Map allAlternatives() {
      return allAlternatives;
   }

   public boolean isExecutorStartupConf(final String name) {
      boolean var10000;
      label30: {
         if (name.startsWith("spark.auth")) {
            String var2 = SecurityManager$.MODULE$.SPARK_AUTH_SECRET_CONF();
            if (name == null) {
               if (var2 != null) {
                  break label30;
               }
            } else if (!name.equals(var2)) {
               break label30;
            }
         }

         if (!name.startsWith("spark.rpc") && !name.startsWith("spark.network") && (!name.startsWith("spark.ssl") || name.contains("Password")) && !this.isSparkPortConf(name)) {
            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public boolean isSparkPortConf(final String name) {
      return name.startsWith("spark.") && name.endsWith(".port") || name.startsWith("spark.port.");
   }

   public Option getDeprecatedConfig(final String key, final java.util.Map conf) {
      return this.org$apache$spark$SparkConf$$configsWithAlternatives().get(key).flatMap((alts) -> alts.collectFirst(new Serializable(conf) {
            private static final long serialVersionUID = 0L;
            private final java.util.Map conf$1;

            public final Object applyOrElse(final SparkConf.AlternateConfig x1, final Function1 default) {
               if (this.conf$1.containsKey(x1.key())) {
                  String value = (String)this.conf$1.get(x1.key());
                  return x1.translation() != null ? x1.translation().apply(value) : value;
               } else {
                  return default.apply(x1);
               }
            }

            public final boolean isDefinedAt(final SparkConf.AlternateConfig x1) {
               return this.conf$1.containsKey(x1.key());
            }

            public {
               this.conf$1 = conf$1;
            }
         }));
   }

   public void logDeprecationWarning(final String key) {
      Object var2 = new Object();

      try {
         this.deprecatedConfigs().get(key).foreach((cfg) -> {
            MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The configuration key '", "' has been deprecated "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, key)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"as of Spark ", " and "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG_VERSION..MODULE$, cfg.version())})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"may be removed in the future. "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG_DEPRECATION_MESSAGE..MODULE$, cfg.deprecationMessage())}))))));
            throw new NonLocalReturnControl.mcV.sp(var2, BoxedUnit.UNIT);
         });
         this.allAlternatives().get(key).foreach((x0$1) -> {
            if (x0$1 != null) {
               String newKey = (String)x0$1._1();
               SparkConf.AlternateConfig cfg = (SparkConf.AlternateConfig)x0$1._2();
               MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The configuration key '", "' "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, key)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"has been deprecated as of "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Spark ", " and "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG_VERSION..MODULE$, cfg.version())})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"may be removed in the future. Please use the new key "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"'", "' instead."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG_KEY_UPDATED..MODULE$, newKey)}))))));
               throw new NonLocalReturnControl.mcV.sp(var2, BoxedUnit.UNIT);
            } else {
               throw new MatchError(x0$1);
            }
         });
      } catch (NonLocalReturnControl var4) {
         if (var4.key() != var2) {
            throw var4;
         }

         var4.value$mcV$sp();
      }

   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkConf$.class);
   }

   private SparkConf$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
