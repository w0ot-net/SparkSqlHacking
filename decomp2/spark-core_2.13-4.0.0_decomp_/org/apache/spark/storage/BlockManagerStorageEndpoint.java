package org.apache.spark.storage;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.spark.MapOutputTracker;
import org.apache.spark.SparkEnv$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.rpc.IsolatedThreadSafeRpcEndpoint;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.shuffle.ShuffleManager;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.PartialFunction;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.concurrent.ExecutionContextExecutorService;
import scala.concurrent.Future;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005y4Q!\u0004\b\u0001\u001dYA\u0001\"\u000b\u0001\u0003\u0006\u0004%\te\u000b\u0005\t_\u0001\u0011\t\u0011)A\u0005Y!A\u0001\u0007\u0001B\u0001B\u0003%\u0011\u0007\u0003\u00056\u0001\t\u0005\t\u0015!\u00037\u0011\u0015Q\u0004\u0001\"\u0001<\u0011\u001d\u0001\u0005A1A\u0005\n\u0005Ca\u0001\u0014\u0001!\u0002\u0013\u0011\u0005bB'\u0001\u0005\u0004%YA\u0014\u0005\u0007)\u0002\u0001\u000b\u0011B(\t\u000bU\u0003A\u0011\t,\t\u000b\u0015\u0004A\u0011\u00024\t\u000bq\u0004A\u0011I?\u00037\tcwnY6NC:\fw-\u001a:Ti>\u0014\u0018mZ3F]\u0012\u0004x.\u001b8u\u0015\ty\u0001#A\u0004ti>\u0014\u0018mZ3\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e\u001cB\u0001A\f\u001eGA\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\u0004\"AH\u0011\u000e\u0003}Q!\u0001\t\t\u0002\u0007I\u00048-\u0003\u0002#?\ti\u0012j]8mCR,G\r\u00165sK\u0006$7+\u00194f%B\u001cWI\u001c3q_&tG\u000f\u0005\u0002%O5\tQE\u0003\u0002'!\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002)K\t9Aj\\4hS:<\u0017A\u0002:qG\u0016sgo\u0001\u0001\u0016\u00031\u0002\"AH\u0017\n\u00059z\"A\u0002*qG\u0016sg/A\u0004sa\u000e,eN\u001e\u0011\u0002\u0019\tdwnY6NC:\fw-\u001a:\u0011\u0005I\u001aT\"\u0001\b\n\u0005Qr!\u0001\u0004\"m_\u000e\\W*\u00198bO\u0016\u0014\u0018\u0001E7ba>+H\u000f];u)J\f7m[3s!\t9\u0004(D\u0001\u0011\u0013\tI\u0004C\u0001\tNCB|U\u000f\u001e9viR\u0013\u0018mY6fe\u00061A(\u001b8jiz\"B\u0001P\u001f?\u007fA\u0011!\u0007\u0001\u0005\u0006S\u0015\u0001\r\u0001\f\u0005\u0006a\u0015\u0001\r!\r\u0005\u0006k\u0015\u0001\rAN\u0001\u0010CNLhn\u0019+ie\u0016\fG\rU8pYV\t!\t\u0005\u0002D\u00156\tAI\u0003\u0002F\r\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005\u001dC\u0015\u0001B;uS2T\u0011!S\u0001\u0005U\u00064\u0018-\u0003\u0002L\t\n\u0011B\u000b\u001b:fC\u0012\u0004vn\u001c7Fq\u0016\u001cW\u000f^8s\u0003A\t7/\u001f8d)\"\u0014X-\u00193Q_>d\u0007%A\u000bbgft7-\u0012=fGV$\u0018n\u001c8D_:$X\r\u001f;\u0016\u0003=\u0003\"\u0001\u0015*\u000e\u0003ES!!R\r\n\u0005M\u000b&aH#yK\u000e,H/[8o\u0007>tG/\u001a=u\u000bb,7-\u001e;peN+'O^5dK\u00061\u0012m]=oG\u0016CXmY;uS>t7i\u001c8uKb$\b%A\bsK\u000e,\u0017N^3B]\u0012\u0014V\r\u001d7z)\t9\u0006\r\u0005\u0003\u00191jk\u0016BA-\u001a\u0005=\u0001\u0016M\u001d;jC24UO\\2uS>t\u0007C\u0001\r\\\u0013\ta\u0016DA\u0002B]f\u0004\"\u0001\u00070\n\u0005}K\"\u0001B+oSRDQ!\u0019\u0006A\u0002\t\fqaY8oi\u0016DH\u000f\u0005\u0002\u001fG&\u0011Am\b\u0002\u000f%B\u001c7)\u00197m\u0007>tG/\u001a=u\u0003\u001d!w.Q:z]\u000e,\"a\u001a9\u0015\u0007!48\u0010\u0006\u0002^S\"1!n\u0003CA\u0002-\fAAY8esB\u0019\u0001\u0004\u001c8\n\u00055L\"\u0001\u0003\u001fcs:\fW.\u001a \u0011\u0005=\u0004H\u0002\u0001\u0003\u0006c.\u0011\rA\u001d\u0002\u0002)F\u00111O\u0017\t\u00031QL!!^\r\u0003\u000f9{G\u000f[5oO\")qo\u0003a\u0001q\u0006i\u0011m\u0019;j_:lUm]:bO\u0016\u0004\"\u0001J=\n\u0005i,#AE'fgN\fw-Z,ji\"\u001cuN\u001c;fqRDQ!Y\u0006A\u0002\t\faa\u001c8Ti>\u0004H#A/"
)
public class BlockManagerStorageEndpoint implements IsolatedThreadSafeRpcEndpoint, Logging {
   private final RpcEnv rpcEnv;
   public final BlockManager org$apache$spark$storage$BlockManagerStorageEndpoint$$blockManager;
   public final MapOutputTracker org$apache$spark$storage$BlockManagerStorageEndpoint$$mapOutputTracker;
   private final ThreadPoolExecutor asyncThreadPool;
   private final ExecutionContextExecutorService asyncExecutionContext;
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

