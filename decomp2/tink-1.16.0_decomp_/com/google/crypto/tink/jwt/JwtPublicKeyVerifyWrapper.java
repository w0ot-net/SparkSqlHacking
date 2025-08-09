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
import java.util.List;

class JwtPublicKeyVerifyWrapper implements PrimitiveWrapper {
   private static final JwtPublicKeyVerifyWrapper WRAPPER = new JwtPublicKeyVerifyWrapper();

   public JwtPublicKeyVerify wrap(final PrimitiveSet primitives) throws GeneralSecurityException {
      return new WrappedJwtPublicKeyVerify(primitives);
   }

   public Class getPrimitiveClass() {
      return JwtPublicKeyVerify.class;
   }

   public Class getInputPrimitiveClass() {
      return JwtPublicKeyVerify.class;
   }

   public static void register() throws GeneralSecurityException {
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(WRAPPER);
   }

   @Immutable
   private static class WrappedJwtPublicKeyVerify implements JwtPublicKeyVerify {
      private final PrimitiveSet primitives;
      private final MonitoringClient.Logger logger;

      public WrappedJwtPublicKeyVerify(PrimitiveSet primitives) {
         this.primitives = primitives;
         if (primitives.hasAnnotations()) {
            MonitoringClient client = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
            MonitoringKeysetInfo keysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitives);
            this.logger = client.createLogger(keysetInfo, "jwtverify", "verify");
         } else {
            this.logger = MonitoringUtil.DO_NOTHING_LOGGER;
         }

      }

      public VerifiedJwt verifyAndDecode(String compact, JwtValidator validator) throws GeneralSecurityException {
         GeneralSecurityException interestingException = null;

         for(List entries : this.primitives.getAll()) {
            for(PrimitiveSet.Entry entry : entries) {
               try {
                  VerifiedJwt result = ((JwtPublicKeyVerify)entry.getFullPrimitive()).verifyAndDecode(compact, validator);
                  this.logger.log(entry.getKeyId(), 1L);
                  return result;
               } catch (GeneralSecurityException e) {
                  if (e instanceof JwtInvalidException) {
                     interestingException = e;
                  }
               }
            }
         }

         this.logger.logFailure();
         if (interestingException != null) {
            throw interestingException;
         } else {
            throw new GeneralSecurityException("invalid JWT");
         }
      }
   }
}
