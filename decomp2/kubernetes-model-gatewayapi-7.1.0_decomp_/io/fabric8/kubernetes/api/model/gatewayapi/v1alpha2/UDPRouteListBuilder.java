package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class UDPRouteListBuilder extends UDPRouteListFluent implements VisitableBuilder {
   UDPRouteListFluent fluent;

   public UDPRouteListBuilder() {
      this(new UDPRouteList());
   }

   public UDPRouteListBuilder(UDPRouteListFluent fluent) {
      this(fluent, new UDPRouteList());
   }

   public UDPRouteListBuilder(UDPRouteListFluent fluent, UDPRouteList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public UDPRouteListBuilder(UDPRouteList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public UDPRouteList build() {
      UDPRouteList buildable = new UDPRouteList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
