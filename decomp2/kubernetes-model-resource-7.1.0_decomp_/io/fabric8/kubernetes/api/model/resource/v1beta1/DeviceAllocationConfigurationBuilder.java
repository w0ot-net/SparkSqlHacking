package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeviceAllocationConfigurationBuilder extends DeviceAllocationConfigurationFluent implements VisitableBuilder {
   DeviceAllocationConfigurationFluent fluent;

   public DeviceAllocationConfigurationBuilder() {
      this(new DeviceAllocationConfiguration());
   }

   public DeviceAllocationConfigurationBuilder(DeviceAllocationConfigurationFluent fluent) {
      this(fluent, new DeviceAllocationConfiguration());
   }

   public DeviceAllocationConfigurationBuilder(DeviceAllocationConfigurationFluent fluent, DeviceAllocationConfiguration instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeviceAllocationConfigurationBuilder(DeviceAllocationConfiguration instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeviceAllocationConfiguration build() {
      DeviceAllocationConfiguration buildable = new DeviceAllocationConfiguration(this.fluent.buildOpaque(), this.fluent.getRequests(), this.fluent.getSource());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
