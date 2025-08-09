package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
public interface FailableBiPredicate {
   FailableBiPredicate FALSE = (t, u) -> false;
   FailableBiPredicate TRUE = (t, u) -> true;

   static FailableBiPredicate falsePredicate() {
      return FALSE;
   }

   static FailableBiPredicate truePredicate() {
      return TRUE;
   }

   default FailableBiPredicate and(FailableBiPredicate other) {
      Objects.requireNonNull(other);
      return (t, u) -> this.test(t, u) && other.test(t, u);
   }

   default FailableBiPredicate negate() {
      return (t, u) -> !this.test(t, u);
   }

   default FailableBiPredicate or(FailableBiPredicate other) {
      Objects.requireNonNull(other);
      return (t, u) -> this.test(t, u) || other.test(t, u);
   }

   boolean test(Object var1, Object var2) throws Throwable;
}
