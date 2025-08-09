package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeviceRequestAllocationResultBuilder extends DeviceRequestAllocationResultFluent implements VisitableBuilder {
   DeviceRequestAllocationResultFluent fluent;

   public DeviceRequestAllocationResultBuilder() {
      this(new DeviceRequestAllocationResult());
   }

   public DeviceRequestAllocationResultBuilder(DeviceRequestAllocationResultFluent fluent) {
      this(fluent, new DeviceRequestAllocationResult());
   }

   public DeviceRequestAllocationResultBuilder(DeviceRequestAllocationResultFluent fluent, DeviceRequestAllocationResult instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeviceRequestAllocationResultBuilder(DeviceRequestAllocationResult instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeviceRequestAllocationResult build() {
      DeviceRequestAllocationResult buildable = new DeviceRequestAllocationResult(this.fluent.getAdminAccess(), this.fluent.getDevice(), this.fluent.getDriver(), this.fluent.getPool(), this.fluent.getRequest());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
