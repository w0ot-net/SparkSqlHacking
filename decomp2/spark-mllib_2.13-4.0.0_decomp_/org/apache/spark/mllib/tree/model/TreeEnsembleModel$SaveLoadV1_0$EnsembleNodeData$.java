package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public class TreeEnsembleModel$SaveLoadV1_0$EnsembleNodeData$ extends AbstractFunction2 implements Serializable {
   public static final TreeEnsembleModel$SaveLoadV1_0$EnsembleNodeData$ MODULE$ = new TreeEnsembleModel$SaveLoadV1_0$EnsembleNodeData$();

   public final String toString() {
      return "EnsembleNodeData";
   }

   public TreeEnsembleModel$SaveLoadV1_0$EnsembleNodeData apply(final int treeId, final DecisionTreeModel$SaveLoadV1_0$NodeData node) {
      return new TreeEnsembleModel$SaveLoadV1_0$EnsembleNodeData(treeId, node);
   }

   public Option unapply(final TreeEnsembleModel$SaveLoadV1_0$EnsembleNodeData x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.treeId()), x$0.node())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TreeEnsembleModel$SaveLoadV1_0$EnsembleNodeData$.class);
   }
}
