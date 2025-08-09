package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GatewayBuilder extends GatewayFluent implements VisitableBuilder {
   GatewayFluent fluent;

   public GatewayBuilder() {
      this(new Gateway());
   }

   public GatewayBuilder(GatewayFluent fluent) {
      this(fluent, new Gateway());
   }

   public GatewayBuilder(GatewayFluent fluent, Gateway instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GatewayBuilder(Gateway instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Gateway build() {
      Gateway buildable = new Gateway(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
