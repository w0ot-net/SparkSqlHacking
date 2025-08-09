package org.apache.spark.deploy.worker;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.spark.SecurityManager;
import org.apache.spark.SecurityManager$;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.Command;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcAddress$;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.RpcEnv$;
import org.apache.spark.util.SparkUncaughtExceptionHandler;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOps;
import scala.collection.LinearSeqOps;
import scala.collection.SeqOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.util.matching.Regex;

public final class Worker$ implements Logging {
   public static final Worker$ MODULE$ = new Worker$();
   private static final String SYSTEM_NAME;
   private static final String ENDPOINT_NAME;
   private static final Regex org$apache$spark$deploy$worker$Worker$$SSL_NODE_LOCAL_CONFIG_PATTERN;
   private static final DateTimeFormatter org$apache$spark$deploy$worker$Worker$$DATE_TIME_FORMATTER;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      SYSTEM_NAME = "sparkWorker";
      ENDPOINT_NAME = "Worker";
      org$apache$spark$deploy$worker$Worker$$SSL_NODE_LOCAL_CONFIG_PATTERN = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("\\-Dspark\\.ssl\\.useNodeLocalConf\\=(.+)"));
      org$apache$spark$deploy$worker$Worker$$DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.US).withZone(ZoneId.systemDefault());
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

   public String $lessinit$greater$default$7() {
      return null;
   }

   public Option $lessinit$greater$default$10() {
      return scala.None..MODULE$;
   }

   public Supplier $lessinit$greater$default$11() {
      return null;
   }

   public String SYSTEM_NAME() {
      return SYSTEM_NAME;
   }

   public String ENDPOINT_NAME() {
      return ENDPOINT_NAME;
   }

   public Regex org$apache$spark$deploy$worker$Worker$$SSL_NODE_LOCAL_CONFIG_PATTERN() {
      return org$apache$spark$deploy$worker$Worker$$SSL_NODE_LOCAL_CONFIG_PATTERN;
   }

   public DateTimeFormatter org$apache$spark$deploy$worker$Worker$$DATE_TIME_FORMATTER() {
      return org$apache$spark$deploy$worker$Worker$$DATE_TIME_FORMATTER;
   }

   public void main(final String[] argStrings) {
      Thread.setDefaultUncaughtExceptionHandler(new SparkUncaughtExceptionHandler(false));
      Utils$.MODULE$.resetStructuredLogging();
      Utils$.MODULE$.initDaemon(this.log());
      SparkConf conf = new SparkConf();
      WorkerArguments args = new WorkerArguments(argStrings, conf);
      String x$1 = args.host();
      int x$2 = args.port();
      int x$3 = args.webUiPort();
      int x$4 = args.cores();
      int x$5 = args.memory();
      String[] x$6 = args.masters();
      String x$7 = args.workDir();
      Option x$9 = (Option)conf.get((ConfigEntry)org.apache.spark.internal.config.Worker$.MODULE$.SPARK_WORKER_RESOURCE_FILE());
      Option x$10 = this.startRpcEnvAndEndpoint$default$8();
      RpcEnv rpcEnv = this.startRpcEnvAndEndpoint(x$1, x$2, x$3, x$4, x$5, x$6, x$7, x$10, conf, x$9);
      boolean externalShuffleServiceEnabled = BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.SHUFFLE_SERVICE_ENABLED()));
      int sparkWorkerInstances = .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString((String)scala.sys.package..MODULE$.env().getOrElse("SPARK_WORKER_INSTANCES", () -> "1")));
      scala.Predef..MODULE$.require(!externalShuffleServiceEnabled || sparkWorkerInstances <= 1, () -> "Starting multiple workers on one host is failed because we may launch no more than one external shuffle service on each host, please set spark.shuffle.service.enabled to false or set SPARK_WORKER_INSTANCES to 1 to resolve the conflict.");
      rpcEnv.awaitTermination();
   }

   public RpcEnv startRpcEnvAndEndpoint(final String host, final int port, final int webUiPort, final int cores, final int memory, final String[] masterUrls, final String workDir, final Option workerNumber, final SparkConf conf, final Option resourceFileOpt) {
      String var10000 = this.SYSTEM_NAME();
      String systemName = var10000 + workerNumber.map((x$14) -> $anonfun$startRpcEnvAndEndpoint$1(BoxesRunTime.unboxToInt(x$14))).getOrElse(() -> "");
      SecurityManager securityMgr = new SecurityManager(conf, SecurityManager$.MODULE$.$lessinit$greater$default$2(), SecurityManager$.MODULE$.$lessinit$greater$default$3());
      RpcEnv rpcEnv = RpcEnv$.MODULE$.create(systemName, host, port, conf, securityMgr, RpcEnv$.MODULE$.create$default$6());
      RpcAddress[] masterAddresses = (RpcAddress[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])masterUrls), (sparkUrl) -> RpcAddress$.MODULE$.fromSparkURL(sparkUrl), scala.reflect.ClassTag..MODULE$.apply(RpcAddress.class));
      rpcEnv.setupEndpoint(this.ENDPOINT_NAME(), new Worker(rpcEnv, webUiPort, cores, memory, masterAddresses, this.ENDPOINT_NAME(), workDir, conf, securityMgr, resourceFileOpt, this.$lessinit$greater$default$11()));
      return rpcEnv;
   }

   public Option startRpcEnvAndEndpoint$default$8() {
      return scala.None..MODULE$;
   }

   public SparkConf startRpcEnvAndEndpoint$default$9() {
      return new SparkConf();
   }

   public Option startRpcEnvAndEndpoint$default$10() {
      return scala.None..MODULE$;
   }

   public boolean isUseLocalNodeSSLConfig(final Command cmd) {
      Option result = cmd.javaOpts().collectFirst(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final String x1, final Function1 default) {
            if (x1 != null) {
               Option var5 = Worker$.MODULE$.org$apache$spark$deploy$worker$Worker$$SSL_NODE_LOCAL_CONFIG_PATTERN().unapplySeq(x1);
               if (!var5.isEmpty() && var5.get() != null && ((List)var5.get()).lengthCompare(1) == 0) {
                  String _result = (String)((LinearSeqOps)var5.get()).apply(0);
                  return BoxesRunTime.boxToBoolean(.MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(_result)));
               }
            }

            return default.apply(x1);
         }

         public final boolean isDefinedAt(final String x1) {
            if (x1 != null) {
               Option var4 = Worker$.MODULE$.org$apache$spark$deploy$worker$Worker$$SSL_NODE_LOCAL_CONFIG_PATTERN().unapplySeq(x1);
               if (!var4.isEmpty() && var4.get() != null && ((List)var4.get()).lengthCompare(1) == 0) {
                  return true;
               }
            }

            return false;
         }
      });
      return BoxesRunTime.unboxToBoolean(result.getOrElse((JFunction0.mcZ.sp)() -> false));
   }

   public Command maybeUpdateSSLSettings(final Command cmd, final SparkConf conf) {
      String prefix = "spark.ssl.";
      String useNLC = "spark.ssl.useNodeLocalConf";
      if (this.isUseLocalNodeSSLConfig(cmd)) {
         Seq newJavaOpts = (Seq)((SeqOps)((IterableOps)cmd.javaOpts().filter((opt) -> BoxesRunTime.boxToBoolean($anonfun$maybeUpdateSSLSettings$1(prefix, opt)))).$plus$plus(scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.collect$extension(scala.Predef..MODULE$.refArrayOps((Object[])conf.getAll()), new Serializable(prefix) {
            private static final long serialVersionUID = 0L;
            private final String prefix$1;

            public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
               if (x1 != null) {
                  String key = (String)x1._1();
                  String value = (String)x1._2();
                  if (key.startsWith(this.prefix$1)) {
                     return "-D" + key + "=" + value;
                  }
               }

               return default.apply(x1);
            }

            public final boolean isDefinedAt(final Tuple2 x1) {
               if (x1 != null) {
                  String key = (String)x1._1();
                  if (key.startsWith(this.prefix$1)) {
                     return true;
                  }
               }

               return false;
            }

            public {
               this.prefix$1 = prefix$1;
            }
         }, scala.reflect.ClassTag..MODULE$.apply(String.class))))).$colon$plus("-D" + useNLC + "=true");
         String x$2 = cmd.copy$default$1();
         Seq x$3 = cmd.copy$default$2();
         scala.collection.Map x$4 = cmd.copy$default$3();
         Seq x$5 = cmd.copy$default$4();
         Seq x$6 = cmd.copy$default$5();
         return cmd.copy(x$2, x$3, x$4, x$5, x$6, newJavaOpts);
      } else {
         return cmd;
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$startRpcEnvAndEndpoint$1(final int x$14) {
      return Integer.toString(x$14);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$maybeUpdateSSLSettings$1(final String prefix$1, final String opt) {
      return !opt.startsWith("-D" + prefix$1);
   }

   private Worker$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
