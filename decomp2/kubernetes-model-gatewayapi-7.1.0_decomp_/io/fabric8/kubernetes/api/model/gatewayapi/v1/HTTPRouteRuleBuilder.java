package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPRouteRuleBuilder extends HTTPRouteRuleFluent implements VisitableBuilder {
   HTTPRouteRuleFluent fluent;

   public HTTPRouteRuleBuilder() {
      this(new HTTPRouteRule());
   }

   public HTTPRouteRuleBuilder(HTTPRouteRuleFluent fluent) {
      this(fluent, new HTTPRouteRule());
   }

   public HTTPRouteRuleBuilder(HTTPRouteRuleFluent fluent, HTTPRouteRule instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPRouteRuleBuilder(HTTPRouteRule instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPRouteRule build() {
      HTTPRouteRule buildable = new HTTPRouteRule(this.fluent.buildBackendRefs(), this.fluent.buildFilters(), this.fluent.buildMatches(), this.fluent.getName(), this.fluent.buildRetry(), this.fluent.buildSessionPersistence(), this.fluent.buildTimeouts());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
