package org.apache.commons.lang3.function;

import java.util.Objects;

public interface FailableDoubleUnaryOperator {
   FailableDoubleUnaryOperator NOP = (t) -> (double)0.0F;

   static FailableDoubleUnaryOperator identity() {
      return (t) -> t;
   }

   static FailableDoubleUnaryOperator nop() {
      return NOP;
   }

   default FailableDoubleUnaryOperator andThen(FailableDoubleUnaryOperator after) {
      Objects.requireNonNull(after);
      return (t) -> after.applyAsDouble(this.applyAsDouble(t));
   }

   double applyAsDouble(double var1) throws Throwable;

   default FailableDoubleUnaryOperator compose(FailableDoubleUnaryOperator before) {
      Objects.requireNonNull(before);
      return (v) -> this.applyAsDouble(before.applyAsDouble(v));
   }
}
