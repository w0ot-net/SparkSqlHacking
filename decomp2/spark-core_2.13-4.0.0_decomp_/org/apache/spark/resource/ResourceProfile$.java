package org.apache.spark.resource;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Python$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

public final class ResourceProfile$ implements Logging, Serializable {
   public static final ResourceProfile$ MODULE$ = new ResourceProfile$();
   private static AtomicInteger nextProfileId;
   private static final String CPUS;
   private static final String CORES;
   private static final String MEMORY;
   private static final String OFFHEAP_MEM;
   private static final String OVERHEAD_MEM;
   private static final String PYSPARK_MEM;
   private static final int UNKNOWN_RESOURCE_PROFILE_ID;
   private static final int DEFAULT_RESOURCE_PROFILE_ID;
   private static final Object DEFAULT_PROFILE_LOCK;
   @GuardedBy("DEFAULT_PROFILE_LOCK")
   private static Option defaultProfile;
   private static Option defaultProfileExecutorResources;
   private static final String PYSPARK_MEMORY_LOCAL_PROPERTY;
   private static final String EXECUTOR_CORES_LOCAL_PROPERTY;
   private static transient Logger org$apache$spark$internal$Logging$$log_;
   private static volatile boolean bitmap$0;

   static {
      Logging.$init$(MODULE$);
      CPUS = "cpus";
      CORES = "cores";
      MEMORY = "memory";
      OFFHEAP_MEM = "offHeap";
      OVERHEAD_MEM = "memoryOverhead";
      PYSPARK_MEM = "pyspark.memory";
      UNKNOWN_RESOURCE_PROFILE_ID = -1;
      DEFAULT_RESOURCE_PROFILE_ID = 0;
      DEFAULT_PROFILE_LOCK = new Object();
      defaultProfile = .MODULE$;
      defaultProfileExecutorResources = .MODULE$;
      PYSPARK_MEMORY_LOCAL_PROPERTY = "resource.pyspark.memory";
      EXECUTOR_CORES_LOCAL_PROPERTY = "resource.executor.cores";
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

   public String CPUS() {
      return CPUS;
   }

   public String CORES() {
      return CORES;
   }

   public String MEMORY() {
      return MEMORY;
   }

   public String OFFHEAP_MEM() {
      return OFFHEAP_MEM;
   }

   public String OVERHEAD_MEM() {
      return OVERHEAD_MEM;
   }

   public String PYSPARK_MEM() {
      return PYSPARK_MEM;
   }

   public String[] allSupportedExecutorResources() {
      return (String[])((Object[])(new String[]{this.CORES(), this.MEMORY(), this.OVERHEAD_MEM(), this.PYSPARK_MEM(), this.OFFHEAP_MEM()}));
   }

   public int UNKNOWN_RESOURCE_PROFILE_ID() {
      return UNKNOWN_RESOURCE_PROFILE_ID;
   }

   public int DEFAULT_RESOURCE_PROFILE_ID() {
      return DEFAULT_RESOURCE_PROFILE_ID;
   }

   private AtomicInteger nextProfileId$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            nextProfileId = new AtomicInteger(0);
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return nextProfileId;
   }

   private AtomicInteger nextProfileId() {
      return !bitmap$0 ? this.nextProfileId$lzycompute() : nextProfileId;
   }

   private Object DEFAULT_PROFILE_LOCK() {
      return DEFAULT_PROFILE_LOCK;
   }

   private Option defaultProfile() {
      return defaultProfile;
   }

   private void defaultProfile_$eq(final Option x$1) {
      defaultProfile = x$1;
   }

   private Option defaultProfileExecutorResources() {
      return defaultProfileExecutorResources;
   }

   private void defaultProfileExecutorResources_$eq(final Option x$1) {
      defaultProfileExecutorResources = x$1;
   }

   public int getNextProfileId() {
      return this.nextProfileId().getAndIncrement();
   }

