package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.daead.internal.LegacyFullDeterministicAead;
import com.google.crypto.tink.internal.LegacyProtoKey;
import com.google.crypto.tink.internal.MonitoringClient;
import com.google.crypto.tink.internal.MonitoringKeysetInfo;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.internal.PrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveSet;
import com.google.crypto.tink.internal.PrimitiveWrapper;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class DeterministicAeadWrapper implements PrimitiveWrapper {
   private static final DeterministicAeadWrapper WRAPPER = new DeterministicAeadWrapper();
   private static final PrimitiveConstructor LEGACY_FULL_DAEAD_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(LegacyFullDeterministicAead::create, LegacyProtoKey.class, DeterministicAead.class);

   DeterministicAeadWrapper() {
   }

   public DeterministicAead wrap(final PrimitiveSet primitives) {
      return new WrappedDeterministicAead(primitives);
   }

   public Class getPrimitiveClass() {
      return DeterministicAead.class;
   }

   public Class getInputPrimitiveClass() {
      return DeterministicAead.class;
   }

   public static void register() throws GeneralSecurityException {
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(WRAPPER);
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(LEGACY_FULL_DAEAD_PRIMITIVE_CONSTRUCTOR);
   }

   public static void registerToInternalPrimitiveRegistry(PrimitiveRegistry.Builder primitiveRegistryBuilder) throws GeneralSecurityException {
      primitiveRegistryBuilder.registerPrimitiveWrapper(WRAPPER);
   }

   private static class WrappedDeterministicAead implements DeterministicAead {
      private final PrimitiveSet primitives;
      private final MonitoringClient.Logger encLogger;
      private final MonitoringClient.Logger decLogger;

      public WrappedDeterministicAead(PrimitiveSet primitives) {
         this.primitives = primitives;
         if (primitives.hasAnnotations()) {
            MonitoringClient client = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
            MonitoringKeysetInfo keysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitives);
            this.encLogger = client.createLogger(keysetInfo, "daead", "encrypt");
            this.decLogger = client.createLogger(keysetInfo, "daead", "decrypt");
         } else {
            this.encLogger = MonitoringUtil.DO_NOTHING_LOGGER;
            this.decLogger = MonitoringUtil.DO_NOTHING_LOGGER;
         }

      }

      public byte[] encryptDeterministically(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
         try {
            byte[] output = ((DeterministicAead)this.primitives.getPrimary().getFullPrimitive()).encryptDeterministically(plaintext, associatedData);
            this.encLogger.log(this.primitives.getPrimary().getKeyId(), (long)plaintext.length);
            return output;
         } catch (GeneralSecurityException e) {
            this.encLogger.logFailure();
            throw e;
         }
      }

      public byte[] decryptDeterministically(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
         if (ciphertext.length > 5) {
            byte[] prefix = Arrays.copyOf(ciphertext, 5);

            for(PrimitiveSet.Entry entry : this.primitives.getPrimitive(prefix)) {
               try {
                  byte[] output = ((DeterministicAead)entry.getFullPrimitive()).decryptDeterministically(ciphertext, associatedData);
                  this.decLogger.log(entry.getKeyId(), (long)ciphertext.length);
                  return output;
               } catch (GeneralSecurityException var9) {
               }
            }
         }

         for(PrimitiveSet.Entry entry : this.primitives.getRawPrimitives()) {
            try {
               byte[] output = ((DeterministicAead)entry.getFullPrimitive()).decryptDeterministically(ciphertext, associatedData);
               this.decLogger.log(entry.getKeyId(), (long)ciphertext.length);
               return output;
            } catch (GeneralSecurityException var8) {
            }
         }

         this.decLogger.logFailure();
         throw new GeneralSecurityException("decryption failed");
      }
   }
}
