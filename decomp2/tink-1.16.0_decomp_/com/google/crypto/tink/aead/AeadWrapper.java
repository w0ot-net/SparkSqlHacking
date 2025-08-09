package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.aead.internal.LegacyFullAead;
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

public class AeadWrapper implements PrimitiveWrapper {
   private static final AeadWrapper WRAPPER = new AeadWrapper();
   private static final PrimitiveConstructor LEGACY_FULL_AEAD_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(LegacyFullAead::create, LegacyProtoKey.class, Aead.class);

   AeadWrapper() {
   }

   public Aead wrap(final PrimitiveSet pset) throws GeneralSecurityException {
      return new WrappedAead(pset);
   }

   public Class getPrimitiveClass() {
      return Aead.class;
   }

   public Class getInputPrimitiveClass() {
      return Aead.class;
   }

   public static void register() throws GeneralSecurityException {
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(WRAPPER);
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(LEGACY_FULL_AEAD_PRIMITIVE_CONSTRUCTOR);
   }

   public static void registerToInternalPrimitiveRegistry(PrimitiveRegistry.Builder primitiveRegistryBuilder) throws GeneralSecurityException {
      primitiveRegistryBuilder.registerPrimitiveWrapper(WRAPPER);
   }

   private static class WrappedAead implements Aead {
      private final PrimitiveSet pSet;
      private final MonitoringClient.Logger encLogger;
      private final MonitoringClient.Logger decLogger;

      private WrappedAead(PrimitiveSet pSet) {
         this.pSet = pSet;
         if (pSet.hasAnnotations()) {
            MonitoringClient client = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
            MonitoringKeysetInfo keysetInfo = MonitoringUtil.getMonitoringKeysetInfo(pSet);
            this.encLogger = client.createLogger(keysetInfo, "aead", "encrypt");
            this.decLogger = client.createLogger(keysetInfo, "aead", "decrypt");
         } else {
            this.encLogger = MonitoringUtil.DO_NOTHING_LOGGER;
            this.decLogger = MonitoringUtil.DO_NOTHING_LOGGER;
         }

      }

      public byte[] encrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
         try {
            byte[] result = ((Aead)this.pSet.getPrimary().getFullPrimitive()).encrypt(plaintext, associatedData);
            this.encLogger.log(this.pSet.getPrimary().getKeyId(), (long)plaintext.length);
            return result;
         } catch (GeneralSecurityException e) {
            this.encLogger.logFailure();
            throw e;
         }
      }

      public byte[] decrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
         if (ciphertext.length > 5) {
            byte[] prefix = Arrays.copyOf(ciphertext, 5);

            for(PrimitiveSet.Entry entry : this.pSet.getPrimitive(prefix)) {
               try {
                  byte[] result = ((Aead)entry.getFullPrimitive()).decrypt(ciphertext, associatedData);
                  this.decLogger.log(entry.getKeyId(), (long)ciphertext.length);
                  return result;
               } catch (GeneralSecurityException var9) {
               }
            }
         }

         for(PrimitiveSet.Entry entry : this.pSet.getRawPrimitives()) {
            try {
               byte[] result = ((Aead)entry.getFullPrimitive()).decrypt(ciphertext, associatedData);
               this.decLogger.log(entry.getKeyId(), (long)ciphertext.length);
               return result;
            } catch (GeneralSecurityException var8) {
            }
         }

         this.decLogger.logFailure();
         throw new GeneralSecurityException("decryption failed");
      }
   }
}
