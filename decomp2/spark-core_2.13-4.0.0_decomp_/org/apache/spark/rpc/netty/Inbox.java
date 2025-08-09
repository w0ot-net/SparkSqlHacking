package org.apache.spark.rpc.netty;

import java.lang.invoke.SerializedLambda;
import java.util.LinkedList;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.ThreadSafeRpcEndpoint;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc!\u0002\r\u001a\u0001e\u0019\u0003\u0002\u0003\u0019\u0001\u0005\u000b\u0007I\u0011\u0001\u001a\t\u0011y\u0002!\u0011!Q\u0001\nMB\u0001b\u0010\u0001\u0003\u0006\u0004%\t\u0001\u0011\u0005\t\u000b\u0002\u0011\t\u0011)A\u0005\u0003\")a\t\u0001C\u0001\u000f\"9A\n\u0001b\u0001\n#i\u0005BB-\u0001A\u0003%a\nC\u0004i\u0001\u0001\u0007I\u0011B5\t\u000f5\u0004\u0001\u0019!C\u0005]\"1A\u000f\u0001Q!\n)DqA\u001e\u0001A\u0002\u0013%\u0011\u000eC\u0004x\u0001\u0001\u0007I\u0011\u0002=\t\ri\u0004\u0001\u0015)\u0003k\u0011\u001da\b\u00011A\u0005\nuD\u0011\"a\u0001\u0001\u0001\u0004%I!!\u0002\t\u000f\u0005%\u0001\u0001)Q\u0005}\"9\u0011Q\u0002\u0001\u0005\u0002\u0005=\u0001bBA\u000e\u0001\u0011\u0005\u0011Q\u0004\u0005\b\u0003G\u0001A\u0011AA\u0013\u0011\u0019\t9\u0003\u0001C\u0001S\"9\u0011\u0011\u0006\u0001\u0005\u0012\u0005-\u0002bBA\u0018\u0001\u0011%\u0011\u0011\u0007\u0005\u0007\u0003\u0003\u0002A\u0011A?\u0003\u000b%s'm\u001c=\u000b\u0005iY\u0012!\u00028fiRL(B\u0001\u000f\u001e\u0003\r\u0011\bo\u0019\u0006\u0003=}\tQa\u001d9be.T!\u0001I\u0011\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0013aA8sON\u0019\u0001\u0001\n\u0016\u0011\u0005\u0015BS\"\u0001\u0014\u000b\u0003\u001d\nQa]2bY\u0006L!!\u000b\u0014\u0003\r\u0005s\u0017PU3g!\tYc&D\u0001-\u0015\tiS$\u0001\u0005j]R,'O\\1m\u0013\tyCFA\u0004M_\u001e<\u0017N\\4\u0002\u0019\u0015tG\r]8j]Rt\u0015-\\3\u0004\u0001U\t1\u0007\u0005\u00025w9\u0011Q'\u000f\t\u0003m\u0019j\u0011a\u000e\u0006\u0003qE\na\u0001\u0010:p_Rt\u0014B\u0001\u001e'\u0003\u0019\u0001&/\u001a3fM&\u0011A(\u0010\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005i2\u0013!D3oIB|\u0017N\u001c;OC6,\u0007%\u0001\u0005f]\u0012\u0004x.\u001b8u+\u0005\t\u0005C\u0001\"D\u001b\u0005Y\u0012B\u0001#\u001c\u0005-\u0011\u0006oY#oIB|\u0017N\u001c;\u0002\u0013\u0015tG\r]8j]R\u0004\u0013A\u0002\u001fj]&$h\bF\u0002I\u0015.\u0003\"!\u0013\u0001\u000e\u0003eAQ\u0001M\u0003A\u0002MBQaP\u0003A\u0002\u0005\u000b\u0001\"\\3tg\u0006<Wm]\u000b\u0002\u001dB\u0019q\n\u0016,\u000e\u0003AS!!\u0015*\u0002\tU$\u0018\u000e\u001c\u0006\u0002'\u0006!!.\u0019<b\u0013\t)\u0006K\u0001\u0006MS:\\W\r\u001a'jgR\u0004\"!S,\n\u0005aK\"\u0001D%oE>DX*Z:tC\u001e,\u0017!C7fgN\fw-Z:!Q\u001191,\u001a4\u0011\u0005q\u001bW\"A/\u000b\u0005y{\u0016AC2p]\u000e,(O]3oi*\u0011\u0001-Y\u0001\u000bC:tw\u000e^1uS>t'\"\u00012\u0002\u000b)\fg/\u0019=\n\u0005\u0011l&!C$vCJ$W\r\u001a\"z\u0003\u00151\u0018\r\\;fC\u00059\u0017\u0001\u0002;iSN\fqa\u001d;paB,G-F\u0001k!\t)3.\u0003\u0002mM\t9!i\\8mK\u0006t\u0017aC:u_B\u0004X\rZ0%KF$\"a\u001c:\u0011\u0005\u0015\u0002\u0018BA9'\u0005\u0011)f.\u001b;\t\u000fML\u0011\u0011!a\u0001U\u0006\u0019\u0001\u0010J\u0019\u0002\u0011M$x\u000e\u001d9fI\u0002BCAC.fM\u0006\u0001RM\\1cY\u0016\u001cuN\\2veJ,g\u000e^\u0001\u0015K:\f'\r\\3D_:\u001cWO\u001d:f]R|F%Z9\u0015\u0005=L\bbB:\r\u0003\u0003\u0005\rA[\u0001\u0012K:\f'\r\\3D_:\u001cWO\u001d:f]R\u0004\u0003\u0006B\u0007\\K\u001a\f\u0001C\\;n\u0003\u000e$\u0018N^3UQJ,\u0017\rZ:\u0016\u0003y\u0004\"!J@\n\u0007\u0005\u0005aEA\u0002J]R\fAC\\;n\u0003\u000e$\u0018N^3UQJ,\u0017\rZ:`I\u0015\fHcA8\u0002\b!91oDA\u0001\u0002\u0004q\u0018!\u00058v[\u0006\u001bG/\u001b<f)\"\u0014X-\u00193tA!\"\u0001cW3g\u0003\u001d\u0001(o\\2fgN$2a\\A\t\u0011\u001d\t\u0019\"\u0005a\u0001\u0003+\t!\u0002Z5ta\u0006$8\r[3s!\rI\u0015qC\u0005\u0004\u00033I\"A\u0003#jgB\fGo\u00195fe\u0006!\u0001o\\:u)\ry\u0017q\u0004\u0005\u0007\u0003C\u0011\u0002\u0019\u0001,\u0002\u000f5,7o]1hK\u0006!1\u000f^8q)\u0005y\u0017aB5t\u000b6\u0004H/_\u0001\u0007_:$%o\u001c9\u0015\u0007=\fi\u0003\u0003\u0004\u0002\"U\u0001\rAV\u0001\u000bg\u00064W\r\\=DC2dG\u0003BA\u001a\u0003\u007f!2a\\A\u001b\u0011!\t9D\u0006CA\u0002\u0005e\u0012AB1di&|g\u000e\u0005\u0003&\u0003wy\u0017bAA\u001fM\tAAHY=oC6,g\bC\u0003@-\u0001\u0007\u0011)A\nhKRtU/\\!di&4X\r\u00165sK\u0006$7\u000f"
)
public class Inbox implements Logging {
   private final String endpointName;
   private final RpcEndpoint endpoint;
   @GuardedBy("this")
   private final LinkedList messages;
   @GuardedBy("this")
   private boolean stopped;
   @GuardedBy("this")
   private boolean enableConcurrent;
   @GuardedBy("this")
   private int numActiveThreads;
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

