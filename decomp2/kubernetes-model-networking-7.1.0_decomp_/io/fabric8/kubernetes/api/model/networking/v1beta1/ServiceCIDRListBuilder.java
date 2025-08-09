package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ServiceCIDRListBuilder extends ServiceCIDRListFluent implements VisitableBuilder {
   ServiceCIDRListFluent fluent;

   public ServiceCIDRListBuilder() {
      this(new ServiceCIDRList());
   }

   public ServiceCIDRListBuilder(ServiceCIDRListFluent fluent) {
      this(fluent, new ServiceCIDRList());
   }

   public ServiceCIDRListBuilder(ServiceCIDRListFluent fluent, ServiceCIDRList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ServiceCIDRListBuilder(ServiceCIDRList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ServiceCIDRList build() {
      ServiceCIDRList buildable = new ServiceCIDRList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
