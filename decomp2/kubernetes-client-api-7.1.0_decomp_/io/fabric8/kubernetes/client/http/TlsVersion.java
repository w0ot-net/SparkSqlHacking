package io.fabric8.kubernetes.client.http;

public enum TlsVersion {
   TLS_1_3("TLSv1.3"),
   TLS_1_2("TLSv1.2"),
   /** @deprecated */
   @Deprecated
   TLS_1_1("TLSv1.1"),
   /** @deprecated */
   @Deprecated
   TLS_1_0("TLSv1"),
   /** @deprecated */
   @Deprecated
   SSL_3_0("SSLv3");

   final String javaName;

   private TlsVersion(String javaName) {
      this.javaName = javaName;
   }

   public static TlsVersion forJavaName(String string) {
      if (string.equals("SSLv3")) {
         return SSL_3_0;
      } else {
         return string.equals("TLSv1") ? TLS_1_0 : valueOf(string.replaceAll("[v.]", "_"));
      }
   }

   public String javaName() {
      return this.javaName;
   }
}
