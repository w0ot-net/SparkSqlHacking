package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableLongPredicate {
   FailableLongPredicate FALSE = (t) -> false;
   FailableLongPredicate TRUE = (t) -> true;

   static FailableLongPredicate falsePredicate() {
      return FALSE;
   }

   static FailableLongPredicate truePredicate() {
      return TRUE;
   }

   default FailableLongPredicate and(FailableLongPredicate other) {
      Objects.requireNonNull(other);
      return (t) -> this.test(t) && other.test(t);
   }

   default FailableLongPredicate negate() {
      return (t) -> !this.test(t);
   }

   default FailableLongPredicate or(FailableLongPredicate other) {
      Objects.requireNonNull(other);
      return (t) -> this.test(t) || other.test(t);
   }

   boolean test(long var1) throws Throwable;
}
