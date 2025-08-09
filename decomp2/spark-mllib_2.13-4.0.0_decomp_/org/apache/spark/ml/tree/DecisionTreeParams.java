package org.apache.spark.ml.tree;

import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasCheckpointInterval;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.configuration.Strategy$;
import org.apache.spark.mllib.tree.impurity.Impurity;
import scala.Enumeration;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ec\u0001\u0003\u000b\u0016!\u0003\r\taF\u0010\t\u000ba\u0002A\u0011\u0001\u001e\t\u000fy\u0002!\u0019!C\u0003\u007f!9\u0001\f\u0001b\u0001\n\u000bI\u0006bB/\u0001\u0005\u0004%)!\u0017\u0005\b=\u0002\u0011\r\u0011\"\u0002Z\u0011\u001dy\u0006A1A\u0005\u0006\u0001Dq\u0001\u001a\u0001C\u0002\u0013\u0015\u0001\rC\u0004f\u0001\t\u0007IQA-\t\u000f\u0019\u0004!\u0019!C\u0003O\")1\u000e\u0001C\u0003Y\")\u0011\u000f\u0001C\u0003e\")A\u000f\u0001C\u0003k\")\u0011\u0010\u0001C\u0003k\")!\u0010\u0001C\u0003k\")1\u0010\u0001C\u0003y\"1\u0011\u0011\u0001\u0001\u0005\u0006qDa!a\u0001\u0001\t\u000b)\bbBA\u0003\u0001\u0011\u0015\u0011q\u0001\u0005\t\u0003\u001f\u0001A\u0011A\f\u0002\u0012\t\u0011B)Z2jg&|g\u000e\u0016:fKB\u000b'/Y7t\u0015\t1r#\u0001\u0003ue\u0016,'B\u0001\r\u001a\u0003\tiGN\u0003\u0002\u001b7\u0005)1\u000f]1sW*\u0011A$H\u0001\u0007CB\f7\r[3\u000b\u0003y\t1a\u001c:h'\u0019\u0001\u0001E\n\u00163kA\u0011\u0011\u0005J\u0007\u0002E)\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\t1\u0011I\\=SK\u001a\u0004\"a\n\u0015\u000e\u0003]I!!K\f\u0003\u001fA\u0013X\rZ5di>\u0014\b+\u0019:b[N\u0004\"a\u000b\u0019\u000e\u00031R!!\f\u0018\u0002\rMD\u0017M]3e\u0015\tys#A\u0003qCJ\fW.\u0003\u00022Y\t)\u0002*Y:DQ\u0016\u001c7\u000e]8j]RLe\u000e^3sm\u0006d\u0007CA\u00164\u0013\t!DFA\u0004ICN\u001cV-\u001a3\u0011\u0005-2\u0014BA\u001c-\u00051A\u0015m],fS\u001eDGoQ8m\u0003\u0019!\u0013N\\5uI\r\u0001A#A\u001e\u0011\u0005\u0005b\u0014BA\u001f#\u0005\u0011)f.\u001b;\u0002\u000f1,\u0017MZ\"pYV\t\u0001\tE\u0002B\u0005\u0012k\u0011AL\u0005\u0003\u0007:\u0012Q\u0001U1sC6\u0004\"!\u0012'\u000f\u0005\u0019S\u0005CA$#\u001b\u0005A%BA%:\u0003\u0019a$o\\8u}%\u00111JI\u0001\u0007!J,G-\u001a4\n\u00055s%AB*ue&twM\u0003\u0002LE!\u001a!\u0001\u0015,\u0011\u0005E#V\"\u0001*\u000b\u0005MK\u0012AC1o]>$\u0018\r^5p]&\u0011QK\u0015\u0002\u0006'&t7-Z\u0011\u0002/\u0006)1G\f\u0019/a\u0005AQ.\u0019=EKB$\b.F\u0001[!\t\t5,\u0003\u0002]]\tA\u0011J\u001c;QCJ\fW.A\u0004nCb\u0014\u0015N\\:\u0002'5Lg.\u00138ti\u0006t7-Z:QKJtu\u000eZ3\u000215LgnV3jO\"$hI]1di&|g\u000eU3s\u001d>$W-F\u0001b!\t\t%-\u0003\u0002d]\tYAi\\;cY\u0016\u0004\u0016M]1n\u0003-i\u0017N\\%oM><\u0015-\u001b8\u0002\u001b5\f\u00070T3n_JL\u0018J\\'C\u00031\u0019\u0017m\u00195f\u001d>$W-\u00133t+\u0005A\u0007CA!j\u0013\tQgF\u0001\u0007C_>dW-\u00198QCJ\fW.\u0001\u0006tKRdU-\u00194D_2$\"!\u001c8\u000e\u0003\u0001AQa\u001c\u0006A\u0002\u0011\u000bQA^1mk\u0016D3A\u0003)W\u0003)9W\r\u001e'fC\u001a\u001cu\u000e\\\u000b\u0002\t\"\u001a1\u0002\u0015,\u0002\u0017\u001d,G/T1y\t\u0016\u0004H\u000f[\u000b\u0002mB\u0011\u0011e^\u0005\u0003q\n\u00121!\u00138u\u0003)9W\r^'bq\nKgn]\u0001\u0017O\u0016$X*\u001b8J]N$\u0018M\\2fgB+'OT8eK\u0006Yr-\u001a;NS:<V-[4ii\u001a\u0013\u0018m\u0019;j_:\u0004VM\u001d(pI\u0016,\u0012! \t\u0003CyL!a \u0012\u0003\r\u0011{WO\u00197f\u000399W\r^'j]&sgm\\$bS:\f\u0001cZ3u\u001b\u0006DX*Z7pefLe.\u0014\"\u0002\u001f\u001d,GoQ1dQ\u0016tu\u000eZ3JIN,\"!!\u0003\u0011\u0007\u0005\nY!C\u0002\u0002\u000e\t\u0012qAQ8pY\u0016\fg.\u0001\bhKR|E\u000eZ*ue\u0006$XmZ=\u0015\u0019\u0005M\u0011QEA\u0018\u0003g\t)%!\u0016\u0011\t\u0005U\u0011\u0011E\u0007\u0003\u0003/QA!!\u0007\u0002\u001c\u0005i1m\u001c8gS\u001e,(/\u0019;j_:T1AFA\u000f\u0015\r\ty\"G\u0001\u0006[2d\u0017NY\u0005\u0005\u0003G\t9B\u0001\u0005TiJ\fG/Z4z\u0011\u001d\t9c\u0005a\u0001\u0003S\t1cY1uK\u001e|'/[2bY\u001a+\u0017\r^;sKN\u0004R!RA\u0016mZL1!!\fO\u0005\ri\u0015\r\u001d\u0005\u0007\u0003c\u0019\u0002\u0019\u0001<\u0002\u00159,Xn\u00117bgN,7\u000fC\u0004\u00026M\u0001\r!a\u000e\u0002\u000f=dG-\u00117h_B!\u0011\u0011HA \u001d\u0011\t)\"a\u000f\n\t\u0005u\u0012qC\u0001\u0005\u00032<w.\u0003\u0003\u0002B\u0005\r#\u0001B!mO>TA!!\u0010\u0002\u0018!9\u0011qI\nA\u0002\u0005%\u0013aC8mI&k\u0007/\u001e:jif\u0004B!a\u0013\u0002R5\u0011\u0011Q\n\u0006\u0005\u0003\u001f\nY\"\u0001\u0005j[B,(/\u001b;z\u0013\u0011\t\u0019&!\u0014\u0003\u0011%k\u0007/\u001e:jifDa!a\u0016\u0014\u0001\u0004i\u0018aD:vEN\fW\u000e\u001d7j]\u001e\u0014\u0016\r^3"
)
public interface DecisionTreeParams extends PredictorParams, HasCheckpointInterval, HasSeed, HasWeightCol {
   void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$leafCol_$eq(final Param x$1);

