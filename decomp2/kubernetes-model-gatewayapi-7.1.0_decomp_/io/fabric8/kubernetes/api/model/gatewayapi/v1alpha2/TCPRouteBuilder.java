package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TCPRouteBuilder extends TCPRouteFluent implements VisitableBuilder {
   TCPRouteFluent fluent;

   public TCPRouteBuilder() {
      this(new TCPRoute());
   }

   public TCPRouteBuilder(TCPRouteFluent fluent) {
      this(fluent, new TCPRoute());
   }

   public TCPRouteBuilder(TCPRouteFluent fluent, TCPRoute instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TCPRouteBuilder(TCPRoute instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TCPRoute build() {
      TCPRoute buildable = new TCPRoute(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
