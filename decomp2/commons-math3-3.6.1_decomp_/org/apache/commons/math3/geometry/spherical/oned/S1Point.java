package org.apache.commons.math3.geometry.spherical.oned;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class S1Point implements Point {
   public static final S1Point NaN;
   private static final long serialVersionUID = 20131218L;
   private final double alpha;
   private final Vector2D vector;

   public S1Point(double alpha) {
      this(MathUtils.normalizeAngle(alpha, Math.PI), new Vector2D(FastMath.cos(alpha), FastMath.sin(alpha)));
   }

   private S1Point(double alpha, Vector2D vector) {
      this.alpha = alpha;
      this.vector = vector;
   }

   public double getAlpha() {
      return this.alpha;
   }

   public Vector2D getVector() {
      return this.vector;
   }

   public Space getSpace() {
      return Sphere1D.getInstance();
   }

   public boolean isNaN() {
      return Double.isNaN(this.alpha);
   }

   public double distance(Point point) {
      return distance(this, (S1Point)point);
   }

   public static double distance(S1Point p1, S1Point p2) {
      return Vector2D.angle(p1.vector, p2.vector);
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other instanceof S1Point) {
         S1Point rhs = (S1Point)other;
         if (rhs.isNaN()) {
            return this.isNaN();
         } else {
            return this.alpha == rhs.alpha;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.isNaN() ? 542 : 1759 * MathUtils.hash(this.alpha);
   }

   static {
      NaN = new S1Point(Double.NaN, Vector2D.NaN);
   }
}
