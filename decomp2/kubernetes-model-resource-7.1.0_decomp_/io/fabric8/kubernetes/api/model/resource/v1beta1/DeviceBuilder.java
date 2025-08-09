package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DeviceBuilder extends DeviceFluent implements VisitableBuilder {
   DeviceFluent fluent;

   public DeviceBuilder() {
      this(new Device());
   }

   public DeviceBuilder(DeviceFluent fluent) {
      this(fluent, new Device());
   }

   public DeviceBuilder(DeviceFluent fluent, Device instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DeviceBuilder(Device instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Device build() {
      Device buildable = new Device(this.fluent.buildBasic(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
