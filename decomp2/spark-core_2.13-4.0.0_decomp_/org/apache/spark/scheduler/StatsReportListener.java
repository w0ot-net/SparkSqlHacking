package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.executor.TaskMetrics;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.util.Distribution;
import org.apache.spark.util.Distribution$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Buffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uh\u0001\u0002\u0012$\u00011BQa\u000e\u0001\u0005\u0002aBqA\u000f\u0001C\u0002\u0013%1\b\u0003\u0004T\u0001\u0001\u0006I\u0001\u0010\u0005\u0006)\u0002!\t%\u0016\u0005\u0006=\u0002!\te\u0018\u0005\u0006K\u0002!IAZ\u0004\u0007}\u000eB\t!J@\u0007\u000f\t\u001a\u0003\u0012A\u0013\u0002\u0002!1q\u0007\u0003C\u0001\u0003\u0013A\u0011\"a\u0003\t\u0005\u0004%\t!!\u0004\t\u0011\u0005m\u0001\u0002)A\u0005\u0003\u001fA\u0011\"!\b\t\u0005\u0004%\t!a\b\t\u0011\u0005%\u0002\u0002)A\u0005\u0003CA\u0011\"a\u000b\t\u0005\u0004%\t!!\f\t\u0011\u0005u\u0002\u0002)A\u0005\u0003_Aq!a\u0010\t\t\u0003\t\t\u0005C\u0004\u0002t!!\t!!\u001e\t\u000f\u0005\r\u0005\u0002\"\u0001\u0002\u0006\"9\u00111\u0011\u0005\u0005\u0002\u0005e\u0005bBAB\u0011\u0011\u0005\u00111\u0015\u0005\b\u0003\u0007CA\u0011AAW\u0011\u001d\t9\f\u0003C\u0001\u0003sCq!a.\t\t\u0003\t\t\rC\u0004\u00028\"!\t!a2\t\u000f\u0005=\u0007\u0002\"\u0001\u0002R\"9\u0011q\u001a\u0005\u0005\u0002\u0005]\u0007\"CAp\u0011\t\u0007I\u0011AAq\u0011!\t\u0019\u000f\u0003Q\u0001\n\u0005u\u0004\"CAs\u0011\t\u0007I\u0011AAq\u0011!\t9\u000f\u0003Q\u0001\n\u0005u\u0004\"CAu\u0011\t\u0007I\u0011AAq\u0011!\tY\u000f\u0003Q\u0001\n\u0005u\u0004bBAw\u0011\u0011\u0005\u0011q\u001e\u0002\u0014'R\fGo\u001d*fa>\u0014H\u000fT5ti\u0016tWM\u001d\u0006\u0003I\u0015\n\u0011b]2iK\u0012,H.\u001a:\u000b\u0005\u0019:\u0013!B:qCJ\\'B\u0001\u0015*\u0003\u0019\t\u0007/Y2iK*\t!&A\u0002pe\u001e\u001c\u0001aE\u0002\u0001[E\u0002\"AL\u0018\u000e\u0003\rJ!\u0001M\u0012\u0003\u001bM\u0003\u0018M]6MSN$XM\\3s!\t\u0011T'D\u00014\u0015\t!T%\u0001\u0005j]R,'O\\1m\u0013\t14GA\u0004M_\u001e<\u0017N\\4\u0002\rqJg.\u001b;?)\u0005I\u0004C\u0001\u0018\u0001\u0003=!\u0018m]6J]\u001a|W*\u001a;sS\u000e\u001cX#\u0001\u001f\u0011\u0007u\"e)D\u0001?\u0015\ty\u0004)A\u0004nkR\f'\r\\3\u000b\u0005\u0005\u0013\u0015AC2pY2,7\r^5p]*\t1)A\u0003tG\u0006d\u0017-\u0003\u0002F}\t1!)\u001e4gKJ\u0004Ba\u0012%K\u001b6\t!)\u0003\u0002J\u0005\n1A+\u001e9mKJ\u0002\"AL&\n\u00051\u001b#\u0001\u0003+bg.LeNZ8\u0011\u00059\u000bV\"A(\u000b\u0005A+\u0013\u0001C3yK\u000e,Ho\u001c:\n\u0005I{%a\u0003+bg.lU\r\u001e:jGN\f\u0001\u0003^1tW&sgm\\'fiJL7m\u001d\u0011\u0002\u0013=tG+Y:l\u000b:$GC\u0001,Z!\t9u+\u0003\u0002Y\u0005\n!QK\\5u\u0011\u0015QF\u00011\u0001\\\u0003\u001d!\u0018m]6F]\u0012\u0004\"A\f/\n\u0005u\u001b#\u0001F*qCJ\\G*[:uK:,'\u000fV1tW\u0016sG-\u0001\tp]N#\u0018mZ3D_6\u0004H.\u001a;fIR\u0011a\u000b\u0019\u0005\u0006C\u0016\u0001\rAY\u0001\u000fgR\fw-Z\"p[BdW\r^3e!\tq3-\u0003\u0002eG\tY2\u000b]1sW2K7\u000f^3oKJ\u001cF/Y4f\u0007>l\u0007\u000f\\3uK\u0012\fqbZ3u'R\fG/^:EKR\f\u0017\u000e\u001c\u000b\u0003OJ\u0004\"\u0001[8\u000f\u0005%l\u0007C\u00016C\u001b\u0005Y'B\u00017,\u0003\u0019a$o\\8u}%\u0011aNQ\u0001\u0007!J,G-\u001a4\n\u0005A\f(AB*ue&twM\u0003\u0002o\u0005\")1O\u0002a\u0001i\u0006!\u0011N\u001c4p!\tqS/\u0003\u0002wG\tI1\u000b^1hK&sgm\u001c\u0015\u0003\u0001a\u0004\"!\u001f?\u000e\u0003iT!a_\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002~u\naA)\u001a<fY>\u0004XM]!qS\u0006\u00192\u000b^1ugJ+\u0007o\u001c:u\u0019&\u001cH/\u001a8feB\u0011a\u0006C\n\u0005\u0011\u0005\r\u0011\u0007E\u0002H\u0003\u000bI1!a\u0002C\u0005\u0019\te.\u001f*fMR\tq0A\u0006qKJ\u001cWM\u001c;jY\u0016\u001cXCAA\b!\u00159\u0015\u0011CA\u000b\u0013\r\t\u0019B\u0011\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004\u000f\u0006]\u0011bAA\r\u0005\n\u0019\u0011J\u001c;\u0002\u0019A,'oY3oi&dWm\u001d\u0011\u0002\u001bA\u0014xNY1cS2LG/[3t+\t\t\t\u0003E\u0003H\u0003#\t\u0019\u0003E\u0002H\u0003KI1!a\nC\u0005\u0019!u.\u001e2mK\u0006q\u0001O]8cC\nLG.\u001b;jKN\u0004\u0013!\u00059fe\u000e,g\u000e^5mKNDU-\u00193feV\u0011\u0011q\u0006\t\u0005\u0003c\tY$\u0004\u0002\u00024)!\u0011QGA\u001c\u0003\u0011a\u0017M\\4\u000b\u0005\u0005e\u0012\u0001\u00026bm\u0006L1\u0001]A\u001a\u0003I\u0001XM]2f]RLG.Z:IK\u0006$WM\u001d\u0011\u00023\u0015DHO]1di\u0012{WO\u00197f\t&\u001cHO]5ckRLwN\u001c\u000b\u0007\u0003\u0007\n)&!\u001b\u0011\u000b\u001d\u000b)%!\u0013\n\u0007\u0005\u001d#I\u0001\u0004PaRLwN\u001c\t\u0005\u0003\u0017\n\t&\u0004\u0002\u0002N)\u0019\u0011qJ\u0013\u0002\tU$\u0018\u000e\\\u0005\u0005\u0003'\niE\u0001\u0007ESN$(/\u001b2vi&|g\u000e\u0003\u0004;!\u0001\u0007\u0011q\u000b\t\u0006\u00033\n\u0019G\u0012\b\u0005\u00037\nyFD\u0002k\u0003;J\u0011aQ\u0005\u0004\u0003C\u0012\u0015a\u00029bG.\fw-Z\u0005\u0005\u0003K\n9GA\u0002TKFT1!!\u0019C\u0011\u001d\tY\u0007\u0005a\u0001\u0003[\n\u0011bZ3u\u001b\u0016$(/[2\u0011\u000f\u001d\u000byGS'\u0002$%\u0019\u0011\u0011\u000f\"\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0014aF3yiJ\f7\r\u001e'p]\u001e$\u0015n\u001d;sS\n,H/[8o)\u0019\t\u0019%a\u001e\u0002z!1!(\u0005a\u0001\u0003/Bq!a\u001b\u0012\u0001\u0004\tY\bE\u0004H\u0003_RU*! \u0011\u0007\u001d\u000by(C\u0002\u0002\u0002\n\u0013A\u0001T8oO\u0006\u00012\u000f[8x\t&\u001cHO]5ckRLwN\u001c\u000b\b-\u0006\u001d\u00151RAH\u0011\u0019\tII\u0005a\u0001O\u00069\u0001.Z1eS:<\u0007bBAG%\u0001\u0007\u0011\u0011J\u0001\u0002I\"9\u0011\u0011\u0013\nA\u0002\u0005M\u0015\u0001\u00044pe6\fGOT;nE\u0016\u0014\bCB$\u0002\u0016\u0006\rr-C\u0002\u0002\u0018\n\u0013\u0011BR;oGRLwN\\\u0019\u0015\u000fY\u000bY*!(\u0002\"\"1\u0011\u0011R\nA\u0002\u001dDq!a(\u0014\u0001\u0004\t\u0019%\u0001\u0003e\u001fB$\bbBAI'\u0001\u0007\u00111\u0013\u000b\b-\u0006\u0015\u0016qUAU\u0011\u0019\tI\t\u0006a\u0001O\"9\u0011q\u0014\u000bA\u0002\u0005\r\u0003BBAV)\u0001\u0007q-\u0001\u0004g_Jl\u0017\r\u001e\u000b\n-\u0006=\u0016\u0011WAZ\u0003kCa!!#\u0016\u0001\u00049\u0007BBAV+\u0001\u0007q\rC\u0004\u0002lU\u0001\r!!\u001c\t\ri*\u0002\u0019AA,\u0003U\u0019\bn\\<CsR,7\u000fR5tiJL'-\u001e;j_:$rAVA^\u0003{\u000by\f\u0003\u0004\u0002\nZ\u0001\ra\u001a\u0005\b\u0003W2\u0002\u0019AA>\u0011\u0019Qd\u00031\u0001\u0002XQ)a+a1\u0002F\"1\u0011\u0011R\fA\u0002\u001dDq!a(\u0018\u0001\u0004\t\u0019\u0005F\u0003W\u0003\u0013\fY\r\u0003\u0004\u0002\nb\u0001\ra\u001a\u0005\b\u0003\u001bD\u0002\u0019AA%\u0003\u0011!\u0017n\u001d;\u0002-MDwn^'jY2L7\u000fR5tiJL'-\u001e;j_:$RAVAj\u0003+Da!!#\u001a\u0001\u00049\u0007bBAP3\u0001\u0007\u00111\t\u000b\b-\u0006e\u00171\\Ao\u0011\u0019\tII\u0007a\u0001O\"9\u00111\u000e\u000eA\u0002\u0005m\u0004B\u0002\u001e\u001b\u0001\u0004\t9&A\u0004tK\u000e|g\u000eZ:\u0016\u0005\u0005u\u0014\u0001C:fG>tGm\u001d\u0011\u0002\u000f5Lg.\u001e;fg\u0006AQ.\u001b8vi\u0016\u001c\b%A\u0003i_V\u00148/\u0001\u0004i_V\u00148\u000fI\u0001\u000f[&dG.[:U_N#(/\u001b8h)\r9\u0017\u0011\u001f\u0005\b\u0003g\f\u0003\u0019AA?\u0003\ti7\u000f"
)
public class StatsReportListener extends SparkListener implements Logging {
   private final Buffer taskInfoMetrics;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static String millisToString(final long ms) {
      return StatsReportListener$.MODULE$.millisToString(ms);
   }

