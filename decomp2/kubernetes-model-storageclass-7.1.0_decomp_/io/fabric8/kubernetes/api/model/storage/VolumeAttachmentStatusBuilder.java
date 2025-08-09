package io.fabric8.kubernetes.api.model.storage;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class VolumeAttachmentStatusBuilder extends VolumeAttachmentStatusFluent implements VisitableBuilder {
   VolumeAttachmentStatusFluent fluent;

   public VolumeAttachmentStatusBuilder() {
      this(new VolumeAttachmentStatus());
   }

   public VolumeAttachmentStatusBuilder(VolumeAttachmentStatusFluent fluent) {
      this(fluent, new VolumeAttachmentStatus());
   }

   public VolumeAttachmentStatusBuilder(VolumeAttachmentStatusFluent fluent, VolumeAttachmentStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public VolumeAttachmentStatusBuilder(VolumeAttachmentStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public VolumeAttachmentStatus build() {
      VolumeAttachmentStatus buildable = new VolumeAttachmentStatus(this.fluent.buildAttachError(), this.fluent.getAttached(), this.fluent.getAttachmentMetadata(), this.fluent.buildDetachError());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
