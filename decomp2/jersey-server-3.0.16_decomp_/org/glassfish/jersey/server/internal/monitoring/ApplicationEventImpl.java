package org.glassfish.jersey.server.internal.monitoring;

import java.util.Set;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.ResourceModel;
import org.glassfish.jersey.server.monitoring.ApplicationEvent;

public class ApplicationEventImpl implements ApplicationEvent {
   private final ApplicationEvent.Type type;
   private final ResourceConfig resourceConfig;
   private final Set providers;
   private final Set registeredClasses;
   private final Set registeredInstances;
   private final ResourceModel resourceModel;

   public ApplicationEventImpl(ApplicationEvent.Type type, ResourceConfig resourceConfig, Set providers, Set registeredClasses, Set registeredInstances, ResourceModel resourceModel) {
      this.type = type;
      this.resourceConfig = resourceConfig;
      this.providers = providers;
      this.registeredClasses = registeredClasses;
      this.registeredInstances = registeredInstances;
      this.resourceModel = resourceModel;
   }

   public ResourceConfig getResourceConfig() {
      return this.resourceConfig;
   }

   public ApplicationEvent.Type getType() {
      return this.type;
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

   public ResourceModel getResourceModel() {
      return this.resourceModel;
   }
}
