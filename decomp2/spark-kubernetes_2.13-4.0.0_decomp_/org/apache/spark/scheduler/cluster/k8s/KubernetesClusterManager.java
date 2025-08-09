package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.client.KubernetesClient;
import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkMasterRegex.;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesConf$;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.deploy.k8s.SparkKubernetesClientFactory;
import org.apache.spark.deploy.k8s.SparkKubernetesClientFactory$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.scheduler.ExternalClusterManager;
import org.apache.spark.scheduler.SchedulerBackend;
import org.apache.spark.scheduler.TaskScheduler;
import org.apache.spark.scheduler.TaskSchedulerImpl;
import org.apache.spark.scheduler.local.LocalSchedulerBackend;
import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple3;
import scala.collection.LinearSeqOps;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005e4Q\u0001C\u0005\u0001\u001fUAQA\n\u0001\u0005\u0002!BQa\u000b\u0001\u0005B1BQ!\u0010\u0001\u0005\nyBQ!\u0012\u0001\u0005B\u0019CQ\u0001\u0015\u0001\u0005BECa\u0001\u0017\u0001\u0005\u0002%I\u0006\"B9\u0001\t\u0003\u0012(\u0001G&vE\u0016\u0014h.\u001a;fg\u000ecWo\u001d;fe6\u000bg.Y4fe*\u0011!bC\u0001\u0004Wb\u001a(B\u0001\u0007\u000e\u0003\u001d\u0019G.^:uKJT!AD\b\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(B\u0001\t\u0012\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00112#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002)\u0005\u0019qN]4\u0014\t\u00011B\u0004\t\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0005uqR\"A\u0007\n\u0005}i!AF#yi\u0016\u0014h.\u00197DYV\u001cH/\u001a:NC:\fw-\u001a:\u0011\u0005\u0005\"S\"\u0001\u0012\u000b\u0005\rz\u0011\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0015\u0012#a\u0002'pO\u001eLgnZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t\u0011\u0006\u0005\u0002+\u00015\t\u0011\"A\u0005dC:\u001c%/Z1uKR\u0011Q\u0006\r\t\u0003/9J!a\f\r\u0003\u000f\t{w\u000e\\3b]\")\u0011G\u0001a\u0001e\u0005IQ.Y:uKJ,&\u000b\u0014\t\u0003gir!\u0001\u000e\u001d\u0011\u0005UBR\"\u0001\u001c\u000b\u0005]:\u0013A\u0002\u001fs_>$h(\u0003\u0002:1\u00051\u0001K]3eK\u001aL!a\u000f\u001f\u0003\rM#(/\u001b8h\u0015\tI\u0004$A\u0004jg2{7-\u00197\u0015\u00055z\u0004\"\u0002!\u0004\u0001\u0004\t\u0015\u0001B2p]\u001a\u0004\"AQ\"\u000e\u0003=I!\u0001R\b\u0003\u0013M\u0003\u0018M]6D_:4\u0017aE2sK\u0006$X\rV1tWN\u001b\u0007.\u001a3vY\u0016\u0014HcA$K\u001fB\u0011Q\u0004S\u0005\u0003\u00136\u0011Q\u0002V1tWN\u001b\u0007.\u001a3vY\u0016\u0014\b\"B&\u0005\u0001\u0004a\u0015AA:d!\t\u0011U*\u0003\u0002O\u001f\ta1\u000b]1sW\u000e{g\u000e^3yi\")\u0011\u0007\u0002a\u0001e\u000512M]3bi\u0016\u001c6\r[3ek2,'OQ1dW\u0016tG\r\u0006\u0003S+Z;\u0006CA\u000fT\u0013\t!VB\u0001\tTG\",G-\u001e7fe\n\u000b7m[3oI\")1*\u0002a\u0001\u0019\")\u0011'\u0002a\u0001e!)a\"\u0002a\u0001\u000f\u0006IR.Y6f\u000bb,7-\u001e;peB{Gm]!mY>\u001c\u0017\r^8s)\u0011QVL\u00187\u0011\u0005)Z\u0016B\u0001/\n\u0005U\t%m\u001d;sC\u000e$\bk\u001c3t\u00032dwnY1u_JDQa\u0013\u0004A\u00021CQa\u0018\u0004A\u0002\u0001\f\u0001c[;cKJtW\r^3t\u00072LWM\u001c;\u0011\u0005\u0005TW\"\u00012\u000b\u0005\r$\u0017AB2mS\u0016tGO\u0003\u0002fM\u0006Q1.\u001e2fe:,G/Z:\u000b\u0005\u001dD\u0017a\u00024bEJL7\r\u000f\u0006\u0002S\u0006\u0011\u0011n\\\u0005\u0003W\n\u0014\u0001cS;cKJtW\r^3t\u00072LWM\u001c;\t\u000b54\u0001\u0019\u00018\u0002\u001dMt\u0017\r]:i_R\u001c8\u000b^8sKB\u0011!f\\\u0005\u0003a&\u0011!$\u0012=fGV$xN\u001d)pIN\u001cf.\u00199tQ>$8o\u0015;pe\u0016\f!\"\u001b8ji&\fG.\u001b>f)\r\u0019ho\u001e\t\u0003/QL!!\u001e\r\u0003\tUs\u0017\u000e\u001e\u0005\u0006\u001d\u001d\u0001\ra\u0012\u0005\u0006q\u001e\u0001\rAU\u0001\bE\u0006\u001c7.\u001a8e\u0001"
)
public class KubernetesClusterManager implements ExternalClusterManager, Logging {
   private transient Logger org$apache$spark$internal$Logging$$log_;

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
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public boolean canCreate(final String masterURL) {
      return masterURL.startsWith("k8s");
   }

