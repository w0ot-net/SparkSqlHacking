package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.api.model.Pod;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.concurrent.GuardedBy;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.Clock;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015a!B\u0013'\u00011\u0012\u0004\u0002C\"\u0001\u0005\u0003\u0005\u000b\u0011B#\t\u0011=\u0003!\u0011!Q\u0001\nAC\u0001\"\u0016\u0001\u0003\u0002\u0003\u0006IA\u0016\u0005\u00065\u0002!\ta\u0017\u0005\bA\u0002\u0011\r\u0011\"\u0003b\u0011\u0019A\u0007\u0001)A\u0005E\"9\u0011\u000e\u0001b\u0001\n\u0013Q\u0007bBA'\u0001\u0001\u0006Ia\u001b\u0005\n\u0003\u001f\u0002!\u0019!C\u0005\u0003#B\u0001\"!\u001a\u0001A\u0003%\u00111\u000b\u0005\n\u0003k\u0002\u0001\u0019!C\u0005\u0003oB\u0011\"!\u001f\u0001\u0001\u0004%I!a\u001f\t\u0011\u0005\u0005\u0005\u0001)Q\u0005\u0003\u000bAq!a'\u0001\t\u0003\ni\nC\u0004\u0002.\u0002!\t%a\u0012\t\u000f\u0005=\u0006\u0001\"\u0011\u0002H!9\u0011\u0011\u0017\u0001\u0005B\u0005M\u0006bBAk\u0001\u0011\u0005\u0013q\u001b\u0005\b\u0003?\u0004A\u0011BA$\r\u0011\u0001\b\u0001B9\t\u0011I$\"\u0011!Q\u0001\nMDaA\u0017\u000b\u0005\u0002\u0005E\u0001\"CA\u000b)\t\u0007I\u0011BA\f\u0011!\ty\u0002\u0006Q\u0001\n\u0005e\u0001\"CA\u0011)\t\u0007I\u0011BA\u0012\u0011!\t\t\u0004\u0006Q\u0001\n\u0005\u0015\u0002\"CA\u001a)\t\u0007I\u0011BA\u001b\u0011!\t\u0019\u0005\u0006Q\u0001\n\u0005]\u0002bBA#)\u0011\u0005\u0011q\t\u0005\b\u0003\u0013\"B\u0011AA$\u0011\u001d\tY\u0005\u0006C\u0005\u0003\u000f:!\"!9'\u0003\u0003E\t\u0001LAr\r%)c%!A\t\u00021\n)\u000f\u0003\u0004[C\u0011\u0005\u0011q\u001d\u0005\n\u0003S\f\u0013\u0013!C\u0001\u0003WD\u0011\"a@\"#\u0003%\tA!\u0001\u0003=\u0015CXmY;u_J\u0004v\u000eZ:T]\u0006\u00048\u000f[8ugN#xN]3J[Bd'BA\u0014)\u0003\rY\u0007h\u001d\u0006\u0003S)\nqa\u00197vgR,'O\u0003\u0002,Y\u0005I1o\u00195fIVdWM\u001d\u0006\u0003[9\nQa\u001d9be.T!a\f\u0019\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0014aA8sON!\u0001aM\u001d>!\t!t'D\u00016\u0015\u00051\u0014!B:dC2\f\u0017B\u0001\u001d6\u0005\u0019\te.\u001f*fMB\u0011!hO\u0007\u0002M%\u0011AH\n\u0002\u001b\u000bb,7-\u001e;peB{Gm]*oCB\u001c\bn\u001c;t'R|'/\u001a\t\u0003}\u0005k\u0011a\u0010\u0006\u0003\u00012\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003\u0005~\u0012q\u0001T8hO&tw-A\ntk\n\u001c8M]5cKJ\u001cX\t_3dkR|'o\u0001\u0001\u0011\u0005\u0019kU\"A$\u000b\u0005!K\u0015AC2p]\u000e,(O]3oi*\u0011!jS\u0001\u0005kRLGNC\u0001M\u0003\u0011Q\u0017M^1\n\u00059;%\u0001G*dQ\u0016$W\u000f\\3e\u000bb,7-\u001e;peN+'O^5dK\u0006)1\r\\8dWB\u0011\u0011kU\u0007\u0002%*\u0011!\nL\u0005\u0003)J\u0013Qa\u00117pG.\fAaY8oMB\u0011q\u000bW\u0007\u0002Y%\u0011\u0011\f\f\u0002\n'B\f'o[\"p]\u001a\fa\u0001P5oSRtD\u0003\u0002/^=~\u0003\"A\u000f\u0001\t\u000b\r#\u0001\u0019A#\t\u000f=#\u0001\u0013!a\u0001!\"9Q\u000b\u0002I\u0001\u0002\u00041\u0016!D*O\u0003B\u001b\u0006j\u0014+`\u0019>\u001b5*F\u0001c!\t\u0019g-D\u0001e\u0015\t)7*\u0001\u0003mC:<\u0017BA4e\u0005\u0019y%M[3di\u0006q1KT!Q'\"{Ek\u0018'P\u0007.\u0003\u0013aC:vEN\u001c'/\u001b2feN,\u0012a\u001b\t\u0004\r2t\u0017BA7H\u0005Q\u0019u\u000e]=P]^\u0013\u0018\u000e^3BeJ\f\u0017\u0010T5tiB\u0011q\u000eF\u0007\u0002\u0001\t\u00192K\\1qg\"|Go]*vEN\u001c'/\u001b2feN\u0011AcM\u0001\u000f_:tUm^*oCB\u001c\bn\u001c;t!\u0015!DO^A\u0006\u0013\t)XGA\u0005Gk:\u001cG/[8ocA!qo`A\u0003\u001d\tAXP\u0004\u0002zy6\t!P\u0003\u0002|\t\u00061AH]8pizJ\u0011AN\u0005\u0003}V\nq\u0001]1dW\u0006<W-\u0003\u0003\u0002\u0002\u0005\r!aA*fc*\u0011a0\u000e\t\u0004u\u0005\u001d\u0011bAA\u0005M\t!R\t_3dkR|'\u000fU8egNs\u0017\r]:i_R\u00042\u0001NA\u0007\u0013\r\ty!\u000e\u0002\u0005+:LG\u000fF\u0002o\u0003'AQA\u001d\fA\u0002M\fqb\u001d8baNDw\u000e^:Ck\u001a4WM]\u000b\u0003\u00033\u0001RARA\u000e\u0003\u000bI1!!\bH\u0005Ma\u0015N\\6fI\ncwnY6j]\u001e\fV/Z;f\u0003A\u0019h.\u00199tQ>$8OQ;gM\u0016\u0014\b%\u0001\u0003m_\u000e\\WCAA\u0013!\u0011\t9#!\f\u000e\u0005\u0005%\"bAA\u0016\u000f\u0006)An\\2lg&!\u0011qFA\u0015\u00055\u0011V-\u001a8ue\u0006tG\u000fT8dW\u0006)An\\2lA\u0005\tbn\u001c;jM&\u001c\u0017\r^5p]\u000e{WO\u001c;\u0016\u0005\u0005]\u0002\u0003BA\u001d\u0003\u007fi!!a\u000f\u000b\u0007\u0005ur)\u0001\u0004bi>l\u0017nY\u0005\u0005\u0003\u0003\nYDA\u0007Bi>l\u0017nY%oi\u0016<WM]\u0001\u0013]>$\u0018NZ5dCRLwN\\\"pk:$\b%\u0001\nbI\u0012\u001cUO\u001d:f]R\u001cf.\u00199tQ>$HCAA\u0006\u0003A\u0001(o\\2fgN\u001cf.\u00199tQ>$8/\u0001\rqe>\u001cWm]:T]\u0006\u00048\u000f[8ug&sG/\u001a:oC2\fAb];cg\u000e\u0014\u0018NY3sg\u0002\nA\u0002]8mY&tw\rV1tWN,\"!a\u0015\u0011\t\u0019c\u0017Q\u000b\u0019\u0005\u0003/\n\t\u0007E\u0003G\u00033\ni&C\u0002\u0002\\\u001d\u0013aAR;ukJ,\u0007\u0003BA0\u0003Cb\u0001\u0001B\u0006\u0002d)\t\t\u0011!A\u0003\u0002\u0005\u001d$aA0%c\u0005i\u0001o\u001c7mS:<G+Y:lg\u0002\nB!!\u001b\u0002pA\u0019A'a\u001b\n\u0007\u00055TGA\u0004O_RD\u0017N\\4\u0011\u0007Q\n\t(C\u0002\u0002tU\u00121!\u00118z\u0003=\u0019WO\u001d:f]R\u001cf.\u00199tQ>$XCAA\u0003\u0003M\u0019WO\u001d:f]R\u001cf.\u00199tQ>$x\fJ3r)\u0011\tY!! \t\u0013\u0005}D\"!AA\u0002\u0005\u0015\u0011a\u0001=%c\u0005\u00012-\u001e:sK:$8K\\1qg\"|G\u000f\t\u0015\b\u001b\u0005\u0015\u0015qSAM!\u0011\t9)a%\u000e\u0005\u0005%%b\u0001%\u0002\f*!\u0011QRAH\u0003)\tgN\\8uCRLwN\u001c\u0006\u0003\u0003#\u000bQA[1wCbLA!!&\u0002\n\nIq)^1sI\u0016$')_\u0001\u0006m\u0006dW/Z\u0011\u0002A\u0006i\u0011\r\u001a3Tk\n\u001c8M]5cKJ$B!a(\u0002$R!\u00111BAQ\u0011\u0015\u0011h\u00021\u0001t\u0011\u001d\t)K\u0004a\u0001\u0003O\u000b!\u0004\u001d:pG\u0016\u001c8OQ1uG\"Le\u000e^3sm\u0006dW*\u001b7mSN\u00042\u0001NAU\u0013\r\tY+\u000e\u0002\u0005\u0019>tw-A\to_RLg-_*vEN\u001c'/\u001b2feN\fAa\u001d;pa\u0006IQ\u000f\u001d3bi\u0016\u0004v\u000e\u001a\u000b\u0005\u0003\u0017\t)\fC\u0004\u00028F\u0001\r!!/\u0002\u0015U\u0004H-\u0019;fIB{G\r\u0005\u0003\u0002<\u0006EWBAA_\u0015\u0011\ty,!1\u0002\u000b5|G-\u001a7\u000b\t\u0005\r\u0017QY\u0001\u0004CBL'\u0002BAd\u0003\u0013\f!b[;cKJtW\r^3t\u0015\u0011\tY-!4\u0002\u000f\u0019\f'M]5dq)\u0011\u0011qZ\u0001\u0003S>LA!a5\u0002>\n\u0019\u0001k\u001c3\u0002\u001fI,\u0007\u000f\\1dKNs\u0017\r]:i_R$B!a\u0003\u0002Z\"9\u00111\u001c\nA\u0002\u0005u\u0017a\u00038foNs\u0017\r]:i_R\u0004Ba^@\u0002:\u0006y\u0012\r\u001a3DkJ\u0014XM\u001c;T]\u0006\u00048\u000f[8u)>\u001cVOY:de&\u0014WM]:\u0002=\u0015CXmY;u_J\u0004v\u000eZ:T]\u0006\u00048\u000f[8ugN#xN]3J[Bd\u0007C\u0001\u001e\"'\t\t3\u0007\u0006\u0002\u0002d\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uII*\"!!<+\u0007A\u000byo\u000b\u0002\u0002rB!\u00111_A~\u001b\t\t)P\u0003\u0003\u0002x\u0006e\u0018!C;oG\",7m[3e\u0015\r\ti)N\u0005\u0005\u0003{\f)PA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001aTC\u0001B\u0002U\r1\u0016q\u001e"
)
public class ExecutorPodsSnapshotsStoreImpl implements ExecutorPodsSnapshotsStore, Logging {
   public final ScheduledExecutorService org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$$subscribersExecutor;
   private final Clock clock;
   private final SparkConf conf;
   private final Object SNAPSHOT_LOCK;
   private final CopyOnWriteArrayList subscribers;
   private final CopyOnWriteArrayList pollingTasks;
   @GuardedBy("SNAPSHOT_LOCK")
   private ExecutorPodsSnapshot org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$$currentSnapshot;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static SparkConf $lessinit$greater$default$3() {
      return ExecutorPodsSnapshotsStoreImpl$.MODULE$.$lessinit$greater$default$3();
   }

