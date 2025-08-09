package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPPathMatchBuilder extends HTTPPathMatchFluent implements VisitableBuilder {
   HTTPPathMatchFluent fluent;

   public HTTPPathMatchBuilder() {
      this(new HTTPPathMatch());
   }

   public HTTPPathMatchBuilder(HTTPPathMatchFluent fluent) {
      this(fluent, new HTTPPathMatch());
   }

   public HTTPPathMatchBuilder(HTTPPathMatchFluent fluent, HTTPPathMatch instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPPathMatchBuilder(HTTPPathMatch instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPPathMatch build() {
      HTTPPathMatch buildable = new HTTPPathMatch(this.fluent.getType(), this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
