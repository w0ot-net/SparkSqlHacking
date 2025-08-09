package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableIntPredicate {
   FailableIntPredicate FALSE = (t) -> false;
   FailableIntPredicate TRUE = (t) -> true;

   static FailableIntPredicate falsePredicate() {
      return FALSE;
   }

   static FailableIntPredicate truePredicate() {
      return TRUE;
   }

   default FailableIntPredicate and(FailableIntPredicate other) {
      Objects.requireNonNull(other);
      return (t) -> this.test(t) && other.test(t);
   }

   default FailableIntPredicate negate() {
      return (t) -> !this.test(t);
   }

   default FailableIntPredicate or(FailableIntPredicate other) {
      Objects.requireNonNull(other);
      return (t) -> this.test(t) || other.test(t);
   }

   boolean test(int var1) throws Throwable;
}
