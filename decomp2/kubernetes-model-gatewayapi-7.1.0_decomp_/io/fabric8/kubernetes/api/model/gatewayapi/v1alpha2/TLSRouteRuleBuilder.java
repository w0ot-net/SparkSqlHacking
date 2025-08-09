package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TLSRouteRuleBuilder extends TLSRouteRuleFluent implements VisitableBuilder {
   TLSRouteRuleFluent fluent;

   public TLSRouteRuleBuilder() {
      this(new TLSRouteRule());
   }

   public TLSRouteRuleBuilder(TLSRouteRuleFluent fluent) {
      this(fluent, new TLSRouteRule());
   }

   public TLSRouteRuleBuilder(TLSRouteRuleFluent fluent, TLSRouteRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TLSRouteRuleBuilder(TLSRouteRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TLSRouteRule build() {
      TLSRouteRule buildable = new TLSRouteRule(this.fluent.buildBackendRefs(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
