package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPRequestRedirectFilterBuilder extends HTTPRequestRedirectFilterFluent implements VisitableBuilder {
   HTTPRequestRedirectFilterFluent fluent;

   public HTTPRequestRedirectFilterBuilder() {
      this(new HTTPRequestRedirectFilter());
   }

   public HTTPRequestRedirectFilterBuilder(HTTPRequestRedirectFilterFluent fluent) {
      this(fluent, new HTTPRequestRedirectFilter());
   }

   public HTTPRequestRedirectFilterBuilder(HTTPRequestRedirectFilterFluent fluent, HTTPRequestRedirectFilter instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPRequestRedirectFilterBuilder(HTTPRequestRedirectFilter instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPRequestRedirectFilter build() {
      HTTPRequestRedirectFilter buildable = new HTTPRequestRedirectFilter(this.fluent.getHostname(), this.fluent.buildPath(), this.fluent.getPort(), this.fluent.getScheme(), this.fluent.getStatusCode());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
