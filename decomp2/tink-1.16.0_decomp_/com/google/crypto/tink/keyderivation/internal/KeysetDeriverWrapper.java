package com.google.crypto.tink.keyderivation.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveSet;
import com.google.crypto.tink.internal.PrimitiveWrapper;
import com.google.crypto.tink.keyderivation.KeysetDeriver;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

public final class KeysetDeriverWrapper implements PrimitiveWrapper {
   private static final KeysetDeriverWrapper WRAPPER = new KeysetDeriverWrapper();

   private static void validate(PrimitiveSet primitiveSet) throws GeneralSecurityException {
      if (primitiveSet.getPrimary() == null) {
         throw new GeneralSecurityException("Primitive set has no primary.");
      }
   }

   KeysetDeriverWrapper() {
   }

   public KeysetDeriver wrap(final PrimitiveSet primitiveSet) throws GeneralSecurityException {
      validate(primitiveSet);
      return new WrappedKeysetDeriver(primitiveSet);
   }

   public Class getPrimitiveClass() {
      return KeysetDeriver.class;
   }

   public Class getInputPrimitiveClass() {
      return KeyDeriver.class;
   }

   public static void register() throws GeneralSecurityException {
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(WRAPPER);
   }

   @Immutable
   private static class WrappedKeysetDeriver implements KeysetDeriver {
      private final PrimitiveSet primitiveSet;

      private WrappedKeysetDeriver(PrimitiveSet primitiveSet) {
         this.primitiveSet = primitiveSet;
      }

      private static KeysetHandle.Builder.Entry deriveAndGetEntry(byte[] salt, PrimitiveSet.Entry entry, int primaryKeyId) throws GeneralSecurityException {
         KeyDeriver deriver = (KeyDeriver)entry.getFullPrimitive();
         if (deriver == null) {
            throw new GeneralSecurityException("Primitive set has non-full primitives -- this is probably a bug");
         } else {
            Key key = deriver.deriveKey(salt);
            KeysetHandle.Builder.Entry result = KeysetHandle.importKey(key);
            result.withFixedId(entry.getKeyId());
            if (entry.getKeyId() == primaryKeyId) {
               result.makePrimary();
            }

            return result;
         }
      }

      public KeysetHandle deriveKeyset(byte[] salt) throws GeneralSecurityException {
         KeysetHandle.Builder builder = KeysetHandle.newBuilder();

         for(PrimitiveSet.Entry entry : this.primitiveSet.getAllInKeysetOrder()) {
            builder.addEntry(deriveAndGetEntry(salt, entry, this.primitiveSet.getPrimary().getKeyId()));
         }

         return builder.build();
      }
   }
}
