package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.metrics.MetricsSystem;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.ListBuffer;
import scala.jdk.CollectionConverters.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.DynamicVariable;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015c!\u0002\u00180\u0001E:\u0004\u0002\u0003 \u0001\u0005\u0003\u0005\u000b\u0011\u0002!\t\u000b\u0011\u0003A\u0011A#\t\u0013%\u0003\u0001\u0019!a\u0001\n\u0013Q\u0005\"\u0003(\u0001\u0001\u0004\u0005\r\u0011\"\u0003P\u0011%)\u0006\u00011A\u0001B\u0003&1\n\u0003\u0005W\u0001\t\u0007I\u0011A\u0019X\u0011\u0019Y\u0006\u0001)A\u00051\"9A\f\u0001b\u0001\n\u0013i\u0006B\u00026\u0001A\u0003%a\fC\u0004l\u0001\t\u0007I\u0011B/\t\r1\u0004\u0001\u0015!\u0003_\u0011\u001di\u0007A1A\u0005\n9DaA\u001e\u0001!\u0002\u0013y\u0007\u0002C<\u0001\u0001\u0004%\ta\f=\t\u0015\u0005%\u0001\u00011A\u0005\u0002=\nY\u0001C\u0004\u0002\u0010\u0001\u0001\u000b\u0015B=\t\u000f\u0005e\u0001\u0001\"\u0001\u0002\u001c!9\u0011q\u0005\u0001\u0005\u0002\u0005%\u0002bBA\u0017\u0001\u0011\u0005\u0011q\u0006\u0005\b\u0003g\u0001A\u0011AA\u001b\u0011!\tI\u0004\u0001C\u0001c\u0005m\u0002bBA-\u0001\u0011\u0005\u00111\f\u0005\b\u0003?\u0002A\u0011AA1\u0011\u001d\t9\u0007\u0001C\u0005\u0003SBq!!\u001c\u0001\t\u0003\ty\u0007\u0003\u0005\u0002\u0004\u0002!\t!MAC\u0011\u001d\t\u0019\t\u0001C\u0001\u0003/Cq!!*\u0001\t\u0003\t)\t\u0003\u0005\u0002(\u0002!\t!MAU\u0011!\t\t\u000f\u0001C\u0001c\u0005\r\b\u0002CAw\u0001\u0011\u0005q&a<\t\u0011\u0005]\b\u0001\"\u00010\u0003s<\u0001Ba\u00030\u0011\u0003\t$Q\u0002\u0004\b]=B\t!\rB\b\u0011\u0019!%\u0005\"\u0001\u0003\u0012!I!1\u0003\u0012C\u0002\u0013\u0005!Q\u0003\u0005\t\u0005O\u0011\u0003\u0015!\u0003\u0003\u0018!Q!\u0011\u0006\u0012C\u0002\u0013\u0005qFa\u000b\t\u0011\t]\"\u0005)A\u0005\u0005[A!B!\u000f#\u0005\u0004%\ta\fB\u0016\u0011!\u0011YD\tQ\u0001\n\t5\u0002B\u0003B\u001fE\t\u0007I\u0011A\u0018\u0003,!A!q\b\u0012!\u0002\u0013\u0011i\u0003\u0003\u0006\u0003B\t\u0012\r\u0011\"\u00010\u0005WA\u0001Ba\u0011#A\u0003%!Q\u0006\u0002\u0010\u0019&4X\rT5ti\u0016tWM\u001d\"vg*\u0011\u0001'M\u0001\ng\u000eDW\rZ;mKJT!AM\u001a\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q*\u0014AB1qC\u000eDWMC\u00017\u0003\ry'oZ\n\u0003\u0001a\u0002\"!\u000f\u001f\u000e\u0003iR\u0011aO\u0001\u0006g\u000e\fG.Y\u0005\u0003{i\u0012a!\u00118z%\u00164\u0017\u0001B2p]\u001a\u001c\u0001\u0001\u0005\u0002B\u00056\t\u0011'\u0003\u0002Dc\tI1\u000b]1sW\u000e{gNZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0019C\u0005CA$\u0001\u001b\u0005y\u0003\"\u0002 \u0003\u0001\u0004\u0001\u0015\u0001D:qCJ\\7i\u001c8uKb$X#A&\u0011\u0005\u0005c\u0015BA'2\u00051\u0019\u0006/\u0019:l\u0007>tG/\u001a=u\u0003A\u0019\b/\u0019:l\u0007>tG/\u001a=u?\u0012*\u0017\u000f\u0006\u0002Q'B\u0011\u0011(U\u0005\u0003%j\u0012A!\u00168ji\"9A\u000bBA\u0001\u0002\u0004Y\u0015a\u0001=%c\u0005i1\u000f]1sW\u000e{g\u000e^3yi\u0002\nq!\\3ue&\u001c7/F\u0001Y!\t9\u0015,\u0003\u0002[_\t1B*\u001b<f\u0019&\u001cH/\u001a8fe\n+8/T3ue&\u001c7/\u0001\u0005nKR\u0014\u0018nY:!\u0003\u001d\u0019H/\u0019:uK\u0012,\u0012A\u0018\t\u0003?\"l\u0011\u0001\u0019\u0006\u0003C\n\fa!\u0019;p[&\u001c'BA2e\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0003K\u001a\fA!\u001e;jY*\tq-\u0001\u0003kCZ\f\u0017BA5a\u00055\tEo\\7jG\n{w\u000e\\3b]\u0006A1\u000f^1si\u0016$\u0007%A\u0004ti>\u0004\b/\u001a3\u0002\u0011M$x\u000e\u001d9fI\u0002\na!];fk\u0016\u001cX#A8\u0011\u0007A\f8/D\u0001c\u0013\t\u0011(M\u0001\u000bD_BLxJ\\,sSR,\u0017I\u001d:bs2K7\u000f\u001e\t\u0003\u000fRL!!^\u0018\u0003\u001f\u0005\u001b\u0018P\\2Fm\u0016tG/U;fk\u0016\fq!];fk\u0016\u001c\b%\u0001\u0007rk\u0016,X\rZ#wK:$8/F\u0001z!\u0011Qx0a\u0001\u000e\u0003mT!\u0001`?\u0002\u000f5,H/\u00192mK*\u0011aPO\u0001\u000bG>dG.Z2uS>t\u0017bAA\u0001w\nQA*[:u\u0005V4g-\u001a:\u0011\u0007\u001d\u000b)!C\u0002\u0002\b=\u0012!c\u00159be.d\u0015n\u001d;f]\u0016\u0014XI^3oi\u0006\u0001\u0012/^3vK\u0012,e/\u001a8ug~#S-\u001d\u000b\u0004!\u00065\u0001b\u0002+\u0010\u0003\u0003\u0005\r!_\u0001\u000ecV,W/\u001a3Fm\u0016tGo\u001d\u0011)\u0007A\t\u0019\u0002E\u0002:\u0003+I1!a\u0006;\u0005!1x\u000e\\1uS2,\u0017\u0001E1eIR{7\u000b[1sK\u0012\fV/Z;f)\r\u0001\u0016Q\u0004\u0005\b\u0003?\t\u0002\u0019AA\u0011\u0003!a\u0017n\u001d;f]\u0016\u0014\bcA$\u0002$%\u0019\u0011QE\u0018\u0003-M\u0003\u0018M]6MSN$XM\\3s\u0013:$XM\u001d4bG\u0016\fA#\u00193e)>l\u0015M\\1hK6,g\u000e^)vKV,Gc\u0001)\u0002,!9\u0011q\u0004\nA\u0002\u0005\u0005\u0012\u0001E1eIR{7\u000b^1ukN\fV/Z;f)\r\u0001\u0016\u0011\u0007\u0005\b\u0003?\u0019\u0002\u0019AA\u0011\u0003I\tG\r\u001a+p\u000bZ,g\u000e\u001e'pOF+X-^3\u0015\u0007A\u000b9\u0004C\u0004\u0002 Q\u0001\r!!\t\u0002\u0015\u0005$G\rV8Rk\u0016,X\rF\u0003Q\u0003{\ty\u0004C\u0004\u0002 U\u0001\r!!\t\t\u000f\u0005\u0005S\u00031\u0001\u0002D\u0005)\u0011/^3vKB!\u0011QIA*\u001d\u0011\t9%a\u0014\u0011\u0007\u0005%#(\u0004\u0002\u0002L)\u0019\u0011QJ \u0002\rq\u0012xn\u001c;?\u0013\r\t\tFO\u0001\u0007!J,G-\u001a4\n\t\u0005U\u0013q\u000b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005E#(\u0001\bsK6|g/\u001a'jgR,g.\u001a:\u0015\u0007A\u000bi\u0006C\u0004\u0002 Y\u0001\r!!\t\u0002\tA|7\u000f\u001e\u000b\u0004!\u0006\r\u0004bBA3/\u0001\u0007\u00111A\u0001\u0006KZ,g\u000e^\u0001\ra>\u001cH\u000fV8Rk\u0016,Xm\u001d\u000b\u0004!\u0006-\u0004bBA31\u0001\u0007\u00111A\u0001\u0006gR\f'\u000f\u001e\u000b\u0006!\u0006E\u0014Q\u000f\u0005\u0007\u0003gJ\u0002\u0019A&\u0002\u0005M\u001c\u0007bBA<3\u0001\u0007\u0011\u0011P\u0001\u000e[\u0016$(/[2t'f\u001cH/Z7\u0011\t\u0005m\u0014qP\u0007\u0003\u0003{R!AV\u0019\n\t\u0005\u0005\u0015Q\u0010\u0002\u000e\u001b\u0016$(/[2t'f\u001cH/Z7\u0002\u001d]\f\u0017\u000e^+oi&dW)\u001c9usR\t\u0001\u000bK\u0003\u001b\u0003\u0013\u000b)\nE\u0003:\u0003\u0017\u000by)C\u0002\u0002\u000ej\u0012a\u0001\u001e5s_^\u001c\bc\u00019\u0002\u0012&\u0019\u00111\u00132\u0003!QKW.Z8vi\u0016C8-\u001a9uS>t7EAAH)\r\u0001\u0016\u0011\u0014\u0005\b\u00037[\u0002\u0019AAO\u00035!\u0018.\\3pkRl\u0015\u000e\u001c7jgB\u0019\u0011(a(\n\u0007\u0005\u0005&H\u0001\u0003M_:<\u0007&B\u000e\u0002\n\u0006U\u0015\u0001B:u_B\fACZ5oI2K7\u000f^3oKJ\u001c()_\"mCN\u001cX\u0003BAV\u0003\u000b$\"!!,\u0015\t\u0005=\u0016\u0011\u001b\t\u0007\u0003c\u000bY,!1\u000f\t\u0005M\u0016q\u0017\b\u0005\u0003\u0013\n),C\u0001<\u0013\r\tILO\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\ti,a0\u0003\u0007M+\u0017OC\u0002\u0002:j\u0002B!a1\u0002F2\u0001AaBAd;\t\u0007\u0011\u0011\u001a\u0002\u0002)F!\u00111ZA\u0011!\rI\u0014QZ\u0005\u0004\u0003\u001fT$a\u0002(pi\"Lgn\u001a\u0005\n\u0003'l\u0012\u0011!a\u0002\u0003+\f!\"\u001a<jI\u0016t7-\u001a\u00132!\u0019\t9.!8\u0002B6\u0011\u0011\u0011\u001c\u0006\u0004\u00037T\u0014a\u0002:fM2,7\r^\u0005\u0005\u0003?\fIN\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003%a\u0017n\u001d;f]\u0016\u00148/\u0006\u0002\u0002fB1\u0011q]Au\u0003Ci\u0011\u0001Z\u0005\u0004\u0003W$'\u0001\u0002'jgR\fA\"Y2uSZ,\u0017+^3vKN$\"!!=\u0011\r\u0005\u0015\u00131_A\"\u0013\u0011\t)0a\u0016\u0003\u0007M+G/\u0001\thKR\fV/Z;f\u0007\u0006\u0004\u0018mY5usR!\u00111 B\u0004!\u0015I\u0014Q B\u0001\u0013\r\tyP\u000f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0007e\u0012\u0019!C\u0002\u0003\u0006i\u00121!\u00138u\u0011\u001d\u0011I\u0001\ta\u0001\u0003\u0007\nAA\\1nK\u0006yA*\u001b<f\u0019&\u001cH/\u001a8fe\n+8\u000f\u0005\u0002HEM\u0011!\u0005\u000f\u000b\u0003\u0005\u001b\tAc^5uQ&tG*[:uK:,'\u000f\u00165sK\u0006$WC\u0001B\f!\u0019\u0011IB!\b\u0003\"5\u0011!1\u0004\u0006\u0003KjJAAa\b\u0003\u001c\tyA)\u001f8b[&\u001cg+\u0019:jC\ndW\rE\u0002:\u0005GI1A!\n;\u0005\u001d\u0011un\u001c7fC:\fQc^5uQ&tG*[:uK:,'\u000f\u00165sK\u0006$\u0007%\u0001\u0007T\u0011\u0006\u0013V\tR0R+\u0016+V)\u0006\u0002\u0003.A!!q\u0006B\u001b\u001b\t\u0011\tDC\u0002\u00034\u0019\fA\u0001\\1oO&!\u0011Q\u000bB\u0019\u00035\u0019\u0006*\u0011*F\t~\u000bV+R+FA\u0005\u0001\u0012\t\u0015)`'R\u000bE+V*`#V+U+R\u0001\u0012\u0003B\u0003vl\u0015+B)V\u001bv,U+F+\u0016\u0003\u0013!G#Y\u000b\u000e+Fk\u0014*`\u001b\u0006s\u0015iR#N\u000b:#v,U+F+\u0016\u000b!$\u0012-F\u0007V#vJU0N\u0003:\u000bu)R'F\u001dR{\u0016+V#V\u000b\u0002\nq\"\u0012,F\u001dR{FjT$`#V+U+R\u0001\u0011\u000bZ+e\nV0M\u001f\u001e{\u0016+V#V\u000b\u0002\u0002"
)
public class LiveListenerBus {
   private final SparkConf conf;
   private SparkContext sparkContext;
   private final LiveListenerBusMetrics metrics;
   private final AtomicBoolean started;
   private final AtomicBoolean stopped;
   private final CopyOnWriteArrayList queues;
   private volatile ListBuffer queuedEvents;

