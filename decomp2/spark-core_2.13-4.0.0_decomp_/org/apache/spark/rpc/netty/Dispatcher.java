package org.apache.spark.rpc.netty;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import javax.annotation.concurrent.GuardedBy;
import org.apache.spark.SparkEnv$;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.rpc.IsolatedRpcEndpoint;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointAddress;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnvStoppedException;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.None.;
import scala.concurrent.Promise;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015g!B\u000e\u001d\u0001q1\u0003\u0002C\u001a\u0001\u0005\u0003\u0005\u000b\u0011B\u001b\t\u0011e\u0002!\u0011!Q\u0001\niBQ!\u0010\u0001\u0005\u0002yBqA\u0011\u0001C\u0002\u0013%1\t\u0003\u0004]\u0001\u0001\u0006I\u0001\u0012\u0005\b;\u0002\u0011\r\u0011\"\u0003_\u0011\u00199\u0007\u0001)A\u0005?\"9\u0001\u000e\u0001b\u0001\n\u0013I\u0007BB7\u0001A\u0003%!\u000e\u0003\u0005o\u0001!\u0015\r\u0011\"\u0003p\u0011\u001d\u0019\b\u00011A\u0005\nQDq\u0001\u001f\u0001A\u0002\u0013%\u0011\u0010\u0003\u0004\u0000\u0001\u0001\u0006K!\u001e\u0005\b\u00037\u0001A\u0011AA\u000f\u0011\u001d\ti\u0003\u0001C\u0001\u0003_Aq!a\r\u0001\t\u0003\t)\u0004C\u0004\u0002:\u0001!I!a\u000f\t\u000f\u0005}\u0002\u0001\"\u0001\u0002B!9\u0011q\t\u0001\u0005\u0002\u0005%\u0003bBA+\u0001\u0011\u0005\u0011q\u000b\u0005\b\u0003k\u0002A\u0011AA<\u0011\u001d\ty\t\u0001C\u0001\u0003#Cq!!&\u0001\t\u0013\t9\nC\u0004\u0002@\u0001!\t!a/\t\u000f\u0005u\u0006\u0001\"\u0001\u0002<\"9\u0011q\u0018\u0001\u0005\u0002\u0005\u0005'A\u0003#jgB\fGo\u00195fe*\u0011QDH\u0001\u0006]\u0016$H/\u001f\u0006\u0003?\u0001\n1A\u001d9d\u0015\t\t#%A\u0003ta\u0006\u00148N\u0003\u0002$I\u00051\u0011\r]1dQ\u0016T\u0011!J\u0001\u0004_J<7c\u0001\u0001([A\u0011\u0001fK\u0007\u0002S)\t!&A\u0003tG\u0006d\u0017-\u0003\u0002-S\t1\u0011I\\=SK\u001a\u0004\"AL\u0019\u000e\u0003=R!\u0001\r\u0011\u0002\u0011%tG/\u001a:oC2L!AM\u0018\u0003\u000f1{wmZ5oO\u0006Aa.\u001a;us\u0016sgo\u0001\u0001\u0011\u0005Y:T\"\u0001\u000f\n\u0005ab\"a\u0003(fiRL(\u000b]2F]Z\faB\\;n+N\f'\r\\3D_J,7\u000f\u0005\u0002)w%\u0011A(\u000b\u0002\u0004\u0013:$\u0018A\u0002\u001fj]&$h\bF\u0002@\u0001\u0006\u0003\"A\u000e\u0001\t\u000bM\u001a\u0001\u0019A\u001b\t\u000be\u001a\u0001\u0019\u0001\u001e\u0002\u0013\u0015tG\r]8j]R\u001cX#\u0001#\u0011\t\u0015ce*W\u0007\u0002\r*\u0011q\tS\u0001\u000bG>t7-\u001e:sK:$(BA%K\u0003\u0011)H/\u001b7\u000b\u0003-\u000bAA[1wC&\u0011QJ\u0012\u0002\u000e\u0007>t7-\u001e:sK:$X*\u00199\u0011\u0005=3fB\u0001)U!\t\t\u0016&D\u0001S\u0015\t\u0019F'\u0001\u0004=e>|GOP\u0005\u0003+&\na\u0001\u0015:fI\u00164\u0017BA,Y\u0005\u0019\u0019FO]5oO*\u0011Q+\u000b\t\u0003miK!a\u0017\u000f\u0003\u00175+7o]1hK2{w\u000e]\u0001\u000bK:$\u0007o\\5oiN\u0004\u0013\u0001D3oIB|\u0017N\u001c;SK\u001a\u001cX#A0\u0011\t\u0015c\u0005\r\u001a\t\u0003C\nl\u0011AH\u0005\u0003Gz\u00111B\u00159d\u000b:$\u0007o\\5oiB\u0011\u0011-Z\u0005\u0003Mz\u0011aB\u00159d\u000b:$\u0007o\\5oiJ+g-A\u0007f]\u0012\u0004x.\u001b8u%\u001647\u000fI\u0001\u000eg\",H\u000fZ8x]2\u000bGo\u00195\u0016\u0003)\u0004\"!R6\n\u000514%AD\"pk:$Hi\\<o\u0019\u0006$8\r[\u0001\u000fg\",H\u000fZ8x]2\u000bGo\u00195!\u0003)\u0019\b.\u0019:fI2{w\u000e]\u000b\u0002aB\u0011a']\u0005\u0003er\u0011\u0011c\u00155be\u0016$W*Z:tC\u001e,Gj\\8q\u0003\u001d\u0019Ho\u001c9qK\u0012,\u0012!\u001e\t\u0003QYL!a^\u0015\u0003\u000f\t{w\u000e\\3b]\u0006Y1\u000f^8qa\u0016$w\fJ3r)\tQX\u0010\u0005\u0002)w&\u0011A0\u000b\u0002\u0005+:LG\u000fC\u0004\u007f\u0019\u0005\u0005\t\u0019A;\u0002\u0007a$\u0013'\u0001\u0005ti>\u0004\b/\u001a3!Q\u001di\u00111AA\u000b\u0003/\u0001B!!\u0002\u0002\u00125\u0011\u0011q\u0001\u0006\u0004\u000f\u0006%!\u0002BA\u0006\u0003\u001b\t!\"\u00198o_R\fG/[8o\u0015\t\ty!A\u0003kCZ\f\u00070\u0003\u0003\u0002\u0014\u0005\u001d!!C$vCJ$W\r\u001a\"z\u0003\u00151\u0018\r\\;fC\t\tI\"\u0001\u0003uQ&\u001c\u0018a\u0005:fO&\u001cH/\u001a:Sa\u000e,e\u000e\u001a9pS:$HCBA\u0010\u0003K\tI\u0003E\u00027\u0003CI1!a\t\u001d\u0005MqU\r\u001e;z%B\u001cWI\u001c3q_&tGOU3g\u0011\u0019\t9C\u0004a\u0001\u001d\u0006!a.Y7f\u0011\u0019\tYC\u0004a\u0001A\u0006AQM\u001c3q_&tG/A\thKR\u0014\u0006oY#oIB|\u0017N\u001c;SK\u001a$2\u0001ZA\u0019\u0011\u0019\tYc\u0004a\u0001A\u0006!\"/Z7pm\u0016\u0014\u0006oY#oIB|\u0017N\u001c;SK\u001a$2A_A\u001c\u0011\u0019\tY\u0003\u0005a\u0001A\u0006)RO\u001c:fO&\u001cH/\u001a:Sa\u000e,e\u000e\u001a9pS:$Hc\u0001>\u0002>!1\u0011qE\tA\u00029\u000bAa\u001d;paR\u0019!0a\u0011\t\r\u0005\u0015#\u00031\u0001e\u00039\u0011\boY#oIB|\u0017N\u001c;SK\u001a\f\u0011\u0002]8tiR{\u0017\t\u001c7\u0015\u0007i\fY\u0005C\u0004\u0002NM\u0001\r!a\u0014\u0002\u000f5,7o]1hKB\u0019a'!\u0015\n\u0007\u0005MCD\u0001\u0007J]\n|\u00070T3tg\u0006<W-A\tq_N$(+Z7pi\u0016lUm]:bO\u0016$RA_A-\u0003CBq!!\u0014\u0015\u0001\u0004\tY\u0006E\u00027\u0003;J1!a\u0018\u001d\u00059\u0011V-];fgRlUm]:bO\u0016Dq!a\u0019\u0015\u0001\u0004\t)'\u0001\u0005dC2d'-Y2l!\u0011\t9'!\u001d\u000e\u0005\u0005%$\u0002BA6\u0003[\naa\u00197jK:$(bAA8A\u00059a.\u001a;x_J\\\u0017\u0002BA:\u0003S\u00121C\u00159d%\u0016\u001c\bo\u001c8tK\u000e\u000bG\u000e\u001c2bG.\f\u0001\u0003]8ti2{7-\u00197NKN\u001c\u0018mZ3\u0015\u000bi\fI(a\u001f\t\u000f\u00055S\u00031\u0001\u0002\\!9\u0011QP\u000bA\u0002\u0005}\u0014!\u00019\u0011\r\u0005\u0005\u0015QQAE\u001b\t\t\u0019I\u0003\u0002HS%!\u0011qQAB\u0005\u001d\u0001&o\\7jg\u0016\u00042\u0001KAF\u0013\r\ti)\u000b\u0002\u0004\u0003:L\u0018!\u00059pgR|e.Z,bs6+7o]1hKR\u0019!0a%\t\u000f\u00055c\u00031\u0001\u0002\\\u0005Y\u0001o\\:u\u001b\u0016\u001c8/Y4f)\u001dQ\u0018\u0011TAO\u0003?Ca!a'\u0018\u0001\u0004q\u0015\u0001D3oIB|\u0017N\u001c;OC6,\u0007bBA'/\u0001\u0007\u0011q\n\u0005\b\u0003C;\u0002\u0019AAR\u0003E\u0019\u0017\r\u001c7cC\u000e\\\u0017JZ*u_B\u0004X\r\u001a\t\u0007Q\u0005\u0015\u0016\u0011\u0016>\n\u0007\u0005\u001d\u0016FA\u0005Gk:\u001cG/[8ocA!\u00111VA[\u001d\u0011\ti+!-\u000f\u0007E\u000by+C\u0001+\u0013\r\t\u0019,K\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t9,!/\u0003\u0013\u0015C8-\u001a9uS>t'bAAZSQ\t!0\u0001\tbo\u0006LG\u000fV3s[&t\u0017\r^5p]\u00061a/\u001a:jMf$2!^Ab\u0011\u0019\t9C\u0007a\u0001\u001d\u0002"
)
public class Dispatcher implements Logging {
   private SharedMessageLoop sharedLoop;
   private final NettyRpcEnv nettyEnv;
   private final int numUsableCores;
   private final ConcurrentMap endpoints;
   private final ConcurrentMap endpointRefs;
   private final CountDownLatch shutdownLatch;
   @GuardedBy("this")
   private boolean stopped;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

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

