package io.vertx.core.net;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.net.impl.SelfSignedCertificateImpl;

@VertxGen
public interface SelfSignedCertificate {
   PemKeyCertOptions keyCertOptions();

   PemTrustOptions trustOptions();

   String privateKeyPath();

   String certificatePath();

   void delete();

   static SelfSignedCertificate create() {
      return new SelfSignedCertificateImpl();
   }

   static SelfSignedCertificate create(String fqdn) {
      return new SelfSignedCertificateImpl(fqdn);
   }
}
