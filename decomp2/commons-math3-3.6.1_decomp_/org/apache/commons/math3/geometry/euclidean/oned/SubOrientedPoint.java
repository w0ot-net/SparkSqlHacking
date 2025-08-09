package org.apache.commons.math3.geometry.euclidean.oned;

import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

public class SubOrientedPoint extends AbstractSubHyperplane {
   public SubOrientedPoint(Hyperplane hyperplane, Region remainingRegion) {
      super(hyperplane, remainingRegion);
   }

   public double getSize() {
      return (double)0.0F;
   }

   public boolean isEmpty() {
      return false;
   }

   protected AbstractSubHyperplane buildNew(Hyperplane hyperplane, Region remainingRegion) {
      return new SubOrientedPoint(hyperplane, remainingRegion);
   }

   public SubHyperplane.SplitSubHyperplane split(Hyperplane hyperplane) {
      double global = hyperplane.getOffset(((OrientedPoint)this.getHyperplane()).getLocation());
      if (global < -1.0E-10) {
         return new SubHyperplane.SplitSubHyperplane((SubHyperplane)null, this);
      } else {
         return global > 1.0E-10 ? new SubHyperplane.SplitSubHyperplane(this, (SubHyperplane)null) : new SubHyperplane.SplitSubHyperplane((SubHyperplane)null, (SubHyperplane)null);
      }
   }
}
