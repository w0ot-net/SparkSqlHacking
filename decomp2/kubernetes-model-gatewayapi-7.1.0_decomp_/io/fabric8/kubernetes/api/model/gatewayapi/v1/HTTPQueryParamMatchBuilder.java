package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPQueryParamMatchBuilder extends HTTPQueryParamMatchFluent implements VisitableBuilder {
   HTTPQueryParamMatchFluent fluent;

   public HTTPQueryParamMatchBuilder() {
      this(new HTTPQueryParamMatch());
   }

   public HTTPQueryParamMatchBuilder(HTTPQueryParamMatchFluent fluent) {
      this(fluent, new HTTPQueryParamMatch());
   }

   public HTTPQueryParamMatchBuilder(HTTPQueryParamMatchFluent fluent, HTTPQueryParamMatch instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPQueryParamMatchBuilder(HTTPQueryParamMatch instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPQueryParamMatch build() {
      HTTPQueryParamMatch buildable = new HTTPQueryParamMatch(this.fluent.getName(), this.fluent.getType(), this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
