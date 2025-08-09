package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VsphereVirtualDiskVolumeSourceBuilder extends VsphereVirtualDiskVolumeSourceFluent implements VisitableBuilder {
   VsphereVirtualDiskVolumeSourceFluent fluent;

   public VsphereVirtualDiskVolumeSourceBuilder() {
      this(new VsphereVirtualDiskVolumeSource());
   }

   public VsphereVirtualDiskVolumeSourceBuilder(VsphereVirtualDiskVolumeSourceFluent fluent) {
      this(fluent, new VsphereVirtualDiskVolumeSource());
   }

   public VsphereVirtualDiskVolumeSourceBuilder(VsphereVirtualDiskVolumeSourceFluent fluent, VsphereVirtualDiskVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VsphereVirtualDiskVolumeSourceBuilder(VsphereVirtualDiskVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VsphereVirtualDiskVolumeSource build() {
      VsphereVirtualDiskVolumeSource buildable = new VsphereVirtualDiskVolumeSource(this.fluent.getFsType(), this.fluent.getStoragePolicyID(), this.fluent.getStoragePolicyName(), this.fluent.getVolumePath());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
