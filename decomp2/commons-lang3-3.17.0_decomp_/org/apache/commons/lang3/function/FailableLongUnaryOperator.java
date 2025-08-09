package org.apache.commons.lang3.function;

import java.util.Objects;

public interface FailableLongUnaryOperator {
   FailableLongUnaryOperator NOP = (t) -> 0L;

   static FailableLongUnaryOperator identity() {
      return (t) -> t;
   }

   static FailableLongUnaryOperator nop() {
      return NOP;
   }

   default FailableLongUnaryOperator andThen(FailableLongUnaryOperator after) {
      Objects.requireNonNull(after);
      return (t) -> after.applyAsLong(this.applyAsLong(t));
   }

   long applyAsLong(long var1) throws Throwable;

   default FailableLongUnaryOperator compose(FailableLongUnaryOperator before) {
      Objects.requireNonNull(before);
      return (v) -> this.applyAsLong(before.applyAsLong(v));
   }
}
