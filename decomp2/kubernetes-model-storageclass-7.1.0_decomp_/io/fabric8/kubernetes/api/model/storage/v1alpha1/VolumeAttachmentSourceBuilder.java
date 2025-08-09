package io.fabric8.kubernetes.api.model.storage.v1alpha1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VolumeAttachmentSourceBuilder extends VolumeAttachmentSourceFluent implements VisitableBuilder {
   VolumeAttachmentSourceFluent fluent;

   public VolumeAttachmentSourceBuilder() {
      this(new VolumeAttachmentSource());
   }

   public VolumeAttachmentSourceBuilder(VolumeAttachmentSourceFluent fluent) {
      this(fluent, new VolumeAttachmentSource());
   }

   public VolumeAttachmentSourceBuilder(VolumeAttachmentSourceFluent fluent, VolumeAttachmentSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VolumeAttachmentSourceBuilder(VolumeAttachmentSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VolumeAttachmentSource build() {
      VolumeAttachmentSource buildable = new VolumeAttachmentSource(this.fluent.getInlineVolumeSpec(), this.fluent.getPersistentVolumeName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
