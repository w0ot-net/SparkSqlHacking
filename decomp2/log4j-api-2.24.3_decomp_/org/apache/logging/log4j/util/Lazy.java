package org.apache.logging.log4j.util;

import java.util.Objects;
import java.util.function.Function;

public interface Lazy extends java.util.function.Supplier {
   Object value();

   default Object get() {
      return this.value();
   }

   default Lazy map(final Function function) {
      return lazy(() -> function.apply(this.value()));
   }

   boolean isInitialized();

   void set(final Object newValue);

   static Lazy lazy(final java.util.function.Supplier supplier) {
      Objects.requireNonNull(supplier);
      return new LazyUtil.SafeLazy(supplier);
   }

   static Lazy value(final Object value) {
      return new LazyUtil.Constant(value);
   }

   static Lazy weak(final Object value) {
      return new LazyUtil.WeakConstant(value);
   }

   static Lazy pure(final java.util.function.Supplier supplier) {
      Objects.requireNonNull(supplier);
      return new LazyUtil.PureLazy(supplier);
   }
}
