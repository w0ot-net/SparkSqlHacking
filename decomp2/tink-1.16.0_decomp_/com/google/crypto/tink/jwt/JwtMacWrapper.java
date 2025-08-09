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

class JwtMacWrapper implements PrimitiveWrapper {
   private static final JwtMacWrapper WRAPPER = new JwtMacWrapper();

   private static void validate(PrimitiveSet primitiveSet) throws GeneralSecurityException {
      if (primitiveSet.getPrimary() == null) {
         throw new GeneralSecurityException("Primitive set has no primary.");
      }
   }

   public JwtMac wrap(final PrimitiveSet primitives) throws GeneralSecurityException {
      validate(primitives);
      return new WrappedJwtMac(primitives);
   }

   public Class getPrimitiveClass() {
      return JwtMac.class;
   }

   public Class getInputPrimitiveClass() {
      return JwtMac.class;
   }

   public static void register() throws GeneralSecurityException {
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(WRAPPER);
   }

   @Immutable
   private static class WrappedJwtMac implements JwtMac {
      private final PrimitiveSet primitives;
      private final MonitoringClient.Logger computeLogger;
      private final MonitoringClient.Logger verifyLogger;

      private WrappedJwtMac(PrimitiveSet primitives) {
         this.primitives = primitives;
         if (primitives.hasAnnotations()) {
            MonitoringClient client = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
            MonitoringKeysetInfo keysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitives);
            this.computeLogger = client.createLogger(keysetInfo, "jwtmac", "compute");
            this.verifyLogger = client.createLogger(keysetInfo, "jwtmac", "verify");
         } else {
            this.computeLogger = MonitoringUtil.DO_NOTHING_LOGGER;
            this.verifyLogger = MonitoringUtil.DO_NOTHING_LOGGER;
         }

      }

      public String computeMacAndEncode(RawJwt token) throws GeneralSecurityException {
         try {
            String result = ((JwtMac)this.primitives.getPrimary().getFullPrimitive()).computeMacAndEncode(token);
            this.computeLogger.log(this.primitives.getPrimary().getKeyId(), 1L);
            return result;
         } catch (GeneralSecurityException e) {
            this.computeLogger.logFailure();
            throw e;
         }
      }

      public VerifiedJwt verifyMacAndDecode(String compact, JwtValidator validator) throws GeneralSecurityException {
         GeneralSecurityException interestingException = null;

         for(List entries : this.primitives.getAll()) {
            for(PrimitiveSet.Entry entry : entries) {
               try {
                  VerifiedJwt result = ((JwtMac)entry.getFullPrimitive()).verifyMacAndDecode(compact, validator);
                  this.verifyLogger.log(entry.getKeyId(), 1L);
                  return result;
               } catch (GeneralSecurityException e) {
                  if (e instanceof JwtInvalidException) {
                     interestingException = e;
                  }
               }
            }
         }

         this.verifyLogger.logFailure();
         if (interestingException != null) {
            throw interestingException;
         } else {
            throw new GeneralSecurityException("invalid MAC");
         }
      }
   }
}
