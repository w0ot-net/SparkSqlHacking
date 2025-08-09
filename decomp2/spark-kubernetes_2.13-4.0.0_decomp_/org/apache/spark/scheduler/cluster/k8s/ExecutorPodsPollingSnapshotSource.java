package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.api.model.ListOptionsBuilder;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.FilterWatchListDeletable;
import io.fabric8.kubernetes.client.dsl.Filterable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Stable;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import org.sparkproject.guava.primitives.UnsignedLong;
import scala.Function0;
import scala.StringContext;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@Stable
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ee\u0001\u0002\f\u0018\u0001\u0011B\u0001\"\r\u0001\u0003\u0002\u0003\u0006IA\r\u0005\tm\u0001\u0011\t\u0011)A\u0005o!A1\t\u0001B\u0001B\u0003%A\t\u0003\u0005I\u0001\t\u0005\t\u0015!\u0003J\u0011\u0015\u0019\u0006\u0001\"\u0001U\u0011\u001dQ\u0006A1A\u0005\nmCaa\u0018\u0001!\u0002\u0013a\u0006b\u00021\u0001\u0005\u0004%I!\u0019\u0005\u0007K\u0002\u0001\u000b\u0011\u00022\t\u0013\u0019\u0004\u0001\u0019!a\u0001\n\u00139\u0007\"\u0003=\u0001\u0001\u0004\u0005\r\u0011\"\u0003z\u0011%\u0001\b\u00011A\u0001B\u0003&\u0001\u000eC\u0004\u0002\b\u0001!\t!!\u0003\t\u000f\u0005]\u0002\u0001\"\u0001\u0002:\u00191\u0011Q\b\u0001\u0005\u0003\u007fA!\"!\u0004\u0010\u0005\u0003\u0005\u000b\u0011BA\b\u0011\u0019\u0019v\u0002\"\u0001\u0002T!Y\u00111L\bA\u0002\u0003\u0007I\u0011BA/\u0011-\t9h\u0004a\u0001\u0002\u0004%I!!\u001f\t\u0017\u0005ut\u00021A\u0001B\u0003&\u0011q\f\u0005\b\u0003\u007fzA\u0011IA\u001d\u0005\u0005*\u00050Z2vi>\u0014\bk\u001c3t!>dG.\u001b8h':\f\u0007o\u001d5piN{WO]2f\u0015\tA\u0012$A\u0002lqMT!AG\u000e\u0002\u000f\rdWo\u001d;fe*\u0011A$H\u0001\ng\u000eDW\rZ;mKJT!AH\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0001\n\u0013AB1qC\u000eDWMC\u0001#\u0003\ry'oZ\u0002\u0001'\r\u0001Qe\u000b\t\u0003M%j\u0011a\n\u0006\u0002Q\u0005)1oY1mC&\u0011!f\n\u0002\u0007\u0003:L(+\u001a4\u0011\u00051zS\"A\u0017\u000b\u00059j\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u0005Aj#a\u0002'pO\u001eLgnZ\u0001\u0005G>tg\r\u0005\u00024i5\tQ$\u0003\u00026;\tI1\u000b]1sW\u000e{gNZ\u0001\u0011WV\u0014WM\u001d8fi\u0016\u001c8\t\\5f]R\u0004\"\u0001O!\u000e\u0003eR!AO\u001e\u0002\r\rd\u0017.\u001a8u\u0015\taT(\u0001\u0006lk\n,'O\\3uKNT!AP \u0002\u000f\u0019\f'M]5dq)\t\u0001)\u0001\u0002j_&\u0011!)\u000f\u0002\u0011\u0017V\u0014WM\u001d8fi\u0016\u001c8\t\\5f]R\fab\u001d8baNDw\u000e^:Ti>\u0014X\r\u0005\u0002F\r6\tq#\u0003\u0002H/\tQR\t_3dkR|'\u000fU8egNs\u0017\r]:i_R\u001c8\u000b^8sK\u0006y\u0001o\u001c7mS:<W\t_3dkR|'\u000f\u0005\u0002K#6\t1J\u0003\u0002M\u001b\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u00059{\u0015\u0001B;uS2T\u0011\u0001U\u0001\u0005U\u00064\u0018-\u0003\u0002S\u0017\nA2k\u00195fIVdW\rZ#yK\u000e,Ho\u001c:TKJ4\u0018nY3\u0002\rqJg.\u001b;?)\u0015)fk\u0016-Z!\t)\u0005\u0001C\u00032\u000b\u0001\u0007!\u0007C\u00037\u000b\u0001\u0007q\u0007C\u0003D\u000b\u0001\u0007A\tC\u0003I\u000b\u0001\u0007\u0011*A\bq_2d\u0017N\\4J]R,'O^1m+\u0005a\u0006C\u0001\u0014^\u0013\tqvE\u0001\u0003M_:<\u0017\u0001\u00059pY2LgnZ%oi\u0016\u0014h/\u00197!\u00039\u0001x\u000e\u001c7j]\u001e,e.\u00192mK\u0012,\u0012A\u0019\t\u0003M\rL!\u0001Z\u0014\u0003\u000f\t{w\u000e\\3b]\u0006y\u0001o\u001c7mS:<WI\\1cY\u0016$\u0007%A\u0007q_2d\u0017N\\4GkR,(/Z\u000b\u0002QB\u0012\u0011N\u001c\t\u0004\u0015*d\u0017BA6L\u0005\u00191U\u000f^;sKB\u0011QN\u001c\u0007\u0001\t%yG\"!A\u0001\u0002\u000b\u0005\u0011OA\u0002`IE\na\u0002]8mY&twMR;ukJ,\u0007%\u0005\u0002skB\u0011ae]\u0005\u0003i\u001e\u0012qAT8uQ&tw\r\u0005\u0002'm&\u0011qo\n\u0002\u0004\u0003:L\u0018!\u00059pY2Lgn\u001a$viV\u0014Xm\u0018\u0013fcR\u0011!0 \t\u0003MmL!\u0001`\u0014\u0003\tUs\u0017\u000e\u001e\u0005\b}.\t\t\u00111\u0001\u0000\u0003\rAH%\r\u0019\u0005\u0003\u0003\t)\u0001\u0005\u0003KU\u0006\r\u0001cA7\u0002\u0006\u0011Iq.`A\u0001\u0002\u0003\u0015\t!]\u0001\u0006gR\f'\u000f\u001e\u000b\u0004u\u0006-\u0001bBA\u0007\u001b\u0001\u0007\u0011qB\u0001\u000eCB\u0004H.[2bi&|g.\u00133\u0011\t\u0005E\u0011q\u0004\b\u0005\u0003'\tY\u0002E\u0002\u0002\u0016\u001dj!!a\u0006\u000b\u0007\u0005e1%\u0001\u0004=e>|GOP\u0005\u0004\u0003;9\u0013A\u0002)sK\u0012,g-\u0003\u0003\u0002\"\u0005\r\"AB*ue&twMC\u0002\u0002\u001e\u001dBS!DA\u0014\u0003g\u0001B!!\u000b\u000205\u0011\u00111\u0006\u0006\u0004\u0003[i\u0012AC1o]>$\u0018\r^5p]&!\u0011\u0011GA\u0016\u0005\u0015\u0019\u0016N\\2fC\t\t)$A\u00034]Er3'\u0001\u0003ti>\u0004H#\u0001>)\u000b9\t9#a\r\u0003\u0019A{G\u000e\u001c*v]:\f'\r\\3\u0014\u000b=\t\t%!\u0014\u0011\t\u0005\r\u0013\u0011J\u0007\u0003\u0003\u000bR1!a\u0012P\u0003\u0011a\u0017M\\4\n\t\u0005-\u0013Q\t\u0002\u0007\u001f\nTWm\u0019;\u0011\t\u0005\r\u0013qJ\u0005\u0005\u0003#\n)E\u0001\u0005Sk:t\u0017M\u00197f)\u0011\t)&!\u0017\u0011\u0007\u0005]s\"D\u0001\u0001\u0011\u001d\ti!\u0005a\u0001\u0003\u001f\tqB]3t_V\u00148-\u001a,feNLwN\\\u000b\u0003\u0003?\u0002B!!\u0019\u0002t5\u0011\u00111\r\u0006\u0005\u0003K\n9'\u0001\u0006qe&l\u0017\u000e^5wKNTA!!\u001b\u0002l\u000511m\\7n_:TA!!\u001c\u0002p\u00051qm\\8hY\u0016T!!!\u001d\u0002\u0007\r|W.\u0003\u0003\u0002v\u0005\r$\u0001D+og&<g.\u001a3M_:<\u0017a\u0005:fg>,(oY3WKJ\u001c\u0018n\u001c8`I\u0015\fHc\u0001>\u0002|!AapEA\u0001\u0002\u0004\ty&\u0001\tsKN|WO]2f-\u0016\u00148/[8oA\u0005\u0019!/\u001e8)\u0007\u0001\t\u0019\t\u0005\u0003\u0002*\u0005\u0015\u0015\u0002BAD\u0003W\u0011aa\u0015;bE2,\u0007f\u0001\u0001\u0002\fB!\u0011\u0011FAG\u0013\u0011\ty)a\u000b\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5"
)
public class ExecutorPodsPollingSnapshotSource implements Logging {
   public final SparkConf org$apache$spark$scheduler$cluster$k8s$ExecutorPodsPollingSnapshotSource$$conf;
   public final KubernetesClient org$apache$spark$scheduler$cluster$k8s$ExecutorPodsPollingSnapshotSource$$kubernetesClient;
   public final ExecutorPodsSnapshotsStore org$apache$spark$scheduler$cluster$k8s$ExecutorPodsPollingSnapshotSource$$snapshotsStore;
   private final ScheduledExecutorService pollingExecutor;
   private final long pollingInterval;
   private final boolean pollingEnabled;
   private Future pollingFuture;
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

