package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.hybrid.internal.LegacyFullHybridDecrypt;
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

public class HybridDecryptWrapper implements PrimitiveWrapper {
   private static final HybridDecryptWrapper WRAPPER = new HybridDecryptWrapper();
   private static final PrimitiveConstructor LEGACY_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(LegacyFullHybridDecrypt::create, LegacyProtoKey.class, HybridDecrypt.class);

   HybridDecryptWrapper() {
   }

   public HybridDecrypt wrap(final PrimitiveSet primitives) {
      return new WrappedHybridDecrypt(primitives);
   }

   public Class getPrimitiveClass() {
      return HybridDecrypt.class;
   }

   public Class getInputPrimitiveClass() {
      return HybridDecrypt.class;
   }

   public static void register() throws GeneralSecurityException {
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(WRAPPER);
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(LEGACY_PRIMITIVE_CONSTRUCTOR);
   }

   public static void registerToInternalPrimitiveRegistry(PrimitiveRegistry.Builder primitiveRegistryBuilder) throws GeneralSecurityException {
      primitiveRegistryBuilder.registerPrimitiveWrapper(WRAPPER);
   }

   private static class WrappedHybridDecrypt implements HybridDecrypt {
      private final PrimitiveSet primitives;
      private final MonitoringClient.Logger decLogger;

      public WrappedHybridDecrypt(final PrimitiveSet primitives) {
         this.primitives = primitives;
         if (primitives.hasAnnotations()) {
            MonitoringClient client = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
            MonitoringKeysetInfo keysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitives);
            this.decLogger = client.createLogger(keysetInfo, "hybrid_decrypt", "decrypt");
         } else {
            this.decLogger = MonitoringUtil.DO_NOTHING_LOGGER;
         }

      }

      public byte[] decrypt(final byte[] ciphertext, final byte[] contextInfo) throws GeneralSecurityException {
         if (ciphertext.length > 5) {
            byte[] prefix = Arrays.copyOfRange(ciphertext, 0, 5);

            for(PrimitiveSet.Entry entry : this.primitives.getPrimitive(prefix)) {
               try {
                  byte[] output = ((HybridDecrypt)entry.getFullPrimitive()).decrypt(ciphertext, contextInfo);
                  this.decLogger.log(entry.getKeyId(), (long)ciphertext.length);
                  return output;
               } catch (GeneralSecurityException var9) {
               }
            }
         }

         for(PrimitiveSet.Entry entry : this.primitives.getRawPrimitives()) {
            try {
               byte[] output = ((HybridDecrypt)entry.getFullPrimitive()).decrypt(ciphertext, contextInfo);
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
