package org.apache.spark.ml.stat;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

public final class Summarizer$ implements Logging {
   public static final Summarizer$ MODULE$ = new Summarizer$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
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

   public SummaryBuilder metrics(final String... metrics) {
      return this.metrics((Seq).MODULE$.wrapRefArray((Object[])metrics));
   }

   public SummaryBuilder metrics(final Seq metrics) {
      scala.Predef..MODULE$.require(metrics.nonEmpty(), () -> "Should include at least one metric");
      Tuple2 var4 = SummaryBuilderImpl$.MODULE$.getRelevantMetrics(metrics);
      if (var4 != null) {
         Seq typedMetrics = (Seq)var4._1();
         Seq computeMetrics = (Seq)var4._2();
         Tuple2 var3 = new Tuple2(typedMetrics, computeMetrics);
         Seq typedMetrics = (Seq)var3._1();
         Seq computeMetrics = (Seq)var3._2();
         return new SummaryBuilderImpl(typedMetrics, computeMetrics);
      } else {
         throw new MatchError(var4);
      }
   }

   public Column mean(final Column col, final Column weightCol) {
      return this.getSingleMetric(col, weightCol, "mean");
   }

   public Column mean(final Column col) {
      return this.mean(col, org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F)));
   }

   public Column sum(final Column col, final Column weightCol) {
      return this.getSingleMetric(col, weightCol, "sum");
   }

   public Column sum(final Column col) {
      return this.sum(col, org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F)));
   }

   public Column variance(final Column col, final Column weightCol) {
      return this.getSingleMetric(col, weightCol, "variance");
   }

   public Column variance(final Column col) {
      return this.variance(col, org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F)));
   }

   public Column std(final Column col, final Column weightCol) {
      return this.getSingleMetric(col, weightCol, "std");
   }

   public Column std(final Column col) {
      return this.std(col, org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F)));
   }

   public Column count(final Column col, final Column weightCol) {
      return this.getSingleMetric(col, weightCol, "count");
   }

   public Column count(final Column col) {
      return this.count(col, org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F)));
   }

   public Column numNonZeros(final Column col, final Column weightCol) {
      return this.getSingleMetric(col, weightCol, "numNonZeros");
   }

   public Column numNonZeros(final Column col) {
      return this.numNonZeros(col, org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F)));
   }

   public Column max(final Column col, final Column weightCol) {
      return this.getSingleMetric(col, weightCol, "max");
   }

   public Column max(final Column col) {
      return this.max(col, org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F)));
   }

   public Column min(final Column col, final Column weightCol) {
      return this.getSingleMetric(col, weightCol, "min");
   }

   public Column min(final Column col) {
      return this.min(col, org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F)));
   }

   public Column normL1(final Column col, final Column weightCol) {
      return this.getSingleMetric(col, weightCol, "normL1");
   }

   public Column normL1(final Column col) {
      return this.normL1(col, org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F)));
   }

   public Column normL2(final Column col, final Column weightCol) {
      return this.getSingleMetric(col, weightCol, "normL2");
   }

   public Column normL2(final Column col) {
      return this.normL2(col, org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F)));
   }

   private Column getSingleMetric(final Column col, final Column weightCol, final String metric) {
      Column c1 = this.metrics((Seq).MODULE$.wrapRefArray((Object[])(new String[]{metric}))).summary(col, weightCol);
      return c1.getField(metric).as(metric + "(" + col + ")");
   }

   public SummarizerBuffer createSummarizerBuffer(final Seq requested) {
      Tuple2 var4 = SummaryBuilderImpl$.MODULE$.getRelevantMetrics(requested);
      if (var4 != null) {
         Seq metrics = (Seq)var4._1();
         Seq computeMetrics = (Seq)var4._2();
         Tuple2 var3 = new Tuple2(metrics, computeMetrics);
         Seq metrics = (Seq)var3._1();
         Seq computeMetrics = (Seq)var3._2();
         return new SummarizerBuffer(metrics, computeMetrics);
      } else {
         throw new MatchError(var4);
      }
   }

   public Tuple2 getRegressionSummarizers(final RDD instances, final int aggregationDepth, final Seq requested) {
      return (Tuple2)instances.treeAggregate(new Tuple2(this.createSummarizerBuffer(requested), this.createSummarizerBuffer(.MODULE$.wrapRefArray((Object[])(new String[]{"mean", "std", "count"})))), (c, instance) -> new Tuple2(((SummarizerBuffer)c._1()).add(instance.features(), instance.weight()), ((SummarizerBuffer)c._2()).add(org.apache.spark.ml.linalg.Vectors..MODULE$.dense(instance.label(), scala.collection.immutable.Nil..MODULE$), instance.weight())), (c1, c2) -> new Tuple2(((SummarizerBuffer)c1._1()).merge((SummarizerBuffer)c2._1()), ((SummarizerBuffer)c1._2()).merge((SummarizerBuffer)c2._2())), aggregationDepth, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public int getRegressionSummarizers$default$2() {
      return 2;
   }

   public Seq getRegressionSummarizers$default$3() {
      return new scala.collection.immutable..colon.colon("mean", new scala.collection.immutable..colon.colon("std", new scala.collection.immutable..colon.colon("count", scala.collection.immutable.Nil..MODULE$)));
   }

   public Tuple2 getClassificationSummarizers(final RDD instances, final int aggregationDepth, final Seq requested) {
      return (Tuple2)instances.treeAggregate(new Tuple2(this.createSummarizerBuffer(requested), new MultiClassSummarizer()), (c, instance) -> new Tuple2(((SummarizerBuffer)c._1()).add(instance.features(), instance.weight()), ((MultiClassSummarizer)c._2()).add(instance.label(), instance.weight())), (c1, c2) -> new Tuple2(((SummarizerBuffer)c1._1()).merge((SummarizerBuffer)c2._1()), ((MultiClassSummarizer)c1._2()).merge((MultiClassSummarizer)c2._2())), aggregationDepth, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public int getClassificationSummarizers$default$2() {
      return 2;
   }

   public Seq getClassificationSummarizers$default$3() {
      return new scala.collection.immutable..colon.colon("mean", new scala.collection.immutable..colon.colon("std", new scala.collection.immutable..colon.colon("count", scala.collection.immutable.Nil..MODULE$)));
   }

   private Summarizer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
