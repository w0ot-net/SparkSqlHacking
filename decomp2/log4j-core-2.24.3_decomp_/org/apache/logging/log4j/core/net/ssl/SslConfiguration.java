package org.apache.logging.log4j.core.net.ssl;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.util.Objects;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.status.StatusLogger;

@Plugin(
   name = "Ssl",
   category = "Core",
   printObject = true
)
public class SslConfiguration {
   private static final StatusLogger LOGGER = StatusLogger.getLogger();
   private final KeyStoreConfiguration keyStoreConfig;
   private final TrustStoreConfiguration trustStoreConfig;
   private final SSLContext sslContext;
   private final String protocol;
   private final boolean verifyHostName;

   private SslConfiguration(final String protocol, final KeyStoreConfiguration keyStoreConfig, final TrustStoreConfiguration trustStoreConfig, final boolean verifyHostName) {
      this.keyStoreConfig = keyStoreConfig;
      this.trustStoreConfig = trustStoreConfig;
      this.protocol = protocol == null ? "TLS" : protocol;
      this.sslContext = this.createSslContext();
      this.verifyHostName = verifyHostName;
   }

   public void clearSecrets() {
      if (this.keyStoreConfig != null) {
         this.keyStoreConfig.clearSecrets();
      }

      if (this.trustStoreConfig != null) {
         this.trustStoreConfig.clearSecrets();
      }

   }

   public SSLSocketFactory getSslSocketFactory() {
      return this.sslContext.getSocketFactory();
   }

   public SSLServerSocketFactory getSslServerSocketFactory() {
      return this.sslContext.getServerSocketFactory();
   }

   private SSLContext createSslContext() {
      SSLContext context = null;

      try {
         context = this.createSslContextBasedOnConfiguration();
         LOGGER.debug("Creating SSLContext with the given parameters");
      } catch (TrustStoreConfigurationException var3) {
         context = this.createSslContextWithTrustStoreFailure();
      } catch (KeyStoreConfigurationException var4) {
         context = this.createSslContextWithKeyStoreFailure();
      }

      return context;
   }

   private SSLContext createSslContextWithTrustStoreFailure() {
      SSLContext context;
      try {
         context = this.createSslContextWithDefaultTrustManagerFactory();
         LOGGER.debug("Creating SSLContext with default truststore");
      } catch (KeyStoreConfigurationException var3) {
         context = this.createDefaultSslContext();
         LOGGER.debug("Creating SSLContext with default configuration");
      }

      return context;
   }

   private SSLContext createSslContextWithKeyStoreFailure() {
      SSLContext context;
      try {
         context = this.createSslContextWithDefaultKeyManagerFactory();
         LOGGER.debug("Creating SSLContext with default keystore");
      } catch (TrustStoreConfigurationException var3) {
         context = this.createDefaultSslContext();
         LOGGER.debug("Creating SSLContext with default configuration");
      }

      return context;
   }

   private SSLContext createSslContextBasedOnConfiguration() throws KeyStoreConfigurationException, TrustStoreConfigurationException {
      return this.createSslContext(false, false);
   }

   private SSLContext createSslContextWithDefaultKeyManagerFactory() throws TrustStoreConfigurationException {
      try {
         return this.createSslContext(true, false);
      } catch (KeyStoreConfigurationException var2) {
         LOGGER.debug("Exception occurred while using default keystore. This should be a BUG");
         return null;
      }
   }

   private SSLContext createSslContextWithDefaultTrustManagerFactory() throws KeyStoreConfigurationException {
      try {
         return this.createSslContext(false, true);
      } catch (TrustStoreConfigurationException var2) {
         LOGGER.debug("Exception occurred while using default truststore. This should be a BUG");
         return null;
      }
   }

   private SSLContext createDefaultSslContext() {
      try {
         return SSLContext.getDefault();
      } catch (NoSuchAlgorithmException e) {
         LOGGER.error("Failed to create an SSLContext with default configuration", e);
         return null;
      }
   }

