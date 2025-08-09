package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ServiceStatusBuilder extends ServiceStatusFluent implements VisitableBuilder {
   ServiceStatusFluent fluent;

   public ServiceStatusBuilder() {
      this(new ServiceStatus());
   }

   public ServiceStatusBuilder(ServiceStatusFluent fluent) {
      this(fluent, new ServiceStatus());
   }

   public ServiceStatusBuilder(ServiceStatusFluent fluent, ServiceStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ServiceStatusBuilder(ServiceStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ServiceStatus build() {
      ServiceStatus buildable = new ServiceStatus(this.fluent.buildConditions(), this.fluent.buildLoadBalancer());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
