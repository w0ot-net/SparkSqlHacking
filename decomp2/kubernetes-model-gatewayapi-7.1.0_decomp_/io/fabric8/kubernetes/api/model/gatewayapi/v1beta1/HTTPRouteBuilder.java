package io.fabric8.kubernetes.api.model.gatewayapi.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPRouteBuilder extends HTTPRouteFluent implements VisitableBuilder {
   HTTPRouteFluent fluent;

   public HTTPRouteBuilder() {
      this(new HTTPRoute());
   }

   public HTTPRouteBuilder(HTTPRouteFluent fluent) {
      this(fluent, new HTTPRoute());
   }

   public HTTPRouteBuilder(HTTPRouteFluent fluent, HTTPRoute instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPRouteBuilder(HTTPRoute instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPRoute build() {
      HTTPRoute buildable = new HTTPRoute(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
