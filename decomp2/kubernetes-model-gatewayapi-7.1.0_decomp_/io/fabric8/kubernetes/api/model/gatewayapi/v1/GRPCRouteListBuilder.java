package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GRPCRouteListBuilder extends GRPCRouteListFluent implements VisitableBuilder {
   GRPCRouteListFluent fluent;

   public GRPCRouteListBuilder() {
      this(new GRPCRouteList());
   }

   public GRPCRouteListBuilder(GRPCRouteListFluent fluent) {
      this(fluent, new GRPCRouteList());
   }

   public GRPCRouteListBuilder(GRPCRouteListFluent fluent, GRPCRouteList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GRPCRouteListBuilder(GRPCRouteList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GRPCRouteList build() {
      GRPCRouteList buildable = new GRPCRouteList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
