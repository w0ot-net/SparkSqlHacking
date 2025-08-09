package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;

public final class MutablePrimitiveRegistry {
   private static MutablePrimitiveRegistry globalInstance = new MutablePrimitiveRegistry();
   private final AtomicReference registry = new AtomicReference(PrimitiveRegistry.builder().build());

   public static MutablePrimitiveRegistry globalInstance() {
      return globalInstance;
   }

   public static void resetGlobalInstanceTestOnly() {
      globalInstance = new MutablePrimitiveRegistry();
   }

   MutablePrimitiveRegistry() {
   }

   public synchronized void registerPrimitiveConstructor(PrimitiveConstructor constructor) throws GeneralSecurityException {
      PrimitiveRegistry newRegistry = PrimitiveRegistry.builder((PrimitiveRegistry)this.registry.get()).registerPrimitiveConstructor(constructor).build();
      this.registry.set(newRegistry);
   }

   public synchronized void registerPrimitiveWrapper(PrimitiveWrapper wrapper) throws GeneralSecurityException {
      PrimitiveRegistry newRegistry = PrimitiveRegistry.builder((PrimitiveRegistry)this.registry.get()).registerPrimitiveWrapper(wrapper).build();
      this.registry.set(newRegistry);
   }

   public Object getPrimitive(Key key, Class primitiveClass) throws GeneralSecurityException {
      return ((PrimitiveRegistry)this.registry.get()).getPrimitive(key, primitiveClass);
   }

   public Class getInputPrimitiveClass(Class wrapperClassObject) throws GeneralSecurityException {
      return ((PrimitiveRegistry)this.registry.get()).getInputPrimitiveClass(wrapperClassObject);
   }

   public Object wrap(PrimitiveSet primitives, Class wrapperClassObject) throws GeneralSecurityException {
      return ((PrimitiveRegistry)this.registry.get()).wrap(primitives, wrapperClassObject);
   }
}
