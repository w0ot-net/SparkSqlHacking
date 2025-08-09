package io.fabric8.kubernetes.api.model.networking.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IngressServiceBackendBuilder extends IngressServiceBackendFluent implements VisitableBuilder {
   IngressServiceBackendFluent fluent;

   public IngressServiceBackendBuilder() {
      this(new IngressServiceBackend());
   }

   public IngressServiceBackendBuilder(IngressServiceBackendFluent fluent) {
      this(fluent, new IngressServiceBackend());
   }

   public IngressServiceBackendBuilder(IngressServiceBackendFluent fluent, IngressServiceBackend instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IngressServiceBackendBuilder(IngressServiceBackend instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IngressServiceBackend build() {
      IngressServiceBackend buildable = new IngressServiceBackend(this.fluent.getName(), this.fluent.buildPort());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
