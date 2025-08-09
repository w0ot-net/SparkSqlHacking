package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ServiceCIDRBuilder extends ServiceCIDRFluent implements VisitableBuilder {
   ServiceCIDRFluent fluent;

   public ServiceCIDRBuilder() {
      this(new ServiceCIDR());
   }

   public ServiceCIDRBuilder(ServiceCIDRFluent fluent) {
      this(fluent, new ServiceCIDR());
   }

   public ServiceCIDRBuilder(ServiceCIDRFluent fluent, ServiceCIDR instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ServiceCIDRBuilder(ServiceCIDR instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ServiceCIDR build() {
      ServiceCIDR buildable = new ServiceCIDR(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
