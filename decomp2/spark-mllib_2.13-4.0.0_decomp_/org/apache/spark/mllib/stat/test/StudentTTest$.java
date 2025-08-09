package org.apache.spark.mllib.stat.test;

import com.twitter.chill.MeatLocker;
import com.twitter.chill.MeatLocker.;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues;
import org.apache.commons.math3.stat.inference.TTest;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.streaming.dstream.DStream;
import org.apache.spark.util.StatCounter;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.runtime.ModuleSerializationProxy;

public final class StudentTTest$ implements StreamingTestMethod, Logging {
   public static final StudentTTest$ MODULE$ = new StudentTTest$();
   private static final MeatLocker tTester;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      StreamingTestMethod.$init$(MODULE$);
      Logging.$init$(MODULE$);
      tTester = .MODULE$.apply(new TTest());
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

   public StatisticalSummaryValues toApacheCommonsStats(final StatCounter summaryStats) {
      return StreamingTestMethod.toApacheCommonsStats$(this, summaryStats);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public final String methodName() {
      return "Student's 2-sample t-test";
   }

   public final String nullHypothesis() {
      return "Both groups have same mean";
   }

   private final MeatLocker tTester() {
      return tTester;
   }

   public DStream doTest(final DStream data) {
      return data.map(((statsA, statsB) -> MODULE$.test(statsA, statsB)).tupled(), scala.reflect.ClassTag..MODULE$.apply(StreamingTestResult.class));
   }

   private StreamingTestResult test(final StatCounter statsA, final StatCounter statsB) {
      return new StreamingTestResult(((TTest)this.tTester().get()).homoscedasticTTest(this.toApacheCommonsStats(statsA), this.toApacheCommonsStats(statsB)), studentDF$1(this.toApacheCommonsStats(statsA), this.toApacheCommonsStats(statsB)), ((TTest)this.tTester().get()).homoscedasticT(this.toApacheCommonsStats(statsA), this.toApacheCommonsStats(statsB)), "Student's 2-sample t-test", "Both groups have same mean");
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StudentTTest$.class);
   }

   private static final double studentDF$1(final StatisticalSummaryValues sample1, final StatisticalSummaryValues sample2) {
      return (double)(sample1.getN() + sample2.getN()) - (double)2.0F;
   }

   private StudentTTest$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
