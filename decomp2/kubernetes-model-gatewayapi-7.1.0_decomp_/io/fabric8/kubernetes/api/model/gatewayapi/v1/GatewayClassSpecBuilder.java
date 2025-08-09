package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GatewayClassSpecBuilder extends GatewayClassSpecFluent implements VisitableBuilder {
   GatewayClassSpecFluent fluent;

   public GatewayClassSpecBuilder() {
      this(new GatewayClassSpec());
   }

   public GatewayClassSpecBuilder(GatewayClassSpecFluent fluent) {
      this(fluent, new GatewayClassSpec());
   }

   public GatewayClassSpecBuilder(GatewayClassSpecFluent fluent, GatewayClassSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GatewayClassSpecBuilder(GatewayClassSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GatewayClassSpec build() {
      GatewayClassSpec buildable = new GatewayClassSpec(this.fluent.getControllerName(), this.fluent.getDescription(), this.fluent.buildParametersRef());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
