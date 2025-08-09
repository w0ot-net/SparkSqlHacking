package org.apache.spark.ml.tree.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.classification.DecisionTreeClassificationModel;
import org.apache.spark.ml.feature.Instance;
import org.apache.spark.ml.regression.DecisionTreeRegressionModel;
import org.apache.spark.ml.tree.CategoricalSplit;
import org.apache.spark.ml.tree.ContinuousSplit;
import org.apache.spark.ml.tree.DecisionTreeModel;
import org.apache.spark.ml.tree.LearningNode;
import org.apache.spark.ml.tree.LearningNode$;
import org.apache.spark.ml.tree.Split;
import org.apache.spark.ml.util.Instrumentation;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.impurity.ImpurityCalculator;
import org.apache.spark.mllib.tree.model.ImpurityStats;
import org.apache.spark.mllib.tree.model.ImpurityStats$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.util.PeriodicRDDCheckpointer;
import org.apache.spark.util.collection.OpenHashMap;
import org.apache.spark.util.random.XORShiftRandom;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.MapOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.Growable;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.LongRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Null;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.util.Random;

public final class RandomForest$ implements Logging, Serializable {
   public static final RandomForest$ MODULE$ = new RandomForest$();
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

   public DecisionTreeModel[] run(final RDD input, final Strategy strategy, final int numTrees, final String featureSubsetStrategy, final long seed) {
      RDD instances = input.map((x0$1) -> {
         if (x0$1 != null) {
            double label = x0$1.label();
            Vector features = x0$1.features();
            return new Instance(label, (double)1.0F, features.asML());
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(Instance.class));
      return this.run(instances, strategy, numTrees, featureSubsetStrategy, seed, scala.None..MODULE$, this.run$default$7(), this.run$default$8());
   }

   public DecisionTreeModel[] runBagged(final RDD baggedInput, final DecisionTreeMetadata metadata, final Broadcast bcSplits, final Strategy strategy, final int numTrees, final String featureSubsetStrategy, final long seed, final Option instr, final boolean prune, final Option parentUID) {
      TimeTracker timer = new TimeTracker();
      timer.start("total");
      SparkContext sc = baggedInput.sparkContext();
      if (instr instanceof Some var18) {
         Instrumentation instrumentation = (Instrumentation)var18.value();
         instrumentation.logNumFeatures((long)metadata.numFeatures());
         instrumentation.logNumClasses((long)metadata.numClasses());
         instrumentation.logNumExamples(metadata.numExamples());
         instrumentation.logSumOfWeights(metadata.weightedNumExamples());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         if (!scala.None..MODULE$.equals(instr)) {
            throw new MatchError(instr);
         }

         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"numFeatures: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_FEATURES..MODULE$, BoxesRunTime.boxToInteger(metadata.numFeatures()))})))));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"numClasses: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_CLASSES..MODULE$, BoxesRunTime.boxToInteger(metadata.numClasses()))})))));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"numExamples: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_EXAMPLES..MODULE$, BoxesRunTime.boxToLong(metadata.numExamples()))})))));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"weightedNumExamples: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_WEIGHTED_EXAMPLES..MODULE$, BoxesRunTime.boxToDouble(metadata.weightedNumExamples()))}))))));
         BoxedUnit var42 = BoxedUnit.UNIT;
      }

      timer.start("init");
      int maxDepth = strategy.maxDepth();
      scala.Predef..MODULE$.require(maxDepth <= 30, () -> "DecisionTree currently only supports maxDepth <= 30, but was given maxDepth = " + maxDepth + ".");
      long maxMemoryUsage = (long)strategy.maxMemoryInMB() * 1024L * 1024L;
      this.logDebug((Function0)(() -> "max memory usage for aggregates = " + maxMemoryUsage + " bytes."));
      RDD nodeIds = null;
      PeriodicRDDCheckpointer nodeIdCheckpointer = null;
      if (strategy.useNodeIdCache()) {
         nodeIds = baggedInput.map((x$1) -> (int[])scala.Array..MODULE$.fill(numTrees, (JFunction0.mcI.sp)() -> 1, .MODULE$.Int()), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Integer.TYPE)));
         nodeIdCheckpointer = new PeriodicRDDCheckpointer(strategy.getCheckpointInterval(), sc, org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
         nodeIdCheckpointer.update(nodeIds);
      }

      ListBuffer nodeStack = new ListBuffer();
      Random rng = new Random();
      rng.setSeed(seed);
      LearningNode[] topNodes = (LearningNode[])scala.Array..MODULE$.fill(numTrees, () -> LearningNode$.MODULE$.emptyNode(1), .MODULE$.apply(LearningNode.class));
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numTrees).foreach((treeIndex) -> $anonfun$runBagged$10(nodeStack, topNodes, BoxesRunTime.unboxToInt(treeIndex)));
      timer.stop("init");

      for(; nodeStack.nonEmpty(); timer.stop("findBestSplits")) {
         Tuple2 var29 = this.selectNodesToSplit(nodeStack, maxMemoryUsage, metadata, rng);
         if (var29 == null) {
            throw new MatchError(var29);
         }

         scala.collection.immutable.Map nodesForGroup = (scala.collection.immutable.Map)var29._1();
         scala.collection.immutable.Map treeToNodeToIndexInfo = (scala.collection.immutable.Map)var29._2();
         Tuple2 var28 = new Tuple2(nodesForGroup, treeToNodeToIndexInfo);
         scala.collection.immutable.Map nodesForGroup = (scala.collection.immutable.Map)var28._1();
         scala.collection.immutable.Map treeToNodeToIndexInfo = (scala.collection.immutable.Map)var28._2();
         scala.Predef..MODULE$.assert(nodesForGroup.nonEmpty(), () -> "RandomForest selected empty nodesForGroup.  Error for unknown reason.");
         scala.collection.immutable.Map topNodesForGroup = ((IterableOnceOps)nodesForGroup.keys().map((treeIdx) -> $anonfun$runBagged$12(topNodes, BoxesRunTime.unboxToInt(treeIdx)))).toMap(scala..less.colon.less..MODULE$.refl());
         timer.start("findBestSplits");
         scala.collection.immutable.Map[] bestSplit = this.findBestSplits(baggedInput, metadata, topNodesForGroup, nodesForGroup, treeToNodeToIndexInfo, bcSplits, nodeStack, timer, nodeIds, strategy.useNodeIdCache());
         if (strategy.useNodeIdCache()) {
            nodeIds = this.updateNodeIds(baggedInput, nodeIds, bcSplits, bestSplit);
            nodeIdCheckpointer.update(nodeIds);
         }
      }

      timer.stop("total");
      this.logInfo((Function0)(() -> "Internal timing for DecisionTree:"));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIMER..MODULE$, timer)})))));
      if (strategy.useNodeIdCache()) {
         nodeIdCheckpointer.unpersistDataSet();
         nodeIdCheckpointer.deleteAllCheckpoints();
      }

      int numFeatures = metadata.numFeatures();
      if (parentUID instanceof Some var38) {
         String uid = (String)var38.value();
         Enumeration.Value var44 = strategy.algo();
         Enumeration.Value var40 = Algo$.MODULE$.Classification();
         if (var44 == null) {
            if (var40 == null) {
               return (DecisionTreeModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(topNodes), (rootNode) -> new DecisionTreeClassificationModel(uid, rootNode.toNode(prune), numFeatures, strategy.getNumClasses()), .MODULE$.apply(DecisionTreeModel.class));
            }
         } else if (var44.equals(var40)) {
            return (DecisionTreeModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(topNodes), (rootNode) -> new DecisionTreeClassificationModel(uid, rootNode.toNode(prune), numFeatures, strategy.getNumClasses()), .MODULE$.apply(DecisionTreeModel.class));
         }

         return (DecisionTreeModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(topNodes), (rootNode) -> new DecisionTreeRegressionModel(uid, rootNode.toNode(prune), numFeatures), .MODULE$.apply(DecisionTreeModel.class));
      } else if (!scala.None..MODULE$.equals(parentUID)) {
         throw new MatchError(parentUID);
      } else {
         Enumeration.Value var43 = strategy.algo();
         Enumeration.Value var41 = Algo$.MODULE$.Classification();
         if (var43 == null) {
            if (var41 == null) {
               return (DecisionTreeModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(topNodes), (rootNode) -> new DecisionTreeClassificationModel(rootNode.toNode(prune), numFeatures, strategy.getNumClasses()), .MODULE$.apply(DecisionTreeModel.class));
            }
         } else if (var43.equals(var41)) {
            return (DecisionTreeModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(topNodes), (rootNode) -> new DecisionTreeClassificationModel(rootNode.toNode(prune), numFeatures, strategy.getNumClasses()), .MODULE$.apply(DecisionTreeModel.class));
         }

         return (DecisionTreeModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(topNodes), (rootNode) -> new DecisionTreeRegressionModel(rootNode.toNode(prune), numFeatures), .MODULE$.apply(DecisionTreeModel.class));
      }
   }

   public DecisionTreeModel[] run(final RDD input, final Strategy strategy, final int numTrees, final String featureSubsetStrategy, final long seed, final Option instr, final boolean prune, final Option parentUID) {
      TimeTracker timer = new TimeTracker();
      timer.start("build metadata");
      DecisionTreeMetadata metadata = DecisionTreeMetadata$.MODULE$.buildMetadata(input.retag(Instance.class), strategy, numTrees, featureSubsetStrategy);
      timer.stop("build metadata");
      RDD retaggedInput = input.retag(Instance.class);
      timer.start("findSplits");
      Split[][] splits = this.findSplits(retaggedInput, metadata, seed);
      timer.stop("findSplits");
      this.logDebug((Function0)(() -> "numBins: feature: number of bins"));
      this.logDebug((Function0)(() -> scala.package..MODULE$.Range().apply(0, metadata.numFeatures()).map((featureIndex) -> $anonfun$run$4(metadata, BoxesRunTime.unboxToInt(featureIndex))).mkString("\n")));
      RDD treeInput = TreePoint$.MODULE$.convertToTreeRDD(retaggedInput, splits, metadata);
      Broadcast bcSplits = input.sparkContext().broadcast(splits, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Split.class))));
      RDD baggedInput = BaggedPoint$.MODULE$.convertToBaggedRDD(treeInput, strategy.subsamplingRate(), numTrees, strategy.bootstrap(), (tp) -> BoxesRunTime.boxToDouble($anonfun$run$5(tp)), seed).persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK()).setName("bagged tree points");
      DecisionTreeModel[] trees = this.runBagged(baggedInput, metadata, bcSplits, strategy, numTrees, featureSubsetStrategy, seed, instr, prune, parentUID);
      baggedInput.unpersist(baggedInput.unpersist$default$1());
      bcSplits.destroy();
      return trees;
   }

   public boolean runBagged$default$9() {
      return true;
   }

   public Option runBagged$default$10() {
      return scala.None..MODULE$;
   }

   public boolean run$default$7() {
      return true;
   }

   public Option run$default$8() {
      return scala.None..MODULE$;
   }

   private RDD updateNodeIds(final RDD input, final RDD nodeIds, final Broadcast bcSplits, final scala.collection.immutable.Map[] bestSplits) {
      scala.Predef..MODULE$.require(nodeIds != null && bestSplits != null);
      return input.zip(nodeIds, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Integer.TYPE))).map((x0$1) -> {
         if (x0$1 != null) {
            BaggedPoint point = (BaggedPoint)x0$1._1();
            int[] ids = (int[])x0$1._2();

            for(IntRef treeId = IntRef.create(0); treeId.elem < bestSplits.length; ++treeId.elem) {
               scala.collection.immutable.Map bestSplitsInTree = bestSplits[treeId.elem];
               if (bestSplitsInTree != null) {
                  int nodeId = ids[treeId.elem];
                  bestSplitsInTree.get(BoxesRunTime.boxToInteger(nodeId)).foreach((bestSplit) -> {
                     $anonfun$updateNodeIds$2(point, bcSplits, nodeId, ids, treeId, bestSplit);
                     return BoxedUnit.UNIT;
                  });
               }
            }

            return ids;
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Integer.TYPE)));
   }

   private void mixedBinSeqOp(final DTStatsAggregator agg, final TreePoint treePoint, final Split[][] splits, final Set unorderedFeatures, final int numSamples, final double sampleWeight, final Option featuresForNode) {
      int numFeaturesPerNode = featuresForNode.nonEmpty() ? ((int[])featuresForNode.get()).length : agg.metadata().numFeatures();

      for(int featureIndexIdx = 0; featureIndexIdx < numFeaturesPerNode; ++featureIndexIdx) {
         int featureIndex = featuresForNode.nonEmpty() ? ((int[])featuresForNode.get())[featureIndexIdx] : featureIndexIdx;
         if (unorderedFeatures.contains(BoxesRunTime.boxToInteger(featureIndex))) {
            int featureValue = treePoint.binnedFeatures()[featureIndex];
            int leftNodeFeatureOffset = agg.getFeatureOffset(featureIndexIdx);
            int numSplits = agg.metadata().numSplits(featureIndex);
            Split[] featureSplits = splits[featureIndex];

            for(int splitIndex = 0; splitIndex < numSplits; ++splitIndex) {
               if (featureSplits[splitIndex].shouldGoLeft(featureValue, featureSplits)) {
                  agg.featureUpdate(leftNodeFeatureOffset, splitIndex, treePoint.label(), numSamples, sampleWeight);
               }
            }
         } else {
            int binIndex = treePoint.binnedFeatures()[featureIndex];
            agg.update(featureIndexIdx, binIndex, treePoint.label(), numSamples, sampleWeight);
         }
      }

   }

   private void orderedBinSeqOp(final DTStatsAggregator agg, final TreePoint treePoint, final int numSamples, final double sampleWeight, final Option featuresForNode) {
      double label = treePoint.label();
      if (featuresForNode.nonEmpty()) {
         for(int featureIndexIdx = 0; featureIndexIdx < ((int[])featuresForNode.get()).length; ++featureIndexIdx) {
            int binIndex = treePoint.binnedFeatures()[((int[])featuresForNode.get())[featureIndexIdx]];
            agg.update(featureIndexIdx, binIndex, label, numSamples, sampleWeight);
         }

      } else {
         int numFeatures = agg.metadata().numFeatures();

         for(int featureIndex = 0; featureIndex < numFeatures; ++featureIndex) {
            int binIndex = treePoint.binnedFeatures()[featureIndex];
            agg.update(featureIndex, binIndex, label, numSamples, sampleWeight);
         }

      }
   }

   public scala.collection.immutable.Map[] findBestSplits(final RDD input, final DecisionTreeMetadata metadata, final scala.collection.immutable.Map topNodesForGroup, final scala.collection.immutable.Map nodesForGroup, final scala.collection.immutable.Map treeToNodeToIndexInfo, final Broadcast bcSplits, final ListBuffer nodeStack, final TimeTracker timer, final RDD nodeIds, final boolean outputBestSplits) {
      boolean useNodeIdCache = nodeIds != null;
      int numNodes = BoxesRunTime.unboxToInt(((IterableOnceOps)nodesForGroup.values().map((x$3) -> BoxesRunTime.boxToInteger($anonfun$findBestSplits$1(x$3)))).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      this.logDebug((Function0)(() -> "numNodes = " + numNodes));
      this.logDebug((Function0)(() -> "numFeatures = " + metadata.numFeatures()));
      this.logDebug((Function0)(() -> "numClasses = " + metadata.numClasses()));
      this.logDebug((Function0)(() -> "isMulticlass = " + metadata.isMulticlass()));
      this.logDebug((Function0)(() -> "isMulticlassWithCategoricalFeatures = " + metadata.isMulticlassWithCategoricalFeatures()));
      this.logDebug((Function0)(() -> "using nodeIdCache = " + useNodeIdCache));
      LearningNode[] nodes = new LearningNode[numNodes];
      nodesForGroup.foreach((x0$1) -> {
         $anonfun$findBestSplits$14(nodes, treeToNodeToIndexInfo, x0$1);
         return BoxedUnit.UNIT;
      });
      timer.start("chooseSplits");
      Option nodeToFeatures = getNodeToFeatures$1(treeToNodeToIndexInfo, metadata);
      Broadcast nodeToFeaturesBc = input.sparkContext().broadcast(nodeToFeatures, .MODULE$.apply(Option.class));
      RDD var10000;
      if (useNodeIdCache) {
         RDD qual$1 = input.zip(nodeIds, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Integer.TYPE)));
         Function1 x$1 = (points) -> {
            DTStatsAggregator[] nodeStatsAggregators = (DTStatsAggregator[])scala.Array..MODULE$.tabulate(numNodes, (nodeIndex) -> $anonfun$findBestSplits$17(nodeToFeaturesBc, metadata, BoxesRunTime.unboxToInt(nodeIndex)), .MODULE$.apply(DTStatsAggregator.class));
            points.foreach((x$4) -> this.binSeqOpWithNodeIdCache$1(nodeStatsAggregators, x$4, (Split[][])bcSplits.value(), treeToNodeToIndexInfo, metadata));
            return scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps(nodeStatsAggregators)).zipWithIndex().map((x$5) -> x$5.swap());
         };
         boolean x$2 = qual$1.mapPartitions$default$2();
         var10000 = qual$1.mapPartitions(x$1, x$2, .MODULE$.apply(Tuple2.class));
      } else {
         var10000 = input.mapPartitions((points) -> {
            DTStatsAggregator[] nodeStatsAggregators = (DTStatsAggregator[])scala.Array..MODULE$.tabulate(numNodes, (nodeIndex) -> $anonfun$findBestSplits$22(nodeToFeaturesBc, metadata, BoxesRunTime.unboxToInt(nodeIndex)), .MODULE$.apply(DTStatsAggregator.class));
            points.foreach((x$6) -> this.binSeqOp$1(nodeStatsAggregators, x$6, (Split[][])bcSplits.value(), treeToNodeToIndexInfo, topNodesForGroup, metadata));
            return scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps(nodeStatsAggregators)).zipWithIndex().map((x$7) -> x$7.swap());
         }, input.mapPartitions$default$2(), .MODULE$.apply(Tuple2.class));
      }

      RDD partitionAggregates = var10000;
      scala.collection.Map nodeToBestSplits = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(partitionAggregates, .MODULE$.Int(), .MODULE$.apply(DTStatsAggregator.class), scala.math.Ordering.Int..MODULE$).reduceByKey((a, b) -> a.merge(b)).map((x0$2) -> {
         if (x0$2 != null) {
            int nodeIndex = x0$2._1$mcI$sp();
            DTStatsAggregator aggStats = (DTStatsAggregator)x0$2._2();
            Option featuresForNode = ((Option)nodeToFeaturesBc.value()).flatMap((nodeToFeatures) -> new Some(nodeToFeatures.apply(BoxesRunTime.boxToInteger(nodeIndex))));
            Tuple2 var11 = MODULE$.binsToBestSplit(aggStats, (Split[][])bcSplits.value(), featuresForNode, nodes[nodeIndex]);
            if (var11 != null) {
               Split split = (Split)var11._1();
               ImpurityStats stats = (ImpurityStats)var11._2();
               if (split != null && stats != null) {
                  Tuple2 var10 = new Tuple2(split, stats);
                  Split splitx = (Split)var10._1();
                  ImpurityStats statsx = (ImpurityStats)var10._2();
                  return new Tuple2(BoxesRunTime.boxToInteger(nodeIndex), new Tuple2(splitx, statsx));
               }
            }

            throw new MatchError(var11);
         } else {
            throw new MatchError(x0$2);
         }
      }, .MODULE$.apply(Tuple2.class)), .MODULE$.Int(), .MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).collectAsMap();
      nodeToFeaturesBc.destroy();
      timer.stop("chooseSplits");
      scala.collection.mutable.Map[] bestSplits = outputBestSplits ? (scala.collection.mutable.Map[])scala.Array..MODULE$.ofDim(metadata.numTrees(), .MODULE$.apply(scala.collection.mutable.Map.class)) : null;
      nodesForGroup.foreach((x0$3) -> {
         $anonfun$findBestSplits$29(treeToNodeToIndexInfo, nodeToBestSplits, metadata, outputBestSplits, bestSplits, nodeStack, x0$3);
         return BoxedUnit.UNIT;
      });
      return outputBestSplits ? (scala.collection.immutable.Map[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])bestSplits), (m) -> m == null ? null : m.toMap(scala..less.colon.less..MODULE$.refl()), .MODULE$.apply(scala.collection.immutable.Map.class)) : null;
   }

   public TimeTracker findBestSplits$default$8() {
      return new TimeTracker();
   }

   public RDD findBestSplits$default$9() {
      return null;
   }

   public boolean findBestSplits$default$10() {
      return false;
   }

   private ImpurityStats calculateImpurityStats(final ImpurityStats stats, final ImpurityCalculator leftImpurityCalculator, final ImpurityCalculator rightImpurityCalculator, final DecisionTreeMetadata metadata) {
      ImpurityCalculator parentImpurityCalculator = stats == null ? leftImpurityCalculator.copy().add(rightImpurityCalculator) : stats.impurityCalculator();
      double impurity = stats == null ? parentImpurityCalculator.calculate() : stats.impurity();
      long leftRawCount = leftImpurityCalculator.rawCount();
      long rightRawCount = rightImpurityCalculator.rawCount();
      double leftCount = leftImpurityCalculator.count();
      double rightCount = rightImpurityCalculator.count();
      double totalCount = leftCount + rightCount;
      boolean violatesMinInstancesPerNode = leftRawCount < (long)metadata.minInstancesPerNode() || rightRawCount < (long)metadata.minInstancesPerNode();
      boolean violatesMinWeightPerNode = leftCount < metadata.minWeightPerNode() || rightCount < metadata.minWeightPerNode();
      if (!violatesMinInstancesPerNode && !violatesMinWeightPerNode) {
         double leftImpurity = leftImpurityCalculator.calculate();
         double rightImpurity = rightImpurityCalculator.calculate();
         double leftWeight = leftCount / totalCount;
         double rightWeight = rightCount / totalCount;
         double gain = impurity - leftWeight * leftImpurity - rightWeight * rightImpurity;
         return gain < metadata.minInfoGain() ? ImpurityStats$.MODULE$.getInvalidImpurityStats(parentImpurityCalculator) : new ImpurityStats(gain, impurity, parentImpurityCalculator, leftImpurityCalculator, rightImpurityCalculator, ImpurityStats$.MODULE$.$lessinit$greater$default$6());
      } else {
         return ImpurityStats$.MODULE$.getInvalidImpurityStats(parentImpurityCalculator);
      }
   }

   public Tuple2 binsToBestSplit(final DTStatsAggregator binAggregates, final Split[][] splits, final Option featuresForNode, final LearningNode node) {
      int level = LearningNode$.MODULE$.indexToLevel(node.id());
      ObjectRef gainAndImpurityStats = ObjectRef.create(level == 0 ? null : node.stats());
      Iterator validFeatureSplits = scala.package..MODULE$.Iterator().range(0, binAggregates.metadata().numFeaturesPerNode()).map((featureIndexIdx) -> $anonfun$binsToBestSplit$1(featuresForNode, BoxesRunTime.unboxToInt(featureIndexIdx))).withFilter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$binsToBestSplit$4(binAggregates, x0$1)));
      Iterator splitsAndImpurityInfo = validFeatureSplits.map((x0$2) -> {
         if (x0$2 == null) {
            throw new MatchError(x0$2);
         } else {
            int featureIndexIdx = x0$2._1$mcI$sp();
            int featureIndex = x0$2._2$mcI$sp();
            int numSplits = binAggregates.metadata().numSplits(featureIndex);
            if (binAggregates.metadata().isContinuous(featureIndex)) {
               int nodeFeatureOffset = binAggregates.getFeatureOffset(featureIndexIdx);

               for(int splitIndex = 0; splitIndex < numSplits; ++splitIndex) {
                  binAggregates.mergeForFeature(nodeFeatureOffset, splitIndex + 1, splitIndex);
               }

               Tuple2 var15 = (Tuple2)scala.package..MODULE$.Range().apply(0, numSplits).map((splitIdx) -> $anonfun$binsToBestSplit$6(binAggregates, nodeFeatureOffset, numSplits, gainAndImpurityStats, BoxesRunTime.unboxToInt(splitIdx))).maxBy((x$10) -> BoxesRunTime.boxToDouble($anonfun$binsToBestSplit$7(x$10)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
               if (var15 != null) {
                  int bestFeatureSplitIndex = var15._1$mcI$sp();
                  ImpurityStats bestFeatureGainStats = (ImpurityStats)var15._2();
                  Tuple2 var14 = new Tuple2(BoxesRunTime.boxToInteger(bestFeatureSplitIndex), bestFeatureGainStats);
                  int bestFeatureSplitIndexxxxxx = var14._1$mcI$sp();
                  ImpurityStats bestFeatureGainStatsxxxxx = (ImpurityStats)var14._2();
                  return new Tuple2(splits[featureIndex][bestFeatureSplitIndexxxxxx], bestFeatureGainStatsxxxxx);
               } else {
                  throw new MatchError(var15);
               }
            } else if (binAggregates.metadata().isUnordered(featureIndex)) {
               int leftChildOffset = binAggregates.getFeatureOffset(featureIndexIdx);
               Tuple2 var22 = (Tuple2)scala.package..MODULE$.Range().apply(0, numSplits).map((splitIndexxx) -> $anonfun$binsToBestSplit$8(binAggregates, leftChildOffset, gainAndImpurityStats, BoxesRunTime.unboxToInt(splitIndexxx))).maxBy((x$12) -> BoxesRunTime.boxToDouble($anonfun$binsToBestSplit$9(x$12)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
               if (var22 != null) {
                  int bestFeatureSplitIndex = var22._1$mcI$sp();
                  ImpurityStats bestFeatureGainStats = (ImpurityStats)var22._2();
                  Tuple2 var21 = new Tuple2(BoxesRunTime.boxToInteger(bestFeatureSplitIndex), bestFeatureGainStats);
                  int bestFeatureSplitIndexx = var21._1$mcI$sp();
                  ImpurityStats bestFeatureGainStatsx = (ImpurityStats)var21._2();
                  return new Tuple2(splits[featureIndex][bestFeatureSplitIndexx], bestFeatureGainStatsx);
               } else {
                  throw new MatchError(var22);
               }
            } else {
               int nodeFeatureOffset = binAggregates.getFeatureOffset(featureIndexIdx);
               int numCategories = binAggregates.metadata().numBins()[featureIndex];
               IndexedSeq centroidForCategories = scala.package..MODULE$.Range().apply(0, numCategories).map((featureValue) -> $anonfun$binsToBestSplit$10(binAggregates, nodeFeatureOffset, BoxesRunTime.unboxToInt(featureValue)));
               MODULE$.logDebug((Function0)(() -> "Centroids for categorical variable: " + centroidForCategories.mkString(",")));
               List categoriesSortedByCentroid = (List)centroidForCategories.toList().sortBy((x$14) -> BoxesRunTime.boxToDouble($anonfun$binsToBestSplit$12(x$14)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
               MODULE$.logDebug((Function0)(() -> "Sorted centroids for categorical variable = " + categoriesSortedByCentroid.mkString(",")));

               for(int splitIndex = 0; splitIndex < numSplits; ++splitIndex) {
                  int currentCategory = ((Tuple2)categoriesSortedByCentroid.apply(splitIndex))._1$mcI$sp();
                  int nextCategory = ((Tuple2)categoriesSortedByCentroid.apply(splitIndex + 1))._1$mcI$sp();
                  binAggregates.mergeForFeature(nodeFeatureOffset, nextCategory, currentCategory);
               }

               int lastCategory = ((Tuple2)categoriesSortedByCentroid.last())._1$mcI$sp();
               Tuple2 var36 = (Tuple2)scala.package..MODULE$.Range().apply(0, numSplits).map((splitIndexxx) -> $anonfun$binsToBestSplit$14(categoriesSortedByCentroid, binAggregates, nodeFeatureOffset, lastCategory, gainAndImpurityStats, BoxesRunTime.unboxToInt(splitIndexxx))).maxBy((x$15) -> BoxesRunTime.boxToDouble($anonfun$binsToBestSplit$15(x$15)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
               if (var36 != null) {
                  int bestFeatureSplitIndex = var36._1$mcI$sp();
                  ImpurityStats bestFeatureGainStats = (ImpurityStats)var36._2();
                  Tuple2 var35 = new Tuple2(BoxesRunTime.boxToInteger(bestFeatureSplitIndex), bestFeatureGainStats);
                  int bestFeatureSplitIndexxxxx = var35._1$mcI$sp();
                  ImpurityStats bestFeatureGainStatsxxxx = (ImpurityStats)var35._2();
                  List categoriesForSplit = categoriesSortedByCentroid.map((x$17) -> BoxesRunTime.boxToDouble($anonfun$binsToBestSplit$16(x$17))).slice(0, bestFeatureSplitIndexxxxx + 1);
                  CategoricalSplit bestFeatureSplit = new CategoricalSplit(featureIndex, (double[])categoriesForSplit.toArray(.MODULE$.Double()), numCategories);
                  return new Tuple2(bestFeatureSplit, bestFeatureGainStatsxxxx);
               } else {
                  throw new MatchError(var36);
               }
            }
         }
      });
      Tuple2 var10000;
      if (splitsAndImpurityInfo.isEmpty()) {
         int dummyFeatureIndex = BoxesRunTime.unboxToInt(featuresForNode.map((x$18) -> BoxesRunTime.boxToInteger($anonfun$binsToBestSplit$17(x$18))).getOrElse((JFunction0.mcI.sp)() -> 0));
         ImpurityCalculator parentImpurityCalculator = binAggregates.getParentImpurityCalculator();
         if (binAggregates.metadata().isContinuous(dummyFeatureIndex)) {
            var10000 = new Tuple2(new ContinuousSplit(dummyFeatureIndex, (double)0.0F), ImpurityStats$.MODULE$.getInvalidImpurityStats(parentImpurityCalculator));
         } else {
            int numCategories = BoxesRunTime.unboxToInt(binAggregates.metadata().featureArity().apply(BoxesRunTime.boxToInteger(dummyFeatureIndex)));
            var10000 = new Tuple2(new CategoricalSplit(dummyFeatureIndex, (double[])scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, .MODULE$.Double()), numCategories), ImpurityStats$.MODULE$.getInvalidImpurityStats(parentImpurityCalculator));
         }
      } else {
         var10000 = (Tuple2)splitsAndImpurityInfo.maxBy((x$19) -> BoxesRunTime.boxToDouble($anonfun$binsToBestSplit$19(x$19)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
      }

      Tuple2 var11 = var10000;
      if (var11 != null) {
         Split bestSplit = (Split)var11._1();
         ImpurityStats bestSplitStats = (ImpurityStats)var11._2();
         Tuple2 var10 = new Tuple2(bestSplit, bestSplitStats);
         Split bestSplit = (Split)var10._1();
         ImpurityStats bestSplitStats = (ImpurityStats)var10._2();
         return new Tuple2(bestSplit, bestSplitStats);
      } else {
         throw new MatchError(var11);
      }
   }

   public Split[][] findSplits(final RDD input, final DecisionTreeMetadata metadata, final long seed) {
      this.logDebug((Function0)(() -> "isMulticlass = " + metadata.isMulticlass()));
      int numFeatures = metadata.numFeatures();
      IndexedSeq continuousFeatures = (IndexedSeq)scala.package..MODULE$.Range().apply(0, numFeatures).filter((JFunction1.mcZI.sp)(featureIndex) -> metadata.isContinuous(featureIndex));
      RDD var10000;
      if (continuousFeatures.nonEmpty()) {
         double fraction = this.samplesFractionForFindSplits(metadata);
         this.logDebug((Function0)(() -> "fraction of data used for calculating quantiles = " + fraction));
         var10000 = fraction < (double)1 ? input.sample(false, fraction, (long)(new XORShiftRandom(seed)).nextInt()) : input;
      } else {
         var10000 = input.sparkContext().emptyRDD(.MODULE$.apply(Instance.class));
      }

      RDD sampledInput = var10000;
      return this.findSplitsBySorting(sampledInput, metadata, continuousFeatures);
   }

   private Split[][] findSplitsBySorting(final RDD input, final DecisionTreeMetadata metadata, final IndexedSeq continuousFeatures) {
      Object var10000;
      if (continuousFeatures.nonEmpty()) {
         int numPartitions = scala.math.package..MODULE$.min(continuousFeatures.length(), input.partitions().length);
         var10000 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(input.flatMap((point) -> continuousFeatures.iterator().map((idx) -> $anonfun$findSplitsBySorting$2(point, BoxesRunTime.unboxToInt(idx))).filter((x$21) -> BoxesRunTime.boxToBoolean($anonfun$findSplitsBySorting$3(x$21))), .MODULE$.apply(Tuple2.class)), .MODULE$.Int(), .MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).aggregateByKey(new Tuple2(new OpenHashMap.mcD.sp(.MODULE$.Double(), .MODULE$.Double()), BoxesRunTime.boxToLong(0L)), numPartitions, (x0$1, x1$1) -> {
            Tuple2 var3 = new Tuple2(x0$1, x1$1);
            if (var3 != null) {
               Tuple2 var4 = (Tuple2)var3._1();
               Tuple2 var5 = (Tuple2)var3._2();
               if (var4 != null) {
                  OpenHashMap map = (OpenHashMap)var4._1();
                  long c = var4._2$mcJ$sp();
                  if (var5 != null) {
                     double v = var5._1$mcD$sp();
                     double w = var5._2$mcD$sp();
                     map.changeValue$mcD$sp(BoxesRunTime.boxToDouble(v), (JFunction0.mcD.sp)() -> w, (JFunction1.mcDD.sp)(x$22) -> x$22 + w);
                     return new Tuple2(map, BoxesRunTime.boxToLong(c + 1L));
                  }
               }
            }

            throw new MatchError(var3);
         }, (x0$2, x1$2) -> {
            Tuple2 var3 = new Tuple2(x0$2, x1$2);
            if (var3 != null) {
               Tuple2 var4 = (Tuple2)var3._1();
               Tuple2 var5 = (Tuple2)var3._2();
               if (var4 != null) {
                  OpenHashMap map1 = (OpenHashMap)var4._1();
                  long c1 = var4._2$mcJ$sp();
                  if (var5 != null) {
                     OpenHashMap map2 = (OpenHashMap)var5._1();
                     long c2 = var5._2$mcJ$sp();
                     map2.foreach((x0$3) -> BoxesRunTime.boxToDouble($anonfun$findSplitsBySorting$8(map1, x0$3)));
                     return new Tuple2(map1, BoxesRunTime.boxToLong(c1 + c2));
                  }
               }
            }

            throw new MatchError(var3);
         }, .MODULE$.apply(Tuple2.class)).map((x0$4) -> {
            if (x0$4 != null) {
               int idx = x0$4._1$mcI$sp();
               Tuple2 var5 = (Tuple2)x0$4._2();
               if (var5 != null) {
                  OpenHashMap map = (OpenHashMap)var5._1();
                  long c = var5._2$mcJ$sp();
                  double[] thresholds = MODULE$.findSplitsForContinuousFeature(map.toMap(scala..less.colon.less..MODULE$.refl()), c, metadata, idx);
                  Split[] splits = (Split[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(thresholds), (thresh) -> $anonfun$findSplitsBySorting$12(idx, BoxesRunTime.unboxToDouble(thresh)), .MODULE$.apply(Split.class));
                  MODULE$.logDebug((Function0)(() -> "featureIndex = " + idx + ", numSplits = " + splits.length));
                  return new Tuple2(BoxesRunTime.boxToInteger(idx), splits);
               }
            }

            throw new MatchError(x0$4);
         }, .MODULE$.apply(Tuple2.class)), .MODULE$.Int(), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Split.class)), scala.math.Ordering.Int..MODULE$).collectAsMap();
      } else {
         var10000 = scala.Predef..MODULE$.Map().empty();
      }

      scala.collection.Map continuousSplits = (scala.collection.Map)var10000;
      int numFeatures = metadata.numFeatures();
      Split[][] splits = (Split[][])scala.Array..MODULE$.tabulate(numFeatures, (x0$5) -> $anonfun$findSplitsBySorting$14(metadata, continuousSplits, BoxesRunTime.unboxToInt(x0$5)), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Split.class)));
      return splits;
   }

   public List extractMultiClassCategories(final int input, final int maxFeatureValue) {
      List categories = scala.collection.immutable.Nil..MODULE$;
      int j = 0;

      for(int bitShiftedInput = input; j < maxFeatureValue; ++j) {
         if (bitShiftedInput % 2 != 0) {
            double var6 = (double)j;
            categories = categories.$colon$colon(BoxesRunTime.boxToDouble(var6));
         }

         bitShiftedInput >>= 1;
      }

      return categories;
   }

   public double[] findSplitsForContinuousFeature(final Iterable featureSamples, final DecisionTreeMetadata metadata, final int featureIndex) {
      OpenHashMap valueWeights = new OpenHashMap.mcD.sp(.MODULE$.Double(), .MODULE$.Double());
      LongRef count = LongRef.create(0L);
      featureSamples.foreach((x0$1) -> {
         $anonfun$findSplitsForContinuousFeature$1(valueWeights, count, x0$1);
         return BoxedUnit.UNIT;
      });
      return this.findSplitsForContinuousFeature(valueWeights.toMap(scala..less.colon.less..MODULE$.refl()), count.elem, metadata, featureIndex);
   }

   public double[] findSplitsForContinuousFeature(final scala.collection.immutable.Map partValueWeights, final long count, final DecisionTreeMetadata metadata, final int featureIndex) {
      scala.Predef..MODULE$.require(metadata.isContinuous(featureIndex), () -> "findSplitsForContinuousFeature can only be used to find splits for a continuous feature.");
      double[] var10000;
      if (partValueWeights.isEmpty()) {
         var10000 = scala.Array..MODULE$.emptyDoubleArray();
      } else {
         int numSplits = metadata.numSplits(featureIndex);
         double partNumSamples = BoxesRunTime.unboxToDouble(partValueWeights.values().sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
         double weightedNumSamples = this.samplesFractionForFindSplits(metadata) * metadata.weightedNumExamples();
         double tolerance = org.apache.spark.ml.impl.Utils..MODULE$.EPSILON() * (double)count * (double)100;
         scala.collection.immutable.Map valueCountMap = weightedNumSamples - partNumSamples > tolerance ? (scala.collection.immutable.Map)partValueWeights.$plus(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToDouble((double)0.0F)), BoxesRunTime.boxToDouble(weightedNumSamples - partNumSamples))) : partValueWeights;
         Tuple2[] valueCounts = (Tuple2[])((IterableOnceOps)valueCountMap.toSeq().sortBy((x$25) -> BoxesRunTime.boxToDouble($anonfun$findSplitsForContinuousFeature$5(x$25)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)).toArray(.MODULE$.apply(Tuple2.class));
         int possibleSplits = valueCounts.length - 1;
         if (possibleSplits == 0) {
            var10000 = scala.Array..MODULE$.emptyDoubleArray();
         } else if (possibleSplits <= numSplits) {
            var10000 = (double[])scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), possibleSplits).map((JFunction1.mcDI.sp)(indexx) -> (valueCounts[indexx - 1]._1$mcD$sp() + valueCounts[indexx]._1$mcD$sp()) / (double)2.0F).toArray(.MODULE$.Double());
         } else {
            double stride = weightedNumSamples / (double)(numSplits + 1);
            this.logDebug((Function0)(() -> "stride = " + stride));
            ArrayBuilder splitsBuilder = scala.collection.mutable.ArrayBuilder..MODULE$.make(.MODULE$.Double());
            int index = 1;
            double currentCount = valueCounts[0]._2$mcD$sp();

            for(double targetCount = stride; index < valueCounts.length; ++index) {
               double previousCount = currentCount;
               currentCount += valueCounts[index]._2$mcD$sp();
               double previousGap = scala.math.package..MODULE$.abs(previousCount - targetCount);
               double currentGap = scala.math.package..MODULE$.abs(currentCount - targetCount);
               if (previousGap < currentGap) {
                  splitsBuilder.$plus$eq(BoxesRunTime.boxToDouble((valueCounts[index - 1]._1$mcD$sp() + valueCounts[index]._1$mcD$sp()) / (double)2.0F));
                  targetCount += stride;
               }
            }

            var10000 = (double[])splitsBuilder.result();
         }
      }

      double[] splits = var10000;
      return splits;
   }

   public Tuple2 selectNodesToSplit(final ListBuffer nodeStack, final long maxMemoryUsage, final DecisionTreeMetadata metadata, final Random rng) {
      HashMap mutableNodesForGroup = new HashMap();
      HashMap mutableTreeToNodeToIndexInfo = new HashMap();
      LongRef memUsage = LongRef.create(0L);
      IntRef numNodesInGroup = IntRef.create(0);
      boolean groupDone = false;

      while(true) {
         if (nodeStack.nonEmpty() && !groupDone) {
            Tuple2 var13 = (Tuple2)nodeStack.head();
            if (var13 != null) {
               int treeIndex = var13._1$mcI$sp();
               LearningNode node = (LearningNode)var13._2();
               Tuple2 var12 = new Tuple2(BoxesRunTime.boxToInteger(treeIndex), node);
               int treeIndex = var12._1$mcI$sp();
               LearningNode node = (LearningNode)var12._2();
               Option featureSubset = (Option)(metadata.subsamplingFeatures() ? new Some(org.apache.spark.util.random.SamplingUtils..MODULE$.reservoirSampleAndCount(scala.package..MODULE$.Range().apply(0, metadata.numFeatures()).iterator(), metadata.numFeaturesPerNode(), rng.nextLong(), .MODULE$.Int())._1()) : scala.None..MODULE$);
               long nodeMemUsage = this.aggregateSizeForNode(metadata, featureSubset) * 8L;
               if (memUsage.elem + nodeMemUsage > maxMemoryUsage && memUsage.elem != 0L) {
                  groupDone = true;
                  continue;
               }

               nodeStack.remove(0);
               ((Growable)mutableNodesForGroup.getOrElseUpdate(BoxesRunTime.boxToInteger(treeIndex), () -> new ArrayBuffer())).$plus$eq(node);
               ((HashMap)mutableTreeToNodeToIndexInfo.getOrElseUpdate(BoxesRunTime.boxToInteger(treeIndex), () -> new HashMap())).update(BoxesRunTime.boxToInteger(node.id()), new RandomForest.NodeIndexInfo(numNodesInGroup.elem, featureSubset));
               ++numNodesInGroup.elem;
               memUsage.elem += nodeMemUsage;
               continue;
            }

            throw new MatchError(var13);
         }

         if (memUsage.elem > maxMemoryUsage) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Tree learning is using approximately ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_SIZE..MODULE$, BoxesRunTime.boxToLong(memUsage.elem))}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"bytes per iteration, which exceeds requested limit "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"maxMemoryUsage=", ". This allows splitting "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_MEMORY_SIZE..MODULE$, BoxesRunTime.boxToLong(maxMemoryUsage))})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " nodes in this iteration."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_NODES..MODULE$, BoxesRunTime.boxToInteger(numNodesInGroup.elem))}))))));
         }

         scala.collection.immutable.Map nodesForGroup = (scala.collection.immutable.Map)mutableNodesForGroup.toMap(scala..less.colon.less..MODULE$.refl()).transform((x$27, v) -> $anonfun$selectNodesToSplit$4(BoxesRunTime.unboxToInt(x$27), v));
         scala.collection.immutable.Map treeToNodeToIndexInfo = (scala.collection.immutable.Map)mutableTreeToNodeToIndexInfo.toMap(scala..less.colon.less..MODULE$.refl()).transform((x$28, v) -> $anonfun$selectNodesToSplit$5(BoxesRunTime.unboxToInt(x$28), v));
         return new Tuple2(nodesForGroup, treeToNodeToIndexInfo);
      }
   }

   private long aggregateSizeForNode(final DecisionTreeMetadata metadata, final Option featureSubset) {
      long totalBins = featureSubset.nonEmpty() ? BoxesRunTime.unboxToLong(scala.Predef..MODULE$.wrapLongArray((long[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps((int[])featureSubset.get()), (JFunction1.mcJI.sp)(featureIndex) -> (long)metadata.numBins()[featureIndex], .MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$)) : BoxesRunTime.unboxToLong(scala.Predef..MODULE$.wrapLongArray((long[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(metadata.numBins()), (JFunction1.mcJI.sp)(x$29) -> (long)x$29, .MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
      return metadata.isClassification() ? (long)metadata.numClasses() * totalBins : 3L * totalBins;
   }

   private double samplesFractionForFindSplits(final DecisionTreeMetadata metadata) {
      int requiredSamples = scala.math.package..MODULE$.max(metadata.maxBins() * metadata.maxBins(), 10000);
      return (long)requiredSamples < metadata.numExamples() ? (double)requiredSamples / (double)metadata.numExamples() : (double)1.0F;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RandomForest$.class);
   }

   // $FF: synthetic method
   public static final ListBuffer $anonfun$runBagged$10(final ListBuffer nodeStack$1, final LearningNode[] topNodes$1, final int treeIndex) {
      return nodeStack$1.prepend(new Tuple2(BoxesRunTime.boxToInteger(treeIndex), topNodes$1[treeIndex]));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$runBagged$12(final LearningNode[] topNodes$1, final int treeIdx) {
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(treeIdx)), topNodes$1[treeIdx]);
   }

   // $FF: synthetic method
   public static final String $anonfun$run$4(final DecisionTreeMetadata metadata$2, final int featureIndex) {
      return "\t" + featureIndex + "\t" + metadata$2.numBins()[featureIndex];
   }

   // $FF: synthetic method
   public static final double $anonfun$run$5(final TreePoint tp) {
      return tp.weight();
   }

   // $FF: synthetic method
   public static final void $anonfun$updateNodeIds$2(final BaggedPoint point$1, final Broadcast bcSplits$1, final int nodeId$1, final int[] ids$1, final IntRef treeId$1, final Split bestSplit) {
      int featureId = bestSplit.featureIndex();
      int bin = ((TreePoint)point$1.datum()).binnedFeatures()[featureId];
      int newNodeId = bestSplit.shouldGoLeft(bin, ((Split[][])bcSplits$1.value())[featureId]) ? LearningNode$.MODULE$.leftChildIndex(nodeId$1) : LearningNode$.MODULE$.rightChildIndex(nodeId$1);
      ids$1[treeId$1.elem] = newNodeId;
   }

   // $FF: synthetic method
   public static final int $anonfun$findBestSplits$1(final LearningNode[] x$3) {
      return x$3.length;
   }

   private final void nodeBinSeqOp$1(final int treeIndex, final RandomForest.NodeIndexInfo nodeInfo, final DTStatsAggregator[] agg, final BaggedPoint baggedPoint, final Split[][] splits, final DecisionTreeMetadata metadata$3) {
      if (nodeInfo != null) {
         int aggNodeIndex = nodeInfo.nodeIndexInGroup();
         Option featuresForNode = nodeInfo.featureSubset();
         int numSamples = baggedPoint.subsampleCounts()[treeIndex];
         double sampleWeight = baggedPoint.sampleWeight();
         if (metadata$3.unorderedFeatures().isEmpty()) {
            this.orderedBinSeqOp(agg[aggNodeIndex], (TreePoint)baggedPoint.datum(), numSamples, sampleWeight, featuresForNode);
         } else {
            this.mixedBinSeqOp(agg[aggNodeIndex], (TreePoint)baggedPoint.datum(), splits, metadata$3.unorderedFeatures(), numSamples, sampleWeight, featuresForNode);
         }

         agg[aggNodeIndex].updateParent(((TreePoint)baggedPoint.datum()).label(), numSamples, sampleWeight);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$findBestSplits$8(final RandomForest$ $this, final scala.collection.immutable.Map topNodesForGroup$1, final BaggedPoint baggedPoint$1, final Split[][] splits$1, final DTStatsAggregator[] agg$1, final DecisionTreeMetadata metadata$3, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int treeIndex = x0$1._1$mcI$sp();
         scala.collection.immutable.Map nodeIndexToInfo = (scala.collection.immutable.Map)x0$1._2();
         int nodeIndex = ((LearningNode)topNodesForGroup$1.apply(BoxesRunTime.boxToInteger(treeIndex))).predictImpl(((TreePoint)baggedPoint$1.datum()).binnedFeatures(), splits$1);
         $this.nodeBinSeqOp$1(treeIndex, (RandomForest.NodeIndexInfo)nodeIndexToInfo.getOrElse(BoxesRunTime.boxToInteger(nodeIndex), () -> null), agg$1, baggedPoint$1, splits$1, metadata$3);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   private final DTStatsAggregator[] binSeqOp$1(final DTStatsAggregator[] agg, final BaggedPoint baggedPoint, final Split[][] splits, final scala.collection.immutable.Map treeToNodeToIndexInfo$1, final scala.collection.immutable.Map topNodesForGroup$1, final DecisionTreeMetadata metadata$3) {
      treeToNodeToIndexInfo$1.foreach((x0$1) -> {
         $anonfun$findBestSplits$8(this, topNodesForGroup$1, baggedPoint, splits, agg, metadata$3, x0$1);
         return BoxedUnit.UNIT;
      });
      return agg;
   }

   // $FF: synthetic method
   public static final void $anonfun$findBestSplits$10(final RandomForest$ $this, final Tuple2 dataPoint$1, final DTStatsAggregator[] agg$2, final Split[][] splits$2, final DecisionTreeMetadata metadata$3, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int treeIndex = x0$1._1$mcI$sp();
         scala.collection.immutable.Map nodeIndexToInfo = (scala.collection.immutable.Map)x0$1._2();
         BaggedPoint baggedPoint = (BaggedPoint)dataPoint$1._1();
         int[] nodeIdCache = (int[])dataPoint$1._2();
         int nodeIndex = nodeIdCache[treeIndex];
         $this.nodeBinSeqOp$1(treeIndex, (RandomForest.NodeIndexInfo)nodeIndexToInfo.getOrElse(BoxesRunTime.boxToInteger(nodeIndex), () -> null), agg$2, baggedPoint, splits$2, metadata$3);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   private final DTStatsAggregator[] binSeqOpWithNodeIdCache$1(final DTStatsAggregator[] agg, final Tuple2 dataPoint, final Split[][] splits, final scala.collection.immutable.Map treeToNodeToIndexInfo$1, final DecisionTreeMetadata metadata$3) {
      treeToNodeToIndexInfo$1.foreach((x0$1) -> {
         $anonfun$findBestSplits$10(this, dataPoint, agg, splits, metadata$3, x0$1);
         return BoxedUnit.UNIT;
      });
      return agg;
   }

   // $FF: synthetic method
   public static final void $anonfun$findBestSplits$13(final HashMap mutableNodeToFeatures$1, final RandomForest.NodeIndexInfo nodeIndexInfo) {
      scala.Predef..MODULE$.assert(nodeIndexInfo.featureSubset().isDefined());
      mutableNodeToFeatures$1.update(BoxesRunTime.boxToInteger(nodeIndexInfo.nodeIndexInGroup()), nodeIndexInfo.featureSubset().get());
   }

   // $FF: synthetic method
   public static final void $anonfun$findBestSplits$12(final HashMap mutableNodeToFeatures$1, final scala.collection.immutable.Map nodeIdToNodeInfo) {
      nodeIdToNodeInfo.values().foreach((nodeIndexInfo) -> {
         $anonfun$findBestSplits$13(mutableNodeToFeatures$1, nodeIndexInfo);
         return BoxedUnit.UNIT;
      });
   }

   private static final Option getNodeToFeatures$1(final scala.collection.immutable.Map treeToNodeToIndexInfo, final DecisionTreeMetadata metadata$3) {
      if (!metadata$3.subsamplingFeatures()) {
         return scala.None..MODULE$;
      } else {
         HashMap mutableNodeToFeatures = new HashMap();
         treeToNodeToIndexInfo.values().foreach((nodeIdToNodeInfo) -> {
            $anonfun$findBestSplits$12(mutableNodeToFeatures, nodeIdToNodeInfo);
            return BoxedUnit.UNIT;
         });
         return new Some(mutableNodeToFeatures.toMap(scala..less.colon.less..MODULE$.refl()));
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$findBestSplits$15(final LearningNode[] nodes$1, final scala.collection.immutable.Map treeToNodeToIndexInfo$1, final int treeIndex$1, final LearningNode node) {
      nodes$1[((RandomForest.NodeIndexInfo)((MapOps)treeToNodeToIndexInfo$1.apply(BoxesRunTime.boxToInteger(treeIndex$1))).apply(BoxesRunTime.boxToInteger(node.id()))).nodeIndexInGroup()] = node;
   }

   // $FF: synthetic method
   public static final void $anonfun$findBestSplits$14(final LearningNode[] nodes$1, final scala.collection.immutable.Map treeToNodeToIndexInfo$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int treeIndex = x0$1._1$mcI$sp();
         LearningNode[] nodesForTree = (LearningNode[])x0$1._2();
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(nodesForTree), (node) -> {
            $anonfun$findBestSplits$15(nodes$1, treeToNodeToIndexInfo$1, treeIndex, node);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final DTStatsAggregator $anonfun$findBestSplits$17(final Broadcast nodeToFeaturesBc$1, final DecisionTreeMetadata metadata$3, final int nodeIndex) {
      Option featuresForNode = ((Option)nodeToFeaturesBc$1.value()).map((nodeToFeatures) -> (int[])nodeToFeatures.apply(BoxesRunTime.boxToInteger(nodeIndex)));
      return new DTStatsAggregator(metadata$3, featuresForNode);
   }

   // $FF: synthetic method
   public static final DTStatsAggregator $anonfun$findBestSplits$22(final Broadcast nodeToFeaturesBc$1, final DecisionTreeMetadata metadata$3, final int nodeIndex) {
      Option featuresForNode = ((Option)nodeToFeaturesBc$1.value()).flatMap((nodeToFeatures) -> new Some(nodeToFeatures.apply(BoxesRunTime.boxToInteger(nodeIndex))));
      return new DTStatsAggregator(metadata$3, featuresForNode);
   }

   // $FF: synthetic method
   public static final void $anonfun$findBestSplits$30(final scala.collection.immutable.Map treeToNodeToIndexInfo$1, final int treeIndex$2, final scala.collection.Map nodeToBestSplits$1, final DecisionTreeMetadata metadata$3, final boolean outputBestSplits$1, final scala.collection.mutable.Map[] bestSplits$2, final ListBuffer nodeStack$2, final LearningNode node) {
      int nodeIndex = node.id();
      RandomForest.NodeIndexInfo nodeInfo = (RandomForest.NodeIndexInfo)((MapOps)treeToNodeToIndexInfo$1.apply(BoxesRunTime.boxToInteger(treeIndex$2))).apply(BoxesRunTime.boxToInteger(nodeIndex));
      int aggNodeIndex = nodeInfo.nodeIndexInGroup();
      Tuple2 var13 = (Tuple2)nodeToBestSplits$1.apply(BoxesRunTime.boxToInteger(aggNodeIndex));
      if (var13 != null) {
         Split split = (Split)var13._1();
         ImpurityStats stats = (ImpurityStats)var13._2();
         if (split != null && stats != null) {
            Tuple2 var12 = new Tuple2(split, stats);
            Split split = (Split)var12._1();
            ImpurityStats stats = (ImpurityStats)var12._2();
            MODULE$.logDebug((Function0)(() -> "best split = " + split));
            boolean isLeaf = stats.gain() <= (double)0 || LearningNode$.MODULE$.indexToLevel(nodeIndex) == metadata$3.maxDepth();
            node.isLeaf_$eq(isLeaf);
            node.stats_$eq(stats);
            MODULE$.logDebug((Function0)(() -> "Node = " + node));
            if (!isLeaf) {
               node.split_$eq(new Some(split));
               boolean childIsLeaf = LearningNode$.MODULE$.indexToLevel(nodeIndex) + 1 == metadata$3.maxDepth();
               boolean leftChildIsLeaf = childIsLeaf || scala.math.package..MODULE$.abs(stats.leftImpurity()) < org.apache.spark.ml.impl.Utils..MODULE$.EPSILON();
               boolean rightChildIsLeaf = childIsLeaf || scala.math.package..MODULE$.abs(stats.rightImpurity()) < org.apache.spark.ml.impl.Utils..MODULE$.EPSILON();
               node.leftChild_$eq(new Some(LearningNode$.MODULE$.apply(LearningNode$.MODULE$.leftChildIndex(nodeIndex), leftChildIsLeaf, ImpurityStats$.MODULE$.getEmptyImpurityStats(stats.leftImpurityCalculator()))));
               node.rightChild_$eq(new Some(LearningNode$.MODULE$.apply(LearningNode$.MODULE$.rightChildIndex(nodeIndex), rightChildIsLeaf, ImpurityStats$.MODULE$.getEmptyImpurityStats(stats.rightImpurityCalculator()))));
               if (outputBestSplits$1) {
                  scala.collection.mutable.Map bestSplitsInTree = bestSplits$2[treeIndex$2];
                  if (bestSplitsInTree == null) {
                     bestSplits$2[treeIndex$2] = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(nodeIndex)), split)})));
                  } else {
                     bestSplitsInTree.update(BoxesRunTime.boxToInteger(nodeIndex), split);
                  }
               }

               if (!leftChildIsLeaf) {
                  nodeStack$2.prepend(new Tuple2(BoxesRunTime.boxToInteger(treeIndex$2), node.leftChild().get()));
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               if (!rightChildIsLeaf) {
                  nodeStack$2.prepend(new Tuple2(BoxesRunTime.boxToInteger(treeIndex$2), node.rightChild().get()));
               } else {
                  BoxedUnit var25 = BoxedUnit.UNIT;
               }

               MODULE$.logDebug((Function0)(() -> {
                  int var10000 = ((LearningNode)node.leftChild().get()).id();
                  return "leftChildIndex = " + var10000 + ", impurity = " + stats.leftImpurity();
               }));
               MODULE$.logDebug((Function0)(() -> {
                  int var10000 = ((LearningNode)node.rightChild().get()).id();
                  return "rightChildIndex = " + var10000 + ", impurity = " + stats.rightImpurity();
               }));
               return;
            }

            return;
         }
      }

      throw new MatchError(var13);
   }

   // $FF: synthetic method
   public static final void $anonfun$findBestSplits$29(final scala.collection.immutable.Map treeToNodeToIndexInfo$1, final scala.collection.Map nodeToBestSplits$1, final DecisionTreeMetadata metadata$3, final boolean outputBestSplits$1, final scala.collection.mutable.Map[] bestSplits$2, final ListBuffer nodeStack$2, final Tuple2 x0$3) {
      if (x0$3 != null) {
         int treeIndex = x0$3._1$mcI$sp();
         LearningNode[] nodesForTree = (LearningNode[])x0$3._2();
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(nodesForTree), (node) -> {
            $anonfun$findBestSplits$30(treeToNodeToIndexInfo$1, treeIndex, nodeToBestSplits$1, metadata$3, outputBestSplits$1, bestSplits$2, nodeStack$2, node);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$3);
      }
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$binsToBestSplit$1(final Option featuresForNode$1, final int featureIndexIdx) {
      return (Tuple2)featuresForNode$1.map((features) -> new Tuple2.mcII.sp(featureIndexIdx, features[featureIndexIdx])).getOrElse(() -> new Tuple2.mcII.sp(featureIndexIdx, featureIndexIdx));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$binsToBestSplit$4(final DTStatsAggregator binAggregates$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int featureIndex = x0$1._2$mcI$sp();
         return binAggregates$1.metadata().numSplits(featureIndex) != 0;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$binsToBestSplit$6(final DTStatsAggregator binAggregates$1, final int nodeFeatureOffset$1, final int numSplits$1, final ObjectRef gainAndImpurityStats$1, final int splitIdx) {
      ImpurityCalculator leftChildStats = binAggregates$1.getImpurityCalculator(nodeFeatureOffset$1, splitIdx);
      ImpurityCalculator rightChildStats = binAggregates$1.getImpurityCalculator(nodeFeatureOffset$1, numSplits$1);
      rightChildStats.subtract(leftChildStats);
      gainAndImpurityStats$1.elem = MODULE$.calculateImpurityStats((ImpurityStats)gainAndImpurityStats$1.elem, leftChildStats, rightChildStats, binAggregates$1.metadata());
      return new Tuple2(BoxesRunTime.boxToInteger(splitIdx), (ImpurityStats)gainAndImpurityStats$1.elem);
   }

   // $FF: synthetic method
   public static final double $anonfun$binsToBestSplit$7(final Tuple2 x$10) {
      return ((ImpurityStats)x$10._2()).gain();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$binsToBestSplit$8(final DTStatsAggregator binAggregates$1, final int leftChildOffset$1, final ObjectRef gainAndImpurityStats$1, final int splitIndex) {
      ImpurityCalculator leftChildStats = binAggregates$1.getImpurityCalculator(leftChildOffset$1, splitIndex);
      ImpurityCalculator rightChildStats = binAggregates$1.getParentImpurityCalculator().subtract(leftChildStats);
      gainAndImpurityStats$1.elem = MODULE$.calculateImpurityStats((ImpurityStats)gainAndImpurityStats$1.elem, leftChildStats, rightChildStats, binAggregates$1.metadata());
      return new Tuple2(BoxesRunTime.boxToInteger(splitIndex), (ImpurityStats)gainAndImpurityStats$1.elem);
   }

   // $FF: synthetic method
   public static final double $anonfun$binsToBestSplit$9(final Tuple2 x$12) {
      return ((ImpurityStats)x$12._2()).gain();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$binsToBestSplit$10(final DTStatsAggregator binAggregates$1, final int nodeFeatureOffset$2, final int featureValue) {
      ImpurityCalculator categoryStats = binAggregates$1.getImpurityCalculator(nodeFeatureOffset$2, featureValue);
      double centroid = categoryStats.count() != (double)0 ? (binAggregates$1.metadata().isMulticlass() ? categoryStats.calculate() : (binAggregates$1.metadata().isClassification() ? categoryStats.stats()[1] : categoryStats.predict())) : Double.MAX_VALUE;
      return new Tuple2.mcID.sp(featureValue, centroid);
   }

   // $FF: synthetic method
   public static final double $anonfun$binsToBestSplit$12(final Tuple2 x$14) {
      return x$14._2$mcD$sp();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$binsToBestSplit$14(final List categoriesSortedByCentroid$1, final DTStatsAggregator binAggregates$1, final int nodeFeatureOffset$2, final int lastCategory$1, final ObjectRef gainAndImpurityStats$1, final int splitIndex) {
      int featureValue = ((Tuple2)categoriesSortedByCentroid$1.apply(splitIndex))._1$mcI$sp();
      ImpurityCalculator leftChildStats = binAggregates$1.getImpurityCalculator(nodeFeatureOffset$2, featureValue);
      ImpurityCalculator rightChildStats = binAggregates$1.getImpurityCalculator(nodeFeatureOffset$2, lastCategory$1);
      rightChildStats.subtract(leftChildStats);
      gainAndImpurityStats$1.elem = MODULE$.calculateImpurityStats((ImpurityStats)gainAndImpurityStats$1.elem, leftChildStats, rightChildStats, binAggregates$1.metadata());
      return new Tuple2(BoxesRunTime.boxToInteger(splitIndex), (ImpurityStats)gainAndImpurityStats$1.elem);
   }

   // $FF: synthetic method
   public static final double $anonfun$binsToBestSplit$15(final Tuple2 x$15) {
      return ((ImpurityStats)x$15._2()).gain();
   }

   // $FF: synthetic method
   public static final double $anonfun$binsToBestSplit$16(final Tuple2 x$17) {
      return (double)x$17._1$mcI$sp();
   }

   // $FF: synthetic method
   public static final int $anonfun$binsToBestSplit$17(final int[] x$18) {
      return BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.intArrayOps(x$18)));
   }

   // $FF: synthetic method
   public static final double $anonfun$binsToBestSplit$19(final Tuple2 x$19) {
      return ((ImpurityStats)x$19._2()).gain();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$findSplitsBySorting$2(final Instance point$2, final int idx) {
      return new Tuple2(BoxesRunTime.boxToInteger(idx), new Tuple2.mcDD.sp(point$2.features().apply(idx), point$2.weight()));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findSplitsBySorting$3(final Tuple2 x$21) {
      return ((Tuple2)x$21._2())._1$mcD$sp() != (double)0.0F;
   }

   // $FF: synthetic method
   public static final double $anonfun$findSplitsBySorting$8(final OpenHashMap map1$1, final Tuple2 x0$3) {
      if (x0$3 != null) {
         double v = x0$3._1$mcD$sp();
         double w = x0$3._2$mcD$sp();
         return map1$1.changeValue$mcD$sp(BoxesRunTime.boxToDouble(v), (JFunction0.mcD.sp)() -> w, (JFunction1.mcDD.sp)(x$23) -> x$23 + w);
      } else {
         throw new MatchError(x0$3);
      }
   }

   // $FF: synthetic method
   public static final ContinuousSplit $anonfun$findSplitsBySorting$12(final int idx$1, final double thresh) {
      return new ContinuousSplit(idx$1, thresh);
   }

   // $FF: synthetic method
   public static final CategoricalSplit $anonfun$findSplitsBySorting$16(final int featureArity$1, final int x1$1, final int splitIndex) {
      List categories = MODULE$.extractMultiClassCategories(splitIndex + 1, featureArity$1);
      return new CategoricalSplit(x1$1, (double[])categories.toArray(.MODULE$.Double()), featureArity$1);
   }

   // $FF: synthetic method
   public static final Split[] $anonfun$findSplitsBySorting$14(final DecisionTreeMetadata metadata$5, final scala.collection.Map continuousSplits$1, final int x0$5) {
      switch (x0$5) {
         default:
            if (metadata$5.isContinuous(x0$5)) {
               Split[] split = (Split[])continuousSplits$1.getOrElse(BoxesRunTime.boxToInteger(x0$5), () -> (Split[])scala.Array..MODULE$.empty(.MODULE$.apply(Split.class)));
               metadata$5.setNumSplits(x0$5, split.length);
               return split;
            } else if (metadata$5.isCategorical(x0$5) && metadata$5.isUnordered(x0$5)) {
               int featureArity = BoxesRunTime.unboxToInt(metadata$5.featureArity().apply(BoxesRunTime.boxToInteger(x0$5)));
               return (Split[])scala.Array..MODULE$.tabulate(metadata$5.numSplits(x0$5), (splitIndex) -> $anonfun$findSplitsBySorting$16(featureArity, x0$5, BoxesRunTime.unboxToInt(splitIndex)), .MODULE$.apply(Split.class));
            } else if (metadata$5.isCategorical(x0$5)) {
               return (Split[])scala.Array..MODULE$.empty(.MODULE$.apply(Split.class));
            } else {
               throw new MatchError(BoxesRunTime.boxToInteger(x0$5));
            }
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$findSplitsForContinuousFeature$1(final OpenHashMap valueWeights$1, final LongRef count$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         double weight = x0$1._1$mcD$sp();
         double value = x0$1._2$mcD$sp();
         valueWeights$1.changeValue$mcD$sp(BoxesRunTime.boxToDouble(value), (JFunction0.mcD.sp)() -> weight, (JFunction1.mcDD.sp)(x$24) -> x$24 + weight);
         ++count$1.elem;
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$findSplitsForContinuousFeature$5(final Tuple2 x$25) {
      return x$25._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final LearningNode[] $anonfun$selectNodesToSplit$4(final int x$27, final ArrayBuffer v) {
      return (LearningNode[])v.toArray(.MODULE$.apply(LearningNode.class));
   }

   // $FF: synthetic method
   public static final scala.collection.immutable.Map $anonfun$selectNodesToSplit$5(final int x$28, final HashMap v) {
      return v.toMap(scala..less.colon.less..MODULE$.refl());
   }

   private RandomForest$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