   private SSLContext createSslContext(final boolean loadDefaultKeyManagerFactory, final boolean loadDefaultTrustManagerFactory) throws KeyStoreConfigurationException, TrustStoreConfigurationException {
      try {
         KeyManager[] kManagers = null;
         TrustManager[] tManagers = null;
         SSLContext newSslContext = SSLContext.getInstance(this.protocol);
         if (!loadDefaultKeyManagerFactory) {
            KeyManagerFactory kmFactory = this.loadKeyManagerFactory();
            kManagers = kmFactory.getKeyManagers();
         }

         if (!loadDefaultTrustManagerFactory) {
            TrustManagerFactory tmFactory = this.loadTrustManagerFactory();
            tManagers = tmFactory.getTrustManagers();
         }

         newSslContext.init(kManagers, tManagers, (SecureRandom)null);
         return newSslContext;
      } catch (NoSuchAlgorithmException e) {
         LOGGER.error("No Provider supports a TrustManagerFactorySpi implementation for the specified protocol", e);
         throw new TrustStoreConfigurationException(e);
      } catch (KeyManagementException e) {
         LOGGER.error("Failed to initialize the SSLContext", e);
         throw new KeyStoreConfigurationException(e);
      }
   }

   private TrustManagerFactory loadTrustManagerFactory() throws TrustStoreConfigurationException {
      if (this.trustStoreConfig == null) {
         throw new TrustStoreConfigurationException(new Exception("The trustStoreConfiguration is null"));
      } else {
         try {
            return this.trustStoreConfig.initTrustManagerFactory();
         } catch (NoSuchAlgorithmException e) {
            LOGGER.error("The specified algorithm is not available from the specified provider", e);
            throw new TrustStoreConfigurationException(e);
         } catch (KeyStoreException e) {
            LOGGER.error("Failed to initialize the TrustManagerFactory", e);
            throw new TrustStoreConfigurationException(e);
         }
      }
   }

   private KeyManagerFactory loadKeyManagerFactory() throws KeyStoreConfigurationException {
      if (this.keyStoreConfig == null) {
         throw new KeyStoreConfigurationException(new Exception("The keyStoreConfiguration is null"));
      } else {
         try {
            return this.keyStoreConfig.initKeyManagerFactory();
         } catch (NoSuchAlgorithmException e) {
            LOGGER.error("The specified algorithm is not available from the specified provider", e);
            throw new KeyStoreConfigurationException(e);
         } catch (KeyStoreException e) {
            LOGGER.error("Failed to initialize the TrustManagerFactory", e);
            throw new KeyStoreConfigurationException(e);
         } catch (UnrecoverableKeyException e) {
            LOGGER.error("The key cannot be recovered (e.g. the given password is wrong)", e);
            throw new KeyStoreConfigurationException(e);
         }
      }
   }

   @PluginFactory
   public static SslConfiguration createSSLConfiguration(@PluginAttribute("protocol") final String protocol, @PluginElement("KeyStore") final KeyStoreConfiguration keyStoreConfig, @PluginElement("TrustStore") final TrustStoreConfiguration trustStoreConfig) {
      return new SslConfiguration(protocol, keyStoreConfig, trustStoreConfig, false);
   }

   public static SslConfiguration createSSLConfiguration(@PluginAttribute("protocol") final String protocol, @PluginElement("KeyStore") final KeyStoreConfiguration keyStoreConfig, @PluginElement("TrustStore") final TrustStoreConfiguration trustStoreConfig, @PluginAttribute("verifyHostName") final boolean verifyHostName) {
      return new SslConfiguration(protocol, keyStoreConfig, trustStoreConfig, verifyHostName);
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.keyStoreConfig, this.protocol, this.sslContext, this.trustStoreConfig});
   }

   public boolean equals(final Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         SslConfiguration other = (SslConfiguration)obj;
         if (!Objects.equals(this.keyStoreConfig, other.keyStoreConfig)) {
            return false;
         } else if (!Objects.equals(this.protocol, other.protocol)) {
            return false;
         } else if (!Objects.equals(this.sslContext, other.sslContext)) {
            return false;
         } else {
            return Objects.equals(this.trustStoreConfig, other.trustStoreConfig);
         }
      }
   }

   public KeyStoreConfiguration getKeyStoreConfig() {
      return this.keyStoreConfig;
   }

   public TrustStoreConfiguration getTrustStoreConfig() {
      return this.trustStoreConfig;
   }

   public SSLContext getSslContext() {
      return this.sslContext;
   }

   public String getProtocol() {
      return this.protocol;
   }

   public boolean isVerifyHostName() {
      return this.verifyHostName;
   }
}
