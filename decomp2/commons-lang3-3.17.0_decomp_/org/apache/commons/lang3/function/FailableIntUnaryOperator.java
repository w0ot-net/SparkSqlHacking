package org.apache.commons.lang3.function;

import java.util.Objects;

public interface FailableIntUnaryOperator {
   FailableIntUnaryOperator NOP = (t) -> 0;

   static FailableIntUnaryOperator identity() {
      return (t) -> t;
   }

   static FailableIntUnaryOperator nop() {
      return NOP;
   }

   default FailableIntUnaryOperator andThen(FailableIntUnaryOperator after) {
      Objects.requireNonNull(after);
      return (t) -> after.applyAsInt(this.applyAsInt(t));
   }

   int applyAsInt(int var1) throws Throwable;

   default FailableIntUnaryOperator compose(FailableIntUnaryOperator before) {
      Objects.requireNonNull(before);
      return (v) -> this.applyAsInt(before.applyAsInt(v));
   }
}
