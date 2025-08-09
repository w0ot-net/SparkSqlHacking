package org.apache.spark;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.PartialFunction;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005U3Q\u0001C\u0005\u0001\u0013=A\u0001B\t\u0001\u0003\u0006\u0004%\t\u0005\n\u0005\tQ\u0001\u0011\t\u0011)A\u0005K!A\u0011\u0006\u0001B\u0001B\u0003%!\u0006\u0003\u0005/\u0001\t\u0005\t\u0015!\u00030\u0011\u0015\u0011\u0004\u0001\"\u00014\u0011\u0015A\u0004\u0001\"\u0003:\u0011\u0015a\u0005\u0001\"\u0011N\u0005yi\u0015\r](viB,H\u000f\u0016:bG.,'/T1ti\u0016\u0014XI\u001c3q_&tGO\u0003\u0002\u000b\u0017\u0005)1\u000f]1sW*\u0011A\"D\u0001\u0007CB\f7\r[3\u000b\u00039\t1a\u001c:h'\u0011\u0001\u0001C\u0006\u000f\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\r\u0005s\u0017PU3g!\t9\"$D\u0001\u0019\u0015\tI\u0012\"A\u0002sa\u000eL!a\u0007\r\u0003\u0017I\u00038-\u00128ea>Lg\u000e\u001e\t\u0003;\u0001j\u0011A\b\u0006\u0003?%\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003Cy\u0011q\u0001T8hO&tw-\u0001\u0004sa\u000e,eN^\u0002\u0001+\u0005)\u0003CA\f'\u0013\t9\u0003D\u0001\u0004Sa\u000e,eN^\u0001\beB\u001cWI\u001c<!\u0003\u001d!(/Y2lKJ\u0004\"a\u000b\u0017\u000e\u0003%I!!L\u0005\u0003-5\u000b\u0007oT;uaV$HK]1dW\u0016\u0014X*Y:uKJ\fAaY8oMB\u00111\u0006M\u0005\u0003c%\u0011\u0011b\u00159be.\u001cuN\u001c4\u0002\rqJg.\u001b;?)\u0011!TGN\u001c\u0011\u0005-\u0002\u0001\"\u0002\u0012\u0006\u0001\u0004)\u0003\"B\u0015\u0006\u0001\u0004Q\u0003\"\u0002\u0018\u0006\u0001\u0004y\u0013A\u00037pO&sgm\\'tOR!!(\u0010\"H!\t\t2(\u0003\u0002=%\t!QK\\5u\u0011\u0015qd\u00011\u0001@\u0003\ri7o\u001a\t\u0003;\u0001K!!\u0011\u0010\u0003%5+7o]1hK^KG\u000f[\"p]R,\u0007\u0010\u001e\u0005\u0006\u0007\u001a\u0001\r\u0001R\u0001\ng\",hM\u001a7f\u0013\u0012\u0004\"!E#\n\u0005\u0019\u0013\"aA%oi\")\u0001J\u0002a\u0001\u0013\u000691m\u001c8uKb$\bCA\fK\u0013\tY\u0005D\u0001\bSa\u000e\u001c\u0015\r\u001c7D_:$X\r\u001f;\u0002\u001fI,7-Z5wK\u0006sGMU3qYf$\"A\u0014+\u0011\tEy\u0015KO\u0005\u0003!J\u0011q\u0002U1si&\fGNR;oGRLwN\u001c\t\u0003#IK!a\u0015\n\u0003\u0007\u0005s\u0017\u0010C\u0003I\u000f\u0001\u0007\u0011\n"
)
public class MapOutputTrackerMasterEndpoint implements RpcEndpoint, Logging {
   private final RpcEnv rpcEnv;
   public final MapOutputTrackerMaster org$apache$spark$MapOutputTrackerMasterEndpoint$$tracker;
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

   public void org$apache$spark$MapOutputTrackerMasterEndpoint$$logInfoMsg(final MessageWithContext msg, final int shuffleId, final RpcCallContext context) {
      String hostPort = context.senderAddress().hostPort();
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to send "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(msg).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" locations for shuffle ", " to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleId)), new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, hostPort)}))))));
   }

   public PartialFunction receiveAndReply(final RpcCallContext context) {
      return new Serializable(context) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final MapOutputTrackerMasterEndpoint $outer;
         private final RpcCallContext context$1;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (x1 instanceof GetMapOutputStatuses var5) {
               int shuffleId = var5.shuffleId();
               if (true) {
                  this.$outer.org$apache$spark$MapOutputTrackerMasterEndpoint$$logInfoMsg(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"map output"})))).log(scala.collection.immutable.Nil..MODULE$), shuffleId, this.context$1);
                  this.$outer.org$apache$spark$MapOutputTrackerMasterEndpoint$$tracker.post(new GetMapOutputMessage(shuffleId, this.context$1));
                  return BoxedUnit.UNIT;
               }
            }

            if (x1 instanceof GetMapAndMergeResultStatuses var8) {
               int shuffleId = var8.shuffleId();
               if (true) {
                  this.$outer.org$apache$spark$MapOutputTrackerMasterEndpoint$$logInfoMsg(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"map/merge result"})))).log(scala.collection.immutable.Nil..MODULE$), shuffleId, this.context$1);
                  this.$outer.org$apache$spark$MapOutputTrackerMasterEndpoint$$tracker.post(new GetMapAndMergeOutputMessage(shuffleId, this.context$1));
                  return BoxedUnit.UNIT;
               }
            }

            if (x1 instanceof GetShufflePushMergerLocations var11) {
               int shuffleId = var11.shuffleId();
               if (true) {
                  this.$outer.org$apache$spark$MapOutputTrackerMasterEndpoint$$logInfoMsg(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"shuffle push merger"})))).log(scala.collection.immutable.Nil..MODULE$), shuffleId, this.context$1);
                  this.$outer.org$apache$spark$MapOutputTrackerMasterEndpoint$$tracker.post(new GetShufflePushMergersMessage(shuffleId, this.context$1));
                  return BoxedUnit.UNIT;
               }
            }

            if (StopMapOutputTracker$.MODULE$.equals(x1)) {
               this.$outer.logInfo((Function0)(() -> "MapOutputTrackerMasterEndpoint stopped!"));
               this.context$1.reply(BoxesRunTime.boxToBoolean(true));
               this.$outer.stop();
               return BoxedUnit.UNIT;
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            if (x1 instanceof GetMapOutputStatuses && true) {
               return true;
            } else if (x1 instanceof GetMapAndMergeResultStatuses && true) {
               return true;
            } else if (x1 instanceof GetShufflePushMergerLocations && true) {
               return true;
            } else {
               return StopMapOutputTracker$.MODULE$.equals(x1);
            }
         }

         public {
            if (MapOutputTrackerMasterEndpoint.this == null) {
               throw null;
            } else {
               this.$outer = MapOutputTrackerMasterEndpoint.this;
               this.context$1 = context$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public MapOutputTrackerMasterEndpoint(final RpcEnv rpcEnv, final MapOutputTrackerMaster tracker, final SparkConf conf) {
      this.rpcEnv = rpcEnv;
      this.org$apache$spark$MapOutputTrackerMasterEndpoint$$tracker = tracker;
      RpcEndpoint.$init$(this);
      Logging.$init$(this);
      this.logDebug((Function0)(() -> "init"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
