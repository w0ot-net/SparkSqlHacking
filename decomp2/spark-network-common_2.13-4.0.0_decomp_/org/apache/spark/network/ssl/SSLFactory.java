package org.apache.spark.network.ssl;

import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.ssl.OpenSsl;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.network.util.JavaUtils;
import org.sparkproject.guava.io.Files;

public class SSLFactory {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(SSLFactory.class);
   private SSLContext jdkSslContext;
   private SslContext nettyClientSslContext;
   private SslContext nettyServerSslContext;
   private KeyManager[] keyManagers;
   private TrustManager[] trustManagers;
   private String requestedProtocol;
   private String[] requestedCiphers;

   private SSLFactory(Builder b) {
      this.requestedProtocol = b.requestedProtocol;
      this.requestedCiphers = b.requestedCiphers;

      try {
         if (b.certChain != null && b.privateKey != null) {
            this.initNettySslContexts(b);
         } else {
            this.initJdkSslContext(b);
         }

      } catch (Exception e) {
         throw new RuntimeException("SSLFactory creation failed", e);
      }
   }

   private void initJdkSslContext(Builder b) throws IOException, GeneralSecurityException {
      this.keyManagers = keyManagers(b.keyStore, b.keyPassword, b.keyStorePassword);
      this.trustManagers = trustStoreManagers(b.trustStore, b.trustStorePassword, b.trustStoreReloadingEnabled, b.trustStoreReloadIntervalMs);
      this.jdkSslContext = createSSLContext(this.requestedProtocol, this.keyManagers, this.trustManagers);
   }

   private void initNettySslContexts(Builder b) throws SSLException {
      this.nettyClientSslContext = SslContextBuilder.forClient().sslProvider(this.getSslProvider(b)).trustManager(b.certChain).build();
      this.nettyServerSslContext = SslContextBuilder.forServer(b.certChain, b.privateKey, b.privateKeyPassword).sslProvider(this.getSslProvider(b)).build();
   }

   private SslProvider getSslProvider(Builder b) {
      if (b.openSslEnabled) {
         if (OpenSsl.isAvailable()) {
            return SslProvider.OPENSSL;
         }

         logger.warn("OpenSSL Provider requested but it is not available, using JDK SSL Provider");
      }

      return SslProvider.JDK;
   }

   public void destroy() {
      if (this.trustManagers != null) {
         for(int i = 0; i < this.trustManagers.length; ++i) {
            TrustManager var3 = this.trustManagers[i];
            if (var3 instanceof ReloadingX509TrustManager) {
               ReloadingX509TrustManager manager = (ReloadingX509TrustManager)var3;

               try {
                  manager.destroy();
               } catch (InterruptedException ex) {
                  logger.info("Interrupted while destroying trust manager: ", ex);
               }
            }
         }

         this.trustManagers = null;
      }

      this.keyManagers = null;
      this.jdkSslContext = null;
      this.nettyClientSslContext = null;
      this.nettyServerSslContext = null;
      this.requestedProtocol = null;
      this.requestedCiphers = null;
   }

   private static SSLContext createSSLContext(String requestedProtocol, KeyManager[] keyManagers, TrustManager[] trustManagers) throws GeneralSecurityException {
      SSLContext sslContext = SSLContext.getInstance(requestedProtocol);
      sslContext.init(keyManagers, trustManagers, (SecureRandom)null);
      return sslContext;
   }

   public SSLEngine createSSLEngine(boolean isClient, ByteBufAllocator allocator) {
      SSLEngine engine = this.createEngine(isClient, allocator);
      engine.setUseClientMode(isClient);
      engine.setNeedClientAuth(false);
      engine.setEnabledProtocols(enabledProtocols(engine, this.requestedProtocol));
      engine.setEnabledCipherSuites(enabledCipherSuites(engine, this.requestedCiphers));
      return engine;
   }

   private SSLEngine createEngine(boolean isClient, ByteBufAllocator allocator) {
      SSLEngine engine;
      if (isClient) {
         if (this.nettyClientSslContext != null) {
            engine = this.nettyClientSslContext.newEngine(allocator);
         } else {
            engine = this.jdkSslContext.createSSLEngine();
         }
      } else if (this.nettyServerSslContext != null) {
         engine = this.nettyServerSslContext.newEngine(allocator);
      } else {
         engine = this.jdkSslContext.createSSLEngine();
      }

      return engine;
   }