   public static DynamicVariable withinListenerThread() {
      return LiveListenerBus$.MODULE$.withinListenerThread();
   }

   private SparkContext sparkContext() {
      return this.sparkContext;
   }

   private void sparkContext_$eq(final SparkContext x$1) {
      this.sparkContext = x$1;
   }

   public LiveListenerBusMetrics metrics() {
      return this.metrics;
   }

   private AtomicBoolean started() {
      return this.started;
   }

   private AtomicBoolean stopped() {
      return this.stopped;
   }

   private CopyOnWriteArrayList queues() {
      return this.queues;
   }

   public ListBuffer queuedEvents() {
      return this.queuedEvents;
   }

   public void queuedEvents_$eq(final ListBuffer x$1) {
      this.queuedEvents = x$1;
   }

   public void addToSharedQueue(final SparkListenerInterface listener) {
      this.addToQueue(listener, LiveListenerBus$.MODULE$.SHARED_QUEUE());
   }

   public void addToManagementQueue(final SparkListenerInterface listener) {
      this.addToQueue(listener, LiveListenerBus$.MODULE$.EXECUTOR_MANAGEMENT_QUEUE());
   }

   public void addToStatusQueue(final SparkListenerInterface listener) {
      this.addToQueue(listener, LiveListenerBus$.MODULE$.APP_STATUS_QUEUE());
   }

