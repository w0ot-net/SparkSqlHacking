package org.apache.spark;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.ThreadSafeRpcEndpoint;
import org.apache.spark.scheduler.LiveListenerBus;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.scheduler.SparkListenerStageCompleted;
import org.apache.spark.scheduler.StageInfo;
import org.apache.spark.util.ThreadUtils$;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.PartialFunction;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashSet;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tUa!B\u0015+\u0001)\u0002\u0004\u0002C\"\u0001\u0005\u0003\u0005\u000b\u0011B#\t\u0011!\u0003!\u0011!Q\u0001\n%C\u0001b\u0014\u0001\u0003\u0006\u0004%\t\u0005\u0015\u0005\t)\u0002\u0011\t\u0011)A\u0005#\")Q\u000b\u0001C\u0001-\"AA\f\u0001EC\u0002\u0013%Q\fC\u0004i\u0001\t\u0007I\u0011B5\t\rY\u0004\u0001\u0015!\u0003k\u0011\u001dq\bA1A\u0005\n}D\u0001\"a\u0002\u0001A\u0003%\u0011\u0011\u0001\u0005\n\u0003\u0013\u0001!\u0019!C\u0005\u0003\u0017A\u0001\"a6\u0001A\u0003%\u0011Q\u0002\u0005\b\u00033\u0004A\u0011IAa\u0011\u001d\tY\u000e\u0001C!\u0003\u00034a!!\b\u0001\t\u0005}\u0001BCA\u0011\u001f\t\u0015\r\u0011\"\u0001\u0002$!Q\u0011QE\b\u0003\u0002\u0003\u0006I!a\u0005\t\u0015\u0005\u001drB!b\u0001\n\u0003\tI\u0003\u0003\u0006\u00022=\u0011\t\u0011)A\u0005\u0003WAa!V\b\u0005\u0002\u0005M\u0002\"CA\u001d\u001f\u0001\u0007I\u0011BA\u0015\u0011%\tYd\u0004a\u0001\n\u0013\ti\u0004\u0003\u0005\u0002J=\u0001\u000b\u0015BA\u0016\u0011%\tYe\u0004b\u0001\n\u0013\ti\u0005\u0003\u0005\u0002f=\u0001\u000b\u0011BA(\u0011%\t9g\u0004b\u0001\n\u0013\tI\u0007\u0003\u0005\u0002\b>\u0001\u000b\u0011BA6\u0011%\tIi\u0004b\u0001\n\u0013\tY\t\u0003\u0005\u0002$>\u0001\u000b\u0011BAG\u0011%\t)k\u0004a\u0001\n\u0013\t9\u000bC\u0005\u00020>\u0001\r\u0011\"\u0003\u00022\"A\u0011QW\b!B\u0013\tI\u000bC\u0004\u00028>!I!!/\t\u000f\u0005}v\u0002\"\u0003\u0002B\"9\u00111Y\b\u0005\u0002\u0005\u0015\u0007bBAk\u001f\u0011\u0005\u0011\u0011\u0019\u0005\b\u0003;\u0004A\u0011BAp\u0011\u001d\t\u0019\u000f\u0001C!\u0003KD\u0011\"!=\u0001\u0005\u0004%I!a=\t\u0011\u0005m\b\u0001)A\u0005\u0003k\u0014!CQ1se&,'oQ8pe\u0012Lg.\u0019;pe*\u00111\u0006L\u0001\u0006gB\f'o\u001b\u0006\u0003[9\na!\u00199bG\",'\"A\u0018\u0002\u0007=\u0014xm\u0005\u0003\u0001c]j\u0004C\u0001\u001a6\u001b\u0005\u0019$\"\u0001\u001b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u001a$AB!osJ+g\r\u0005\u00029w5\t\u0011H\u0003\u0002;U\u0005\u0019!\u000f]2\n\u0005qJ$!\u0006+ie\u0016\fGmU1gKJ\u00038-\u00128ea>Lg\u000e\u001e\t\u0003}\u0005k\u0011a\u0010\u0006\u0003\u0001*\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003\u0005~\u0012q\u0001T8hO&tw-A\u0007uS6,w.\u001e;J]N+7m]\u0002\u0001!\t\u0011d)\u0003\u0002Hg\t!Aj\u001c8h\u0003-a\u0017n\u001d;f]\u0016\u0014()^:\u0011\u0005)kU\"A&\u000b\u00051S\u0013!C:dQ\u0016$W\u000f\\3s\u0013\tq5JA\bMSZ,G*[:uK:,'OQ;t\u0003\u0019\u0011\boY#omV\t\u0011\u000b\u0005\u00029%&\u00111+\u000f\u0002\u0007%B\u001cWI\u001c<\u0002\u000fI\u00048-\u00128wA\u00051A(\u001b8jiz\"BaV-[7B\u0011\u0001\fA\u0007\u0002U!)1)\u0002a\u0001\u000b\")\u0001*\u0002a\u0001\u0013\")q*\u0002a\u0001#\u0006)A/[7feV\ta\f\u0005\u0002`M6\t\u0001M\u0003\u0002bE\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005\r$\u0017\u0001B;uS2T\u0011!Z\u0001\u0005U\u00064\u0018-\u0003\u0002hA\nA2k\u00195fIVdW\rZ#yK\u000e,Ho\u001c:TKJ4\u0018nY3\u0002\u0019QLW.\u001a:GkR,(/Z:\u0016\u0003)\u00042a\u001b7o\u001b\u0005\u0011\u0017BA7c\u0005\u0011a\u0015n\u001d;1\u0005=$\bcA0qe&\u0011\u0011\u000f\u0019\u0002\u0010'\u000eDW\rZ;mK\u00124U\u000f^;sKB\u00111\u000f\u001e\u0007\u0001\t%)\b\"!A\u0001\u0002\u000b\u0005qOA\u0002`IE\nQ\u0002^5nKJ4U\u000f^;sKN\u0004\u0013C\u0001=|!\t\u0011\u00140\u0003\u0002{g\t9aj\u001c;iS:<\u0007C\u0001\u001a}\u0013\ti8GA\u0002B]f\f\u0001\u0002\\5ti\u0016tWM]\u000b\u0003\u0003\u0003\u00012ASA\u0002\u0013\r\t)a\u0013\u0002\u000e'B\f'o\u001b'jgR,g.\u001a:\u0002\u00131L7\u000f^3oKJ\u0004\u0013AB:uCR,7/\u0006\u0002\u0002\u000eA9q,a\u0004\u0002\u0014\u0005e\u0011bAA\tA\n\t2i\u001c8dkJ\u0014XM\u001c;ICNDW*\u00199\u0011\u0007a\u000b)\"C\u0002\u0002\u0018)\u0012\u0001cQ8oi\u0016DHOQ1se&,'/\u00133\u0011\u0007\u0005mq\"D\u0001\u0001\u0005M\u0019uN\u001c;fqR\u0014\u0015M\u001d:jKJ\u001cF/\u0019;f'\ty\u0011'A\u0005cCJ\u0014\u0018.\u001a:JIV\u0011\u00111C\u0001\u000bE\u0006\u0014(/[3s\u0013\u0012\u0004\u0013\u0001\u00038v[R\u000b7o[:\u0016\u0005\u0005-\u0002c\u0001\u001a\u0002.%\u0019\u0011qF\u001a\u0003\u0007%sG/A\u0005ok6$\u0016m]6tAQ1\u0011\u0011DA\u001b\u0003oAq!!\t\u0015\u0001\u0004\t\u0019\u0002C\u0004\u0002(Q\u0001\r!a\u000b\u0002\u0019\t\f'O]5fe\u0016\u0003xn\u00195\u0002!\t\f'O]5fe\u0016\u0003xn\u00195`I\u0015\fH\u0003BA \u0003\u000b\u00022AMA!\u0013\r\t\u0019e\r\u0002\u0005+:LG\u000fC\u0005\u0002HY\t\t\u00111\u0001\u0002,\u0005\u0019\u0001\u0010J\u0019\u0002\u001b\t\f'O]5fe\u0016\u0003xn\u00195!\u0003)\u0011X-];fgR,'o]\u000b\u0003\u0003\u001f\u0002b!!\u0015\u0002\\\u0005}SBAA*\u0015\u0011\t)&a\u0016\u0002\u000f5,H/\u00192mK*\u0019\u0011\u0011L\u001a\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002^\u0005M#aC!se\u0006L()\u001e4gKJ\u00042\u0001OA1\u0013\r\t\u0019'\u000f\u0002\u000f%B\u001c7)\u00197m\u0007>tG/\u001a=u\u0003-\u0011X-];fgR,'o\u001d\u0011\u0002\u00115,7o]1hKN,\"!a\u001b\u0011\u000bI\ni'!\u001d\n\u0007\u0005=4GA\u0003BeJ\f\u0017\u0010\u0005\u0003\u0002t\u0005\u0005e\u0002BA;\u0003{\u00022!a\u001e4\u001b\t\tIHC\u0002\u0002|\u0011\u000ba\u0001\u0010:p_Rt\u0014bAA@g\u00051\u0001K]3eK\u001aLA!a!\u0002\u0006\n11\u000b\u001e:j]\u001eT1!a 4\u0003%iWm]:bO\u0016\u001c\b%\u0001\bsKF,Xm\u001d;NKRDw\u000eZ:\u0016\u0005\u00055\u0005CBA)\u0003\u001f\u000b\u0019*\u0003\u0003\u0002\u0012\u0006M#a\u0002%bg\"\u001cV\r\u001e\t\u0005\u0003+\u000bYJD\u0002Y\u0003/K1!!'+\u00035\u0011V-];fgRlU\r\u001e5pI&!\u0011QTAP\u0005\u00151\u0016\r\\;f\u0013\r\t\tk\r\u0002\f\u000b:,X.\u001a:bi&|g.A\bsKF,Xm\u001d;NKRDw\u000eZ:!\u0003%!\u0018.\\3s)\u0006\u001c8.\u0006\u0002\u0002*B\u00191.a+\n\u0007\u00055&MA\u0005US6,'\u000fV1tW\u0006iA/[7feR\u000b7o[0%KF$B!a\u0010\u00024\"I\u0011qI\u0010\u0002\u0002\u0003\u0007\u0011\u0011V\u0001\u000bi&lWM\u001d+bg.\u0004\u0013!D5oSR$\u0016.\\3s)\u0006\u001c8\u000e\u0006\u0003\u0002@\u0005m\u0006bBA_C\u0001\u0007\u0011\u0011D\u0001\u0006gR\fG/Z\u0001\u0010G\u0006t7-\u001a7US6,'\u000fV1tWR\u0011\u0011qH\u0001\u000eQ\u0006tG\r\\3SKF,Xm\u001d;\u0015\r\u0005}\u0012qYAf\u0011\u001d\tIm\ta\u0001\u0003?\n\u0011B]3rk\u0016\u001cH/\u001a:\t\u000f\u000557\u00051\u0001\u0002P\u00069!/Z9vKN$\bc\u0001-\u0002R&\u0019\u00111\u001b\u0016\u0003\u001bI+\u0017/^3tiR{7+\u001f8d\u0003\u0015\u0019G.Z1s\u0003\u001d\u0019H/\u0019;fg\u0002\nqa\u001c8Ti\u0006\u0014H/\u0001\u0004p]N#x\u000e]\u0001\u0014G2,\u0017M\\;q\u0005\u0006\u0014(/[3s'R\fw-\u001a\u000b\u0005\u0003\u007f\t\t\u000fC\u0004\u0002\"\u0015\u0002\r!a\u0005\u0002\u001fI,7-Z5wK\u0006sGMU3qYf$B!a:\u0002nB1!'!;|\u0003\u007fI1!a;4\u0005=\u0001\u0016M\u001d;jC24UO\\2uS>t\u0007bBAxM\u0001\u0007\u0011qL\u0001\bG>tG/\u001a=u\u0003I\u0019G.Z1s'R\fG/Z\"p]N,X.\u001a:\u0016\u0005\u0005U(CBA|\u0003{\u0014IA\u0002\u0004\u0002z\"\u0002\u0011Q\u001f\u0002\ryI,g-\u001b8f[\u0016tGOP\u0001\u0014G2,\u0017M]*uCR,7i\u001c8tk6,'\u000f\t\t\u0005\u0003\u007f\u0014)!\u0004\u0002\u0003\u0002)\u0019!1\u00013\u0002\t1\fgnZ\u0005\u0005\u0005\u000f\u0011\tA\u0001\u0004PE*,7\r\u001e\t\u0007\u0005\u0017\u0011\t\"!\u0007\u000e\u0005\t5!b\u0001B\bE\u0006Aa-\u001e8di&|g.\u0003\u0003\u0003\u0014\t5!\u0001C\"p]N,X.\u001a:"
)
public class BarrierCoordinator implements ThreadSafeRpcEndpoint, Logging {
   private ScheduledExecutorService org$apache$spark$BarrierCoordinator$$timer;
   public final long org$apache$spark$BarrierCoordinator$$timeoutInSecs;
   private final LiveListenerBus listenerBus;
   private final RpcEnv rpcEnv;
   private final List org$apache$spark$BarrierCoordinator$$timerFutures;
   private final SparkListener listener;
   private final ConcurrentHashMap org$apache$spark$BarrierCoordinator$$states;
   private final Consumer clearStateConsumer;
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

