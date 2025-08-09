package io.netty.handler.ssl;

import java.io.File;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManagerFactory;

public final class OpenSslClientContext extends OpenSslContext {
   private final OpenSslSessionContext sessionContext;

   /** @deprecated */
   @Deprecated
   public OpenSslClientContext() throws SSLException {
      this((File)null, (TrustManagerFactory)null, (File)null, (File)null, (String)null, (KeyManagerFactory)null, (Iterable)null, IdentityCipherSuiteFilter.INSTANCE, (ApplicationProtocolConfig)null, 0L, 0L);
   }

   /** @deprecated */
   @Deprecated
   public OpenSslClientContext(File certChainFile) throws SSLException {
      this(certChainFile, (TrustManagerFactory)null);
   }

   /** @deprecated */
   @Deprecated
   public OpenSslClientContext(TrustManagerFactory trustManagerFactory) throws SSLException {
      this((File)null, trustManagerFactory);
   }

   /** @deprecated */
   @Deprecated
   public OpenSslClientContext(File certChainFile, TrustManagerFactory trustManagerFactory) throws SSLException {
      this(certChainFile, trustManagerFactory, (File)null, (File)null, (String)null, (KeyManagerFactory)null, (Iterable)null, IdentityCipherSuiteFilter.INSTANCE, (ApplicationProtocolConfig)null, 0L, 0L);
   }

   /** @deprecated */
   @Deprecated
   public OpenSslClientContext(File certChainFile, TrustManagerFactory trustManagerFactory, Iterable ciphers, ApplicationProtocolConfig apn, long sessionCacheSize, long sessionTimeout) throws SSLException {
      this(certChainFile, trustManagerFactory, (File)null, (File)null, (String)null, (KeyManagerFactory)null, ciphers, IdentityCipherSuiteFilter.INSTANCE, apn, sessionCacheSize, sessionTimeout);
   }

   /** @deprecated */
   @Deprecated
   public OpenSslClientContext(File certChainFile, TrustManagerFactory trustManagerFactory, Iterable ciphers, CipherSuiteFilter cipherFilter, ApplicationProtocolConfig apn, long sessionCacheSize, long sessionTimeout) throws SSLException {
      this(certChainFile, trustManagerFactory, (File)null, (File)null, (String)null, (KeyManagerFactory)null, ciphers, cipherFilter, apn, sessionCacheSize, sessionTimeout);
   }

   /** @deprecated */
   @Deprecated
   public OpenSslClientContext(File trustCertCollectionFile, TrustManagerFactory trustManagerFactory, File keyCertChainFile, File keyFile, String keyPassword, KeyManagerFactory keyManagerFactory, Iterable ciphers, CipherSuiteFilter cipherFilter, ApplicationProtocolConfig apn, long sessionCacheSize, long sessionTimeout) throws SSLException {
      this(toX509CertificatesInternal(trustCertCollectionFile), trustManagerFactory, toX509CertificatesInternal(keyCertChainFile), toPrivateKeyInternal(keyFile, keyPassword), keyPassword, keyManagerFactory, ciphers, cipherFilter, apn, (String[])null, sessionCacheSize, sessionTimeout, false, KeyStore.getDefaultType(), (String)null, (ResumptionController)null);
   }

   OpenSslClientContext(X509Certificate[] trustCertCollection, TrustManagerFactory trustManagerFactory, X509Certificate[] keyCertChain, PrivateKey key, String keyPassword, KeyManagerFactory keyManagerFactory, Iterable ciphers, CipherSuiteFilter cipherFilter, ApplicationProtocolConfig apn, String[] protocols, long sessionCacheSize, long sessionTimeout, boolean enableOcsp, String keyStore, String endpointIdentificationAlgorithm, ResumptionController resumptionController, Map.Entry... options) throws SSLException {
      super(ciphers, cipherFilter, apn, 0, keyCertChain, ClientAuth.NONE, protocols, false, endpointIdentificationAlgorithm, enableOcsp, resumptionController, options);
      boolean success = false;

      try {
         OpenSslKeyMaterialProvider.validateKeyMaterialSupported(keyCertChain, key, keyPassword);
         this.sessionContext = ReferenceCountedOpenSslClientContext.newSessionContext(this, this.ctx, this.engineMap, trustCertCollection, trustManagerFactory, keyCertChain, key, keyPassword, keyManagerFactory, keyStore, sessionCacheSize, sessionTimeout, resumptionController);
         success = true;
      } finally {
         if (!success) {
            this.release();
         }

      }

   }

   public OpenSslSessionContext sessionContext() {
      return this.sessionContext;
   }
}
