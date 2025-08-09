package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HTTPBackendRefBuilder extends HTTPBackendRefFluent implements VisitableBuilder {
   HTTPBackendRefFluent fluent;

   public HTTPBackendRefBuilder() {
      this(new HTTPBackendRef());
   }

   public HTTPBackendRefBuilder(HTTPBackendRefFluent fluent) {
      this(fluent, new HTTPBackendRef());
   }

   public HTTPBackendRefBuilder(HTTPBackendRefFluent fluent, HTTPBackendRef instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HTTPBackendRefBuilder(HTTPBackendRef instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HTTPBackendRef build() {
      HTTPBackendRef buildable = new HTTPBackendRef(this.fluent.buildFilters(), this.fluent.getGroup(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getNamespace(), this.fluent.getPort(), this.fluent.getWeight());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
