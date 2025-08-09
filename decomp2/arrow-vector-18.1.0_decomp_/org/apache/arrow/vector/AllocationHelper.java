package org.apache.arrow.vector;

import org.apache.arrow.vector.complex.RepeatedFixedWidthVectorLike;
import org.apache.arrow.vector.complex.RepeatedVariableWidthVectorLike;

public class AllocationHelper {
   private AllocationHelper() {
   }

   public static void allocate(ValueVector v, int valueCount, int bytesPerValue) {
      allocate(v, valueCount, bytesPerValue, 5);
   }

   public static void allocatePrecomputedChildCount(ValueVector v, int valueCount, int bytesPerValue, int childValCount) {
      if (v instanceof FixedWidthVector) {
         ((FixedWidthVector)v).allocateNew(valueCount);
      } else if (v instanceof VariableWidthVector) {
         ((VariableWidthVector)v).allocateNew((long)(valueCount * bytesPerValue), valueCount);
      } else if (v instanceof RepeatedFixedWidthVectorLike) {
         ((RepeatedFixedWidthVectorLike)v).allocateNew(valueCount, childValCount);
      } else if (v instanceof RepeatedVariableWidthVectorLike) {
         ((RepeatedVariableWidthVectorLike)v).allocateNew(childValCount * bytesPerValue, valueCount, childValCount);
      } else {
         v.allocateNew();
      }

   }

   public static void allocate(ValueVector v, int valueCount, int bytesPerValue, int repeatedPerTop) {
      allocatePrecomputedChildCount(v, valueCount, bytesPerValue, repeatedPerTop * valueCount);
   }

   public static void allocateNew(ValueVector v, int valueCount) {
      if (v instanceof FixedWidthVector) {
         ((FixedWidthVector)v).allocateNew(valueCount);
      } else if (v instanceof VariableWidthVector) {
         ((VariableWidthVector)v).allocateNew(valueCount);
      } else {
         v.allocateNew();
      }

   }
}
