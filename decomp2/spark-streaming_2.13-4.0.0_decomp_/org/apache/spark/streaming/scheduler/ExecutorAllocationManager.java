package org.apache.spark.streaming.scheduler;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.ExecutorAllocationClient;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.scheduler.ExecutorDecommissionInfo;
import org.apache.spark.streaming.util.RecurringTimer;
import org.apache.spark.util.Clock;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ud!\u0002\u0014(\u0001%\n\u0004\u0002\u0003\"\u0001\u0005\u0003\u0005\u000b\u0011\u0002#\t\u0011!\u0003!\u0011!Q\u0001\n%C\u0001\u0002\u0014\u0001\u0003\u0002\u0003\u0006I!\u0014\u0005\t!\u0002\u0011\t\u0011)A\u0005#\"AA\u000b\u0001B\u0001B\u0003%Q\u000bC\u0003\\\u0001\u0011\u0005A\fC\u0004d\u0001\t\u0007I\u0011\u00023\t\r\u0015\u0004\u0001\u0015!\u0003R\u0011\u001d1\u0007A1A\u0005\n\u001dDaa\u001b\u0001!\u0002\u0013A\u0007b\u00027\u0001\u0005\u0004%Ia\u001a\u0005\u0007[\u0002\u0001\u000b\u0011\u00025\t\u000f9\u0004!\u0019!C\u0005_\"11\u000f\u0001Q\u0001\nADq\u0001\u001e\u0001C\u0002\u0013%q\u000e\u0003\u0004v\u0001\u0001\u0006I\u0001\u001d\u0005\bm\u0002\u0011\r\u0011\"\u0003x\u0011\u0019i\b\u0001)A\u0005q\"9a\u0010\u0001a\u0001\n\u0013!\u0007\u0002C@\u0001\u0001\u0004%I!!\u0001\t\u000f\u00055\u0001\u0001)Q\u0005#\"A\u0011q\u0003\u0001A\u0002\u0013%q\u000eC\u0005\u0002\u001a\u0001\u0001\r\u0011\"\u0003\u0002\u001c!9\u0011q\u0004\u0001!B\u0013\u0001\bbBA\u0012\u0001\u0011\u0005\u0011Q\u0005\u0005\b\u0003O\u0001A\u0011AA\u0013\u0011\u001d\tI\u0003\u0001C\u0005\u0003KAq!a\u000b\u0001\t\u0013\ti\u0003C\u0004\u00024\u0001!I!!\n\t\u000f\u0005U\u0002\u0001\"\u0003\u00028!9\u0011Q\b\u0001\u0005\n\u0005\u0015\u0002bBA \u0001\u0011\u0005\u0013\u0011I\u0004\t\u0003\u001b:\u0003\u0012A\u0015\u0002P\u00199ae\nE\u0001S\u0005E\u0003BB.#\t\u0003\t\u0019\u0006C\u0004\u0002V\t\"\t!a\u0016\t\u000f\u0005\u0005$\u0005\"\u0001\u0002d\tIR\t_3dkR|'/\u00117m_\u000e\fG/[8o\u001b\u0006t\u0017mZ3s\u0015\tA\u0013&A\u0005tG\",G-\u001e7fe*\u0011!fK\u0001\ngR\u0014X-Y7j]\u001eT!\u0001L\u0017\u0002\u000bM\u0004\u0018M]6\u000b\u00059z\u0013AB1qC\u000eDWMC\u00011\u0003\ry'oZ\n\u0005\u0001IBD\b\u0005\u00024m5\tAGC\u00016\u0003\u0015\u00198-\u00197b\u0013\t9DG\u0001\u0004B]f\u0014VM\u001a\t\u0003sij\u0011aJ\u0005\u0003w\u001d\u0012\u0011c\u0015;sK\u0006l\u0017N\\4MSN$XM\\3s!\ti\u0004)D\u0001?\u0015\ty4&\u0001\u0005j]R,'O\\1m\u0013\t\teHA\u0004M_\u001e<\u0017N\\4\u0002\r\rd\u0017.\u001a8u\u0007\u0001\u0001\"!\u0012$\u000e\u0003-J!aR\u0016\u00031\u0015CXmY;u_J\fE\u000e\\8dCRLwN\\\"mS\u0016tG/A\bsK\u000e,\u0017N^3s)J\f7m[3s!\tI$*\u0003\u0002LO\ty!+Z2fSZ,'\u000f\u0016:bG.,'/\u0001\u0003d_:4\u0007CA#O\u0013\ty5FA\u0005Ta\u0006\u00148nQ8oM\u0006y!-\u0019;dQ\u0012+(/\u0019;j_:l5\u000f\u0005\u00024%&\u00111\u000b\u000e\u0002\u0005\u0019>tw-A\u0003dY>\u001c7\u000e\u0005\u0002W36\tqK\u0003\u0002YW\u0005!Q\u000f^5m\u0013\tQvKA\u0003DY>\u001c7.\u0001\u0004=S:LGO\u0010\u000b\u0007;z{\u0006-\u00192\u0011\u0005e\u0002\u0001\"\u0002\"\u0007\u0001\u0004!\u0005\"\u0002%\u0007\u0001\u0004I\u0005\"\u0002'\u0007\u0001\u0004i\u0005\"\u0002)\u0007\u0001\u0004\t\u0006\"\u0002+\u0007\u0001\u0004)\u0016aE:dC2LgnZ%oi\u0016\u0014h/\u00197TK\u000e\u001cX#A)\u0002)M\u001c\u0017\r\\5oO&sG/\u001a:wC2\u001cVmY:!\u00039\u00198-\u00197j]\u001e,\u0006OU1uS>,\u0012\u0001\u001b\t\u0003g%L!A\u001b\u001b\u0003\r\u0011{WO\u00197f\u0003=\u00198-\u00197j]\u001e,\u0006OU1uS>\u0004\u0013\u0001E:dC2Lgn\u001a#po:\u0014\u0016\r^5p\u0003E\u00198-\u00197j]\u001e$un\u001e8SCRLw\u000eI\u0001\u0010[&tg*^7Fq\u0016\u001cW\u000f^8sgV\t\u0001\u000f\u0005\u00024c&\u0011!\u000f\u000e\u0002\u0004\u0013:$\u0018\u0001E7j]:+X.\u0012=fGV$xN]:!\u0003=i\u0017\r\u001f(v[\u0016CXmY;u_J\u001c\u0018\u0001E7bq:+X.\u0012=fGV$xN]:!\u0003\u0015!\u0018.\\3s+\u0005A\bCA=|\u001b\u0005Q(B\u0001-*\u0013\ta(P\u0001\bSK\u000e,(O]5oORKW.\u001a:\u0002\rQLW.\u001a:!\u0003A\u0011\u0017\r^2i!J|7\rV5nKN+X.\u0001\u000bcCR\u001c\u0007\u000e\u0015:pGRKW.Z*v[~#S-\u001d\u000b\u0005\u0003\u0007\tI\u0001E\u00024\u0003\u000bI1!a\u00025\u0005\u0011)f.\u001b;\t\u0011\u0005-A#!AA\u0002E\u000b1\u0001\u001f\u00132\u0003E\u0011\u0017\r^2i!J|7\rV5nKN+X\u000e\t\u0015\u0004+\u0005E\u0001cA\u001a\u0002\u0014%\u0019\u0011Q\u0003\u001b\u0003\u0011Y|G.\u0019;jY\u0016\f!CY1uG\"\u0004&o\\2US6,7i\\;oi\u00061\"-\u0019;dQB\u0013xn\u0019+j[\u0016\u001cu.\u001e8u?\u0012*\u0017\u000f\u0006\u0003\u0002\u0004\u0005u\u0001\u0002CA\u0006/\u0005\u0005\t\u0019\u00019\u0002'\t\fGo\u00195Qe>\u001cG+[7f\u0007>,h\u000e\u001e\u0011)\u0007a\t\t\"A\u0003ti\u0006\u0014H\u000f\u0006\u0002\u0002\u0004\u0005!1\u000f^8q\u0003Ai\u0017M\\1hK\u0006cGn\\2bi&|g.\u0001\tsKF,Xm\u001d;Fq\u0016\u001cW\u000f^8sgR!\u00111AA\u0018\u0011\u0019\t\t\u0004\ba\u0001a\u0006ya.^7OK^,\u00050Z2vi>\u00148/\u0001\u0007lS2dW\t_3dkR|'/\u0001\tbI\u0012\u0014\u0015\r^2i!J|7\rV5nKR!\u00111AA\u001d\u0011\u0019\tYD\ba\u0001#\u00061A/[7f\u001bN\f\u0001C^1mS\u0012\fG/Z*fiRLgnZ:\u0002!=t')\u0019;dQ\u000e{W\u000e\u001d7fi\u0016$G\u0003BA\u0002\u0003\u0007Bq!!\u0012!\u0001\u0004\t9%\u0001\bcCR\u001c\u0007nQ8na2,G/\u001a3\u0011\u0007e\nI%C\u0002\u0002L\u001d\u0012qd\u0015;sK\u0006l\u0017N\\4MSN$XM\\3s\u0005\u0006$8\r[\"p[BdW\r^3e\u0003e)\u00050Z2vi>\u0014\u0018\t\u001c7pG\u0006$\u0018n\u001c8NC:\fw-\u001a:\u0011\u0005e\u00123c\u0001\u00123yQ\u0011\u0011qJ\u0001\u001bSN$\u0015P\\1nS\u000e\fE\u000e\\8dCRLwN\\#oC\ndW\r\u001a\u000b\u0005\u00033\ny\u0006E\u00024\u00037J1!!\u00185\u0005\u001d\u0011un\u001c7fC:DQ\u0001\u0014\u0013A\u00025\u000bqb\u0019:fCR,\u0017JZ#oC\ndW\r\u001a\u000b\r\u0003K\nY'!\u001c\u0002p\u0005E\u00141\u000f\t\u0005g\u0005\u001dT,C\u0002\u0002jQ\u0012aa\u00149uS>t\u0007\"\u0002\"&\u0001\u0004!\u0005\"\u0002%&\u0001\u0004I\u0005\"\u0002'&\u0001\u0004i\u0005\"\u0002)&\u0001\u0004\t\u0006\"\u0002+&\u0001\u0004)\u0006"
)
public class ExecutorAllocationManager implements StreamingListener, Logging {
   private final ExecutorAllocationClient client;
   private final ReceiverTracker receiverTracker;
   private final SparkConf conf;
   private final long batchDurationMs;
   private final long scalingIntervalSecs;
   private final double scalingUpRatio;
   private final double scalingDownRatio;
   private final int minNumExecutors;
   private final int maxNumExecutors;
   private final RecurringTimer timer;
   private volatile long batchProcTimeSum;
   private volatile int batchProcTimeCount;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Option createIfEnabled(final ExecutorAllocationClient client, final ReceiverTracker receiverTracker, final SparkConf conf, final long batchDurationMs, final Clock clock) {
      return ExecutorAllocationManager$.MODULE$.createIfEnabled(client, receiverTracker, conf, batchDurationMs, clock);
   }

