package com.google.crypto.tink.mac;

import com.google.crypto.tink.Mac;
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
import com.google.crypto.tink.mac.internal.LegacyFullMac;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class MacWrapper implements PrimitiveWrapper {
   private static final MacWrapper WRAPPER = new MacWrapper();
   private static final PrimitiveConstructor LEGACY_FULL_MAC_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(LegacyFullMac::create, LegacyProtoKey.class, Mac.class);

   MacWrapper() {
   }

   public Mac wrap(final PrimitiveSet primitives) throws GeneralSecurityException {
      return new WrappedMac(primitives);
   }

   public Class getPrimitiveClass() {
      return Mac.class;
   }

   public Class getInputPrimitiveClass() {
      return Mac.class;
   }

   static void register() throws GeneralSecurityException {
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(WRAPPER);
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(LEGACY_FULL_MAC_PRIMITIVE_CONSTRUCTOR);
   }

   public static void registerToInternalPrimitiveRegistry(PrimitiveRegistry.Builder primitiveRegistryBuilder) throws GeneralSecurityException {
      primitiveRegistryBuilder.registerPrimitiveWrapper(WRAPPER);
   }

   private static class WrappedMac implements Mac {
      private final PrimitiveSet primitives;
      private final MonitoringClient.Logger computeLogger;
      private final MonitoringClient.Logger verifyLogger;

      private WrappedMac(PrimitiveSet primitives) {
         this.primitives = primitives;
         if (primitives.hasAnnotations()) {
            MonitoringClient client = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
            MonitoringKeysetInfo keysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitives);
            this.computeLogger = client.createLogger(keysetInfo, "mac", "compute");
            this.verifyLogger = client.createLogger(keysetInfo, "mac", "verify");
         } else {
            this.computeLogger = MonitoringUtil.DO_NOTHING_LOGGER;
            this.verifyLogger = MonitoringUtil.DO_NOTHING_LOGGER;
         }

      }

      public byte[] computeMac(final byte[] data) throws GeneralSecurityException {
         try {
            byte[] output = ((Mac)this.primitives.getPrimary().getFullPrimitive()).computeMac(data);
            this.computeLogger.log(this.primitives.getPrimary().getKeyId(), (long)data.length);
            return output;
         } catch (GeneralSecurityException e) {
            this.computeLogger.logFailure();
            throw e;
         }
      }

      public void verifyMac(final byte[] mac, final byte[] data) throws GeneralSecurityException {
         if (mac.length <= 5) {
            this.verifyLogger.logFailure();
            throw new GeneralSecurityException("tag too short");
         } else {
            byte[] prefix = Arrays.copyOf(mac, 5);

            for(PrimitiveSet.Entry entry : this.primitives.getPrimitive(prefix)) {
               try {
                  ((Mac)entry.getFullPrimitive()).verifyMac(mac, data);
                  this.verifyLogger.log(entry.getKeyId(), (long)data.length);
                  return;
               } catch (GeneralSecurityException var9) {
               }
            }

            for(PrimitiveSet.Entry entry : this.primitives.getRawPrimitives()) {
               try {
                  ((Mac)entry.getFullPrimitive()).verifyMac(mac, data);
                  this.verifyLogger.log(entry.getKeyId(), (long)data.length);
                  return;
               } catch (GeneralSecurityException var8) {
               }
            }

            this.verifyLogger.logFailure();
            throw new GeneralSecurityException("invalid MAC");
         }
      }
   }
}
