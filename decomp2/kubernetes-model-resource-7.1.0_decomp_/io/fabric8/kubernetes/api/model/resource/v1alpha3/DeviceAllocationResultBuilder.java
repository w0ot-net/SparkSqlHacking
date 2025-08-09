package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeviceAllocationResultBuilder extends DeviceAllocationResultFluent implements VisitableBuilder {
   DeviceAllocationResultFluent fluent;

   public DeviceAllocationResultBuilder() {
      this(new DeviceAllocationResult());
   }

   public DeviceAllocationResultBuilder(DeviceAllocationResultFluent fluent) {
      this(fluent, new DeviceAllocationResult());
   }

   public DeviceAllocationResultBuilder(DeviceAllocationResultFluent fluent, DeviceAllocationResult instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeviceAllocationResultBuilder(DeviceAllocationResult instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeviceAllocationResult build() {
      DeviceAllocationResult buildable = new DeviceAllocationResult(this.fluent.buildConfig(), this.fluent.buildResults());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
