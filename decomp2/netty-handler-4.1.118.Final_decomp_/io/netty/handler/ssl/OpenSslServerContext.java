package io.netty.handler.ssl;

import java.io.File;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManagerFactory;

public final class OpenSslServerContext extends OpenSslContext {
   private final OpenSslServerSessionContext sessionContext;

   /** @deprecated */
   @Deprecated
   public OpenSslServerContext(File certChainFile, File keyFile) throws SSLException {
      this(certChainFile, keyFile, (String)null);
   }

   /** @deprecated */
   @Deprecated
   public OpenSslServerContext(File certChainFile, File keyFile, String keyPassword) throws SSLException {
      this(certChainFile, keyFile, keyPassword, (Iterable)null, (CipherSuiteFilter)IdentityCipherSuiteFilter.INSTANCE, (ApplicationProtocolConfig)ApplicationProtocolConfig.DISABLED, 0L, 0L);
   }

   /** @deprecated */
   @Deprecated
   public OpenSslServerContext(File certChainFile, File keyFile, String keyPassword, Iterable ciphers, ApplicationProtocolConfig apn, long sessionCacheSize, long sessionTimeout) throws SSLException {
      this(certChainFile, keyFile, keyPassword, (Iterable)ciphers, (CipherSuiteFilter)IdentityCipherSuiteFilter.INSTANCE, (ApplicationProtocolConfig)apn, sessionCacheSize, sessionTimeout);
   }

   /** @deprecated */
   @Deprecated
   public OpenSslServerContext(File certChainFile, File keyFile, String keyPassword, Iterable ciphers, Iterable nextProtocols, long sessionCacheSize, long sessionTimeout) throws SSLException {
      this(certChainFile, keyFile, keyPassword, ciphers, toApplicationProtocolConfig(nextProtocols), sessionCacheSize, sessionTimeout);
   }

   /** @deprecated */
   @Deprecated
   public OpenSslServerContext(File certChainFile, File keyFile, String keyPassword, TrustManagerFactory trustManagerFactory, Iterable ciphers, ApplicationProtocolConfig config, long sessionCacheSize, long sessionTimeout) throws SSLException {
      this(certChainFile, keyFile, keyPassword, trustManagerFactory, ciphers, toNegotiator(config), sessionCacheSize, sessionTimeout);
   }

   /** @deprecated */
   @Deprecated
   public OpenSslServerContext(File certChainFile, File keyFile, String keyPassword, TrustManagerFactory trustManagerFactory, Iterable ciphers, OpenSslApplicationProtocolNegotiator apn, long sessionCacheSize, long sessionTimeout) throws SSLException {
      this((File)null, trustManagerFactory, certChainFile, keyFile, keyPassword, (KeyManagerFactory)null, ciphers, (CipherSuiteFilter)null, (OpenSslApplicationProtocolNegotiator)apn, sessionCacheSize, sessionTimeout);
   }

   /** @deprecated */
   @Deprecated
   public OpenSslServerContext(File certChainFile, File keyFile, String keyPassword, Iterable ciphers, CipherSuiteFilter cipherFilter, ApplicationProtocolConfig apn, long sessionCacheSize, long sessionTimeout) throws SSLException {
      this((File)null, (TrustManagerFactory)null, certChainFile, keyFile, keyPassword, (KeyManagerFactory)null, ciphers, cipherFilter, (ApplicationProtocolConfig)apn, sessionCacheSize, sessionTimeout);
   }

   /** @deprecated */
   @Deprecated
   public OpenSslServerContext(File trustCertCollectionFile, TrustManagerFactory trustManagerFactory, File keyCertChainFile, File keyFile, String keyPassword, KeyManagerFactory keyManagerFactory, Iterable ciphers, CipherSuiteFilter cipherFilter, ApplicationProtocolConfig config, long sessionCacheSize, long sessionTimeout) throws SSLException {
      this(trustCertCollectionFile, trustManagerFactory, keyCertChainFile, keyFile, keyPassword, keyManagerFactory, ciphers, cipherFilter, toNegotiator(config), sessionCacheSize, sessionTimeout);
   }

