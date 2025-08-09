package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TCPRouteStatusBuilder extends TCPRouteStatusFluent implements VisitableBuilder {
   TCPRouteStatusFluent fluent;

   public TCPRouteStatusBuilder() {
      this(new TCPRouteStatus());
   }

   public TCPRouteStatusBuilder(TCPRouteStatusFluent fluent) {
      this(fluent, new TCPRouteStatus());
   }

   public TCPRouteStatusBuilder(TCPRouteStatusFluent fluent, TCPRouteStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TCPRouteStatusBuilder(TCPRouteStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TCPRouteStatus build() {
      TCPRouteStatus buildable = new TCPRouteStatus(this.fluent.buildParents());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
