package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GRPCRouteSpecBuilder extends GRPCRouteSpecFluent implements VisitableBuilder {
   GRPCRouteSpecFluent fluent;

   public GRPCRouteSpecBuilder() {
      this(new GRPCRouteSpec());
   }

   public GRPCRouteSpecBuilder(GRPCRouteSpecFluent fluent) {
      this(fluent, new GRPCRouteSpec());
   }

   public GRPCRouteSpecBuilder(GRPCRouteSpecFluent fluent, GRPCRouteSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GRPCRouteSpecBuilder(GRPCRouteSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GRPCRouteSpec build() {
      GRPCRouteSpec buildable = new GRPCRouteSpec(this.fluent.getHostnames(), this.fluent.buildParentRefs(), this.fluent.buildRules());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
