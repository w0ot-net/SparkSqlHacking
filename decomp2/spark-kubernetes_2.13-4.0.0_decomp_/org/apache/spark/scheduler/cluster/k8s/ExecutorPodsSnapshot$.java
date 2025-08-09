package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.Pod;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import java.util.Map;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorPodsSnapshot$ implements Logging, Serializable {
   public static final ExecutorPodsSnapshot$ MODULE$ = new ExecutorPodsSnapshot$();
   private static boolean shouldCheckAllContainers;
   private static String sparkContainerName;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      sparkContainerName = Constants$.MODULE$.DEFAULT_EXECUTOR_CONTAINER_NAME();
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

   private boolean shouldCheckAllContainers() {
      return shouldCheckAllContainers;
   }

   private void shouldCheckAllContainers_$eq(final boolean x$1) {
      shouldCheckAllContainers = x$1;
   }

   private String sparkContainerName() {
      return sparkContainerName;
   }

   private void sparkContainerName_$eq(final String x$1) {
      sparkContainerName = x$1;
   }

   public ExecutorPodsSnapshot apply(final Seq executorPods, final long fullSnapshotTs) {
      return new ExecutorPodsSnapshot(this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshot$$toStatesByExecutorId(executorPods), fullSnapshotTs);
   }

   public ExecutorPodsSnapshot apply() {
      return new ExecutorPodsSnapshot(.MODULE$.Map().empty(), 0L);
   }

   public void setShouldCheckAllContainers(final boolean watchAllContainers) {
      this.shouldCheckAllContainers_$eq(watchAllContainers);
   }

   public void setSparkContainerName(final String containerName) {
      this.sparkContainerName_$eq(containerName);
   }

   public scala.collection.immutable.Map org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshot$$toStatesByExecutorId(final Seq executorPods) {
      return ((IterableOnceOps)executorPods.flatMap((pod) -> {
         String var2 = (String)pod.getMetadata().getLabels().get(Constants$.MODULE$.SPARK_EXECUTOR_ID_LABEL());
         switch (var2 == null ? 0 : var2.hashCode()) {
            case 0:
               if (var2 == null) {
                  return scala.None..MODULE$;
               }
               break;
            case 2058803564:
               if ("EXECID".equals(var2)) {
                  return scala.None..MODULE$;
               }
         }

         return new Some(new Tuple2(BoxesRunTime.boxToLong(scala.collection.StringOps..MODULE$.toLong$extension(.MODULE$.augmentString(var2))), MODULE$.toState(pod)));
      })).toMap(scala..less.colon.less..MODULE$.refl());
   }

   private ExecutorPodState toState(final Pod pod) {
      if (this.isDeleted(pod)) {
         return new PodDeleted(pod);
      } else {
         String phase = pod.getStatus().getPhase().toLowerCase(Locale.ROOT);
         switch (phase == null ? 0 : phase.hashCode()) {
            case -1918581026:
               if ("terminating".equals(phase)) {
                  return new PodTerminating(pod);
               }
               break;
            case -1281977283:
               if ("failed".equals(phase)) {
                  return new PodFailed(pod);
               }
               break;
            case -682587753:
               if ("pending".equals(phase)) {
                  return new PodPending(pod);
               }
               break;
            case 945734241:
               if ("succeeded".equals(phase)) {
                  return new PodSucceeded(pod);
               }
               break;
            case 1550783935:
               if ("running".equals(phase)) {
                  if (this.shouldCheckAllContainers() && "Never".equals(pod.getSpec().getRestartPolicy()) && pod.getStatus().getContainerStatuses().stream().map((cs) -> cs.getState().getTerminated()).anyMatch((tx) -> tx != null && !BoxesRunTime.equalsNumObject(tx.getExitCode(), BoxesRunTime.boxToInteger(0)))) {
                     return new PodFailed(pod);
                  }

                  Option sparkContainerExitCode = scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(pod.getStatus().getContainerStatuses()).asScala().find((x$2) -> BoxesRunTime.boxToBoolean($anonfun$toState$3(x$2))).flatMap((x) -> scala.Option..MODULE$.apply(x.getState())).flatMap((x) -> scala.Option..MODULE$.apply(x.getTerminated())).flatMap((x) -> scala.Option..MODULE$.apply(x.getExitCode())).map((x$3) -> BoxesRunTime.boxToInteger($anonfun$toState$7(x$3)));
                  if (sparkContainerExitCode instanceof Some) {
                     Some var8 = (Some)sparkContainerExitCode;
                     int t = BoxesRunTime.unboxToInt(var8.value());
                     switch (t) {
                        case 0 -> {
                           return new PodSucceeded(pod);
                        }
                        default -> {
                           return new PodFailed(pod);
                        }
                     }
                  }

                  return new PodRunning(pod);
               }
         }

         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Received unknown phase ", " for executor "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.POD_PHASE..MODULE$, phase)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"pod with name ", " in "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.POD_NAME..MODULE$, pod.getMetadata().getName())})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"namespace ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.POD_NAMESPACE..MODULE$, pod.getMetadata().getNamespace())}))))));
         return new PodUnknown(pod);
      }
   }

   private boolean isDeleted(final Pod pod) {
      boolean var5;
      label41: {
         if (pod.getMetadata().getDeletionTimestamp() != null) {
            label36: {
               if (pod.getStatus() == null || pod.getStatus().getPhase() == null) {
                  break label41;
               }

               String var10000 = pod.getStatus().getPhase().toLowerCase(Locale.ROOT);
               String var2 = "terminating";
               if (var10000 == null) {
                  if (var2 == null) {
                     break label36;
                  }
               } else if (var10000.equals(var2)) {
                  break label36;
               }

               var10000 = pod.getStatus().getPhase().toLowerCase(Locale.ROOT);
               String var3 = "running";
               if (var10000 == null) {
                  if (var3 != null) {
                     break label41;
                  }
               } else if (!var10000.equals(var3)) {
                  break label41;
               }
            }
         }

         var5 = false;
         return var5;
      }

      var5 = true;
      return var5;
   }

   public ExecutorPodsSnapshot apply(final scala.collection.immutable.Map executorPods, final long fullSnapshotTs) {
      return new ExecutorPodsSnapshot(executorPods, fullSnapshotTs);
   }

   public Option unapply(final ExecutorPodsSnapshot x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.executorPods(), BoxesRunTime.boxToLong(x$0.fullSnapshotTs()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorPodsSnapshot$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$toState$3(final ContainerStatus x$2) {
      boolean var2;
      label23: {
         String var10000 = x$2.getName();
         String var1 = MODULE$.sparkContainerName();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final int $anonfun$toState$7(final Integer x$3) {
      return .MODULE$.Integer2int(x$3);
   }

   private ExecutorPodsSnapshot$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
