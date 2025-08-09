package org.apache.hive.beeline.hs2connection;

import com.google.common.annotations.VisibleForTesting;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserHS2ConnectionFileParser implements HS2ConnectionFileParser {
   public static final String DEFAULT_CONNECTION_CONFIG_FILE_NAME = "beeline-hs2-connection.xml";
   public static final String DEFAULT_BEELINE_USER_CONF_LOCATION;
   public static final String ETC_HIVE_CONF_LOCATION;
   private final List locations = new ArrayList();
   private static final Logger log;

   public UserHS2ConnectionFileParser() {
      this.locations.add(DEFAULT_BEELINE_USER_CONF_LOCATION + "beeline-hs2-connection.xml");
      if (System.getenv("HIVE_CONF_DIR") != null) {
         this.locations.add(System.getenv("HIVE_CONF_DIR") + File.separator + "beeline-hs2-connection.xml");
      }

      this.locations.add(ETC_HIVE_CONF_LOCATION + "beeline-hs2-connection.xml");
   }

   @VisibleForTesting
   UserHS2ConnectionFileParser(List testLocations) {
      if (testLocations != null) {
         this.locations.addAll(testLocations);
      }
   }

   public Properties getConnectionProperties() throws BeelineHS2ConnectionFileParseException {
      Properties props = new Properties();
      String fileLocation = this.getFileLocation();
      if (fileLocation == null) {
         log.debug("User connection configuration file not found");
         return props;
      } else {
         log.info("Using connection configuration file at " + fileLocation);
         props.setProperty("url_prefix", "jdbc:hive2://");
         Configuration conf = new Configuration(false);
         conf.addResource(new Path((new File(fileLocation)).toURI()));

         try {
            for(Map.Entry kv : conf) {
               String key = (String)kv.getKey();
               if (key.startsWith("beeline.hs2.connection.")) {
                  props.setProperty(key.substring("beeline.hs2.connection.".length()), (String)kv.getValue());
               } else {
                  log.warn("Ignoring " + key + " since it does not start with " + "beeline.hs2.connection.");
               }
            }

            return props;
         } catch (Exception ex) {
            throw new BeelineHS2ConnectionFileParseException(ex.getMessage(), ex);
         }
      }
   }

   public boolean configExists() {
      return this.getFileLocation() != null;
   }

   String getFileLocation() {
      for(String location : this.locations) {
         if ((new File(location)).exists()) {
            return location;
         }
      }

      return null;
   }

   static {
      DEFAULT_BEELINE_USER_CONF_LOCATION = System.getProperty("user.home") + File.separator + (System.getProperty("os.name").toLowerCase().indexOf("windows") != -1 ? "" : ".") + "beeline" + File.separator;
      ETC_HIVE_CONF_LOCATION = File.separator + "etc" + File.separator + "conf" + File.separator + "hive";
      log = LoggerFactory.getLogger(UserHS2ConnectionFileParser.class);
   }
}
