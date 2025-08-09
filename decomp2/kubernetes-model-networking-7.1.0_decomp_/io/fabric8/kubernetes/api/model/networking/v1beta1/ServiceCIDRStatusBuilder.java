package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ServiceCIDRStatusBuilder extends ServiceCIDRStatusFluent implements VisitableBuilder {
   ServiceCIDRStatusFluent fluent;

   public ServiceCIDRStatusBuilder() {
      this(new ServiceCIDRStatus());
   }

   public ServiceCIDRStatusBuilder(ServiceCIDRStatusFluent fluent) {
      this(fluent, new ServiceCIDRStatus());
   }

   public ServiceCIDRStatusBuilder(ServiceCIDRStatusFluent fluent, ServiceCIDRStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ServiceCIDRStatusBuilder(ServiceCIDRStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ServiceCIDRStatus build() {
      ServiceCIDRStatus buildable = new ServiceCIDRStatus(this.fluent.getConditions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
