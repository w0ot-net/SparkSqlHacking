package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPRouteTimeoutsBuilder extends HTTPRouteTimeoutsFluent implements VisitableBuilder {
   HTTPRouteTimeoutsFluent fluent;

   public HTTPRouteTimeoutsBuilder() {
      this(new HTTPRouteTimeouts());
   }

   public HTTPRouteTimeoutsBuilder(HTTPRouteTimeoutsFluent fluent) {
      this(fluent, new HTTPRouteTimeouts());
   }

   public HTTPRouteTimeoutsBuilder(HTTPRouteTimeoutsFluent fluent, HTTPRouteTimeouts instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPRouteTimeoutsBuilder(HTTPRouteTimeouts instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPRouteTimeouts build() {
      HTTPRouteTimeouts buildable = new HTTPRouteTimeouts(this.fluent.getBackendRequest(), this.fluent.getRequest());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
