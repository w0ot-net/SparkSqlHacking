package com.google.crypto.tink.internal;

import com.google.crypto.tink.KeyStatus;
import com.google.crypto.tink.proto.KeyStatusType;
import java.security.GeneralSecurityException;
import java.util.List;

public final class MonitoringUtil {
   public static final MonitoringClient.Logger DO_NOTHING_LOGGER = new DoNothingLogger();
   private static final String TYPE_URL_PREFIX = "type.googleapis.com/google.crypto.";

   private static KeyStatus parseStatus(KeyStatusType in) {
      switch (in) {
         case ENABLED:
            return KeyStatus.ENABLED;
         case DISABLED:
            return KeyStatus.DISABLED;
         case DESTROYED:
            return KeyStatus.DESTROYED;
         default:
            throw new IllegalStateException("Unknown key status");
      }
   }

   private static String parseKeyTypeUrl(String keyTypeUrl) {
      return !keyTypeUrl.startsWith("type.googleapis.com/google.crypto.") ? keyTypeUrl : keyTypeUrl.substring("type.googleapis.com/google.crypto.".length());
   }

   public static MonitoringKeysetInfo getMonitoringKeysetInfo(PrimitiveSet primitiveSet) {
      MonitoringKeysetInfo.Builder builder = MonitoringKeysetInfo.newBuilder();
      builder.setAnnotations(primitiveSet.getAnnotations());

      for(List entries : primitiveSet.getAll()) {
         for(PrimitiveSet.Entry entry : entries) {
            builder.addEntry(parseStatus(entry.getStatus()), entry.getKeyId(), parseKeyTypeUrl(entry.getKeyTypeUrl()), entry.getOutputPrefixType().name());
         }
      }

      PrimitiveSet.Entry<P> primary = primitiveSet.getPrimary();
      if (primary != null) {
         builder.setPrimaryKeyId(primitiveSet.getPrimary().getKeyId());
      }

      try {
         return builder.build();
      } catch (GeneralSecurityException e) {
         throw new IllegalStateException(e);
      }
   }

   private MonitoringUtil() {
   }

   private static class DoNothingLogger implements MonitoringClient.Logger {
      private DoNothingLogger() {
      }

      public void log(int keyId, long numBytesAsInput) {
      }

      public void logFailure() {
      }
   }
}
