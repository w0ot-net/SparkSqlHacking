package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ServiceReferenceBuilder extends ServiceReferenceFluent implements VisitableBuilder {
   ServiceReferenceFluent fluent;

   public ServiceReferenceBuilder() {
      this(new ServiceReference());
   }

   public ServiceReferenceBuilder(ServiceReferenceFluent fluent) {
      this(fluent, new ServiceReference());
   }

   public ServiceReferenceBuilder(ServiceReferenceFluent fluent, ServiceReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ServiceReferenceBuilder(ServiceReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ServiceReference build() {
      ServiceReference buildable = new ServiceReference(this.fluent.getName(), this.fluent.getNamespace(), this.fluent.getPort());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
