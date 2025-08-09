package org.glassfish.jersey.server.internal.monitoring;

import java.util.Date;
import java.util.Set;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.monitoring.ApplicationInfo;

final class ApplicationInfoImpl implements ApplicationInfo {
   private final ResourceConfig resourceConfig;
   private final Date startTime;
   private final Set registeredClasses;
   private final Set registeredInstances;
   private final Set providers;

   ApplicationInfoImpl(ResourceConfig resourceConfig, Date startTime, Set registeredClasses, Set registeredInstances, Set providers) {
      this.resourceConfig = resourceConfig;
      this.startTime = startTime;
      this.registeredClasses = registeredClasses;
      this.registeredInstances = registeredInstances;
      this.providers = providers;
   }

   public ResourceConfig getResourceConfig() {
      return this.resourceConfig;
   }

   public Date getStartTime() {
      return this.startTime;
   }

   public Set getRegisteredClasses() {
      return this.registeredClasses;
   }

   public Set getRegisteredInstances() {
      return this.registeredInstances;
   }

   public Set getProviders() {
      return this.providers;
   }

   public ApplicationInfo snapshot() {
      return this;
   }
}
