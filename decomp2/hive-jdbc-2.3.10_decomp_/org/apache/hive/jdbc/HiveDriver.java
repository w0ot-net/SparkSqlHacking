package org.apache.hive.jdbc;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.jar.Attributes.Name;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class HiveDriver implements Driver {
   private static final boolean JDBC_COMPLIANT = false;
   private static final String DBNAME_PROPERTY_KEY = "DBNAME";
   private static final String HOST_PROPERTY_KEY = "HOST";
   private static final String PORT_PROPERTY_KEY = "PORT";
   private static Attributes manifestAttributes;

   public HiveDriver() {
      SecurityManager security = System.getSecurityManager();
      if (security != null) {
         security.checkWrite("foobah");
      }

   }

   public boolean acceptsURL(String url) throws SQLException {
      return Pattern.matches("jdbc:hive2://.*", url);
   }

   public Connection connect(String url, Properties info) throws SQLException {
      return this.acceptsURL(url) ? new HiveConnection(url, info) : null;
   }

   static int getMajorDriverVersion() {
      int version = -1;

      try {
         String fullVersion = fetchManifestAttribute(Name.IMPLEMENTATION_VERSION);
         String[] tokens = fullVersion.split("\\.");
         if (tokens != null && tokens.length > 0 && tokens[0] != null) {
            version = Integer.parseInt(tokens[0]);
         }
      } catch (Exception var3) {
         version = -1;
      }

      return version;
   }

   static int getMinorDriverVersion() {
      int version = -1;

      try {
         String fullVersion = fetchManifestAttribute(Name.IMPLEMENTATION_VERSION);
         String[] tokens = fullVersion.split("\\.");
         if (tokens != null && tokens.length > 1 && tokens[1] != null) {
            version = Integer.parseInt(tokens[1]);
         }
      } catch (Exception var3) {
         version = -1;
      }

      return version;
   }

   public int getMajorVersion() {
      return getMajorDriverVersion();
   }

   public int getMinorVersion() {
      return getMinorDriverVersion();
   }

   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
      if (info == null) {
         info = new Properties();
      }

      if (url != null && url.startsWith("jdbc:hive2://")) {
         info = this.parseURLforPropertyInfo(url, info);
      }

      DriverPropertyInfo hostProp = new DriverPropertyInfo("HOST", info.getProperty("HOST", ""));
      hostProp.required = false;
      hostProp.description = "Hostname of Hive Server2";
      DriverPropertyInfo portProp = new DriverPropertyInfo("PORT", info.getProperty("PORT", ""));
      portProp.required = false;
      portProp.description = "Port number of Hive Server2";
      DriverPropertyInfo dbProp = new DriverPropertyInfo("DBNAME", info.getProperty("DBNAME", "default"));
      dbProp.required = false;
      dbProp.description = "Database name";
      DriverPropertyInfo[] dpi = new DriverPropertyInfo[3];
      dpi[0] = hostProp;
      dpi[1] = portProp;
      dpi[2] = dbProp;
      return dpi;
   }

   public boolean jdbcCompliant() {
      return false;
   }

   private Properties parseURLforPropertyInfo(String url, Properties defaults) throws SQLException {
      Properties urlProps = defaults != null ? new Properties(defaults) : new Properties();
      if (url != null && url.startsWith("jdbc:hive2://")) {
         Utils.JdbcConnectionParams params = null;

         try {
            params = Utils.parseURL(url, defaults);
         } catch (ZooKeeperHiveClientException e) {
            throw new SQLException(e);
         }

         String host = params.getHost();
         if (host == null) {
            host = "";
         }

         String port = Integer.toString(params.getPort());
         if (host.equals("")) {
            port = "";
         } else if (port.equals("0") || port.equals("-1")) {
            port = "10000";
         }

         String db = params.getDbName();
         urlProps.put("HOST", host);
         urlProps.put("PORT", port);
         urlProps.put("DBNAME", db);
         return urlProps;
      } else {
         throw new SQLException("Invalid connection url: " + url);
      }
   }

   private static synchronized void loadManifestAttributes() throws IOException {
      if (manifestAttributes == null) {
         Class<?> clazz = HiveDriver.class;
         String classContainer = clazz.getProtectionDomain().getCodeSource().getLocation().toString();
         URL manifestUrl = new URL("jar:" + classContainer + "!/META-INF/MANIFEST.MF");
         Manifest manifest = new Manifest(manifestUrl.openStream());
         manifestAttributes = manifest.getMainAttributes();
      }
   }

   static String fetchManifestAttribute(Attributes.Name attributeName) throws SQLException {
      try {
         loadManifestAttributes();
      } catch (IOException e) {
         throw new SQLException("Couldn't load manifest attributes.", e);
      }

      return manifestAttributes.getValue(attributeName);
   }

   static {
      try {
         DriverManager.registerDriver(new HiveDriver());
      } catch (SQLException e) {
         e.printStackTrace();
      }

      manifestAttributes = null;
   }
}
