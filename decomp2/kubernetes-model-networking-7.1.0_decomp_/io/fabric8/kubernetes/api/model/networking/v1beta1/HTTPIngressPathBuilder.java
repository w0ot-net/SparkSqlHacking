package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPIngressPathBuilder extends HTTPIngressPathFluent implements VisitableBuilder {
   HTTPIngressPathFluent fluent;

   public HTTPIngressPathBuilder() {
      this(new HTTPIngressPath());
   }

   public HTTPIngressPathBuilder(HTTPIngressPathFluent fluent) {
      this(fluent, new HTTPIngressPath());
   }

   public HTTPIngressPathBuilder(HTTPIngressPathFluent fluent, HTTPIngressPath instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPIngressPathBuilder(HTTPIngressPath instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPIngressPath build() {
      HTTPIngressPath buildable = new HTTPIngressPath(this.fluent.buildBackend(), this.fluent.getPath(), this.fluent.getPathType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
