package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TLSRouteSpecBuilder extends TLSRouteSpecFluent implements VisitableBuilder {
   TLSRouteSpecFluent fluent;

   public TLSRouteSpecBuilder() {
      this(new TLSRouteSpec());
   }

   public TLSRouteSpecBuilder(TLSRouteSpecFluent fluent) {
      this(fluent, new TLSRouteSpec());
   }

   public TLSRouteSpecBuilder(TLSRouteSpecFluent fluent, TLSRouteSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TLSRouteSpecBuilder(TLSRouteSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TLSRouteSpec build() {
      TLSRouteSpec buildable = new TLSRouteSpec(this.fluent.getHostnames(), this.fluent.buildParentRefs(), this.fluent.buildRules());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