   private boolean isLocal(final SparkConf conf) {
      return ((String)conf.get(Config$.MODULE$.KUBERNETES_DRIVER_MASTER_URL())).startsWith("local");
   }

   public TaskScheduler createTaskScheduler(final SparkContext sc, final String masterURL) {
      String var6 = (String)sc.conf().get(Config$.MODULE$.KUBERNETES_DRIVER_MASTER_URL());
      boolean var10000;
      if ("local".equals(var6)) {
         var10000 = 1;
      } else {
         label40: {
            if (var6 != null) {
               Option var7 = .MODULE$.LOCAL_N_REGEX().unapplySeq(var6);
               if (!var7.isEmpty() && var7.get() != null && ((List)var7.get()).lengthCompare(1) == 0) {
                  var10000 = 1;
                  break label40;
               }
            }

            var10000 = 0;
         }
      }

      if (var10000) {
         var10000 = 1;
      } else {
         label32: {
            if (var6 != null) {
               Option var8 = .MODULE$.LOCAL_N_FAILURES_REGEX().unapplySeq(var6);
               if (!var8.isEmpty() && var8.get() != null && ((List)var8.get()).lengthCompare(2) == 0) {
                  String maxFailures = (String)((LinearSeqOps)var8.get()).apply(1);
                  var10000 = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(maxFailures));
                  break label32;
               }
            }

            var10000 = BoxesRunTime.unboxToInt(sc.conf().get(org.apache.spark.internal.config.package..MODULE$.TASK_MAX_FAILURES()));
         }
      }

