package org.apache.spark.mllib.tree;

import java.io.Serializable;
import java.util.Map;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.configuration.QuantileStrategy$;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.configuration.Strategy$;
import org.apache.spark.mllib.tree.impurity.Impurities$;
import org.apache.spark.mllib.tree.impurity.Impurity;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.StringContext;
import scala.jdk.CollectionConverters.;
import scala.runtime.ModuleSerializationProxy;

public final class DecisionTree$ implements Serializable, Logging {
   public static final DecisionTree$ MODULE$ = new DecisionTree$();
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

   public DecisionTreeModel train(final RDD input, final Strategy strategy) {
      return (new DecisionTree(strategy)).run(input);
   }

   public DecisionTreeModel train(final RDD input, final Enumeration.Value algo, final Impurity impurity, final int maxDepth) {
      Strategy strategy = new Strategy(algo, impurity, maxDepth, Strategy$.MODULE$.$lessinit$greater$default$4(), Strategy$.MODULE$.$lessinit$greater$default$5(), Strategy$.MODULE$.$lessinit$greater$default$6(), Strategy$.MODULE$.$lessinit$greater$default$7(), Strategy$.MODULE$.$lessinit$greater$default$8(), Strategy$.MODULE$.$lessinit$greater$default$9(), Strategy$.MODULE$.$lessinit$greater$default$10(), Strategy$.MODULE$.$lessinit$greater$default$11(), Strategy$.MODULE$.$lessinit$greater$default$12(), Strategy$.MODULE$.$lessinit$greater$default$13(), Strategy$.MODULE$.$lessinit$greater$default$14(), Strategy$.MODULE$.$lessinit$greater$default$15());
      return (new DecisionTree(strategy)).run(input);
   }

   public DecisionTreeModel train(final RDD input, final Enumeration.Value algo, final Impurity impurity, final int maxDepth, final int numClasses) {
      Strategy strategy = new Strategy(algo, impurity, maxDepth, numClasses, Strategy$.MODULE$.$lessinit$greater$default$5(), Strategy$.MODULE$.$lessinit$greater$default$6(), Strategy$.MODULE$.$lessinit$greater$default$7(), Strategy$.MODULE$.$lessinit$greater$default$8(), Strategy$.MODULE$.$lessinit$greater$default$9(), Strategy$.MODULE$.$lessinit$greater$default$10(), Strategy$.MODULE$.$lessinit$greater$default$11(), Strategy$.MODULE$.$lessinit$greater$default$12(), Strategy$.MODULE$.$lessinit$greater$default$13(), Strategy$.MODULE$.$lessinit$greater$default$14(), Strategy$.MODULE$.$lessinit$greater$default$15());
      return (new DecisionTree(strategy)).run(input);
   }

   public DecisionTreeModel train(final RDD input, final Enumeration.Value algo, final Impurity impurity, final int maxDepth, final int numClasses, final int maxBins, final Enumeration.Value quantileCalculationStrategy, final scala.collection.immutable.Map categoricalFeaturesInfo) {
      Strategy strategy = new Strategy(algo, impurity, maxDepth, numClasses, maxBins, quantileCalculationStrategy, categoricalFeaturesInfo, Strategy$.MODULE$.$lessinit$greater$default$8(), Strategy$.MODULE$.$lessinit$greater$default$9(), Strategy$.MODULE$.$lessinit$greater$default$10(), Strategy$.MODULE$.$lessinit$greater$default$11(), Strategy$.MODULE$.$lessinit$greater$default$12(), Strategy$.MODULE$.$lessinit$greater$default$13(), Strategy$.MODULE$.$lessinit$greater$default$14(), Strategy$.MODULE$.$lessinit$greater$default$15());
      return (new DecisionTree(strategy)).run(input);
   }

   public DecisionTreeModel trainClassifier(final RDD input, final int numClasses, final scala.collection.immutable.Map categoricalFeaturesInfo, final String impurity, final int maxDepth, final int maxBins) {
      Impurity impurityType = Impurities$.MODULE$.fromString(impurity);
      return this.train(input, Algo$.MODULE$.Classification(), impurityType, maxDepth, numClasses, maxBins, QuantileStrategy$.MODULE$.Sort(), categoricalFeaturesInfo);
   }

   public DecisionTreeModel trainClassifier(final JavaRDD input, final int numClasses, final Map categoricalFeaturesInfo, final String impurity, final int maxDepth, final int maxBins) {
      return this.trainClassifier(input.rdd(), numClasses, .MODULE$.MapHasAsScala(categoricalFeaturesInfo).asScala().toMap(scala..less.colon.less..MODULE$.refl()), impurity, maxDepth, maxBins);
   }

   public DecisionTreeModel trainRegressor(final RDD input, final scala.collection.immutable.Map categoricalFeaturesInfo, final String impurity, final int maxDepth, final int maxBins) {
      Impurity impurityType = Impurities$.MODULE$.fromString(impurity);
      return this.train(input, Algo$.MODULE$.Regression(), impurityType, maxDepth, 0, maxBins, QuantileStrategy$.MODULE$.Sort(), categoricalFeaturesInfo);
   }

   public DecisionTreeModel trainRegressor(final JavaRDD input, final Map categoricalFeaturesInfo, final String impurity, final int maxDepth, final int maxBins) {
      return this.trainRegressor(input.rdd(), .MODULE$.MapHasAsScala(categoricalFeaturesInfo).asScala().toMap(scala..less.colon.less..MODULE$.refl()), impurity, maxDepth, maxBins);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DecisionTree$.class);
   }

   private DecisionTree$() {
   }
}
