package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailablePredicate {
   FailablePredicate FALSE = (t) -> false;
   FailablePredicate TRUE = (t) -> true;

   static FailablePredicate falsePredicate() {
      return FALSE;
   }

   static FailablePredicate truePredicate() {
      return TRUE;
   }

   default FailablePredicate and(FailablePredicate other) {
      Objects.requireNonNull(other);
      return (t) -> this.test(t) && other.test(t);
   }

   default FailablePredicate negate() {
      return (t) -> !this.test(t);
   }

   default FailablePredicate or(FailablePredicate other) {
      Objects.requireNonNull(other);
      return (t) -> this.test(t) || other.test(t);
   }

   boolean test(Object var1) throws Throwable;
}