      int maxTaskFailures = var10000;
      return new TaskSchedulerImpl(sc, maxTaskFailures, this.isLocal(sc.conf()), org.apache.spark.scheduler.TaskSchedulerImpl..MODULE$.$lessinit$greater$default$4());
   }

   public SchedulerBackend createSchedulerBackend(final SparkContext sc, final String masterURL, final TaskScheduler scheduler) {
      if (!this.isLocal(sc.conf())) {
         boolean wasSparkSubmittedInClusterMode = BoxesRunTime.unboxToBoolean(sc.conf().get(Config$.MODULE$.KUBERNETES_DRIVER_SUBMIT_CHECK()));
         Tuple3 var40;
         if (wasSparkSubmittedInClusterMode) {
            scala.Predef..MODULE$.require(((Option)sc.conf().get(Config$.MODULE$.KUBERNETES_DRIVER_POD_NAME())).isDefined(), () -> "If the application is deployed using spark-submit in cluster mode, the driver pod name must be provided.");
            Option serviceAccountCaCrt = (new Some(new File("/var/run/secrets/kubernetes.io/serviceaccount/ca.crt"))).filter((x$2x) -> BoxesRunTime.boxToBoolean($anonfun$createSchedulerBackend$5(x$2x)));
            var40 = new Tuple3(Config$.MODULE$.KUBERNETES_AUTH_DRIVER_MOUNTED_CONF_PREFIX(), sc.conf().get(Config$.MODULE$.KUBERNETES_DRIVER_MASTER_URL()), serviceAccountCaCrt);
         } else {
            var40 = new Tuple3(Config$.MODULE$.KUBERNETES_AUTH_CLIENT_MODE_PREFIX(), KubernetesUtils$.MODULE$.parseMasterUrl(masterURL), scala.None..MODULE$);
         }

         Tuple3 var19 = var40;
         if (var19 != null) {
            String authConfPrefix = (String)var19._1();
            String apiServerUri = (String)var19._2();
            Option defaultServiceAccountCaCrt = (Option)var19._3();
            Tuple3 var18 = new Tuple3(authConfPrefix, apiServerUri, defaultServiceAccountCaCrt);
            String authConfPrefix = (String)var18._1();
            String apiServerUri = (String)var18._2();
            Option defaultServiceAccountCaCrt = (Option)var18._3();
            if (!sc.conf().contains(Config$.MODULE$.KUBERNETES_EXECUTOR_POD_NAME_PREFIX())) {
               sc.conf().set(Config$.MODULE$.KUBERNETES_EXECUTOR_POD_NAME_PREFIX(), KubernetesConf$.MODULE$.getResourceNamePrefix(sc.conf().get("spark.app.name")));
            } else {
               BoxedUnit var41 = BoxedUnit.UNIT;
            }

            KubernetesClient kubernetesClient = SparkKubernetesClientFactory$.MODULE$.createKubernetesClient(apiServerUri, new Some(sc.conf().get(Config$.MODULE$.KUBERNETES_NAMESPACE())), authConfPrefix, SparkKubernetesClientFactory.ClientType$.MODULE$.Driver(), sc.conf(), defaultServiceAccountCaCrt);
            if (((Option)sc.conf().get(Config$.MODULE$.KUBERNETES_EXECUTOR_PODTEMPLATE_FILE())).isDefined()) {
               KubernetesUtils$.MODULE$.loadPodFromTemplate(kubernetesClient, (String)((Option)sc.conf().get(Config$.MODULE$.KUBERNETES_EXECUTOR_PODTEMPLATE_FILE())).get(), (Option)sc.conf().get(Config$.MODULE$.KUBERNETES_EXECUTOR_PODTEMPLATE_CONTAINER_NAME()), sc.conf());
            } else {
               BoxedUnit var42 = BoxedUnit.UNIT;
            }

            ScheduledExecutorService schedulerExecutorService = org.apache.spark.util.ThreadUtils..MODULE$.newDaemonSingleThreadScheduledExecutor("kubernetes-executor-maintenance");
            ExecutorPodsSnapshot$.MODULE$.setShouldCheckAllContainers(BoxesRunTime.unboxToBoolean(sc.conf().get(Config$.MODULE$.KUBERNETES_EXECUTOR_CHECK_ALL_CONTAINERS())));
            String sparkContainerName = (String)((Option)sc.conf().get(Config$.MODULE$.KUBERNETES_EXECUTOR_PODTEMPLATE_CONTAINER_NAME())).getOrElse(() -> Constants$.MODULE$.DEFAULT_EXECUTOR_CONTAINER_NAME());
            ExecutorPodsSnapshot$.MODULE$.setSparkContainerName(sparkContainerName);
            ScheduledExecutorService subscribersExecutor = org.apache.spark.util.ThreadUtils..MODULE$.newDaemonThreadPoolScheduledExecutor("kubernetes-executor-snapshots-subscribers", 2);
            SparkConf x$2 = sc.conf();
            Clock x$3 = ExecutorPodsSnapshotsStoreImpl$.MODULE$.$lessinit$greater$default$2();
            ExecutorPodsSnapshotsStoreImpl snapshotsStore = new ExecutorPodsSnapshotsStoreImpl(subscribersExecutor, x$3, x$2);
            ExecutorPodsLifecycleManager executorPodsLifecycleEventHandler = new ExecutorPodsLifecycleManager(sc.conf(), kubernetesClient, snapshotsStore, ExecutorPodsLifecycleManager$.MODULE$.$lessinit$greater$default$4());
            AbstractPodsAllocator executorPodsAllocator = this.makeExecutorPodsAllocator(sc, kubernetesClient, snapshotsStore);
            ExecutorPodsWatchSnapshotSource podsWatchEventSource = new ExecutorPodsWatchSnapshotSource(snapshotsStore, kubernetesClient, sc.conf());
            ScheduledExecutorService eventsPollingExecutor = org.apache.spark.util.ThreadUtils..MODULE$.newDaemonSingleThreadScheduledExecutor("kubernetes-executor-pod-polling-sync");
            ExecutorPodsPollingSnapshotSource podsPollingEventSource = new ExecutorPodsPollingSnapshotSource(sc.conf(), kubernetesClient, snapshotsStore, eventsPollingExecutor);
            return new KubernetesClusterSchedulerBackend((TaskSchedulerImpl)scheduler, sc, kubernetesClient, schedulerExecutorService, snapshotsStore, executorPodsAllocator, executorPodsLifecycleEventHandler, podsWatchEventSource, podsPollingEventSource);
         } else {
            throw new MatchError(var19);
         }
      } else {
         int var10000;
         label85: {
            String var7 = (String)sc.conf().get(Config$.MODULE$.KUBERNETES_DRIVER_MASTER_URL());
            if (var7 != null) {
               Option var8 = .MODULE$.LOCAL_N_REGEX().unapplySeq(var7);
               if (!var8.isEmpty() && var8.get() != null && ((List)var8.get()).lengthCompare(1) == 0) {
                  label72: {
                     String threads = (String)((LinearSeqOps)var8.get()).apply(0);
                     String var10 = "*";
                     if (threads == null) {
                        if (var10 == null) {
                           break label72;
                        }
                     } else if (threads.equals(var10)) {
                        break label72;
                     }

                     var10000 = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(threads));
                     break label85;
                  }

                  var10000 = localCpuCount$1();
                  break label85;
               }
            }

            if (var7 != null) {
               Option var11 = .MODULE$.LOCAL_N_FAILURES_REGEX().unapplySeq(var7);
               if (!var11.isEmpty() && var11.get() != null && ((List)var11.get()).lengthCompare(2) == 0) {
                  label59: {
                     String threads = (String)((LinearSeqOps)var11.get()).apply(0);
                     String var13 = "*";
                     if (threads == null) {
                        if (var13 == null) {
                           break label59;
                        }
                     } else if (threads.equals(var13)) {
                        break label59;
                     }

                     var10000 = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(threads));
                     break label85;
                  }

                  var10000 = localCpuCount$1();
                  break label85;
               }
            }

            var10000 = 1;
         }

         int threadCount = var10000;
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Running Spark with ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, sc.conf().get(Config$.MODULE$.KUBERNETES_DRIVER_MASTER_URL()))})))));
         TaskSchedulerImpl schedulerImpl = (TaskSchedulerImpl)scheduler;
         Option conf = sc.conf().getOption("spark.app.id").map((x$1) -> sc.conf().set("spark.test.appId", x$1));
         LocalSchedulerBackend backend = new LocalSchedulerBackend((SparkConf)conf.getOrElse(() -> sc.conf()), schedulerImpl, threadCount);
         schedulerImpl.initialize(backend);
         return backend;
      }
   }

   public AbstractPodsAllocator makeExecutorPodsAllocator(final SparkContext sc, final KubernetesClient kubernetesClient, final ExecutorPodsSnapshotsStore snapshotsStore) {
      String var10000;
      label24: {
         String var6 = (String)sc.conf().get(Config$.MODULE$.KUBERNETES_ALLOCATION_PODS_ALLOCATOR());
         switch (var6 == null ? 0 : var6.hashCode()) {
            case -1331586071:
               if ("direct".equals(var6)) {
                  var10000 = ExecutorPodsAllocator.class.getName();
                  break label24;
               }
               break;
            case 1736521110:
               if ("statefulset".equals(var6)) {
                  var10000 = StatefulSetPodsAllocator.class.getName();
                  break label24;
               }
         }

         var10000 = var6;
      }

      String executorPodsAllocatorName = var10000;
      Class cls = org.apache.spark.util.Utils..MODULE$.classForName(executorPodsAllocatorName, org.apache.spark.util.Utils..MODULE$.classForName$default$2(), org.apache.spark.util.Utils..MODULE$.classForName$default$3());
      Constructor cstr = cls.getConstructor(SparkConf.class, SecurityManager.class, KubernetesExecutorBuilder.class, KubernetesClient.class, ExecutorPodsSnapshotsStore.class, Clock.class);
      return (AbstractPodsAllocator)cstr.newInstance(sc.conf(), sc.env().securityManager(), new KubernetesExecutorBuilder(), kubernetesClient, snapshotsStore, new SystemClock());
   }

   public void initialize(final TaskScheduler scheduler, final SchedulerBackend backend) {
      ((TaskSchedulerImpl)scheduler).initialize(backend);
   }

   private static final int localCpuCount$1() {
      return Runtime.getRuntime().availableProcessors();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createSchedulerBackend$5(final File x$2) {
      return x$2.exists();
   }

   public KubernetesClusterManager() {
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