   private static TrustManager[] credulousTrustStoreManagers() {
      return new TrustManager[]{new X509TrustManager() {
         public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
         }

         public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
         }

         public X509Certificate[] getAcceptedIssuers() {
            return null;
         }
      }};
   }

   private static TrustManager[] trustStoreManagers(File trustStore, String trustStorePassword, boolean trustStoreReloadingEnabled, int trustStoreReloadIntervalMs) throws IOException, GeneralSecurityException {
      if (trustStore != null && trustStore.exists()) {
         if (trustStoreReloadingEnabled) {
            ReloadingX509TrustManager reloading = new ReloadingX509TrustManager(KeyStore.getDefaultType(), trustStore, trustStorePassword, (long)trustStoreReloadIntervalMs);
            reloading.init();
            return new TrustManager[]{reloading};
         } else {
            return defaultTrustManagers(trustStore, trustStorePassword);
         }
      } else {
         return credulousTrustStoreManagers();
      }
   }

   private static TrustManager[] defaultTrustManagers(File trustStore, String trustStorePassword) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
      InputStream input = Files.asByteSource(trustStore).openStream();

      TrustManager[] var6;
      try {
         KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
         char[] passwordCharacters = trustStorePassword != null ? trustStorePassword.toCharArray() : null;
         ks.load(input, passwordCharacters);
         TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
         tmf.init(ks);
         var6 = tmf.getTrustManagers();
      } catch (Throwable var8) {
         if (input != null) {
            try {
               input.close();
            } catch (Throwable var7) {
               var8.addSuppressed(var7);
            }
         }

         throw var8;
      }

      if (input != null) {
         input.close();
      }

      return var6;
   }

   private static KeyManager[] keyManagers(File keyStore, String keyPassword, String keyStorePassword) throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException, UnrecoverableKeyException {
      KeyManagerFactory factory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
      char[] keyStorePasswordChars = keyStorePassword != null ? keyStorePassword.toCharArray() : null;
      char[] keyPasswordChars = keyPassword != null ? keyPassword.toCharArray() : keyStorePasswordChars;
      factory.init(loadKeyStore(keyStore, keyStorePasswordChars), keyPasswordChars);
      return factory.getKeyManagers();
   }

   private static KeyStore loadKeyStore(File keyStore, char[] keyStorePassword) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
      if (keyStore == null) {
         throw new KeyStoreException("keyStore cannot be null. Please configure spark.ssl.rpc.keyStore");
      } else {
         KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
         FileInputStream fin = new FileInputStream(keyStore);

         KeyStore var4;
         try {
            ks.load(fin, keyStorePassword);
            var4 = ks;
         } finally {
            JavaUtils.closeQuietly(fin);
         }

         return var4;
      }
   }

   private static String[] enabledProtocols(SSLEngine engine, String requestedProtocol) {
      String[] supportedProtocols = engine.getSupportedProtocols();
      String[] defaultProtocols = new String[]{"TLSv1.3", "TLSv1.2"};
      String[] enabledProtocols = requestedProtocol != null && !requestedProtocol.isEmpty() ? new String[]{requestedProtocol} : defaultProtocols;
      List<String> protocols = addIfSupported(supportedProtocols, enabledProtocols);
      return !protocols.isEmpty() ? (String[])protocols.toArray(new String[protocols.size()]) : supportedProtocols;
   }

   private static String[] enabledCipherSuites(String[] supportedCiphers, String[] defaultCiphers, String[] requestedCiphers) {
      String[] baseCiphers = new String[]{"TLS_CHACHA20_POLY1305_SHA256", "TLS_AES_128_GCM_SHA256", "TLS_AES_256_GCM_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", "TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384"};
      String[] enabledCiphers = requestedCiphers != null && requestedCiphers.length != 0 ? requestedCiphers : baseCiphers;
      List<String> ciphers = addIfSupported(supportedCiphers, enabledCiphers);
      return !ciphers.isEmpty() ? (String[])ciphers.toArray(new String[ciphers.size()]) : defaultCiphers;
   }

   private static String[] enabledCipherSuites(SSLEngine engine, String[] requestedCiphers) {
      return enabledCipherSuites(engine.getSupportedCipherSuites(), engine.getEnabledCipherSuites(), requestedCiphers);
   }

   private static List addIfSupported(String[] supported, String... names) {
      List<String> enabled = new ArrayList();
      Set<String> supportedSet = new HashSet(Arrays.asList(supported));

      for(String n : names) {
         if (supportedSet.contains(n)) {
            enabled.add(n);
         }
      }

      return enabled;
   }

   public static class Builder {
      private String requestedProtocol;
      private String[] requestedCiphers;
      private File keyStore;
      private String keyStorePassword;
      private File privateKey;
      private String privateKeyPassword;
      private String keyPassword;
      private File certChain;
      private File trustStore;
      private String trustStorePassword;
      private boolean trustStoreReloadingEnabled;
      private int trustStoreReloadIntervalMs;
      private boolean openSslEnabled;

      public Builder requestedProtocol(String requestedProtocol) {
         this.requestedProtocol = requestedProtocol == null ? "TLSv1.3" : requestedProtocol;
         return this;
      }

      public Builder requestedCiphers(String[] requestedCiphers) {
         this.requestedCiphers = requestedCiphers;
         return this;
      }

      public Builder keyStore(File keyStore, String keyStorePassword) {
         this.keyStore = keyStore;
         this.keyStorePassword = keyStorePassword;
         return this;
      }

      public Builder privateKey(File privateKey) {
         this.privateKey = privateKey;
         return this;
      }

      public Builder keyPassword(String keyPassword) {
         this.keyPassword = keyPassword;
         return this;
      }

      public Builder privateKeyPassword(String privateKeyPassword) {
         this.privateKeyPassword = privateKeyPassword;
         return this;
      }

      public Builder certChain(File certChain) {
         this.certChain = certChain;
         return this;
      }

      public Builder openSslEnabled(boolean enabled) {
         this.openSslEnabled = enabled;
         return this;
      }

      public Builder trustStore(File trustStore, String trustStorePassword, boolean trustStoreReloadingEnabled, int trustStoreReloadIntervalMs) {
         this.trustStore = trustStore;
         this.trustStorePassword = trustStorePassword;
         this.trustStoreReloadingEnabled = trustStoreReloadingEnabled;
         this.trustStoreReloadIntervalMs = trustStoreReloadIntervalMs;
         return this;
      }

      public SSLFactory build() {
         return new SSLFactory(this);
      }
   }
}
