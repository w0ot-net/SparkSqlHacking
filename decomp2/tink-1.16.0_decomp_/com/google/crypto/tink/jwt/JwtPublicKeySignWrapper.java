package com.google.crypto.tink.jwt;

import com.google.crypto.tink.internal.MonitoringClient;
import com.google.crypto.tink.internal.MonitoringKeysetInfo;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveSet;
import com.google.crypto.tink.internal.PrimitiveWrapper;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

class JwtPublicKeySignWrapper implements PrimitiveWrapper {
   private static final JwtPublicKeySignWrapper WRAPPER = new JwtPublicKeySignWrapper();

   public JwtPublicKeySign wrap(final PrimitiveSet primitives) throws GeneralSecurityException {
      return new WrappedJwtPublicKeySign(primitives);
   }

   public Class getPrimitiveClass() {
      return JwtPublicKeySign.class;
   }

   public Class getInputPrimitiveClass() {
      return JwtPublicKeySign.class;
   }

   public static void register() throws GeneralSecurityException {
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(WRAPPER);
   }

   @Immutable
   private static class WrappedJwtPublicKeySign implements JwtPublicKeySign {
      private final JwtPublicKeySign primary;
      private final int primaryKeyId;
      private final MonitoringClient.Logger logger;

      public WrappedJwtPublicKeySign(final PrimitiveSet primitives) {
         this.primary = (JwtPublicKeySign)primitives.getPrimary().getFullPrimitive();
         this.primaryKeyId = primitives.getPrimary().getKeyId();
         if (primitives.hasAnnotations()) {
            MonitoringClient client = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
            MonitoringKeysetInfo keysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitives);
            this.logger = client.createLogger(keysetInfo, "jwtsign", "sign");
         } else {
            this.logger = MonitoringUtil.DO_NOTHING_LOGGER;
         }

      }

      public String signAndEncode(RawJwt token) throws GeneralSecurityException {
         try {
            String output = this.primary.signAndEncode(token);
            this.logger.log(this.primaryKeyId, 1L);
            return output;
         } catch (GeneralSecurityException e) {
            this.logger.logFailure();
            throw e;
         }
      }
   }
}
