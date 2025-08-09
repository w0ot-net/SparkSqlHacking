package org.apache.spark;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.broadcast.BroadcastManager;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.OptionalConfigEntry;
import org.apache.spark.memory.MemoryManager;
import org.apache.spark.metrics.MetricsSystem;
import org.apache.spark.metrics.MetricsSystem$;
import org.apache.spark.metrics.MetricsSystemInstances$;
import org.apache.spark.network.netty.NettyBlockTransferService;
import org.apache.spark.network.netty.SparkTransportConf$;
import org.apache.spark.network.shuffle.ExternalBlockStoreClient;
import org.apache.spark.network.util.TransportConf;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.RpcEnv$;
import org.apache.spark.scheduler.LiveListenerBus;
import org.apache.spark.scheduler.OutputCommitCoordinator;
import org.apache.spark.security.CryptoStreamUtils$;
import org.apache.spark.serializer.JavaSerializer;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.shuffle.ShuffleManager;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.storage.BlockManagerMaster;
import org.apache.spark.storage.BlockManagerMaster$;
import org.apache.spark.storage.BlockManagerMasterEndpoint;
import org.apache.spark.storage.BlockManagerMasterHeartbeatEndpoint;
import org.apache.spark.util.RpcUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqOps;
import scala.collection.concurrent.TrieMap;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class SparkEnv$ implements Logging {
   public static final SparkEnv$ MODULE$ = new SparkEnv$();
   private static volatile SparkEnv env;
   private static final String driverSystemName;
   private static final String executorSystemName;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      driverSystemName = "sparkDriver";
      executorSystemName = "sparkExecutor";
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

   private SparkEnv env() {
      return env;
   }

   private void env_$eq(final SparkEnv x$1) {
      env = x$1;
   }

   public String driverSystemName() {
      return driverSystemName;
   }

   public String executorSystemName() {
      return executorSystemName;
   }

   public void set(final SparkEnv e) {
      this.env_$eq(e);
   }

   public SparkEnv get() {
      return this.env();
   }

   public SparkEnv createDriverEnv(final SparkConf conf, final boolean isLocal, final LiveListenerBus listenerBus, final int numCores, final Option mockOutputCommitCoordinator) {
      .MODULE$.assert(conf.contains(org.apache.spark.internal.config.package$.MODULE$.DRIVER_HOST_ADDRESS()), () -> org.apache.spark.internal.config.package$.MODULE$.DRIVER_HOST_ADDRESS().key() + " is not set on the driver!");
      .MODULE$.assert(conf.contains(org.apache.spark.internal.config.package$.MODULE$.DRIVER_PORT()), () -> org.apache.spark.internal.config.package$.MODULE$.DRIVER_PORT().key() + " is not set on the driver!");
      String bindAddress = (String)conf.get(org.apache.spark.internal.config.package$.MODULE$.DRIVER_BIND_ADDRESS());
      String advertiseAddress = (String)conf.get(org.apache.spark.internal.config.package$.MODULE$.DRIVER_HOST_ADDRESS());
      int port = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.DRIVER_PORT()));
      Option ioEncryptionKey = (Option)(BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.IO_ENCRYPTION_ENABLED())) ? new Some(CryptoStreamUtils$.MODULE$.createKey(conf)) : scala.None..MODULE$);
      return this.create(conf, SparkContext$.MODULE$.DRIVER_IDENTIFIER(), bindAddress, advertiseAddress, scala.Option..MODULE$.apply(BoxesRunTime.boxToInteger(port)), isLocal, numCores, ioEncryptionKey, listenerBus, mockOutputCommitCoordinator);
   }

   public SparkEnv createExecutorEnv(final SparkConf conf, final String executorId, final String bindAddress, final String hostname, final int numCores, final Option ioEncryptionKey, final boolean isLocal) {
      SparkEnv env = this.create(conf, executorId, bindAddress, hostname, scala.None..MODULE$, isLocal, numCores, ioEncryptionKey, this.create$default$9(), this.create$default$10());
      env.initializeMemoryManager(numCores);
      this.set(env);
      return env;
   }

   private SparkEnv create(final SparkConf conf, final String executorId, final String bindAddress, final String advertiseAddress, final Option port, final boolean isLocal, final int numUsableCores, final Option ioEncryptionKey, final LiveListenerBus listenerBus, final Option mockOutputCommitCoordinator) {
      boolean var10000;
      label74: {
         label73: {
            String var12 = SparkContext$.MODULE$.DRIVER_IDENTIFIER();
            if (executorId == null) {
               if (var12 == null) {
                  break label73;
               }
            } else if (executorId.equals(var12)) {
               break label73;
            }

            var10000 = false;
            break label74;
         }

         var10000 = true;
      }

      boolean isDriver = var10000;
      if (isDriver) {
         .MODULE$.assert(listenerBus != null, () -> "Attempted to create driver SparkEnv with null listener bus!");
      }

      ConfigEntry authSecretFileConf = isDriver ? org.apache.spark.internal.config.package$.MODULE$.AUTH_SECRET_FILE_DRIVER() : org.apache.spark.internal.config.package$.MODULE$.AUTH_SECRET_FILE_EXECUTOR();
      SecurityManager securityManager = new SecurityManager(conf, ioEncryptionKey, authSecretFileConf);
      if (isDriver) {
         securityManager.initializeAuth();
      }

      ioEncryptionKey.foreach((x$7) -> {
         $anonfun$create$2(securityManager, x$7);
         return BoxedUnit.UNIT;
      });
      String systemName = isDriver ? this.driverSystemName() : this.executorSystemName();
      RpcEnv rpcEnv = RpcEnv$.MODULE$.create(systemName, bindAddress, advertiseAddress, BoxesRunTime.unboxToInt(port.getOrElse((JFunction0.mcI.sp)() -> -1)), conf, securityManager, numUsableCores, !isDriver);
      if (isDriver) {
         conf.set((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.DRIVER_PORT(), (Object)BoxesRunTime.boxToInteger(rpcEnv.address().port()));
      } else {
         BoxedUnit var40 = BoxedUnit.UNIT;
      }

      Serializer serializer = (Serializer)Utils$.MODULE$.instantiateSerializerFromConf(org.apache.spark.internal.config.package$.MODULE$.SERIALIZER(), conf, isDriver);
      this.logDebug((Function0)(() -> "Using serializer: " + serializer.getClass()));
      SerializerManager serializerManager = new SerializerManager(serializer, conf, ioEncryptionKey);
      JavaSerializer closureSerializer = new JavaSerializer(conf);
      BroadcastManager broadcastManager = new BroadcastManager(isDriver, conf);
      MapOutputTracker mapOutputTracker = (MapOutputTracker)(isDriver ? new MapOutputTrackerMaster(conf, broadcastManager, isLocal) : new MapOutputTrackerWorker(conf));
      mapOutputTracker.trackerEndpoint_$eq(this.registerOrLookupEndpoint$1(MapOutputTracker$.MODULE$.ENDPOINT_NAME(), () -> new MapOutputTrackerMasterEndpoint(rpcEnv, (MapOutputTrackerMaster)mapOutputTracker, conf), isDriver, rpcEnv, conf));
      int blockManagerPort = isDriver ? BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.DRIVER_BLOCK_MANAGER_PORT())) : BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.BLOCK_MANAGER_PORT()));
      Object var41;
      if (BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_SERVICE_ENABLED()))) {
         String x$2 = "shuffle";
         Some x$4 = new Some(securityManager.getRpcSSLOptions());
         Option x$5 = SparkTransportConf$.MODULE$.fromSparkConf$default$4();
         TransportConf transConf = SparkTransportConf$.MODULE$.fromSparkConf(conf, "shuffle", numUsableCores, x$5, x$4);
         var41 = new Some(new ExternalBlockStoreClient(transConf, securityManager, securityManager.isAuthenticationEnabled(), BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_REGISTRATION_TIMEOUT()))));
      } else {
         var41 = scala.None..MODULE$;
      }

      Option externalShuffleClient = (Option)var41;
      TrieMap blockManagerInfo = new TrieMap();
      BlockManagerMaster blockManagerMaster = new BlockManagerMaster(this.registerOrLookupEndpoint$1(BlockManagerMaster$.MODULE$.DRIVER_ENDPOINT_NAME(), () -> new BlockManagerMasterEndpoint(rpcEnv, isLocal, conf, listenerBus, (Option)(BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_SERVICE_ENABLED())) ? externalShuffleClient : scala.None..MODULE$), blockManagerInfo, (MapOutputTrackerMaster)mapOutputTracker, (ShuffleManager)null, isDriver), isDriver, rpcEnv, conf), this.registerOrLookupEndpoint$1(BlockManagerMaster$.MODULE$.DRIVER_HEARTBEAT_ENDPOINT_NAME(), () -> new BlockManagerMasterHeartbeatEndpoint(rpcEnv, isLocal, blockManagerInfo), isDriver, rpcEnv, conf), conf, isDriver);
      NettyBlockTransferService blockTransferService = new NettyBlockTransferService(conf, securityManager, serializerManager, bindAddress, advertiseAddress, blockManagerPort, numUsableCores, blockManagerMaster.driverEndpoint());
      BlockManager blockManager = new BlockManager(executorId, rpcEnv, blockManagerMaster, serializerManager, conf, (MemoryManager)null, mapOutputTracker, (ShuffleManager)null, blockTransferService, securityManager, externalShuffleClient);
      MetricsSystem var42;
      if (isDriver) {
         var42 = MetricsSystem$.MODULE$.createMetricsSystem(MetricsSystemInstances$.MODULE$.DRIVER(), conf);
      } else {
         conf.set((OptionalConfigEntry)org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_ID(), (Object)executorId);
         MetricsSystem ms = MetricsSystem$.MODULE$.createMetricsSystem(MetricsSystemInstances$.MODULE$.EXECUTOR(), conf);
         ms.start(BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.METRICS_STATIC_SOURCES_ENABLED())));
         var42 = ms;
      }

      MetricsSystem metricsSystem = var42;
      OutputCommitCoordinator outputCommitCoordinator = (OutputCommitCoordinator)mockOutputCommitCoordinator.getOrElse(() -> new OutputCommitCoordinator(conf, isDriver));
      RpcEndpointRef outputCommitCoordinatorRef = this.registerOrLookupEndpoint$1("OutputCommitCoordinator", () -> new OutputCommitCoordinator.OutputCommitCoordinatorEndpoint(rpcEnv, outputCommitCoordinator), isDriver, rpcEnv, conf);
      outputCommitCoordinator.coordinatorRef_$eq(new Some(outputCommitCoordinatorRef));
      SparkEnv envInstance = new SparkEnv(executorId, rpcEnv, serializer, closureSerializer, serializerManager, mapOutputTracker, broadcastManager, blockManager, securityManager, metricsSystem, outputCommitCoordinator, conf);
      if (isDriver) {
         String sparkFilesDir = Utils$.MODULE$.createTempDir(Utils$.MODULE$.getLocalDir(conf), "userFiles").getAbsolutePath();
         envInstance.driverTmpDir_$eq(new Some(sparkFilesDir));
      }

      return envInstance;
   }

   public Option createDriverEnv$default$5() {
      return scala.None..MODULE$;
   }

   private LiveListenerBus create$default$9() {
      return null;
   }

   private Option create$default$10() {
      return scala.None..MODULE$;
   }

   public scala.collection.immutable.Map environmentDetails(final SparkConf conf, final Configuration hadoopConf, final String schedulingMode, final Seq addedJars, final Seq addedFiles, final Seq addedArchives, final scala.collection.immutable.Map metricsProperties) {
      String var10005 = scala.util.Properties..MODULE$.javaVersion();
      Seq jvmInformation = (Seq)(new scala.collection.immutable..colon.colon(new Tuple2("Java Version", var10005 + " (" + scala.util.Properties..MODULE$.javaVendor() + ")"), new scala.collection.immutable..colon.colon(new Tuple2("Java Home", scala.util.Properties..MODULE$.javaHome()), new scala.collection.immutable..colon.colon(new Tuple2("Scala Version", scala.util.Properties..MODULE$.versionString()), scala.collection.immutable.Nil..MODULE$)))).sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.String..MODULE$, scala.math.Ordering.String..MODULE$));
      Seq schedulerMode = (Seq)(!conf.contains(org.apache.spark.internal.config.package$.MODULE$.SCHEDULER_MODE()) ? new scala.collection.immutable..colon.colon(new Tuple2(org.apache.spark.internal.config.package$.MODULE$.SCHEDULER_MODE().key(), schedulingMode), scala.collection.immutable.Nil..MODULE$) : (Seq)scala.package..MODULE$.Seq().empty());
      Tuple2[] sparkProperties = (Tuple2[])scala.collection.ArrayOps..MODULE$.sorted$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.$plus$plus$extension(.MODULE$.refArrayOps((Object[])conf.getAll()), schedulerMode, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.String..MODULE$, scala.math.Ordering.String..MODULE$));
      Seq systemProperties = Utils$.MODULE$.getSystemProperties().toSeq();
      Seq otherProperties = (Seq)((SeqOps)systemProperties.filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$environmentDetails$1(x0$1)))).sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.String..MODULE$, scala.math.Ordering.String..MODULE$));
      Tuple2[] classPathEntries = (Tuple2[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filterNot$extension(.MODULE$.refArrayOps((Object[])scala.util.Properties..MODULE$.javaClassPath().split(File.pathSeparator)), (x$8) -> BoxesRunTime.boxToBoolean($anonfun$environmentDetails$2(x$8)))), (x$9) -> new Tuple2(x$9, "System Classpath"), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      Seq addedJarsAndFiles = (Seq)((IterableOps)((IterableOps)addedJars.$plus$plus(addedFiles)).$plus$plus(addedArchives)).map((x$10) -> new Tuple2(x$10, "Added By User"));
      Seq classPaths = (Seq)((SeqOps)addedJarsAndFiles.$plus$plus(.MODULE$.wrapRefArray((Object[])classPathEntries))).sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.String..MODULE$, scala.math.Ordering.String..MODULE$));
      Seq hadoopProperties = (Seq)((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(hadoopConf).asScala().map((entry) -> new Tuple2(entry.getKey(), entry.getValue()))).toSeq().sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.String..MODULE$, scala.math.Ordering.String..MODULE$));
      return (scala.collection.immutable.Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("JVM Information"), jvmInformation), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("Spark Properties"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(sparkProperties).toImmutableArraySeq()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("Hadoop Properties"), hadoopProperties), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("System Properties"), otherProperties), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("Classpath Entries"), classPaths), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("Metrics Properties"), metricsProperties.toSeq().sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.String..MODULE$, scala.math.Ordering.String..MODULE$)))})));
   }

   // $FF: synthetic method
   public static final void $anonfun$create$2(final SecurityManager securityManager$1, final byte[] x$7) {
      if (!securityManager$1.isEncryptionEnabled() && !securityManager$1.isSslRpcEnabled()) {
         MODULE$.logWarning((Function0)(() -> "I/O encryption enabled without RPC encryption: keys will be visible on the wire."));
      }
   }

   private final RpcEndpointRef registerOrLookupEndpoint$1(final String name, final Function0 endpointCreator, final boolean isDriver$1, final RpcEnv rpcEnv$1, final SparkConf conf$1) {
      if (isDriver$1) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Registering ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ENDPOINT_NAME..MODULE$, name)})))));
         return rpcEnv$1.setupEndpoint(name, (RpcEndpoint)endpointCreator.apply());
      } else {
         return RpcUtils$.MODULE$.makeDriverRef(name, conf$1, rpcEnv$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$environmentDetails$1(final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         boolean var10000;
         label22: {
            String k = (String)x0$1._1();
            String var4 = "java.class.path";
            if (k == null) {
               if (var4 == null) {
                  break label22;
               }
            } else if (k.equals(var4)) {
               break label22;
            }

            if (!k.startsWith("spark.")) {
               var10000 = true;
               return var10000;
            }
         }

         var10000 = false;
         return var10000;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$environmentDetails$2(final String x$8) {
      return x$8.isEmpty();
   }

   private SparkEnv$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
