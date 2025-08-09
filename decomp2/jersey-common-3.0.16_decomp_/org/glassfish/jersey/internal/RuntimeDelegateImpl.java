package org.glassfish.jersey.internal;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.ext.RuntimeDelegate;
import org.glassfish.jersey.message.internal.MessagingBinders;

public class RuntimeDelegateImpl extends AbstractRuntimeDelegate {
   public RuntimeDelegateImpl() {
      super((new MessagingBinders.HeaderDelegateProviders()).getHeaderDelegateProviders());
   }

   public Object createEndpoint(Application application, Class endpointType) throws IllegalArgumentException, UnsupportedOperationException {
      for(RuntimeDelegate delegate : ServiceFinder.find(RuntimeDelegate.class)) {
         if (delegate.getClass() != RuntimeDelegateImpl.class) {
            RuntimeDelegate.setInstance(delegate);
            return delegate.createEndpoint(application, endpointType);
         }
      }

      throw new UnsupportedOperationException(LocalizationMessages.NO_CONTAINER_AVAILABLE());
   }
}
