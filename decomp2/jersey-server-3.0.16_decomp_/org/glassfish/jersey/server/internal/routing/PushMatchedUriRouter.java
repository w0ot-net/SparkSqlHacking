package org.glassfish.jersey.server.internal.routing;

import org.glassfish.jersey.server.internal.process.RequestProcessingContext;

final class PushMatchedUriRouter implements Router {
   public Router.Continuation apply(RequestProcessingContext context) {
      context.routingContext().pushLeftHandPath();
      return Router.Continuation.of(context);
   }
}
