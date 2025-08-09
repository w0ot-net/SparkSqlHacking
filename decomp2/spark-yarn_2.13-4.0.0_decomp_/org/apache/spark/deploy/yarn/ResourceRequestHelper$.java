package org.apache.spark.deploy.yarn;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.api.records.ResourceInformation;
import org.apache.hadoop.yarn.exceptions.ResourceNotFoundException;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.deploy.yarn.config.package$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.resource.ResourceID;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.LinearSeqOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime;
import scala.util.matching.Regex;

public final class ResourceRequestHelper$ implements Logging {
   public static final ResourceRequestHelper$ MODULE$ = new ResourceRequestHelper$();
   private static final Regex AMOUNT_AND_UNIT_REGEX;
   private static volatile int numResourceErrors;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      AMOUNT_AND_UNIT_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("([0-9]+)([A-Za-z]*)"));
      numResourceErrors = 0;
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

   private Regex AMOUNT_AND_UNIT_REGEX() {
      return AMOUNT_AND_UNIT_REGEX;
   }

   private int numResourceErrors() {
      return numResourceErrors;
   }

   private void numResourceErrors_$eq(final int x$1) {
      numResourceErrors = x$1;
   }

   public scala.collection.immutable.Map getYarnResourcesAndAmounts(final SparkConf sparkConf, final String componentName) {
      return scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])sparkConf.getAllWithPrefix(String.valueOf(componentName))), (x0$1) -> {
         if (x0$1 != null) {
            String key = (String)x0$1._1();
            String value = (String)x0$1._2();
            int splitIndex = key.lastIndexOf(46);
            if (splitIndex == -1) {
               String errorMessage = "Missing suffix for " + componentName + key + ", you must specify a suffix - " + org.apache.spark.resource.ResourceUtils..MODULE$.AMOUNT() + " is currently the only supported suffix.";
               throw new IllegalArgumentException(errorMessage);
            } else {
               String resourceName = key.substring(0, splitIndex);
               String resourceSuffix = key.substring(splitIndex + 1);
               if (!org.apache.spark.resource.ResourceUtils..MODULE$.AMOUNT().equals(resourceSuffix)) {
                  String errorMessage = "Unsupported suffix: " + resourceSuffix + " in: " + componentName + key + ", only ." + org.apache.spark.resource.ResourceUtils..MODULE$.AMOUNT() + " is supported.";
                  throw new IllegalArgumentException(errorMessage);
               } else {
                  return new Tuple2(resourceName, value);
               }
            }
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public scala.collection.immutable.Map getResourceNameMapping(final SparkConf sparkConf) {
      return (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.resource.ResourceUtils..MODULE$.GPU()), sparkConf.get(package$.MODULE$.YARN_GPU_DEVICE())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.resource.ResourceUtils..MODULE$.FPGA()), sparkConf.get(package$.MODULE$.YARN_FPGA_DEVICE()))})));
   }

   public scala.collection.immutable.Map getYarnResourcesFromSparkResources(final String confPrefix, final SparkConf sparkConf) {
      return (scala.collection.immutable.Map)this.getResourceNameMapping(sparkConf).map((x0$1) -> {
         if (x0$1 != null) {
            String rName = (String)x0$1._1();
            String yarnName = (String)x0$1._2();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(yarnName), sparkConf.get((new ResourceID(confPrefix, rName)).amountConf(), "0"));
         } else {
            throw new MatchError(x0$1);
         }
      }).filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$getYarnResourcesFromSparkResources$2(x0$2)));
   }

   public void validateResources(final SparkConf sparkConf) {
      Seq var10000 = scala.package..MODULE$.Seq();
      ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
      Tuple2[] var10002 = new Tuple2[]{new Tuple2(package$.MODULE$.AM_MEMORY().key(), package$.MODULE$.YARN_AM_RESOURCE_TYPES_PREFIX() + "memory"), new Tuple2(org.apache.spark.internal.config.package..MODULE$.DRIVER_MEMORY().key(), package$.MODULE$.YARN_DRIVER_RESOURCE_TYPES_PREFIX() + "memory"), new Tuple2(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_MEMORY().key(), package$.MODULE$.YARN_EXECUTOR_RESOURCE_TYPES_PREFIX() + "memory"), new Tuple2(package$.MODULE$.AM_MEMORY().key(), package$.MODULE$.YARN_AM_RESOURCE_TYPES_PREFIX() + "mb"), new Tuple2(org.apache.spark.internal.config.package..MODULE$.DRIVER_MEMORY().key(), package$.MODULE$.YARN_DRIVER_RESOURCE_TYPES_PREFIX() + "mb"), new Tuple2(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_MEMORY().key(), package$.MODULE$.YARN_EXECUTOR_RESOURCE_TYPES_PREFIX() + "mb"), new Tuple2(package$.MODULE$.AM_MEMORY().key(), package$.MODULE$.YARN_AM_RESOURCE_TYPES_PREFIX() + "memory-mb"), new Tuple2(org.apache.spark.internal.config.package..MODULE$.DRIVER_MEMORY().key(), package$.MODULE$.YARN_DRIVER_RESOURCE_TYPES_PREFIX() + "memory-mb"), new Tuple2(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_MEMORY().key(), package$.MODULE$.YARN_EXECUTOR_RESOURCE_TYPES_PREFIX() + "memory-mb"), new Tuple2(package$.MODULE$.AM_CORES().key(), package$.MODULE$.YARN_AM_RESOURCE_TYPES_PREFIX() + "cores"), new Tuple2(org.apache.spark.internal.config.package..MODULE$.DRIVER_CORES().key(), package$.MODULE$.YARN_DRIVER_RESOURCE_TYPES_PREFIX() + "cores"), new Tuple2(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_CORES().key(), package$.MODULE$.YARN_EXECUTOR_RESOURCE_TYPES_PREFIX() + "cores"), new Tuple2(package$.MODULE$.AM_CORES().key(), package$.MODULE$.YARN_AM_RESOURCE_TYPES_PREFIX() + "vcores"), new Tuple2(org.apache.spark.internal.config.package..MODULE$.DRIVER_CORES().key(), package$.MODULE$.YARN_DRIVER_RESOURCE_TYPES_PREFIX() + "vcores"), new Tuple2(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_CORES().key(), package$.MODULE$.YARN_EXECUTOR_RESOURCE_TYPES_PREFIX() + "vcores"), new Tuple2(package$.MODULE$.AM_CORES().key(), package$.MODULE$.YARN_AM_RESOURCE_TYPES_PREFIX() + "cpu-vcores"), new Tuple2(org.apache.spark.internal.config.package..MODULE$.DRIVER_CORES().key(), package$.MODULE$.YARN_DRIVER_RESOURCE_TYPES_PREFIX() + "cpu-vcores"), new Tuple2(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_CORES().key(), package$.MODULE$.YARN_EXECUTOR_RESOURCE_TYPES_PREFIX() + "cpu-vcores"), null, null, null, null};
      String var10007 = (new ResourceID(org.apache.spark.internal.config.package..MODULE$.SPARK_EXECUTOR_PREFIX(), "fpga")).amountConf();
      String var10008 = package$.MODULE$.YARN_EXECUTOR_RESOURCE_TYPES_PREFIX();
      var10002[18] = new Tuple2(var10007, var10008 + sparkConf.get(package$.MODULE$.YARN_FPGA_DEVICE()));
      var10007 = (new ResourceID(org.apache.spark.internal.config.package..MODULE$.SPARK_DRIVER_PREFIX(), "fpga")).amountConf();
      var10008 = package$.MODULE$.YARN_DRIVER_RESOURCE_TYPES_PREFIX();
      var10002[19] = new Tuple2(var10007, var10008 + sparkConf.get(package$.MODULE$.YARN_FPGA_DEVICE()));
      var10007 = (new ResourceID(org.apache.spark.internal.config.package..MODULE$.SPARK_EXECUTOR_PREFIX(), "gpu")).amountConf();
      var10008 = package$.MODULE$.YARN_EXECUTOR_RESOURCE_TYPES_PREFIX();
      var10002[20] = new Tuple2(var10007, var10008 + sparkConf.get(package$.MODULE$.YARN_GPU_DEVICE()));
      var10007 = (new ResourceID(org.apache.spark.internal.config.package..MODULE$.SPARK_DRIVER_PREFIX(), "gpu")).amountConf();
      var10008 = package$.MODULE$.YARN_DRIVER_RESOURCE_TYPES_PREFIX();
      var10002[21] = new Tuple2(var10007, var10008 + sparkConf.get(package$.MODULE$.YARN_GPU_DEVICE()));
      Seq resourceDefinitions = (Seq)var10000.apply(var10001.wrapRefArray((Object[])var10002));
      StringBuilder errorMessage = new StringBuilder();
      resourceDefinitions.foreach((x0$1) -> {
         if (x0$1 != null) {
            String sparkName = (String)x0$1._1();
            String resourceRequest = (String)x0$1._2();
            String resourceRequestAmount = resourceRequest + "." + org.apache.spark.resource.ResourceUtils..MODULE$.AMOUNT();
            return sparkConf.contains(resourceRequestAmount) ? errorMessage.append("Error: Do not use " + resourceRequestAmount + ", please use " + sparkName + " instead!\n") : BoxedUnit.UNIT;
         } else {
            throw new MatchError(x0$1);
         }
      });
      if (errorMessage.nonEmpty()) {
         throw new SparkException(errorMessage.toString());
      }
   }

   public void setResourceRequests(final scala.collection.immutable.Map resources, final Resource resource) {
      scala.Predef..MODULE$.require(resource != null, () -> "Resource parameter should not be null!");
      this.logDebug((Function0)(() -> "Custom resources requested: " + resources));
      if (!resources.isEmpty()) {
         resources.foreach((x0$1) -> {
            $anonfun$setResourceRequests$3(resource, x0$1);
            return BoxedUnit.UNIT;
         });
      }
   }

   private ResourceInformation createResourceInformation(final String resourceName, final long amount, final String unit) {
      return .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(unit)) ? ResourceInformation.newInstance(resourceName, unit, amount) : ResourceInformation.newInstance(resourceName, amount);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getYarnResourcesFromSparkResources$2(final Tuple2 x0$2) {
      if (x0$2 != null) {
         String count = (String)x0$2._2();
         return .MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(count)) > 0L;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$setResourceRequests$3(final Resource resource$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String name = (String)x0$1._1();
         String rawAmount = (String)x0$1._2();

         try {
            if (rawAmount != null) {
               Option var11 = MODULE$.AMOUNT_AND_UNIT_REGEX().unapplySeq(rawAmount);
               if (!var11.isEmpty() && var11.get() != null && ((List)var11.get()).lengthCompare(2) == 0) {
                  long amount;
                  String var30;
                  label76: {
                     String amountPart = (String)((LinearSeqOps)var11.get()).apply(0);
                     String unitPart = (String)((LinearSeqOps)var11.get()).apply(1);
                     Tuple2 var9 = new Tuple2(amountPart, unitPart);
                     String amountPart = (String)var9._1();
                     String unitPart = (String)var9._2();
                     amount = .MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(amountPart));
                     switch (unitPart == null ? 0 : unitPart.hashCode()) {
                        case 103:
                           if ("g".equals(unitPart)) {
                              var30 = "G";
                              break label76;
                           }
                           break;
                        case 112:
                           if ("p".equals(unitPart)) {
                              var30 = "P";
                              break label76;
                           }
                           break;
                        case 116:
                           if ("t".equals(unitPart)) {
                              var30 = "T";
                              break label76;
                           }
                     }

                     var30 = unitPart;
                  }

                  String unit = var30;
                  MODULE$.logDebug((Function0)(() -> "Registering resource with name: " + name + ", amount: " + amount + ", unit: " + unit));
                  ResourceInformation resourceInformation = MODULE$.createResourceInformation(name, amount, unit);
                  resource$1.setResourceInformation(name, resourceInformation);
                  BoxedUnit var31 = BoxedUnit.UNIT;
                  return;
               }
            }

            throw new MatchError(rawAmount);
         } catch (Throwable var27) {
            if (var27 instanceof MatchError) {
               throw new IllegalArgumentException("Resource request for '" + name + "' ('" + rawAmount + "') does not match pattern " + MODULE$.AMOUNT_AND_UNIT_REGEX() + ".");
            } else {
               if (var27 != null) {
                  Option var23 = org.apache.spark.util.CausedBy..MODULE$.unapply(var27);
                  if (!var23.isEmpty()) {
                     Throwable e = (Throwable)var23.get();
                     if (e instanceof IllegalArgumentException) {
                        IllegalArgumentException var25 = (IllegalArgumentException)e;
                        throw new IllegalArgumentException("Invalid request for " + name + ": " + var25.getMessage());
                     }
                  }
               }

               if (!(var27 instanceof ResourceNotFoundException)) {
                  throw var27;
               } else {
                  ResourceNotFoundException var26 = (ResourceNotFoundException)var27;
                  if (MODULE$.numResourceErrors() < 2) {
                     MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"YARN doesn't know about resource ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_NAME..MODULE$, name)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"your resource discovery has to handle properly discovering and isolating "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"the resource! Error: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var26.getMessage())}))))));
                     MODULE$.numResourceErrors_$eq(MODULE$.numResourceErrors() + 1);
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  } else {
                     BoxedUnit var28 = BoxedUnit.UNIT;
                  }

                  BoxedUnit var29 = BoxedUnit.UNIT;
               }
            }
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   private ResourceRequestHelper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
