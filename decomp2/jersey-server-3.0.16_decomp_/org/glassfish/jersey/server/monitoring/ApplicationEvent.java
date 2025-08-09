package org.glassfish.jersey.server.monitoring;

import java.util.Set;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.ResourceModel;

public interface ApplicationEvent {
   Type getType();

   ResourceConfig getResourceConfig();

   Set getRegisteredClasses();

   Set getRegisteredInstances();

   Set getProviders();

   ResourceModel getResourceModel();

   public static enum Type {
      INITIALIZATION_START,
      INITIALIZATION_APP_FINISHED,
      INITIALIZATION_FINISHED,
      DESTROY_FINISHED,
      RELOAD_FINISHED;
   }
}
