package com.google.crypto.tink.prf;

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
import com.google.crypto.tink.prf.internal.LegacyFullPrf;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Immutable
public class PrfSetWrapper implements PrimitiveWrapper {
   private static final PrfSetWrapper WRAPPER = new PrfSetWrapper();
   private static final PrimitiveConstructor LEGACY_FULL_PRF_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(LegacyFullPrf::create, LegacyProtoKey.class, Prf.class);

   public PrfSet wrap(PrimitiveSet set) throws GeneralSecurityException {
      return new WrappedPrfSet(set);
   }

   public Class getPrimitiveClass() {
      return PrfSet.class;
   }

   public Class getInputPrimitiveClass() {
      return Prf.class;
   }

   public static void register() throws GeneralSecurityException {
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(WRAPPER);
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(LEGACY_FULL_PRF_PRIMITIVE_CONSTRUCTOR);
   }

   public static void registerToInternalPrimitiveRegistry(PrimitiveRegistry.Builder primitiveRegistryBuilder) throws GeneralSecurityException {
      primitiveRegistryBuilder.registerPrimitiveWrapper(WRAPPER);
   }

   private static class WrappedPrfSet extends PrfSet {
      private final Map keyIdToPrfMap;
      private final int primaryKeyId;

      private WrappedPrfSet(PrimitiveSet primitives) throws GeneralSecurityException {
         if (primitives.getRawPrimitives().isEmpty()) {
            throw new GeneralSecurityException("No primitives provided.");
         } else if (primitives.getPrimary() == null) {
            throw new GeneralSecurityException("Primary key not set.");
         } else {
            MonitoringClient.Logger logger;
            if (primitives.hasAnnotations()) {
               MonitoringClient client = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
               MonitoringKeysetInfo keysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitives);
               logger = client.createLogger(keysetInfo, "prf", "compute");
            } else {
               logger = MonitoringUtil.DO_NOTHING_LOGGER;
            }

            this.primaryKeyId = primitives.getPrimary().getKeyId();
            List<PrimitiveSet.Entry<Prf>> entries = primitives.getRawPrimitives();
            Map<Integer, Prf> mutablePrfMap = new HashMap();

            for(PrimitiveSet.Entry entry : entries) {
               mutablePrfMap.put(entry.getKeyId(), new PrfWithMonitoring((Prf)entry.getFullPrimitive(), entry.getKeyId(), logger));
            }

            this.keyIdToPrfMap = Collections.unmodifiableMap(mutablePrfMap);
         }
      }

      public int getPrimaryId() {
         return this.primaryKeyId;
      }

      public Map getPrfs() throws GeneralSecurityException {
         return this.keyIdToPrfMap;
      }

      @Immutable
      private static class PrfWithMonitoring implements Prf {
         private final Prf prf;
         private final int keyId;
         private final MonitoringClient.Logger logger;

         public byte[] compute(byte[] input, int outputLength) throws GeneralSecurityException {
            try {
               byte[] output = this.prf.compute(input, outputLength);
               this.logger.log(this.keyId, (long)input.length);
               return output;
            } catch (GeneralSecurityException e) {
               this.logger.logFailure();
               throw e;
            }
         }

         public PrfWithMonitoring(Prf prf, int keyId, MonitoringClient.Logger logger) {
            this.prf = prf;
            this.keyId = keyId;
            this.logger = logger;
         }
      }
   }
}
