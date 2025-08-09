package io.vertx.core.spi.context.storage;

import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.Supplier;

public interface AccessMode {
   AccessMode CONCURRENT = new AccessMode() {
      public Object get(AtomicReferenceArray locals, int idx) {
         return locals.get(idx);
      }

      public void put(AtomicReferenceArray locals, int idx, Object value) {
         locals.set(idx, value);
      }

      public Object getOrCreate(AtomicReferenceArray locals, int idx, Supplier initialValueSupplier) {
         while(true) {
            Object res = locals.get(idx);
            if (res == null) {
               Object initial = initialValueSupplier.get();
               if (initial == null) {
                  throw new IllegalStateException();
               }

               if (!locals.compareAndSet(idx, (Object)null, initial)) {
                  continue;
               }

               res = initial;
            }

            return res;
         }
      }
   };

   Object get(AtomicReferenceArray var1, int var2);

   void put(AtomicReferenceArray var1, int var2, Object var3);

   Object getOrCreate(AtomicReferenceArray var1, int var2, Supplier var3);
}
