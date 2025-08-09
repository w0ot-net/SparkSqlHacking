package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.hybrid.internal.LegacyFullHybridEncrypt;
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

public class HybridEncryptWrapper implements PrimitiveWrapper {
   private static final HybridEncryptWrapper WRAPPER = new HybridEncryptWrapper();
   private static final PrimitiveConstructor LEGACY_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(LegacyFullHybridEncrypt::create, LegacyProtoKey.class, HybridEncrypt.class);

   HybridEncryptWrapper() {
   }

   public HybridEncrypt wrap(final PrimitiveSet primitives) {
      return new WrappedHybridEncrypt(primitives);
   }

   public Class getPrimitiveClass() {
      return HybridEncrypt.class;
   }

   public Class getInputPrimitiveClass() {
      return HybridEncrypt.class;
   }

   public static void register() throws GeneralSecurityException {
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(WRAPPER);
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(LEGACY_PRIMITIVE_CONSTRUCTOR);
   }

   public static void registerToInternalPrimitiveRegistry(PrimitiveRegistry.Builder primitiveRegistryBuilder) throws GeneralSecurityException {
      primitiveRegistryBuilder.registerPrimitiveWrapper(WRAPPER);
   }

   private static class WrappedHybridEncrypt implements HybridEncrypt {
      final PrimitiveSet primitives;
      private final MonitoringClient.Logger encLogger;

      public WrappedHybridEncrypt(final PrimitiveSet primitives) {
         this.primitives = primitives;
         if (primitives.hasAnnotations()) {
            MonitoringClient client = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
            MonitoringKeysetInfo keysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitives);
            this.encLogger = client.createLogger(keysetInfo, "hybrid_encrypt", "encrypt");
         } else {
            this.encLogger = MonitoringUtil.DO_NOTHING_LOGGER;
         }

      }

      public byte[] encrypt(final byte[] plaintext, final byte[] contextInfo) throws GeneralSecurityException {
         if (this.primitives.getPrimary() == null) {
            this.encLogger.logFailure();
            throw new GeneralSecurityException("keyset without primary key");
         } else {
            try {
               byte[] output = ((HybridEncrypt)this.primitives.getPrimary().getFullPrimitive()).encrypt(plaintext, contextInfo);
               this.encLogger.log(this.primitives.getPrimary().getKeyId(), (long)plaintext.length);
               return output;
            } catch (GeneralSecurityException e) {
               this.encLogger.logFailure();
               throw e;
            }
         }
      }
   }
}
