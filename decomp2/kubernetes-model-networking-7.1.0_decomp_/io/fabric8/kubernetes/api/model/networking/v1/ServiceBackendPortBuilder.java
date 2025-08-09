package io.fabric8.kubernetes.api.model.networking.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ServiceBackendPortBuilder extends ServiceBackendPortFluent implements VisitableBuilder {
   ServiceBackendPortFluent fluent;

   public ServiceBackendPortBuilder() {
      this(new ServiceBackendPort());
   }

   public ServiceBackendPortBuilder(ServiceBackendPortFluent fluent) {
      this(fluent, new ServiceBackendPort());
   }

   public ServiceBackendPortBuilder(ServiceBackendPortFluent fluent, ServiceBackendPort instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ServiceBackendPortBuilder(ServiceBackendPort instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ServiceBackendPort build() {
      ServiceBackendPort buildable = new ServiceBackendPort(this.fluent.getName(), this.fluent.getNumber());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
