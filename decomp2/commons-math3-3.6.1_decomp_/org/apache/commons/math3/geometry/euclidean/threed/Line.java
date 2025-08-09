package org.apache.commons.math3.geometry.euclidean.threed;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class Line implements Embedding {
   private static final double DEFAULT_TOLERANCE = 1.0E-10;
   private Vector3D direction;
   private Vector3D zero;
   private final double tolerance;

   public Line(Vector3D p1, Vector3D p2, double tolerance) throws MathIllegalArgumentException {
      this.reset(p1, p2);
      this.tolerance = tolerance;
   }

   public Line(Line line) {
      this.direction = line.direction;
      this.zero = line.zero;
      this.tolerance = line.tolerance;
   }

   /** @deprecated */
   @Deprecated
   public Line(Vector3D p1, Vector3D p2) throws MathIllegalArgumentException {
      this(p1, p2, 1.0E-10);
   }

   public void reset(Vector3D p1, Vector3D p2) throws MathIllegalArgumentException {
      Vector3D delta = p2.subtract(p1);
      double norm2 = delta.getNormSq();
      if (norm2 == (double)0.0F) {
         throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM, new Object[0]);
      } else {
         this.direction = new Vector3D((double)1.0F / FastMath.sqrt(norm2), delta);
         this.zero = new Vector3D((double)1.0F, p1, -p1.dotProduct(delta) / norm2, delta);
      }
   }

   public double getTolerance() {
      return this.tolerance;
   }

   public Line revert() {
      Line reverted = new Line(this);
      reverted.direction = reverted.direction.negate();
      return reverted;
   }

   public Vector3D getDirection() {
      return this.direction;
   }

   public Vector3D getOrigin() {
      return this.zero;
   }

   public double getAbscissa(Vector3D point) {
      return point.subtract(this.zero).dotProduct(this.direction);
   }

   public Vector3D pointAt(double abscissa) {
      return new Vector3D((double)1.0F, this.zero, abscissa, this.direction);
   }

   public Vector1D toSubSpace(Vector vector) {
      return this.toSubSpace((Point)vector);
   }

   public Vector3D toSpace(Vector vector) {
      return this.toSpace((Point)vector);
   }

   public Vector1D toSubSpace(Point point) {
      return new Vector1D(this.getAbscissa((Vector3D)point));
   }

   public Vector3D toSpace(Point point) {
      return this.pointAt(((Vector1D)point).getX());
   }

   public boolean isSimilarTo(Line line) {
      double angle = Vector3D.angle(this.direction, line.direction);
      return (angle < this.tolerance || angle > Math.PI - this.tolerance) && this.contains(line.zero);
   }

   public boolean contains(Vector3D p) {
      return this.distance(p) < this.tolerance;
   }

   public double distance(Vector3D p) {
      Vector3D d = p.subtract(this.zero);
      Vector3D n = new Vector3D((double)1.0F, d, -d.dotProduct(this.direction), this.direction);
      return n.getNorm();
   }

   public double distance(Line line) {
      Vector3D normal = Vector3D.crossProduct(this.direction, line.direction);
      double n = normal.getNorm();
      if (n < Precision.SAFE_MIN) {
         return this.distance(line.zero);
      } else {
         double offset = line.zero.subtract(this.zero).dotProduct(normal) / n;
         return FastMath.abs(offset);
      }
   }

   public Vector3D closestPoint(Line line) {
      double cos = this.direction.dotProduct(line.direction);
      double n = (double)1.0F - cos * cos;
      if (n < Precision.EPSILON) {
         return this.zero;
      } else {
         Vector3D delta0 = line.zero.subtract(this.zero);
         double a = delta0.dotProduct(this.direction);
         double b = delta0.dotProduct(line.direction);
         return new Vector3D((double)1.0F, this.zero, (a - b * cos) / n, this.direction);
      }
   }

   public Vector3D intersection(Line line) {
      Vector3D closest = this.closestPoint(line);
      return line.contains(closest) ? closest : null;
   }

   public SubLine wholeLine() {
      return new SubLine(this, new IntervalsSet(this.tolerance));
   }
}
