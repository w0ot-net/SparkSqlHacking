package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class BackendRefBuilder extends BackendRefFluent implements VisitableBuilder {
   BackendRefFluent fluent;

   public BackendRefBuilder() {
      this(new BackendRef());
   }

   public BackendRefBuilder(BackendRefFluent fluent) {
      this(fluent, new BackendRef());
   }

   public BackendRefBuilder(BackendRefFluent fluent, BackendRef instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public BackendRefBuilder(BackendRef instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public BackendRef build() {
      BackendRef buildable = new BackendRef(this.fluent.getGroup(), this.fluent.getKind(), this.fluent.getName(), this.fluent.getNamespace(), this.fluent.getPort(), this.fluent.getWeight());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
