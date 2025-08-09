package io.vertx.core.net.impl;

import io.vertx.core.VertxException;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.SelfSignedCertificate;
import java.security.cert.CertificateException;

public class SelfSignedCertificateImpl implements SelfSignedCertificate {
   private final io.netty.handler.ssl.util.SelfSignedCertificate certificate;

   public SelfSignedCertificateImpl() {
      try {
         this.certificate = new io.netty.handler.ssl.util.SelfSignedCertificate();
      } catch (CertificateException e) {
         throw new VertxException(e);
      }
   }

   public SelfSignedCertificateImpl(String fqdn) {
      try {
         this.certificate = new io.netty.handler.ssl.util.SelfSignedCertificate(fqdn);
      } catch (CertificateException e) {
         throw new VertxException(e);
      }
   }

   public PemKeyCertOptions keyCertOptions() {
      return (new PemKeyCertOptions()).setKeyPath(this.privateKeyPath()).setCertPath(this.certificatePath());
   }

   public PemTrustOptions trustOptions() {
      return (new PemTrustOptions()).addCertPath(this.certificatePath());
   }

   public String privateKeyPath() {
      return this.certificate.privateKey().getAbsolutePath();
   }

   public String certificatePath() {
      return this.certificate.certificate().getAbsolutePath();
   }

   public void delete() {
      this.certificate.delete();
   }
}
