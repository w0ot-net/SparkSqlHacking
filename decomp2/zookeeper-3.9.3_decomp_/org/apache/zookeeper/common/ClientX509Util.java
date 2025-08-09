package org.apache.zookeeper.common;

import io.netty.handler.ssl.DelegatingSslContext;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import java.util.Arrays;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientX509Util extends X509Util {
   private static final Logger LOG = LoggerFactory.getLogger(ClientX509Util.class);
   private final String sslAuthProviderProperty = this.getConfigPrefix() + "authProvider";
   private final String sslProviderProperty = this.getConfigPrefix() + "sslProvider";

   protected String getConfigPrefix() {
      return "zookeeper.ssl.";
   }

   protected boolean shouldVerifyClientHostname() {
      return false;
   }

   public String getSslAuthProviderProperty() {
      return this.sslAuthProviderProperty;
   }

   public String getSslProviderProperty() {
      return this.sslProviderProperty;
   }

   public SslContext createNettySslContextForClient(ZKConfig config) throws X509Exception.KeyManagerException, X509Exception.TrustManagerException, SSLException {
      String keyStoreLocation = config.getProperty(this.getSslKeystoreLocationProperty(), "");
      String keyStorePassword = this.getPasswordFromConfigPropertyOrFile(config, this.getSslKeystorePasswdProperty(), this.getSslKeystorePasswdPathProperty());
      String keyStoreType = config.getProperty(this.getSslKeystoreTypeProperty());
      SslContextBuilder sslContextBuilder = SslContextBuilder.forClient();
      if (keyStoreLocation.isEmpty()) {
         LOG.warn("{} not specified", this.getSslKeystoreLocationProperty());
      } else {
         sslContextBuilder.keyManager(createKeyManager(keyStoreLocation, keyStorePassword, keyStoreType));
      }

      TrustManager tm = this.getTrustManager(config);
      if (tm != null) {
         sslContextBuilder.trustManager(tm);
      }

      sslContextBuilder.enableOcsp(config.getBoolean(this.getSslOcspEnabledProperty()));
      sslContextBuilder.protocols(this.getEnabledProtocols(config));
      Iterable<String> enabledCiphers = this.getCipherSuites(config);
      if (enabledCiphers != null) {
         sslContextBuilder.ciphers(enabledCiphers);
      }

      sslContextBuilder.sslProvider(this.getSslProvider(config));
      SslContext sslContext1 = sslContextBuilder.build();
      return this.getFipsMode(config) && this.isServerHostnameVerificationEnabled(config) ? this.addHostnameVerification(sslContext1, "Server") : sslContext1;
   }

   public SslContext createNettySslContextForServer(ZKConfig config) throws X509Exception.SSLContextException, X509Exception.KeyManagerException, X509Exception.TrustManagerException, SSLException {
      String keyStoreLocation = config.getProperty(this.getSslKeystoreLocationProperty(), "");
      String keyStorePassword = this.getPasswordFromConfigPropertyOrFile(config, this.getSslKeystorePasswdProperty(), this.getSslKeystorePasswdPathProperty());
      String keyStoreType = config.getProperty(this.getSslKeystoreTypeProperty());
      if (keyStoreLocation.isEmpty()) {
         throw new X509Exception.SSLContextException("Keystore is required for SSL server: " + this.getSslKeystoreLocationProperty());
      } else {
         KeyManager km = createKeyManager(keyStoreLocation, keyStorePassword, keyStoreType);
         return this.createNettySslContextForServer(config, km, this.getTrustManager(config));
      }
   }

   public SslContext createNettySslContextForServer(ZKConfig config, KeyManager keyManager, TrustManager trustManager) throws SSLException {
      SslContextBuilder sslContextBuilder = SslContextBuilder.forServer(keyManager);
      if (trustManager != null) {
         sslContextBuilder.trustManager(trustManager);
      }

      sslContextBuilder.enableOcsp(config.getBoolean(this.getSslOcspEnabledProperty()));
      sslContextBuilder.protocols(this.getEnabledProtocols(config));
      sslContextBuilder.clientAuth(this.getClientAuth(config).toNettyClientAuth());
      Iterable<String> enabledCiphers = this.getCipherSuites(config);
      if (enabledCiphers != null) {
         sslContextBuilder.ciphers(enabledCiphers);
      }

      sslContextBuilder.sslProvider(this.getSslProvider(config));
      SslContext sslContext1 = sslContextBuilder.build();
      return this.getFipsMode(config) && this.isClientHostnameVerificationEnabled(config) ? this.addHostnameVerification(sslContext1, "Client") : sslContext1;
   }

   private SslContext addHostnameVerification(SslContext sslContext, final String clientOrServer) {
      return new DelegatingSslContext(sslContext) {
         protected void initEngine(SSLEngine sslEngine) {
            SSLParameters sslParameters = sslEngine.getSSLParameters();
            sslParameters.setEndpointIdentificationAlgorithm("HTTPS");
            sslEngine.setSSLParameters(sslParameters);
            if (ClientX509Util.LOG.isDebugEnabled()) {
               ClientX509Util.LOG.debug("{} hostname verification: enabled HTTPS style endpoint identification algorithm", clientOrServer);
            }

         }
      };
   }

   private String[] getEnabledProtocols(ZKConfig config) {
      String enabledProtocolsInput = config.getProperty(this.getSslEnabledProtocolsProperty());
      return enabledProtocolsInput == null ? new String[]{config.getProperty(this.getSslProtocolProperty(), DEFAULT_PROTOCOL)} : enabledProtocolsInput.split(",");
   }

   private X509Util.ClientAuth getClientAuth(ZKConfig config) {
      return X509Util.ClientAuth.fromPropertyValue(config.getProperty(this.getSslClientAuthProperty()));
   }

   private Iterable getCipherSuites(ZKConfig config) {
      String cipherSuitesInput = config.getProperty(this.getSslCipherSuitesProperty());
      if (cipherSuitesInput == null) {
         return this.getSslProvider(config) != SslProvider.JDK ? null : Arrays.asList(X509Util.getDefaultCipherSuites());
      } else {
         return Arrays.asList(cipherSuitesInput.split(","));
      }
   }

   public SslProvider getSslProvider(ZKConfig config) {
      return SslProvider.valueOf(config.getProperty(this.getSslProviderProperty(), "JDK"));
   }

   private TrustManager getTrustManager(ZKConfig config) throws X509Exception.TrustManagerException {
      String trustStoreLocation = config.getProperty(this.getSslTruststoreLocationProperty(), "");
      String trustStorePassword = this.getPasswordFromConfigPropertyOrFile(config, this.getSslTruststorePasswdProperty(), this.getSslTruststorePasswdPathProperty());
      String trustStoreType = config.getProperty(this.getSslTruststoreTypeProperty());
      boolean sslCrlEnabled = config.getBoolean(this.getSslCrlEnabledProperty());
      boolean sslOcspEnabled = config.getBoolean(this.getSslOcspEnabledProperty());
      boolean sslServerHostnameVerificationEnabled = this.isServerHostnameVerificationEnabled(config);
      boolean sslClientHostnameVerificationEnabled = this.isClientHostnameVerificationEnabled(config);
      if (trustStoreLocation.isEmpty()) {
         LOG.warn("{} not specified", this.getSslTruststoreLocationProperty());
         return null;
      } else {
         return createTrustManager(trustStoreLocation, trustStorePassword, trustStoreType, sslCrlEnabled, sslOcspEnabled, sslServerHostnameVerificationEnabled, sslClientHostnameVerificationEnabled, this.getFipsMode(config));
      }
   }
}
