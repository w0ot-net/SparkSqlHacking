package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPGetActionBuilder extends HTTPGetActionFluent implements VisitableBuilder {
   HTTPGetActionFluent fluent;

   public HTTPGetActionBuilder() {
      this(new HTTPGetAction());
   }

   public HTTPGetActionBuilder(HTTPGetActionFluent fluent) {
      this(fluent, new HTTPGetAction());
   }

   public HTTPGetActionBuilder(HTTPGetActionFluent fluent, HTTPGetAction instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPGetActionBuilder(HTTPGetAction instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPGetAction build() {
      HTTPGetAction buildable = new HTTPGetAction(this.fluent.getHost(), this.fluent.buildHttpHeaders(), this.fluent.getPath(), this.fluent.buildPort(), this.fluent.getScheme());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
