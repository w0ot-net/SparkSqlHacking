package org.apache.spark.scheduler.local;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.spark.SparkContext$;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskState$;
import org.apache.spark.executor.Executor;
import org.apache.spark.executor.Executor$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.ThreadSafeRpcEndpoint;
import org.apache.spark.scheduler.TaskDescription;
import org.apache.spark.scheduler.TaskSchedulerImpl;
import org.apache.spark.scheduler.WorkerOffer;
import org.apache.spark.scheduler.WorkerOffer$;
import org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]b!B\u000b\u0017\u0001i\u0001\u0003\u0002C\u001a\u0001\u0005\u000b\u0007I\u0011I\u001b\t\u0011e\u0002!\u0011!Q\u0001\nYB\u0001B\u000f\u0001\u0003\u0002\u0003\u0006Ia\u000f\u0005\t3\u0001\u0011\t\u0011)A\u0005\u001f\"A1\u000b\u0001B\u0001B\u0003%A\u000b\u0003\u0005Y\u0001\t\u0015\r\u0011\"\u0003Z\u0011!i\u0006A!A!\u0002\u0013Q\u0006\"\u00020\u0001\t\u0003y\u0006b\u00024\u0001\u0001\u0004%I!\u0017\u0005\bO\u0002\u0001\r\u0011\"\u0003i\u0011\u0019q\u0007\u0001)Q\u00055\"9q\u000e\u0001b\u0001\n\u0003\u0001\bBB<\u0001A\u0003%\u0011\u000fC\u0004y\u0001\t\u0007I\u0011A=\t\u000f\u0005\r\u0001\u0001)A\u0005u\"I\u0011Q\u0001\u0001C\u0002\u0013%\u0011q\u0001\u0005\t\u0003'\u0001\u0001\u0015!\u0003\u0002\n!9\u0011Q\u0003\u0001\u0005B\u0005]\u0001bBA\u0013\u0001\u0011\u0005\u0013q\u0005\u0005\b\u0003g\u0001A\u0011AA\u001b\u00055aunY1m\u000b:$\u0007o\\5oi*\u0011q\u0003G\u0001\u0006Y>\u001c\u0017\r\u001c\u0006\u00033i\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005ma\u0012!B:qCJ\\'BA\u000f\u001f\u0003\u0019\t\u0007/Y2iK*\tq$A\u0002pe\u001e\u001cB\u0001A\u0011([A\u0011!%J\u0007\u0002G)\tA%A\u0003tG\u0006d\u0017-\u0003\u0002'G\t1\u0011I\\=SK\u001a\u0004\"\u0001K\u0016\u000e\u0003%R!A\u000b\u000e\u0002\u0007I\u00048-\u0003\u0002-S\t)B\u000b\u001b:fC\u0012\u001c\u0016MZ3Sa\u000e,e\u000e\u001a9pS:$\bC\u0001\u00182\u001b\u0005y#B\u0001\u0019\u001b\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u001a0\u0005\u001daunZ4j]\u001e\faA\u001d9d\u000b:48\u0001A\u000b\u0002mA\u0011\u0001fN\u0005\u0003q%\u0012aA\u00159d\u000b:4\u0018a\u0002:qG\u0016sg\u000fI\u0001\u000ekN,'o\u00117bgN\u0004\u0016\r\u001e5\u0011\u0007q\"uI\u0004\u0002>\u0005:\u0011a(Q\u0007\u0002\u007f)\u0011\u0001\tN\u0001\u0007yI|w\u000e\u001e \n\u0003\u0011J!aQ\u0012\u0002\u000fA\f7m[1hK&\u0011QI\u0012\u0002\u0004'\u0016\f(BA\"$!\tAU*D\u0001J\u0015\tQ5*A\u0002oKRT\u0011\u0001T\u0001\u0005U\u00064\u0018-\u0003\u0002O\u0013\n\u0019QK\u0015'\u0011\u0005A\u000bV\"\u0001\r\n\u0005IC\"!\u0005+bg.\u001c6\r[3ek2,'/S7qY\u0006yQ\r_3dkR|'OQ1dW\u0016tG\r\u0005\u0002V-6\ta#\u0003\u0002X-\t)Bj\\2bYN\u001b\u0007.\u001a3vY\u0016\u0014()Y2lK:$\u0017A\u0003;pi\u0006d7i\u001c:fgV\t!\f\u0005\u0002#7&\u0011Al\t\u0002\u0004\u0013:$\u0018a\u0003;pi\u0006d7i\u001c:fg\u0002\na\u0001P5oSRtDC\u00021bE\u000e$W\r\u0005\u0002V\u0001!)1\u0007\u0003a\u0001m!)!\b\u0003a\u0001w!)\u0011\u0004\u0003a\u0001\u001f\")1\u000b\u0003a\u0001)\")\u0001\f\u0003a\u00015\u0006IaM]3f\u0007>\u0014Xm]\u0001\u000eMJ,WmQ8sKN|F%Z9\u0015\u0005%d\u0007C\u0001\u0012k\u0013\tY7E\u0001\u0003V]&$\bbB7\u000b\u0003\u0003\u0005\rAW\u0001\u0004q\u0012\n\u0014A\u00034sK\u0016\u001cuN]3tA\u0005yAn\\2bY\u0016CXmY;u_JLE-F\u0001r!\t\u0011X/D\u0001t\u0015\t!8*\u0001\u0003mC:<\u0017B\u0001<t\u0005\u0019\u0019FO]5oO\u0006\u0001Bn\\2bY\u0016CXmY;u_JLE\rI\u0001\u0016Y>\u001c\u0017\r\\#yK\u000e,Ho\u001c:I_N$h.Y7f+\u0005Q\bCA>\u0000\u001d\taX\u0010\u0005\u0002?G%\u0011apI\u0001\u0007!J,G-\u001a4\n\u0007Y\f\tA\u0003\u0002\u007fG\u00051Bn\\2bY\u0016CXmY;u_JDun\u001d;oC6,\u0007%\u0001\u0005fq\u0016\u001cW\u000f^8s+\t\tI\u0001\u0005\u0003\u0002\f\u0005=QBAA\u0007\u0015\r\t)AG\u0005\u0005\u0003#\tiA\u0001\u0005Fq\u0016\u001cW\u000f^8s\u0003%)\u00070Z2vi>\u0014\b%A\u0004sK\u000e,\u0017N^3\u0016\u0005\u0005e\u0001C\u0002\u0012\u0002\u001c\u0005}\u0011.C\u0002\u0002\u001e\r\u0012q\u0002U1si&\fGNR;oGRLwN\u001c\t\u0004E\u0005\u0005\u0012bAA\u0012G\t\u0019\u0011I\\=\u0002\u001fI,7-Z5wK\u0006sGMU3qYf$B!!\u0007\u0002*!9\u00111F\nA\u0002\u00055\u0012aB2p]R,\u0007\u0010\u001e\t\u0004Q\u0005=\u0012bAA\u0019S\tq!\u000b]2DC2d7i\u001c8uKb$\u0018\u0001\u0004:fm&4Xm\u00144gKJ\u001cH#A5"
)
public class LocalEndpoint implements ThreadSafeRpcEndpoint, Logging {
   private final RpcEnv rpcEnv;
   public final TaskSchedulerImpl org$apache$spark$scheduler$local$LocalEndpoint$$scheduler;
   private final LocalSchedulerBackend executorBackend;
   private final int totalCores;
   private int org$apache$spark$scheduler$local$LocalEndpoint$$freeCores;
   private final String localExecutorId;
   private final String localExecutorHostname;
   private final Executor org$apache$spark$scheduler$local$LocalEndpoint$$executor;
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

