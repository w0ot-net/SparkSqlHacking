package org.apache.commons.math3.geometry.spherical.twod;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Transform;
import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.apache.commons.math3.geometry.spherical.oned.ArcsSet;
import org.apache.commons.math3.geometry.spherical.oned.S1Point;
import org.apache.commons.math3.geometry.spherical.oned.Sphere1D;
import org.apache.commons.math3.util.FastMath;

public class Circle implements Hyperplane, Embedding {
   private Vector3D pole;
   private Vector3D x;
   private Vector3D y;
   private final double tolerance;

   public Circle(Vector3D pole, double tolerance) {
      this.reset(pole);
      this.tolerance = tolerance;
   }

   public Circle(S2Point first, S2Point second, double tolerance) {
      this.reset(first.getVector().crossProduct(second.getVector()));
      this.tolerance = tolerance;
   }

   private Circle(Vector3D pole, Vector3D x, Vector3D y, double tolerance) {
      this.pole = pole;
      this.x = x;
      this.y = y;
      this.tolerance = tolerance;
   }

   public Circle(Circle circle) {
      this(circle.pole, circle.x, circle.y, circle.tolerance);
   }

   public Circle copySelf() {
      return new Circle(this);
   }

   public void reset(Vector3D newPole) {
      this.pole = newPole.normalize();
      this.x = newPole.orthogonal();
      this.y = Vector3D.crossProduct(newPole, this.x).normalize();
   }

   public void revertSelf() {
      this.y = this.y.negate();
      this.pole = this.pole.negate();
   }

   public Circle getReverse() {
      return new Circle(this.pole.negate(), this.x, this.y.negate(), this.tolerance);
   }

   public Point project(Point point) {
      return this.toSpace(this.toSubSpace(point));
   }

   public double getTolerance() {
      return this.tolerance;
   }

   public S1Point toSubSpace(Point point) {
      return new S1Point(this.getPhase(((S2Point)point).getVector()));
   }

   public double getPhase(Vector3D direction) {
      return Math.PI + FastMath.atan2(-direction.dotProduct(this.y), -direction.dotProduct(this.x));
   }

   public S2Point toSpace(Point point) {
      return new S2Point(this.getPointAt(((S1Point)point).getAlpha()));
   }

   public Vector3D getPointAt(double alpha) {
      return new Vector3D(FastMath.cos(alpha), this.x, FastMath.sin(alpha), this.y);
   }

   public Vector3D getXAxis() {
      return this.x;
   }

   public Vector3D getYAxis() {
      return this.y;
   }

   public Vector3D getPole() {
      return this.pole;
   }

   public Arc getInsideArc(Circle other) {
      double alpha = this.getPhase(other.pole);
      double halfPi = (Math.PI / 2D);
      return new Arc(alpha - (Math.PI / 2D), alpha + (Math.PI / 2D), this.tolerance);
   }

   public SubCircle wholeHyperplane() {
      return new SubCircle(this, new ArcsSet(this.tolerance));
   }

   public SphericalPolygonsSet wholeSpace() {
      return new SphericalPolygonsSet(this.tolerance);
   }

   public double getOffset(Point point) {
      return this.getOffset(((S2Point)point).getVector());
   }

   public double getOffset(Vector3D direction) {
      return Vector3D.angle(this.pole, direction) - (Math.PI / 2D);
   }

   public boolean sameOrientationAs(Hyperplane other) {
      Circle otherC = (Circle)other;
      return Vector3D.dotProduct(this.pole, otherC.pole) >= (double)0.0F;
   }

   public static Transform getTransform(Rotation rotation) {
      return new CircleTransform(rotation);
   }

   private static class CircleTransform implements Transform {
      private final Rotation rotation;

      CircleTransform(Rotation rotation) {
         this.rotation = rotation;
      }

      public S2Point apply(Point point) {
         return new S2Point(this.rotation.applyTo(((S2Point)point).getVector()));
      }

      public Circle apply(Hyperplane hyperplane) {
         Circle circle = (Circle)hyperplane;
         return new Circle(this.rotation.applyTo(circle.pole), this.rotation.applyTo(circle.x), this.rotation.applyTo(circle.y), circle.tolerance);
      }

      public SubHyperplane apply(SubHyperplane sub, Hyperplane original, Hyperplane transformed) {
         return sub;
      }
   }
}
