package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Node$ implements Serializable {
   public static final Node$ MODULE$ = new Node$();

   public Node emptyNode(final int nodeIndex) {
      return new Node(nodeIndex, new Predict(-Double.MAX_VALUE, Predict$.MODULE$.$lessinit$greater$default$2()), (double)-1.0F, false, .MODULE$, .MODULE$, .MODULE$, .MODULE$);
   }

   public Node apply(final int nodeIndex, final Predict predict, final double impurity, final boolean isLeaf) {
      return new Node(nodeIndex, predict, impurity, isLeaf, .MODULE$, .MODULE$, .MODULE$, .MODULE$);
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

   public Node getNode(final int nodeIndex, final Node rootNode) {
      Node tmpNode = rootNode;

      for(int levelsToGo = this.indexToLevel(nodeIndex); levelsToGo > 0; --levelsToGo) {
         if ((nodeIndex & 1 << levelsToGo - 1) == 0) {
            tmpNode = (Node)tmpNode.leftNode().get();
         } else {
            tmpNode = (Node)tmpNode.rightNode().get();
         }
      }

      return tmpNode;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Node$.class);
   }

   private Node$() {
   }
}
