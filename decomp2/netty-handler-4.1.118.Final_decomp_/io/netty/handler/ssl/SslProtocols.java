package io.netty.handler.ssl;

public final class SslProtocols {
   /** @deprecated */
   @Deprecated
   public static final String SSL_v2_HELLO = "SSLv2Hello";
   /** @deprecated */
   @Deprecated
   public static final String SSL_v2 = "SSLv2";
   /** @deprecated */
   @Deprecated
   public static final String SSL_v3 = "SSLv3";
   /** @deprecated */
   @Deprecated
   public static final String TLS_v1 = "TLSv1";
   /** @deprecated */
   @Deprecated
   public static final String TLS_v1_1 = "TLSv1.1";
   public static final String TLS_v1_2 = "TLSv1.2";
   public static final String TLS_v1_3 = "TLSv1.3";

   private SslProtocols() {
   }
}
