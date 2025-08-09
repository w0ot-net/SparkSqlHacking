package org.apache.spark.network.util;

import java.util.Map;
import java.util.Properties;

public class CryptoUtils {
   public static final String COMMONS_CRYPTO_CONFIG_PREFIX = "commons.crypto.";

   public static Properties toCryptoConf(String prefix, Iterable conf) {
      Properties props = new Properties();

      for(Map.Entry e : conf) {
         String key = (String)e.getKey();
         if (key.startsWith(prefix)) {
            props.setProperty("commons.crypto." + key.substring(prefix.length()), (String)e.getValue());
         }
      }

      return props;
   }
}
