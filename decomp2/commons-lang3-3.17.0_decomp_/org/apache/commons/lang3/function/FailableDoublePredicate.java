package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableDoublePredicate {
   FailableDoublePredicate FALSE = (t) -> false;
   FailableDoublePredicate TRUE = (t) -> true;

   static FailableDoublePredicate falsePredicate() {
      return FALSE;
   }

   static FailableDoublePredicate truePredicate() {
      return TRUE;
   }

   default FailableDoublePredicate and(FailableDoublePredicate other) {
      Objects.requireNonNull(other);
      return (t) -> this.test(t) && other.test(t);
   }

   default FailableDoublePredicate negate() {
      return (t) -> !this.test(t);
   }

   default FailableDoublePredicate or(FailableDoublePredicate other) {
      Objects.requireNonNull(other);
      return (t) -> this.test(t) || other.test(t);
   }

   boolean test(double var1) throws Throwable;
}
