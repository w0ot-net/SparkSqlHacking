package org.apache.commons.math3.geometry.partitioning;

class BoundaryBuilder implements BSPTreeVisitor {
   public BSPTreeVisitor.Order visitOrder(BSPTree node) {
      return BSPTreeVisitor.Order.PLUS_MINUS_SUB;
   }

   public void visitInternalNode(BSPTree node) {
      SubHyperplane<S> plusOutside = null;
      SubHyperplane<S> plusInside = null;
      NodesSet<S> splitters = null;
      Characterization<S> plusChar = new Characterization(node.getPlus(), node.getCut().copySelf());
      if (plusChar.touchOutside()) {
         Characterization<S> minusChar = new Characterization(node.getMinus(), plusChar.outsideTouching());
         if (minusChar.touchInside()) {
            plusOutside = minusChar.insideTouching();
            splitters = new NodesSet();
            splitters.addAll(minusChar.getInsideSplitters());
            splitters.addAll(plusChar.getOutsideSplitters());
         }
      }

      if (plusChar.touchInside()) {
         Characterization<S> minusChar = new Characterization(node.getMinus(), plusChar.insideTouching());
         if (minusChar.touchOutside()) {
            plusInside = minusChar.outsideTouching();
            if (splitters == null) {
               splitters = new NodesSet();
            }

            splitters.addAll(minusChar.getOutsideSplitters());
            splitters.addAll(plusChar.getInsideSplitters());
         }
      }

      if (splitters != null) {
         for(BSPTree<S> up = node.getParent(); up != null; up = up.getParent()) {
            splitters.add(up);
         }
      }

      node.setAttribute(new BoundaryAttribute(plusOutside, plusInside, splitters));
   }

   public void visitLeafNode(BSPTree node) {
   }
}
