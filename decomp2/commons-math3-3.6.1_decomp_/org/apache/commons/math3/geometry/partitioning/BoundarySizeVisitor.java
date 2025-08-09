package org.apache.commons.math3.geometry.partitioning;

class BoundarySizeVisitor implements BSPTreeVisitor {
   private double boundarySize = (double)0.0F;

   public BSPTreeVisitor.Order visitOrder(BSPTree node) {
      return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
   }

   public void visitInternalNode(BSPTree node) {
      BoundaryAttribute<S> attribute = (BoundaryAttribute)node.getAttribute();
      if (attribute.getPlusOutside() != null) {
         this.boundarySize += attribute.getPlusOutside().getSize();
      }

      if (attribute.getPlusInside() != null) {
         this.boundarySize += attribute.getPlusInside().getSize();
      }

   }

   public void visitLeafNode(BSPTree node) {
   }

   public double getSize() {
      return this.boundarySize;
   }
}