   public final RpcEndpointRef self() {
      return RpcEndpoint.self$(this);
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

   public void onStart() {
      RpcEndpoint.onStart$(this);
   }

   public void onStop() {
      RpcEndpoint.onStop$(this);
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

   private int totalCores() {
      return this.totalCores;
   }

   public int org$apache$spark$scheduler$local$LocalEndpoint$$freeCores() {
      return this.org$apache$spark$scheduler$local$LocalEndpoint$$freeCores;
   }

   public void org$apache$spark$scheduler$local$LocalEndpoint$$freeCores_$eq(final int x$1) {
      this.org$apache$spark$scheduler$local$LocalEndpoint$$freeCores = x$1;
   }

   public String localExecutorId() {
      return this.localExecutorId;
   }

   public String localExecutorHostname() {
      return this.localExecutorHostname;
   }

   public Executor org$apache$spark$scheduler$local$LocalEndpoint$$executor() {
      return this.org$apache$spark$scheduler$local$LocalEndpoint$$executor;
   }

   public PartialFunction receive() {
      return new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final LocalEndpoint $outer;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (ReviveOffers$.MODULE$.equals(x1)) {
               this.$outer.reviveOffers();
               return BoxedUnit.UNIT;
            } else if (x1 instanceof StatusUpdate) {
               StatusUpdate var5 = (StatusUpdate)x1;
               long taskId = var5.taskId();
               Enumeration.Value state = var5.state();
               ByteBuffer serializedData = var5.serializedData();
               this.$outer.org$apache$spark$scheduler$local$LocalEndpoint$$scheduler.statusUpdate(taskId, state, serializedData);
               if (TaskState$.MODULE$.isFinished(state)) {
                  this.$outer.org$apache$spark$scheduler$local$LocalEndpoint$$freeCores_$eq(this.$outer.org$apache$spark$scheduler$local$LocalEndpoint$$freeCores() + this.$outer.org$apache$spark$scheduler$local$LocalEndpoint$$scheduler.CPUS_PER_TASK());
                  this.$outer.reviveOffers();
                  return BoxedUnit.UNIT;
               } else {
                  return BoxedUnit.UNIT;
               }
            } else if (x1 instanceof KillTask) {
               KillTask var10 = (KillTask)x1;
               long taskId = var10.taskId();
               boolean interruptThread = var10.interruptThread();
               String reason = var10.reason();
               this.$outer.org$apache$spark$scheduler$local$LocalEndpoint$$executor().killTask(taskId, interruptThread, reason);
               return BoxedUnit.UNIT;
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            if (ReviveOffers$.MODULE$.equals(x1)) {
               return true;
            } else if (x1 instanceof StatusUpdate) {
               return true;
            } else {
               return x1 instanceof KillTask;
            }
         }

         public {
            if (LocalEndpoint.this == null) {
               throw null;
            } else {
               this.$outer = LocalEndpoint.this;
            }
         }
      };
   }

   public PartialFunction receiveAndReply(final RpcCallContext context) {
      return new Serializable(context) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final LocalEndpoint $outer;
         private final RpcCallContext context$1;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (StopExecutor$.MODULE$.equals(x1)) {
               this.$outer.org$apache$spark$scheduler$local$LocalEndpoint$$executor().stop();
               this.context$1.reply(BoxesRunTime.boxToBoolean(true));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof CoarseGrainedClusterMessages.TaskThreadDump) {
               CoarseGrainedClusterMessages.TaskThreadDump var5 = (CoarseGrainedClusterMessages.TaskThreadDump)x1;
               long taskId = var5.taskId();
               this.context$1.reply(this.$outer.org$apache$spark$scheduler$local$LocalEndpoint$$executor().getTaskThreadDump(taskId));
               return BoxedUnit.UNIT;
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            if (StopExecutor$.MODULE$.equals(x1)) {
               return true;
            } else {
               return x1 instanceof CoarseGrainedClusterMessages.TaskThreadDump;
            }
         }

         public {
            if (LocalEndpoint.this == null) {
               throw null;
            } else {
               this.$outer = LocalEndpoint.this;
               this.context$1 = context$1;
            }
         }
      };
   }

