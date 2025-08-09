package org.apache.commons.math3.geometry.euclidean.threed;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.enclosing.EnclosingBall;
import org.apache.commons.math3.geometry.enclosing.SupportBallGenerator;
import org.apache.commons.math3.geometry.euclidean.twod.DiskGenerator;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

public class SphereGenerator implements SupportBallGenerator {
   public EnclosingBall ballOnSupport(List support) {
      if (support.size() < 1) {
         return new EnclosingBall(Vector3D.ZERO, Double.NEGATIVE_INFINITY, new Vector3D[0]);
      } else {
         Vector3D vA = (Vector3D)support.get(0);
         if (support.size() < 2) {
            return new EnclosingBall(vA, (double)0.0F, new Vector3D[]{vA});
         } else {
            Vector3D vB = (Vector3D)support.get(1);
            if (support.size() < 3) {
               return new EnclosingBall(new Vector3D((double)0.5F, vA, (double)0.5F, vB), (double)0.5F * vA.distance((Vector)vB), new Vector3D[]{vA, vB});
            } else {
               Vector3D vC = (Vector3D)support.get(2);
               if (support.size() < 4) {
                  Plane p = new Plane(vA, vB, vC, 1.0E-10 * (vA.getNorm1() + vB.getNorm1() + vC.getNorm1()));
                  EnclosingBall<Euclidean2D, Vector2D> disk = (new DiskGenerator()).ballOnSupport(Arrays.asList(p.toSubSpace((Vector)vA), p.toSubSpace((Vector)vB), p.toSubSpace((Vector)vC)));
                  return new EnclosingBall(p.toSpace((Vector)disk.getCenter()), disk.getRadius(), new Vector3D[]{vA, vB, vC});
               } else {
                  Vector3D vD = (Vector3D)support.get(3);
                  BigFraction[] c2 = new BigFraction[]{new BigFraction(vA.getX()), new BigFraction(vB.getX()), new BigFraction(vC.getX()), new BigFraction(vD.getX())};
                  BigFraction[] c3 = new BigFraction[]{new BigFraction(vA.getY()), new BigFraction(vB.getY()), new BigFraction(vC.getY()), new BigFraction(vD.getY())};
                  BigFraction[] c4 = new BigFraction[]{new BigFraction(vA.getZ()), new BigFraction(vB.getZ()), new BigFraction(vC.getZ()), new BigFraction(vD.getZ())};
                  BigFraction[] c1 = new BigFraction[]{c2[0].multiply(c2[0]).add(c3[0].multiply(c3[0])).add(c4[0].multiply(c4[0])), c2[1].multiply(c2[1]).add(c3[1].multiply(c3[1])).add(c4[1].multiply(c4[1])), c2[2].multiply(c2[2]).add(c3[2].multiply(c3[2])).add(c4[2].multiply(c4[2])), c2[3].multiply(c2[3]).add(c3[3].multiply(c3[3])).add(c4[3].multiply(c4[3]))};
                  BigFraction twoM11 = this.minor(c2, c3, c4).multiply(2);
                  BigFraction m12 = this.minor(c1, c3, c4);
                  BigFraction m13 = this.minor(c1, c2, c4);
                  BigFraction m14 = this.minor(c1, c2, c3);
                  BigFraction centerX = m12.divide(twoM11);
                  BigFraction centerY = m13.divide(twoM11).negate();
                  BigFraction centerZ = m14.divide(twoM11);
                  BigFraction dx = c2[0].subtract(centerX);
                  BigFraction dy = c3[0].subtract(centerY);
                  BigFraction dz = c4[0].subtract(centerZ);
                  BigFraction r2 = dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz));
                  return new EnclosingBall(new Vector3D(centerX.doubleValue(), centerY.doubleValue(), centerZ.doubleValue()), FastMath.sqrt(r2.doubleValue()), new Vector3D[]{vA, vB, vC, vD});
               }
            }
         }
      }
   }

   private BigFraction minor(BigFraction[] c1, BigFraction[] c2, BigFraction[] c3) {
      return c2[0].multiply(c3[1]).multiply(c1[2].subtract(c1[3])).add(c2[0].multiply(c3[2]).multiply(c1[3].subtract(c1[1]))).add(c2[0].multiply(c3[3]).multiply(c1[1].subtract(c1[2]))).add(c2[1].multiply(c3[0]).multiply(c1[3].subtract(c1[2]))).add(c2[1].multiply(c3[2]).multiply(c1[0].subtract(c1[3]))).add(c2[1].multiply(c3[3]).multiply(c1[2].subtract(c1[0]))).add(c2[2].multiply(c3[0]).multiply(c1[1].subtract(c1[3]))).add(c2[2].multiply(c3[1]).multiply(c1[3].subtract(c1[0]))).add(c2[2].multiply(c3[3]).multiply(c1[0].subtract(c1[1]))).add(c2[3].multiply(c3[0]).multiply(c1[2].subtract(c1[1]))).add(c2[3].multiply(c3[1]).multiply(c1[0].subtract(c1[2]))).add(c2[3].multiply(c3[2]).multiply(c1[1].subtract(c1[0])));
   }
}