   public String endpointName() {
      return this.endpointName;
   }

   public RpcEndpoint endpoint() {
      return this.endpoint;
   }

   public LinkedList messages() {
      return this.messages;
   }

   private boolean stopped() {
      return this.stopped;
   }

   private void stopped_$eq(final boolean x$1) {
      this.stopped = x$1;
   }

   private boolean enableConcurrent() {
      return this.enableConcurrent;
   }

   private void enableConcurrent_$eq(final boolean x$1) {
      this.enableConcurrent = x$1;
   }

   private int numActiveThreads() {
      return this.numActiveThreads;
   }

   private void numActiveThreads_$eq(final int x$1) {
      this.numActiveThreads = x$1;
   }

   public void process(final Dispatcher dispatcher) {
      ObjectRef message = ObjectRef.create((Object)null);
      synchronized(this){}

      try {
         if (!this.enableConcurrent() && this.numActiveThreads() != 0) {
            return;
         }

         message.elem = (InboxMessage)this.messages().poll();
         if ((InboxMessage)message.elem == null) {
            return;
         }

         this.numActiveThreads_$eq(this.numActiveThreads() + 1);
      } catch (Throwable var10) {
         throw var10;
      }

      while(true) {
         this.safelyCall(this.endpoint(), (JFunction0.mcV.sp)() -> {
            InboxMessage var4 = (InboxMessage)message.elem;
            if (var4 instanceof RpcMessage var5) {
               RpcAddress _sender = var5.senderAddress();
               Object content = var5.content();
               NettyRpcCallContext context = var5.context();

               try {
                  BoxedUnit var39 = (BoxedUnit)this.endpoint().receiveAndReply(context).applyOrElse(content, (msg) -> {
                     InboxMessage var10002 = (InboxMessage)message.elem;
                     throw new SparkException("Unsupported message " + var10002 + " from " + _sender);
                  });
               } catch (Throwable var30) {
                  context.sendFailure(var30);
                  throw var30;
               }

            } else if (var4 instanceof OneWayMessage var10) {
               RpcAddress _sender = var10.senderAddress();
               Object content = var10.content();
               BoxedUnit var38 = (BoxedUnit)this.endpoint().receive().applyOrElse(content, (msg) -> {
                  InboxMessage var10002 = (InboxMessage)message.elem;
                  throw new SparkException("Unsupported message " + var10002 + " from " + _sender);
               });
            } else if (!OnStart$.MODULE$.equals(var4)) {
               if (OnStop$.MODULE$.equals(var4)) {
                  synchronized(this){}

                  int var16;
                  try {
                     var16 = this.numActiveThreads();
                  } catch (Throwable var31) {
                     throw var31;
                  }

                  .MODULE$.assert(var16 == 1, () -> "There should be only a single active thread but found " + var16 + " threads.");
                  dispatcher.removeRpcEndpointRef(this.endpoint());
                  this.endpoint().onStop();
                  .MODULE$.assert(this.isEmpty(), () -> "OnStop should be the last message");
                  BoxedUnit var37 = BoxedUnit.UNIT;
               } else if (var4 instanceof RemoteProcessConnected) {
                  RemoteProcessConnected var17 = (RemoteProcessConnected)var4;
                  RpcAddress remoteAddress = var17.remoteAddress();
                  this.endpoint().onConnected(remoteAddress);
                  BoxedUnit var36 = BoxedUnit.UNIT;
               } else if (var4 instanceof RemoteProcessDisconnected) {
                  RemoteProcessDisconnected var19 = (RemoteProcessDisconnected)var4;
                  RpcAddress remoteAddress = var19.remoteAddress();
                  this.endpoint().onDisconnected(remoteAddress);
                  BoxedUnit var35 = BoxedUnit.UNIT;
               } else if (var4 instanceof RemoteProcessConnectionError) {
                  RemoteProcessConnectionError var21 = (RemoteProcessConnectionError)var4;
                  Throwable cause = var21.cause();
                  RpcAddress remoteAddress = var21.remoteAddress();
                  this.endpoint().onNetworkError(cause, remoteAddress);
                  BoxedUnit var34 = BoxedUnit.UNIT;
               } else {
                  throw new MatchError(var4);
               }
            } else {
               this.endpoint().onStart();
               if (!(this.endpoint() instanceof ThreadSafeRpcEndpoint)) {
                  synchronized(this){}

                  try {
                     if (!this.stopped()) {
                        this.enableConcurrent_$eq(true);
                     }
                  } catch (Throwable var32) {
                     throw var32;
                  }

                  BoxedUnit var33 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }
            }
         });
         synchronized(this){}

         try {
            if (!this.enableConcurrent() && this.numActiveThreads() != 1) {
               this.numActiveThreads_$eq(this.numActiveThreads() - 1);
               break;
            }

            message.elem = (InboxMessage)this.messages().poll();
            if ((InboxMessage)message.elem == null) {
               this.numActiveThreads_$eq(this.numActiveThreads() - 1);
               break;
            }
         } catch (Throwable var9) {
            throw var9;
         }
      }

   }