   public void reviveOffers() {
      IndexedSeq offers = (IndexedSeq).MODULE$.IndexedSeq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new WorkerOffer[]{new WorkerOffer(this.localExecutorId(), this.localExecutorHostname(), this.org$apache$spark$scheduler$local$LocalEndpoint$$freeCores(), new Some(this.rpcEnv().address().hostPort()), WorkerOffer$.MODULE$.$lessinit$greater$default$5(), WorkerOffer$.MODULE$.$lessinit$greater$default$6())}));
      ((IterableOnceOps)this.org$apache$spark$scheduler$local$LocalEndpoint$$scheduler.resourceOffers(offers, true).flatten(scala.Predef..MODULE$.$conforms())).foreach((task) -> {
         $anonfun$reviveOffers$1(this, task);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$reviveOffers$1(final LocalEndpoint $this, final TaskDescription task) {
      $this.org$apache$spark$scheduler$local$LocalEndpoint$$freeCores_$eq($this.org$apache$spark$scheduler$local$LocalEndpoint$$freeCores() - $this.org$apache$spark$scheduler$local$LocalEndpoint$$scheduler.CPUS_PER_TASK());
      $this.org$apache$spark$scheduler$local$LocalEndpoint$$executor().launchTask($this.executorBackend, task);
   }

   public LocalEndpoint(final RpcEnv rpcEnv, final Seq userClassPath, final TaskSchedulerImpl scheduler, final LocalSchedulerBackend executorBackend, final int totalCores) {
      this.rpcEnv = rpcEnv;
      this.org$apache$spark$scheduler$local$LocalEndpoint$$scheduler = scheduler;
      this.executorBackend = executorBackend;
      this.totalCores = totalCores;
      RpcEndpoint.$init$(this);
      Logging.$init$(this);
      this.org$apache$spark$scheduler$local$LocalEndpoint$$freeCores = totalCores;
      this.localExecutorId = SparkContext$.MODULE$.DRIVER_IDENTIFIER();
      this.localExecutorHostname = Utils$.MODULE$.localCanonicalHostName();
      String x$1 = this.localExecutorId();
      String x$2 = this.localExecutorHostname();
      SparkEnv x$3 = SparkEnv$.MODULE$.get();
      boolean x$5 = true;
      scala.collection.immutable.Map x$6 = scala.Predef..MODULE$.Map().empty();
      Thread.UncaughtExceptionHandler x$7 = Executor$.MODULE$.$lessinit$greater$default$6();
      this.org$apache$spark$scheduler$local$LocalEndpoint$$executor = new Executor(x$1, x$2, x$3, userClassPath, true, x$7, x$6);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
