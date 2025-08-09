package org.apache.commons.math3.geometry.spherical.twod;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.apache.commons.math3.geometry.spherical.oned.ArcsSet;
import org.apache.commons.math3.geometry.spherical.oned.Sphere1D;

public class SubCircle extends AbstractSubHyperplane {
   public SubCircle(Hyperplane hyperplane, Region remainingRegion) {
      super(hyperplane, remainingRegion);
   }

   protected AbstractSubHyperplane buildNew(Hyperplane hyperplane, Region remainingRegion) {
      return new SubCircle(hyperplane, remainingRegion);
   }

   public SubHyperplane.SplitSubHyperplane split(Hyperplane hyperplane) {
      Circle thisCircle = (Circle)this.getHyperplane();
      Circle otherCircle = (Circle)hyperplane;
      double angle = Vector3D.angle(thisCircle.getPole(), otherCircle.getPole());
      if (!(angle < thisCircle.getTolerance()) && !(angle > Math.PI - thisCircle.getTolerance())) {
         Arc arc = thisCircle.getInsideArc(otherCircle);
         ArcsSet.Split split = ((ArcsSet)this.getRemainingRegion()).split(arc);
         ArcsSet plus = split.getPlus();
         ArcsSet minus = split.getMinus();
         return new SubHyperplane.SplitSubHyperplane(plus == null ? null : new SubCircle(thisCircle.copySelf(), plus), minus == null ? null : new SubCircle(thisCircle.copySelf(), minus));
      } else {
         return new SubHyperplane.SplitSubHyperplane((SubHyperplane)null, (SubHyperplane)null);
      }
   }
}
