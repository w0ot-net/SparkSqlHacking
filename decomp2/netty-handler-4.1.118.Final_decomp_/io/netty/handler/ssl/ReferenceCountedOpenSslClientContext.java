package io.netty.handler.ssl;

import io.netty.internal.tcnative.CertificateCallback;
import io.netty.internal.tcnative.SSL;
import io.netty.internal.tcnative.SSLContext;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.SuppressJava6Requirement;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

public final class ReferenceCountedOpenSslClientContext extends ReferenceCountedOpenSslContext {
   private static final Set SUPPORTED_KEY_TYPES = Collections.unmodifiableSet(new LinkedHashSet(Arrays.asList("RSA", "DH_RSA", "EC", "EC_RSA", "EC_EC")));
   private final OpenSslSessionContext sessionContext;

   ReferenceCountedOpenSslClientContext(X509Certificate[] trustCertCollection, TrustManagerFactory trustManagerFactory, X509Certificate[] keyCertChain, PrivateKey key, String keyPassword, KeyManagerFactory keyManagerFactory, Iterable ciphers, CipherSuiteFilter cipherFilter, ApplicationProtocolConfig apn, String[] protocols, long sessionCacheSize, long sessionTimeout, boolean enableOcsp, String keyStore, String endpointIdentificationAlgorithm, ResumptionController resumptionController, Map.Entry... options) throws SSLException {
      super(ciphers, cipherFilter, toNegotiator(apn), 0, keyCertChain, ClientAuth.NONE, protocols, false, endpointIdentificationAlgorithm, enableOcsp, true, resumptionController, options);
      boolean success = false;

      try {
         this.sessionContext = newSessionContext(this, this.ctx, this.engineMap, trustCertCollection, trustManagerFactory, keyCertChain, key, keyPassword, keyManagerFactory, keyStore, sessionCacheSize, sessionTimeout, resumptionController);
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

   static OpenSslSessionContext newSessionContext(ReferenceCountedOpenSslContext thiz, long ctx, OpenSslEngineMap engineMap, X509Certificate[] trustCertCollection, TrustManagerFactory trustManagerFactory, X509Certificate[] keyCertChain, PrivateKey key, String keyPassword, KeyManagerFactory keyManagerFactory, String keyStore, long sessionCacheSize, long sessionTimeout, ResumptionController resumptionController) throws SSLException {
      if ((key != null || keyCertChain == null) && (key == null || keyCertChain != null)) {
         OpenSslKeyMaterialProvider keyMaterialProvider = null;

         OpenSslClientSessionContext var30;
         try {
            try {
               if (!OpenSsl.useKeyManagerFactory()) {
                  if (keyManagerFactory != null) {
                     throw new IllegalArgumentException("KeyManagerFactory not supported");
                  }

                  if (keyCertChain != null) {
                     setKeyMaterial(ctx, keyCertChain, key, keyPassword);
                  }
               } else {
                  if (keyManagerFactory == null && keyCertChain != null) {
                     char[] keyPasswordChars = keyStorePassword(keyPassword);
                     KeyStore ks = buildKeyStore(keyCertChain, key, keyPasswordChars, keyStore);
                     if (ks.aliases().hasMoreElements()) {
                        keyManagerFactory = new OpenSslX509KeyManagerFactory();
                     } else {
                        keyManagerFactory = new OpenSslCachingX509KeyManagerFactory(KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()));
                     }

                     keyManagerFactory.init(ks, keyPasswordChars);
                     keyMaterialProvider = providerFor(keyManagerFactory, keyPassword);
                  } else if (keyManagerFactory != null) {
                     keyMaterialProvider = providerFor(keyManagerFactory, keyPassword);
                  }

                  if (keyMaterialProvider != null) {
                     OpenSslKeyMaterialManager materialManager = new OpenSslKeyMaterialManager(keyMaterialProvider);
                     SSLContext.setCertificateCallback(ctx, new OpenSslClientCertificateCallback(engineMap, materialManager));
                  }
               }
            } catch (Exception e) {
               throw new SSLException("failed to set certificate and key", e);
            }

            SSLContext.setVerify(ctx, 1, 10);

            try {
               if (trustCertCollection != null) {
                  trustManagerFactory = buildTrustManagerFactory(trustCertCollection, trustManagerFactory, keyStore);
               } else if (trustManagerFactory == null) {
                  trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                  trustManagerFactory.init((KeyStore)null);
               }

               X509TrustManager manager = chooseTrustManager(trustManagerFactory.getTrustManagers(), resumptionController);
               setVerifyCallback(ctx, engineMap, manager);
            } catch (Exception e) {
               if (keyMaterialProvider != null) {
                  keyMaterialProvider.destroy();
               }

               throw new SSLException("unable to setup trustmanager", e);
            }

            OpenSslClientSessionContext context = new OpenSslClientSessionContext(thiz, keyMaterialProvider);
            context.setSessionCacheEnabled(CLIENT_ENABLE_SESSION_CACHE);
            if (sessionCacheSize > 0L) {
               context.setSessionCacheSize((int)Math.min(sessionCacheSize, 2147483647L));
            }

            if (sessionTimeout > 0L) {
               context.setSessionTimeout((int)Math.min(sessionTimeout, 2147483647L));
            }

            if (CLIENT_ENABLE_SESSION_TICKET) {
               context.setTicketKeys(new OpenSslSessionTicketKey[0]);
            }

            keyMaterialProvider = null;
            var30 = context;
         } finally {
            if (keyMaterialProvider != null) {
               keyMaterialProvider.destroy();
            }

         }

         return var30;
      } else {
         throw new IllegalArgumentException("Either both keyCertChain and key needs to be null or none of them");
      }
   }

   @SuppressJava6Requirement(
      reason = "Guarded by java version check"
   )
   private static void setVerifyCallback(long ctx, OpenSslEngineMap engineMap, X509TrustManager manager) {
      if (useExtendedTrustManager(manager)) {
         SSLContext.setCertVerifyCallback(ctx, new ExtendedTrustManagerVerifyCallback(engineMap, (X509ExtendedTrustManager)manager));
      } else {
         SSLContext.setCertVerifyCallback(ctx, new TrustManagerVerifyCallback(engineMap, manager));
      }

   }

   static final class OpenSslClientSessionContext extends OpenSslSessionContext {
      OpenSslClientSessionContext(ReferenceCountedOpenSslContext context, OpenSslKeyMaterialProvider provider) {
         super(context, provider, SSL.SSL_SESS_CACHE_CLIENT, new OpenSslClientSessionCache(context.engineMap));
      }
   }

   private static final class TrustManagerVerifyCallback extends ReferenceCountedOpenSslContext.AbstractCertificateVerifier {
      private final X509TrustManager manager;

      TrustManagerVerifyCallback(OpenSslEngineMap engineMap, X509TrustManager manager) {
         super(engineMap);
         this.manager = manager;
      }

      void verify(ReferenceCountedOpenSslEngine engine, X509Certificate[] peerCerts, String auth) throws Exception {
         this.manager.checkServerTrusted(peerCerts, auth);
      }
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   private static final class ExtendedTrustManagerVerifyCallback extends ReferenceCountedOpenSslContext.AbstractCertificateVerifier {
      private final X509ExtendedTrustManager manager;

      ExtendedTrustManagerVerifyCallback(OpenSslEngineMap engineMap, X509ExtendedTrustManager manager) {
         super(engineMap);
         this.manager = manager;
      }

      void verify(ReferenceCountedOpenSslEngine engine, X509Certificate[] peerCerts, String auth) throws Exception {
         this.manager.checkServerTrusted(peerCerts, auth, engine);
      }
   }

   private static final class OpenSslClientCertificateCallback implements CertificateCallback {
      private final OpenSslEngineMap engineMap;
      private final OpenSslKeyMaterialManager keyManagerHolder;

      OpenSslClientCertificateCallback(OpenSslEngineMap engineMap, OpenSslKeyMaterialManager keyManagerHolder) {
         this.engineMap = engineMap;
         this.keyManagerHolder = keyManagerHolder;
      }

      public void handle(long ssl, byte[] keyTypeBytes, byte[][] asn1DerEncodedPrincipals) throws Exception {
         ReferenceCountedOpenSslEngine engine = this.engineMap.get(ssl);
         if (engine != null) {
            try {
               Set<String> keyTypesSet = supportedClientKeyTypes(keyTypeBytes);
               String[] keyTypes = (String[])keyTypesSet.toArray(EmptyArrays.EMPTY_STRINGS);
               X500Principal[] issuers;
               if (asn1DerEncodedPrincipals == null) {
                  issuers = null;
               } else {
                  issuers = new X500Principal[asn1DerEncodedPrincipals.length];

                  for(int i = 0; i < asn1DerEncodedPrincipals.length; ++i) {
                     issuers[i] = new X500Principal(asn1DerEncodedPrincipals[i]);
                  }
               }

               this.keyManagerHolder.setKeyMaterialClientSide(engine, keyTypes, issuers);
            } catch (Throwable cause) {
               engine.initHandshakeException(cause);
               if (cause instanceof Exception) {
                  throw (Exception)cause;
               } else {
                  throw new SSLException(cause);
               }
            }
         }
      }

      private static Set supportedClientKeyTypes(byte[] clientCertificateTypes) {
         if (clientCertificateTypes == null) {
            return ReferenceCountedOpenSslClientContext.SUPPORTED_KEY_TYPES;
         } else {
            Set<String> result = new HashSet(clientCertificateTypes.length);

            for(byte keyTypeCode : clientCertificateTypes) {
               String keyType = clientKeyType(keyTypeCode);
               if (keyType != null) {
                  result.add(keyType);
               }
            }

            return result;
         }
      }

      private static String clientKeyType(byte clientCertificateType) {
         switch (clientCertificateType) {
            case 1:
               return "RSA";
            case 3:
               return "DH_RSA";
            case 64:
               return "EC";
            case 65:
               return "EC_RSA";
            case 66:
               return "EC_EC";
            default:
               return null;
         }
      }
   }
}
