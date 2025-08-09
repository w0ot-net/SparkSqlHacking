package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class UDPRouteSpecBuilder extends UDPRouteSpecFluent implements VisitableBuilder {
   UDPRouteSpecFluent fluent;

   public UDPRouteSpecBuilder() {
      this(new UDPRouteSpec());
   }

   public UDPRouteSpecBuilder(UDPRouteSpecFluent fluent) {
      this(fluent, new UDPRouteSpec());
   }

   public UDPRouteSpecBuilder(UDPRouteSpecFluent fluent, UDPRouteSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public UDPRouteSpecBuilder(UDPRouteSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public UDPRouteSpec build() {
      UDPRouteSpec buildable = new UDPRouteSpec(this.fluent.buildParentRefs(), this.fluent.buildRules());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
