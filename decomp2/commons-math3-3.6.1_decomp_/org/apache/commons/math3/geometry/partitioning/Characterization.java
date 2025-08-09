package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;

class Characterization {
   private SubHyperplane outsideTouching = null;
   private SubHyperplane insideTouching = null;
   private final NodesSet outsideSplitters = new NodesSet();
   private final NodesSet insideSplitters = new NodesSet();

   Characterization(BSPTree node, SubHyperplane sub) {
      this.characterize(node, sub, new ArrayList());
   }

   private void characterize(BSPTree node, SubHyperplane sub, List splitters) {
      if (node.getCut() == null) {
         boolean inside = (Boolean)node.getAttribute();
         if (inside) {
            this.addInsideTouching(sub, splitters);
         } else {
            this.addOutsideTouching(sub, splitters);
         }
      } else {
         Hyperplane<S> hyperplane = node.getCut().getHyperplane();
         SubHyperplane.SplitSubHyperplane<S> split = sub.split(hyperplane);
         switch (split.getSide()) {
            case PLUS:
               this.characterize(node.getPlus(), sub, splitters);
               break;
            case MINUS:
               this.characterize(node.getMinus(), sub, splitters);
               break;
            case BOTH:
               splitters.add(node);
               this.characterize(node.getPlus(), split.getPlus(), splitters);
               this.characterize(node.getMinus(), split.getMinus(), splitters);
               splitters.remove(splitters.size() - 1);
               break;
            default:
               throw new MathInternalError();
         }
      }

   }

   private void addOutsideTouching(SubHyperplane sub, List splitters) {
      if (this.outsideTouching == null) {
         this.outsideTouching = sub;
      } else {
         this.outsideTouching = this.outsideTouching.reunite(sub);
      }

      this.outsideSplitters.addAll(splitters);
   }

   private void addInsideTouching(SubHyperplane sub, List splitters) {
      if (this.insideTouching == null) {
         this.insideTouching = sub;
      } else {
         this.insideTouching = this.insideTouching.reunite(sub);
      }

      this.insideSplitters.addAll(splitters);
   }

   public boolean touchOutside() {
      return this.outsideTouching != null && !this.outsideTouching.isEmpty();
   }

   public SubHyperplane outsideTouching() {
      return this.outsideTouching;
   }

   public NodesSet getOutsideSplitters() {
      return this.outsideSplitters;
   }

   public boolean touchInside() {
      return this.insideTouching != null && !this.insideTouching.isEmpty();
   }

   public SubHyperplane insideTouching() {
      return this.insideTouching;
   }

   public NodesSet getInsideSplitters() {
      return this.insideSplitters;
   }
}
