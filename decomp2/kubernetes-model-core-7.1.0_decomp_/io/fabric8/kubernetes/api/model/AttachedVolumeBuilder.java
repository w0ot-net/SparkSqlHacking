package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AttachedVolumeBuilder extends AttachedVolumeFluent implements VisitableBuilder {
   AttachedVolumeFluent fluent;

   public AttachedVolumeBuilder() {
      this(new AttachedVolume());
   }

   public AttachedVolumeBuilder(AttachedVolumeFluent fluent) {
      this(fluent, new AttachedVolume());
   }

   public AttachedVolumeBuilder(AttachedVolumeFluent fluent, AttachedVolume instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AttachedVolumeBuilder(AttachedVolume instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AttachedVolume build() {
      AttachedVolume buildable = new AttachedVolume(this.fluent.getDevicePath(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