   public ResourceProfile getOrCreateDefaultProfile(final SparkConf conf) {
      synchronized(this.DEFAULT_PROFILE_LOCK()){}

      ResourceProfile var4;
      try {
         Option var5 = this.defaultProfile();
         ResourceProfile var10000;
         if (var5 instanceof Some var6) {
            ResourceProfile prof = (ResourceProfile)var6.value();
            var10000 = prof;
         } else {
            if (!.MODULE$.equals(var5)) {
               throw new MatchError(var5);
            }

            scala.collection.immutable.Map taskResources = this.getDefaultTaskResources(conf);
            scala.collection.immutable.Map executorResources = this.getDefaultExecutorResources(conf);
            ResourceProfile defProf = new ResourceProfile(executorResources, taskResources);
            defProf.setToDefaultProfile();
            this.defaultProfile_$eq(new Some(defProf));
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Default ResourceProfile created, executor resources: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", task resources: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_RESOURCES..MODULE$, defProf.executorResources())})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_RESOURCES..MODULE$, defProf.taskResources())}))))));
            var10000 = defProf;
         }

         var4 = var10000;
      } catch (Throwable var12) {
         throw var12;
      }

      return var4;
   }

   public ResourceProfile.DefaultProfileExecutorResources getDefaultProfileExecutorResources(final SparkConf conf) {
      return (ResourceProfile.DefaultProfileExecutorResources)this.defaultProfileExecutorResources().getOrElse(() -> {
         MODULE$.getOrCreateDefaultProfile(conf);
         return (ResourceProfile.DefaultProfileExecutorResources)MODULE$.defaultProfileExecutorResources().get();
      });
   }

