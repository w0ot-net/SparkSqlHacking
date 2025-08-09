package org.apache.commons.math3.geometry.euclidean.oned;

import java.text.NumberFormat;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class Vector1D implements Vector {
   public static final Vector1D ZERO = new Vector1D((double)0.0F);
   public static final Vector1D ONE = new Vector1D((double)1.0F);
   public static final Vector1D NaN = new Vector1D(Double.NaN);
   public static final Vector1D POSITIVE_INFINITY = new Vector1D(Double.POSITIVE_INFINITY);
   public static final Vector1D NEGATIVE_INFINITY = new Vector1D(Double.NEGATIVE_INFINITY);
   private static final long serialVersionUID = 7556674948671647925L;
   private final double x;

   public Vector1D(double x) {
      this.x = x;
   }

   public Vector1D(double a, Vector1D u) {
      this.x = a * u.x;
   }

   public Vector1D(double a1, Vector1D u1, double a2, Vector1D u2) {
      this.x = a1 * u1.x + a2 * u2.x;
   }

   public Vector1D(double a1, Vector1D u1, double a2, Vector1D u2, double a3, Vector1D u3) {
      this.x = a1 * u1.x + a2 * u2.x + a3 * u3.x;
   }

   public Vector1D(double a1, Vector1D u1, double a2, Vector1D u2, double a3, Vector1D u3, double a4, Vector1D u4) {
      this.x = a1 * u1.x + a2 * u2.x + a3 * u3.x + a4 * u4.x;
   }

   public double getX() {
      return this.x;
   }

   public Space getSpace() {
      return Euclidean1D.getInstance();
   }

   public Vector1D getZero() {
      return ZERO;
   }

   public double getNorm1() {
      return FastMath.abs(this.x);
   }

   public double getNorm() {
      return FastMath.abs(this.x);
   }

   public double getNormSq() {
      return this.x * this.x;
   }

   public double getNormInf() {
      return FastMath.abs(this.x);
   }

   public Vector1D add(Vector v) {
      Vector1D v1 = (Vector1D)v;
      return new Vector1D(this.x + v1.getX());
   }

   public Vector1D add(double factor, Vector v) {
      Vector1D v1 = (Vector1D)v;
      return new Vector1D(this.x + factor * v1.getX());
   }

   public Vector1D subtract(Vector p) {
      Vector1D p3 = (Vector1D)p;
      return new Vector1D(this.x - p3.x);
   }

   public Vector1D subtract(double factor, Vector v) {
      Vector1D v1 = (Vector1D)v;
      return new Vector1D(this.x - factor * v1.getX());
   }

   public Vector1D normalize() throws MathArithmeticException {
      double s = this.getNorm();
      if (s == (double)0.0F) {
         throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
      } else {
         return this.scalarMultiply((double)1.0F / s);
      }
   }

   public Vector1D negate() {
      return new Vector1D(-this.x);
   }

   public Vector1D scalarMultiply(double a) {
      return new Vector1D(a * this.x);
   }

   public boolean isNaN() {
      return Double.isNaN(this.x);
   }

   public boolean isInfinite() {
      return !this.isNaN() && Double.isInfinite(this.x);
   }

   public double distance1(Vector p) {
      Vector1D p3 = (Vector1D)p;
      double dx = FastMath.abs(p3.x - this.x);
      return dx;
   }

   /** @deprecated */
   @Deprecated
   public double distance(Vector p) {
      return this.distance((Point)p);
   }

   public double distance(Point p) {
      Vector1D p3 = (Vector1D)p;
      double dx = p3.x - this.x;
      return FastMath.abs(dx);
   }

   public double distanceInf(Vector p) {
      Vector1D p3 = (Vector1D)p;
      double dx = FastMath.abs(p3.x - this.x);
      return dx;
   }

   public double distanceSq(Vector p) {
      Vector1D p3 = (Vector1D)p;
      double dx = p3.x - this.x;
      return dx * dx;
   }

   public double dotProduct(Vector v) {
      Vector1D v1 = (Vector1D)v;
      return this.x * v1.x;
   }

   public static double distance(Vector1D p1, Vector1D p2) {
      return p1.distance((Vector)p2);
   }

   public static double distanceInf(Vector1D p1, Vector1D p2) {
      return p1.distanceInf(p2);
   }

   public static double distanceSq(Vector1D p1, Vector1D p2) {
      return p1.distanceSq(p2);
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other instanceof Vector1D) {
         Vector1D rhs = (Vector1D)other;
         if (rhs.isNaN()) {
            return this.isNaN();
         } else {
            return this.x == rhs.x;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.isNaN() ? 7785 : 997 * MathUtils.hash(this.x);
   }

   public String toString() {
      return Vector1DFormat.getInstance().format(this);
   }

   public String toString(NumberFormat format) {
      return (new Vector1DFormat(format)).format(this);
   }
}