   private ScheduledExecutorService timer$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.org$apache$spark$BarrierCoordinator$$timer = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("BarrierCoordinator barrier epoch increment timer");
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.org$apache$spark$BarrierCoordinator$$timer;
   }

   public ScheduledExecutorService org$apache$spark$BarrierCoordinator$$timer() {
      return !this.bitmap$0 ? this.timer$lzycompute() : this.org$apache$spark$BarrierCoordinator$$timer;
   }

   public List org$apache$spark$BarrierCoordinator$$timerFutures() {
      return this.org$apache$spark$BarrierCoordinator$$timerFutures;
   }

   private SparkListener listener() {
      return this.listener;
   }

   public ConcurrentHashMap org$apache$spark$BarrierCoordinator$$states() {
      return this.org$apache$spark$BarrierCoordinator$$states;
   }

   public void onStart() {
      RpcEndpoint.onStart$(this);
      this.listenerBus.addToStatusQueue(this.listener());
   }

   public void onStop() {
      try {
         this.org$apache$spark$BarrierCoordinator$$states().forEachValue(1L, this.clearStateConsumer());
         this.org$apache$spark$BarrierCoordinator$$states().clear();
         this.listenerBus.removeListener(this.listener());
      } finally {
         .MODULE$.ListHasAsScala(this.org$apache$spark$BarrierCoordinator$$timerFutures()).asScala().foreach((x$1) -> BoxesRunTime.boxToBoolean($anonfun$onStop$1(x$1)));
         this.org$apache$spark$BarrierCoordinator$$timerFutures().clear();
         ThreadUtils$.MODULE$.shutdown(this.org$apache$spark$BarrierCoordinator$$timer(), ThreadUtils$.MODULE$.shutdown$default$2());
         RpcEndpoint.onStop$(this);
      }

   }

   public void org$apache$spark$BarrierCoordinator$$cleanupBarrierStage(final ContextBarrierId barrierId) {
      ContextBarrierState barrierState = (ContextBarrierState)this.org$apache$spark$BarrierCoordinator$$states().remove(barrierId);
      if (barrierState != null) {
         barrierState.clear();
      }
   }

   public PartialFunction receiveAndReply(final RpcCallContext context) {
      return new Serializable(context) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final BarrierCoordinator $outer;
         private final RpcCallContext context$1;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (x1 instanceof RequestToSync var5) {
               int numTasks = var5.numTasks();
               int stageId = var5.stageId();
               int stageAttemptId = var5.stageAttemptId();
               ContextBarrierId barrierId = new ContextBarrierId(stageId, stageAttemptId);
               this.$outer.org$apache$spark$BarrierCoordinator$$states().computeIfAbsent(barrierId, (key) -> this.$outer.new ContextBarrierState(key, numTasks));
               ContextBarrierState barrierState = (ContextBarrierState)this.$outer.org$apache$spark$BarrierCoordinator$$states().get(barrierId);
               barrierState.handleRequest(this.context$1, var5);
               return BoxedUnit.UNIT;
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            return x1 instanceof RequestToSync;
         }

         public {
            if (BarrierCoordinator.this == null) {
               throw null;
            } else {
               this.$outer = BarrierCoordinator.this;
               this.context$1 = context$1;
            }
         }
      };
   }

   private Consumer clearStateConsumer() {
      return this.clearStateConsumer;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onStop$1(final ScheduledFuture x$1) {
      return x$1.cancel(false);
   }

   public BarrierCoordinator(final long timeoutInSecs, final LiveListenerBus listenerBus, final RpcEnv rpcEnv) {
      this.org$apache$spark$BarrierCoordinator$$timeoutInSecs = timeoutInSecs;
      this.listenerBus = listenerBus;
      this.rpcEnv = rpcEnv;
      RpcEndpoint.$init$(this);
      Logging.$init$(this);
      this.org$apache$spark$BarrierCoordinator$$timerFutures = Collections.synchronizedList(new ArrayList());
      this.listener = new SparkListener() {
         // $FF: synthetic field
         private final BarrierCoordinator $outer;

         public void onStageCompleted(final SparkListenerStageCompleted stageCompleted) {
            StageInfo stageInfo = stageCompleted.stageInfo();
            ContextBarrierId barrierId = new ContextBarrierId(stageInfo.stageId(), stageInfo.attemptNumber());
            this.$outer.org$apache$spark$BarrierCoordinator$$cleanupBarrierStage(barrierId);
         }

         public {
            if (BarrierCoordinator.this == null) {
               throw null;
            } else {
               this.$outer = BarrierCoordinator.this;
            }
         }
      };
      this.org$apache$spark$BarrierCoordinator$$states = new ConcurrentHashMap();
      this.clearStateConsumer = new Consumer() {
         public Consumer andThen(final Consumer x$1) {
            return super.andThen(x$1);
         }

         public void accept(final ContextBarrierState state) {
            state.clear();
         }
      };
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   private class ContextBarrierState {
      private final ContextBarrierId barrierId;
      private final int numTasks;
      private int org$apache$spark$BarrierCoordinator$ContextBarrierState$$barrierEpoch;
      private final ArrayBuffer org$apache$spark$BarrierCoordinator$ContextBarrierState$$requesters;
      private final String[] messages;
      private final HashSet requestMethods;
      private TimerTask timerTask;
      // $FF: synthetic field
      public final BarrierCoordinator $outer;

      public ContextBarrierId barrierId() {
         return this.barrierId;
      }

      public int numTasks() {
         return this.numTasks;
      }

      public int org$apache$spark$BarrierCoordinator$ContextBarrierState$$barrierEpoch() {
         return this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$barrierEpoch;
      }

      private void barrierEpoch_$eq(final int x$1) {
         this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$barrierEpoch = x$1;
      }

      public ArrayBuffer org$apache$spark$BarrierCoordinator$ContextBarrierState$$requesters() {
         return this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$requesters;
      }

      private String[] messages() {
         return this.messages;
      }

      private HashSet requestMethods() {
         return this.requestMethods;
      }

      private TimerTask timerTask() {
         return this.timerTask;
      }

      private void timerTask_$eq(final TimerTask x$1) {
         this.timerTask = x$1;
      }

      private void initTimerTask(final ContextBarrierState state) {
         this.timerTask_$eq(new TimerTask(state) {
            // $FF: synthetic field
            private final ContextBarrierState $outer;
            private final ContextBarrierState state$1;

            public void run() {
               synchronized(this.state$1){}

               try {
                  this.$outer.org$apache$spark$BarrierCoordinator$ContextBarrierState$$requesters().foreach((x$2) -> {
                     $anonfun$run$1(this, x$2);
                     return BoxedUnit.UNIT;
                  });
                  this.$outer.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().org$apache$spark$BarrierCoordinator$$cleanupBarrierStage(this.$outer.barrierId());
               } catch (Throwable var3) {
                  throw var3;
               }

            }

            // $FF: synthetic method
            public static final void $anonfun$run$1(final Object $this, final RpcCallContext x$2) {
               int var10003 = $this.$outer.org$apache$spark$BarrierCoordinator$ContextBarrierState$$barrierEpoch();
               x$2.sendFailure(new SparkException("The coordinator didn't get all barrier sync requests for barrier epoch " + var10003 + " from " + $this.$outer.barrierId() + " within " + $this.$outer.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().org$apache$spark$BarrierCoordinator$$timeoutInSecs + " second(s)."));
            }

            public {
               if (ContextBarrierState.this == null) {
                  throw null;
               } else {
                  this.$outer = ContextBarrierState.this;
                  this.state$1 = state$1;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         });
      }

      private void cancelTimerTask() {
         .MODULE$.ListHasAsScala(this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().org$apache$spark$BarrierCoordinator$$timerFutures()).asScala().foreach((x$3) -> BoxesRunTime.boxToBoolean($anonfun$cancelTimerTask$1(x$3)));
         this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().org$apache$spark$BarrierCoordinator$$timerFutures().clear();
      }

      public synchronized void handleRequest(final RpcCallContext requester, final RequestToSync request) {
         long taskId = request.taskAttemptId();
         int epoch = request.barrierEpoch();
         Enumeration.Value curReqMethod = request.requestMethod();
         this.requestMethods().add(curReqMethod);
         if (this.requestMethods().size() > 1) {
            ContextBarrierId var10002 = this.barrierId();
            SparkException error = new SparkException("Different barrier sync types found for the sync " + var10002 + ": " + this.requestMethods().mkString(", ") + ". Please use the same barrier sync type within a single sync.");
            ((IterableOnceOps)this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$requesters().$colon$plus(requester)).foreach((x$4) -> {
               $anonfun$handleRequest$1(error, x$4);
               return BoxedUnit.UNIT;
            });
            this.clear();
         } else {
            scala.Predef..MODULE$.require(request.numTasks() == this.numTasks(), () -> {
               ContextBarrierId var10000 = this.barrierId();
               return "Number of tasks of " + var10000 + " is " + request.numTasks() + " from Task " + taskId + ", previously it was " + this.numTasks() + ".";
            });
            this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Current barrier epoch for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BARRIER_ID..MODULE$, this.barrierId())}))).$plus(this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" is ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BARRIER_EPOCH..MODULE$, BoxesRunTime.boxToInteger(this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$barrierEpoch()))}))))));
            if (epoch != this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$barrierEpoch()) {
               ContextBarrierId var10003 = this.barrierId();
               requester.sendFailure(new SparkException("The request to sync of " + var10003 + " with barrier epoch " + this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$barrierEpoch() + " has already finished. Maybe task " + taskId + " is not properly killed."));
            } else {
               if (this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$requesters().isEmpty()) {
                  this.initTimerTask(this);
                  ScheduledFuture timerFuture = this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().org$apache$spark$BarrierCoordinator$$timer().schedule(this.timerTask(), this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().org$apache$spark$BarrierCoordinator$$timeoutInSecs, TimeUnit.SECONDS);
                  BoxesRunTime.boxToBoolean(this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().org$apache$spark$BarrierCoordinator$$timerFutures().add(timerFuture));
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$requesters().$plus$eq(requester);
               this.messages()[request.partitionId()] = request.message();
               this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Barrier sync epoch ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BARRIER_EPOCH..MODULE$, BoxesRunTime.boxToInteger(this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$barrierEpoch()))}))).$plus(this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" from ", " received update from Task"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BARRIER_ID..MODULE$, this.barrierId())})))).$plus(this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ", current progress:"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong(taskId))})))).$plus(this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", "/", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REQUESTER_SIZE..MODULE$, BoxesRunTime.boxToInteger(this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$requesters().size())), new MDC(org.apache.spark.internal.LogKeys.NUM_REQUEST_SYNC_TASK..MODULE$, BoxesRunTime.boxToInteger(this.numTasks()))}))))));
               if (this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$requesters().size() == this.numTasks()) {
                  this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$requesters().foreach((x$5) -> {
                     $anonfun$handleRequest$5(this, x$5);
                     return BoxedUnit.UNIT;
                  });
                  this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Barrier sync epoch ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BARRIER_EPOCH..MODULE$, BoxesRunTime.boxToInteger(this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$barrierEpoch()))}))).$plus(this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" from ", " received all updates from"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BARRIER_ID..MODULE$, this.barrierId())})))).$plus(this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" tasks, finished successfully."})))).log(scala.collection.immutable.Nil..MODULE$))));
                  this.barrierEpoch_$eq(this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$barrierEpoch() + 1);
                  this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$requesters().clear();
                  this.requestMethods().clear();
                  this.cancelTimerTask();
               }
            }
         }
      }

      public synchronized void clear() {
         this.barrierEpoch_$eq(-1);
         this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$requesters().clear();
         this.cancelTimerTask();
      }

      // $FF: synthetic method
      public BarrierCoordinator org$apache$spark$BarrierCoordinator$ContextBarrierState$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$cancelTimerTask$1(final ScheduledFuture x$3) {
         return x$3.cancel(false);
      }

      // $FF: synthetic method
      public static final void $anonfun$handleRequest$1(final SparkException error$1, final RpcCallContext x$4) {
         x$4.sendFailure(error$1);
      }

      // $FF: synthetic method
      public static final void $anonfun$handleRequest$5(final ContextBarrierState $this, final RpcCallContext x$5) {
         x$5.reply($this.messages().clone());
      }

      public ContextBarrierState(final ContextBarrierId barrierId, final int numTasks) {
         this.barrierId = barrierId;
         this.numTasks = numTasks;
         if (BarrierCoordinator.this == null) {
            throw null;
         } else {
            this.$outer = BarrierCoordinator.this;
            super();
            this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$barrierEpoch = 0;
            this.org$apache$spark$BarrierCoordinator$ContextBarrierState$$requesters = new ArrayBuffer(numTasks);
            this.messages = (String[])scala.Array..MODULE$.ofDim(numTasks, scala.reflect.ClassTag..MODULE$.apply(String.class));
            this.requestMethods = new HashSet();
            this.timerTask = null;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
