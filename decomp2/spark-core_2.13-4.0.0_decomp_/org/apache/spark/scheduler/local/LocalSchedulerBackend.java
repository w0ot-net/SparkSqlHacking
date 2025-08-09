package org.apache.spark.scheduler.local;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.executor.ExecutorBackend;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.launcher.LauncherBackend;
import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkAppHandle.State;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.resource.ResourceProfile$;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.scheduler.LiveListenerBus;
import org.apache.spark.scheduler.SchedulerBackend;
import org.apache.spark.scheduler.SparkListenerExecutorAdded;
import org.apache.spark.scheduler.TaskSchedulerImpl;
import org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages;
import org.apache.spark.scheduler.cluster.ExecutorInfo;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.Option.;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}g!\u0002\u000f\u001e\u0001\u0005:\u0003\u0002\u0003 \u0001\u0005\u0003\u0005\u000b\u0011\u0002!\t\u0011\u0001\u0002!\u0011!Q\u0001\n\u0011C\u0001b\u0012\u0001\u0003\u0006\u0004%\t\u0001\u0013\u0005\t\u0019\u0002\u0011\t\u0011)A\u0005\u0013\")Q\n\u0001C\u0001\u001d\"9A\u000b\u0001b\u0001\n\u0013)\u0006BB1\u0001A\u0003%a\u000bC\u0004c\u0001\u0001\u0007I\u0011B2\t\u000f)\u0004\u0001\u0019!C\u0005W\"1\u0011\u000f\u0001Q!\n\u0011DqA\u001d\u0001C\u0002\u0013%1\u000fC\u0004\u0002\f\u0001\u0001\u000b\u0011\u0002;\t\u0013\u00055\u0001A1A\u0005\n\u0005=\u0001\u0002CA\f\u0001\u0001\u0006I!!\u0005\t\u0013\u0005e\u0001A1A\u0005\n\u0005m\u0001\u0002CA\u0015\u0001\u0001\u0006I!!\b\t\u000f\u0005-\u0002\u0001\"\u0001\u0002.!9\u0011\u0011\u0007\u0001\u0005B\u0005M\u0002bBA\u001b\u0001\u0011\u0005\u00131\u0007\u0005\b\u0003o\u0001A\u0011IA\u001a\u0011\u001d\tI\u0004\u0001C!\u0003wAq!!\u0010\u0001\t\u0003\ny\u0004C\u0004\u0002^\u0001!\t%a\u0018\t\u000f\u0005E\u0005\u0001\"\u0011\u0002\u0014\"9\u0011Q\u0013\u0001\u0005B\u0005]\u0005bBA\u001b\u0001\u0011%\u0011\u0011\u0016\u0005\b\u0003{\u0003A\u0011IA`\u0005UaunY1m'\u000eDW\rZ;mKJ\u0014\u0015mY6f]\u0012T!AH\u0010\u0002\u000b1|7-\u00197\u000b\u0005\u0001\n\u0013!C:dQ\u0016$W\u000f\\3s\u0015\t\u00113%A\u0003ta\u0006\u00148N\u0003\u0002%K\u00051\u0011\r]1dQ\u0016T\u0011AJ\u0001\u0004_J<7#\u0002\u0001)]IB\u0004CA\u0015-\u001b\u0005Q#\"A\u0016\u0002\u000bM\u001c\u0017\r\\1\n\u00055R#AB!osJ+g\r\u0005\u00020a5\tq$\u0003\u00022?\t\u00012k\u00195fIVdWM\u001d\"bG.,g\u000e\u001a\t\u0003gYj\u0011\u0001\u000e\u0006\u0003k\u0005\n\u0001\"\u001a=fGV$xN]\u0005\u0003oQ\u0012q\"\u0012=fGV$xN\u001d\"bG.,g\u000e\u001a\t\u0003sqj\u0011A\u000f\u0006\u0003w\u0005\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003{i\u0012q\u0001T8hO&tw-\u0001\u0003d_:47\u0001\u0001\t\u0003\u0003\nk\u0011!I\u0005\u0003\u0007\u0006\u0012\u0011b\u00159be.\u001cuN\u001c4\u0011\u0005=*\u0015B\u0001$ \u0005E!\u0016m]6TG\",G-\u001e7fe&k\u0007\u000f\\\u0001\u000bi>$\u0018\r\\\"pe\u0016\u001cX#A%\u0011\u0005%R\u0015BA&+\u0005\rIe\u000e^\u0001\fi>$\u0018\r\\\"pe\u0016\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0005\u001fF\u00136\u000b\u0005\u0002Q\u00015\tQ\u0004C\u0003?\u000b\u0001\u0007\u0001\tC\u0003!\u000b\u0001\u0007A\tC\u0003H\u000b\u0001\u0007\u0011*A\u0003baBLE-F\u0001W!\t9fL\u0004\u0002Y9B\u0011\u0011LK\u0007\u00025*\u00111lP\u0001\u0007yI|w\u000e\u001e \n\u0005uS\u0013A\u0002)sK\u0012,g-\u0003\u0002`A\n11\u000b\u001e:j]\u001eT!!\u0018\u0016\u0002\r\u0005\u0004\b/\u00133!\u00035awnY1m\u000b:$\u0007o\\5oiV\tA\r\u0005\u0002fQ6\taM\u0003\u0002hC\u0005\u0019!\u000f]2\n\u0005%4'A\u0004*qG\u0016sG\r]8j]R\u0014VMZ\u0001\u0012Y>\u001c\u0017\r\\#oIB|\u0017N\u001c;`I\u0015\fHC\u00017p!\tIS.\u0003\u0002oU\t!QK\\5u\u0011\u001d\u0001\u0018\"!AA\u0002\u0011\f1\u0001\u001f\u00132\u00039awnY1m\u000b:$\u0007o\\5oi\u0002\nQ\"^:fe\u000ec\u0017m]:QCRDW#\u0001;\u0011\u0007UTXP\u0004\u0002wq:\u0011\u0011l^\u0005\u0002W%\u0011\u0011PK\u0001\ba\u0006\u001c7.Y4f\u0013\tYHPA\u0002TKFT!!\u001f\u0016\u0011\u0007y\f9!D\u0001\u0000\u0015\u0011\t\t!a\u0001\u0002\u00079,GO\u0003\u0002\u0002\u0006\u0005!!.\u0019<b\u0013\r\tIa \u0002\u0004+Jc\u0015AD;tKJ\u001cE.Y:t!\u0006$\b\u000eI\u0001\fY&\u001cH/\u001a8fe\n+8/\u0006\u0002\u0002\u0012A\u0019q&a\u0005\n\u0007\u0005UqDA\bMSZ,G*[:uK:,'OQ;t\u00031a\u0017n\u001d;f]\u0016\u0014()^:!\u0003=a\u0017-\u001e8dQ\u0016\u0014()Y2lK:$WCAA\u000f!\u0011\ty\"!\n\u000e\u0005\u0005\u0005\"bAA\u0012C\u0005AA.Y;oG\",'/\u0003\u0003\u0002(\u0005\u0005\"a\u0004'bk:\u001c\u0007.\u001a:CC\u000e\\WM\u001c3\u0002!1\fWO\\2iKJ\u0014\u0015mY6f]\u0012\u0004\u0013\u0001E4fiV\u001bXM]\"mCN\u001c\b/\u0019;i)\r!\u0018q\u0006\u0005\u0006}E\u0001\r\u0001Q\u0001\u0006gR\f'\u000f\u001e\u000b\u0002Y\u0006!1\u000f^8q\u00031\u0011XM^5wK>3g-\u001a:t\u0003I!WMZ1vYR\u0004\u0016M]1mY\u0016d\u0017n]7\u0015\u0003%\u000b\u0001b[5mYR\u000b7o\u001b\u000b\nY\u0006\u0005\u00131JA(\u00033Bq!a\u0011\u0017\u0001\u0004\t)%\u0001\u0004uCN\\\u0017\n\u001a\t\u0004S\u0005\u001d\u0013bAA%U\t!Aj\u001c8h\u0011\u0019\tiE\u0006a\u0001-\u0006QQ\r_3dkR|'/\u00133\t\u000f\u0005Ec\u00031\u0001\u0002T\u0005y\u0011N\u001c;feJ,\b\u000f\u001e+ie\u0016\fG\rE\u0002*\u0003+J1!a\u0016+\u0005\u001d\u0011un\u001c7fC:Da!a\u0017\u0017\u0001\u00041\u0016A\u0002:fCN|g.\u0001\u0007ti\u0006$Xo]+qI\u0006$X\rF\u0004m\u0003C\n\u0019'!!\t\u000f\u0005\rs\u00031\u0001\u0002F!9\u0011QM\fA\u0002\u0005\u001d\u0014!B:uCR,\u0007\u0003BA5\u0003wrA!a\u001b\u0002x9!\u0011QNA;\u001d\u0011\ty'a\u001d\u000f\u0007e\u000b\t(C\u0001'\u0013\t!S%\u0003\u0002#G%\u0019\u0011\u0011P\u0011\u0002\u0013Q\u000b7o[*uCR,\u0017\u0002BA?\u0003\u007f\u0012\u0011\u0002V1tWN#\u0018\r^3\u000b\u0007\u0005e\u0014\u0005C\u0004\u0002\u0004^\u0001\r!!\"\u0002\u001dM,'/[1mSj,G\rR1uCB!\u0011qQAG\u001b\t\tII\u0003\u0003\u0002\f\u0006\r\u0011a\u00018j_&!\u0011qRAE\u0005)\u0011\u0015\u0010^3Ck\u001a4WM]\u0001\u000eCB\u0004H.[2bi&|g.\u00133\u0015\u0003Y\u000bQ#\\1y\u001dVl7i\u001c8dkJ\u0014XM\u001c;UCN\\7\u000fF\u0002J\u00033Cq!a'\u001a\u0001\u0004\ti*\u0001\u0002saB!\u0011qTAS\u001b\t\t\tKC\u0002\u0002$\u0006\n\u0001B]3t_V\u00148-Z\u0005\u0005\u0003O\u000b\tKA\bSKN|WO]2f!J|g-\u001b7f)\ra\u00171\u0016\u0005\b\u0003[S\u0002\u0019AAX\u0003)1\u0017N\\1m'R\fG/\u001a\t\u0005\u0003c\u000b9L\u0004\u0003\u0002 \u0005M\u0016\u0002BA[\u0003C\tab\u00159be.\f\u0005\u000f\u001d%b]\u0012dW-\u0003\u0003\u0002:\u0006m&!B*uCR,'\u0002BA[\u0003C\t\u0011cZ3u)\u0006\u001c8\u000e\u00165sK\u0006$G)^7q)\u0019\t\t-a7\u0002^B)\u0011&a1\u0002H&\u0019\u0011Q\u0019\u0016\u0003\r=\u0003H/[8o!\u0011\tI-a6\u000e\u0005\u0005-'\u0002BAg\u0003\u001f\f!A^\u0019\u000b\t\u0005E\u00171[\u0001\u0004CBL'bAAkC\u000511\u000f^1ukNLA!!7\u0002L\n\u0001B\u000b\u001b:fC\u0012\u001cF/Y2l)J\f7-\u001a\u0005\b\u0003\u0007Z\u0002\u0019AA#\u0011\u0019\tie\u0007a\u0001-\u0002"
)
public class LocalSchedulerBackend implements SchedulerBackend, ExecutorBackend, Logging {
   public final SparkConf org$apache$spark$scheduler$local$LocalSchedulerBackend$$conf;
   private final TaskSchedulerImpl scheduler;
   private final int totalCores;
   private final String appId;
   private RpcEndpointRef localEndpoint;
   private final Seq userClassPath;
   private final LiveListenerBus listenerBus;
   private final LauncherBackend launcherBackend;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private String org$apache$spark$scheduler$SchedulerBackend$$appId;

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

