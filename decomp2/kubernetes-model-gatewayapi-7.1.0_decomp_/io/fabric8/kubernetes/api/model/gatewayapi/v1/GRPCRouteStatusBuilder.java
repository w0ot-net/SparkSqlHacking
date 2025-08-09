package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GRPCRouteStatusBuilder extends GRPCRouteStatusFluent implements VisitableBuilder {
   GRPCRouteStatusFluent fluent;

   public GRPCRouteStatusBuilder() {
      this(new GRPCRouteStatus());
   }

   public GRPCRouteStatusBuilder(GRPCRouteStatusFluent fluent) {
      this(fluent, new GRPCRouteStatus());
   }

   public GRPCRouteStatusBuilder(GRPCRouteStatusFluent fluent, GRPCRouteStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GRPCRouteStatusBuilder(GRPCRouteStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GRPCRouteStatus build() {
      GRPCRouteStatus buildable = new GRPCRouteStatus(this.fluent.buildParents());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
