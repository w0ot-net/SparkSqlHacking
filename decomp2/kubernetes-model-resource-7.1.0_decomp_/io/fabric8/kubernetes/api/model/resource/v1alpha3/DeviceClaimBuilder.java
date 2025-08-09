package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeviceClaimBuilder extends DeviceClaimFluent implements VisitableBuilder {
   DeviceClaimFluent fluent;

   public DeviceClaimBuilder() {
      this(new DeviceClaim());
   }

   public DeviceClaimBuilder(DeviceClaimFluent fluent) {
      this(fluent, new DeviceClaim());
   }

   public DeviceClaimBuilder(DeviceClaimFluent fluent, DeviceClaim instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeviceClaimBuilder(DeviceClaim instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeviceClaim build() {
      DeviceClaim buildable = new DeviceClaim(this.fluent.buildConfig(), this.fluent.buildConstraints(), this.fluent.buildRequests());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
