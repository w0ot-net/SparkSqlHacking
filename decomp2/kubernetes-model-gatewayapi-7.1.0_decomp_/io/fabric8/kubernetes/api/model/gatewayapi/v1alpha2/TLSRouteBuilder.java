package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TLSRouteBuilder extends TLSRouteFluent implements VisitableBuilder {
   TLSRouteFluent fluent;

   public TLSRouteBuilder() {
      this(new TLSRoute());
   }

   public TLSRouteBuilder(TLSRouteFluent fluent) {
      this(fluent, new TLSRoute());
   }

   public TLSRouteBuilder(TLSRouteFluent fluent, TLSRoute instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TLSRouteBuilder(TLSRoute instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TLSRoute build() {
      TLSRoute buildable = new TLSRoute(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
