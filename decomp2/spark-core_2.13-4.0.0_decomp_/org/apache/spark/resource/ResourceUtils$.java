package org.apache.spark.resource;

import java.lang.invoke.SerializedLambda;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.api.resource.ResourceDiscoveryPlugin;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.config.Tests$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.Utils$;
import org.json4s.Formats;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

public final class ResourceUtils$ implements Logging {
   public static final ResourceUtils$ MODULE$ = new ResourceUtils$();
   private static final String DISCOVERY_SCRIPT;
   private static final String VENDOR;
   private static final String AMOUNT;
   private static final String GPU;
   private static final String FPGA;
   private static final String RESOURCE_PREFIX;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      DISCOVERY_SCRIPT = "discoveryScript";
      VENDOR = "vendor";
      AMOUNT = "amount";
      GPU = "gpu";
      FPGA = "fpga";
      RESOURCE_PREFIX = "resource";
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

   public String DISCOVERY_SCRIPT() {
      return DISCOVERY_SCRIPT;
   }

   public String VENDOR() {
      return VENDOR;
   }

   public String AMOUNT() {
      return AMOUNT;
   }

   public ResourceRequest parseResourceRequest(final SparkConf sparkConf, final ResourceID resourceId) {
      scala.collection.immutable.Map settings = .MODULE$.wrapRefArray((Object[])sparkConf.getAllWithPrefix(resourceId.confPrefix())).toMap(scala..less.colon.less..MODULE$.refl());
      int amount = scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString((String)settings.getOrElse(this.AMOUNT(), () -> {
         throw new SparkException("You must specify an amount for " + resourceId.resourceName());
      })));
      Optional discoveryScript = Optional.ofNullable(settings.get(this.DISCOVERY_SCRIPT()).orNull(scala..less.colon.less..MODULE$.refl()));
      Optional vendor = Optional.ofNullable(settings.get(this.VENDOR()).orNull(scala..less.colon.less..MODULE$.refl()));
      return new ResourceRequest(resourceId, (long)amount, discoveryScript, vendor);
   }

   public Seq listResourceIds(final SparkConf sparkConf, final String componentName) {
      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.distinct$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])sparkConf.getAllWithPrefix(componentName + "." + this.RESOURCE_PREFIX() + ".")), (x0$1) -> {
         if (x0$1 != null) {
            String key = (String)x0$1._1();
            int index = key.indexOf(46);
            if (index < 0) {
               throw new SparkException("You must specify an amount config for resource: " + key + " config: " + componentName + "." + MODULE$.RESOURCE_PREFIX() + "." + key);
            } else {
               return key.substring(0, index);
            }
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(String.class))))), (name) -> new ResourceID(componentName, name), scala.reflect.ClassTag..MODULE$.apply(ResourceID.class))).toImmutableArraySeq();
   }

   public Seq parseAllResourceRequests(final SparkConf sparkConf, final String componentName) {
      return (Seq)((IterableOps)this.listResourceIds(sparkConf, componentName).map((id) -> MODULE$.parseResourceRequest(sparkConf, id))).filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$parseAllResourceRequests$2(x$1)));
   }

   public Tuple2 calculateAmountAndPartsForFraction(final double doubleAmount) {
      int var10000;
      if (doubleAmount <= (double)1.0F) {
         var10000 = (int)Math.floor((double)1.0F / doubleAmount);
      } else {
         if (doubleAmount % (double)1 != (double)0) {
            throw new SparkException("The resource amount " + doubleAmount + " must be either <= 1.0, or a whole number.");
         }

         var10000 = 1;
      }

      int parts = var10000;
      return new Tuple2.mcII.sp((int)Math.ceil(doubleAmount), parts);
   }

   public void addTaskResourceRequests(final SparkConf sparkConf, final TaskResourceRequests treqs) {
      scala.collection.immutable.Map nonZeroTaskReqs = (scala.collection.immutable.Map)((IterableOnceOps)this.listResourceIds(sparkConf, package$.MODULE$.SPARK_TASK_PREFIX()).map((resourceId) -> {
         scala.collection.immutable.Map settings = .MODULE$.wrapRefArray((Object[])sparkConf.getAllWithPrefix(resourceId.confPrefix())).toMap(scala..less.colon.less..MODULE$.refl());
         double amountDouble = scala.collection.StringOps..MODULE$.toDouble$extension(.MODULE$.augmentString((String)settings.getOrElse(MODULE$.AMOUNT(), () -> {
            throw new SparkException("You must specify an amount for " + resourceId.resourceName());
         })));
         return new Tuple2(resourceId.resourceName(), BoxesRunTime.boxToDouble(amountDouble));
      })).toMap(scala..less.colon.less..MODULE$.refl()).filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$addTaskResourceRequests$3(x0$1)));
      nonZeroTaskReqs.foreach((x0$2) -> {
         if (x0$2 != null) {
            String resourceName = (String)x0$2._1();
            double amount = x0$2._2$mcD$sp();
            return treqs.resource(resourceName, amount);
         } else {
            throw new MatchError(x0$2);
         }
      });
   }

   public Seq parseResourceRequirements(final SparkConf sparkConf, final String componentName) {
      Seq resourceIds = this.listResourceIds(sparkConf, componentName);
      Seq rnamesAndAmounts = (Seq)resourceIds.map((resourceId) -> {
         scala.collection.immutable.Map settings = .MODULE$.wrapRefArray((Object[])sparkConf.getAllWithPrefix(resourceId.confPrefix())).toMap(scala..less.colon.less..MODULE$.refl());
         double amountDouble = scala.collection.StringOps..MODULE$.toDouble$extension(.MODULE$.augmentString((String)settings.getOrElse(MODULE$.AMOUNT(), () -> {
            throw new SparkException("You must specify an amount for " + resourceId.resourceName());
         })));
         return new Tuple2(resourceId.resourceName(), BoxesRunTime.boxToDouble(amountDouble));
      });
      return (Seq)((IterableOps)rnamesAndAmounts.filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$parseResourceRequirements$3(x0$1)))).map((x0$2) -> {
         if (x0$2 != null) {
            String rName = (String)x0$2._1();
            double amountDouble = x0$2._2$mcD$sp();
            Object var10000;
            if (componentName.equalsIgnoreCase(package$.MODULE$.SPARK_TASK_PREFIX())) {
               var10000 = MODULE$.calculateAmountAndPartsForFraction(amountDouble);
            } else {
               if (amountDouble % (double)1 != (double)0) {
                  throw new SparkException("Only tasks support fractional resources, please check your " + componentName + " settings");
               }

               var10000 = new Tuple2.mcII.sp((int)amountDouble, 1);
            }

            Object var9 = var10000;
            if (var9 != null) {
               int amount = ((Tuple2)var9)._1$mcI$sp();
               int parts = ((Tuple2)var9)._2$mcI$sp();
               Tuple2.mcII.sp var8 = new Tuple2.mcII.sp(amount, parts);
               int amount = ((Tuple2)var8)._1$mcI$sp();
               int partsx = ((Tuple2)var8)._2$mcI$sp();
               return new ResourceRequirement(rName, amount, partsx);
            } else {
               throw new MatchError(var9);
            }
         } else {
            throw new MatchError(x0$2);
         }
      });
   }

   public Seq executorResourceRequestToRequirement(final Seq resourceRequest) {
      return (Seq)resourceRequest.map((request) -> new ResourceRequirement(request.resourceName(), (int)request.amount(), 1));
   }

   public boolean resourcesMeetRequirements(final scala.collection.immutable.Map resourcesFree, final Seq resourceRequirements) {
      return resourceRequirements.forall((req) -> BoxesRunTime.boxToBoolean($anonfun$resourcesMeetRequirements$1(resourcesFree, req)));
   }

   public Seq withResourcesJson(final String resourcesFile, final Function1 extract) {
      String json = new String(Files.readAllBytes(Paths.get(resourcesFile)));

      try {
         return (Seq)extract.apply(json);
      } catch (Throwable var8) {
         if (var8 != null && scala.util.control.NonFatal..MODULE$.apply(var8)) {
            throw new SparkException("Error parsing resources file " + resourcesFile, var8);
         } else {
            throw var8;
         }
      }
   }

   public Seq parseAllocatedFromJsonFile(final String resourcesFile) {
      return this.withResourcesJson(resourcesFile, (json) -> {
         Formats formats = org.json4s.DefaultFormats..MODULE$;
         return (Seq)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.jackson.JsonMethods..MODULE$.parse(json, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput())), formats, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.classType(ResourceAllocation.class), scala.collection.immutable.Nil..MODULE$));
      });
   }

   public Seq parseAllocated(final Option resourcesFileOpt, final String componentName) {
      return (Seq)((IterableOps)scala.Option..MODULE$.option2Iterable(resourcesFileOpt).toSeq().flatMap((resourcesFile) -> MODULE$.parseAllocatedFromJsonFile(resourcesFile))).filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$parseAllocated$2(componentName, x$3)));
   }

   private Seq parseAllocatedOrDiscoverResources(final SparkConf sparkConf, final String componentName, final Option resourcesFileOpt) {
      Seq allocated = this.parseAllocated(resourcesFileOpt, componentName);
      Seq otherResourceIds = (Seq)this.listResourceIds(sparkConf, componentName).diff((scala.collection.Seq)allocated.map((x$4) -> x$4.id()));
      Seq otherResources = (Seq)otherResourceIds.flatMap((id) -> {
         ResourceRequest request = MODULE$.parseResourceRequest(sparkConf, id);
         return (Option)(request.amount() > 0L ? new Some(new ResourceAllocation(id, org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(MODULE$.discoverResource(sparkConf, request).addresses()).toImmutableArraySeq())) : scala.None..MODULE$);
      });
      return (Seq)allocated.$plus$plus(otherResources);
   }

   private void assertResourceAllocationMeetsRequest(final ResourceAllocation allocation, final ResourceRequest request) {
      boolean var4;
      Predef var10000;
      label19: {
         label18: {
            var10000 = .MODULE$;
            ResourceID var10001 = allocation.id();
            ResourceID var3 = request.id();
            if (var10001 == null) {
               if (var3 != null) {
                  break label18;
               }
            } else if (!var10001.equals(var3)) {
               break label18;
            }

            if ((long)allocation.addresses().size() >= request.amount()) {
               var4 = true;
               break label19;
            }
         }

         var4 = false;
      }

      var10000.require(var4, () -> {
         String var10000 = allocation.id().resourceName();
         return "Resource: " + var10000 + ", with addresses: " + allocation.addresses().mkString(",") + " is less than what the user requested: " + request.amount() + ")";
      });
   }

   private void assertAllResourceAllocationsMeetRequests(final Seq allocations, final Seq requests) {
      scala.collection.immutable.Map allocated = ((IterableOnceOps)allocations.map((x) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(x.id()), x))).toMap(scala..less.colon.less..MODULE$.refl());
      requests.foreach((r) -> {
         $anonfun$assertAllResourceAllocationsMeetRequests$2(allocated, r);
         return BoxedUnit.UNIT;
      });
   }

   private void assertAllResourceAllocationsMatchResourceProfile(final scala.collection.immutable.Map allocations, final scala.collection.immutable.Map execReqs) {
      execReqs.foreach((x0$1) -> {
         $anonfun$assertAllResourceAllocationsMatchResourceProfile$1(allocations, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public scala.collection.immutable.Map getOrDiscoverAllResources(final SparkConf sparkConf, final String componentName, final Option resourcesFileOpt) {
      Seq requests = this.parseAllResourceRequests(sparkConf, componentName);
      Seq allocations = this.parseAllocatedOrDiscoverResources(sparkConf, componentName, resourcesFileOpt);
      this.assertAllResourceAllocationsMeetRequests(allocations, requests);
      scala.collection.immutable.Map resourceInfoMap = ((IterableOnceOps)allocations.map((a) -> new Tuple2(a.id().resourceName(), a.toResourceInformation()))).toMap(scala..less.colon.less..MODULE$.refl());
      return resourceInfoMap;
   }

   private Optional emptyStringToOptional(final String optStr) {
      return optStr.isEmpty() ? Optional.empty() : Optional.of(optStr);
   }

   public scala.collection.immutable.Map getOrDiscoverAllResourcesForResourceProfile(final Option resourcesFileOpt, final String componentName, final ResourceProfile resourceProfile, final SparkConf sparkConf) {
      Seq fileAllocated = this.parseAllocated(resourcesFileOpt, componentName);
      scala.collection.immutable.Map fileAllocResMap = ((IterableOnceOps)fileAllocated.map((a) -> new Tuple2(a.id().resourceName(), a.toResourceInformation()))).toMap(scala..less.colon.less..MODULE$.refl());
      scala.collection.immutable.Map execReq = resourceProfile.getCustomExecutorResources();
      scala.collection.immutable.Map filteredExecreq = (scala.collection.immutable.Map)execReq.filterNot((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$getOrDiscoverAllResourcesForResourceProfile$2(fileAllocResMap, x0$1)));
      scala.collection.immutable.Map rpAllocations = (scala.collection.immutable.Map)filteredExecreq.map((x0$2) -> {
         if (x0$2 != null) {
            String rName = (String)x0$2._1();
            ExecutorResourceRequest execRequest = (ExecutorResourceRequest)x0$2._2();
            ResourceID resourceId = new ResourceID(componentName, rName);
            Optional scriptOpt = MODULE$.emptyStringToOptional(execRequest.discoveryScript());
            Optional vendorOpt = MODULE$.emptyStringToOptional(execRequest.vendor());
            ResourceRequest resourceReq = new ResourceRequest(resourceId, execRequest.amount(), scriptOpt, vendorOpt);
            String[] addrs = MODULE$.discoverResource(sparkConf, resourceReq).addresses();
            return new Tuple2(rName, new ResourceInformation(rName, addrs));
         } else {
            throw new MatchError(x0$2);
         }
      });
      scala.collection.immutable.Map allAllocations = (scala.collection.immutable.Map)fileAllocResMap.$plus$plus(rpAllocations);
      this.assertAllResourceAllocationsMatchResourceProfile(allAllocations, execReq);
      return allAllocations;
   }

   public void logResourceInfo(final String componentName, final scala.collection.immutable.Map resources) {
      String resourceInfo = resources.isEmpty() ? "No custom resources configured for " + componentName + "." : "Custom resources for " + componentName + ":\n" + resources.mkString("\n");
      this.logInfo((Function0)(() -> "=============================================================="));
      this.logInfo((Function0)(() -> resourceInfo));
      this.logInfo((Function0)(() -> "=============================================================="));
   }

   public ResourceInformation discoverResource(final SparkConf sparkConf, final ResourceRequest resourceRequest) {
      Object var3 = new Object();

      try {
         String discoveryScriptPlugin = "org.apache.spark.resource.ResourceDiscoveryScriptPlugin";
         Seq pluginClasses = (Seq)((SeqOps)sparkConf.get(package$.MODULE$.RESOURCES_DISCOVERY_PLUGIN())).$colon$plus(discoveryScriptPlugin);
         Seq resourcePlugins = Utils$.MODULE$.loadExtensions(ResourceDiscoveryPlugin.class, pluginClasses, sparkConf);
         resourcePlugins.foreach((plugin) -> {
            $anonfun$discoverResource$1(resourceRequest, sparkConf, var3, plugin);
            return BoxedUnit.UNIT;
         });
         throw new SparkException("None of the discovery plugins returned ResourceInformation for " + resourceRequest.id().resourceName());
      } catch (NonLocalReturnControl var8) {
         if (var8.key() == var3) {
            return (ResourceInformation)var8.value();
         } else {
            throw var8;
         }
      }
   }

   public boolean validateTaskCpusLargeEnough(final SparkConf sparkConf, final int execCores, final int taskCpus) {
      if (execCores < taskCpus) {
         throw new SparkException("The number of cores per executor (=" + execCores + ") has to be >= the number of cpus per task = " + taskCpus + ".");
      } else {
         return true;
      }
   }

   public void warnOnWastedResources(final ResourceProfile rp, final SparkConf sparkConf, final Option execCores) {
      boolean coresKnown = rp.isCoresLimitKnown();
      ObjectRef limitingResource = ObjectRef.create(rp.limitingResource(sparkConf));
      IntRef maxTaskPerExec = IntRef.create(rp.maxTasksPerExecutor(sparkConf));
      int taskCpus = ResourceProfile$.MODULE$.getTaskCpusOrDefaultForProfile(rp, sparkConf);
      int var10000;
      if (execCores.isDefined()) {
         var10000 = BoxesRunTime.unboxToInt(execCores.get());
      } else {
         if (!coresKnown) {
            return;
         }

         var10000 = BoxesRunTime.unboxToInt(rp.getExecutorCores().getOrElse((JFunction0.mcI.sp)() -> BoxesRunTime.unboxToInt(sparkConf.get(package$.MODULE$.EXECUTOR_CORES()))));
      }

      int cores = var10000;
      if (!coresKnown) {
         int numTasksPerExecCores = cores / taskCpus;
         int numTasksPerExecCustomResource = rp.maxTasksPerExecutor(sparkConf);
         if (((String)limitingResource.elem).isEmpty() || scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString((String)limitingResource.elem)) && numTasksPerExecCores < numTasksPerExecCustomResource) {
            limitingResource.elem = ResourceProfile$.MODULE$.CPUS();
            maxTaskPerExec.elem = numTasksPerExecCores;
         }
      }

      scala.collection.immutable.Map taskReq = rp.getCustomTaskResources();
      scala.collection.immutable.Map execReq = rp.getCustomExecutorResources();
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString((String)limitingResource.elem)) && !((String)limitingResource.elem).equals(ResourceProfile$.MODULE$.CPUS()) && taskCpus * maxTaskPerExec.elem < cores) {
         int resourceNumSlots = (int)Math.floor((double)(cores / taskCpus));
         MessageWithContext message = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The configuration of cores (exec = ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_CORES..MODULE$, BoxesRunTime.boxToInteger(cores))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"task = ", ", runnable tasks = "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_TASK_CPUS..MODULE$, BoxesRunTime.boxToInteger(taskCpus))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ") will "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_RESOURCE_SLOTS..MODULE$, BoxesRunTime.boxToInteger(resourceNumSlots))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"result in wasted resources due to resource ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE..MODULE$, (String)limitingResource.elem)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"limiting the number of runnable tasks per executor to: "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". Please adjust "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_TASKS..MODULE$, BoxesRunTime.boxToInteger(maxTaskPerExec.elem))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"your configuration."})))).log(scala.collection.immutable.Nil..MODULE$));
         if (BoxesRunTime.unboxToBoolean(sparkConf.get(Tests$.MODULE$.RESOURCES_WARNING_TESTING()))) {
            throw new SparkException(message.message());
         }

         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> message));
      }

      taskReq.foreach((x0$1) -> {
         $anonfun$warnOnWastedResources$3(execReq, rp, sparkConf, maxTaskPerExec, limitingResource, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public Option warnOnWastedResources$default$3() {
      return scala.None..MODULE$;
   }

   public final String GPU() {
      return GPU;
   }

   public final String FPGA() {
      return FPGA;
   }

   public final String RESOURCE_PREFIX() {
      return RESOURCE_PREFIX;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parseAllResourceRequests$2(final ResourceRequest x$1) {
      return x$1.amount() > 0L;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$addTaskResourceRequests$3(final Tuple2 x0$1) {
      if (x0$1 != null) {
         double amount = x0$1._2$mcD$sp();
         return amount > (double)0.0F;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parseResourceRequirements$3(final Tuple2 x0$1) {
      if (x0$1 != null) {
         double amount = x0$1._2$mcD$sp();
         return amount > (double)0;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$resourcesMeetRequirements$1(final scala.collection.immutable.Map resourcesFree$1, final ResourceRequirement req) {
      return BoxesRunTime.unboxToInt(resourcesFree$1.getOrElse(req.resourceName(), (JFunction0.mcI.sp)() -> 0)) >= req.amount();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parseAllocated$2(final String componentName$3, final ResourceAllocation x$3) {
      boolean var3;
      label23: {
         String var10000 = x$3.id().componentName();
         if (var10000 == null) {
            if (componentName$3 == null) {
               break label23;
            }
         } else if (var10000.equals(componentName$3)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final void $anonfun$assertAllResourceAllocationsMeetRequests$2(final scala.collection.immutable.Map allocated$1, final ResourceRequest r) {
      MODULE$.assertResourceAllocationMeetsRequest((ResourceAllocation)allocated$1.apply(r.id()), r);
   }

   // $FF: synthetic method
   public static final void $anonfun$assertAllResourceAllocationsMatchResourceProfile$1(final scala.collection.immutable.Map allocations$1, final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         String rName = (String)x0$1._1();
         ExecutorResourceRequest req = (ExecutorResourceRequest)x0$1._2();
         .MODULE$.require(allocations$1.contains(rName) && (long)((ResourceInformation)allocations$1.apply(rName)).addresses().length >= req.amount(), () -> "Resource: " + rName + ", with addresses: " + .MODULE$.wrapRefArray((Object[])((ResourceInformation)allocations$1.apply(rName)).addresses()).mkString(",") + " is less than what the user requested: " + req.amount() + ")");
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getOrDiscoverAllResourcesForResourceProfile$2(final scala.collection.immutable.Map fileAllocResMap$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String rname = (String)x0$1._1();
         return fileAllocResMap$1.contains(rname);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$discoverResource$1(final ResourceRequest resourceRequest$1, final SparkConf sparkConf$6, final Object nonLocalReturnKey1$1, final ResourceDiscoveryPlugin plugin) {
      Optional riOption = plugin.discoverResource(resourceRequest$1, sparkConf$6);
      if (riOption.isPresent()) {
         throw new NonLocalReturnControl(nonLocalReturnKey1$1, riOption.get());
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$warnOnWastedResources$3(final scala.collection.immutable.Map execReq$1, final ResourceProfile rp$1, final SparkConf sparkConf$7, final IntRef maxTaskPerExec$1, final ObjectRef limitingResource$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String rName = (String)x0$1._1();
         TaskResourceRequest treq = (TaskResourceRequest)x0$1._2();
         long execAmount = ((ExecutorResourceRequest)execReq$1.apply(rName)).amount();
         int taskAmount = rp$1.getSchedulerTaskResourceAmount(rName);
         int numParts = rp$1.getNumSlotsPerAddress(rName, sparkConf$7);
         if ((long)maxTaskPerExec$1.elem < execAmount * (long)numParts / (long)taskAmount) {
            double origTaskAmount = treq.amount();
            String taskReqStr = origTaskAmount + "/" + numParts;
            int resourceNumSlots = (int)(execAmount * (long)numParts / (long)taskAmount);
            MessageWithContext message = MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The configuration of resource: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_NAME..MODULE$, treq.resourceName())})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(exec = ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXEC_AMOUNT..MODULE$, BoxesRunTime.boxToLong(execAmount))})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"task = ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_REQUIREMENTS..MODULE$, taskReqStr)})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"runnable tasks = ", ") will "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_RESOURCE_SLOTS..MODULE$, BoxesRunTime.boxToInteger(resourceNumSlots))})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"result in wasted resources due to resource ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE..MODULE$, (String)limitingResource$1.elem)})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"limiting the number of runnable tasks per executor to: "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". Please adjust your configuration."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_TASKS..MODULE$, BoxesRunTime.boxToInteger(maxTaskPerExec$1.elem))}))));
            if (BoxesRunTime.unboxToBoolean(sparkConf$7.get(Tests$.MODULE$.RESOURCES_WARNING_TESTING()))) {
               throw new SparkException(message.message());
            } else {
               MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> message));
               BoxedUnit var19 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   private ResourceUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
