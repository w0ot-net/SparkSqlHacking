package org.apache.commons.math3.complex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class Complex implements FieldElement, Serializable {
   public static final Complex I = new Complex((double)0.0F, (double)1.0F);
   public static final Complex NaN = new Complex(Double.NaN, Double.NaN);
   public static final Complex INF = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
   public static final Complex ONE = new Complex((double)1.0F, (double)0.0F);
   public static final Complex ZERO = new Complex((double)0.0F, (double)0.0F);
   private static final long serialVersionUID = -6195664516687396620L;
   private final double imaginary;
   private final double real;
   private final transient boolean isNaN;
   private final transient boolean isInfinite;

   public Complex(double real) {
      this(real, (double)0.0F);
   }

   public Complex(double real, double imaginary) {
      this.real = real;
      this.imaginary = imaginary;
      this.isNaN = Double.isNaN(real) || Double.isNaN(imaginary);
      this.isInfinite = !this.isNaN && (Double.isInfinite(real) || Double.isInfinite(imaginary));
   }

   public double abs() {
      if (this.isNaN) {
         return Double.NaN;
      } else if (this.isInfinite()) {
         return Double.POSITIVE_INFINITY;
      } else if (FastMath.abs(this.real) < FastMath.abs(this.imaginary)) {
         if (this.imaginary == (double)0.0F) {
            return FastMath.abs(this.real);
         } else {
            double q = this.real / this.imaginary;
            return FastMath.abs(this.imaginary) * FastMath.sqrt((double)1.0F + q * q);
         }
      } else if (this.real == (double)0.0F) {
         return FastMath.abs(this.imaginary);
      } else {
         double q = this.imaginary / this.real;
         return FastMath.abs(this.real) * FastMath.sqrt((double)1.0F + q * q);
      }
   }

   public Complex add(Complex addend) throws NullArgumentException {
      MathUtils.checkNotNull(addend);
      return !this.isNaN && !addend.isNaN ? this.createComplex(this.real + addend.getReal(), this.imaginary + addend.getImaginary()) : NaN;
   }

   public Complex add(double addend) {
      return !this.isNaN && !Double.isNaN(addend) ? this.createComplex(this.real + addend, this.imaginary) : NaN;
   }

   public Complex conjugate() {
      return this.isNaN ? NaN : this.createComplex(this.real, -this.imaginary);
   }

   public Complex divide(Complex divisor) throws NullArgumentException {
      MathUtils.checkNotNull(divisor);
      if (!this.isNaN && !divisor.isNaN) {
         double c = divisor.getReal();
         double d = divisor.getImaginary();
         if (c == (double)0.0F && d == (double)0.0F) {
            return NaN;
         } else if (divisor.isInfinite() && !this.isInfinite()) {
            return ZERO;
         } else if (FastMath.abs(c) < FastMath.abs(d)) {
            double q = c / d;
            double denominator = c * q + d;
            return this.createComplex((this.real * q + this.imaginary) / denominator, (this.imaginary * q - this.real) / denominator);
         } else {
            double q = d / c;
            double denominator = d * q + c;
            return this.createComplex((this.imaginary * q + this.real) / denominator, (this.imaginary - this.real * q) / denominator);
         }
      } else {
         return NaN;
      }
   }

   public Complex divide(double divisor) {
      if (!this.isNaN && !Double.isNaN(divisor)) {
         if (divisor == (double)0.0F) {
            return NaN;
         } else if (Double.isInfinite(divisor)) {
            return !this.isInfinite() ? ZERO : NaN;
         } else {
            return this.createComplex(this.real / divisor, this.imaginary / divisor);
         }
      } else {
         return NaN;
      }
   }

   public Complex reciprocal() {
      if (this.isNaN) {
         return NaN;
      } else if (this.real == (double)0.0F && this.imaginary == (double)0.0F) {
         return INF;
      } else if (this.isInfinite) {
         return ZERO;
      } else if (FastMath.abs(this.real) < FastMath.abs(this.imaginary)) {
         double q = this.real / this.imaginary;
         double scale = (double)1.0F / (this.real * q + this.imaginary);
         return this.createComplex(scale * q, -scale);
      } else {
         double q = this.imaginary / this.real;
         double scale = (double)1.0F / (this.imaginary * q + this.real);
         return this.createComplex(scale, -scale * q);
      }
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other instanceof Complex) {
         Complex c = (Complex)other;
         if (c.isNaN) {
            return this.isNaN;
         } else {
            return MathUtils.equals(this.real, c.real) && MathUtils.equals(this.imaginary, c.imaginary);
         }
      } else {
         return false;
      }
   }

   public static boolean equals(Complex x, Complex y, int maxUlps) {
      return Precision.equals(x.real, y.real, maxUlps) && Precision.equals(x.imaginary, y.imaginary, maxUlps);
   }

   public static boolean equals(Complex x, Complex y) {
      return equals(x, y, 1);
   }

   public static boolean equals(Complex x, Complex y, double eps) {
      return Precision.equals(x.real, y.real, eps) && Precision.equals(x.imaginary, y.imaginary, eps);
   }

   public static boolean equalsWithRelativeTolerance(Complex x, Complex y, double eps) {
      return Precision.equalsWithRelativeTolerance(x.real, y.real, eps) && Precision.equalsWithRelativeTolerance(x.imaginary, y.imaginary, eps);
   }

   public int hashCode() {
      return this.isNaN ? 7 : 37 * (17 * MathUtils.hash(this.imaginary) + MathUtils.hash(this.real));
   }

   public double getImaginary() {
      return this.imaginary;
   }

   public double getReal() {
      return this.real;
   }

   public boolean isNaN() {
      return this.isNaN;
   }

   public boolean isInfinite() {
      return this.isInfinite;
   }

   public Complex multiply(Complex factor) throws NullArgumentException {
      MathUtils.checkNotNull(factor);
      if (!this.isNaN && !factor.isNaN) {
         return !Double.isInfinite(this.real) && !Double.isInfinite(this.imaginary) && !Double.isInfinite(factor.real) && !Double.isInfinite(factor.imaginary) ? this.createComplex(this.real * factor.real - this.imaginary * factor.imaginary, this.real * factor.imaginary + this.imaginary * factor.real) : INF;
      } else {
         return NaN;
      }
   }

   public Complex multiply(int factor) {
      if (this.isNaN) {
         return NaN;
      } else {
         return !Double.isInfinite(this.real) && !Double.isInfinite(this.imaginary) ? this.createComplex(this.real * (double)factor, this.imaginary * (double)factor) : INF;
      }
   }

   public Complex multiply(double factor) {
      if (!this.isNaN && !Double.isNaN(factor)) {
         return !Double.isInfinite(this.real) && !Double.isInfinite(this.imaginary) && !Double.isInfinite(factor) ? this.createComplex(this.real * factor, this.imaginary * factor) : INF;
      } else {
         return NaN;
      }
   }

   public Complex negate() {
      return this.isNaN ? NaN : this.createComplex(-this.real, -this.imaginary);
   }

   public Complex subtract(Complex subtrahend) throws NullArgumentException {
      MathUtils.checkNotNull(subtrahend);
      return !this.isNaN && !subtrahend.isNaN ? this.createComplex(this.real - subtrahend.getReal(), this.imaginary - subtrahend.getImaginary()) : NaN;
   }

   public Complex subtract(double subtrahend) {
      return !this.isNaN && !Double.isNaN(subtrahend) ? this.createComplex(this.real - subtrahend, this.imaginary) : NaN;
   }

   public Complex acos() {
      return this.isNaN ? NaN : this.add(this.sqrt1z().multiply(I)).log().multiply(I.negate());
   }

   public Complex asin() {
      return this.isNaN ? NaN : this.sqrt1z().add(this.multiply(I)).log().multiply(I.negate());
   }

   public Complex atan() {
      return this.isNaN ? NaN : this.add(I).divide(I.subtract(this)).log().multiply(I.divide(this.createComplex((double)2.0F, (double)0.0F)));
   }

   public Complex cos() {
      return this.isNaN ? NaN : this.createComplex(FastMath.cos(this.real) * FastMath.cosh(this.imaginary), -FastMath.sin(this.real) * FastMath.sinh(this.imaginary));
   }

   public Complex cosh() {
      return this.isNaN ? NaN : this.createComplex(FastMath.cosh(this.real) * FastMath.cos(this.imaginary), FastMath.sinh(this.real) * FastMath.sin(this.imaginary));
   }

   public Complex exp() {
      if (this.isNaN) {
         return NaN;
      } else {
         double expReal = FastMath.exp(this.real);
         return this.createComplex(expReal * FastMath.cos(this.imaginary), expReal * FastMath.sin(this.imaginary));
      }
   }

   public Complex log() {
      return this.isNaN ? NaN : this.createComplex(FastMath.log(this.abs()), FastMath.atan2(this.imaginary, this.real));
   }

   public Complex pow(Complex x) throws NullArgumentException {
      MathUtils.checkNotNull(x);
      return this.log().multiply(x).exp();
   }

   public Complex pow(double x) {
      return this.log().multiply(x).exp();
   }

   public Complex sin() {
      return this.isNaN ? NaN : this.createComplex(FastMath.sin(this.real) * FastMath.cosh(this.imaginary), FastMath.cos(this.real) * FastMath.sinh(this.imaginary));
   }

   public Complex sinh() {
      return this.isNaN ? NaN : this.createComplex(FastMath.sinh(this.real) * FastMath.cos(this.imaginary), FastMath.cosh(this.real) * FastMath.sin(this.imaginary));
   }

   public Complex sqrt() {
      if (this.isNaN) {
         return NaN;
      } else if (this.real == (double)0.0F && this.imaginary == (double)0.0F) {
         return this.createComplex((double)0.0F, (double)0.0F);
      } else {
         double t = FastMath.sqrt((FastMath.abs(this.real) + this.abs()) / (double)2.0F);
         return this.real >= (double)0.0F ? this.createComplex(t, this.imaginary / ((double)2.0F * t)) : this.createComplex(FastMath.abs(this.imaginary) / ((double)2.0F * t), FastMath.copySign((double)1.0F, this.imaginary) * t);
      }
   }

   public Complex sqrt1z() {
      return this.createComplex((double)1.0F, (double)0.0F).subtract(this.multiply(this)).sqrt();
   }

   public Complex tan() {
      if (!this.isNaN && !Double.isInfinite(this.real)) {
         if (this.imaginary > (double)20.0F) {
            return this.createComplex((double)0.0F, (double)1.0F);
         } else if (this.imaginary < (double)-20.0F) {
            return this.createComplex((double)0.0F, (double)-1.0F);
         } else {
            double real2 = (double)2.0F * this.real;
            double imaginary2 = (double)2.0F * this.imaginary;
            double d = FastMath.cos(real2) + FastMath.cosh(imaginary2);
            return this.createComplex(FastMath.sin(real2) / d, FastMath.sinh(imaginary2) / d);
         }
      } else {
         return NaN;
      }
   }

   public Complex tanh() {
      if (!this.isNaN && !Double.isInfinite(this.imaginary)) {
         if (this.real > (double)20.0F) {
            return this.createComplex((double)1.0F, (double)0.0F);
         } else if (this.real < (double)-20.0F) {
            return this.createComplex((double)-1.0F, (double)0.0F);
         } else {
            double real2 = (double)2.0F * this.real;
            double imaginary2 = (double)2.0F * this.imaginary;
            double d = FastMath.cosh(real2) + FastMath.cos(imaginary2);
            return this.createComplex(FastMath.sinh(real2) / d, FastMath.sin(imaginary2) / d);
         }
      } else {
         return NaN;
      }
   }

   public double getArgument() {
      return FastMath.atan2(this.getImaginary(), this.getReal());
   }

   public List nthRoot(int n) throws NotPositiveException {
      if (n <= 0) {
         throw new NotPositiveException(LocalizedFormats.CANNOT_COMPUTE_NTH_ROOT_FOR_NEGATIVE_N, n);
      } else {
         List<Complex> result = new ArrayList();
         if (this.isNaN) {
            result.add(NaN);
            return result;
         } else if (this.isInfinite()) {
            result.add(INF);
            return result;
         } else {
            double nthRootOfAbs = FastMath.pow(this.abs(), (double)1.0F / (double)n);
            double nthPhi = this.getArgument() / (double)n;
            double slice = (Math.PI * 2D) / (double)n;
            double innerPart = nthPhi;

            for(int k = 0; k < n; ++k) {
               double realPart = nthRootOfAbs * FastMath.cos(innerPart);
               double imaginaryPart = nthRootOfAbs * FastMath.sin(innerPart);
               result.add(this.createComplex(realPart, imaginaryPart));
               innerPart += slice;
            }

            return result;
         }
      }
   }

   protected Complex createComplex(double realPart, double imaginaryPart) {
      return new Complex(realPart, imaginaryPart);
   }

   public static Complex valueOf(double realPart, double imaginaryPart) {
      return !Double.isNaN(realPart) && !Double.isNaN(imaginaryPart) ? new Complex(realPart, imaginaryPart) : NaN;
   }

   public static Complex valueOf(double realPart) {
      return Double.isNaN(realPart) ? NaN : new Complex(realPart);
   }

   protected final Object readResolve() {
      return this.createComplex(this.real, this.imaginary);
   }

   public ComplexField getField() {
      return ComplexField.getInstance();
   }

   public String toString() {
      return "(" + this.real + ", " + this.imaginary + ")";
   }
}