   public static long hours() {
      return StatsReportListener$.MODULE$.hours();
   }

   public static long minutes() {
      return StatsReportListener$.MODULE$.minutes();
   }

   public static long seconds() {
      return StatsReportListener$.MODULE$.seconds();
   }

   public static void showMillisDistribution(final String heading, final Function2 getMetric, final Seq taskInfoMetrics) {
      StatsReportListener$.MODULE$.showMillisDistribution(heading, getMetric, taskInfoMetrics);
   }

   public static void showMillisDistribution(final String heading, final Option dOpt) {
      StatsReportListener$.MODULE$.showMillisDistribution(heading, dOpt);
   }

   public static void showBytesDistribution(final String heading, final Distribution dist) {
      StatsReportListener$.MODULE$.showBytesDistribution(heading, dist);
   }

   public static void showBytesDistribution(final String heading, final Option dOpt) {
      StatsReportListener$.MODULE$.showBytesDistribution(heading, dOpt);
   }

   public static void showBytesDistribution(final String heading, final Function2 getMetric, final Seq taskInfoMetrics) {
      StatsReportListener$.MODULE$.showBytesDistribution(heading, getMetric, taskInfoMetrics);
   }

   public static void showDistribution(final String heading, final String format, final Function2 getMetric, final Seq taskInfoMetrics) {
      StatsReportListener$.MODULE$.showDistribution(heading, format, getMetric, taskInfoMetrics);
   }