   private ConcurrentMap endpoints() {
      return this.endpoints;
   }

   private ConcurrentMap endpointRefs() {
      return this.endpointRefs;
   }

   private CountDownLatch shutdownLatch() {
      return this.shutdownLatch;
   }

   private SharedMessageLoop sharedLoop$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.sharedLoop = new SharedMessageLoop(this.nettyEnv.conf(), this, this.numUsableCores);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.sharedLoop;
   }

   private SharedMessageLoop sharedLoop() {
      return !this.bitmap$0 ? this.sharedLoop$lzycompute() : this.sharedLoop;
   }

   private boolean stopped() {
      return this.stopped;
   }

   private void stopped_$eq(final boolean x$1) {
      this.stopped = x$1;
   }

   public NettyRpcEndpointRef registerRpcEndpoint(final String name, final RpcEndpoint endpoint) {
      RpcEndpointAddress addr = new RpcEndpointAddress(this.nettyEnv.address(), name);
      NettyRpcEndpointRef endpointRef = new NettyRpcEndpointRef(this.nettyEnv.conf(), addr, this.nettyEnv);
      synchronized(this){}

      try {
         if (this.stopped()) {
            throw new IllegalStateException("RpcEnv has been stopped");
         }

         if (this.endpoints().containsKey(name)) {
            throw new IllegalArgumentException("There is already an RpcEndpoint called " + name);
         }

         this.endpointRefs().put(endpoint, endpointRef);
         ObjectRef messageLoop = ObjectRef.create((Object)null);
         this.liftedTree1$1(messageLoop, endpoint, name);
      } catch (Throwable var8) {
         throw var8;
      }

      return endpointRef;
   }

   public RpcEndpointRef getRpcEndpointRef(final RpcEndpoint endpoint) {
      return (RpcEndpointRef)this.endpointRefs().get(endpoint);
   }

   public void removeRpcEndpointRef(final RpcEndpoint endpoint) {
      this.endpointRefs().remove(endpoint);
   }

   private void unregisterRpcEndpoint(final String name) {
      MessageLoop loop = (MessageLoop)this.endpoints().remove(name);
      if (loop != null) {
         loop.unregister(name);
      }
   }

   public synchronized void stop(final RpcEndpointRef rpcEndpointRef) {
      if (!this.stopped()) {
         this.unregisterRpcEndpoint(rpcEndpointRef.name());
      }
   }

   public void postToAll(final InboxMessage message) {
      for(String name : this.endpoints().keySet()) {
         this.postMessage(name, message, (e) -> {
            $anonfun$postToAll$1(this, message, e);
            return BoxedUnit.UNIT;
         });
      }

   }

   public void postRemoteMessage(final RequestMessage message, final RpcResponseCallback callback) {
      RemoteNettyRpcCallContext rpcCallContext = new RemoteNettyRpcCallContext(this.nettyEnv, callback, message.senderAddress());
      RpcMessage rpcMessage = new RpcMessage(message.senderAddress(), message.content(), rpcCallContext);
      this.postMessage(message.receiver().name(), rpcMessage, (e) -> {
         $anonfun$postRemoteMessage$1(callback, e);
         return BoxedUnit.UNIT;
      });
   }

   public void postLocalMessage(final RequestMessage message, final Promise p) {
      LocalNettyRpcCallContext rpcCallContext = new LocalNettyRpcCallContext(message.senderAddress(), p);
      RpcMessage rpcMessage = new RpcMessage(message.senderAddress(), message.content(), rpcCallContext);
      this.postMessage(message.receiver().name(), rpcMessage, (e) -> {
         $anonfun$postLocalMessage$1(p, e);
         return BoxedUnit.UNIT;
      });
   }

   public void postOneWayMessage(final RequestMessage message) {
      this.postMessage(message.receiver().name(), new OneWayMessage(message.senderAddress(), message.content()), (x0$1) -> {
         $anonfun$postOneWayMessage$1(this, message, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   private void postMessage(final String endpointName, final InboxMessage message, final Function1 callbackIfStopped) {
      synchronized(this){}

      Object var6;
      try {
         MessageLoop loop = (MessageLoop)this.endpoints().get(endpointName);
         Object var10000;
         if (this.stopped()) {
            var10000 = new Some(new RpcEnvStoppedException());
         } else if (loop == null) {
            var10000 = new Some(new SparkException("Could not find " + endpointName + "."));
         } else {
            loop.post(endpointName, message);
            var10000 = .MODULE$;
         }

         var6 = var10000;
      } catch (Throwable var9) {
         throw var9;
      }

      ((Option)var6).foreach(callbackIfStopped);
   }

   public void stop() {
      synchronized(this){}

      try {
         if (this.stopped()) {
            return;
         }

         this.stopped_$eq(true);
      } catch (Throwable var4) {
         throw var4;
      }

      BooleanRef stopSharedLoop = BooleanRef.create(false);
      scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.endpoints()).asScala().foreach((x0$1) -> {
         $anonfun$stop$1(this, stopSharedLoop, x0$1);
         return BoxedUnit.UNIT;
      });
      if (stopSharedLoop.elem) {
         this.sharedLoop().stop();
      }

      this.shutdownLatch().countDown();
   }

   public void awaitTermination() {
      this.shutdownLatch().await();
   }

   public boolean verify(final String name) {
      return this.endpoints().containsKey(name);
   }

   // $FF: synthetic method
   private final MessageLoop liftedTree1$1(final ObjectRef messageLoop$1, final RpcEndpoint endpoint$1, final String name$1) {
      try {
         Object var10001;
         if (endpoint$1 instanceof IsolatedRpcEndpoint var7) {
            var10001 = new DedicatedMessageLoop(name$1, var7, this);
         } else {
            this.sharedLoop().register(name$1, endpoint$1);
            var10001 = this.sharedLoop();
         }

         messageLoop$1.elem = var10001;
         return (MessageLoop)this.endpoints().put(name$1, (MessageLoop)messageLoop$1.elem);
      } catch (Throwable var11) {
         if (var11 != null && scala.util.control.NonFatal..MODULE$.apply(var11)) {
            this.endpointRefs().remove(endpoint$1);
            throw var11;
         } else {
            throw var11;
         }
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$postToAll$1(final Dispatcher $this, final InboxMessage message$1, final Exception e) {
      if (e instanceof RpcEnvStoppedException var5) {
         $this.logDebug((Function0)(() -> "Message " + message$1 + " dropped. " + var5.getMessage()));
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else if (e != null) {
         $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Message ", " dropped. ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, message$1), new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, e.getMessage())})))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(e);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$postRemoteMessage$1(final RpcResponseCallback callback$1, final Exception e) {
      callback$1.onFailure(e);
   }

   // $FF: synthetic method
   public static final void $anonfun$postLocalMessage$1(final Promise p$1, final Exception e) {
      p$1.tryFailure(e);
   }

   // $FF: synthetic method
   public static final void $anonfun$postOneWayMessage$1(final Dispatcher $this, final RequestMessage message$2, final Exception x0$1) {
      if (x0$1 instanceof RpcEnvStoppedException var5) {
         $this.logDebug((Function0)(() -> "Message " + message$2 + " dropped. " + var5.getMessage()));
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (SparkEnv$.MODULE$.get().isStopped()) {
         $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Message ", " dropped due to sparkEnv "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, message$2)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"is stopped. ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, x0$1.getMessage())}))))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw x0$1;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$1(final Dispatcher $this, final BooleanRef stopSharedLoop$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String name = (String)x0$1._1();
         MessageLoop loop = (MessageLoop)x0$1._2();
         $this.unregisterRpcEndpoint(name);
         if (!(loop instanceof SharedMessageLoop)) {
            loop.stop();
            BoxedUnit var7 = BoxedUnit.UNIT;
         } else {
            stopSharedLoop$1.elem = true;
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   public Dispatcher(final NettyRpcEnv nettyEnv, final int numUsableCores) {
      this.nettyEnv = nettyEnv;
      this.numUsableCores = numUsableCores;
      Logging.$init$(this);
      this.endpoints = new ConcurrentHashMap();
      this.endpointRefs = new ConcurrentHashMap();
      this.shutdownLatch = new CountDownLatch(1);
      this.stopped = false;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
