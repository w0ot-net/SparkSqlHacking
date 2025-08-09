package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GatewayStatusBuilder extends GatewayStatusFluent implements VisitableBuilder {
   GatewayStatusFluent fluent;

   public GatewayStatusBuilder() {
      this(new GatewayStatus());
   }

   public GatewayStatusBuilder(GatewayStatusFluent fluent) {
      this(fluent, new GatewayStatus());
   }

   public GatewayStatusBuilder(GatewayStatusFluent fluent, GatewayStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GatewayStatusBuilder(GatewayStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GatewayStatus build() {
      GatewayStatus buildable = new GatewayStatus(this.fluent.buildAddresses(), this.fluent.getConditions(), this.fluent.buildListeners());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
