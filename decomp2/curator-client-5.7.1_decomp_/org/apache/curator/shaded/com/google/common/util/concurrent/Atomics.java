package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public final class Atomics {
   private Atomics() {
   }

   public static AtomicReference newReference() {
      return new AtomicReference();
   }

   public static AtomicReference newReference(@ParametricNullness Object initialValue) {
      return new AtomicReference(initialValue);
   }

   public static AtomicReferenceArray newReferenceArray(int length) {
      return new AtomicReferenceArray(length);
   }

   public static AtomicReferenceArray newReferenceArray(Object[] array) {
      return new AtomicReferenceArray(array);
   }
}