   public void addToEventLogQueue(final SparkListenerInterface listener) {
      this.addToQueue(listener, LiveListenerBus$.MODULE$.EVENT_LOG_QUEUE());
   }

   public void addToQueue(final SparkListenerInterface listener, final String queue) {
      synchronized(this){}

      try {
         if (this.stopped().get()) {
            throw new IllegalStateException("LiveListenerBus is stopped.");
         }

         Option var5 = .MODULE$.ListHasAsScala(this.queues()).asScala().find((x$2) -> BoxesRunTime.boxToBoolean($anonfun$addToQueue$1(queue, x$2)));
         if (var5 instanceof Some var6) {
            AsyncEventQueue queue = (AsyncEventQueue)var6.value();
            queue.addListener(listener);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            if (!scala.None..MODULE$.equals(var5)) {
               throw new MatchError(var5);
            }

            AsyncEventQueue newQueue = new AsyncEventQueue(queue, this.conf, this.metrics(), this);
            newQueue.addListener(listener);
            if (this.started().get()) {
               newQueue.start(this.sparkContext());
            }

            BoxesRunTime.boxToBoolean(this.queues().add(newQueue));
         }
      } catch (Throwable var10) {
         throw var10;
      }

   }

   public synchronized void removeListener(final SparkListenerInterface listener) {
      ((IterableOnceOps).MODULE$.ListHasAsScala(this.queues()).asScala().filter((queue) -> BoxesRunTime.boxToBoolean($anonfun$removeListener$1(listener, queue)))).foreach((toRemove) -> BoxesRunTime.boxToBoolean($anonfun$removeListener$2(this, toRemove)));
   }

