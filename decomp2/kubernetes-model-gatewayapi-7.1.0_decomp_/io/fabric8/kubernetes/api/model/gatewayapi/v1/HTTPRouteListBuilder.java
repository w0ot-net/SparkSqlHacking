package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPRouteListBuilder extends HTTPRouteListFluent implements VisitableBuilder {
   HTTPRouteListFluent fluent;

   public HTTPRouteListBuilder() {
      this(new HTTPRouteList());
   }

   public HTTPRouteListBuilder(HTTPRouteListFluent fluent) {
      this(fluent, new HTTPRouteList());
   }

   public HTTPRouteListBuilder(HTTPRouteListFluent fluent, HTTPRouteList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPRouteListBuilder(HTTPRouteList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPRouteList build() {
      HTTPRouteList buildable = new HTTPRouteList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
