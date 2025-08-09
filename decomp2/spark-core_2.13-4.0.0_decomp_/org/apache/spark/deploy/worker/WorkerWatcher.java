package org.apache.spark.deploy.worker;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcAddress$;
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

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=c!B\f\u0019\u0001q\u0011\u0003\u0002C\u001b\u0001\u0005\u000b\u0007I\u0011I\u001c\t\u0011m\u0002!\u0011!Q\u0001\naB\u0001\u0002\u0010\u0001\u0003\u0002\u0003\u0006I!\u0010\u0005\t\u0011\u0002\u0011\t\u0011)A\u0005\u0013\"AA\n\u0001B\u0001B\u0003%Q\nC\u0003Z\u0001\u0011\u0005!\f\u0003\u0005b\u0001\u0001\u0007I\u0011\u0001\u000ec\u0011!\u0019\u0007\u00011A\u0005\u0002i!\u0007B\u00026\u0001A\u0003&\u0011\nC\u0004l\u0001\t\u0007I\u0011\u00027\t\rA\u0004\u0001\u0015!\u0003n\u0011\u0015\t\b\u0001\"\u0003s\u0011\u0015)\b\u0001\"\u0003w\u0011\u00159\b\u0001\"\u0011y\u0011\u0019y\b\u0001\"\u0011\u0002\u0002!9\u0011q\u0001\u0001\u0005B\u0005%\u0001bBA\u0007\u0001\u0011\u0005\u0013qB\u0004\u000b\u0003SA\u0012\u0011!E\u00019\u0005-b!C\f\u0019\u0003\u0003E\t\u0001HA\u0017\u0011\u0019I6\u0003\"\u0001\u00020!I\u0011\u0011G\n\u0012\u0002\u0013\u0005\u00111\u0007\u0005\n\u0003\u0013\u001a\u0012\u0013!C\u0001\u0003\u0017\u0012QbV8sW\u0016\u0014x+\u0019;dQ\u0016\u0014(BA\r\u001b\u0003\u00199xN]6fe*\u00111\u0004H\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005uq\u0012!B:qCJ\\'BA\u0010!\u0003\u0019\t\u0007/Y2iK*\t\u0011%A\u0002pe\u001e\u001cB\u0001A\u0012*_A\u0011AeJ\u0007\u0002K)\ta%A\u0003tG\u0006d\u0017-\u0003\u0002)K\t1\u0011I\\=SK\u001a\u0004\"AK\u0017\u000e\u0003-R!\u0001\f\u000f\u0002\u0007I\u00048-\u0003\u0002/W\tY!\u000b]2F]\u0012\u0004x.\u001b8u!\t\u00014'D\u00012\u0015\t\u0011D$\u0001\u0005j]R,'O\\1m\u0013\t!\u0014GA\u0004M_\u001e<\u0017N\\4\u0002\rI\u00048-\u00128w\u0007\u0001)\u0012\u0001\u000f\t\u0003UeJ!AO\u0016\u0003\rI\u00038-\u00128w\u0003\u001d\u0011\boY#om\u0002\n\u0011b^8sW\u0016\u0014XK\u001d7\u0011\u0005y*eBA D!\t\u0001U%D\u0001B\u0015\t\u0011e'\u0001\u0004=e>|GOP\u0005\u0003\t\u0016\na\u0001\u0015:fI\u00164\u0017B\u0001$H\u0005\u0019\u0019FO]5oO*\u0011A)J\u0001\nSN$Vm\u001d;j]\u001e\u0004\"\u0001\n&\n\u0005-+#a\u0002\"p_2,\u0017M\\\u0001\u0017SN\u001c\u0005.\u001b7e!J|7-Z:t'R|\u0007\u000f]5oOB\u0011ajV\u0007\u0002\u001f*\u0011\u0001+U\u0001\u0007CR|W.[2\u000b\u0005I\u001b\u0016AC2p]\u000e,(O]3oi*\u0011A+V\u0001\u0005kRLGNC\u0001W\u0003\u0011Q\u0017M^1\n\u0005a{%!D!u_6L7MQ8pY\u0016\fg.\u0001\u0004=S:LGO\u0010\u000b\u00067vsv\f\u0019\t\u00039\u0002i\u0011\u0001\u0007\u0005\u0006k\u0019\u0001\r\u0001\u000f\u0005\u0006y\u0019\u0001\r!\u0010\u0005\b\u0011\u001a\u0001\n\u00111\u0001J\u0011\u001dae\u0001%AA\u00025\u000b!\"[:TQV$Hi\\<o+\u0005I\u0015AD5t'\",H\u000fR8x]~#S-\u001d\u000b\u0003K\"\u0004\"\u0001\n4\n\u0005\u001d,#\u0001B+oSRDq!\u001b\u0005\u0002\u0002\u0003\u0007\u0011*A\u0002yIE\n1\"[:TQV$Hi\\<oA\u0005yQ\r\u001f9fGR,G-\u00113ee\u0016\u001c8/F\u0001n!\tQc.\u0003\u0002pW\tQ!\u000b]2BI\u0012\u0014Xm]:\u0002!\u0015D\b/Z2uK\u0012\fE\r\u001a:fgN\u0004\u0013\u0001C5t/>\u00148.\u001a:\u0015\u0005%\u001b\b\"\u0002;\r\u0001\u0004i\u0017aB1eIJ,7o]\u0001\fKbLGOT8o5\u0016\u0014x\u000eF\u0001f\u0003\u001d\u0011XmY3jm\u0016,\u0012!\u001f\t\u0005IidX-\u0003\u0002|K\ty\u0001+\u0019:uS\u0006dg)\u001e8di&|g\u000e\u0005\u0002%{&\u0011a0\n\u0002\u0004\u0003:L\u0018aC8o\u0007>tg.Z2uK\u0012$2!ZA\u0002\u0011\u0019\t)a\u0004a\u0001[\u0006i!/Z7pi\u0016\fE\r\u001a:fgN\fab\u001c8ESN\u001cwN\u001c8fGR,G\rF\u0002f\u0003\u0017Aa!!\u0002\u0011\u0001\u0004i\u0017AD8o\u001d\u0016$xo\u001c:l\u000bJ\u0014xN\u001d\u000b\u0006K\u0006E\u0011q\u0005\u0005\b\u0003'\t\u0002\u0019AA\u000b\u0003\u0015\u0019\u0017-^:f!\u0011\t9\"!\t\u000f\t\u0005e\u0011Q\u0004\b\u0004\u0001\u0006m\u0011\"\u0001\u0014\n\u0007\u0005}Q%A\u0004qC\u000e\\\u0017mZ3\n\t\u0005\r\u0012Q\u0005\u0002\n)\"\u0014xn^1cY\u0016T1!a\b&\u0011\u0019\t)!\u0005a\u0001[\u0006iqk\u001c:lKJ<\u0016\r^2iKJ\u0004\"\u0001X\n\u0014\u0005M\u0019CCAA\u0016\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u0011Q\u0007\u0016\u0004\u0013\u0006]2FAA\u001d!\u0011\tY$!\u0012\u000e\u0005\u0005u\"\u0002BA \u0003\u0003\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\rS%\u0001\u0006b]:|G/\u0019;j_:LA!a\u0012\u0002>\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00135+\t\tiEK\u0002N\u0003o\u0001"
)
public class WorkerWatcher implements RpcEndpoint, Logging {
   private final RpcEnv rpcEnv;
   private final String workerUrl;
   private final boolean isTesting;
   private final AtomicBoolean isChildProcessStopping;
   private boolean isShutDown;
   private final RpcAddress expectedAddress;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static AtomicBoolean $lessinit$greater$default$4() {
      return WorkerWatcher$.MODULE$.$lessinit$greater$default$4();
   }

