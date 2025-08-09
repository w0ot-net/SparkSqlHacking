package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPHeaderMatchBuilder extends HTTPHeaderMatchFluent implements VisitableBuilder {
   HTTPHeaderMatchFluent fluent;

   public HTTPHeaderMatchBuilder() {
      this(new HTTPHeaderMatch());
   }

   public HTTPHeaderMatchBuilder(HTTPHeaderMatchFluent fluent) {
      this(fluent, new HTTPHeaderMatch());
   }

   public HTTPHeaderMatchBuilder(HTTPHeaderMatchFluent fluent, HTTPHeaderMatch instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPHeaderMatchBuilder(HTTPHeaderMatch instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPHeaderMatch build() {
      HTTPHeaderMatch buildable = new HTTPHeaderMatch(this.fluent.getName(), this.fluent.getType(), this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
