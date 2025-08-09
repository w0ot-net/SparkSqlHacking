package org.apache.spark.storage;

import java.io.Serializable;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.ThreadSafeRpcEndpoint;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.PartialFunction;
import scala.StringContext;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005e3Q\u0001C\u0005\u0001\u0017EA\u0001\u0002\n\u0001\u0003\u0006\u0004%\tE\n\u0005\tU\u0001\u0011\t\u0011)A\u0005O!A1\u0006\u0001B\u0001B\u0003%A\u0006\u0003\u00050\u0001\t\u0005\t\u0015!\u00031\u0011\u0015y\u0004\u0001\"\u0001A\u0011\u0015)\u0005\u0001\"\u0011G\u0011\u0015)\u0006\u0001\"\u0003W\u0005\r\u0012En\\2l\u001b\u0006t\u0017mZ3s\u001b\u0006\u001cH/\u001a:IK\u0006\u0014HOY3bi\u0016sG\r]8j]RT!AC\u0006\u0002\u000fM$xN]1hK*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0005\u0003\u0001%aq\u0002CA\n\u0017\u001b\u0005!\"\"A\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]!\"AB!osJ+g\r\u0005\u0002\u001a95\t!D\u0003\u0002\u001c\u0017\u0005\u0019!\u000f]2\n\u0005uQ\"!\u0006+ie\u0016\fGmU1gKJ\u00038-\u00128ea>Lg\u000e\u001e\t\u0003?\tj\u0011\u0001\t\u0006\u0003C-\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003G\u0001\u0012q\u0001T8hO&tw-\u0001\u0004sa\u000e,eN^\u0002\u0001+\u00059\u0003CA\r)\u0013\tI#D\u0001\u0004Sa\u000e,eN^\u0001\beB\u001cWI\u001c<!\u0003\u001dI7\u000fT8dC2\u0004\"aE\u0017\n\u00059\"\"a\u0002\"p_2,\u0017M\\\u0001\u0011E2|7m['b]\u0006<WM]%oM>\u0004B!\r\u001c9y5\t!G\u0003\u00024i\u00059Q.\u001e;bE2,'BA\u001b\u0015\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003oI\u00121!T1q!\tI$(D\u0001\n\u0013\tY\u0014B\u0001\bCY>\u001c7.T1oC\u001e,'/\u00133\u0011\u0005ej\u0014B\u0001 \n\u0005A\u0011En\\2l\u001b\u0006t\u0017mZ3s\u0013:4w.\u0001\u0004=S:LGO\u0010\u000b\u0005\u0003\n\u001bE\t\u0005\u0002:\u0001!)A%\u0002a\u0001O!)1&\u0002a\u0001Y!)q&\u0002a\u0001a\u0005y!/Z2fSZ,\u0017I\u001c3SKBd\u0017\u0010\u0006\u0002H!B!1\u0003\u0013&N\u0013\tIECA\bQCJ$\u0018.\u00197Gk:\u001cG/[8o!\t\u00192*\u0003\u0002M)\t\u0019\u0011I\\=\u0011\u0005Mq\u0015BA(\u0015\u0005\u0011)f.\u001b;\t\u000bE3\u0001\u0019\u0001*\u0002\u000f\r|g\u000e^3yiB\u0011\u0011dU\u0005\u0003)j\u0011aB\u00159d\u0007\u0006dGnQ8oi\u0016DH/A\tiK\u0006\u0014HOY3biJ+7-Z5wK\u0012$\"\u0001L,\t\u000ba;\u0001\u0019\u0001\u001d\u0002\u001d\tdwnY6NC:\fw-\u001a:JI\u0002"
)
public class BlockManagerMasterHeartbeatEndpoint implements ThreadSafeRpcEndpoint, Logging {
   private final RpcEnv rpcEnv;
   private final boolean isLocal;
   private final Map blockManagerInfo;
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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public PartialFunction receiveAndReply(final RpcCallContext context) {
      return new Serializable(context) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final BlockManagerMasterHeartbeatEndpoint $outer;
         private final RpcCallContext context$1;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (x1 instanceof BlockManagerMessages.BlockManagerHeartbeat var5) {
               BlockManagerId blockManagerId = var5.blockManagerId();
               this.context$1.reply(BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$storage$BlockManagerMasterHeartbeatEndpoint$$heartbeatReceived(blockManagerId)));
               return BoxedUnit.UNIT;
            } else if (BlockManagerMessages.StopBlockManagerMaster$.MODULE$.equals(x1)) {
               this.$outer.stop();
               this.context$1.reply(BoxesRunTime.boxToBoolean(true));
               return BoxedUnit.UNIT;
            } else {
               return BoxedUnit.UNIT;
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            if (x1 instanceof BlockManagerMessages.BlockManagerHeartbeat) {
               return true;
            } else {
               return BlockManagerMessages.StopBlockManagerMaster$.MODULE$.equals(x1) ? true : true;
            }
         }

         public {
            if (BlockManagerMasterHeartbeatEndpoint.this == null) {
               throw null;
            } else {
               this.$outer = BlockManagerMasterHeartbeatEndpoint.this;
               this.context$1 = context$1;
            }
         }
      };
   }

   public boolean org$apache$spark$storage$BlockManagerMasterHeartbeatEndpoint$$heartbeatReceived(final BlockManagerId blockManagerId) {
      if (this.blockManagerInfo.contains(blockManagerId)) {
         ((BlockManagerInfo)this.blockManagerInfo.apply(blockManagerId)).updateLastSeenMs();
         return true;
      } else {
         return blockManagerId.isDriver() && !this.isLocal;
      }
   }

   public BlockManagerMasterHeartbeatEndpoint(final RpcEnv rpcEnv, final boolean isLocal, final Map blockManagerInfo) {
      this.rpcEnv = rpcEnv;
      this.isLocal = isLocal;
      this.blockManagerInfo = blockManagerInfo;
      RpcEndpoint.$init$(this);
      Logging.$init$(this);
   }
}
