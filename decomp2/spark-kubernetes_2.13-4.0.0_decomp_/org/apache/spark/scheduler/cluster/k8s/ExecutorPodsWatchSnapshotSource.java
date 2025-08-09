package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;
import io.fabric8.kubernetes.client.dsl.Filterable;
import io.fabric8.kubernetes.client.dsl.Watchable;
import java.io.Closeable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Stable;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@Stable
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005ud\u0001\u0002\u000b\u0016\u0001\tB\u0001b\f\u0001\u0003\u0002\u0003\u0006I\u0001\r\u0005\ti\u0001\u0011\t\u0011)A\u0005k!A\u0011\t\u0001B\u0001B\u0003%!\tC\u0003G\u0001\u0011\u0005q\tC\u0005M\u0001\u0001\u0007\t\u0019!C\u0005\u001b\"IQ\u000b\u0001a\u0001\u0002\u0004%IA\u0016\u0005\n9\u0002\u0001\r\u0011!Q!\n9Cq!\u0018\u0001C\u0002\u0013%a\f\u0003\u0004c\u0001\u0001\u0006Ia\u0018\u0005\bG\u0002\u0011\r\u0011\"\u0003e\u0011\u0019\u0001\b\u0001)A\u0005K\")a\t\u0001C\u0001c\")A\u000f\u0001C\u0001k\"9\u00111\u0001\u0001\u0005\u0002\u0005\u0015aABA\u0005\u0001\u0011\tY\u0001\u0003\u0004G\u001f\u0011\u0005\u0011q\u0006\u0005\b\u0003kyA\u0011IA\u001c\u0011\u001d\tyf\u0004C!\u0003CBq!a\u0018\u0010\t\u0003\n)AA\u0010Fq\u0016\u001cW\u000f^8s!>$7oV1uG\"\u001cf.\u00199tQ>$8k\\;sG\u0016T!AF\f\u0002\u0007-D4O\u0003\u0002\u00193\u000591\r\\;ti\u0016\u0014(B\u0001\u000e\u001c\u0003%\u00198\r[3ek2,'O\u0003\u0002\u001d;\u0005)1\u000f]1sW*\u0011adH\u0001\u0007CB\f7\r[3\u000b\u0003\u0001\n1a\u001c:h\u0007\u0001\u00192\u0001A\u0012*!\t!s%D\u0001&\u0015\u00051\u0013!B:dC2\f\u0017B\u0001\u0015&\u0005\u0019\te.\u001f*fMB\u0011!&L\u0007\u0002W)\u0011AfG\u0001\tS:$XM\u001d8bY&\u0011af\u000b\u0002\b\u0019><w-\u001b8h\u00039\u0019h.\u00199tQ>$8o\u0015;pe\u0016\u0004\"!\r\u001a\u000e\u0003UI!aM\u000b\u00035\u0015CXmY;u_J\u0004v\u000eZ:T]\u0006\u00048\u000f[8ugN#xN]3\u0002!-,(-\u001a:oKR,7o\u00117jK:$\bC\u0001\u001c@\u001b\u00059$B\u0001\u001d:\u0003\u0019\u0019G.[3oi*\u0011!hO\u0001\u000bWV\u0014WM\u001d8fi\u0016\u001c(B\u0001\u001f>\u0003\u001d1\u0017M\u0019:jGbR\u0011AP\u0001\u0003S>L!\u0001Q\u001c\u0003!-+(-\u001a:oKR,7o\u00117jK:$\u0018\u0001B2p]\u001a\u0004\"a\u0011#\u000e\u0003mI!!R\u000e\u0003\u0013M\u0003\u0018M]6D_:4\u0017A\u0002\u001fj]&$h\b\u0006\u0003I\u0013*[\u0005CA\u0019\u0001\u0011\u0015yC\u00011\u00011\u0011\u0015!D\u00011\u00016\u0011\u0015\tE\u00011\u0001C\u0003=9\u0018\r^2i\u0007>tg.Z2uS>tW#\u0001(\u0011\u0005=\u001bV\"\u0001)\u000b\u0005y\n&\"\u0001*\u0002\t)\fg/Y\u0005\u0003)B\u0013\u0011b\u00117pg\u0016\f'\r\\3\u0002']\fGo\u00195D_:tWm\u0019;j_:|F%Z9\u0015\u0005]S\u0006C\u0001\u0013Y\u0013\tIVE\u0001\u0003V]&$\bbB.\u0007\u0003\u0003\u0005\rAT\u0001\u0004q\u0012\n\u0014\u0001E<bi\u000eD7i\u001c8oK\u000e$\u0018n\u001c8!\u00039)g.\u00192mK^\u000bGo\u00195j]\u001e,\u0012a\u0018\t\u0003I\u0001L!!Y\u0013\u0003\u000f\t{w\u000e\\3b]\u0006yQM\\1cY\u0016<\u0016\r^2iS:<\u0007%A\u0005oC6,7\u000f]1dKV\tQ\r\u0005\u0002g[:\u0011qm\u001b\t\u0003Q\u0016j\u0011!\u001b\u0006\u0003U\u0006\na\u0001\u0010:p_Rt\u0014B\u00017&\u0003\u0019\u0001&/\u001a3fM&\u0011an\u001c\u0002\u0007'R\u0014\u0018N\\4\u000b\u00051,\u0013A\u00038b[\u0016\u001c\b/Y2fAQ\u0019\u0001J]:\t\u000b=b\u0001\u0019\u0001\u0019\t\u000bQb\u0001\u0019A\u001b\u0002\u000bM$\u0018M\u001d;\u0015\u0005]3\b\"B<\u000e\u0001\u0004)\u0017!D1qa2L7-\u0019;j_:LE\rK\u0002\u000es~\u0004\"A_?\u000e\u0003mT!\u0001`\u000e\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002\u007fw\n)1+\u001b8dK\u0006\u0012\u0011\u0011A\u0001\u0006g9\ndfM\u0001\u0005gR|\u0007\u000fF\u0001XQ\rq\u0011p \u0002\u0014\u000bb,7-\u001e;peB{Gm],bi\u000eDWM]\n\u0006\u001f\u00055\u0011\u0011\u0004\t\u0005\u0003\u001f\t)\"\u0004\u0002\u0002\u0012)\u0019\u00111C)\u0002\t1\fgnZ\u0005\u0005\u0003/\t\tB\u0001\u0004PE*,7\r\u001e\t\u0006m\u0005m\u0011qD\u0005\u0004\u0003;9$aB,bi\u000eDWM\u001d\t\u0005\u0003C\tY#\u0004\u0002\u0002$)!\u0011QEA\u0014\u0003\u0015iw\u000eZ3m\u0015\r\tI#O\u0001\u0004CBL\u0017\u0002BA\u0017\u0003G\u00111\u0001U8e)\t\t\t\u0004E\u0002\u00024=i\u0011\u0001A\u0001\u000eKZ,g\u000e\u001e*fG\u0016Lg/\u001a3\u0015\u000b]\u000bI$a\u0017\t\u000f\u0005m\u0012\u00031\u0001\u0002>\u00051\u0011m\u0019;j_:\u0004B!a\u0010\u0002V9!\u0011\u0011IA)\u001d\u0011\t\u0019%a\u0014\u000f\t\u0005\u0015\u0013Q\n\b\u0005\u0003\u000f\nYED\u0002i\u0003\u0013J\u0011AP\u0005\u0003yuJ!AO\u001e\n\u0005aJ\u0014bAA*o\u00059q+\u0019;dQ\u0016\u0014\u0018\u0002BA,\u00033\u0012a!Q2uS>t'bAA*o!9\u0011QL\tA\u0002\u0005}\u0011a\u00019pI\u00069qN\\\"m_N,GcA,\u0002d!9\u0011Q\r\nA\u0002\u0005\u001d\u0014!A3\u0011\u0007Y\nI'C\u0002\u0002l]\u0012\u0001cV1uG\",'/\u0012=dKB$\u0018n\u001c8)\u0007\u0001\ty\u0007E\u0002{\u0003cJ1!a\u001d|\u0005\u0019\u0019F/\u00192mK\"\u001a\u0001!a\u001e\u0011\u0007i\fI(C\u0002\u0002|m\u0014A\u0002R3wK2|\u0007/\u001a:Ba&\u0004"
)
public class ExecutorPodsWatchSnapshotSource implements Logging {
   public final ExecutorPodsSnapshotsStore org$apache$spark$scheduler$cluster$k8s$ExecutorPodsWatchSnapshotSource$$snapshotsStore;
   private final KubernetesClient kubernetesClient;
   private Closeable watchConnection;
   private final boolean enableWatching;
   private final String namespace;
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

