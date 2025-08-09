package org.apache.spark.mllib.optimization;

import breeze.optimize.CachedDiffFunction;
import breeze.optimize.FirstOrderMinimizer;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArrayBuilder.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class LBFGS$ implements Logging, Serializable {
   public static final LBFGS$ MODULE$ = new LBFGS$();
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

   public Tuple2 runLBFGS(final RDD data, final Gradient gradient, final Updater updater, final int numCorrections, final double convergenceTol, final int maxNumIterations, final double regParam, final Vector initialWeights) {
      ArrayBuilder lossHistory = .MODULE$.make(scala.reflect.ClassTag..MODULE$.Double());
      long numExamples = data.count();
      LBFGS.CostFun costFun = new LBFGS.CostFun(data, gradient, updater, regParam, numExamples);
      breeze.optimize.LBFGS lbfgs = new breeze.optimize.LBFGS(maxNumIterations, numCorrections, convergenceTol, breeze.linalg.DenseVector..MODULE$.space_Double());
      Iterator states = lbfgs.iterations(new CachedDiffFunction(costFun, breeze.linalg.DenseVector..MODULE$.canCopyDenseVector(scala.reflect.ClassTag..MODULE$.Double())), initialWeights.asBreeze().toDenseVector$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()));

      FirstOrderMinimizer.State state;
      for(state = (FirstOrderMinimizer.State)states.next(); states.hasNext(); state = (FirstOrderMinimizer.State)states.next()) {
         lossHistory.$plus$eq(BoxesRunTime.boxToDouble(state.value()));
      }

      lossHistory.$plus$eq(BoxesRunTime.boxToDouble(state.value()));
      Vector weights = Vectors$.MODULE$.fromBreeze((breeze.linalg.Vector)state.x());
      double[] lossHistoryArray = (double[])lossHistory.result();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"LBFGS.runLBFGS finished. Last 10 losses ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LOSSES..MODULE$, scala.Predef..MODULE$.wrapDoubleArray((double[])scala.collection.ArrayOps..MODULE$.takeRight$extension(scala.Predef..MODULE$.doubleArrayOps(lossHistoryArray), 10)).mkString(", "))})))));
      return new Tuple2(weights, lossHistoryArray);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LBFGS$.class);
   }

   private LBFGS$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
