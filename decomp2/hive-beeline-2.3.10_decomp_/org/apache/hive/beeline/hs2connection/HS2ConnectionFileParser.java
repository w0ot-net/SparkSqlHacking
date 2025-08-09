package org.apache.hive.beeline.hs2connection;

import java.util.Properties;

public interface HS2ConnectionFileParser {
   String BEELINE_CONNECTION_PROPERTY_PREFIX = "beeline.hs2.connection.";
   String URL_PREFIX_PROPERTY_KEY = "url_prefix";
   String DEFAULT_DB_PROPERTY_KEY = "defaultDB";
   String HOST_PROPERTY_KEY = "hosts";
   String HIVE_CONF_PROPERTY_KEY = "hiveconf";
   String HIVE_VAR_PROPERTY_KEY = "hivevar";

   Properties getConnectionProperties() throws BeelineHS2ConnectionFileParseException;

   boolean configExists();
}
