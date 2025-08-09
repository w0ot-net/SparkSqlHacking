package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPRouteRetryBuilder extends HTTPRouteRetryFluent implements VisitableBuilder {
   HTTPRouteRetryFluent fluent;

   public HTTPRouteRetryBuilder() {
      this(new HTTPRouteRetry());
   }

   public HTTPRouteRetryBuilder(HTTPRouteRetryFluent fluent) {
      this(fluent, new HTTPRouteRetry());
   }

   public HTTPRouteRetryBuilder(HTTPRouteRetryFluent fluent, HTTPRouteRetry instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPRouteRetryBuilder(HTTPRouteRetry instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPRouteRetry build() {
      HTTPRouteRetry buildable = new HTTPRouteRetry(this.fluent.getAttempts(), this.fluent.getBackoff(), this.fluent.getCodes());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
