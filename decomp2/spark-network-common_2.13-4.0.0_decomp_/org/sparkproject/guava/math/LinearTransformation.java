package org.sparkproject.guava.math;

import com.google.errorprone.annotations.concurrent.LazyInit;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public abstract class LinearTransformation {
   public static LinearTransformationBuilder mapping(double x1, double y1) {
      Preconditions.checkArgument(DoubleUtils.isFinite(x1) && DoubleUtils.isFinite(y1));
      return new LinearTransformationBuilder(x1, y1);
   }

   public static LinearTransformation vertical(double x) {
      Preconditions.checkArgument(DoubleUtils.isFinite(x));
      return new VerticalLinearTransformation(x);
   }

   public static LinearTransformation horizontal(double y) {
      Preconditions.checkArgument(DoubleUtils.isFinite(y));
      double slope = (double)0.0F;
      return new RegularLinearTransformation(slope, y);
   }

   public static LinearTransformation forNaN() {
      return LinearTransformation.NaNLinearTransformation.INSTANCE;
   }

   public abstract boolean isVertical();

   public abstract boolean isHorizontal();

   public abstract double slope();

   public abstract double transform(double x);

   public abstract LinearTransformation inverse();

   public static final class LinearTransformationBuilder {
      private final double x1;
      private final double y1;

      private LinearTransformationBuilder(double x1, double y1) {
         this.x1 = x1;
         this.y1 = y1;
      }

      public LinearTransformation and(double x2, double y2) {
         Preconditions.checkArgument(DoubleUtils.isFinite(x2) && DoubleUtils.isFinite(y2));
         if (x2 == this.x1) {
            Preconditions.checkArgument(y2 != this.y1);
            return new VerticalLinearTransformation(this.x1);
         } else {
            return this.withSlope((y2 - this.y1) / (x2 - this.x1));
         }
      }

      public LinearTransformation withSlope(double slope) {
         Preconditions.checkArgument(!Double.isNaN(slope));
         if (DoubleUtils.isFinite(slope)) {
            double yIntercept = this.y1 - this.x1 * slope;
            return new RegularLinearTransformation(slope, yIntercept);
         } else {
            return new VerticalLinearTransformation(this.x1);
         }
      }
   }

   private static final class RegularLinearTransformation extends LinearTransformation {
      final double slope;
      final double yIntercept;
      @CheckForNull
      @LazyInit
      LinearTransformation inverse;

      RegularLinearTransformation(double slope, double yIntercept) {
         this.slope = slope;
         this.yIntercept = yIntercept;
         this.inverse = null;
      }

      RegularLinearTransformation(double slope, double yIntercept, LinearTransformation inverse) {
         this.slope = slope;
         this.yIntercept = yIntercept;
         this.inverse = inverse;
      }

      public boolean isVertical() {
         return false;
      }

      public boolean isHorizontal() {
         return this.slope == (double)0.0F;
      }

      public double slope() {
         return this.slope;
      }

      public double transform(double x) {
         return x * this.slope + this.yIntercept;
      }

      public LinearTransformation inverse() {
         LinearTransformation result = this.inverse;
         return result == null ? (this.inverse = this.createInverse()) : result;
      }

      public String toString() {
         return String.format("y = %g * x + %g", this.slope, this.yIntercept);
      }

      private LinearTransformation createInverse() {
         return (LinearTransformation)(this.slope != (double)0.0F ? new RegularLinearTransformation((double)1.0F / this.slope, (double)-1.0F * this.yIntercept / this.slope, this) : new VerticalLinearTransformation(this.yIntercept, this));
      }
   }

   private static final class VerticalLinearTransformation extends LinearTransformation {
      final double x;
      @CheckForNull
      @LazyInit
      LinearTransformation inverse;

      VerticalLinearTransformation(double x) {
         this.x = x;
         this.inverse = null;
      }

      VerticalLinearTransformation(double x, LinearTransformation inverse) {
         this.x = x;
         this.inverse = inverse;
      }

      public boolean isVertical() {
         return true;
      }

      public boolean isHorizontal() {
         return false;
      }

      public double slope() {
         throw new IllegalStateException();
      }

      public double transform(double x) {
         throw new IllegalStateException();
      }

      public LinearTransformation inverse() {
         LinearTransformation result = this.inverse;
         return result == null ? (this.inverse = this.createInverse()) : result;
      }

      public String toString() {
         return String.format("x = %g", this.x);
      }

      private LinearTransformation createInverse() {
         return new RegularLinearTransformation((double)0.0F, this.x, this);
      }
   }

   private static final class NaNLinearTransformation extends LinearTransformation {
      static final NaNLinearTransformation INSTANCE = new NaNLinearTransformation();

      public boolean isVertical() {
         return false;
      }

      public boolean isHorizontal() {
         return false;
      }

      public double slope() {
         return Double.NaN;
      }

      public double transform(double x) {
         return Double.NaN;
      }

      public LinearTransformation inverse() {
         return this;
      }

      public String toString() {
         return "NaN";
      }
   }
}
