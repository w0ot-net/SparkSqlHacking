package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RouteParentStatusBuilder extends RouteParentStatusFluent implements VisitableBuilder {
   RouteParentStatusFluent fluent;

   public RouteParentStatusBuilder() {
      this(new RouteParentStatus());
   }

   public RouteParentStatusBuilder(RouteParentStatusFluent fluent) {
      this(fluent, new RouteParentStatus());
   }

   public RouteParentStatusBuilder(RouteParentStatusFluent fluent, RouteParentStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RouteParentStatusBuilder(RouteParentStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RouteParentStatus build() {
      RouteParentStatus buildable = new RouteParentStatus(this.fluent.getConditions(), this.fluent.getControllerName(), this.fluent.buildParentRef());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
