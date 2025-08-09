package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class TCPRouteRuleBuilder extends TCPRouteRuleFluent implements VisitableBuilder {
   TCPRouteRuleFluent fluent;

   public TCPRouteRuleBuilder() {
      this(new TCPRouteRule());
   }

   public TCPRouteRuleBuilder(TCPRouteRuleFluent fluent) {
      this(fluent, new TCPRouteRule());
   }

   public TCPRouteRuleBuilder(TCPRouteRuleFluent fluent, TCPRouteRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public TCPRouteRuleBuilder(TCPRouteRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public TCPRouteRule build() {
      TCPRouteRule buildable = new TCPRouteRule(this.fluent.buildBackendRefs(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
