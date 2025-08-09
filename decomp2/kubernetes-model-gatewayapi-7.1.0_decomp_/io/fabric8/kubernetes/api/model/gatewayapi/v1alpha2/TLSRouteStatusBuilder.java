package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TLSRouteStatusBuilder extends TLSRouteStatusFluent implements VisitableBuilder {
   TLSRouteStatusFluent fluent;

   public TLSRouteStatusBuilder() {
      this(new TLSRouteStatus());
   }

   public TLSRouteStatusBuilder(TLSRouteStatusFluent fluent) {
      this(fluent, new TLSRouteStatus());
   }

   public TLSRouteStatusBuilder(TLSRouteStatusFluent fluent, TLSRouteStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TLSRouteStatusBuilder(TLSRouteStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TLSRouteStatus build() {
      TLSRouteStatus buildable = new TLSRouteStatus(this.fluent.buildParents());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