   private Closeable watchConnection() {
      return this.watchConnection;
   }

   private void watchConnection_$eq(final Closeable x$1) {
      this.watchConnection = x$1;
   }

   private boolean enableWatching() {
      return this.enableWatching;
   }

   private String namespace() {
      return this.namespace;
   }

   public void start(final String applicationId) {
      if (this.enableWatching()) {
         .MODULE$.require(this.watchConnection() == null, () -> "Cannot start the watcher twice.");
         this.logDebug((Function0)(() -> {
            String var10000 = Constants$.MODULE$.SPARK_APP_ID_LABEL();
            return "Starting to watch for pods with labels " + var10000 + "=" + applicationId + ", " + Constants$.MODULE$.SPARK_ROLE_LABEL() + "=" + Constants$.MODULE$.SPARK_POD_EXECUTOR_ROLE() + ".";
         }));
         this.watchConnection_$eq(((Watchable)((Filterable)((Filterable)this.kubernetesClient.pods().inNamespace(this.namespace())).withLabel(Constants$.MODULE$.SPARK_APP_ID_LABEL(), applicationId)).withLabel(Constants$.MODULE$.SPARK_ROLE_LABEL(), Constants$.MODULE$.SPARK_POD_EXECUTOR_ROLE())).watch(new ExecutorPodsWatcher()));
      }
   }

