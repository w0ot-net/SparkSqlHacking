package io.fabric8.kubernetes.api.model.gatewayapi.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GatewayClassListBuilder extends GatewayClassListFluent implements VisitableBuilder {
   GatewayClassListFluent fluent;

   public GatewayClassListBuilder() {
      this(new GatewayClassList());
   }

   public GatewayClassListBuilder(GatewayClassListFluent fluent) {
      this(fluent, new GatewayClassList());
   }

   public GatewayClassListBuilder(GatewayClassListFluent fluent, GatewayClassList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GatewayClassListBuilder(GatewayClassList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GatewayClassList build() {
      GatewayClassList buildable = new GatewayClassList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