   void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$maxDepth_$eq(final IntParam x$1);

   void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$maxBins_$eq(final IntParam x$1);

   void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$minInstancesPerNode_$eq(final IntParam x$1);

   void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$minWeightFractionPerNode_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$minInfoGain_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$maxMemoryInMB_$eq(final IntParam x$1);

   void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$cacheNodeIds_$eq(final BooleanParam x$1);

   Param leafCol();

   IntParam maxDepth();

   IntParam maxBins();

   IntParam minInstancesPerNode();

   DoubleParam minWeightFractionPerNode();

   DoubleParam minInfoGain();

   IntParam maxMemoryInMB();

   BooleanParam cacheNodeIds();

   // $FF: synthetic method
   static DecisionTreeParams setLeafCol$(final DecisionTreeParams $this, final String value) {
      return $this.setLeafCol(value);
   }

   default DecisionTreeParams setLeafCol(final String value) {
      return (DecisionTreeParams)this.set(this.leafCol(), value);
   }

   // $FF: synthetic method
   static String getLeafCol$(final DecisionTreeParams $this) {
      return $this.getLeafCol();
   }

   default String getLeafCol() {
      return (String)this.$(this.leafCol());
   }

   // $FF: synthetic method
   static int getMaxDepth$(final DecisionTreeParams $this) {
      return $this.getMaxDepth();
   }

