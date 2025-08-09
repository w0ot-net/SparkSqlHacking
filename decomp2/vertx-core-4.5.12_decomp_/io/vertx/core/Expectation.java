package io.vertx.core;

import java.util.Objects;
import java.util.function.BiFunction;

@FunctionalInterface
public interface Expectation {
   boolean test(Object var1);

   default Expectation and(final Expectation other) {
      Objects.requireNonNull(other);
      return new Expectation() {
         public boolean test(Object value) {
            return Expectation.this.test(value) && other.test(value);
         }

         public Throwable describe(Object value) {
            if (!Expectation.this.test(value)) {
               return Expectation.this.describe(value);
            } else {
               return !other.test(value) ? other.describe(value) : null;
            }
         }
      };
   }

   default Expectation or(final Expectation other) {
      Objects.requireNonNull(other);
      return new Expectation() {
         public boolean test(Object value) {
            return Expectation.this.test(value) || other.test(value);
         }

         public Throwable describe(Object value) {
            if (Expectation.this.test(value)) {
               return null;
            } else {
               return other.test(value) ? null : Expectation.this.describe(value);
            }
         }
      };
   }

   default Throwable describe(Object value) {
      return new VertxException("Unexpected result: " + value, true);
   }

   default Expectation wrappingFailure(BiFunction descriptor) {
      class CustomizedExpectation implements Expectation {
         private final BiFunction descriptor;

         CustomizedExpectation(BiFunction descriptor) {
            this.descriptor = (BiFunction)Objects.requireNonNull(descriptor);
         }

         public boolean test(Object value) {
            return Expectation.this.test(value);
         }

         public Throwable describe(Object value) {
            Throwable err = Expectation.this.describe(value);
            return (Throwable)this.descriptor.apply(value, err);
         }

         public Expectation wrappingFailure(BiFunction descriptor) {
            return new CustomizedExpectation(descriptor);
         }
      }

      return new CustomizedExpectation(descriptor);
   }
}
