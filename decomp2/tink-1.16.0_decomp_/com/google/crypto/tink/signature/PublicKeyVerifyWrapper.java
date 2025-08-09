package com.google.crypto.tink.signature;

import com.google.crypto.tink.PublicKeyVerify;
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
import com.google.crypto.tink.signature.internal.LegacyFullVerify;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class PublicKeyVerifyWrapper implements PrimitiveWrapper {
   private static final PublicKeyVerifyWrapper WRAPPER = new PublicKeyVerifyWrapper();
   private static final PrimitiveConstructor LEGACY_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(LegacyFullVerify::create, LegacyProtoKey.class, PublicKeyVerify.class);

   public PublicKeyVerify wrap(final PrimitiveSet primitives) {
      return new WrappedPublicKeyVerify(primitives);
   }

   public Class getPrimitiveClass() {
      return PublicKeyVerify.class;
   }

   public Class getInputPrimitiveClass() {
      return PublicKeyVerify.class;
   }

   static void register() throws GeneralSecurityException {
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(WRAPPER);
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(LEGACY_PRIMITIVE_CONSTRUCTOR);
   }

   public static void registerToInternalPrimitiveRegistry(PrimitiveRegistry.Builder primitiveRegistryBuilder) throws GeneralSecurityException {
      primitiveRegistryBuilder.registerPrimitiveWrapper(WRAPPER);
   }

   private static class WrappedPublicKeyVerify implements PublicKeyVerify {
      private final PrimitiveSet primitives;
      private final MonitoringClient.Logger monitoringLogger;

      public WrappedPublicKeyVerify(PrimitiveSet primitives) {
         this.primitives = primitives;
         if (primitives.hasAnnotations()) {
            MonitoringClient client = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
            MonitoringKeysetInfo keysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitives);
            this.monitoringLogger = client.createLogger(keysetInfo, "public_key_verify", "verify");
         } else {
            this.monitoringLogger = MonitoringUtil.DO_NOTHING_LOGGER;
         }

      }

      public void verify(final byte[] signature, final byte[] data) throws GeneralSecurityException {
         if (signature.length <= 5) {
            this.monitoringLogger.logFailure();
            throw new GeneralSecurityException("signature too short");
         } else {
            byte[] prefix = Arrays.copyOf(signature, 5);

            for(PrimitiveSet.Entry entry : this.primitives.getPrimitive(prefix)) {
               try {
                  ((PublicKeyVerify)entry.getFullPrimitive()).verify(signature, data);
                  this.monitoringLogger.log(entry.getKeyId(), (long)data.length);
                  return;
               } catch (GeneralSecurityException var9) {
               }
            }

            for(PrimitiveSet.Entry entry : this.primitives.getRawPrimitives()) {
               try {
                  ((PublicKeyVerify)entry.getFullPrimitive()).verify(signature, data);
                  this.monitoringLogger.log(entry.getKeyId(), (long)data.length);
                  return;
               } catch (GeneralSecurityException var8) {
               }
            }

            this.monitoringLogger.logFailure();
            throw new GeneralSecurityException("invalid signature");
         }
      }
   }
}
