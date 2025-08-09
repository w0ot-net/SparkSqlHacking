package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IngressBackendBuilder extends IngressBackendFluent implements VisitableBuilder {
   IngressBackendFluent fluent;

   public IngressBackendBuilder() {
      this(new IngressBackend());
   }

   public IngressBackendBuilder(IngressBackendFluent fluent) {
      this(fluent, new IngressBackend());
   }

   public IngressBackendBuilder(IngressBackendFluent fluent, IngressBackend instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IngressBackendBuilder(IngressBackend instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IngressBackend build() {
      IngressBackend buildable = new IngressBackend(this.fluent.getResource(), this.fluent.getServiceName(), this.fluent.buildServicePort());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
