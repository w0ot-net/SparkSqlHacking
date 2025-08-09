package org.apache.commons.math3.geometry.spherical.oned;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;

public class LimitAngle implements Hyperplane {
   private S1Point location;
   private boolean direct;
   private final double tolerance;

   public LimitAngle(S1Point location, boolean direct, double tolerance) {
      this.location = location;
      this.direct = direct;
      this.tolerance = tolerance;
   }

   public LimitAngle copySelf() {
      return this;
   }

   public double getOffset(Point point) {
      double delta = ((S1Point)point).getAlpha() - this.location.getAlpha();
      return this.direct ? delta : -delta;
   }

   public boolean isDirect() {
      return this.direct;
   }

   public LimitAngle getReverse() {
      return new LimitAngle(this.location, !this.direct, this.tolerance);
   }

   public SubLimitAngle wholeHyperplane() {
      return new SubLimitAngle(this, (Region)null);
   }

   public ArcsSet wholeSpace() {
      return new ArcsSet(this.tolerance);
   }

   public boolean sameOrientationAs(Hyperplane other) {
      return !(this.direct ^ ((LimitAngle)other).direct);
   }

   public S1Point getLocation() {
      return this.location;
   }

   public Point project(Point point) {
      return this.location;
   }

   public double getTolerance() {
      return this.tolerance;
   }
}