   public void stop(final int exitCode) {
      SchedulerBackend.stop$(this, exitCode);
   }

   public void updateExecutorsLogLevel(final String logLevel) {
      SchedulerBackend.updateExecutorsLogLevel$(this, logLevel);
   }

   public boolean isReady() {
      return SchedulerBackend.isReady$(this);
   }

   public Option applicationAttemptId() {
      return SchedulerBackend.applicationAttemptId$(this);
   }

   public Option getDriverLogUrls() {
      return SchedulerBackend.getDriverLogUrls$(this);
   }

   public Option getDriverAttributes() {
      return SchedulerBackend.getDriverAttributes$(this);
   }

   public Seq getShufflePushMergerLocations(final int numPartitions, final int resourceProfileId) {
      return SchedulerBackend.getShufflePushMergerLocations$(this, numPartitions, resourceProfileId);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public String org$apache$spark$scheduler$SchedulerBackend$$appId() {
      return this.org$apache$spark$scheduler$SchedulerBackend$$appId;
   }

   public final void org$apache$spark$scheduler$SchedulerBackend$_setter_$org$apache$spark$scheduler$SchedulerBackend$$appId_$eq(final String x$1) {
      this.org$apache$spark$scheduler$SchedulerBackend$$appId = x$1;
   }

   public int totalCores() {
      return this.totalCores;
   }

   private String appId() {
      return this.appId;
   }

   private RpcEndpointRef localEndpoint() {
      return this.localEndpoint;
   }

   private void localEndpoint_$eq(final RpcEndpointRef x$1) {
      this.localEndpoint = x$1;
   }

   private Seq userClassPath() {
      return this.userClassPath;
   }

   private LiveListenerBus listenerBus() {
      return this.listenerBus;
   }

   private LauncherBackend launcherBackend() {
      return this.launcherBackend;
   }

   public Seq getUserClasspath(final SparkConf conf) {
      Option userClassPathStr = (Option)conf.get((ConfigEntry)package$.MODULE$.EXECUTOR_CLASS_PATH());
      return (Seq)((IterableOps).MODULE$.option2Iterable(userClassPathStr.map((x$1) -> x$1.split(File.pathSeparator))).toSeq().flatten((xs) -> scala.Predef..MODULE$.wrapRefArray((Object[])xs))).map((x$2) -> (new File(x$2)).toURI().toURL());
   }

   public void start() {
      RpcEnv rpcEnv = SparkEnv$.MODULE$.get().rpcEnv();
      LocalEndpoint executorEndpoint = new LocalEndpoint(rpcEnv, this.userClassPath(), this.scheduler, this, this.totalCores());
      this.localEndpoint_$eq(rpcEnv.setupEndpoint("LocalSchedulerBackendEndpoint", executorEndpoint));
      this.listenerBus().post(new SparkListenerExecutorAdded(System.currentTimeMillis(), executorEndpoint.localExecutorId(), new ExecutorInfo(executorEndpoint.localExecutorHostname(), this.totalCores(), scala.Predef..MODULE$.Map().empty(), scala.Predef..MODULE$.Map().empty())));
      this.launcherBackend().setAppId(this.appId());
      this.launcherBackend().setState(State.RUNNING);
      this.reviveOffers();
   }

   public void stop() {
      this.org$apache$spark$scheduler$local$LocalSchedulerBackend$$stop(State.FINISHED);
   }

   public void reviveOffers() {
      this.localEndpoint().send(ReviveOffers$.MODULE$);
   }

   public int defaultParallelism() {
      return this.scheduler.conf().getInt(package$.MODULE$.DEFAULT_PARALLELISM().key(), this.totalCores());
   }

   public void killTask(final long taskId, final String executorId, final boolean interruptThread, final String reason) {
      this.localEndpoint().send(new KillTask(taskId, interruptThread, reason));
   }

   public void statusUpdate(final long taskId, final Enumeration.Value state, final ByteBuffer serializedData) {
      this.localEndpoint().send(new StatusUpdate(taskId, state, serializedData));
   }

   public String applicationId() {
      return this.appId();
   }

   public int maxNumConcurrentTasks(final ResourceProfile rp) {
      int cpusPerTask = ResourceProfile$.MODULE$.getTaskCpusOrDefaultForProfile(rp, this.org$apache$spark$scheduler$local$LocalSchedulerBackend$$conf);
      return this.totalCores() / cpusPerTask;
   }

   public void org$apache$spark$scheduler$local$LocalSchedulerBackend$$stop(final SparkAppHandle.State finalState) {
      this.localEndpoint().ask(StopExecutor$.MODULE$, scala.reflect.ClassTag..MODULE$.Nothing());

      try {
         this.launcherBackend().setState(finalState);
      } finally {
         this.launcherBackend().close();
      }

   }

   public Option getTaskThreadDump(final long taskId, final String executorId) {
      return (Option)this.localEndpoint().askSync(new CoarseGrainedClusterMessages.TaskThreadDump(taskId), scala.reflect.ClassTag..MODULE$.apply(Option.class));
   }

   public LocalSchedulerBackend(final SparkConf conf, final TaskSchedulerImpl scheduler, final int totalCores) {
      this.org$apache$spark$scheduler$local$LocalSchedulerBackend$$conf = conf;
      this.scheduler = scheduler;
      this.totalCores = totalCores;
      SchedulerBackend.$init$(this);
      Logging.$init$(this);
      this.appId = conf.get("spark.test.appId", "local-" + System.currentTimeMillis());
      this.localEndpoint = null;
      this.userClassPath = this.getUserClasspath(conf);
      this.listenerBus = scheduler.sc().listenerBus();
      this.launcherBackend = new LauncherBackend() {
         // $FF: synthetic field
         private final LocalSchedulerBackend $outer;

         public SparkConf conf() {
            return this.$outer.org$apache$spark$scheduler$local$LocalSchedulerBackend$$conf;
         }

         public void onStopRequest() {
            this.$outer.org$apache$spark$scheduler$local$LocalSchedulerBackend$$stop(State.KILLED);
         }

         public {
            if (LocalSchedulerBackend.this == null) {
               throw null;
            } else {
               this.$outer = LocalSchedulerBackend.this;
            }
         }
      };
      this.launcherBackend().connect();
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
