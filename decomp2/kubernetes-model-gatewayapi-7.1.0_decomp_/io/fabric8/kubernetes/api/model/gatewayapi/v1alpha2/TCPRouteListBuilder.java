package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TCPRouteListBuilder extends TCPRouteListFluent implements VisitableBuilder {
   TCPRouteListFluent fluent;

   public TCPRouteListBuilder() {
      this(new TCPRouteList());
   }

   public TCPRouteListBuilder(TCPRouteListFluent fluent) {
      this(fluent, new TCPRouteList());
   }

   public TCPRouteListBuilder(TCPRouteListFluent fluent, TCPRouteList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TCPRouteListBuilder(TCPRouteList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TCPRouteList build() {
      TCPRouteList buildable = new TCPRouteList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
