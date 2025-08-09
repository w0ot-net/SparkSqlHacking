package io.fabric8.kubernetes.api.model.gatewayapi.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GatewayListBuilder extends GatewayListFluent implements VisitableBuilder {
   GatewayListFluent fluent;

   public GatewayListBuilder() {
      this(new GatewayList());
   }

   public GatewayListBuilder(GatewayListFluent fluent) {
      this(fluent, new GatewayList());
   }

   public GatewayListBuilder(GatewayListFluent fluent, GatewayList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GatewayListBuilder(GatewayList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GatewayList build() {
      GatewayList buildable = new GatewayList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
