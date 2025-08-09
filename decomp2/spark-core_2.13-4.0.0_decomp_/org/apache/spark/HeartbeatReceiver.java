package org.apache.spark;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Network$;
import org.apache.spark.rpc.IsolatedThreadSafeRpcEndpoint;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.scheduler.ExecutorProcessLost;
import org.apache.spark.scheduler.ExecutorProcessLost$;
import org.apache.spark.scheduler.SchedulerBackend;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.scheduler.SparkListenerExecutorAdded;
import org.apache.spark.scheduler.SparkListenerExecutorRemoved;
import org.apache.spark.scheduler.TaskScheduler;
import org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages;
import org.apache.spark.scheduler.cluster.CoarseGrainedSchedulerBackend;
import org.apache.spark.scheduler.local.LocalSchedulerBackend;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.StringContext;
import scala.Tuple2;
import scala.Option.;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rh!\u0002\u0014(\u0001\u001dj\u0003\u0002\u0003!\u0001\u0005\u0003\u0005\u000b\u0011\u0002\"\t\u0011\u0019\u0003!\u0011!Q\u0001\n\u001dCQ!\u0014\u0001\u0005\u00029CQ!\u0014\u0001\u0005\u0002ICq\u0001\u0016\u0001C\u0002\u0013\u0005S\u000b\u0003\u0004Z\u0001\u0001\u0006IA\u0016\u0005\tc\u0001\u0001\r\u0011\"\u0001(5\"Aa\f\u0001a\u0001\n\u00039s\f\u0003\u0004i\u0001\u0001\u0006Ka\u0017\u0005\bS\u0002\u0011\r\u0011\"\u0003k\u0011\u001d\t\u0019\u0001\u0001Q\u0001\n-D\u0011\"!\u0002\u0001\u0005\u0004%I!a\u0002\t\u000f\u0005%\u0001\u0001)A\u0005}\"I\u00111\u0002\u0001C\u0002\u0013%\u0011q\u0001\u0005\b\u0003\u001b\u0001\u0001\u0015!\u0003\u007f\u0011%\ty\u0001\u0001b\u0001\n\u0013\t9\u0001C\u0004\u0002\u0012\u0001\u0001\u000b\u0011\u0002@\t\u0013\u0005M\u0001\u00011A\u0005\n\u0005U\u0001\"CA\"\u0001\u0001\u0007I\u0011BA#\u0011!\t\u0019\u0004\u0001Q!\n\u0005]\u0001\"CA)\u0001\t\u0007I\u0011BA*\u0011!\tY\u0006\u0001Q\u0001\n\u0005U\u0003\"CA/\u0001\t\u0007I\u0011BA0\u0011!\t9\u0007\u0001Q\u0001\n\u0005\u0005\u0004bBA5\u0001\u0011\u0005\u00131\u000e\u0005\b\u0003[\u0002A\u0011IA8\u0011\u001d\t\t\t\u0001C\u0001\u0003\u0007Cq!a(\u0001\t\u0003\n\t\u000bC\u0004\u0002.\u0002!\t!a,\t\u000f\u0005M\u0006\u0001\"\u0011\u00026\"9\u0011\u0011\u0019\u0001\u0005\n\u0005-\u0004bBAb\u0001\u0011\u0005\u00131N\u0004\t\u0003\u000b<\u0003\u0012A\u0014\u0002H\u001a9ae\nE\u0001O\u0005%\u0007BB'#\t\u0003\t\t\u000eC\u0005\u0002T\n\u0012\r\u0011\"\u0001\u0002V\"A\u0011\u0011\u001d\u0012!\u0002\u0013\t9NA\tIK\u0006\u0014HOY3biJ+7-Z5wKJT!\u0001K\u0015\u0002\u000bM\u0004\u0018M]6\u000b\u0005)Z\u0013AB1qC\u000eDWMC\u0001-\u0003\ry'oZ\n\u0005\u00019\"$\b\u0005\u00020e5\t\u0001G\u0003\u00022O\u0005I1o\u00195fIVdWM]\u0005\u0003gA\u0012Qb\u00159be.d\u0015n\u001d;f]\u0016\u0014\bCA\u001b9\u001b\u00051$BA\u001c(\u0003\r\u0011\boY\u0005\u0003sY\u0012Q$S:pY\u0006$X\r\u001a+ie\u0016\fGmU1gKJ\u00038-\u00128ea>Lg\u000e\u001e\t\u0003wyj\u0011\u0001\u0010\u0006\u0003{\u001d\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003\u007fq\u0012q\u0001T8hO&tw-\u0001\u0002tG\u000e\u0001\u0001CA\"E\u001b\u00059\u0013BA#(\u00051\u0019\u0006/\u0019:l\u0007>tG/\u001a=u\u0003\u0015\u0019Gn\\2l!\tA5*D\u0001J\u0015\tQu%\u0001\u0003vi&d\u0017B\u0001'J\u0005\u0015\u0019En\\2l\u0003\u0019a\u0014N\\5u}Q\u0019q\nU)\u0011\u0005\r\u0003\u0001\"\u0002!\u0004\u0001\u0004\u0011\u0005\"\u0002$\u0004\u0001\u00049ECA(T\u0011\u0015\u0001E\u00011\u0001C\u0003\u0019\u0011\boY#omV\ta\u000b\u0005\u00026/&\u0011\u0001L\u000e\u0002\u0007%B\u001cWI\u001c<\u0002\u000fI\u00048-\u00128wAU\t1\f\u0005\u000209&\u0011Q\f\r\u0002\u000e)\u0006\u001c8nU2iK\u0012,H.\u001a:\u0002\u001bM\u001c\u0007.\u001a3vY\u0016\u0014x\fJ3r)\t\u0001g\r\u0005\u0002bI6\t!MC\u0001d\u0003\u0015\u00198-\u00197b\u0013\t)'M\u0001\u0003V]&$\bbB4\t\u0003\u0003\u0005\raW\u0001\u0004q\u0012\n\u0014AC:dQ\u0016$W\u000f\\3sA\u0005\u0001R\r_3dkR|'\u000fT1tiN+WM\\\u000b\u0002WB!A.]:\u007f\u001b\u0005i'B\u00018p\u0003\u001diW\u000f^1cY\u0016T!\u0001\u001d2\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002s[\n9\u0001*Y:i\u001b\u0006\u0004\bC\u0001;|\u001d\t)\u0018\u0010\u0005\u0002wE6\tqO\u0003\u0002y\u0003\u00061AH]8pizJ!A\u001f2\u0002\rA\u0013X\rZ3g\u0013\taXP\u0001\u0004TiJLgn\u001a\u0006\u0003u\n\u0004\"!Y@\n\u0007\u0005\u0005!M\u0001\u0003M_:<\u0017!E3yK\u000e,Ho\u001c:MCN$8+Z3oA\u0005\tR\r_3dkR|'\u000fV5nK>,H/T:\u0016\u0003y\f!#\u001a=fGV$xN\u001d+j[\u0016|W\u000f^'tA\u000512\r[3dWRKW.Z8vi&sG/\u001a:wC2l5/A\fdQ\u0016\u001c7\u000eV5nK>,H/\u00138uKJ4\u0018\r\\'tA\u0005YR\r_3dkR|'\u000fS3beR\u0014W-\u0019;J]R,'O^1m\u001bN\fA$\u001a=fGV$xN\u001d%fCJ$(-Z1u\u0013:$XM\u001d<bY6\u001b\b%A\nuS6,w.\u001e;DQ\u0016\u001c7.\u001b8h)\u0006\u001c8.\u0006\u0002\u0002\u0018A\"\u0011\u0011DA\u0018!\u0019\tY\"a\n\u0002,5\u0011\u0011Q\u0004\u0006\u0005\u0003?\t\t#\u0001\u0006d_:\u001cWO\u001d:f]RT1ASA\u0012\u0015\t\t)#\u0001\u0003kCZ\f\u0017\u0002BA\u0015\u0003;\u0011qbU2iK\u0012,H.\u001a3GkR,(/\u001a\t\u0005\u0003[\ty\u0003\u0004\u0001\u0005\u0017\u0005EB#!A\u0001\u0002\u000b\u0005\u0011Q\u0007\u0002\u0004?\u0012\u001a\u0014\u0001\u0006;j[\u0016|W\u000f^\"iK\u000e\\\u0017N\\4UCN\\\u0007%\u0005\u0003\u00028\u0005u\u0002cA1\u0002:%\u0019\u00111\b2\u0003\u000f9{G\u000f[5oOB\u0019\u0011-a\u0010\n\u0007\u0005\u0005#MA\u0002B]f\fq\u0003^5nK>,Ho\u00115fG.Lgn\u001a+bg.|F%Z9\u0015\u0007\u0001\f9\u0005\u0003\u0005h'\u0005\u0005\t\u0019AA%a\u0011\tY%a\u0014\u0011\r\u0005m\u0011qEA'!\u0011\ti#a\u0014\u0005\u0019\u0005E\u0012qIA\u0001\u0002\u0003\u0015\t!!\u000e\u0002\u001f\u00154XM\u001c;M_>\u0004H\u000b\u001b:fC\u0012,\"!!\u0016\u0011\t\u0005m\u0011qK\u0005\u0005\u00033\niB\u0001\rTG\",G-\u001e7fI\u0016CXmY;u_J\u001cVM\u001d<jG\u0016\f\u0001#\u001a<f]Rdun\u001c9UQJ,\u0017\r\u001a\u0011\u0002%-LG\u000e\\#yK\u000e,Ho\u001c:UQJ,\u0017\rZ\u000b\u0003\u0003C\u0002B!a\u0007\u0002d%!\u0011QMA\u000f\u0005I!\u0006N]3bIB{w\u000e\\#yK\u000e,Ho\u001c:\u0002'-LG\u000e\\#yK\u000e,Ho\u001c:UQJ,\u0017\r\u001a\u0011\u0002\u000f=t7\u000b^1siR\t\u0001-A\bsK\u000e,\u0017N^3B]\u0012\u0014V\r\u001d7z)\u0011\t\t(a\u001e\u0011\r\u0005\f\u0019(!\u0010a\u0013\r\t)H\u0019\u0002\u0010!\u0006\u0014H/[1m\rVt7\r^5p]\"9\u0011\u0011\u0010\u000eA\u0002\u0005m\u0014aB2p]R,\u0007\u0010\u001e\t\u0004k\u0005u\u0014bAA@m\tq!\u000b]2DC2d7i\u001c8uKb$\u0018aC1eI\u0016CXmY;u_J$B!!\"\u0002\u001cB)\u0011-a\"\u0002\f&\u0019\u0011\u0011\u00122\u0003\r=\u0003H/[8o!\u0019\ti)!%\u0002\u00166\u0011\u0011q\u0012\u0006\u0004\u0003?\u0011\u0017\u0002BAJ\u0003\u001f\u0013aAR;ukJ,\u0007cA1\u0002\u0018&\u0019\u0011\u0011\u00142\u0003\u000f\t{w\u000e\\3b]\"1\u0011QT\u000eA\u0002M\f!\"\u001a=fGV$xN]%e\u0003=yg.\u0012=fGV$xN]!eI\u0016$Gc\u00011\u0002$\"9\u0011Q\u0015\u000fA\u0002\u0005\u001d\u0016!D3yK\u000e,Ho\u001c:BI\u0012,G\rE\u00020\u0003SK1!a+1\u0005i\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u0016CXmY;u_J\fE\rZ3e\u00039\u0011X-\\8wK\u0016CXmY;u_J$B!!\"\u00022\"1\u0011QT\u000fA\u0002M\f\u0011c\u001c8Fq\u0016\u001cW\u000f^8s%\u0016lwN^3e)\r\u0001\u0017q\u0017\u0005\b\u0003ss\u0002\u0019AA^\u0003=)\u00070Z2vi>\u0014(+Z7pm\u0016$\u0007cA\u0018\u0002>&\u0019\u0011q\u0018\u0019\u00039M\u0003\u0018M]6MSN$XM\\3s\u000bb,7-\u001e;peJ+Wn\u001c<fI\u0006yQ\r\u001f9je\u0016$U-\u00193I_N$8/\u0001\u0004p]N#x\u000e]\u0001\u0012\u0011\u0016\f'\u000f\u001e2fCR\u0014VmY3jm\u0016\u0014\bCA\"#'\r\u0011\u00131\u001a\t\u0004C\u00065\u0017bAAhE\n1\u0011I\\=SK\u001a$\"!a2\u0002\u001b\u0015sE\tU(J\u001dR{f*Q'F+\t\t9\u000e\u0005\u0003\u0002Z\u0006}WBAAn\u0015\u0011\ti.a\t\u0002\t1\fgnZ\u0005\u0004y\u0006m\u0017AD#O\tB{\u0015J\u0014+`\u001d\u0006kU\t\t"
)
public class HeartbeatReceiver extends SparkListener implements IsolatedThreadSafeRpcEndpoint, Logging {
   public final SparkContext org$apache$spark$HeartbeatReceiver$$sc;
   public final Clock org$apache$spark$HeartbeatReceiver$$clock;
   private final RpcEnv rpcEnv;
   private TaskScheduler scheduler;
   private final HashMap org$apache$spark$HeartbeatReceiver$$executorLastSeen;
   private final long executorTimeoutMs;
   private final long checkTimeoutIntervalMs;
   private final long executorHeartbeatIntervalMs;
   private ScheduledFuture timeoutCheckingTask;
   private final ScheduledExecutorService org$apache$spark$HeartbeatReceiver$$eventLoopThread;
   private final ThreadPoolExecutor killExecutorThread;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static String ENDPOINT_NAME() {
      return HeartbeatReceiver$.MODULE$.ENDPOINT_NAME();
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

   public final int threadCount() {
      return IsolatedThreadSafeRpcEndpoint.threadCount$(this);
   }

   public final RpcEndpointRef self() {
      return RpcEndpoint.self$(this);
   }

   public PartialFunction receive() {
      return RpcEndpoint.receive$(this);
   }

   public void onError(final Throwable cause) {
      RpcEndpoint.onError$(this, cause);
   }

   public void onConnected(final RpcAddress remoteAddress) {
      RpcEndpoint.onConnected$(this, remoteAddress);
   }

   public void onDisconnected(final RpcAddress remoteAddress) {
      RpcEndpoint.onDisconnected$(this, remoteAddress);
   }

   public void onNetworkError(final Throwable cause, final RpcAddress remoteAddress) {
      RpcEndpoint.onNetworkError$(this, cause, remoteAddress);
   }

   public final void stop() {
      RpcEndpoint.stop$(this);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public RpcEnv rpcEnv() {
      return this.rpcEnv;
   }

   public TaskScheduler scheduler() {
      return this.scheduler;
   }

   public void scheduler_$eq(final TaskScheduler x$1) {
      this.scheduler = x$1;
   }

   public HashMap org$apache$spark$HeartbeatReceiver$$executorLastSeen() {
      return this.org$apache$spark$HeartbeatReceiver$$executorLastSeen;
   }

   private long executorTimeoutMs() {
      return this.executorTimeoutMs;
   }

   private long checkTimeoutIntervalMs() {
      return this.checkTimeoutIntervalMs;
   }

   private long executorHeartbeatIntervalMs() {
      return this.executorHeartbeatIntervalMs;
   }

   private ScheduledFuture timeoutCheckingTask() {
      return this.timeoutCheckingTask;
   }

   private void timeoutCheckingTask_$eq(final ScheduledFuture x$1) {
      this.timeoutCheckingTask = x$1;
   }

   public ScheduledExecutorService org$apache$spark$HeartbeatReceiver$$eventLoopThread() {
      return this.org$apache$spark$HeartbeatReceiver$$eventLoopThread;
   }

   private ThreadPoolExecutor killExecutorThread() {
      return this.killExecutorThread;
   }

   public void onStart() {
      this.timeoutCheckingTask_$eq(this.org$apache$spark$HeartbeatReceiver$$eventLoopThread().scheduleAtFixedRate(() -> Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> .MODULE$.apply(this.self()).foreach((x$1) -> x$1.ask(ExpireDeadHosts$.MODULE$, scala.reflect.ClassTag..MODULE$.Boolean()))), 0L, this.checkTimeoutIntervalMs(), TimeUnit.MILLISECONDS));
   }

   public PartialFunction receiveAndReply(final RpcCallContext context) {
      return new Serializable(context) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final HeartbeatReceiver $outer;
         public final RpcCallContext context$1;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (x1 instanceof ExecutorRegistered var5) {
               String executorId = var5.executorId();
               this.$outer.org$apache$spark$HeartbeatReceiver$$executorLastSeen().update(executorId, BoxesRunTime.boxToLong(this.$outer.org$apache$spark$HeartbeatReceiver$$clock.getTimeMillis()));
               this.context$1.reply(BoxesRunTime.boxToBoolean(true));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof ExecutorRemoved var7) {
               String executorId = var7.executorId();
               this.$outer.org$apache$spark$HeartbeatReceiver$$executorLastSeen().remove(executorId);
               this.context$1.reply(BoxesRunTime.boxToBoolean(true));
               return BoxedUnit.UNIT;
            } else if (TaskSchedulerIsSet$.MODULE$.equals(x1)) {
               this.$outer.scheduler_$eq(this.$outer.org$apache$spark$HeartbeatReceiver$$sc.taskScheduler());
               this.context$1.reply(BoxesRunTime.boxToBoolean(true));
               return BoxedUnit.UNIT;
            } else if (ExpireDeadHosts$.MODULE$.equals(x1)) {
               this.$outer.org$apache$spark$HeartbeatReceiver$$expireDeadHosts();
               this.context$1.reply(BoxesRunTime.boxToBoolean(true));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof Heartbeat) {
               Heartbeat var9 = (Heartbeat)x1;
               String executorId = var9.executorId();
               Tuple2[] accumUpdates = var9.accumUpdates();
               BlockManagerId blockManagerId = var9.blockManagerId();
               scala.collection.mutable.Map executorUpdates = var9.executorUpdates();
               BooleanRef reregisterBlockManager = BooleanRef.create(!this.$outer.org$apache$spark$HeartbeatReceiver$$sc.isStopped());
               if (this.$outer.scheduler() != null) {
                  if (this.$outer.org$apache$spark$HeartbeatReceiver$$executorLastSeen().contains(executorId)) {
                     this.$outer.org$apache$spark$HeartbeatReceiver$$executorLastSeen().update(executorId, BoxesRunTime.boxToLong(this.$outer.org$apache$spark$HeartbeatReceiver$$clock.getTimeMillis()));
                     this.$outer.org$apache$spark$HeartbeatReceiver$$eventLoopThread().submit(new Runnable(executorId, accumUpdates, blockManagerId, executorUpdates, reregisterBlockManager) {
                        // $FF: synthetic field
                        private final <undefinedtype> $outer;
                        private final String executorId$1;
                        private final Tuple2[] accumUpdates$1;
                        private final BlockManagerId blockManagerId$1;
                        private final scala.collection.mutable.Map executorUpdates$1;
                        private final BooleanRef reregisterBlockManager$1;

                        public void run() {
                           Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> {
                              boolean unknownExecutor = !this.$outer.org$apache$spark$HeartbeatReceiver$$anonfun$$$outer().scheduler().executorHeartbeatReceived(this.executorId$1, this.accumUpdates$1, this.blockManagerId$1, this.executorUpdates$1);
                              this.reregisterBlockManager$1.elem &= unknownExecutor;
                              HeartbeatResponse response = new HeartbeatResponse(this.reregisterBlockManager$1.elem);
                              this.$outer.context$1.reply(response);
                           });
                        }

                        public {
                           if (<VAR_NAMELESS_ENCLOSURE> == null) {
                              throw null;
                           } else {
                              this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                              this.executorId$1 = executorId$1;
                              this.accumUpdates$1 = accumUpdates$1;
                              this.blockManagerId$1 = blockManagerId$1;
                              this.executorUpdates$1 = executorUpdates$1;
                              this.reregisterBlockManager$1 = reregisterBlockManager$1;
                           }
                        }

                        // $FF: synthetic method
                        private static Object $deserializeLambda$(SerializedLambda var0) {
                           return var0.lambdaDeserialize<invokedynamic>(var0);
                        }
                     });
                     return BoxedUnit.UNIT;
                  } else {
                     this.$outer.logDebug((Function0)(() -> "Received heartbeat from unknown executor " + executorId));
                     this.context$1.reply(new HeartbeatResponse(reregisterBlockManager.elem));
                     return BoxedUnit.UNIT;
                  }
               } else {
                  this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Dropping ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HEARTBEAT..MODULE$, var9)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"because TaskScheduler is not ready yet"})))).log(scala.collection.immutable.Nil..MODULE$))));
                  this.context$1.reply(new HeartbeatResponse(reregisterBlockManager.elem));
                  return BoxedUnit.UNIT;
               }
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            if (x1 instanceof ExecutorRegistered) {
               return true;
            } else if (x1 instanceof ExecutorRemoved) {
               return true;
            } else if (TaskSchedulerIsSet$.MODULE$.equals(x1)) {
               return true;
            } else if (ExpireDeadHosts$.MODULE$.equals(x1)) {
               return true;
            } else {
               return x1 instanceof Heartbeat;
            }
         }

         // $FF: synthetic method
         public HeartbeatReceiver org$apache$spark$HeartbeatReceiver$$anonfun$$$outer() {
            return this.$outer;
         }

         public {
            if (HeartbeatReceiver.this == null) {
               throw null;
            } else {
               this.$outer = HeartbeatReceiver.this;
               this.context$1 = context$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public Option addExecutor(final String executorId) {
      return .MODULE$.apply(this.self()).map((x$2) -> x$2.ask(new ExecutorRegistered(executorId), scala.reflect.ClassTag..MODULE$.Boolean()));
   }

   public void onExecutorAdded(final SparkListenerExecutorAdded executorAdded) {
      this.addExecutor(executorAdded.executorId());
   }

   public Option removeExecutor(final String executorId) {
      return .MODULE$.apply(this.self()).map((x$3) -> x$3.ask(new ExecutorRemoved(executorId), scala.reflect.ClassTag..MODULE$.Boolean()));
   }

   public void onExecutorRemoved(final SparkListenerExecutorRemoved executorRemoved) {
      this.removeExecutor(executorRemoved.executorId());
   }

   public void org$apache$spark$HeartbeatReceiver$$expireDeadHosts() {
      this.logTrace((Function0)(() -> "Checking for hosts with no recent heartbeats in HeartbeatReceiver."));
      long now = this.org$apache$spark$HeartbeatReceiver$$clock.getTimeMillis();
      this.org$apache$spark$HeartbeatReceiver$$executorLastSeen().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$expireDeadHosts$2(check$ifrefutable$1))).foreach((x$4) -> {
         if (x$4 != null) {
            String executorId = (String)x$4._1();
            long lastSeenMs = x$4._2$mcJ$sp();
            if (now - lastSeenMs > this.executorTimeoutMs()) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removing executor ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with no recent heartbeats: "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " ms exceeds timeout "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME_UNITS..MODULE$, BoxesRunTime.boxToLong(now - lastSeenMs))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " ms"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_TIMEOUT..MODULE$, BoxesRunTime.boxToLong(this.executorTimeoutMs()))}))))));
               this.killExecutorThread().submit(new Runnable(executorId, now, lastSeenMs) {
                  // $FF: synthetic field
                  private final HeartbeatReceiver $outer;
                  private final String executorId$4;
                  private final long now$1;
                  private final long lastSeenMs$1;

                  public void run() {
                     Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> {
                        this.$outer.org$apache$spark$HeartbeatReceiver$$sc.killAndReplaceExecutor(this.executorId$4);
                        SchedulerBackend var2 = this.$outer.org$apache$spark$HeartbeatReceiver$$sc.schedulerBackend();
                        if (var2 instanceof CoarseGrainedSchedulerBackend var3) {
                           var3.driverEndpoint().send(new CoarseGrainedClusterMessages.RemoveExecutor(this.executorId$4, new ExecutorProcessLost("Executor heartbeat timed out after " + (this.now$1 - this.lastSeenMs$1) + " ms", ExecutorProcessLost$.MODULE$.apply$default$2(), ExecutorProcessLost$.MODULE$.apply$default$3())));
                           BoxedUnit var4 = BoxedUnit.UNIT;
                        } else if (var2 instanceof LocalSchedulerBackend) {
                           BoxedUnit var10000 = BoxedUnit.UNIT;
                        } else {
                           throw new UnsupportedOperationException("Unknown scheduler backend: " + var2.getClass());
                        }
                     });
                  }

                  public {
                     if (HeartbeatReceiver.this == null) {
                        throw null;
                     } else {
                        this.$outer = HeartbeatReceiver.this;
                        this.executorId$4 = executorId$4;
                        this.now$1 = now$1;
                        this.lastSeenMs$1 = lastSeenMs$1;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               });
               return this.org$apache$spark$HeartbeatReceiver$$executorLastSeen().remove(executorId);
            } else {
               return BoxedUnit.UNIT;
            }
         } else {
            throw new MatchError(x$4);
         }
      });
   }

   public void onStop() {
      if (this.timeoutCheckingTask() != null) {
         BoxesRunTime.boxToBoolean(this.timeoutCheckingTask().cancel(true));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.org$apache$spark$HeartbeatReceiver$$eventLoopThread().shutdownNow();
      this.killExecutorThread().shutdownNow();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$expireDeadHosts$2(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   public HeartbeatReceiver(final SparkContext sc, final Clock clock) {
      this.org$apache$spark$HeartbeatReceiver$$sc = sc;
      this.org$apache$spark$HeartbeatReceiver$$clock = clock;
      RpcEndpoint.$init$(this);
      IsolatedThreadSafeRpcEndpoint.$init$(this);
      Logging.$init$(this);
      sc.listenerBus().addToManagementQueue(this);
      this.rpcEnv = sc.env().rpcEnv();
      this.scheduler = null;
      this.org$apache$spark$HeartbeatReceiver$$executorLastSeen = new HashMap();
      this.executorTimeoutMs = BoxesRunTime.unboxToLong(((Option)sc.conf().get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.STORAGE_BLOCKMANAGER_HEARTBEAT_TIMEOUT())).getOrElse((JFunction0.mcJ.sp)() -> {
         Utils$ var10000 = Utils$.MODULE$;
         SparkConf var10001 = this.org$apache$spark$HeartbeatReceiver$$sc.conf();
         return var10000.timeStringAsMs(var10001.get(Network$.MODULE$.NETWORK_TIMEOUT()) + "s");
      }));
      this.checkTimeoutIntervalMs = BoxesRunTime.unboxToLong(sc.conf().get(Network$.MODULE$.NETWORK_TIMEOUT_INTERVAL()));
      this.executorHeartbeatIntervalMs = BoxesRunTime.unboxToLong(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_HEARTBEAT_INTERVAL()));
      if (((Option)sc.conf().get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.STORAGE_BLOCKMANAGER_HEARTBEAT_TIMEOUT())).isEmpty()) {
         scala.Predef..MODULE$.require(this.checkTimeoutIntervalMs() <= this.executorTimeoutMs(), () -> {
            String var10000 = Network$.MODULE$.NETWORK_TIMEOUT_INTERVAL().key();
            return var10000 + " should be less than or equal to " + Network$.MODULE$.NETWORK_TIMEOUT().key() + ".";
         });
      } else {
         scala.Predef..MODULE$.require(this.checkTimeoutIntervalMs() <= this.executorTimeoutMs(), () -> {
            String var10000 = Network$.MODULE$.NETWORK_TIMEOUT_INTERVAL().key();
            return var10000 + " should be less than or equal to " + org.apache.spark.internal.config.package$.MODULE$.STORAGE_BLOCKMANAGER_HEARTBEAT_TIMEOUT().key() + ".";
         });
      }

      scala.Predef..MODULE$.require(this.executorHeartbeatIntervalMs() <= this.executorTimeoutMs(), () -> {
         String var10000 = org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_HEARTBEAT_INTERVAL().key();
         return var10000 + " should be less than or equal to " + org.apache.spark.internal.config.package$.MODULE$.STORAGE_BLOCKMANAGER_HEARTBEAT_TIMEOUT().key();
      });
      this.timeoutCheckingTask = null;
      this.org$apache$spark$HeartbeatReceiver$$eventLoopThread = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("heartbeat-receiver-event-loop-thread");
      this.killExecutorThread = ThreadUtils$.MODULE$.newDaemonSingleThreadExecutor("kill-executor-thread");
   }

   public HeartbeatReceiver(final SparkContext sc) {
      this(sc, new SystemClock());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
