package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VolumeDeviceBuilder extends VolumeDeviceFluent implements VisitableBuilder {
   VolumeDeviceFluent fluent;

   public VolumeDeviceBuilder() {
      this(new VolumeDevice());
   }

   public VolumeDeviceBuilder(VolumeDeviceFluent fluent) {
      this(fluent, new VolumeDevice());
   }

   public VolumeDeviceBuilder(VolumeDeviceFluent fluent, VolumeDevice instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VolumeDeviceBuilder(VolumeDevice instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VolumeDevice build() {
      VolumeDevice buildable = new VolumeDevice(this.fluent.getDevicePath(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
