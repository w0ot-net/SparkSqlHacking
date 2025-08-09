package org.apache.spark.mllib.tree;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.tree.TreeEnsembleParams$;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.configuration.QuantileStrategy$;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.configuration.Strategy$;
import org.apache.spark.mllib.tree.impurity.Impurities$;
import org.apache.spark.mllib.tree.impurity.Impurity;
import org.apache.spark.mllib.tree.model.RandomForestModel;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Predef;
import scala.StringContext;
import scala.Predef.;
import scala.runtime.ModuleSerializationProxy;

public final class RandomForest$ implements Serializable, Logging {
   public static final RandomForest$ MODULE$ = new RandomForest$();
   private static final String[] supportedFeatureSubsetStrategies;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      supportedFeatureSubsetStrategies = TreeEnsembleParams$.MODULE$.supportedFeatureSubsetStrategies();
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

   public RandomForestModel trainClassifier(final RDD input, final Strategy strategy, final int numTrees, final String featureSubsetStrategy, final int seed) {
      boolean var8;
      Predef var10000;
      label17: {
         label16: {
            var10000 = .MODULE$;
            Enumeration.Value var10001 = strategy.algo();
            Enumeration.Value var6 = Algo$.MODULE$.Classification();
            if (var10001 == null) {
               if (var6 == null) {
                  break label16;
               }
            } else if (var10001.equals(var6)) {
               break label16;
            }

            var8 = false;
            break label17;
         }

         var8 = true;
      }

      var10000.require(var8, () -> "RandomForest.trainClassifier given Strategy with invalid algo: " + strategy.algo());
      RandomForest rf = new RandomForest(strategy, numTrees, featureSubsetStrategy, seed);
      return rf.run(input);
   }

   public RandomForestModel trainClassifier(final RDD input, final int numClasses, final scala.collection.immutable.Map categoricalFeaturesInfo, final int numTrees, final String featureSubsetStrategy, final String impurity, final int maxDepth, final int maxBins, final int seed) {
      Impurity impurityType = Impurities$.MODULE$.fromString(impurity);
      Strategy strategy = new Strategy(Algo$.MODULE$.Classification(), impurityType, maxDepth, numClasses, maxBins, QuantileStrategy$.MODULE$.Sort(), categoricalFeaturesInfo, Strategy$.MODULE$.$lessinit$greater$default$8(), Strategy$.MODULE$.$lessinit$greater$default$9(), Strategy$.MODULE$.$lessinit$greater$default$10(), Strategy$.MODULE$.$lessinit$greater$default$11(), Strategy$.MODULE$.$lessinit$greater$default$12(), Strategy$.MODULE$.$lessinit$greater$default$13(), Strategy$.MODULE$.$lessinit$greater$default$14(), Strategy$.MODULE$.$lessinit$greater$default$15());
      return this.trainClassifier(input, strategy, numTrees, featureSubsetStrategy, seed);
   }

   public RandomForestModel trainClassifier(final JavaRDD input, final int numClasses, final Map categoricalFeaturesInfo, final int numTrees, final String featureSubsetStrategy, final String impurity, final int maxDepth, final int maxBins, final int seed) {
      return this.trainClassifier(input.rdd(), numClasses, scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(categoricalFeaturesInfo).asScala().toMap(scala..less.colon.less..MODULE$.refl()), numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins, seed);
   }

   public int trainClassifier$default$9() {
      return org.apache.spark.util.Utils..MODULE$.random().nextInt();
   }

   public RandomForestModel trainRegressor(final RDD input, final Strategy strategy, final int numTrees, final String featureSubsetStrategy, final int seed) {
      boolean var8;
      Predef var10000;
      label17: {
         label16: {
            var10000 = .MODULE$;
            Enumeration.Value var10001 = strategy.algo();
            Enumeration.Value var6 = Algo$.MODULE$.Regression();
            if (var10001 == null) {
               if (var6 == null) {
                  break label16;
               }
            } else if (var10001.equals(var6)) {
               break label16;
            }

            var8 = false;
            break label17;
         }

         var8 = true;
      }

      var10000.require(var8, () -> "RandomForest.trainRegressor given Strategy with invalid algo: " + strategy.algo());
      RandomForest rf = new RandomForest(strategy, numTrees, featureSubsetStrategy, seed);
      return rf.run(input);
   }

   public RandomForestModel trainRegressor(final RDD input, final scala.collection.immutable.Map categoricalFeaturesInfo, final int numTrees, final String featureSubsetStrategy, final String impurity, final int maxDepth, final int maxBins, final int seed) {
      Impurity impurityType = Impurities$.MODULE$.fromString(impurity);
      Strategy strategy = new Strategy(Algo$.MODULE$.Regression(), impurityType, maxDepth, 0, maxBins, QuantileStrategy$.MODULE$.Sort(), categoricalFeaturesInfo, Strategy$.MODULE$.$lessinit$greater$default$8(), Strategy$.MODULE$.$lessinit$greater$default$9(), Strategy$.MODULE$.$lessinit$greater$default$10(), Strategy$.MODULE$.$lessinit$greater$default$11(), Strategy$.MODULE$.$lessinit$greater$default$12(), Strategy$.MODULE$.$lessinit$greater$default$13(), Strategy$.MODULE$.$lessinit$greater$default$14(), Strategy$.MODULE$.$lessinit$greater$default$15());
      return this.trainRegressor(input, strategy, numTrees, featureSubsetStrategy, seed);
   }

   public RandomForestModel trainRegressor(final JavaRDD input, final Map categoricalFeaturesInfo, final int numTrees, final String featureSubsetStrategy, final String impurity, final int maxDepth, final int maxBins, final int seed) {
      return this.trainRegressor(input.rdd(), scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(categoricalFeaturesInfo).asScala().toMap(scala..less.colon.less..MODULE$.refl()), numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins, seed);
   }

   public int trainRegressor$default$8() {
      return org.apache.spark.util.Utils..MODULE$.random().nextInt();
   }

   public String[] supportedFeatureSubsetStrategies() {
      return supportedFeatureSubsetStrategies;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RandomForest$.class);
   }

   private RandomForest$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
