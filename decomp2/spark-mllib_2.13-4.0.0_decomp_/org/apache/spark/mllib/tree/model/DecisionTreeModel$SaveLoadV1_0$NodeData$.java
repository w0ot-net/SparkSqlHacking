package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.Row;
import scala.Option;
import scala.Some;
import scala.Tuple9;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public class DecisionTreeModel$SaveLoadV1_0$NodeData$ implements Serializable {
   public static final DecisionTreeModel$SaveLoadV1_0$NodeData$ MODULE$ = new DecisionTreeModel$SaveLoadV1_0$NodeData$();

   public DecisionTreeModel$SaveLoadV1_0$NodeData apply(final int treeId, final Node n) {
      return new DecisionTreeModel$SaveLoadV1_0$NodeData(treeId, n.id(), DecisionTreeModel$SaveLoadV1_0$PredictData$.MODULE$.apply(n.predict()), n.impurity(), n.isLeaf(), n.split().map((s) -> DecisionTreeModel$SaveLoadV1_0$SplitData$.MODULE$.apply(s)), n.leftNode().map((x$1) -> BoxesRunTime.boxToInteger($anonfun$apply$2(x$1))), n.rightNode().map((x$2) -> BoxesRunTime.boxToInteger($anonfun$apply$3(x$2))), n.stats().map((x$3) -> BoxesRunTime.boxToDouble($anonfun$apply$4(x$3))));
   }

   public DecisionTreeModel$SaveLoadV1_0$NodeData apply(final Row r) {
      Option split = (Option)(r.isNullAt(5) ? .MODULE$ : new Some(DecisionTreeModel$SaveLoadV1_0$SplitData$.MODULE$.apply(r.getStruct(5))));
      Option leftNodeId = (Option)(r.isNullAt(6) ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(r.getInt(6))));
      Option rightNodeId = (Option)(r.isNullAt(7) ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(r.getInt(7))));
      Option infoGain = (Option)(r.isNullAt(8) ? .MODULE$ : new Some(BoxesRunTime.boxToDouble(r.getDouble(8))));
      return new DecisionTreeModel$SaveLoadV1_0$NodeData(r.getInt(0), r.getInt(1), DecisionTreeModel$SaveLoadV1_0$PredictData$.MODULE$.apply(r.getStruct(2)), r.getDouble(3), r.getBoolean(4), split, leftNodeId, rightNodeId, infoGain);
   }

   public DecisionTreeModel$SaveLoadV1_0$NodeData apply(final int treeId, final int nodeId, final DecisionTreeModel$SaveLoadV1_0$PredictData predict, final double impurity, final boolean isLeaf, final Option split, final Option leftNodeId, final Option rightNodeId, final Option infoGain) {
      return new DecisionTreeModel$SaveLoadV1_0$NodeData(treeId, nodeId, predict, impurity, isLeaf, split, leftNodeId, rightNodeId, infoGain);
   }

   public Option unapply(final DecisionTreeModel$SaveLoadV1_0$NodeData x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple9(BoxesRunTime.boxToInteger(x$0.treeId()), BoxesRunTime.boxToInteger(x$0.nodeId()), x$0.predict(), BoxesRunTime.boxToDouble(x$0.impurity()), BoxesRunTime.boxToBoolean(x$0.isLeaf()), x$0.split(), x$0.leftNodeId(), x$0.rightNodeId(), x$0.infoGain())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DecisionTreeModel$SaveLoadV1_0$NodeData$.class);
   }

   // $FF: synthetic method
   public static final int $anonfun$apply$2(final Node x$1) {
      return x$1.id();
   }

   // $FF: synthetic method
   public static final int $anonfun$apply$3(final Node x$2) {
      return x$2.id();
   }

   // $FF: synthetic method
   public static final double $anonfun$apply$4(final InformationGainStats x$3) {
      return x$3.gain();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
