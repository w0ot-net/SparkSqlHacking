package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.util.Bytes;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

public abstract class KeyParser {
   private final Bytes objectIdentifier;
   private final Class serializationClass;

   private KeyParser(Bytes objectIdentifier, Class serializationClass) {
      this.objectIdentifier = objectIdentifier;
      this.serializationClass = serializationClass;
   }

   public abstract Key parseKey(Serialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException;

   public final Bytes getObjectIdentifier() {
      return this.objectIdentifier;
   }

   public final Class getSerializationClass() {
      return this.serializationClass;
   }

   public static KeyParser create(final KeyParsingFunction function, Bytes objectIdentifier, Class serializationClass) {
      return new KeyParser(objectIdentifier, serializationClass) {
         public Key parseKey(Serialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
            return function.parseKey(serialization, access);
         }
      };
   }

   public interface KeyParsingFunction {
      Key parseKey(Serialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException;
   }
}
