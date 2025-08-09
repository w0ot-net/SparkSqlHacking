package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GatewayInfrastructureBuilder extends GatewayInfrastructureFluent implements VisitableBuilder {
   GatewayInfrastructureFluent fluent;

   public GatewayInfrastructureBuilder() {
      this(new GatewayInfrastructure());
   }

   public GatewayInfrastructureBuilder(GatewayInfrastructureFluent fluent) {
      this(fluent, new GatewayInfrastructure());
   }

   public GatewayInfrastructureBuilder(GatewayInfrastructureFluent fluent, GatewayInfrastructure instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GatewayInfrastructureBuilder(GatewayInfrastructure instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GatewayInfrastructure build() {
      GatewayInfrastructure buildable = new GatewayInfrastructure(this.fluent.getAnnotations(), this.fluent.getLabels(), this.fluent.buildParametersRef());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
