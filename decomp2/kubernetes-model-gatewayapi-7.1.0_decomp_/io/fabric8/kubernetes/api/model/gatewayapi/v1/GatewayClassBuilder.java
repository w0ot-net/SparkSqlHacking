package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GatewayClassBuilder extends GatewayClassFluent implements VisitableBuilder {
   GatewayClassFluent fluent;

   public GatewayClassBuilder() {
      this(new GatewayClass());
   }

   public GatewayClassBuilder(GatewayClassFluent fluent) {
      this(fluent, new GatewayClass());
   }

   public GatewayClassBuilder(GatewayClassFluent fluent, GatewayClass instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GatewayClassBuilder(GatewayClass instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GatewayClass build() {
      GatewayClass buildable = new GatewayClass(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