   default int getMaxDepth() {
      return BoxesRunTime.unboxToInt(this.$(this.maxDepth()));
   }

   // $FF: synthetic method
   static int getMaxBins$(final DecisionTreeParams $this) {
      return $this.getMaxBins();
   }

   default int getMaxBins() {
      return BoxesRunTime.unboxToInt(this.$(this.maxBins()));
   }

   // $FF: synthetic method
   static int getMinInstancesPerNode$(final DecisionTreeParams $this) {
      return $this.getMinInstancesPerNode();
   }

   default int getMinInstancesPerNode() {
      return BoxesRunTime.unboxToInt(this.$(this.minInstancesPerNode()));
   }

   // $FF: synthetic method
   static double getMinWeightFractionPerNode$(final DecisionTreeParams $this) {
      return $this.getMinWeightFractionPerNode();
   }

   default double getMinWeightFractionPerNode() {
      return BoxesRunTime.unboxToDouble(this.$(this.minWeightFractionPerNode()));
   }

   // $FF: synthetic method
   static double getMinInfoGain$(final DecisionTreeParams $this) {
      return $this.getMinInfoGain();
   }

   default double getMinInfoGain() {
      return BoxesRunTime.unboxToDouble(this.$(this.minInfoGain()));
   }

   // $FF: synthetic method
   static int getMaxMemoryInMB$(final DecisionTreeParams $this) {
      return $this.getMaxMemoryInMB();
   }

   default int getMaxMemoryInMB() {
      return BoxesRunTime.unboxToInt(this.$(this.maxMemoryInMB()));
   }

   // $FF: synthetic method
   static boolean getCacheNodeIds$(final DecisionTreeParams $this) {
      return $this.getCacheNodeIds();
   }

   default boolean getCacheNodeIds() {
      return BoxesRunTime.unboxToBoolean(this.$(this.cacheNodeIds()));
   }

   // $FF: synthetic method
   static Strategy getOldStrategy$(final DecisionTreeParams $this, final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity, final double subsamplingRate) {
      return $this.getOldStrategy(categoricalFeatures, numClasses, oldAlgo, oldImpurity, subsamplingRate);
   }

