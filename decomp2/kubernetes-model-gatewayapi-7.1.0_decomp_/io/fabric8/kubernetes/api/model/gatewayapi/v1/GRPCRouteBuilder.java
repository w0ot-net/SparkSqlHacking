package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GRPCRouteBuilder extends GRPCRouteFluent implements VisitableBuilder {
   GRPCRouteFluent fluent;

   public GRPCRouteBuilder() {
      this(new GRPCRoute());
   }

   public GRPCRouteBuilder(GRPCRouteFluent fluent) {
      this(fluent, new GRPCRoute());
   }

   public GRPCRouteBuilder(GRPCRouteFluent fluent, GRPCRoute instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GRPCRouteBuilder(GRPCRoute instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GRPCRoute build() {
      GRPCRoute buildable = new GRPCRoute(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
