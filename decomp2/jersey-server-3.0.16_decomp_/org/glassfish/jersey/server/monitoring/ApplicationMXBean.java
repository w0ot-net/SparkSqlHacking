package org.glassfish.jersey.server.monitoring;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface ApplicationMXBean {
   String getApplicationName();

   String getApplicationClass();

   Map getProperties();

   Date getStartTime();

   Set getRegisteredClasses();

   Set getRegisteredInstances();

   Set getProviderClasses();
}
