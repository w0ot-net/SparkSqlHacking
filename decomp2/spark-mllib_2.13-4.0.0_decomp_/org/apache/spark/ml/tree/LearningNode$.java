package org.apache.spark.ml.tree;

import java.io.Serializable;
import org.apache.spark.mllib.tree.model.ImpurityStats;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class LearningNode$ implements Serializable {
   public static final LearningNode$ MODULE$ = new LearningNode$();

   public LearningNode apply(final int id, final boolean isLeaf, final ImpurityStats stats) {
      return new LearningNode(id, .MODULE$, .MODULE$, .MODULE$, false, stats);
   }

   public LearningNode emptyNode(final int nodeIndex) {
      return new LearningNode(nodeIndex, .MODULE$, .MODULE$, .MODULE$, false, (ImpurityStats)null);
   }

   public int leftChildIndex(final int nodeIndex) {
      return nodeIndex << 1;
   }

   public int rightChildIndex(final int nodeIndex) {
      return (nodeIndex << 1) + 1;
   }

   public int parentIndex(final int nodeIndex) {
      return nodeIndex >> 1;
   }

   public int indexToLevel(final int nodeIndex) {
      if (nodeIndex == 0) {
         throw new IllegalArgumentException("0 is not a valid node index.");
      } else {
         return Integer.numberOfTrailingZeros(Integer.highestOneBit(nodeIndex));
      }
   }

   public boolean isLeftChild(final int nodeIndex) {
      return nodeIndex > 1 && nodeIndex % 2 == 0;
   }

   public int maxNodesInLevel(final int level) {
      return 1 << level;
   }

   public int startIndexInLevel(final int level) {
      return 1 << level;
   }

   public LearningNode getNode(final int nodeIndex, final LearningNode rootNode) {
      LearningNode tmpNode = rootNode;

      for(int levelsToGo = this.indexToLevel(nodeIndex); levelsToGo > 0; --levelsToGo) {
         if ((nodeIndex & 1 << levelsToGo - 1) == 0) {
            tmpNode = (LearningNode)tmpNode.leftChild().get();
         } else {
            tmpNode = (LearningNode)tmpNode.rightChild().get();
         }
      }

      return tmpNode;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LearningNode$.class);
   }

   private LearningNode$() {
   }
}