   public void post(final SparkListenerEvent event) {
      if (!this.stopped().get()) {
         this.metrics().numEventsPosted().inc();
         if (this.queuedEvents() == null) {
            this.postToQueues(event);
         } else {
            synchronized(this){}

            label49: {
               try {
                  if (this.started().get()) {
                     break label49;
                  }

                  this.queuedEvents().$plus$eq(event);
               } catch (Throwable var4) {
                  throw var4;
               }

               return;
            }

            this.postToQueues(event);
         }
      }
   }

   private void postToQueues(final SparkListenerEvent event) {
      Iterator it = this.queues().iterator();

      while(it.hasNext()) {
         ((AsyncEventQueue)it.next()).post(event);
      }

   }

   public synchronized void start(final SparkContext sc, final MetricsSystem metricsSystem) {
      if (!this.started().compareAndSet(false, true)) {
         throw new IllegalStateException("LiveListenerBus already started.");
      } else {
         this.sparkContext_$eq(sc);
         .MODULE$.ListHasAsScala(this.queues()).asScala().foreach((q) -> {
            $anonfun$start$1(this, sc, q);
            return BoxedUnit.UNIT;
         });
         this.queuedEvents_$eq((ListBuffer)null);
         metricsSystem.registerSource(this.metrics());
      }
   }

