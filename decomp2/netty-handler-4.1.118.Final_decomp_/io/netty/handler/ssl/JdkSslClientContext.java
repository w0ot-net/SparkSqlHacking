package io.netty.handler.ssl;

import java.io.File;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/** @deprecated */
@Deprecated
public final class JdkSslClientContext extends JdkSslContext {
   /** @deprecated */
   @Deprecated
   public JdkSslClientContext() throws SSLException {
      this((File)null, (TrustManagerFactory)null);
   }

   /** @deprecated */
   @Deprecated
   public JdkSslClientContext(File certChainFile) throws SSLException {
      this(certChainFile, (TrustManagerFactory)null);
   }

   /** @deprecated */
   @Deprecated
   public JdkSslClientContext(TrustManagerFactory trustManagerFactory) throws SSLException {
      this((File)null, trustManagerFactory);
   }

   /** @deprecated */
   @Deprecated
   public JdkSslClientContext(File certChainFile, TrustManagerFactory trustManagerFactory) throws SSLException {
      this(certChainFile, trustManagerFactory, (Iterable)null, IdentityCipherSuiteFilter.INSTANCE, (JdkApplicationProtocolNegotiator)JdkDefaultApplicationProtocolNegotiator.INSTANCE, 0L, 0L);
   }

   /** @deprecated */
   @Deprecated
   public JdkSslClientContext(File certChainFile, TrustManagerFactory trustManagerFactory, Iterable ciphers, Iterable nextProtocols, long sessionCacheSize, long sessionTimeout) throws SSLException {
      this(certChainFile, trustManagerFactory, ciphers, IdentityCipherSuiteFilter.INSTANCE, (JdkApplicationProtocolNegotiator)toNegotiator(toApplicationProtocolConfig(nextProtocols), false), sessionCacheSize, sessionTimeout);
   }

   /** @deprecated */
   @Deprecated
   public JdkSslClientContext(File certChainFile, TrustManagerFactory trustManagerFactory, Iterable ciphers, CipherSuiteFilter cipherFilter, ApplicationProtocolConfig apn, long sessionCacheSize, long sessionTimeout) throws SSLException {
      this(certChainFile, trustManagerFactory, ciphers, cipherFilter, toNegotiator(apn, false), sessionCacheSize, sessionTimeout);
   }

   /** @deprecated */
   @Deprecated
   public JdkSslClientContext(File certChainFile, TrustManagerFactory trustManagerFactory, Iterable ciphers, CipherSuiteFilter cipherFilter, JdkApplicationProtocolNegotiator apn, long sessionCacheSize, long sessionTimeout) throws SSLException {
      this((Provider)null, certChainFile, trustManagerFactory, ciphers, cipherFilter, apn, sessionCacheSize, sessionTimeout);
   }

   JdkSslClientContext(Provider provider, File trustCertCollectionFile, TrustManagerFactory trustManagerFactory, Iterable ciphers, CipherSuiteFilter cipherFilter, JdkApplicationProtocolNegotiator apn, long sessionCacheSize, long sessionTimeout) throws SSLException {
      super(newSSLContext(provider, toX509CertificatesInternal(trustCertCollectionFile), trustManagerFactory, (X509Certificate[])null, (PrivateKey)null, (String)null, (KeyManagerFactory)null, sessionCacheSize, sessionTimeout, (SecureRandom)null, KeyStore.getDefaultType(), (ResumptionController)null), true, ciphers, cipherFilter, (JdkApplicationProtocolNegotiator)apn, ClientAuth.NONE, (String[])null, false);
   }

   /** @deprecated */
   @Deprecated
   public JdkSslClientContext(File trustCertCollectionFile, TrustManagerFactory trustManagerFactory, File keyCertChainFile, File keyFile, String keyPassword, KeyManagerFactory keyManagerFactory, Iterable ciphers, CipherSuiteFilter cipherFilter, ApplicationProtocolConfig apn, long sessionCacheSize, long sessionTimeout) throws SSLException {
      this(trustCertCollectionFile, trustManagerFactory, keyCertChainFile, keyFile, keyPassword, keyManagerFactory, ciphers, cipherFilter, toNegotiator(apn, false), sessionCacheSize, sessionTimeout);
   }

