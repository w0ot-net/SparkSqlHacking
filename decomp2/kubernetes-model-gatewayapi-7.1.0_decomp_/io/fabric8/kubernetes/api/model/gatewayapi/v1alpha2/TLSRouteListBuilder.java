package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TLSRouteListBuilder extends TLSRouteListFluent implements VisitableBuilder {
   TLSRouteListFluent fluent;

   public TLSRouteListBuilder() {
      this(new TLSRouteList());
   }

   public TLSRouteListBuilder(TLSRouteListFluent fluent) {
      this(fluent, new TLSRouteList());
   }

   public TLSRouteListBuilder(TLSRouteListFluent fluent, TLSRouteList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TLSRouteListBuilder(TLSRouteList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TLSRouteList build() {
      TLSRouteList buildable = new TLSRouteList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
