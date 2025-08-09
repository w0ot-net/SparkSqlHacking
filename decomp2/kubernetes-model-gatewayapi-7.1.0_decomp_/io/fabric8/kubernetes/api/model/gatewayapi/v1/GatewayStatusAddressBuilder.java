package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GatewayStatusAddressBuilder extends GatewayStatusAddressFluent implements VisitableBuilder {
   GatewayStatusAddressFluent fluent;

   public GatewayStatusAddressBuilder() {
      this(new GatewayStatusAddress());
   }

   public GatewayStatusAddressBuilder(GatewayStatusAddressFluent fluent) {
      this(fluent, new GatewayStatusAddress());
   }

   public GatewayStatusAddressBuilder(GatewayStatusAddressFluent fluent, GatewayStatusAddress instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GatewayStatusAddressBuilder(GatewayStatusAddress instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GatewayStatusAddress build() {
      GatewayStatusAddress buildable = new GatewayStatusAddress(this.fluent.getType(), this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
