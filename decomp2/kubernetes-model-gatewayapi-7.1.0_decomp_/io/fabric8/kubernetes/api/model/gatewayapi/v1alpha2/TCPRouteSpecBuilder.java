package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TCPRouteSpecBuilder extends TCPRouteSpecFluent implements VisitableBuilder {
   TCPRouteSpecFluent fluent;

   public TCPRouteSpecBuilder() {
      this(new TCPRouteSpec());
   }

   public TCPRouteSpecBuilder(TCPRouteSpecFluent fluent) {
      this(fluent, new TCPRouteSpec());
   }

   public TCPRouteSpecBuilder(TCPRouteSpecFluent fluent, TCPRouteSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TCPRouteSpecBuilder(TCPRouteSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TCPRouteSpec build() {
      TCPRouteSpec buildable = new TCPRouteSpec(this.fluent.buildParentRefs(), this.fluent.buildRules());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
