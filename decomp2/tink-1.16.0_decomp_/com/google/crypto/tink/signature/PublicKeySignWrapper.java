package com.google.crypto.tink.signature;

import com.google.crypto.tink.PublicKeySign;
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
import com.google.crypto.tink.signature.internal.LegacyFullSign;
import java.security.GeneralSecurityException;

public class PublicKeySignWrapper implements PrimitiveWrapper {
   private static final PublicKeySignWrapper WRAPPER = new PublicKeySignWrapper();
   private static final PrimitiveConstructor LEGACY_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(LegacyFullSign::create, LegacyProtoKey.class, PublicKeySign.class);

   PublicKeySignWrapper() {
   }

   public PublicKeySign wrap(final PrimitiveSet primitives) {
      return new WrappedPublicKeySign(primitives);
   }

   public Class getPrimitiveClass() {
      return PublicKeySign.class;
   }

   public Class getInputPrimitiveClass() {
      return PublicKeySign.class;
   }

   public static void register() throws GeneralSecurityException {
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(WRAPPER);
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(LEGACY_PRIMITIVE_CONSTRUCTOR);
   }

   public static void registerToInternalPrimitiveRegistry(PrimitiveRegistry.Builder primitiveRegistryBuilder) throws GeneralSecurityException {
      primitiveRegistryBuilder.registerPrimitiveWrapper(WRAPPER);
   }

   private static class WrappedPublicKeySign implements PublicKeySign {
      private final PrimitiveSet primitives;
      private final MonitoringClient.Logger logger;

      public WrappedPublicKeySign(final PrimitiveSet primitives) {
         this.primitives = primitives;
         if (primitives.hasAnnotations()) {
            MonitoringClient client = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
            MonitoringKeysetInfo keysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitives);
            this.logger = client.createLogger(keysetInfo, "public_key_sign", "sign");
         } else {
            this.logger = MonitoringUtil.DO_NOTHING_LOGGER;
         }

      }

      public byte[] sign(final byte[] data) throws GeneralSecurityException {
         try {
            byte[] output = ((PublicKeySign)this.primitives.getPrimary().getFullPrimitive()).sign(data);
            this.logger.log(this.primitives.getPrimary().getKeyId(), (long)data.length);
            return output;
         } catch (GeneralSecurityException e) {
            this.logger.logFailure();
            throw e;
         }
      }
   }
}
