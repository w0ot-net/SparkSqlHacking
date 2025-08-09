package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.executor.TaskMetrics;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.util.Distribution;
import org.apache.spark.util.Distribution$;
import org.apache.spark.util.StatCounter;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.ArrayOps.;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArraySeq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class StatsReportListener$ implements Logging {
   public static final StatsReportListener$ MODULE$ = new StatsReportListener$();
   private static final int[] percentiles;
   private static final double[] probabilities;
   private static final String percentilesHeader;
   private static final long seconds;
   private static final long minutes;
   private static final long hours;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      percentiles = new int[]{0, 5, 10, 25, 50, 75, 90, 95, 100};
      probabilities = (double[]).MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(MODULE$.percentiles()), (JFunction1.mcDI.sp)(x$11) -> (double)x$11 / (double)100.0F, scala.reflect.ClassTag..MODULE$.Double());
      ArraySeq.ofInt var10000 = scala.Predef..MODULE$.wrapIntArray(MODULE$.percentiles());
      percentilesHeader = "\t" + var10000.mkString("%\t") + "%";
      seconds = 1000L;
      minutes = MODULE$.seconds() * 60L;
      hours = MODULE$.minutes() * 60L;
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
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public int[] percentiles() {
      return percentiles;
   }

   public double[] probabilities() {
      return probabilities;
   }

   public String percentilesHeader() {
      return percentilesHeader;
   }

   public Option extractDoubleDistribution(final Seq taskInfoMetrics, final Function2 getMetric) {
      return Distribution$.MODULE$.apply((Iterable)taskInfoMetrics.map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$extractDoubleDistribution$1(getMetric, x0$1))));
   }

   public Option extractLongDistribution(final Seq taskInfoMetrics, final Function2 getMetric) {
      return this.extractDoubleDistribution(taskInfoMetrics, (info, metric) -> BoxesRunTime.boxToDouble($anonfun$extractLongDistribution$1(getMetric, info, metric)));
   }

   public void showDistribution(final String heading, final Distribution d, final Function1 formatNumber) {
      StatCounter stats = d.statCounter();
      IndexedSeq quantiles = (IndexedSeq)d.getQuantiles(scala.Predef..MODULE$.wrapDoubleArray(this.probabilities())).map(formatNumber);
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DESCRIPTION..MODULE$, heading), new MDC(org.apache.spark.internal.LogKeys.STATS..MODULE$, stats)})))));
      this.logInfo((Function0)(() -> MODULE$.percentilesHeader()));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"\\t"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.QUANTILES..MODULE$, quantiles.mkString("\t"))}))))));
   }

   public void showDistribution(final String heading, final Option dOpt, final Function1 formatNumber) {
      dOpt.foreach((d) -> {
         $anonfun$showDistribution$4(heading, formatNumber, d);
         return BoxedUnit.UNIT;
      });
   }

   public void showDistribution(final String heading, final Option dOpt, final String format) {
      this.showDistribution(heading, (Option)dOpt, (Function1)((d) -> $anonfun$showDistribution$5(format, BoxesRunTime.unboxToDouble(d))));
   }

   public void showDistribution(final String heading, final String format, final Function2 getMetric, final Seq taskInfoMetrics) {
      this.showDistribution(heading, this.extractDoubleDistribution(taskInfoMetrics, getMetric), format);
   }

   public void showBytesDistribution(final String heading, final Function2 getMetric, final Seq taskInfoMetrics) {
      this.showBytesDistribution(heading, this.extractLongDistribution(taskInfoMetrics, getMetric));
   }

   public void showBytesDistribution(final String heading, final Option dOpt) {
      dOpt.foreach((dist) -> {
         $anonfun$showBytesDistribution$1(heading, dist);
         return BoxedUnit.UNIT;
      });
   }

   public void showBytesDistribution(final String heading, final Distribution dist) {
      this.showDistribution(heading, (Distribution)dist, (Function1)((d) -> $anonfun$showBytesDistribution$2(BoxesRunTime.unboxToDouble(d))));
   }

   public void showMillisDistribution(final String heading, final Option dOpt) {
      this.showDistribution(heading, (Option)dOpt, (Function1)((d) -> $anonfun$showMillisDistribution$1(BoxesRunTime.unboxToDouble(d))));
   }

   public void showMillisDistribution(final String heading, final Function2 getMetric, final Seq taskInfoMetrics) {
      this.showMillisDistribution(heading, this.extractLongDistribution(taskInfoMetrics, getMetric));
   }

   public long seconds() {
      return seconds;
   }

   public long minutes() {
      return minutes;
   }

   public long hours() {
      return hours;
   }

   public String millisToString(final long ms) {
      Tuple2 var5 = ms > this.hours() ? new Tuple2(BoxesRunTime.boxToDouble((double)ms / (double)this.hours()), "hours") : (ms > this.minutes() ? new Tuple2(BoxesRunTime.boxToDouble((double)ms / (double)this.minutes()), "min") : (ms > this.seconds() ? new Tuple2(BoxesRunTime.boxToDouble((double)ms / (double)this.seconds()), "s") : new Tuple2(BoxesRunTime.boxToDouble((double)ms), "ms")));
      if (var5 != null) {
         double size = var5._1$mcD$sp();
         String units = (String)var5._2();
         Tuple2 var4 = new Tuple2(BoxesRunTime.boxToDouble(size), units);
         double size = var4._1$mcD$sp();
         String units = (String)var4._2();
         return scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%.1f %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(size), units}));
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$extractDoubleDistribution$1(final Function2 getMetric$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         TaskInfo info = (TaskInfo)x0$1._1();
         TaskMetrics metric = (TaskMetrics)x0$1._2();
         return BoxesRunTime.unboxToDouble(getMetric$1.apply(info, metric));
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$extractLongDistribution$1(final Function2 getMetric$2, final TaskInfo info, final TaskMetrics metric) {
      return (double)BoxesRunTime.unboxToLong(getMetric$2.apply(info, metric));
   }

   // $FF: synthetic method
   public static final void $anonfun$showDistribution$4(final String heading$2, final Function1 formatNumber$1, final Distribution d) {
      MODULE$.showDistribution(heading$2, d, formatNumber$1);
   }

   private static final String f$1(final double d, final String format$1) {
      return scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString(format$1), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(d)}));
   }

   // $FF: synthetic method
   public static final String $anonfun$showDistribution$5(final String format$1, final double d) {
      return f$1(d, format$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$showBytesDistribution$1(final String heading$3, final Distribution dist) {
      MODULE$.showBytesDistribution(heading$3, dist);
   }

   // $FF: synthetic method
   public static final String $anonfun$showBytesDistribution$2(final double d) {
      return Utils$.MODULE$.bytesToString((long)d);
   }

   // $FF: synthetic method
   public static final String $anonfun$showMillisDistribution$1(final double d) {
      return MODULE$.millisToString((long)d);
   }

   private StatsReportListener$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
