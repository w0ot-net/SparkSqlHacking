package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class UDPRouteStatusBuilder extends UDPRouteStatusFluent implements VisitableBuilder {
   UDPRouteStatusFluent fluent;

   public UDPRouteStatusBuilder() {
      this(new UDPRouteStatus());
   }

   public UDPRouteStatusBuilder(UDPRouteStatusFluent fluent) {
      this(fluent, new UDPRouteStatus());
   }

   public UDPRouteStatusBuilder(UDPRouteStatusFluent fluent, UDPRouteStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public UDPRouteStatusBuilder(UDPRouteStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public UDPRouteStatus build() {
      UDPRouteStatus buildable = new UDPRouteStatus(this.fluent.buildParents());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