   public void stop() {
      if (this.watchConnection() != null) {
         org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.watchConnection().close());
         this.watchConnection_$eq((Closeable)null);
      }
   }

   public ExecutorPodsWatchSnapshotSource(final ExecutorPodsSnapshotsStore snapshotsStore, final KubernetesClient kubernetesClient, final SparkConf conf) {
      this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsWatchSnapshotSource$$snapshotsStore = snapshotsStore;
      this.kubernetesClient = kubernetesClient;
      Logging.$init$(this);
      this.enableWatching = BoxesRunTime.unboxToBoolean(conf.get(Config$.MODULE$.KUBERNETES_EXECUTOR_ENABLE_API_WATCHER()));
      this.namespace = (String)conf.get(Config$.MODULE$.KUBERNETES_NAMESPACE());
   }

   public ExecutorPodsWatchSnapshotSource(final ExecutorPodsSnapshotsStore snapshotsStore, final KubernetesClient kubernetesClient) {
      this(snapshotsStore, kubernetesClient, org.apache.spark.SparkContext..MODULE$.getOrCreate().conf());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class ExecutorPodsWatcher implements Watcher {
      // $FF: synthetic field
      public final ExecutorPodsWatchSnapshotSource $outer;

      public boolean reconnecting() {
         return super.reconnecting();
      }

      public void eventReceived(final Watcher.Action action, final Pod pod) {
         String podName = pod.getMetadata().getName();
         this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsWatchSnapshotSource$ExecutorPodsWatcher$$$outer().logDebug((Function0)(() -> "Received executor pod update for pod named " + podName + ", action " + action));
         this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsWatchSnapshotSource$ExecutorPodsWatcher$$$outer().org$apache$spark$scheduler$cluster$k8s$ExecutorPodsWatchSnapshotSource$$snapshotsStore.updatePod(pod);
      }

      public void onClose(final WatcherException e) {
         if (BoxesRunTime.unboxToBoolean(org.apache.spark.SparkContext..MODULE$.getActive().map((x$2) -> BoxesRunTime.boxToBoolean($anonfun$onClose$1(x$2))).getOrElse((JFunction0.mcZ.sp)() -> true))) {
            this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsWatchSnapshotSource$ExecutorPodsWatcher$$$outer().logInfo((Function0)(() -> "Kubernetes client has been closed."));
         } else {
            this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsWatchSnapshotSource$ExecutorPodsWatcher$$$outer().logWarning((Function0)(() -> "Kubernetes client has been closed (this is expected if the application is shutting down.)"), e);
         }
      }

      public void onClose() {
         if (BoxesRunTime.unboxToBoolean(org.apache.spark.SparkContext..MODULE$.getActive().map((x$3) -> BoxesRunTime.boxToBoolean($anonfun$onClose$5(x$3))).getOrElse((JFunction0.mcZ.sp)() -> true))) {
            this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsWatchSnapshotSource$ExecutorPodsWatcher$$$outer().logInfo((Function0)(() -> "Kubernetes client has been closed."));
         } else {
            this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsWatchSnapshotSource$ExecutorPodsWatcher$$$outer().logWarning((Function0)(() -> "Kubernetes client has been closed."));
         }
      }

      // $FF: synthetic method
      public ExecutorPodsWatchSnapshotSource org$apache$spark$scheduler$cluster$k8s$ExecutorPodsWatchSnapshotSource$ExecutorPodsWatcher$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$onClose$1(final SparkContext x$2) {
         return x$2.isStopped();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$onClose$5(final SparkContext x$3) {
         return x$3.isStopped();
      }

      public ExecutorPodsWatcher() {
         if (ExecutorPodsWatchSnapshotSource.this == null) {
            throw null;
         } else {
            this.$outer = ExecutorPodsWatchSnapshotSource.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
