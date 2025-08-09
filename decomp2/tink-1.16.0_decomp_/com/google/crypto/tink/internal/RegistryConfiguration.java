package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.KeyData;
import com.google.errorprone.annotations.DoNotCall;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

public final class RegistryConfiguration extends InternalConfiguration {
   private static final RegistryConfiguration CONFIG = new RegistryConfiguration();

   public static RegistryConfiguration get() {
      return CONFIG;
   }

   private RegistryConfiguration() {
   }

   public Object getLegacyPrimitive(KeyData keyData, Class primitiveClass) throws GeneralSecurityException {
      return Registry.getPrimitive(keyData, primitiveClass);
   }

   public Object getPrimitive(Key key, Class primitiveClass) throws GeneralSecurityException {
      return MutablePrimitiveRegistry.globalInstance().getPrimitive(key, primitiveClass);
   }

   public Object wrap(PrimitiveSet primitiveSet, Class clazz) throws GeneralSecurityException {
      return Registry.wrap(primitiveSet, clazz);
   }

   @Nullable
   public Class getInputPrimitiveClass(Class wrapperClassObject) {
      return Registry.getInputPrimitive(wrapperClassObject);
   }

   @DoNotCall
   public static InternalConfiguration createFromPrimitiveRegistry(PrimitiveRegistry registry) {
      throw new UnsupportedOperationException("Cannot create RegistryConfiguration from a PrimitiveRegistry");
   }
}
