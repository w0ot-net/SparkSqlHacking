package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.util.FastMath;

class BoundaryProjector implements BSPTreeVisitor {
   private final Point original;
   private Point projected;
   private BSPTree leaf;
   private double offset;

   BoundaryProjector(Point original) {
      this.original = original;
      this.projected = null;
      this.leaf = null;
      this.offset = Double.POSITIVE_INFINITY;
   }

   public BSPTreeVisitor.Order visitOrder(BSPTree node) {
      return node.getCut().getHyperplane().getOffset(this.original) <= (double)0.0F ? BSPTreeVisitor.Order.MINUS_SUB_PLUS : BSPTreeVisitor.Order.PLUS_SUB_MINUS;
   }

   public void visitInternalNode(BSPTree node) {
      Hyperplane<S> hyperplane = node.getCut().getHyperplane();
      double signedOffset = hyperplane.getOffset(this.original);
      if (FastMath.abs(signedOffset) < this.offset) {
         Point<S> regular = hyperplane.project(this.original);
         List<Region<T>> boundaryParts = this.boundaryRegions(node);
         boolean regularFound = false;

         for(Region part : boundaryParts) {
            if (!regularFound && this.belongsToPart(regular, hyperplane, part)) {
               this.projected = regular;
               this.offset = FastMath.abs(signedOffset);
               regularFound = true;
            }
         }

         if (!regularFound) {
            for(Region part : boundaryParts) {
               Point<S> spI = this.singularProjection(regular, hyperplane, part);
               if (spI != null) {
                  double distance = this.original.distance(spI);
                  if (distance < this.offset) {
                     this.projected = spI;
                     this.offset = distance;
                  }
               }
            }
         }
      }

   }

   public void visitLeafNode(BSPTree node) {
      if (this.leaf == null) {
         this.leaf = node;
      }

   }

   public BoundaryProjection getProjection() {
      this.offset = FastMath.copySign(this.offset, (Boolean)this.leaf.getAttribute() ? (double)-1.0F : (double)1.0F);
      return new BoundaryProjection(this.original, this.projected, this.offset);
   }

   private List boundaryRegions(BSPTree node) {
      List<Region<T>> regions = new ArrayList(2);
      BoundaryAttribute<S> ba = (BoundaryAttribute)node.getAttribute();
      this.addRegion(ba.getPlusInside(), regions);
      this.addRegion(ba.getPlusOutside(), regions);
      return regions;
   }

   private void addRegion(SubHyperplane sub, List list) {
      if (sub != null) {
         Region<T> region = ((AbstractSubHyperplane)sub).getRemainingRegion();
         if (region != null) {
            list.add(region);
         }
      }

   }

   private boolean belongsToPart(Point point, Hyperplane hyperplane, Region part) {
      Embedding<S, T> embedding = (Embedding)hyperplane;
      return part.checkPoint(embedding.toSubSpace(point)) != Region.Location.OUTSIDE;
   }

   private Point singularProjection(Point point, Hyperplane hyperplane, Region part) {
      Embedding<S, T> embedding = (Embedding)hyperplane;
      BoundaryProjection<T> bp = part.projectToBoundary(embedding.toSubSpace(point));
      return bp.getProjected() == null ? null : embedding.toSpace(bp.getProjected());
   }
}