   public void waitUntilEmpty() throws TimeoutException {
      this.waitUntilEmpty(TimeUnit.SECONDS.toMillis(10L));
   }

   public void waitUntilEmpty(final long timeoutMillis) throws TimeoutException {
      long deadline = System.currentTimeMillis() + timeoutMillis;
      .MODULE$.ListHasAsScala(this.queues()).asScala().foreach((queue) -> {
         $anonfun$waitUntilEmpty$1(deadline, timeoutMillis, queue);
         return BoxedUnit.UNIT;
      });
   }

   public void stop() {
      if (!this.started().get()) {
         throw new IllegalStateException("Attempted to stop bus that has not yet started!");
      } else if (this.stopped().compareAndSet(false, true)) {
         .MODULE$.ListHasAsScala(this.queues()).asScala().foreach((x$3) -> {
            $anonfun$stop$1(x$3);
            return BoxedUnit.UNIT;
         });
         this.queues().clear();
      }
   }

   public Seq findListenersByClass(final ClassTag evidence$1) {
      return ((IterableOnceOps).MODULE$.ListHasAsScala(this.queues()).asScala().flatMap((queue) -> queue.findListenersByClass(evidence$1))).toSeq();
   }

   public List listeners() {
      return .MODULE$.BufferHasAsJava((Buffer).MODULE$.ListHasAsScala(this.queues()).asScala().flatMap((x$4) -> .MODULE$.ListHasAsScala(x$4.listeners()).asScala())).asJava();
   }

