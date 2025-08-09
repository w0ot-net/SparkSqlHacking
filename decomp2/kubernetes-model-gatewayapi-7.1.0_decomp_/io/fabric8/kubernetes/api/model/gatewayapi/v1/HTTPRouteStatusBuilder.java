package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPRouteStatusBuilder extends HTTPRouteStatusFluent implements VisitableBuilder {
   HTTPRouteStatusFluent fluent;

   public HTTPRouteStatusBuilder() {
      this(new HTTPRouteStatus());
   }

   public HTTPRouteStatusBuilder(HTTPRouteStatusFluent fluent) {
      this(fluent, new HTTPRouteStatus());
   }

   public HTTPRouteStatusBuilder(HTTPRouteStatusFluent fluent, HTTPRouteStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPRouteStatusBuilder(HTTPRouteStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPRouteStatus build() {
      HTTPRouteStatus buildable = new HTTPRouteStatus(this.fluent.buildParents());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
