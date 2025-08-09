package org.apache.logging.log4j.core.jmx;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import javax.management.ObjectName;

public interface LoggerContextAdminMBean {
   String PATTERN = "org.apache.logging.log4j2:type=%s";
   String NOTIF_TYPE_RECONFIGURED = "com.apache.logging.log4j.core.jmx.config.reconfigured";

   ObjectName getObjectName();

   String getStatus();

   String getName();

   String getConfigLocationUri();

   void setConfigLocationUri(String configLocation) throws URISyntaxException, IOException;

   String getConfigText() throws IOException;

   String getConfigText(String charsetName) throws IOException;

   void setConfigText(String configText, String charsetName);

   String getConfigName();

   String getConfigClassName();

   String getConfigFilter();

   Map getConfigProperties();
}
