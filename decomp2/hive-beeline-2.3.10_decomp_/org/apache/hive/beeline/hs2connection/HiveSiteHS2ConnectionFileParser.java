package org.apache.hive.beeline.hs2connection;

import com.google.common.annotations.VisibleForTesting;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.ServerUtils;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveSiteHS2ConnectionFileParser implements HS2ConnectionFileParser {
   private Configuration conf = new Configuration();
   private final URL hiveSiteURI = HiveConf.getHiveSiteLocation();
   private static final String TRUSTSTORE_PASS_PROP = "javax.net.ssl.trustStorePassword";
   private static final String TRUSTSTORE_PROP = "javax.net.ssl.trustStore";
   private static final Logger log = LoggerFactory.getLogger(HiveSiteHS2ConnectionFileParser.class);

   public HiveSiteHS2ConnectionFileParser() {
      if (this.hiveSiteURI == null) {
         log.debug("hive-site.xml not found for constructing the connection URL");
      } else {
         log.info("Using hive-site.xml at " + this.hiveSiteURI);
         this.conf.addResource(this.hiveSiteURI);
      }

   }

   @VisibleForTesting
   void setHiveConf(HiveConf hiveConf) {
      this.conf = hiveConf;
   }

   public Properties getConnectionProperties() throws BeelineHS2ConnectionFileParseException {
      Properties props = new Properties();
      if (!this.configExists()) {
         return props;
      } else {
         props.setProperty("url_prefix", "jdbc:hive2://");
         this.addHosts(props);
         this.addSSL(props);
         this.addKerberos(props);
         this.addHttp(props);
         return props;
      }
   }

   private void addSSL(Properties props) {
      if (HiveConf.getBoolVar(this.conf, ConfVars.HIVE_SERVER2_USE_SSL)) {
         props.setProperty("ssl", "true");
         String truststore = System.getenv("javax.net.ssl.trustStore");
         if (truststore != null && truststore.isEmpty()) {
            props.setProperty("sslTruststore", truststore);
         }

         String trustStorePassword = System.getenv("javax.net.ssl.trustStorePassword");
         if (trustStorePassword != null && !trustStorePassword.isEmpty()) {
            props.setProperty("trustStorePassword", trustStorePassword);
         }

         String saslQop = HiveConf.getVar(this.conf, ConfVars.HIVE_SERVER2_THRIFT_SASL_QOP);
         if (!"auth".equalsIgnoreCase(saslQop)) {
            props.setProperty("sasl.qop", saslQop);
         }

      }
   }

   private void addKerberos(Properties props) {
      if ("KERBEROS".equals(HiveConf.getVar(this.conf, ConfVars.HIVE_SERVER2_AUTHENTICATION))) {
         props.setProperty("principal", HiveConf.getVar(this.conf, ConfVars.HIVE_SERVER2_KERBEROS_PRINCIPAL));
      }

   }

   private void addHttp(Properties props) {
      if ("http".equalsIgnoreCase(HiveConf.getVar(this.conf, ConfVars.HIVE_SERVER2_TRANSPORT_MODE))) {
         props.setProperty("transportMode", "http");
         props.setProperty("httpPath", HiveConf.getVar(this.conf, ConfVars.HIVE_SERVER2_THRIFT_HTTP_PATH));
      }
   }

   private void addHosts(Properties props) throws BeelineHS2ConnectionFileParseException {
      if (HiveConf.getBoolVar(this.conf, ConfVars.HIVE_SERVER2_SUPPORT_DYNAMIC_SERVICE_DISCOVERY)) {
         this.addZKServiceDiscoveryHosts(props);
      } else {
         this.addDefaultHS2Hosts(props);
      }

   }

   private void addZKServiceDiscoveryHosts(Properties props) throws BeelineHS2ConnectionFileParseException {
      props.setProperty("serviceDiscoveryMode", "zooKeeper");
      props.setProperty("zooKeeperNamespace", HiveConf.getVar(this.conf, ConfVars.HIVE_SERVER2_ZOOKEEPER_NAMESPACE));
      props.setProperty("hosts", HiveConf.getVar(this.conf, ConfVars.HIVE_ZOOKEEPER_QUORUM));
   }

   private void addDefaultHS2Hosts(Properties props) throws BeelineHS2ConnectionFileParseException {
      String hiveHost = System.getenv("HIVE_SERVER2_THRIFT_BIND_HOST");
      if (hiveHost == null) {
         hiveHost = HiveConf.getVar(this.conf, ConfVars.HIVE_SERVER2_THRIFT_BIND_HOST);
      }

      InetAddress serverIPAddress;
      try {
         serverIPAddress = ServerUtils.getHostAddress(hiveHost);
      } catch (UnknownHostException e) {
         throw new BeelineHS2ConnectionFileParseException(e.getMessage(), e);
      }

      int portNum = this.getPortNum("http".equalsIgnoreCase(HiveConf.getVar(this.conf, ConfVars.HIVE_SERVER2_TRANSPORT_MODE)));
      props.setProperty("hosts", serverIPAddress.getHostName() + ":" + portNum);
   }

   private int getPortNum(boolean isHttp) {
      int portNum;
      if (isHttp) {
         String portString = System.getenv("HIVE_SERVER2_THRIFT_HTTP_PORT");
         if (portString != null) {
            portNum = Integer.parseInt(portString);
         } else {
            portNum = HiveConf.getIntVar(this.conf, ConfVars.HIVE_SERVER2_THRIFT_HTTP_PORT);
         }
      } else {
         String portString = System.getenv("HIVE_SERVER2_THRIFT_PORT");
         if (portString != null) {
            portNum = Integer.parseInt(portString);
         } else {
            portNum = HiveConf.getIntVar(this.conf, ConfVars.HIVE_SERVER2_THRIFT_PORT);
         }
      }

      return portNum;
   }

   public boolean configExists() {
      return this.hiveSiteURI != null;
   }
}
