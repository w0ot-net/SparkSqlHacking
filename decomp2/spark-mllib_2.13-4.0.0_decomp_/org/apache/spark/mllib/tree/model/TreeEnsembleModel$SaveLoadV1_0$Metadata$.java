package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.ModuleSerializationProxy;

public class TreeEnsembleModel$SaveLoadV1_0$Metadata$ extends AbstractFunction4 implements Serializable {
   public static final TreeEnsembleModel$SaveLoadV1_0$Metadata$ MODULE$ = new TreeEnsembleModel$SaveLoadV1_0$Metadata$();

   public final String toString() {
      return "Metadata";
   }

   public TreeEnsembleModel$SaveLoadV1_0$Metadata apply(final String algo, final String treeAlgo, final String combiningStrategy, final double[] treeWeights) {
      return new TreeEnsembleModel$SaveLoadV1_0$Metadata(algo, treeAlgo, combiningStrategy, treeWeights);
   }

   public Option unapply(final TreeEnsembleModel$SaveLoadV1_0$Metadata x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.algo(), x$0.treeAlgo(), x$0.combiningStrategy(), x$0.treeWeights())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TreeEnsembleModel$SaveLoadV1_0$Metadata$.class);
   }
}
