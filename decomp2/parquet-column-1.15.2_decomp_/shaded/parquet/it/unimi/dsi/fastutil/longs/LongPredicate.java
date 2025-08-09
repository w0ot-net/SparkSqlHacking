package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.util.Objects;
import java.util.function.Predicate;

@FunctionalInterface
public interface LongPredicate extends Predicate, java.util.function.LongPredicate {
   /** @deprecated */
   @Deprecated
   default boolean test(Long t) {
      return this.test(t);
   }

   default LongPredicate and(java.util.function.LongPredicate other) {
      Objects.requireNonNull(other);
      return (t) -> this.test(t) && other.test(t);
   }

   default LongPredicate and(LongPredicate other) {
      return this.and((java.util.function.LongPredicate)other);
   }

   /** @deprecated */
   @Deprecated
   default Predicate and(Predicate other) {
      return super.and(other);
   }

   default LongPredicate negate() {
      return (t) -> !this.test(t);
   }

   default LongPredicate or(java.util.function.LongPredicate other) {
      Objects.requireNonNull(other);
      return (t) -> this.test(t) || other.test(t);
   }

   default LongPredicate or(LongPredicate other) {
      return this.or((java.util.function.LongPredicate)other);
   }

   /** @deprecated */
   @Deprecated
   default Predicate or(Predicate other) {
      return super.or(other);
   }
}
