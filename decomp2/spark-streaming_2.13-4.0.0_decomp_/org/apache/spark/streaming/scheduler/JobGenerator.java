package org.apache.spark.streaming.scheduler;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.streaming.Checkpoint;
import org.apache.spark.streaming.CheckpointWriter;
import org.apache.spark.streaming.DStreamGraph;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.StreamingConf$;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.Time$;
import org.apache.spark.streaming.api.python.PythonDStream$;
import org.apache.spark.streaming.util.RecurringTimer;
import org.apache.spark.util.Clock;
import org.apache.spark.util.EventLoop;
import org.apache.spark.util.ManualClock;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%d!B\u0011#\u0001\u0011b\u0003\u0002C\u001d\u0001\u0005\u0003\u0005\u000b\u0011B\u001e\t\u000b}\u0002A\u0011\u0001!\t\u000f\r\u0003!\u0019!C\u0005\t\"1\u0011\n\u0001Q\u0001\n\u0015CqA\u0013\u0001C\u0002\u0013%1\n\u0003\u0004Q\u0001\u0001\u0006I\u0001\u0014\u0005\b#\u0002\u0011\r\u0011\"\u0003S\u0011\u00191\u0006\u0001)A\u0005'\"9q\u000b\u0001b\u0001\n\u0003A\u0006BB0\u0001A\u0003%\u0011\fC\u0004a\u0001\t\u0007I\u0011B1\t\r\u001d\u0004\u0001\u0015!\u0003c\u0011!A\u0007\u0001#b\u0001\n\u0013I\u0007\u0002C7\u0001\u0011\u000b\u0007I\u0011\u00028\t\u000fI\u0004\u0001\u0019!C\u0005g\"9!\u0010\u0001a\u0001\n\u0013Y\bbBA\u0002\u0001\u0001\u0006K\u0001\u001e\u0005\u000b\u0003\u000b\u0001\u0001\u0019!C\u0001I\u0005\u001d\u0001BCA\b\u0001\u0001\u0007I\u0011\u0001\u0013\u0002\u0012!A\u0011Q\u0003\u0001!B\u0013\tI\u0001C\u0004\u0002 \u0001!\t!!\t\t\u000f\u0005\r\u0002\u0001\"\u0001\u0002&!9\u00111\u0006\u0001\u0005\u0002\u00055\u0002bBA\u001a\u0001\u0011\u0005\u0011Q\u0007\u0005\b\u0003{\u0001A\u0011BA \u0011\u001d\t)\u0005\u0001C\u0005\u0003CAq!a\u0012\u0001\t\u0013\t\t\u0003C\u0004\u0002J\u0001!I!a\u0013\t\u000f\u0005=\u0003\u0001\"\u0003\u0002R!9\u0011Q\u000b\u0001\u0005\n\u0005]\u0003bBA.\u0001\u0011%\u0011Q\f\u0005\b\u0003G\u0002A\u0011BA3\u00051QuNY$f]\u0016\u0014\u0018\r^8s\u0015\t\u0019C%A\u0005tG\",G-\u001e7fe*\u0011QEJ\u0001\ngR\u0014X-Y7j]\u001eT!a\n\u0015\u0002\u000bM\u0004\u0018M]6\u000b\u0005%R\u0013AB1qC\u000eDWMC\u0001,\u0003\ry'oZ\n\u0004\u00015\u001a\u0004C\u0001\u00182\u001b\u0005y#\"\u0001\u0019\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iz#AB!osJ+g\r\u0005\u00025o5\tQG\u0003\u00027M\u0005A\u0011N\u001c;fe:\fG.\u0003\u00029k\t9Aj\\4hS:<\u0017\u0001\u00046pEN\u001b\u0007.\u001a3vY\u0016\u00148\u0001\u0001\t\u0003yuj\u0011AI\u0005\u0003}\t\u0012ABS8c'\u000eDW\rZ;mKJ\fa\u0001P5oSRtDCA!C!\ta\u0004\u0001C\u0003:\u0005\u0001\u00071(A\u0002tg\u000e,\u0012!\u0012\t\u0003\r\u001ek\u0011\u0001J\u0005\u0003\u0011\u0012\u0012\u0001c\u0015;sK\u0006l\u0017N\\4D_:$X\r\u001f;\u0002\tM\u001c8\rI\u0001\u0005G>tg-F\u0001M!\tie*D\u0001'\u0013\tyeEA\u0005Ta\u0006\u00148nQ8oM\u0006)1m\u001c8gA\u0005)qM]1qQV\t1\u000b\u0005\u0002G)&\u0011Q\u000b\n\u0002\r\tN#(/Z1n\u000fJ\f\u0007\u000f[\u0001\u0007OJ\f\u0007\u000f\u001b\u0011\u0002\u000b\rdwnY6\u0016\u0003e\u0003\"AW/\u000e\u0003mS!\u0001\u0018\u0014\u0002\tU$\u0018\u000e\\\u0005\u0003=n\u0013Qa\u00117pG.\faa\u00197pG.\u0004\u0013!\u0002;j[\u0016\u0014X#\u00012\u0011\u0005\r,W\"\u00013\u000b\u0005q#\u0013B\u00014e\u00059\u0011VmY;se&tw\rV5nKJ\fa\u0001^5nKJ\u0004\u0013\u0001E:i_VdGm\u00115fG.\u0004x.\u001b8u+\u0005Q\u0007C\u0001\u0018l\u0013\tawFA\u0004C_>dW-\u00198\u0002!\rDWmY6q_&tGo\u0016:ji\u0016\u0014X#A8\u0011\u0005\u0019\u0003\u0018BA9%\u0005A\u0019\u0005.Z2la>Lg\u000e^,sSR,'/A\u0005fm\u0016tG\u000fT8paV\tA\u000fE\u0002[k^L!A^.\u0003\u0013\u00153XM\u001c;M_>\u0004\bC\u0001\u001fy\u0013\tI(EA\tK_\n<UM\\3sCR|'/\u0012<f]R\fQ\"\u001a<f]Rdun\u001c9`I\u0015\fHC\u0001?\u0000!\tqS0\u0003\u0002\u007f_\t!QK\\5u\u0011!\t\t\u0001EA\u0001\u0002\u0004!\u0018a\u0001=%c\u0005QQM^3oi2{w\u000e\u001d\u0011\u0002%1\f7\u000f\u001e)s_\u000e,7o]3e\u0005\u0006$8\r[\u000b\u0003\u0003\u0013\u00012ARA\u0006\u0013\r\ti\u0001\n\u0002\u0005)&lW-\u0001\fmCN$\bK]8dKN\u001cX\r\u001a\"bi\u000eDw\fJ3r)\ra\u00181\u0003\u0005\n\u0003\u0003\u0019\u0012\u0011!a\u0001\u0003\u0013\t1\u0003\\1tiB\u0013xnY3tg\u0016$')\u0019;dQ\u0002B3\u0001FA\r!\rq\u00131D\u0005\u0004\u0003;y#\u0001\u0003<pY\u0006$\u0018\u000e\\3\u0002\u000bM$\u0018M\u001d;\u0015\u0003q\fAa\u001d;paR\u0019A0a\n\t\r\u0005%b\u00031\u0001k\u0003M\u0001(o\\2fgN\u0014VmY3jm\u0016$G)\u0019;b\u0003EygNQ1uG\"\u001cu.\u001c9mKRLwN\u001c\u000b\u0004y\u0006=\u0002bBA\u0019/\u0001\u0007\u0011\u0011B\u0001\u0005i&lW-\u0001\fp]\u000eCWmY6q_&tGoQ8na2,G/[8o)\u0015a\u0018qGA\u001d\u0011\u001d\t\t\u0004\u0007a\u0001\u0003\u0013Aa!a\u000f\u0019\u0001\u0004Q\u0017\u0001G2mK\u0006\u00148\t[3dWB|\u0017N\u001c;ECR\fG*\u0019;fe\u0006a\u0001O]8dKN\u001cXI^3oiR\u0019A0!\u0011\t\r\u0005\r\u0013\u00041\u0001x\u0003\u0015)g/\u001a8u\u00039\u0019H/\u0019:u\r&\u00148\u000f\u001e+j[\u0016\fqA]3ti\u0006\u0014H/\u0001\u0007hK:,'/\u0019;f\u0015>\u00147\u000fF\u0002}\u0003\u001bBq!!\r\u001d\u0001\u0004\tI!A\u0007dY\u0016\f'/T3uC\u0012\fG/\u0019\u000b\u0004y\u0006M\u0003bBA\u0019;\u0001\u0007\u0011\u0011B\u0001\u0014G2,\u0017M]\"iK\u000e\\\u0007o\\5oi\u0012\u000bG/\u0019\u000b\u0004y\u0006e\u0003bBA\u0019=\u0001\u0007\u0011\u0011B\u0001\rI>\u001c\u0005.Z2la>Lg\u000e\u001e\u000b\u0006y\u0006}\u0013\u0011\r\u0005\b\u0003cy\u0002\u0019AA\u0005\u0011\u0019\tYd\ba\u0001U\u00069R.\u0019:l\u0005\u0006$8\r\u001b$vY2L\bK]8dKN\u001cX\r\u001a\u000b\u0004y\u0006\u001d\u0004bBA\u0019A\u0001\u0007\u0011\u0011\u0002"
)
public class JobGenerator implements Logging {
   private boolean shouldCheckpoint;
   private CheckpointWriter checkpointWriter;
   public final JobScheduler org$apache$spark$streaming$scheduler$JobGenerator$$jobScheduler;
   private final StreamingContext ssc;
   private final SparkConf conf;
   private final DStreamGraph graph;
   private final Clock clock;
   private final RecurringTimer timer;
   private EventLoop eventLoop;
   private volatile Time lastProcessedBatch;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile byte bitmap$0;

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

