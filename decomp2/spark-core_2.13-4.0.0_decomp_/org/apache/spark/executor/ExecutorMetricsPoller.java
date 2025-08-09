package org.apache.spark.executor;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLongArray;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.memory.MemoryManager;
import org.apache.spark.metrics.ExecutorMetricType$;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t=b!B\u0016-\u00019\"\u0004\u0002C!\u0001\u0005\u0003\u0005\u000b\u0011B\"\t\u0011%\u0003!\u0011!Q\u0001\n)C\u0001\"\u0014\u0001\u0003\u0002\u0003\u0006IA\u0014\u0005\u0006+\u0002!\tAV\u0003\u00057\u0002\u0001ALB\u0003c\u0001\u0001c3\r\u0003\u0005t\r\tU\r\u0011\"\u0001u\u0011!)hA!E!\u0002\u0013Q\u0005\u0002\u0003<\u0007\u0005+\u0007I\u0011A<\t\u0013\u0005%aA!E!\u0002\u0013A\bBB+\u0007\t\u0003\tY\u0001C\u0005\u0002\u0016\u0019\t\t\u0011\"\u0001\u0002\u0018!I\u0011Q\u0004\u0004\u0012\u0002\u0013\u0005\u0011q\u0004\u0005\n\u0003k1\u0011\u0013!C\u0001\u0003oA\u0011\"a\u000f\u0007\u0003\u0003%\t%!\u0010\t\u0013\u0005-c!!A\u0005\u0002\u00055\u0003\"CA(\r\u0005\u0005I\u0011AA)\u0011%\tiFBA\u0001\n\u0003\ny\u0006C\u0005\u0002n\u0019\t\t\u0011\"\u0001\u0002p!I\u0011\u0011\u0010\u0004\u0002\u0002\u0013\u0005\u00131\u0010\u0005\n\u0003\u007f2\u0011\u0011!C!\u0003\u0003C\u0011\"a!\u0007\u0003\u0003%\t%!\"\t\u0013\u0005\u001de!!A\u0005B\u0005%uACAG\u0001\u0005\u0005\t\u0012\u0001\u0017\u0002\u0010\u001aI!\rAA\u0001\u0012\u0003a\u0013\u0011\u0013\u0005\u0007+f!\t!!+\t\u0013\u0005\r\u0015$!A\u0005F\u0005\u0015\u0005\"CAV3\u0005\u0005I\u0011QAW\u0011%\t\u0019,GA\u0001\n\u0003\u000b)\f\u0003\u0006\u0002@\u0002\u0011\r\u0011\"\u0001-\u0003\u0003D\u0001\"!4\u0001A\u0003%\u00111\u0019\u0005\n\u0003\u001f\u0004!\u0019!C\u0005\u0003#D\u0001\"!6\u0001A\u0003%\u00111\u001b\u0005\n\u0003/\u0004!\u0019!C\u0005\u00033D\u0001\"a9\u0001A\u0003%\u00111\u001c\u0005\b\u0003K\u0004A\u0011AAt\u0011\u001d\ty\u000f\u0001C\u0001\u0003ODq!!=\u0001\t\u0003\t\u0019\u0010C\u0004\u0003\u0002\u0001!\tAa\u0001\t\u000f\t-\u0001\u0001\"\u0001\u0003\u000e!9!q\u0003\u0001\u0005\u0002\te\u0001b\u0002B\u0017\u0001\u0011\u0005\u0011q\u001d\u0002\u0016\u000bb,7-\u001e;pe6+GO]5dgB{G\u000e\\3s\u0015\tic&\u0001\u0005fq\u0016\u001cW\u000f^8s\u0015\ty\u0003'A\u0003ta\u0006\u00148N\u0003\u00022e\u00051\u0011\r]1dQ\u0016T\u0011aM\u0001\u0004_J<7c\u0001\u00016wA\u0011a'O\u0007\u0002o)\t\u0001(A\u0003tG\u0006d\u0017-\u0003\u0002;o\t1\u0011I\\=SK\u001a\u0004\"\u0001P \u000e\u0003uR!A\u0010\u0018\u0002\u0011%tG/\u001a:oC2L!\u0001Q\u001f\u0003\u000f1{wmZ5oO\u0006iQ.Z7pefl\u0015M\\1hKJ\u001c\u0001\u0001\u0005\u0002E\u000f6\tQI\u0003\u0002G]\u00051Q.Z7pefL!\u0001S#\u0003\u001b5+Wn\u001c:z\u001b\u0006t\u0017mZ3s\u0003=\u0001x\u000e\u001c7j]\u001eLe\u000e^3sm\u0006d\u0007C\u0001\u001cL\u0013\tauG\u0001\u0003M_:<\u0017!F3yK\u000e,Ho\u001c:NKR\u0014\u0018nY:T_V\u00148-\u001a\t\u0004m=\u000b\u0016B\u0001)8\u0005\u0019y\u0005\u000f^5p]B\u0011!kU\u0007\u0002Y%\u0011A\u000b\f\u0002\u0016\u000bb,7-\u001e;pe6+GO]5dgN{WO]2f\u0003\u0019a\u0014N\\5u}Q!q\u000bW-[!\t\u0011\u0006\u0001C\u0003B\t\u0001\u00071\tC\u0003J\t\u0001\u0007!\nC\u0003N\t\u0001\u0007aJ\u0001\u0005Ti\u0006<WmS3z!\u00111TlX0\n\u0005y;$A\u0002+va2,'\u0007\u0005\u00027A&\u0011\u0011m\u000e\u0002\u0004\u0013:$(\u0001\u0002+D\u001bB\u001bBAB\u001beOB\u0011a'Z\u0005\u0003M^\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002ia:\u0011\u0011N\u001c\b\u0003U6l\u0011a\u001b\u0006\u0003Y\n\u000ba\u0001\u0010:p_Rt\u0014\"\u0001\u001d\n\u0005=<\u0014a\u00029bG.\fw-Z\u0005\u0003cJ\u0014AbU3sS\u0006d\u0017N_1cY\u0016T!a\\\u001c\u0002\u000b\r|WO\u001c;\u0016\u0003)\u000baaY8v]R\u0004\u0013!\u00029fC.\u001cX#\u0001=\u0011\u0007e\f)!D\u0001{\u0015\tYH0\u0001\u0004bi>l\u0017n\u0019\u0006\u0003{z\f!bY8oGV\u0014(/\u001a8u\u0015\ry\u0018\u0011A\u0001\u0005kRLGN\u0003\u0002\u0002\u0004\u0005!!.\u0019<b\u0013\r\t9A\u001f\u0002\u0010\u0003R|W.[2M_:<\u0017I\u001d:bs\u00061\u0001/Z1lg\u0002\"b!!\u0004\u0002\u0012\u0005M\u0001cAA\b\r5\t\u0001\u0001C\u0003t\u0017\u0001\u0007!\nC\u0003w\u0017\u0001\u0007\u00010\u0001\u0003d_BLHCBA\u0007\u00033\tY\u0002C\u0004t\u0019A\u0005\t\u0019\u0001&\t\u000fYd\u0001\u0013!a\u0001q\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA\u0011U\rQ\u00151E\u0016\u0003\u0003K\u0001B!a\n\u000225\u0011\u0011\u0011\u0006\u0006\u0005\u0003W\ti#A\u0005v]\u000eDWmY6fI*\u0019\u0011qF\u001c\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u00024\u0005%\"!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCAA\u001dU\rA\u00181E\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005}\u0002\u0003BA!\u0003\u000fj!!a\u0011\u000b\t\u0005\u0015\u0013\u0011A\u0001\u0005Y\u0006tw-\u0003\u0003\u0002J\u0005\r#AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001`\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0015\u0002ZA\u0019a'!\u0016\n\u0007\u0005]sGA\u0002B]fD\u0001\"a\u0017\u0012\u0003\u0003\u0005\raX\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005\u0005\u0004CBA2\u0003S\n\u0019&\u0004\u0002\u0002f)\u0019\u0011qM\u001c\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002l\u0005\u0015$\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u001d\u0002xA\u0019a'a\u001d\n\u0007\u0005UtGA\u0004C_>dW-\u00198\t\u0013\u0005m3#!AA\u0002\u0005M\u0013A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!a\u0010\u0002~!A\u00111\f\u000b\u0002\u0002\u0003\u0007q,\u0001\u0005iCND7i\u001c3f)\u0005y\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005}\u0012AB3rk\u0006d7\u000f\u0006\u0003\u0002r\u0005-\u0005\"CA./\u0005\u0005\t\u0019AA*\u0003\u0011!6)\u0014)\u0011\u0007\u0005=\u0011dE\u0003\u001a\u0003'\u000by\n\u0005\u0005\u0002\u0016\u0006m%\n_A\u0007\u001b\t\t9JC\u0002\u0002\u001a^\nqA];oi&lW-\u0003\u0003\u0002\u001e\u0006]%!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u0011\u0011UAT\u001b\t\t\u0019K\u0003\u0003\u0002&\u0006\u0005\u0011AA5p\u0013\r\t\u00181\u0015\u000b\u0003\u0003\u001f\u000bQ!\u00199qYf$b!!\u0004\u00020\u0006E\u0006\"B:\u001d\u0001\u0004Q\u0005\"\u0002<\u001d\u0001\u0004A\u0018aB;oCB\u0004H.\u001f\u000b\u0005\u0003o\u000bY\f\u0005\u00037\u001f\u0006e\u0006\u0003\u0002\u001c^\u0015bD\u0011\"!0\u001e\u0003\u0003\u0005\r!!\u0004\u0002\u0007a$\u0003'A\u0005ti\u0006<W\rV\"N!V\u0011\u00111\u0019\t\t\u0003\u000b\f9-a3\u0002\u000e5\tA0C\u0002\u0002Jr\u0014\u0011cQ8oGV\u0014(/\u001a8u\u0011\u0006\u001c\b.T1q!\r\ty!B\u0001\u000bgR\fw-\u001a+D\u001bB\u0003\u0013a\u0004;bg.lU\r\u001e:jGB+\u0017m[:\u0016\u0005\u0005M\u0007CBAc\u0003\u000fT\u00050\u0001\tuCN\\W*\u001a;sS\u000e\u0004V-Y6tA\u00051\u0001o\u001c7mKJ,\"!a7\u0011\tYz\u0015Q\u001c\t\u0005\u0003\u000b\fy.C\u0002\u0002br\u0014\u0001dU2iK\u0012,H.\u001a3Fq\u0016\u001cW\u000f^8s'\u0016\u0014h/[2f\u0003\u001d\u0001x\u000e\u001c7fe\u0002\nA\u0001]8mYR\u0011\u0011\u0011\u001e\t\u0004m\u0005-\u0018bAAwo\t!QK\\5u\u0003\u0015\u0019H/\u0019:u\u0003-yg\u000eV1tWN#\u0018M\u001d;\u0015\u0011\u0005%\u0018Q_A}\u0003{Da!a>'\u0001\u0004Q\u0015A\u0002;bg.LE\r\u0003\u0004\u0002|\u001a\u0002\raX\u0001\bgR\fw-Z%e\u0011\u0019\tyP\na\u0001?\u0006q1\u000f^1hK\u0006#H/Z7qi&#\u0017\u0001E8o)\u0006\u001c8nQ8na2,G/[8o)!\tIO!\u0002\u0003\b\t%\u0001BBA|O\u0001\u0007!\n\u0003\u0004\u0002|\u001e\u0002\ra\u0018\u0005\u0007\u0003\u007f<\u0003\u0019A0\u0002%\u001d,G\u000fV1tW6+GO]5d!\u0016\f7n\u001d\u000b\u0005\u0005\u001f\u0011)\u0002\u0005\u00037\u0005#Q\u0015b\u0001B\no\t)\u0011I\u001d:bs\"1\u0011q\u001f\u0015A\u0002)\u000b!cZ3u\u000bb,7-\u001e;peV\u0003H-\u0019;fgR\u0011!1\u0004\t\t\u0005;\u0011\u0019#a3\u0003(5\u0011!q\u0004\u0006\u0005\u0005C\t)'A\u0004nkR\f'\r\\3\n\t\t\u0015\"q\u0004\u0002\b\u0011\u0006\u001c\b.T1q!\r\u0011&\u0011F\u0005\u0004\u0005Wa#aD#yK\u000e,Ho\u001c:NKR\u0014\u0018nY:\u0002\tM$x\u000e\u001d"
)
public class ExecutorMetricsPoller implements Logging {
   private volatile TCMP$ TCMP$module;
   private final MemoryManager memoryManager;
   private final long pollingInterval;
   private final Option executorMetricsSource;
   private final ConcurrentHashMap stageTCMP;
   private final ConcurrentHashMap taskMetricPeaks;
   private final Option poller;
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

