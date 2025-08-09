package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GRPCRouteFilterBuilder extends GRPCRouteFilterFluent implements VisitableBuilder {
   GRPCRouteFilterFluent fluent;

   public GRPCRouteFilterBuilder() {
      this(new GRPCRouteFilter());
   }

   public GRPCRouteFilterBuilder(GRPCRouteFilterFluent fluent) {
      this(fluent, new GRPCRouteFilter());
   }

   public GRPCRouteFilterBuilder(GRPCRouteFilterFluent fluent, GRPCRouteFilter instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GRPCRouteFilterBuilder(GRPCRouteFilter instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GRPCRouteFilter build() {
      GRPCRouteFilter buildable = new GRPCRouteFilter(this.fluent.buildExtensionRef(), this.fluent.buildRequestHeaderModifier(), this.fluent.buildRequestMirror(), this.fluent.buildResponseHeaderModifier(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
