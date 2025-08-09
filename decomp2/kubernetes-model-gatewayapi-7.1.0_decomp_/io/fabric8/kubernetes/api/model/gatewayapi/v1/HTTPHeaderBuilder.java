package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPHeaderBuilder extends HTTPHeaderFluent implements VisitableBuilder {
   HTTPHeaderFluent fluent;

   public HTTPHeaderBuilder() {
      this(new HTTPHeader());
   }

   public HTTPHeaderBuilder(HTTPHeaderFluent fluent) {
      this(fluent, new HTTPHeader());
   }

   public HTTPHeaderBuilder(HTTPHeaderFluent fluent, HTTPHeader instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPHeaderBuilder(HTTPHeader instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPHeader build() {
      HTTPHeader buildable = new HTTPHeader(this.fluent.getName(), this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
