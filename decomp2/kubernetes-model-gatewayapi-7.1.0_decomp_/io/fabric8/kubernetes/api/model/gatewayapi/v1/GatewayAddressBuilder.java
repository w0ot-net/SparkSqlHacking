package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GatewayAddressBuilder extends GatewayAddressFluent implements VisitableBuilder {
   GatewayAddressFluent fluent;

   public GatewayAddressBuilder() {
      this(new GatewayAddress());
   }

   public GatewayAddressBuilder(GatewayAddressFluent fluent) {
      this(fluent, new GatewayAddress());
   }

   public GatewayAddressBuilder(GatewayAddressFluent fluent, GatewayAddress instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GatewayAddressBuilder(GatewayAddress instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GatewayAddress build() {
      GatewayAddress buildable = new GatewayAddress(this.fluent.getType(), this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
