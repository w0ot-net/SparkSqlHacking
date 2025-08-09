package org.glassfish.jersey.server.internal.routing;

import java.util.LinkedList;
import java.util.List;
import org.glassfish.jersey.uri.PathPattern;

final class PathMatchingRouterBuilder implements PathToRouterBuilder {
   private final List acceptedRoutes = new LinkedList();
   private List currentRouters;

   static PathToRouterBuilder newRoute(PathPattern pattern) {
      PathMatchingRouterBuilder builder = new PathMatchingRouterBuilder();
      builder.startNewRoute(pattern);
      return builder;
   }

   private PathMatchingRouterBuilder() {
   }

   private void startNewRoute(PathPattern pattern) {
      this.currentRouters = new LinkedList();
      this.acceptedRoutes.add(Route.of(pattern, this.currentRouters));
   }

   protected List acceptedRoutes() {
      return this.acceptedRoutes;
   }

   public PathMatchingRouterBuilder to(Router router) {
      if (AbstractMethodSelectingRouter.class.isInstance(router)) {
         ((Route)this.acceptedRoutes.get(this.acceptedRoutes.size() - 1)).setHttpMethods(((AbstractMethodSelectingRouter)router).getHttpMethods());
      }

      this.currentRouters.add(router);
      return this;
   }

   public PathToRouterBuilder route(PathPattern pattern) {
      this.startNewRoute(pattern);
      return this;
   }

   public PathMatchingRouter build() {
      return new PathMatchingRouter(this.acceptedRoutes());
   }
}
