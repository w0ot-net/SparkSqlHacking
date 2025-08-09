package io.vertx.core.spi.context.storage;

import io.vertx.core.Context;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.ContextLocalImpl;
import java.util.function.Function;
import java.util.function.Supplier;

public interface ContextLocal {
   static ContextLocal registerLocal(Class type) {
      return ContextLocalImpl.create(type, Function.identity());
   }

   static ContextLocal registerLocal(Class type, Function duplicator) {
      return ContextLocalImpl.create(type, duplicator);
   }

   default Object get(Context context) {
      return this.get(context, AccessMode.CONCURRENT);
   }

   default Object get(Context context, Supplier initialValueSupplier) {
      return this.get(context, AccessMode.CONCURRENT, initialValueSupplier);
   }

   default void put(Context context, Object data) {
      this.put(context, AccessMode.CONCURRENT, data);
   }

   default void remove(Context context) {
      this.put(context, AccessMode.CONCURRENT, (Object)null);
   }

   default Object get(Context context, AccessMode accessMode) {
      return ((ContextInternal)context).getLocal(this, accessMode);
   }

   default Object get(Context context, AccessMode accessMode, Supplier initialValueSupplier) {
      return ((ContextInternal)context).getLocal(this, accessMode, initialValueSupplier);
   }

   default void put(Context context, AccessMode accessMode, Object value) {
      ((ContextInternal)context).putLocal(this, accessMode, value);
   }

   default void remove(Context context, AccessMode accessMode) {
      this.put(context, accessMode, (Object)null);
   }
}
