package io.vertx.core.net.impl;

import [Ljavax.net.ssl.TrustManager;;
import io.netty.handler.ssl.SslContext;
import io.vertx.core.VertxException;
import io.vertx.core.http.ClientAuth;
import io.vertx.core.spi.tls.SslContextFactory;
import java.security.cert.CRL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class SslContextProvider {
   private final boolean jdkSSLProvider;
   private final Supplier provider;
   private final Set enabledProtocols;
   private final List crls;
   private final ClientAuth clientAuth;
   private final Set enabledCipherSuites;
   private final List applicationProtocols;
   private final String endpointIdentificationAlgorithm;
   private final KeyManagerFactory keyManagerFactory;
   private final TrustManagerFactory trustManagerFactory;
   private final Function keyManagerFactoryMapper;
   private final Function trustManagerMapper;
   private static final TrustManager TRUST_ALL_MANAGER = new X509TrustManager() {
      public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
      }

      public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
      }

      public X509Certificate[] getAcceptedIssuers() {
         return new X509Certificate[0];
      }
   };

   public SslContextProvider(boolean jdkSSLProvider, ClientAuth clientAuth, String endpointIdentificationAlgorithm, List applicationProtocols, Set enabledCipherSuites, Set enabledProtocols, KeyManagerFactory keyManagerFactory, Function keyManagerFactoryMapper, TrustManagerFactory trustManagerFactory, Function trustManagerMapper, List crls, Supplier provider) {
      this.jdkSSLProvider = jdkSSLProvider;
      this.provider = provider;
      this.clientAuth = clientAuth;
      this.endpointIdentificationAlgorithm = endpointIdentificationAlgorithm;
      this.applicationProtocols = applicationProtocols;
      this.enabledCipherSuites = new HashSet(enabledCipherSuites);
      this.enabledProtocols = enabledProtocols;
      this.keyManagerFactory = keyManagerFactory;
      this.trustManagerFactory = trustManagerFactory;
      this.keyManagerFactoryMapper = keyManagerFactoryMapper;
      this.trustManagerMapper = trustManagerMapper;
      this.crls = crls;
   }

   boolean jdkSSLProvider() {
      return this.jdkSSLProvider;
   }

   public VertxSslContext createContext(boolean server, KeyManagerFactory keyManagerFactory, TrustManager[] trustManagers, String serverName, boolean useAlpn, boolean trustAll) {
      if (keyManagerFactory == null) {
         keyManagerFactory = this.defaultKeyManagerFactory();
      }

      if (trustAll) {
         trustManagers = createTrustAllManager();
      } else if (trustManagers == null) {
         trustManagers = this.defaultTrustManagers();
      }

      return server ? this.createServerContext(keyManagerFactory, trustManagers, serverName, useAlpn) : this.createClientContext(keyManagerFactory, trustManagers, serverName, useAlpn);
   }

   public VertxSslContext createContext(boolean server, boolean useAlpn) {
      return this.createContext(server, this.defaultKeyManagerFactory(), this.defaultTrustManagers(), (String)null, useAlpn, false);
   }

   public VertxSslContext createClientContext(KeyManagerFactory keyManagerFactory, TrustManager[] trustManagers, final String serverName, boolean useAlpn) {
      try {
         SslContextFactory factory = ((SslContextFactory)this.provider.get()).useAlpn(useAlpn).forClient(true).enabledCipherSuites(this.enabledCipherSuites).applicationProtocols(this.applicationProtocols);
         if (keyManagerFactory != null) {
            factory.keyMananagerFactory(keyManagerFactory);
         }

         if (trustManagers != null) {
            TrustManagerFactory tmf = this.buildVertxTrustManagerFactory(trustManagers);
            factory.trustManagerFactory(tmf);
         }

         SslContext context = factory.create();
         return new VertxSslContext(context) {
            protected void initEngine(SSLEngine engine) {
               SslContextProvider.this.configureEngine(engine, SslContextProvider.this.enabledProtocols, serverName, true);
            }
         };
      } catch (Exception e) {
         throw new VertxException(e);
      }
   }

   public VertxSslContext createServerContext(KeyManagerFactory keyManagerFactory, TrustManager[] trustManagers, final String serverName, boolean useAlpn) {
      try {
         SslContextFactory factory = ((SslContextFactory)this.provider.get()).useAlpn(useAlpn).forClient(false).enabledCipherSuites(this.enabledCipherSuites).applicationProtocols(this.applicationProtocols);
         factory.clientAuth((io.netty.handler.ssl.ClientAuth)SSLHelper.CLIENT_AUTH_MAPPING.get(this.clientAuth));
         if (serverName != null) {
            factory.serverName(serverName);
         }

         if (keyManagerFactory != null) {
            factory.keyMananagerFactory(keyManagerFactory);
         }

         if (trustManagers != null) {
            TrustManagerFactory tmf = this.buildVertxTrustManagerFactory(trustManagers);
            factory.trustManagerFactory(tmf);
         }

         SslContext context = factory.create();
         return new VertxSslContext(context) {
            protected void initEngine(SSLEngine engine) {
               SslContextProvider.this.configureEngine(engine, SslContextProvider.this.enabledProtocols, serverName, false);
            }
         };
      } catch (Exception e) {
         throw new VertxException(e);
      }
   }

   public TrustManager[] defaultTrustManagers() {
      return this.trustManagerFactory != null ? this.trustManagerFactory.getTrustManagers() : null;
   }

   public TrustManagerFactory defaultTrustManagerFactory() {
      return this.trustManagerFactory;
   }

   public KeyManagerFactory defaultKeyManagerFactory() {
      return this.keyManagerFactory;
   }

   public KeyManagerFactory resolveKeyManagerFactory(String serverName) throws Exception {
      return this.keyManagerFactoryMapper != null ? (KeyManagerFactory)this.keyManagerFactoryMapper.apply(serverName) : null;
   }

   public TrustManager[] resolveTrustManagers(String serverName) throws Exception {
      return this.trustManagerMapper != null ? (TrustManager[])this.trustManagerMapper.apply(serverName) : null;
   }

   private VertxTrustManagerFactory buildVertxTrustManagerFactory(TrustManager[] mgrs) {
      if (this.crls != null && this.crls.size() > 0) {
         mgrs = createUntrustRevokedCertTrustManager(mgrs, this.crls);
      }

      return new VertxTrustManagerFactory(mgrs);
   }

   private static TrustManager[] createUntrustRevokedCertTrustManager(TrustManager[] trustMgrs, final List crls) {
      trustMgrs = (TrustManager[])((TrustManager;)trustMgrs).clone();

      for(int i = 0; i < trustMgrs.length; ++i) {
         TrustManager trustMgr = trustMgrs[i];
         if (trustMgr instanceof X509TrustManager) {
            final X509TrustManager x509TrustManager = (X509TrustManager)trustMgr;
            trustMgrs[i] = new X509TrustManager() {
               public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                  this.checkRevoked(x509Certificates);
                  x509TrustManager.checkClientTrusted(x509Certificates, s);
               }

               public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                  this.checkRevoked(x509Certificates);
                  x509TrustManager.checkServerTrusted(x509Certificates, s);
               }

               private void checkRevoked(X509Certificate[] x509Certificates) throws CertificateException {
                  for(X509Certificate cert : x509Certificates) {
                     for(CRL crl : crls) {
                        if (crl.isRevoked(cert)) {
                           throw new CertificateException("Certificate revoked");
                        }
                     }
                  }

               }

               public X509Certificate[] getAcceptedIssuers() {
                  return x509TrustManager.getAcceptedIssuers();
               }
            };
         }
      }

      return trustMgrs;
   }

   private static TrustManager[] createTrustAllManager() {
      return new TrustManager[]{TRUST_ALL_MANAGER};
   }

   public void configureEngine(SSLEngine engine, Set enabledProtocols, String serverName, boolean client) {
      Set<String> protocols = new LinkedHashSet(enabledProtocols);
      protocols.retainAll(Arrays.asList(engine.getSupportedProtocols()));
      engine.setEnabledProtocols((String[])protocols.toArray(new String[protocols.size()]));
      if (client && !this.endpointIdentificationAlgorithm.isEmpty()) {
         SSLParameters sslParameters = engine.getSSLParameters();
         sslParameters.setEndpointIdentificationAlgorithm(this.endpointIdentificationAlgorithm);
         engine.setSSLParameters(sslParameters);
      }

      if (serverName != null) {
         SSLParameters sslParameters = engine.getSSLParameters();
         sslParameters.setServerNames(Collections.singletonList(new SNIHostName(serverName)));
         engine.setSSLParameters(sslParameters);
      }

   }
}
