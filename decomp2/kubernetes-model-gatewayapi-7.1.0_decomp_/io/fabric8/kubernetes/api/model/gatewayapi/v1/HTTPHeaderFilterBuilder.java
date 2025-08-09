package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPHeaderFilterBuilder extends HTTPHeaderFilterFluent implements VisitableBuilder {
   HTTPHeaderFilterFluent fluent;

   public HTTPHeaderFilterBuilder() {
      this(new HTTPHeaderFilter());
   }

   public HTTPHeaderFilterBuilder(HTTPHeaderFilterFluent fluent) {
      this(fluent, new HTTPHeaderFilter());
   }

   public HTTPHeaderFilterBuilder(HTTPHeaderFilterFluent fluent, HTTPHeaderFilter instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPHeaderFilterBuilder(HTTPHeaderFilter instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPHeaderFilter build() {
      HTTPHeaderFilter buildable = new HTTPHeaderFilter(this.fluent.buildAdd(), this.fluent.getRemove(), this.fluent.buildSet());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
