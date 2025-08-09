package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RouteGroupKindBuilder extends RouteGroupKindFluent implements VisitableBuilder {
   RouteGroupKindFluent fluent;

   public RouteGroupKindBuilder() {
      this(new RouteGroupKind());
   }

   public RouteGroupKindBuilder(RouteGroupKindFluent fluent) {
      this(fluent, new RouteGroupKind());
   }

   public RouteGroupKindBuilder(RouteGroupKindFluent fluent, RouteGroupKind instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RouteGroupKindBuilder(RouteGroupKind instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RouteGroupKind build() {
      RouteGroupKind buildable = new RouteGroupKind(this.fluent.getGroup(), this.fluent.getKind());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
