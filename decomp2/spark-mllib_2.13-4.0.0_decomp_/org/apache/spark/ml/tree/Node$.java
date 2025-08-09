package org.apache.spark.ml.tree;

import java.io.Serializable;
import org.apache.spark.mllib.tree.impurity.ImpurityCalculator;
import org.apache.spark.mllib.tree.impurity.ImpurityCalculator$;
import org.apache.spark.mllib.tree.model.InformationGainStats;
import scala.Array.;
import scala.collection.immutable.Map;
import scala.runtime.ModuleSerializationProxy;

public final class Node$ implements Serializable {
   public static final Node$ MODULE$ = new Node$();
   private static final Node dummyNode;

   static {
      dummyNode = new LeafNode((double)0.0F, (double)0.0F, ImpurityCalculator$.MODULE$.getCalculator("gini", (double[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double()), 0L));
   }

   public Node fromOld(final org.apache.spark.mllib.tree.model.Node oldNode, final Map categoricalFeatures) {
      if (oldNode.isLeaf()) {
         return new LeafNode(oldNode.predict().predict(), oldNode.impurity(), (ImpurityCalculator)null);
      } else {
         double gain = oldNode.stats().nonEmpty() ? ((InformationGainStats)oldNode.stats().get()).gain() : (double)0.0F;
         return new InternalNode(oldNode.predict().predict(), oldNode.impurity(), gain, this.fromOld((org.apache.spark.mllib.tree.model.Node)oldNode.leftNode().get(), categoricalFeatures), this.fromOld((org.apache.spark.mllib.tree.model.Node)oldNode.rightNode().get(), categoricalFeatures), Split$.MODULE$.fromOld((org.apache.spark.mllib.tree.model.Split)oldNode.split().get(), categoricalFeatures), (ImpurityCalculator)null);
      }
   }

   public Node dummyNode() {
      return dummyNode;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Node$.class);
   }

   private Node$() {
   }
}
