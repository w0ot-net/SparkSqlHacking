package net.razorvine.pickle.objects;

import java.io.Serializable;

public class ComplexNumber implements Serializable {
   private static final long serialVersionUID = 4668080260997226513L;
   private final double r;
   private final double i;

   public ComplexNumber(double rr, double ii) {
      this.r = rr;
      this.i = ii;
   }

   public ComplexNumber(Double rr, Double ii) {
      this.r = rr;
      this.i = ii;
   }

   public String toString() {
      StringBuilder sb = (new StringBuilder()).append(this.r);
      if (this.i >= (double)0.0F) {
         sb.append('+');
      }

      return sb.append(this.i).append('i').toString();
   }

   public double getReal() {
      return this.r;
   }

   public double getImaginary() {
      return this.i;
   }

   public double magnitude() {
      return Math.sqrt(this.r * this.r + this.i * this.i);
   }

   public ComplexNumber add(ComplexNumber other) {
      return add(this, other);
   }

   public static ComplexNumber add(ComplexNumber c1, ComplexNumber c2) {
      return new ComplexNumber(c1.r + c2.r, c1.i + c2.i);
   }

   public ComplexNumber subtract(ComplexNumber other) {
      return subtract(this, other);
   }

   public static ComplexNumber subtract(ComplexNumber c1, ComplexNumber c2) {
      return new ComplexNumber(c1.r - c2.r, c1.i - c2.i);
   }

   public ComplexNumber multiply(ComplexNumber other) {
      return multiply(this, other);
   }

   public static ComplexNumber multiply(ComplexNumber c1, ComplexNumber c2) {
      return new ComplexNumber(c1.r * c2.r - c1.i * c2.i, c1.r * c2.i + c1.i * c2.r);
   }

   public static ComplexNumber divide(ComplexNumber c1, ComplexNumber c2) {
      return new ComplexNumber((c1.r * c2.r + c1.i * c2.i) / (c2.r * c2.r + c2.i * c2.i), (c1.i * c2.r - c1.r * c2.i) / (c2.r * c2.r + c2.i * c2.i));
   }

   public boolean equals(Object o) {
      if (!(o instanceof ComplexNumber)) {
         return false;
      } else {
         ComplexNumber other = (ComplexNumber)o;
         return this.r == other.r && this.i == other.i;
      }
   }

   public int hashCode() {
      return Double.valueOf(this.r).hashCode() ^ Double.valueOf(this.i).hashCode();
   }
}
