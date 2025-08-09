package org.apache.arrow.vector;

public class NullCheckingForGet {
   public static final boolean NULL_CHECKING_ENABLED;

   private NullCheckingForGet() {
   }

   static {
      String envProperty = System.getenv("ARROW_ENABLE_NULL_CHECK_FOR_GET");
      String sysProperty = System.getProperty("arrow.enable_null_check_for_get");
      String flagValue = sysProperty;
      if (sysProperty == null) {
         flagValue = envProperty;
      }

      NULL_CHECKING_ENABLED = !"false".equals(flagValue);
   }
}
