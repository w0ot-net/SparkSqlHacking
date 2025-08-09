package io.vertx.core.net.impl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public class TrustAllTrustManager implements X509TrustManager {
   public static TrustAllTrustManager INSTANCE = new TrustAllTrustManager();

   private TrustAllTrustManager() {
   }

   public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
   }

   public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
   }

   public X509Certificate[] getAcceptedIssuers() {
      return new X509Certificate[0];
   }
}
