package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class UDPRouteRuleBuilder extends UDPRouteRuleFluent implements VisitableBuilder {
   UDPRouteRuleFluent fluent;

   public UDPRouteRuleBuilder() {
      this(new UDPRouteRule());
   }

   public UDPRouteRuleBuilder(UDPRouteRuleFluent fluent) {
      this(fluent, new UDPRouteRule());
   }

   public UDPRouteRuleBuilder(UDPRouteRuleFluent fluent, UDPRouteRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public UDPRouteRuleBuilder(UDPRouteRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public UDPRouteRule build() {
      UDPRouteRule buildable = new UDPRouteRule(this.fluent.buildBackendRefs(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
