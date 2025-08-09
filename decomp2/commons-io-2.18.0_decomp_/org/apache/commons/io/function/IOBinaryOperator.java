package org.apache.commons.io.function;

import java.util.Objects;
import java.util.function.BinaryOperator;

@FunctionalInterface
public interface IOBinaryOperator extends IOBiFunction {
   static IOBinaryOperator maxBy(IOComparator comparator) {
      Objects.requireNonNull(comparator);
      return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
   }

   static IOBinaryOperator minBy(IOComparator comparator) {
      Objects.requireNonNull(comparator);
      return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
   }

   default BinaryOperator asBinaryOperator() {
      return (t, u) -> Uncheck.apply(this, t, u);
   }
}
