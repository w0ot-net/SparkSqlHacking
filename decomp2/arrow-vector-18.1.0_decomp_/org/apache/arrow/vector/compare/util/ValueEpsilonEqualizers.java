package org.apache.arrow.vector.compare.util;

import org.apache.arrow.vector.Float4Vector;
import org.apache.arrow.vector.Float8Vector;
import org.apache.arrow.vector.FloatingPointVector;
import org.apache.arrow.vector.compare.VectorValueEqualizer;

public class ValueEpsilonEqualizers {
   private ValueEpsilonEqualizers() {
   }

   public static class FloatingPointEpsilonEqualizer implements VectorValueEqualizer {
      private final double epsilon;

      public FloatingPointEpsilonEqualizer(double epsilon) {
         this.epsilon = epsilon;
      }

      public final boolean valuesEqual(FloatingPointVector vector1, int index1, FloatingPointVector vector2, int index2) {
         boolean isNull1 = vector1.isNull(index1);
         boolean isNull2 = vector2.isNull(index2);
         if (!isNull1 && !isNull2) {
            double d1 = vector1.getValueAsDouble(index1);
            double d2 = vector2.getValueAsDouble(index2);
            if (Double.isNaN(d1)) {
               return Double.isNaN(d2);
            } else if (!Double.isInfinite(d1)) {
               return Math.abs(d1 - d2) <= this.epsilon;
            } else {
               return Double.isInfinite(d2) && Math.signum(d1) == Math.signum(d2);
            }
         } else {
            return isNull1 == isNull2;
         }
      }

      public VectorValueEqualizer clone() {
         return new FloatingPointEpsilonEqualizer(this.epsilon);
      }
   }

   public static class Float4EpsilonEqualizer implements VectorValueEqualizer {
      private final float epsilon;

      public Float4EpsilonEqualizer(float epsilon) {
         this.epsilon = epsilon;
      }

      public final boolean valuesEqual(Float4Vector vector1, int index1, Float4Vector vector2, int index2) {
         boolean isNull1 = vector1.isNull(index1);
         boolean isNull2 = vector2.isNull(index2);
         if (!isNull1 && !isNull2) {
            float f1 = vector1.get(index1);
            float f2 = vector2.get(index2);
            if (Float.isNaN(f1)) {
               return Float.isNaN(f2);
            } else if (!Float.isInfinite(f1)) {
               return Math.abs(f1 - f2) <= this.epsilon;
            } else {
               return Float.isInfinite(f2) && Math.signum(f1) == Math.signum(f2);
            }
         } else {
            return isNull1 == isNull2;
         }
      }

      public VectorValueEqualizer clone() {
         return new Float4EpsilonEqualizer(this.epsilon);
      }
   }

   public static class Float8EpsilonEqualizer implements VectorValueEqualizer {
      private final double epsilon;

      public Float8EpsilonEqualizer(double epsilon) {
         this.epsilon = epsilon;
      }

      public final boolean valuesEqual(Float8Vector vector1, int index1, Float8Vector vector2, int index2) {
         boolean isNull1 = vector1.isNull(index1);
         boolean isNull2 = vector2.isNull(index2);
         if (!isNull1 && !isNull2) {
            double d1 = vector1.get(index1);
            double d2 = vector2.get(index2);
            if (Double.isNaN(d1)) {
               return Double.isNaN(d2);
            } else if (!Double.isInfinite(d1)) {
               return Math.abs(d1 - d2) <= this.epsilon;
            } else {
               return Double.isInfinite(d2) && Math.signum(d1) == Math.signum(d2);
            }
         } else {
            return isNull1 == isNull2;
         }
      }

      public VectorValueEqualizer clone() {
         return new Float8EpsilonEqualizer(this.epsilon);
      }
   }
}
