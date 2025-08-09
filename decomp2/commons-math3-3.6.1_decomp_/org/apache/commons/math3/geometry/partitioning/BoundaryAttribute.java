package org.apache.commons.math3.geometry.partitioning;

public class BoundaryAttribute {
   private final SubHyperplane plusOutside;
   private final SubHyperplane plusInside;
   private final NodesSet splitters;

   /** @deprecated */
   @Deprecated
   public BoundaryAttribute(SubHyperplane plusOutside, SubHyperplane plusInside) {
      this(plusOutside, plusInside, (NodesSet)null);
   }

   BoundaryAttribute(SubHyperplane plusOutside, SubHyperplane plusInside, NodesSet splitters) {
      this.plusOutside = plusOutside;
      this.plusInside = plusInside;
      this.splitters = splitters;
   }

   public SubHyperplane getPlusOutside() {
      return this.plusOutside;
   }

   public SubHyperplane getPlusInside() {
      return this.plusInside;
   }

   public NodesSet getSplitters() {
      return this.splitters;
   }
}