   public static Clock $lessinit$greater$default$2() {
      return ExecutorPodsSnapshotsStoreImpl$.MODULE$.$lessinit$greater$default$2();
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

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private Object SNAPSHOT_LOCK() {
      return this.SNAPSHOT_LOCK;
   }

   private CopyOnWriteArrayList subscribers() {
      return this.subscribers;
   }

   private CopyOnWriteArrayList pollingTasks() {
      return this.pollingTasks;
   }

   public ExecutorPodsSnapshot org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$$currentSnapshot() {
      return this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$$currentSnapshot;
   }

   private void currentSnapshot_$eq(final ExecutorPodsSnapshot x$1) {
      this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$$currentSnapshot = x$1;
   }

   public void addSubscriber(final long processBatchIntervalMillis, final Function1 onNewSnapshots) {
      SnapshotsSubscriber newSubscriber = new SnapshotsSubscriber(onNewSnapshots);
      synchronized(this.SNAPSHOT_LOCK()){}

      try {
         newSubscriber.addCurrentSnapshot();
      } catch (Throwable var7) {
         throw var7;
      }

      this.subscribers().add(newSubscriber);
      this.pollingTasks().add(this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$$subscribersExecutor.scheduleWithFixedDelay(() -> newSubscriber.processSnapshots(), 0L, processBatchIntervalMillis, TimeUnit.MILLISECONDS));
   }

   public void notifySubscribers() {
      synchronized(this.SNAPSHOT_LOCK()){}

      try {
         .MODULE$.ListHasAsScala(this.subscribers()).asScala().foreach((s) -> this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$$subscribersExecutor.submit(new Runnable(s) {
               private final SnapshotsSubscriber s$1;

               public void run() {
                  this.s$1.processSnapshots();
               }

               public {
                  this.s$1 = s$1;
               }
            }));
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public void stop() {
      .MODULE$.ListHasAsScala(this.pollingTasks()).asScala().foreach((x$1) -> BoxesRunTime.boxToBoolean($anonfun$stop$1(x$1)));
      long awaitSeconds = BoxesRunTime.unboxToLong(this.conf.get(Config$.MODULE$.KUBERNETES_EXECUTOR_SNAPSHOTS_SUBSCRIBERS_GRACE_PERIOD()));
      org.apache.spark.util.ThreadUtils..MODULE$.shutdown(this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$$subscribersExecutor, scala.concurrent.duration.FiniteDuration..MODULE$.apply(awaitSeconds, TimeUnit.SECONDS));
   }

   public void updatePod(final Pod updatedPod) {
      synchronized(this.SNAPSHOT_LOCK()){}

      try {
         this.currentSnapshot_$eq(this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$$currentSnapshot().withUpdate(updatedPod));
         this.addCurrentSnapshotToSubscribers();
      } catch (Throwable var4) {
         throw var4;
      }

   }

   public void replaceSnapshot(final Seq newSnapshot) {
      synchronized(this.SNAPSHOT_LOCK()){}

      try {
         this.currentSnapshot_$eq(ExecutorPodsSnapshot$.MODULE$.apply(newSnapshot, this.clock.getTimeMillis()));
         this.addCurrentSnapshotToSubscribers();
      } catch (Throwable var4) {
         throw var4;
      }

   }

   private void addCurrentSnapshotToSubscribers() {
      .MODULE$.ListHasAsScala(this.subscribers()).asScala().foreach((x$2) -> {
         $anonfun$addCurrentSnapshotToSubscribers$1(x$2);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$stop$1(final Future x$1) {
      return x$1.cancel(false);
   }

   // $FF: synthetic method
   public static final void $anonfun$addCurrentSnapshotToSubscribers$1(final SnapshotsSubscriber x$2) {
      x$2.addCurrentSnapshot();
   }

   public ExecutorPodsSnapshotsStoreImpl(final ScheduledExecutorService subscribersExecutor, final Clock clock, final SparkConf conf) {
      this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$$subscribersExecutor = subscribersExecutor;
      this.clock = clock;
      this.conf = conf;
      Logging.$init$(this);
      this.SNAPSHOT_LOCK = new Object();
      this.subscribers = new CopyOnWriteArrayList();
      this.pollingTasks = new CopyOnWriteArrayList();
      this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$$currentSnapshot = ExecutorPodsSnapshot$.MODULE$.apply();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class SnapshotsSubscriber {
      private final Function1 onNewSnapshots;
      private final LinkedBlockingQueue snapshotsBuffer;
      private final ReentrantLock lock;
      private final AtomicInteger notificationCount;
      // $FF: synthetic field
      public final ExecutorPodsSnapshotsStoreImpl $outer;

      private LinkedBlockingQueue snapshotsBuffer() {
         return this.snapshotsBuffer;
      }

      private ReentrantLock lock() {
         return this.lock;
      }

      private AtomicInteger notificationCount() {
         return this.notificationCount;
      }

      public void addCurrentSnapshot() {
         this.snapshotsBuffer().add(this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$SnapshotsSubscriber$$$outer().org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$$currentSnapshot());
      }

      public void processSnapshots() {
         this.notificationCount().incrementAndGet();
         this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$SnapshotsSubscriber$$processSnapshotsInternal();
      }

      public void org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$SnapshotsSubscriber$$processSnapshotsInternal() {
         if (this.lock().tryLock()) {
            if (this.notificationCount().get() > 0) {
               try {
                  ArrayList snapshots = new ArrayList();
                  this.snapshotsBuffer().drainTo(snapshots);
                  this.onNewSnapshots.apply(.MODULE$.ListHasAsScala(snapshots).asScala().toSeq());
               } catch (Throwable var10) {
                  if (var10 instanceof IllegalArgumentException) {
                     IllegalArgumentException var5 = (IllegalArgumentException)var10;
                     this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$SnapshotsSubscriber$$$outer().logError((Function0)(() -> "Going to stop due to IllegalArgumentException"), var5);
                     System.exit(1);
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  } else {
                     if (var10 == null || !scala.util.control.NonFatal..MODULE$.apply(var10)) {
                        throw var10;
                     }

                     this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$SnapshotsSubscriber$$$outer().logWarning((Function0)(() -> "Exception when notifying snapshot subscriber."), var10);
                     BoxedUnit var12 = BoxedUnit.UNIT;
                  }

                  BoxedUnit var13 = BoxedUnit.UNIT;
               } finally {
                  this.lock().unlock();
               }

               if (this.notificationCount().decrementAndGet() > 0) {
                  this.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$SnapshotsSubscriber$$$outer().org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$$subscribersExecutor.submit(new Runnable() {
                     // $FF: synthetic field
                     private final SnapshotsSubscriber $outer;

                     public void run() {
                        this.$outer.org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$SnapshotsSubscriber$$processSnapshotsInternal();
                     }

                     public {
                        if (SnapshotsSubscriber.this == null) {
                           throw null;
                        } else {
                           this.$outer = SnapshotsSubscriber.this;
                        }
                     }
                  });
               }
            } else {
               this.lock().unlock();
            }
         }
      }

      // $FF: synthetic method
      public ExecutorPodsSnapshotsStoreImpl org$apache$spark$scheduler$cluster$k8s$ExecutorPodsSnapshotsStoreImpl$SnapshotsSubscriber$$$outer() {
         return this.$outer;
      }

      public SnapshotsSubscriber(final Function1 onNewSnapshots) {
         this.onNewSnapshots = onNewSnapshots;
         if (ExecutorPodsSnapshotsStoreImpl.this == null) {
            throw null;
         } else {
            this.$outer = ExecutorPodsSnapshotsStoreImpl.this;
            super();
            this.snapshotsBuffer = new LinkedBlockingQueue();
            this.lock = new ReentrantLock();
            this.notificationCount = new AtomicInteger();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
