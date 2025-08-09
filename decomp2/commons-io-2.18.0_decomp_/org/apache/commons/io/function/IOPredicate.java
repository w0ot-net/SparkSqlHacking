package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Predicate;

@FunctionalInterface
public interface IOPredicate {
   static IOPredicate alwaysFalse() {
      return Constants.IO_PREDICATE_FALSE;
   }

   static IOPredicate alwaysTrue() {
      return Constants.IO_PREDICATE_TRUE;
   }

   static IOPredicate isEqual(Object target) {
      return null == target ? Objects::isNull : (object) -> target.equals(object);
   }

   default IOPredicate and(IOPredicate other) {
      Objects.requireNonNull(other);
      return (t) -> this.test(t) && other.test(t);
   }

   default Predicate asPredicate() {
      return (t) -> Uncheck.test(this, t);
   }

   default IOPredicate negate() {
      return (t) -> !this.test(t);
   }

   default IOPredicate or(IOPredicate other) {
      Objects.requireNonNull(other);
      return (t) -> this.test(t) || other.test(t);
   }

   boolean test(Object var1) throws IOException;
}
