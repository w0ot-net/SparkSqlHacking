package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import java.util.Objects;
import java.util.function.Predicate;

@FunctionalInterface
public interface BooleanPredicate extends Predicate {
   boolean test(boolean var1);

   static BooleanPredicate identity() {
      return (b) -> b;
   }

   static BooleanPredicate negation() {
      return (b) -> !b;
   }

   /** @deprecated */
   @Deprecated
   default boolean test(Boolean t) {
      return this.test(t);
   }

   default BooleanPredicate and(BooleanPredicate other) {
      Objects.requireNonNull(other);
      return (t) -> this.test(t) && other.test(t);
   }

   /** @deprecated */
   @Deprecated
   default Predicate and(Predicate other) {
      return super.and(other);
   }

   default BooleanPredicate negate() {
      return (t) -> !this.test(t);
   }

   default BooleanPredicate or(BooleanPredicate other) {
      Objects.requireNonNull(other);
      return (t) -> this.test(t) || other.test(t);
   }

   /** @deprecated */
   @Deprecated
   default Predicate or(Predicate other) {
      return super.or(other);
   }
}
