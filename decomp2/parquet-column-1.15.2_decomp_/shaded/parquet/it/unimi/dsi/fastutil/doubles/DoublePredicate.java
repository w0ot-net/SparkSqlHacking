package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.util.Objects;
import java.util.function.Predicate;

@FunctionalInterface
public interface DoublePredicate extends Predicate, java.util.function.DoublePredicate {
   /** @deprecated */
   @Deprecated
   default boolean test(Double t) {
      return this.test(t);
   }

   default DoublePredicate and(java.util.function.DoublePredicate other) {
      Objects.requireNonNull(other);
      return (t) -> this.test(t) && other.test(t);
   }

   default DoublePredicate and(DoublePredicate other) {
      return this.and((java.util.function.DoublePredicate)other);
   }

   /** @deprecated */
   @Deprecated
   default Predicate and(Predicate other) {
      return super.and(other);
   }

   default DoublePredicate negate() {
      return (t) -> !this.test(t);
   }

   default DoublePredicate or(java.util.function.DoublePredicate other) {
      Objects.requireNonNull(other);
      return (t) -> this.test(t) || other.test(t);
   }

   default DoublePredicate or(DoublePredicate other) {
      return this.or((java.util.function.DoublePredicate)other);
   }

   /** @deprecated */
   @Deprecated
   default Predicate or(Predicate other) {
      return super.or(other);
   }
}
