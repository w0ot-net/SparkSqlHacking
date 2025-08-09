package io.fabric8.kubernetes.client.utils;

import io.fabric8.kubernetes.api.model.HasMetadata;

public class ApiVersionUtil {
   private ApiVersionUtil() {
      throw new IllegalStateException("Utility class");
   }

   public static String apiGroup(Object item, String apiGroup) {
      if (item instanceof HasMetadata && Utils.isNotNullOrEmpty(((HasMetadata)item).getApiVersion())) {
         return trimGroupOrNull(((HasMetadata)item).getApiVersion());
      } else {
         return apiGroup != null && !apiGroup.isEmpty() ? trimGroup(apiGroup) : null;
      }
   }

   public static String apiVersion(Object item, String apiVersion) {
      if (item instanceof HasMetadata && Utils.isNotNullOrEmpty(((HasMetadata)item).getApiVersion())) {
         return trimVersion(((HasMetadata)item).getApiVersion());
      } else {
         return apiVersion != null && !apiVersion.isEmpty() ? trimVersion(apiVersion) : null;
      }
   }

   public static String trimVersion(String apiVersion) {
      if (apiVersion != null) {
         int slash = apiVersion.indexOf(47);
         if (slash > 0) {
            return apiVersion.substring(slash + 1);
         }
      }

      return apiVersion;
   }

   public static String trimGroup(String apiVersion) {
      if (apiVersion != null) {
         int slash = apiVersion.indexOf(47);
         if (slash > 0) {
            return apiVersion.substring(0, slash);
         }
      }

      return apiVersion;
   }

   public static String trimGroupOrNull(String apiVersion) {
      return apiVersion != null && apiVersion.contains("/") ? trimGroup(apiVersion) : null;
   }

   public static String joinApiGroupAndVersion(String group, String version) {
      return Utils.isNullOrEmpty(group) ? version : group + "/" + version;
   }
}
