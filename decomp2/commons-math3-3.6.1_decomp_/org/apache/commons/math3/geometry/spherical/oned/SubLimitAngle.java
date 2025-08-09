package org.apache.commons.math3.geometry.spherical.oned;

import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

public class SubLimitAngle extends AbstractSubHyperplane {
   public SubLimitAngle(Hyperplane hyperplane, Region remainingRegion) {
      super(hyperplane, remainingRegion);
   }

   public double getSize() {
      return (double)0.0F;
   }

   public boolean isEmpty() {
      return false;
   }

   protected AbstractSubHyperplane buildNew(Hyperplane hyperplane, Region remainingRegion) {
      return new SubLimitAngle(hyperplane, remainingRegion);
   }

   public SubHyperplane.SplitSubHyperplane split(Hyperplane hyperplane) {
      double global = hyperplane.getOffset(((LimitAngle)this.getHyperplane()).getLocation());
      return global < -1.0E-10 ? new SubHyperplane.SplitSubHyperplane((SubHyperplane)null, this) : new SubHyperplane.SplitSubHyperplane(this, (SubHyperplane)null);
   }
}