   default Strategy getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity, final double subsamplingRate) {
      Strategy strategy = Strategy$.MODULE$.defaultStrategy(oldAlgo);
      strategy.impurity_$eq(oldImpurity);
      strategy.checkpointInterval_$eq(this.getCheckpointInterval());
      strategy.maxBins_$eq(this.getMaxBins());
      strategy.maxDepth_$eq(this.getMaxDepth());
      strategy.maxMemoryInMB_$eq(this.getMaxMemoryInMB());
      strategy.minInfoGain_$eq(this.getMinInfoGain());
      strategy.minInstancesPerNode_$eq(this.getMinInstancesPerNode());
      strategy.minWeightFractionPerNode_$eq(this.getMinWeightFractionPerNode());
      strategy.useNodeIdCache_$eq(this.getCacheNodeIds());
      strategy.numClasses_$eq(numClasses);
      strategy.categoricalFeaturesInfo_$eq(categoricalFeatures);
      strategy.subsamplingRate_$eq(subsamplingRate);
      return strategy;
   }

   static void $init$(final DecisionTreeParams $this) {
      $this.org$apache$spark$ml$tree$DecisionTreeParams$_setter_$leafCol_$eq(new Param($this, "leafCol", "Leaf indices column name. Predicted leaf index of each instance in each tree by preorder", .MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$tree$DecisionTreeParams$_setter_$maxDepth_$eq(new IntParam($this, "maxDepth", "Maximum depth of the tree. (Nonnegative) E.g., depth 0 means 1 leaf node; depth 1 means 1 internal node + 2 leaf nodes. Must be in range [0, 30].", ParamValidators$.MODULE$.inRange((double)0.0F, (double)30.0F)));
      $this.org$apache$spark$ml$tree$DecisionTreeParams$_setter_$maxBins_$eq(new IntParam($this, "maxBins", "Max number of bins for discretizing continuous features.  Must be at least 2 and at least number of categories for any categorical feature.", ParamValidators$.MODULE$.gtEq((double)2.0F)));
      $this.org$apache$spark$ml$tree$DecisionTreeParams$_setter_$minInstancesPerNode_$eq(new IntParam($this, "minInstancesPerNode", "Minimum number of instances each child must have after split.  If a split causes the left or right child to have fewer than minInstancesPerNode, the split will be discarded as invalid. Must be at least 1.", ParamValidators$.MODULE$.gtEq((double)1.0F)));
      $this.org$apache$spark$ml$tree$DecisionTreeParams$_setter_$minWeightFractionPerNode_$eq(new DoubleParam($this, "minWeightFractionPerNode", "Minimum fraction of the weighted sample count that each child must have after split. If a split causes the fraction of the total weight in the left or right child to be less than minWeightFractionPerNode, the split will be discarded as invalid. Should be in interval [0.0, 0.5)", ParamValidators$.MODULE$.inRange((double)0.0F, (double)0.5F, true, false)));
      $this.org$apache$spark$ml$tree$DecisionTreeParams$_setter_$minInfoGain_$eq(new DoubleParam($this, "minInfoGain", "Minimum information gain for a split to be considered at a tree node.", ParamValidators$.MODULE$.gtEq((double)0.0F)));
      $this.org$apache$spark$ml$tree$DecisionTreeParams$_setter_$maxMemoryInMB_$eq(new IntParam($this, "maxMemoryInMB", "Maximum memory in MB allocated to histogram aggregation.", ParamValidators$.MODULE$.gtEq((double)0.0F)));
      $this.org$apache$spark$ml$tree$DecisionTreeParams$_setter_$cacheNodeIds_$eq(new BooleanParam($this, "cacheNodeIds", "If false, the algorithm will pass trees to executors to match instances with nodes. If true, the algorithm will cache node IDs for each instance. Caching can speed up training of deeper trees."));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.leafCol().$minus$greater(""), $this.maxDepth().$minus$greater(BoxesRunTime.boxToInteger(5)), $this.maxBins().$minus$greater(BoxesRunTime.boxToInteger(32)), $this.minInstancesPerNode().$minus$greater(BoxesRunTime.boxToInteger(1)), $this.minWeightFractionPerNode().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F)), $this.minInfoGain().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F)), $this.maxMemoryInMB().$minus$greater(BoxesRunTime.boxToInteger(256)), $this.cacheNodeIds().$minus$greater(BoxesRunTime.boxToBoolean(false)), $this.checkpointInterval().$minus$greater(BoxesRunTime.boxToInteger(10))}));
   }
}