   public void onStart() {
      RpcEndpoint.onStart$(this);
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

   private ThreadPoolExecutor asyncThreadPool() {
      return this.asyncThreadPool;
   }

   private ExecutionContextExecutorService asyncExecutionContext() {
      return this.asyncExecutionContext;
   }

   public PartialFunction receiveAndReply(final RpcCallContext context) {
      return new Serializable(context) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final BlockManagerStorageEndpoint $outer;
         private final RpcCallContext context$1;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (x1 instanceof BlockManagerMessages.RemoveBlock var5) {
               BlockId blockId = var5.blockId();
               this.$outer.org$apache$spark$storage$BlockManagerStorageEndpoint$$doAsync(this.$outer.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"removing block ", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))), this.context$1, (JFunction0.mcZ.sp)() -> {
                  this.$outer.org$apache$spark$storage$BlockManagerStorageEndpoint$$blockManager.removeBlock(blockId, this.$outer.org$apache$spark$storage$BlockManagerStorageEndpoint$$blockManager.removeBlock$default$2());
                  return true;
               });
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.RemoveRdd var7) {
               int rddId = var7.rddId();
               this.$outer.org$apache$spark$storage$BlockManagerStorageEndpoint$$doAsync(this.$outer.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"removing RDD ", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RDD_ID..MODULE$, BoxesRunTime.boxToInteger(rddId))}))), this.context$1, (JFunction0.mcI.sp)() -> this.$outer.org$apache$spark$storage$BlockManagerStorageEndpoint$$blockManager.removeRdd(rddId));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.RemoveShuffle var9) {
               int shuffleId = var9.shuffleId();
               this.$outer.org$apache$spark$storage$BlockManagerStorageEndpoint$$doAsync(this.$outer.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"removing shuffle ", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleId))}))), this.context$1, (JFunction0.mcZ.sp)() -> {
                  if (this.$outer.org$apache$spark$storage$BlockManagerStorageEndpoint$$mapOutputTracker != null) {
                     this.$outer.org$apache$spark$storage$BlockManagerStorageEndpoint$$mapOutputTracker.unregisterShuffle(shuffleId);
                  }

                  ShuffleManager shuffleManager = SparkEnv$.MODULE$.get().shuffleManager();
                  if (shuffleManager != null) {
                     return shuffleManager.unregisterShuffle(shuffleId);
                  } else {
                     this.$outer.logDebug(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Ignore remove shuffle ", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleId))})))));
                     return true;
                  }
               });
               return BoxedUnit.UNIT;
            } else if (BlockManagerMessages.DecommissionBlockManager$.MODULE$.equals(x1)) {
               RpcCallContext var24 = this.context$1;
               this.$outer.org$apache$spark$storage$BlockManagerStorageEndpoint$$blockManager.decommissionSelf();
               var24.reply(BoxedUnit.UNIT);
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.RemoveBroadcast) {
               BlockManagerMessages.RemoveBroadcast var11 = (BlockManagerMessages.RemoveBroadcast)x1;
               long broadcastId = var11.broadcastId();
               this.$outer.org$apache$spark$storage$BlockManagerStorageEndpoint$$doAsync(this.$outer.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"removing broadcast ", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BROADCAST_ID..MODULE$, BoxesRunTime.boxToLong(broadcastId))}))), this.context$1, (JFunction0.mcI.sp)() -> this.$outer.org$apache$spark$storage$BlockManagerStorageEndpoint$$blockManager.removeBroadcast(broadcastId, true));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.GetBlockStatus) {
               BlockManagerMessages.GetBlockStatus var14 = (BlockManagerMessages.GetBlockStatus)x1;
               BlockId blockId = var14.blockId();
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerStorageEndpoint$$blockManager.getStatus(blockId));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.GetMatchingBlockIds) {
               BlockManagerMessages.GetMatchingBlockIds var16 = (BlockManagerMessages.GetMatchingBlockIds)x1;
               Function1 filter = var16.filter();
               this.context$1.reply(this.$outer.org$apache$spark$storage$BlockManagerStorageEndpoint$$blockManager.getMatchingBlockIds(filter));
               return BoxedUnit.UNIT;
            } else if (BlockManagerMessages.TriggerThreadDump$.MODULE$.equals(x1)) {
               this.context$1.reply(Utils$.MODULE$.getThreadDump());
               return BoxedUnit.UNIT;
            } else if (BlockManagerMessages.TriggerHeapHistogram$.MODULE$.equals(x1)) {
               this.context$1.reply(Utils$.MODULE$.getHeapHistogram());
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.ReplicateBlock) {
               BlockManagerMessages.ReplicateBlock var18 = (BlockManagerMessages.ReplicateBlock)x1;
               BlockId blockId = var18.blockId();
               Seq replicas = var18.replicas();
               int maxReplicas = var18.maxReplicas();
               this.context$1.reply(BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$storage$BlockManagerStorageEndpoint$$blockManager.replicateBlock(blockId, replicas.toSet(), maxReplicas, this.$outer.org$apache$spark$storage$BlockManagerStorageEndpoint$$blockManager.replicateBlock$default$4())));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof BlockManagerMessages.MarkRDDBlockAsVisible) {
               BlockManagerMessages.MarkRDDBlockAsVisible var22 = (BlockManagerMessages.MarkRDDBlockAsVisible)x1;
               RDDBlockId blockId = var22.blockId();
               RpcCallContext var10000 = this.context$1;
               this.$outer.org$apache$spark$storage$BlockManagerStorageEndpoint$$blockManager.blockInfoManager().tryMarkBlockAsVisible(blockId);
               var10000.reply(BoxedUnit.UNIT);
               return BoxedUnit.UNIT;
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            if (x1 instanceof BlockManagerMessages.RemoveBlock) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.RemoveRdd) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.RemoveShuffle) {
               return true;
            } else if (BlockManagerMessages.DecommissionBlockManager$.MODULE$.equals(x1)) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.RemoveBroadcast) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.GetBlockStatus) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.GetMatchingBlockIds) {
               return true;
            } else if (BlockManagerMessages.TriggerThreadDump$.MODULE$.equals(x1)) {
               return true;
            } else if (BlockManagerMessages.TriggerHeapHistogram$.MODULE$.equals(x1)) {
               return true;
            } else if (x1 instanceof BlockManagerMessages.ReplicateBlock) {
               return true;
            } else {
               return x1 instanceof BlockManagerMessages.MarkRDDBlockAsVisible;
            }
         }

         public {
            if (BlockManagerStorageEndpoint.this == null) {
               throw null;
            } else {
               this.$outer = BlockManagerStorageEndpoint.this;
               this.context$1 = context$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public void org$apache$spark$storage$BlockManagerStorageEndpoint$$doAsync(final MessageWithContext actionMessage, final RpcCallContext context, final Function0 body) {
      Future future = scala.concurrent.Future..MODULE$.apply(() -> {
         this.logDebug((Function0)(() -> actionMessage.message()));
         return body.apply();
      }, this.asyncExecutionContext());
      future.foreach((response) -> {
         $anonfun$doAsync$3(this, actionMessage, context, response);
         return BoxedUnit.UNIT;
      }, this.asyncExecutionContext());
      future.failed().foreach((t) -> {
         $anonfun$doAsync$6(this, actionMessage, context, t);
         return BoxedUnit.UNIT;
      }, this.asyncExecutionContext());
   }

   public void onStop() {
      this.asyncThreadPool().shutdownNow();
   }

   // $FF: synthetic method
   public static final void $anonfun$doAsync$3(final BlockManagerStorageEndpoint $this, final MessageWithContext actionMessage$1, final RpcCallContext context$2, final Object response) {
      $this.logDebug((Function0)(() -> {
         String var10000 = actionMessage$1.message();
         return "Done " + var10000 + ", response is " + response;
      }));
      context$2.reply(response);
      $this.logDebug((Function0)(() -> "Sent response: " + response + " to " + context$2.senderAddress()));
   }

   // $FF: synthetic method
   public static final void $anonfun$doAsync$6(final BlockManagerStorageEndpoint $this, final MessageWithContext actionMessage$1, final RpcCallContext context$2, final Throwable t) {
      $this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Error in "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(actionMessage$1)), t);
      context$2.sendFailure(t);
   }

   public BlockManagerStorageEndpoint(final RpcEnv rpcEnv, final BlockManager blockManager, final MapOutputTracker mapOutputTracker) {
      this.rpcEnv = rpcEnv;
      this.org$apache$spark$storage$BlockManagerStorageEndpoint$$blockManager = blockManager;
      this.org$apache$spark$storage$BlockManagerStorageEndpoint$$mapOutputTracker = mapOutputTracker;
      RpcEndpoint.$init$(this);
      IsolatedThreadSafeRpcEndpoint.$init$(this);
      Logging.$init$(this);
      this.asyncThreadPool = ThreadUtils$.MODULE$.newDaemonCachedThreadPool("block-manager-storage-async-thread-pool", 100, ThreadUtils$.MODULE$.newDaemonCachedThreadPool$default$3());
      this.asyncExecutionContext = scala.concurrent.ExecutionContext..MODULE$.fromExecutorService(this.asyncThreadPool());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