   private long pollingInterval() {
      return this.pollingInterval;
   }

   private boolean pollingEnabled() {
      return this.pollingEnabled;
   }

   private Future pollingFuture() {
      return this.pollingFuture;
   }

   private void pollingFuture_$eq(final Future x$1) {
      this.pollingFuture = x$1;
   }

   public void start(final String applicationId) {
      if (this.pollingEnabled()) {
         .MODULE$.require(this.pollingFuture() == null, () -> "Cannot start polling more than once.");
         this.logDebug((Function0)(() -> "Starting to check for executor pod state every " + this.pollingInterval() + " ms."));
         this.pollingFuture_$eq(this.pollingExecutor.scheduleWithFixedDelay(new PollRunnable(applicationId), this.pollingInterval(), this.pollingInterval(), TimeUnit.MILLISECONDS));
      }
   }

   public void stop() {
      if (this.pollingFuture() != null) {
         this.pollingFuture().cancel(true);
         this.pollingFuture_$eq((Future)null);
      }

      org.apache.spark.util.ThreadUtils..MODULE$.shutdown(this.pollingExecutor, org.apache.spark.util.ThreadUtils..MODULE$.shutdown$default$2());
   }

   public ExecutorPodsPollingSnapshotSource(final SparkConf conf, final KubernetesClient kubernetesClient, final ExecutorPodsSnapshotsStore snapshotsStore, final ScheduledExecutorService pollingExecutor) {
      this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsPollingSnapshotSource$$conf = conf;
      this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsPollingSnapshotSource$$kubernetesClient = kubernetesClient;
      this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsPollingSnapshotSource$$snapshotsStore = snapshotsStore;
      this.pollingExecutor = pollingExecutor;
      Logging.$init$(this);
      this.pollingInterval = BoxesRunTime.unboxToLong(conf.get(Config$.MODULE$.KUBERNETES_EXECUTOR_API_POLLING_INTERVAL()));
      this.pollingEnabled = BoxesRunTime.unboxToBoolean(conf.get(Config$.MODULE$.KUBERNETES_EXECUTOR_ENABLE_API_POLLING()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class PollRunnable implements Runnable {
      private final String applicationId;
      private UnsignedLong resourceVersion;
      // $FF: synthetic field
      public final ExecutorPodsPollingSnapshotSource $outer;

      private UnsignedLong resourceVersion() {
         return this.resourceVersion;
      }

      private void resourceVersion_$eq(final UnsignedLong x$1) {
         this.resourceVersion = x$1;
      }

      public void run() {
         org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> {
            this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsPollingSnapshotSource$PollRunnable$$$outer().logDebug((Function0)(() -> "Resynchronizing full executor pod state from Kubernetes."));
            FilterWatchListDeletable pods = (FilterWatchListDeletable)((Filterable)((Filterable)this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsPollingSnapshotSource$PollRunnable$$$outer().org$apache$spark$scheduler$cluster$k8s$ExecutorPodsPollingSnapshotSource$$kubernetesClient.pods().withLabel(Constants$.MODULE$.SPARK_APP_ID_LABEL(), this.applicationId)).withLabel(Constants$.MODULE$.SPARK_ROLE_LABEL(), Constants$.MODULE$.SPARK_POD_EXECUTOR_ROLE())).withoutLabel(Constants$.MODULE$.SPARK_EXECUTOR_INACTIVE_LABEL(), "true");
            if (BoxesRunTime.unboxToBoolean(this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsPollingSnapshotSource$PollRunnable$$$outer().org$apache$spark$scheduler$cluster$k8s$ExecutorPodsPollingSnapshotSource$$conf.get(Config$.MODULE$.KUBERNETES_EXECUTOR_API_POLLING_WITH_RESOURCE_VERSION()))) {
               PodList list = (PodList)pods.list(((ListOptionsBuilder)(new ListOptionsBuilder()).withResourceVersion("0")).build());
               UnsignedLong newResourceVersion = UnsignedLong.valueOf(list.getMetadata().getResourceVersion());
               if (this.resourceVersion() == null || newResourceVersion.compareTo(this.resourceVersion()) >= 0) {
                  this.resourceVersion_$eq(newResourceVersion);
                  this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsPollingSnapshotSource$PollRunnable$$$outer().org$apache$spark$scheduler$cluster$k8s$ExecutorPodsPollingSnapshotSource$$snapshotsStore.replaceSnapshot(scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(list.getItems()).asScala().toSeq());
               }
            } else {
               this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsPollingSnapshotSource$PollRunnable$$$outer().org$apache$spark$scheduler$cluster$k8s$ExecutorPodsPollingSnapshotSource$$snapshotsStore.replaceSnapshot(scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(((PodList)pods.list()).getItems()).asScala().toSeq());
            }
         });
      }

      // $FF: synthetic method
      public ExecutorPodsPollingSnapshotSource org$apache$spark$scheduler$cluster$k8s$ExecutorPodsPollingSnapshotSource$PollRunnable$$$outer() {
         return this.$outer;
      }

      public PollRunnable(final String applicationId) {
         this.applicationId = applicationId;
         if (ExecutorPodsPollingSnapshotSource.this == null) {
            throw null;
         } else {
            this.$outer = ExecutorPodsPollingSnapshotSource.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
