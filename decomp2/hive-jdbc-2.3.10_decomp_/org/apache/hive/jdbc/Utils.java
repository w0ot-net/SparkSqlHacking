package org.apache.hive.jdbc;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.rpc.thrift.TStatus;
import org.apache.hive.service.rpc.thrift.TStatusCode;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
   static final Logger LOG = LoggerFactory.getLogger(Utils.class.getName());
   public static final String URL_PREFIX = "jdbc:hive2://";
   static final String DEFAULT_PORT = "10000";
   static final String DEFAULT_DATABASE = "default";
   private static final String URI_JDBC_PREFIX = "jdbc:";
   private static final String URI_HIVE_PREFIX = "hive2:";
   static final String HIVE_SERVER2_RETRY_KEY = "hive.server2.retryserver";
   static final String HIVE_SERVER2_RETRY_TRUE = "true";
   static final String HIVE_SERVER2_RETRY_FALSE = "false";

   static void verifySuccessWithInfo(TStatus status) throws SQLException {
      verifySuccess(status, true);
   }

   static void verifySuccess(TStatus status) throws SQLException {
      verifySuccess(status, false);
   }

   static void verifySuccess(TStatus status, boolean withInfo) throws SQLException {
      if (status.getStatusCode() != TStatusCode.SUCCESS_STATUS && (!withInfo || status.getStatusCode() != TStatusCode.SUCCESS_WITH_INFO_STATUS)) {
         throw new HiveSQLException(status);
      }
   }

   public static JdbcConnectionParams parseURL(String uri) throws JdbcUriParseException, SQLException, ZooKeeperHiveClientException {
      return parseURL(uri, new Properties());
   }

   static JdbcConnectionParams parseURL(String uri, Properties info) throws JdbcUriParseException, SQLException, ZooKeeperHiveClientException {
      JdbcConnectionParams connParams = new JdbcConnectionParams();
      if (!uri.startsWith("jdbc:hive2://")) {
         throw new JdbcUriParseException("Bad URL format: Missing prefix jdbc:hive2://");
      } else if (uri.equalsIgnoreCase("jdbc:hive2://")) {
         connParams.setEmbeddedMode(true);
         return connParams;
      } else {
         String dummyAuthorityString = "dummyhost:00000";
         String suppliedAuthorities = getAuthorities(uri, connParams);
         if (suppliedAuthorities != null && !suppliedAuthorities.isEmpty()) {
            LOG.info("Supplied authorities: " + suppliedAuthorities);
            String[] authorityList = suppliedAuthorities.split(",");
            connParams.setSuppliedAuthorityList(authorityList);
            uri = uri.replace(suppliedAuthorities, dummyAuthorityString);
         } else {
            connParams.setEmbeddedMode(true);
         }

         URI jdbcURI = URI.create(uri.substring("jdbc:".length()));
         Pattern pattern = Pattern.compile("([^;]*)=([^;]*)[;]?");
         String sessVars = jdbcURI.getPath();
         if (sessVars != null && !sessVars.isEmpty()) {
            String dbName = "";
            sessVars = sessVars.substring(1);
            if (!sessVars.contains(";")) {
               dbName = sessVars;
            } else {
               dbName = sessVars.substring(0, sessVars.indexOf(59));
               sessVars = sessVars.substring(sessVars.indexOf(59) + 1);
               if (sessVars != null) {
                  Matcher sessMatcher = pattern.matcher(sessVars);

                  while(sessMatcher.find()) {
                     if (connParams.getSessionVars().put(sessMatcher.group(1), sessMatcher.group(2)) != null) {
                        throw new JdbcUriParseException("Bad URL format: Multiple values for property " + sessMatcher.group(1));
                     }
                  }
               }
            }

            if (!dbName.isEmpty()) {
               connParams.setDbName(dbName);
            }
         }

         String confStr = jdbcURI.getQuery();
         if (confStr != null) {
            Matcher confMatcher = pattern.matcher(confStr);

            while(confMatcher.find()) {
               connParams.getHiveConfs().put(confMatcher.group(1), confMatcher.group(2));
            }
         }

         String varStr = jdbcURI.getFragment();
         if (varStr != null) {
            Matcher varMatcher = pattern.matcher(varStr);

            while(varMatcher.find()) {
               connParams.getHiveVars().put(varMatcher.group(1), varMatcher.group(2));
            }
         }

         for(Map.Entry kv : info.entrySet()) {
            if (kv.getKey() instanceof String) {
               String key = (String)kv.getKey();
               if (key.startsWith("hivevar:")) {
                  connParams.getHiveVars().put(key.substring("hivevar:".length()), info.getProperty(key));
               } else if (key.startsWith("hiveconf:")) {
                  connParams.getHiveConfs().put(key.substring("hiveconf:".length()), info.getProperty(key));
               }
            }
         }

         if (!connParams.getSessionVars().containsKey("user")) {
            if (info.containsKey("user")) {
               connParams.getSessionVars().put("user", info.getProperty("user"));
            }

            if (info.containsKey("password")) {
               connParams.getSessionVars().put("password", info.getProperty("password"));
            }
         }

         if (info.containsKey("auth")) {
            connParams.getSessionVars().put("auth", info.getProperty("auth"));
         }

         String usageUrlBase = "jdbc:hive2://<host>:<port>/dbName;";
         String newUsage = usageUrlBase + "saslQop" + "=<qop_value>";
         handleParamDeprecation(connParams.getSessionVars(), connParams.getSessionVars(), "sasl.qop", "saslQop", newUsage);
         newUsage = usageUrlBase + "transportMode" + "=<transport_mode_value>";
         handleParamDeprecation(connParams.getHiveConfs(), connParams.getSessionVars(), "hive.server2.transport.mode", "transportMode", newUsage);
         newUsage = usageUrlBase + "httpPath" + "=<http_path_value>";
         handleParamDeprecation(connParams.getHiveConfs(), connParams.getSessionVars(), "hive.server2.thrift.http.path", "httpPath", newUsage);
         if (connParams.isEmbeddedMode()) {
            connParams.setHost(jdbcURI.getHost());
            connParams.setPort(jdbcURI.getPort());
         } else {
            configureConnParams(connParams);
            String authorityStr = connParams.getHost() + ":" + connParams.getPort();
            LOG.info("Resolved authority: " + authorityStr);
            uri = uri.replace(dummyAuthorityString, authorityStr);
            connParams.setJdbcUriString(uri);
         }

         return connParams;
      }
   }

   private static void handleParamDeprecation(Map fromMap, Map toMap, String deprecatedName, String newName, String newUsage) {
      if (fromMap.containsKey(deprecatedName)) {
         LOG.warn("***** JDBC param deprecation *****");
         LOG.warn("The use of " + deprecatedName + " is deprecated.");
         LOG.warn("Please use " + newName + " like so: " + newUsage);
         String paramValue = (String)fromMap.remove(deprecatedName);
         toMap.put(newName, paramValue);
      }

   }

   private static String getAuthorities(String uri, JdbcConnectionParams connParams) throws JdbcUriParseException {
      int fromIndex = "jdbc:hive2://".length();
      int toIndex = -1;

      for(String toIndexChar : new ArrayList(Arrays.asList("/", "?", "#"))) {
         toIndex = uri.indexOf(toIndexChar, fromIndex);
         if (toIndex > 0) {
            break;
         }
      }

      String authorities;
      if (toIndex < 0) {
         authorities = uri.substring(fromIndex);
      } else {
         authorities = uri.substring(fromIndex, toIndex);
      }

      return authorities;
   }

   private static void configureConnParams(JdbcConnectionParams connParams) throws JdbcUriParseException, ZooKeeperHiveClientException {
      String serviceDiscoveryMode = (String)connParams.getSessionVars().get("serviceDiscoveryMode");
      if (serviceDiscoveryMode != null && "zooKeeper".equalsIgnoreCase(serviceDiscoveryMode)) {
         connParams.setZooKeeperEnsemble(joinStringArray(connParams.getAuthorityList(), ","));
         ZooKeeperHiveClientHelper.configureConnParams(connParams);
      } else {
         String authority = connParams.getAuthorityList()[0];
         URI jdbcURI = URI.create("hive2://" + authority);
         if (jdbcURI.getAuthority() != null) {
            String host = jdbcURI.getHost();
            int port = jdbcURI.getPort();
            if (host == null) {
               throw new JdbcUriParseException("Bad URL format. Hostname not found  in authority part of the url: " + jdbcURI.getAuthority() + ". Are you missing a '/' after the hostname ?");
            }

            if (port <= 0) {
               port = Integer.parseInt("10000");
            }

            connParams.setHost(jdbcURI.getHost());
            connParams.setPort(jdbcURI.getPort());
         }
      }

   }

   static boolean updateConnParamsFromZooKeeper(JdbcConnectionParams connParams) {
      connParams.getRejectedHostZnodePaths().add(connParams.getCurrentHostZnodePath());
      String oldServerHost = connParams.getHost();
      int oldServerPort = connParams.getPort();

      try {
         ZooKeeperHiveClientHelper.configureConnParams(connParams);
         connParams.setJdbcUriString(connParams.getJdbcUriString().replace(oldServerHost + ":" + oldServerPort, connParams.getHost() + ":" + connParams.getPort()));
         LOG.info("Selected HiveServer2 instance with uri: " + connParams.getJdbcUriString());
         return true;
      } catch (ZooKeeperHiveClientException e) {
         LOG.error(e.getMessage());
         return false;
      }
   }

   private static String joinStringArray(String[] stringArray, String seperator) {
      StringBuilder stringBuilder = new StringBuilder();
      int cur = 0;

      for(int end = stringArray.length; cur < end; ++cur) {
         if (cur > 0) {
            stringBuilder.append(seperator);
         }

         stringBuilder.append(stringArray[cur]);
      }

      return stringBuilder.toString();
   }

   static int getVersionPart(String fullVersion, int position) {
      int version = -1;

      try {
         String[] tokens = fullVersion.split("[\\.-]");
         if (tokens != null && tokens.length > 1 && tokens[position] != null) {
            version = Integer.parseInt(tokens[position]);
         }
      } catch (Exception var4) {
         version = -1;
      }

      return version;
   }

   static boolean needToSendCredentials(CookieStore cookieStore, String cookieName, boolean isSSL) {
      if (cookieName != null && cookieStore != null) {
         for(Cookie c : cookieStore.getCookies()) {
            if ((!c.isSecure() || isSSL) && c.getName().equals(cookieName)) {
               return false;
            }
         }

         return true;
      } else {
         return true;
      }
   }

   public static String parsePropertyFromUrl(String url, String key) {
      String[] tokens = url.split(";");

      for(String token : tokens) {
         if (token.trim().startsWith(key.trim() + "=")) {
            return token.trim().substring((key.trim() + "=").length());
         }
      }

      return null;
   }

   public static class JdbcConnectionParams {
      static final String RETRIES = "retries";
      public static final String AUTH_TYPE = "auth";
      public static final String AUTH_QOP_DEPRECATED = "sasl.qop";
      public static final String AUTH_QOP = "saslQop";
      public static final String AUTH_SIMPLE = "noSasl";
      public static final String AUTH_TOKEN = "delegationToken";
      public static final String AUTH_USER = "user";
      public static final String AUTH_PRINCIPAL = "principal";
      public static final String AUTH_PASSWD = "password";
      public static final String AUTH_KERBEROS_AUTH_TYPE = "kerberosAuthType";
      public static final String AUTH_KERBEROS_AUTH_TYPE_FROM_SUBJECT = "fromSubject";
      public static final String ANONYMOUS_USER = "anonymous";
      public static final String ANONYMOUS_PASSWD = "anonymous";
      public static final String USE_SSL = "ssl";
      public static final String SSL_TRUST_STORE = "sslTrustStore";
      public static final String SSL_TRUST_STORE_PASSWORD = "trustStorePassword";
      static final String TRANSPORT_MODE_DEPRECATED = "hive.server2.transport.mode";
      public static final String TRANSPORT_MODE = "transportMode";
      static final String HTTP_PATH_DEPRECATED = "hive.server2.thrift.http.path";
      public static final String HTTP_PATH = "httpPath";
      public static final String SERVICE_DISCOVERY_MODE = "serviceDiscoveryMode";
      public static final String PROPERTY_DRIVER = "driver";
      public static final String PROPERTY_URL = "url";
      static final String SERVICE_DISCOVERY_MODE_NONE = "none";
      static final String SERVICE_DISCOVERY_MODE_ZOOKEEPER = "zooKeeper";
      static final String ZOOKEEPER_NAMESPACE = "zooKeeperNamespace";
      static final String ZOOKEEPER_DEFAULT_NAMESPACE = "hiveserver2";
      static final String COOKIE_AUTH = "cookieAuth";
      static final String COOKIE_AUTH_FALSE = "false";
      static final String COOKIE_NAME = "cookieName";
      static final String DEFAULT_COOKIE_NAMES_HS2 = "hive.server2.auth";
      static final String HTTP_HEADER_PREFIX = "http.header.";
      static final String FETCH_SIZE = "fetchSize";
      static final String INIT_FILE = "initFile";
      static final String USE_TWO_WAY_SSL = "twoWay";
      static final String TRUE = "true";
      static final String SSL_KEY_STORE = "sslKeyStore";
      static final String SSL_KEY_STORE_PASSWORD = "keyStorePassword";
      static final String SSL_KEY_STORE_TYPE = "JKS";
      static final String SUNX509_ALGORITHM_STRING = "SunX509";
      static final String SUNJSSE_ALGORITHM_STRING = "SunJSSE";
      static final String SSL_TRUST_STORE_TYPE = "JKS";
      private static final String HIVE_VAR_PREFIX = "hivevar:";
      private static final String HIVE_CONF_PREFIX = "hiveconf:";
      private String host = null;
      private int port = 0;
      private String jdbcUriString;
      private String dbName = "default";
      private Map hiveConfs = new LinkedHashMap();
      private Map hiveVars = new LinkedHashMap();
      private Map sessionVars = new LinkedHashMap();
      private boolean isEmbeddedMode = false;
      private String[] authorityList;
      private String zooKeeperEnsemble = null;
      private String currentHostZnodePath;
      private final List rejectedHostZnodePaths = new ArrayList();

      public String getHost() {
         return this.host;
      }

      public int getPort() {
         return this.port;
      }

      public String getJdbcUriString() {
         return this.jdbcUriString;
      }

      public String getDbName() {
         return this.dbName;
      }

      public Map getHiveConfs() {
         return this.hiveConfs;
      }

      public Map getHiveVars() {
         return this.hiveVars;
      }

      public boolean isEmbeddedMode() {
         return this.isEmbeddedMode;
      }

      public Map getSessionVars() {
         return this.sessionVars;
      }

      public String[] getAuthorityList() {
         return this.authorityList;
      }

      public String getZooKeeperEnsemble() {
         return this.zooKeeperEnsemble;
      }

      public List getRejectedHostZnodePaths() {
         return this.rejectedHostZnodePaths;
      }

      public String getCurrentHostZnodePath() {
         return this.currentHostZnodePath;
      }

      public void setHost(String host) {
         this.host = host;
      }

      public void setPort(int port) {
         this.port = port;
      }

      public void setJdbcUriString(String jdbcUriString) {
         this.jdbcUriString = jdbcUriString;
      }

      public void setDbName(String dbName) {
         this.dbName = dbName;
      }

      public void setHiveConfs(Map hiveConfs) {
         this.hiveConfs = hiveConfs;
      }

      public void setHiveVars(Map hiveVars) {
         this.hiveVars = hiveVars;
      }

      public void setEmbeddedMode(boolean embeddedMode) {
         this.isEmbeddedMode = embeddedMode;
      }

      public void setSessionVars(Map sessionVars) {
         this.sessionVars = sessionVars;
      }

      public void setSuppliedAuthorityList(String[] authorityList) {
         this.authorityList = authorityList;
      }

      public void setZooKeeperEnsemble(String zooKeeperEnsemble) {
         this.zooKeeperEnsemble = zooKeeperEnsemble;
      }

      public void setCurrentHostZnodePath(String currentHostZnodePath) {
         this.currentHostZnodePath = currentHostZnodePath;
      }
   }
}