   public static boolean $lessinit$greater$default$3() {
      return WorkerWatcher$.MODULE$.$lessinit$greater$default$3();
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

   public final RpcEndpointRef self() {
      return RpcEndpoint.self$(this);
   }

   public PartialFunction receiveAndReply(final RpcCallContext context) {
      return RpcEndpoint.receiveAndReply$(this, context);
   }

   public void onError(final Throwable cause) {
      RpcEndpoint.onError$(this, cause);
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

   public boolean isShutDown() {
      return this.isShutDown;
   }

   public void isShutDown_$eq(final boolean x$1) {
      this.isShutDown = x$1;
   }

   private RpcAddress expectedAddress() {
      return this.expectedAddress;
   }

   private boolean isWorker(final RpcAddress address) {
      boolean var3;
      label23: {
         RpcAddress var10000 = this.expectedAddress();
         if (var10000 == null) {
            if (address == null) {
               break label23;
            }
         } else if (var10000.equals(address)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   private void exitNonZero() {
      if (this.isTesting) {
         this.isShutDown_$eq(true);
      } else if (this.isChildProcessStopping.compareAndSet(false, true)) {
         (new Thread() {
            public void run() {
               System.exit(-1);
            }
         }).start();
      }
   }

   public PartialFunction receive() {
      return new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final WorkerWatcher $outer;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            this.$outer.logWarning(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Received unexpected message: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, x1)})))));
            return BoxedUnit.UNIT;
         }

         public final boolean isDefinedAt(final Object x1) {
            return true;
         }

         public {
            if (WorkerWatcher.this == null) {
               throw null;
            } else {
               this.$outer = WorkerWatcher.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public void onConnected(final RpcAddress remoteAddress) {
      if (this.isWorker(remoteAddress)) {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Successfully connected to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_URL..MODULE$, this.workerUrl)})))));
      }
   }

   public void onDisconnected(final RpcAddress remoteAddress) {
      if (this.isWorker(remoteAddress)) {
         this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Lost connection to worker rpc endpoint ", ". Exiting."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_URL..MODULE$, this.workerUrl)})))));
         this.exitNonZero();
      }
   }

   public void onNetworkError(final Throwable cause, final RpcAddress remoteAddress) {
      if (this.isWorker(remoteAddress)) {
         this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Could not initialize connection to worker ", ". Exiting."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_URL..MODULE$, this.workerUrl)})))), cause);
         this.exitNonZero();
      }
   }

   public WorkerWatcher(final RpcEnv rpcEnv, final String workerUrl, final boolean isTesting, final AtomicBoolean isChildProcessStopping) {
      this.rpcEnv = rpcEnv;
      this.workerUrl = workerUrl;
      this.isTesting = isTesting;
      this.isChildProcessStopping = isChildProcessStopping;
      RpcEndpoint.$init$(this);
      Logging.$init$(this);
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Connecting to worker ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_URL..MODULE$, this.workerUrl)})))));
      if (!isTesting) {
         rpcEnv.asyncSetupEndpointRefByURI(workerUrl);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.isShutDown = false;
      this.expectedAddress = RpcAddress$.MODULE$.fromUrlString(workerUrl);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
