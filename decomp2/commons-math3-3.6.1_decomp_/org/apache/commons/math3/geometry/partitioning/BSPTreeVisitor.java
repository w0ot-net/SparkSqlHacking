package org.apache.commons.math3.geometry.partitioning;

public interface BSPTreeVisitor {
   Order visitOrder(BSPTree var1);

   void visitInternalNode(BSPTree var1);

   void visitLeafNode(BSPTree var1);

   public static enum Order {
      PLUS_MINUS_SUB,
      PLUS_SUB_MINUS,
      MINUS_PLUS_SUB,
      MINUS_SUB_PLUS,
      SUB_PLUS_MINUS,
      SUB_MINUS_PLUS;
   }
}