   public static void showDistribution(final String heading, final Option dOpt, final String format) {
      StatsReportListener$.MODULE$.showDistribution(heading, dOpt, format);
   }

   public static void showDistribution(final String heading, final Option dOpt, final Function1 formatNumber) {
      StatsReportListener$.MODULE$.showDistribution(heading, dOpt, formatNumber);
   }

   public static void showDistribution(final String heading, final Distribution d, final Function1 formatNumber) {
      StatsReportListener$.MODULE$.showDistribution(heading, d, formatNumber);
   }

   public static Option extractLongDistribution(final Seq taskInfoMetrics, final Function2 getMetric) {
      return StatsReportListener$.MODULE$.extractLongDistribution(taskInfoMetrics, getMetric);
   }

   public static Option extractDoubleDistribution(final Seq taskInfoMetrics, final Function2 getMetric) {
      return StatsReportListener$.MODULE$.extractDoubleDistribution(taskInfoMetrics, getMetric);
   }

   public static String percentilesHeader() {
      return StatsReportListener$.MODULE$.percentilesHeader();
   }

   public static double[] probabilities() {
      return StatsReportListener$.MODULE$.probabilities();
   }

   public static int[] percentiles() {
      return StatsReportListener$.MODULE$.percentiles();
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

   private Buffer taskInfoMetrics() {
      return this.taskInfoMetrics;
   }

   public void onTaskEnd(final SparkListenerTaskEnd taskEnd) {
      TaskInfo info = taskEnd.taskInfo();
      TaskMetrics metrics = taskEnd.taskMetrics();
      if (info != null && metrics != null) {
         this.taskInfoMetrics().$plus$eq(new Tuple2(info, metrics));
      }
   }

   public void onStageCompleted(final SparkListenerStageCompleted stageCompleted) {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Finished stage: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE..MODULE$, this.getStatusDetail(stageCompleted.stageInfo()))})))));
      StatsReportListener$.MODULE$.showMillisDistribution("task runtime:", (info, x$1) -> BoxesRunTime.boxToLong($anonfun$onStageCompleted$2(info, x$1)), this.taskInfoMetrics().toSeq());
      StatsReportListener$.MODULE$.showBytesDistribution("shuffle bytes written:", (x$2, metric) -> BoxesRunTime.boxToLong($anonfun$onStageCompleted$3(x$2, metric)), this.taskInfoMetrics().toSeq());
      StatsReportListener$.MODULE$.showMillisDistribution("fetch wait time:", (x$3, metric) -> BoxesRunTime.boxToLong($anonfun$onStageCompleted$4(x$3, metric)), this.taskInfoMetrics().toSeq());
      StatsReportListener$.MODULE$.showBytesDistribution("remote bytes read:", (x$4, metric) -> BoxesRunTime.boxToLong($anonfun$onStageCompleted$5(x$4, metric)), this.taskInfoMetrics().toSeq());
      StatsReportListener$.MODULE$.showBytesDistribution("task result size:", (x$5, metric) -> BoxesRunTime.boxToLong($anonfun$onStageCompleted$6(x$5, metric)), this.taskInfoMetrics().toSeq());
      Buffer runtimePcts = (Buffer)this.taskInfoMetrics().map((x0$1) -> {
         if (x0$1 != null) {
            TaskInfo info = (TaskInfo)x0$1._1();
            TaskMetrics metrics = (TaskMetrics)x0$1._2();
            return RuntimePercentage$.MODULE$.apply(info.duration(), metrics);
         } else {
            throw new MatchError(x0$1);
         }
      });
      StatsReportListener$.MODULE$.showDistribution("executor (non-fetch) time pct: ", Distribution$.MODULE$.apply((Iterable)runtimePcts.map((x$6) -> BoxesRunTime.boxToDouble($anonfun$onStageCompleted$8(x$6)))), "%2.0f %%");
      StatsReportListener$.MODULE$.showDistribution("fetch wait time pct: ", Distribution$.MODULE$.apply((Iterable)runtimePcts.flatMap((x$7) -> x$7.fetchPct().map((JFunction1.mcDD.sp)(x$8) -> x$8 * (double)100))), "%2.0f %%");
      StatsReportListener$.MODULE$.showDistribution("other time pct: ", Distribution$.MODULE$.apply((Iterable)runtimePcts.map((x$9) -> BoxesRunTime.boxToDouble($anonfun$onStageCompleted$11(x$9)))), "%2.0f %%");
      this.taskInfoMetrics().clear();
   }

   private String getStatusDetail(final StageInfo info) {
      String failureReason = (String)info.failureReason().map((x$10) -> "(" + x$10 + ")").getOrElse(() -> "");
      Object timeTaken = info.submissionTime().map((JFunction1.mcJJ.sp)(x) -> BoxesRunTime.unboxToLong(info.completionTime().getOrElse((JFunction0.mcJ.sp)() -> System.currentTimeMillis())) - x).getOrElse(() -> "-");
      int var10000 = info.stageId();
      return "Stage(" + var10000 + ", " + info.attemptNumber() + "); Name: '" + info.name() + "'; Status: " + info.getStatusString() + failureReason + "; numTasks: " + info.numTasks() + "; Took: " + timeTaken + " msec";
   }

   // $FF: synthetic method
   public static final long $anonfun$onStageCompleted$2(final TaskInfo info, final TaskMetrics x$1) {
      return info.duration();
   }

   // $FF: synthetic method
   public static final long $anonfun$onStageCompleted$3(final TaskInfo x$2, final TaskMetrics metric) {
      return metric.shuffleWriteMetrics().bytesWritten();
   }

   // $FF: synthetic method
   public static final long $anonfun$onStageCompleted$4(final TaskInfo x$3, final TaskMetrics metric) {
      return metric.shuffleReadMetrics().fetchWaitTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$onStageCompleted$5(final TaskInfo x$4, final TaskMetrics metric) {
      return metric.shuffleReadMetrics().remoteBytesRead();
   }

   // $FF: synthetic method
   public static final long $anonfun$onStageCompleted$6(final TaskInfo x$5, final TaskMetrics metric) {
      return metric.resultSize();
   }

   // $FF: synthetic method
   public static final double $anonfun$onStageCompleted$8(final RuntimePercentage x$6) {
      return x$6.executorPct() * (double)100;
   }

   // $FF: synthetic method
   public static final double $anonfun$onStageCompleted$11(final RuntimePercentage x$9) {
      return x$9.other() * (double)100;
   }

   public StatsReportListener() {
      Logging.$init$(this);
      this.taskInfoMetrics = (Buffer)scala.collection.mutable.Buffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