   public TCMP$ TCMP() {
      if (this.TCMP$module == null) {
         this.TCMP$lzycompute$1();
      }

      return this.TCMP$module;
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public ConcurrentHashMap stageTCMP() {
      return this.stageTCMP;
   }

   private ConcurrentHashMap taskMetricPeaks() {
      return this.taskMetricPeaks;
   }

   private Option poller() {
      return this.poller;
   }

   public void poll() {
      long[] latestMetrics = ExecutorMetrics$.MODULE$.getCurrentMetrics(this.memoryManager);
      this.executorMetricsSource.foreach((x$1) -> {
         $anonfun$poll$1(latestMetrics, x$1);
         return BoxedUnit.UNIT;
      });
      this.stageTCMP().forEachValue(Long.MAX_VALUE, (v) -> updatePeaks$1(v.peaks(), latestMetrics));
      this.taskMetricPeaks().forEachValue(Long.MAX_VALUE, (metrics) -> updatePeaks$1(metrics, latestMetrics));
   }

   public void start() {
      this.poller().foreach((exec) -> {
         Runnable pollingTask = () -> Utils$.MODULE$.logUncaughtExceptions((JFunction0.mcV.sp)() -> this.poll());
         return exec.scheduleAtFixedRate(pollingTask, 0L, this.pollingInterval, TimeUnit.MILLISECONDS);
      });
   }

   public void onTaskStart(final long taskId, final int stageId, final int stageAttemptId) {
      this.taskMetricPeaks().put(BoxesRunTime.boxToLong(taskId), new AtomicLongArray(ExecutorMetricType$.MODULE$.numMetrics()));
      TCMP countAndPeaks = (TCMP)this.stageTCMP().compute(new Tuple2.mcII.sp(stageId, stageAttemptId), (k, v) -> v == null ? this.new TCMP(1L, new AtomicLongArray(ExecutorMetricType$.MODULE$.numMetrics())) : this.new TCMP(v.count() + 1L, v.peaks()));
      this.logDebug((Function0)(() -> "stageTCMP: (" + stageId + ", " + stageAttemptId + ") -> " + countAndPeaks.count()));
   }

   public void onTaskCompletion(final long taskId, final int stageId, final int stageAttemptId) {
      this.stageTCMP().computeIfPresent(new Tuple2.mcII.sp(stageId, stageAttemptId), (stage, countAndPeaks) -> this.decrementCount$1(stage, countAndPeaks));
      this.taskMetricPeaks().remove(BoxesRunTime.boxToLong(taskId));
   }

   public long[] getTaskMetricPeaks(final long taskId) {
      AtomicLongArray currentPeaks = (AtomicLongArray)this.taskMetricPeaks().get(BoxesRunTime.boxToLong(taskId));
      long[] metricPeaks = new long[ExecutorMetricType$.MODULE$.numMetrics()];
      if (currentPeaks != null) {
         ExecutorMetricType$.MODULE$.metricToOffset().foreach((x0$1) -> {
            $anonfun$getTaskMetricPeaks$1(metricPeaks, currentPeaks, x0$1);
            return BoxedUnit.UNIT;
         });
      }

      return metricPeaks;
   }

   public HashMap getExecutorUpdates() {
      HashMap executorUpdates = new HashMap();
      this.stageTCMP().replaceAll((k, v) -> this.getUpdateAndResetPeaks$1(k, v, executorUpdates));
      executorUpdates.foreach((x0$1) -> {
         if (x0$1 != null) {
            Tuple2 k = (Tuple2)x0$1._1();
            return (TCMP)this.stageTCMP().computeIfPresent(k, (kx, v) -> this.removeIfInactive$1(kx, v));
         } else {
            throw new MatchError(x0$1);
         }
      });
      return executorUpdates;
   }

   public void stop() {
      this.poller().foreach((exec) -> BoxesRunTime.boxToBoolean($anonfun$stop$1(exec)));
   }

   private final void TCMP$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TCMP$module == null) {
            this.TCMP$module = new TCMP$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$poll$1(final long[] latestMetrics$1, final ExecutorMetricsSource x$1) {
      x$1.updateMetricsSnapshot(latestMetrics$1);
   }

   private static final void updatePeaks$1(final AtomicLongArray metrics, final long[] latestMetrics$1) {
      .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), metrics.length()).foreach((JFunction1.mcJI.sp)(i) -> metrics.getAndAccumulate(i, latestMetrics$1[i], (x, y) -> scala.math.package..MODULE$.max(x, y)));
   }

   private final TCMP decrementCount$1(final Tuple2 stage, final TCMP countAndPeaks) {
      long countValue = countAndPeaks.count() - 1L;
      scala.Predef..MODULE$.assert(countValue >= 0L, () -> "task count shouldn't below 0");
      this.logDebug((Function0)(() -> {
         int var10000 = stage._1$mcI$sp();
         return "stageTCMP: (" + var10000 + ", " + stage._2$mcI$sp() + ") -> " + countValue;
      }));
      return new TCMP(countValue, countAndPeaks.peaks());
   }

   // $FF: synthetic method
   public static final void $anonfun$getTaskMetricPeaks$1(final long[] metricPeaks$1, final AtomicLongArray currentPeaks$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int i = x0$1._2$mcI$sp();
         metricPeaks$1[i] = currentPeaks$1.get(i);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   private final TCMP getUpdateAndResetPeaks$1(final Tuple2 k, final TCMP v, final HashMap executorUpdates$1) {
      executorUpdates$1.put(k, new ExecutorMetrics(v.peaks()));
      return new TCMP(v.count(), new AtomicLongArray(ExecutorMetricType$.MODULE$.numMetrics()));
   }

   private final TCMP removeIfInactive$1(final Tuple2 k, final TCMP v) {
      if (v.count() == 0L) {
         this.logDebug((Function0)(() -> {
            int var10000 = k._1$mcI$sp();
            return "removing (" + var10000 + ", " + k._2$mcI$sp() + ") from stageTCMP";
         }));
         return null;
      } else {
         return v;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$stop$1(final ScheduledExecutorService exec) {
      exec.shutdown();
      return exec.awaitTermination(10L, TimeUnit.SECONDS);
   }

   public ExecutorMetricsPoller(final MemoryManager memoryManager, final long pollingInterval, final Option executorMetricsSource) {
      this.memoryManager = memoryManager;
      this.pollingInterval = pollingInterval;
      this.executorMetricsSource = executorMetricsSource;
      Logging.$init$(this);
      this.stageTCMP = new ConcurrentHashMap();
      this.taskMetricPeaks = new ConcurrentHashMap();
      this.poller = (Option)(pollingInterval > 0L ? new Some(ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("executor-metrics-poller")) : scala.None..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class TCMP implements Product, Serializable {
      private final long count;
      private final AtomicLongArray peaks;
      // $FF: synthetic field
      public final ExecutorMetricsPoller $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public long count() {
         return this.count;
      }

      public AtomicLongArray peaks() {
         return this.peaks;
      }

      public TCMP copy(final long count, final AtomicLongArray peaks) {
         return this.org$apache$spark$executor$ExecutorMetricsPoller$TCMP$$$outer().new TCMP(count, peaks);
      }

      public long copy$default$1() {
         return this.count();
      }

      public AtomicLongArray copy$default$2() {
         return this.peaks();
      }

      public String productPrefix() {
         return "TCMP";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToLong(this.count());
            }
            case 1 -> {
               return this.peaks();
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof TCMP;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "count";
            }
            case 1 -> {
               return "peaks";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.longHash(this.count()));
         var1 = Statics.mix(var1, Statics.anyHash(this.peaks()));
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label56: {
               if (x$1 instanceof TCMP && ((TCMP)x$1).org$apache$spark$executor$ExecutorMetricsPoller$TCMP$$$outer() == this.org$apache$spark$executor$ExecutorMetricsPoller$TCMP$$$outer()) {
                  TCMP var4 = (TCMP)x$1;
                  if (this.count() == var4.count()) {
                     label46: {
                        AtomicLongArray var10000 = this.peaks();
                        AtomicLongArray var5 = var4.peaks();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label46;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label46;
                        }

                        if (var4.canEqual(this)) {
                           break label56;
                        }
                     }
                  }
               }

               var6 = false;
               return var6;
            }
         }

         var6 = true;
         return var6;
      }

      // $FF: synthetic method
      public ExecutorMetricsPoller org$apache$spark$executor$ExecutorMetricsPoller$TCMP$$$outer() {
         return this.$outer;
      }

      public TCMP(final long count, final AtomicLongArray peaks) {
         this.count = count;
         this.peaks = peaks;
         if (ExecutorMetricsPoller.this == null) {
            throw null;
         } else {
            this.$outer = ExecutorMetricsPoller.this;
            super();
            Product.$init$(this);
         }
      }
   }

   public class TCMP$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final ExecutorMetricsPoller $outer;

      public final String toString() {
         return "TCMP";
      }

      public TCMP apply(final long count, final AtomicLongArray peaks) {
         return this.$outer.new TCMP(count, peaks);
      }

      public Option unapply(final TCMP x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToLong(x$0.count()), x$0.peaks())));
      }

      public TCMP$() {
         if (ExecutorMetricsPoller.this == null) {
            throw null;
         } else {
            this.$outer = ExecutorMetricsPoller.this;
            super();
         }
      }
   }
}