   public void post(final InboxMessage message) {
      synchronized(this){}

      try {
         if (this.stopped()) {
            this.onDrop(message);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            this.messages().add(message);
            BoxesRunTime.boxToBoolean(false);
         }
      } catch (Throwable var4) {
         throw var4;
      }

   }

   public void stop() {
      synchronized(this){}

      try {
         if (!this.stopped()) {
            this.enableConcurrent_$eq(false);
            this.stopped_$eq(true);
            BoxesRunTime.boxToBoolean(this.messages().add(OnStop$.MODULE$));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public synchronized boolean isEmpty() {
      return this.messages().isEmpty();
   }

   public void onDrop(final InboxMessage message) {
      this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Drop ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, message)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"because endpoint ", " is stopped"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.END_POINT..MODULE$, this.endpointName())}))))));
   }

   private void safelyCall(final RpcEndpoint endpoint, final Function0 action) {
      try {
         action.apply$mcV$sp();
      } catch (Throwable var14) {
         if (var14 != null) {
            Throwable var7 = var14;
            if (scala.util.control.NonFatal..MODULE$.apply(var14)) {
               try {
                  endpoint.onError(var7);
                  BoxedUnit var19 = BoxedUnit.UNIT;
               } catch (Throwable var13) {
                  if (var13 != null && scala.util.control.NonFatal..MODULE$.apply(var13)) {
                     if (this.stopped()) {
                        this.logDebug((Function0)(() -> "Ignoring error"), var13);
                        BoxedUnit var16 = BoxedUnit.UNIT;
                     } else {
                        this.logError((Function0)(() -> "Ignoring error"), var13);
                        BoxedUnit var17 = BoxedUnit.UNIT;
                     }
                  } else {
                     if (var13 == null) {
                        throw var13;
                     }

                     this.dealWithFatalError$1(var13);
                     BoxedUnit var15 = BoxedUnit.UNIT;
                  }

                  BoxedUnit var18 = BoxedUnit.UNIT;
               }

               return;
            }
         }

         if (var14 == null) {
            throw var14;
         }

         this.dealWithFatalError$1(var14);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

   }

   public synchronized int getNumActiveThreads() {
      return this.numActiveThreads();
   }

   private final void dealWithFatalError$1(final Throwable fatal) {
      synchronized(this){}

      try {
         .MODULE$.assert(this.numActiveThreads() > 0, () -> "The number of active threads should be positive.");
         this.numActiveThreads_$eq(this.numActiveThreads() - 1);
      } catch (Throwable var4) {
         throw var4;
      }

      this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"An error happened while processing message in the inbox for"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.END_POINT..MODULE$, this.endpointName())}))))), fatal);
      throw fatal;
   }

   public Inbox(final String endpointName, final RpcEndpoint endpoint) {
      this.endpointName = endpointName;
      this.endpoint = endpoint;
      Logging.$init$(this);
      this.messages = new LinkedList();
      this.stopped = false;
      this.enableConcurrent = false;
      this.numActiveThreads = 0;
      synchronized(this){}

      try {
         this.messages().add(OnStart$.MODULE$);
      } catch (Throwable var5) {
         throw var5;
      }

   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