   public static boolean isDynamicAllocationEnabled(final SparkConf conf) {
      return ExecutorAllocationManager$.MODULE$.isDynamicAllocationEnabled(conf);
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

   public void onStreamingStarted(final StreamingListenerStreamingStarted streamingStarted) {
      StreamingListener.onStreamingStarted$(this, streamingStarted);
   }

   public void onReceiverStarted(final StreamingListenerReceiverStarted receiverStarted) {
      StreamingListener.onReceiverStarted$(this, receiverStarted);
   }

   public void onReceiverError(final StreamingListenerReceiverError receiverError) {
      StreamingListener.onReceiverError$(this, receiverError);
   }

   public void onReceiverStopped(final StreamingListenerReceiverStopped receiverStopped) {
      StreamingListener.onReceiverStopped$(this, receiverStopped);
   }

   public void onBatchSubmitted(final StreamingListenerBatchSubmitted batchSubmitted) {
      StreamingListener.onBatchSubmitted$(this, batchSubmitted);
   }

   public void onBatchStarted(final StreamingListenerBatchStarted batchStarted) {
      StreamingListener.onBatchStarted$(this, batchStarted);
   }

   public void onOutputOperationStarted(final StreamingListenerOutputOperationStarted outputOperationStarted) {
      StreamingListener.onOutputOperationStarted$(this, outputOperationStarted);
   }

   public void onOutputOperationCompleted(final StreamingListenerOutputOperationCompleted outputOperationCompleted) {
      StreamingListener.onOutputOperationCompleted$(this, outputOperationCompleted);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private long scalingIntervalSecs() {
      return this.scalingIntervalSecs;
   }

   private double scalingUpRatio() {
      return this.scalingUpRatio;
   }

   private double scalingDownRatio() {
      return this.scalingDownRatio;
   }

   private int minNumExecutors() {
      return this.minNumExecutors;
   }

   private int maxNumExecutors() {
      return this.maxNumExecutors;
   }

   private RecurringTimer timer() {
      return this.timer;
   }

   private long batchProcTimeSum() {
      return this.batchProcTimeSum;
   }

   private void batchProcTimeSum_$eq(final long x$1) {
      this.batchProcTimeSum = x$1;
   }

   private int batchProcTimeCount() {
      return this.batchProcTimeCount;
   }

   private void batchProcTimeCount_$eq(final int x$1) {
      this.batchProcTimeCount = x$1;
   }

   public void start() {
      this.timer().start();
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"ExecutorAllocationManager started with ratios = "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"[", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SCALING_UP_RATIO..MODULE$, BoxesRunTime.boxToDouble(this.scalingUpRatio()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "] and interval = "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SCALING_DOWN_RATIO..MODULE$, BoxesRunTime.boxToDouble(this.scalingDownRatio()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " sec"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.INTERVAL..MODULE$, BoxesRunTime.boxToLong(this.scalingIntervalSecs()))}))))));
   }

   public void stop() {
      this.timer().stop(true);
      this.logInfo((Function0)(() -> "ExecutorAllocationManager stopped"));
   }

   private synchronized void manageAllocation() {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Managing executor allocation with ratios = ["})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SCALING_UP_RATIO..MODULE$, BoxesRunTime.boxToDouble(this.scalingUpRatio()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "]"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SCALING_DOWN_RATIO..MODULE$, BoxesRunTime.boxToDouble(this.scalingDownRatio()))}))))));
      if (this.batchProcTimeCount() > 0) {
         long averageBatchProcTime = this.batchProcTimeSum() / (long)this.batchProcTimeCount();
         double ratio = (double)averageBatchProcTime / (double)this.batchDurationMs;
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Average: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.AVG_BATCH_PROC_TIME..MODULE$, BoxesRunTime.boxToLong(averageBatchProcTime))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"ratio = ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RATIO..MODULE$, BoxesRunTime.boxToDouble(ratio))}))))));
         if (ratio >= this.scalingUpRatio()) {
            this.logDebug((Function0)(() -> "Requesting executors"));
            int numNewExecutors = scala.math.package..MODULE$.max((int)scala.math.package..MODULE$.round(ratio), 1);
            this.requestExecutors(numNewExecutors);
         } else if (ratio <= this.scalingDownRatio()) {
            this.logDebug((Function0)(() -> "Killing executors"));
            this.killExecutor();
         }
      }

      this.batchProcTimeSum_$eq(0L);
      this.batchProcTimeCount_$eq(0);
   }

   private void requestExecutors(final int numNewExecutors) {
      scala.Predef..MODULE$.require(numNewExecutors >= 1);
      Seq allExecIds = this.client.getExecutorIds();
      this.logDebug((Function0)(() -> {
         int var10000 = allExecIds.size();
         return "Executors (" + var10000 + ") = " + allExecIds;
      }));
      int targetTotalExecutors = scala.math.package..MODULE$.max(scala.math.package..MODULE$.min(this.maxNumExecutors(), allExecIds.size() + numNewExecutors), this.minNumExecutors());
      this.client.requestTotalExecutors((scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(org.apache.spark.resource.ResourceProfile..MODULE$.DEFAULT_RESOURCE_PROFILE_ID())), BoxesRunTime.boxToInteger(targetTotalExecutors))}))), (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(org.apache.spark.resource.ResourceProfile..MODULE$.DEFAULT_RESOURCE_PROFILE_ID())), BoxesRunTime.boxToInteger(0))}))), scala.Predef..MODULE$.Map().empty());
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Requested total ", " executors"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_EXECUTORS..MODULE$, BoxesRunTime.boxToInteger(targetTotalExecutors))})))));
   }

   private void killExecutor() {
      Seq allExecIds = this.client.getExecutorIds();
      this.logDebug((Function0)(() -> {
         int var10000 = allExecIds.size();
         return "Executors (" + var10000 + ") = " + allExecIds;
      }));
      if (allExecIds.nonEmpty() && allExecIds.size() > this.minNumExecutors()) {
         Seq execIdsWithReceivers = ((IterableOnceOps)this.receiverTracker.allocatedExecutors().values().flatten(scala.Predef..MODULE$.$conforms())).toSeq();
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executors with receivers (", "): "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_EXECUTORS..MODULE$, BoxesRunTime.boxToInteger(execIdsWithReceivers.size()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_IDS..MODULE$, execIdsWithReceivers)}))))));
         Seq removableExecIds = (Seq)allExecIds.diff(execIdsWithReceivers);
         this.logDebug((Function0)(() -> {
            int var10000 = removableExecIds.size();
            return "Removable executors (" + var10000 + "): " + removableExecIds;
         }));
         if (removableExecIds.nonEmpty()) {
            String execIdToRemove = (String)removableExecIds.apply(scala.util.Random..MODULE$.nextInt(removableExecIds.size()));
            if (BoxesRunTime.unboxToBoolean(this.conf.get(org.apache.spark.internal.config.package..MODULE$.DECOMMISSION_ENABLED()))) {
               this.client.decommissionExecutor(execIdToRemove, new ExecutorDecommissionInfo("spark scale down", scala.None..MODULE$), true, this.client.decommissionExecutor$default$4());
            } else {
               this.client.killExecutor(execIdToRemove);
            }

            this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Requested to kill executor ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, execIdToRemove)})))));
         } else {
            this.logInfo((Function0)(() -> "No non-receiver executors to kill"));
         }
      } else {
         this.logInfo((Function0)(() -> "No available executor to kill"));
      }
   }

   private synchronized void addBatchProcTime(final long timeMs) {
      this.batchProcTimeSum_$eq(this.batchProcTimeSum() + timeMs);
      this.batchProcTimeCount_$eq(this.batchProcTimeCount() + 1);
      this.logDebug((Function0)(() -> "Added batch processing time " + timeMs + ", sum = " + this.batchProcTimeSum() + ", count = " + this.batchProcTimeCount()));
   }

   private void validateSettings() {
      scala.Predef..MODULE$.require(this.scalingUpRatio() > this.scalingDownRatio(), () -> {
         String var10000 = org.apache.spark.internal.config.Streaming..MODULE$.STREAMING_DYN_ALLOCATION_SCALING_UP_RATIO().key();
         return "Config " + var10000 + " must be more than config " + org.apache.spark.internal.config.Streaming..MODULE$.STREAMING_DYN_ALLOCATION_SCALING_DOWN_RATIO().key();
      });
      if (this.conf.contains(org.apache.spark.internal.config.Streaming..MODULE$.STREAMING_DYN_ALLOCATION_MIN_EXECUTORS().key()) && this.conf.contains(org.apache.spark.internal.config.Streaming..MODULE$.STREAMING_DYN_ALLOCATION_MAX_EXECUTORS().key())) {
         scala.Predef..MODULE$.require(this.maxNumExecutors() >= this.minNumExecutors(), () -> {
            String var10000 = org.apache.spark.internal.config.Streaming..MODULE$.STREAMING_DYN_ALLOCATION_MAX_EXECUTORS().key();
            return "Config " + var10000 + " must be more than config " + org.apache.spark.internal.config.Streaming..MODULE$.STREAMING_DYN_ALLOCATION_MIN_EXECUTORS().key();
         });
      }
   }

   public void onBatchCompleted(final StreamingListenerBatchCompleted batchCompleted) {
      this.logDebug((Function0)(() -> "onBatchCompleted called: " + batchCompleted));
      if (!batchCompleted.batchInfo().outputOperationInfos().values().exists((x$2) -> BoxesRunTime.boxToBoolean($anonfun$onBatchCompleted$2(x$2)))) {
         batchCompleted.batchInfo().processingDelay().foreach((JFunction1.mcVJ.sp)(timeMs) -> this.addBatchProcTime(timeMs));
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onBatchCompleted$2(final OutputOperationInfo x$2) {
      return x$2.failureReason().nonEmpty();
   }

   public ExecutorAllocationManager(final ExecutorAllocationClient client, final ReceiverTracker receiverTracker, final SparkConf conf, final long batchDurationMs, final Clock clock) {
      this.client = client;
      this.receiverTracker = receiverTracker;
      this.conf = conf;
      this.batchDurationMs = batchDurationMs;
      StreamingListener.$init$(this);
      Logging.$init$(this);
      this.scalingIntervalSecs = BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.Streaming..MODULE$.STREAMING_DYN_ALLOCATION_SCALING_INTERVAL()));
      this.scalingUpRatio = BoxesRunTime.unboxToDouble(conf.get(org.apache.spark.internal.config.Streaming..MODULE$.STREAMING_DYN_ALLOCATION_SCALING_UP_RATIO()));
      this.scalingDownRatio = BoxesRunTime.unboxToDouble(conf.get(org.apache.spark.internal.config.Streaming..MODULE$.STREAMING_DYN_ALLOCATION_SCALING_DOWN_RATIO()));
      this.minNumExecutors = BoxesRunTime.unboxToInt(((Option)conf.get(org.apache.spark.internal.config.Streaming..MODULE$.STREAMING_DYN_ALLOCATION_MIN_EXECUTORS())).getOrElse((JFunction0.mcI.sp)() -> scala.math.package..MODULE$.max(1, this.receiverTracker.numReceivers())));
      this.maxNumExecutors = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.Streaming..MODULE$.STREAMING_DYN_ALLOCATION_MAX_EXECUTORS()));
      this.timer = new RecurringTimer(clock, this.scalingIntervalSecs() * 1000L, (JFunction1.mcVJ.sp)(x$1) -> this.manageAllocation(), "streaming-executor-allocation-manager");
      this.batchProcTimeSum = 0L;
      this.batchProcTimeCount = 0;
      this.validateSettings();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
