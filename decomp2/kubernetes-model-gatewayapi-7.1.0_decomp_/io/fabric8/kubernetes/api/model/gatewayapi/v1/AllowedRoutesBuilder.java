package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AllowedRoutesBuilder extends AllowedRoutesFluent implements VisitableBuilder {
   AllowedRoutesFluent fluent;

   public AllowedRoutesBuilder() {
      this(new AllowedRoutes());
   }

   public AllowedRoutesBuilder(AllowedRoutesFluent fluent) {
      this(fluent, new AllowedRoutes());
   }

   public AllowedRoutesBuilder(AllowedRoutesFluent fluent, AllowedRoutes instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AllowedRoutesBuilder(AllowedRoutes instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AllowedRoutes build() {
      AllowedRoutes buildable = new AllowedRoutes(this.fluent.buildKinds(), this.fluent.buildNamespaces());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
