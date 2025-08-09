package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RouteStatusBuilder extends RouteStatusFluent implements VisitableBuilder {
   RouteStatusFluent fluent;

   public RouteStatusBuilder() {
      this(new RouteStatus());
   }

   public RouteStatusBuilder(RouteStatusFluent fluent) {
      this(fluent, new RouteStatus());
   }

   public RouteStatusBuilder(RouteStatusFluent fluent, RouteStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RouteStatusBuilder(RouteStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RouteStatus build() {
      RouteStatus buildable = new RouteStatus(this.fluent.buildParents());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