   private StreamingContext ssc() {
      return this.ssc;
   }

   private SparkConf conf() {
      return this.conf;
   }

   private DStreamGraph graph() {
      return this.graph;
   }

   public Clock clock() {
      return this.clock;
   }

   private RecurringTimer timer() {
      return this.timer;
   }

   private boolean shouldCheckpoint$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.shouldCheckpoint = this.ssc().checkpointDuration() != null && this.ssc().checkpointDir() != null;
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.shouldCheckpoint;
   }

   private boolean shouldCheckpoint() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.shouldCheckpoint$lzycompute() : this.shouldCheckpoint;
   }

   private CheckpointWriter checkpointWriter$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.checkpointWriter = this.shouldCheckpoint() ? new CheckpointWriter(this, this.ssc().conf(), this.ssc().checkpointDir(), this.ssc().sparkContext().hadoopConfiguration()) : null;
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.checkpointWriter;
   }

   private CheckpointWriter checkpointWriter() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.checkpointWriter$lzycompute() : this.checkpointWriter;
   }

   private EventLoop eventLoop() {
      return this.eventLoop;
   }

   private void eventLoop_$eq(final EventLoop x$1) {
      this.eventLoop = x$1;
   }

   public Time lastProcessedBatch() {
      return this.lastProcessedBatch;
   }

   public void lastProcessedBatch_$eq(final Time x$1) {
      this.lastProcessedBatch = x$1;
   }

   public synchronized void start() {
      if (this.eventLoop() == null) {
         this.checkpointWriter();
         this.eventLoop_$eq(new EventLoop() {
            // $FF: synthetic field
            private final JobGenerator $outer;

            public void onReceive(final JobGeneratorEvent event) {
               this.$outer.org$apache$spark$streaming$scheduler$JobGenerator$$processEvent(event);
            }

            public void onError(final Throwable e) {
               this.$outer.org$apache$spark$streaming$scheduler$JobGenerator$$jobScheduler.reportError("Error in job generator", e);
            }

            public {
               if (JobGenerator.this == null) {
                  throw null;
               } else {
                  this.$outer = JobGenerator.this;
               }
            }
         });
         this.eventLoop().start();
         if (this.ssc().isCheckpointPresent()) {
            this.restart();
         } else {
            this.startFirstTime();
         }
      }
   }

   public synchronized void stop(final boolean processReceivedData) {
      if (this.eventLoop() != null) {
         if (processReceivedData) {
            this.logInfo((Function0)(() -> "Stopping JobGenerator gracefully"));
            long timeWhenStopStarted = System.nanoTime();
            long stopTimeoutMs = this.conf().getTimeAsMs(StreamingConf$.MODULE$.GRACEFUL_STOP_TIMEOUT().key(), 10L * this.ssc().graph().batchDuration().milliseconds() + "ms");
            int pollTime = 100;
            this.logInfo((Function0)(() -> "Waiting for all received blocks to be consumed for job generation"));

            while(!this.hasTimedOut$1(timeWhenStopStarted, stopTimeoutMs) && this.org$apache$spark$streaming$scheduler$JobGenerator$$jobScheduler.receiverTracker().hasUnallocatedBlocks()) {
               Thread.sleep((long)pollTime);
            }

            this.logInfo((Function0)(() -> "Waited for all received blocks to be consumed for job generation"));
            long stopTime = this.timer().stop(false);
            this.logInfo((Function0)(() -> "Stopped generation timer"));
            this.logInfo((Function0)(() -> "Waiting for jobs to be processed and checkpoints to be written"));

            while(!this.hasTimedOut$1(timeWhenStopStarted, stopTimeoutMs) && !this.haveAllBatchesBeenProcessed$1(stopTime)) {
               Thread.sleep((long)pollTime);
            }

            this.logInfo((Function0)(() -> "Waited for jobs to be processed and checkpoints to be written"));
            this.graph().stop();
         } else {
            this.logInfo((Function0)(() -> "Stopping JobGenerator immediately"));
            this.timer().stop(true);
            this.graph().stop();
         }

         this.eventLoop().stop();
         if (this.shouldCheckpoint()) {
            this.checkpointWriter().stop();
         }

         this.logInfo((Function0)(() -> "Stopped JobGenerator"));
      }
   }

   public void onBatchCompletion(final Time time) {
      this.eventLoop().post(new ClearMetadata(time));
   }

   public void onCheckpointCompletion(final Time time, final boolean clearCheckpointDataLater) {
      if (clearCheckpointDataLater) {
         this.eventLoop().post(new ClearCheckpointData(time));
      }
   }

   public void org$apache$spark$streaming$scheduler$JobGenerator$$processEvent(final JobGeneratorEvent event) {
      this.logDebug((Function0)(() -> "Got event " + event));
      if (event instanceof GenerateJobs var4) {
         Time time = var4.time();
         this.generateJobs(time);
         BoxedUnit var15 = BoxedUnit.UNIT;
      } else if (event instanceof ClearMetadata var6) {
         Time time = var6.time();
         this.clearMetadata(time);
         BoxedUnit var14 = BoxedUnit.UNIT;
      } else if (event instanceof DoCheckpoint var8) {
         Time time = var8.time();
         boolean clearCheckpointDataLater = var8.clearCheckpointDataLater();
         this.doCheckpoint(time, clearCheckpointDataLater);
         BoxedUnit var13 = BoxedUnit.UNIT;
      } else if (event instanceof ClearCheckpointData var11) {
         Time time = var11.time();
         this.clearCheckpointData(time);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(event);
      }
   }

   private void startFirstTime() {
      Time startTime = new Time(this.timer().getStartTime());
      this.graph().start(startTime.$minus(this.graph().batchDuration()));
      this.timer().start(startTime.milliseconds());
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Started JobGenerator at ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.START_TIME..MODULE$, startTime)})))));
   }

   private void restart() {
      Clock var2 = this.clock();
      if (var2 instanceof ManualClock var3) {
         long lastTime = this.ssc().initialCheckpoint().checkpointTime().milliseconds();
         long jumpTime = BoxesRunTime.unboxToLong(this.ssc().sc().conf().get(StreamingConf$.MODULE$.MANUAL_CLOCK_JUMP()));
         var3.setTime(lastTime + jumpTime);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var14 = BoxedUnit.UNIT;
      }

      Duration batchDuration = this.ssc().graph().batchDuration();
      Time checkpointTime = this.ssc().initialCheckpoint().checkpointTime();
      Time restartTime = new Time(this.timer().getRestartTime(this.graph().zeroTime().milliseconds()));
      Seq downTimes = checkpointTime.until(restartTime, batchDuration);
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Batches during down time (", " batches): "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BATCHES..MODULE$, BoxesRunTime.boxToInteger(downTimes.size()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BATCH_TIMES..MODULE$, downTimes.mkString(","))}))))));
      Time[] pendingTimes = (Time[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.refArrayOps(this.ssc().initialCheckpoint().pendingTimes()), Time$.MODULE$.ordering());
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Batches pending processing ("})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " batches): "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(pendingTimes.length))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PENDING_TIMES..MODULE$, scala.Predef..MODULE$.wrapRefArray(pendingTimes).mkString(","))}))))));
      Time[] timesToReschedule = (Time[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.distinct$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps(pendingTimes), downTimes, scala.reflect.ClassTag..MODULE$.apply(Time.class))), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$restart$3(restartTime, x$1)))))), Time$.MODULE$.ordering());
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Batches to reschedule (", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(timesToReschedule.length))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"batches): ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BATCH_TIMES..MODULE$, scala.Predef..MODULE$.wrapRefArray(timesToReschedule).mkString(","))}))))));
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(timesToReschedule), (time) -> {
         $anonfun$restart$5(this, time);
         return BoxedUnit.UNIT;
      });
      this.timer().start(restartTime.milliseconds());
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Restarted JobGenerator at ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESTART_TIME..MODULE$, restartTime)})))));
   }

   private void generateJobs(final Time time) {
      this.ssc().sparkContext().setLocalProperty(org.apache.spark.rdd.RDD..MODULE$.CHECKPOINT_ALL_MARKED_ANCESTORS(), "true");
      Try var3 = scala.util.Try..MODULE$.apply(() -> {
         this.org$apache$spark$streaming$scheduler$JobGenerator$$jobScheduler.receiverTracker().allocateBlocksToBatch(time);
         return this.graph().generateJobs(time);
      });
      if (var3 instanceof Success var4) {
         Seq jobs = (Seq)var4.value();
         scala.collection.immutable.Map streamIdToInputInfos = this.org$apache$spark$streaming$scheduler$JobGenerator$$jobScheduler.inputInfoTracker().getInfo(time);
         this.org$apache$spark$streaming$scheduler$JobGenerator$$jobScheduler.submitJobSet(new JobSet(time, jobs, streamIdToInputInfos));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         if (!(var3 instanceof Failure)) {
            throw new MatchError(var3);
         }

         Failure var7 = (Failure)var3;
         Throwable e = var7.exception();
         this.org$apache$spark$streaming$scheduler$JobGenerator$$jobScheduler.reportError("Error generating jobs for time " + time, e);
         PythonDStream$.MODULE$.stopStreamingContextIfPythonProcessIsDead(e);
         BoxedUnit var9 = BoxedUnit.UNIT;
      }

      this.eventLoop().post(new DoCheckpoint(time, false));
   }

   private void clearMetadata(final Time time) {
      this.ssc().graph().clearMetadata(time);
      if (this.shouldCheckpoint()) {
         this.eventLoop().post(new DoCheckpoint(time, true));
      } else {
         Duration maxRememberDuration = this.graph().getMaxInputStreamRememberDuration();
         this.org$apache$spark$streaming$scheduler$JobGenerator$$jobScheduler.receiverTracker().cleanupOldBlocksAndBatches(time.$minus(maxRememberDuration));
         this.org$apache$spark$streaming$scheduler$JobGenerator$$jobScheduler.inputInfoTracker().cleanup(time.$minus(maxRememberDuration));
         this.markBatchFullyProcessed(time);
      }
   }

   private void clearCheckpointData(final Time time) {
      this.ssc().graph().clearCheckpointData(time);
      Duration maxRememberDuration = this.graph().getMaxInputStreamRememberDuration();
      this.org$apache$spark$streaming$scheduler$JobGenerator$$jobScheduler.receiverTracker().cleanupOldBlocksAndBatches(time.$minus(maxRememberDuration));
      this.org$apache$spark$streaming$scheduler$JobGenerator$$jobScheduler.inputInfoTracker().cleanup(time.$minus(maxRememberDuration));
      this.markBatchFullyProcessed(time);
   }

   private void doCheckpoint(final Time time, final boolean clearCheckpointDataLater) {
      if (this.shouldCheckpoint() && time.$minus(this.graph().zeroTime()).isMultipleOf(this.ssc().checkpointDuration())) {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Checkpointing graph for time ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, time)})))));
         this.ssc().graph().updateCheckpointData(time);
         this.checkpointWriter().write(new Checkpoint(this.ssc(), time), clearCheckpointDataLater);
      } else if (clearCheckpointDataLater) {
         this.markBatchFullyProcessed(time);
      }
   }

   private void markBatchFullyProcessed(final Time time) {
      this.lastProcessedBatch_$eq(time);
   }

   // $FF: synthetic method
   private static final Clock liftedTree1$1(final String clockClass$1) {
      Clock var10000;
      try {
         var10000 = (Clock)org.apache.spark.util.Utils..MODULE$.classForName(clockClass$1, org.apache.spark.util.Utils..MODULE$.classForName$default$2(), org.apache.spark.util.Utils..MODULE$.classForName$default$3()).getConstructor().newInstance();
      } catch (Throwable var5) {
         if (!(var5 instanceof ClassNotFoundException) || !clockClass$1.startsWith("org.apache.spark.streaming")) {
            throw var5;
         }

         String newClockClass = clockClass$1.replace("org.apache.spark.streaming", "org.apache.spark");
         var10000 = (Clock)org.apache.spark.util.Utils..MODULE$.classForName(newClockClass, org.apache.spark.util.Utils..MODULE$.classForName$default$2(), org.apache.spark.util.Utils..MODULE$.classForName$default$3()).getConstructor().newInstance();
      }

      return var10000;
   }

   private final boolean hasTimedOut$1(final long timeWhenStopStarted$1, final long stopTimeoutMs$1) {
      long diff = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - timeWhenStopStarted$1);
      boolean timedOut = diff > stopTimeoutMs$1;
      if (timedOut) {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Timed out while stopping the job generator "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(timeout = ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIMEOUT..MODULE$, BoxesRunTime.boxToLong(stopTimeoutMs$1))}))))));
      }

      return timedOut;
   }

   private final boolean haveAllBatchesBeenProcessed$1(final long stopTime$1) {
      return this.lastProcessedBatch() != null && this.lastProcessedBatch().milliseconds() == stopTime$1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$restart$3(final Time restartTime$1, final Time x$1) {
      return x$1.$less(restartTime$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$restart$5(final JobGenerator $this, final Time time) {
      $this.org$apache$spark$streaming$scheduler$JobGenerator$$jobScheduler.receiverTracker().allocateBlocksToBatch(time);
      $this.org$apache$spark$streaming$scheduler$JobGenerator$$jobScheduler.submitJobSet(new JobSet(time, $this.graph().generateJobs(time), JobSet$.MODULE$.apply$default$3()));
   }

   public JobGenerator(final JobScheduler jobScheduler) {
      this.org$apache$spark$streaming$scheduler$JobGenerator$$jobScheduler = jobScheduler;
      Logging.$init$(this);
      this.ssc = jobScheduler.ssc();
      this.conf = this.ssc().conf();
      this.graph = this.ssc().graph();
      String clockClass = this.ssc().sc().conf().get("spark.streaming.clock", "org.apache.spark.util.SystemClock");
      this.clock = liftedTree1$1(clockClass);
      this.timer = new RecurringTimer(this.clock(), this.ssc().graph().batchDuration().milliseconds(), (JFunction1.mcVJ.sp)(longTime) -> this.eventLoop().post(new GenerateJobs(new Time(longTime))), "JobGenerator");
      this.eventLoop = null;
      this.lastProcessedBatch = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
