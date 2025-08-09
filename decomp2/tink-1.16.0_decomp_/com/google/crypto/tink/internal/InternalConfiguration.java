package com.google.crypto.tink.internal;

import com.google.crypto.tink.Configuration;
import com.google.crypto.tink.Key;
import com.google.crypto.tink.proto.KeyData;
import java.security.GeneralSecurityException;

public abstract class InternalConfiguration extends Configuration {
   public abstract Object getLegacyPrimitive(KeyData keyData, Class primitiveClass) throws GeneralSecurityException;

   public abstract Object getPrimitive(Key key, Class primitiveClass) throws GeneralSecurityException;

   public abstract Object wrap(PrimitiveSet primitiveSet, Class clazz) throws GeneralSecurityException;

   public abstract Class getInputPrimitiveClass(Class wrapperClassObject) throws GeneralSecurityException;

   public static InternalConfiguration createFromPrimitiveRegistry(PrimitiveRegistry registry) {
      return new InternalConfigurationImpl(registry);
   }

   private static class InternalConfigurationImpl extends InternalConfiguration {
      private final PrimitiveRegistry registry;

      private InternalConfigurationImpl(PrimitiveRegistry registry) {
         this.registry = registry;
      }

      public Object getLegacyPrimitive(KeyData keyData, Class primitiveClass) throws GeneralSecurityException {
         throw new UnsupportedOperationException("Not implemented");
      }

      public Object getPrimitive(Key key, Class primitiveClass) throws GeneralSecurityException {
         return this.registry.getPrimitive(key, primitiveClass);
      }

      public Class getInputPrimitiveClass(Class wrapperClassObject) throws GeneralSecurityException {
         return this.registry.getInputPrimitiveClass(wrapperClassObject);
      }

      public Object wrap(PrimitiveSet primitiveSet, Class clazz) throws GeneralSecurityException {
         return this.registry.wrap(primitiveSet, clazz);
      }
   }
}
