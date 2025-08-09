package io.vertx.core.impl;

import io.vertx.core.spi.context.storage.AccessMode;
import io.vertx.core.spi.context.storage.ContextLocal;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.Supplier;

class ContextBase extends AtomicReferenceArray {
   final int localsLength;

   ContextBase(int localsLength) {
      super(localsLength);
      this.localsLength = localsLength;
   }

   ContextBase(ContextBase another) {
      super(another.localsLength);
      this.localsLength = another.localsLength;
   }

   public final Object getLocal(ContextLocal key, AccessMode accessMode) {
      ContextLocalImpl<T> internalKey = (ContextLocalImpl)key;
      int index = internalKey.index;
      if (index >= this.localsLength) {
         throw new IllegalArgumentException();
      } else {
         Object res = accessMode.get(this, index);
         return res;
      }
   }

   public final Object getLocal(ContextLocal key, AccessMode accessMode, Supplier initialValueSupplier) {
      ContextLocalImpl<T> internalKey = (ContextLocalImpl)key;
      int index = internalKey.index;
      if (index >= this.localsLength) {
         throw new IllegalArgumentException("Invalid key index: " + index);
      } else {
         Object res = accessMode.getOrCreate(this, index, initialValueSupplier);
         return res;
      }
   }

   public final void putLocal(ContextLocal key, AccessMode accessMode, Object value) {
      ContextLocalImpl<T> internalKey = (ContextLocalImpl)key;
      int index = internalKey.index;
      if (index >= this.localsLength) {
         throw new IllegalArgumentException();
      } else {
         accessMode.put(this, index, value);
      }
   }
}
