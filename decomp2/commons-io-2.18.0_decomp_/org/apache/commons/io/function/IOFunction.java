package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
public interface IOFunction {
   static IOFunction identity() {
      return Constants.IO_FUNCTION_ID;
   }

   default IOConsumer andThen(Consumer after) {
      Objects.requireNonNull(after, "after");
      return (t) -> after.accept(this.apply(t));
   }

   default IOFunction andThen(Function after) {
      Objects.requireNonNull(after, "after");
      return (t) -> after.apply(this.apply(t));
   }

   default IOConsumer andThen(IOConsumer after) {
      Objects.requireNonNull(after, "after");
      return (t) -> after.accept(this.apply(t));
   }

   default IOFunction andThen(IOFunction after) {
      Objects.requireNonNull(after, "after");
      return (t) -> after.apply(this.apply(t));
   }

   Object apply(Object var1) throws IOException;

   default Function asFunction() {
      return (t) -> Uncheck.apply(this, t);
   }

   default IOFunction compose(Function before) {
      Objects.requireNonNull(before, "before");
      return (v) -> this.apply(before.apply(v));
   }

   default IOFunction compose(IOFunction before) {
      Objects.requireNonNull(before, "before");
      return (v) -> this.apply(before.apply(v));
   }

   default IOSupplier compose(IOSupplier before) {
      Objects.requireNonNull(before, "before");
      return () -> this.apply(before.get());
   }

   default IOSupplier compose(Supplier before) {
      Objects.requireNonNull(before, "before");
      return () -> this.apply(before.get());
   }
}
