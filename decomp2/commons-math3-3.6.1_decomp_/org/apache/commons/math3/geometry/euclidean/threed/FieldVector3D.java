package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import java.text.NumberFormat;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class FieldVector3D implements Serializable {
   private static final long serialVersionUID = 20130224L;
   private final RealFieldElement x;
   private final RealFieldElement y;
   private final RealFieldElement z;

   public FieldVector3D(RealFieldElement x, RealFieldElement y, RealFieldElement z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public FieldVector3D(RealFieldElement[] v) throws DimensionMismatchException {
      if (v.length != 3) {
         throw new DimensionMismatchException(v.length, 3);
      } else {
         this.x = v[0];
         this.y = v[1];
         this.z = v[2];
      }
   }

   public FieldVector3D(RealFieldElement alpha, RealFieldElement delta) {
      T cosDelta = (T)((RealFieldElement)delta.cos());
      this.x = (RealFieldElement)((RealFieldElement)alpha.cos()).multiply(cosDelta);
      this.y = (RealFieldElement)((RealFieldElement)alpha.sin()).multiply(cosDelta);
      this.z = (RealFieldElement)delta.sin();
   }

   public FieldVector3D(RealFieldElement a, FieldVector3D u) {
      this.x = (RealFieldElement)a.multiply(u.x);
      this.y = (RealFieldElement)a.multiply(u.y);
      this.z = (RealFieldElement)a.multiply(u.z);
   }

   public FieldVector3D(RealFieldElement a, Vector3D u) {
      this.x = (RealFieldElement)a.multiply(u.getX());
      this.y = (RealFieldElement)a.multiply(u.getY());
      this.z = (RealFieldElement)a.multiply(u.getZ());
   }

   public FieldVector3D(double a, FieldVector3D u) {
      this.x = (RealFieldElement)u.x.multiply(a);
      this.y = (RealFieldElement)u.y.multiply(a);
      this.z = (RealFieldElement)u.z.multiply(a);
   }

   public FieldVector3D(RealFieldElement a1, FieldVector3D u1, RealFieldElement a2, FieldVector3D u2) {
      this.x = (RealFieldElement)a1.linearCombination(a1, u1.getX(), a2, u2.getX());
      this.y = (RealFieldElement)a1.linearCombination(a1, u1.getY(), a2, u2.getY());
      this.z = (RealFieldElement)a1.linearCombination(a1, u1.getZ(), a2, u2.getZ());
   }

   public FieldVector3D(RealFieldElement a1, Vector3D u1, RealFieldElement a2, Vector3D u2) {
      this.x = (RealFieldElement)a1.linearCombination(u1.getX(), a1, u2.getX(), a2);
      this.y = (RealFieldElement)a1.linearCombination(u1.getY(), a1, u2.getY(), a2);
      this.z = (RealFieldElement)a1.linearCombination(u1.getZ(), a1, u2.getZ(), a2);
   }

   public FieldVector3D(double a1, FieldVector3D u1, double a2, FieldVector3D u2) {
      T prototype = (T)u1.getX();
      this.x = (RealFieldElement)prototype.linearCombination(a1, u1.getX(), a2, u2.getX());
      this.y = (RealFieldElement)prototype.linearCombination(a1, u1.getY(), a2, u2.getY());
      this.z = (RealFieldElement)prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ());
   }

   public FieldVector3D(RealFieldElement a1, FieldVector3D u1, RealFieldElement a2, FieldVector3D u2, RealFieldElement a3, FieldVector3D u3) {
      this.x = (RealFieldElement)a1.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX());
      this.y = (RealFieldElement)a1.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY());
      this.z = (RealFieldElement)a1.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ());
   }

   public FieldVector3D(RealFieldElement a1, Vector3D u1, RealFieldElement a2, Vector3D u2, RealFieldElement a3, Vector3D u3) {
      this.x = (RealFieldElement)a1.linearCombination(u1.getX(), a1, u2.getX(), a2, u3.getX(), a3);
      this.y = (RealFieldElement)a1.linearCombination(u1.getY(), a1, u2.getY(), a2, u3.getY(), a3);
      this.z = (RealFieldElement)a1.linearCombination(u1.getZ(), a1, u2.getZ(), a2, u3.getZ(), a3);
   }

   public FieldVector3D(double a1, FieldVector3D u1, double a2, FieldVector3D u2, double a3, FieldVector3D u3) {
      T prototype = (T)u1.getX();
      this.x = (RealFieldElement)prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX());
      this.y = (RealFieldElement)prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY());
      this.z = (RealFieldElement)prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ());
   }

   public FieldVector3D(RealFieldElement a1, FieldVector3D u1, RealFieldElement a2, FieldVector3D u2, RealFieldElement a3, FieldVector3D u3, RealFieldElement a4, FieldVector3D u4) {
      this.x = (RealFieldElement)a1.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX(), a4, u4.getX());
      this.y = (RealFieldElement)a1.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY(), a4, u4.getY());
      this.z = (RealFieldElement)a1.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ(), a4, u4.getZ());
   }

   public FieldVector3D(RealFieldElement a1, Vector3D u1, RealFieldElement a2, Vector3D u2, RealFieldElement a3, Vector3D u3, RealFieldElement a4, Vector3D u4) {
      this.x = (RealFieldElement)a1.linearCombination(u1.getX(), a1, u2.getX(), a2, u3.getX(), a3, u4.getX(), a4);
      this.y = (RealFieldElement)a1.linearCombination(u1.getY(), a1, u2.getY(), a2, u3.getY(), a3, u4.getY(), a4);
      this.z = (RealFieldElement)a1.linearCombination(u1.getZ(), a1, u2.getZ(), a2, u3.getZ(), a3, u4.getZ(), a4);
   }

   public FieldVector3D(double a1, FieldVector3D u1, double a2, FieldVector3D u2, double a3, FieldVector3D u3, double a4, FieldVector3D u4) {
      T prototype = (T)u1.getX();
      this.x = (RealFieldElement)prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX(), a4, u4.getX());
      this.y = (RealFieldElement)prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY(), a4, u4.getY());
      this.z = (RealFieldElement)prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ(), a4, u4.getZ());
   }

   public RealFieldElement getX() {
      return this.x;
   }

   public RealFieldElement getY() {
      return this.y;
   }

   public RealFieldElement getZ() {
      return this.z;
   }

   public RealFieldElement[] toArray() {
      T[] array = (T[])((RealFieldElement[])MathArrays.buildArray(this.x.getField(), 3));
      array[0] = this.x;
      array[1] = this.y;
      array[2] = this.z;
      return array;
   }

   public Vector3D toVector3D() {
      return new Vector3D(this.x.getReal(), this.y.getReal(), this.z.getReal());
   }

   public RealFieldElement getNorm1() {
      return (RealFieldElement)((RealFieldElement)((RealFieldElement)this.x.abs()).add(this.y.abs())).add(this.z.abs());
   }

   public RealFieldElement getNorm() {
      return (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.x.multiply(this.x)).add(this.y.multiply(this.y))).add(this.z.multiply(this.z))).sqrt();
   }

   public RealFieldElement getNormSq() {
      return (RealFieldElement)((RealFieldElement)((RealFieldElement)this.x.multiply(this.x)).add(this.y.multiply(this.y))).add(this.z.multiply(this.z));
   }

   public RealFieldElement getNormInf() {
      T xAbs = (T)((RealFieldElement)this.x.abs());
      T yAbs = (T)((RealFieldElement)this.y.abs());
      T zAbs = (T)((RealFieldElement)this.z.abs());
      if (xAbs.getReal() <= yAbs.getReal()) {
         return yAbs.getReal() <= zAbs.getReal() ? zAbs : yAbs;
      } else {
         return xAbs.getReal() <= zAbs.getReal() ? zAbs : xAbs;
      }
   }

   public RealFieldElement getAlpha() {
      return (RealFieldElement)this.y.atan2(this.x);
   }

   public RealFieldElement getDelta() {
      return (RealFieldElement)((RealFieldElement)this.z.divide(this.getNorm())).asin();
   }

   public FieldVector3D add(FieldVector3D v) {
      return new FieldVector3D((RealFieldElement)this.x.add(v.x), (RealFieldElement)this.y.add(v.y), (RealFieldElement)this.z.add(v.z));
   }

   public FieldVector3D add(Vector3D v) {
      return new FieldVector3D((RealFieldElement)this.x.add(v.getX()), (RealFieldElement)this.y.add(v.getY()), (RealFieldElement)this.z.add(v.getZ()));
   }

   public FieldVector3D add(RealFieldElement factor, FieldVector3D v) {
      return new FieldVector3D((RealFieldElement)this.x.getField().getOne(), this, factor, v);
   }

   public FieldVector3D add(RealFieldElement factor, Vector3D v) {
      return new FieldVector3D((RealFieldElement)this.x.add(factor.multiply(v.getX())), (RealFieldElement)this.y.add(factor.multiply(v.getY())), (RealFieldElement)this.z.add(factor.multiply(v.getZ())));
   }

   public FieldVector3D add(double factor, FieldVector3D v) {
      return new FieldVector3D((double)1.0F, this, factor, v);
   }

   public FieldVector3D add(double factor, Vector3D v) {
      return new FieldVector3D((RealFieldElement)this.x.add(factor * v.getX()), (RealFieldElement)this.y.add(factor * v.getY()), (RealFieldElement)this.z.add(factor * v.getZ()));
   }

   public FieldVector3D subtract(FieldVector3D v) {
      return new FieldVector3D((RealFieldElement)this.x.subtract(v.x), (RealFieldElement)this.y.subtract(v.y), (RealFieldElement)this.z.subtract(v.z));
   }

   public FieldVector3D subtract(Vector3D v) {
      return new FieldVector3D((RealFieldElement)this.x.subtract(v.getX()), (RealFieldElement)this.y.subtract(v.getY()), (RealFieldElement)this.z.subtract(v.getZ()));
   }

   public FieldVector3D subtract(RealFieldElement factor, FieldVector3D v) {
      return new FieldVector3D((RealFieldElement)this.x.getField().getOne(), this, (RealFieldElement)factor.negate(), v);
   }

   public FieldVector3D subtract(RealFieldElement factor, Vector3D v) {
      return new FieldVector3D((RealFieldElement)this.x.subtract(factor.multiply(v.getX())), (RealFieldElement)this.y.subtract(factor.multiply(v.getY())), (RealFieldElement)this.z.subtract(factor.multiply(v.getZ())));
   }

   public FieldVector3D subtract(double factor, FieldVector3D v) {
      return new FieldVector3D((double)1.0F, this, -factor, v);
   }

   public FieldVector3D subtract(double factor, Vector3D v) {
      return new FieldVector3D((RealFieldElement)this.x.subtract(factor * v.getX()), (RealFieldElement)this.y.subtract(factor * v.getY()), (RealFieldElement)this.z.subtract(factor * v.getZ()));
   }

   public FieldVector3D normalize() throws MathArithmeticException {
      T s = (T)this.getNorm();
      if (s.getReal() == (double)0.0F) {
         throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
      } else {
         return this.scalarMultiply((RealFieldElement)s.reciprocal());
      }
   }

   public FieldVector3D orthogonal() throws MathArithmeticException {
      double threshold = 0.6 * this.getNorm().getReal();
      if (threshold == (double)0.0F) {
         throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
      } else if (FastMath.abs(this.x.getReal()) <= threshold) {
         T inverse = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.y.multiply(this.y)).add(this.z.multiply(this.z))).sqrt()).reciprocal());
         return new FieldVector3D((RealFieldElement)inverse.getField().getZero(), (RealFieldElement)inverse.multiply(this.z), (RealFieldElement)((RealFieldElement)inverse.multiply(this.y)).negate());
      } else if (FastMath.abs(this.y.getReal()) <= threshold) {
         T inverse = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.x.multiply(this.x)).add(this.z.multiply(this.z))).sqrt()).reciprocal());
         return new FieldVector3D((RealFieldElement)((RealFieldElement)inverse.multiply(this.z)).negate(), (RealFieldElement)inverse.getField().getZero(), (RealFieldElement)inverse.multiply(this.x));
      } else {
         T inverse = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.x.multiply(this.x)).add(this.y.multiply(this.y))).sqrt()).reciprocal());
         return new FieldVector3D((RealFieldElement)inverse.multiply(this.y), (RealFieldElement)((RealFieldElement)inverse.multiply(this.x)).negate(), (RealFieldElement)inverse.getField().getZero());
      }
   }

   public static RealFieldElement angle(FieldVector3D v1, FieldVector3D v2) throws MathArithmeticException {
      T normProduct = (T)((RealFieldElement)v1.getNorm().multiply(v2.getNorm()));
      if (normProduct.getReal() == (double)0.0F) {
         throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
      } else {
         T dot = (T)dotProduct(v1, v2);
         double threshold = normProduct.getReal() * 0.9999;
         if (!(dot.getReal() < -threshold) && !(dot.getReal() > threshold)) {
            return (RealFieldElement)((RealFieldElement)dot.divide(normProduct)).acos();
         } else {
            FieldVector3D<T> v3 = crossProduct(v1, v2);
            return dot.getReal() >= (double)0.0F ? (RealFieldElement)((RealFieldElement)v3.getNorm().divide(normProduct)).asin() : (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)v3.getNorm().divide(normProduct)).asin()).subtract(Math.PI)).negate();
         }
      }
   }

   public static RealFieldElement angle(FieldVector3D v1, Vector3D v2) throws MathArithmeticException {
      T normProduct = (T)((RealFieldElement)v1.getNorm().multiply(v2.getNorm()));
      if (normProduct.getReal() == (double)0.0F) {
         throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
      } else {
         T dot = (T)dotProduct(v1, v2);
         double threshold = normProduct.getReal() * 0.9999;
         if (!(dot.getReal() < -threshold) && !(dot.getReal() > threshold)) {
            return (RealFieldElement)((RealFieldElement)dot.divide(normProduct)).acos();
         } else {
            FieldVector3D<T> v3 = crossProduct(v1, v2);
            return dot.getReal() >= (double)0.0F ? (RealFieldElement)((RealFieldElement)v3.getNorm().divide(normProduct)).asin() : (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)v3.getNorm().divide(normProduct)).asin()).subtract(Math.PI)).negate();
         }
      }
   }

   public static RealFieldElement angle(Vector3D v1, FieldVector3D v2) throws MathArithmeticException {
      return angle(v2, v1);
   }

   public FieldVector3D negate() {
      return new FieldVector3D((RealFieldElement)this.x.negate(), (RealFieldElement)this.y.negate(), (RealFieldElement)this.z.negate());
   }

   public FieldVector3D scalarMultiply(RealFieldElement a) {
      return new FieldVector3D((RealFieldElement)this.x.multiply(a), (RealFieldElement)this.y.multiply(a), (RealFieldElement)this.z.multiply(a));
   }

   public FieldVector3D scalarMultiply(double a) {
      return new FieldVector3D((RealFieldElement)this.x.multiply(a), (RealFieldElement)this.y.multiply(a), (RealFieldElement)this.z.multiply(a));
   }

   public boolean isNaN() {
      return Double.isNaN(this.x.getReal()) || Double.isNaN(this.y.getReal()) || Double.isNaN(this.z.getReal());
   }

   public boolean isInfinite() {
      return !this.isNaN() && (Double.isInfinite(this.x.getReal()) || Double.isInfinite(this.y.getReal()) || Double.isInfinite(this.z.getReal()));
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other instanceof FieldVector3D) {
         FieldVector3D<T> rhs = (FieldVector3D)other;
         if (rhs.isNaN()) {
            return this.isNaN();
         } else {
            return this.x.equals(rhs.x) && this.y.equals(rhs.y) && this.z.equals(rhs.z);
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.isNaN() ? 409 : 311 * (107 * this.x.hashCode() + 83 * this.y.hashCode() + this.z.hashCode());
   }

   public RealFieldElement dotProduct(FieldVector3D v) {
      return (RealFieldElement)this.x.linearCombination(this.x, v.x, this.y, v.y, this.z, v.z);
   }

   public RealFieldElement dotProduct(Vector3D v) {
      return (RealFieldElement)this.x.linearCombination(v.getX(), this.x, v.getY(), this.y, v.getZ(), this.z);
   }

   public FieldVector3D crossProduct(FieldVector3D v) {
      return new FieldVector3D((RealFieldElement)this.x.linearCombination(this.y, v.z, this.z.negate(), v.y), (RealFieldElement)this.y.linearCombination(this.z, v.x, this.x.negate(), v.z), (RealFieldElement)this.z.linearCombination(this.x, v.y, this.y.negate(), v.x));
   }

   public FieldVector3D crossProduct(Vector3D v) {
      return new FieldVector3D((RealFieldElement)this.x.linearCombination(v.getZ(), this.y, -v.getY(), this.z), (RealFieldElement)this.y.linearCombination(v.getX(), this.z, -v.getZ(), this.x), (RealFieldElement)this.z.linearCombination(v.getY(), this.x, -v.getX(), this.y));
   }

   public RealFieldElement distance1(FieldVector3D v) {
      T dx = (T)((RealFieldElement)((RealFieldElement)v.x.subtract(this.x)).abs());
      T dy = (T)((RealFieldElement)((RealFieldElement)v.y.subtract(this.y)).abs());
      T dz = (T)((RealFieldElement)((RealFieldElement)v.z.subtract(this.z)).abs());
      return (RealFieldElement)((RealFieldElement)dx.add(dy)).add(dz);
   }

   public RealFieldElement distance1(Vector3D v) {
      T dx = (T)((RealFieldElement)((RealFieldElement)this.x.subtract(v.getX())).abs());
      T dy = (T)((RealFieldElement)((RealFieldElement)this.y.subtract(v.getY())).abs());
      T dz = (T)((RealFieldElement)((RealFieldElement)this.z.subtract(v.getZ())).abs());
      return (RealFieldElement)((RealFieldElement)dx.add(dy)).add(dz);
   }

   public RealFieldElement distance(FieldVector3D v) {
      T dx = (T)((RealFieldElement)v.x.subtract(this.x));
      T dy = (T)((RealFieldElement)v.y.subtract(this.y));
      T dz = (T)((RealFieldElement)v.z.subtract(this.z));
      return (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)dx.multiply(dx)).add(dy.multiply(dy))).add(dz.multiply(dz))).sqrt();
   }

   public RealFieldElement distance(Vector3D v) {
      T dx = (T)((RealFieldElement)this.x.subtract(v.getX()));
      T dy = (T)((RealFieldElement)this.y.subtract(v.getY()));
      T dz = (T)((RealFieldElement)this.z.subtract(v.getZ()));
      return (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)dx.multiply(dx)).add(dy.multiply(dy))).add(dz.multiply(dz))).sqrt();
   }

   public RealFieldElement distanceInf(FieldVector3D v) {
      T dx = (T)((RealFieldElement)((RealFieldElement)v.x.subtract(this.x)).abs());
      T dy = (T)((RealFieldElement)((RealFieldElement)v.y.subtract(this.y)).abs());
      T dz = (T)((RealFieldElement)((RealFieldElement)v.z.subtract(this.z)).abs());
      if (dx.getReal() <= dy.getReal()) {
         return dy.getReal() <= dz.getReal() ? dz : dy;
      } else {
         return dx.getReal() <= dz.getReal() ? dz : dx;
      }
   }

   public RealFieldElement distanceInf(Vector3D v) {
      T dx = (T)((RealFieldElement)((RealFieldElement)this.x.subtract(v.getX())).abs());
      T dy = (T)((RealFieldElement)((RealFieldElement)this.y.subtract(v.getY())).abs());
      T dz = (T)((RealFieldElement)((RealFieldElement)this.z.subtract(v.getZ())).abs());
      if (dx.getReal() <= dy.getReal()) {
         return dy.getReal() <= dz.getReal() ? dz : dy;
      } else {
         return dx.getReal() <= dz.getReal() ? dz : dx;
      }
   }

   public RealFieldElement distanceSq(FieldVector3D v) {
      T dx = (T)((RealFieldElement)v.x.subtract(this.x));
      T dy = (T)((RealFieldElement)v.y.subtract(this.y));
      T dz = (T)((RealFieldElement)v.z.subtract(this.z));
      return (RealFieldElement)((RealFieldElement)((RealFieldElement)dx.multiply(dx)).add(dy.multiply(dy))).add(dz.multiply(dz));
   }

   public RealFieldElement distanceSq(Vector3D v) {
      T dx = (T)((RealFieldElement)this.x.subtract(v.getX()));
      T dy = (T)((RealFieldElement)this.y.subtract(v.getY()));
      T dz = (T)((RealFieldElement)this.z.subtract(v.getZ()));
      return (RealFieldElement)((RealFieldElement)((RealFieldElement)dx.multiply(dx)).add(dy.multiply(dy))).add(dz.multiply(dz));
   }

   public static RealFieldElement dotProduct(FieldVector3D v1, FieldVector3D v2) {
      return v1.dotProduct(v2);
   }

   public static RealFieldElement dotProduct(FieldVector3D v1, Vector3D v2) {
      return v1.dotProduct(v2);
   }

   public static RealFieldElement dotProduct(Vector3D v1, FieldVector3D v2) {
      return v2.dotProduct(v1);
   }

   public static FieldVector3D crossProduct(FieldVector3D v1, FieldVector3D v2) {
      return v1.crossProduct(v2);
   }

   public static FieldVector3D crossProduct(FieldVector3D v1, Vector3D v2) {
      return v1.crossProduct(v2);
   }

   public static FieldVector3D crossProduct(Vector3D v1, FieldVector3D v2) {
      return new FieldVector3D((RealFieldElement)v2.x.linearCombination(v1.getY(), v2.z, -v1.getZ(), v2.y), (RealFieldElement)v2.y.linearCombination(v1.getZ(), v2.x, -v1.getX(), v2.z), (RealFieldElement)v2.z.linearCombination(v1.getX(), v2.y, -v1.getY(), v2.x));
   }

   public static RealFieldElement distance1(FieldVector3D v1, FieldVector3D v2) {
      return v1.distance1(v2);
   }

   public static RealFieldElement distance1(FieldVector3D v1, Vector3D v2) {
      return v1.distance1(v2);
   }

   public static RealFieldElement distance1(Vector3D v1, FieldVector3D v2) {
      return v2.distance1(v1);
   }

   public static RealFieldElement distance(FieldVector3D v1, FieldVector3D v2) {
      return v1.distance(v2);
   }

   public static RealFieldElement distance(FieldVector3D v1, Vector3D v2) {
      return v1.distance(v2);
   }

   public static RealFieldElement distance(Vector3D v1, FieldVector3D v2) {
      return v2.distance(v1);
   }

   public static RealFieldElement distanceInf(FieldVector3D v1, FieldVector3D v2) {
      return v1.distanceInf(v2);
   }

   public static RealFieldElement distanceInf(FieldVector3D v1, Vector3D v2) {
      return v1.distanceInf(v2);
   }

   public static RealFieldElement distanceInf(Vector3D v1, FieldVector3D v2) {
      return v2.distanceInf(v1);
   }

   public static RealFieldElement distanceSq(FieldVector3D v1, FieldVector3D v2) {
      return v1.distanceSq(v2);
   }

   public static RealFieldElement distanceSq(FieldVector3D v1, Vector3D v2) {
      return v1.distanceSq(v2);
   }

   public static RealFieldElement distanceSq(Vector3D v1, FieldVector3D v2) {
      return v2.distanceSq(v1);
   }

   public String toString() {
      return Vector3DFormat.getInstance().format(this.toVector3D());
   }

   public String toString(NumberFormat format) {
      return (new Vector3DFormat(format)).format(this.toVector3D());
   }
}
