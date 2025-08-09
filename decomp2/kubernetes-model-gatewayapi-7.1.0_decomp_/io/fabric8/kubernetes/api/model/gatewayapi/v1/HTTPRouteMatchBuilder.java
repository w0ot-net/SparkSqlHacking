package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPRouteMatchBuilder extends HTTPRouteMatchFluent implements VisitableBuilder {
   HTTPRouteMatchFluent fluent;

   public HTTPRouteMatchBuilder() {
      this(new HTTPRouteMatch());
   }

   public HTTPRouteMatchBuilder(HTTPRouteMatchFluent fluent) {
      this(fluent, new HTTPRouteMatch());
   }

   public HTTPRouteMatchBuilder(HTTPRouteMatchFluent fluent, HTTPRouteMatch instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPRouteMatchBuilder(HTTPRouteMatch instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPRouteMatch build() {
      HTTPRouteMatch buildable = new HTTPRouteMatch(this.fluent.buildHeaders(), this.fluent.getMethod(), this.fluent.buildPath(), this.fluent.buildQueryParams());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
