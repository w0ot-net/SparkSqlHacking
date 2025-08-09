package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GatewaySpecBuilder extends GatewaySpecFluent implements VisitableBuilder {
   GatewaySpecFluent fluent;

   public GatewaySpecBuilder() {
      this(new GatewaySpec());
   }

   public GatewaySpecBuilder(GatewaySpecFluent fluent) {
      this(fluent, new GatewaySpec());
   }

   public GatewaySpecBuilder(GatewaySpecFluent fluent, GatewaySpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GatewaySpecBuilder(GatewaySpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GatewaySpec build() {
      GatewaySpec buildable = new GatewaySpec(this.fluent.buildAddresses(), this.fluent.buildBackendTLS(), this.fluent.getGatewayClassName(), this.fluent.buildInfrastructure(), this.fluent.buildListeners());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
