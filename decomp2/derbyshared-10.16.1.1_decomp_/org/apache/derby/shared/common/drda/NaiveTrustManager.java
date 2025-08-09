package org.apache.derby.shared.common.drda;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;
import javax.net.SocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class NaiveTrustManager implements X509TrustManager {
   public static final String SSL_KEYSTORE = "javax.net.ssl.keyStore";
   public static final String SSL_KEYSTORE_PASSWORD = "javax.net.ssl.keyStorePassword";
   private static TrustManager[] thisManager = null;

   private NaiveTrustManager() {
   }

   public static SocketFactory getSocketFactory(Properties var0) throws NoSuchAlgorithmException, KeyManagementException, NoSuchProviderException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException {
      if (thisManager == null) {
         thisManager = new TrustManager[]{new NaiveTrustManager()};
      }

      SSLContext var1 = SSLContext.getInstance("TLS");
      if (var1.getProvider().getName().equals("SunJSSE") && var0.getProperty("javax.net.ssl.keyStore") != null && var0.getProperty("javax.net.ssl.keyStorePassword") != null) {
         String var2 = var0.getProperty("javax.net.ssl.keyStore");
         String var3 = var0.getProperty("javax.net.ssl.keyStorePassword");
         KeyStore var4 = KeyStore.getInstance("JKS");
         var4.load(new FileInputStream(var2), var3.toCharArray());
         KeyManagerFactory var5 = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
         var5.init(var4, var3.toCharArray());
         var1.init(var5.getKeyManagers(), thisManager, (SecureRandom)null);
      } else {
         var1.init((KeyManager[])null, thisManager, (SecureRandom)null);
      }

      return var1.getSocketFactory();
   }

   public void checkClientTrusted(X509Certificate[] var1, String var2) throws CertificateException {
      throw new CertificateException();
   }

   public void checkServerTrusted(X509Certificate[] var1, String var2) throws CertificateException {
   }

   public X509Certificate[] getAcceptedIssuers() {
      return new X509Certificate[0];
   }
}
