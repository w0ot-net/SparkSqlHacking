package io.fabric8.kubernetes.api.model.storage.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VolumeNodeResourcesBuilder extends VolumeNodeResourcesFluent implements VisitableBuilder {
   VolumeNodeResourcesFluent fluent;

   public VolumeNodeResourcesBuilder() {
      this(new VolumeNodeResources());
   }

   public VolumeNodeResourcesBuilder(VolumeNodeResourcesFluent fluent) {
      this(fluent, new VolumeNodeResources());
   }

   public VolumeNodeResourcesBuilder(VolumeNodeResourcesFluent fluent, VolumeNodeResources instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VolumeNodeResourcesBuilder(VolumeNodeResources instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VolumeNodeResources build() {
      VolumeNodeResources buildable = new VolumeNodeResources(this.fluent.getCount());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
