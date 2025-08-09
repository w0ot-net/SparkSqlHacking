package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeviceCapacityBuilder extends DeviceCapacityFluent implements VisitableBuilder {
   DeviceCapacityFluent fluent;

   public DeviceCapacityBuilder() {
      this(new DeviceCapacity());
   }

   public DeviceCapacityBuilder(DeviceCapacityFluent fluent) {
      this(fluent, new DeviceCapacity());
   }

   public DeviceCapacityBuilder(DeviceCapacityFluent fluent, DeviceCapacity instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeviceCapacityBuilder(DeviceCapacity instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DeviceCapacity build() {
      DeviceCapacity buildable = new DeviceCapacity(this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
