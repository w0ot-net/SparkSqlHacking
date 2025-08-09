package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPRouteSpecBuilder extends HTTPRouteSpecFluent implements VisitableBuilder {
   HTTPRouteSpecFluent fluent;

   public HTTPRouteSpecBuilder() {
      this(new HTTPRouteSpec());
   }

   public HTTPRouteSpecBuilder(HTTPRouteSpecFluent fluent) {
      this(fluent, new HTTPRouteSpec());
   }

   public HTTPRouteSpecBuilder(HTTPRouteSpecFluent fluent, HTTPRouteSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPRouteSpecBuilder(HTTPRouteSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPRouteSpec build() {
      HTTPRouteSpec buildable = new HTTPRouteSpec(this.fluent.getHostnames(), this.fluent.buildParentRefs(), this.fluent.buildRules());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
