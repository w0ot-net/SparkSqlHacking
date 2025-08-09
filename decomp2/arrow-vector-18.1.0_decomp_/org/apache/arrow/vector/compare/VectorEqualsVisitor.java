package org.apache.arrow.vector.compare;

import java.util.function.BiFunction;
import org.apache.arrow.vector.ValueVector;

public class VectorEqualsVisitor {
   public static boolean vectorEquals(ValueVector left, ValueVector right) {
      return vectorEquals(left, right, RangeEqualsVisitor.DEFAULT_TYPE_COMPARATOR);
   }

   public static boolean vectorEquals(ValueVector left, ValueVector right, BiFunction typeComparator) {
      if (left.getValueCount() != right.getValueCount()) {
         return false;
      } else {
         RangeEqualsVisitor visitor = new RangeEqualsVisitor(left, right, typeComparator);
         return visitor.rangeEquals(new Range(0, 0, left.getValueCount()));
      }
   }
}
