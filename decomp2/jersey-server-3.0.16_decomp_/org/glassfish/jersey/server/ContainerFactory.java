package org.glassfish.jersey.server;

import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.internal.ServiceFinder;
import org.glassfish.jersey.server.spi.ContainerProvider;

public final class ContainerFactory {
   private ContainerFactory() {
   }

   public static Object createContainer(Class type, Application application) {
      for(ContainerProvider containerProvider : ServiceFinder.find(ContainerProvider.class)) {
         T container = (T)containerProvider.createContainer(type, application);
         if (container != null) {
            return container;
         }
      }

      throw new IllegalArgumentException("No container provider supports the type " + type);
   }
}
