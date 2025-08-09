package org.glassfish.jersey.server.monitoring;

import java.util.Date;
import java.util.Set;
import org.glassfish.jersey.server.ResourceConfig;

public interface ApplicationInfo {
   ResourceConfig getResourceConfig();

   Date getStartTime();

   Set getRegisteredClasses();

   Set getRegisteredInstances();

   Set getProviders();

   ApplicationInfo snapshot();
}
