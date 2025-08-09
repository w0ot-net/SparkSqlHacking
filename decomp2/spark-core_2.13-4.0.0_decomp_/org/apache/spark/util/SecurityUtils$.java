package org.apache.spark.util;

public final class SecurityUtils$ {
   public static final SecurityUtils$ MODULE$ = new SecurityUtils$();
   private static final String JAVA_VENDOR = "java.vendor";
   private static final String IBM_KRB_DEBUG_CONFIG = "com.ibm.security.krb5.Krb5Debug";
   private static final String SUN_KRB_DEBUG_CONFIG = "sun.security.krb5.debug";

   private String JAVA_VENDOR() {
      return JAVA_VENDOR;
   }

   private String IBM_KRB_DEBUG_CONFIG() {
      return IBM_KRB_DEBUG_CONFIG;
   }

   private String SUN_KRB_DEBUG_CONFIG() {
      return SUN_KRB_DEBUG_CONFIG;
   }

   public void setGlobalKrbDebug(final boolean enabled) {
      if (enabled) {
         if (this.isIBMVendor()) {
            System.setProperty(this.IBM_KRB_DEBUG_CONFIG(), "all");
         } else {
            System.setProperty(this.SUN_KRB_DEBUG_CONFIG(), "true");
         }
      } else if (this.isIBMVendor()) {
         System.clearProperty(this.IBM_KRB_DEBUG_CONFIG());
      } else {
         System.clearProperty(this.SUN_KRB_DEBUG_CONFIG());
      }
   }

   public boolean isGlobalKrbDebugEnabled() {
      if (this.isIBMVendor()) {
         String debug = System.getenv(this.IBM_KRB_DEBUG_CONFIG());
         return debug != null && debug.equalsIgnoreCase("all");
      } else {
         String debug = System.getenv(this.SUN_KRB_DEBUG_CONFIG());
         return debug != null && debug.equalsIgnoreCase("true");
      }
   }

   public String getKrb5LoginModuleName() {
      return this.isIBMVendor() ? "com.ibm.security.auth.module.Krb5LoginModule" : "com.sun.security.auth.module.Krb5LoginModule";
   }

   private boolean isIBMVendor() {
      return System.getProperty(this.JAVA_VENDOR()).contains("IBM");
   }

   private SecurityUtils$() {
   }
}
