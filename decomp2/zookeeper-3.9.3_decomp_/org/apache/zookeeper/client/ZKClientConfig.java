package org.apache.zookeeper.client;

import java.io.File;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.common.ZKConfig;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

@Public
public class ZKClientConfig extends ZKConfig {
   public static final String ZK_SASL_CLIENT_USERNAME = "zookeeper.sasl.client.username";
   public static final String ZK_SASL_CLIENT_USERNAME_DEFAULT = "zookeeper";
   public static final String ZK_SASL_CLIENT_CANONICALIZE_HOSTNAME = "zookeeper.sasl.client.canonicalize.hostname";
   public static final String ZK_SASL_CLIENT_CANONICALIZE_HOSTNAME_DEFAULT = "true";
   public static final String LOGIN_CONTEXT_NAME_KEY = "zookeeper.sasl.clientconfig";
   public static final String LOGIN_CONTEXT_NAME_KEY_DEFAULT = "Client";
   public static final String ENABLE_CLIENT_SASL_KEY = "zookeeper.sasl.client";
   public static final String ENABLE_CLIENT_SASL_DEFAULT = "true";
   public static final String ZOOKEEPER_SERVER_REALM = "zookeeper.server.realm";
   public static final String DISABLE_AUTO_WATCH_RESET = "zookeeper.disableAutoWatchReset";
   public static final String ZOOKEEPER_CLIENT_CNXN_SOCKET = "zookeeper.clientCnxnSocket";
   public static final String SECURE_CLIENT = "zookeeper.client.secure";
   public static final int CLIENT_MAX_PACKET_LENGTH_DEFAULT = 1048575;
   public static final String ZOOKEEPER_REQUEST_TIMEOUT = "zookeeper.request.timeout";
   public static final String ZOOKEEPER_SERVER_PRINCIPAL = "zookeeper.server.principal";
   public static final long ZOOKEEPER_REQUEST_TIMEOUT_DEFAULT = 0L;

   public ZKClientConfig() {
      this.initFromJavaSystemProperties();
   }

   public ZKClientConfig(File configFile) throws QuorumPeerConfig.ConfigException {
      super(configFile);
   }

   public ZKClientConfig(String configPath) throws QuorumPeerConfig.ConfigException {
      super(configPath);
   }

   private void initFromJavaSystemProperties() {
      this.setProperty("zookeeper.request.timeout", System.getProperty("zookeeper.request.timeout"));
      this.setProperty("zookeeper.server.principal", System.getProperty("zookeeper.server.principal"));
   }

   protected void handleBackwardCompatibility() {
      super.handleBackwardCompatibility();
      this.setProperty("zookeeper.sasl.client.username", System.getProperty("zookeeper.sasl.client.username"));
      this.setProperty("zookeeper.sasl.client.canonicalize.hostname", System.getProperty("zookeeper.sasl.client.canonicalize.hostname"));
      this.setProperty("zookeeper.sasl.clientconfig", System.getProperty("zookeeper.sasl.clientconfig"));
      this.setProperty("zookeeper.sasl.client", System.getProperty("zookeeper.sasl.client"));
      this.setProperty("zookeeper.server.realm", System.getProperty("zookeeper.server.realm"));
      this.setProperty("zookeeper.disableAutoWatchReset", System.getProperty("zookeeper.disableAutoWatchReset"));
      this.setProperty("zookeeper.clientCnxnSocket", System.getProperty("zookeeper.clientCnxnSocket"));
      this.setProperty("zookeeper.client.secure", System.getProperty("zookeeper.client.secure"));
   }

   public boolean isSaslClientEnabled() {
      return Boolean.valueOf(this.getProperty("zookeeper.sasl.client", "true"));
   }

   public long getLong(String key, long defaultValue) {
      String value = this.getProperty(key);
      return value != null ? Long.parseLong(value.trim()) : defaultValue;
   }
}
