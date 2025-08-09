package org.glassfish.jersey.server;

import jakarta.inject.Inject;
import jakarta.inject.Provider;
import java.util.function.Function;
import org.glassfish.jersey.message.MessageBodyWorkers;
import org.glassfish.jersey.server.internal.process.RequestProcessingContext;

public class ContainerMessageBodyWorkersInitializer implements Function {
   private final Provider workersFactory;

   @Inject
   public ContainerMessageBodyWorkersInitializer(Provider workersFactory) {
      this.workersFactory = workersFactory;
   }

   public RequestProcessingContext apply(RequestProcessingContext requestContext) {
      requestContext.request().setWorkers((MessageBodyWorkers)this.workersFactory.get());
      return requestContext;
   }
}
