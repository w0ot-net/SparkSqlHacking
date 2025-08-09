package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GRPCRouteRuleBuilder extends GRPCRouteRuleFluent implements VisitableBuilder {
   GRPCRouteRuleFluent fluent;

   public GRPCRouteRuleBuilder() {
      this(new GRPCRouteRule());
   }

   public GRPCRouteRuleBuilder(GRPCRouteRuleFluent fluent) {
      this(fluent, new GRPCRouteRule());
   }

   public GRPCRouteRuleBuilder(GRPCRouteRuleFluent fluent, GRPCRouteRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GRPCRouteRuleBuilder(GRPCRouteRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GRPCRouteRule build() {
      GRPCRouteRule buildable = new GRPCRouteRule(this.fluent.buildBackendRefs(), this.fluent.buildFilters(), this.fluent.buildMatches(), this.fluent.getName(), this.fluent.buildSessionPersistence());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
