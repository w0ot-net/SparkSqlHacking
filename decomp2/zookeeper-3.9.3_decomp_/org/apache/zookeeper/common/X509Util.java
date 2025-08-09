package org.apache.zookeeper.common;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.X509CertSelector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;
import org.apache.zookeeper.server.auth.ProviderRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class X509Util implements Closeable, AutoCloseable {
   private static final Logger LOG = LoggerFactory.getLogger(X509Util.class);
   private static final String REJECT_CLIENT_RENEGOTIATION_PROPERTY = "jdk.tls.rejectClientInitiatedRenegotiation";
   private static final String FIPS_MODE_PROPERTY = "zookeeper.fips-mode";
   public static final String TLS_1_1 = "TLSv1.1";
   public static final String TLS_1_2 = "TLSv1.2";
   public static final String TLS_1_3 = "TLSv1.3";
   public static final String DEFAULT_PROTOCOL;
   private static final String[] DEFAULT_CIPHERS_JAVA8;
   private static final String[] DEFAULT_CIPHERS_JAVA9;
   public static final int DEFAULT_HANDSHAKE_DETECTION_TIMEOUT_MILLIS = 5000;
   private final String sslProtocolProperty = this.getConfigPrefix() + "protocol";
   private final String sslEnabledProtocolsProperty = this.getConfigPrefix() + "enabledProtocols";
   private final String cipherSuitesProperty = this.getConfigPrefix() + "ciphersuites";
   private final String sslKeystoreLocationProperty = this.getConfigPrefix() + "keyStore.location";
   private final String sslKeystorePasswdProperty = this.getConfigPrefix() + "keyStore.password";
   private final String sslKeystorePasswdPathProperty = this.getConfigPrefix() + "keyStore.passwordPath";
   private final String sslKeystoreTypeProperty = this.getConfigPrefix() + "keyStore.type";
   private final String sslTruststoreLocationProperty = this.getConfigPrefix() + "trustStore.location";
   private final String sslTruststorePasswdProperty = this.getConfigPrefix() + "trustStore.password";
   private final String sslTruststorePasswdPathProperty = this.getConfigPrefix() + "trustStore.passwordPath";
   private final String sslTruststoreTypeProperty = this.getConfigPrefix() + "trustStore.type";
   private final String sslContextSupplierClassProperty = this.getConfigPrefix() + "context.supplier.class";
   private final String sslHostnameVerificationEnabledProperty = this.getConfigPrefix() + "hostnameVerification";
   private final String sslCrlEnabledProperty = this.getConfigPrefix() + "crl";
   private final String sslOcspEnabledProperty = this.getConfigPrefix() + "ocsp";
   private final String sslClientAuthProperty = this.getConfigPrefix() + "clientAuth";
   private final String sslHandshakeDetectionTimeoutMillisProperty = this.getConfigPrefix() + "handshakeDetectionTimeoutMillis";
   private final AtomicReference defaultSSLContextAndOptions = new AtomicReference((Object)null);
   private FileChangeWatcher keyStoreFileWatcher;
   private FileChangeWatcher trustStoreFileWatcher;

   private static String defaultTlsProtocol() {
      String defaultProtocol = "TLSv1.2";
      List<String> supported = new ArrayList();

      try {
         supported = Arrays.asList(SSLContext.getDefault().getSupportedSSLParameters().getProtocols());
         if (supported.contains("TLSv1.3")) {
            defaultProtocol = "TLSv1.3";
         }
      } catch (NoSuchAlgorithmException var3) {
      }

      LOG.info("Default TLS protocol is {}, supported TLS protocols are {}", defaultProtocol, supported);
      return defaultProtocol;
   }

   private static String[] getTLSv13Ciphers() {
      return new String[]{"TLS_AES_256_GCM_SHA384", "TLS_AES_128_GCM_SHA256", "TLS_CHACHA20_POLY1305_SHA256"};
   }

   private static String[] getGCMCiphers() {
      return new String[]{"TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384"};
   }

   private static String[] getCBCCiphers() {
      return new String[]{"TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA"};
   }

   private static String[] getSupportedCiphers(String[]... cipherLists) {
      List<String> supported = Arrays.asList(((SSLServerSocketFactory)SSLServerSocketFactory.getDefault()).getSupportedCipherSuites());
      Stream var10000 = Arrays.stream(cipherLists).flatMap(Arrays::stream);
      Objects.requireNonNull(supported);
      return (String[])((List)var10000.filter(supported::contains).collect(Collectors.toList())).toArray(new String[0]);
   }

   public X509Util() {
      this.keyStoreFileWatcher = this.trustStoreFileWatcher = null;
   }

   protected abstract String getConfigPrefix();

   protected abstract boolean shouldVerifyClientHostname();

   public String getSslProtocolProperty() {
      return this.sslProtocolProperty;
   }

   public String getSslEnabledProtocolsProperty() {
      return this.sslEnabledProtocolsProperty;
   }

   public String getCipherSuitesProperty() {
      return this.cipherSuitesProperty;
   }

   public String getSslKeystoreLocationProperty() {
      return this.sslKeystoreLocationProperty;
   }

   public String getSslCipherSuitesProperty() {
      return this.cipherSuitesProperty;
   }

   public String getSslKeystorePasswdProperty() {
      return this.sslKeystorePasswdProperty;
   }

   public String getSslKeystorePasswdPathProperty() {
      return this.sslKeystorePasswdPathProperty;
   }

   public String getSslKeystoreTypeProperty() {
      return this.sslKeystoreTypeProperty;
   }

   public String getSslTruststoreLocationProperty() {
      return this.sslTruststoreLocationProperty;
   }

   public String getSslTruststorePasswdProperty() {
      return this.sslTruststorePasswdProperty;
   }

   public String getSslTruststorePasswdPathProperty() {
      return this.sslTruststorePasswdPathProperty;
   }

   public String getSslTruststoreTypeProperty() {
      return this.sslTruststoreTypeProperty;
   }

   public String getSslContextSupplierClassProperty() {
      return this.sslContextSupplierClassProperty;
   }

   public String getSslHostnameVerificationEnabledProperty() {
      return this.sslHostnameVerificationEnabledProperty;
   }

   public String getSslCrlEnabledProperty() {
      return this.sslCrlEnabledProperty;
   }

   public String getSslOcspEnabledProperty() {
      return this.sslOcspEnabledProperty;
   }

   public String getSslClientAuthProperty() {
      return this.sslClientAuthProperty;
   }

   public String getSslHandshakeDetectionTimeoutMillisProperty() {
      return this.sslHandshakeDetectionTimeoutMillisProperty;
   }

   public String getFipsModeProperty() {
      return "zookeeper.fips-mode";
   }

   public boolean getFipsMode(ZKConfig config) {
      return config.getBoolean("zookeeper.fips-mode", true);
   }

   public boolean isServerHostnameVerificationEnabled(ZKConfig config) {
      return config.getBoolean(this.getSslHostnameVerificationEnabledProperty(), true);
   }

   public boolean isClientHostnameVerificationEnabled(ZKConfig config) {
      return this.isServerHostnameVerificationEnabled(config) && this.shouldVerifyClientHostname();
   }

   public SSLContext getDefaultSSLContext() throws X509Exception.SSLContextException {
      return this.getDefaultSSLContextAndOptions().getSSLContext();
   }

   public SSLContext createSSLContext(ZKConfig config) throws X509Exception.SSLContextException {
      return this.createSSLContextAndOptions(config).getSSLContext();
   }

   public SSLContextAndOptions getDefaultSSLContextAndOptions() throws X509Exception.SSLContextException {
      SSLContextAndOptions result = (SSLContextAndOptions)this.defaultSSLContextAndOptions.get();
      if (result == null) {
         result = this.createSSLContextAndOptions();
         if (!this.defaultSSLContextAndOptions.compareAndSet((Object)null, result)) {
            result = (SSLContextAndOptions)this.defaultSSLContextAndOptions.get();
         }
      }

      return result;
   }

   private void resetDefaultSSLContextAndOptions() throws X509Exception.SSLContextException {
      SSLContextAndOptions newContext = this.createSSLContextAndOptions();
      this.defaultSSLContextAndOptions.set(newContext);
      if (Boolean.getBoolean("zookeeper.client.certReload")) {
         ProviderRegistry.addOrUpdateProvider("zookeeper.authProvider.x509");
      }

   }

   private SSLContextAndOptions createSSLContextAndOptions() throws X509Exception.SSLContextException {
      return this.createSSLContextAndOptions(new ZKConfig());
   }

   public int getSslHandshakeTimeoutMillis() {
      try {
         SSLContextAndOptions ctx = this.getDefaultSSLContextAndOptions();
         return ctx.getHandshakeDetectionTimeoutMillis();
      } catch (X509Exception.SSLContextException e) {
         LOG.error("Error creating SSL context and options", e);
         return 5000;
      } catch (Exception e) {
         LOG.error("Error parsing config property {}", this.getSslHandshakeDetectionTimeoutMillisProperty(), e);
         return 5000;
      }
   }

   public SSLContextAndOptions createSSLContextAndOptions(ZKConfig config) throws X509Exception.SSLContextException {
      String supplierContextClassName = config.getProperty(this.sslContextSupplierClassProperty);
      if (supplierContextClassName != null) {
         LOG.debug("Loading SSLContext supplier from property '{}'", this.sslContextSupplierClassProperty);

         try {
            Class<?> sslContextClass = Class.forName(supplierContextClassName);
            Supplier<SSLContext> sslContextSupplier = (Supplier)sslContextClass.getConstructor().newInstance();
            return new SSLContextAndOptions(this, config, (SSLContext)sslContextSupplier.get());
         } catch (ClassCastException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new X509Exception.SSLContextException("Could not retrieve the SSLContext from supplier source '" + supplierContextClassName + "' provided in the property '" + this.sslContextSupplierClassProperty + "'", e);
         }
      } else {
         return this.createSSLContextAndOptionsFromConfig(config);
      }
   }

   public SSLContextAndOptions createSSLContextAndOptionsFromConfig(ZKConfig config) throws X509Exception.SSLContextException {
      KeyManager[] keyManagers = null;
      TrustManager[] trustManagers = null;
      String keyStoreLocationProp = config.getProperty(this.sslKeystoreLocationProperty, "");
      String keyStorePasswordProp = this.getPasswordFromConfigPropertyOrFile(config, this.sslKeystorePasswdProperty, this.sslKeystorePasswdPathProperty);
      String keyStoreTypeProp = config.getProperty(this.sslKeystoreTypeProperty);
      if (keyStoreLocationProp.isEmpty()) {
         LOG.warn("{} not specified", this.getSslKeystoreLocationProperty());
      } else {
         try {
            keyManagers = new KeyManager[]{createKeyManager(keyStoreLocationProp, keyStorePasswordProp, keyStoreTypeProp)};
         } catch (X509Exception.KeyManagerException keyManagerException) {
            throw new X509Exception.SSLContextException("Failed to create KeyManager", keyManagerException);
         } catch (IllegalArgumentException e) {
            throw new X509Exception.SSLContextException("Bad value for " + this.sslKeystoreTypeProperty + ": " + keyStoreTypeProp, e);
         }
      }

      String trustStoreLocationProp = config.getProperty(this.sslTruststoreLocationProperty, "");
      String trustStorePasswordProp = this.getPasswordFromConfigPropertyOrFile(config, this.sslTruststorePasswdProperty, this.sslTruststorePasswdPathProperty);
      String trustStoreTypeProp = config.getProperty(this.sslTruststoreTypeProperty);
      boolean sslCrlEnabled = config.getBoolean(this.sslCrlEnabledProperty);
      boolean sslOcspEnabled = config.getBoolean(this.sslOcspEnabledProperty);
      boolean sslServerHostnameVerificationEnabled = this.isServerHostnameVerificationEnabled(config);
      boolean sslClientHostnameVerificationEnabled = this.isClientHostnameVerificationEnabled(config);
      boolean fipsMode = this.getFipsMode(config);
      if (trustStoreLocationProp.isEmpty()) {
         LOG.warn("{} not specified", this.getSslTruststoreLocationProperty());
      } else {
         try {
            trustManagers = new TrustManager[]{createTrustManager(trustStoreLocationProp, trustStorePasswordProp, trustStoreTypeProp, sslCrlEnabled, sslOcspEnabled, sslServerHostnameVerificationEnabled, sslClientHostnameVerificationEnabled, fipsMode)};
         } catch (X509Exception.TrustManagerException trustManagerException) {
            throw new X509Exception.SSLContextException("Failed to create TrustManager", trustManagerException);
         } catch (IllegalArgumentException e) {
            throw new X509Exception.SSLContextException("Bad value for " + this.sslTruststoreTypeProperty + ": " + trustStoreTypeProp, e);
         }
      }

      String protocol = config.getProperty(this.sslProtocolProperty, DEFAULT_PROTOCOL);

      try {
         SSLContext sslContext = SSLContext.getInstance(protocol);
         sslContext.init(keyManagers, trustManagers, (SecureRandom)null);
         return new SSLContextAndOptions(this, config, sslContext);
      } catch (KeyManagementException | NoSuchAlgorithmException sslContextInitException) {
         throw new X509Exception.SSLContextException(sslContextInitException);
      }
   }

   public static KeyStore loadKeyStore(String keyStoreLocation, String keyStorePassword, String keyStoreTypeProp) throws IOException, GeneralSecurityException {
      KeyStoreFileType storeFileType = KeyStoreFileType.fromPropertyValueOrFileName(keyStoreTypeProp, keyStoreLocation);
      return FileKeyStoreLoaderBuilderProvider.getBuilderForKeyStoreFileType(storeFileType).setKeyStorePath(keyStoreLocation).setKeyStorePassword(keyStorePassword).build().loadKeyStore();
   }

   public static KeyStore loadTrustStore(String trustStoreLocation, String trustStorePassword, String trustStoreTypeProp) throws IOException, GeneralSecurityException {
      KeyStoreFileType storeFileType = KeyStoreFileType.fromPropertyValueOrFileName(trustStoreTypeProp, trustStoreLocation);
      return FileKeyStoreLoaderBuilderProvider.getBuilderForKeyStoreFileType(storeFileType).setTrustStorePath(trustStoreLocation).setTrustStorePassword(trustStorePassword).build().loadTrustStore();
   }

   public String getPasswordFromConfigPropertyOrFile(ZKConfig config, String propertyName, String pathPropertyName) {
      String value = config.getProperty(propertyName, "");
      String pathProperty = config.getProperty(pathPropertyName, "");
      if (!pathProperty.isEmpty()) {
         value = String.valueOf(SecretUtils.readSecret(pathProperty));
      }

      return value;
   }

   public static X509KeyManager createKeyManager(String keyStoreLocation, String keyStorePassword, String keyStoreTypeProp) throws X509Exception.KeyManagerException {
      if (keyStorePassword == null) {
         keyStorePassword = "";
      }

      try {
         KeyStore ks = loadKeyStore(keyStoreLocation, keyStorePassword, keyStoreTypeProp);
         KeyManagerFactory kmf = KeyManagerFactory.getInstance("PKIX");
         kmf.init(ks, keyStorePassword.toCharArray());

         for(KeyManager km : kmf.getKeyManagers()) {
            if (km instanceof X509KeyManager) {
               return (X509KeyManager)km;
            }
         }

         throw new X509Exception.KeyManagerException("Couldn't find X509KeyManager");
      } catch (GeneralSecurityException | IllegalArgumentException | IOException e) {
         throw new X509Exception.KeyManagerException(e);
      }
   }

   public static X509TrustManager createTrustManager(String trustStoreLocation, String trustStorePassword, String trustStoreTypeProp, boolean crlEnabled, boolean ocspEnabled, boolean serverHostnameVerificationEnabled, boolean clientHostnameVerificationEnabled, boolean fipsMode) throws X509Exception.TrustManagerException {
      if (trustStorePassword == null) {
         trustStorePassword = "";
      }

      try {
         KeyStore ts = loadTrustStore(trustStoreLocation, trustStorePassword, trustStoreTypeProp);
         PKIXBuilderParameters pbParams = new PKIXBuilderParameters(ts, new X509CertSelector());
         if (!crlEnabled && !ocspEnabled) {
            pbParams.setRevocationEnabled(false);
         } else {
            pbParams.setRevocationEnabled(true);
            System.setProperty("com.sun.net.ssl.checkRevocation", "true");
            System.setProperty("com.sun.security.enableCRLDP", "true");
            if (ocspEnabled) {
               Security.setProperty("ocsp.enable", "true");
            }
         }

         TrustManagerFactory tmf = TrustManagerFactory.getInstance("PKIX");
         tmf.init(new CertPathTrustManagerParameters(pbParams));

         for(TrustManager tm : tmf.getTrustManagers()) {
            if (tm instanceof X509ExtendedTrustManager) {
               if (fipsMode) {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("FIPS mode is ON: selecting standard x509 trust manager {}", tm);
                  }

                  return (X509TrustManager)tm;
               }

               if (LOG.isDebugEnabled()) {
                  LOG.debug("FIPS mode is OFF: creating ZKTrustManager");
               }

               return new ZKTrustManager((X509ExtendedTrustManager)tm, serverHostnameVerificationEnabled, clientHostnameVerificationEnabled);
            }
         }

         throw new X509Exception.TrustManagerException("Couldn't find X509TrustManager");
      } catch (GeneralSecurityException | IllegalArgumentException | IOException e) {
         throw new X509Exception.TrustManagerException(e);
      }
   }

   public SSLSocket createSSLSocket() throws X509Exception, IOException {
      return this.getDefaultSSLContextAndOptions().createSSLSocket();
   }

   public SSLSocket createSSLSocket(Socket socket, byte[] pushbackBytes) throws X509Exception, IOException {
      return this.getDefaultSSLContextAndOptions().createSSLSocket(socket, pushbackBytes);
   }

   public SSLServerSocket createSSLServerSocket() throws X509Exception, IOException {
      return this.getDefaultSSLContextAndOptions().createSSLServerSocket();
   }

   public SSLServerSocket createSSLServerSocket(int port) throws X509Exception, IOException {
      return this.getDefaultSSLContextAndOptions().createSSLServerSocket(port);
   }

   static String[] getDefaultCipherSuites() {
      return getDefaultCipherSuitesForJavaVersion(System.getProperty("java.specification.version"));
   }

   static String[] getDefaultCipherSuitesForJavaVersion(String javaVersion) {
      Objects.requireNonNull(javaVersion);
      if (javaVersion.matches("\\d+")) {
         LOG.debug("Using Java9+ optimized cipher suites for Java version {}", javaVersion);
         return DEFAULT_CIPHERS_JAVA9;
      } else if (javaVersion.startsWith("1.")) {
         LOG.debug("Using Java8 optimized cipher suites for Java version {}", javaVersion);
         return DEFAULT_CIPHERS_JAVA8;
      } else {
         LOG.debug("Could not parse java version {}, using Java8 optimized cipher suites", javaVersion);
         return DEFAULT_CIPHERS_JAVA8;
      }
   }

   private FileChangeWatcher newFileChangeWatcher(String fileLocation) throws IOException {
      if (fileLocation != null && !fileLocation.isEmpty()) {
         Path filePath = Paths.get(fileLocation).toAbsolutePath();
         Path parentPath = filePath.getParent();
         if (parentPath == null) {
            throw new IOException("Key/trust store path does not have a parent: " + filePath);
         } else {
            return new FileChangeWatcher(parentPath, (watchEvent) -> this.handleWatchEvent(filePath, watchEvent));
         }
      } else {
         return null;
      }
   }

   public void enableCertFileReloading() throws IOException {
      LOG.info("enabling cert file reloading");
      ZKConfig config = new ZKConfig();
      FileChangeWatcher newKeyStoreFileWatcher = this.newFileChangeWatcher(config.getProperty(this.sslKeystoreLocationProperty));
      if (newKeyStoreFileWatcher != null) {
         if (this.keyStoreFileWatcher != null) {
            this.keyStoreFileWatcher.stop();
         }

         this.keyStoreFileWatcher = newKeyStoreFileWatcher;
         this.keyStoreFileWatcher.start();
      }

      FileChangeWatcher newTrustStoreFileWatcher = this.newFileChangeWatcher(config.getProperty(this.sslTruststoreLocationProperty));
      if (newTrustStoreFileWatcher != null) {
         if (this.trustStoreFileWatcher != null) {
            this.trustStoreFileWatcher.stop();
         }

         this.trustStoreFileWatcher = newTrustStoreFileWatcher;
         this.trustStoreFileWatcher.start();
      }

   }

   public void close() {
      this.defaultSSLContextAndOptions.set((Object)null);
      if (this.keyStoreFileWatcher != null) {
         this.keyStoreFileWatcher.stop();
         this.keyStoreFileWatcher = null;
      }

      if (this.trustStoreFileWatcher != null) {
         this.trustStoreFileWatcher.stop();
         this.trustStoreFileWatcher = null;
      }

   }

   private void handleWatchEvent(Path filePath, WatchEvent event) {
      boolean shouldResetContext = false;
      Path dirPath = filePath.getParent();
      if (event.kind().equals(StandardWatchEventKinds.OVERFLOW)) {
         shouldResetContext = true;
      } else if (event.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY) || event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {
         Path eventFilePath = dirPath.resolve((Path)event.context());
         if (filePath.equals(eventFilePath)) {
            shouldResetContext = true;
         }
      }

      if (shouldResetContext) {
         LOG.debug("Attempting to reset default SSL context after receiving watch event: {} with context: {}", event.kind(), event.context());

         try {
            this.resetDefaultSSLContextAndOptions();
         } catch (X509Exception.SSLContextException e) {
            throw new RuntimeException(e);
         }
      } else {
         LOG.debug("Ignoring watch event and keeping previous default SSL context. Event kind: {} with context: {}", event.kind(), event.context());
      }

   }

   static {
      if (System.getProperty("jdk.tls.rejectClientInitiatedRenegotiation") == null) {
         LOG.info("Setting -D {}=true to disable client-initiated TLS renegotiation", "jdk.tls.rejectClientInitiatedRenegotiation");
         System.setProperty("jdk.tls.rejectClientInitiatedRenegotiation", Boolean.TRUE.toString());
      }

      DEFAULT_PROTOCOL = defaultTlsProtocol();
      DEFAULT_CIPHERS_JAVA8 = getSupportedCiphers(getCBCCiphers(), getGCMCiphers(), getTLSv13Ciphers());
      DEFAULT_CIPHERS_JAVA9 = getSupportedCiphers(getGCMCiphers(), getCBCCiphers(), getTLSv13Ciphers());
   }

   public static enum ClientAuth {
      NONE(io.netty.handler.ssl.ClientAuth.NONE),
      WANT(io.netty.handler.ssl.ClientAuth.OPTIONAL),
      NEED(io.netty.handler.ssl.ClientAuth.REQUIRE);

      private final io.netty.handler.ssl.ClientAuth nettyAuth;

      private ClientAuth(io.netty.handler.ssl.ClientAuth nettyAuth) {
         this.nettyAuth = nettyAuth;
      }

      public static ClientAuth fromPropertyValue(String prop) {
         return prop != null && prop.length() != 0 ? valueOf(prop.toUpperCase()) : NEED;
      }

      public io.netty.handler.ssl.ClientAuth toNettyClientAuth() {
         return this.nettyAuth;
      }
   }
}
