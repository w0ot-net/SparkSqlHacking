package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ServiceCIDRSpecBuilder extends ServiceCIDRSpecFluent implements VisitableBuilder {
   ServiceCIDRSpecFluent fluent;

   public ServiceCIDRSpecBuilder() {
      this(new ServiceCIDRSpec());
   }

   public ServiceCIDRSpecBuilder(ServiceCIDRSpecFluent fluent) {
      this(fluent, new ServiceCIDRSpec());
   }

   public ServiceCIDRSpecBuilder(ServiceCIDRSpecFluent fluent, ServiceCIDRSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ServiceCIDRSpecBuilder(ServiceCIDRSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ServiceCIDRSpec build() {
      ServiceCIDRSpec buildable = new ServiceCIDRSpec(this.fluent.getCidrs());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
