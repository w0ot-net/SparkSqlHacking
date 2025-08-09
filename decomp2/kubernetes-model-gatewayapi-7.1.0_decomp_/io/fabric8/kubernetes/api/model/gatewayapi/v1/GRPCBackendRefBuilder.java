package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GRPCBackendRefBuilder extends GRPCBackendRefFluent implements VisitableBuilder {
   GRPCBackendRefFluent fluent;

   public GRPCBackendRefBuilder() {
      this(new GRPCBackendRef());
   }

   public GRPCBackendRefBuilder(GRPCBackendRefFluent fluent) {
      this(fluent, new GRPCBackendRef());
   }

   public GRPCBackendRefBuilder(GRPCBackendRefFluent fluent, GRPCBackendRef instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GRPCBackendRefBuilder(GRPCBackendRef instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GRPCBackendRef build() {
      GRPCBackendRef buildable = new GRPCBackendRef(this.fluent.buildFilters(), this.fluent.getGroup(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getNamespace(), this.fluent.getPort(), this.fluent.getWeight());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
