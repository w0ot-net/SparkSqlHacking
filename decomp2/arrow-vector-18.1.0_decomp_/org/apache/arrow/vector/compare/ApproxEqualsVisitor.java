package org.apache.arrow.vector.compare;

import java.util.function.BiFunction;
import org.apache.arrow.vector.BaseFixedWidthVector;
import org.apache.arrow.vector.Float4Vector;
import org.apache.arrow.vector.Float8Vector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.compare.util.ValueEpsilonEqualizers;

public class ApproxEqualsVisitor extends RangeEqualsVisitor {
   private final VectorValueEqualizer floatDiffFunction;
   private final VectorValueEqualizer doubleDiffFunction;
   public static final float DEFAULT_FLOAT_EPSILON = 1.0E-6F;
   public static final double DEFAULT_DOUBLE_EPSILON = 1.0E-6;

   public ApproxEqualsVisitor(ValueVector left, ValueVector right) {
      this(left, right, 1.0E-6F, 1.0E-6);
   }

   public ApproxEqualsVisitor(ValueVector left, ValueVector right, float floatEpsilon, double doubleEpsilon) {
      this(left, right, new ValueEpsilonEqualizers.Float4EpsilonEqualizer(floatEpsilon), new ValueEpsilonEqualizers.Float8EpsilonEqualizer(doubleEpsilon));
   }

   public ApproxEqualsVisitor(ValueVector left, ValueVector right, VectorValueEqualizer floatDiffFunction, VectorValueEqualizer doubleDiffFunction) {
      this(left, right, floatDiffFunction, doubleDiffFunction, DEFAULT_TYPE_COMPARATOR);
   }

   public ApproxEqualsVisitor(ValueVector left, ValueVector right, VectorValueEqualizer floatDiffFunction, VectorValueEqualizer doubleDiffFunction, BiFunction typeComparator) {
      super(left, right, typeComparator);
      this.floatDiffFunction = floatDiffFunction;
      this.doubleDiffFunction = doubleDiffFunction;
   }

   public Boolean visit(BaseFixedWidthVector left, Range range) {
      if (left instanceof Float4Vector) {
         return !this.validate(left) ? false : this.float4ApproxEquals(range);
      } else if (left instanceof Float8Vector) {
         return !this.validate(left) ? false : this.float8ApproxEquals(range);
      } else {
         return super.visit(left, range);
      }
   }

   protected ApproxEqualsVisitor createInnerVisitor(ValueVector left, ValueVector right, BiFunction typeComparator) {
      return new ApproxEqualsVisitor(left, right, this.floatDiffFunction.clone(), this.doubleDiffFunction.clone(), typeComparator);
   }

   private boolean float4ApproxEquals(Range range) {
      Float4Vector leftVector = (Float4Vector)this.getLeft();
      Float4Vector rightVector = (Float4Vector)this.getRight();

      for(int i = 0; i < range.getLength(); ++i) {
         int leftIndex = range.getLeftStart() + i;
         int rightIndex = range.getRightStart() + i;
         if (!this.floatDiffFunction.valuesEqual(leftVector, leftIndex, rightVector, rightIndex)) {
            return false;
         }
      }

      return true;
   }

   private boolean float8ApproxEquals(Range range) {
      Float8Vector leftVector = (Float8Vector)this.getLeft();
      Float8Vector rightVector = (Float8Vector)this.getRight();

      for(int i = 0; i < range.getLength(); ++i) {
         int leftIndex = range.getLeftStart() + i;
         int rightIndex = range.getRightStart() + i;
         if (!this.doubleDiffFunction.valuesEqual(leftVector, leftIndex, rightVector, rightIndex)) {
            return false;
         }
      }

      return true;
   }
}
