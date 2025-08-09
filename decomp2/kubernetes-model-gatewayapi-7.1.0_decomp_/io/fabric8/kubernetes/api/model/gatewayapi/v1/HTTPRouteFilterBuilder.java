package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPRouteFilterBuilder extends HTTPRouteFilterFluent implements VisitableBuilder {
   HTTPRouteFilterFluent fluent;

   public HTTPRouteFilterBuilder() {
      this(new HTTPRouteFilter());
   }

   public HTTPRouteFilterBuilder(HTTPRouteFilterFluent fluent) {
      this(fluent, new HTTPRouteFilter());
   }

   public HTTPRouteFilterBuilder(HTTPRouteFilterFluent fluent, HTTPRouteFilter instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPRouteFilterBuilder(HTTPRouteFilter instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPRouteFilter build() {
      HTTPRouteFilter buildable = new HTTPRouteFilter(this.fluent.buildExtensionRef(), this.fluent.buildRequestHeaderModifier(), this.fluent.buildRequestMirror(), this.fluent.buildRequestRedirect(), this.fluent.buildResponseHeaderModifier(), this.fluent.getType(), this.fluent.buildUrlRewrite());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
