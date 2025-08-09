package io.fabric8.kubernetes.api.model.extensions;

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
      HTTPIngressPath buildable = new HTTPIngressPath(this.fluent.buildBackend(), this.fluent.getPath());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
