package org.apache.spark.ml.tree.impl;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.feature.Instance;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.regression.DecisionTreeRegressionModel;
import org.apache.spark.ml.tree.Split;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.configuration.BoostingStrategy;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.impurity.Variance$;
import org.apache.spark.mllib.tree.loss.Loss;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.util.PeriodicRDDCheckpointer;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Function2;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.Predef;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.ArrayOps;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public final class GradientBoostedTrees$ implements Logging {
   public static final GradientBoostedTrees$ MODULE$ = new GradientBoostedTrees$();
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

   public Tuple2 run(final RDD input, final BoostingStrategy boostingStrategy, final long seed, final String featureSubsetStrategy, final Option instr) {
      Enumeration.Value algo = boostingStrategy.treeStrategy().algo();
      Enumeration.Value var10000 = Algo$.MODULE$.Regression();
      if (var10000 == null) {
         if (algo == null) {
            return this.boost(input, input, boostingStrategy, false, seed, featureSubsetStrategy, instr);
         }
      } else if (var10000.equals(algo)) {
         return this.boost(input, input, boostingStrategy, false, seed, featureSubsetStrategy, instr);
      }

      label22: {
         var10000 = Algo$.MODULE$.Classification();
         if (var10000 == null) {
            if (algo == null) {
               break label22;
            }
         } else if (var10000.equals(algo)) {
            break label22;
         }

         throw new IllegalArgumentException(algo + " is not supported by gradient boosting.");
      }

      RDD remappedInput = input.map((x) -> new Instance(x.label() * (double)2 - (double)1, x.weight(), x.features()), .MODULE$.apply(Instance.class));
      return this.boost(remappedInput, remappedInput, boostingStrategy, false, seed, featureSubsetStrategy, instr);
   }

   public Option run$default$5() {
      return scala.None..MODULE$;
   }

   public Tuple2 runWithValidation(final RDD input, final RDD validationInput, final BoostingStrategy boostingStrategy, final long seed, final String featureSubsetStrategy, final Option instr) {
      Enumeration.Value algo = boostingStrategy.treeStrategy().algo();
      Enumeration.Value var10000 = Algo$.MODULE$.Regression();
      if (var10000 == null) {
         if (algo == null) {
            return this.boost(input, validationInput, boostingStrategy, true, seed, featureSubsetStrategy, instr);
         }
      } else if (var10000.equals(algo)) {
         return this.boost(input, validationInput, boostingStrategy, true, seed, featureSubsetStrategy, instr);
      }

      label22: {
         var10000 = Algo$.MODULE$.Classification();
         if (var10000 == null) {
            if (algo == null) {
               break label22;
            }
         } else if (var10000.equals(algo)) {
            break label22;
         }

         throw new IllegalArgumentException(algo + " is not supported by the gradient boosting.");
      }

      RDD remappedInput = input.map((x) -> new Instance(x.label() * (double)2 - (double)1, x.weight(), x.features()), .MODULE$.apply(Instance.class));
      RDD remappedValidationInput = validationInput.map((x) -> new Instance(x.label() * (double)2 - (double)1, x.weight(), x.features()), .MODULE$.apply(Instance.class));
      return this.boost(remappedInput, remappedValidationInput, boostingStrategy, true, seed, featureSubsetStrategy, instr);
   }

   public Option runWithValidation$default$6() {
      return scala.None..MODULE$;
   }

   public RDD computeInitialPredictionAndError(final RDD data, final double initTreeWeight, final DecisionTreeRegressionModel initTree, final Loss loss, final Broadcast bcSplits) {
      return data.map((treePoint) -> {
         double pred = MODULE$.updatePrediction(treePoint, (double)0.0F, initTree, initTreeWeight, (Split[][])bcSplits.value());
         double error = loss.computeError(pred, treePoint.label());
         return new Tuple2.mcDD.sp(pred, error);
      }, .MODULE$.apply(Tuple2.class));
   }

   public RDD updatePredictionError(final RDD data, final RDD predictionAndError, final double treeWeight, final DecisionTreeRegressionModel tree, final Loss loss, final Broadcast bcSplits) {
      return data.zip(predictionAndError, .MODULE$.apply(Tuple2.class)).map((x0$1) -> {
         if (x0$1 != null) {
            TreePoint treePoint = (TreePoint)x0$1._1();
            Tuple2 var9 = (Tuple2)x0$1._2();
            if (var9 != null) {
               double pred = var9._1$mcD$sp();
               double newPred = MODULE$.updatePrediction(treePoint, pred, tree, treeWeight, (Split[][])bcSplits.value());
               double newError = loss.computeError(newPred, treePoint.label());
               return new Tuple2.mcDD.sp(newPred, newError);
            }
         }

         throw new MatchError(x0$1);
      }, .MODULE$.apply(Tuple2.class));
   }

   public double updatePrediction(final TreePoint treePoint, final double prediction, final DecisionTreeRegressionModel tree, final double weight, final Split[][] splits) {
      return prediction + tree.rootNode().predictBinned(treePoint.binnedFeatures(), splits).prediction() * weight;
   }

   public double updatePrediction(final Vector features, final double prediction, final DecisionTreeRegressionModel tree, final double weight) {
      return prediction + tree.rootNode().predictImpl(features).prediction() * weight;
   }

   public double computeWeightedError(final RDD data, final DecisionTreeRegressionModel[] trees, final double[] treeWeights, final Loss loss) {
      RDD qual$1 = data.map((x0$1) -> {
         if (x0$1 != null) {
            double label = x0$1.label();
            double weight = x0$1.weight();
            Vector features = x0$1.features();
            double predicted = BoxesRunTime.unboxToDouble(scala.collection.ArrayOps..MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps(trees), scala.Predef..MODULE$.wrapDoubleArray(treeWeights))), BoxesRunTime.boxToDouble((double)0.0F), (x0$2, x1$1) -> BoxesRunTime.boxToDouble($anonfun$computeWeightedError$2(features, BoxesRunTime.unboxToDouble(x0$2), x1$1))));
            return new Tuple2.mcDD.sp(loss.computeError(predicted, label) * weight, weight);
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(Tuple2.class));
      Function2 x$1 = (x0$3, x1$2) -> {
         Tuple2 var3 = new Tuple2(x0$3, x1$2);
         if (var3 != null) {
            Tuple2 var4 = (Tuple2)var3._1();
            Tuple2 var5 = (Tuple2)var3._2();
            if (var4 != null) {
               double err1 = var4._1$mcD$sp();
               double weight1 = var4._2$mcD$sp();
               if (var5 != null) {
                  double err2 = var5._1$mcD$sp();
                  double weight2 = var5._2$mcD$sp();
                  return new Tuple2.mcDD.sp(err1 + err2, weight1 + weight2);
               }
            }
         }

         throw new MatchError(var3);
      };
      int x$2 = qual$1.treeReduce$default$2();
      Tuple2 var7 = (Tuple2)qual$1.treeReduce(x$1, x$2);
      if (var7 != null) {
         double errSum = var7._1$mcD$sp();
         double weightSum = var7._2$mcD$sp();
         Tuple2.mcDD.sp var6 = new Tuple2.mcDD.sp(errSum, weightSum);
         double errSum = ((Tuple2)var6)._1$mcD$sp();
         double weightSum = ((Tuple2)var6)._2$mcD$sp();
         return errSum / weightSum;
      } else {
         throw new MatchError(var7);
      }
   }

   public double computeWeightedError(final RDD data, final RDD predError) {
      RDD qual$1 = data.zip(predError, .MODULE$.apply(Tuple2.class)).map((x0$1) -> {
         if (x0$1 != null) {
            TreePoint treePoint = (TreePoint)x0$1._1();
            Tuple2 var4 = (Tuple2)x0$1._2();
            if (var4 != null) {
               double err = var4._2$mcD$sp();
               return new Tuple2.mcDD.sp(err * treePoint.weight(), treePoint.weight());
            }
         }

         throw new MatchError(x0$1);
      }, .MODULE$.apply(Tuple2.class));
      Function2 x$1 = (x0$2, x1$1) -> {
         Tuple2 var3 = new Tuple2(x0$2, x1$1);
         if (var3 != null) {
            Tuple2 var4 = (Tuple2)var3._1();
            Tuple2 var5 = (Tuple2)var3._2();
            if (var4 != null) {
               double err1 = var4._1$mcD$sp();
               double weight1 = var4._2$mcD$sp();
               if (var5 != null) {
                  double err2 = var5._1$mcD$sp();
                  double weight2 = var5._2$mcD$sp();
                  return new Tuple2.mcDD.sp(err1 + err2, weight1 + weight2);
               }
            }
         }

         throw new MatchError(var3);
      };
      int x$2 = qual$1.treeReduce$default$2();
      Tuple2 var5 = (Tuple2)qual$1.treeReduce(x$1, x$2);
      if (var5 != null) {
         double errSum = var5._1$mcD$sp();
         double weightSum = var5._2$mcD$sp();
         Tuple2.mcDD.sp var4 = new Tuple2.mcDD.sp(errSum, weightSum);
         double errSum = ((Tuple2)var4)._1$mcD$sp();
         double weightSum = ((Tuple2)var4)._2$mcD$sp();
         return errSum / weightSum;
      } else {
         throw new MatchError(var5);
      }
   }

   public double[] evaluateEachIteration(final RDD data, final DecisionTreeRegressionModel[] trees, final double[] treeWeights, final Loss loss, final Enumeration.Value algo) {
      RDD var23;
      label23: {
         label22: {
            Enumeration.Value var10000 = Algo$.MODULE$.Classification();
            if (var10000 == null) {
               if (algo == null) {
                  break label22;
               }
            } else if (var10000.equals(algo)) {
               break label22;
            }

            var23 = data;
            break label23;
         }

         var23 = data.map((x) -> new Instance(x.label() * (double)2 - (double)1, x.weight(), x.features()), .MODULE$.apply(Instance.class));
      }

      RDD remappedData = var23;
      int numTrees = trees.length;
      RDD qual$1 = remappedData.mapPartitions((iter) -> iter.map((x0$1) -> {
            if (x0$1 != null) {
               double label = x0$1.label();
               double weight = x0$1.weight();
               Vector features = x0$1.features();
               double[] pred = (double[])scala.Array..MODULE$.tabulate(numTrees, (JFunction1.mcDI.sp)(i) -> trees[i].rootNode().predictImpl(features).prediction() * treeWeights[i], .MODULE$.Double());
               double[] err = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps((double[])scala.collection.ArrayOps..MODULE$.drop$extension(scala.Predef..MODULE$.doubleArrayOps((double[])scala.collection.ArrayOps..MODULE$.scanLeft$extension(scala.Predef..MODULE$.doubleArrayOps(pred), BoxesRunTime.boxToDouble((double)0.0F), (JFunction2.mcDDD.sp)(x$3, x$4) -> x$3 + x$4, .MODULE$.Double())), 1)), (JFunction1.mcDD.sp)(p) -> loss.computeError(p, label) * weight, .MODULE$.Double());
               return new Tuple2(err, BoxesRunTime.boxToDouble(weight));
            } else {
               throw new MatchError(x0$1);
            }
         }), remappedData.mapPartitions$default$2(), .MODULE$.apply(Tuple2.class));
      Function2 x$1 = (x0$2, x1$1) -> {
         Tuple2 var4 = new Tuple2(x0$2, x1$1);
         if (var4 != null) {
            Tuple2 var5 = (Tuple2)var4._1();
            Tuple2 var6 = (Tuple2)var4._2();
            if (var5 != null) {
               double[] err1 = (double[])var5._1();
               double weight1 = var5._2$mcD$sp();
               if (var6 != null) {
                  double[] err2 = (double[])var6._1();
                  double weight2 = var6._2$mcD$sp();
                  scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numTrees).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> err1[i] += err2[i]);
                  return new Tuple2(err1, BoxesRunTime.boxToDouble(weight1 + weight2));
               }
            }
         }

         throw new MatchError(var4);
      };
      int x$2 = qual$1.treeReduce$default$2();
      Tuple2 var13 = (Tuple2)qual$1.treeReduce(x$1, x$2);
      if (var13 != null) {
         double[] errSum = (double[])var13._1();
         double weightSum = var13._2$mcD$sp();
         Tuple2 var12 = new Tuple2(errSum, BoxesRunTime.boxToDouble(weightSum));
         double[] errSum = (double[])var12._1();
         double weightSum = var12._2$mcD$sp();
         return (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(errSum), (JFunction1.mcDD.sp)(x$6) -> x$6 / weightSum, .MODULE$.Double());
      } else {
         throw new MatchError(var13);
      }
   }

   public Tuple2 boost(final RDD input, final RDD validationInput, final BoostingStrategy boostingStrategy, final boolean validate, final long seed, final String featureSubsetStrategy, final Option instr) {
      TimeTracker timer = new TimeTracker();
      timer.start("total");
      timer.start("init");
      SparkContext sc = input.sparkContext();
      boostingStrategy.assertValid();
      int numIterations = boostingStrategy.numIterations();
      DecisionTreeRegressionModel[] baseLearners = new DecisionTreeRegressionModel[numIterations];
      double[] baseLearnerWeights = new double[numIterations];
      Loss loss = boostingStrategy.loss();
      double learningRate = boostingStrategy.learningRate();
      Strategy treeStrategy = boostingStrategy.treeStrategy().copy();
      double validationTol = boostingStrategy.validationTol();
      treeStrategy.algo_$eq(Algo$.MODULE$.Regression());
      treeStrategy.impurity_$eq(Variance$.MODULE$);
      scala.Predef..MODULE$.require(!treeStrategy.bootstrap(), () -> "GradientBoostedTrees does not need bootstrap sampling");
      treeStrategy.assertValid();
      PeriodicRDDCheckpointer predErrorCheckpointer = new PeriodicRDDCheckpointer(treeStrategy.getCheckpointInterval(), sc, org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
      timer.stop("init");
      this.logDebug((Function0)(() -> "##########"));
      this.logDebug((Function0)(() -> "Building tree 0"));
      this.logDebug((Function0)(() -> "##########"));
      timer.start("building tree 0");
      RDD retaggedInput = input.retag(Instance.class);
      timer.start("buildMetadata");
      DecisionTreeMetadata metadata = DecisionTreeMetadata$.MODULE$.buildMetadata(retaggedInput, treeStrategy, 1, featureSubsetStrategy);
      timer.stop("buildMetadata");
      timer.start("findSplits");
      Split[][] splits = RandomForest$.MODULE$.findSplits(retaggedInput, metadata, seed);
      timer.stop("findSplits");
      Broadcast bcSplits = sc.broadcast(splits, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Split.class))));
      RDD treePoints = TreePoint$.MODULE$.convertToTreeRDD(retaggedInput, splits, metadata).persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK()).setName("binned tree points");
      RDD firstCounts = BaggedPoint$.MODULE$.convertToBaggedRDD(treePoints, treeStrategy.subsamplingRate(), 1, treeStrategy.bootstrap(), (tp) -> BoxesRunTime.boxToDouble($anonfun$boost$5(tp)), seed).map((baggedx) -> BoxesRunTime.boxToInteger($anonfun$boost$6(baggedx)), .MODULE$.Int()).persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK()).setName("firstCounts at iter=0");
      RDD firstBagged = treePoints.zip(firstCounts, .MODULE$.Int()).map((x0$1) -> {
         if (x0$1 != null) {
            TreePoint treePoint = (TreePoint)x0$1._1();
            int count = x0$1._2$mcI$sp();
            return new BaggedPoint(treePoint, new int[]{count}, treePoint.weight());
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(BaggedPoint.class));
      ArrayOps var10000 = scala.collection.ArrayOps..MODULE$;
      Predef var10001 = scala.Predef..MODULE$;
      int x$5 = 1;
      None x$9 = scala.None..MODULE$;
      boolean x$10 = RandomForest$.MODULE$.runBagged$default$9();
      DecisionTreeRegressionModel firstTreeModel = (DecisionTreeRegressionModel)var10000.head$extension(var10001.refArrayOps(RandomForest$.MODULE$.runBagged(firstBagged, metadata, bcSplits, treeStrategy, 1, featureSubsetStrategy, seed, instr, x$10, x$9)));
      firstCounts.unpersist(firstCounts.unpersist$default$1());
      double firstTreeWeight = (double)1.0F;
      baseLearners[0] = firstTreeModel;
      baseLearnerWeights[0] = firstTreeWeight;
      ObjectRef predError = ObjectRef.create(this.computeInitialPredictionAndError(treePoints, firstTreeWeight, firstTreeModel, loss, bcSplits));
      predErrorCheckpointer.update((RDD)predError.elem);
      this.logDebug((Function0)(() -> {
         double var10000 = MODULE$.computeWeightedError(treePoints, (RDD)predError.elem);
         return "error of gbt = " + var10000;
      }));
      timer.stop("building tree 0");
      RDD validationTreePoints = null;
      RDD validatePredError = null;
      PeriodicRDDCheckpointer validatePredErrorCheckpointer = null;
      double bestValidateError = (double)0.0F;
      if (validate) {
         timer.start("init validation");
         validationTreePoints = TreePoint$.MODULE$.convertToTreeRDD(validationInput.retag(Instance.class), splits, metadata).persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
         validatePredError = this.computeInitialPredictionAndError(validationTreePoints, firstTreeWeight, firstTreeModel, loss, bcSplits);
         validatePredErrorCheckpointer = new PeriodicRDDCheckpointer(treeStrategy.getCheckpointInterval(), sc, org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
         validatePredErrorCheckpointer.update(validatePredError);
         bestValidateError = this.computeWeightedError(validationTreePoints, validatePredError);
         BoxesRunTime.boxToDouble(timer.stop("init validation"));
      } else {
         BoxedUnit var69 = BoxedUnit.UNIT;
      }

      int bestM = 1;
      IntRef m = IntRef.create(1);

      for(boolean doneLearning = false; m.elem < numIterations && !doneLearning; ++m.elem) {
         timer.start("building tree " + m.elem);
         this.logDebug((Function0)(() -> "###################################################"));
         this.logDebug((Function0)(() -> "Gradient boosting tree iteration " + m.elem));
         this.logDebug((Function0)(() -> "###################################################"));
         RDD labelWithCounts = BaggedPoint$.MODULE$.convertToBaggedRDD(treePoints, treeStrategy.subsamplingRate(), 1, treeStrategy.bootstrap(), (tp) -> BoxesRunTime.boxToDouble($anonfun$boost$12(tp)), seed + (long)m.elem).zip((RDD)predError.elem, .MODULE$.apply(Tuple2.class)).map((x0$2) -> {
            if (x0$2 != null) {
               BaggedPoint bagged = (BaggedPoint)x0$2._1();
               Tuple2 var5 = (Tuple2)x0$2._2();
               if (var5 != null) {
                  double pred = var5._1$mcD$sp();
                  scala.Predef..MODULE$.require(bagged.subsampleCounts().length == 1);
                  scala.Predef..MODULE$.require(bagged.sampleWeight() == ((TreePoint)bagged.datum()).weight());
                  double newLabel = -loss.gradient(pred, ((TreePoint)bagged.datum()).label());
                  return new Tuple2.mcDI.sp(newLabel, BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.intArrayOps(bagged.subsampleCounts()))));
               }
            }

            throw new MatchError(x0$2);
         }, .MODULE$.apply(Tuple2.class)).persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK()).setName("labelWithCounts at iter=" + m.elem);
         RDD bagged = treePoints.zip(labelWithCounts, .MODULE$.apply(Tuple2.class)).map((x0$3) -> {
            if (x0$3 != null) {
               TreePoint treePoint = (TreePoint)x0$3._1();
               Tuple2 var4 = (Tuple2)x0$3._2();
               if (var4 != null) {
                  double newLabel = var4._1$mcD$sp();
                  int count = var4._2$mcI$sp();
                  TreePoint newTreePoint = new TreePoint(newLabel, treePoint.binnedFeatures(), treePoint.weight());
                  return new BaggedPoint(newTreePoint, new int[]{count}, treePoint.weight());
               }
            }

            throw new MatchError(x0$3);
         }, .MODULE$.apply(BaggedPoint.class));
         var10000 = scala.collection.ArrayOps..MODULE$;
         var10001 = scala.Predef..MODULE$;
         int x$15 = 1;
         long x$17 = seed + (long)m.elem;
         None x$18 = scala.None..MODULE$;
         None x$19 = scala.None..MODULE$;
         boolean x$20 = RandomForest$.MODULE$.runBagged$default$9();
         DecisionTreeRegressionModel model = (DecisionTreeRegressionModel)var10000.head$extension(var10001.refArrayOps(RandomForest$.MODULE$.runBagged(bagged, metadata, bcSplits, treeStrategy, 1, featureSubsetStrategy, x$17, x$18, x$20, x$19)));
         labelWithCounts.unpersist(labelWithCounts.unpersist$default$1());
         timer.stop("building tree " + m.elem);
         baseLearners[m.elem] = model;
         baseLearnerWeights[m.elem] = learningRate;
         predError.elem = this.updatePredictionError(treePoints, (RDD)predError.elem, baseLearnerWeights[m.elem], baseLearners[m.elem], loss, bcSplits);
         predErrorCheckpointer.update((RDD)predError.elem);
         this.logDebug((Function0)(() -> {
            double var10000 = MODULE$.computeWeightedError(treePoints, (RDD)predError.elem);
            return "error of gbt = " + var10000;
         }));
         if (validate) {
            validatePredError = this.updatePredictionError(validationTreePoints, validatePredError, baseLearnerWeights[m.elem], baseLearners[m.elem], loss, bcSplits);
            validatePredErrorCheckpointer.update(validatePredError);
            double currentValidateError = this.computeWeightedError(validationTreePoints, validatePredError);
            if (bestValidateError - currentValidateError < validationTol * Math.max(currentValidateError, 0.01)) {
               doneLearning = true;
            } else if (currentValidateError < bestValidateError) {
               bestValidateError = currentValidateError;
               bestM = m.elem + 1;
            }
         }
      }

      timer.stop("total");
      this.logInfo((Function0)(() -> "Internal timing for DecisionTree:"));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIMER..MODULE$, timer)})))));
      bcSplits.destroy();
      treePoints.unpersist(treePoints.unpersist$default$1());
      predErrorCheckpointer.unpersistDataSet();
      predErrorCheckpointer.deleteAllCheckpoints();
      if (validate) {
         boolean x$21 = validationTreePoints.unpersist$default$1();
         validationTreePoints.unpersist(x$21);
         validatePredErrorCheckpointer.unpersistDataSet();
         validatePredErrorCheckpointer.deleteAllCheckpoints();
      }

      return validate ? new Tuple2(scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.refArrayOps(baseLearners), 0, bestM), scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps(baseLearnerWeights), 0, bestM)) : new Tuple2(baseLearners, baseLearnerWeights);
   }

   public Option boost$default$7() {
      return scala.None..MODULE$;
   }

   // $FF: synthetic method
   public static final double $anonfun$computeWeightedError$2(final Vector features$1, final double x0$2, final Tuple2 x1$1) {
      Tuple2 var6 = new Tuple2(BoxesRunTime.boxToDouble(x0$2), x1$1);
      if (var6 != null) {
         double acc = var6._1$mcD$sp();
         Tuple2 var9 = (Tuple2)var6._2();
         if (var9 != null) {
            DecisionTreeRegressionModel model = (DecisionTreeRegressionModel)var9._1();
            double weight = var9._2$mcD$sp();
            return MODULE$.updatePrediction(features$1, acc, model, weight);
         }
      }

      throw new MatchError(var6);
   }

   // $FF: synthetic method
   public static final double $anonfun$boost$5(final TreePoint tp) {
      return tp.weight();
   }

   // $FF: synthetic method
   public static final int $anonfun$boost$6(final BaggedPoint bagged) {
      scala.Predef..MODULE$.require(bagged.subsampleCounts().length == 1);
      scala.Predef..MODULE$.require(bagged.sampleWeight() == ((TreePoint)bagged.datum()).weight());
      return BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.intArrayOps(bagged.subsampleCounts())));
   }

   // $FF: synthetic method
   public static final double $anonfun$boost$12(final TreePoint tp) {
      return tp.weight();
   }

   private GradientBoostedTrees$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