   /** @deprecated */
   @Deprecated
   public JdkSslClientContext(File trustCertCollectionFile, TrustManagerFactory trustManagerFactory, File keyCertChainFile, File keyFile, String keyPassword, KeyManagerFactory keyManagerFactory, Iterable ciphers, CipherSuiteFilter cipherFilter, JdkApplicationProtocolNegotiator apn, long sessionCacheSize, long sessionTimeout) throws SSLException {
      super(newSSLContext((Provider)null, toX509CertificatesInternal(trustCertCollectionFile), trustManagerFactory, toX509CertificatesInternal(keyCertChainFile), toPrivateKeyInternal(keyFile, keyPassword), keyPassword, keyManagerFactory, sessionCacheSize, sessionTimeout, (SecureRandom)null, KeyStore.getDefaultType(), (ResumptionController)null), true, ciphers, cipherFilter, (JdkApplicationProtocolNegotiator)apn, ClientAuth.NONE, (String[])null, false);
   }

   JdkSslClientContext(Provider sslContextProvider, X509Certificate[] trustCertCollection, TrustManagerFactory trustManagerFactory, X509Certificate[] keyCertChain, PrivateKey key, String keyPassword, KeyManagerFactory keyManagerFactory, Iterable ciphers, CipherSuiteFilter cipherFilter, ApplicationProtocolConfig apn, String[] protocols, long sessionCacheSize, long sessionTimeout, SecureRandom secureRandom, String keyStoreType, String endpointIdentificationAlgorithm, ResumptionController resumptionController) throws SSLException {
      super(newSSLContext(sslContextProvider, trustCertCollection, trustManagerFactory, keyCertChain, key, keyPassword, keyManagerFactory, sessionCacheSize, sessionTimeout, secureRandom, keyStoreType, resumptionController), true, ciphers, cipherFilter, toNegotiator(apn, false), ClientAuth.NONE, protocols, false, endpointIdentificationAlgorithm, resumptionController);
   }

   private static SSLContext newSSLContext(Provider sslContextProvider, X509Certificate[] trustCertCollection, TrustManagerFactory trustManagerFactory, X509Certificate[] keyCertChain, PrivateKey key, String keyPassword, KeyManagerFactory keyManagerFactory, long sessionCacheSize, long sessionTimeout, SecureRandom secureRandom, String keyStore, ResumptionController resumptionController) throws SSLException {
      try {
         if (trustCertCollection != null) {
            trustManagerFactory = buildTrustManagerFactory(trustCertCollection, trustManagerFactory, keyStore);
         }

         if (keyCertChain != null) {
            keyManagerFactory = buildKeyManagerFactory(keyCertChain, (String)null, key, keyPassword, keyManagerFactory, keyStore);
         }

         SSLContext ctx = sslContextProvider == null ? SSLContext.getInstance("TLS") : SSLContext.getInstance("TLS", sslContextProvider);
         ctx.init(keyManagerFactory == null ? null : keyManagerFactory.getKeyManagers(), trustManagerFactory == null ? null : wrapIfNeeded(trustManagerFactory.getTrustManagers(), resumptionController), secureRandom);
         SSLSessionContext sessCtx = ctx.getClientSessionContext();
         if (sessionCacheSize > 0L) {
            sessCtx.setSessionCacheSize((int)Math.min(sessionCacheSize, 2147483647L));
         }

         if (sessionTimeout > 0L) {
            sessCtx.setSessionTimeout((int)Math.min(sessionTimeout, 2147483647L));
         }

         return ctx;
      } catch (Exception e) {
         if (e instanceof SSLException) {
            throw (SSLException)e;
         } else {
            throw new SSLException("failed to initialize the client-side SSL context", e);
         }
      }
   }

   private static TrustManager[] wrapIfNeeded(TrustManager[] tms, ResumptionController resumptionController) {
      if (resumptionController != null) {
         for(int i = 0; i < tms.length; ++i) {
            tms[i] = resumptionController.wrapIfNeeded(tms[i]);
         }
      }

      return tms;
   }
}
