package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GatewayClassStatusBuilder extends GatewayClassStatusFluent implements VisitableBuilder {
   GatewayClassStatusFluent fluent;

   public GatewayClassStatusBuilder() {
      this(new GatewayClassStatus());
   }

   public GatewayClassStatusBuilder(GatewayClassStatusFluent fluent) {
      this(fluent, new GatewayClassStatus());
   }

   public GatewayClassStatusBuilder(GatewayClassStatusFluent fluent, GatewayClassStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GatewayClassStatusBuilder(GatewayClassStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GatewayClassStatus build() {
      GatewayClassStatus buildable = new GatewayClassStatus(this.fluent.getConditions(), this.fluent.buildSupportedFeatures());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