   public Set activeQueues() {
      return ((IterableOnceOps).MODULE$.ListHasAsScala(this.queues()).asScala().map((x$5) -> x$5.name())).toSet();
   }

   public Option getQueueCapacity(final String name) {
      return .MODULE$.ListHasAsScala(this.queues()).asScala().find((x$6) -> BoxesRunTime.boxToBoolean($anonfun$getQueueCapacity$1(name, x$6))).map((x$7) -> BoxesRunTime.boxToInteger($anonfun$getQueueCapacity$2(x$7)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$addToQueue$1(final String queue$1, final AsyncEventQueue x$2) {
      boolean var3;
      label23: {
         String var10000 = x$2.name();
         if (var10000 == null) {
            if (queue$1 == null) {
               break label23;
            }
         } else if (var10000.equals(queue$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeListener$1(final SparkListenerInterface listener$1, final AsyncEventQueue queue) {
      queue.removeListener(listener$1);
      return queue.listeners().isEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeListener$2(final LiveListenerBus $this, final AsyncEventQueue toRemove) {
      if ($this.started().get() && !$this.stopped().get()) {
         toRemove.stop();
      }

      return $this.queues().remove(toRemove);
   }

   // $FF: synthetic method
   public static final void $anonfun$start$2(final AsyncEventQueue q$1, final SparkListenerEvent event) {
      q$1.post(event);
   }

   // $FF: synthetic method
   public static final void $anonfun$start$1(final LiveListenerBus $this, final SparkContext sc$1, final AsyncEventQueue q) {
      q.start(sc$1);
      $this.queuedEvents().foreach((event) -> {
         $anonfun$start$2(q, event);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$waitUntilEmpty$1(final long deadline$1, final long timeoutMillis$1, final AsyncEventQueue queue) {
      if (!queue.waitUntilEmpty(deadline$1)) {
         throw SparkCoreErrors$.MODULE$.nonEmptyEventQueueAfterTimeoutError(timeoutMillis$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$1(final AsyncEventQueue x$3) {
      x$3.stop();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getQueueCapacity$1(final String name$1, final AsyncEventQueue x$6) {
      boolean var3;
      label23: {
         String var10000 = x$6.name();
         if (var10000 == null) {
            if (name$1 == null) {
               break label23;
            }
         } else if (var10000.equals(name$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final int $anonfun$getQueueCapacity$2(final AsyncEventQueue x$7) {
      return x$7.capacity();
   }

   public LiveListenerBus(final SparkConf conf) {
      this.conf = conf;
      this.metrics = new LiveListenerBusMetrics(conf);
      this.started = new AtomicBoolean(false);
      this.stopped = new AtomicBoolean(false);
      this.queues = new CopyOnWriteArrayList();
      this.queuedEvents = new ListBuffer();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
