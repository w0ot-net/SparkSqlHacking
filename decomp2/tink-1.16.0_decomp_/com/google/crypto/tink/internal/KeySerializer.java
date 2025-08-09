package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.SecretKeyAccess;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

public abstract class KeySerializer {
   private final Class keyClass;
   private final Class serializationClass;

   private KeySerializer(Class keyClass, Class serializationClass) {
      this.keyClass = keyClass;
      this.serializationClass = serializationClass;
   }

   public abstract Serialization serializeKey(Key key, @Nullable SecretKeyAccess access) throws GeneralSecurityException;

   public Class getKeyClass() {
      return this.keyClass;
   }

   public Class getSerializationClass() {
      return this.serializationClass;
   }

   public static KeySerializer create(final KeySerializationFunction function, Class keyClass, Class serializationClass) {
      return new KeySerializer(keyClass, serializationClass) {
         public Serialization serializeKey(Key key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
            return function.serializeKey(key, access);
         }
      };
   }

   public interface KeySerializationFunction {
      Serialization serializeKey(Key key, @Nullable SecretKeyAccess access) throws GeneralSecurityException;
   }
}
