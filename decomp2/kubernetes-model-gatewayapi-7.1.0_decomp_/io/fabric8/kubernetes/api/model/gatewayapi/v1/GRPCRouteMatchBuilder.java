package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GRPCRouteMatchBuilder extends GRPCRouteMatchFluent implements VisitableBuilder {
   GRPCRouteMatchFluent fluent;

   public GRPCRouteMatchBuilder() {
      this(new GRPCRouteMatch());
   }

   public GRPCRouteMatchBuilder(GRPCRouteMatchFluent fluent) {
      this(fluent, new GRPCRouteMatch());
   }

   public GRPCRouteMatchBuilder(GRPCRouteMatchFluent fluent, GRPCRouteMatch instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GRPCRouteMatchBuilder(GRPCRouteMatch instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GRPCRouteMatch build() {
      GRPCRouteMatch buildable = new GRPCRouteMatch(this.fluent.buildHeaders(), this.fluent.buildMethod());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
