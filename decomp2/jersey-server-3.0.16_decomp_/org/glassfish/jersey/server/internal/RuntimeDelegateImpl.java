package org.glassfish.jersey.server.internal;

import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.internal.AbstractRuntimeDelegate;
import org.glassfish.jersey.message.internal.MessagingBinders;
import org.glassfish.jersey.server.ContainerFactory;

public class RuntimeDelegateImpl extends AbstractRuntimeDelegate {
   public RuntimeDelegateImpl() {
      super((new MessagingBinders.HeaderDelegateProviders()).getHeaderDelegateProviders());
   }

   public Object createEndpoint(Application application, Class endpointType) throws IllegalArgumentException, UnsupportedOperationException {
      if (application == null) {
         throw new IllegalArgumentException("application is null.");
      } else {
         return ContainerFactory.createContainer(endpointType, application);
      }
   }
}
