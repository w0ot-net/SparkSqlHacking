package org.apache.zookeeper.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.apache.zookeeper.server.util.VerifyingFileFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZKConfig {
   private static final Logger LOG = LoggerFactory.getLogger(ZKConfig.class);
   public static final String JUTE_MAXBUFFER = "jute.maxbuffer";
   public static final String KINIT_COMMAND = "zookeeper.kinit";
   public static final String JGSS_NATIVE = "sun.security.jgss.native";
   private final Map properties;

   public ZKConfig() {
      this.properties = new HashMap();
      this.init();
   }

   public ZKConfig(String configPath) throws QuorumPeerConfig.ConfigException {
      this(new File(configPath));
   }

   public ZKConfig(File configFile) throws QuorumPeerConfig.ConfigException {
      this();
      this.addConfiguration(configFile);
      LOG.info("ZK Config {}", this.properties);
   }

   private void init() {
      this.handleBackwardCompatibility();
   }

   protected void handleBackwardCompatibility() {
      this.properties.put("jute.maxbuffer", System.getProperty("jute.maxbuffer"));
      this.properties.put("zookeeper.kinit", System.getProperty("zookeeper.kinit"));
      this.properties.put("sun.security.jgss.native", System.getProperty("sun.security.jgss.native"));
      ClientX509Util clientX509Util = new ClientX509Util();

      try {
         this.putSSLProperties(clientX509Util);
         this.properties.put(clientX509Util.getSslAuthProviderProperty(), System.getProperty(clientX509Util.getSslAuthProviderProperty()));
         this.properties.put(clientX509Util.getSslProviderProperty(), System.getProperty(clientX509Util.getSslProviderProperty()));
      } catch (Throwable var7) {
         try {
            clientX509Util.close();
         } catch (Throwable var5) {
            var7.addSuppressed(var5);
         }

         throw var7;
      }

      clientX509Util.close();
      QuorumX509Util x509Util = new QuorumX509Util();

      try {
         this.putSSLProperties(x509Util);
      } catch (Throwable var6) {
         try {
            ((X509Util)x509Util).close();
         } catch (Throwable var4) {
            var6.addSuppressed(var4);
         }

         throw var6;
      }

      ((X509Util)x509Util).close();
   }

   private void putSSLProperties(X509Util x509Util) {
      this.properties.put(x509Util.getSslProtocolProperty(), System.getProperty(x509Util.getSslProtocolProperty()));
      this.properties.put(x509Util.getSslEnabledProtocolsProperty(), System.getProperty(x509Util.getSslEnabledProtocolsProperty()));
      this.properties.put(x509Util.getSslCipherSuitesProperty(), System.getProperty(x509Util.getSslCipherSuitesProperty()));
      this.properties.put(x509Util.getSslKeystoreLocationProperty(), System.getProperty(x509Util.getSslKeystoreLocationProperty()));
      this.properties.put(x509Util.getSslKeystorePasswdProperty(), System.getProperty(x509Util.getSslKeystorePasswdProperty()));
      this.properties.put(x509Util.getSslKeystorePasswdPathProperty(), System.getProperty(x509Util.getSslKeystorePasswdPathProperty()));
      this.properties.put(x509Util.getSslKeystoreTypeProperty(), System.getProperty(x509Util.getSslKeystoreTypeProperty()));
      this.properties.put(x509Util.getSslTruststoreLocationProperty(), System.getProperty(x509Util.getSslTruststoreLocationProperty()));
      this.properties.put(x509Util.getSslTruststorePasswdProperty(), System.getProperty(x509Util.getSslTruststorePasswdProperty()));
      this.properties.put(x509Util.getSslTruststorePasswdPathProperty(), System.getProperty(x509Util.getSslTruststorePasswdPathProperty()));
      this.properties.put(x509Util.getSslTruststoreTypeProperty(), System.getProperty(x509Util.getSslTruststoreTypeProperty()));
      this.properties.put(x509Util.getSslContextSupplierClassProperty(), System.getProperty(x509Util.getSslContextSupplierClassProperty()));
      this.properties.put(x509Util.getSslHostnameVerificationEnabledProperty(), System.getProperty(x509Util.getSslHostnameVerificationEnabledProperty()));
      this.properties.put(x509Util.getSslCrlEnabledProperty(), System.getProperty(x509Util.getSslCrlEnabledProperty()));
      this.properties.put(x509Util.getSslOcspEnabledProperty(), System.getProperty(x509Util.getSslOcspEnabledProperty()));
      this.properties.put(x509Util.getSslClientAuthProperty(), System.getProperty(x509Util.getSslClientAuthProperty()));
      this.properties.put(x509Util.getSslHandshakeDetectionTimeoutMillisProperty(), System.getProperty(x509Util.getSslHandshakeDetectionTimeoutMillisProperty()));
      this.properties.put(x509Util.getFipsModeProperty(), System.getProperty(x509Util.getFipsModeProperty()));
   }

   public String getProperty(String key) {
      return (String)this.properties.get(key);
   }

   public String getProperty(String key, String defaultValue) {
      String value = (String)this.properties.get(key);
      return value == null ? defaultValue : value;
   }

   public String getJaasConfKey() {
      return System.getProperty("java.security.auth.login.config");
   }

   public void setProperty(String key, String value) {
      if (null == key) {
         throw new IllegalArgumentException("property key is null.");
      } else {
         String oldValue = (String)this.properties.put(key, value);
         if (null != oldValue && !oldValue.equals(value)) {
            LOG.debug("key {}'s value {} is replaced with new value {}", new Object[]{key, oldValue, value});
         }

      }
   }

   public void addConfiguration(File configFile) throws QuorumPeerConfig.ConfigException {
      LOG.info("Reading configuration from: {}", configFile.getAbsolutePath());

      try {
         configFile = (new VerifyingFileFactory.Builder(LOG)).warnForRelativePath().failForNonExistingPath().build().validate(configFile);
         Properties cfg = new Properties();
         FileInputStream in = new FileInputStream(configFile);

         try {
            cfg.load(in);
         } finally {
            in.close();
         }

         this.parseProperties(cfg);
      } catch (IllegalArgumentException | IOException e) {
         LOG.error("Error while configuration from: {}", configFile.getAbsolutePath(), e);
         throw new QuorumPeerConfig.ConfigException("Error while processing " + configFile.getAbsolutePath(), e);
      }
   }

   public void addConfiguration(String configPath) throws QuorumPeerConfig.ConfigException {
      this.addConfiguration(new File(configPath));
   }

   private void parseProperties(Properties cfg) {
      for(Map.Entry entry : cfg.entrySet()) {
         String key = entry.getKey().toString().trim();
         String value = entry.getValue().toString().trim();
         this.setProperty(key, value);
      }

   }

   public boolean getBoolean(String key) {
      return this.getBoolean(key, false);
   }

   public boolean getBoolean(String key, boolean defaultValue) {
      String propertyValue = this.getProperty(key);
      return propertyValue == null ? defaultValue : Boolean.parseBoolean(propertyValue.trim());
   }

   public int getInt(String key, int defaultValue) {
      String value = this.getProperty(key);
      return value != null ? Integer.decode(value.trim()) : defaultValue;
   }
}
