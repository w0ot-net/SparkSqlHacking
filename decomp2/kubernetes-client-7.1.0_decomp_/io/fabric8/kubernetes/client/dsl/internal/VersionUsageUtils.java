package io.fabric8.kubernetes.client.dsl.internal;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class VersionUsageUtils {
   private static final Logger LOG = LoggerFactory.getLogger(VersionUsageUtils.class);
   private static final ConcurrentHashMap UNSTABLE_TYPES = new ConcurrentHashMap();
   private static final boolean LOG_EACH_USAGE = false;

   private VersionUsageUtils() {
   }

   public static void log(String type, String version) {
      if (type != null && version != null) {
         if (isUnstable(version) && UNSTABLE_TYPES.putIfAbsent(type + "-" + version, true) == null) {
            alert(type, version);
         }

      }
   }

   private static boolean isUnstable(String version) {
      String lowerCaseVersion = version.toLowerCase(Locale.ROOT);
      return lowerCaseVersion.contains("beta") || lowerCaseVersion.contains("alpha");
   }

   private static void alert(String type, String version) {
      String message = "The client is using resource type '{}' with unstable version '{}'";
      if (type.equals("customresourcedefinitions") && version.equals("v1beta1")) {
         LOG.debug(message, type, version);
      } else {
         LOG.warn(message, type, version);
      }

   }
}
