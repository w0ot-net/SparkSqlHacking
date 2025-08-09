package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import java.net.URL;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.spark.deploy.LocalSparkCluster;
import org.apache.spark.deploy.LocalSparkCluster$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.OptionalConfigEntry;
import org.apache.spark.internal.config.Tests$;
import org.apache.spark.launcher.JavaModuleOptions;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.resource.ResourceUtils$;
import org.apache.spark.scheduler.ExternalClusterManager;
import org.apache.spark.scheduler.SchedulerBackend;
import org.apache.spark.scheduler.TaskScheduler;
import org.apache.spark.scheduler.TaskSchedulerImpl;
import org.apache.spark.scheduler.TaskSchedulerImpl$;
import org.apache.spark.scheduler.cluster.StandaloneSchedulerBackend;
import org.apache.spark.scheduler.local.LocalSchedulerBackend;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.LinearSeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class SparkContext$ implements Logging {
   public static final SparkContext$ MODULE$ = new SparkContext$();
   private static final Set VALID_LOG_LEVELS;
   private static final Object SPARK_CONTEXT_CONSTRUCTOR_LOCK;
   private static final AtomicReference org$apache$spark$SparkContext$$activeContext;
   private static Option contextBeingConstructed;
   private static final String SPARK_JOB_DESCRIPTION;
   private static final String SPARK_JOB_GROUP_ID;
   private static final String SPARK_JOB_INTERRUPT_ON_CANCEL;
   private static final String SPARK_JOB_TAGS;
   private static final String SPARK_SCHEDULER_POOL;
   private static final String RDD_SCOPE_KEY;
   private static final String RDD_SCOPE_NO_OVERRIDE_KEY;
   private static final String DRIVER_IDENTIFIER;
   private static final String SPARK_JOB_TAGS_SEP;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      VALID_LOG_LEVELS = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"ALL", "DEBUG", "ERROR", "FATAL", "INFO", "OFF", "TRACE", "WARN"})));
      SPARK_CONTEXT_CONSTRUCTOR_LOCK = new Object();
      org$apache$spark$SparkContext$$activeContext = new AtomicReference((Object)null);
      contextBeingConstructed = scala.None..MODULE$;
      SPARK_JOB_DESCRIPTION = "spark.job.description";
      SPARK_JOB_GROUP_ID = "spark.jobGroup.id";
      SPARK_JOB_INTERRUPT_ON_CANCEL = "spark.job.interruptOnCancel";
      SPARK_JOB_TAGS = "spark.job.tags";
      SPARK_SCHEDULER_POOL = "spark.scheduler.pool";
      RDD_SCOPE_KEY = "spark.rdd.scope";
      RDD_SCOPE_NO_OVERRIDE_KEY = "spark.rdd.scope.noOverride";
      DRIVER_IDENTIFIER = "driver";
      SPARK_JOB_TAGS_SEP = ",";
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

   public String $lessinit$greater$default$3() {
      return null;
   }

   public Seq $lessinit$greater$default$4() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   public scala.collection.Map $lessinit$greater$default$5() {
      return (scala.collection.Map)scala.collection.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }

   public Set VALID_LOG_LEVELS() {
      return VALID_LOG_LEVELS;
   }

   private Object SPARK_CONTEXT_CONSTRUCTOR_LOCK() {
      return SPARK_CONTEXT_CONSTRUCTOR_LOCK;
   }

   public AtomicReference org$apache$spark$SparkContext$$activeContext() {
      return org$apache$spark$SparkContext$$activeContext;
   }

   private Option contextBeingConstructed() {
      return contextBeingConstructed;
   }

   private void contextBeingConstructed_$eq(final Option x$1) {
      contextBeingConstructed = x$1;
   }

   private void assertNoOtherContextIsRunning(final SparkContext sc) {
      synchronized(this.SPARK_CONTEXT_CONSTRUCTOR_LOCK()){}

      try {
         scala.Option..MODULE$.apply(this.org$apache$spark$SparkContext$$activeContext().get()).filter((x$65) -> BoxesRunTime.boxToBoolean($anonfun$assertNoOtherContextIsRunning$1(sc, x$65))).foreach((ctx) -> {
            String errMsg = "Only one SparkContext should be running in this JVM (see SPARK-2243).The currently running SparkContext was created at:\n" + ctx.org$apache$spark$SparkContext$$creationSite().longForm();
            throw new SparkException(errMsg);
         });
         this.contextBeingConstructed().filter((x$66) -> BoxesRunTime.boxToBoolean($anonfun$assertNoOtherContextIsRunning$3(sc, x$66))).foreach((otherContext) -> {
            $anonfun$assertNoOtherContextIsRunning$4(otherContext);
            return BoxedUnit.UNIT;
         });
      } catch (Throwable var4) {
         throw var4;
      }

   }

   public void org$apache$spark$SparkContext$$assertOnDriver() {
      if (Utils$.MODULE$.isInRunningSparkTask()) {
         throw new IllegalStateException("SparkContext should only be created and accessed on the driver.");
      }
   }

   public SparkContext getOrCreate(final SparkConf config) {
      synchronized(this.SPARK_CONTEXT_CONSTRUCTOR_LOCK()){}

      SparkContext var3;
      try {
         if (this.org$apache$spark$SparkContext$$activeContext().get() == null) {
            this.setActiveContext(new SparkContext(config));
         } else if (scala.collection.ArrayOps..MODULE$.nonEmpty$extension(.MODULE$.refArrayOps((Object[])config.getAll()))) {
            this.logWarning((Function0)(() -> "Using an existing SparkContext; some configuration may not take effect."));
         }

         var3 = (SparkContext)this.org$apache$spark$SparkContext$$activeContext().get();
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   public SparkContext getOrCreate() {
      synchronized(this.SPARK_CONTEXT_CONSTRUCTOR_LOCK()){}

      SparkContext var2;
      try {
         if (this.org$apache$spark$SparkContext$$activeContext().get() == null) {
            this.setActiveContext(new SparkContext());
         }

         var2 = (SparkContext)this.org$apache$spark$SparkContext$$activeContext().get();
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   public Option getActive() {
      synchronized(this.SPARK_CONTEXT_CONSTRUCTOR_LOCK()){}

      Option var2;
      try {
         var2 = scala.Option..MODULE$.apply(this.org$apache$spark$SparkContext$$activeContext().get());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   public void markPartiallyConstructed(final SparkContext sc) {
      synchronized(this.SPARK_CONTEXT_CONSTRUCTOR_LOCK()){}

      try {
         this.assertNoOtherContextIsRunning(sc);
         this.contextBeingConstructed_$eq(new Some(sc));
      } catch (Throwable var4) {
         throw var4;
      }

   }

   public void setActiveContext(final SparkContext sc) {
      synchronized(this.SPARK_CONTEXT_CONSTRUCTOR_LOCK()){}

      try {
         this.assertNoOtherContextIsRunning(sc);
         this.contextBeingConstructed_$eq(scala.None..MODULE$);
         this.org$apache$spark$SparkContext$$activeContext().set(sc);
      } catch (Throwable var4) {
         throw var4;
      }

   }

   public void clearActiveContext() {
      synchronized(this.SPARK_CONTEXT_CONSTRUCTOR_LOCK()){}

      try {
         this.org$apache$spark$SparkContext$$activeContext().set((Object)null);
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public String SPARK_JOB_DESCRIPTION() {
      return SPARK_JOB_DESCRIPTION;
   }

   public String SPARK_JOB_GROUP_ID() {
      return SPARK_JOB_GROUP_ID;
   }

   public String SPARK_JOB_INTERRUPT_ON_CANCEL() {
      return SPARK_JOB_INTERRUPT_ON_CANCEL;
   }

   public String SPARK_JOB_TAGS() {
      return SPARK_JOB_TAGS;
   }

   public String SPARK_SCHEDULER_POOL() {
      return SPARK_SCHEDULER_POOL;
   }

   public String RDD_SCOPE_KEY() {
      return RDD_SCOPE_KEY;
   }

   public String RDD_SCOPE_NO_OVERRIDE_KEY() {
      return RDD_SCOPE_NO_OVERRIDE_KEY;
   }

   public String DRIVER_IDENTIFIER() {
      return DRIVER_IDENTIFIER;
   }

   public String SPARK_JOB_TAGS_SEP() {
      return SPARK_JOB_TAGS_SEP;
   }

   public void throwIfInvalidTag(final String tag) {
      if (tag == null) {
         throw new IllegalArgumentException("Spark job tag cannot be null.");
      } else if (tag.contains(this.SPARK_JOB_TAGS_SEP())) {
         throw new IllegalArgumentException("Spark job tag cannot contain '" + this.SPARK_JOB_TAGS_SEP() + "'.");
      } else if (tag.isEmpty()) {
         throw new IllegalArgumentException("Spark job tag cannot be an empty string.");
      }
   }

   public Option jarOfClass(final Class cls) {
      String var10001 = cls.getName();
      URL uri = cls.getResource("/" + var10001.replace('.', '/') + ".class");
      if (uri != null) {
         String uriStr = uri.toString();
         return (Option)(uriStr.startsWith("jar:file:") ? new Some(uriStr.substring("jar:file:".length(), uriStr.indexOf(33))) : scala.None..MODULE$);
      } else {
         return scala.None..MODULE$;
      }
   }

   public Option jarOfObject(final Object obj) {
      return this.jarOfClass(obj.getClass());
   }

   public SparkConf updatedConf(final SparkConf conf, final String master, final String appName, final String sparkHome, final Seq jars, final scala.collection.Map environment) {
      SparkConf res = conf.clone();
      res.setMaster(master);
      res.setAppName(appName);
      if (sparkHome != null) {
         res.setSparkHome(sparkHome);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (jars != null && !jars.isEmpty()) {
         res.setJars(jars);
      } else {
         BoxedUnit var8 = BoxedUnit.UNIT;
      }

      res.setExecutorEnv(environment.toSeq());
      return res;
   }

   public String updatedConf$default$4() {
      return null;
   }

   public Seq updatedConf$default$5() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   public scala.collection.Map updatedConf$default$6() {
      return (scala.collection.Map)scala.collection.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }

   public int numDriverCores(final String master) {
      return this.numDriverCores(master, (SparkConf)null);
   }

   public int numDriverCores(final String master, final SparkConf conf) {
      if ("local".equals(master)) {
         return 1;
      } else {
         if (master != null) {
            Option var6 = SparkMasterRegex$.MODULE$.LOCAL_N_REGEX().unapplySeq(master);
            if (!var6.isEmpty() && var6.get() != null && ((List)var6.get()).lengthCompare(1) == 0) {
               String threads = (String)((LinearSeqOps)var6.get()).apply(0);
               return convertToInt$1(threads);
            }
         }

         if (master != null) {
            Option var8 = SparkMasterRegex$.MODULE$.LOCAL_N_FAILURES_REGEX().unapplySeq(master);
            if (!var8.isEmpty() && var8.get() != null && ((List)var8.get()).lengthCompare(2) == 0) {
               String threads = (String)((LinearSeqOps)var8.get()).apply(0);
               return convertToInt$1(threads);
            }
         }

         boolean var10000;
         if ("yarn".equals(master)) {
            var10000 = true;
         } else {
            label65: {
               if (master != null) {
                  Option var10 = SparkMasterRegex$.MODULE$.KUBERNETES_REGEX().unapplySeq(master);
                  if (!var10.isEmpty() && var10.get() != null && ((List)var10.get()).lengthCompare(1) == 0) {
                     var10000 = true;
                     break label65;
                  }
               }

               var10000 = false;
            }
         }

         if (!var10000) {
            return 0;
         } else {
            if (conf != null) {
               Object var12 = conf.get(org.apache.spark.internal.config.package$.MODULE$.SUBMIT_DEPLOY_MODE());
               String var11 = "cluster";
               if (var12 == null) {
                  if (var11 == null) {
                     return conf.getInt(org.apache.spark.internal.config.package$.MODULE$.DRIVER_CORES().key(), 0);
                  }
               } else if (var12.equals(var11)) {
                  return conf.getInt(org.apache.spark.internal.config.package$.MODULE$.DRIVER_CORES().key(), 0);
               }
            }

            return 0;
         }
      }
   }

   public int executorMemoryInMb(final SparkConf conf) {
      return BoxesRunTime.unboxToInt(conf.getOption(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_MEMORY().key()).orElse(() -> scala.Option..MODULE$.apply(System.getenv("SPARK_EXECUTOR_MEMORY"))).orElse(() -> scala.Option..MODULE$.apply(System.getenv("SPARK_MEM")).map((value) -> MODULE$.warnSparkMem(value))).map((str) -> BoxesRunTime.boxToInteger($anonfun$executorMemoryInMb$4(str))).getOrElse((JFunction0.mcI.sp)() -> 1024));
   }

   private String warnSparkMem(final String value) {
      this.logWarning((Function0)(() -> "Using SPARK_MEM to set amount of memory to use per executor process is deprecated, please use spark.executor.memory instead."));
      return value;
   }

   public Tuple2 org$apache$spark$SparkContext$$createTaskScheduler(final SparkContext sc, final String master) {
      int MAX_LOCAL_TASK_FAILURES = 1;
      String var7 = master;
      if ("local".equals(master)) {
         checkResourcesPerTask$1(1, sc);
         TaskSchedulerImpl scheduler = new TaskSchedulerImpl(sc, MAX_LOCAL_TASK_FAILURES, true, TaskSchedulerImpl$.MODULE$.$lessinit$greater$default$4());
         LocalSchedulerBackend backend = new LocalSchedulerBackend(sc.getConf(), scheduler, 1);
         scheduler.initialize(backend);
         return new Tuple2(backend, scheduler);
      } else {
         if (master != null) {
            Option var10 = SparkMasterRegex$.MODULE$.LOCAL_N_REGEX().unapplySeq(master);
            if (!var10.isEmpty() && var10.get() != null && ((List)var10.get()).lengthCompare(1) == 0) {
               int var48;
               label106: {
                  label105: {
                     String threads = (String)((LinearSeqOps)var10.get()).apply(0);
                     String var13 = "*";
                     if (threads == null) {
                        if (var13 == null) {
                           break label105;
                        }
                     } else if (threads.equals(var13)) {
                        break label105;
                     }

                     var48 = scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(threads));
                     break label106;
                  }

                  var48 = localCpuCount$1();
               }

               int threadCount = var48;
               if (threadCount <= 0) {
                  throw new SparkException("Asked to run locally with " + threadCount + " threads");
               }

               checkResourcesPerTask$1(threadCount, sc);
               TaskSchedulerImpl scheduler = new TaskSchedulerImpl(sc, MAX_LOCAL_TASK_FAILURES, true, TaskSchedulerImpl$.MODULE$.$lessinit$greater$default$4());
               LocalSchedulerBackend backend = new LocalSchedulerBackend(sc.getConf(), scheduler, threadCount);
               scheduler.initialize(backend);
               return new Tuple2(backend, scheduler);
            }
         }

         if (master != null) {
            Option var16 = SparkMasterRegex$.MODULE$.LOCAL_N_FAILURES_REGEX().unapplySeq(master);
            if (!var16.isEmpty() && var16.get() != null && ((List)var16.get()).lengthCompare(2) == 0) {
               String maxFailures;
               int var10000;
               label119: {
                  label118: {
                     String threads = (String)((LinearSeqOps)var16.get()).apply(0);
                     maxFailures = (String)((LinearSeqOps)var16.get()).apply(1);
                     String var20 = "*";
                     if (threads == null) {
                        if (var20 == null) {
                           break label118;
                        }
                     } else if (threads.equals(var20)) {
                        break label118;
                     }

                     var10000 = scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(threads));
                     break label119;
                  }

                  var10000 = localCpuCount$2();
               }

               int threadCount = var10000;
               checkResourcesPerTask$1(threadCount, sc);
               TaskSchedulerImpl scheduler = new TaskSchedulerImpl(sc, scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(maxFailures)), true, TaskSchedulerImpl$.MODULE$.$lessinit$greater$default$4());
               LocalSchedulerBackend backend = new LocalSchedulerBackend(sc.getConf(), scheduler, threadCount);
               scheduler.initialize(backend);
               return new Tuple2(backend, scheduler);
            }
         }

         if (master != null) {
            Option var23 = SparkMasterRegex$.MODULE$.SPARK_REGEX().unapplySeq(master);
            if (!var23.isEmpty() && var23.get() != null && ((List)var23.get()).lengthCompare(1) == 0) {
               String sparkUrl = (String)((LinearSeqOps)var23.get()).apply(0);
               TaskSchedulerImpl scheduler = new TaskSchedulerImpl(sc);
               String[] masterUrls = (String[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])sparkUrl.split(",")), (x$68) -> "spark://" + x$68, scala.reflect.ClassTag..MODULE$.apply(String.class));
               StandaloneSchedulerBackend backend = new StandaloneSchedulerBackend(scheduler, sc, masterUrls);
               scheduler.initialize(backend);
               return new Tuple2(backend, scheduler);
            }
         }

         if (master != null) {
            Option var28 = SparkMasterRegex$.MODULE$.LOCAL_CLUSTER_REGEX().unapplySeq(master);
            if (!var28.isEmpty() && var28.get() != null && ((List)var28.get()).lengthCompare(3) == 0) {
               String numWorkers = (String)((LinearSeqOps)var28.get()).apply(0);
               String coresPerWorker = (String)((LinearSeqOps)var28.get()).apply(1);
               String memoryPerWorker = (String)((LinearSeqOps)var28.get()).apply(2);
               checkResourcesPerTask$1(scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(coresPerWorker)), sc);
               int memoryPerWorkerInt = scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(memoryPerWorker));
               if (sc.executorMemory() > memoryPerWorkerInt) {
                  throw new SparkException(scala.collection.StringOps..MODULE$.format$extension(.MODULE$.augmentString("Asked to launch cluster with %d MiB/worker but requested %d MiB/executor"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(memoryPerWorkerInt), BoxesRunTime.boxToInteger(sc.executorMemory())})));
               }

               sc.conf().setIfMissing((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_HOST_LOCAL_DISK_READING_ENABLED(), (Object)BoxesRunTime.boxToBoolean(false));
               TaskSchedulerImpl scheduler = new TaskSchedulerImpl(sc);
               LocalSparkCluster localCluster = LocalSparkCluster$.MODULE$.apply(scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(numWorkers)), scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(coresPerWorker)), memoryPerWorkerInt, sc.conf());
               String[] masterUrls = localCluster.start();
               StandaloneSchedulerBackend backend = new StandaloneSchedulerBackend(scheduler, sc, masterUrls);
               scheduler.initialize(backend);
               backend.shutdownCallback_$eq((backendx) -> {
                  $anonfun$createTaskScheduler$2(localCluster, backendx);
                  return BoxedUnit.UNIT;
               });
               return new Tuple2(backend, scheduler);
            }
         }

         Option var38 = this.getClusterManager(master);
         if (var38 instanceof Some) {
            Some var39 = (Some)var38;
            ExternalClusterManager clusterMgr = (ExternalClusterManager)var39.value();
            ExternalClusterManager cm = clusterMgr;

            try {
               TaskScheduler scheduler = cm.createTaskScheduler(sc, var7);
               SchedulerBackend backend = cm.createSchedulerBackend(sc, var7, scheduler);
               cm.initialize(scheduler, backend);
               return new Tuple2(backend, scheduler);
            } catch (Throwable var47) {
               if (var47 instanceof SparkException) {
                  SparkException var45 = (SparkException)var47;
                  throw var45;
               } else if (var47 != null && scala.util.control.NonFatal..MODULE$.apply(var47)) {
                  throw new SparkException("External scheduler cannot be instantiated", var47);
               } else {
                  throw var47;
               }
            }
         } else if (scala.None..MODULE$.equals(var38)) {
            throw new SparkException("Could not parse Master URL: '" + master + "'");
         } else {
            throw new MatchError(var38);
         }
      }
   }

   private Option getClusterManager(final String url) {
      ClassLoader loader = Utils$.MODULE$.getContextOrSparkClassLoader();
      Iterable serviceLoaders = (Iterable)scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(ServiceLoader.load(ExternalClusterManager.class, loader)).asScala().filter((x$69) -> BoxesRunTime.boxToBoolean($anonfun$getClusterManager$1(url, x$69)));
      if (serviceLoaders.size() > 1) {
         throw new SparkException("Multiple external cluster managers registered for the url " + url + ": " + serviceLoaders);
      } else {
         return serviceLoaders.headOption();
      }
   }

   public void org$apache$spark$SparkContext$$fillMissingMagicCommitterConfsIfNeeded(final SparkConf conf) {
      Tuple2[] magicCommitterConfs = (Tuple2[])scala.collection.ArrayOps..MODULE$.filter$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(.MODULE$.refArrayOps((Object[])conf.getAllWithPrefix("spark.hadoop.fs.s3a.bucket.")), (x$70) -> BoxesRunTime.boxToBoolean($anonfun$fillMissingMagicCommitterConfsIfNeeded$1(x$70)))), (x$71) -> BoxesRunTime.boxToBoolean($anonfun$fillMissingMagicCommitterConfsIfNeeded$2(x$71)));
      if (scala.collection.ArrayOps..MODULE$.nonEmpty$extension(.MODULE$.refArrayOps((Object[])magicCommitterConfs))) {
         conf.setIfMissing("spark.hadoop.fs.s3a.committer.magic.enabled", "true");
         if (conf.get("spark.hadoop.fs.s3a.committer.magic.enabled").equals("true")) {
            conf.setIfMissing("spark.hadoop.fs.s3a.committer.name", "magic");
            conf.setIfMissing("spark.hadoop.mapreduce.outputcommitter.factory.scheme.s3a", "org.apache.hadoop.fs.s3a.commit.S3ACommitterFactory");
            conf.setIfMissing("spark.sql.parquet.output.committer.class", "org.apache.spark.internal.io.cloud.BindingParquetOutputCommitter");
            conf.setIfMissing("spark.sql.sources.commitProtocolClass", "org.apache.spark.internal.io.cloud.PathOutputCommitProtocol");
         }
      }
   }

   public void org$apache$spark$SparkContext$$supplementJavaModuleOptions(final SparkConf conf) {
      supplement$1(org.apache.spark.internal.config.package$.MODULE$.DRIVER_JAVA_OPTIONS(), conf);
      supplement$1(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_JAVA_OPTIONS(), conf);
   }

   public void org$apache$spark$SparkContext$$supplementJavaIPv6Options(final SparkConf conf) {
      supplement$2(org.apache.spark.internal.config.package$.MODULE$.DRIVER_JAVA_OPTIONS(), conf);
      supplement$2(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_JAVA_OPTIONS(), conf);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$assertNoOtherContextIsRunning$1(final SparkContext sc$1, final SparkContext x$65) {
      return x$65 != sc$1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$assertNoOtherContextIsRunning$3(final SparkContext sc$1, final SparkContext x$66) {
      return x$66 != sc$1;
   }

   // $FF: synthetic method
   public static final void $anonfun$assertNoOtherContextIsRunning$4(final SparkContext otherContext) {
      String otherContextCreationSite = (String)scala.Option..MODULE$.apply(otherContext.org$apache$spark$SparkContext$$creationSite()).map((x$67) -> x$67.longForm()).getOrElse(() -> "unknown location");
      MessageWithContext warnMsg = MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Another SparkContext is being constructed (or threw an exception in its"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" constructor). This may indicate an error, since only one SparkContext should be"})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" running in this JVM (see SPARK-2243)."})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" The other SparkContext was created at:\\n"})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CREATION_SITE..MODULE$, otherContextCreationSite)}))));
      MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> warnMsg));
   }

   private static final int convertToInt$1(final String threads) {
      String var1 = "*";
      if (threads == null) {
         if (var1 == null) {
            return Runtime.getRuntime().availableProcessors();
         }
      } else if (threads.equals(var1)) {
         return Runtime.getRuntime().availableProcessors();
      }

      return scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(threads));
   }

   // $FF: synthetic method
   public static final int $anonfun$executorMemoryInMb$4(final String str) {
      return Utils$.MODULE$.memoryStringToMb(str);
   }

   private static final void checkResourcesPerTask$1(final int executorCores, final SparkContext sc$2) {
      int taskCores = BoxesRunTime.unboxToInt(sc$2.conf().get(org.apache.spark.internal.config.package$.MODULE$.CPUS_PER_TASK()));
      if (!BoxesRunTime.unboxToBoolean(sc$2.conf().get(Tests$.MODULE$.SKIP_VALIDATE_CORES_TESTING()))) {
         BoxesRunTime.boxToBoolean(ResourceUtils$.MODULE$.validateTaskCpusLargeEnough(sc$2.conf(), executorCores, taskCores));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      ResourceProfile defaultProf = sc$2.resourceProfileManager().defaultResourceProfile();
      ResourceUtils$.MODULE$.warnOnWastedResources(defaultProf, sc$2.conf(), new Some(BoxesRunTime.boxToInteger(executorCores)));
   }

   private static final int localCpuCount$1() {
      return Runtime.getRuntime().availableProcessors();
   }

   private static final int localCpuCount$2() {
      return Runtime.getRuntime().availableProcessors();
   }

   // $FF: synthetic method
   public static final void $anonfun$createTaskScheduler$2(final LocalSparkCluster localCluster$1, final StandaloneSchedulerBackend backend) {
      localCluster$1.stop();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getClusterManager$1(final String url$1, final ExternalClusterManager x$69) {
      return x$69.canCreate(url$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fillMissingMagicCommitterConfsIfNeeded$1(final Tuple2 x$70) {
      return ((String)x$70._1()).endsWith(".committer.magic.enabled");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fillMissingMagicCommitterConfsIfNeeded$2(final Tuple2 x$71) {
      return ((String)x$71._2()).equalsIgnoreCase("true");
   }

   private static final void supplement$1(final OptionalConfigEntry key, final SparkConf conf$5) {
      Option var4 = (Option)conf$5.get((ConfigEntry)key);
      String var10000;
      if (var4 instanceof Some var5) {
         String opts = (String)var5.value();
         var10000 = JavaModuleOptions.defaultModuleOptions() + " " + opts;
      } else {
         if (!scala.None..MODULE$.equals(var4)) {
            throw new MatchError(var4);
         }

         var10000 = JavaModuleOptions.defaultModuleOptions();
      }

      String v = var10000;
      conf$5.set(key.key(), v);
   }

   private static final void supplement$2(final OptionalConfigEntry key, final SparkConf conf$6) {
      Option var4 = (Option)conf$6.get((ConfigEntry)key);
      String var10000;
      if (var4 instanceof Some var5) {
         String opts = (String)var5.value();
         var10000 = "-Djava.net.preferIPv6Addresses=" + Utils$.MODULE$.preferIPv6() + " " + opts;
      } else {
         if (!scala.None..MODULE$.equals(var4)) {
            throw new MatchError(var4);
         }

         var10000 = "-Djava.net.preferIPv6Addresses=" + Utils$.MODULE$.preferIPv6();
      }

      String v = var10000;
      conf$6.set(key.key(), v);
   }

   private SparkContext$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
