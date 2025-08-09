package org.apache.spark.ml.tree.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.feature.Instance;
import org.apache.spark.ml.tree.TreeEnsembleParams$;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.ArrayOps.;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.HashSet;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class DecisionTreeMetadata$ implements Logging, Serializable {
   public static final DecisionTreeMetadata$ MODULE$ = new DecisionTreeMetadata$();
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

   public DecisionTreeMetadata buildMetadata(final RDD input, final Strategy strategy, final int numTrees, final String featureSubsetStrategy) {
      int numFeatures = BoxesRunTime.unboxToInt(.MODULE$.headOption$extension(scala.Predef..MODULE$.intArrayOps((int[])input.map((x$1) -> BoxesRunTime.boxToInteger($anonfun$buildMetadata$1(x$1)), scala.reflect.ClassTag..MODULE$.Int()).take(1))).getOrElse(() -> {
         throw new IllegalArgumentException("DecisionTree requires size of input RDD > 0, but was given by empty one.");
      }));
      scala.Predef..MODULE$.require(numFeatures > 0, () -> "DecisionTree requires number of features > 0, but was given an empty features vector");
      Tuple2 var13 = (Tuple2)input.aggregate(new Tuple2.mcJD.sp(0L, (double)0.0F), (cw, instance) -> new Tuple2.mcJD.sp(cw._1$mcJ$sp() + 1L, cw._2$mcD$sp() + instance.weight()), (cw1, cw2) -> new Tuple2.mcJD.sp(cw1._1$mcJ$sp() + cw2._1$mcJ$sp(), cw1._2$mcD$sp() + cw2._2$mcD$sp()), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      if (var13 == null) {
         throw new MatchError(var13);
      } else {
         long numExamples;
         double weightSum;
         int var45;
         label132: {
            label138: {
               long numExamples = var13._1$mcJ$sp();
               double weightSum = var13._2$mcD$sp();
               Tuple2.mcJD.sp var12 = new Tuple2.mcJD.sp(numExamples, weightSum);
               numExamples = ((Tuple2)var12)._1$mcJ$sp();
               weightSum = ((Tuple2)var12)._2$mcD$sp();
               Enumeration.Value var23 = strategy.algo();
               Enumeration.Value var10000 = Algo$.MODULE$.Classification();
               if (var10000 == null) {
                  if (var23 == null) {
                     break label138;
                  }
               } else if (var10000.equals(var23)) {
                  break label138;
               }

               var10000 = Algo$.MODULE$.Regression();
               if (var10000 == null) {
                  if (var23 != null) {
                     throw new MatchError(var23);
                  }
               } else if (!var10000.equals(var23)) {
                  throw new MatchError(var23);
               }

               var45 = 0;
               break label132;
            }

            var45 = strategy.numClasses();
         }

         int numClasses = var45;
         int maxPossibleBins = (int)scala.math.package..MODULE$.min((long)strategy.maxBins(), numExamples);
         if (maxPossibleBins < strategy.maxBins()) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"DecisionTree reducing maxBins from "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " to "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_NUM_BINS..MODULE$, BoxesRunTime.boxToInteger(strategy.maxBins()))})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_NUM_POSSIBLE_BINS..MODULE$, BoxesRunTime.boxToInteger(maxPossibleBins))})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(= number of training instances)"})))).log(scala.collection.immutable.Nil..MODULE$))));
         }

         if (strategy.categoricalFeaturesInfo().nonEmpty()) {
            int maxCategoriesPerFeature = BoxesRunTime.unboxToInt(strategy.categoricalFeaturesInfo().values().max(scala.math.Ordering.Int..MODULE$));
            int maxCategory = ((Tuple2)strategy.categoricalFeaturesInfo().find((x$3) -> BoxesRunTime.boxToBoolean($anonfun$buildMetadata$7(maxCategoriesPerFeature, x$3))).get())._1$mcI$sp();
            scala.Predef..MODULE$.require(maxCategoriesPerFeature <= maxPossibleBins, () -> "DecisionTree requires maxBins (= " + maxPossibleBins + ") to be at least as large as the number of values in each categorical feature, but categorical feature " + maxCategory + " has " + maxCategoriesPerFeature + " values. Consider removing this and other categorical features with a large number of values, or add more training examples.");
         }

         HashSet unorderedFeatures = new HashSet();
         int[] numBins = (int[])scala.Array..MODULE$.fill(numFeatures, (JFunction0.mcI.sp)() -> maxPossibleBins, scala.reflect.ClassTag..MODULE$.Int());
         if (numClasses > 2) {
            int maxCategoriesForUnorderedFeature = (int)scala.runtime.RichDouble..MODULE$.floor$extension(scala.Predef..MODULE$.doubleWrapper(scala.math.package..MODULE$.log((double)(maxPossibleBins / 2 + 1)) / scala.math.package..MODULE$.log((double)2.0F) + (double)1));
            strategy.categoricalFeaturesInfo().foreach((x0$1) -> {
               $anonfun$buildMetadata$10(maxCategoriesForUnorderedFeature, unorderedFeatures, numBins, x0$1);
               return BoxedUnit.UNIT;
            });
         } else {
            strategy.categoricalFeaturesInfo().foreach((x0$2) -> {
               $anonfun$buildMetadata$11(numBins, x0$2);
               return BoxedUnit.UNIT;
            });
         }

         String var46;
         switch (featureSubsetStrategy == null ? 0 : featureSubsetStrategy.hashCode()) {
            case 3005871:
               if ("auto".equals(featureSubsetStrategy)) {
                  if (numTrees == 1) {
                     var46 = "all";
                  } else {
                     label109: {
                        Enumeration.Value var47 = strategy.algo();
                        Enumeration.Value var34 = Algo$.MODULE$.Classification();
                        if (var47 == null) {
                           if (var34 == null) {
                              break label109;
                           }
                        } else if (var47.equals(var34)) {
                           break label109;
                        }

                        var46 = "onethird";
                        break;
                     }

                     var46 = "sqrt";
                  }
                  break;
               }
            default:
               var46 = featureSubsetStrategy;
         }

         label101: {
            String _featureSubsetStrategy = var46;
            switch (_featureSubsetStrategy == null ? 0 : _featureSubsetStrategy.hashCode()) {
               case 96673:
                  if ("all".equals(_featureSubsetStrategy)) {
                     var48 = numFeatures;
                     break label101;
                  }
                  break;
               case 3327342:
                  if ("log2".equals(_featureSubsetStrategy)) {
                     var48 = scala.math.package..MODULE$.max(1, (int)scala.runtime.RichDouble..MODULE$.ceil$extension(scala.Predef..MODULE$.doubleWrapper(scala.math.package..MODULE$.log((double)numFeatures) / scala.math.package..MODULE$.log((double)2.0F))));
                     break label101;
                  }
                  break;
               case 3538208:
                  if ("sqrt".equals(_featureSubsetStrategy)) {
                     var48 = (int)scala.runtime.RichDouble..MODULE$.ceil$extension(scala.Predef..MODULE$.doubleWrapper(scala.math.package..MODULE$.sqrt((double)numFeatures)));
                     break label101;
                  }
                  break;
               case 2021451457:
                  if ("onethird".equals(_featureSubsetStrategy)) {
                     var48 = (int)scala.runtime.RichDouble..MODULE$.ceil$extension(scala.Predef..MODULE$.doubleWrapper((double)numFeatures / (double)3.0F));
                     break label101;
                  }
            }

            Option var37 = scala.util.Try..MODULE$.apply((JFunction0.mcI.sp)() -> scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(_featureSubsetStrategy))).filter((JFunction1.mcZI.sp)(x$4) -> x$4 > 0).toOption();
            if (var37 instanceof Some) {
               Some var38 = (Some)var37;
               int value = BoxesRunTime.unboxToInt(var38.value());
               var48 = scala.math.package..MODULE$.min(value, numFeatures);
            } else {
               if (!scala.None..MODULE$.equals(var37)) {
                  throw new MatchError(var37);
               }

               Option var40 = scala.util.Try..MODULE$.apply((JFunction0.mcD.sp)() -> scala.collection.StringOps..MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString(_featureSubsetStrategy))).filter((JFunction1.mcZD.sp)(x$5) -> x$5 > (double)0).filter((JFunction1.mcZD.sp)(x$6) -> x$6 <= (double)1.0F).toOption();
               if (!(var40 instanceof Some)) {
                  ArraySeq.ofRef var10002 = scala.Predef..MODULE$.wrapRefArray((Object[])TreeEnsembleParams$.MODULE$.supportedFeatureSubsetStrategies());
                  throw new IllegalArgumentException("Supported values: " + var10002.mkString(", ") + ", (0.0-1.0], [1-n].");
               }

               Some var41 = (Some)var40;
               double value = BoxesRunTime.unboxToDouble(var41.value());
               var48 = (int)scala.math.package..MODULE$.ceil(value * (double)numFeatures);
            }
         }

         int numFeaturesPerNode = var48;
         return new DecisionTreeMetadata(numFeatures, numExamples, weightSum, numClasses, BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray(numBins).max(scala.math.Ordering.Int..MODULE$)), strategy.categoricalFeaturesInfo(), unorderedFeatures.toSet(), numBins, strategy.impurity(), strategy.quantileCalculationStrategy(), strategy.maxDepth(), strategy.minInstancesPerNode(), strategy.minWeightFractionPerNode(), strategy.minInfoGain(), numTrees, numFeaturesPerNode);
      }
   }

   public DecisionTreeMetadata buildMetadata(final RDD input, final Strategy strategy) {
      return this.buildMetadata(input, strategy, 1, "all");
   }

   public int numUnorderedBins(final int arity) {
      return (1 << arity - 1) - 1;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DecisionTreeMetadata$.class);
   }

   // $FF: synthetic method
   public static final int $anonfun$buildMetadata$1(final Instance x$1) {
      return x$1.features().size();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$buildMetadata$7(final int maxCategoriesPerFeature$1, final Tuple2 x$3) {
      return x$3._2$mcI$sp() == maxCategoriesPerFeature$1;
   }

   // $FF: synthetic method
   public static final void $anonfun$buildMetadata$10(final int maxCategoriesForUnorderedFeature$1, final HashSet unorderedFeatures$1, final int[] numBins$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int featureIndex = x0$1._1$mcI$sp();
         int numCategories = x0$1._2$mcI$sp();
         if (numCategories > 1) {
            if (numCategories <= maxCategoriesForUnorderedFeature$1) {
               unorderedFeatures$1.add(BoxesRunTime.boxToInteger(featureIndex));
               numBins$1[featureIndex] = MODULE$.numUnorderedBins(numCategories);
               BoxedUnit var9 = BoxedUnit.UNIT;
            } else {
               numBins$1[featureIndex] = numCategories;
               BoxedUnit var8 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$buildMetadata$11(final int[] numBins$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         int featureIndex = x0$2._1$mcI$sp();
         int numCategories = x0$2._2$mcI$sp();
         if (numCategories > 1) {
            numBins$1[featureIndex] = numCategories;
            BoxedUnit var6 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$2);
      }
   }

   private DecisionTreeMetadata$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
