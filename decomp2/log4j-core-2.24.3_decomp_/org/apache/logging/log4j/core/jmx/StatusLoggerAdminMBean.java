package org.apache.logging.log4j.core.jmx;

import java.util.List;
import javax.management.ObjectName;

public interface StatusLoggerAdminMBean {
   String PATTERN = "org.apache.logging.log4j2:type=%s,component=StatusLogger";
   String NOTIF_TYPE_DATA = "com.apache.logging.log4j.core.jmx.statuslogger.data";
   String NOTIF_TYPE_MESSAGE = "com.apache.logging.log4j.core.jmx.statuslogger.message";

   ObjectName getObjectName();

   List getStatusData();

   String[] getStatusDataHistory();

   String getLevel();

   void setLevel(String level);

   String getContextName();
}
