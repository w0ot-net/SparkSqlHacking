package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeviceClaimConfigurationBuilder extends DeviceClaimConfigurationFluent implements VisitableBuilder {
   DeviceClaimConfigurationFluent fluent;

   public DeviceClaimConfigurationBuilder() {
      this(new DeviceClaimConfiguration());
   }

   public DeviceClaimConfigurationBuilder(DeviceClaimConfigurationFluent fluent) {
      this(fluent, new DeviceClaimConfiguration());
   }

   public DeviceClaimConfigurationBuilder(DeviceClaimConfigurationFluent fluent, DeviceClaimConfiguration instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeviceClaimConfigurationBuilder(DeviceClaimConfiguration instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeviceClaimConfiguration build() {
      DeviceClaimConfiguration buildable = new DeviceClaimConfiguration(this.fluent.buildOpaque(), this.fluent.getRequests());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