   /** @deprecated */
   @Deprecated
   public OpenSslServerContext(File certChainFile, File keyFile, String keyPassword, TrustManagerFactory trustManagerFactory, Iterable ciphers, CipherSuiteFilter cipherFilter, ApplicationProtocolConfig config, long sessionCacheSize, long sessionTimeout) throws SSLException {
      this((File)null, trustManagerFactory, certChainFile, keyFile, keyPassword, (KeyManagerFactory)null, ciphers, cipherFilter, (OpenSslApplicationProtocolNegotiator)toNegotiator(config), sessionCacheSize, sessionTimeout);
   }

   /** @deprecated */
   @Deprecated
   public OpenSslServerContext(File certChainFile, File keyFile, String keyPassword, TrustManagerFactory trustManagerFactory, Iterable ciphers, CipherSuiteFilter cipherFilter, OpenSslApplicationProtocolNegotiator apn, long sessionCacheSize, long sessionTimeout) throws SSLException {
      this((File)null, trustManagerFactory, certChainFile, keyFile, keyPassword, (KeyManagerFactory)null, ciphers, cipherFilter, (OpenSslApplicationProtocolNegotiator)apn, sessionCacheSize, sessionTimeout);
   }

   /** @deprecated */
   @Deprecated
   public OpenSslServerContext(File trustCertCollectionFile, TrustManagerFactory trustManagerFactory, File keyCertChainFile, File keyFile, String keyPassword, KeyManagerFactory keyManagerFactory, Iterable ciphers, CipherSuiteFilter cipherFilter, OpenSslApplicationProtocolNegotiator apn, long sessionCacheSize, long sessionTimeout) throws SSLException {
      this(toX509CertificatesInternal(trustCertCollectionFile), trustManagerFactory, toX509CertificatesInternal(keyCertChainFile), toPrivateKeyInternal(keyFile, keyPassword), keyPassword, keyManagerFactory, ciphers, cipherFilter, (OpenSslApplicationProtocolNegotiator)apn, sessionCacheSize, sessionTimeout, ClientAuth.NONE, (String[])null, false, false, KeyStore.getDefaultType(), (ResumptionController)null);
   }

   OpenSslServerContext(X509Certificate[] trustCertCollection, TrustManagerFactory trustManagerFactory, X509Certificate[] keyCertChain, PrivateKey key, String keyPassword, KeyManagerFactory keyManagerFactory, Iterable ciphers, CipherSuiteFilter cipherFilter, ApplicationProtocolConfig apn, long sessionCacheSize, long sessionTimeout, ClientAuth clientAuth, String[] protocols, boolean startTls, boolean enableOcsp, String keyStore, ResumptionController resumptionController, Map.Entry... options) throws SSLException {
      this(trustCertCollection, trustManagerFactory, keyCertChain, key, keyPassword, keyManagerFactory, ciphers, cipherFilter, toNegotiator(apn), sessionCacheSize, sessionTimeout, clientAuth, protocols, startTls, enableOcsp, keyStore, resumptionController, options);
   }

   private OpenSslServerContext(X509Certificate[] trustCertCollection, TrustManagerFactory trustManagerFactory, X509Certificate[] keyCertChain, PrivateKey key, String keyPassword, KeyManagerFactory keyManagerFactory, Iterable ciphers, CipherSuiteFilter cipherFilter, OpenSslApplicationProtocolNegotiator apn, long sessionCacheSize, long sessionTimeout, ClientAuth clientAuth, String[] protocols, boolean startTls, boolean enableOcsp, String keyStore, ResumptionController resumptionController, Map.Entry... options) throws SSLException {
      super(ciphers, cipherFilter, apn, 1, keyCertChain, clientAuth, protocols, startTls, enableOcsp, resumptionController, options);
      boolean success = false;

      try {
         OpenSslKeyMaterialProvider.validateKeyMaterialSupported(keyCertChain, key, keyPassword);
         this.sessionContext = ReferenceCountedOpenSslServerContext.newSessionContext(this, this.ctx, this.engineMap, trustCertCollection, trustManagerFactory, keyCertChain, key, keyPassword, keyManagerFactory, keyStore, sessionCacheSize, sessionTimeout, resumptionController);
         success = true;
      } finally {
         if (!success) {
            this.release();
         }

      }

   }

   public OpenSslServerSessionContext sessionContext() {
      return this.sessionContext;
   }
}
