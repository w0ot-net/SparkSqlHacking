package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AllocatedDeviceStatusBuilder extends AllocatedDeviceStatusFluent implements VisitableBuilder {
   AllocatedDeviceStatusFluent fluent;

   public AllocatedDeviceStatusBuilder() {
      this(new AllocatedDeviceStatus());
   }

   public AllocatedDeviceStatusBuilder(AllocatedDeviceStatusFluent fluent) {
      this(fluent, new AllocatedDeviceStatus());
   }

   public AllocatedDeviceStatusBuilder(AllocatedDeviceStatusFluent fluent, AllocatedDeviceStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AllocatedDeviceStatusBuilder(AllocatedDeviceStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AllocatedDeviceStatus build() {
      AllocatedDeviceStatus buildable = new AllocatedDeviceStatus(this.fluent.getConditions(), this.fluent.getData(), this.fluent.getDevice(), this.fluent.getDriver(), this.fluent.buildNetworkData(), this.fluent.getPool());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