   private scala.collection.immutable.Map getDefaultTaskResources(final SparkConf conf) {
      int cpusPerTask = BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.CPUS_PER_TASK()));
      TaskResourceRequests treqs = (new TaskResourceRequests()).cpus(cpusPerTask);
      ResourceUtils$.MODULE$.addTaskResourceRequests(conf, treqs);
      return treqs.requests();
   }

   private scala.collection.immutable.Map getDefaultExecutorResources(final SparkConf conf) {
      ExecutorResourceRequests ereqs = new ExecutorResourceRequests();
      boolean isStandalone = conf.getOption("spark.master").exists((x$10) -> BoxesRunTime.boxToBoolean($anonfun$getDefaultExecutorResources$1(x$10)));
      boolean isLocalCluster = conf.getOption("spark.master").exists((x$11) -> BoxesRunTime.boxToBoolean($anonfun$getDefaultExecutorResources$2(x$11)));
      Option cores = (Option)(!isStandalone && !isLocalCluster ? new Some(conf.get(package$.MODULE$.EXECUTOR_CORES())) : conf.getOption(package$.MODULE$.EXECUTOR_CORES().key()).map((x$12) -> BoxesRunTime.boxToInteger($anonfun$getDefaultExecutorResources$3(x$12))));
      cores.foreach((amount) -> $anonfun$getDefaultExecutorResources$4(ereqs, BoxesRunTime.unboxToInt(amount)));
      long memory = !isStandalone && !isLocalCluster ? BoxesRunTime.unboxToLong(conf.get(package$.MODULE$.EXECUTOR_MEMORY())) : (long)SparkContext$.MODULE$.executorMemoryInMb(conf);
      ereqs.memory(Long.toString(memory));
      Option overheadMem = (Option)conf.get((ConfigEntry)package$.MODULE$.EXECUTOR_MEMORY_OVERHEAD());
      overheadMem.map((mem) -> $anonfun$getDefaultExecutorResources$5(ereqs, BoxesRunTime.unboxToLong(mem)));
      Option pysparkMem = (Option)conf.get((ConfigEntry)Python$.MODULE$.PYSPARK_EXECUTOR_MEMORY());
      pysparkMem.map((mem) -> $anonfun$getDefaultExecutorResources$6(ereqs, BoxesRunTime.unboxToLong(mem)));
      int offheapMem = Utils$.MODULE$.executorOffHeapMemorySizeAsMb(conf);
      ereqs.offHeapMemory(Integer.toString(offheapMem));
      Seq execReq = ResourceUtils$.MODULE$.parseAllResourceRequests(conf, package$.MODULE$.SPARK_EXECUTOR_PREFIX());
      execReq.foreach((req) -> ereqs.resource(req.id().resourceName(), req.amount(), (String)req.discoveryScript().orElse(""), (String)req.vendor().orElse("")));
      Set customResourceNames = ((IterableOnceOps)execReq.map((x$13) -> x$13.id().resourceName())).toSet();
      scala.collection.immutable.Map customResources = (scala.collection.immutable.Map)ereqs.requests().filter((v) -> BoxesRunTime.boxToBoolean($anonfun$getDefaultExecutorResources$9(customResourceNames, v)));
      this.defaultProfileExecutorResources_$eq(new Some(new ResourceProfile.DefaultProfileExecutorResources(cores, memory, (long)offheapMem, pysparkMem, overheadMem, customResources)));
      return ereqs.requests();
   }

   public ResourceProfile reInitDefaultProfile(final SparkConf conf) {
      this.clearDefaultProfile();
      return this.getOrCreateDefaultProfile(conf);
   }

   public void clearDefaultProfile() {
      synchronized(this.DEFAULT_PROFILE_LOCK()){}

      try {
         this.defaultProfile_$eq(.MODULE$);
         this.defaultProfileExecutorResources_$eq(.MODULE$);
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public int getTaskCpusOrDefaultForProfile(final ResourceProfile rp, final SparkConf conf) {
      return BoxesRunTime.unboxToInt(rp.getTaskCpus().getOrElse((JFunction0.mcI.sp)() -> BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.CPUS_PER_TASK()))));
   }

   public long executorOffHeapMemorySizeAsMb(final SparkConf sparkConf, final ExecutorResourceRequest execRequest) {
      return Utils$.MODULE$.checkOffHeapEnabled(sparkConf, execRequest.amount());
   }

   public long calculateOverHeadMemory(final Option overHeadMemFromConf, final long minimumOverHeadMemoryFromConf, final long executorMemoryMiB, final double overheadFactor) {
      return BoxesRunTime.unboxToLong(overHeadMemFromConf.getOrElse((JFunction0.mcJ.sp)() -> scala.math.package..MODULE$.max((long)((int)(overheadFactor * (double)executorMemoryMiB)), minimumOverHeadMemoryFromConf)));
   }

   public ResourceProfile.ExecutorResourcesOrDefaults getResourcesForClusterManager(final int rpId, final scala.collection.immutable.Map execResources, final long minimumOverheadMemory, final double overheadFactor, final SparkConf conf, final boolean isPythonApp, final scala.collection.immutable.Map resourceMappings) {
      ResourceProfile.DefaultProfileExecutorResources defaultResources = this.getDefaultProfileExecutorResources(conf);
      ObjectRef cores = ObjectRef.create(defaultResources.cores());
      LongRef executorMemoryMiB = LongRef.create(defaultResources.executorMemoryMiB());
      LongRef memoryOffHeapMiB = LongRef.create(defaultResources.memoryOffHeapMiB());
      LongRef pysparkMemoryMiB = LongRef.create(BoxesRunTime.unboxToLong(defaultResources.pysparkMemoryMiB().getOrElse((JFunction0.mcJ.sp)() -> 0L)));
      LongRef memoryOverheadMiB = LongRef.create(this.calculateOverHeadMemory(defaultResources.memoryOverheadMiB(), minimumOverheadMemory, executorMemoryMiB.elem, overheadFactor));
      scala.collection.immutable.Map var10000;
      if (rpId != this.DEFAULT_RESOURCE_PROFILE_ID()) {
         HashMap customResources = new HashMap();
         execResources.foreach((x0$1) -> {
            $anonfun$getResourcesForClusterManager$2(executorMemoryMiB, memoryOverheadMiB, pysparkMemoryMiB, memoryOffHeapMiB, conf, cores, resourceMappings, customResources, x0$1);
            return BoxedUnit.UNIT;
         });
         var10000 = customResources.toMap(scala..less.colon.less..MODULE$.refl());
      } else {
         var10000 = (scala.collection.immutable.Map)defaultResources.customResources().map((x0$2) -> {
            if (x0$2 != null) {
               String rName = (String)x0$2._1();
               ExecutorResourceRequest execReq = (ExecutorResourceRequest)x0$2._2();
               String nameToUse = (String)resourceMappings.getOrElse(rName, () -> rName);
               return new Tuple2(nameToUse, execReq);
            } else {
               throw new MatchError(x0$2);
            }
         });
      }

      scala.collection.immutable.Map finalCustomResources = var10000;
      long pysparkMemToUseMiB = isPythonApp ? pysparkMemoryMiB.elem : 0L;
      long totalMemMiB = executorMemoryMiB.elem + memoryOverheadMiB.elem + memoryOffHeapMiB.elem + pysparkMemToUseMiB;
      return new ResourceProfile.ExecutorResourcesOrDefaults((Option)cores.elem, executorMemoryMiB.elem, memoryOffHeapMiB.elem, pysparkMemToUseMiB, memoryOverheadMiB.elem, totalMemMiB, finalCustomResources);
   }

   public String PYSPARK_MEMORY_LOCAL_PROPERTY() {
      return PYSPARK_MEMORY_LOCAL_PROPERTY;
   }

   public String EXECUTOR_CORES_LOCAL_PROPERTY() {
      return EXECUTOR_CORES_LOCAL_PROPERTY;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ResourceProfile$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getDefaultExecutorResources$1(final String x$10) {
      return x$10.startsWith("spark://");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getDefaultExecutorResources$2(final String x$11) {
      return x$11.startsWith("local-cluster");
   }

   // $FF: synthetic method
   public static final int $anonfun$getDefaultExecutorResources$3(final String x$12) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$12));
   }

   // $FF: synthetic method
   public static final ExecutorResourceRequests $anonfun$getDefaultExecutorResources$4(final ExecutorResourceRequests ereqs$1, final int amount) {
      return ereqs$1.cores(amount);
   }

   // $FF: synthetic method
   public static final ExecutorResourceRequests $anonfun$getDefaultExecutorResources$5(final ExecutorResourceRequests ereqs$1, final long mem) {
      return ereqs$1.memoryOverhead(Long.toString(mem));
   }

   // $FF: synthetic method
   public static final ExecutorResourceRequests $anonfun$getDefaultExecutorResources$6(final ExecutorResourceRequests ereqs$1, final long mem) {
      return ereqs$1.pysparkMemory(Long.toString(mem));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getDefaultExecutorResources$9(final Set customResourceNames$1, final Tuple2 v) {
      return customResourceNames$1.contains(v._1());
   }

   // $FF: synthetic method
   public static final void $anonfun$getResourcesForClusterManager$2(final LongRef executorMemoryMiB$2, final LongRef memoryOverheadMiB$1, final LongRef pysparkMemoryMiB$1, final LongRef memoryOffHeapMiB$1, final SparkConf conf$3, final ObjectRef cores$1, final scala.collection.immutable.Map resourceMappings$1, final HashMap customResources$1, final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         label74: {
            ExecutorResourceRequest execReq;
            label78: {
               String r = (String)x0$1._1();
               execReq = (ExecutorResourceRequest)x0$1._2();
               String var10000 = MODULE$.MEMORY();
               if (var10000 == null) {
                  if (r == null) {
                     break label78;
                  }
               } else if (var10000.equals(r)) {
                  break label78;
               }

               label79: {
                  var10000 = MODULE$.OVERHEAD_MEM();
                  if (var10000 == null) {
                     if (r == null) {
                        break label79;
                     }
                  } else if (var10000.equals(r)) {
                     break label79;
                  }

                  label80: {
                     var10000 = MODULE$.PYSPARK_MEM();
                     if (var10000 == null) {
                        if (r == null) {
                           break label80;
                        }
                     } else if (var10000.equals(r)) {
                        break label80;
                     }

                     label81: {
                        var10000 = MODULE$.OFFHEAP_MEM();
                        if (var10000 == null) {
                           if (r == null) {
                              break label81;
                           }
                        } else if (var10000.equals(r)) {
                           break label81;
                        }

                        label45: {
                           var10000 = MODULE$.CORES();
                           if (var10000 == null) {
                              if (r == null) {
                                 break label45;
                              }
                           } else if (var10000.equals(r)) {
                              break label45;
                           }

                           String nameToUse = (String)resourceMappings$1.getOrElse(r, () -> r);
                           customResources$1.update(nameToUse, execReq);
                           BoxedUnit var25 = BoxedUnit.UNIT;
                           break label74;
                        }

                        cores$1.elem = new Some(BoxesRunTime.boxToInteger((int)execReq.amount()));
                        BoxedUnit var26 = BoxedUnit.UNIT;
                        break label74;
                     }

                     memoryOffHeapMiB$1.elem = MODULE$.executorOffHeapMemorySizeAsMb(conf$3, execReq);
                     BoxedUnit var27 = BoxedUnit.UNIT;
                     break label74;
                  }

                  pysparkMemoryMiB$1.elem = execReq.amount();
                  BoxedUnit var28 = BoxedUnit.UNIT;
                  break label74;
               }

               memoryOverheadMiB$1.elem = execReq.amount();
               BoxedUnit var29 = BoxedUnit.UNIT;
               break label74;
            }

            executorMemoryMiB$2.elem = execReq.amount();
            BoxedUnit var30 = BoxedUnit.UNIT;
         }

         BoxedUnit var31 = BoxedUnit.UNIT;
      }
   }

   private ResourceProfile$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
