package org.glassfish.jersey.server.internal.routing;

import java.util.List;
import java.util.Set;
import org.glassfish.jersey.uri.PathPattern;

final class Route {
   private final PathPattern routingPattern;
   private final List routers;
   private Set httpMethods;

   static Route of(PathPattern routingPattern, List routers) {
      return new Route(routingPattern, routers);
   }

   private Route(PathPattern routingPattern, List routers) {
      this.routingPattern = routingPattern;
      this.routers = routers;
   }

   public PathPattern routingPattern() {
      return this.routingPattern;
   }

   public List next() {
      return this.routers;
   }

   Set getHttpMethods() {
      return this.httpMethods;
   }

   void setHttpMethods(Set httpMethods) {
      this.httpMethods = httpMethods;
   }
}
