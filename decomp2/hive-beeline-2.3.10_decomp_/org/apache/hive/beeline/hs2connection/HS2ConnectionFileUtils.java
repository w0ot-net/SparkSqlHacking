package org.apache.hive.beeline.hs2connection;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class HS2ConnectionFileUtils {
   public static String getUrl(Properties props) throws BeelineHS2ConnectionFileParseException {
      if (props != null && !props.isEmpty()) {
         String urlPrefix = (String)props.remove("url_prefix");
         if (urlPrefix != null && !urlPrefix.isEmpty()) {
            String hosts = (String)props.remove("hosts");
            if (hosts != null && !hosts.isEmpty()) {
               String defaultDB = (String)props.remove("defaultDB");
               if (defaultDB == null) {
                  defaultDB = "default";
               }

               String hiveConfProperties = "";
               if (props.containsKey("hiveconf")) {
                  hiveConfProperties = extractHiveVariables((String)props.remove("hiveconf"), true);
               }

               String hiveVarProperties = "";
               if (props.containsKey("hivevar")) {
                  hiveVarProperties = extractHiveVariables((String)props.remove("hivevar"), false);
               }

               StringBuilder urlSb = new StringBuilder();
               urlSb.append(urlPrefix.trim());
               urlSb.append(hosts.trim());
               urlSb.append(File.separator);
               urlSb.append(defaultDB.trim());
               List<String> keys = new ArrayList(props.stringPropertyNames());
               Collections.sort(keys);

               for(String propertyName : keys) {
                  urlSb.append(";");
                  urlSb.append(propertyName);
                  urlSb.append("=");
                  urlSb.append(props.getProperty(propertyName));
               }

               if (!hiveConfProperties.isEmpty()) {
                  urlSb.append(hiveConfProperties.toString());
               }

               if (!hiveVarProperties.isEmpty()) {
                  urlSb.append(hiveVarProperties.toString());
               }

               return urlSb.toString();
            } else {
               throw new BeelineHS2ConnectionFileParseException("hosts parameter cannot be empty");
            }
         } else {
            throw new BeelineHS2ConnectionFileParseException("url_prefix parameter cannot be empty");
         }
      } else {
         return null;
      }
   }

   private static String extractHiveVariables(String propertyValue, boolean isHiveConf) throws BeelineHS2ConnectionFileParseException {
      StringBuilder hivePropertiesList = new StringBuilder();
      String delimiter;
      if (isHiveConf) {
         delimiter = "?";
      } else {
         delimiter = "#";
      }

      hivePropertiesList.append(delimiter);
      addPropertyValues(propertyValue, hivePropertiesList);
      return hivePropertiesList.toString();
   }

   private static void addPropertyValues(String value, StringBuilder hivePropertiesList) throws BeelineHS2ConnectionFileParseException {
      String[] values = value.split(",");
      boolean first = true;

      for(String keyValuePair : values) {
         String[] keyValue = keyValuePair.split("=");
         if (keyValue.length != 2) {
            throw new BeelineHS2ConnectionFileParseException("Unable to parse " + keyValuePair + " in hs2 connection config file");
         }

         if (!first) {
            hivePropertiesList.append(";");
         }

         first = false;
         hivePropertiesList.append(keyValue[0].trim());
         hivePropertiesList.append("=");
         hivePropertiesList.append(keyValue[1].trim());
      }

   }
}
