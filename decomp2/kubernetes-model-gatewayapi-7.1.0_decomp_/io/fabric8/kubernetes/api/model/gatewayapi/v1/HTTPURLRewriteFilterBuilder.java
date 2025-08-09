package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPURLRewriteFilterBuilder extends HTTPURLRewriteFilterFluent implements VisitableBuilder {
   HTTPURLRewriteFilterFluent fluent;

   public HTTPURLRewriteFilterBuilder() {
      this(new HTTPURLRewriteFilter());
   }

   public HTTPURLRewriteFilterBuilder(HTTPURLRewriteFilterFluent fluent) {
      this(fluent, new HTTPURLRewriteFilter());
   }

   public HTTPURLRewriteFilterBuilder(HTTPURLRewriteFilterFluent fluent, HTTPURLRewriteFilter instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPURLRewriteFilterBuilder(HTTPURLRewriteFilter instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPURLRewriteFilter build() {
      HTTPURLRewriteFilter buildable = new HTTPURLRewriteFilter(this.fluent.getHostname(), this.fluent.buildPath());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
