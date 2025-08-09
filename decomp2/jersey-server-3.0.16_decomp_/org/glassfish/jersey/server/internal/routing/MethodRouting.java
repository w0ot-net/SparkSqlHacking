package org.glassfish.jersey.server.internal.routing;

import java.util.Arrays;
import java.util.List;
import org.glassfish.jersey.server.model.ResourceMethod;

final class MethodRouting {
   final ResourceMethod method;
   final List routers;

   MethodRouting(ResourceMethod method, Router... routers) {
      this.method = method;
      this.routers = Arrays.asList(routers);
   }

   public String toString() {
      return "{" + this.method + " -> " + this.routers + '}';
   }
}
