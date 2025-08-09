package io.vertx.core.spi.tls;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;

class DefaultJDKCipherSuite {
   private static final List DEFAULT_JDK_CIPHER_SUITE;

   static List get() {
      return DEFAULT_JDK_CIPHER_SUITE;
   }

   static {
      ArrayList<String> suite = new ArrayList();

      try {
         SSLContext context = SSLContext.getInstance("TLS");
         context.init((KeyManager[])null, (TrustManager[])null, (SecureRandom)null);
         SSLEngine engine = context.createSSLEngine();
         Collections.addAll(suite, engine.getEnabledCipherSuites());
      } catch (Throwable var3) {
         suite = null;
      }

      DEFAULT_JDK_CIPHER_SUITE = suite != null ? Collections.unmodifiableList(suite) : null;
   }
}
