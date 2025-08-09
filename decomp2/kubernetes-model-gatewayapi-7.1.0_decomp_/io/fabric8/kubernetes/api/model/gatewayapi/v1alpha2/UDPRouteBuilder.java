package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class UDPRouteBuilder extends UDPRouteFluent implements VisitableBuilder {
   UDPRouteFluent fluent;

   public UDPRouteBuilder() {
      this(new UDPRoute());
   }

   public UDPRouteBuilder(UDPRouteFluent fluent) {
      this(fluent, new UDPRoute());
   }

   public UDPRouteBuilder(UDPRouteFluent fluent, UDPRoute instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public UDPRouteBuilder(UDPRoute instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public UDPRoute build() {
      UDPRoute buildable = new UDPRoute(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
