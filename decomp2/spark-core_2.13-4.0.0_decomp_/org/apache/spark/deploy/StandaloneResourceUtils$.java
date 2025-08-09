package org.apache.spark.deploy;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Map;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.resource.ResourceAllocation;
import org.apache.spark.resource.ResourceID;
import org.apache.spark.resource.ResourceInformation;
import org.json4s.Formats;
import org.json4s.JValue;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashSet;
import scala.runtime.BoxesRunTime;

public final class StandaloneResourceUtils$ implements Logging {
   public static final StandaloneResourceUtils$ MODULE$ = new StandaloneResourceUtils$();
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

   public Option prepareResourcesFile(final String componentName, final scala.collection.immutable.Map resources, final File dir) {
      if (resources.isEmpty()) {
         return .MODULE$;
      } else {
         String compShortName = componentName.substring(componentName.lastIndexOf(".") + 1);
         File tmpFile = org.apache.spark.util.Utils$.MODULE$.tempFileWith(dir);
         Seq allocations = ((IterableOnceOps)resources.map((x0$1) -> {
            if (x0$1 != null) {
               String rName = (String)x0$1._1();
               ResourceInformation rInfo = (ResourceInformation)x0$1._2();
               return new ResourceAllocation(new ResourceID(componentName, rName), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(rInfo.addresses()).toImmutableArraySeq());
            } else {
               throw new MatchError(x0$1);
            }
         })).toSeq();

         try {
            this.writeResourceAllocationJson(allocations, tmpFile);
         } catch (Throwable var13) {
            if (var13 != null && scala.util.control.NonFatal..MODULE$.apply(var13)) {
               MessageWithContext errMsg = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception threw while preparing resource file for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COMPONENT..MODULE$, compShortName)})));
               this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> errMsg), var13);
               throw new SparkException(errMsg.message(), var13);
            }

            throw var13;
         }

         File resourcesFile = File.createTempFile("resource-" + compShortName + "-", ".json", dir);
         tmpFile.renameTo(resourcesFile);
         return new Some(resourcesFile);
      }
   }

   private void writeResourceAllocationJson(final Seq allocations, final File jsonFile) {
      Formats formats = org.json4s.DefaultFormats..MODULE$;
      JValue allocationJson = org.json4s.Extraction..MODULE$.decompose(allocations, formats);
      Files.write(jsonFile.toPath(), org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(allocationJson, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3())).getBytes(), new OpenOption[0]);
   }

   public scala.collection.immutable.Map toMutable(final scala.collection.immutable.Map immutableResources) {
      return (scala.collection.immutable.Map)immutableResources.map((x0$1) -> {
         if (x0$1 != null) {
            String rName = (String)x0$1._1();
            ResourceInformation rInfo = (ResourceInformation)x0$1._2();
            HashSet mutableAddress = new HashSet();
            mutableAddress.$plus$plus$eq(scala.Predef..MODULE$.wrapRefArray((Object[])rInfo.addresses()));
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(rName), new StandaloneResourceUtils.MutableResourceInfo(rInfo.name(), mutableAddress));
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public String formatResourcesDetails(final scala.collection.immutable.Map usedInfo, final scala.collection.immutable.Map freeInfo) {
      return ((IterableOnceOps)usedInfo.map((x0$1) -> {
         if (x0$1 != null) {
            String rName = (String)x0$1._1();
            ResourceInformation rInfo = (ResourceInformation)x0$1._2();
            String used = scala.Predef..MODULE$.wrapRefArray((Object[])rInfo.addresses()).mkString("[", ", ", "]");
            String free = scala.Predef..MODULE$.wrapRefArray((Object[])((ResourceInformation)freeInfo.apply(rName)).addresses()).mkString("[", ", ", "]");
            return rName + ": Free: " + free + " / Used: " + used;
         } else {
            throw new MatchError(x0$1);
         }
      })).mkString(", ");
   }

   public String formatResourcesAddresses(final scala.collection.immutable.Map resources) {
      return ((IterableOnceOps)resources.map((x0$1) -> {
         if (x0$1 != null) {
            String rName = (String)x0$1._1();
            ResourceInformation rInfo = (ResourceInformation)x0$1._2();
            return rName + ": " + scala.Predef..MODULE$.wrapRefArray((Object[])rInfo.addresses()).mkString("[", ", ", "]");
         } else {
            throw new MatchError(x0$1);
         }
      })).mkString(", ");
   }

   public String formatResourcesUsed(final scala.collection.immutable.Map resourcesTotal, final scala.collection.immutable.Map resourcesUsed) {
      return ((IterableOnceOps)resourcesTotal.map((x0$1) -> {
         if (x0$1 != null) {
            String rName = (String)x0$1._1();
            int totalSize = x0$1._2$mcI$sp();
            int used = BoxesRunTime.unboxToInt(resourcesUsed.apply(rName));
            return used + " / " + totalSize + " " + rName;
         } else {
            throw new MatchError(x0$1);
         }
      })).mkString(", ");
   }

   public String formatResourceRequirements(final Seq requirements) {
      return ((IterableOnceOps)requirements.map((req) -> {
         int var10000 = req.amount();
         return var10000 + " " + req.resourceName();
      })).mkString(", ");
   }

   private StandaloneResourceUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
